package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.io.InputStream;

/* compiled from: IMASDK */
public final class sq extends InputStream {
    private final sn a;
    private final sr b;
    private final byte[] c;
    private boolean d = false;
    private boolean e = false;
    private long f;

    public sq(sn snVar, sr srVar) {
        this.a = snVar;
        this.b = srVar;
        this.c = new byte[1];
    }

    public final void a() throws IOException {
        b();
    }

    public final int read() throws IOException {
        if (read(this.c) == -1) {
            return -1;
        }
        return this.c[0] & 255;
    }

    public final int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    public final int read(byte[] bArr, int i, int i2) throws IOException {
        qi.c(!this.e);
        b();
        int a2 = this.a.a(bArr, i, i2);
        if (a2 == -1) {
            return -1;
        }
        this.f += (long) a2;
        return a2;
    }

    public final void close() throws IOException {
        if (!this.e) {
            this.a.c();
            this.e = true;
        }
    }

    private final void b() throws IOException {
        if (!this.d) {
            this.a.a(this.b);
            this.d = true;
        }
    }
}
