package activity;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.broadcastreceiverapp.R;

import broadcastreceiver.BCR;

public class HomeActivity extends AppCompatActivity {
    private final String TAG = "HomeActivity";

    BCR listener = null;
    IntentFilter intentFilter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        this.listener = new BCR();
        this.intentFilter = new IntentFilter("ACTION_CUSTOM_TO_EVERYBODY");
        registerReceiver(listener, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

//public class MainActivity extends AppCompatActivity {
//    private final String TAG = "[TAD-"+this.getClass().getSimpleName()+"]";
//
//    MessageReceiver listener = null;
//    IntentFilter intentFilter = null;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        // 1. Create listener
//        this.listener = new MessageReceiver();
//        // 2. Create IntentFilter with intent 'android.media.VOLUME_CHANGED_ACTION'
//        this.intentFilter = new IntentFilter("android.media.VOLUME_CHANGED_ACTION");
//        // 3. register receiver to system
//        registerReceiver(listener, intentFilter);
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        unregisterReceiver(listener);
//    }
//}