package com.example.pokedex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import android.widget.ListView;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    /* For debug */
    private static final String Tag = "MainActivity";

    /* Service */
    MainService mMainService = null;
    Intent mServiceIntent = null;
    Messenger mServiceMessenger = null;
    Handler mHandler = null;
    /* Adapter */
    private boolean mIsBound = false;
    /* Item */
    ListView pkmList = null;

    /** Defines callbacks for service binding, passed to bindService()
     * This service use for list data in list screen
     * */
    private ServiceConnection mServiceConnection = new ServiceConnection()
    {
        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            /// Get Service
            MainService.LocalBinder binder = (MainService.LocalBinder)service;
            mMainService = binder.getService();
            /// Set handler
            mIsBound = true;
            MainActivity.this.onBindingSuggest();
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mIsBound = false;
            mMainService = null;
            MainActivity.this.onBindingFalse();
        }
    };

    /** Local Method **/
    private void onBindingSuggest() {
        Toast.makeText(getApplicationContext(), "On Binding Service Suggest", Toast.LENGTH_SHORT).show();
        mMainService.callBackMainActivity();
    }

    private void onBindingFalse() {
        Toast.makeText(getApplicationContext(), "On Binding Service False", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mServiceIntent = new Intent(this, MainService.class);
        bindService(mServiceIntent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    protected void saySomething() {
        Toast.makeText(getApplicationContext(), "Hello World", Toast.LENGTH_SHORT).show();
    }
}