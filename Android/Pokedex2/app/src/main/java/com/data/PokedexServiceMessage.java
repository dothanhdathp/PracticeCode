package com.data;

import android.os.Bundle;
import android.os.Message;

public class PokedexServiceMessage {
    public static final String Key = "TEST";
    /// REQUEST
    public static final int MSG_REQUEST_START = 0;
    public static final int MSG_REQUEST_GETLIST = 1;
    public static final int MSG_REQUEST_DETAIL_BY_NAME = 2;

    /// RESPOND
    public static final int MSG_RESPOND_START = 1000;
    public static final int MSG_RESPOND_GETLIST_DONE = 1001;
    public static final int MSG_RESPOND_DETAIL_RESULT = 1002;
}
