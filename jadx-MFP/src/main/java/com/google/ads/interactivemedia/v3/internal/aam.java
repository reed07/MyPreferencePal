package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.math.BigInteger;

/* compiled from: IMASDK */
final class aam extends xj<BigInteger> {
    aam() {
    }

    private static BigInteger a(abu abu) throws IOException {
        if (abu.f() == abw.NULL) {
            abu.j();
            return null;
        }
        try {
            return new BigInteger(abu.h());
        } catch (NumberFormatException e) {
            throw new xh((Throwable) e);
        }
    }

    public final /* synthetic */ Object read(abu abu) throws IOException {
        return a(abu);
    }

    public final /* synthetic */ void write(abx abx, Object obj) throws IOException {
        abx.a((Number) (BigInteger) obj);
    }
}
