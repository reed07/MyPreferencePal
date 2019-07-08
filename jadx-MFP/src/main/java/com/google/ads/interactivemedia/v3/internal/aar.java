package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/* compiled from: IMASDK */
final class aar extends xj<URI> {
    aar() {
    }

    private static URI a(abu abu) throws IOException {
        if (abu.f() == abw.NULL) {
            abu.j();
            return null;
        }
        try {
            String h = abu.h();
            if ("null".equals(h)) {
                return null;
            }
            return new URI(h);
        } catch (URISyntaxException e) {
            throw new xa((Throwable) e);
        }
    }

    public final /* synthetic */ Object read(abu abu) throws IOException {
        return a(abu);
    }

    public final /* synthetic */ void write(abx abx, Object obj) throws IOException {
        String str;
        URI uri = (URI) obj;
        if (uri == null) {
            str = null;
        } else {
            str = uri.toASCIIString();
        }
        abx.b(str);
    }
}
