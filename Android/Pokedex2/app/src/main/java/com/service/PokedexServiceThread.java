package com.service;

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

    public void postMessage(int message_id) {
        if(mHandler == null) {
            mHandler = new PokedexThreadHandler();
        }
        Message msg = mHandler.obtainMessage();
        msg.what = message_id;
        mHandler.sendMessage(msg);
    }

    private class PokedexThreadHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            Log.d(TAG, "handleMessage: " + msg.what);
            switch (msg.what) {
                case PokedexServiceMessage.PKD_MESSAGE_CLAIM_DATA:
                    CSVDateParser mCSVDateParser = new CSVDateParser();
                    mCSVDateParser.readData();
                    mService.postMessage(PokedexServiceMessage.PKD_MESSAGE_CLAIM_DATA_DONE);
                default:
                    break;
            }
        }
    }
}
