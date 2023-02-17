package com.example.pokedex;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.util.Map;

public class DatabaseManager extends Service {
    public DatabaseManager() {
    }

//    Map<int, PokemonDetail> pkmMap;
//
//    private class PokemonDetail {
//        int nationalID = 0;
//        String imageDir = "";
//        // stab
//        public PokemonDetail(int id, String dir) {
//            nationalID = id;
//            imageDir = dir;
//        }
//    }
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

//    @Override
//    public void onCreate() {
//        super.onCreate();
//        // Start paser data base
////        pkmMap<1, PokemonDetail(1, ?????)>;
//    }
//
//    /** Local method **/
//    private boolean onLoadedPokemonID(int pkmId)
//    {
//        return (pkmId < 10) ? true : false;
//    }
}