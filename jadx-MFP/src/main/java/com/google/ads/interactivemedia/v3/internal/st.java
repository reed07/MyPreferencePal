package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.util.SparseArray;

/* compiled from: IMASDK */
public final class st {
    private final Context a;
    private SparseArray<Long> b;
    private int c;
    private ua d;

    public st(Context context) {
        Context context2;
        if (context == null) {
            context2 = null;
        } else {
            context2 = context.getApplicationContext();
        }
        this.a = context2;
        int[] iArr = (int[]) ss.a.get(vf.b(context));
        if (iArr == null) {
            iArr = new int[]{2, 2, 2, 2};
        }
        SparseArray<Long> sparseArray = new SparseArray<>(6);
        sparseArray.append(0, Long.valueOf(1000000));
        sparseArray.append(2, Long.valueOf(ss.b[iArr[0]]));
        sparseArray.append(3, Long.valueOf(ss.c[iArr[1]]));
        sparseArray.append(4, Long.valueOf(ss.d[iArr[2]]));
        sparseArray.append(5, Long.valueOf(ss.e[iArr[3]]));
        sparseArray.append(7, Long.valueOf(ss.b[iArr[0]]));
        this.b = sparseArray;
        this.c = 2000;
        this.d = ua.a;
    }

    public final ss a() {
        ss ssVar = new ss(this.a, this.b, this.c, this.d, false, 0);
        return ssVar;
    }
}
