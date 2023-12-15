package com.service;

import android.content.Context;
import android.content.res.Resources;
import android.media.Image;
import android.util.Log;

import com.activity.pokedex2.R;
import com.data.PokedexData;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/// This class parser data from csv file -> something
public class AppResourceManager {
    private final String TAG = "AppResourceMgr-" + CommonValue.Arthur;
    private static AppResourceManager mInstance = null;
    private int mRawId[] = { R.raw.gen1, R.raw.gen2, R.raw.gen3, R.raw.gen4, R.raw.gen5, R.raw.gen6, R.raw.gen7, R.raw.gen8, R.raw.gen9 };
    private final int MIN_GEN = 0;
    private final int MAX_GEN = 9;

    public final int GEN1 = 0, GEN2 = 1, GEN3 = 2, GEN4 = 3, GEN5 = 4, GEN6 = 5, GEN7 = 6, GEN8 = 7, GEN9 = 8, GEN_E = -1;
    public final int ALL_GEN_INDEX[] = { GEN1, GEN2, GEN3, GEN4, GEN5, GEN6, GEN7, GEN8, GEN9 };
    private final int mMaxIndex[] = { 152, 252, 387, 494, 650, 722, 810, 906, 1011 };
    private final int mMinIndex[] = {   1, 152, 252, 387, 494, 650, 722, 810,  906 };

    private Resources mResources;
    private Context mAppContext;
    private int index=0;

    public static AppResourceManager getInstance() {
        if(mInstance == null) {
            mInstance = new AppResourceManager();
        }
        synchronized (mInstance) {
            return mInstance;
        }
    }

    public void init(Context context, Resources resources) {
        mAppContext = context;
        mResources  = resources;
    }

