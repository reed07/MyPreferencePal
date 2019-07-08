package com.facebook.ads.internal.view.g;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.facebook.ads.internal.adapters.b.q;
import com.facebook.ads.internal.s.c;
import com.facebook.ads.internal.view.a.C0012a;
import com.facebook.ads.internal.view.a.a.C0013a;
import com.facebook.ads.internal.view.c.d;
import com.facebook.ads.internal.view.c.e;
import com.facebook.ads.internal.view.component.f;
import com.facebook.ads.internal.view.component.g;
import com.facebook.ads.internal.view.component.k;
import com.facebook.ads.internal.view.i.c.l;
import com.facebook.ads.internal.w.b.w;
import com.facebook.ads.internal.w.b.x;
import com.facebook.ads.internal.x.a;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.HashMap;
import java.util.Map;

public class b extends g {
    private static final int b = ((int) (x.b * 48.0f));
    private static final int c = ((int) (x.b * 40.0f));
    private static final int d = ((int) (x.b * 16.0f));
    private static final int e = ((int) (x.b * 56.0f));
    private static final int f = ((int) (x.b * 200.0f));
    private q g;
    private final c h;
    private final a i;
    private final w j;
    private final Map<String, String> k = new HashMap();
    private RelativeLayout l;
    private k m;
    private ImageView n;
    private l o;
    private ObjectAnimator p;
    private boolean q;
    /* access modifiers changed from: private */
    public boolean r;
    private boolean s;
    private boolean t = false;
    private boolean u;
    @Nullable
    private com.facebook.ads.internal.f.c v;
    @Nullable
    private com.facebook.ads.internal.f.b.a w;

    public b(Context context, q qVar, c cVar, a aVar, w wVar, C0012a aVar2) {
        super(context);
        this.g = qVar;
        this.h = cVar;
        this.i = aVar;
        this.j = wVar;
        this.n = new f(context);
        this.o = new l(context, true);
        this.o.setClickable(false);
        this.n.setScaleType(ScaleType.CENTER_CROP);
        addView(this.n, new LayoutParams(-1, -1));
        new d(this.n).a().a((e) new e() {
            public void a(boolean z) {
                b.this.r = z;
                b.this.e();
            }
        }).a(this.g.j().g());
        String a = qVar.a();
        RelativeLayout relativeLayout = new RelativeLayout(getContext());
        int i2 = d;
        relativeLayout.setPadding(i2, i2, i2, i2);
        relativeLayout.setLayoutParams(new LayoutParams(-1, -1));
        addView(relativeLayout);
        GradientDrawable gradientDrawable = new GradientDrawable(Orientation.TOP_BOTTOM, new int[]{0, -872415232});
        gradientDrawable.setCornerRadius(BitmapDescriptorFactory.HUE_RED);
        x.a((View) relativeLayout, (Drawable) gradientDrawable);
        this.l = new RelativeLayout(getContext());
        x.a((View) this.l);
        LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.addRule(12);
        relativeLayout.addView(this.l, layoutParams);
        this.m = new k(getContext(), a, this.g.g().f(), aVar2);
        this.m.a(this.g.g().a(), true, 22, -1);
        this.m.b(this.g.g().d(), false, 14, -1);
        this.m.c(this.g.g().g(), false, 14, -1);
        this.m.d(this.g.g().e(), false, 14, -1);
        this.l.addView(this.m, new LayoutParams(-1, -2));
        f fVar = new f(getContext());
        int i3 = b;
        LayoutParams layoutParams2 = new LayoutParams(i3, i3);
        layoutParams2.addRule(2, this.l.getId());
        fVar.setLayoutParams(layoutParams2);
        fVar.setFullCircleCorners(this.g.g().f().equals(com.facebook.ads.internal.adapters.b.e.a.PAGE_POST));
        relativeLayout.addView(fVar);
        d dVar = new d((ImageView) fVar);
        int i4 = b;
        dVar.a(i4, i4).a(this.g.f().b());
        c(this.s);
    }

