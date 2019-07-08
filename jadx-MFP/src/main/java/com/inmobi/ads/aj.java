package com.inmobi.ads;

import android.content.Context;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.support.annotation.VisibleForTesting;
import android.view.View;
import com.facebook.internal.AnalyticsEvents;
import com.inmobi.ads.AdContainer.RenderingProperties.PlacementType;
import com.inmobi.ads.InMobiAdRequestStatus.StatusCode;
import com.inmobi.ads.c.a;
import com.inmobi.ads.cache.d;
import com.inmobi.ads.i.b;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.b.c;
import com.inmobi.rendering.RenderView;
import java.lang.ref.WeakReference;
import java.util.Map;

/* compiled from: NativeAdUnit */
public final class aj extends i {
    /* access modifiers changed from: 0000 */
    public static final String y = "aj";
    boolean A = false;
    private boolean B;
    private a C;
    private int D = 0;
    WeakReference<View> z;

    public final String b() {
        return AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE;
    }

    public final String c() {
        return null;
    }

    @NonNull
    public static aj a(@NonNull Context context, bi biVar, b bVar, int i) {
        i iVar = (i) a.a.get(biVar);
        aj ajVar = iVar instanceof aj ? (aj) iVar : null;
        if (ajVar == null || 1 != i) {
            if (ajVar == null) {
                new StringBuilder("Creating new adUnit for placement-ID : ").append(biVar.a);
                ajVar = new aj(context, biVar.a, bVar);
                if (i != 0) {
                    a.a.put(biVar, ajVar);
                }
            } else {
                new StringBuilder("Found pre-fetching adUnit for placement-ID : ").append(biVar.a);
                ajVar.a(context);
                a.a.remove(biVar);
                ajVar.B = true;
            }
            ajVar.a(bVar);
            ajVar.a(biVar.f);
            return ajVar;
        }
        throw new IllegalStateException("There's already a pre-loading going on for the same placementID");
    }

    private aj(@NonNull Context context, long j, b bVar) {
        super(context, j, bVar);
    }

    public final void a(Context context) {
        super.a(context);
        b(context);
    }

    @VisibleForTesting
    private void b(Context context) {
        AdContainer j = j();
        if (j instanceof ah) {
            ((ah) j).a(context);
        }
    }

    /* access modifiers changed from: 0000 */
    @UiThread
    public final void q() {
        try {
            if (p()) {
                c("IllegalState");
            } else {
                super.a(false);
            }
        } catch (Exception e) {
            Logger.a(InternalLogLevel.ERROR, "InMobi", "Unable to Prefetch ad; SDK encountered an unexpected error");
            new StringBuilder("Prefetch failed with unexpected error: ").append(e.getMessage());
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
        }
    }

    @UiThread
    public final void a(boolean z2) {
        try {
            if (p()) {
                c("IllegalState");
            } else {
                super.a(z2);
            }
        } catch (Exception e) {
            Logger.a(InternalLogLevel.ERROR, "InMobi", "Unable to Prefetch ad; SDK encountered an unexpected error");
            new StringBuilder("Prefetch failed with unexpected error: ").append(e.getMessage());
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
        }
    }

    /* access modifiers changed from: protected */
    public final void b(a aVar) {
        i().a(aVar);
    }

