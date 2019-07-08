package com.facebook.ads.internal.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.AudienceNetworkActivity.BackButtonInterceptor;
import com.facebook.ads.internal.adapters.b.b;
import com.facebook.ads.internal.adapters.b.n;
import com.facebook.ads.internal.adapters.b.o;
import com.facebook.ads.internal.adapters.b.q;
import com.facebook.ads.internal.o.d;
import com.facebook.ads.internal.view.a.C0012a;
import com.facebook.ads.internal.view.f.b.c;
import com.facebook.ads.internal.view.i.b.aa;
import com.facebook.ads.internal.w.b.k;
import com.facebook.ads.internal.w.b.p;
import com.facebook.ads.internal.w.b.w;
import com.facebook.ads.internal.w.e.e;
import com.facebook.ads.internal.w.e.f;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

@TargetApi(16)
public class r extends RelativeLayout implements a, c {
    private final com.facebook.ads.internal.s.c a;
    private final q b;
    private final n c;
    private final b d;
    private int e;
    @Nullable
    private Context f;
    @Nullable
    private AudienceNetworkActivity g;
    @Nullable
    private C0012a h;
    private Executor i = p.a;
    private final BackButtonInterceptor j = new BackButtonInterceptor() {
        public boolean interceptBackButton() {
            return !r.this.m;
        }
    };
    private boolean k;
    private com.facebook.ads.internal.view.f.b l;
    /* access modifiers changed from: private */
    public boolean m;
    private com.facebook.ads.internal.adapters.r n;

    private static class a implements com.facebook.ads.internal.w.e.e.a {
        final WeakReference<C0012a> a;

        private a(WeakReference<C0012a> weakReference) {
            this.a = weakReference;
        }

        public void a() {
            if (this.a.get() != null) {
                ((C0012a) this.a.get()).a(aa.REWARD_SERVER_FAILED.a());
            }
        }

        public void a(f fVar) {
            C0012a aVar;
            aa aaVar;
            if (this.a.get() != null) {
                if (fVar == null || !fVar.a()) {
                    aVar = (C0012a) this.a.get();
                    aaVar = aa.REWARD_SERVER_FAILED;
                } else {
                    aVar = (C0012a) this.a.get();
                    aaVar = aa.REWARD_SERVER_SUCCESS;
                }
                aVar.a(aaVar.a());
            }
        }
    }

    public r(Context context, com.facebook.ads.internal.s.c cVar, C0012a aVar, q qVar) {
        super(context);
        this.f = context;
        this.h = aVar;
        this.a = cVar;
        this.b = qVar;
        this.c = qVar.j().j();
        this.d = qVar.i();
    }

    @NonNull
    private com.facebook.ads.internal.view.component.a a(com.facebook.ads.internal.view.c.a aVar) {
        com.facebook.ads.internal.view.component.a aVar2 = new com.facebook.ads.internal.view.component.a(this.f, true, false, aa.REWARDED_VIDEO_AD_CLICK.a(), this.d.a(), this.a, this.h, aVar.getViewabilityChecker(), aVar.getTouchDataRecorder());
        return aVar2;
    }

    static /* synthetic */ void b(r rVar) {
        C0012a aVar = rVar.h;
        if (aVar != null) {
            aVar.a(aa.REWARDED_VIDEO_IMPRESSION.a());
        }
    }

    public void a() {
    }

