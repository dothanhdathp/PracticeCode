package com.example.aidlclient;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myaidl.IMyAidlInterface;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "[TAD-Client]";
    private IMyAidlInterface aidlInterface = null;
    private Button btn;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            aidlInterface = IMyAidlInterface.Stub.asInterface(service);
            Log.d(TAG, "--- onServiceConnected ---");
            btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onServerSayHello();
                    }
                }
            );
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "--- onServiceDisconnected ---");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button)findViewById(R.id.button);
        bindToService();
    }

    private void bindToService() {
        boolean res = false;
        try {
            Intent intent = new Intent("MyAidlService.AIDL");
            Log.d(TAG, "intent = " + ((intent == null) ? "null" : "not null") );
            res = this.bindService(convertImplicitIntentToExplicitIntent(intent), connection, Context.BIND_AUTO_CREATE);
        } catch (Exception e) {
            Log.d(TAG, " dead on bind service");
        }
        Log.d(TAG, "bindToService: " + (res ? "SUGGESTED": "FAILED"));
    }

    private void onServerSayHello() {
        if(null==aidlInterface) {
            Log.d(TAG, "onServerSayHello: aidlInterface == null");
        } else {
            try {
                aidlInterface.sayHello();
            } catch (RemoteException e) {
                Log.e(TAG, "onServiceConnected: " + e.toString());
                throw new RuntimeException(e);
            }
        }
    }

    public Intent convertImplicitIntentToExplicitIntent(Intent implicitIntent) {
        PackageManager pm = getPackageManager();
        List<ResolveInfo> resolveInfoList = pm.queryIntentServices(implicitIntent, 0);

        if (resolveInfoList == null || resolveInfoList.size() != 1) {
            return null;
        }
        ResolveInfo serviceInfo = resolveInfoList.get(0);
        ComponentName component = new ComponentName(serviceInfo.serviceInfo.packageName, serviceInfo.serviceInfo.name);
        Intent explicitIntent = new Intent(implicitIntent);
        explicitIntent.setComponent(component);
        return explicitIntent;
    }
}