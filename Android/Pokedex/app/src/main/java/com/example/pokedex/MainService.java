/*
MainService used by MainAcivity
I want to make it as
* */

package com.example.pokedex;

import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.os.Binder;
import android.os.IBinder;
import android.os.Messenger;

import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class MainService extends Service {
    final String Tag = "MainService";
    private final IBinder mLocalBinder = new MainService.LocalBinder();
    private MainActivity mMainActivity = null;

    public class LocalBinder extends Binder {
        MainService getService() {
            return MainService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mLocalBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public void setActivity(MainActivity activity_main_class) {
        mMainActivity = activity_main_class;
    }

    public void callBackMainActivity() {
        mMainActivity.saySomething();
    }
}
