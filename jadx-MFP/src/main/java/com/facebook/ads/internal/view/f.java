package com.facebook.ads.internal.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.AudienceNetworkActivity.BackButtonInterceptor;
import com.facebook.ads.internal.adapters.b.q;
import com.facebook.ads.internal.s.h;
import com.facebook.ads.internal.view.a.C0012a;
import com.facebook.ads.internal.view.component.i;
import com.facebook.ads.internal.view.i.b.aa;
import com.facebook.ads.internal.w.b.e;
import com.facebook.ads.internal.w.b.k;
import com.facebook.ads.internal.w.b.w;
import com.facebook.ads.internal.w.b.x;
import com.facebook.ads.internal.x.a.C0023a;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class f extends RelativeLayout implements a {
    private static final LayoutParams a = new LayoutParams(-1, -1);
    private static final int b = ((int) (x.b * 16.0f));
    private static final int c = ((int) (x.b * 56.0f));
    private static final int d = ((int) (x.b * 230.0f));
    private final com.facebook.ads.internal.adapters.b.f e;
    private final C0012a f;
    private final com.facebook.ads.internal.s.c g;
    /* access modifiers changed from: private */
    public final w h = new w();
    private final com.facebook.ads.internal.x.a i;
    private final C0023a j;
    /* access modifiers changed from: private */
    public final e k;
    private final int l;
    /* access modifiers changed from: private */
    public boolean m;
    private boolean n;
    private WeakReference<AudienceNetworkActivity> o;
    /* access modifiers changed from: private */
    public final i p;
    private final TextView q;
    /* access modifiers changed from: private */
    public final LinearLayout r;
    private final BackButtonInterceptor s = new BackButtonInterceptor() {
        public boolean interceptBackButton() {
            return true;
        }
    };

    public static class a extends com.facebook.ads.internal.o.d {
        private q a;

        public a(q qVar) {
            this.a = qVar;
        }

        public q a() {
            return this.a;
        }
    }

    private static class b implements OnClickListener, OnTouchListener {
        final WeakReference<f> a;
        final WeakReference<com.facebook.ads.internal.view.g.b> b;

        public b(f fVar, com.facebook.ads.internal.view.g.b bVar) {
            this.a = new WeakReference<>(fVar);
            this.b = new WeakReference<>(bVar);
        }

        public void onClick(View view) {
            if (this.a.get() != null && this.b.get() != null && !((com.facebook.ads.internal.view.g.b) this.b.get()).a()) {
                f.a((f) this.a.get(), ((com.facebook.ads.internal.view.g.b) this.b.get()).getAdDataBundle());
            }
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (this.a.get() != null) {
                ((f) this.a.get()).getTouchDataRecorder().a(motionEvent, (View) this.a.get(), view);
            }
            return false;
        }
    }

    public static class c implements com.facebook.ads.internal.view.a.b {
        final WeakReference<f> a;
        final WeakReference<com.facebook.ads.internal.view.g.b> b;

        c(f fVar, com.facebook.ads.internal.view.g.b bVar) {
            this.a = new WeakReference<>(fVar);
            this.b = new WeakReference<>(bVar);
        }

        public void a() {
            f fVar = (f) this.a.get();
            if (fVar != null) {
                fVar.setIsAdReportingLayoutVisible(true);
                fVar.c(true);
            }
        }

        public void a(com.facebook.ads.internal.f.c cVar, com.facebook.ads.internal.f.b.a aVar) {
            if (this.b.get() != null) {
                ((com.facebook.ads.internal.view.g.b) this.b.get()).a(cVar, aVar);
            }
        }

        public void a(boolean z) {
            if (this.a.get() != null) {
                ((f) this.a.get()).setIsAdReportingLayoutVisible(false);
                if (z) {
                    ((f) this.a.get()).a();
                } else {
                    ((f) this.a.get()).c(false);
                }
            }
        }
    }

    private static class d implements com.facebook.ads.internal.w.b.e.a {
        private final WeakReference<f> a;
        private final WeakReference<i> b;
        private final com.facebook.ads.internal.adapters.b.f c;
        private int d;

        public d(f fVar, com.facebook.ads.internal.adapters.b.f fVar2, int i) {
            this.a = new WeakReference<>(fVar);
            this.b = new WeakReference<>(fVar.p);
            this.c = fVar2;
            this.d = i;
        }

        public void a() {
            if (this.a.get() != null) {
                LinearLayout b2 = ((f) this.a.get()).r;
                int b3 = this.c.i().b();
                if (((com.facebook.ads.internal.view.g.b) b2.getChildAt(b3)).a()) {
                    int i = 0;
                    while (true) {
                        if (i >= b2.getChildCount()) {
                            break;
                        } else if (!((com.facebook.ads.internal.view.g.b) b2.getChildAt(i)).a()) {
                            b3 = i;
                            break;
                        } else {
                            i++;
                        }
                    }
                }
                f.a((f) this.a.get(), (q) this.c.j().get(b3));
            }
        }

        public void a(int i) {
            i iVar = (i) this.b.get();
            if (iVar != null) {
                int i2 = this.d;
                iVar.setProgress(((i2 - i) * 100) / i2);
                iVar.setText(this.c.e().a(String.valueOf(i)));
            }
        }
    }

    public f(Context context, com.facebook.ads.internal.adapters.b.f fVar, com.facebook.ads.internal.s.c cVar, C0012a aVar) {
        super(context);
        this.e = fVar;
        this.g = cVar;
        this.l = this.e.i().a() / 1000;
        this.f = aVar;
        this.j = new C0023a() {
            public void a() {
                if (!f.this.h.b()) {
                    f.this.h.a();
                    for (int i = 0; i < f.this.r.getChildCount(); i++) {
                        if (f.this.r.getChildAt(i) instanceof com.facebook.ads.internal.view.g.b) {
                            com.facebook.ads.internal.view.g.b bVar = (com.facebook.ads.internal.view.g.b) f.this.r.getChildAt(i);
                            bVar.a(i);
                            bVar.setViewability(true);
                        }
                    }
                    if (!f.this.m) {
                        f.this.k.a();
                    }
                }
            }
        };
        this.i = new com.facebook.ads.internal.x.a(this, 1, this.j);
        this.i.a((int) Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
        this.p = new i(context);
        x.a((View) this.p);
        this.q = new TextView(getContext());
        x.a((View) this.q);
        this.r = new LinearLayout(getContext());
        boolean z = getResources().getConfiguration().orientation == 1;
        this.p.setProgress(0);
        this.p.a(false, Color.parseColor(this.e.g()), 14);
        this.p.setText(this.e.e().a(String.valueOf(this.l)));
        x.a((View) this.p, 0);
        LayoutParams layoutParams = new LayoutParams(-1, c);
        layoutParams.addRule(10);
        addView(this.p, layoutParams);
        this.q.setText(this.e.e().a());
        x.a(this.q, true, 32);
        this.q.setTextColor(Color.parseColor(this.e.h()));
        LayoutParams layoutParams2 = new LayoutParams(z ? d : -1, -2);
        int i2 = b;
        layoutParams2.setMargins(i2, 0, i2, i2 / 2);
        layoutParams2.addRule(3, this.p.getId());
        addView(this.q, layoutParams2);
        LinearLayout linearLayout = this.r;
        int i3 = b;
        linearLayout.setPadding(i3 / 2, i3 / 2, i3 / 2, i3 / 2);
        this.r.setOrientation(z ? 1 : 0);
        a(z, this.e.j());
        LayoutParams layoutParams3 = new LayoutParams(-1, -1);
        layoutParams3.addRule(3, this.q.getId());
        addView(this.r, layoutParams3);
        x.a((View) this, Color.parseColor(this.e.f()));
        int i4 = this.l;
        this.k = new e(i4, new d(this, this.e, i4));
        this.i.a();
    }

    static /* synthetic */ void a(f fVar, q qVar) {
        if (!fVar.m) {
            fVar.m = true;
            fVar.k.b();
            com.facebook.ads.internal.x.a aVar = fVar.i;
            if (aVar != null) {
                aVar.c();
            }
            View view = new View(fVar.getContext());
            view.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                }
            });
            fVar.addView(view, new LayoutParams(-1, -1));
            h hVar = new h();
            for (int i2 = 0; i2 < fVar.r.getChildCount(); i2++) {
                com.facebook.ads.internal.view.g.b bVar = (com.facebook.ads.internal.view.g.b) fVar.r.getChildAt(i2);
                if (bVar.getAdDataBundle() == qVar) {
                    hVar.c(i2);
                }
                bVar.d();
            }
            String a2 = qVar.a();
            hVar.d((fVar.l - fVar.k.e()) * 1000);
            hVar.e(fVar.l * 1000);
            hVar.a(fVar.e.j().size());
            hVar.a(fVar.k.d());
            hVar.b(fVar.e.i().b());
            HashMap hashMap = new HashMap();
            fVar.i.a((Map<String, String>) hashMap);
            hashMap.put("touch", k.a(fVar.h.e()));
            hashMap.put("ad_selection", k.a(hVar.a()));
            hashMap.put("is_cyoa", Boolean.TRUE.toString());
            fVar.g.p(a2, hashMap);
            qVar.a(fVar.e.b());
            qVar.a(fVar.e.c());
            x.c(fVar);
            x.b(fVar);
            fVar.f.a(aa.REWARDED_VIDEO_CHOOSE_YOUR_OWN_AD.a(), (com.facebook.ads.internal.o.d) new a(qVar));
            WeakReference<AudienceNetworkActivity> weakReference = fVar.o;
            if (weakReference != null && weakReference.get() != null) {
                ((AudienceNetworkActivity) fVar.o.get()).removeBackButtonInterceptor(fVar.s);
            }
        }
    }

    private void a(boolean z, List<q> list) {
        this.r.setWeightSum((float) list.size());
        boolean z2 = list.size() == 2;
        boolean z3 = list.size() >= 3 && !z;
        int i2 = 0;
        for (q bVar : list) {
            com.facebook.ads.internal.view.g.b bVar2 = new com.facebook.ads.internal.view.g.b(getContext(), bVar, this.g, this.i, this.h, this.f);
            bVar2.setShouldPlayButtonOnTop(z3);
            bVar2.a(this.e.i().d());
            bVar2.setCornerRadius(10);
            int i3 = -1;
            int i4 = z ? -1 : 0;
            if (z) {
                i3 = 0;
            }
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(i4, i3);
            int i5 = b;
            layoutParams.setMargins(i5 / 2, i5 / 2, i5 / 2, i5 / 2);
            layoutParams.weight = 1.0f;
            b bVar3 = new b(this, bVar2);
            bVar2.setOnTouchListener(bVar3);
            bVar2.setOnClickListener(bVar3);
            bVar2.setAdReportingFlowListener(new c(this, bVar2));
            if (z2) {
                bVar2.a(i2 % 2 != 0, this.e.i().c());
            }
            this.r.addView(bVar2, layoutParams);
            i2++;
        }
    }

    /* access modifiers changed from: 0000 */
    public void a() {
        boolean z = true;
        for (int i2 = 0; i2 < this.r.getChildCount(); i2++) {
            com.facebook.ads.internal.view.g.b bVar = (com.facebook.ads.internal.view.g.b) this.r.getChildAt(i2);
            z &= bVar.a();
            bVar.d();
        }
        if (z) {
            C0012a aVar = this.f;
            if (aVar != null) {
                aVar.a(aa.REWARDED_VIDEO_END_ACTIVITY.a());
            }
        }
    }

    public void a(Intent intent, Bundle bundle, AudienceNetworkActivity audienceNetworkActivity) {
        if (this.f != null) {
            setLayoutParams(a);
            this.f.a((View) this);
            audienceNetworkActivity.addBackButtonInterceptor(this.s);
            this.o = new WeakReference<>(audienceNetworkActivity);
        }
    }

    public void a(Bundle bundle) {
        this.k.b();
    }

    public void a_(boolean z) {
        this.k.b();
    }

    public void b(boolean z) {
        if (this.m) {
            return;
        }
        if (z || !this.n) {
            this.k.a();
        }
    }

    /* access modifiers changed from: 0000 */
    public void c(boolean z) {
        for (int i2 = 0; i2 < this.r.getChildCount(); i2++) {
            if (z) {
                ((com.facebook.ads.internal.view.g.b) this.r.getChildAt(i2)).b();
            } else {
                ((com.facebook.ads.internal.view.g.b) this.r.getChildAt(i2)).c();
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public final w getTouchDataRecorder() {
        return this.h;
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
        boolean z = true;
        boolean z2 = configuration.orientation == 1;
        ((LayoutParams) this.q.getLayoutParams()).width = z2 ? d : -1;
        this.r.setOrientation(z2 ? 1 : 0);
        if (this.e.j().size() < 3 || z2) {
            z = false;
        }
        for (int i2 = 0; i2 < this.r.getChildCount(); i2++) {
            com.facebook.ads.internal.view.g.b bVar = (com.facebook.ads.internal.view.g.b) this.r.getChildAt(i2);
            bVar.b(z2);
            bVar.setShouldPlayButtonOnTop(z);
        }
    }

    public void onDestroy() {
        this.k.b();
        com.facebook.ads.internal.x.a aVar = this.i;
        if (aVar != null) {
            aVar.c();
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.h.a(motionEvent, this, this);
        if (motionEvent.getAction() == 1) {
            HashMap hashMap = new HashMap();
            this.i.a((Map<String, String>) hashMap);
            hashMap.put("touch", k.a(this.h.e()));
            hashMap.put("is_cyoa", Boolean.TRUE.toString());
            this.g.d(((q) this.e.j().get(0)).a(), hashMap);
        }
        return true;
    }

    /* access modifiers changed from: 0000 */
    public void setIsAdReportingLayoutVisible(boolean z) {
        this.n = z;
    }

    public void setListener(C0012a aVar) {
    }
}
