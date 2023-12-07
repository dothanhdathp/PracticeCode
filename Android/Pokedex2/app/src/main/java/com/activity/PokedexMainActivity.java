package com.activity.pokedex2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

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

import com.service.CSVDateParser;
import com.service.CommonValue;
import com.service.PokedexMainService;

public class PokedexMainActivity extends AppCompatActivity {
    private static String TAG = "PokedexMainActivity-" + CommonValue.getInstance().getOwnner();
    private PokedexMainService mPokedexMainService = null;

    private ServiceConnection mServiceConnection = null;

    private void init() {
        if(mServiceConnection == null) {
            mServiceConnection = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    Log.d(TAG, "TAD_ZEILA onServiceConnected");
                    PokedexMainService.LocalBinder service_binder = (PokedexMainService.LocalBinder)iBinder;
                    mPokedexMainService = service_binder.getService();
                }

                @Override
                public void onServiceDisconnected(ComponentName componentName) {
                    Log.d(TAG, "TAD_ZEILA onServiceDisconnected");
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
        setContentView(R.layout.activity_pokedex_main);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "[onCreate]");
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
//        CSVDateParser data = new CSVDateParser();
//        data.readData();
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
}