package activity;

import common.AppInfo;
import javaclass.TestFunction;
import javaclass.TestInfo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.testapptemplate.R;


public class HomeActivity extends AppCompatActivity {
    private final String TAG = AppInfo.OWNER+"HomeActivity";
    private final int TOP_MARGIN = 5;

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
        AppInfo.getInstance().addDrawAbleId("pkm_icon", R.drawable.pkb);
        AppInfo.getInstance().addDrawAbleId("cpp_icon", R.drawable.cpp);
        this.getSystemService(NotificationManager.class);
        mMainLayout = (LinearLayout)findViewById(R.id.main_layout);
        generateUI();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        TestFunction.getInstance().unlistenningAll();
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
        lp.topMargin = TOP_MARGIN;
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
        lp.topMargin = TOP_MARGIN;
        button.setLayoutParams(lp);
        button.setText(content);
        if(event!=null) {
            button.setOnClickListener(event);
        } else {
            button.setOnClickListener(defaultButtonClicked);
        }
        mMainLayout.addView(button);
    }

    private void AddCheckBox(String content, CompoundButton.OnCheckedChangeListener onCheck) {
        CheckBox checkBox = new CheckBox(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.CENTER_VERTICAL;
        lp.topMargin = TOP_MARGIN;
        checkBox.setLayoutParams(lp);
        checkBox.setText(content);
        checkBox.setOnCheckedChangeListener(onCheck);
        mMainLayout.addView(checkBox);
    }

    private void generateUI()
    {
        AddTextView("Package Name: " + getApplicationContext().getPackageName());
        AddTextView("Screen size (WxH): " + TestInfo.getInstance().getScreenSize());
        AddTextView("AndroidOS: " + System.getProperty("os.version"));
        AddTextView("SDK: " + android.os.Build.VERSION.SDK);
        AddTextView("Device: " + android.os.Build.DEVICE);
        AddTextView("Model: " + android.os.Build.MODEL);
        AddTextView("Product: " + android.os.Build.PRODUCT);
        AddTextView("Version: " + TestFunction.getInstance().currentVersion());

        AddButton("Pokeball Icon", TestFunction.getInstance().onButtonNotification(
            "Pokeball",
            "[pokeball_icon] Hello this is fucking long long long long long long long long long length text",
            R.drawable.pkm_icon
        ));

        AddButton("Cpp Icon", TestFunction.getInstance().onButtonNotification(
            "CPP",
            "[cpp_icon] Hello this is fucking long long long long long long long long long length text and doublt :)) Hello this is fucking long long long long long long long long long length text",
            R.drawable.cpp_icon
        ));

        AddButton("Plain text 1 no icon", TestFunction.getInstance().onButtonNotification(
            null,
            "This is plain text only",
            R.drawable.pkm_icon
        ));

        AddButton("Plain text 2 no icon", TestFunction.getInstance().onButtonNotification(
            null,
            "This is new plain text!",
            R.drawable.cpp_icon
        ));

        AddTextView("Use command below to send MESSAGE_?");
        AddTextView("adb shell \"am broadcast -a MESSAGE_x --es sms_body 'adb' -n com.example.testapptemplate/receiver.AppReceiver\"");

        AddCheckBox(
            "Broadcast MESSAGE_1 = 'Pokeball Icon'",
            TestFunction.getInstance().creatListener(
                "MESSAGE_1",
                "Pokeball",
                "pokeball_icon",
                R.drawable.pkm_icon
            )
        );

        AddCheckBox(
            "Broadcast MESSAGE_2 = 'Cpp Icon'",
            TestFunction.getInstance().creatListener(
                "MESSAGE_2",
                "CPP",
                "cpp_icon",
                R.drawable.cpp_icon
            )
        );
        AddCheckBox(
            "Broadcast MESSAGE_3 = 'Plain text 1 no icon'",
            TestFunction.getInstance().creatListener(
                "MESSAGE_3",
                null,
                "This is plain text only",
                R.drawable.pkm_icon
            )
        );
        AddCheckBox(
            "Broadcast MESSAGE_4 = 'Plain text 2 no icon'",
            TestFunction.getInstance().creatListener(
                "MESSAGE_4",
                null,
                "This is new plain text!",
                R.drawable.cpp_icon
            )
        );
    }
}