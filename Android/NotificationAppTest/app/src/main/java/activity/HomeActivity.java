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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
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

    private TextView AddTextView(String text) {
        TextView text_view = new TextView(this);
        text_view.setLayoutParams(Default.getInstance().newLayout(Default.LAYOUT_TEXTVIEW));
        text_view.setText(text);
        mMainLayout.addView(text_view);
        return text_view;
    }

    private Button AddButton(String content, View.OnClickListener event) {
        Button button = new Button(this);
        button.setLayoutParams(Default.getInstance().newLayout(Default.LAYOUT_BUTTON));
        button.setText(content);
        if(event==null) {
            button.setOnClickListener(defaultButtonClicked);
            return button;
        }
        button.setOnClickListener(event);
        mMainLayout.addView(button);
        return button;
    }

    private CheckBox AddCheckBox(String content, CompoundButton.OnCheckedChangeListener onCheck) {
        CheckBox check_box = new CheckBox(this);
        check_box.setLayoutParams(Default.getInstance().newLayout(Default.LAYOUT_CHECKBOX));
        check_box.setText(content);
        check_box.setOnCheckedChangeListener(onCheck);
        mMainLayout.addView(check_box);
        return check_box;
    }

    private void AddDropDownBox(String[] list) {
        Spinner spinner = new Spinner(this);
        spinner.setLayoutParams(Default.getInstance().newLayout(Default.LAYOUT_DROPLIST));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setScrollBarSize(60);
        mMainLayout.addView(spinner);
    }

    private void generateUI()
    {
        /** NOTE
         * This Zone show device environment which run test. In future when need check
         */
        AddTextView("Package Name: " + getApplicationContext().getPackageName());
        AddTextView("Screen size (WxH): " + TestInfo.getInstance().getScreenSize());
        AddTextView("AndroidOS: " + System.getProperty("os.version"));
        AddTextView("SDK: " + android.os.Build.VERSION.SDK);
        AddTextView("Device: " + android.os.Build.DEVICE);
        AddTextView("Model: " + android.os.Build.MODEL);
        AddTextView("Product: " + android.os.Build.PRODUCT);
        AddTextView("Version: " + TestFunction.getInstance().currentVersion());

        /** NOTE
         * This zone is button.
         * Click on button will do the function test
         * */
        AddDropDownBox(new String[]{"1", "2", "3"});
        AddDropDownBox(new String[]{"4", "5", "6"});
        AddDropDownBox(new String[]{"9", "8", "7"});

         /** NOTE
         * This zone is button.
         * Click on button will do the function test
         * */
        AddButton("Pokeball Icon", TestFunction.getInstance().onButtonNotification(
            "Pokeball",
            "[pokeball_icon] This is normal text",
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
            "This is new long text! long long long long long long long long long long long long long long long long long long long long long long long long long man!",
            R.drawable.cpp_icon
        ));

        // white-list
        for (String i : new String[]{
                "com.humaxdigital.automotive.radio",
                "com.humaxdigital.media.usbmusic",
                "com.humaxdigital.media.video",
                "com.humaxdigital.media.mymusic",
                "com.humaxdigital.media.myvideo",
                "com.humaxdigital.carplay",
                "com.humaxdigital.androidauto"
        }) {

        }
        /** NOTE
         * This zone is check button. When checked, this app will take an message and create notification base on test content
         * */
        AddTextView("Use command below to send MESSAGE_?");
        AddTextView("adb shell \"am broadcast -a MESSAGE_x --es sms_body 'adb' -n com.example.testapptemplate/receiver.AppReceiver\"");

        // -------------------- CHECKBOX -------------------- //
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