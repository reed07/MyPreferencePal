package com.google.ads.interactivemedia.v3.internal;

import android.util.Log;

/* compiled from: IMASDK */
public class nj {
    private final int[] a;
    private final mq[] b;

    public gc a(int i) {
        int i2 = 0;
        while (true) {
            int[] iArr = this.a;
            if (i2 >= iArr.length) {
                StringBuilder sb = new StringBuilder(36);
                sb.append("Unmatched track of type: ");
                sb.append(i);
                Log.e("BaseMediaChunkOutput", sb.toString());
                return new fp();
            } else if (i == iArr[i2]) {
                return this.b[i2];
            } else {
                i2++;
            }
        }
    }

    public nj(int[] iArr, mq[] mqVarArr) {
        this.a = iArr;
        this.b = mqVarArr;
    }

    public int[] a() {
        int[] iArr = new int[this.b.length];
        int i = 0;
        while (true) {
            mq[] mqVarArr = this.b;
            if (i >= mqVarArr.length) {
                return iArr;
            }
            if (mqVarArr[i] != null) {
                iArr[i] = mqVarArr[i].c();
            }
            i++;
        }
    }

    public void a(long j) {
        mq[] mqVarArr;
        for (mq mqVar : this.b) {
            if (mqVar != null) {
                mqVar.a(j);
            }
        }
    }
}
