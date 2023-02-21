package com.example.pokedex;

import com.example.pokedex.GestureScreenHandler.SimpleGestureListener;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

enum SpecialType {
    SPECIAL_TYPE_NONE,
    SPECIAL_TYPE_MEGA,
    SPECIAL_TYPE_MEGA_2,
    SPECIAL_TYPE_DYNAMAX,
    SPECIAL_TYPE_ANOTHER_FORM,
    SPECIAL_TYPE_MAX // invalid type form
}

enum DisplayingForm {
    DIPLAING_FORM_NORMAL,
    DIPLAING_FORM_MEGA,
    DIPLAING_FORM_MEGA_X,
    DIPLAING_FORM_MEGA_Y,
    DIPLAING_FORM_MEGA_DYNAMAX,
    DIPLAING_FORM_UNDENTIFY
}

public class PkmDetailActivity extends AppCompatActivity implements SimpleGestureListener {
    final String TAG = "PkmDetailActivity";
    /* List UI common element */
    private TextView pkmTextName; // Pokemon name in the top
    private ImageView pkmImage; // Biggest image of pokemon
    private Button pkmButtonTest;
    int drawableId = 0;
    int pkmNaID = 0;
    String megaType = "None";
    DisplayingForm displaying_form = DisplayingForm.DIPLAING_FORM_NORMAL;
    // For listener Event
    private GestureScreenHandler detector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pkm_detail);

        pkmImage = (ImageView)findViewById(R.id.pokemon_image);
        pkmTextName = (TextView)findViewById(R.id.pokemon_name);
        pkmButtonTest = (Button)findViewById(R.id.button_test);

        Intent intent = getIntent();
        String mPkmName = intent.getStringExtra("pkm_name");
        String mParseId = intent.getStringExtra("pkm_id");
        pkmNaID = Integer.parseInt(mParseId) + 1; // PkmID start with 1

        pkmTextName.setText(mPkmName);

//        pkmButtonTest.setOnClickListener(this); // Tam thoi khong dung cai nay
        createActivityUI(pkmNaID);
        // Detect touched area
        detector = new GestureScreenHandler(PkmDetailActivity.this, this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent me) {
        // Call onTouchEvent of SimpleGestureFilter class
        this.detector.onTouchEvent(me);
        return super.dispatchTouchEvent(me);
    }

    @Override
    public void onSwipe(int direction) {
        //Detect the swipe gestures and display toast
        switch (direction) {
            case GestureScreenHandler.SWIPE_RIGHT:
                if (pkmNaID > 1) createActivityUI(pkmNaID-1);
                break;
            case GestureScreenHandler.SWIPE_LEFT:
                if (pkmNaID < 9) createActivityUI(pkmNaID+1);
                break;
            default:
                break;
        }
    }


    //Toast shown when double tapped on screen
    @Override
    public void onDoubleTap() {
        Toast.makeText(this, "You have Double Tapped.", Toast.LENGTH_SHORT)
                .show();
    }

    public int getDrawableIdByName(String drawAbleName)
    {
        Resources resources = this.getResources();
        final int resourceId = resources.getIdentifier(drawAbleName, "drawable", this.getApplicationContext().getPackageName());
//        return resources.getDrawable(resourceId);
        return resourceId;
    }
