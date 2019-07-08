package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;

/* compiled from: IMASDK */
final class aan extends xj<StringBuilder> {
    aan() {
    }

    public final /* synthetic */ Object read(abu abu) throws IOException {
        if (abu.f() != abw.NULL) {
            return new StringBuilder(abu.h());
        }
        abu.j();
        return null;
    }

    public final /* synthetic */ void write(abx abx, Object obj) throws IOException {
        String str;
        StringBuilder sb = (StringBuilder) obj;
        if (sb == null) {
            str = null;
        } else {
            str = sb.toString();
        }
        abx.b(str);
    }
}
