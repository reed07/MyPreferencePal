package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;

/* compiled from: IMASDK */
final class wp extends xj<Number> {
    wp(wo woVar) {
    }

    public final /* synthetic */ Object read(abu abu) throws IOException {
        if (abu.f() != abw.NULL) {
            return Double.valueOf(abu.k());
        }
        abu.j();
        return null;
    }

    public final /* synthetic */ void write(abx abx, Object obj) throws IOException {
        Number number = (Number) obj;
        if (number == null) {
            abx.f();
            return;
        }
        wo.a(number.doubleValue());
        abx.a(number);
    }
}
