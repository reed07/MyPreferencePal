package com.google.ads.interactivemedia.v3.internal;

import com.google.ads.interactivemedia.v3.api.player.AdProgressProvider;
import com.google.ads.interactivemedia.v3.api.player.VideoProgressUpdate;

/* compiled from: IMASDK */
public final class acg extends aeg {
    private final AdProgressProvider a;

    public acg(AdProgressProvider adProgressProvider, long j) {
        super(j);
        this.a = adProgressProvider;
    }

    public final VideoProgressUpdate a() {
        return this.a.getAdProgress();
    }
}
