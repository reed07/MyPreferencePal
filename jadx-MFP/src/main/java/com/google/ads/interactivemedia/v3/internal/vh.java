package com.google.ads.interactivemedia.v3.internal;

import java.util.ArrayList;
import java.util.List;

/* compiled from: IMASDK */
public final class vh {
    public final List<byte[]> a;
    public final int b;
    public final float c;

    public static vh a(ut utVar) throws ca {
        float f;
        int i;
        int i2;
        try {
            utVar.d(4);
            int e = (utVar.e() & 3) + 1;
            if (e != 3) {
                ArrayList arrayList = new ArrayList();
                int e2 = utVar.e() & 31;
                for (int i3 = 0; i3 < e2; i3++) {
                    arrayList.add(b(utVar));
                }
                int e3 = utVar.e();
                for (int i4 = 0; i4 < e3; i4++) {
                    arrayList.add(b(utVar));
                }
                if (e2 > 0) {
                    ur a2 = up.a((byte[]) arrayList.get(0), e, ((byte[]) arrayList.get(0)).length);
                    int i5 = a2.e;
                    int i6 = a2.f;
                    f = a2.g;
                    i2 = i5;
                    i = i6;
                } else {
                    i2 = -1;
                    i = -1;
                    f = 1.0f;
                }
                vh vhVar = new vh(arrayList, e, i2, i, f);
                return vhVar;
            }
            throw new IllegalStateException();
        } catch (ArrayIndexOutOfBoundsException e4) {
            throw new ca("Error parsing AVC config", e4);
        }
    }

    private vh(List<byte[]> list, int i, int i2, int i3, float f) {
        this.a = list;
        this.b = i;
        this.c = f;
    }

    private static byte[] b(ut utVar) {
        int f = utVar.f();
        int d = utVar.d();
        utVar.d(f);
        return ub.a(utVar.a, d, f);
    }
}
