package com.google.ads.interactivemedia.v3.internal;

import android.util.SparseArray;

/* compiled from: IMASDK */
public final class qg {
    private final SparseArray<ve> a = new SparseArray<>();

    public final ve a(int i) {
        ve veVar = (ve) this.a.get(i);
        if (veVar != null) {
            return veVar;
        }
        ve veVar2 = new ve(Long.MAX_VALUE);
        this.a.put(i, veVar2);
        return veVar2;
    }

    public final void a() {
        this.a.clear();
    }
}
