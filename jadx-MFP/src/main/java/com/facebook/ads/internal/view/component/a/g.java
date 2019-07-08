package com.facebook.ads.internal.view.component.a;

import android.net.Uri;
import com.facebook.ads.internal.a.b;
import com.facebook.ads.internal.a.c;
import com.facebook.ads.internal.a.i;
import com.facebook.ads.internal.adapters.b.h;
import com.facebook.ads.internal.adapters.b.l;
import java.util.HashMap;

public class g {
    public static l a(e eVar, int i, int i2, boolean z) {
        h a = eVar.k() == 1 ? eVar.g().b().a() : eVar.g().b().b();
        l lVar = (l) eVar.g().d().get(0);
        b a2 = c.a(eVar.a(), eVar.b(), eVar.g().c(), Uri.parse(((l) eVar.g().d().get(0)).b().a()), new HashMap(), false, true);
        if (!((l) eVar.g().d().get(0)).d() || !(a2 instanceof i)) {
            return null;
        }
        h hVar = new h(eVar, a, i, i2, (i) a2, z);
        hVar.a(lVar);
        return hVar;
    }
}
