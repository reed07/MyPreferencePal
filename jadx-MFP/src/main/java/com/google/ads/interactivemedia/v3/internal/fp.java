package com.google.ads.interactivemedia.v3.internal;

import java.io.EOFException;
import java.io.IOException;

/* compiled from: IMASDK */
public final class fp implements gc {
    public final void a(long j, int i, int i2, int i3, gd gdVar) {
    }

    public final void a(bs bsVar) {
    }

    public final int a(fr frVar, int i, boolean z) throws IOException, InterruptedException {
        int a = frVar.a(i);
        if (a != -1) {
            return a;
        }
        if (z) {
            return -1;
        }
        throw new EOFException();
    }

    public final void a(ut utVar, int i) {
        utVar.d(i);
    }
}