    /* access modifiers changed from: protected */
    public final void b(long j, boolean z2) {
        super.b(j, z2);
        boolean z3 = false;
        if (!z2) {
            if (j == this.d && (2 == this.a || 5 == this.a)) {
                this.a = 0;
                if (f() != null) {
                    f().a(new InMobiAdRequestStatus(StatusCode.AD_NO_LONGER_AVAILABLE));
                }
            }
        } else if (j == this.d && 2 == this.a) {
            b f = f();
            if (f != null) {
                a aVar = this.C;
                if (aVar != null) {
                    if (aVar instanceof bc) {
                        bc bcVar = (bc) aVar;
                        d.a();
                        com.inmobi.ads.cache.a b = d.b(bcVar.l);
                        if (b != null && b.a()) {
                            bx bxVar = new bx(b.e, bcVar.m, bcVar.n, bcVar.h(), bcVar.i(), this.g.m);
                            this.j = bxVar;
                        }
                    }
                    z3 = true;
                }
                if (!z3) {
                    f.a(new InMobiAdRequestStatus(StatusCode.INTERNAL_ERROR));
                } else if (a() != null) {
                    if (this.t) {
                        this.v = true;
                        J();
                        return;
                    }
                    K();
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void c(long j, @NonNull a aVar) {
        super.c(j, aVar);
        this.C = aVar;
        if (a(aVar)) {
            if (this.r != 0 || aVar.j) {
                c(aVar);
            } else {
                a(true, (RenderView) null);
            }
            if (aVar.j) {
                this.t = true;
                I();
            }
        }
    }

    public final boolean a(a aVar) {
        if (super.a(aVar)) {
            return true;
        }
        b(aVar);
        return false;
    }

    /* access modifiers changed from: 0000 */
    public final void K() {
        a(f(), this.h, new Runnable() {
            public final void run() {
                if (2 == aj.this.a) {
                    aj ajVar = aj.this;
                    ajVar.a = 5;
                    AdContainer j = ajVar.j();
                    RenderView renderView = aj.this.u;
                    b f = aj.this.f();
                    if (j instanceof ah) {
                        ah ahVar = (ah) j;
                        ahVar.w = renderView;
                        ahVar.y = aj.this.r;
                        aj.this.F();
                        if (f != null) {
                            aj.y;
                            f.a();
                        }
                    } else {
                        if (f != null) {
                            aj.y;
                            f.a(new InMobiAdRequestStatus(StatusCode.INTERNAL_ERROR));
                        }
                    }
                }
            }
        }, Looper.getMainLooper());
    }

    public final void O() {
        try {
            super.v();
            this.h = null;
        } catch (Exception e) {
            Logger.a(InternalLogLevel.ERROR, "InMobi", "Could not destroy native ad; SDK encountered unexpected error");
            new StringBuilder("SDK encountered unexpected error in destroying native ad unit; ").append(e.getMessage());
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
        }
    }

    /* access modifiers changed from: protected */
    public final PlacementType d() {
        return PlacementType.PLACEMENT_TYPE_INLINE;
    }

    /* access modifiers changed from: protected */
    @NonNull
    public final Map<String, String> e() {
        Map<String, String> e = super.e();
        e.put("a-parentViewWidth", String.valueOf(c.a().a));
        e.put("a-productVersion", "NS-1.0.0-20160411");
        e.put("trackerType", "url_ping");
        return e;
    }

    public final void n() {
        if (!this.w) {
            b f = f();
            if (m()) {
                a("MissingDependency");
                if (f != null) {
                    f.a(new InMobiAdRequestStatus(StatusCode.MISSING_REQUIRED_DEPENDENCIES));
                }
            } else if (1 == this.a || 2 == this.a) {
                Logger.a(InternalLogLevel.ERROR, y, "An ad load is already in progress. Please wait for the load to complete before requesting for another ad");
                if (!this.B) {
                    a(new InMobiAdRequestStatus(StatusCode.REQUEST_PENDING), false);
                }
            } else {
                InternalLogLevel internalLogLevel = InternalLogLevel.DEBUG;
                String str = y;
                StringBuilder sb = new StringBuilder("Fetching a Native ad for placement id: ");
                sb.append(this.d);
                Logger.a(internalLogLevel, str, sb.toString());
                if (5 != this.a || h()) {
                    super.n();
                    return;
                }
                a(f, "VAR", "");
                a(f, "ARF", "");
                if (f != null) {
                    b(a());
                    f.a();
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public final int r() {
        if (1 != this.a && 2 != this.a) {
            return super.r();
        }
        StringBuilder sb = new StringBuilder("An ad load is already in progress. Please wait for the load to complete before requesting for another ad for placement id: ");
        sb.append(this.d);
        Logger.a(InternalLogLevel.ERROR, "InMobi", sb.toString());
        this.s.post(new Runnable() {
            public final void run() {
                aj.this.a(new InMobiAdRequestStatus(StatusCode.REQUEST_PENDING), false);
            }
        });
        return 2;
    }

    public final boolean P() {
        return this.a == 5;
    }

    public final void H() {
        if (1 == this.a) {
            this.a = 9;
            if (!this.n) {
                this.B = false;
                n();
            } else if (this.q != null) {
                this.q.a(this);
            }
        }
    }

    public final void b(InMobiAdRequestStatus inMobiAdRequestStatus) {
        if (1 == this.a) {
            this.a = 3;
            b f = f();
            if (!this.n && f != null) {
                this.B = false;
                a(f, "VAR", "");
                a(f, "ARN", "");
                f.a(inMobiAdRequestStatus);
            } else if (this.q != null) {
                this.q.a(this, inMobiAdRequestStatus);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public final void b(b bVar) {
        if (this.a == 5) {
            this.a = 7;
        } else if (this.a == 7) {
            this.D++;
        }
        StringBuilder sb = new StringBuilder("Successfully displayed fullscreen for placement id: ");
        sb.append(this.d);
        Logger.a(InternalLogLevel.DEBUG, "InMobi", sb.toString());
        if (this.D == 0) {
            if (bVar != null) {
                bVar.d();
                return;
            }
            g();
        }
    }

    /* access modifiers changed from: 0000 */
    public final void c(b bVar) {
        if (this.a == 7) {
            int i = this.D;
            if (i > 0) {
                this.D = i - 1;
            } else {
                this.a = 5;
            }
        }
        StringBuilder sb = new StringBuilder("Successfully dismissed fullscreen for placement id: ");
        sb.append(this.d);
        Logger.a(InternalLogLevel.DEBUG, "InMobi", sb.toString());
        if (this.D == 0 && this.a == 5) {
            if (bVar != null) {
                bVar.e();
                return;
            }
            g();
        }
    }
}
