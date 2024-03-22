package javaclass;

import static android.content.Context.*;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.Spinner;

import androidx.core.app.NotificationCompat;

import com.example.aidlnotificationtest.R;

import java.util.HashMap;
import java.util.List;

import common.AppInfo;
import receiver.AppReceiver;

public class TestFunction {
    private final String TAG = AppInfo.OWNER+"TestFunction";
    private TestFunction() {};
    private static TestFunction mInstance = null;

    // For check box
    private HashMap<String, AppReceiver> mReceiveHashMap = new HashMap<String, AppReceiver>();
    private HashMap<String, NotiData> mNotificationTestBox = new HashMap<String, NotiData>();

    // For dropdown list
    private NotiData mNotiData = new NotiData(null, "NA", R.drawable.ic_launcher_background);

    public class NotiData {
        public String title;
        public String text;
        public int iconId;

        public String service = NOTIFICATION_SERVICE;
        public int    priority = NotificationManager.IMPORTANCE_MIN;
        public String category = Notification.CATEGORY_SERVICE;

        public NotiData(String _title, String _text, int _iconId) {
            title  = _title;
            text   = _text;
            iconId = _iconId;
        }
    }

    public static TestFunction getInstance() {
        if(mInstance==null) {
            mInstance = new TestFunction();
            mInstance.createChannel();
        }
        return mInstance;
    }

    public String currentVersion() {
        double release=Double.parseDouble(Build.VERSION.RELEASE.replaceAll("(\\d+[.]\\d+)(.*)","$1"));
        String codeName="Unsupported"; //below Jelly Bean

        if(release >= 4.1 && release < 4.4) codeName = "Jelly Bean";
        else if(release < 5)   codeName="Kit Kat";
        else if(release < 6)   codeName="Lollipop";
        else if(release < 7)   codeName="Marshmallow";
        else if(release < 8)   codeName="Nougat";
        else if(release < 9)   codeName="Oreo";
        else if(release < 10)  codeName="Pie";
        else if(release >= 10) codeName="Android "+((int)release);

        //since API 29 no more candy code names
        return codeName+" ("+release+"), api level "+Build.VERSION.SDK_INT;
    }

    private void createChannel() {
        String CHANNEL_ID = AppInfo.CHANNEL_ID;
        String CHANNEL_NAME = AppInfo.CHANNEL_NAME;
        int CHANNEL_IMPORTANT = NotificationManager.IMPORTANCE_DEFAULT;
        String CHANNEL_DESCRIPTION = "This is test channel";

        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, CHANNEL_IMPORTANT);
        channel.setDescription(CHANNEL_DESCRIPTION);

        NotificationManager notificationManager = AppInfo.getInstance().getApplicationContext().getSystemService(NotificationManager.class);
        if(notificationManager != null) {
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void makeNotification(String title, String text, int iconId) {
        NotificationManager notificationManager =
                (NotificationManager)AppInfo.getInstance().getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(
                AppInfo.getInstance().getApplicationContext(),
                AppInfo.CHANNEL_ID);
        if(title!=null) builder.setContentTitle(title);
        builder.setContentText(text);
        builder.setSmallIcon(iconId);
        builder.setPriority(NotificationManager.IMPORTANCE_MIN);
        builder.setCategory(Notification.CATEGORY_SERVICE);
        notificationManager.notify(1, builder.build());
    }

    public View.OnClickListener onButtonNotification(String title, String text, int iconId) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeNotification(title, text, iconId);
            }
        };
    }

    public AdapterView.OnItemSelectedListener onDropdownSelected() {
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selected_item = parentView.getSelectedItem().toString();
                Log.d(TAG, "onItemSelected = " + selected_item);
                TestData.getInstance().setData(selected_item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // nothing
            }
        };
    }

    public AdapterView.OnItemSelectedListener onDropdownInput(int type) {
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selected_item = parentView.getSelectedItem().toString();
                Log.d(TAG, "onItemSelected = " + selected_item);
                if(TestData.NID_TITTLE==type) {
                    if(selected_item.equals("<Empty>")) {
                        TestData.getInstance().setTitle(null);
                    } else {
                        TestData.getInstance().setTitle(selected_item);
                    }
                } else if (TestData.NID_TEXT==type) {
                    TestData.getInstance().setText(selected_item);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // nothing
            }
        };
    }

//    public View.OnClickListener AddListen(String action) {
//        return new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AppReceiver receiver = mReceiveHashMap.get(action);
//                Button btn = (Button)v;
//                if(receiver == null) {
//                    IntentFilter filter = new IntentFilter();
//                    filter.addAction(action);
//                    receiver = new AppReceiver();
//                    AppInfo.getInstance().getApplicationContext().registerReceiver(receiver, filter);
//                    mReceiveHashMap.put(action, receiver);
//                } else {
//                    AppInfo.getInstance().getApplicationContext().unregisterReceiver(receiver);
//                    mReceiveHashMap.remove(action);
//                    btn.setText("Register action [" + action + "]");
//                }
//            }
//        };
//    }
//
//    public void unlistenningAll() {
//        for(Map.Entry<String, AppReceiver> entry : mReceiveHashMap.entrySet()) {
//            String key = entry.getKey();
//            AppReceiver receiver = entry.getValue();
//            AppInfo.getInstance().getApplicationContext().unregisterReceiver(receiver);
//            Log.d(TAG, "un-bind: " + key);
//        }
//    }

    public CompoundButton.OnCheckedChangeListener creatListener(
        String mess,
        String title, String text, int iconId
    ) {
        return new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    Log.d(TAG, "Listening: " + mess);
                    mNotificationTestBox.put(mess, new NotiData(title, text, iconId));
                } else if (mNotificationTestBox.get(mess)!=null) {
                    Log.d(TAG, "UnListen: " + mess);
                    mNotificationTestBox.remove(mess);
                }
            }
        };
    }

    public void onListen(String mess) {
        NotiData data = mNotificationTestBox.get(mess);
        if(data!=null) {
            Log.d(TAG, "on handle for mess: " + mess);
            makeNotification(data.title, data.text, data.iconId);
        }
    }

    public View.OnClickListener sendCustomNotification() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.d(TAG, "{Debug} sendCustomNotification: [TestData]"
//                    + "\n{Debug}     Title    : " + TestData.getInstance().getTitle()
//                    + "\n{Debug}     Text     : " + TestData.getInstance().getText()
//                    + "\n{Debug}     Icon     : " + TestData.getInstance().getIcon()
//                    + "\n{Debug}     Priority : " + TestData.getInstance().getPriority()
//                    + "\n{Debug}     Category : " + TestData.getInstance().getCategory()
//                );
                NotificationManager notificationManager =
                        (NotificationManager)AppInfo.getInstance().getSystemService(
                                TestData.getInstance().getService()
                        );

                NotificationCompat.Builder builder = new NotificationCompat.Builder(
                        AppInfo.getInstance().getApplicationContext(),
                        AppInfo.CHANNEL_ID);

                builder.setContentTitle(TestData.getInstance().getTitle());
                builder.setContentText(TestData.getInstance().getText());
                builder.setSmallIcon(TestData.getInstance().getIcon());
                builder.setPriority(TestData.getInstance().getPriority());
                builder.setCategory(TestData.getInstance().getCategory());
                notificationManager.notify(1, builder.build());
            }
        };
    }
}
