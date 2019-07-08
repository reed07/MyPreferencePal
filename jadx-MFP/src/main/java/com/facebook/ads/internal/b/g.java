package com.facebook.ads.internal.b;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import com.facebook.ads.NativeAdBase;
import com.facebook.ads.internal.adapters.AdAdapter;
import com.facebook.ads.internal.adapters.i;
import com.facebook.ads.internal.adapters.q;
import com.facebook.ads.internal.m.a;
import com.facebook.ads.internal.m.c;
import com.facebook.ads.internal.m.e;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.ads.internal.t.d;
import com.samsung.android.sdk.healthdata.HealthConstants.FoodIntake;
import java.util.HashMap;
import java.util.Map;

public class g extends c {
    public g(Context context, a aVar) {
        super(context, aVar);
    }

    static /* synthetic */ Map a(g gVar, long j) {
        HashMap hashMap = new HashMap();
        hashMap.put("delay", String.valueOf(System.currentTimeMillis() - j));
        return hashMap;
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.List, code=java.util.List<java.lang.String>, for r5v0, types: [java.util.List, java.util.List<java.lang.String>] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ void a(com.facebook.ads.internal.b.g r4, java.util.List<java.lang.String> r5, java.util.Map r6) {
        /*
            if (r5 == 0) goto L_0x002a
            boolean r0 = r5.isEmpty()
            if (r0 == 0) goto L_0x0009
            goto L_0x002a
        L_0x0009:
            java.util.Iterator r5 = r5.iterator()
        L_0x000d:
            boolean r0 = r5.hasNext()
            if (r0 == 0) goto L_0x002a
            java.lang.Object r0 = r5.next()
            java.lang.String r0 = (java.lang.String) r0
            com.facebook.ads.internal.w.e.e r1 = new com.facebook.ads.internal.w.e.e
            android.content.Context r2 = r4.b
            r1.<init>(r2, r6)
            r2 = 1
            java.lang.String[] r2 = new java.lang.String[r2]
            r3 = 0
            r2[r3] = r0
            r1.execute(r2)
            goto L_0x000d
        L_0x002a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.b.g.a(com.facebook.ads.internal.b.g, java.util.List, java.util.Map):void");
    }

    /* access modifiers changed from: protected */
    public void a() {
        i iVar = (i) this.f;
        if (iVar.A()) {
            this.c.a(iVar);
            return;
        }
        throw new IllegalStateException("ad is not ready or already displayed");
    }

    /* access modifiers changed from: protected */
    public void a(AdAdapter adAdapter, c cVar, a aVar, Map<String, Object> map) {
        i iVar = (i) adAdapter;
        final i iVar2 = iVar;
        final long currentTimeMillis = System.currentTimeMillis();
        final a aVar2 = aVar;
        AnonymousClass1 r0 = new Runnable() {
            public void run() {
                g.this.a((AdAdapter) iVar2);
                Map a2 = g.a(g.this, currentTimeMillis);
                a2.put("error", FoodIntake.UNIT_TYPE_NOT_DEFINED);
                a2.put(NotificationCompat.CATEGORY_MESSAGE, "timeout");
                g.a(g.this, aVar2.a(e.REQUEST), a2);
                g.this.i();
            }
        };
        j().postDelayed(r0, (long) cVar.a().j());
        Context context = this.b;
        final AnonymousClass1 r2 = r0;
        AnonymousClass2 r02 = new q() {
            boolean a = false;
            boolean b = false;
            boolean c = false;

            public void a(i iVar) {
                if (iVar == g.this.e) {
                    g.this.j().removeCallbacks(r2);
                    g gVar = g.this;
                    gVar.f = iVar;
                    gVar.c.a((AdAdapter) iVar);
                    if (!this.a) {
                        this.a = true;
                        g.a(g.this, aVar2.a(e.REQUEST), g.a(g.this, currentTimeMillis));
                    }
                }
            }

            public void a(i iVar, com.facebook.ads.internal.protocol.a aVar) {
                if (iVar == g.this.e) {
                    g.this.j().removeCallbacks(r2);
                    g.this.a((AdAdapter) iVar);
                    if (!this.a) {
                        this.a = true;
                        Map a2 = g.a(g.this, currentTimeMillis);
                        a2.put("error", String.valueOf(aVar.a().getErrorCode()));
                        a2.put(NotificationCompat.CATEGORY_MESSAGE, String.valueOf(aVar.b()));
                        g.a(g.this, aVar2.a(e.REQUEST), a2);
                    }
                    g.this.i();
                }
            }

            public void b(i iVar) {
                if (!this.b) {
                    this.b = true;
                    g.a(g.this, aVar2.a(e.IMPRESSION), null);
                }
            }

            public void c(i iVar) {
                if (!this.c) {
                    this.c = true;
                    g.a(g.this, aVar2.a(e.CLICK), null);
                }
                if (g.this.c != null) {
                    g.this.c.a();
                }
            }
        };
        iVar.a(context, r02, this.g, map, NativeAdBase.getViewTraversalPredicate());
    }

    /* access modifiers changed from: protected */
    public void a(String str) {
        com.facebook.ads.internal.protocol.a a = e.a(this.b, Integer.valueOf(0), Integer.valueOf(1));
        if (a != null) {
            a(a);
        } else {
            super.a(str);
        }
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public com.facebook.ads.internal.protocol.a c() {
        if (this.h.h == null || this.h.h == d.NONE || d()) {
            return null;
        }
        return new com.facebook.ads.internal.protocol.a(AdErrorType.CLEAR_TEXT_SUPPORT_NOT_ALLOWED, "");
    }
}
