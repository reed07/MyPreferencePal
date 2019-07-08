package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;

/* compiled from: IMASDK */
final class wr extends xj<Number> {
    wr() {
    }

    public final /* synthetic */ Object read(abu abu) throws IOException {
        if (abu.f() != abw.NULL) {
            return Long.valueOf(abu.l());
        }
        abu.j();
        return null;
    }

    public final /* synthetic */ void write(abx abx, Object obj) throws IOException {
        Number number = (Number) obj;
        if (number == null) {
            abx.f();
        } else {
            abx.b(number.toString());
        }
    }
}
