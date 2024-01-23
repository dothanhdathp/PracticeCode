package activity;

import common.AppInfo;
import javaclass.TestInfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.testapptemplate.R;


public class HomeActivity extends AppCompatActivity {
    private final String TAG = AppInfo.OWNER+"HomeActivity";
    LinearLayout mMainLayout = null;
    Context mContext = null;
    View.OnClickListener defaultButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button btn = (Button)v;
            Log.d(TAG, "onClick: Clicked item " + btn.getText().toString());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        AppInfo.getInstance().setApplicationContext(getApplicationContext());
        AppInfo.getInstance().setWindowManager(getWindowManager());
        mMainLayout = (LinearLayout)findViewById(R.id.main_layout);
        generateUI();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    private void AddTextView(String text) {
        TextView text_view = new TextView(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.CENTER_VERTICAL;
        lp.topMargin = 10;
        text_view.setLayoutParams(lp);
        text_view.setText(text);
        mMainLayout.addView(text_view);
    }

    private void AddButton(String content, View.OnClickListener event) {
        Button button = new Button(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL;
        lp.topMargin = 10;
        button.setLayoutParams(lp);
        button.setText(content);
        if(event!=null) {
            button.setOnClickListener(event);
        } else {
            button.setOnClickListener(defaultButtonClicked);
        }
        mMainLayout.addView(button);
    }

    private void generateUI()
    {
        AddTextView("Screen size (WxH): " + TestInfo.getInstance().getScreenSize());
        AddTextView("AndroidOS: " + System.getProperty("os.version"));
        AddTextView("SDK: " + android.os.Build.VERSION.SDK);
        AddTextView("Device: " + android.os.Build.DEVICE);
        AddTextView("Model: " + android.os.Build.MODEL);
        AddTextView("Product: " + android.os.Build.PRODUCT);

        AddButton("Button 1", null);
        AddButton("Button 2", null);
        AddButton("Button 3", null);
    }
}