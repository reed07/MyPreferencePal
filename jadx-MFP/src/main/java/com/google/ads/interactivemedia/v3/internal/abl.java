package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;

/* compiled from: IMASDK */
final class abl extends xj<Number> {
    abl() {
    }

    private static Number a(abu abu) throws IOException {
        if (abu.f() == abw.NULL) {
            abu.j();
            return null;
        }
        try {
            return Integer.valueOf(abu.m());
        } catch (NumberFormatException e) {
            throw new xh((Throwable) e);
        }
    }

    public final /* synthetic */ Object read(abu abu) throws IOException {
        return a(abu);
    }

    public final /* synthetic */ void write(abx abx, Object obj) throws IOException {
        abx.a((Number) obj);
    }
}
