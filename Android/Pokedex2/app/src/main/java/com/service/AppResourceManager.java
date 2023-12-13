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
    private final int mMaxIndex[] = { 152, 252, 387, 494, 650, 721, 809, 905, 1011 };
    private final int mMinIndex[] = {   1, 152, 252, 387, 494, 650, 721, 809,  905 };

    private Resources mResources;
    private Context mAppContext;

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
                PokedexData.getInstance().addToNationalList(data_array[1]);
            }
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public String findDetailByIndex(int gen_id, int index) {
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
        int id = mResources.getIdentifier(name.toLowerCase(), "drawable", mAppContext.getPackageName());
        Log.d(TAG, "getImageByName: " + id);
        return id;
    }
}