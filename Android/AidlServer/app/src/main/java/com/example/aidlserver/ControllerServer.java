package com.example.aidlserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import com.example.myaidl.IMyAidlInterface;

public class ControllerServer extends Service {
    private static final String TAG = "ControllerServer";

//    private IMyAidlInterface.Stub mBinder = new IMyAidlInterface.Stub() {
//        @Override
//        public void sayHello() throws RemoteException {
//            Log.i(TAG, "Tad say 'Hello' from Server!");
//        }
//    };

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new IMyAidlInterface.Stub() {
            @Override
            public void sayHello() throws RemoteException {
                Log.d(TAG, "Server sayHello");
//                Toast.makeText(getApplicationContext(), "Hello", Toast.LENGTH_SHORT);
            }
        };
    }
}