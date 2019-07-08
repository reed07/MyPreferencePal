package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.io.Writer;

/* compiled from: IMASDK */
final class yv extends Writer {
    private final Appendable a;
    private final yw b = new yw();

    yv(Appendable appendable) {
        this.a = appendable;
    }

    public final void close() {
    }

    public final void flush() {
    }

    public final void write(char[] cArr, int i, int i2) throws IOException {
        yw ywVar = this.b;
        ywVar.a = cArr;
        this.a.append(ywVar, i, i2 + i);
    }

    public final void write(int i) throws IOException {
        this.a.append((char) i);
    }
}
