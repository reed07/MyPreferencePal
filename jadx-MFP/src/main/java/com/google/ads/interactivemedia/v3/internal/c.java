package com.google.ads.interactivemedia.v3.internal;

import com.facebook.internal.AnalyticsEvents;
import com.mopub.common.AdType;

/* compiled from: IMASDK */
public enum c {
    HTML(AdType.HTML),
    NATIVE(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE);
    
    private final String c;

    private c(String str) {
        this.c = str;
    }

    public final String toString() {
        return this.c;
    }
}
