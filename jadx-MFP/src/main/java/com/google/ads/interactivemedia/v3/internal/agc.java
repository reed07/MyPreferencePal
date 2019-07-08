package com.google.ads.interactivemedia.v3.internal;

import java.io.Serializable;

/* compiled from: IMASDK */
final class agc implements Serializable {
    private static final long serialVersionUID = 0;
    private final Object[] a;

    agc(Object[] objArr) {
        this.a = objArr;
    }

    /* access modifiers changed from: 0000 */
    public final Object readResolve() {
        return agb.a((E[]) this.a);
    }
}
