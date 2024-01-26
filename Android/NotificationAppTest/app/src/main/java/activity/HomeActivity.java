package activity;

import common.AppInfo;
import javaclass.TestData;
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
import android.widget.AdapterView;
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

    private void AddTextView(String text, int item_style, int layout_style) {
        TextView text_view = UiStyle.getInstance().newTextViewStyle(item_style, layout_style);
        text_view.setText(text);
        mMainLayout.addView(text_view);
    }

    private void AddButton(String content, View.OnClickListener event, int item_style, int layout_style) {
//        Button button = UiStyle.getInstance().newButtonStyle(layout_style, UiStyle.LAYOUT_STYLE_2);
        Button button = UiStyle.getInstance().newButtonStyle(UiStyle.STYLE_4, UiStyle.LAYOUT_STYLE_2);
        button.setText(content);
        if(event==null) {
            button.setOnClickListener(defaultButtonClicked);
        }
        button.setOnClickListener(event);
        mMainLayout.addView(button);
    }

    private void AddCheckBox(String content, CompoundButton.OnCheckedChangeListener onCheck, int item_style, int layout_style) {
        CheckBox check_box = UiStyle.getInstance().newCheckBoxStyle(item_style, layout_style);
        check_box.setText(content);
        check_box.setOnCheckedChangeListener(onCheck);
        mMainLayout.addView(check_box);
    }

    private void AddDropDownBox(String[] list, AdapterView.OnItemSelectedListener event, int item_style, int layout_style) {
        Spinner spinner = UiStyle.getInstance().newSpinnerStyle(item_style, layout_style);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(event);
        mMainLayout.addView(spinner);
    }

    private void generateUI()
    {
        // HEADER
        AddTextView("Device Infomation", UiStyle.STYLE_2, UiStyle.LAYOUT_STYLE_2);
        /** NOTE
         * This Zone show device environment which run test. In future when need check
         */
        AddTextView("Package Name: " + getApplicationContext().getPackageName(),
                UiStyle.STYLE_1, UiStyle.LAYOUT_STYLE_1);
        AddTextView("Screen size (WxH): " + TestInfo.getInstance().getScreenSize(),
                UiStyle.STYLE_1, UiStyle.LAYOUT_STYLE_1);
        AddTextView("AndroidOS: " + System.getProperty("os.version"),
                UiStyle.STYLE_1, UiStyle.LAYOUT_STYLE_1);
        AddTextView("SDK: " + android.os.Build.VERSION.SDK,
                UiStyle.STYLE_1, UiStyle.LAYOUT_STYLE_1);
        AddTextView("Device: " + android.os.Build.DEVICE,
                UiStyle.STYLE_1, UiStyle.LAYOUT_STYLE_1);
        AddTextView("Model: " + android.os.Build.MODEL,
                UiStyle.STYLE_1, UiStyle.LAYOUT_STYLE_1);
        AddTextView("Product: " + android.os.Build.PRODUCT,
                UiStyle.STYLE_1, UiStyle.LAYOUT_STYLE_1);
        AddTextView("Version: " + TestFunction.getInstance().currentVersion(),
                UiStyle.STYLE_1, UiStyle.LAYOUT_STYLE_1);

        // HEADER
        AddTextView("Fixed function button", UiStyle.STYLE_2, UiStyle.LAYOUT_STYLE_2);
        /** NOTE
        * This zone is button.
        * Click on button will do the function test
        * */
        AddButton(
            "Pokeball Icon",
            TestFunction.getInstance().onButtonNotification("Pokeball", "[pokeball_icon] This is normal text", R.drawable.pkm_icon
        ), UiStyle.STYLE_4, UiStyle.LAYOUT_STYLE_2);

        AddButton("Cpp Icon", TestFunction.getInstance().onButtonNotification(
            "CPP",
            "[cpp_icon] Hello this is fucking long long long long long long long long long length text and doublt :)) Hello this is fucking long long long long long long long long long length text",
            R.drawable.cpp_icon
        ), UiStyle.STYLE_4, UiStyle.LAYOUT_STYLE_2);

        AddButton("Plain text 1 no icon", TestFunction.getInstance().onButtonNotification(
            null,
            "This is plain text only",
            R.drawable.pkm_icon
        ), UiStyle.STYLE_4, UiStyle.LAYOUT_STYLE_2);

        AddButton("Plain text 2 no icon", TestFunction.getInstance().onButtonNotification(
            null,
            "This is new long text! long long long long long long long long long long long long long long long long long long long long long long long long long man!",
            R.drawable.cpp_icon
        ), UiStyle.STYLE_4, UiStyle.LAYOUT_STYLE_2);

        // HEADER
        AddTextView("Use command below to send MESSAGE_?\n" +
            "adb shell \"am broadcast -a MESSAGE_x --es sms_body 'adb' -n com.example.testapptemplate/receiver.AppReceiver\"",
            UiStyle.STYLE_4, UiStyle.LAYOUT_STYLE_2);

        /** NOTE
         * This zone is check button. When checked, this app will take an message and create notification base on test content
         * */
        AddCheckBox(
            "Broadcast MESSAGE_1 = 'Pokeball Icon'",
            TestFunction.getInstance().creatListener("MESSAGE_1", "Pokeball", "pokeball_icon", R.drawable.pkm_icon),
            UiStyle.STYLE_2,
            UiStyle.LAYOUT_STYLE_1);

        AddCheckBox(
            "Broadcast MESSAGE_2 = 'Cpp Icon'",
            TestFunction.getInstance().creatListener("MESSAGE_2", "CPP", "cpp_icon", R.drawable.cpp_icon),
            UiStyle.STYLE_2,
            UiStyle.LAYOUT_STYLE_1);

        AddCheckBox(
            "Broadcast MESSAGE_3 = 'Plain text 1 no icon'",
            TestFunction.getInstance().creatListener("MESSAGE_3", null, "This is plain text only", R.drawable.pkm_icon),
            UiStyle.STYLE_2,
            UiStyle.LAYOUT_STYLE_1);

        AddCheckBox(
            "Broadcast MESSAGE_4 = 'Plain text 2 no icon'",
            TestFunction.getInstance().creatListener("MESSAGE_4",null, "This is new plain text!", R.drawable.cpp_icon),
            UiStyle.STYLE_2,
            UiStyle.LAYOUT_STYLE_1);

        /** NOTE
         * This zone is button.
         * Click on button will do the function test
         * */
        // HEADER
        AddTextView(" Changeable notification ", UiStyle.STYLE_2, UiStyle.LAYOUT_STYLE_2);

        AddDropDownBox(
                TestData.getInstance().getTestList(TestData.NID_SERVICE),
                TestFunction.getInstance().onDropdownSelected(),
                UiStyle.STYLE_0,
                UiStyle.LAYOUT_STYLE_3
        );

        AddDropDownBox(
                TestData.getInstance().getTestList(TestData.NID_PRIORITY),
                TestFunction.getInstance().onDropdownSelected(),
                UiStyle.STYLE_0,
                UiStyle.LAYOUT_STYLE_3
        );

        AddDropDownBox(
                TestData.getInstance().getTestList(TestData.NID_CATEGORY),
                TestFunction.getInstance().onDropdownSelected(),
                UiStyle.STYLE_0,
                UiStyle.LAYOUT_STYLE_3
        );

        AddDropDownBox(
                TestData.getInstance().getTestList(TestData.NID_TITTLE),
                TestFunction.getInstance().onDropdownInput(TestData.NID_TITTLE),
                UiStyle.STYLE_0,
                UiStyle.LAYOUT_STYLE_3
        );

        AddDropDownBox(
                TestData.getInstance().getTestList(TestData.NID_TEXT),
                TestFunction.getInstance().onDropdownInput(TestData.NID_TEXT),
                UiStyle.STYLE_0,
                UiStyle.LAYOUT_STYLE_3
        );

        AddDropDownBox(
                TestData.getInstance().getTestList(TestData.NID_ICON),
                TestFunction.getInstance().onDropdownSelected(),
                UiStyle.STYLE_0,
                UiStyle.LAYOUT_STYLE_3
        );

        AddButton("Send custom notification",
                TestFunction.getInstance().sendCustomNotification(),
                UiStyle.STYLE_5, UiStyle.LAYOUT_STYLE_2);

        AddTextView("*** Auther: TZ & Tommy ***\n"
                + " - Product: No\n"
                + " - For Sale: No\n"
                + " - For Fun: True\n"
                + " - Help Contact: On Air\n"
                + " - Free: Yes\n"
                , UiStyle.STYLE_4, UiStyle.LAYOUT_STYLE_2);
    }
}