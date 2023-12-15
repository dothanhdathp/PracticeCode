package com.service;

import android.util.Log;

import java.util.HashMap;

public class CommonValue {
    public static final String Arthur = "TAD_ZEILA";
    public static final String Key = "ieujsfv";

    private HashMap<String, Integer> mColor;

    private static CommonValue mInstance;;

    public static CommonValue getInstance() {
        if(mInstance == null) {
            mInstance = new CommonValue();
            mInstance.mColor = new HashMap<String, Integer>();
            mInstance.mColor.put("NORMAL"   , 0xD2D2D200);
            mInstance.mColor.put("FIRE"     , 0xFF2D0000);
            mInstance.mColor.put("WATER"    , 0x33A1FF00);
            mInstance.mColor.put("GRASS"    , 0x00FF3700);
            mInstance.mColor.put("ELECTRIC" , 0xF4FF0000);
            mInstance.mColor.put("ICE"      , 0x00FFC900);
            mInstance.mColor.put("FIGHTING" , 0xD3000000);
            mInstance.mColor.put("POISON"   , 0xAE00D300);
            mInstance.mColor.put("GROUND"   , 0xD38C0000);
            mInstance.mColor.put("FLYING"   , 0xB3E2FF00);
            mInstance.mColor.put("PSYCHIC"  , 0xE88DFF00);
            mInstance.mColor.put("BUG"      , 0xC0FE3300);
            mInstance.mColor.put("ROCK"     , 0x857B5200);
            mInstance.mColor.put("GHOST"    , 0x44306100);
            mInstance.mColor.put("DRAGON"   , 0x4B5B9100);
            mInstance.mColor.put("DARK"     , 0x2F2F2F00);
            mInstance.mColor.put("STEEL"    , 0xABABAB00);
            mInstance.mColor.put("FAIRY"    , 0xFFB5F900);
        }
        return mInstance;
    }

    public int getColorType(String type) {
        String TAG = "CommonValue-TAD_ZEILA";
        int value = 0;
        switch (type) {
            case "NORMAL"   : { value = 0xFFD2D2D2; break; }
            case "FIRE"     : { value = 0xFFFF2D00; break; }
            case "WATER"    : { value = 0xFF33A1FF; break; }
            case "GRASS"    : { value = 0xFF00FF37; break; }
            case "ELECTRIC" : { value = 0xFFF4FF00; break; }
            case "ICE"      : { value = 0xFF00FFC9; break; }
            case "FIGHTING" : { value = 0xFFD30000; break; }
            case "POISON"   : { value = 0xFFAE00D3; break; }
            case "GROUND"   : { value = 0xFFD38C00; break; }
            case "FLYING"   : { value = 0xFFB3E2FF; break; }
            case "PSYCHIC"  : { value = 0xFFE88DFF; break; }
            case "BUG"      : { value = 0xFF9DBD3D; break; }
            case "ROCK"     : { value = 0xFF857B52; break; }
            case "GHOST"    : { value = 0xFF443061; break; }
            case "DRAGON"   : { value = 0xFF4B5B91; break; }
            case "DARK"     : { value = 0xFF2F2F2F; break; }
            case "STEEL"    : { value = 0xFFABABAB; break; }
            case "FAIRY"    : { value = 0xFFFFB5F9; break; }
            default:
                Log.d(TAG, type + " <default> ");
                break;
        }
        return value;
    }
}