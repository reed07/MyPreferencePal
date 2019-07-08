package com.facebook.ads.internal.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.AudienceNetworkActivity.BackButtonInterceptor;
import com.facebook.ads.internal.h.b;
import com.facebook.ads.internal.o.d;
import com.facebook.ads.internal.view.a.C0012a;
import com.facebook.ads.internal.view.component.a.g;
import com.facebook.ads.internal.view.i.a;
import com.facebook.ads.internal.view.i.b.c;
import com.facebook.ads.internal.view.i.b.e;
import com.facebook.ads.internal.view.i.b.f;
import com.facebook.ads.internal.view.i.b.i;
import com.facebook.ads.internal.view.i.b.j;
import com.facebook.ads.internal.view.i.b.k;
import com.facebook.ads.internal.view.i.b.l;
import com.facebook.ads.internal.view.i.b.m;
import com.facebook.ads.internal.view.i.c.o;
import com.facebook.ads.internal.w.b.w;
import com.facebook.ads.internal.w.b.x;
import com.facebook.ads.internal.x.a.C0023a;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class n extends o {
    private boolean A = false;
    /* access modifiers changed from: private */
    public boolean B = false;
    private final BackButtonInterceptor d = new BackButtonInterceptor() {
        public boolean interceptBackButton() {
            return (n.this.x != null ? n.this.x.c() : false) || !n.this.b.a();
        }
    };
    private final f e = new f() {
        public void a(e eVar) {
            if (n.this.getAudienceNetworkListener() != null) {
                n.this.getAudienceNetworkListener().a("videoInterstitalEvent", (d) eVar);
            }
            if (!n.this.y) {
                n.this.j.g();
                n.this.j.l();
                n.this.y = true;
            }
            if (n.this.v != null) {
                n.this.v.finish();
            }
        }
    };
    private final l f = new l() {
        public void a(k kVar) {
            if (n.this.getAudienceNetworkListener() != null) {
                n.this.getAudienceNetworkListener().a("videoInterstitalEvent", (d) kVar);
            }
        }
    };
    private final j g = new j() {
        public void a(i iVar) {
            if (n.this.getAudienceNetworkListener() != null) {
                n.this.getAudienceNetworkListener().a("videoInterstitalEvent", (d) iVar);
            }
        }
    };
    private final com.facebook.ads.internal.view.i.b.d h = new com.facebook.ads.internal.view.i.b.d() {
        public void a(c cVar) {
            n.this.s.set(true);
            if (n.this.getAudienceNetworkListener() != null) {
                n.this.getAudienceNetworkListener().a("videoInterstitalEvent", (d) cVar);
            }
        }
    };
    private final com.facebook.ads.internal.view.i.b.n i = new com.facebook.ads.internal.view.i.b.n() {
        public void a(m mVar) {
            n.this.B = true;
            if (!n.this.y) {
                n.this.t.set(n.this.j.k());
                n.this.a();
            }
            if (n.this.getAudienceNetworkListener() != null) {
                n.this.getAudienceNetworkListener().a("videoInterstitalEvent", (d) mVar);
            }
            n.this.o.a();
        }
    };
    /* access modifiers changed from: private */
    public final a j = new a(getContext());
    private final o k;
    private final com.facebook.ads.internal.view.i.c.f l;
    /* access modifiers changed from: private */
    public final com.facebook.ads.internal.adapters.b.k m;
    private final com.facebook.ads.internal.adapters.b.l n;
    /* access modifiers changed from: private */
    public final com.facebook.ads.internal.x.a o;
    private final C0023a p;
    /* access modifiers changed from: private */
    public final w q = new w();
    @Nullable
    private final b r;
    /* access modifiers changed from: private */
    public final AtomicBoolean s = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    public final AtomicBoolean t = new AtomicBoolean(false);
    private final com.facebook.ads.internal.view.i.c u;
    /* access modifiers changed from: private */
    @Nullable
    public AudienceNetworkActivity v;
    @Nullable
    private com.facebook.ads.internal.view.i.a.a w;
    /* access modifiers changed from: private */
    @Nullable
    public com.facebook.ads.internal.view.component.a.l x;
    /* access modifiers changed from: private */
    public boolean y = false;
    private boolean z = false;

    public n(Context context, com.facebook.ads.internal.s.c cVar, com.facebook.ads.internal.adapters.b.k kVar, @Nullable b bVar, C0012a aVar) {
        super(context, cVar, aVar);
        this.j.setVideoProgressReportIntervalMs(kVar.h());
        x.a((View) this.j);
        x.a((View) this.j, 0);
        this.m = kVar;
        this.n = (com.facebook.ads.internal.adapters.b.l) this.m.d().get(0);
        this.r = bVar;
        this.k = new o(getContext());
        this.l = new com.facebook.ads.internal.view.i.c.f(context);
        this.j.getEventBus().a((T[]) new com.facebook.ads.internal.o.f[]{this.f, this.g, this.h, this.e, this.i});
        setupPlugins(this.n);
        this.p = new C0023a() {
            public void a() {
                if (!n.this.q.b()) {
                    n.this.q.a();
                    HashMap hashMap = new HashMap();
                    if (!TextUtils.isEmpty(n.this.m.c())) {
                        n.this.o.a((Map<String, String>) hashMap);
                        hashMap.put("touch", com.facebook.ads.internal.w.b.k.a(n.this.q.e()));
                        n.this.a(hashMap);
                        n.this.a.a(n.this.m.c(), hashMap);
                        if (n.this.getAudienceNetworkListener() != null) {
                            n.this.getAudienceNetworkListener().a("com.facebook.ads.interstitial.impression.logged");
                        }
                    }
                }
            }
        };
        this.o = new com.facebook.ads.internal.x.a(this, 1, this.p);
        this.o.a(kVar.f());
        this.o.b(kVar.g());
        this.u = new com.facebook.ads.internal.view.i.b(getContext(), this.a, this.j, this.m.c());
        a aVar2 = this.j;
        String a = this.n.c().a();
        String str = "";
        b bVar2 = this.r;
        if (!(bVar2 == null || a == null)) {
            str = bVar2.c(a);
        }
        if (!TextUtils.isEmpty(str)) {
            a = str;
        }
        aVar2.setVideoURI(a);
    }

    /* access modifiers changed from: private */
    public void a() {
        this.l.setVisibility(this.t.get() ? 0 : 8);
    }

    private void setUpContent(int i2) {
        com.facebook.ads.internal.view.component.a.e.a aVar = new com.facebook.ads.internal.view.component.a.e.a(getContext(), this.a, getAudienceNetworkListener(), this.m, this.j, this.o, this.q);
        com.facebook.ads.internal.view.component.a.e a = aVar.a(i.a).b(i2).a(this.k).a((View) this.l).a();
        com.facebook.ads.internal.view.component.a.c a2 = com.facebook.ads.internal.view.component.a.d.a(a);
        a();
        this.x = g.a(a, x.a.heightPixels - a2.getExactMediaHeightIfAvailable(), x.a.widthPixels - a2.getExactMediaWidthIfAvailable(), this.A);
        a(a2, this.x, this.x != null ? new u.a() {
            public void a() {
                if (n.this.j.m() && !n.this.j.n()) {
                    n.this.j.a(com.facebook.ads.internal.view.i.a.a.AUTO_STARTED);
                }
                n.this.x.b();
            }

            public void b() {
                n.this.x.a();
                n.this.j.a(false);
            }
        } : null, a2.getExactMediaHeightIfAvailable(), x.a.widthPixels - a2.getExactMediaWidthIfAvailable(), a2.a(), i2);
    }

    private void setupPlugins(com.facebook.ads.internal.adapters.b.l lVar) {
        this.j.d();
        this.j.a((com.facebook.ads.internal.view.i.a.b) this.k);
        this.j.a((com.facebook.ads.internal.view.i.a.b) this.l);
        if (!TextUtils.isEmpty(lVar.c().g())) {
            com.facebook.ads.internal.view.i.c.g gVar = new com.facebook.ads.internal.view.i.c.g(getContext());
            this.j.a((com.facebook.ads.internal.view.i.a.b) gVar);
            gVar.setImage(lVar.c().g());
        }
        com.facebook.ads.internal.view.i.c.l lVar2 = new com.facebook.ads.internal.view.i.c.l(getContext(), true);
        this.j.a((com.facebook.ads.internal.view.i.a.b) lVar2);
        this.j.a((com.facebook.ads.internal.view.i.a.b) new com.facebook.ads.internal.view.i.c.d(lVar2, lVar.c().e() ? com.facebook.ads.internal.view.i.c.d.a.FADE_OUT_ON_PLAY : com.facebook.ads.internal.view.i.c.d.a.VISIBLE, true));
        this.j.a((com.facebook.ads.internal.view.i.a.b) new com.facebook.ads.internal.view.i.c.k(getContext()));
        this.j.a((com.facebook.ads.internal.view.i.a.b) this.b);
    }

    public void a(Intent intent, Bundle bundle, AudienceNetworkActivity audienceNetworkActivity) {
        super.a(audienceNetworkActivity, this.m);
        this.v = audienceNetworkActivity;
        setUpContent(audienceNetworkActivity.getResources().getConfiguration().orientation);
        this.v.addBackButtonInterceptor(this.d);
        com.facebook.ads.internal.adapters.b.l lVar = (com.facebook.ads.internal.adapters.b.l) this.m.d().get(0);
        this.j.setVolume(lVar.c().f() ? BitmapDescriptorFactory.HUE_RED : 1.0f);
        if (lVar.c().e()) {
            this.j.a(com.facebook.ads.internal.view.i.a.a.AUTO_STARTED);
        }
        if (lVar.c().c() > 0) {
            postDelayed(new Runnable() {
                public void run() {
                    if (!n.this.B) {
                        n.this.b.a(true);
                    }
                }
            }, (long) com.facebook.ads.internal.r.a.ad(getContext()));
        }
    }

    public void a(Bundle bundle) {
    }

    public void a_(boolean z2) {
        com.facebook.ads.internal.view.component.a.l lVar = this.x;
        if (lVar != null) {
            lVar.e();
        }
        if (!this.y && !this.j.m()) {
            this.w = this.j.getVideoStartReason();
            this.z = z2;
            this.j.a(false);
        }
    }

    public void b(boolean z2) {
        com.facebook.ads.internal.view.component.a.l lVar = this.x;
        if (lVar != null) {
            lVar.f();
        }
        if (!this.y && !this.j.n()) {
            if ((this.j.getState() != com.facebook.ads.internal.view.i.d.d.PREPARED || this.j.getVideoStartReason() != com.facebook.ads.internal.view.i.a.a.NOT_STARTED) && this.j.getState() != com.facebook.ads.internal.view.i.d.d.PLAYBACK_COMPLETED && this.w != null) {
                if (!this.z || z2) {
                    this.j.a(this.w);
                }
            }
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        removeAllViews();
        x.b(this.j);
        x.b(this.k);
        x.b(this.l);
        com.facebook.ads.internal.view.component.a.l lVar = this.x;
        if (lVar != null) {
            x.b(lVar);
            this.A = this.x.d();
        }
        setUpContent(configuration.orientation);
        super.onConfigurationChanged(configuration);
    }

    public void onDestroy() {
        if (!this.y) {
            if (!this.s.get()) {
                this.j.f();
            }
            com.facebook.ads.internal.adapters.b.k kVar = this.m;
            if (kVar != null && !TextUtils.isEmpty(kVar.c())) {
                HashMap hashMap = new HashMap();
                this.o.a((Map<String, String>) hashMap);
                hashMap.put("touch", com.facebook.ads.internal.w.b.k.a(this.q.e()));
                this.a.l(this.m.c(), hashMap);
            }
            this.j.g();
            this.j.l();
            this.y = true;
        }
        com.facebook.ads.internal.view.component.a.l lVar = this.x;
        if (lVar != null) {
            lVar.g();
        }
        this.o.c();
        this.v = null;
        super.onDestroy();
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        this.q.a(motionEvent, this, this);
        return super.onInterceptTouchEvent(motionEvent);
    }
}
