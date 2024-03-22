package com.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import com.example.broadcastsender.R;

public class HomeActivity extends AppCompatActivity {
    private final String TAG = "HomeActivity";
    private LinearLayout mMainLayout = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mMainLayout = (LinearLayout)findViewById(R.id.main_layout);
        createUI();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void createUI() {
        // Create layout
        LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        // Create Item
        Button button = new Button(this);
        button.setLayoutParams(layout);
        button.setText("LOCK");
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent();
                        i.setAction("ACTION_SET_RANK");

                        Bundle b = new Bundle();
                        String data[] = {getApplication().getPackageName(), "com.example.aidlnotificationtest", "LOCK"};
                        b.putStringArray(".EXTRA_KEY", data);
                        // Check
                        i.putExtras(b);
                        sendBroadcast(i);
                        Log.d(TAG, "[TADZEILA] onClick: Send ACTION_SET_RANK (" + data.length + ")");
                    }
                }
        );
        // Add
        mMainLayout.addView(button);

        LinearLayout.LayoutParams layout2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        // Create Item
        Button button2 = new Button(this);
        button2.setLayoutParams(layout2);
        button2.setText("UNLOCK");
        button2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent();
                        i.setAction("ACTION_SET_RANK");

                        Bundle b = new Bundle();
                        String data[] = {getApplication().getPackageName(), "com.example.aidlnotificationtest", "UNLOCK"};
                        b.putStringArray(".EXTRA_KEY", data);
                        // Check
                        i.putExtras(b);
                        sendBroadcast(i);
                        Log.d(TAG, "[TADZEILA] onClick: Send ACTION_SET_RANK (" + data.length + ")");
                    }
                }
        );
        // Add
        mMainLayout.addView(button2);
    }
}

