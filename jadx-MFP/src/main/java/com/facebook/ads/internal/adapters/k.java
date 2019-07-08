package com.facebook.ads.internal.adapters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import com.facebook.ads.AdError;
import com.facebook.ads.internal.adapters.b.d;
import com.facebook.ads.internal.adapters.b.f;
import com.facebook.ads.internal.adapters.b.n;
import com.facebook.ads.internal.adapters.b.o;
import com.facebook.ads.internal.adapters.b.q;
import com.facebook.ads.internal.settings.a.C0009a;
import com.facebook.ads.internal.view.e.c;
import com.facebook.ads.internal.w.b.x;
import java.lang.ref.WeakReference;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

public class k extends s {
    private final String c = UUID.randomUUID().toString();
    private final AtomicBoolean d = new AtomicBoolean();
    /* access modifiers changed from: private */
    public Context e;
    private t f;
    private String g;
    private String h;
    private long i;
    /* access modifiers changed from: private */
    public com.facebook.ads.internal.adapters.b.a j;
    private u k;
    private C0009a l;
    private String m;
    private a n;

    private static class a implements com.facebook.ads.internal.adapters.c.a.b {
        final WeakReference<k> a;
        final WeakReference<t> b;
        final AtomicBoolean c;

        a(k kVar, t tVar, AtomicBoolean atomicBoolean) {
            this.a = new WeakReference<>(kVar);
            this.b = new WeakReference<>(tVar);
            this.c = atomicBoolean;
        }

        public void a() {
            this.c.set(true);
            if (this.b.get() != null && this.a.get() != null) {
                ((t) this.b.get()).a((s) this.a.get());
            }
        }

        public void a(AdError adError) {
            if (this.b.get() != null && this.a.get() != null) {
                ((t) this.b.get()).a((s) this.a.get(), adError);
            }
        }
    }

    private static abstract class b implements com.facebook.ads.internal.h.a {
        final WeakReference<k> d;
        final WeakReference<t> e;
        final com.facebook.ads.internal.h.b f;
        final AtomicBoolean g;
        final boolean h;

        private b(k kVar, t tVar, com.facebook.ads.internal.h.b bVar, AtomicBoolean atomicBoolean, boolean z) {
            this.d = new WeakReference<>(kVar);
            this.e = new WeakReference<>(tVar);
            this.f = bVar;
            this.g = atomicBoolean;
            this.h = z;
        }

        public void a() {
            a(true, (k) this.d.get(), (t) this.e.get());
        }

        /* access modifiers changed from: 0000 */
        public abstract void a(boolean z, @Nullable k kVar, @Nullable t tVar);

