package com.facebook.ads.internal.b;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.security.NetworkSecurityPolicy;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.CacheFlag;
import com.facebook.ads.internal.adapters.AdAdapter;
import com.facebook.ads.internal.adapters.a;
import com.facebook.ads.internal.adapters.d;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.ads.internal.protocol.g;
import com.facebook.ads.internal.u.c.b;
import com.facebook.ads.internal.u.f;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public abstract class c implements b {
    private static final String i = c.class.getSimpleName();
    private static final Handler j = new Handler(Looper.getMainLooper());
    @SuppressLint({"StaticFieldLeak"})
    private static com.facebook.ads.internal.u.c k;
    private static d l;
    volatile boolean a;
    protected final Context b;
    protected a c;
    View d;
    @Nullable
    AdAdapter e;
    public AdAdapter f;
    public final com.facebook.ads.internal.s.c g;
    public final a h;
    private final com.facebook.ads.internal.u.c m;
    private final d n;
    /* access modifiers changed from: private */
    public com.facebook.ads.internal.m.c o;
    private com.facebook.ads.internal.u.b p;

    static {
        com.facebook.ads.internal.w.b.c.a();
    }

    public c(Context context, a aVar) {
        this.b = context.getApplicationContext();
        this.h = aVar;
        com.facebook.ads.internal.u.c cVar = k;
        if (cVar == null) {
            cVar = new com.facebook.ads.internal.u.c(this.b);
        }
        this.m = cVar;
        this.m.a((b) this);
        d dVar = l;
        if (dVar == null) {
            dVar = new d();
        }
        this.n = dVar;
        try {
            CookieManager.getInstance();
            if (VERSION.SDK_INT < 21) {
                CookieSyncManager.createInstance(this.b);
            }
        } catch (Exception e2) {
            Log.w(i, "Failed to initialize CookieManager.", e2);
        }
        com.facebook.ads.internal.n.a.b(this.b);
        this.g = com.facebook.ads.internal.s.d.a(this.b);
    }

    static /* synthetic */ void a(c cVar) {
        com.facebook.ads.internal.protocol.a a2;
        a aVar;
        AdErrorType adErrorType;
        cVar.e = null;
        com.facebook.ads.internal.m.c cVar2 = cVar.o;
        com.facebook.ads.internal.m.a e2 = cVar2.e();
        if (e2 == null) {
            aVar = cVar.c;
            adErrorType = AdErrorType.NO_FILL;
        } else {
            String a3 = e2.a();
            AdAdapter a4 = cVar.n.a(cVar2.a().b());
            if (a4 == null) {
                String str = i;
                StringBuilder sb = new StringBuilder();
                sb.append("Adapter does not exist: ");
                sb.append(a3);
                Log.e(str, sb.toString());
                cVar.i();
                return;
            } else if (cVar.h.a() != a4.getPlacementType()) {
                aVar = cVar.c;
                adErrorType = AdErrorType.INTERNAL_ERROR;
            } else {
                cVar.e = a4;
                com.facebook.ads.internal.m.d a5 = cVar2.a();
                HashMap hashMap = new HashMap();
                hashMap.put("data", e2.c());
                hashMap.put("definition", a5);
                hashMap.put(AudienceNetworkActivity.PLACEMENT_ID, cVar.h.a);
                hashMap.put(AudienceNetworkActivity.REQUEST_TIME, Long.valueOf(a5.a()));
                hashMap.put("data_model_type", e2.b());
                if (cVar.p == null) {
                    a2 = com.facebook.ads.internal.protocol.a.a(AdErrorType.UNKNOWN_ERROR, "environment is empty");
                    aVar = cVar.c;
                    aVar.a(a2);
                }
                cVar.a(a4, cVar2, e2, hashMap);
                return;
            }
        }
        a2 = com.facebook.ads.internal.protocol.a.a(adErrorType, "");
        aVar.a(a2);
    }

    /* access modifiers changed from: protected */
    public abstract void a();

    /* access modifiers changed from: 0000 */
    public void a(AdAdapter adAdapter) {
        if (adAdapter != null) {
            adAdapter.onDestroy();
        }
    }

    /* access modifiers changed from: protected */
    public abstract void a(AdAdapter adAdapter, com.facebook.ads.internal.m.c cVar, com.facebook.ads.internal.m.a aVar, Map<String, Object> map);

    public void a(a aVar) {
        this.c = aVar;
    }

    public synchronized void a(final com.facebook.ads.internal.protocol.a aVar) {
        j().post(new Runnable() {
            public void run() {
                c.this.c.a(aVar);
            }
        });
    }

    public synchronized void a(final f fVar) {
        if (com.facebook.ads.internal.r.a.U(this.b)) {
            com.facebook.ads.internal.protocol.a c2 = c();
            if (c2 != null) {
                Log.e(AudienceNetworkAds.TAG, c2.b());
                a(c2);
                return;
            }
        }
        j().post(new Runnable() {
            public void run() {
                com.facebook.ads.internal.m.c a2 = fVar.a();
                if (a2 == null || a2.a() == null) {
                    throw new IllegalStateException("invalid placement in response");
                }
                c.this.o = a2;
                c.this.i();
            }
        });
    }

    /* access modifiers changed from: protected */
    public void a(String str) {
        try {
            this.p = this.h.a(this.b, new g(this.b, str, this.h.a, this.h.b));
            this.m.a(this.p);
        } catch (com.facebook.ads.internal.protocol.b e2) {
            a(com.facebook.ads.internal.protocol.a.a(e2));
        }
    }

    public void a(boolean z) {
        if (z || this.a) {
            a(this.f);
            this.m.a();
            this.d = null;
            this.a = false;
        }
    }

    public com.facebook.ads.internal.m.d b() {
        com.facebook.ads.internal.m.c cVar = this.o;
        if (cVar == null) {
            return null;
        }
        return cVar.a();
    }

    public void b(String str) {
        a(str);
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public com.facebook.ads.internal.protocol.a c() {
        EnumSet<CacheFlag> enumSet = this.h.d;
        if (enumSet == null || enumSet.contains(CacheFlag.NONE) || d()) {
            return null;
        }
        return new com.facebook.ads.internal.protocol.a(AdErrorType.CLEAR_TEXT_SUPPORT_NOT_ALLOWED, "");
    }

    /* access modifiers changed from: 0000 */
    public boolean d() {
        boolean z = VERSION.SDK_INT < 24 || NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted() || NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted("127.0.0.1");
        if (!z) {
            com.facebook.ads.internal.w.h.a.b(this.b, "cache", com.facebook.ads.internal.w.h.b.W, new Exception("Cleartext http is not allowed."));
        }
        return z;
    }

    public void e() {
        if (this.f == null) {
            com.facebook.ads.internal.w.h.a.b(this.b, "api", com.facebook.ads.internal.w.h.b.e, new com.facebook.ads.internal.protocol.b(AdErrorType.NO_ADAPTER_ON_START, "Adapter is null on startAd"));
            this.c.a(com.facebook.ads.internal.protocol.a.a(AdErrorType.INTERNAL_ERROR, AdErrorType.INTERNAL_ERROR.getDefaultErrorMessage()));
        } else if (this.a) {
            com.facebook.ads.internal.w.h.a.b(this.b, "api", com.facebook.ads.internal.w.h.b.c, new com.facebook.ads.internal.protocol.b(AdErrorType.AD_ALREADY_STARTED, "ad already started"));
            this.c.a(com.facebook.ads.internal.protocol.a.a(AdErrorType.AD_ALREADY_STARTED, AdErrorType.AD_ALREADY_STARTED.getDefaultErrorMessage()));
        } else {
            if (!TextUtils.isEmpty(this.f.getClientToken())) {
                this.g.b(this.f.getClientToken());
            }
            this.a = true;
            a();
        }
    }

    public void f() {
        a(false);
    }

    public boolean g() {
        com.facebook.ads.internal.m.c cVar = this.o;
        return cVar == null || cVar.g();
    }

    public long h() {
        com.facebook.ads.internal.m.c cVar = this.o;
        if (cVar != null) {
            return cVar.h();
        }
        return -1;
    }

    /* access modifiers changed from: 0000 */
    public synchronized void i() {
        j.post(new Runnable() {
            public void run() {
                try {
                    c.a(c.this);
                } catch (Exception e) {
                    com.facebook.ads.internal.w.h.a.b(c.this.b, "api", com.facebook.ads.internal.w.h.b.q, e);
                }
            }
        });
    }

    /* access modifiers changed from: 0000 */
    public Handler j() {
        return j;
    }
}
