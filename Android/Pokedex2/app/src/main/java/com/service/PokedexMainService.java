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

    public void startClaimData() {
        Log.d(TAG, "startClaimData: ");
        sendMessage(PokedexServiceMessage.PKD_MESSAGE_CLAIM_DATA);
    }

    private void sendMessage(int message_id) {
        Log.d(TAG, "sendMessage: " + message_id);
        mServiceThread.postMessage(message_id);
    }

    public void postMessage(int message_id) {
        Message msg = mServiceHandler.obtainMessage();
        msg.what = message_id;
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
                case PokedexServiceMessage.PKD_MESSAGE_CLAIM_DATA_DONE:
                    Log.d(TAG, "Claim data done!");
                    mServiceCallback.onLoadList();
                    break;
                default:
                    break;
            }
        }
    }
}