    private void CheckResourceStatus() {
        try {
            InputStream is = mResources.openRawResource(0);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            while (reader.ready()) {
                String line = reader.readLine();
                Log.d(TAG, "CheckResourceStatus: " + line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean getListName(int gen_id) {
        try {
            InputStream is = mResources.openRawResource(mRawId[gen_id]);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            while (reader.ready()) {
                String line = reader.readLine();
                String[] data_array = line.split(",");
                PokedexData.getInstance().addToNationalList(data_array[1], index, data_array[0]);
                index += 1;
            }
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public String findDetailByIndex(int gen_id, int index) {
        Log.d(TAG, "findDetailByIndex: gen_id: "+gen_id+ " - index : "+index);
        String result = null;
        if(gen_id < 0) {
            return result;
        }

        try {
            InputStream is = mResources.openRawResource(mRawId[gen_id]);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            int var = index - mMinIndex[gen_id];
            while (reader.ready()) {
                String line = reader.readLine();
                if(var == 0) {
                    result = line;
                    break;
                }
                var -= 1;
            }
        } catch (IOException e) {
            Log.d(TAG, "E: " + e.getMessage());
        }
        return result;
    }

    public int getIdByName(String name) {
        int idx = 0;
        for(String item : PokedexData.getInstance().getList()) {
            idx += 1;
            Log.d(TAG, "getIdByName: <<Show list>> ["+idx+"] "+item);
            if(item.equals(name)) {
                return idx;
            }
        }
        return 0;
    }

    public String getDetailById(int index) {
        int gen = GEN_E;
        if(index == 0) { return null; }
        else if(index < mMaxIndex[GEN1]) { gen = GEN1; }
        else if(index < mMaxIndex[GEN2]) { gen = GEN2; }
        else if(index < mMaxIndex[GEN3]) { gen = GEN3; }
        else if(index < mMaxIndex[GEN4]) { gen = GEN4; }
        else if(index < mMaxIndex[GEN5]) { gen = GEN5; }
        else if(index < mMaxIndex[GEN6]) { gen = GEN6; }
        else if(index < mMaxIndex[GEN7]) { gen = GEN7; }
        else if(index < mMaxIndex[GEN8]) { gen = GEN8; }
        else if(index < mMaxIndex[GEN9]) { gen = GEN9; }
        return findDetailByIndex(gen, index);
    }


    public int getImageIdByName(String name) {
        switch (name) {
            case "Farfetch'd"   : return R.drawable.farfetchd;
            case "Ho-oh"        : return R.drawable.ho_oh;
            case "Mr.Mime"      : return R.drawable.mr_mime;
            case "Mime Jr."     : return R.drawable.mime_jr;
            case "Porygon-Z"    : return R.drawable.porygon_z;
            case "Giratina"     : return R.drawable.giratina_altered;
            case "Shaymin"      : return R.drawable.shaymin_land;
            case "Flabébé"      : return R.drawable.flabebe;
            case "Wishiwashi"   : return R.drawable.wishiwashi_solo;
            case "Type: Null"   : return R.drawable.type_null;
            case "Jangmo-o"     : return R.drawable.jangmo_o;
            case "Hakamo-o"     : return R.drawable.hakamo_o;
            case "Kommo-o"      : return R.drawable.kommo_o;
            case "Tapu Koko"    : return R.drawable.tapu_koko;
            case "Tapu Lele"    : return R.drawable.tapu_lele;
            case "Tapu Bulu"    : return R.drawable.tapu_bulu;
            case "Tapu Fini"    : return R.drawable.tapu_fini;
            case "Mr. Rime"     : return R.drawable.mr_rime;
            case "Urshifu"      : return R.drawable.urshifu_single_strike;
            case "Great Tusk"   : return R.drawable.great_tusk;
            case "Brute Bonnet" : return R.drawable.brute_bonnet;
            case "Flutter Mane" : return R.drawable.flutter_mane;
            case "Slither Wing" : return R.drawable.slither_wing;
            case "Sandy Shocks" : return R.drawable.sandy_shocks;
            case "Iron Treads"  : return R.drawable.iron_treads;
            case "Iron Bundle"  : return R.drawable.iron_bundle;
            case "Iron Hands"   : return R.drawable.iron_hands;
            case "Iron Jugulis" : return R.drawable.iron_jugulis;
            case "Iron Moth"    : return R.drawable.iron_moth;
            case "Iron Thorns"  : return R.drawable.iron_thorns;
            case "Wo-Chien"     : return R.drawable.wo_chien;
            case "Chien-Pao"    : return R.drawable.chien_pao;
            case "Ting-Lu"      : return R.drawable.ting_lu;
            case "Chi-Yu"       : return R.drawable.chi_yu;
            case "Roaring Moon" : return R.drawable.roaring_moon;
            case "Iron Valiant" : return R.drawable.iron_valiant;
            case "Walking Wake" : return R.drawable.walking_wake;
            case "Iron Leaves"  : return R.drawable.iron_leaves;
            case "Nidoran♀"     : return R.drawable.nidoran_f;
            case "Nidoran♂"     : return R.drawable.nidoran_m;
            case "Sirfetch'd"   : return R.drawable.sirfetchd;
            case "Oricorio"     : return R.drawable.oricorio_baile;
            case "Lycanroc"     : return R.drawable.lycanroc_midday;
            case "Eiscue"       : return R.drawable.eiscue_ice;
            case "Morpeko"      : return R.drawable.morpeko_full_belly;
            case "Enamorus"     : return R.drawable.enamorus_incarnate;
            case "Scream Tail"  : return R.drawable.scream_tail;
            default:
                break;
        }
        int id = mResources.getIdentifier(name.toLowerCase(), "drawable", mAppContext.getPackageName());
        Log.d(TAG, "getImageByName: " + id);
        return id;
    }

    public void getSearchListForName(String name) {
        List<String> mList = PokedexData.getInstance().getList();
        List<String> searchList = new ArrayList<String>();
        for(String item : mList) {
            if(item.contains(name)) {
                searchList.add(item);
            }
        }
        PokedexData.getInstance().setSearchList(searchList);
        return;
    }
}