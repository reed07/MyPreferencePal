package com.google.ads.interactivemedia.v3.internal;

import android.os.Handler;

/* compiled from: IMASDK */
public final class dm {
    private final Handler a;
    private final dl b;

    public dm(Handler handler, dl dlVar) {
        this.a = dlVar != null ? (Handler) qi.a(handler) : null;
        this.b = dlVar;
    }

    public final void a(ew ewVar) {
        if (this.b != null) {
            this.a.post(new dn(this, ewVar));
        }
    }

    public final void a(String str, long j, long j2) {
        if (this.b != null) {
            Handler handler = this.a;
            Cdo doVar = new Cdo(this, str, j, j2);
            handler.post(doVar);
        }
    }

    public final void a(bs bsVar) {
        if (this.b != null) {
            this.a.post(new dp(this, bsVar));
        }
    }

    public final void a(int i, long j, long j2) {
        if (this.b != null) {
            Handler handler = this.a;
            dq dqVar = new dq(this, i, j, j2);
            handler.post(dqVar);
        }
    }

    public final void b(ew ewVar) {
        ewVar.a();
        if (this.b != null) {
            this.a.post(new dr(this, ewVar));
        }
    }

    public final void a(int i) {
        if (this.b != null) {
            this.a.post(new ds(this, i));
        }
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void b(int i) {
        this.b.a(i);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void c(ew ewVar) {
        ewVar.a();
        this.b.d(ewVar);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void b(int i, long j, long j2) {
        this.b.a(i, j, j2);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void b(bs bsVar) {
        this.b.b(bsVar);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void b(String str, long j, long j2) {
        this.b.b(str, j, j2);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void d(ew ewVar) {
        this.b.c(ewVar);
    }
}
