package common;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class AppInfo {
    public static String OWNER = "[TZ]";

    private WindowManager mWindowManager = null;
    private Context mApplicationContext = null;

    private static AppInfo mInstance = null;
    private AppInfo() {};

    public static AppInfo getInstance() {
        if(mInstance==null) {
            mInstance = new AppInfo();
        }
        return mInstance;
    }

    public WindowManager getWindowManager() {
        return mWindowManager;
    }

    public void setWindowManager(WindowManager w) {
        mWindowManager = w;
    }
    public Context getApplicationContext() {
        return mApplicationContext;
    }

    public void setApplicationContext(Context context) {
        mApplicationContext = context;
    }
}