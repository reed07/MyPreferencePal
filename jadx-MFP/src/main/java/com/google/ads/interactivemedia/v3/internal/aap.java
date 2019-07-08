package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;

/* compiled from: IMASDK */
final class aap extends xj<StringBuffer> {
    aap() {
    }

    public final /* synthetic */ Object read(abu abu) throws IOException {
        if (abu.f() != abw.NULL) {
            return new StringBuffer(abu.h());
        }
        abu.j();
        return null;
    }

    public final /* synthetic */ void write(abx abx, Object obj) throws IOException {
        String str;
        StringBuffer stringBuffer = (StringBuffer) obj;
        if (stringBuffer == null) {
            str = null;
        } else {
            str = stringBuffer.toString();
        }
        abx.b(str);
    }
}
