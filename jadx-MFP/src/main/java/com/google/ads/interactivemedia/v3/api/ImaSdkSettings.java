package com.google.ads.interactivemedia.v3.api;

/* compiled from: IMASDK */
public interface ImaSdkSettings {
    public static final int DEFAULT_MAX_REDIRECTS = 4;

    boolean doesRestrictToCustomPlayer();

    boolean getAutoPlayAdBreaks();

    String getLanguage();

    int getMaxRedirects();

    String getPlayerType();

    String getPlayerVersion();

    String getPpid();

    boolean isDebugMode();

    void setAutoPlayAdBreaks(boolean z);

    void setDebugMode(boolean z);

    void setLanguage(String str);

    void setMaxRedirects(int i);

    void setPlayerType(String str);

    void setPlayerVersion(String str);

    void setPpid(String str);

    void setRestrictToCustomPlayer(boolean z);

    String toString();
}
