package receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import common.AppInfo;
import javaclass.TestFunction;

//adb shell "am broadcast -a MESSAGE_1 --es sms_body 'test from adb' -n com.example.testapptemplate/receiver.AppReceiver"
//adb shell "am broadcast -a MESSAGE_2 --es sms_body 'test from adb' -n com.example.testapptemplate/receiver.AppReceiver"
//adb shell "am broadcast -a MESSAGE_3 --es sms_body 'test from adb' -n com.example.testapptemplate/receiver.AppReceiver"
//adb shell "am broadcast -a MESSAGE_4 --es sms_body 'test from adb' -n com.example.testapptemplate/receiver.AppReceiver"

public class AppReceiver extends BroadcastReceiver {
    final String TAG = AppInfo.OWNER + "[AppReceiver]";
    @Override
    public void onReceive(Context context, Intent intent) {
        String mess = intent.getAction().toString();
        if(mess.equals("MESSAGE_TEST")) {
//            TestFunction.getInstance().makeNotification(
//        "MESSAGE_TEST",
//                intent.getDataString(),
//                  to do late
//            );
        } else {
            Log.d(TAG, "onReceive: " + mess);
            TestFunction.getInstance().onListen(mess);
        }
    }
}