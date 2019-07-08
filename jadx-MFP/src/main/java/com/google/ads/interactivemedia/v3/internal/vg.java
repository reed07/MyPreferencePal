package com.google.ads.interactivemedia.v3.internal;

import java.util.concurrent.ThreadFactory;

final /* synthetic */ class vg implements ThreadFactory {
    private final String a;

    vg(String str) {
        this.a = str;
    }

    public final Thread newThread(Runnable runnable) {
        return vf.a(this.a, runnable);
    }
}
