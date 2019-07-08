package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;

/* compiled from: IMASDK */
final class aba extends xj<Boolean> {
    aba() {
    }

    public final /* synthetic */ Object read(abu abu) throws IOException {
        abw f = abu.f();
        if (f == abw.NULL) {
            abu.j();
            return null;
        } else if (f == abw.STRING) {
            return Boolean.valueOf(Boolean.parseBoolean(abu.h()));
        } else {
            return Boolean.valueOf(abu.i());
        }
    }

    public final /* synthetic */ void write(abx abx, Object obj) throws IOException {
        abx.a((Boolean) obj);
    }
}
