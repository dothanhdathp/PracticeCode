package activity;

import common.AppInfo;
import javaclass.TestData;
import javaclass.TestFunction;
import javaclass.TestInfo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
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
import android.widget.Toast;

import com.example.aidlnotificationtest.R;
import com.humaxdigital.automotive.systemui.notification.INotificationServiceCallback;

import java.sql.Connection;
import java.util.List;


public class HomeActivity extends AppCompatActivity {
    private final String TAG = AppInfo.OWNER+"HomeActivity";
    private final int BUTTON_CALL_SERVER = 9999;
    private boolean isSeviceConnected = false;

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

    /*****   UI-Widget-Creator   *****/

    private void AddTextView(String text, int item_style, int layout_style, int margin) {
        TextView text_view = UiStyle.getInstance().newTextViewStyle(item_style, layout_style, margin);
        text_view.setText(text);
        mMainLayout.addView(text_view);
    }

    private void AddButton(String content, View.OnClickListener event, int item_style, int layout_style, int margin) {
//        Button button = UiStyle.getInstance().newButtonStyle(layout_style, UiStyle.LAYOUT_STYLE_2);
        Button button = UiStyle.getInstance().newButtonStyle(UiStyle.STYLE_4, UiStyle.LAYOUT_STYLE_2, margin);
        button.setText(content);
        if(event==null) {
            button.setOnClickListener(defaultButtonClicked);
        }
        button.setOnClickListener(event);
        mMainLayout.addView(button);
    }

//    private void AddButtonId(int id, String content, View.OnClickListener event, int item_style, int layout_style, int margin) {
//        Button button = UiStyle.getInstance().newButtonStyle(UiStyle.STYLE_4, UiStyle.LAYOUT_STYLE_2, margin);
//        button.setId(id);
//        button.setText(content);
//        if(event==null) {
//            button.setOnClickListener(defaultButtonClicked);
//        }
//        button.setOnClickListener(event);
//        mMainLayout.addView(button);
//    }

    private void AddCheckBox(String content, CompoundButton.OnCheckedChangeListener onCheck, int item_style, int layout_style, int margin) {
        CheckBox check_box = UiStyle.getInstance().newCheckBoxStyle(item_style, layout_style, margin);
        check_box.setText(content);
        check_box.setOnCheckedChangeListener(onCheck);
        mMainLayout.addView(check_box);
    }

    /** Parameter
     * list: list data
     * event: clicked event
     * item_style: drop list item style
     * item_style: drop list layout style
     * */
    private void AddDropDownBox(String[] list, AdapterView.OnItemSelectedListener event, int item_style, int layout_style, int margin) {
        Spinner spinner = UiStyle.getInstance().newSpinnerStyle(item_style, layout_style, margin);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(event);
        mMainLayout.addView(spinner);
    }