        public void b() {
            if (this.e.get() != null && this.d.get() != null) {
                if (this.h) {
                    ((t) this.e.get()).a((s) this.d.get(), AdError.CACHE_ERROR);
                } else {
                    a(false, (k) this.d.get(), (t) this.e.get());
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(Context context, boolean z, q qVar) {
        this.n = new a(this, this.f, this.d);
        com.facebook.ads.internal.adapters.c.a.a(context, o.a(qVar), z, this.n);
    }

    private void a(com.facebook.ads.internal.h.b bVar, q qVar) {
        bVar.a(qVar.f().b(), c.a, c.a);
        bVar.a(qVar.j().a());
        String g2 = qVar.j().g();
        Context context = this.e;
        d j2 = qVar.j();
        int min = com.facebook.ads.internal.r.a.S(context) ? Math.min(x.a.heightPixels, j2.i()) : j2.i();
        Context context2 = this.e;
        d j3 = qVar.j();
        bVar.a(g2, min, com.facebook.ads.internal.r.a.S(context2) ? Math.min(x.a.widthPixels, j3.h()) : j3.h());
        for (String a2 : qVar.k().d()) {
            bVar.a(a2, -1, -1);
        }
    }

    /* access modifiers changed from: private */
    public static boolean b(q qVar, boolean z) {
        n j2 = qVar.j().j();
        return j2 != null && (!z || !j2.i());
    }

    public int a() {
        if (this.j == null) {
            return -1;
        }
        if (this.l != C0009a.REWARDED_VIDEO_CHOOSE_YOUR_OWN_AD) {
            return ((q) this.j).j().d();
        }
        int i2 = 0;
        for (q j2 : ((f) this.j).j()) {
            int d2 = j2.j().d();
            if (i2 < d2) {
                i2 = d2;
            }
        }
        return i2;
    }

    /* JADX WARNING: type inference failed for: r0v11, types: [com.facebook.ads.internal.adapters.k$2] */
    /* JADX WARNING: type inference failed for: r10v5, types: [com.facebook.ads.internal.h.a] */
    /* JADX WARNING: type inference failed for: r0v12, types: [com.facebook.ads.internal.adapters.k$1] */
    /* JADX WARNING: type inference failed for: r0v13, types: [com.facebook.ads.internal.adapters.k$2] */
    /* JADX WARNING: type inference failed for: r0v14, types: [com.facebook.ads.internal.adapters.k$1] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v13, types: [com.facebook.ads.internal.adapters.k$2]
  assigns: [com.facebook.ads.internal.adapters.k$2, com.facebook.ads.internal.adapters.k$1]
  uses: [com.facebook.ads.internal.adapters.k$2, com.facebook.ads.internal.h.a, com.facebook.ads.internal.adapters.k$1]
  mth insns count: 113
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 3 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(android.content.Context r10, com.facebook.ads.internal.adapters.t r11, java.util.Map<java.lang.String, java.lang.Object> r12, boolean r13, java.lang.String r14) {
        /*
            r9 = this;
            r9.e = r10
            r9.f = r11
            java.util.concurrent.atomic.AtomicBoolean r0 = r9.d
            r1 = 0
            r0.set(r1)
            java.lang.String r0 = "placementId"
            java.lang.Object r0 = r12.get(r0)
            java.lang.String r0 = (java.lang.String) r0
            r9.h = r0
            java.lang.String r0 = "requestTime"
            java.lang.Object r0 = r12.get(r0)
            java.lang.Long r0 = (java.lang.Long) r0
            long r2 = r0.longValue()
            r9.i = r2
            java.lang.String r0 = "definition"
            java.lang.Object r0 = r12.get(r0)
            com.facebook.ads.internal.m.d r0 = (com.facebook.ads.internal.m.d) r0
            int r0 = r0.k()
            r9.m = r14
            java.lang.String r14 = r9.h
            if (r14 == 0) goto L_0x003d
            java.lang.String r2 = "_"
            java.lang.String[] r14 = r14.split(r2)
            r14 = r14[r1]
            goto L_0x003f
        L_0x003d:
            java.lang.String r14 = ""
        L_0x003f:
            r9.g = r14
            java.lang.String r14 = "choose_your_own_ad_rewarded_video"
            java.lang.String r1 = "data_model_type"
            java.lang.Object r1 = r12.get(r1)
            boolean r14 = r14.equals(r1)
            java.lang.String r1 = "data"
            java.lang.Object r12 = r12.get(r1)
            org.json.JSONObject r12 = (org.json.JSONObject) r12
            com.facebook.ads.internal.adapters.b.a r12 = com.facebook.ads.internal.adapters.b.a.a(r14, r12)
            r9.j = r12
            if (r14 == 0) goto L_0x0060
            com.facebook.ads.internal.settings.a$a r12 = com.facebook.ads.internal.settings.a.C0009a.REWARDED_VIDEO_CHOOSE_YOUR_OWN_AD
            goto L_0x0070
        L_0x0060:
            com.facebook.ads.internal.adapters.b.a r12 = r9.j
            com.facebook.ads.internal.adapters.b.q r12 = (com.facebook.ads.internal.adapters.b.q) r12
            r14 = 1
            boolean r12 = b(r12, r14)
            if (r12 == 0) goto L_0x006e
            com.facebook.ads.internal.settings.a$a r12 = com.facebook.ads.internal.settings.a.C0009a.REWARDED_PLAYABLE
            goto L_0x0070
        L_0x006e:
            com.facebook.ads.internal.settings.a$a r12 = com.facebook.ads.internal.settings.a.C0009a.REWARDED_VIDEO
        L_0x0070:
            r9.l = r12
            com.facebook.ads.internal.adapters.b.a r12 = r9.j
            java.lang.String r14 = r9.m
            r12.b(r14)
            com.facebook.ads.internal.adapters.b.a r12 = r9.j
            r12.a(r0)
            com.facebook.ads.internal.settings.a$a r12 = r9.l
            com.facebook.ads.internal.settings.a$a r14 = com.facebook.ads.internal.settings.a.C0009a.REWARDED_VIDEO
            if (r12 != r14) goto L_0x00a2
            com.facebook.ads.internal.adapters.b.a r12 = r9.j
            com.facebook.ads.internal.adapters.b.q r12 = (com.facebook.ads.internal.adapters.b.q) r12
            com.facebook.ads.internal.adapters.b.d r12 = r12.j()
            java.lang.String r12 = r12.a()
            boolean r12 = android.text.TextUtils.isEmpty(r12)
            if (r12 == 0) goto L_0x00a2
            com.facebook.ads.internal.adapters.t r10 = r9.f
            r11 = 2003(0x7d3, float:2.807E-42)
            com.facebook.ads.AdError r11 = com.facebook.ads.AdError.internalError(r11)
            r10.a(r9, r11)
            return
        L_0x00a2:
            com.facebook.ads.internal.adapters.u r12 = new com.facebook.ads.internal.adapters.u
            java.lang.String r14 = r9.c
            r12.<init>(r14, r9, r11)
            r9.k = r12
            android.content.Context r11 = r9.e
            android.support.v4.content.LocalBroadcastManager r11 = android.support.v4.content.LocalBroadcastManager.getInstance(r11)
            com.facebook.ads.internal.adapters.u r12 = r9.k
            android.content.IntentFilter r14 = r12.a()
            r11.registerReceiver(r12, r14)
            com.facebook.ads.internal.settings.a$a r11 = r9.l
            com.facebook.ads.internal.settings.a$a r12 = com.facebook.ads.internal.settings.a.C0009a.REWARDED_VIDEO
            if (r11 != r12) goto L_0x00e0
            com.facebook.ads.internal.h.b r11 = new com.facebook.ads.internal.h.b
            r11.<init>(r10)
            com.facebook.ads.internal.adapters.b.a r10 = r9.j
            r7 = r10
            com.facebook.ads.internal.adapters.b.q r7 = (com.facebook.ads.internal.adapters.b.q) r7
            r9.a(r11, r7)
            com.facebook.ads.internal.adapters.k$1 r10 = new com.facebook.ads.internal.adapters.k$1
            com.facebook.ads.internal.adapters.t r3 = r9.f
            java.util.concurrent.atomic.AtomicBoolean r5 = r9.d
            r0 = r10
            r1 = r9
            r2 = r9
            r4 = r11
            r6 = r13
            r8 = r13
            r0.<init>(r2, r3, r4, r5, r6, r7, r8)
        L_0x00dc:
            r11.a(r10)
            goto L_0x011f
        L_0x00e0:
            com.facebook.ads.internal.settings.a$a r11 = r9.l
            com.facebook.ads.internal.settings.a$a r12 = com.facebook.ads.internal.settings.a.C0009a.REWARDED_PLAYABLE
            if (r11 != r12) goto L_0x00ee
            com.facebook.ads.internal.adapters.b.a r11 = r9.j
            com.facebook.ads.internal.adapters.b.q r11 = (com.facebook.ads.internal.adapters.b.q) r11
            r9.a(r10, r13, r11)
            goto L_0x011f
        L_0x00ee:
            com.facebook.ads.internal.h.b r11 = new com.facebook.ads.internal.h.b
            r11.<init>(r10)
            com.facebook.ads.internal.adapters.b.a r10 = r9.j
            r7 = r10
            com.facebook.ads.internal.adapters.b.f r7 = (com.facebook.ads.internal.adapters.b.f) r7
            java.util.List r10 = r7.j()
            java.util.Iterator r10 = r10.iterator()
        L_0x0100:
            boolean r12 = r10.hasNext()
            if (r12 == 0) goto L_0x0110
            java.lang.Object r12 = r10.next()
            com.facebook.ads.internal.adapters.b.q r12 = (com.facebook.ads.internal.adapters.b.q) r12
            r9.a(r11, r12)
            goto L_0x0100
        L_0x0110:
            com.facebook.ads.internal.adapters.k$2 r10 = new com.facebook.ads.internal.adapters.k$2
            com.facebook.ads.internal.adapters.t r3 = r9.f
            java.util.concurrent.atomic.AtomicBoolean r5 = r9.d
            r0 = r10
            r1 = r9
            r2 = r9
            r4 = r11
            r6 = r13
            r0.<init>(r2, r3, r4, r5, r6, r7)
            goto L_0x00dc
        L_0x011f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.adapters.k.a(android.content.Context, com.facebook.ads.internal.adapters.t, java.util.Map, boolean, java.lang.String):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x00e9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean b() {
        /*
            r6 = this;
            java.util.concurrent.atomic.AtomicBoolean r0 = r6.d
            boolean r0 = r0.get()
            r1 = 0
            if (r0 != 0) goto L_0x000a
            return r1
        L_0x000a:
            com.facebook.ads.RewardData r0 = r6.a
            r2 = 1
            if (r0 == 0) goto L_0x0084
            java.lang.String r0 = com.facebook.ads.AdSettings.getUrlPrefix()
            if (r0 == 0) goto L_0x0029
            boolean r3 = r0.isEmpty()
            if (r3 == 0) goto L_0x001c
            goto L_0x0029
        L_0x001c:
            java.util.Locale r3 = java.util.Locale.US
            java.lang.String r4 = "https://www.%s.facebook.com/audience_network/server_side_reward"
            java.lang.Object[] r5 = new java.lang.Object[r2]
            r5[r1] = r0
            java.lang.String r0 = java.lang.String.format(r3, r4, r5)
            goto L_0x002b
        L_0x0029:
            java.lang.String r0 = "https://www.facebook.com/audience_network/server_side_reward"
        L_0x002b:
            android.net.Uri r0 = android.net.Uri.parse(r0)
            android.net.Uri$Builder r3 = new android.net.Uri$Builder
            r3.<init>()
            java.lang.String r4 = r0.getScheme()
            r3.scheme(r4)
            java.lang.String r4 = r0.getAuthority()
            r3.authority(r4)
            java.lang.String r4 = r0.getPath()
            r3.path(r4)
            java.lang.String r4 = r0.getQuery()
            r3.query(r4)
            java.lang.String r0 = r0.getFragment()
            r3.fragment(r0)
            java.lang.String r0 = "puid"
            com.facebook.ads.RewardData r4 = r6.a
            java.lang.String r4 = r4.getUserID()
            r3.appendQueryParameter(r0, r4)
            java.lang.String r0 = "pc"
            com.facebook.ads.RewardData r4 = r6.a
            java.lang.String r4 = r4.getCurrency()
            r3.appendQueryParameter(r0, r4)
            java.lang.String r0 = "ptid"
            java.lang.String r4 = r6.c
            r3.appendQueryParameter(r0, r4)
            java.lang.String r0 = "appid"
            java.lang.String r4 = r6.g
            r3.appendQueryParameter(r0, r4)
            android.net.Uri r0 = r3.build()
            java.lang.String r0 = r0.toString()
            goto L_0x0085
        L_0x0084:
            r0 = 0
        L_0x0085:
            com.facebook.ads.internal.adapters.b.a r3 = r6.j
            r3.a(r0)
            android.content.Intent r3 = new android.content.Intent
            android.content.Context r4 = r6.e
            java.lang.Class r5 = com.facebook.ads.AudienceNetworkActivity.getAdActivity()
            r3.<init>(r4, r5)
            java.lang.String r4 = "viewType"
            com.facebook.ads.internal.settings.a$a r5 = r6.l
            r3.putExtra(r4, r5)
            java.lang.String r4 = "rewardedVideoAdDataBundle"
            com.facebook.ads.internal.adapters.b.a r5 = r6.j
            r3.putExtra(r4, r5)
            java.lang.String r4 = "uniqueId"
            java.lang.String r5 = r6.c
            r3.putExtra(r4, r5)
            java.lang.String r4 = "rewardServerURL"
            r3.putExtra(r4, r0)
            java.lang.String r0 = "placementId"
            java.lang.String r4 = r6.h
            r3.putExtra(r0, r4)
            java.lang.String r0 = "requestTime"
            long r4 = r6.i
            r3.putExtra(r0, r4)
            int r0 = r6.b
            r4 = -1
            if (r0 == r4) goto L_0x00d5
            android.content.Context r0 = r6.e
            android.content.ContentResolver r0 = r0.getContentResolver()
            java.lang.String r4 = "accelerometer_rotation"
            int r0 = android.provider.Settings.System.getInt(r0, r4, r1)
            if (r0 == r2) goto L_0x00d5
            java.lang.String r0 = "predefinedOrientationKey"
            int r1 = r6.b
            goto L_0x00e0
        L_0x00d5:
            android.content.Context r0 = r6.e
            boolean r0 = com.facebook.ads.internal.r.a.y(r0)
            if (r0 != 0) goto L_0x00e3
            java.lang.String r0 = "predefinedOrientationKey"
            r1 = 6
        L_0x00e0:
            r3.putExtra(r0, r1)
        L_0x00e3:
            android.content.Context r0 = r6.e
            boolean r0 = r0 instanceof android.app.Activity
            if (r0 != 0) goto L_0x00f3
            int r0 = r3.getFlags()
            r1 = 268435456(0x10000000, float:2.5243549E-29)
            r0 = r0 | r1
            r3.setFlags(r0)
        L_0x00f3:
            android.content.Context r0 = r6.e
            r0.startActivity(r3)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.adapters.k.b():boolean");
    }

    public String getClientToken() {
        return this.j.a();
    }

    public void onDestroy() {
        if (this.k != null) {
            try {
                LocalBroadcastManager.getInstance(this.e).unregisterReceiver(this.k);
            } catch (Exception unused) {
            }
        }
    }
}