/*  This for test mega form, didn't need in here
    public void onTestClick()
    {
        // Case normal
        SpecialType type = ishaveMegaEvolution(pkmNaID);
        if(type == SpecialType.SPECIAL_TYPE_NONE)
        {
            showMess("This pokemon don't have another form");
            return;
        }
        if(type == SpecialType.SPECIAL_TYPE_MEGA)
        {
            if(displaying_form == DisplayingForm.DIPLAING_FORM_NORMAL) {
                String pkmDrawableImageName = "_0" + Integer.toString(pkmNaID) + "_m";
                int mmm = getDrawableIdByName(pkmDrawableImageName);
                displaying_form = DisplayingForm.DIPLAING_FORM_MEGA;
                pkmImage.setImageResource(mmm);
            }
            else if(displaying_form == DisplayingForm.DIPLAING_FORM_MEGA) {
                String pkmDrawableImageName = "_0" + Integer.toString(pkmNaID);
                int mmm = getDrawableIdByName(pkmDrawableImageName);
                pkmImage.setImageResource(mmm);
                displaying_form = DisplayingForm.DIPLAING_FORM_NORMAL;
                return;
            }
        }
        if(type == SpecialType.SPECIAL_TYPE_MEGA_2)
        {
            if(displaying_form == DisplayingForm.DIPLAING_FORM_NORMAL) {
                String pkmDrawableImageName = "_0" + Integer.toString(pkmNaID) + "_mx";
                pkmImage.setImageResource(getDrawableIdByName(pkmDrawableImageName));
                displaying_form = DisplayingForm.DIPLAING_FORM_MEGA_X;
                return;
            } else if(displaying_form == DisplayingForm.DIPLAING_FORM_MEGA_X) {
                String pkmDrawableImageName = "_0" + Integer.toString(pkmNaID) + "_my";
                pkmImage.setImageResource(getDrawableIdByName(pkmDrawableImageName));
                displaying_form = DisplayingForm.DIPLAING_FORM_MEGA_Y;
                return;
            } else {
                String pkmDrawableImageName = "_0" + Integer.toString(pkmNaID);
                pkmImage.setImageResource(getDrawableIdByName(pkmDrawableImageName));
                displaying_form = DisplayingForm.DIPLAING_FORM_NORMAL;
                return;
            }
        }
        return;
    }
*/
    @Override
    protected void onStart() {
        super.onStart();
    }
    /** Create paper base on pokemon ID
     * */
    public void createActivityUI(int pkmNationalID)
    {
        // Set image
        pkmNaID = pkmNationalID;
        String pkmDrawableImageName = "_0" + Integer.toString(pkmNationalID);
        int mmm = getDrawableIdByName(pkmDrawableImageName);
        if(mmm != 0) {
            pkmImage.setImageResource(mmm);
        }
        // Create stats
        List<Integer> data = DataBaseManager.getInstance().getListStatsByIndex(pkmNationalID);
        pkmTextName.setText(DataBaseManager.getInstance().getPokemonNameByIndex(pkmNationalID));
        TextView pkmTextHp     = (TextView)findViewById(R.id.p_hp);
        TextView pkmTextAtk    = (TextView)findViewById(R.id.p_atk);
        TextView pkmTextDef    = (TextView)findViewById(R.id.p_def);
        TextView pkmTextSAtk   = (TextView)findViewById(R.id.p_Satk);
        TextView pkmTextSDef   = (TextView)findViewById(R.id.p_Sdef);
        TextView pkmTextSpd    = (TextView)findViewById(R.id.p_spd);
        TextView pkmTextTotal  = (TextView)findViewById(R.id.p_total_stats);
        ProgressBar pkmProssHp   = (ProgressBar)findViewById(R.id.p_bar_hp);
        ProgressBar pkmProssAtk  = (ProgressBar)findViewById(R.id.p_bar_atk);
        ProgressBar pkmProssDef  = (ProgressBar)findViewById(R.id.p_bar_def);
        ProgressBar pkmProssSAtk = (ProgressBar)findViewById(R.id.p_bar_Satk);
        ProgressBar pkmProssSDef = (ProgressBar)findViewById(R.id.p_bar_Sdef);
        ProgressBar pkmProssSpd  = (ProgressBar)findViewById(R.id.p_bar_spd);
        pkmTextHp.setText(data.get(0).toString());
        pkmTextAtk.setText(data.get(1).toString());
        pkmTextDef.setText(data.get(2).toString());
        pkmTextSAtk.setText(data.get(3).toString());
        pkmTextSDef.setText(data.get(4).toString());
        pkmTextSpd.setText(data.get(5).toString());
        pkmTextTotal.setText(data.get(6).toString());
        pkmProssHp.setProgress(  (int)data.get(0)*100/255);
        pkmProssAtk.setProgress( (int)data.get(1)*100/255);
        pkmProssDef.setProgress( (int)data.get(2)*100/255);
        pkmProssSAtk.setProgress((int)data.get(3)*100/255);
        pkmProssSDef.setProgress((int)data.get(4)*100/255);
        pkmProssSpd.setProgress( (int)data.get(5)*100/255);
    }

    public SpecialType ishaveMegaEvolution(int pkmNaID)
    {
        if(pkmNaID == 3 || pkmNaID == 9)
        {
            return SpecialType.SPECIAL_TYPE_MEGA;
        }
        if(pkmNaID == 6) {
            return SpecialType.SPECIAL_TYPE_MEGA_2;
        }
        return SpecialType.SPECIAL_TYPE_NONE;
    }

    private void showMess(String message)
    {
        Context ctx = PkmDetailActivity.this.getApplicationContext();
        Toast.makeText(ctx, message, Toast.LENGTH_SHORT).show();
    }
}