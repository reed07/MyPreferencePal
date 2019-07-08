package com.facebook.ads.internal.view;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.internal.adapters.b.b;
import com.facebook.ads.internal.adapters.b.h;
import com.facebook.ads.internal.adapters.b.k;
import com.facebook.ads.internal.adapters.b.l;
import com.facebook.ads.internal.s.c;
import com.facebook.ads.internal.view.a.C0012a;
import com.facebook.ads.internal.view.i.a;
import com.facebook.ads.internal.w.b.t;
import com.facebook.ads.internal.w.b.x;
import java.util.Map;

public abstract class o extends RelativeLayout implements a {
    /* access modifiers changed from: protected */
    public final c a;
    protected final i b = new i(getContext(), getAudienceNetworkListener(), a.CROSS);
    protected b c;
    private final C0012a d;
    private final t e = new t(this);
    private String f;

    protected o(Context context, c cVar, C0012a aVar) {
        super(context.getApplicationContext());
        this.a = cVar;
        this.d = aVar;
    }

    private void a() {
        removeAllViews();
        x.b(this);
    }

    /* access modifiers changed from: protected */
    public void a(View view, boolean z, int i) {
        this.e.a(t.a.DEFAULT);
        a();
        LayoutParams layoutParams = new LayoutParams(-1, -1);
        layoutParams.setMargins(0, z ? 0 : i.a, 0, 0);
        addView(view, layoutParams);
        h a2 = i == 1 ? this.c.a() : this.c.b();
        LayoutParams layoutParams2 = new LayoutParams(-1, i.a);
        layoutParams2.addRule(10);
        this.b.a(a2, z);
        addView(this.b, layoutParams2);
        x.a((View) this, a2.d(z));
        C0012a aVar = this.d;
        if (aVar != null) {
            aVar.a((View) this, 0);
            if (z && VERSION.SDK_INT >= 19) {
                this.e.a(t.a.FULL_SCREEN);
            }
        }
    }

    public void a(final AudienceNetworkActivity audienceNetworkActivity, k kVar) {
        this.e.a(audienceNetworkActivity.getWindow());
        this.c = kVar.b();
        this.f = kVar.i();
        this.b.a(kVar.a(), kVar.c(), ((l) kVar.d().get(0)).c().c());
        this.b.setToolbarListener(new i.b() {
            public void a() {
                audienceNetworkActivity.finish();
            }
        });
        if (com.facebook.ads.internal.f.a.a(getContext(), true)) {
            this.b.a(kVar.a(), kVar.c());
        }
    }

    /* access modifiers changed from: protected */
    public void a(com.facebook.ads.internal.view.component.a.c cVar, @Nullable com.facebook.ads.internal.view.component.a.l lVar, @Nullable u.a aVar, int i, int i2, boolean z, int i3) {
        a(cVar, z, i3);
        if (lVar != null) {
            this.b.setPageDetailsVisibility(4);
            this.e.a(t.a.DEFAULT);
            if (i3 == 1) {
                u uVar = new u(getContext(), lVar, i - i.a, 0);
                addView(uVar);
                if (aVar != null) {
                    uVar.setDragListener(aVar);
                    return;
                }
                return;
            }
            LayoutParams layoutParams = new LayoutParams(i2, -1);
            LayoutParams layoutParams2 = new LayoutParams(x.a.widthPixels - i2, i.a);
            layoutParams2.addRule(10);
            this.b.setLayoutParams(layoutParams2);
            layoutParams.addRule(11);
            cVar.addView(lVar, layoutParams);
        }
    }

    /* access modifiers changed from: protected */
    public final void a(Map<String, String> map) {
        String str = this.f;
        if (str != null) {
            map.put("extra_hints", str);
        }
    }

    /* access modifiers changed from: protected */
    public c getAdEventManager() {
        return this.a;
    }

    /* access modifiers changed from: protected */
    public C0012a getAudienceNetworkListener() {
        return this.d;
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
        this.b.d();
        super.onConfigurationChanged(configuration);
        final ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
            @RequiresApi
            public void onGlobalLayout() {
                o.this.b.e();
                if (VERSION.SDK_INT < 16) {
                    viewTreeObserver.removeGlobalOnLayoutListener(this);
                } else {
                    viewTreeObserver.removeOnGlobalLayoutListener(this);
                }
            }
        });
    }

    public void onDestroy() {
        this.e.a();
        this.b.setToolbarListener(null);
        a();
    }

    public void setListener(C0012a aVar) {
    }
}
