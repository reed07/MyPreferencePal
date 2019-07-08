package com.google.ads.interactivemedia.v3.internal;

import com.google.ads.interactivemedia.v3.api.AdError;
import com.google.ads.interactivemedia.v3.api.AdErrorEvent;

/* compiled from: IMASDK */
public final class acc implements AdErrorEvent {
    private final AdError a;
    private final Object b;

    acc(AdError adError) {
        this.a = adError;
        this.b = null;
    }

    acc(AdError adError, Object obj) {
        this.a = adError;
        this.b = obj;
    }

    public final AdError getError() {
        return this.a;
    }

    public final Object getUserRequestContext() {
        return this.b;
    }
}
