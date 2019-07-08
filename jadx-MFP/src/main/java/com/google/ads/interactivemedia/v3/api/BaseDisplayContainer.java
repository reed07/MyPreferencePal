package com.google.ads.interactivemedia.v3.api;

import android.view.View;
import android.view.ViewGroup;
import java.util.Collection;

/* compiled from: IMASDK */
public interface BaseDisplayContainer {
    void destroy();

    ViewGroup getAdContainer();

    Collection<CompanionAdSlot> getCompanionSlots();

    void registerVideoControlsOverlay(View view);

    void setAdContainer(ViewGroup viewGroup);

    void setCompanionSlots(Collection<CompanionAdSlot> collection);

    void unregisterAllVideoControlsOverlays();
}
