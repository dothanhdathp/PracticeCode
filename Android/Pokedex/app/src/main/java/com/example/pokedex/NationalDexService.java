package com.example.pokedex;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NationalDexService extends Service {
    private final IBinder mNationalDexBinder = new LocalBinder();
//    private final Random mGenerator = new Random();

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
    }

    /** method for clients */
    public List<String> getlistPkm() {
        // This should be through via Database than by here
        String[] pkm = new String[] { "Bulbasaur", "Ivysaur", "Venusaur", "Charmander", "Charmeleon", "Charizard", "Squirtle", "Wartortle", "Blastoise",
                    "empty",
                    "empty",
                    "empty",
                    "empty",
                    "empty",
                    "empty",
                    "empty",
                    "empty",
                    "empty",
                    "empty",
                    "empty",
                    "empty",
                    "empty",
                    "empty",
                    "empty",
                    "empty",
                    "empty",
                    "empty",
                    "empty",
                    "empty",
                    "empty",
                    "empty",
                    "empty"
        };
        final List<String> pkm_list = new ArrayList<String>(Arrays.asList(pkm));
        return pkm_list;
    }
}