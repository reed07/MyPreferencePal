package com.facebook.ads.internal.b;

import android.content.Context;
import android.view.View;
import com.facebook.ads.AdError;
import com.facebook.ads.internal.adapters.AdAdapter;
import com.facebook.ads.internal.adapters.f;
import com.facebook.ads.internal.adapters.n;
import com.facebook.ads.internal.m.a;
import com.facebook.ads.internal.m.c;
import java.util.Map;

public class d extends c {
    public d(Context context, a aVar) {
        super(context, aVar);
    }

    /* access modifiers changed from: protected */
    public void a() {
        ((n) this.f).e();
    }

    /* access modifiers changed from: protected */
    public void a(AdAdapter adAdapter, c cVar, a aVar, Map<String, Object> map) {
        ((f) adAdapter).a(this.b, (com.facebook.ads.a.a) new com.facebook.ads.a.a() {
            public void a(n nVar) {
                d dVar = d.this;
                dVar.f = nVar;
                dVar.a = false;
                dVar.c.a((AdAdapter) nVar);
            }

            public void a(n nVar, View view) {
                d.this.c.a(view);
            }

            public void a(n nVar, AdError adError) {
                d.this.c.a(new com.facebook.ads.internal.protocol.a(adError.getErrorCode(), adError.getErrorMessage()));
            }

            public void b(n nVar) {
                d.this.c.a();
            }

            public void c(n nVar) {
                d.this.c.b();
            }

            public void d(n nVar) {
                d.this.c.c();
            }
        }, map, this.g, this.h.d);
    }

    /* access modifiers changed from: protected */
    public void a(String str) {
        com.facebook.ads.internal.protocol.a a = e.a(this.b, Integer.valueOf(0));
        if (a != null) {
            a(a);
        } else {
            super.a(str);
        }
    }
}
