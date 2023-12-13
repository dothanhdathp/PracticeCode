package com.activity;

import android.app.ActionBar;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.activity.pokedex2.R;
import com.service.CommonValue;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PokedexDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PokedexDetailFragment extends Fragment {
    private final String TAG = "DetailF-" + CommonValue.Arthur;
    private static PokedexDetailFragment mInstance;
    private View mView;
    private String mData;
    private int mImageResourceId;

    public PokedexDetailFragment() {
        // Required empty public constructor
    }

    public static PokedexDetailFragment getInstance() {
        if(mInstance == null) {
            mInstance = new PokedexDetailFragment();
        }
        return mInstance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_pokedex_detail, container, false);
        loadImage();
        loadData();
        return mView;
    }

    public void updateImage(int id) {
        mImageResourceId = id;
        loadImage();
    }

    public void setDataInfo(String data) {
        mData = data;
        loadData();
    }

    private void loadData() {
        if((mData == null) || (mView == null)) return;

        String info[] = mData.split(",");
        int hp   = Integer.parseInt(info[4]);
        int atk  = Integer.parseInt(info[5]);
        int def  = Integer.parseInt(info[6]);
        int satk = Integer.parseInt(info[7]);
        int sdef = Integer.parseInt(info[8]);
        int spd  = Integer.parseInt(info[9]);

        ((TextView)mView.findViewById(R.id.pokemon_name)).setText(info[1]);

        Button type1 = (Button)mView.findViewById(R.id.p_type_1);
        Button type2 = (Button)mView.findViewById(R.id.p_type_2);
        String strType1 = info[2].toUpperCase();
        String strType2 = info[3].toUpperCase();

        if(strType2.equals("")) {
            type2.setVisibility(View.INVISIBLE);
            type1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        } else {
            type2.setVisibility(View.VISIBLE);
            type1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 0.5F));
            type2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 0.5F));
        }
        type1.setText(strType1);
        type1.setBackgroundColor(CommonValue.getInstance().getColorType(strType1));
        type2.setText(strType2);
        type2.setBackgroundColor(CommonValue.getInstance().getColorType(strType2));

        ((TextView)mView.findViewById(R.id.p_hp)).setText(info[4]);
        ((TextView)mView.findViewById(R.id.p_atk)).setText(info[5]);
        ((TextView)mView.findViewById(R.id.p_def)).setText(info[6]);
        ((TextView)mView.findViewById(R.id.p_Satk)).setText(info[7]);
        ((TextView)mView.findViewById(R.id.p_Sdef)).setText(info[8]);
        ((TextView)mView.findViewById(R.id.p_spd)).setText(info[9]);

        ((TextView)mView.findViewById(R.id.p_total_stats)).setText(
                String.valueOf(hp + atk + def + satk + sdef + spd)
        );

        ((ProgressBar)mView.findViewById(R.id.p_bar_hp)).setProgress(  (int)(hp*100/255));
        ((ProgressBar)mView.findViewById(R.id.p_bar_atk)).setProgress( (int)(atk*100/255));
        ((ProgressBar)mView.findViewById(R.id.p_bar_def)).setProgress( (int)(def*100/255));
        ((ProgressBar)mView.findViewById(R.id.p_bar_Satk)).setProgress((int)(satk*100/255));
        ((ProgressBar)mView.findViewById(R.id.p_bar_Sdef)).setProgress((int)(sdef*100/255));
        ((ProgressBar)mView.findViewById(R.id.p_bar_spd)).setProgress( (int)(spd*100/255));
    }

    private void loadImage() {
        if(mView == null) return;
        ((ImageView)mView.findViewById(R.id.pokemon_image)).setImageResource(mImageResourceId);
    }
}