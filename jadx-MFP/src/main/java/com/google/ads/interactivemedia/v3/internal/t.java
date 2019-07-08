package com.google.ads.interactivemedia.v3.internal;

import android.view.View;
import java.util.ArrayList;
import java.util.Collection;
import java.util.IdentityHashMap;
import org.json.JSONObject;

/* compiled from: IMASDK */
public final class t implements q {
    private final q a;

    public t(q qVar) {
        this.a = qVar;
    }

    public final JSONObject a(View view) {
        return z.a(0, 0, 0, 0);
    }

    public final void a(View view, JSONObject jSONObject, r rVar, boolean z) {
        ArrayList arrayList = new ArrayList();
        j a2 = j.a();
        if (a2 != null) {
            Collection<e> c = a2.c();
            IdentityHashMap identityHashMap = new IdentityHashMap((c.size() << 1) + 3);
            for (e g : c) {
                View g2 = g.g();
                if (g2 != null && ho.c(g2)) {
                    View rootView = g2.getRootView();
                    if (rootView != null && !identityHashMap.containsKey(rootView)) {
                        identityHashMap.put(rootView, rootView);
                        float a3 = ho.a(rootView);
                        int size = arrayList.size();
                        while (size > 0 && ho.a((View) arrayList.get(size - 1)) > a3) {
                            size--;
                        }
                        arrayList.add(size, rootView);
                    }
                }
            }
        }
        ArrayList arrayList2 = arrayList;
        int size2 = arrayList2.size();
        int i = 0;
        while (i < size2) {
            Object obj = arrayList2.get(i);
            i++;
            rVar.a((View) obj, this.a, jSONObject);
        }
    }
}
