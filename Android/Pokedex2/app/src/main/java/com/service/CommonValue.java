package com.service;

public class CommonValue {
    private String Ownner = "TAD_ZEILA";
    public enum ServiceMessage {
        SERVICE_MESSAGE_LOADING,
        SERVICE_MESSAGE_DONE,
        SERVICE_MESSAGE_MAX // Unknown
    };

    private static CommonValue mInstance = null;
    public String getOwnner() { return Ownner; }

    // Singleton
    public static CommonValue getInstance() {
        if(mInstance == null) {
            mInstance = new CommonValue();
        }
        return mInstance;
    }
}
