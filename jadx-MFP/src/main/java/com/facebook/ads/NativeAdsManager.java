package com.facebook.ads;

import android.content.Context;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.facebook.ads.NativeAdBase.MediaCacheFlag;
import com.facebook.ads.internal.a;
import com.facebook.ads.internal.a.C0000a;
import com.facebook.ads.internal.adapters.i;
import com.facebook.ads.internal.h.b;
import com.facebook.ads.internal.protocol.e;
import java.util.ArrayList;
import java.util.List;

public class NativeAdsManager {
    private static final String a = "NativeAdsManager";
    /* access modifiers changed from: private */
    public final Context b;
    private final String c;
    private final int d;
    /* access modifiers changed from: private */
    public final List<NativeAd> e;
    /* access modifiers changed from: private */
    public int f;
    /* access modifiers changed from: private */
    public Listener g;
    private String h;
    private a i;
    private boolean j;
    /* access modifiers changed from: private */
    public boolean k;

    public interface Listener {
        void onAdError(AdError adError);

        void onAdsLoaded();
    }

    public NativeAdsManager(Context context, String str, int i2) {
        if (context != null) {
            this.b = context;
            this.c = str;
            this.d = Math.max(i2, 0);
            this.e = new ArrayList(i2);
            this.f = -1;
            this.k = false;
            this.j = false;
            try {
                CookieManager.getInstance();
                if (VERSION.SDK_INT < 21) {
                    CookieSyncManager.createInstance(context);
                }
            } catch (Exception e2) {
                Log.w(a, "Failed to initialize CookieManager.", e2);
            }
        } else {
            throw new IllegalArgumentException("context can not be null");
        }
    }

    public void disableAutoRefresh() {
        this.j = true;
        a aVar = this.i;
        if (aVar != null) {
            aVar.c();
        }
    }

    public int getUniqueNativeAdCount() {
        return this.e.size();
    }

    public boolean isLoaded() {
        return this.k;
    }

    public void loadAds() {
        loadAds(MediaCacheFlag.ALL);
    }

    public void loadAds(final MediaCacheFlag mediaCacheFlag) {
        e eVar = e.NATIVE_UNKNOWN;
        int i2 = this.d;
        a aVar = this.i;
        if (aVar != null) {
            aVar.b();
        }
        a aVar2 = new a(this.b, this.c, eVar, null, i2);
        this.i = aVar2;
        if (this.j) {
            this.i.c();
        }
        this.i.a(this.h);
        this.i.a((C0000a) new C0000a() {
            public void a(com.facebook.ads.internal.protocol.a aVar) {
                if (NativeAdsManager.this.g != null) {
                    NativeAdsManager.this.g.onAdError(AdError.getAdErrorFromWrapper(aVar));
                }
            }

            public void a(final List<i> list) {
                b bVar = new b(NativeAdsManager.this.b);
                for (i iVar : list) {
                    if (mediaCacheFlag.equals(MediaCacheFlag.ALL)) {
                        if (iVar.l() != null) {
                            bVar.a(iVar.l().a(), iVar.l().c(), iVar.l().b());
                        }
                        if (iVar.m() != null) {
                            bVar.a(iVar.m().a(), iVar.m().c(), iVar.m().b());
                        }
                        if (!TextUtils.isEmpty(iVar.t())) {
                            bVar.a(iVar.t());
                        }
                    }
                }
                bVar.a((com.facebook.ads.internal.h.a) new com.facebook.ads.internal.h.a() {
                    private void c() {
                        NativeAdsManager.this.k = true;
                        NativeAdsManager.this.e.clear();
                        NativeAdsManager.this.f = 0;
                        for (i nativeAd : list) {
                            NativeAdsManager.this.e.add(new NativeAd(NativeAdsManager.this.b, nativeAd, null));
                        }
                        if (NativeAdsManager.this.g != null) {
                            NativeAdsManager.this.g.onAdsLoaded();
                        }
                    }

                    public void a() {
                        c();
                    }

                    public void b() {
                        c();
                    }
                });
            }
        });
        this.i.a();
    }

    public NativeAd nextNativeAd() {
        if (this.e.size() == 0) {
            return null;
        }
        int i2 = this.f;
        this.f = i2 + 1;
        List<NativeAd> list = this.e;
        NativeAd nativeAd = (NativeAd) list.get(i2 % list.size());
        return i2 >= this.e.size() ? new NativeAd((NativeAdBase) nativeAd) : nativeAd;
    }

    public void setExtraHints(String str) {
        this.h = str;
    }

    public void setListener(Listener listener) {
        this.g = listener;
    }
}
