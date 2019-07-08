package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;

/* compiled from: IMASDK */
final class aak extends xj<String> {
    aak() {
    }

    public final /* synthetic */ Object read(abu abu) throws IOException {
        abw f = abu.f();
        if (f == abw.NULL) {
            abu.j();
            return null;
        } else if (f == abw.BOOLEAN) {
            return Boolean.toString(abu.i());
        } else {
            return abu.h();
        }
    }

    public final /* synthetic */ void write(abx abx, Object obj) throws IOException {
        abx.b((String) obj);
    }
}
