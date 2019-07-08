package com.facebook.ads.internal.adapters;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import com.facebook.ads.AdError;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.CacheFlag;
import com.facebook.ads.InterstitialAdActivity;
import com.facebook.ads.internal.a.e;
import com.facebook.ads.internal.adapters.b.d;
import com.facebook.ads.internal.adapters.b.k;
import com.facebook.ads.internal.adapters.b.l;
import com.facebook.ads.internal.adapters.b.n;
import com.facebook.ads.internal.adapters.b.o;
import com.facebook.ads.internal.adapters.c.a.b;
import com.facebook.ads.internal.protocol.AdPlacementType;
import com.facebook.ads.internal.s.c;
import com.facebook.ads.internal.settings.a.C0009a;
import com.facebook.ads.internal.view.a;
import com.facebook.ads.internal.view.c.f;
import com.facebook.ads.internal.w.b.x;
import com.facebook.appevents.UserDataStore;
import com.google.android.exoplayer2.C;
import com.mopub.mobileads.BaseVideoPlayerActivity;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.json.JSONObject;

public class g implements AdAdapter {
    private static final ConcurrentMap<String, a> a = new ConcurrentHashMap();
    /* access modifiers changed from: private */
    public final String b = UUID.randomUUID().toString();
    private String c;
    private long d;
    /* access modifiers changed from: private */
    public Context e;
    private o f;
    /* access modifiers changed from: private */
    public InterstitialAdapterListener g;
    private b h;
    /* access modifiers changed from: private */
    public boolean i;
    private l j;
    /* access modifiers changed from: private */
    public f k = f.UNSPECIFIED;
    private k l;
    private C0009a m;
    /* access modifiers changed from: private */
    public boolean n;
    private String o;
    private String p;

    private static int a(Context context, d dVar) {
        return com.facebook.ads.internal.r.a.S(context) ? Math.min(x.a.widthPixels, dVar.h()) : dVar.h();
    }

    public static a a(String str) {
        return (a) a.get(str);
    }

    public static void a(a aVar) {
        for (Entry entry : a.entrySet()) {
            if (entry.getValue() == aVar) {
                a.remove(entry.getKey());
            }
        }
    }

    private static int b(Context context, d dVar) {
        return com.facebook.ads.internal.r.a.S(context) ? Math.min(x.a.heightPixels, dVar.i()) : dVar.i();
    }

