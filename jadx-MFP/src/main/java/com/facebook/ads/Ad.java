package com.facebook.ads;

public interface Ad {
    void destroy();

    String getPlacementId();

    boolean isAdInvalidated();

    void loadAd();

    void loadAdFromBid(String str);
}
