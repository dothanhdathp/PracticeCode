package com.example.broadcast;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.shapes.OvalShape;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

/**
 * AsyncTask<param1, param2, param3> with each parameter is the basic type in Java
 */

public class DumpDownloadAsync extends AsyncTask {
    String Tag = "ASYNC_TAD";

    public class Dump{
        int value;
        public Dump(int x)
        {
            value = x;
        }
    }

    public DumpDownloadAsync() {
        // i need nothing in here
    }

    // This function will be do first
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d(Tag, "onPreExecute ...");
    }

    // This function called after function onPreExecute call
    @Override
    protected Object doInBackground(Object[] objects) {
        // What is object and why i need that?
        // I did the fucking slow task in here
        Log.d(Tag, "This log just for you know that i'm doInBackground()");
        Log.d(Tag, "Thread counting ...");
        int i = 0;
        while (i < 1000000) {
            if((i%100)==0){
                Dump data = new Dump(i*100/1000000);
                publishProgress(data);
            }
            i = i+1;
        }
        Log.d(Tag, "Thread count stop!");
        return null;
    }

    // This fun come when doInBackground call method publishPrgress
    // Before that is onProgressUpdate(Void... values) -> onProgressUpdate(Integer... values)
    @Override
    protected void onProgressUpdate(Object[] values) {
        super.onProgressUpdate(values);
        Dump data = (Dump)values[0];
        Log.d(Tag, "This log just for you know that i'm onProgressUpdate()");
        String mess = "Update progress: "+String.valueOf(data.value)+"%";
        Log.d(Tag, mess);
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        Log.d(Tag, "onPostExecute ...");
    }
}
