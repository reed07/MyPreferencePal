package com.inmobi.ads.c;

import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.facebook.internal.AnalyticsEvents;
import com.inmobi.ads.InMobiAdRequestStatus;
import com.inmobi.ads.InMobiAdRequestStatus.StatusCode;
import com.inmobi.ads.aj;
import com.inmobi.ads.bi;
import com.inmobi.ads.bj;
import com.inmobi.ads.f;
import com.inmobi.ads.i;
import com.inmobi.ads.i.e;
import com.inmobi.ads.p;
import com.inmobi.commons.core.configs.b;
import com.inmobi.commons.core.configs.b.c;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: AdPreFetcher */
public class a implements c {
    public static ConcurrentHashMap<bi, i> a = new ConcurrentHashMap<>(8, 0.9f, 3);
    public static com.inmobi.ads.c b = null;
    /* access modifiers changed from: private */
    public static final String d = "a";
    private static volatile a e;
    private static volatile a f;
    private static volatile a g;
    private static final Object h = new Object();
    private static final Object i = new Object();
    private static final Object j = new Object();
    public String c;

    /* renamed from: com.inmobi.ads.c.a$a reason: collision with other inner class name */
    /* compiled from: AdPreFetcher */
    static class C0042a implements e {
        private bi a;

        C0042a(bi biVar) {
            this.a = biVar;
        }

        public final void a(@NonNull i iVar) {
            a.d;
            a.a.remove(this.a);
        }

        public final void a(@NonNull i iVar, @NonNull InMobiAdRequestStatus inMobiAdRequestStatus) {
            a.d;
            new StringBuilder("onAdLoadFailed called. Status:").append(inMobiAdRequestStatus.getMessage());
            a.a.remove(this.a);
            if (StatusCode.NO_FILL.equals(inMobiAdRequestStatus.getStatusCode())) {
                iVar.d("PreLoadServerNoFill");
            }
        }
    }

    private static a d() {
        a aVar = e;
        if (aVar == null) {
            synchronized (h) {
                aVar = e;
                if (aVar == null) {
                    aVar = new a("banner");
                    e = aVar;
                }
            }
        }
        return aVar;
    }

    private static a e() {
        a aVar = f;
        if (aVar == null) {
            synchronized (i) {
                aVar = f;
                if (aVar == null) {
                    aVar = new a("int");
                    f = aVar;
                }
            }
        }
        return aVar;
    }

