package com.facebook.ads.internal.view.component.a;

import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.facebook.ads.internal.adapters.b.h;
import com.facebook.ads.internal.adapters.b.l;
import com.facebook.ads.internal.view.c.d;
import com.facebook.ads.internal.view.component.a;
import com.facebook.ads.internal.view.component.a.C0016a;
import com.facebook.ads.internal.view.component.f;
import com.facebook.ads.internal.view.component.j;
import com.facebook.ads.internal.w.b.x;
import java.util.HashMap;
import java.util.Map;

public class n extends LinearLayout {
    public static final int a = ((int) (x.b * 275.0f));
    private static final int b = ((int) (x.b * 56.0f));
    private static final int c = ((int) (x.b * 4.0f));
    private static final int d = ((int) (x.b * 8.0f));
    private static final int e = ((int) (x.b * 16.0f));
    private static final int f = ((int) (x.b * 20.0f));
    private final j g;
    private final f h;
    @Nullable
    private m i;

    public n(e eVar, h hVar, C0016a aVar) {
        C0016a aVar2 = aVar;
        super(eVar.a());
        setOrientation(1);
        setGravity(17);
        this.h = new f(eVar.a());
        this.h.setFullCircleCorners(true);
        setupIconView(eVar);
        int i2 = b;
        LayoutParams layoutParams = new LayoutParams(i2, i2);
        addView(this.h, layoutParams);
        layoutParams.bottomMargin = c;
        j jVar = new j(getContext(), hVar, true, true, false);
        this.g = jVar;
        x.a((View) this.g);
        this.g.setTitleGravity(17);
        this.g.setDescriptionGravity(17);
        this.g.a(true, 17);
        LayoutParams layoutParams2 = new LayoutParams(-1, -2);
        int i3 = e;
        layoutParams2.setMargins(i3, 0, i3, c);
        addView(this.g, layoutParams2);
        x.a((View) this.g);
        LayoutParams layoutParams3 = new LayoutParams(-2, -2);
        layoutParams3.topMargin = f;
        layoutParams3.bottomMargin = c;
        if (eVar.k() == 1) {
            this.i = new m(eVar, ((l) eVar.g().d().get(0)).b().b(), hVar, aVar2);
            addView(this.i, layoutParams3);
            return;
        }
        e eVar2 = eVar;
        h hVar2 = new h();
        hVar2.a(654311423);
        a aVar3 = new a(eVar.a(), true, false, "com.facebook.ads.interstitial.clicked", hVar2, eVar.b(), eVar.c(), eVar.e(), eVar.f());
        aVar3.a(((l) eVar.g().d().get(0)).b(), eVar.g().c(), (Map<String, String>) new HashMap<String,String>(), aVar2);
        int i4 = d;
        int i5 = c;
        aVar3.setPadding(i4, i5, i4, i5);
        aVar3.setBackgroundColor(0);
        aVar3.setTextColor(-1);
        aVar3.setTypeface(Typeface.defaultFromStyle(1));
        addView(aVar3, layoutParams3);
    }

    private void setupIconView(e eVar) {
        d dVar = new d((ImageView) this.h);
        int i2 = b;
        dVar.a(i2, i2);
        dVar.a(eVar.g().a().b());
    }

    public void a(String str, String str2, String str3, boolean z, boolean z2) {
        this.g.a(str, str2, str3, z, z2);
    }

    @Nullable
    public m getSwipeUpCtaButton() {
        return this.i;
    }
}
