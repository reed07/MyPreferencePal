package com.google.ads.interactivemedia.v3.internal;

import java.util.Arrays;

/* compiled from: IMASDK */
final class pn extends nq {
    private byte[] a;

    public pn(sn snVar, sr srVar, bs bsVar, int i, Object obj, byte[] bArr) {
        super(snVar, srVar, 3, bsVar, i, obj, bArr);
    }

    /* access modifiers changed from: protected */
    public final void a(byte[] bArr, int i) {
        this.a = Arrays.copyOf(bArr, i);
    }

    public final byte[] g() {
        return this.a;
    }
}
