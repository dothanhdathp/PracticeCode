package com.data;

import android.util.Log;
import android.util.Pair;

import com.service.CommonValue;

import java.util.ArrayList;
import java.util.List;

public class StaticData {
    private final String TAG = "StaticData-" + CommonValue.getInstance().getOwnner();
    private final Pair<Integer, Integer> RangePair[] = new Pair[]{
            new Pair<>(1,151),
            new Pair<>(152,251),
            new Pair<>(252,386),
            new Pair<>(387,493),
            new Pair<>(494,649),
            new Pair<>(650,721),
            new Pair<>(722,809),
            new Pair<>(810,905),
            new Pair<>(906,1010)
    };
    private final int MIN_GEN = 1;
    private final int MAX_GEN = 9;

    private List<String> mList = new ArrayList<String>();

    private static StaticData mInstance = null;

    public static StaticData getInstance() {
        if(mInstance == null) {
            mInstance = new StaticData();
        }
        return mInstance;
    }

    public int getMinIndex(int genId) {
        if((genId < MIN_GEN) || (genId > MAX_GEN)) {
            return -1;
        }
        return RangePair[genId].first;
    }

    public int getMaxIndex(int genId) {
        if((genId < MIN_GEN) || (genId > MAX_GEN)) {
            return -1;
        }
        return RangePair[genId].second;
    }

    public void pushName(String name) {
        Log.d(TAG, "pushName: "+ name);
        mList.add(name);
    }

    public List<String> getList() {
        return mList;
    }

    public static byte[] data() {
        String data = "001,Bulbasaur,Grass,Poison,45,49,49,65,65,45"
        + "\n002,Ivysaur,Grass,Poison,60,62,63,80,80,60"
        + "\n003,Venusaur,Grass,Poison,80,82,83,100,100,80"
        + "\n004,Charmander,Fire,,39,52,43,60,50,65"
        + "\n005,Charmeleon,Fire,,58,64,58,80,65,80"
        + "\n006,Charizard,Fire,Flying,78,84,78,109,85,100"
        + "\n007,Squirtle,Water,,44,48,65,50,64,43"
        + "\n008,Wartortle,Water,,59,63,80,65,80,58"
        + "\n009,Blastoise,Water,,79,83,100,85,105,78";
        return data.getBytes();
    }
}
