package com.facebook.ads.internal.view.component.a;

import android.content.res.Resources;
import android.widget.FrameLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.internal.adapters.b.h;
import com.facebook.ads.internal.adapters.b.l;

public class f extends c {
    private static final int c = Resources.getSystem().getDisplayMetrics().widthPixels;
    private final k d;

    public f(e eVar, boolean z, h hVar) {
        super(eVar, hVar, z);
        this.d = new k(eVar.a(), eVar.d());
        this.d.a(eVar.h(), eVar.i(), 10, getTitleDescContainer(), z);
        LayoutParams layoutParams = new LayoutParams(-1, -2);
        layoutParams.addRule(12);
        layoutParams.setMargins(a, a, a, a);
        getCtaButton().setLayoutParams(layoutParams);
        FrameLayout frameLayout = new FrameLayout(eVar.a());
        LayoutParams layoutParams2 = new LayoutParams(-1, -1);
        layoutParams2.addRule(2, getCtaButton().getId());
        frameLayout.setLayoutParams(layoutParams2);
        FrameLayout.LayoutParams layoutParams3 = new FrameLayout.LayoutParams(-1, -2);
        layoutParams3.gravity = 17;
        layoutParams3.setMargins(a, 0, a, 0);
        frameLayout.addView(this.d, layoutParams3);
        addView(frameLayout);
        addView(getCtaButton());
    }

    public void a(l lVar, String str, double d2) {
        super.a(lVar, str, d2);
        if (d2 > 0.0d) {
            this.d.a((int) (((double) (c - (a * 2))) / d2));
        }
    }

    public boolean a() {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean c() {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean e() {
        return false;
    }
}
