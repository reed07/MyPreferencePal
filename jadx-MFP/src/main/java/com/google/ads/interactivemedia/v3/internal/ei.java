package com.google.ads.interactivemedia.v3.internal;

import java.util.Arrays;

/* compiled from: IMASDK */
public class ei {
    private final dj[] a;
    private final ep b = new ep();
    private final er c = new er();

    public dj[] a() {
        return this.a;
    }

    public cc a(cc ccVar) {
        this.b.a(ccVar.d);
        return new cc(this.c.a(ccVar.b), this.c.b(ccVar.c), ccVar.d);
    }

    public long a(long j) {
        return this.c.a(j);
    }

    public long b() {
        return this.b.n();
    }

    public ei(dj... djVarArr) {
        this.a = (dj[]) Arrays.copyOf(djVarArr, djVarArr.length + 2);
        dj[] djVarArr2 = this.a;
        djVarArr2[djVarArr.length] = this.b;
        djVarArr2[djVarArr.length + 1] = this.c;
    }
}
