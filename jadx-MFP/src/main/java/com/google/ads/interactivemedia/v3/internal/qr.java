package com.google.ads.interactivemedia.v3.internal;

import java.util.Collections;
import java.util.List;

/* compiled from: IMASDK */
public abstract class qr implements la<qr> {
    public final String n;
    public final List<String> o;
    public final boolean p;

    protected qr(String str, List<String> list, boolean z) {
        this.n = str;
        this.o = Collections.unmodifiableList(list);
        this.p = z;
    }
}
