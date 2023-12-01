package com.service;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/// This class parser data from csv file -> something
public class CSVDateParser {
    String TAG = CommonValue.getInstance().getOwnner() + "_CSVDateParser";

    public void readData() {
        String fileName = "gen1.csv";

        File file = new File(Environment.getExternalStorageDirectory(), fileName);

        if(!file.exists()) {
            Log.d(TAG, fileName + " not exist!");
            return;
        }
        Log.d(TAG, fileName + " exist!");
        try {
            Scanner scanner = new Scanner(file);
            while(scanner.hasNext()){
                //read single line, put in string
                String data = scanner.next();
                Log.d(TAG, "data: " + data);
            }
        } catch (FileNotFoundException e) {
            Log.d(TAG, "Can't read file, E: " + e.getMessage());
        }
    }
}
