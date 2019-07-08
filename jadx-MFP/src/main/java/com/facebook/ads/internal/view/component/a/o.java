package com.facebook.ads.internal.view.component.a;

import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.facebook.ads.internal.adapters.b.h;
import com.facebook.ads.internal.adapters.b.l;
import com.facebook.ads.internal.view.c.d;
import com.facebook.ads.internal.view.component.a.C0016a;
import com.facebook.ads.internal.w.b.x;

class o extends FrameLayout {
    final n a;

    public o(e eVar, h hVar, int i, int i2, C0016a aVar) {
        super(eVar.a());
        boolean z = true;
        if (eVar.k() == 1) {
            z = false;
        }
        new d(this, 12).a(z ? x.a.heightPixels : i, z ? i2 : x.a.widthPixels).a(((l) eVar.g().d().get(0)).c().g());
        FrameLayout frameLayout = new FrameLayout(eVar.a());
        addView(frameLayout, new LayoutParams(-1, -1));
        frameLayout.setBackgroundColor(-433903825);
        FrameLayout frameLayout2 = new FrameLayout(eVar.a());
        if (!z) {
            i2 = -1;
        }
        if (z) {
            i = -1;
        }
        LayoutParams layoutParams = new LayoutParams(i2, i);
        layoutParams.gravity = 48;
        addView(frameLayout2, layoutParams);
        this.a = new n(eVar, hVar, aVar);
        LayoutParams layoutParams2 = new LayoutParams(-1, -2);
        layoutParams2.gravity = 17;
        frameLayout2.addView(this.a, layoutParams2);
    }

    public void a(String str, String str2, String str3, boolean z, boolean z2) {
        this.a.a(str, str2, str3, z, z2);
    }
}
