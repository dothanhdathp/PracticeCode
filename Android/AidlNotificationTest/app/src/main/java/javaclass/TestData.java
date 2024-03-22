package javaclass;

import static android.content.Context.*;
import android.app.Notification;
import android.app.NotificationManager;
import com.example.aidlnotificationtest.R;

public class TestData {
    private static TestData mInstance;

    public static TestData getInstance() {
        if(null==mInstance) {
            mInstance = new TestData();
        }
        return mInstance;
    }

    private String mTitle    = "";
    private String mText     = "";
    private int    mIcon     = R.drawable.ic_launcher_background;
    private String mService  = NOTIFICATION_SERVICE;
    private int    mPriority = NotificationManager.IMPORTANCE_MIN;
    private String mCategory = Notification.CATEGORY_SERVICE;

    public static final int NID_TITTLE   = 0;
    public static final int NID_TEXT     = 1;
    public static final int NID_ICON     = 2;
    public static final int NID_SERVICE  = 3;
    public static final int NID_PRIORITY = 4;
    public static final int NID_CATEGORY = 5;

    public String getTitle() { return mTitle; }
    public String getText() { return mText; }
    public int    getIcon() { return mIcon; }
    public String getService() { return mService; }
    public int    getPriority() { return mPriority; }
    public String getCategory() { return mCategory; }

    void setTitle(String value) {
        /// Set you specific title
        mTitle = value;
    }

    void setText(String value) {
        /// Set you specific text
        mText = value;
    }

    void setVar(String key, String var) {

    }

    void setData(String key) {
        switch (key)
        {
            // SERVICE
            case "NOTIFICATION_SERVICE"   : mService = NOTIFICATION_SERVICE; break;
            case "LOCATION_SERVICE"       : mService = LOCATION_SERVICE; break;
            case "SEARCH_SERVICE"         : mService = SEARCH_SERVICE; break;
            case "SENSOR_SERVICE"         : mService = SENSOR_SERVICE; break;
            // ICON
            case "ICON_DEFAULT_BG"        : mIcon = R.drawable.ic_launcher_background; break;
            case "ICON_DEFAULT_FG"        : mIcon = R.drawable.ic_launcher_foreground; break;
            case "ICON_POKE_BALL"         : mIcon = R.drawable.pkm_icon; break;
            case "ICON_CPP"               : mIcon = R.drawable.cpp_icon; break;
            case "ICON_PHONE"             : mIcon = R.drawable.phone_icon; break;
            case "ICON_ANDROID_AUTO"      : mIcon = R.drawable.androidautu_icon; break;
            // PRIORITY
            case "IMPORTANCE_UNSPECIFIED" : mPriority =  NotificationManager.IMPORTANCE_UNSPECIFIED; break;
            case "IMPORTANCE_NONE"        : mPriority =  NotificationManager.IMPORTANCE_NONE; break;
            case "IMPORTANCE_MIN"         : mPriority =  NotificationManager.IMPORTANCE_MIN; break;
            case "IMPORTANCE_LOW"         : mPriority =  NotificationManager.IMPORTANCE_LOW; break;
            case "IMPORTANCE_DEFAULT"     : mPriority =  NotificationManager.IMPORTANCE_DEFAULT; break;
            case "IMPORTANCE_HIGH"        : mPriority =  NotificationManager.IMPORTANCE_HIGH; break;
            case "IMPORTANCE_MAX"         : mPriority =  NotificationManager.IMPORTANCE_MAX; break;
            // CATEGORY
            case "CATEGORY_CALL"             : mCategory = Notification.CATEGORY_CALL; break;
            case "CATEGORY_NAVIGATION"       : mCategory = Notification.CATEGORY_NAVIGATION; break;
            case "CATEGORY_MESSAGE"          : mCategory = Notification.CATEGORY_MESSAGE; break;
            case "CATEGORY_EMAIL"            : mCategory = Notification.CATEGORY_EMAIL; break;
            case "CATEGORY_EVENT"            : mCategory = Notification.CATEGORY_EVENT; break;
            case "CATEGORY_PROMO"            : mCategory = Notification.CATEGORY_PROMO; break;
            case "CATEGORY_ALARM"            : mCategory = Notification.CATEGORY_ALARM; break;
            case "CATEGORY_PROGRESS"         : mCategory = Notification.CATEGORY_PROGRESS; break;
            case "CATEGORY_SOCIAL"           : mCategory = Notification.CATEGORY_SOCIAL; break;
            case "CATEGORY_ERROR"            : mCategory = Notification.CATEGORY_ERROR; break;
            case "CATEGORY_TRANSPORT"        : mCategory = Notification.CATEGORY_TRANSPORT; break;
            case "CATEGORY_SYSTEM"           : mCategory = Notification.CATEGORY_SYSTEM; break;
            case "CATEGORY_SERVICE"          : mCategory = Notification.CATEGORY_SERVICE; break;
            case "CATEGORY_RECOMMENDATION"   : mCategory = Notification.CATEGORY_RECOMMENDATION; break;
            case "CATEGORY_STATUS"           : mCategory = Notification.CATEGORY_STATUS; break;
            case "CATEGORY_REMINDER"         : mCategory = Notification.CATEGORY_REMINDER; break;
            case "CATEGORY_WORKOUT"          : mCategory = Notification.CATEGORY_WORKOUT; break;
            case "CATEGORY_LOCATION_SHARING" : mCategory = Notification.CATEGORY_LOCATION_SHARING; break;
            case "CATEGORY_STOPWATCH"        : mCategory = Notification.CATEGORY_STOPWATCH; break;
            case "CATEGORY_MISSED_CALL"      : mCategory = Notification.CATEGORY_MISSED_CALL; break;
            default: break;
        }
    }