    /** Dropbox + Title
     * --- Parameter ---
     * title: title of drop list
     * list: list data
     * event: clicked event
     * item_style: drop list item style
     * <> Note: Title and Droplist will be show in 1 line so on not have layout style any more </>
     * */
    private void AddDropDownBoxType2(String title, String[] list, AdapterView.OnItemSelectedListener event, int margin) {
        LinearLayout subLinerLayout = new LinearLayout(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        // lp.width = 0;
        // lp.weight = 0.0f;
        subLinerLayout.setLayoutParams(lp);
        subLinerLayout.setOrientation(LinearLayout.HORIZONTAL);
        /// Create Tittle
        TextView text_view = UiStyle.getInstance().newTextViewStyle(UiStyle.STYLE_1, UiStyle.LAYOUT_STYLE_L, margin);
        text_view.setText(title);
        /// Create drop box
        Spinner spinner = UiStyle.getInstance().newSpinnerStyle(UiStyle.STYLE_0, UiStyle.LAYOUT_STYLE_R, margin);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(event);
        subLinerLayout.addView(text_view);
        subLinerLayout.addView(spinner);
        mMainLayout.addView(subLinerLayout);
    }

    /**
     * ADIL zone (for bind service)
     */
    private INotificationServiceCallback aidlInterface = null;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            aidlInterface = INotificationServiceCallback.Stub.asInterface(service);
            Log.d(TAG, "--- onServiceConnected ---");
            isSeviceConnected = true;
            Toast.makeText(getApplicationContext(), "Service Connected", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "--- onServiceDisconnected ---");
            isSeviceConnected = false;
            aidlInterface = null;
            Toast.makeText(getApplicationContext(), "Service Disconnected", Toast.LENGTH_SHORT).show();
        }
    };

    public Intent convertImplicitIntentToExplicitIntent(Intent implicitIntent) {
        PackageManager pm = getPackageManager();
        List<ResolveInfo> resolveInfoList = pm.queryIntentServices(implicitIntent, 0);

        if (resolveInfoList == null || resolveInfoList.size() != 1) {
            return null;
        }
        ResolveInfo serviceInfo = resolveInfoList.get(0);
        ComponentName component = new ComponentName(serviceInfo.serviceInfo.packageName, serviceInfo.serviceInfo.name);
        Intent explicitIntent = new Intent(implicitIntent);
        explicitIntent.setComponent(component);
        Log.d(TAG, "explicitIntent = " + ((explicitIntent == null) ? "null" : "not null"));
        return explicitIntent;
    }

    private void bindToService() {
        boolean res = false;
        try {
            Intent intent = new Intent("MyAidlService.AIDL");
            Log.d(TAG, "intent = " + ((intent == null) ? "null" : "not null"));
            Intent __intent = convertImplicitIntentToExplicitIntent(intent);
            res = this.bindService(__intent, connection, Context.BIND_AUTO_CREATE);
        } catch (Exception e) {
            Log.d(TAG, " dead on bind service: " + e.toString() );
        }
        Toast.makeText(getApplicationContext(), "Connection " + (res?"SUGGEST":"FALSE"), Toast.LENGTH_SHORT).show();
    }

