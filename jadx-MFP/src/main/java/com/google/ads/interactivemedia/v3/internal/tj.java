package com.google.ads.interactivemedia.v3.internal;

import android.os.Looper;
import android.os.SystemClock;
import java.io.IOException;
import java.util.concurrent.ExecutorService;

/* compiled from: IMASDK */
public final class tj implements ts {
    public static final tm a = a(false, -9223372036854775807L);
    public static final tm b = new tm(2, -9223372036854775807L, 0);
    public static final tm c = new tm(3, -9223372036854775807L, 0);
    /* access modifiers changed from: private */
    public final ExecutorService d;
    /* access modifiers changed from: private */
    public tn<? extends to> e;
    /* access modifiers changed from: private */
    public IOException f;

    public tj(String str) {
        this.d = vf.a(str);
    }

    public static tm a(boolean z, long j) {
        return new tm(z ? 1 : 0, j, 0);
    }

    public final <T extends to> long a(T t, tl<T> tlVar, int i) {
        Looper myLooper = Looper.myLooper();
        qi.c(myLooper != null);
        this.f = null;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        tn tnVar = new tn(this, myLooper, t, tlVar, i, elapsedRealtime);
        tnVar.a(0);
        return elapsedRealtime;
    }

    public final boolean b() {
        return this.e != null;
    }

    public final void c() {
        this.e.a(false);
    }

    public final void a(tp tpVar) {
        tn<? extends to> tnVar = this.e;
        if (tnVar != null) {
            tnVar.a(true);
        }
        if (tpVar != null) {
            this.d.execute(new tq(tpVar));
        }
        this.d.shutdown();
    }

    public final void a() throws IOException {
        a(Integer.MIN_VALUE);
    }

    public final void a(int i) throws IOException {
        IOException iOException = this.f;
        if (iOException == null) {
            tn<? extends to> tnVar = this.e;
            if (tnVar != null) {
                if (i == Integer.MIN_VALUE) {
                    i = tnVar.a;
                }
                tnVar.a(i);
                return;
            }
            return;
        }
        throw iOException;
    }

    static {
        a(true, -9223372036854775807L);
    }
}
