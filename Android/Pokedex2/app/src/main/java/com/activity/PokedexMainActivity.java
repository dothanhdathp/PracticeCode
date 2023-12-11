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
import com.data.PokedexServiceMessage;
import com.interfaces.IPkmMainServiceCallback;
import com.service.CSVDateParser;
import com.service.CommonValue;
import com.service.PokedexMainService;

import java.util.List;

public class PokedexMainActivity extends AppCompatActivity implements IPkmMainServiceCallback {
    private static String TAG = "PokedexMainActivity-" + CommonValue.getInstance().getOwnner();
    private PokedexMainService mPokedexMainService = null;
    private ServiceConnection mServiceConnection = null;

    private void init() {
        if(mServiceConnection == null) {
            mServiceConnection = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    Log.d(TAG, "onServiceConnected");
                    PokedexMainService.LocalBinder service_binder = (PokedexMainService.LocalBinder)iBinder;
                    mPokedexMainService = service_binder.getService();
                    mPokedexMainService.init(PokedexMainActivity.this);
                    PokedexListFragment.getInstance().init(mPokedexMainService);
                }

                @Override
                public void onServiceDisconnected(ComponentName componentName) {
                    Log.d(TAG, "onServiceDisconnected");
                    PokedexListFragment.getInstance().init(null);
                }
            };
        }

        /// Get permission read/write external storage
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
        Log.d(TAG, "onResume: ");
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

    /// *** Service callback *** ///
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
        startFragment(PokedexListFragment.getInstance());
    }

    public void onStartPokemonDetail(String data) {
        PokedexDetailFragment.getInstance().setDataInfo(data);
        startFragment(PokedexDetailFragment.getInstance());
    }

    public void startFragment(Fragment fragment) {
        if(fragment == null)
            return;

        String tag = fragment.getClass().getSimpleName();
        Fragment frm_check = getSupportFragmentManager().findFragmentByTag(tag);
        if(frm_check == null) {
            /// Fragment not exits, add
            getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_frame, fragment, tag)
                .addToBackStack(null)
                .commit();
        } else {
            getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_frame ,fragment, tag)
                .addToBackStack(null)
                .commit();
        }
    }
}