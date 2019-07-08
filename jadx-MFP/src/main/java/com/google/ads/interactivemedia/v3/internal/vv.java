package com.google.ads.interactivemedia.v3.internal;

import android.os.Handler;
import android.view.Surface;

/* compiled from: IMASDK */
public final class vv {
    private final Handler a;
    private final vu b;

    public vv(Handler handler, vu vuVar) {
        this.a = vuVar != null ? (Handler) qi.a(handler) : null;
        this.b = vuVar;
    }

    public final void a(ew ewVar) {
        if (this.b != null) {
            this.a.post(new vw(this, ewVar));
        }
    }

    public final void a(String str, long j, long j2) {
        if (this.b != null) {
            Handler handler = this.a;
            vx vxVar = new vx(this, str, j, j2);
            handler.post(vxVar);
        }
    }

    public final void a(bs bsVar) {
        if (this.b != null) {
            this.a.post(new vy(this, bsVar));
        }
    }

    public final void a(int i, long j) {
        if (this.b != null) {
            this.a.post(new vz(this, i, j));
        }
    }

    public final void a(int i, int i2, int i3, float f) {
        if (this.b != null) {
            Handler handler = this.a;
            wa waVar = new wa(this, i, i2, i3, f);
            handler.post(waVar);
        }
    }

    public final void a(Surface surface) {
        if (this.b != null) {
            this.a.post(new wb(this, surface));
        }
    }

    public final void b(ew ewVar) {
        ewVar.a();
        if (this.b != null) {
            this.a.post(new wc(this, ewVar));
        }
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void c(ew ewVar) {
        ewVar.a();
        this.b.b(ewVar);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void b(Surface surface) {
        this.b.a(surface);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void b(int i, int i2, int i3, float f) {
        this.b.a(i, i2, i3, f);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void b(int i, long j) {
        this.b.a(i, j);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void b(bs bsVar) {
        this.b.a(bsVar);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void b(String str, long j, long j2) {
        this.b.a(str, j, j2);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void d(ew ewVar) {
        this.b.a(ewVar);
    }
}
