package receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import common.AppInfo;
import javaclass.TestFunction;

//adb shell "am broadcast -a MESSAGE_1 --es sms_body 'test from adb' -n com.example.aidlnotificationtest/receiver.AppReceiver"
//adb shell "am broadcast -a MESSAGE_2 --es sms_body 'test from adb' -n com.example.aidlnotificationtest/receiver.AppReceiver"
//adb shell "am broadcast -a MESSAGE_3 --es sms_body 'test from adb' -n com.example.aidlnotificationtest/receiver.AppReceiver"
//adb shell "am broadcast -a MESSAGE_4 --es sms_body 'test from adb' -n com.example.aidlnotificationtest/receiver.AppReceiver"

public class AppReceiver extends BroadcastReceiver {
    final String TAG = AppInfo.OWNER + "[AppReceiver]";
    @Override
    public void onReceive(Context context, Intent intent) {
        String mess = intent.getAction().toString();
        Log.d(TAG, "onReceive: " + mess);
        if(mess.equals("MESSAGE_TEST")) {
            Log.d(TAG, "MESSAGE_TEST is test_message, no action!");
        } else {
            switch (mess) {
                case "MESSAGE_1":
                case "MESSAGE_2":
                case "MESSAGE_3":
                case "MESSAGE_4":
                    TestFunction.getInstance().onListen(mess);
                    break;
                // WHITE_LIST_MESSAGE
                case "MESSAGE_ANDROID_AUTO":
                case "MESSAGE_ANDROID_CARPLAY":
                    break;
                // BLACK_LIST_MESSAGE
                case "MESSAGE_BLACK_1":
                case "MESSAGE_BLACK_2":
                default:
                    break;
            }
        }
    }
}