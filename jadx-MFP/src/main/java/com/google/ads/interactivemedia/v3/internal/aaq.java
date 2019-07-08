package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.net.URL;

/* compiled from: IMASDK */
final class aaq extends xj<URL> {
    aaq() {
    }

    public final /* synthetic */ Object read(abu abu) throws IOException {
        if (abu.f() == abw.NULL) {
            abu.j();
            return null;
        }
        String h = abu.h();
        if ("null".equals(h)) {
            return null;
        }
        return new URL(h);
    }

    public final /* synthetic */ void write(abx abx, Object obj) throws IOException {
        String str;
        URL url = (URL) obj;
        if (url == null) {
            str = null;
        } else {
            str = url.toExternalForm();
        }
        abx.b(str);
    }
}
