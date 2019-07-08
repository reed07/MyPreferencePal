package com.facebook.ads.internal.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;
import com.facebook.ads.AdError;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.internal.protocol.AdPlacementType;
import com.facebook.ads.internal.protocol.d;
import com.facebook.ads.internal.s.c;
import com.facebook.ads.internal.view.c.a;
import com.facebook.ads.internal.view.c.a.b;
import java.lang.ref.WeakReference;
import java.util.Map;
import org.json.JSONObject;

public class e implements AdAdapter {
    /* access modifiers changed from: private */
    public static final String a = "e";
    @Nullable
    private b b;
    @Nullable
    private a c;
    /* access modifiers changed from: private */
    public m d;
    /* access modifiers changed from: private */
    public BannerAdapterListener e;
    private Map<String, Object> f;
    /* access modifiers changed from: private */
    @Nullable
    public c g;
    private String h;
    /* access modifiers changed from: private */
    public Context i;

    public void a(Context context, c cVar, d dVar, BannerAdapterListener bannerAdapterListener, Map<String, Object> map) {
        this.i = context;
        this.g = cVar;
        this.e = bannerAdapterListener;
        this.f = map;
        com.facebook.ads.internal.m.d dVar2 = (com.facebook.ads.internal.m.d) this.f.get("definition");
        final l a2 = l.a((JSONObject) this.f.get("data"));
        this.h = a2.getClientToken();
        if (com.facebook.ads.internal.a.e.a(this.i, a2, this.g)) {
            this.e.onBannerError(this, AdError.internalError(AdError.INTERNAL_ERROR_2006));
            return;
        }
        this.b = new a.c() {
            public void a() {
                e.this.d.b();
            }

            public void a(String str, Map<String, String> map) {
                Uri parse = Uri.parse(str);
                if ("fbad".equals(parse.getScheme()) && com.facebook.ads.internal.a.c.a(parse.getAuthority()) && e.this.e != null) {
                    e.this.e.onBannerAdClicked(e.this);
                }
                com.facebook.ads.internal.a.b a2 = com.facebook.ads.internal.a.c.a(e.this.i, e.this.g, a2.getClientToken(), parse, map);
                if (a2 != null) {
                    try {
                        a2.a();
                    } catch (Exception e) {
                        Log.e(e.a, "Error executing action", e);
                    }
                }
            }

            public void b() {
                if (e.this.d != null) {
                    e.this.d.a();
                }
            }
        };
        this.c = new a(this.i, new WeakReference(this.b), dVar2.f());
        this.c.a(dVar2.h(), dVar2.i());
        AnonymousClass2 r5 = new c() {
            public void a() {
                if (e.this.e != null) {
                    e.this.e.onBannerLoggingImpression(e.this);
                }
            }
        };
        Context context2 = this.i;
        c cVar2 = this.g;
        a aVar = this.c;
        m mVar = new m(context2, cVar2, aVar, aVar.getViewabilityChecker(), r5);
        this.d = mVar;
        this.d.a(a2);
        this.c.loadDataWithBaseURL(com.facebook.ads.internal.w.e.b.a(), a2.c(), AudienceNetworkActivity.WEBVIEW_MIME_TYPE, AudienceNetworkActivity.WEBVIEW_ENCODING, null);
        BannerAdapterListener bannerAdapterListener2 = this.e;
        if (bannerAdapterListener2 != null) {
            bannerAdapterListener2.onBannerAdLoaded(this, this.c);
        }
    }

    public String getClientToken() {
        return this.h;
    }

    public final AdPlacementType getPlacementType() {
        return AdPlacementType.BANNER;
    }

    public void onDestroy() {
        a aVar = this.c;
        if (aVar != null) {
            aVar.destroy();
            this.c = null;
            this.b = null;
        }
    }
}
