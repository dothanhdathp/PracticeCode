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
/* This belong have data or not
    public List<String> mListGen2;
    public List<String> mListGen3;
    public List<String> mListGen4;
    public List<String> mListGen5;
    public List<String> mListGen6;
    public List<String> mListGen7;
    public List<String> mListGen8;
    public List<String> mListGen9;
*/
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
        synchronized (mInstance) {
            if(mInstance == null) {
                mInstance = new CSVDateParser();
            }
            return mInstance;
        }
    }

    public void readData() {
        String fileName = "gen1.csv";
        File file = new File(Environment.getExternalStorageDirectory(), fileName);

        if(!file.exists()) {
//            try {
//                file.createNewFile();
//                OutputStream fo = new FileOutputStream(file);
//                fo.write(StaticData.data());
//                fo.close();
//            } catch (IOException io) {
//                Log.d(TAG, "Can not create file: " + fileName);
//                Log.d(TAG, fileName + " not exist!");
//                return;
//            }
            Log.d(TAG, "Database file not exist!");
        }
        Log.d(TAG, "readData: file link = " + file.getPath());
        try {
            Scanner scanner = new Scanner(file);
            while(scanner.hasNext()){
                //read single line, put in string
                String data = scanner.next();
                String[] DataArray = data.split(",");
                StaticData.getInstance().pushName(DataArray[1]);
            }
        } catch (FileNotFoundException e) {
            Log.d(TAG, "Can't read file, E: " + e.getMessage());
        }
    }
}
