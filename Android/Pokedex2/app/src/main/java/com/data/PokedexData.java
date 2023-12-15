package com.data;

import android.util.Log;

import com.service.CommonValue;

import java.util.ArrayList;
import java.util.List;

public class PokedexData {
    private final String TAG = "PokedexData-" + CommonValue.Arthur;

    private List<String> mNationalList = new ArrayList<String>();
    private List<String> mSearchList;

    private static PokedexData mInstance;

    public static PokedexData getInstance() {
        if(mInstance == null) {
            mInstance = new PokedexData();
        }
        return mInstance;
    }

    public void addToNationalList(String name, int index, String real) {
        Log.d(TAG, "addToNationalList: ["+index+"] -> ["+mNationalList.size()+"] "+ name + " {" +real+"}");
        mNationalList.add(name);
    }

    public List<String> getList() {
        return mNationalList;
    }

    public void setSearchList(List<String> list) {
        mSearchList = list;
    }
    public List<String> getSearchList() {
        return mSearchList;
    }
}