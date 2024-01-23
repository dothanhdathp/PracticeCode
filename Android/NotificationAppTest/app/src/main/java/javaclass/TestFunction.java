package javaclass;

import android.app.NotificationManager;

import common.AppInfo;

public class TestFunction {
    private final String TAG = AppInfo.OWNER+"TestFunction";
    private TestFunction() {};
    private static TestFunction mInstance = null;

    public static TestFunction getInstance() {
        if(mInstance==null) {
            mInstance = new TestFunction();
        }
        return mInstance;
    }

    public void sendNotification() {
//        NotificationManager NotificationManager
    }
}
