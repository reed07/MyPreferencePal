package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
public abstract class acj {
    public abstract int appVersion();

    public abstract String packageName();

    public static acj create(int i, String str) {
        return new acr(i, str);
    }
}
