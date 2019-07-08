package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;

/* compiled from: IMASDK */
final class abi extends xj<Boolean> {
    abi() {
    }

    public final /* synthetic */ Object read(abu abu) throws IOException {
        if (abu.f() != abw.NULL) {
            return Boolean.valueOf(abu.h());
        }
        abu.j();
        return null;
    }

    public final /* synthetic */ void write(abx abx, Object obj) throws IOException {
        String str;
        Boolean bool = (Boolean) obj;
        if (bool == null) {
            str = "null";
        } else {
            str = bool.toString();
        }
        abx.b(str);
    }
}
