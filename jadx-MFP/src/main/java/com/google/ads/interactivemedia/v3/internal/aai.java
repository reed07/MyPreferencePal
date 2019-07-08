package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;

/* compiled from: IMASDK */
final class aai extends xj<Number> {
    aai() {
    }

    public final /* synthetic */ Object read(abu abu) throws IOException {
        abw f = abu.f();
        switch (f.ordinal()) {
            case 5:
            case 6:
                return new yn(abu.h());
            case 8:
                abu.j();
                return null;
            default:
                StringBuilder sb = new StringBuilder("Expecting number, got: ");
                sb.append(f);
                throw new xh(sb.toString());
        }
    }

    public final /* synthetic */ void write(abx abx, Object obj) throws IOException {
        abx.a((Number) obj);
    }
}
