package com.data;

import com.service.CommonValue;

import java.util.ArrayList;
import java.util.List;

public class PokedexData {
    private final String TAG = "PokedexData-" + CommonValue.Arthur;

    private List<String> mNationalList = new ArrayList<String>();
    private static PokedexData mInstance;

    public static PokedexData getInstance() {
        if(mInstance == null) {
            mInstance = new PokedexData();
        }
        return mInstance;
    }

    public void addToNationalList(String name) {
        mNationalList.add(name);
    }

    public List<String> getList() {
        return mNationalList;
    }
}