    public void a(Context context, InterstitialAdapterListener interstitialAdapterListener, Map<String, Object> map, c cVar, final EnumSet<CacheFlag> enumSet, String str) {
        com.facebook.ads.internal.h.a aVar;
        com.facebook.ads.internal.h.b bVar;
        this.e = context;
        this.g = interstitialAdapterListener;
        this.c = (String) map.get(AudienceNetworkActivity.PLACEMENT_ID);
        this.d = ((Long) map.get(AudienceNetworkActivity.REQUEST_TIME)).longValue();
        this.o = str;
        JSONObject jSONObject = (JSONObject) map.get("data");
        this.p = jSONObject.optString(UserDataStore.CITY);
        com.facebook.ads.internal.m.d dVar = (com.facebook.ads.internal.m.d) map.get("definition");
        if (jSONObject.has("markup")) {
            this.m = C0009a.INTERSTITIAL_WEB_VIEW;
            this.j = l.a(jSONObject);
            if (e.a(this.e, this.j, cVar)) {
                interstitialAdapterListener.onInterstitialError(this, AdError.internalError(AdError.INTERNAL_ERROR_2006));
                return;
            }
            this.f = new o(this.e, this.b, this, this.g);
            this.f.a();
            Map e2 = this.j.e();
            if (e2.containsKey("orientation")) {
                this.k = f.a(Integer.parseInt((String) e2.get("orientation")));
            }
            this.i = true;
            InterstitialAdapterListener interstitialAdapterListener2 = this.g;
            if (interstitialAdapterListener2 != null) {
                interstitialAdapterListener2.onInterstitialAdLoaded(this);
            }
        } else if (jSONObject.has("video")) {
            this.m = C0009a.INTERSTITIAL_OLD_NATIVE_VIDEO;
            this.f = new o(this.e, this.b, this, this.g);
            this.f.a();
            final h hVar = new h();
            hVar.a(this.e, (com.facebook.ads.a.a) new com.facebook.ads.a.a() {
                public void a(n nVar) {
                    g.this.i = true;
                    if (g.this.g != null) {
                        g.this.g.onInterstitialAdLoaded(g.this);
                    }
                }

                public void a(n nVar, View view) {
                    g.this.k = hVar.i();
                    g.a.put(g.this.b, hVar);
                }

                public void a(n nVar, AdError adError) {
                    hVar.j();
                    g.this.g.onInterstitialError(g.this, adError);
                }

                public void b(n nVar) {
                    g.this.g.onInterstitialAdClicked(g.this, "", true);
                }

                public void c(n nVar) {
                    g.this.g.onInterstitialLoggingImpression(g.this);
                }

                public void d(n nVar) {
                }
            }, map, cVar, enumSet);
        } else {
            this.l = k.a(jSONObject, this.e);
            this.l.a(this.o);
            if (dVar != null) {
                this.l.a(dVar.k());
            }
            if (this.l.d().size() == 0) {
                this.g.onInterstitialError(this, AdError.internalError(AdError.INTERNAL_ERROR_2006));
                com.facebook.ads.internal.w.h.a.b(this.e, "api", com.facebook.ads.internal.w.h.b.j, new Exception("Internal Error 2006 without a valid AdInfo."));
                return;
            }
            this.f = new o(this.e, this.b, this, this.g);
            this.f.a();
            if (jSONObject.has("carousel")) {
                this.m = C0009a.INTERSTITIAL_NATIVE_CAROUSEL;
                bVar = new com.facebook.ads.internal.h.b(this.e);
                bVar.a(this.l.a().b(), com.facebook.ads.internal.view.e.c.a, com.facebook.ads.internal.view.e.c.a);
                List<l> d2 = this.l.d();
                boolean contains = enumSet.contains(CacheFlag.VIDEO);
                for (l lVar : d2) {
                    bVar.a(lVar.c().g(), b(this.e, lVar.c()), a(this.e, lVar.c()));
                    if (contains && !TextUtils.isEmpty(lVar.c().a())) {
                        bVar.a(lVar.c().g());
                    }
                }
                aVar = new com.facebook.ads.internal.h.a() {
                    private void a(boolean z) {
                        boolean z2 = !enumSet.contains(CacheFlag.NONE);
                        if (z || !com.facebook.ads.internal.r.a.T(g.this.e)) {
                            g.this.n = z && z2;
                            g.this.i = true;
                            g.this.g.onInterstitialAdLoaded(g.this);
                            return;
                        }
                        g.this.g.onInterstitialError(g.this, AdError.CACHE_ERROR);
                    }

                    public void a() {
                        a(true);
                    }

                    public void b() {
                        if (com.facebook.ads.internal.r.a.R(g.this.e)) {
                            com.facebook.ads.internal.w.h.a.b(g.this.e, "cache", com.facebook.ads.internal.w.h.b.T, new Exception("Interstitial carousel cache failed"));
                        }
                        a(false);
                    }
                };
            } else if (jSONObject.has("playable_data")) {
                this.m = C0009a.INTERSTITIAL_NATIVE_PLAYABLE;
                o a2 = o.a(this.l);
                n j2 = a2.f().j();
                this.k = j2 != null ? j2.f() : f.UNSPECIFIED;
                this.h = new b() {
                    private void b() {
                        g.this.i = true;
                        g.this.g.onInterstitialAdLoaded(g.this);
                    }

                    public void a() {
                        g.this.n = !enumSet.contains(CacheFlag.NONE);
                        b();
                    }

                    public void a(AdError adError) {
                        if (!com.facebook.ads.internal.r.a.T(g.this.e)) {
                            b();
                        } else {
                            g.this.g.onInterstitialError(g.this, AdError.CACHE_ERROR);
                        }
                    }
                };
                Context context2 = this.e;
                com.facebook.ads.internal.adapters.c.a.a(context2, a2, com.facebook.ads.internal.r.a.T(context2), this.h);
            } else if (jSONObject.has(BaseVideoPlayerActivity.VIDEO_URL)) {
                this.m = C0009a.INTERSTITIAL_NATIVE_VIDEO;
                bVar = new com.facebook.ads.internal.h.b(this.e);
                d c2 = ((l) this.l.d().get(0)).c();
                bVar.a(c2.g(), b(this.e, c2), a(this.e, c2));
                bVar.a(this.l.a().b(), com.facebook.ads.internal.view.e.c.a, com.facebook.ads.internal.view.e.c.a);
                if (enumSet.contains(CacheFlag.VIDEO)) {
                    bVar.a(c2.a());
                }
                aVar = new com.facebook.ads.internal.h.a() {
                    private void a(boolean z) {
                        if (z || !com.facebook.ads.internal.r.a.T(g.this.e)) {
                            g.this.n = z && enumSet.contains(CacheFlag.VIDEO);
                            g.this.i = true;
                            g.this.g.onInterstitialAdLoaded(g.this);
                            return;
                        }
                        g.this.g.onInterstitialError(g.this, AdError.CACHE_ERROR);
                    }

                    public void a() {
                        a(true);
                    }

                    public void b() {
                        if (com.facebook.ads.internal.r.a.R(g.this.e)) {
                            com.facebook.ads.internal.w.h.a.b(g.this.e, "cache", com.facebook.ads.internal.w.h.b.V, new Exception("Interstitial video cache failed"));
                        }
                        a(false);
                    }
                };
            } else {
                this.m = C0009a.INTERSTITIAL_NATIVE_IMAGE;
                bVar = new com.facebook.ads.internal.h.b(this.e);
                d c3 = ((l) this.l.d().get(0)).c();
                bVar.a(c3.g(), b(this.e, c3), a(this.e, c3));
                bVar.a(this.l.a().b(), com.facebook.ads.internal.view.e.c.a, com.facebook.ads.internal.view.e.c.a);
                aVar = new com.facebook.ads.internal.h.a() {
                    private void a(boolean z) {
                        if (z || !com.facebook.ads.internal.r.a.T(g.this.e)) {
                            g.this.i = true;
                            g.this.g.onInterstitialAdLoaded(g.this);
                            return;
                        }
                        g.this.g.onInterstitialError(g.this, AdError.CACHE_ERROR);
                    }

                    public void a() {
                        a(true);
                    }

                    public void b() {
                        if (com.facebook.ads.internal.r.a.R(g.this.e)) {
                            com.facebook.ads.internal.w.h.a.b(g.this.e, "cache", com.facebook.ads.internal.w.h.b.U, new Exception("Interstitial image cache failed"));
                        }
                        a(false);
                    }
                };
            }
            bVar.a(aVar);
        }
    }

