package com.facebook.ads.internal.view.component.a;

import android.content.res.Resources;
import android.widget.FrameLayout.LayoutParams;
import com.facebook.ads.internal.adapters.b.h;
import com.facebook.ads.internal.adapters.b.l;
import com.facebook.ads.internal.w.b.x;

class j extends c {
    private static final int c = Resources.getSystem().getDisplayMetrics().widthPixels;
    private final k d;
    private int e;

    public j(e eVar, h hVar) {
        super(eVar, hVar, true);
        this.d = new k(eVar.a(), eVar.d());
        this.d.a(eVar.h(), eVar.i());
        LayoutParams layoutParams = new LayoutParams(-1, -2);
        layoutParams.gravity = 17;
        addView(this.d, layoutParams);
    }

    public void a(l lVar, String str, double d2) {
        super.a(lVar, str, d2);
        if (d2 > 0.0d) {
            int i = (int) (((double) (c - (a * 2))) / d2);
            if (x.a.heightPixels - i < n.a) {
                i = x.a.heightPixels - n.a;
            }
            this.d.a(i);
            this.e = i;
        }
    }

    public boolean a() {
        return true;
    }

    public int getExactMediaHeightIfAvailable() {
        return this.e;
    }
}