    private static a f() {
        a aVar = g;
        if (aVar == null) {
            synchronized (j) {
                aVar = g;
                if (aVar == null) {
                    aVar = new a(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE);
                    g = aVar;
                }
            }
        }
        return aVar;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x003e  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0043  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0048  */
    @android.support.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.inmobi.ads.c.a a(java.lang.String r2) {
        /*
            int r0 = r2.hashCode()
            r1 = -1396342996(0xffffffffacc57f2c, float:-5.6131957E-12)
            if (r0 == r1) goto L_0x0028
            r1 = -1052618729(0xffffffffc1425017, float:-12.144553)
            if (r0 == r1) goto L_0x001e
            r1 = 104431(0x197ef, float:1.46339E-40)
            if (r0 == r1) goto L_0x0014
            goto L_0x0032
        L_0x0014:
            java.lang.String r0 = "int"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x0032
            r2 = 2
            goto L_0x0033
        L_0x001e:
            java.lang.String r0 = "native"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x0032
            r2 = 1
            goto L_0x0033
        L_0x0028:
            java.lang.String r0 = "banner"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x0032
            r2 = 0
            goto L_0x0033
        L_0x0032:
            r2 = -1
        L_0x0033:
            switch(r2) {
                case 0: goto L_0x0048;
                case 1: goto L_0x0043;
                case 2: goto L_0x003e;
                default: goto L_0x0036;
            }
        L_0x0036:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "Unknown adType passed"
            r2.<init>(r0)
            throw r2
        L_0x003e:
            com.inmobi.ads.c.a r2 = e()
            return r2
        L_0x0043:
            com.inmobi.ads.c.a r2 = f()
            return r2
        L_0x0048:
            com.inmobi.ads.c.a r2 = d()
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.c.a.a(java.lang.String):com.inmobi.ads.c.a");
    }

    /* access modifiers changed from: private */
    @Nullable
    public static i b(String str, @NonNull Context context, @NonNull bi biVar) {
        char c2 = 65535;
        try {
            int hashCode = str.hashCode();
            if (hashCode != -1396342996) {
                if (hashCode != -1052618729) {
                    if (hashCode == 104431) {
                        if (str.equals("int")) {
                            c2 = 1;
                        }
                    }
                } else if (str.equals(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE)) {
                    c2 = 2;
                }
            } else if (str.equals("banner")) {
                c2 = 0;
            }
            switch (c2) {
                case 0:
                    return p.a(context, biVar, null, 1);
                case 1:
                    return com.inmobi.ads.ac.a.a(com.inmobi.commons.a.a.b(), biVar, null);
                case 2:
                    return aj.a(context, biVar, null, 1);
            }
        } catch (IllegalStateException e2) {
            e2.getMessage();
        }
        return null;
    }

    a(String str) {
        this.c = str;
        b = new com.inmobi.ads.c();
        b.a().a((com.inmobi.commons.core.configs.a) b, (c) this);
        com.inmobi.commons.core.e.b.a().a("ads", b.l);
    }

    public final void a(com.inmobi.commons.core.configs.a aVar) {
        b = (com.inmobi.ads.c) aVar;
        com.inmobi.commons.core.e.b.a().a("ads", b.l);
    }

    private void g() {
        Iterator it = a.entrySet().iterator();
        while (it.hasNext()) {
            try {
                Entry entry = (Entry) it.next();
                final i iVar = (i) entry.getValue();
                if (iVar.h()) {
                    StringBuilder sb = new StringBuilder("cleanUpExpiredCachedAdUnits. pid:");
                    sb.append(((bi) entry.getKey()).a);
                    sb.append(" tp:");
                    sb.append(((bi) entry.getKey()).b);
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        public final void run() {
                            try {
                                iVar.v();
                            } catch (Exception e) {
                                a.d;
                                new StringBuilder("Encountered an unexpected error clearing the ad unit: ").append(e.getMessage());
                                Logger.a(InternalLogLevel.DEBUG, "InMobi", "SDK encountered an unexpected error clearing an old ad");
                                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                            }
                        }
                    });
                    it.remove();
                }
            } catch (Exception e2) {
                new StringBuilder("SDK encountered an unexpected error in expiring ad units; ").append(e2.getMessage());
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
                return;
            }
        }
    }

    public final void b() {
        h();
        g();
    }

    public final void a(final bi biVar) {
        if (b.c(this.c).a) {
            new Thread() {
                public final void run() {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(biVar);
                    int a2 = bj.a().a((List<bi>) arrayList, a.b.c(a.this.c).c);
                    if (a2 > 0) {
                        HashMap hashMap = new HashMap();
                        hashMap.put("count", Integer.valueOf(a2));
                        hashMap.put("type", a.this.c);
                        hashMap.put("plId", Long.valueOf(biVar.a));
                        com.inmobi.commons.core.e.b.a();
                        com.inmobi.commons.core.e.b.a("ads", "PreLoadPidOverflow", hashMap);
                    }
                }
            }.start();
        }
    }

    private void h() {
        if (b.c(this.c).a) {
            bj.a();
            int a2 = bj.a(b.c(this.c).b, this.c);
            if (a2 > 0) {
                try {
                    HashMap hashMap = new HashMap();
                    hashMap.put("type", this.c);
                    hashMap.put("count", Integer.valueOf(a2));
                    com.inmobi.commons.core.e.b.a();
                    com.inmobi.commons.core.e.b.a("ads", "PreLoadPidExpiry", hashMap);
                } catch (Exception e2) {
                    StringBuilder sb = new StringBuilder("Error in submitting telemetry event : (");
                    sb.append(e2.getMessage());
                    sb.append(")");
                }
            }
        }
    }

