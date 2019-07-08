package com.google.ads.interactivemedia.v3.api.player;

/* compiled from: IMASDK */
public interface VideoAdPlayer extends AdProgressProvider, VolumeProvider {

    /* compiled from: IMASDK */
    public interface VideoAdPlayerCallback {
        void onBuffering();

        void onEnded();

        void onError();

        void onLoaded();

        void onPause();

        void onPlay();

        void onResume();

        void onVolumeChanged(int i);
    }

    void addCallback(VideoAdPlayerCallback videoAdPlayerCallback);

    void loadAd(String str);

    void pauseAd();

    void playAd();

    void removeCallback(VideoAdPlayerCallback videoAdPlayerCallback);

    @Deprecated
    void resumeAd();

    void stopAd();
}
