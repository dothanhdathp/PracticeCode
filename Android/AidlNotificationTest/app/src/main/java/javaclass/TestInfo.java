package javaclass;

import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.os.Build;

import com.example.aidlnotificationtest.R;

import common.AppInfo;

public class TestInfo
{
    private final String TAG = AppInfo.OWNER+"TestInfo";
    private TestInfo() {};
    private static TestInfo mInstance = null;

    public static TestInfo getInstance() {
        if(mInstance==null) {
            mInstance = new TestInfo();
        }
        return mInstance;
    }

    public String getScreenSize() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        AppInfo.getInstance().getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        return (String.valueOf(width) + "x" + String.valueOf(height));
    }

//    private String[] TestServiceNameArray = null;
//    public String[] getServicesList() {
//        if(TestServiceNameArray == null) {
//            Resources r = AppInfo.getInstance().getApplicationContext().getResources();
//            XmlResourceParser parser = r.getXml(R.xml.sevic_names);
//        }
//    }
}