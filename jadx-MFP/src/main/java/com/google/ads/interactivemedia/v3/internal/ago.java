package com.google.ads.interactivemedia.v3.internal;

import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Map.Entry;

/* compiled from: IMASDK */
final class ago extends agb<Entry<K, V>> {
    private final /* synthetic */ agn a;

    ago(agn agn) {
        this.a = agn;
    }

    public final boolean f() {
        return true;
    }

    public final int size() {
        return this.a.d;
    }

    public final /* synthetic */ Object get(int i) {
        afx.a(i, this.a.d);
        int i2 = i * 2;
        return new SimpleImmutableEntry(this.a.b[this.a.c + i2], this.a.b[i2 + (this.a.c ^ 1)]);
    }
}