//    private void generateUI()
//    {
//        // HEADER
//        AddTextView("Device Infomation", UiStyle.STYLE_2, UiStyle.LAYOUT_STYLE_2, 10);
//        /** NOTE
//         * This Zone show device environment which run test. In future when need check
//         */
//        AddTextView("Package Name: " + getApplicationContext().getPackageName(),
//            UiStyle.STYLE_1, UiStyle.LAYOUT_STYLE_1, 0);
//        AddTextView("Screen size (WxH): " + TestInfo.getInstance().getScreenSize(),
//            UiStyle.STYLE_1, UiStyle.LAYOUT_STYLE_1, 0);
//        AddTextView("AndroidOS: " + System.getProperty("os.version"),
//            UiStyle.STYLE_1, UiStyle.LAYOUT_STYLE_1, 0);
//        AddTextView("SDK: " + android.os.Build.VERSION.SDK,
//            UiStyle.STYLE_1, UiStyle.LAYOUT_STYLE_1, 0);
//        AddTextView("Device: " + android.os.Build.DEVICE,
//            UiStyle.STYLE_1, UiStyle.LAYOUT_STYLE_1, 0);
//        AddTextView("Model: " + android.os.Build.MODEL,
//            UiStyle.STYLE_1, UiStyle.LAYOUT_STYLE_1, 0);
//        AddTextView("Product: " + android.os.Build.PRODUCT,
//            UiStyle.STYLE_1, UiStyle.LAYOUT_STYLE_1, 0);
//        AddTextView("Version: " + TestFunction.getInstance().currentVersion(),
//            UiStyle.STYLE_1, UiStyle.LAYOUT_STYLE_1, 0);
//
//        /** NOTE
//         * This zone is button.
//         * Click on button will do the function test
//         * */
//        // HEADER
//        AddTextView(" Changeable notification ", UiStyle.STYLE_2, UiStyle.LAYOUT_STYLE_2, 10);
//
//        AddDropDownBoxType2(
//                "Service type",
//                TestData.getInstance().getTestList(TestData.NID_SERVICE),
//                TestFunction.getInstance().onDropdownSelected(),
//                20
//        );
//
//        AddDropDownBoxType2(
//                "Priority",
//                TestData.getInstance().getTestList(TestData.NID_PRIORITY),
//                TestFunction.getInstance().onDropdownSelected(),
//                20
//        );
//
//        AddDropDownBoxType2(
//                "Category",
//                TestData.getInstance().getTestList(TestData.NID_CATEGORY),
//                TestFunction.getInstance().onDropdownSelected(),
//                20
//        );
//
//        // Title and text should.
//        // This have another function because
//        AddDropDownBoxType2(
//                "Title",
//                TestData.getInstance().getTestList(TestData.NID_TITTLE),
//                TestFunction.getInstance().onDropdownInput(TestData.NID_TITTLE),
//                20
//        );
//
//        AddDropDownBoxType2(
//                "Text",
//                TestData.getInstance().getTestList(TestData.NID_TEXT),
//                TestFunction.getInstance().onDropdownInput(TestData.NID_TEXT),
//                20
//        );
//
//        AddDropDownBoxType2(
//                "Icon",
//                TestData.getInstance().getTestList(TestData.NID_ICON),
//                TestFunction.getInstance().onDropdownSelected(),
//                20
//        );
//
//        AddButton("Send custom notification",
//                TestFunction.getInstance().sendCustomNotification(),
//                UiStyle.STYLE_5, UiStyle.LAYOUT_STYLE_2,0);
//
//        /// AIDL
//        AddButton("Send message block",
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Log.d(TAG, "onClick: [~] Send message block ~");
//                        Intent i = new Intent();
//                        i.setAction("ACTION_SET_RANK");
//                        i.putExtra("AAA", "com.example.aidlnotificationtest");
//                        sendBroadcast(i);
//
//                        i.getStringExtra("AAA");
//
////                        Bundle bb = intent.getExtras();
////                        String bc = bb.getString("AAA");
////                        int    bd = bb.getInt("AAA", 0);
//
//                    }
//                },
//                UiStyle.STYLE_5, UiStyle.LAYOUT_STYLE_2,0);
//
//        // HEADER
//        AddTextView("Use command below to send MESSAGE_?\n" +
//            "adb shell \"am broadcast -a MESSAGE_x --es sms_body 'adb' -n com.example.aidlnotificationtest/receiver.AppReceiver\"",
//            UiStyle.STYLE_4, UiStyle.LAYOUT_STYLE_2, 0);
//
//        /** NOTE
//         * This zone is check button. When checked, this app will take an message and create notification base on test content
//         * */
//        AddCheckBox(
//            "Broadcast MESSAGE_1 = 'Pokeball Icon'",
//            TestFunction.getInstance().creatListener("MESSAGE_1", "Pokeball", "pokeball_icon", R.drawable.pkm_icon),
//            UiStyle.STYLE_2,
//            UiStyle.LAYOUT_STYLE_1, 0);
//
//        AddCheckBox(
//            "Broadcast MESSAGE_2 = 'Cpp Icon'",
//            TestFunction.getInstance().creatListener("MESSAGE_2", "CPP", "cpp_icon", R.drawable.cpp_icon),
//            UiStyle.STYLE_2,
//            UiStyle.LAYOUT_STYLE_1, 0);
//
//        AddCheckBox(
//            "Broadcast MESSAGE_3 = 'Plain text 1 no icon'",
//            TestFunction.getInstance().creatListener("MESSAGE_3", null, "This is plain text only", R.drawable.pkm_icon),
//            UiStyle.STYLE_2,
//            UiStyle.LAYOUT_STYLE_1, 0);
//
//        AddCheckBox(
//            "Broadcast MESSAGE_4 = 'Plain text 2 no icon'",
//            TestFunction.getInstance().creatListener("MESSAGE_4",null, "This is new plain text!", R.drawable.cpp_icon),
//            UiStyle.STYLE_2,
//            UiStyle.LAYOUT_STYLE_1, 0);
//
//        AddTextView("*** Auther: TZ & Tommy ***\n"
//                + " - Product: No\n"
//                + " - For Sale: No\n"
//                + " - For Fun: True\n"
//                + " - Help Contact: On Air\n"
//                + " - Free: Yes\n"
//                , UiStyle.STYLE_4, UiStyle.LAYOUT_STYLE_2, 0);
//    }
    private void generateUI()
    {
        AddButton("Send Broadcast Message", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: [~] Send message block ~");

                Intent i = new Intent();
                i.setAction("ACTION_SET_RANK");
                sendBroadcast(i);
            }
        }
        ,UiStyle.STYLE_5, UiStyle.LAYOUT_STYLE_2,0);
    }
}