package com.google.ads.interactivemedia.v3.impl.data;

import com.google.ads.interactivemedia.v3.internal.afs;

@afs(a = k.class)
/* compiled from: IMASDK */
public abstract class x {
    public abstract double end();

    public abstract boolean played();

    public abstract double start();

    private static x create(double d, double d2, boolean z) {
        k kVar = new k(d, d2, z);
        return kVar;
    }
}
