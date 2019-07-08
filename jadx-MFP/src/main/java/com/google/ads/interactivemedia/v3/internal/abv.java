package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;

/* compiled from: IMASDK */
final class abv extends ym {
    abv() {
    }

    public final void a(abu abu) throws IOException {
        if (abu instanceof zj) {
            ((zj) abu).o();
            return;
        }
        int i = abu.a;
        if (i == 0) {
            i = abu.r();
        }
        if (i == 13) {
            abu.a = 9;
        } else if (i == 12) {
            abu.a = 8;
        } else if (i == 14) {
            abu.a = 10;
        } else {
            StringBuilder sb = new StringBuilder("Expected a name but was ");
            sb.append(abu.f());
            sb.append(abu.s());
            throw new IllegalStateException(sb.toString());
        }
    }
}
