package com.facebook.ads.internal.c;

import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import com.facebook.ads.AdError;
import com.facebook.ads.CacheFlag;
import com.facebook.ads.InterstitialAdExtendedListener;
import com.facebook.ads.internal.adapters.AdAdapter;
import com.facebook.ads.internal.adapters.a;
import com.facebook.ads.internal.b.f;
import com.facebook.ads.internal.c.a.b;
import com.facebook.ads.internal.c.a.c;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.ads.internal.protocol.AdPlacementType;
import java.util.EnumSet;

public class d implements c {
    private static final String a = "d";
    /* access modifiers changed from: private */
    public f b;
    /* access modifiers changed from: private */
    public boolean c;
    /* access modifiers changed from: private */
    public boolean d;
    /* access modifiers changed from: private */
    public final g e;
    /* access modifiers changed from: private */
    public final InterstitialAdExtendedListener f;

    public d(g gVar, c cVar, String str) {
        this.e = gVar;
        this.f = new b(str, cVar, this);
    }

    public void a() {
        f fVar = this.b;
        if (fVar != null) {
            fVar.a((a) new a() {
            });
            this.b.a(true);
            this.b = null;
            this.c = false;
            this.d = false;
        }
    }

    public void a(EnumSet<CacheFlag> enumSet, @Nullable String str) {
        if (!this.c && this.b != null) {
            Log.w(a, "An ad load is already in progress. You should wait for adLoaded() to be called");
        }
        this.c = false;
        if (this.d) {
            com.facebook.ads.internal.w.h.a.b(this.e.a, "api", com.facebook.ads.internal.w.h.b.f, new com.facebook.ads.internal.protocol.b(AdErrorType.NO_ADAPTER_ON_LOAD, "Interstitial load called while showing interstitial."));
            this.f.onError(this.e.a(), new AdError(AdErrorType.LOAD_CALLED_WHILE_SHOWING_AD.getErrorCode(), AdErrorType.LOAD_CALLED_WHILE_SHOWING_AD.getDefaultErrorMessage()));
            return;
        }
        f fVar = this.b;
        if (fVar != null) {
            fVar.a((a) new a() {
            });
            this.b.f();
            this.b = null;
        }
        com.facebook.ads.internal.b.a aVar = new com.facebook.ads.internal.b.a(this.e.b, com.facebook.ads.internal.protocol.f.a(this.e.a.getResources().getDisplayMetrics()), AdPlacementType.INTERSTITIAL, com.facebook.ads.internal.protocol.d.INTERSTITIAL, 1, enumSet);
        aVar.a(this.e.d);
        this.b = new f(this.e.a, aVar);
        this.b.a((a) new a() {
            public void a() {
                d.this.f.onAdClicked(d.this.e.a());
            }

            public void a(View view) {
            }

            public void a(AdAdapter adAdapter) {
                d.this.c = true;
                d.this.f.onAdLoaded(d.this.e.a());
            }

            public void a(com.facebook.ads.internal.protocol.a aVar) {
                d.this.f.onError(d.this.e.a(), AdError.getAdErrorFromWrapper(aVar));
            }

            public void b() {
                d.this.f.onLoggingImpression(d.this.e.a());
            }

            public void d() {
                d.this.d = false;
                if (d.this.b != null) {
                    d.this.b.a((a) new a() {
                    });
                    d.this.b.f();
                    d.this.b = null;
                }
                d.this.f.onInterstitialDismissed(d.this.e.a());
            }

            public void e() {
                d.this.f.onInterstitialDisplayed(d.this.e.a());
            }

            public void f() {
                d.this.d = false;
                d.this.f.onInterstitialActivityDestroyed();
            }
        });
        this.b.b(str);
    }

    public long b() {
        f fVar = this.b;
        if (fVar != null) {
            return fVar.h();
        }
        return -1;
    }

    public boolean c() {
        f fVar = this.b;
        return fVar == null || fVar.g();
    }

    public boolean d() {
        return this.c;
    }

    public boolean e() {
        if (this.c) {
            f fVar = this.b;
            if (fVar == null) {
                com.facebook.ads.internal.w.h.a.b(this.e.a, "api", com.facebook.ads.internal.w.h.b.g, new com.facebook.ads.internal.protocol.b(AdErrorType.INTERSTITIAL_CONTROLLER_IS_NULL, AdErrorType.INTERSTITIAL_CONTROLLER_IS_NULL.getDefaultErrorMessage()));
            } else {
                fVar.e();
                this.d = true;
                this.c = false;
                return true;
            }
        }
        this.f.onError(this.e.a(), AdError.SHOW_CALLED_BEFORE_LOAD_ERROR);
        return false;
    }
}
