package broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class BCR extends BroadcastReceiver {
    private final String TAG="BCR";
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Receive!", Toast.LENGTH_SHORT).show();
        // Show info:
        Bundle b = intent.getExtras();
        String data[] = b.getStringArray("KEY");
        Log.d(TAG, "[TAD] >>> onReceive: ACTION_CUSTOM_TO_EVERYBODY (" + data.length + ")");
        for (String obj : data) {
            Log.d(TAG, "[TAD] <<< (b) getString : " + obj);
        }
    }
}
