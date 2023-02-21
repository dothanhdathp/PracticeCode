package com.example.pokedex;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PersistableBundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    /* For debug */
    private static final String Tag = "PKD";
    /* Service */
    NationalDexService mNDexService = null;
    /* Adapter */
    private boolean isNDexServiceBounded = false;
    /* Item */
    ListView pkmList = null;

//    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pkmList = (ListView)findViewById(R.id.list_pokemon);
//        WindowManager wmgr = (WindowManager)MainActivity.this.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
//        DisplayMetrics metrics = new DisplayMetrics();
//        wmgr.getDefaultDisplay().getRealMetrics(metrics);
//        int  widthOfScreen = (int) (metrics.widthPixels / metrics.density);
//        int  heightOfScreen = (int) (metrics.heightPixels / metrics.density);
//        Log.d(Tag, "xxxxx ----- widthOfScreen  == " + String.valueOf(widthOfScreen));
//        Log.d(Tag, "xxxxx ----- heightOfScreen == " + String.valueOf(heightOfScreen));
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Connect to service
        Intent intent = new Intent(this, NationalDexService.class);
        bindService(intent, NationalDexServiceConnection, Context.BIND_AUTO_CREATE);

        pkmList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
                Log.d(Tag, "The selected item is : " + selectedItem);
                // Set selected idx
                mNDexService.setSelectedPkmInList(position);
                Intent newIntent = new Intent(MainActivity.this, PkmDetailActivity.class);
                newIntent.putExtra("pkm_name", selectedItem);
                newIntent.putExtra("pkm_id", Integer.toString(position));
                view.getContext().startActivity(newIntent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    /** Defines callbacks for service binding, passed to bindService()
     * This service use for list data in list screen
     * */
    private ServiceConnection NationalDexServiceConnection = new ServiceConnection()
    {
        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            NationalDexService.LocalBinder binder = (NationalDexService.LocalBinder)service;
            mNDexService = binder.getService();
            isNDexServiceBounded = true;
            MainActivity.this.onBindingSuggest();
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            isNDexServiceBounded = false;
            MainActivity.this.onBindingFalse();
        }
    };

    /** Local Method **/
    private void onBindingSuggest() {
        List<String> pkm_list = mNDexService.getListPkm();
        ArrayAdapter<String> metadataAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, pkm_list);
        pkmList.setAdapter(metadataAdapter);
    }

    private void onBindingFalse() { /*do nothing*/ }

    public static int getLineNumber() {
        return new Throwable().getStackTrace()[0].getLineNumber();
    }
}