package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
public final class sb {
    public final int a;
    public final ck[] b;
    public final rw c;
    public final Object d;

    public sb(ck[] ckVarArr, rt[] rtVarArr, Object obj) {
        this.b = ckVarArr;
        this.c = new rw(rtVarArr);
        this.d = obj;
        this.a = ckVarArr.length;
    }

    public final boolean a(int i) {
        return this.b[i] != null;
    }

    public final boolean a(sb sbVar, int i) {
        if (sbVar != null && vf.a((Object) this.b[i], (Object) sbVar.b[i]) && vf.a((Object) this.c.a(i), (Object) sbVar.c.a(i))) {
            return true;
        }
        return false;
    }
}
