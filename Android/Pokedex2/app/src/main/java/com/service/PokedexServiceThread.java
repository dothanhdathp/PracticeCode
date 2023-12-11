package com.service;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.data.PokedexServiceMessage;

public class PokedexServiceThread extends Thread {
    private final String TAG = "PokedexServiceThread-"+CommonValue.getInstance().getOwnner();
    private PokedexThreadHandler mHandler = null;
    private PokedexMainService mService = null;

    public void init(PokedexMainService service) {
        mService = service;
    }

    @Override
    public void run() {
        Looper.prepare();
        if(mHandler == null) {
            mHandler = new PokedexThreadHandler();
        }

        Looper.loop();
    }

    /// post message to ThreadHandler
    public void postMessage(int message_id, String d1, String d2) {
        Log.d(TAG, "postMessage: " + message_id +" | "+d1+" | "+d2);
        if(mHandler == null) {
            mHandler = new PokedexThreadHandler();
        }
        Message msg = mHandler.obtainMessage();
        msg.what = message_id;
        Bundle b = new Bundle();
        b.putString(d1, d2);
        msg.setData(b);
        mHandler.sendMessage(msg);
    }

    private class PokedexThreadHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            Log.d(TAG, "handleMessage: " + msg.what);
            switch (msg.what) {
                case PokedexServiceMessage.MSG_REQUEST_GETLIST:;
                    CSVDateParser.getInstance().readData();
                    mService.threadRespond(PokedexServiceMessage.MSG_RESPOND_GETLIST_DONE, "", "");
                    break;
                case PokedexServiceMessage.MSG_REQUEST_DETAIL_BY_NAME:
                    String data = msg.getData().getString(PokedexServiceMessage.Key);
                    String result = CSVDateParser.getInstance().findDetailByName(data);
                    Log.d(TAG, "handleMessage: MSG_REQUEST_DETAIL_BY_NAME | " + data + " | " + result);
                    if(result != null) {
                        mService.threadRespond(PokedexServiceMessage.MSG_RESPOND_DETAIL_RESULT, PokedexServiceMessage.Key, result);
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
