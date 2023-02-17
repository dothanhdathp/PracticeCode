package com.example.pokedex;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    /* For debug */
    private static final String TAG = "PKD";
    /* Service */
    NationalDexService mNDexService = null;
    /* Adapter */
    private boolean mBound = false;
    /* Item */
    ListView pkmList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pkmList = (ListView)findViewById(R.id.list_pokemon);
        pkmList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String selectedItem = (String) parent.getItemAtPosition(position);
            Log.d(TAG, "The selected item is : " + selectedItem);
            // Intent intent = new Intent(this, NationalDexService.class);
            Intent newActivity = new Intent(view.getContext(), PkmDetailActivity.class);
            newActivity.putExtra("pkm_id", String.valueOf(position));
            newActivity.putExtra("pkm_name", selectedItem);
            view.getContext().startActivity(newActivity);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Connect to service
        Intent intent = new Intent(this, NationalDexService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    /** Defines callbacks for service binding, passed to bindService() */
    private ServiceConnection connection = new ServiceConnection()
    {
        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            NationalDexService.LocalBinder binder = (NationalDexService.LocalBinder)service;
            mNDexService = binder.getService();
            mBound = true;
            MainActivity.this.onBindingSuggest();
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
            MainActivity.this.onBindingFalse();
        }
    };

    /** Local Method **/
    private void onBindingSuggest() {
        List<String> pkm_list = mNDexService.getlistPkm();
        ArrayAdapter<String> metadataAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, pkm_list);
        pkmList.setAdapter(metadataAdapter);
    }

    private void onBindingFalse() { /*do nothing*/ }
}