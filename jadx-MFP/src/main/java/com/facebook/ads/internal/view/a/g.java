package com.facebook.ads.internal.view.a;

import android.content.Context;
import android.os.Build.VERSION;
import android.transition.ChangeBounds;
import android.transition.Explode;
import android.transition.Transition;
import android.transition.TransitionSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.internal.f.b;
import com.facebook.ads.internal.s.c;
import com.facebook.ads.internal.view.a;
import com.facebook.ads.internal.view.a.C0012a;
import com.facebook.ads.internal.view.a.a.C0013a;
import com.facebook.ads.internal.w.b.x;

public class g extends c {
    private static final int c = ((int) (x.b * 8.0f));
    private final RelativeLayout d = new RelativeLayout(getContext());

    g(Context context, c cVar, String str, a aVar, C0012a aVar2) {
        super(context, cVar, str, aVar, aVar2);
        addView(this.d, new LayoutParams(-1, -1));
        x.a((View) this.d, -1728053248);
        this.d.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                g.this.b.a(false);
            }
        });
    }

    private static LayoutParams b(boolean z) {
        LayoutParams layoutParams = new LayoutParams(-1, z ? -1 : -2);
        layoutParams.addRule(12);
        return layoutParams;
    }

    private void f() {
        if (VERSION.SDK_INT >= 21) {
            TransitionSet transitionSet = new TransitionSet();
            transitionSet.setOrdering(0);
            transitionSet.addTransition(new ChangeBounds()).addTransition(new Explode());
            x.a((ViewGroup) this, (Transition) transitionSet);
            return;
        }
        x.a((ViewGroup) this);
    }

    public void a(com.facebook.ads.internal.f.c cVar, b.a aVar) {
        boolean z = aVar == b.a.REPORT;
        j jVar = new j(getContext(), cVar, this.b, z ? com.facebook.ads.internal.f.a.e(getContext()) : com.facebook.ads.internal.f.a.b(getContext()), z ? com.facebook.ads.internal.w.c.b.REPORT_AD : com.facebook.ads.internal.w.c.b.HIDE_AD);
        jVar.setClickable(true);
        x.a((View) jVar, -1);
        int i = c;
        jVar.setPadding(i * 2, i, i * 2, i);
        f();
        this.d.removeAllViews();
        this.d.addView(jVar, b(false));
    }

    public void b(com.facebook.ads.internal.f.c cVar, b.a aVar) {
        if (aVar != b.a.NONE) {
            boolean z = aVar == b.a.REPORT;
            a a = new C0013a(getContext()).a(this.b).a(z ? com.facebook.ads.internal.f.a.j(getContext()) : com.facebook.ads.internal.f.a.i(getContext())).b(com.facebook.ads.internal.f.a.k(getContext())).c(cVar.b()).a(z ? com.facebook.ads.internal.w.c.b.REPORT_AD : com.facebook.ads.internal.w.c.b.HIDE_AD).a(z ? -552389 : -13272859).d(this.a).a();
            x.a((View) a, -1);
            x.a((ViewGroup) this);
            this.d.removeAllViews();
            this.d.addView(a, b(true));
        }
    }

    public void c() {
        x.c(this);
        this.d.removeAllViews();
        x.b(this);
    }

    public void d() {
        com.facebook.ads.internal.f.c d2 = com.facebook.ads.internal.f.a.d(getContext());
        i iVar = new i(getContext());
        iVar.a(com.facebook.ads.internal.w.c.b.HIDE_AD, com.facebook.ads.internal.f.a.b(getContext()), com.facebook.ads.internal.f.a.c(getContext()));
        iVar.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                g.this.b.a(b.a.HIDE);
            }
        });
        com.facebook.ads.internal.f.c g = com.facebook.ads.internal.f.a.g(getContext());
        i iVar2 = new i(getContext());
        iVar2.a(com.facebook.ads.internal.w.c.b.REPORT_AD, com.facebook.ads.internal.f.a.e(getContext()), com.facebook.ads.internal.f.a.f(getContext()));
        iVar2.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                g.this.b.a(b.a.REPORT);
            }
        });
        i iVar3 = new i(getContext());
        iVar3.a(com.facebook.ads.internal.w.c.b.AD_CHOICES_ICON, com.facebook.ads.internal.f.a.l(getContext()), "");
        iVar3.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                g.this.b.d();
            }
        });
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setClickable(true);
        linearLayout.setOrientation(1);
        int i = c;
        linearLayout.setPadding(i * 2, i, i * 2, i);
        x.a((View) linearLayout, -1);
        if (!d2.d().isEmpty()) {
            linearLayout.addView(iVar, layoutParams);
        }
        if (!g.d().isEmpty()) {
            linearLayout.addView(iVar2, layoutParams);
        }
        linearLayout.addView(iVar3, layoutParams);
        f();
        this.d.removeAllViews();
        this.d.addView(linearLayout, b(false));
    }

    /* access modifiers changed from: 0000 */
    public boolean e() {
        return false;
    }
}
