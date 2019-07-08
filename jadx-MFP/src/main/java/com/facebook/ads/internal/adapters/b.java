package com.facebook.ads.internal.adapters;

import android.content.Context;
import com.facebook.ads.internal.w.b.c;
import com.facebook.ads.internal.x.a;
import java.util.HashMap;
import java.util.Map;

public abstract class b {
    protected final c a;
    protected final a b;
    private final Context c;
    private boolean d;

    public b(Context context, c cVar, a aVar) {
        this.c = context;
        this.a = cVar;
        this.b = aVar;
    }

    public final void a() {
        if (!this.d) {
            c cVar = this.a;
            if (cVar != null) {
                cVar.a();
            }
            HashMap hashMap = new HashMap();
            a aVar = this.b;
            if (aVar != null) {
                aVar.a((Map<String, String>) hashMap);
            }
            a(hashMap);
            this.d = true;
            c.a(this.c, "Impression logged");
            c cVar2 = this.a;
            if (cVar2 != null) {
                cVar2.b();
            }
        }
    }

    /* access modifiers changed from: protected */
    public abstract void a(Map<String, String> map);
}