    public boolean a() {
        int i2 = 0;
        if (!this.i) {
            InterstitialAdapterListener interstitialAdapterListener = this.g;
            if (interstitialAdapterListener != null) {
                interstitialAdapterListener.onInterstitialError(this, AdError.SHOW_CALLED_BEFORE_LOAD_ERROR);
            }
            return false;
        }
        Intent intent = new Intent(this.e, AudienceNetworkActivity.getAdActivity());
        String str = AudienceNetworkActivity.PREDEFINED_ORIENTATION_KEY;
        int rotation = ((WindowManager) this.e.getSystemService("window")).getDefaultDisplay().getRotation();
        if (this.k == f.UNSPECIFIED) {
            i2 = -1;
        } else if (this.k == f.LANDSCAPE) {
            switch (rotation) {
                case 2:
                case 3:
                    i2 = 8;
                    break;
            }
        } else {
            i2 = rotation != 2 ? 1 : 9;
        }
        intent.putExtra(str, i2);
        intent.putExtra(AudienceNetworkActivity.AUDIENCE_NETWORK_UNIQUE_ID_EXTRA, this.b);
        intent.putExtra(AudienceNetworkActivity.PLACEMENT_ID, this.c);
        intent.putExtra(AudienceNetworkActivity.REQUEST_TIME, this.d);
        intent.putExtra(AudienceNetworkActivity.VIEW_TYPE, this.m);
        intent.putExtra(AudienceNetworkActivity.USE_CACHE, this.n);
        k kVar = this.l;
        if (kVar != null) {
            intent.putExtra("ad_data_bundle", kVar);
        } else {
            l lVar = this.j;
            if (lVar != null) {
                lVar.a(intent);
            }
        }
        intent.addFlags(C.ENCODING_PCM_MU_LAW);
        try {
            this.e.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            intent.setClass(this.e, InterstitialAdActivity.class);
            this.e.startActivity(intent);
        }
        return true;
    }

    @Nullable
    public String getClientToken() {
        return this.p;
    }

    public final AdPlacementType getPlacementType() {
        return AdPlacementType.INTERSTITIAL;
    }

    public void onDestroy() {
        o oVar = this.f;
        if (oVar != null) {
            oVar.b();
        }
    }
}
