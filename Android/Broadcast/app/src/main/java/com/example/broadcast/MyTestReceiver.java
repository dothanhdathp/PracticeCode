package com.example.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

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
        Log.d(Tag, "Message received from " + intent.getAction());
        String messTransfer = intent.getExtras().getString("data");
        Toast.makeText(context, "Message: \""+messTransfer+"\"", Toast.LENGTH_SHORT).show();

        if(false) {
            throw new UnsupportedOperationException("Not yet implemented");
        }
    }
}