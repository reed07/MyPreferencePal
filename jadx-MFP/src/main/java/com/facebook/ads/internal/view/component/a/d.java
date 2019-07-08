package com.facebook.ads.internal.view.component.a;

import android.net.Uri;
import com.facebook.ads.internal.a.b;
import com.facebook.ads.internal.a.c;
import com.facebook.ads.internal.a.j;
import com.facebook.ads.internal.adapters.b.h;
import com.facebook.ads.internal.adapters.b.l;
import java.util.HashMap;

public final class d {
    public static c a(e eVar) {
        c cVar;
        boolean z = true;
        h a = eVar.k() == 1 ? eVar.g().b().a() : eVar.g().b().b();
        l lVar = (l) eVar.g().d().get(0);
        double a2 = (double) a.a(lVar);
        boolean d = ((l) eVar.g().d().get(0)).d();
        boolean a3 = a.a(eVar.k(), eVar.j(), a2);
        b a4 = c.a(eVar.a(), eVar.b(), "", Uri.parse(((l) eVar.g().d().get(0)).b().a()), new HashMap());
        if (d && a4 != null && (a4 instanceof j)) {
            cVar = eVar.k() == 1 ? new j(eVar, a) : new i(eVar, a);
        } else if (a3) {
            if (eVar.k() != 2) {
                z = false;
            }
            cVar = new b(eVar, a, z);
        } else {
            cVar = new f(eVar, a.a(a2), a);
        }
        cVar.a(lVar, eVar.g().c(), a2);
        return cVar;
    }
}
