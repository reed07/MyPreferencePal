package com.facebook.ads.internal.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.AdError;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.CacheFlag;
import com.facebook.ads.internal.s.c;
import com.facebook.ads.internal.view.i.a;
import com.facebook.ads.internal.view.i.b.b;
import com.facebook.ads.internal.view.i.b.e;
import com.facebook.ads.internal.view.i.b.m;
import com.facebook.ads.internal.view.i.c.d;
import com.facebook.ads.internal.view.i.c.i;
import com.facebook.ads.internal.view.i.c.k;
import com.facebook.ads.internal.view.i.c.l;
import com.facebook.ads.internal.w.b.r;
import com.facebook.ads.internal.w.b.u;
import com.facebook.appevents.UserDataStore;
import com.google.ads.mediation.inmobi.InMobiNetworkValues;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.shared.db.table.ImagesTable.Columns;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class f extends n implements r<Bundle> {
    static final /* synthetic */ boolean e = (!f.class.desiredAssertionStatus());
    @Nullable
    protected c a;
    @Nullable
    protected a b;
    @Nullable
    protected JSONObject c;
    @Nullable
    protected Context d;
    private final com.facebook.ads.internal.o.f<com.facebook.ads.internal.view.i.b.c> f = new com.facebook.ads.internal.o.f<com.facebook.ads.internal.view.i.b.c>() {
        public Class<com.facebook.ads.internal.view.i.b.c> a() {
            return com.facebook.ads.internal.view.i.b.c.class;
        }

        public void a(com.facebook.ads.internal.view.i.b.c cVar) {
            if (f.this.j != null) {
                f.this.j.d(f.this);
            }
        }
    };
    private final com.facebook.ads.internal.o.f<m> g = new com.facebook.ads.internal.o.f<m>() {
        public Class<m> a() {
            return m.class;
        }

        public void a(m mVar) {
            f.this.l = true;
            if (f.this.j != null) {
                f.this.j.a(f.this);
            }
        }
    };
    private final com.facebook.ads.internal.o.f<e> h = new com.facebook.ads.internal.o.f<e>() {
        public Class<e> a() {
            return e.class;
        }

        public void a(e eVar) {
            if (f.this.j != null) {
                f.this.j.a((n) f.this, AdError.internalError(2003));
            }
        }
    };
    private final com.facebook.ads.internal.o.f<b> i = new com.facebook.ads.internal.o.f<b>() {
        public Class<b> a() {
            return b.class;
        }

        public void a(b bVar) {
            if (f.this.j != null) {
                f.this.j.b(f.this);
            }
        }
    };
    /* access modifiers changed from: private */
    @Nullable
    public com.facebook.ads.a.a j;
    @Nullable
    private String k;
    /* access modifiers changed from: private */
    public boolean l = false;
    @Nullable
    private com.facebook.ads.internal.view.i.c m;
    @Nullable
    private String n;
    private boolean o = false;
    private com.facebook.ads.internal.h.b p;

    private void a(Context context, com.facebook.ads.a.a aVar, JSONObject jSONObject, c cVar, @Nullable Bundle bundle, EnumSet<CacheFlag> enumSet, int i2) {
        Context context2 = context;
        JSONObject jSONObject2 = jSONObject;
        Bundle bundle2 = bundle;
        this.d = context2;
        this.j = aVar;
        this.a = cVar;
        this.c = jSONObject2;
        this.l = false;
        JSONObject jSONObject3 = jSONObject2.getJSONObject("video");
        this.n = jSONObject2.optString(UserDataStore.CITY);
        this.b = new a(context2);
        this.b.setVideoProgressReportIntervalMs(i2);
        a();
        this.b.getEventBus().a((T[]) new com.facebook.ads.internal.o.f[]{this.f, this.g, this.h, this.i});
        ArrayList arrayList = new ArrayList();
        AnonymousClass5 r0 = new com.facebook.ads.internal.d.b(1.0E-7d, -1.0d, 0.001d, false) {
            /* access modifiers changed from: protected */
            public void a(boolean z, boolean z2, com.facebook.ads.internal.d.c cVar) {
                f.this.f();
            }
        };
        arrayList.add(r0);
        if (bundle2 != null) {
            com.facebook.ads.internal.view.i.b bVar = new com.facebook.ads.internal.view.i.b(context, cVar, this.b, arrayList, this.n, bundle2.getBundle("logger"), null);
            this.m = bVar;
        } else {
            com.facebook.ads.internal.view.i.b bVar2 = new com.facebook.ads.internal.view.i.b(context, cVar, this.b, (List<com.facebook.ads.internal.d.b>) arrayList, this.n);
            this.m = bVar2;
        }
        this.j.a((n) this, (View) this.b);
        this.k = jSONObject3.getString((u.a(context) != u.a.c || !jSONObject3.has("videoHDURL") || jSONObject3.isNull("videoHDURL")) ? AudienceNetworkActivity.VIDEO_URL : "videoHDURL");
        if (enumSet.contains(CacheFlag.VIDEO)) {
            this.p = new com.facebook.ads.internal.h.b(context2);
            this.p.a(this.k);
            this.p.a((com.facebook.ads.internal.h.a) new com.facebook.ads.internal.h.a() {
                public void a() {
                    f.this.b.setVideoURI(f.this.h());
                }

                public void b() {
                    f.this.b.setVideoURI(f.this.h());
                }
            });
            return;
        }
        this.b.setVideoURI(h());
    }

    /* access modifiers changed from: private */
    public String h() {
        String str = "";
        com.facebook.ads.internal.h.b bVar = this.p;
        if (bVar != null) {
            String str2 = this.k;
            if (str2 != null) {
                str = bVar.c(str2);
            }
        }
        return TextUtils.isEmpty(str) ? this.k : str;
    }

    /* access modifiers changed from: protected */
    public void a() {
        if (!e && this.d == null) {
            throw new AssertionError();
        } else if (e || this.c != null) {
            JSONObject optJSONObject = this.c.optJSONObject("text");
            if (optJSONObject == null) {
                optJSONObject = new JSONObject();
            }
            this.b.a((com.facebook.ads.internal.view.i.a.b) new k(this.d));
            l lVar = new l(this.d);
            this.b.a((com.facebook.ads.internal.view.i.a.b) lVar);
            this.b.a((com.facebook.ads.internal.view.i.a.b) new d(lVar, d.a.INVSIBLE));
            this.b.a((com.facebook.ads.internal.view.i.a.b) new com.facebook.ads.internal.view.i.c.b(this.d));
            String b2 = b();
            if (b2 != null) {
                com.facebook.ads.internal.view.i.c.c cVar = new com.facebook.ads.internal.view.i.c.c(this.d, b2);
                LayoutParams layoutParams = new LayoutParams(-2, -2);
                layoutParams.addRule(12);
                layoutParams.addRule(9);
                cVar.setLayoutParams(layoutParams);
                cVar.setCountdownTextColor(-1);
                this.b.a((com.facebook.ads.internal.view.i.a.b) cVar);
            }
            if (this.c.has(InMobiNetworkValues.CTA) && !this.c.isNull(InMobiNetworkValues.CTA)) {
                JSONObject jSONObject = this.c.getJSONObject(InMobiNetworkValues.CTA);
                com.facebook.ads.internal.view.i.c.e eVar = new com.facebook.ads.internal.view.i.c.e(this.d, jSONObject.getString("url"), this.a, this.n, jSONObject.getString("text"));
                LayoutParams layoutParams2 = new LayoutParams(-2, -2);
                layoutParams2.addRule(10);
                layoutParams2.addRule(11);
                eVar.setLayoutParams(layoutParams2);
                this.b.a((com.facebook.ads.internal.view.i.a.b) eVar);
            }
            String d2 = d();
            if (!TextUtils.isEmpty(d2)) {
                this.b.a((com.facebook.ads.internal.view.i.a.b) new com.facebook.ads.internal.view.i.c.a(this.d, d2, this.n, new float[]{BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 8.0f, BitmapDescriptorFactory.HUE_RED}));
            }
            int c2 = c();
            if (c2 > 0) {
                i iVar = new i(this.d, c2, optJSONObject.optString("skipAdIn", "Skip Ad in"), optJSONObject.optString("skipAd", "Skip Ad"));
                LayoutParams layoutParams3 = new LayoutParams(-2, -2);
                layoutParams3.addRule(12);
                layoutParams3.addRule(11);
                iVar.setLayoutParams(layoutParams3);
                iVar.setPadding(0, 0, 0, 30);
                this.b.a((com.facebook.ads.internal.view.i.a.b) iVar);
            }
        } else {
            throw new AssertionError();
        }
    }

    public void a(Context context, com.facebook.ads.a.a aVar, c cVar, Bundle bundle, EnumSet<CacheFlag> enumSet) {
        try {
            JSONObject jSONObject = new JSONObject(bundle.getString("ad_response"));
            a(context, aVar, jSONObject, cVar, bundle, enumSet, jSONObject.optInt("video_time_polling_interval", 200));
        } catch (JSONException unused) {
            aVar.a((n) this, AdError.INTERNAL_ERROR);
        }
    }

    public void a(Context context, com.facebook.ads.a.a aVar, Map<String, Object> map, c cVar, EnumSet<CacheFlag> enumSet) {
        try {
            JSONObject jSONObject = (JSONObject) map.get("data");
            com.facebook.ads.internal.m.d dVar = (com.facebook.ads.internal.m.d) map.get("definition");
            a(context, aVar, jSONObject, cVar, null, enumSet, dVar == null ? 200 : dVar.k());
        } catch (JSONException unused) {
            aVar.a((n) this, AdError.INTERNAL_ERROR);
        }
    }

    /* access modifiers changed from: protected */
    public String b() {
        if (e || this.c != null) {
            String str = null;
            try {
                JSONObject jSONObject = this.c.getJSONObject("capabilities");
                if (jSONObject.has("countdown")) {
                    if (!jSONObject.isNull("countdown")) {
                        JSONObject jSONObject2 = jSONObject.getJSONObject("countdown");
                        if (jSONObject2.has(Columns.FORMAT)) {
                            str = jSONObject2.optString(Columns.FORMAT);
                        }
                    }
                }
                return str;
            } catch (Exception e2) {
                Log.w(String.valueOf(f.class), "Invalid JSON", e2);
                return null;
            }
        } else {
            throw new AssertionError();
        }
    }

    /* access modifiers changed from: protected */
    public int c() {
        if (e || this.c != null) {
            int i2 = -1;
            try {
                JSONObject jSONObject = this.c.getJSONObject("capabilities");
                if (jSONObject.has("skipButton")) {
                    if (!jSONObject.isNull("skipButton")) {
                        JSONObject jSONObject2 = jSONObject.getJSONObject("skipButton");
                        if (jSONObject2.has("skippableSeconds")) {
                            i2 = jSONObject2.getInt("skippableSeconds");
                        }
                    }
                }
                return i2;
            } catch (Exception e2) {
                Log.w(String.valueOf(f.class), "Invalid JSON", e2);
                return -1;
            }
        } else {
            throw new AssertionError();
        }
    }

    /* access modifiers changed from: protected */
    @Nullable
    public String d() {
        if (e || this.c != null) {
            String str = null;
            try {
                JSONObject jSONObject = this.c.getJSONObject("capabilities");
                if (jSONObject.has("adChoices")) {
                    if (!jSONObject.isNull("adChoices")) {
                        JSONObject jSONObject2 = jSONObject.getJSONObject("adChoices");
                        if (jSONObject2.has("url")) {
                            str = jSONObject2.getString("url");
                        }
                    }
                }
                return str;
            } catch (Exception e2) {
                Log.w(String.valueOf(f.class), "Invalid JSON", e2);
                return null;
            }
        } else {
            throw new AssertionError();
        }
    }

    public boolean e() {
        if (!this.l || this.b == null) {
            return false;
        }
        if (this.m.j() > 0) {
            this.b.a(this.m.j());
        }
        this.b.a(com.facebook.ads.internal.view.i.a.a.AUTO_STARTED);
        return true;
    }

    /* access modifiers changed from: protected */
    public void f() {
        c cVar = this.a;
        if (cVar != null && !this.o) {
            this.o = true;
            cVar.a(this.n, new HashMap());
            com.facebook.ads.a.a aVar = this.j;
            if (aVar != null) {
                aVar.c(this);
            }
        }
    }

    public Bundle g() {
        if (!(this.m == null || this.c == null)) {
            a aVar = this.b;
            if (!(aVar == null || aVar.getState() == com.facebook.ads.internal.view.i.d.d.IDLE)) {
                Bundle bundle = new Bundle();
                bundle.putBundle("logger", this.m.g());
                bundle.putString("ad_response", this.c.toString());
                return bundle;
            }
        }
        return null;
    }

    public String getClientToken() {
        return this.n;
    }

    public void onDestroy() {
        a aVar = this.b;
        if (aVar != null) {
            aVar.g();
            this.b.l();
        }
        this.j = null;
        this.a = null;
        this.k = null;
        this.l = false;
        this.n = null;
        this.b = null;
        this.m = null;
        this.c = null;
        this.d = null;
        this.o = false;
    }
}
