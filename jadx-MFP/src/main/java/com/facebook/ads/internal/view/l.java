package com.facebook.ads.internal.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.AudienceNetworkActivity.BackButtonInterceptor;
import com.facebook.ads.internal.adapters.b.k;
import com.facebook.ads.internal.s.c;
import com.facebook.ads.internal.view.a.C0012a;
import com.facebook.ads.internal.view.c.d;
import com.facebook.ads.internal.view.c.e;
import com.facebook.ads.internal.view.component.a.g;
import com.facebook.ads.internal.w.b.w;
import com.facebook.ads.internal.w.b.x;
import com.facebook.ads.internal.x.a;
import com.facebook.ads.internal.x.a.C0023a;
import java.util.HashMap;
import java.util.Map;

public class l extends o {
    /* access modifiers changed from: private */
    public final k d;
    /* access modifiers changed from: private */
    public final a e;
    /* access modifiers changed from: private */
    public final w f = new w();
    private final C0023a g;
    /* access modifiers changed from: private */
    @Nullable
    public com.facebook.ads.internal.view.component.a.l h;
    private boolean i = false;

    public l(Context context, k kVar, c cVar, C0012a aVar) {
        super(context, cVar, aVar);
        this.d = kVar;
        this.g = new C0023a() {
            public void a() {
                if (!l.this.f.b()) {
                    l.this.f.a();
                    HashMap hashMap = new HashMap();
                    l.this.e.a((Map<String, String>) hashMap);
                    hashMap.put("touch", com.facebook.ads.internal.w.b.k.a(l.this.f.e()));
                    l.this.a(hashMap);
                    l.this.a.a(l.this.d.c(), hashMap);
                    if (l.this.getAudienceNetworkListener() != null) {
                        l.this.getAudienceNetworkListener().a("com.facebook.ads.interstitial.impression.logged");
                    }
                }
            }
        };
        this.e = new a(this, 100, this.g);
        this.e.a(kVar.f());
    }

    private void setUpContent(int i2) {
        com.facebook.ads.internal.adapters.b.l lVar = (com.facebook.ads.internal.adapters.b.l) this.d.d().get(0);
        ImageView imageView = new ImageView(getContext());
        imageView.setScaleType(ScaleType.CENTER);
        imageView.setAdjustViewBounds(true);
        d a = new d(imageView).a(lVar.c().i(), lVar.c().h());
        a.a((e) new e() {
            public void a(boolean z) {
                if (z) {
                    l.this.e.a();
                }
            }
        });
        a.a(lVar.c().g());
        com.facebook.ads.internal.view.component.a.e.a aVar = new com.facebook.ads.internal.view.component.a.e.a(getContext(), this.a, getAudienceNetworkListener(), this.d, imageView, this.e, this.f);
        com.facebook.ads.internal.view.component.a.e a2 = aVar.a(i.a).b(i2).a();
        com.facebook.ads.internal.view.component.a.c a3 = com.facebook.ads.internal.view.component.a.d.a(a2);
        this.h = g.a(a2, x.a.heightPixels - a3.getExactMediaHeightIfAvailable(), x.a.widthPixels - a3.getExactMediaWidthIfAvailable(), this.i);
        a(a3, this.h, this.h != null ? new u.a() {
            public void a() {
                l.this.h.b();
            }

            public void b() {
                l.this.h.a();
            }
        } : null, a3.getExactMediaHeightIfAvailable(), x.a.widthPixels - a3.getExactMediaWidthIfAvailable(), a3.a(), i2);
    }

    public void a(Intent intent, Bundle bundle, AudienceNetworkActivity audienceNetworkActivity) {
        super.a(audienceNetworkActivity, this.d);
        audienceNetworkActivity.addBackButtonInterceptor(new BackButtonInterceptor() {
            public boolean interceptBackButton() {
                return l.this.h != null && l.this.h.c();
            }
        });
        setUpContent(audienceNetworkActivity.getResources().getConfiguration().orientation);
    }

    public void a(Bundle bundle) {
    }

    public void a_(boolean z) {
        com.facebook.ads.internal.view.component.a.l lVar = this.h;
        if (lVar != null) {
            lVar.e();
        }
    }

    public void b(boolean z) {
        com.facebook.ads.internal.view.component.a.l lVar = this.h;
        if (lVar != null) {
            lVar.f();
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        removeAllViews();
        com.facebook.ads.internal.view.component.a.l lVar = this.h;
        if (lVar != null) {
            x.b(lVar);
            this.i = this.h.d();
        }
        setUpContent(configuration.orientation);
        super.onConfigurationChanged(configuration);
    }

    public void onDestroy() {
        k kVar = this.d;
        if (kVar != null && !TextUtils.isEmpty(kVar.c())) {
            HashMap hashMap = new HashMap();
            this.e.a((Map<String, String>) hashMap);
            hashMap.put("touch", com.facebook.ads.internal.w.b.k.a(this.f.e()));
            this.a.l(this.d.c(), hashMap);
        }
        this.e.c();
        com.facebook.ads.internal.view.component.a.l lVar = this.h;
        if (lVar != null) {
            lVar.g();
        }
        super.onDestroy();
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        this.f.a(motionEvent, this, this);
        return super.onInterceptTouchEvent(motionEvent);
    }
}
