package com.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.annotation.Nullable;

import com.data.PokedexServiceMessage;
import com.data.StaticData;
import com.interfaces.IPkmMainServiceCallback;

import java.nio.file.ClosedFileSystemException;
import java.util.List;


public class PokedexMainService extends Service {
    private final String TAG = "PokedexMainService-"+CommonValue.getInstance().getOwnner();
    private final IBinder mLocalBinder = new LocalBinder();

    private boolean mReady = false;
    private boolean mDataReady = false;
    PokedexServiceThread mServiceThread = null;
    PokedexServiceHandler mServiceHandler = null;
    IPkmMainServiceCallback mServiceCallback = null;
    // UI
    List<String> mNationalList = null;

    public class LocalBinder extends Binder {
        public PokedexMainService getService() {
            return PokedexMainService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mLocalBinder;
    }

    public boolean isDataReady() { return mDataReady; }
    public boolean isReady() { return mReady; }
    public List<String> getList() {
        return mNationalList;
    };

    public void init(IPkmMainServiceCallback callback) {
        mServiceHandler = new PokedexServiceHandler();
        mServiceThread = new PokedexServiceThread();
        mServiceThread.init(PokedexMainService.this);
        mServiceThread.start();
        mServiceCallback = callback;
        mServiceCallback.onServiceReady();
    };

    private void sendMessage(int message_id, String d1, String d2) {
        Log.d(TAG, "sendMessage: " + message_id +" | "+d1+" | "+d2);
        mServiceThread.postMessage(message_id, d1, d2);
    }

    /// post message to ServiceHandler
    public void threadRespond(int message_id, String d1, String d2) {
        Message msg = mServiceHandler.obtainMessage();
        msg.what = message_id;
        Bundle b = new Bundle();
        b.putString(d1, d2);
        msg.setData(b);
        mServiceHandler.sendMessage(msg);
    }

    public List<String> getPkmList() {
        mNationalList = StaticData.getInstance().getList();
        return mNationalList;
    }

    private class PokedexServiceHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            Log.d(TAG, "handleMessage: " + msg.what);
            switch (msg.what) {
                case PokedexServiceMessage.MSG_RESPOND_GETLIST_DONE:
                    mServiceCallback.onLoadList();
                    break;
                case PokedexServiceMessage.MSG_RESPOND_DETAIL_RESULT:
                    mServiceCallback.onStartPokemonDetail(msg.getData().getString(PokedexServiceMessage.Key));
                default:
                    break;
            }
        }
    }

    public void request(int message) {
        sendMessage(message, "", "");
    }

    public void request(int message, String data) {
        sendMessage(message, PokedexServiceMessage.Key, data);
    }

    public void request(int message, String data1, String data2) {
        sendMessage(message, data1, data2);
    }
}