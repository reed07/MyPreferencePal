package com.facebook.ads.internal.view.e.a;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.PagerSnapHelper;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.internal.adapters.b.h;
import com.facebook.ads.internal.adapters.b.l;
import com.facebook.ads.internal.h.b;
import com.facebook.ads.internal.s.c;
import com.facebook.ads.internal.view.a.C0012a;
import com.facebook.ads.internal.view.d;
import com.facebook.ads.internal.view.o;
import com.facebook.ads.internal.w.b.k;
import com.facebook.ads.internal.w.b.w;
import com.facebook.ads.internal.w.b.x;
import com.facebook.ads.internal.x.a;
import com.facebook.ads.internal.x.a.C0023a;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class e extends o {
    private static final int d = ((int) (x.b * 48.0f));
    private static final int e = ((int) (x.b * 8.0f));
    private static final int f = ((int) (x.b * 8.0f));
    private static final int g = ((int) (x.b * 56.0f));
    private static final int h = ((int) (x.b * 12.0f));
    /* access modifiers changed from: private */
    public final w i = new w();
    @Nullable
    private b j;
    @Nullable
    private LinearLayout k;
    /* access modifiers changed from: private */
    public String l;
    private List<b> m;
    private a n;
    /* access modifiers changed from: private */
    @Nullable
    public com.facebook.ads.internal.view.component.e o;
    @Nullable
    private d p;
    /* access modifiers changed from: private */
    public a q;
    private C0023a r;
    private int s;
    private int t;

    public e(Context context, c cVar, @Nullable b bVar, C0012a aVar) {
        super(context, cVar, aVar);
        this.j = bVar;
    }

    public void a() {
        LinearLayout linearLayout = this.k;
        if (linearLayout != null) {
            linearLayout.removeAllViews();
            this.k = null;
        }
        d dVar = this.p;
        if (dVar != null) {
            dVar.removeAllViews();
            this.p = null;
        }
        com.facebook.ads.internal.view.component.e eVar = this.o;
        if (eVar != null) {
            eVar.removeAllViews();
            this.o = null;
        }
    }

    public void a(int i2, @Nullable Bundle bundle) {
        int i3;
        LinearLayout linearLayout;
        int i4;
        int i5;
        int i6;
        e eVar;
        int i7 = i2;
        this.k = new LinearLayout(getContext());
        if (i7 == 1) {
            linearLayout = this.k;
            i3 = 17;
        } else {
            linearLayout = this.k;
            i3 = 48;
        }
        linearLayout.setGravity(i3);
        this.k.setLayoutParams(new LayoutParams(-1, -1));
        this.k.setOrientation(1);
        int i8 = x.a.widthPixels;
        int i9 = x.a.heightPixels;
        if (i7 == 1) {
            int min = Math.min(i8 - (e * 4), i9 / 2);
            int i10 = (i8 - min) / 8;
            i5 = i10;
            i6 = min;
            i4 = i10 * 4;
        } else {
            int i11 = g + d;
            int i12 = e;
            i4 = i12 * 2;
            i6 = i9 - (i11 + (i12 * 2));
            i5 = i12;
        }
        this.r = new C0023a() {
            public void a() {
                HashMap hashMap = new HashMap();
                if (!e.this.i.b()) {
                    e.this.i.a();
                    if (e.this.getAudienceNetworkListener() != null) {
                        e.this.getAudienceNetworkListener().a("com.facebook.ads.interstitial.impression.logged");
                    }
                    if (!TextUtils.isEmpty(e.this.l)) {
                        e.this.q.a((Map<String, String>) hashMap);
                        hashMap.put("touch", k.a(e.this.i.e()));
                        e.this.a(hashMap);
                        e.this.a.a(e.this.l, hashMap);
                    }
                }
            }
        };
        this.q = new a(this, 1, this.r);
        this.q.a(this.s);
        this.q.b(this.t);
        this.p = new d(getContext());
        this.p.setLayoutParams(new LayoutParams(-1, -2));
        a aVar = new a(this.p, i2, this.m, this.q, bundle);
        this.n = aVar;
        d dVar = this.p;
        List<b> list = this.m;
        c cVar = this.a;
        b bVar = this.j;
        a aVar2 = this.q;
        w wVar = this.i;
        C0012a audienceNetworkListener = getAudienceNetworkListener();
        h a = i7 == 1 ? this.c.a() : this.c.b();
        String str = this.l;
        a aVar3 = this.n;
        c cVar2 = r1;
        d dVar2 = dVar;
        c cVar3 = new c(list, cVar, bVar, aVar2, wVar, audienceNetworkListener, a, str, i6, i5, i4, i2, aVar3);
        dVar2.setAdapter(cVar2);
        int i13 = i2;
        if (i13 == 1) {
            eVar = this;
            a aVar4 = eVar.n;
            new PagerSnapHelper().attachToRecyclerView(eVar.p);
            aVar4.a((c.a) new c.a() {
                public void a(int i) {
                    if (e.this.o != null) {
                        e.this.o.a(i);
                    }
                }
            });
            eVar.o = new com.facebook.ads.internal.view.component.e(getContext(), eVar.c.a(), eVar.m.size());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, f);
            layoutParams.setMargins(0, h, 0, 0);
            eVar.o.setLayoutParams(layoutParams);
        } else {
            eVar = this;
        }
        eVar.k.addView(eVar.p);
        com.facebook.ads.internal.view.component.e eVar2 = eVar.o;
        if (eVar2 != null) {
            eVar.k.addView(eVar2);
        }
        eVar.a(eVar.k, false, i13);
    }

    public void a(Intent intent, Bundle bundle, AudienceNetworkActivity audienceNetworkActivity) {
        com.facebook.ads.internal.adapters.b.k kVar = (com.facebook.ads.internal.adapters.b.k) intent.getSerializableExtra("ad_data_bundle");
        super.a(audienceNetworkActivity, kVar);
        this.l = kVar.c();
        this.s = kVar.f();
        this.t = kVar.g();
        List d2 = kVar.d();
        this.m = new ArrayList(d2.size());
        for (int i2 = 0; i2 < d2.size(); i2++) {
            this.m.add(new b(i2, d2.size(), (l) d2.get(i2)));
        }
        a(audienceNetworkActivity.getResources().getConfiguration().orientation, bundle);
    }

    public void a(Bundle bundle) {
        a aVar = this.n;
        if (aVar != null) {
            aVar.a(bundle);
        }
    }

    public void a_(boolean z) {
        a aVar = this.n;
        if (aVar != null) {
            aVar.a();
        }
    }

    public void b(boolean z) {
        this.n.b();
    }

    public void onConfigurationChanged(Configuration configuration) {
        Bundle bundle = new Bundle();
        a(bundle);
        a();
        a(configuration.orientation, bundle);
        super.onConfigurationChanged(configuration);
    }

    public void onDestroy() {
        super.onDestroy();
        if (!TextUtils.isEmpty(this.l)) {
            HashMap hashMap = new HashMap();
            this.q.a((Map<String, String>) hashMap);
            hashMap.put("touch", k.a(this.i.e()));
            this.a.l(this.l, hashMap);
        }
        a();
        this.q.c();
        this.q = null;
        this.r = null;
        this.m = null;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        this.i.a(motionEvent, this, this);
        return super.onInterceptTouchEvent(motionEvent);
    }
}
