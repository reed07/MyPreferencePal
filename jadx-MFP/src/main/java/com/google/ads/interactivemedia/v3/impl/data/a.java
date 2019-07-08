package com.google.ads.interactivemedia.v3.impl.data;

import com.google.ads.interactivemedia.v3.internal.afs;

@afs(a = e.class)
/* compiled from: IMASDK */
public abstract class a {
    public abstract String appState();

    public abstract String eventId();

    public abstract long nativeTime();

    public abstract boolean nativeViewAttached();

    public abstract v nativeViewBounds();

    public abstract boolean nativeViewHidden();

    public abstract v nativeViewVisibleBounds();

    public abstract double nativeVolume();

    public abstract String queryId();

    public abstract String vastEvent();

    public static b builder() {
        return new g();
    }
}
