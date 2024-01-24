package receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import common.AppInfo;
import javaclass.TestFunction;

//adb shell "am broadcast -a NOTIFY1 --es sms_body 'test from adb' -n com.example.testapptemplate/receiver.AppReceiver"
//adb shell "am broadcast -a NOTIFY2 --es sms_body 'test from adb' -n com.example.testapptemplate/receiver.AppReceiver"
//adb shell "am broadcast -a NOTIFY3 --es sms_body 'test from adb' -n com.example.testapptemplate/receiver.AppReceiver"
//adb shell "am broadcast -a NOTIFY4 --es sms_body 'test from adb' -n com.example.testapptemplate/receiver.AppReceiver"

public class AppReceiver extends BroadcastReceiver {
    final String TAG = AppInfo.OWNER + "[AppReceiver]";
    @Override
    public void onReceive(Context context, Intent intent) {
        String mess = intent.getAction().toString();
        Log.d(TAG, "onReceive: " + mess);
        TestFunction.getInstance().onListen(mess);
    }
}