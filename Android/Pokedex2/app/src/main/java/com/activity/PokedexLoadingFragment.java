package com.activity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.service.controls.actions.CommandAction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.activity.pokedex2.R;
import com.service.CommonValue;

import java.util.Hashtable;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PokedexLoadingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PokedexLoadingFragment extends Fragment {
    private String TAG = "PokedexLoadingFragment-" + CommonValue.getInstance().getOwnner();
    private static PokedexLoadingFragment mInstance;
    private int mProgress = 0;

    public PokedexLoadingFragment() {
        // Required empty public constructor
    }

    public static PokedexLoadingFragment getInstance() {
        if(mInstance == null) {
            mInstance = new PokedexLoadingFragment();
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
        return inflater.inflate(R.layout.fragment_pokedex_loading, container, false);
    }

    public void setProgress(int progress_id) {

    }
}