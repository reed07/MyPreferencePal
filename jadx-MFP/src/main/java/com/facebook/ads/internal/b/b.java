package com.facebook.ads.internal.b;

import android.content.Context;
import android.view.View;
import com.facebook.ads.AdError;
import com.facebook.ads.internal.adapters.AdAdapter;
import com.facebook.ads.internal.adapters.BannerAdapterListener;
import com.facebook.ads.internal.adapters.e;
import com.facebook.ads.internal.m.a;
import com.facebook.ads.internal.m.c;
import java.util.Map;

public class b extends c {
    public b(Context context, a aVar) {
        super(context, aVar);
    }

    /* access modifiers changed from: protected */
    public void a() {
        if (this.d != null) {
            this.c.a(this.d);
        }
    }

    /* access modifiers changed from: protected */
    public void a(AdAdapter adAdapter, c cVar, a aVar, Map<String, Object> map) {
        final e eVar = (e) adAdapter;
        final AnonymousClass1 r7 = new Runnable() {
            public void run() {
                b.this.a((AdAdapter) eVar);
                b.this.i();
            }
        };
        j().postDelayed(r7, (long) cVar.a().j());
        eVar.a(this.b, this.g, this.h.c, new BannerAdapterListener() {
            public void onBannerAdClicked(e eVar) {
                b.this.c.a();
            }

            public void onBannerAdLoaded(e eVar, View view) {
                if (eVar == b.this.e) {
                    b.this.j().removeCallbacks(r7);
                    AdAdapter adAdapter = b.this.f;
                    b bVar = b.this;
                    bVar.f = eVar;
                    bVar.d = view;
                    if (!bVar.a) {
                        b.this.c.a((AdAdapter) eVar);
                    } else {
                        b.this.c.a(view);
                        b.this.a(adAdapter);
                    }
                }
            }

            public void onBannerError(e eVar, AdError adError) {
                if (eVar == b.this.e) {
                    b.this.j().removeCallbacks(r7);
                    b.this.a((AdAdapter) eVar);
                    b.this.i();
                }
            }

            public void onBannerLoggingImpression(e eVar) {
                b.this.c.b();
            }
        }, map);
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
