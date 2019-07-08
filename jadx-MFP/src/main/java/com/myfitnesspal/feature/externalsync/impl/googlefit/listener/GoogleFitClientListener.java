package com.myfitnesspal.feature.externalsync.impl.googlefit.listener;

public interface GoogleFitClientListener {
    void onConnected();

    void onConnectionDisabled();

    void onConnectionSuspended(int i);
}
