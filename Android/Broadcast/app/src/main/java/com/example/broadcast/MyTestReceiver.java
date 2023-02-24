package com.example.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/** Đây là một "BroadcastReceiver" mới được tạo ra.
 * Với cái này thì nên dùng Android Studio để tạo thì sẽ an toàn hơn bởi vì nó sẽ tự động add vào Manifest
 * Phần khai báo sẽ được đặt ở "AndroidManifest.xml", hãy đọc ở đó để có thể biết thêm chi tiết
 */

public class MyTestReceiver extends BroadcastReceiver {
    String Tag = "[Tad_Zeilar]";

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        // Ok let do something in here like log.
        Log.d(Tag, "Receive Message $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
//        throw new UnsupportedOperationException("Not yet implemented");
    }

    /** Singleton for test Looper
     * */
//    private static MyTestReceiver singleObject = null;
//    public MyTestReceiver getInstance()
//    {
//        if(singleObject == null) {
//            singleObject = new MyTestReceiver();
//        }
//        return singleObject;
//    }
}