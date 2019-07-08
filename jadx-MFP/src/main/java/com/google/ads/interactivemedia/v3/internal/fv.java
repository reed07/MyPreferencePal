package com.google.ads.interactivemedia.v3.internal;

import java.io.EOFException;
import java.io.IOException;

/* compiled from: IMASDK */
public final class fv {
    private final ut a = new ut(10);

    public final js a(fr frVar, kn knVar) throws IOException, InterruptedException {
        js jsVar = null;
        int i = 0;
        while (true) {
            try {
                frVar.c(this.a.a, 0, 10);
                this.a.c(0);
                if (this.a.i() != km.a) {
                    break;
                }
                this.a.d(3);
                int o = this.a.o();
                int i2 = o + 10;
                if (jsVar == null) {
                    byte[] bArr = new byte[i2];
                    System.arraycopy(this.a.a, 0, bArr, 0, 10);
                    frVar.c(bArr, 10, o);
                    jsVar = new km(knVar).a(bArr, i2);
                } else {
                    frVar.c(o);
                }
                i += i2;
            } catch (EOFException unused) {
            }
        }
        frVar.a();
        frVar.c(i);
        return jsVar;
    }
}
