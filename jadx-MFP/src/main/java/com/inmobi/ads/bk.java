package com.inmobi.ads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.inmobi.ads.c.l;

/* compiled from: PollingVisibilityTracker */
class bk extends ce {
    private static final String d = "bk";
    @Nullable
    private l e;

    bk(@NonNull a aVar, @Nullable l lVar) {
        super(aVar);
        this.e = lVar;
    }

    /* access modifiers changed from: protected */
    public final int a() {
        l lVar = this.e;
        if (lVar == null) {
            return 100;
        }
        return lVar.c;
    }

    /* access modifiers changed from: protected */
    public final void b() {
        g();
    }
}
