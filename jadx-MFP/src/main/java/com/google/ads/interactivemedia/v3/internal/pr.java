package com.google.ads.interactivemedia.v3.internal;

import android.os.SystemClock;
import java.util.List;

/* compiled from: IMASDK */
final class pr extends rg {
    private int b;

    public pr(mx mxVar, int[] iArr) {
        super(mxVar, iArr);
        this.b = a(mxVar.a(0));
    }

    public final int b() {
        return 0;
    }

    public final Object c() {
        return null;
    }

    public final void a(long j, long j2, long j3, List<? extends ns> list, nt[] ntVarArr) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (b(this.b, elapsedRealtime)) {
            for (int i = this.a - 1; i >= 0; i--) {
                if (!b(i, elapsedRealtime)) {
                    this.b = i;
                    return;
                }
            }
            throw new IllegalStateException();
        }
    }

    public final int a() {
        return this.b;
    }
}