    @NonNull
    public static String a(Map<String, String> map) {
        if (map == null) {
            return "";
        }
        if (((String) map.get("tp")) == null) {
            return "";
        }
        return (String) map.get("tp");
    }

    public static void a(String str, Map<String, Object> map, f fVar) {
        HashMap hashMap = new HashMap();
        hashMap.put("type", fVar.e);
        hashMap.put("plId", Long.valueOf(fVar.a));
        hashMap.put("isPreloaded", Integer.valueOf(1));
        hashMap.put("networkType", Integer.valueOf(com.inmobi.commons.core.utilities.b.b.a()));
        hashMap.put("ts", Long.valueOf(System.currentTimeMillis()));
        if (map.get("clientRequestId") == null) {
            hashMap.put("clientRequestId", fVar.i);
        }
        for (Entry entry : map.entrySet()) {
            hashMap.put(entry.getKey(), entry.getValue());
        }
        try {
            com.inmobi.commons.core.e.b.a();
            com.inmobi.commons.core.e.b.a("ads", str, hashMap);
        } catch (Exception e2) {
            StringBuilder sb = new StringBuilder("Error in submitting telemetry event : (");
            sb.append(e2.getMessage());
            sb.append(")");
        }
    }

    public final void a() {
        Application application = (Application) com.inmobi.commons.a.a.b();
        if (application != null) {
            application.registerComponentCallbacks(new ComponentCallbacks2() {
                public final void onConfigurationChanged(Configuration configuration) {
                }

                public final void onLowMemory() {
                }

                public final void onTrimMemory(int i) {
                    if (i == 15) {
                        a.a(a.this);
                    }
                }
            });
        }
        h();
        g();
        if (b.c(this.c).a) {
            bj.a();
            ArrayList arrayList = (ArrayList) bj.a(this.c);
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                final bi biVar = (bi) arrayList.get(i2);
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    private e c;

                    public final void run() {
                        try {
                            Context b2 = com.inmobi.commons.a.a.b();
                            if (b2 != null) {
                                a.d;
                                StringBuilder sb = new StringBuilder("preFetchAdUnit. pid:");
                                sb.append(biVar.a);
                                sb.append(" tp:");
                                sb.append(biVar.b);
                                if (biVar.c == null && biVar.b != null) {
                                    HashMap hashMap = new HashMap();
                                    hashMap.put("tp", biVar.b);
                                    biVar.c = hashMap;
                                }
                                this.c = new C0042a(biVar);
                                i a2 = a.b(a.this.c, b2, biVar);
                                if (a2 != null) {
                                    a2.e = biVar.d;
                                    a2.f = biVar.c;
                                    a2.n = true;
                                    a2.q = this.c;
                                    a2.a(true);
                                }
                            }
                        } catch (Exception e) {
                            a.d;
                            new StringBuilder("SDK encountered an unexpected error preloading ad units; ").append(e.getMessage());
                            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                        }
                    }
                });
            }
        }
    }

    static /* synthetic */ void a(a aVar) {
        Context b2 = com.inmobi.commons.a.a.b();
        if (b2 != null) {
            new Handler(b2.getMainLooper()).post(new Runnable() {
                public final void run() {
                    try {
                        a.d;
                        Iterator it = a.a.entrySet().iterator();
                        while (it.hasNext()) {
                            ((i) ((Entry) it.next()).getValue()).v();
                            it.remove();
                        }
                    } catch (Exception e) {
                        a.d;
                        new StringBuilder("SDK encountered unexpected error in flushing ad unit cache; ").append(e.getMessage());
                        com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                    }
                }
            });
        }
    }
}
