package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.math.BigDecimal;

/* compiled from: IMASDK */
final class aal extends xj<BigDecimal> {
    aal() {
    }

    private static BigDecimal a(abu abu) throws IOException {
        if (abu.f() == abw.NULL) {
            abu.j();
            return null;
        }
        try {
            return new BigDecimal(abu.h());
        } catch (NumberFormatException e) {
            throw new xh((Throwable) e);
        }
    }

    public final /* synthetic */ Object read(abu abu) throws IOException {
        return a(abu);
    }

    public final /* synthetic */ void write(abx abx, Object obj) throws IOException {
        abx.a((Number) (BigDecimal) obj);
    }
}
