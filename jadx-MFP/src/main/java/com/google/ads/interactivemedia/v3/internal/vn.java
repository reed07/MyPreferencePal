package com.google.ads.interactivemedia.v3.internal;

import java.util.Collections;
import java.util.List;

/* compiled from: IMASDK */
public final class vn {
    public final List<byte[]> a;
    public final int b;

    public static vn a(ut utVar) throws ca {
        List list;
        try {
            utVar.d(21);
            int e = utVar.e() & 3;
            int e2 = utVar.e();
            int d = utVar.d();
            int i = 0;
            int i2 = 0;
            while (i < e2) {
                utVar.d(1);
                int f = utVar.f();
                int i3 = i2;
                for (int i4 = 0; i4 < f; i4++) {
                    int f2 = utVar.f();
                    i3 += f2 + 4;
                    utVar.d(f2);
                }
                i++;
                i2 = i3;
            }
            utVar.c(d);
            byte[] bArr = new byte[i2];
            int i5 = 0;
            int i6 = 0;
            while (i5 < e2) {
                utVar.d(1);
                int f3 = utVar.f();
                int i7 = i6;
                for (int i8 = 0; i8 < f3; i8++) {
                    int f4 = utVar.f();
                    System.arraycopy(up.a, 0, bArr, i7, up.a.length);
                    int length = i7 + up.a.length;
                    System.arraycopy(utVar.a, utVar.d(), bArr, length, f4);
                    i7 = length + f4;
                    utVar.d(f4);
                }
                i5++;
                i6 = i7;
            }
            if (i2 == 0) {
                list = null;
            } else {
                list = Collections.singletonList(bArr);
            }
            return new vn(list, e + 1);
        } catch (ArrayIndexOutOfBoundsException e3) {
            throw new ca("Error parsing HEVC config", e3);
        }
    }

    private vn(List<byte[]> list, int i) {
        this.a = list;
        this.b = i;
    }
}
