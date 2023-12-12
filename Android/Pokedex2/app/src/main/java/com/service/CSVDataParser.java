package com.service;

import android.util.Log;

import com.data.StaticData;

import java.io.FileNotFoundException;
import java.util.Scanner;

/// This class parser data from csv file -> something
public class CSVDataParser {
    private final String TAG = "CSVDataParser-" + CommonValue.getInstance().getOwnner();
    private static CSVDataParser mInstance = null;
    public String mFileArrary[] = {"gen1.csv", "gen2.csv", "gen3.csv", "gen4.csv", "gen5.csv", "gen6.csv", "gen7.csv", "gen8.csv", "gen9.csv"};

    public static CSVDataParser getInstance() {
        if(mInstance == null) {
            mInstance = new CSVDataParser();
        }
        synchronized (mInstance) {
            return mInstance;
        }
    }

//    public void writeDataToFile(String filename, String data) {
//        if (!data_file.exists()) {
//            Log.d("CSVDataParser-TAD_ZEILA", "Database file not exist!");
//            try {
//                FileOutputStream stream = new FileOutputStream(data_file);
//                String data = context.getString(id);
//                stream.write(data.getBytes());
//                stream.close();
//            } catch (IOException e) {
//                Log.d(TAG, "init: e: " + e.getMessage());
//            }
//        }
//    }

    public boolean readData() {
        try {
            Scanner scanner = new Scanner(
                StaticData.getInstance().getDataFile(StaticData.getInstance().GEN1)
            );
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
            Scanner scanner = new Scanner(
                StaticData.getInstance().getDataFile(StaticData.getInstance().GEN1)
            );
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