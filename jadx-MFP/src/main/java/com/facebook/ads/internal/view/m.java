package com.facebook.ads.internal.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.AudienceNetworkActivity.BackButtonInterceptor;
import com.facebook.ads.internal.adapters.b.k;
import com.facebook.ads.internal.adapters.b.l;
import com.facebook.ads.internal.adapters.b.o;
import com.facebook.ads.internal.view.a.C0012a;
import com.facebook.ads.internal.view.f.b;
import com.facebook.ads.internal.view.f.b.c;
import com.facebook.ads.internal.w.b.w;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class m extends o {
    private final k d;
    private final BackButtonInterceptor e = new BackButtonInterceptor() {
        public boolean interceptBackButton() {
            return !m.this.g;
        }
    };
    /* access modifiers changed from: private */
    public b f;
    /* access modifiers changed from: private */
    public boolean g;

    private static class a implements c {
        private final WeakReference<Activity> a;
        private final WeakReference<m> b;
        private final k c;
        private final com.facebook.ads.internal.s.c d;
        private final WeakReference<C0012a> e;

        a(Activity activity, m mVar, k kVar, com.facebook.ads.internal.s.c cVar, C0012a aVar) {
            this.a = new WeakReference<>(activity);
            this.b = new WeakReference<>(mVar);
            this.c = kVar;
            this.d = cVar;
            this.e = new WeakReference<>(aVar);
        }

        private void e() {
            if (this.a.get() != null) {
                ((Activity) this.a.get()).finish();
            }
        }

        public void a() {
        }

        public void a(com.facebook.ads.internal.x.a aVar, w wVar) {
            HashMap hashMap = new HashMap();
            if (!TextUtils.isEmpty(this.c.c())) {
                aVar.a((Map<String, String>) hashMap);
                hashMap.put("touch", com.facebook.ads.internal.w.b.k.a(wVar.e()));
                this.d.a(this.c.c(), hashMap);
                if (this.e.get() != null) {
                    ((C0012a) this.e.get()).a("com.facebook.ads.interstitial.impression.logged");
                }
            }
        }

        public void a(boolean z) {
            if (this.b.get() != null && ((m) this.b.get()).f.getAdWebView() != null && this.e.get() != null) {
                com.facebook.ads.internal.view.c.a adWebView = ((m) this.b.get()).f.getAdWebView();
                com.facebook.ads.internal.view.component.a aVar = new com.facebook.ads.internal.view.component.a(((m) this.b.get()).getContext(), true, false, "com.facebook.ads.interstitial.clicked", this.c.b().a(), this.d, (C0012a) this.e.get(), adWebView.getViewabilityChecker(), adWebView.getTouchDataRecorder());
                aVar.a(((l) this.c.d().get(0)).b(), this.c.c(), (Map<String, String>) new HashMap<String,String>(), z);
                aVar.performClick();
            }
        }

        public void b() {
            if (this.b.get() != null) {
                ((m) this.b.get()).g = true;
            }
        }

        public void c() {
            e();
        }

        public void d() {
            if (this.e.get() != null) {
                ((C0012a) this.e.get()).a("com.facebook.ads.interstitial.error");
            }
            e();
        }
    }

    public m(Context context, com.facebook.ads.internal.s.c cVar, k kVar, C0012a aVar) {
        super(context, cVar, aVar);
        this.d = kVar;
    }

    public void a(Intent intent, Bundle bundle, AudienceNetworkActivity audienceNetworkActivity) {
        AudienceNetworkActivity audienceNetworkActivity2 = audienceNetworkActivity;
        super.a(audienceNetworkActivity2, this.d);
        audienceNetworkActivity2.addBackButtonInterceptor(this.e);
        o a2 = o.a(this.d);
        boolean z = a2.f().c() > 0;
        com.facebook.ads.internal.s.c adEventManager = getAdEventManager();
        C0012a audienceNetworkListener = getAudienceNetworkListener();
        a aVar = new a(audienceNetworkActivity, this, this.d, getAdEventManager(), getAudienceNetworkListener());
        b bVar = r7;
        b bVar2 = new b(audienceNetworkActivity, a2, adEventManager, audienceNetworkListener, aVar, z, true);
        this.f = bVar;
        a(this.f, true, 1);
        this.b.setVisibility(8);
        this.f.c();
    }

    public void a(Bundle bundle) {
    }

    public void a_(boolean z) {
        this.f.e();
    }

    public void b(boolean z) {
        this.f.d();
    }

    public void onDestroy() {
        super.onDestroy();
        if (!TextUtils.isEmpty(this.d.c())) {
            com.facebook.ads.internal.view.c.a adWebView = this.f.getAdWebView();
            w wVar = null;
            com.facebook.ads.internal.x.a viewabilityChecker = adWebView != null ? adWebView.getViewabilityChecker() : null;
            if (adWebView != null) {
                wVar = adWebView.getTouchDataRecorder();
            }
            HashMap hashMap = new HashMap();
            if (viewabilityChecker != null) {
                viewabilityChecker.a((Map<String, String>) hashMap);
                hashMap.put("touch", com.facebook.ads.internal.w.b.k.a(wVar.e()));
            }
            this.a.l(this.d.c(), hashMap);
        }
        this.f.f();
    }
}
