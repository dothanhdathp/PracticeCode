package activity;

import android.view.Gravity;
import android.widget.LinearLayout;

public class Default {
    private static Default mInstance;
    private Default(){};

    public static Default getInstance() {
        if(null==mInstance) {
            mInstance = new Default();
        }
        return mInstance;
    }

    private static int TOP_MARGIN = 5;
    private static int BOT_MARGIN = 5;

    public static final int LAYOUT_TEXTVIEW = 1;
    public static final int LAYOUT_BUTTON   = 2;
    public static final int LAYOUT_CHECKBOX = 3;
    public static final int LAYOUT_DROPLIST = 4;

    public LinearLayout.LayoutParams newLayout(int type) {
        LinearLayout.LayoutParams lp = null;
        switch (type) {
            case LAYOUT_TEXTVIEW:
                lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lp.gravity = Gravity.CENTER_VERTICAL;
                lp.topMargin = TOP_MARGIN;
                break;
            case LAYOUT_BUTTON:
            case LAYOUT_CHECKBOX:
                lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lp.gravity = Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL;
                lp.topMargin = TOP_MARGIN;
                break;
            case LAYOUT_DROPLIST:
                lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lp.gravity = Gravity.NO_GRAVITY;
                lp.topMargin = TOP_MARGIN*4;
                lp.bottomMargin = BOT_MARGIN*4;
                break;
            default:
                break;
        }
        return lp;
    }
}