package com.google.ads.interactivemedia.v3.internal;

import android.util.Pair;

/* compiled from: IMASDK */
public abstract class rz {
    private sh a;

    public rz() {
    }

    /* access modifiers changed from: protected */
    public abstract Pair<ck[], rt[]> a(rp rpVar, int[][][] iArr, int[] iArr2) throws aw;

    public void a(Object obj) {
    }

    public final void a(sa saVar, sh shVar) {
        this.a = shVar;
    }

    public sb a(cj[] cjVarArr, mz mzVar) throws aw {
        int[] iArr = new int[(cjVarArr.length + 1)];
        mx[][] mxVarArr = new mx[(cjVarArr.length + 1)][];
        int[][][] iArr2 = new int[(cjVarArr.length + 1)][][];
        for (int i = 0; i < mxVarArr.length; i++) {
            mxVarArr[i] = new mx[mzVar.b];
            iArr2[i] = new int[mzVar.b][];
        }
        int[] a2 = a(cjVarArr);
        for (int i2 = 0; i2 < mzVar.b; i2++) {
            mx a3 = mzVar.a(i2);
            int a4 = a(cjVarArr, a3);
            int[] a5 = a4 == cjVarArr.length ? new int[a3.a] : a(cjVarArr[a4], a3);
            int i3 = iArr[a4];
            mxVarArr[a4][i3] = a3;
            iArr2[a4][i3] = a5;
            iArr[a4] = iArr[a4] + 1;
        }
        mz[] mzVarArr = new mz[cjVarArr.length];
        int[] iArr3 = new int[cjVarArr.length];
        for (int i4 = 0; i4 < cjVarArr.length; i4++) {
            int i5 = iArr[i4];
            mzVarArr[i4] = new mz((mx[]) vf.a((T[]) mxVarArr[i4], i5));
            iArr2[i4] = (int[][]) vf.a((T[]) iArr2[i4], i5);
            iArr3[i4] = cjVarArr[i4].a();
        }
        rp rpVar = new rp(iArr3, mzVarArr, a2, iArr2, new mz((mx[]) vf.a((T[]) mxVarArr[cjVarArr.length], iArr[cjVarArr.length])));
        Pair a6 = a(rpVar, iArr2, a2);
        return new sb((ck[]) a6.first, (rt[]) a6.second, rpVar);
    }

    /* access modifiers changed from: protected */
    public final sh a() {
        return (sh) qi.a(this.a);
    }

    public rz(byte b) {
        this();
    }

    private static int a(cj[] cjVarArr, mx mxVar) throws aw {
        int length = cjVarArr.length;
        int i = 0;
        int i2 = 0;
        while (i < cjVarArr.length) {
            cj cjVar = cjVarArr[i];
            int i3 = i2;
            int i4 = length;
            for (int i5 = 0; i5 < mxVar.a; i5++) {
                int a2 = cjVar.a(mxVar.a(i5)) & 7;
                if (a2 > i3) {
                    if (a2 == 4) {
                        return i;
                    }
                    i4 = i;
                    i3 = a2;
                }
            }
            i++;
            length = i4;
            i2 = i3;
        }
        return length;
    }

    private static int[] a(cj cjVar, mx mxVar) throws aw {
        int[] iArr = new int[mxVar.a];
        for (int i = 0; i < mxVar.a; i++) {
            iArr[i] = cjVar.a(mxVar.a(i));
        }
        return iArr;
    }

    private static int[] a(cj[] cjVarArr) throws aw {
        int[] iArr = new int[cjVarArr.length];
        for (int i = 0; i < iArr.length; i++) {
            iArr[i] = cjVarArr[i].s();
        }
        return iArr;
    }
}
