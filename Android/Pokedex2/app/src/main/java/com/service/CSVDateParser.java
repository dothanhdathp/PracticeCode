package com.service;

import android.os.ConditionVariable;
import android.os.Environment;
import android.util.Log;

import com.activity.pokedex2.PokedexMainActivity;
import com.data.StaticData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.List;
import java.util.Scanner;

/// This class parser data from csv file -> something
public class CSVDateParser {
    String TAG = CommonValue.getInstance().getOwnner() + "_CSVDateParser";
    private static CSVDateParser mInstance = null;
    public List<String> mListGen1;
    private static File data_file;

    public class PokemonItem {
        public int Idx;
        public String name;
        public String type_1;
        public String type_2;
        public int hp;
        public int atk;
        public int satk;
        public int def;
        public int sdef;
        public int spd;
    }

    public static CSVDateParser getInstance() {
        if(mInstance == null) {
            mInstance = new CSVDateParser();
            data_file = new File(Environment.getExternalStorageDirectory(), "gen1.csv");
        }
        synchronized (mInstance) {
            return mInstance;
        }
    }

    public boolean readData() {
        if (!data_file.exists()) {
            Log.d(TAG, "Database file not exist!");
            return false;
        }
        try {
            Scanner scanner = new Scanner(data_file);
            while(scanner.hasNext()){
                //read single line, put in string
                String data = scanner.next();
                String[] DataArray = data.split(",");
                StaticData.getInstance().pushName(DataArray[1]);
            }
            return true;
        } catch (FileNotFoundException e) {
            Log.d(TAG, "Can't read file, E: " + e.getMessage());
        }
        return false;
    }

    public String findDetailByName(String name) {
        try {
            Scanner scanner = new Scanner(data_file);
            while(scanner.hasNext()) {
                //read single line, put in string
                String data = scanner.next();
                String[] DataArray = data.split(",");
                if(name.equals(DataArray[1])) {
                    Log.d(TAG, "findDetailByName: data founded!");
                    return data;
                };
            }
        } catch (FileNotFoundException e) {
            Log.d(TAG, "Can't read file, E: " + e.getMessage());
        }
        Log.d(TAG, "findDetailByName("+name+"): data not found!");
        return null;
    }
}
