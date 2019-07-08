package com.google.ads.interactivemedia.v3.internal;

import android.os.Handler;
import android.os.Looper;
import java.io.IOException;
import java.util.ArrayList;

/* compiled from: IMASDK */
public abstract class ln {
    private final ArrayList<lp> a = new ArrayList<>(1);
    private final lr b = new lr();
    private Looper c;
    private cq d;
    private Object e;

    public abstract ll a(lo loVar, sf sfVar);

    public abstract void a() throws IOException;

    public void a(Handler handler, lq lqVar) {
        this.b.a(handler, lqVar);
    }

    public abstract void a(ll llVar);

    /* access modifiers changed from: protected */
    public abstract void a(tz tzVar);

    /* access modifiers changed from: protected */
    public abstract void b();

    public void a(lq lqVar) {
        this.b.a(lqVar);
    }

    public void a(lp lpVar, tz tzVar) {
        Looper myLooper = Looper.myLooper();
        Looper looper = this.c;
        qi.b(looper == null || looper == myLooper);
        this.a.add(lpVar);
        if (this.c == null) {
            this.c = myLooper;
            a(tzVar);
            return;
        }
        cq cqVar = this.d;
        if (cqVar != null) {
            lpVar.a(this, cqVar, this.e);
        }
    }

    public void a(lp lpVar) {
        this.a.remove(lpVar);
        if (this.a.isEmpty()) {
            this.c = null;
            this.d = null;
            this.e = null;
            b();
        }
    }

    /* access modifiers changed from: protected */
    public void a(cq cqVar, Object obj) {
        this.d = cqVar;
        this.e = obj;
        ArrayList arrayList = this.a;
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj2 = arrayList.get(i);
            i++;
            ((lp) obj2).a(this, cqVar, obj);
        }
    }

    /* access modifiers changed from: protected */
    public lr a(lo loVar) {
        return this.b.a(0, loVar, 0);
    }

    /* access modifiers changed from: protected */
    public lr a(lo loVar, long j) {
        qi.b(loVar != null);
        return this.b.a(0, loVar, j);
    }
}