    public String[] getTestList(int list_id) {
        switch (list_id) {
            case NID_TITTLE:
                return new String[] {
                    "<Empty>",
                    "POKEDEX",
                    "CPP",
                    "Pokedex Game",
                    "Cpp Code",
                    "Phone",
                    "Android Auto",
                    "Tester"
                };
            case NID_TEXT:
                return new String[] {
                    "This is normal text",
                    "This is longer text but this still ok and can read them all in notification",
                    "This is fucking long long text which you need a A4 paper to read them all."
                        + "So long... so long... so long... so long... so long... so long... so long... so long... so long... "
                        + "so long... so long... so long... so long... so long... so long... so long... so long... so long... "
                        + "so long... so long... so long... so long... so long... so long... so long... so long... so long... "
                        + "so long... so long... so long... so long... so long... so long... so long... so long... so long... "
                        + "so long... so long... so long... so long... so long... so long... so long... so long... so long... "
                        + "so long... so long... so long... so long... so long... so long... so long... so long... so long... "
                };
            case NID_ICON:
                return new String[] { "ICON_DEFAULT_BG", "ICON_DEFAULT_FG", "ICON_POKE_BALL", "ICON_CPP",
                    "ICON_PHONE", "ICON_ANDROID_AUTO"};
            case NID_SERVICE:
                return new String[] { "NOTIFICATION_SERVICE", "LOCATION_SERVICE", "SEARCH_SERVICE", "SENSOR_SERVICE" };
            case NID_PRIORITY:
                return new String[]
                {
                    "IMPORTANCE_MIN", "IMPORTANCE_LOW", "IMPORTANCE_DEFAULT",
                    "IMPORTANCE_HIGH", "IMPORTANCE_MAX",
                    "IMPORTANCE_UNSPECIFIED", "IMPORTANCE_NONE"
                };
            case NID_CATEGORY:
                return new String[] {
                    "CATEGORY_SERVICE", "CATEGORY_CALL", "CATEGORY_NAVIGATION", "CATEGORY_MESSAGE", "CATEGORY_EMAIL", "CATEGORY_EVENT", "CATEGORY_PROMO",
                    "CATEGORY_ALARM", "CATEGORY_PROGRESS", "CATEGORY_SOCIAL", "CATEGORY_ERROR", "CATEGORY_TRANSPORT", "CATEGORY_SYSTEM",
                    "CATEGORY_RECOMMENDATION", "CATEGORY_STATUS", "CATEGORY_REMINDER", "CATEGORY_WORKOUT",
                    "CATEGORY_LOCATION_SHARING", "CATEGORY_STOPWATCH", "CATEGORY_MISSED_CALL"
                };
            default:
                return null;
        }
    }
}
