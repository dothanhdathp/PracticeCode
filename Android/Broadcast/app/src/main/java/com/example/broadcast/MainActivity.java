package com.example.broadcast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

class MainTheme {
    int mainTextColor = Color.parseColor("#000000");
    float H1 = 40.0f;
    float H2 = 30.0f;
    float H3 = 20.0f;
    float plainText = 15.0f;
};

public class MainActivity extends AppCompatActivity {
    static String Tag = "[Tad_Zeilar]";
    static String ClsTag = MainActivity.class.getSimpleName();
    // Struct keep element for setup environment
    private MainTheme theme = new MainTheme();
    MyTestReceiver myTestReceiver = null;
    MyTestReceiver myTestReceiver2 = null;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // registerReceiver get local intent
        Log.d(Tag, "On create UI :: " + getPackageName()); // getPackageName() = com.example.broadcast
        myTestReceiver = new MyTestReceiver();
        IntentFilter filter = new IntentFilter(getPackageName().toString() +".MY_DEFINED_ACTION");
        registerReceiver(myTestReceiver, filter);

        // registerReceiver get system intent
        myTestReceiver2 = new MyTestReceiver();
        IntentFilter filter2 = new IntentFilter("android.media.VOLUME_CHANGED_ACTION");
        registerReceiver(myTestReceiver2, filter2);

        createUI();
    }

    @Override
    protected void onStart() {
        Log.d(Tag, Tag);
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d(Tag, Tag);
        super.onResume();
    }

    @Override
    protected void onStop() {
        Log.d(Tag, Tag);
//        unregisterReceiver(myTestReceiver);
        super.onStop();
    }

    /** Create broadcast receiver
     * I don't know but what ever
     * */
    private void registerReceiver() {

    }

    /** Create Main UI
     * I'd like to create UI by code far than create via xml so i write this function.
     * In case you don't want to do that, please don't try.
     * Anyway you can do the both type if you want.
     * */
    private void createUI()
    {
        // Create the base form of UI
        /* Layout param */
        RelativeLayout.LayoutParams RLP_0 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        RelativeLayout.LayoutParams RLP_1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams RLP_2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        RelativeLayout.LayoutParams RLP_3 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        LinearLayout.LayoutParams LLP_0 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        LinearLayout.LayoutParams LLP_1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams LLP_2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        LinearLayout.LayoutParams LLP_3 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        final RelativeLayout Main_Layout = new RelativeLayout(MainActivity.this);
        LinearLayout Line_Layout_0 = new LinearLayout(MainActivity.this);
        Line_Layout_0.setOrientation(LinearLayout.VERTICAL);
        /* Create text = Service */
        TextView txtService = new TextView(MainActivity.this);
        txtService.setText("Server");
        txtService.setTextSize(theme.H1);
        txtService.setTextColor(theme.mainTextColor);
        txtService.setGravity(Gravity.CENTER);
        txtService.setLayoutParams(LLP_1);

        /* Create Editable text */
        EditText edit_text = new EditText(MainActivity.this);
        edit_text.setTextColor(theme.mainTextColor);
        edit_text.setLayoutParams(LLP_1);

        /* Create Button send text to client */
        Button sendButton = new Button(MainActivity.this);
        sendButton.setText("CLICK ME!");
        sendButton.setLayoutParams(LLP_1);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(getPackageName().toString() +".MY_DEFINED_ACTION");
                intent.putExtra("data", "Nothing to see here, move along.");
                sendBroadcast(intent);
            }
        });
        /* Add to Layout */
        Line_Layout_0.addView(txtService, 0);
        Line_Layout_0.addView(edit_text, 1);
        Line_Layout_0.addView(sendButton, 2);
        // Main_Layout.addView(Line_Layout_0);
        MainActivity.this.setContentView(Line_Layout_0);
    }
}