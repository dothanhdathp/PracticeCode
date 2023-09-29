package com.example.pokedex;


import java.util.HashMap;

// Singleton class
public class GetIntent {
    HashMap<String, Integer> mMapIntent = null;

    private GetIntent() {
        // create class
        mMapIntent = new HashMap<String, Integer>();
    }
    static GetIntent mInstanceGetIntent = null;

    static GetIntent getInstance() {
        if ( null == mInstanceGetIntent ) {
            mInstanceGetIntent = new GetIntent();
        }
        return mInstanceGetIntent;
    }


}
