package com.activity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.activity.pokedex2.R;
import com.service.CommonValue;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PokedexDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PokedexDetailFragment extends Fragment {
    private final String TAG = "DetailF" + CommonValue.getInstance().getOwnner();
    private static PokedexDetailFragment mInstance;
    private View mView;
    private String mData;

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
        updateView();
        return mView;
    }

    private void updateView() {
        if(mData != null) {
            String info[] = mData.split(",");
            int hp   = Integer.parseInt(info[4]);
            int atk  = Integer.parseInt(info[5]);
            int def  = Integer.parseInt(info[6]);
            int satk = Integer.parseInt(info[7]);
            int sdef = Integer.parseInt(info[8]);
            int spd  = Integer.parseInt(info[9]);

            ((TextView)mView.findViewById(R.id.pokemon_name)).setText(info[1]);
            ((TextView)mView.findViewById(R.id.text_hp)).setText(info[4]);
            ((TextView)mView.findViewById(R.id.text_atk)).setText(info[5]);
            ((TextView)mView.findViewById(R.id.text_def)).setText(info[6]);
            ((TextView)mView.findViewById(R.id.text_Satk)).setText(info[7]);
            ((TextView)mView.findViewById(R.id.text_Sdef)).setText(info[8]);
            ((TextView)mView.findViewById(R.id.text_spd)).setText(info[9]);

            ((TextView)mView.findViewById(R.id.p_total_stats)).setText(
                    String.valueOf(hp + atk + def + satk + sdef + spd)
            );

            ((ProgressBar)mView.findViewById(R.id.p_bar_hp)).setProgress(  (int)(hp*100/255));
            ((ProgressBar)mView.findViewById(R.id.p_bar_atk)).setProgress( (int)(atk*100/255));
            ((ProgressBar)mView.findViewById(R.id.p_bar_def)).setProgress( (int)(def*100/255));
            ((ProgressBar)mView.findViewById(R.id.p_bar_Satk)).setProgress((int)(satk*100/255));
            ((ProgressBar)mView.findViewById(R.id.p_bar_Sdef)).setProgress((int)(sdef*100/255));
            ((ProgressBar)mView.findViewById(R.id.p_bar_spd)).setProgress( (int)(spd*100/255));

//            (mView.findViewById(R.id.pokemon_image));
        }
    }
    public void setDataInfo(String data) {
        mData = data;
    }
}