    public void a(Intent intent, Bundle bundle, AudienceNetworkActivity audienceNetworkActivity) {
        int i2;
        if (this.h != null && this.f != null) {
            this.g = audienceNetworkActivity;
            this.g.addBackButtonInterceptor(this.j);
            this.e = audienceNetworkActivity.getRequestedOrientation();
            switch (this.c.f()) {
                case PORTRAIT:
                    i2 = 1;
                    break;
                case LANDSCAPE:
                    i2 = 0;
                    break;
                case UNSPECIFIED:
                    i2 = -1;
                    break;
                default:
                    com.facebook.ads.internal.view.f.b bVar = new com.facebook.ads.internal.view.f.b(this.f, o.a(this.b), this.a, this.h, this, true, false);
                    this.l = bVar;
                    addView(bVar);
                    this.h.a((View) this);
                    bVar.c();
            }
            audienceNetworkActivity.setRequestedOrientation(i2);
            com.facebook.ads.internal.view.f.b bVar2 = new com.facebook.ads.internal.view.f.b(this.f, o.a(this.b), this.a, this.h, this, true, false);
            this.l = bVar2;
            addView(bVar2);
            this.h.a((View) this);
            bVar2.c();
        }
    }

    public void a(Bundle bundle) {
    }

    public void a(com.facebook.ads.internal.x.a aVar, w wVar) {
        com.facebook.ads.internal.adapters.r rVar = this.n;
        if (rVar == null) {
            com.facebook.ads.internal.adapters.r rVar2 = new com.facebook.ads.internal.adapters.r(getContext(), this.a, aVar, wVar, new com.facebook.ads.internal.adapters.c() {
                public void a() {
                    r.b(r.this);
                }
            });
            this.n = rVar2;
            this.n.a(this.b);
            rVar = this.n;
        }
        rVar.a();
    }

    public void a(boolean z) {
        this.k = true;
        com.facebook.ads.internal.view.c.a adWebView = this.l.getAdWebView();
        if (adWebView != null) {
            com.facebook.ads.internal.view.component.a a2 = a(adWebView);
            a2.a(this.b.h(), this.b.a(), (Map<String, String>) new HashMap<String,String>(), z);
            a2.performClick();
        }
    }

    public void a_(boolean z) {
        this.l.e();
    }

    public void b() {
        this.m = true;
        String a2 = this.b.k().a();
        if (this.f != null || !TextUtils.isEmpty(a2)) {
            e eVar = new e(this.f, new HashMap());
            eVar.a((com.facebook.ads.internal.w.e.e.a) new a(new WeakReference(this.h)));
            eVar.executeOnExecutor(this.i, new String[]{a2});
        }
        C0012a aVar = this.h;
        if (aVar != null) {
            aVar.a(aa.REWARDED_VIDEO_COMPLETE.a(), (d) new com.facebook.ads.internal.view.i.b.c(0, 0));
        }
        com.facebook.ads.internal.view.c.a adWebView = this.l.getAdWebView();
        if (this.k && adWebView != null) {
            a(adWebView).b(this.b.h(), this.b.a(), new HashMap());
        }
    }

    public void b(boolean z) {
        this.l.d();
    }

    public void c() {
        C0012a aVar = this.h;
        if (aVar != null) {
            aVar.a(aa.REWARDED_VIDEO_END_ACTIVITY.a());
        }
    }

    public void d() {
        C0012a aVar = this.h;
        if (aVar != null) {
            aVar.a(aa.REWARDED_VIDEO_ERROR.a());
        }
    }

    public void onDestroy() {
        AudienceNetworkActivity audienceNetworkActivity = this.g;
        if (audienceNetworkActivity != null) {
            audienceNetworkActivity.removeBackButtonInterceptor(this.j);
            this.g.setRequestedOrientation(this.e);
        }
        com.facebook.ads.internal.view.c.a adWebView = this.l.getAdWebView();
        if (adWebView != null && !TextUtils.isEmpty(this.b.a())) {
            HashMap hashMap = new HashMap();
            adWebView.getViewabilityChecker().a((Map<String, String>) hashMap);
            hashMap.put("touch", k.a(adWebView.getTouchDataRecorder().e()));
            this.a.l(this.b.a(), hashMap);
        }
        this.l.f();
        this.h = null;
        this.g = null;
        this.f = null;
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (this.l.getAdWebView() != null) {
            if (z) {
                b(false);
            } else {
                a_(false);
            }
        }
    }

    public void setListener(C0012a aVar) {
        this.h = aVar;
    }
}
