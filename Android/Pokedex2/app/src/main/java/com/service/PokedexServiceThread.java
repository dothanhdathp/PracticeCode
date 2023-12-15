package com.service;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.data.PokedexServiceMessage;

public class PokedexServiceThread extends Thread {
    private final String TAG = "PokedexServiceThread-"+ CommonValue.Arthur;
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
    public void postMessage(int message_id, String key, String data) {
        Log.d(TAG, "postMessage: " + message_id +" | "+key+" | "+data);
        if(mHandler == null) {
            mHandler = new PokedexThreadHandler();
        }
        Message msg = mHandler.obtainMessage();
        msg.what = message_id;
        Bundle b = new Bundle();
        b.putString(key, data);
        msg.setData(b);
        mHandler.sendMessage(msg);
    }

    public void postMessage(int message_id, String key, int data) {
        Log.d(TAG, "postMessage: " + message_id +" | "+key+" | "+data);
        if(mHandler == null) {
            mHandler = new PokedexThreadHandler();
        }
        Message msg = mHandler.obtainMessage();
        msg.what = message_id;
        Bundle b = new Bundle();
        b.putInt(key, data);
        msg.setData(b);
        mHandler.sendMessage(msg);
    }

    private class PokedexThreadHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            Log.d(TAG, "handleMessage: " + msg.what);
            switch (msg.what) {
                case PokedexServiceMessage.MSG_REQUEST_GETLIST:
                    onMSG_REQUEST_GETLIST(msg);
                    break;

                case PokedexServiceMessage.MSG_REQUEST_DETAIL_BY_NAME:
                    onMSG_REQUEST_DETAIL_BY_NAME(msg);
                    break;

                case PokedexServiceMessage.MSG_REQUEST_DETAIL_BY_NATION_ID:
                    onMSG_REQUEST_DETAIL_BY_NATION_ID(msg);
                    break;

                case PokedexServiceMessage.MSG_REQUEST_SEARCH_NAME:
                    onMSG_REQUEST_SEARCH_NAME(msg);
                    break;
                default:
                    break;
            }
        }

        public void onMSG_REQUEST_GETLIST(Message msg) {
            for(int index : AppResourceManager.getInstance().ALL_GEN_INDEX) {
                boolean res = AppResourceManager.getInstance().getListName(index);
                if(!res) {
                    Log.d(TAG, "Get List False Gen_"+(index+1));
                }
                mService.threadRespond(PokedexServiceMessage.MSG_RESPOND_GETLIST_DONE, "");
            }
            return;
        }

        public void onMSG_REQUEST_DETAIL_BY_NAME(Message msg) {
            String name = msg.getData().getString(CommonValue.Key);
            int id = AppResourceManager.getInstance().getIdByName(name);
            String result = AppResourceManager.getInstance().getDetailById(id);
            Log.d(TAG, "handleMessage: MSG_REQUEST_DETAIL_BY_NAME | " + id + " | " + result);
            if(result != null) {
                mService.threadRespond(PokedexServiceMessage.MSG_RESPOND_DETAIL_RESULT, result);
                String data[] = result.split(",");
                int imageId = AppResourceManager.getInstance().getImageIdByName(data[1]);
                mService.threadRespond(PokedexServiceMessage.MSG_RESPOND_IMAGE_ID, imageId);
            }
        }

        public void onMSG_REQUEST_DETAIL_BY_NATION_ID(Message msg) {
            int id = msg.getData().getInt(CommonValue.Key);
            String result = AppResourceManager.getInstance().getDetailById(id);
            Log.d(TAG, "handleMessage: MSG_REQUEST_DETAIL_BY_NATION_ID | " + id + " | " + result);
            if(result != null) {
                mService.threadRespond(PokedexServiceMessage.MSG_RESPOND_DETAIL_RESULT, result);
                String data[] = result.split(",");
                int imageId = AppResourceManager.getInstance().getImageIdByName(data[1]);
                mService.threadRespond(PokedexServiceMessage.MSG_RESPOND_IMAGE_ID, imageId);
            }
        }

        public void onMSG_REQUEST_SEARCH_NAME(Message msg) {
            String name = msg.getData().getString(CommonValue.Key);
            AppResourceManager.getInstance().getSearchListForName(name);
            mService.threadRespond(PokedexServiceMessage.MSG_RESPOND_SEARCH_LIST_DONE);
        }
    }
}
