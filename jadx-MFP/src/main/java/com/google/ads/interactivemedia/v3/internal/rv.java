package com.google.ads.interactivemedia.v3.internal;

import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;

/* compiled from: IMASDK */
public class rv {
    private final sh a;
    private final int b;
    private final int c;
    private final int d;
    private final float e;
    private final float f;
    private final long g;
    private final ua h;
    private rq i;

    public rt[] a(ru[] ruVarArr, sh shVar) {
        rt[] rtVarArr = new rt[ruVarArr.length];
        for (int i2 = 0; i2 < ruVarArr.length; i2++) {
            ru ruVar = ruVarArr[i2];
            if (ruVar != null) {
                if (ruVar.b.length > 1) {
                    rtVarArr[i2] = a(ruVar.a, shVar, ruVar.b);
                } else {
                    rtVarArr[i2] = new ro(ruVar.a, ruVar.b[0]);
                    int i3 = ruVar.a.a(ruVar.b[0]).d;
                }
            }
        }
        return rtVarArr;
    }

    public rv() {
        this(10000, 25000, 25000, 0.75f, 0.75f, AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS, ua.a);
    }

    public rv(int i2, int i3, int i4, float f2, float f3, long j, ua uaVar) {
        this(null, 10000, 25000, 25000, 0.75f, 0.75f, AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS, uaVar);
    }

    @Deprecated
    public rv(sh shVar, int i2, int i3, int i4, float f2, float f3, long j, ua uaVar) {
        this.a = null;
        this.b = i2;
        this.c = i3;
        this.d = i4;
        this.e = f2;
        this.f = f3;
        this.g = j;
        this.h = uaVar;
        this.i = rq.a;
    }

    private re a(mx mxVar, sh shVar, int[] iArr) {
        sh shVar2 = this.a;
        if (shVar2 == null) {
            shVar2 = shVar;
        }
        re reVar = r2;
        re reVar2 = new re(mxVar, iArr, new rf(shVar2, this.e), (long) this.b, (long) this.c, (long) this.d, this.f, this.g, this.h, 0);
        re reVar3 = reVar;
        reVar3.a(this.i);
        return reVar3;
    }
}
