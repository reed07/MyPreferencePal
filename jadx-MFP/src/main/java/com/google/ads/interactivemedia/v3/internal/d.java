package com.google.ads.interactivemedia.v3.internal;

import com.facebook.internal.AnalyticsEvents;

/* compiled from: IMASDK */
public enum d {
    NATIVE(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE),
    JAVASCRIPT("javascript"),
    NONE("none");
    
    private final String d;

    private d(String str) {
        this.d = str;
    }

    public final String toString() {
        return this.d;
    }
}
