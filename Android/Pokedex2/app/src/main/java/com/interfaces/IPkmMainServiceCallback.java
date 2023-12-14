package com.interfaces;

import java.util.List;

public interface IPkmMainServiceCallback {
    public void onCallLog(String log_test);
    public void onServiceReady();
    public void onLoadList();
    public void onStartPokemonDetail(String data);
    public void onUpdatePokemonImage(int imageID);
    public void releaseScreen(String classname);
}
