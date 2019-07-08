package com.facebook.ads.internal.view.component.a;

import android.view.View;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.adapters.b.h;
import com.facebook.ads.internal.adapters.b.l;
import com.facebook.ads.internal.view.component.a;
import com.facebook.ads.internal.view.component.j;
import com.facebook.ads.internal.w.b.x;
import java.util.HashMap;

public abstract class c extends RelativeLayout {
    static final int a = ((int) (x.b * 16.0f));
    static final int b = ((int) (x.b * 28.0f));
    private final j c;
    private final a d;
    private final com.facebook.ads.internal.s.c e;

    protected c(e eVar, h hVar, boolean z) {
        super(eVar.a());
        this.e = eVar.b();
        a aVar = new a(eVar.a(), d(), e(), "com.facebook.ads.interstitial.clicked", hVar, eVar.b(), eVar.c(), eVar.e(), eVar.f());
        this.d = aVar;
        x.a((View) this.d);
        j jVar = new j(getContext(), hVar, z, b(), c());
        this.c = jVar;
        x.a((View) this.c);
    }

    public void a(l lVar, String str, double d2) {
        this.c.a(lVar.a().b(), lVar.a().c(), null, false, !a() && d2 > 0.0d && d2 < 1.0d);
        this.d.a(lVar.b(), str, new HashMap());
    }

    public abstract boolean a();

    /* access modifiers changed from: protected */
    public boolean b() {
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean c() {
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean d() {
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean e() {
        return true;
    }

    public com.facebook.ads.internal.s.c getAdEventManager() {
        return this.e;
    }

    /* access modifiers changed from: protected */
    public a getCtaButton() {
        return this.d;
    }

    public int getExactMediaHeightIfAvailable() {
        return 0;
    }

    public int getExactMediaWidthIfAvailable() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public j getTitleDescContainer() {
        return this.c;
    }
}
