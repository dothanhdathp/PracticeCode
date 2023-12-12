package com.data;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.util.Pair;

import com.service.CommonValue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class StaticData {
    private final String TAG = "StaticData-" + CommonValue.getInstance().getOwnner();
    private final int mMaxIndex[] = { 151, 251, 386, 493, 649, 721, 809, 905, 1010 };
    private final int MIN_GEN = 0;
    private final int MAX_GEN = 9;

    public final int GEN1 = 0, GEN2 = 1, GEN3 = 2, GEN4 = 3, GEN5 = 4, GEN6 = 5, GEN7 = 6, GEN8 = 7, GEN9 = 8;
    public final int ALL_GEN_INDEX[] = { GEN1, GEN2, GEN3, GEN4, GEN5, GEN6, GEN7, GEN8, GEN9 };
    private File mDataFile[] = {null,null,null,null,null,null,null,null,null};
    private String mDataFileName[] = { "gen1.csv", "gen2.csv", "gen3.csv", "gen4.csv", "gen5.csv", "gen6.csv", "gen7.csv", "gen8.csv", "gen9.csv" };

    private List<String> mList = new ArrayList<String>();

    public StaticData() {
        for ( int i : ALL_GEN_INDEX) {
            mDataFile[i] = new File(Environment.getExternalStorageDirectory(), mDataFileName[i]);
        }
    }

    private static StaticData mInstance = null;

    public static StaticData getInstance() {
        if(mInstance == null) {
            mInstance = new StaticData();
        }
        return mInstance;
    }

    public boolean allDataFilesExist() {
        for ( int i : ALL_GEN_INDEX) {
            if(!mDataFile[i].exists()) {
                return (boolean)false;
            }
        }
        return (boolean)true;
    }

    public File getDataFile(int index) {
        File f = mDataFile[index];
        if(!f.exists()) {
            Log.d(TAG, "getDataFile: data file not exist!");
        };
        return f;
    }

    public void getMinIndex(int genId) {
        if((genId < MIN_GEN) || (genId > MAX_GEN)) {
            return;
        }
    }

    public void getMaxIndex(int genId) {
        if((genId < MIN_GEN) || (genId > MAX_GEN)) {
            return;
        }
    }

    public void pushName(String name) {
        if(mList.size() < 10) {
            Log.d(TAG, "pushName: "+ name);
        }
        mList.add(name);
    }

    public List<String> getList() {
        return mList;
    }
}