package com.activity.pokedex2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.activity.PokedexDetailFragment;
import com.activity.PokedexListFragment;
import com.activity.PokedexLoadingFragment;
import com.data.PokedexServiceMessage;
import com.data.PokedexData;
import com.interfaces.IPkmMainServiceCallback;
import com.service.AppResourceManager;
import com.service.CommonValue;
import com.service.PokedexMainService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

public class PokedexMainActivity extends AppCompatActivity implements IPkmMainServiceCallback {
    private static String TAG = "PokedexMainActivity-" + CommonValue.Arthur;
    private PokedexMainService mPokedexMainService = null;
    private ServiceConnection mServiceConnection = null;
    private HashMap<String, Fragment> mFragmentTable = new HashMap<String, Fragment>();
    private final String FRAGMENT_LOADING = PokedexLoadingFragment.getInstance().getClass().getSimpleName();
    private final String FRAGMENT_LIST    = PokedexListFragment.getInstance().getClass().getSimpleName();
    private final String FRAGMENT_DETAIL  = PokedexDetailFragment.getInstance().getClass().getSimpleName();
    private String mLastScreen = "NO_SCREEN";

    private void init() {
//        String data = this.getString(R.string.Gen1);
        if(mServiceConnection == null) {
            mServiceConnection = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    Log.d(TAG, "onServiceConnected");
                    PokedexMainService.LocalBinder service_binder = (PokedexMainService.LocalBinder)iBinder;
                    mPokedexMainService = service_binder.getService();
                    // Give context to service
                    mPokedexMainService.init(PokedexMainActivity.this);
                    PokedexListFragment.getInstance().init(mPokedexMainService, PokedexMainActivity.this);
                    PokedexDetailFragment.getInstance().init(mPokedexMainService, PokedexMainActivity.this);
                }

                @Override
                public void onServiceDisconnected(ComponentName componentName) {
                    Log.d(TAG, "onServiceDisconnected");
                    PokedexListFragment.getInstance().init(null, null);
                    PokedexDetailFragment.getInstance().init(null, null);
                }
            };
        }

        /// Check permission read/write external storage
        try {
            int permission_read = this.checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE);
            if(permission_read != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
            }

            int permission_write = this.checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if(permission_write != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
            }

            Log.d(TAG, "[init/permission] READ: " + ((permission_read==0)?"TRUE":"FALSE") + " | WRITE: " + ((permission_write==0)?"TRUE":"FALSE"));
        } catch (IllegalArgumentException ig) {
            Log.d(TAG, "[init/permission] error: " + ig.getMessage());
        }

        /// Bind connection with Service
        Log.d(TAG, "Bind connection with Service ... ");
        Intent mServiceIntent = new Intent(this, PokedexMainService.class);
        bindService(mServiceIntent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokedex_main);
        mFragmentTable.put(PokedexLoadingFragment.getInstance().getClass().getSimpleName(), PokedexLoadingFragment.getInstance());
        mFragmentTable.put(PokedexListFragment.getInstance().getClass().getSimpleName(), PokedexListFragment.getInstance());
        mFragmentTable.put(PokedexDetailFragment.getInstance().getClass().getSimpleName(), PokedexDetailFragment.getInstance());
        AppResourceManager.getInstance().init(this.getApplicationContext(), getResources());
        startFragment(FRAGMENT_LOADING);
        init();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: mLastScreen = " + mLastScreen);
        /// Call last fragment
        startFragment(mLastScreen);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
        unbindService(mServiceConnection);
        mServiceConnection = null;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.d(TAG, "onConfigurationChanged: Landscape");
            // When land scape, update layout of app
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Log.d(TAG, "onConfigurationChanged: Portrait");
            // When land scape, update layout of app
        }
    }
    /// ********** Service callback ********** ///
    public void onCallLog(String log_test) {
        Log.d(TAG, "onCallLog: " + log_test);
    }

    public void onServiceReady() {
        if(mPokedexMainService.isDataReady()) {

        } else {
            mPokedexMainService.request(PokedexServiceMessage.MSG_REQUEST_GETLIST);
        }
    }

    public void onLoadList() {
        PokedexListFragment.getInstance().setListData(mPokedexMainService.getPkmList());
        startFragment(FRAGMENT_LIST);
    }

    public void onStartPokemonDetail(String data) {
        PokedexDetailFragment.getInstance().setDataInfo(data);
        startFragment(FRAGMENT_DETAIL);
    }

    public void onReleaseScreen(String screen) {
        if (screen.equals(FRAGMENT_DETAIL)) {
            mLastScreen = FRAGMENT_LIST;
        }
    };

    public void onUpdateSearchList() {
        PokedexListFragment.getInstance().setSearchList(mPokedexMainService.getSearchPkmList());
    };

    /// ********** Service callback ********** ///

    public void startFragment(String tag) {
        Fragment fragment = mFragmentTable.get(tag);
        if(fragment == null) {
            Log.d(TAG, "startFragment: Not have screen " + tag);
            return;
        }

        if(mLastScreen.equals("NO_SCREEN")) {
            getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_frame, fragment, tag)
                .addToBackStack(null)
                .commit();
        } else {
            getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_frame, fragment, tag)
                .addToBackStack(null)
                .commit();
        }
        mLastScreen = tag;
    }

    public void onUpdatePokemonImage(int imageID) {
        PokedexDetailFragment.getInstance().updateImage(imageID);
    }
}