package com.example.pokedex;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PkmDetailActivity extends AppCompatActivity {
    final String TAG = "PAD";
    List<Integer> pkm_list_icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pkm_detail);

        Integer listIconPkm[] = {
            R.drawable._01, R.drawable._02, R.drawable._03, R.drawable._04, R.drawable._05, R.drawable._06, R.drawable._07, R.drawable._08, R.drawable._09
        };
        pkm_list_icon = new ArrayList<Integer>(Arrays.asList(listIconPkm));

        String strPkmName = getIntent().getStringExtra("pkm_name");
        String strPkmID   = getIntent().getStringExtra("pkm_id");
        Log.d(TAG, "strPkmName : " + strPkmName + " - strPkmID: " + R.drawable._03_m + " ?= " + pkm_list_icon.get(Integer.parseInt(strPkmID)));
        PkmDetailActivity.this.CreateUI(strPkmName, pkm_list_icon.get(Integer.parseInt(strPkmID)));
    }
    public void InitUI()
    {

    }
    public void CreateUI(String name, int id)
    {
        ImageView pkmImage = (ImageView)findViewById(R.id.pkm_Image);
        pkmImage.setImageResource(id);
        TextView PkmName = (TextView) findViewById(R.id.pkm_name);
        PkmName.setText(name);
    }
}