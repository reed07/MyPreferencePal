package com.google.ads.interactivemedia.v3.internal;

import android.net.Uri;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/* compiled from: IMASDK */
final class lj implements sn {
    private final sn a;
    private final int b;
    private final lk c;
    private final byte[] d;
    private int e;

    public lj(sn snVar, int i, lk lkVar) {
        qi.b(i > 0);
        this.a = snVar;
        this.b = i;
        this.c = lkVar;
        this.d = new byte[1];
        this.e = i;
    }

    public final void a(tz tzVar) {
        this.a.a(tzVar);
    }

    public final long a(sr srVar) throws IOException {
        throw new UnsupportedOperationException();
    }

    public final int a(byte[] bArr, int i, int i2) throws IOException {
        if (this.e == 0) {
            boolean z = false;
            if (this.a.a(this.d, 0, 1) != -1) {
                int i3 = (this.d[0] & 255) << 4;
                if (i3 != 0) {
                    byte[] bArr2 = new byte[i3];
                    int i4 = i3;
                    int i5 = 0;
                    while (true) {
                        if (i4 > 0) {
                            int a2 = this.a.a(bArr2, i5, i4);
                            if (a2 == -1) {
                                break;
                            }
                            i5 += a2;
                            i4 -= a2;
                        } else {
                            while (i3 > 0 && bArr2[i3 - 1] == 0) {
                                i3--;
                            }
                            if (i3 > 0) {
                                this.c.a(new ut(bArr2, i3));
                            }
                        }
                    }
                }
                z = true;
            }
            if (!z) {
                return -1;
            }
            this.e = this.b;
        }
        int a3 = this.a.a(bArr, i, Math.min(this.e, i2));
        if (a3 != -1) {
            this.e -= a3;
        }
        return a3;
    }

    public final Uri a() {
        return this.a.a();
    }

    public final Map<String, List<String>> b() {
        return this.a.b();
    }

    public final void c() throws IOException {
        throw new UnsupportedOperationException();
    }
}
