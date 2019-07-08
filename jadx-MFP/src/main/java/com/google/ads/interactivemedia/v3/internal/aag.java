package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;

/* compiled from: IMASDK */
final class aag extends xj<Number> {
    aag() {
    }

    public final /* synthetic */ Object read(abu abu) throws IOException {
        if (abu.f() != abw.NULL) {
            return Float.valueOf((float) abu.k());
        }
        abu.j();
        return null;
    }

    public final /* synthetic */ void write(abx abx, Object obj) throws IOException {
        abx.a((Number) obj);
    }
}
