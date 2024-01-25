package javaclass;

import static android.content.Context.NOTIFICATION_SERVICE;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;

import androidx.core.app.NotificationCompat;

import java.util.HashMap;
import common.AppInfo;
import receiver.AppReceiver;

public class TestFunction {
    private final String TAG = AppInfo.OWNER+"TestFunction";
    private TestFunction() {};
    private static TestFunction mInstance = null;
    private HashMap<String, AppReceiver> mReceiveHashMap = new HashMap<String, AppReceiver>();
    private HashMap<String, NotiData> mNotificationTestBox = new HashMap<String, NotiData>();

    public class NotiData {
        public String title;
        public String text;
        public int iconId;
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
        String codeName="Unsupported";//below Jelly Bean

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
}
