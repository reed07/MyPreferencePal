package com.facebook.ads.internal.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.internal.adapters.l;
import com.facebook.ads.internal.adapters.m;
import com.facebook.ads.internal.s.c;
import com.facebook.ads.internal.view.a.C0012a;
import com.facebook.ads.internal.view.c.a;
import com.facebook.ads.internal.view.c.a.b;
import com.facebook.ads.internal.w.b.k;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class h implements a {
    /* access modifiers changed from: private */
    public static final String a = "h";
    /* access modifiers changed from: private */
    public final C0012a b;
    private final a c;
    private final b d;
    /* access modifiers changed from: private */
    public final m e;
    private final c f;
    /* access modifiers changed from: private */
    public l g;

    public h(final AudienceNetworkActivity audienceNetworkActivity, final c cVar, C0012a aVar) {
        this.b = aVar;
        this.f = cVar;
        this.d = new a.c() {
            private long d = 0;

            public void a() {
                h.this.e.b();
            }

            public void a(String str, Map<String, String> map) {
                Uri parse = Uri.parse(str);
                if (!"fbad".equals(parse.getScheme()) || !"close".equals(parse.getAuthority())) {
                    long j = this.d;
                    this.d = System.currentTimeMillis();
                    if (this.d - j >= 1000) {
                        if ("fbad".equals(parse.getScheme()) && com.facebook.ads.internal.a.c.a(parse.getAuthority())) {
                            h.this.b.a("com.facebook.ads.interstitial.clicked");
                        }
                        com.facebook.ads.internal.a.b a2 = com.facebook.ads.internal.a.c.a(audienceNetworkActivity, cVar, h.this.g.getClientToken(), parse, map);
                        if (a2 != null) {
                            try {
                                a2.a();
                            } catch (Exception e) {
                                Log.e(h.a, "Error executing action", e);
                            }
                        }
                        return;
                    }
                    return;
                }
                audienceNetworkActivity.finish();
            }

            public void b() {
                h.this.e.a();
            }
        };
        this.c = new a(audienceNetworkActivity, new WeakReference(this.d), 1);
        this.c.setLayoutParams(new LayoutParams(-1, -1));
        AnonymousClass2 r8 = new com.facebook.ads.internal.adapters.c() {
            public void a() {
                h.this.b.a("com.facebook.ads.interstitial.impression.logged");
            }
        };
        a aVar2 = this.c;
        m mVar = new m(audienceNetworkActivity, cVar, aVar2, aVar2.getViewabilityChecker(), r8);
        this.e = mVar;
        aVar.a((View) this.c);
    }

    public void a(Intent intent, Bundle bundle, AudienceNetworkActivity audienceNetworkActivity) {
        if (bundle == null || !bundle.containsKey("dataModel")) {
            this.g = l.b(intent);
            l lVar = this.g;
            if (lVar != null) {
                this.e.a(lVar);
                this.c.loadDataWithBaseURL(com.facebook.ads.internal.w.e.b.a(), this.g.c(), AudienceNetworkActivity.WEBVIEW_MIME_TYPE, AudienceNetworkActivity.WEBVIEW_ENCODING, null);
                this.c.a(this.g.f(), this.g.g());
            }
            return;
        }
        this.g = l.a(bundle.getBundle("dataModel"));
        if (this.g != null) {
            this.c.loadDataWithBaseURL(com.facebook.ads.internal.w.e.b.a(), this.g.c(), AudienceNetworkActivity.WEBVIEW_MIME_TYPE, AudienceNetworkActivity.WEBVIEW_ENCODING, null);
            this.c.a(this.g.f(), this.g.g());
        }
    }

    public void a(Bundle bundle) {
        l lVar = this.g;
        if (lVar != null) {
            bundle.putBundle("dataModel", lVar.h());
        }
    }

    public void a_(boolean z) {
        this.c.onPause();
    }

    public void b(boolean z) {
        this.c.onResume();
    }

    public void onDestroy() {
        l lVar = this.g;
        if (lVar != null && !TextUtils.isEmpty(lVar.getClientToken())) {
            HashMap hashMap = new HashMap();
            this.c.getViewabilityChecker().a((Map<String, String>) hashMap);
            hashMap.put("touch", k.a(this.c.getTouchData()));
            this.f.l(this.g.getClientToken(), hashMap);
        }
        com.facebook.ads.internal.w.e.b.a(this.c);
        this.c.destroy();
    }

    public void setListener(C0012a aVar) {
    }
}
