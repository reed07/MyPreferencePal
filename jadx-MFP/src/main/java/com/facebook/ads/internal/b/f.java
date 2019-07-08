package com.facebook.ads.internal.b;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.facebook.ads.AdError;
import com.facebook.ads.internal.adapters.AdAdapter;
import com.facebook.ads.internal.adapters.InterstitialAdapterListener;
import com.facebook.ads.internal.adapters.g;
import com.facebook.ads.internal.m.a;
import com.facebook.ads.internal.m.c;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.ads.internal.w.h.b;
import com.google.android.exoplayer2.C;
import java.util.Map;

public class f extends c {
    public f(Context context, a aVar) {
        super(context, aVar);
    }

    /* access modifiers changed from: protected */
    public void a() {
        ((g) this.f).a();
    }

    /* access modifiers changed from: protected */
    public void a(AdAdapter adAdapter, c cVar, a aVar, Map<String, Object> map) {
        final g gVar = (g) adAdapter;
        final AnonymousClass1 r8 = new Runnable() {
            public void run() {
                f.this.a((AdAdapter) gVar);
                if (com.facebook.ads.internal.r.a.ac(f.this.b)) {
                    f fVar = f.this;
                    fVar.e = null;
                    fVar.c.a(new com.facebook.ads.internal.protocol.a(AdErrorType.INTERSTITIAL_AD_TIMEOUT, ""));
                    return;
                }
                f.this.i();
            }
        };
        j().postDelayed(r8, (long) cVar.a().j());
        gVar.a(this.b, new InterstitialAdapterListener() {
            public void onInterstitialActivityDestroyed() {
                f.this.c.f();
            }

            public void onInterstitialAdClicked(g gVar, String str, boolean z) {
                f.this.c.a();
                boolean z2 = !TextUtils.isEmpty(str);
                if (z && z2) {
                    Intent intent = new Intent("android.intent.action.VIEW");
                    if (!(f.this.b instanceof Activity)) {
                        intent.addFlags(C.ENCODING_PCM_MU_LAW);
                    }
                    intent.setData(Uri.parse(str));
                    f.this.b.startActivity(intent);
                }
            }

            public void onInterstitialAdDismissed(g gVar) {
                f.this.c.d();
            }

            public void onInterstitialAdDisplayed(g gVar) {
                f.this.c.e();
            }

            public void onInterstitialAdLoaded(g gVar) {
                if (gVar == f.this.e) {
                    if (gVar == null) {
                        com.facebook.ads.internal.w.h.a.b(f.this.b, "api", b.b, new com.facebook.ads.internal.protocol.b(AdErrorType.NO_ADAPTER_ON_LOAD, "Adapter is null on loadInterstitialAd"));
                        onInterstitialError(gVar, AdError.internalError(AdError.INTERNAL_ERROR_2004));
                        return;
                    }
                    f.this.j().removeCallbacks(r8);
                    f fVar = f.this;
                    fVar.f = gVar;
                    fVar.c.a((AdAdapter) gVar);
                }
            }

            public void onInterstitialError(g gVar, AdError adError) {
                if (gVar == f.this.e) {
                    f.this.j().removeCallbacks(r8);
                    f.this.a((AdAdapter) gVar);
                    if (!com.facebook.ads.internal.r.a.ac(f.this.b)) {
                        f.this.i();
                    }
                    f.this.c.a(new com.facebook.ads.internal.protocol.a(adError.getErrorCode(), adError.getErrorMessage()));
                }
            }

            public void onInterstitialLoggingImpression(g gVar) {
                f.this.c.b();
            }
        }, map, this.g, this.h.d, this.h.e);
    }
}
