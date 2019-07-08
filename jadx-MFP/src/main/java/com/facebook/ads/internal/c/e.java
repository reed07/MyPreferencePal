package com.facebook.ads.internal.c;

import android.util.Log;
import com.facebook.ads.AdError;
import com.facebook.ads.RewardData;
import com.facebook.ads.S2SRewardedVideoAdExtendedListener;
import com.facebook.ads.internal.adapters.AdAdapter;
import com.facebook.ads.internal.adapters.a;
import com.facebook.ads.internal.adapters.s;
import com.facebook.ads.internal.b.h;
import com.facebook.ads.internal.c.a.c;
import com.facebook.ads.internal.c.a.d;
import com.facebook.ads.internal.protocol.AdPlacementType;
import com.facebook.ads.internal.w.h.b;

public class e implements c {
    private static final String a = "e";
    /* access modifiers changed from: private */
    public final j b;
    private h c;
    /* access modifiers changed from: private */
    public boolean d = false;
    /* access modifiers changed from: private */
    public final S2SRewardedVideoAdExtendedListener e;

    public e(j jVar, c cVar, String str) {
        this.b = jVar;
        this.e = new d(str, cVar, this, jVar);
    }

    /* access modifiers changed from: private */
    public void a(boolean z) {
        h hVar = this.c;
        if (hVar != null) {
            hVar.a((a) new a() {
            });
            this.c.a(z);
            this.c = null;
        }
    }

    public void a() {
        a(true);
    }

    public void a(RewardData rewardData) {
        this.b.e = rewardData;
        if (this.d) {
            this.c.a(rewardData);
        }
    }

    public void a(String str, boolean z) {
        try {
            if (!this.d && this.c != null) {
                Log.w(a, "An ad load is already in progress. You should wait for adLoaded() to be called");
            }
            a(false);
            this.d = false;
            com.facebook.ads.internal.b.a aVar = new com.facebook.ads.internal.b.a(this.b.b, com.facebook.ads.internal.protocol.e.REWARDED_VIDEO, AdPlacementType.REWARDED_VIDEO, com.facebook.ads.internal.protocol.d.INTERSTITIAL, 1);
            aVar.a(z);
            aVar.a(this.b.d);
            this.c = new h(this.b.a, aVar);
            this.c.a((a) new a() {
                public void a() {
                    e.this.e.onAdClicked(e.this.b.a());
                }

                public void a(AdAdapter adAdapter) {
                    s sVar = (s) adAdapter;
                    if (e.this.b.e != null) {
                        sVar.a(e.this.b.e);
                    }
                    e.this.b.h = sVar.a();
                    e.this.d = true;
                    e.this.e.onAdLoaded(e.this.b.a());
                }

                public void a(com.facebook.ads.internal.protocol.a aVar) {
                    e.this.a(true);
                    e.this.e.onError(e.this.b.a(), AdError.getAdErrorFromWrapper(aVar));
                }

                public void b() {
                    e.this.e.onLoggingImpression(e.this.b.a());
                }

                public void g() {
                    e.this.e.onRewardedVideoCompleted();
                }

                public void h() {
                    e.this.e.onRewardedVideoClosed();
                }

                public void i() {
                    e.this.e.onRewardServerFailed();
                }

                public void j() {
                    e.this.e.onRewardServerSuccess();
                }

                public void k() {
                    e.this.e.onRewardedVideoActivityDestroyed();
                }
            });
            this.c.b(str);
        } catch (Exception e2) {
            Log.e(a, "Error loading rewarded video ad", e2);
            com.facebook.ads.internal.w.h.a.b(this.b.a, "api", b.i, e2);
            this.e.onError(this.b.a(), AdError.internalError(AdError.INTERNAL_ERROR_2004));
        }
    }

    public boolean a(int i) {
        if (!this.d) {
            this.e.onError(this.b.a(), AdError.SHOW_CALLED_BEFORE_LOAD_ERROR);
            return false;
        }
        h hVar = this.c;
        if (hVar != null) {
            hVar.h.a(i);
            this.c.e();
            this.d = false;
            return true;
        }
        this.d = false;
        return false;
    }

    public long b() {
        h hVar = this.c;
        if (hVar != null) {
            return hVar.h();
        }
        return -1;
    }

    public boolean c() {
        h hVar = this.c;
        return hVar == null || hVar.g();
    }

    public boolean d() {
        return this.d;
    }
}
