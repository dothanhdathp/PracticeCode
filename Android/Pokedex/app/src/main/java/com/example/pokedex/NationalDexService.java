package com.example.pokedex;

import android.app.Service;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.nfc.Tag;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NationalDexService extends Service {
    final String Tag = "NDexSv";
    private final IBinder mNationalDexBinder = new LocalBinder();
    List<String> pkm_display_list;
    int pos_in_pkm_display_list;

    public class LocalBinder extends Binder {
        NationalDexService getService() {
            // Return this instance of LocalService so clients can call public methods
            return NationalDexService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mNationalDexBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        DataBaseManager.getInstance(this.getApplicationContext());
        pkm_display_list = new ArrayList<String>();
    }

    /** method for clients */
    public List<String> getListPkm() {
        Cursor mCursor = DataBaseManager.getInstance().getAllPokemonName();
        while (mCursor.moveToNext()) {
            pkm_display_list.add(mCursor.getString(0));
        }
        return pkm_display_list;
    }

    public void setSelectedPkmInList(int position) {
        pos_in_pkm_display_list = position;
        return;
    }

    public String getSelectedPkmName() {
        String pkmName = "UNKNOWN";
        if(pkm_display_list.size() < pos_in_pkm_display_list) {
            pkmName = pkm_display_list.get(pos_in_pkm_display_list);
        }
        return pkmName;
    }
}