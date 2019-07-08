package com.google.ads.interactivemedia.v3.api.player;

import java.util.HashMap;
import java.util.List;

/* compiled from: IMASDK */
public interface VideoStreamPlayer extends ContentProgressProvider, VolumeProvider {

    /* compiled from: IMASDK */
    public interface VideoStreamPlayerCallback {
        void onUserTextReceived(String str);

        void onVolumeChanged(int i);
    }

    void addCallback(VideoStreamPlayerCallback videoStreamPlayerCallback);

    int getVolume();

    void loadUrl(String str, List<HashMap<String, String>> list);

    void onAdBreakEnded();

    void onAdBreakStarted();

    void onAdPeriodEnded();

    void onAdPeriodStarted();

    void removeCallback(VideoStreamPlayerCallback videoStreamPlayerCallback);

    void seek(long j);
}
