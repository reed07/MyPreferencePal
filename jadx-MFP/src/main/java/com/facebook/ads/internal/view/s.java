package com.facebook.ads.internal.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import android.text.TextUtils;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.AudienceNetworkActivity.BackButtonInterceptor;
import com.facebook.ads.internal.adapters.b.h;
import com.facebook.ads.internal.adapters.b.q;
import com.facebook.ads.internal.view.a.C0012a;
import com.facebook.ads.internal.view.g.c;
import com.facebook.ads.internal.view.i.a;
import com.facebook.ads.internal.view.i.b;
import com.facebook.ads.internal.view.i.b.aa;
import com.facebook.ads.internal.view.i.b.e;
import com.facebook.ads.internal.view.i.b.f;
import com.facebook.ads.internal.view.i.b.m;
import com.facebook.ads.internal.view.i.b.n;
import com.facebook.ads.internal.view.i.b.o;
import com.facebook.ads.internal.view.i.b.p;
import com.facebook.ads.internal.view.i.c.d;
import com.facebook.ads.internal.view.i.c.j;
import com.facebook.ads.internal.view.i.c.l;
import com.facebook.ads.internal.w.b.k;
import com.facebook.ads.internal.w.b.w;
import com.facebook.ads.internal.w.b.x;
import com.facebook.ads.internal.x.a.C0023a;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class s extends RelativeLayout implements a {
    static final /* synthetic */ boolean a = (!s.class.desiredAssertionStatus());
    private static final int b = ((int) (x.b * 12.0f));
    private static final int c = ((int) (x.b * 18.0f));
    private static final int d = ((int) (x.b * 16.0f));
    private static final int e = ((int) (x.b * 72.0f));
    private static final int f = ((int) (x.b * 56.0f));
    private static final int g = ((int) (x.b * 56.0f));
    private static final int h = ((int) (x.b * 28.0f));
    private static final int i = ((int) (x.b * 20.0f));
    private static final LayoutParams j = new LayoutParams(-1, -1);
    @Nullable
    private Context A;
    /* access modifiers changed from: private */
    @Nullable
    public a B;
    /* access modifiers changed from: private */
    @Nullable
    public C0012a C;
    @Nullable
    private com.facebook.ads.internal.view.g.a D;
    /* access modifiers changed from: private */
    @Nullable
    public d E;
    @Nullable
    private l F;
    @Nullable
    private View G;
    /* access modifiers changed from: private */
    @Nullable
    public j H;
    /* access modifiers changed from: private */
    @Nullable
    public i I;
    @Nullable
    private com.facebook.ads.internal.view.i.a.a J;
    @Nullable
    private Integer K;
    /* access modifiers changed from: private */
    public c L;
    /* access modifiers changed from: private */
    public boolean M = false;
    private boolean N = false;
    private WeakReference<AudienceNetworkActivity> O;
    private final BackButtonInterceptor k = new BackButtonInterceptor() {
        public boolean interceptBackButton() {
            return !s.this.M;
        }
    };
    private final com.facebook.ads.internal.view.i.b.d l = new com.facebook.ads.internal.view.i.b.d() {
        public void a(com.facebook.ads.internal.view.i.b.c cVar) {
            if (s.this.C != null) {
                s.this.L.d();
                s.d(s.this);
                s.this.C.a(aa.REWARDED_VIDEO_COMPLETE.a(), (com.facebook.ads.internal.o.d) cVar);
            }
        }
    };
    private final f m = new f() {
        public void a(e eVar) {
            if (s.this.C != null) {
                s.this.C.a(aa.REWARDED_VIDEO_ERROR.a());
            }
            s.this.a();
        }
    };
    private final n n = new n() {
        public void a(m mVar) {
            if (s.this.B != null) {
                s.this.B.a(com.facebook.ads.internal.view.i.a.a.USER_STARTED);
                s.this.r.a();
                s.this.z.set(s.this.B.k());
                s.this.b();
            }
        }
    };
    private final p o = new p() {
        public void a(o oVar) {
            if (s.this.B != null && s.this.E != null && s.this.B.getDuration() - s.this.B.getCurrentPositionInMillis() <= 3000 && s.this.E.a()) {
                s.this.E.b();
            }
        }
    };
    /* access modifiers changed from: private */
    public final q p;
    /* access modifiers changed from: private */
    public final com.facebook.ads.internal.s.c q;
    /* access modifiers changed from: private */
    public final com.facebook.ads.internal.x.a r;
    private final C0023a s;
    /* access modifiers changed from: private */
    public final w t = new w();
    private final com.facebook.ads.internal.view.i.c.o u;
    private final b v;
    private final RelativeLayout w;
    private final com.facebook.ads.internal.view.i.c.f x;
    private final h y;
    /* access modifiers changed from: private */
    public final AtomicBoolean z = new AtomicBoolean(false);

    public s(Context context, com.facebook.ads.internal.s.c cVar, a aVar, C0012a aVar2, q qVar) {
        super(context);
        this.A = context;
        this.C = aVar2;
        this.B = aVar;
        this.q = cVar;
        this.p = qVar;
        this.y = this.p.i().a();
        this.w = new RelativeLayout(context);
        this.u = new com.facebook.ads.internal.view.i.c.o(this.A);
        this.x = new com.facebook.ads.internal.view.i.c.f(this.A);
        new com.facebook.ads.internal.view.c.d(this.w, i).a().a(com.facebook.ads.internal.r.a.m(this.A)).a(this.p.j().g());
        this.s = new C0023a() {
            public void a() {
                if (!s.this.t.b()) {
                    s.this.t.a();
                    HashMap hashMap = new HashMap();
                    if (!TextUtils.isEmpty(s.this.p.a())) {
                        s.this.r.a((Map<String, String>) hashMap);
                        hashMap.put("touch", k.a(s.this.t.e()));
                        if (s.this.p.d() != null) {
                            hashMap.put("extra_hints", s.this.p.d());
                        }
                        hashMap.put("is_cyoa", String.valueOf(s.this.p.l()));
                        s.this.q.a(s.this.p.a(), hashMap);
                    }
                    if (s.this.C != null) {
                        s.this.C.a(aa.REWARDED_VIDEO_IMPRESSION.a());
                    }
                }
            }
        };
        this.r = new com.facebook.ads.internal.x.a(this, 1, this.s);
        this.r.a((int) Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
        this.v = new b(this.A, this.q, this.B, this.p.a());
        c cVar2 = new c(this.A, this.q, this.p, this.C, this.r, this.t);
        this.L = cVar2;
        if (a || this.B != null) {
            this.B.setVideoProgressReportIntervalMs(qVar.b());
            x.a((View) this.B, -16777216);
            this.B.getEventBus().a((T[]) new com.facebook.ads.internal.o.f[]{this.l, this.m, this.n, this.o});
            return;
        }
        throw new AssertionError();
    }

    /* access modifiers changed from: private */
    public void b() {
        this.x.setVisibility(this.z.get() ? 0 : 8);
    }

    static /* synthetic */ void d(s sVar) {
        LayoutParams layoutParams;
        sVar.M = true;
        Context context = sVar.A;
        if (context != null) {
            FrameLayout frameLayout = new FrameLayout(context);
            frameLayout.setLayoutParams(j);
            x.a((View) frameLayout, -1509949440);
            sVar.w.addView(frameLayout, 0);
        }
        x.a((ViewGroup) sVar.w);
        a aVar = sVar.B;
        if (aVar != null) {
            aVar.d();
            sVar.B.setVisibility(4);
        }
        i iVar = sVar.I;
        if (iVar != null) {
            if (iVar.a()) {
                sVar.I.b();
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    public void run() {
                        if (s.this.I != null) {
                            s.this.I.setCloseButtonStyle(i.a.CROSS);
                            s.this.I.a(true);
                        }
                    }
                }, 1000);
            } else {
                sVar.I.a(true);
                sVar.I.setCloseButtonStyle(i.a.CROSS);
            }
            sVar.I.c();
        }
        x.a(sVar.B, sVar.H, sVar.x, sVar.u);
        Pair c2 = sVar.L.c();
        switch ((c.a) c2.first) {
            case MARKUP:
                x.a(sVar.D);
                sVar.w.addView((View) c2.second, j);
                return;
            case SCREENSHOTS:
                com.facebook.ads.internal.view.g.a aVar2 = sVar.D;
                if (aVar2 != null) {
                    aVar2.setVisibility(0);
                    sVar.D.a();
                }
                layoutParams = new LayoutParams(-1, -1);
                layoutParams.setMargins(0, g, 0, 0);
                layoutParams.addRule(2, sVar.D.getId());
                break;
            case INFO:
                x.a(sVar.D);
                layoutParams = new LayoutParams(-1, -2);
                layoutParams.addRule(15);
                int i2 = d;
                layoutParams.setMargins(i2, i2, i2, i2);
                break;
            case PLAYABLE:
                AudienceNetworkActivity audienceNetworkActivity = (AudienceNetworkActivity) sVar.O.get();
                if (audienceNetworkActivity != null) {
                    sVar.K = Integer.valueOf(audienceNetworkActivity.getRequestedOrientation());
                    audienceNetworkActivity.setRequestedOrientation(1);
                }
                sVar.w.removeAllViews();
                x.b(sVar.I);
                sVar.w.addView((View) c2.second, j);
                ((com.facebook.ads.internal.view.f.b) c2.second).c();
                return;
            default:
                return;
        }
        sVar.w.addView((View) c2.second, layoutParams);
        sVar.t.a();
    }

    private void setUpContentLayoutForVideo(int i2) {
        this.w.removeAllViews();
        this.w.addView(this.B, j);
        com.facebook.ads.internal.view.g.a aVar = this.D;
        if (aVar != null) {
            x.a((View) aVar);
            this.D.a(i2);
            LayoutParams layoutParams = new LayoutParams(-1, -2);
            layoutParams.addRule(12);
            com.facebook.ads.internal.view.g.a aVar2 = this.D;
            int i3 = d;
            aVar2.setPadding(i3, i3, i3, i3);
            this.w.addView(this.D, layoutParams);
        }
        if (this.H != null) {
            int i4 = f;
            LayoutParams layoutParams2 = new LayoutParams(i4, i4);
            layoutParams2.addRule(10);
            layoutParams2.addRule(11);
            j jVar = this.H;
            int i5 = d;
            jVar.setPadding(i5, i5, i5, i5);
            this.w.addView(this.H, layoutParams2);
        }
        int i6 = h;
        LayoutParams layoutParams3 = new LayoutParams(i6, i6);
        layoutParams3.addRule(10);
        layoutParams3.addRule(11);
        int i7 = b;
        layoutParams3.setMargins(i7, g + i7, i7, c);
        this.w.addView(this.x, layoutParams3);
        b();
        LayoutParams layoutParams4 = new LayoutParams(-1, -2);
        layoutParams4.addRule(12);
        this.w.addView(this.u, layoutParams4);
    }

    public void a() {
        a aVar = this.B;
        if (aVar != null) {
            aVar.g();
            this.B.l();
        }
        com.facebook.ads.internal.x.a aVar2 = this.r;
        if (aVar2 != null) {
            aVar2.c();
        }
    }

    public void a(Intent intent, Bundle bundle, AudienceNetworkActivity audienceNetworkActivity) {
        a aVar;
        com.facebook.ads.internal.view.i.a.b bVar;
        if (this.B != null && this.C != null) {
            this.O = new WeakReference<>(audienceNetworkActivity);
            a aVar2 = this.B;
            if (aVar2 != null) {
                aVar2.d();
                this.B.a((com.facebook.ads.internal.view.i.a.b) new com.facebook.ads.internal.view.i.c.k(this.A));
                this.B.a((com.facebook.ads.internal.view.i.a.b) this.x);
                this.B.a((com.facebook.ads.internal.view.i.a.b) this.u);
                this.F = new l(this.A, true);
                this.G = new View(this.A);
                this.G.setLayoutParams(j);
                x.a(this.G, -1509949440);
                d dVar = new d(this.G, d.a.FADE_OUT_ON_PLAY, true);
                this.B.addView(this.G);
                this.B.a((com.facebook.ads.internal.view.i.a.b) dVar);
                d dVar2 = new d(this.F, d.a.FADE_OUT_ON_PLAY, true);
                this.B.a((com.facebook.ads.internal.view.i.a.b) this.F);
                this.B.a((com.facebook.ads.internal.view.i.a.b) dVar2);
                com.facebook.ads.internal.view.g.a aVar3 = new com.facebook.ads.internal.view.g.a(this.A, e, this.y, this.q, this.C, this.L.b() == c.a.INFO, this.L.b() == c.a.INFO, this.r, this.t);
                this.D = aVar3;
                this.D.setInfo(this.p);
                this.E = new d(this.D, d.a.FADE_OUT_ON_PLAY, true);
                this.B.a((com.facebook.ads.internal.view.i.a.b) this.E);
                if (this.L.a() && this.p.j().c() > 0) {
                    this.H = new j(this.A, this.p.j().c(), -12286980);
                    this.H.setButtonMode(j.a.SKIP_BUTTON_MODE);
                    this.H.setOnClickListener(new OnClickListener() {
                        public void onClick(View view) {
                            if (s.this.H != null && s.this.H.a() && s.this.H.getSkipSeconds() != 0 && s.this.B != null) {
                                s.this.B.f();
                            }
                        }
                    });
                    aVar = this.B;
                    bVar = this.H;
                } else if (!this.L.a()) {
                    this.I = new i(this.A, this.C, com.facebook.ads.internal.r.a.K(this.A) ? i.a.ARROWS : i.a.CROSS);
                    this.I.a(this.p.f(), this.p.a(), this.p.j().c());
                    if (this.p.j().c() <= 0) {
                        this.I.b();
                    }
                    if (this.L.b() != c.a.INFO) {
                        this.I.c();
                    }
                    this.I.setToolbarListener(new i.b() {
                        public void a() {
                            if (s.this.t.a(s.this.getContext())) {
                                HashMap hashMap = new HashMap();
                                s.this.r.a((Map<String, String>) hashMap);
                                hashMap.put("touch", k.a(s.this.t.e()));
                                s.this.q.i(s.this.p.a(), hashMap);
                                return;
                            }
                            if (!s.this.M && s.this.B != null) {
                                s.this.M = true;
                                s.this.B.f();
                            } else if (s.this.M && s.this.C != null) {
                                s.this.C.a(aa.REWARDED_VIDEO_END_ACTIVITY.a());
                            }
                        }
                    });
                    aVar = this.B;
                    bVar = this.I;
                }
                aVar.a(bVar);
            }
            audienceNetworkActivity.addBackButtonInterceptor(this.k);
            this.B.setVideoURI(!TextUtils.isEmpty(this.p.j().b()) ? this.p.j().b() : this.p.j().a());
            setUpContentLayoutForVideo(audienceNetworkActivity.getResources().getConfiguration().orientation);
            addView(this.w, j);
            i iVar = this.I;
            if (iVar != null) {
                x.a((View) iVar);
                this.I.a(this.y, true);
                if (com.facebook.ads.internal.f.a.a(getContext(), true)) {
                    this.I.a(this.p.f(), this.p.a());
                }
                addView(this.I, new LayoutParams(-1, g));
            }
            setLayoutParams(j);
            this.C.a((View) this);
        }
    }

    public void a(Bundle bundle) {
    }

    public void a_(boolean z2) {
        a aVar = this.B;
        if (aVar != null && !aVar.m()) {
            this.J = this.B.getVideoStartReason();
            this.N = z2;
            this.B.a(false);
        }
    }

    public void b(boolean z2) {
        a aVar = this.B;
        if (aVar != null && !aVar.n() && this.B.getState() != com.facebook.ads.internal.view.i.d.d.PLAYBACK_COMPLETED && this.J != null) {
            if (!this.N || z2) {
                this.B.a(this.J);
            }
        }
    }

    public int getCurrentPosition() {
        a aVar = this.B;
        if (aVar != null) {
            return aVar.getCurrentPositionInMillis();
        }
        return 0;
    }

    public void onConfigurationChanged(Configuration configuration) {
        com.facebook.ads.internal.view.g.a aVar = this.D;
        if (aVar != null) {
            aVar.a(configuration.orientation);
        }
    }

    public void onDestroy() {
        a();
        a aVar = this.B;
        if (aVar != null) {
            aVar.getEventBus().b((T[]) new com.facebook.ads.internal.o.f[]{this.l, this.m, this.n, this.o});
        }
        if (!TextUtils.isEmpty(this.p.a())) {
            HashMap hashMap = new HashMap();
            this.r.a((Map<String, String>) hashMap);
            hashMap.put("touch", k.a(this.t.e()));
            this.q.l(this.p.a(), hashMap);
        }
        i iVar = this.I;
        if (iVar != null) {
            iVar.setToolbarListener(null);
        }
        if (!(this.K == null || this.O.get() == null)) {
            ((AudienceNetworkActivity) this.O.get()).setRequestedOrientation(this.K.intValue());
        }
        this.v.a();
        this.B = null;
        this.L.e();
        this.H = null;
        this.D = null;
        this.E = null;
        this.C = null;
        this.A = null;
        this.u.a();
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        this.t.a(motionEvent, this, this);
        return super.onInterceptTouchEvent(motionEvent);
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public void setEndCardController(c cVar) {
        this.L = cVar;
    }

    public void setListener(C0012a aVar) {
    }
}