    private void b(com.facebook.ads.internal.f.c cVar, com.facebook.ads.internal.f.b.a aVar) {
        View view;
        String str;
        int i2;
        com.facebook.ads.internal.w.c.b bVar;
        if (getWidth() >= f && getHeight() >= f) {
            if (aVar == com.facebook.ads.internal.f.b.a.REPORT) {
                str = com.facebook.ads.internal.f.a.j(getContext());
                bVar = com.facebook.ads.internal.w.c.b.REPORT_AD;
                i2 = -552389;
            } else {
                str = com.facebook.ads.internal.f.a.i(getContext());
                bVar = com.facebook.ads.internal.w.c.b.HIDE_AD;
                i2 = -13272859;
            }
            view = new C0013a(getContext()).a(str).b(com.facebook.ads.internal.f.a.k(getContext())).c(cVar.b()).a(false).a(bVar).a(i2).b(false).c(false).e(false).a();
        } else {
            view = getAdHiddenViewTextOnly();
        }
        x.a(view, -1);
        LayoutParams layoutParams = new LayoutParams(-1, -1);
        layoutParams.addRule(13);
        view.setLayoutParams(layoutParams);
        removeAllViews();
        addView(view);
    }

    private void c(boolean z) {
        LayoutParams layoutParams = (LayoutParams) this.m.getLayoutParams();
        int i2 = c;
        LayoutParams layoutParams2 = new LayoutParams(i2, i2);
        if (z) {
            layoutParams.rightMargin = 0;
            int i3 = d;
            layoutParams2.topMargin = i3;
            layoutParams2.rightMargin = i3;
            layoutParams2.addRule(11);
            layoutParams2.addRule(10);
            addView(this.o, layoutParams2);
            return;
        }
        layoutParams.rightMargin = e;
        layoutParams2.addRule(15);
        layoutParams2.addRule(11);
        this.l.addView(this.o, layoutParams2);
    }

    /* access modifiers changed from: private */
    public void e() {
        if (this.q && this.r) {
            this.i.a(this.k);
            this.k.put("touch", com.facebook.ads.internal.w.b.k.a(this.j.e()));
            this.k.put("is_cyoa", Boolean.TRUE.toString());
            this.h.o(this.g.a(), this.k);
        }
    }

    private View getAdHiddenViewTextOnly() {
        TextView textView = new TextView(getContext());
        x.a(textView, true, 14);
        textView.setText(com.facebook.ads.internal.f.a.k(getContext()));
        textView.setGravity(17);
        return textView;
    }

    public void a(int i2) {
        this.k.put("ad_intro_position", String.valueOf(i2));
    }

    public void a(com.facebook.ads.internal.f.c cVar, com.facebook.ads.internal.f.b.a aVar) {
        this.t = true;
        this.v = cVar;
        this.w = aVar;
        b(cVar, aVar);
    }

    public void a(boolean z) {
        this.m.a(z);
    }

    public void a(boolean z, int i2) {
        ObjectAnimator objectAnimator = this.p;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
        float f2 = 1.01f;
        float f3 = z ? 1.01f : 0.99f;
        if (z) {
            f2 = 0.99f;
        }
        this.p = ObjectAnimator.ofPropertyValuesHolder(this, new PropertyValuesHolder[]{PropertyValuesHolder.ofFloat("scaleX", new float[]{f3, f2}), PropertyValuesHolder.ofFloat("scaleY", new float[]{f3, f2})});
        this.p.setInterpolator(new FastOutLinearInInterpolator());
        this.p.setDuration((long) i2);
        this.p.setRepeatCount(-1);
        this.p.setRepeatMode(2);
        this.p.start();
        this.u = false;
    }

    public boolean a() {
        return this.t;
    }

    public void b() {
        if (this.p != null && !this.u) {
            if (VERSION.SDK_INT >= 19) {
                this.p.pause();
            } else {
                this.p.cancel();
            }
        }
    }

    public void b(boolean z) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getLayoutParams();
        int i2 = -1;
        layoutParams.width = z ? -1 : 0;
        if (z) {
            i2 = 0;
        }
        layoutParams.height = i2;
    }

    public void c() {
        if (this.p != null && !this.u) {
            if (VERSION.SDK_INT >= 19) {
                this.p.resume();
            } else {
                this.p.start();
            }
        }
    }

    public void d() {
        ObjectAnimator objectAnimator = this.p;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
        this.u = true;
    }

    public q getAdDataBundle() {
        return this.g;
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        if (z && a()) {
            removeAllViews();
            b(this.v, this.w);
        }
    }

    public void setAdReportingFlowListener(com.facebook.ads.internal.view.a.b bVar) {
        this.m.setAdReportingFlowListener(bVar);
    }

    public void setShouldPlayButtonOnTop(boolean z) {
        if (z != this.s) {
            this.s = z;
            x.b(this.o);
            c(this.s);
        }
    }

    public void setViewability(boolean z) {
        this.q = z;
        e();
    }
}
