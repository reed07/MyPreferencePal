package com.facebook.ads.internal;

import android.content.Context;
import android.os.Handler;
import com.facebook.ads.AdSettings;
import com.facebook.ads.AdSize;
import com.facebook.ads.NativeAd;
import com.facebook.ads.internal.adapters.AdAdapter;
import com.facebook.ads.internal.adapters.d;
import com.facebook.ads.internal.adapters.i;
import com.facebook.ads.internal.adapters.q;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.ads.internal.protocol.AdPlacementType;
import com.facebook.ads.internal.protocol.e;
import com.facebook.ads.internal.protocol.g;
import com.facebook.ads.internal.u.c;
import com.facebook.ads.internal.u.f;
import com.facebook.ads.internal.w.b.m;
import com.facebook.ads.internal.w.b.y;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class a implements com.facebook.ads.internal.u.c.b {
    /* access modifiers changed from: private */
    public final Context a;
    private final String b;
    private final c c = new c(this.a);
    private final d d;
    private final e e;
    private final AdSize f;
    private final int g;
    private boolean h;
    /* access modifiers changed from: private */
    public final Handler i;
    /* access modifiers changed from: private */
    public final Runnable j;
    private final com.facebook.ads.internal.s.c k;
    private C0000a l;
    private com.facebook.ads.internal.m.c m;
    private String n;

    /* renamed from: com.facebook.ads.internal.a$a reason: collision with other inner class name */
    public interface C0000a {
        void a(com.facebook.ads.internal.protocol.a aVar);

        void a(List<i> list);
    }

    private static final class b extends y<a> {
        public b(a aVar) {
            super(aVar);
        }

        public void run() {
            a aVar = (a) a();
            if (aVar != null) {
                if (com.facebook.ads.internal.w.i.a.a(aVar.a)) {
                    aVar.a();
                } else {
                    aVar.i.postDelayed(aVar.j, DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS);
                }
            }
        }
    }

    static {
        com.facebook.ads.internal.w.b.c.a();
    }

    public a(Context context, String str, e eVar, AdSize adSize, int i2) {
        this.a = context;
        this.b = str;
        this.e = eVar;
        this.f = adSize;
        this.g = i2;
        this.c.a((com.facebook.ads.internal.u.c.b) this);
        this.d = new d();
        this.h = true;
        this.i = new Handler();
        this.j = new b(this);
        this.k = com.facebook.ads.internal.s.d.a(this.a);
        com.facebook.ads.internal.n.a.b(this.a);
    }

    private List<i> d() {
        com.facebook.ads.internal.m.c cVar = this.m;
        final ArrayList arrayList = new ArrayList(cVar.d());
        for (com.facebook.ads.internal.m.a e2 = cVar.e(); e2 != null; e2 = cVar.e()) {
            AdAdapter a2 = this.d.a(AdPlacementType.NATIVE);
            if (a2 != null && a2.getPlacementType() == AdPlacementType.NATIVE) {
                HashMap hashMap = new HashMap();
                hashMap.put("data", e2.c());
                hashMap.put("definition", cVar.a());
                final i iVar = (i) a2;
                iVar.a(this.a, new q() {
                    public void a(i iVar) {
                        arrayList.add(iVar);
                    }

                    public void a(i iVar, com.facebook.ads.internal.protocol.a aVar) {
                    }

                    public void b(i iVar) {
                    }

                    public void c(i iVar) {
                    }
                }, this.k, hashMap, NativeAd.getViewTraversalPredicate());
            }
        }
        return arrayList;
    }

    public void a() {
        try {
            m mVar = null;
            g gVar = new g(this.a, null, null, null);
            Context context = this.a;
            com.facebook.ads.internal.n.d dVar = new com.facebook.ads.internal.n.d(context, false);
            String str = this.b;
            AdSize adSize = this.f;
            if (adSize != null) {
                mVar = new m(adSize.getHeight(), this.f.getWidth());
            }
            com.facebook.ads.internal.u.b bVar = new com.facebook.ads.internal.u.b(context, dVar, str, mVar, this.e, null, this.g, AdSettings.isTestMode(this.a), AdSettings.isChildDirected(), gVar, com.facebook.ads.internal.w.b.q.a(com.facebook.ads.internal.r.a.G(this.a)), this.n);
            this.c.a(bVar);
        } catch (com.facebook.ads.internal.protocol.b e2) {
            a(com.facebook.ads.internal.protocol.a.a(e2));
        }
    }

    public void a(C0000a aVar) {
        this.l = aVar;
    }

    public void a(com.facebook.ads.internal.protocol.a aVar) {
        if (this.h) {
            this.i.postDelayed(this.j, 1800000);
        }
        C0000a aVar2 = this.l;
        if (aVar2 != null) {
            aVar2.a(aVar);
        }
    }

    public void a(f fVar) {
        com.facebook.ads.internal.m.c a2 = fVar.a();
        if (a2 != null) {
            if (this.h) {
                long c2 = a2.a().c();
                if (c2 == 0) {
                    c2 = 1800000;
                }
                this.i.postDelayed(this.j, c2);
            }
            this.m = a2;
            List d2 = d();
            if (this.l != null) {
                if (d2.isEmpty()) {
                    this.l.a(com.facebook.ads.internal.protocol.a.a(AdErrorType.NO_FILL, ""));
                    return;
                }
                this.l.a(d2);
            }
            return;
        }
        throw new IllegalStateException("no placement in response");
    }

    public void a(String str) {
        this.n = str;
    }

    public void b() {
    }

    public void c() {
        this.h = false;
        this.i.removeCallbacks(this.j);
    }
}
