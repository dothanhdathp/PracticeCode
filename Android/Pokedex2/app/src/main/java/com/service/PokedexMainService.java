package com.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.Nullable;

import com.data.PokedexServiceMessage;


public class PokedexMainService extends Service {
    private final IBinder mLocalBinder = new LocalBinder();

    private boolean mReady = false;
    private boolean mDataReady = false;

    PokedexMainSeriveThread mSeriveThread = null;

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

    public void startClaimData() {
        sendMessage(PokedexServiceMessage.PKD_MESSAGE_CLAIM_DATA);
    }

    private void sendMessage(int message_id) {
        if(mSeriveThread != null) {
            Message msg = Message.obtain(mSeriveThread.mServiceHandler, message_id);
            mSeriveThread.mServiceHandler.sendMessage(msg);
        }
    }

    private class PokedexMainSeriveThread extends Thread {
        PokedexMainSeriveHandler mServiceHandler = null;

        private PokedexMainSeriveThread() {
            // nothing
        }

        public void run() {
            Looper.prepare();
            mServiceHandler = new PokedexMainSeriveHandler();
            Looper.loop();
        }

        public synchronized void sendMessage(Message msg) {
            if(mServiceHandler != null) {
                mServiceHandler.sendMessage(msg);
            }
        }
    }

    private class PokedexMainSeriveHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case PokedexServiceMessage.PKD_MESSAGE_CLAIM_DATA:

                default:
                    break;
            }
        }
    }
}