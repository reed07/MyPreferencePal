package com.inmobi.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.annotation.VisibleForTesting;
import android.support.v7.widget.RecyclerView;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.share.internal.ShareConstants;
import com.inmobi.a.o;
import com.inmobi.ads.AdContainer.RenderingProperties;
import com.inmobi.ads.AdContainer.RenderingProperties.PlacementType;
import com.inmobi.ads.InMobiAdRequest.MonetizationContext;
import com.inmobi.ads.InMobiAdRequestStatus.StatusCode;
import com.inmobi.commons.core.configs.g;
import com.inmobi.commons.core.configs.h;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.rendering.RenderView;
import com.integralads.avid.library.inmobi.session.AbstractAvidAdSession;
import com.mopub.common.AdType;
import com.mopub.common.MoPubBrowser;
import com.mopub.mobileads.VastResourceXmlManager;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.squareup.picasso.Picasso;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@UiThread
/* compiled from: AdUnit */
public abstract class i implements com.inmobi.ads.bl.a, com.inmobi.ads.h.a, com.inmobi.commons.core.configs.b.c, com.inmobi.rendering.RenderView.a, com.inmobi.rendering.a {
    /* access modifiers changed from: private */
    public static final String y = "i";
    /* access modifiers changed from: private */
    public boolean A;
    /* access modifiers changed from: private */
    @Nullable
    public h B;
    private long C;
    private long D;
    private WeakReference<b> E;
    private RenderView F;
    private bn G;
    /* access modifiers changed from: private */
    public long H;
    private long I = 0;
    @NonNull
    private a J;
    private Runnable K;
    /* access modifiers changed from: private */
    public Set<bq> L;
    private MonetizationContext M;
    /* access modifiers changed from: private */
    public bl N;
    /* access modifiers changed from: private */
    public boolean O;
    private com.inmobi.ads.d.a P;
    private boolean Q;
    /* access modifiers changed from: private */
    @Nullable
    public com.inmobi.ads.b.a R;
    /* access modifiers changed from: private */
    public com.inmobi.rendering.RenderView.a S = new com.inmobi.rendering.RenderView.a() {
        public final void A() {
        }

        public final void B() {
        }

        public final void G() {
        }

        public final void a(HashMap<Object, Object> hashMap) {
        }

        public final void b(RenderView renderView) {
        }

        public final void b(String str, Map<String, Object> map) {
        }

        public final void b(HashMap<Object, Object> hashMap) {
        }

        public final void c(RenderView renderView) {
        }

        public final void d(RenderView renderView) {
        }

        public final void w() {
            i.this.s.post(new Runnable() {
                public final void run() {
                    if (2 == i.this.a) {
                        i.this.O = true;
                        i.this.J();
                    }
                }
            });
        }

        public final void y() {
            i.this.s.post(new Runnable() {
                public final void run() {
                    if (i.this.k != null) {
                        i.this.i().a(i.this.k);
                    }
                    i.this.a(new InMobiAdRequestStatus(StatusCode.INTERNAL_ERROR), false);
                }
            });
        }

        public final void a(RenderView renderView) {
            i.this.s.post(new Runnable() {
                public final void run() {
                    if (2 == i.this.a) {
                        i.this.L();
                    }
                }
            });
        }
    };
    int a;
    final JSONObject b = new JSONObject();
    final boolean c = false;
    public long d;
    public String e;
    public Map<String, String> f;
    /* access modifiers changed from: 0000 */
    public c g;
    String h;
    JSONObject i;
    bx j;
    /* access modifiers changed from: 0000 */
    public String k;
    public String l;
    String m;
    public boolean n = false;
    ah o;
    ExecutorService p;
    public e q;
    int r;
    Handler s;
    boolean t;
    /* access modifiers changed from: 0000 */
    public RenderView u;
    boolean v;
    boolean w = false;
    /* access modifiers changed from: 0000 */
    public String x;
    private WeakReference<Context> z;

    /* compiled from: AdUnit */
    static final class a extends Handler {
        private WeakReference<i> a;

        a(i iVar) {
            super(Looper.getMainLooper());
            this.a = new WeakReference<>(iVar);
        }

        public final void handleMessage(Message message) {
            WeakReference<i> weakReference = this.a;
            i iVar = weakReference == null ? null : (i) weakReference.get();
            if (iVar != null) {
                Bundle data = message.getData();
                long j = data.getLong(AudienceNetworkActivity.PLACEMENT_ID);
                int i = message.what;
                switch (i) {
                    case 1:
                        iVar.a(j, data.getBoolean("adAvailable"), (a) message.obj);
                        return;
                    case 2:
                        iVar.c(j, (a) message.obj);
                        return;
                    case 3:
                        return;
                    case 4:
                        iVar.b(j, data.getBoolean("assetAvailable"));
                        return;
                    default:
                        switch (i) {
                            case 11:
                                iVar.x();
                                return;
                            case 12:
                                iVar.z();
                                return;
                            case 13:
                                iVar.b((InMobiAdRequestStatus) message.obj);
                                return;
                            case 14:
                                iVar.H();
                                return;
                            default:
                                return;
                        }
                }
            }
        }
    }

    /* compiled from: AdUnit */
    public static abstract class b {
        public void a() {
        }

        public void a(InMobiAdRequestStatus inMobiAdRequestStatus) {
        }

        /* access modifiers changed from: 0000 */
        public void a(i iVar) {
        }

        /* access modifiers changed from: 0000 */
        public void a(@NonNull Map<Object, Object> map) {
        }

        public void a(boolean z) {
        }

        /* access modifiers changed from: 0000 */
        public void a(byte[] bArr) {
        }

        /* access modifiers changed from: 0000 */
        public void b() {
        }

        /* access modifiers changed from: 0000 */
        public void b(InMobiAdRequestStatus inMobiAdRequestStatus) {
        }

        /* access modifiers changed from: 0000 */
        public void b(@NonNull Map<Object, Object> map) {
        }

        /* access modifiers changed from: 0000 */
        public void b(boolean z) {
        }

        /* access modifiers changed from: 0000 */
        public void c() {
        }

        /* access modifiers changed from: 0000 */
        public void d() {
        }

        /* access modifiers changed from: 0000 */
        public void e() {
        }

        /* access modifiers changed from: 0000 */
        public void f() {
        }

        /* access modifiers changed from: 0000 */
        public void g() {
        }

        /* access modifiers changed from: 0000 */
        public void h() {
        }

        public boolean i() {
            return true;
        }

        /* access modifiers changed from: 0000 */
        public void j() {
        }
    }

    /* compiled from: AdUnit */
    public static class c {
        public static Map<String, Object> a(JSONArray jSONArray) {
            HashMap hashMap = new HashMap();
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    arrayList.add(jSONArray.getString(i));
                } catch (JSONException e) {
                    i.y;
                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                }
            }
            hashMap.put("trackerUrls", arrayList);
            return hashMap;
        }
    }

    /* compiled from: AdUnit */
    static class d {
        @NonNull
        static HashMap<String, String> a(@NonNull String str, @NonNull String str2, JSONArray jSONArray, JSONArray jSONArray2, JSONObject jSONObject) {
            HashMap<String, String> hashMap = new HashMap<>();
            int i = 0;
            if (jSONArray != null) {
                try {
                    int length = jSONArray.length();
                    int i2 = 0;
                    while (i2 < length) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(str);
                        int i3 = i2 + 1;
                        sb.append(i3);
                        hashMap.put(sb.toString(), jSONArray.getString(i2));
                        i2 = i3;
                    }
                } catch (Exception e) {
                    i.y;
                    new StringBuilder("Exception while parsing map details for Moat : ").append(e.getMessage());
                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                }
            }
            if (jSONArray2 != null) {
                int length2 = jSONArray2.length();
                while (i < length2) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(str2);
                    int i4 = i + 1;
                    sb2.append(i4);
                    hashMap.put(sb2.toString(), jSONArray2.getString(i));
                    i = i4;
                }
            }
            if (jSONObject != null) {
                Iterator keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String str3 = (String) keys.next();
                    hashMap.put(str3, jSONObject.optString(str3));
                }
            }
            return hashMap;
        }

        @Nullable
        static Map<String, Object> a(@NonNull JSONArray jSONArray) {
            JSONObject jSONObject;
            try {
                int length = jSONArray.length();
                int i = 0;
                while (true) {
                    if (i >= length) {
                        jSONObject = null;
                        break;
                    }
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                    if (jSONObject2.has("moat")) {
                        jSONObject = jSONObject2.getJSONObject("moat");
                        break;
                    }
                    i++;
                }
                if (jSONObject == null) {
                    return null;
                }
                HashMap hashMap = new HashMap();
                hashMap.put("enabled", Boolean.valueOf(jSONObject.getBoolean("enabled")));
                hashMap.put("instrumentVideo", Boolean.valueOf(jSONObject.optBoolean("instrumentVideo", false)));
                hashMap.put("partnerCode", jSONObject.optString("partnerCode", null));
                hashMap.put("clientLevels", jSONObject.optJSONArray("clientLevels"));
                hashMap.put("clientSlicers", jSONObject.optJSONArray("clientSlicers"));
                hashMap.put("zMoatExtras", jSONObject.optJSONObject("zMoatExtras"));
                return hashMap;
            } catch (JSONException e) {
                i.y;
                new StringBuilder("Exception while parsing MoatParams from response : ").append(e.getMessage());
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                return null;
            }
        }
    }

    /* compiled from: AdUnit */
    public interface e {
        void a(@NonNull i iVar);

        void a(@NonNull i iVar, @NonNull InMobiAdRequestStatus inMobiAdRequestStatus);
    }

    public final void G() {
    }

    /* access modifiers changed from: 0000 */
    public void K() {
    }

    /* access modifiers changed from: 0000 */
    public void L() {
    }

    public abstract String b();

    /* access modifiers changed from: protected */
    public abstract void b(a aVar);

    /* access modifiers changed from: 0000 */
    public void b(b bVar) {
    }

    public abstract String c();

    /* access modifiers changed from: 0000 */
    public void c(b bVar) {
    }

    /* access modifiers changed from: protected */
    public abstract PlacementType d();

    /* access modifiers changed from: protected */
    public void x() {
    }

    public final void a(com.inmobi.commons.core.configs.a aVar) {
        this.g = (c) aVar;
        i().d = this.g.a(b());
        bl blVar = this.N;
        if (blVar != null) {
            blVar.b = this.g.a(b());
        }
        com.inmobi.commons.core.e.b.a().a("ads", this.g.l);
    }

    public i(Context context, long j2, b bVar) {
        this.z = new WeakReference<>(context);
        this.d = j2;
        this.E = new WeakReference<>(bVar);
        this.P = new com.inmobi.ads.d.b(com.inmobi.b.a.a());
        this.m = "unknown";
        this.g = new c();
        com.inmobi.commons.core.configs.b.a().a((com.inmobi.commons.core.configs.a) new g(), (com.inmobi.commons.core.configs.b.c) null);
        com.inmobi.commons.core.configs.b.a().a((com.inmobi.commons.core.configs.a) this.g, (com.inmobi.commons.core.configs.b.c) this);
        this.p = Executors.newSingleThreadExecutor();
        this.p.submit(new Runnable() {
            public final void run() {
                i iVar = i.this;
                iVar.B = new h(iVar, iVar.g.a(i.this.b()), i.this.u());
            }
        });
        this.J = new a(this);
        this.G = new bn(this);
        this.L = new HashSet();
        this.r = -1;
        this.K = new Runnable() {
            public final void run() {
                int r = i.this.r();
                switch (r) {
                    case -2:
                    case -1:
                    case 0:
                    case 1:
                    case 2:
                        break;
                    default:
                        StringBuilder sb = new StringBuilder("Unknown return value (");
                        sb.append(r);
                        sb.append(") from #doAdLoadWork()");
                        break;
                }
                i.y;
            }
        };
        com.inmobi.commons.core.e.b.a().a("ads", this.g.l);
        this.s = new Handler(Looper.getMainLooper());
        this.t = false;
        this.x = "";
        this.i = this.b;
        this.A = false;
        this.a = 0;
    }

    @Nullable
    public final Context a() {
        WeakReference<Context> weakReference = this.z;
        if (weakReference == null) {
            return null;
        }
        return (Context) weakReference.get();
    }

    public void a(Context context) {
        this.z = new WeakReference<>(context);
    }

    /* access modifiers changed from: protected */
    @NonNull
    public Map<String, String> e() {
        HashMap hashMap = new HashMap();
        hashMap.put("preload-request", this.n ? AppEventsConstants.EVENT_PARAM_VALUE_YES : "0");
        return hashMap;
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public final b f() {
        b bVar = (b) this.E.get();
        if (bVar == null) {
            g();
        }
        return bVar;
    }

    /* access modifiers changed from: 0000 */
    public final void g() {
        Logger.a(InternalLogLevel.DEBUG, "InMobi", "Listener was garbage collected. Unable to give callback");
        d("ListenerNotFound");
    }

    /* access modifiers changed from: 0000 */
    public final void a(b bVar) {
        this.E = new WeakReference<>(bVar);
    }

    public final boolean h() {
        if (1 == this.a) {
            return false;
        }
        return this.D == -1 ? this.C != 0 && System.currentTimeMillis() - this.C > TimeUnit.SECONDS.toMillis(this.g.a(b()).d) : this.C != 0 && System.currentTimeMillis() > this.D;
    }

    @NonNull
    public final h i() {
        if (this.B == null) {
            this.B = new h(this, this.g.a(b()), u());
        }
        return this.B;
    }

    /* access modifiers changed from: protected */
    @NonNull
    public RenderView k() {
        RenderView renderView = this.F;
        if ((renderView == null || renderView.s.get()) && a() != null) {
            this.F = new RenderView(a(), new RenderingProperties(d()), this.L, this.k);
            this.F.a((com.inmobi.rendering.RenderView.a) this, this.g);
            this.F.setPlacementId(this.d);
            this.F.setCreativeId(this.x);
            this.F.setAllowAutoRedirection(this.A);
        }
        return this.F;
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0053 A[Catch:{ JSONException -> 0x0221, IllegalArgumentException -> 0x0211 }] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0056 A[Catch:{ JSONException -> 0x0221, IllegalArgumentException -> 0x0211 }] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0059 A[Catch:{ JSONException -> 0x0221, IllegalArgumentException -> 0x0211 }] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0078 A[Catch:{ JSONException -> 0x0221, IllegalArgumentException -> 0x0211 }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0079 A[Catch:{ JSONException -> 0x0221, IllegalArgumentException -> 0x0211 }] */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x0180 A[Catch:{ JSONException -> 0x020f, IllegalArgumentException -> 0x020d }] */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x0183 A[Catch:{ JSONException -> 0x020f, IllegalArgumentException -> 0x020d }] */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0186 A[Catch:{ JSONException -> 0x020f, IllegalArgumentException -> 0x020d }] */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x0193 A[Catch:{ JSONException -> 0x020f, IllegalArgumentException -> 0x020d }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(com.inmobi.ads.a r12) {
        /*
            r11 = this;
            r0 = 0
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0221, IllegalArgumentException -> 0x0211 }
            java.lang.String r2 = r12.c     // Catch:{ JSONException -> 0x0221, IllegalArgumentException -> 0x0211 }
            r1.<init>(r2)     // Catch:{ JSONException -> 0x0221, IllegalArgumentException -> 0x0211 }
            long r2 = r12.e     // Catch:{ JSONException -> 0x0221, IllegalArgumentException -> 0x0211 }
            r11.C = r2     // Catch:{ JSONException -> 0x0221, IllegalArgumentException -> 0x0211 }
            long r2 = r12.c()     // Catch:{ JSONException -> 0x0221, IllegalArgumentException -> 0x0211 }
            r11.D = r2     // Catch:{ JSONException -> 0x0221, IllegalArgumentException -> 0x0211 }
            java.lang.String r2 = r12.g     // Catch:{ JSONException -> 0x0221, IllegalArgumentException -> 0x0211 }
            r11.k = r2     // Catch:{ JSONException -> 0x0221, IllegalArgumentException -> 0x0211 }
            java.lang.String r2 = r12.h     // Catch:{ JSONException -> 0x0221, IllegalArgumentException -> 0x0211 }
            r11.l = r2     // Catch:{ JSONException -> 0x0221, IllegalArgumentException -> 0x0211 }
            java.lang.String r2 = "markupType"
            java.lang.String r2 = r1.optString(r2)     // Catch:{ JSONException -> 0x0221, IllegalArgumentException -> 0x0211 }
            r3 = 2
            r4 = -1
            r5 = 1
            if (r2 == 0) goto L_0x005c
            int r6 = r2.length()     // Catch:{ JSONException -> 0x0221, IllegalArgumentException -> 0x0211 }
            if (r6 != 0) goto L_0x002c
            goto L_0x005c
        L_0x002c:
            int r6 = r2.hashCode()     // Catch:{ JSONException -> 0x0221, IllegalArgumentException -> 0x0211 }
            r7 = -1084172778(0xffffffffbf60d616, float:-0.8782667)
            if (r6 == r7) goto L_0x0045
            r7 = 3213227(0x3107ab, float:4.50269E-39)
            if (r6 == r7) goto L_0x003b
            goto L_0x004f
        L_0x003b:
            java.lang.String r6 = "html"
            boolean r2 = r2.equals(r6)     // Catch:{ JSONException -> 0x0221, IllegalArgumentException -> 0x0211 }
            if (r2 == 0) goto L_0x004f
            r2 = 1
            goto L_0x0050
        L_0x0045:
            java.lang.String r6 = "inmobiJson"
            boolean r2 = r2.equals(r6)     // Catch:{ JSONException -> 0x0221, IllegalArgumentException -> 0x0211 }
            if (r2 == 0) goto L_0x004f
            r2 = 2
            goto L_0x0050
        L_0x004f:
            r2 = -1
        L_0x0050:
            switch(r2) {
                case 1: goto L_0x0059;
                case 2: goto L_0x0056;
                default: goto L_0x0053;
            }     // Catch:{ JSONException -> 0x0221, IllegalArgumentException -> 0x0211 }
        L_0x0053:
            java.lang.String r2 = "unknown"
            goto L_0x005e
        L_0x0056:
            java.lang.String r2 = "inmobiJson"
            goto L_0x005e
        L_0x0059:
            java.lang.String r2 = "html"
            goto L_0x005e
        L_0x005c:
            java.lang.String r2 = "html"
        L_0x005e:
            r11.m = r2     // Catch:{ JSONException -> 0x0221, IllegalArgumentException -> 0x0211 }
            java.lang.String r2 = "allowAutoRedirection"
            boolean r2 = r1.optBoolean(r2, r0)     // Catch:{ JSONException -> 0x0221, IllegalArgumentException -> 0x0211 }
            r11.A = r2     // Catch:{ JSONException -> 0x0221, IllegalArgumentException -> 0x0211 }
            org.json.JSONObject r12 = r12.b()     // Catch:{ JSONException -> 0x0221, IllegalArgumentException -> 0x0211 }
            r11.i = r12     // Catch:{ JSONException -> 0x0221, IllegalArgumentException -> 0x0211 }
            java.lang.String r12 = "unknown"
            java.lang.String r2 = r11.m     // Catch:{ JSONException -> 0x0221, IllegalArgumentException -> 0x0211 }
            boolean r12 = r12.equals(r2)     // Catch:{ JSONException -> 0x0221, IllegalArgumentException -> 0x0211 }
            if (r12 == 0) goto L_0x0079
            return r0
        L_0x0079:
            java.lang.String r12 = "inmobiJson"
            java.lang.String r2 = r11.m     // Catch:{ JSONException -> 0x0221, IllegalArgumentException -> 0x0211 }
            boolean r12 = r12.equals(r2)     // Catch:{ JSONException -> 0x0221, IllegalArgumentException -> 0x0211 }
            if (r12 == 0) goto L_0x0090
            java.lang.String r12 = "pubContent"
            org.json.JSONObject r12 = r1.getJSONObject(r12)     // Catch:{ JSONException -> 0x0221, IllegalArgumentException -> 0x0211 }
            java.lang.String r12 = r12.toString()     // Catch:{ JSONException -> 0x0221, IllegalArgumentException -> 0x0211 }
            r11.h = r12     // Catch:{ JSONException -> 0x0221, IllegalArgumentException -> 0x0211 }
            goto L_0x009c
        L_0x0090:
            java.lang.String r12 = "pubContent"
            java.lang.String r12 = r1.getString(r12)     // Catch:{ JSONException -> 0x0221, IllegalArgumentException -> 0x0211 }
            java.lang.String r12 = r12.trim()     // Catch:{ JSONException -> 0x0221, IllegalArgumentException -> 0x0211 }
            r11.h = r12     // Catch:{ JSONException -> 0x0221, IllegalArgumentException -> 0x0211 }
        L_0x009c:
            java.lang.String r12 = r11.h     // Catch:{ JSONException -> 0x0221, IllegalArgumentException -> 0x0211 }
            if (r12 == 0) goto L_0x00c9
            java.lang.String r12 = r11.h     // Catch:{ JSONException -> 0x0221, IllegalArgumentException -> 0x0211 }
            int r12 = r12.length()     // Catch:{ JSONException -> 0x0221, IllegalArgumentException -> 0x0211 }
            if (r12 == 0) goto L_0x00c9
            java.lang.String r12 = "unknown"
            java.lang.String r2 = r11.m     // Catch:{ JSONException -> 0x0221, IllegalArgumentException -> 0x0211 }
            boolean r12 = r12.equals(r2)     // Catch:{ JSONException -> 0x0221, IllegalArgumentException -> 0x0211 }
            if (r12 != 0) goto L_0x00c9
            java.lang.String r12 = r11.h     // Catch:{ JSONException -> 0x0221, IllegalArgumentException -> 0x0211 }
            java.lang.String r2 = "@__imm_aft@"
            long r6 = java.lang.System.currentTimeMillis()     // Catch:{ JSONException -> 0x0221, IllegalArgumentException -> 0x0211 }
            long r8 = r11.H     // Catch:{ JSONException -> 0x0221, IllegalArgumentException -> 0x0211 }
            long r6 = r6 - r8
            java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch:{ JSONException -> 0x0221, IllegalArgumentException -> 0x0211 }
            java.lang.String r12 = r12.replace(r2, r6)     // Catch:{ JSONException -> 0x0221, IllegalArgumentException -> 0x0211 }
            r11.h = r12     // Catch:{ JSONException -> 0x0221, IllegalArgumentException -> 0x0211 }
            r12 = 1
            goto L_0x00ca
        L_0x00c9:
            r12 = 0
        L_0x00ca:
            java.lang.String r2 = "creativeId"
            java.lang.String r6 = ""
            java.lang.String r2 = r1.optString(r2, r6)     // Catch:{ JSONException -> 0x020f, IllegalArgumentException -> 0x020d }
            r11.x = r2     // Catch:{ JSONException -> 0x020f, IllegalArgumentException -> 0x020d }
            java.util.Set<com.inmobi.ads.bq> r2 = r11.L     // Catch:{ JSONException -> 0x020f, IllegalArgumentException -> 0x020d }
            boolean r2 = r2.isEmpty()     // Catch:{ JSONException -> 0x020f, IllegalArgumentException -> 0x020d }
            if (r2 == 0) goto L_0x0230
            java.lang.String r2 = "viewability"
            boolean r2 = r1.has(r2)     // Catch:{ JSONException -> 0x020f, IllegalArgumentException -> 0x020d }
            if (r2 == 0) goto L_0x0121
            com.inmobi.ads.bq r2 = new com.inmobi.ads.bq     // Catch:{ JSONException -> 0x020f, IllegalArgumentException -> 0x020d }
            r2.<init>(r5)     // Catch:{ JSONException -> 0x020f, IllegalArgumentException -> 0x020d }
            java.lang.String r6 = "viewability"
            org.json.JSONArray r6 = r1.getJSONArray(r6)     // Catch:{ JSONException -> 0x020f, IllegalArgumentException -> 0x020d }
            java.util.Map r6 = com.inmobi.ads.i.d.a(r6)     // Catch:{ JSONException -> 0x020f, IllegalArgumentException -> 0x020d }
            r2.b = r6     // Catch:{ JSONException -> 0x020f, IllegalArgumentException -> 0x020d }
            java.util.Set<com.inmobi.ads.bq> r6 = r11.L     // Catch:{ JSONException -> 0x020f, IllegalArgumentException -> 0x020d }
            r6.add(r2)     // Catch:{ JSONException -> 0x020f, IllegalArgumentException -> 0x020d }
            android.content.Context r6 = r11.a()     // Catch:{ JSONException -> 0x020f, IllegalArgumentException -> 0x020d }
            if (r6 == 0) goto L_0x010d
            boolean r7 = r6 instanceof android.app.Activity     // Catch:{ JSONException -> 0x020f, IllegalArgumentException -> 0x020d }
            if (r7 == 0) goto L_0x010d
            android.app.Activity r6 = (android.app.Activity) r6     // Catch:{ JSONException -> 0x020f, IllegalArgumentException -> 0x020d }
            android.app.Application r6 = r6.getApplication()     // Catch:{ JSONException -> 0x020f, IllegalArgumentException -> 0x020d }
            com.inmobi.ads.z.a(r6)     // Catch:{ JSONException -> 0x020f, IllegalArgumentException -> 0x020d }
        L_0x010d:
            java.util.Map<java.lang.String, java.lang.Object> r6 = r2.b     // Catch:{ JSONException -> 0x020f, IllegalArgumentException -> 0x020d }
            if (r6 == 0) goto L_0x0121
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x020f, IllegalArgumentException -> 0x020d }
            java.lang.String r7 = "Read out Moat params: "
            r6.<init>(r7)     // Catch:{ JSONException -> 0x020f, IllegalArgumentException -> 0x020d }
            java.util.Map<java.lang.String, java.lang.Object> r2 = r2.b     // Catch:{ JSONException -> 0x020f, IllegalArgumentException -> 0x020d }
            java.lang.String r2 = r2.toString()     // Catch:{ JSONException -> 0x020f, IllegalArgumentException -> 0x020d }
            r6.append(r2)     // Catch:{ JSONException -> 0x020f, IllegalArgumentException -> 0x020d }
        L_0x0121:
            java.lang.String r2 = "metaInfo"
            boolean r2 = r1.has(r2)     // Catch:{ JSONException -> 0x020f, IllegalArgumentException -> 0x020d }
            if (r2 == 0) goto L_0x01f4
            java.lang.String r2 = "metaInfo"
            org.json.JSONObject r2 = r1.getJSONObject(r2)     // Catch:{ JSONException -> 0x020f, IllegalArgumentException -> 0x020d }
            java.lang.String r6 = "unknown"
            java.lang.String r7 = "creativeType"
            boolean r7 = r2.has(r7)     // Catch:{ JSONException -> 0x020f, IllegalArgumentException -> 0x020d }
            if (r7 == 0) goto L_0x013f
            java.lang.String r6 = "creativeType"
            java.lang.String r6 = r2.getString(r6)     // Catch:{ JSONException -> 0x020f, IllegalArgumentException -> 0x020d }
        L_0x013f:
            java.lang.String r7 = "iasEnabled"
            boolean r7 = r2.has(r7)     // Catch:{ JSONException -> 0x020f, IllegalArgumentException -> 0x020d }
            if (r7 == 0) goto L_0x01a8
            java.lang.String r7 = "iasEnabled"
            boolean r7 = r2.getBoolean(r7)     // Catch:{ JSONException -> 0x020f, IllegalArgumentException -> 0x020d }
            if (r7 == 0) goto L_0x01a8
            com.inmobi.ads.bq r7 = new com.inmobi.ads.bq     // Catch:{ JSONException -> 0x020f, IllegalArgumentException -> 0x020d }
            r8 = 3
            r7.<init>(r8)     // Catch:{ JSONException -> 0x020f, IllegalArgumentException -> 0x020d }
            java.util.HashMap r8 = new java.util.HashMap     // Catch:{ JSONException -> 0x020f, IllegalArgumentException -> 0x020d }
            r8.<init>()     // Catch:{ JSONException -> 0x020f, IllegalArgumentException -> 0x020d }
            int r9 = r6.hashCode()     // Catch:{ JSONException -> 0x020f, IllegalArgumentException -> 0x020d }
            r10 = 112202875(0x6b0147b, float:6.6233935E-35)
            if (r9 == r10) goto L_0x0173
            r3 = 1425678798(0x54fa21ce, float:8.5944718E12)
            if (r9 == r3) goto L_0x0169
            goto L_0x017c
        L_0x0169:
            java.lang.String r3 = "nonvideo"
            boolean r3 = r6.equals(r3)     // Catch:{ JSONException -> 0x020f, IllegalArgumentException -> 0x020d }
            if (r3 == 0) goto L_0x017c
            r3 = 1
            goto L_0x017d
        L_0x0173:
            java.lang.String r5 = "video"
            boolean r5 = r6.equals(r5)     // Catch:{ JSONException -> 0x020f, IllegalArgumentException -> 0x020d }
            if (r5 == 0) goto L_0x017c
            goto L_0x017d
        L_0x017c:
            r3 = -1
        L_0x017d:
            switch(r3) {
                case 1: goto L_0x0186;
                case 2: goto L_0x0183;
                default: goto L_0x0180;
            }     // Catch:{ JSONException -> 0x020f, IllegalArgumentException -> 0x020d }
        L_0x0180:
            java.lang.String r3 = "unknown"
            goto L_0x0188
        L_0x0183:
            java.lang.String r3 = "video"
            goto L_0x0188
        L_0x0186:
            java.lang.String r3 = "nonvideo"
        L_0x0188:
            java.lang.String r4 = "creativeType"
            r8.put(r4, r3)     // Catch:{ JSONException -> 0x020f, IllegalArgumentException -> 0x020d }
            r7.b = r8     // Catch:{ JSONException -> 0x020f, IllegalArgumentException -> 0x020d }
            java.util.Map<java.lang.String, java.lang.Object> r3 = r7.b     // Catch:{ JSONException -> 0x020f, IllegalArgumentException -> 0x020d }
            if (r3 == 0) goto L_0x01a3
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x020f, IllegalArgumentException -> 0x020d }
            java.lang.String r4 = "Read out IAS params: "
            r3.<init>(r4)     // Catch:{ JSONException -> 0x020f, IllegalArgumentException -> 0x020d }
            java.util.Map<java.lang.String, java.lang.Object> r4 = r7.b     // Catch:{ JSONException -> 0x020f, IllegalArgumentException -> 0x020d }
            java.lang.String r4 = r4.toString()     // Catch:{ JSONException -> 0x020f, IllegalArgumentException -> 0x020d }
            r3.append(r4)     // Catch:{ JSONException -> 0x020f, IllegalArgumentException -> 0x020d }
        L_0x01a3:
            java.util.Set<com.inmobi.ads.bq> r3 = r11.L     // Catch:{ JSONException -> 0x020f, IllegalArgumentException -> 0x020d }
            r3.add(r7)     // Catch:{ JSONException -> 0x020f, IllegalArgumentException -> 0x020d }
        L_0x01a8:
            java.lang.String r3 = "adMasterSDKInfo"
            boolean r3 = r2.has(r3)     // Catch:{ JSONException -> 0x020f, IllegalArgumentException -> 0x020d }
            if (r3 == 0) goto L_0x01f4
            com.inmobi.ads.c r3 = r11.g     // Catch:{ JSONException -> 0x020f, IllegalArgumentException -> 0x020d }
            com.inmobi.ads.c$l r3 = r3.k     // Catch:{ JSONException -> 0x020f, IllegalArgumentException -> 0x020d }
            com.inmobi.ads.c$f r3 = r3.k     // Catch:{ JSONException -> 0x020f, IllegalArgumentException -> 0x020d }
            boolean r3 = r3.a     // Catch:{ JSONException -> 0x020f, IllegalArgumentException -> 0x020d }
            if (r3 == 0) goto L_0x01f4
            java.lang.String r3 = "adMasterSDKInfo"
            org.json.JSONObject r2 = r2.getJSONObject(r3)     // Catch:{ JSONException -> 0x020f, IllegalArgumentException -> 0x020d }
            java.lang.String r3 = "enabled"
            boolean r3 = r2.has(r3)     // Catch:{ JSONException -> 0x020f, IllegalArgumentException -> 0x020d }
            if (r3 == 0) goto L_0x01f4
            java.lang.String r3 = "enabled"
            boolean r3 = r2.getBoolean(r3)     // Catch:{ JSONException -> 0x020f, IllegalArgumentException -> 0x020d }
            if (r3 == 0) goto L_0x01f4
            com.inmobi.ads.bq r3 = new com.inmobi.ads.bq     // Catch:{ JSONException -> 0x020f, IllegalArgumentException -> 0x020d }
            r4 = 6
            r3.<init>(r4)     // Catch:{ JSONException -> 0x020f, IllegalArgumentException -> 0x020d }
            java.lang.String r4 = "trackerUrls"
            org.json.JSONArray r2 = r2.getJSONArray(r4)     // Catch:{ JSONException -> 0x020f, IllegalArgumentException -> 0x020d }
            java.util.Map r2 = com.inmobi.ads.i.c.a(r2)     // Catch:{ JSONException -> 0x020f, IllegalArgumentException -> 0x020d }
            r3.b = r2     // Catch:{ JSONException -> 0x020f, IllegalArgumentException -> 0x020d }
            android.content.Context r2 = r11.a()     // Catch:{ JSONException -> 0x020f, IllegalArgumentException -> 0x020d }
            if (r2 == 0) goto L_0x01ef
            boolean r2 = r2 instanceof android.app.Activity     // Catch:{ JSONException -> 0x020f, IllegalArgumentException -> 0x020d }
            if (r2 == 0) goto L_0x01ef
            com.inmobi.ads.e.a.c.a.a     // Catch:{ JSONException -> 0x020f, IllegalArgumentException -> 0x020d }
        L_0x01ef:
            java.util.Set<com.inmobi.ads.bq> r2 = r11.L     // Catch:{ JSONException -> 0x020f, IllegalArgumentException -> 0x020d }
            r2.add(r3)     // Catch:{ JSONException -> 0x020f, IllegalArgumentException -> 0x020d }
        L_0x01f4:
            java.lang.String r2 = "tracking"
            boolean r2 = r1.has(r2)     // Catch:{ JSONException -> 0x020f, IllegalArgumentException -> 0x020d }
            if (r2 == 0) goto L_0x0230
            java.lang.String r2 = "web"
            java.lang.String r3 = "tracking"
            java.lang.String r1 = r1.getString(r3)     // Catch:{ JSONException -> 0x020f, IllegalArgumentException -> 0x020d }
            boolean r1 = r2.equals(r1)     // Catch:{ JSONException -> 0x020f, IllegalArgumentException -> 0x020d }
            if (r1 == 0) goto L_0x0230
            r11.r = r0     // Catch:{ JSONException -> 0x020f, IllegalArgumentException -> 0x020d }
            goto L_0x0230
        L_0x020d:
            r0 = move-exception
            goto L_0x0214
        L_0x020f:
            r0 = move-exception
            goto L_0x0224
        L_0x0211:
            r12 = move-exception
            r0 = r12
            r12 = 0
        L_0x0214:
            com.inmobi.commons.core.a.a r1 = com.inmobi.commons.core.a.a.a()
            com.inmobi.commons.core.e.a r2 = new com.inmobi.commons.core.e.a
            r2.<init>(r0)
            r1.a(r2)
            goto L_0x0230
        L_0x0221:
            r12 = move-exception
            r0 = r12
            r12 = 0
        L_0x0224:
            com.inmobi.commons.core.a.a r1 = com.inmobi.commons.core.a.a.a()
            com.inmobi.commons.core.e.a r2 = new com.inmobi.commons.core.e.a
            r2.<init>(r0)
            r1.a(r2)
        L_0x0230:
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.i.a(com.inmobi.ads.a):boolean");
    }

    /* access modifiers changed from: protected */
    public void b(long j2, boolean z2) {
        StringBuilder sb = new StringBuilder("Asset availability changed (");
        sb.append(z2);
        sb.append(") for placement ID (");
        sb.append(j2);
        sb.append(")");
    }

    /* access modifiers changed from: protected */
    @UiThread
    public void a(long j2, boolean z2, @NonNull a aVar) {
        if (j2 == this.d && 1 == this.a && z2) {
            this.C = aVar.e;
            this.D = aVar.c();
        }
    }

    /* access modifiers changed from: protected */
    @UiThread
    public void c(long j2, @NonNull a aVar) {
        if (j2 == this.d && this.a == 1) {
            if (a(aVar)) {
                a(f(), "ARF", "");
                this.I = SystemClock.elapsedRealtime();
                this.a = 2;
                return;
            }
            b("ParsingFailed");
            a(new InMobiAdRequestStatus(StatusCode.INTERNAL_ERROR), true);
        }
    }

    /* access modifiers changed from: 0000 */
    public final void a(InMobiAdRequestStatus inMobiAdRequestStatus) {
        if (StatusCode.NO_FILL.equals(inMobiAdRequestStatus.getStatusCode())) {
            b("NoFill");
        } else if (StatusCode.SERVER_ERROR.equals(inMobiAdRequestStatus.getStatusCode())) {
            b("ServerError");
        } else if (StatusCode.NETWORK_UNREACHABLE.equals(inMobiAdRequestStatus.getStatusCode())) {
            b("NetworkUnreachable");
        } else if (StatusCode.AD_ACTIVE.equals(inMobiAdRequestStatus.getStatusCode())) {
            b("AdActive");
        } else if (StatusCode.REQUEST_PENDING.equals(inMobiAdRequestStatus.getStatusCode())) {
            b("RequestPending");
        } else if (StatusCode.REQUEST_INVALID.equals(inMobiAdRequestStatus.getStatusCode())) {
            b("RequestInvalid");
        } else if (StatusCode.REQUEST_TIMED_OUT.equals(inMobiAdRequestStatus.getStatusCode())) {
            b("RequestTimedOut");
        } else if (StatusCode.EARLY_REFRESH_REQUEST.equals(inMobiAdRequestStatus.getStatusCode())) {
            b("EarlyRefreshRequest");
        } else {
            if (StatusCode.INTERNAL_ERROR.equals(inMobiAdRequestStatus.getStatusCode())) {
                b("InternalError");
            }
        }
    }

    public void a(MonetizationContext monetizationContext) {
        this.M = monetizationContext;
    }

    public MonetizationContext l() {
        return this.M;
    }

    static boolean m() {
        try {
            RecyclerView.class.getName();
            Picasso.class.getName();
            return false;
        } catch (NoClassDefFoundError unused) {
            return true;
        }
    }

    @UiThread
    public void n() {
        d("AdLoadRequested");
        if (!com.inmobi.commons.core.utilities.d.a()) {
            a(new InMobiAdRequestStatus(StatusCode.NETWORK_UNREACHABLE), true);
        } else if (this.Q) {
            a(new InMobiAdRequestStatus(StatusCode.LOAD_CALLED_AFTER_GET_SIGNALS), false);
        } else {
            this.p.execute(this.K);
        }
    }

    /* access modifiers changed from: 0000 */
    public final void o() {
        boolean z2;
        final b f2 = f();
        final long currentTimeMillis = System.currentTimeMillis();
        if (com.inmobi.commons.core.utilities.d.a()) {
            switch (this.a) {
                case 1:
                case 2:
                case 4:
                    if (f2 != null) {
                        f2.b(new InMobiAdRequestStatus(StatusCode.GET_SIGNALS_CALLED_WHILE_LOADING));
                    }
                    a(f2, "ART", "LoadInProgress");
                    a("AdGetSignalsFailed", currentTimeMillis);
                    z2 = true;
                    break;
                case 6:
                case 7:
                case 8:
                    if (f2 != null) {
                        f2.b(new InMobiAdRequestStatus(StatusCode.AD_ACTIVE));
                    }
                    a(f2, "ART", "ReloadNotPermitted");
                    a("AdGetSignalsFailed", currentTimeMillis);
                    z2 = true;
                    break;
                case 10:
                    if (f2 != null) {
                        f2.b(new InMobiAdRequestStatus(StatusCode.FETCHING_SIGNALS_STATE_ERROR));
                    }
                    a(f2, "ART", "SignalsFetchInProgress");
                    a("AdGetSignalsFailed", currentTimeMillis);
                    z2 = true;
                    break;
                default:
                    z2 = false;
                    break;
            }
        } else {
            if (f2 != null) {
                f2.b(new InMobiAdRequestStatus(StatusCode.NETWORK_UNREACHABLE));
            }
            a(f2, "ART", "NetworkNotAvailable");
            a("AdGetSignalsFailed", currentTimeMillis);
            z2 = true;
        }
        if (!z2) {
            this.Q = true;
            d("AdGetSignalsRequested");
            this.p.execute(new Runnable() {
                public final void run() {
                    i iVar = i.this;
                    iVar.a = 10;
                    String a2 = com.inmobi.ads.c.a.a(iVar.f);
                    if (i.this.R == null) {
                        i iVar2 = i.this;
                        iVar2.R = new com.inmobi.ads.b.a(iVar2, a2);
                    } else {
                        i.this.R.b = a2;
                    }
                    if (f2 != null) {
                        try {
                            byte[] a3 = i.this.R.a();
                            if (a3 == null) {
                                i.this.a = 3;
                                f2.b(new InMobiAdRequestStatus(StatusCode.INTERNAL_ERROR));
                                i.this.a(i.this.f(), "ART", "RequestCreationFailed");
                                i.this.a("AdGetSignalsFailed", currentTimeMillis);
                                return;
                            }
                            f2.a(a3);
                            i.this.a = 11;
                            i.this.a(i.this.f(), "VAR", "");
                            i.this.a("AdGetSignalsSucceeded", currentTimeMillis);
                        } catch (com.inmobi.ads.a.b unused) {
                            i.this.a = 3;
                            f2.b(new InMobiAdRequestStatus(StatusCode.GDPR_COMPLIANCE_ENFORCED));
                        }
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void a(@NonNull String str, long j2) {
        HashMap hashMap = new HashMap();
        hashMap.put("latency", Long.valueOf(System.currentTimeMillis() - j2));
        a(str, (Map<String, Object>) hashMap);
    }

    @UiThread
    public void a(final boolean z2) {
        d("AdPrefetchRequested");
        this.a = 1;
        this.p.execute(new Runnable() {
            public final void run() {
                try {
                    if (!com.inmobi.commons.core.utilities.d.a()) {
                        i.this.b(i.this.d, new InMobiAdRequestStatus(StatusCode.NETWORK_UNREACHABLE));
                        return;
                    }
                    o.a().e();
                    i.N();
                    h hVar = new h();
                    com.inmobi.commons.core.configs.b.a().a((com.inmobi.commons.core.configs.a) hVar, (com.inmobi.commons.core.configs.b.c) null);
                    if (!hVar.g) {
                        i.this.H = System.currentTimeMillis();
                        try {
                            if (i.this.N == null) {
                                i.this.N = new bl(i.this, i.this.g.a(i.this.b()));
                            }
                            i.this.l = i.this.N.a(i.this.u(), z2, i.this.g.c);
                        } catch (com.inmobi.ads.a.a e) {
                            i.y;
                            e.getMessage();
                            if (!i.this.N.a) {
                                i.this.b(i.this.d, new InMobiAdRequestStatus(StatusCode.EARLY_REFRESH_REQUEST));
                            }
                        }
                    }
                } catch (Exception e2) {
                    Logger.a(InternalLogLevel.ERROR, "InMobi", "Unable to Prefetch ad; SDK encountered an unexpected error");
                    i.y;
                    new StringBuilder("Prefetch failed with unexpected error: ").append(e2.getMessage());
                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
                }
            }
        });
    }

    /* access modifiers changed from: 0000 */
    @UiThread
    public void q() {
        a(false);
    }

    private void a(@NonNull final String str, final WeakReference<b> weakReference) {
        this.s.post(new Runnable() {
            public final void run() {
                i iVar = i.this;
                iVar.a = 3;
                iVar.b(str);
                if (i.this.w) {
                    i.y;
                    return;
                }
                b bVar = (b) weakReference.get();
                if (bVar == null) {
                    i.this.g();
                } else if ("int".equals(i.this.b())) {
                    i.this.a(bVar, "AVFB", "");
                    bVar.b();
                } else {
                    bVar.a(new InMobiAdRequestStatus(StatusCode.INTERNAL_ERROR));
                }
            }
        });
    }

    /* access modifiers changed from: 0000 */
    public final void s() {
        AdContainer j2 = j();
        if (j2 != null) {
            j2.a(2, null);
        }
    }

    public final f t() {
        f u2 = u();
        u2.l = true;
        return u2;
    }

    @NonNull
    public final f u() {
        String str = this.g.a;
        long j2 = this.d;
        com.inmobi.commons.core.utilities.uid.d dVar = new com.inmobi.commons.core.utilities.uid.d(this.g.p.a);
        com.inmobi.ads.cache.d.a();
        f fVar = new f(str, j2, dVar, com.inmobi.ads.cache.d.c());
        fVar.f = this.e;
        fVar.g = this.f;
        fVar.e = b();
        fVar.b = "sdkJson";
        fVar.d = this.g.a(b()).b;
        fVar.h = e();
        fVar.c = c();
        fVar.r = this.g.e * 1000;
        fVar.s = this.g.e * 1000;
        fVar.j = this.M;
        fVar.y = O();
        return fVar;
    }

    @UiThread
    public void v() {
        if (!this.w) {
            this.w = true;
            this.k = null;
            this.C = 0;
            this.D = -1;
            this.L.clear();
            AdContainer j2 = j();
            if (j2 != null) {
                j2.destroy();
            }
            this.a = 0;
            this.m = "unknown";
            this.O = false;
            this.u = null;
            this.t = false;
            this.v = false;
            this.x = "";
            this.i = this.b;
            this.A = false;
            this.Q = false;
        }
    }

    /* access modifiers changed from: protected */
    public void z() {
        b("RenderFailed");
    }

    /* access modifiers changed from: 0000 */
    public final void C() {
        this.G.removeMessages(0);
    }

    /* access modifiers changed from: 0000 */
    public final void D() {
        this.s.post(new Runnable() {
            public final void run() {
                i.this.E();
            }
        });
    }

    /* access modifiers changed from: protected */
    public void E() {
        b("RenderTimeOut");
        if (this.a == 2) {
            this.a = 3;
            if (f() != null) {
                f().a(new InMobiAdRequestStatus(StatusCode.INTERNAL_ERROR));
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public final void a(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("errorCode", str);
        c("AdLoadRejected", (Map<String, Object>) hashMap);
    }

    /* access modifiers changed from: 0000 */
    public final void F() {
        HashMap hashMap = new HashMap();
        hashMap.put("latency", Long.valueOf(SystemClock.elapsedRealtime() - this.I));
        c("AdLoadSuccessful", (Map<String, Object>) hashMap);
    }

    /* access modifiers changed from: 0000 */
    public final void b(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("errorCode", str);
        hashMap.put("latency", Long.valueOf(SystemClock.elapsedRealtime() - this.I));
        c("AdLoadFailed", (Map<String, Object>) hashMap);
    }

    /* access modifiers changed from: 0000 */
    public final void c(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("errorCode", str);
        c("AdPrefetchRejected", (Map<String, Object>) hashMap);
    }

    /* access modifiers changed from: 0000 */
    public final void a(b bVar, String str, String str2) {
        if (bVar != null && bVar.i()) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(" ");
            sb.append(str2);
            c cVar = this.g;
            String b2 = b();
            StringBuilder sb2 = new StringBuilder();
            sb2.append(b2);
            sb2.append("Dict");
            com.inmobi.ads.c.a aVar = (com.inmobi.ads.c.a) cVar.g.get(sb2.toString());
            if (aVar == null) {
                aVar = cVar.f;
            }
            if (aVar.h) {
                String str3 = "";
                String str4 = this.l;
                com.inmobi.commons.core.f.b bVar2 = new com.inmobi.commons.core.f.b(UUID.randomUUID().toString(), this.m, str, this.d, str4 != null ? str4 : str3, str2, (String) com.inmobi.commons.core.utilities.b.b.a(O()).get("d-nettype-raw"), b(), System.currentTimeMillis());
                this.P.a(bVar2);
            }
        }
    }

    @VisibleForTesting
    private boolean O() {
        return this.g.i.m && com.inmobi.commons.a.a.d();
    }

    public final void b(String str, Map<String, Object> map) {
        c(str, map);
    }

    public final void a(String str, Map<String, Object> map) {
        c(str, map);
    }

    public final void d(String str) {
        c(str, (Map<String, Object>) new HashMap<String,Object>());
    }

    public final void c(String str, Map<String, Object> map) {
        String str2;
        HashMap hashMap = new HashMap();
        hashMap.put("type", b());
        hashMap.put("plId", Long.valueOf(this.d));
        hashMap.put("impId", this.k);
        hashMap.put("isPreloaded", this.n ? AppEventsConstants.EVENT_PARAM_VALUE_YES : "0");
        String str3 = "networkType";
        switch (com.inmobi.commons.core.utilities.b.b.a()) {
            case 0:
                str2 = Attributes.CARRIER;
                break;
            case 1:
                str2 = "wifi";
                break;
            default:
                str2 = "NIL";
                break;
        }
        hashMap.put(str3, str2);
        hashMap.put("ts", Long.valueOf(System.currentTimeMillis()));
        if (map.get("clientRequestId") == null) {
            hashMap.put("clientRequestId", this.l);
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

    /* access modifiers changed from: 0000 */
    public final void c(@NonNull a aVar) {
        if (aVar instanceof bc) {
            bc bcVar = (bc) aVar;
            Context a2 = a();
            boolean z2 = this.g.k.j;
            for (bq bqVar : this.L) {
                if (z2 && 3 == bqVar.a && "video" == bqVar.b.get(VastResourceXmlManager.CREATIVE_TYPE)) {
                    try {
                        bx bxVar = new bx(bcVar.l, bcVar.m, bcVar.n, bcVar.h(), bcVar.i(), this.g.m);
                        be beVar = (be) new ao(d(), new JSONObject(this.h), this.g, bxVar).c(ShareConstants.VIDEO_URL).get(0);
                        if (a2 != null) {
                            HashSet hashSet = new HashSet();
                            for (NativeTracker nativeTracker : beVar.u) {
                                if (TrackerEventType.TRACKER_EVENT_TYPE_IAS == nativeTracker.b) {
                                    hashSet.add(d(nativeTracker.a, nativeTracker.c));
                                }
                            }
                            if (hashSet.size() != 0) {
                                bqVar.b.put("avidAdSession", w.a(a2, (Set<String>) hashSet));
                                bqVar.b.put("deferred", Boolean.valueOf(true));
                            }
                        }
                    } catch (Exception e2) {
                        new StringBuilder("Setting up impression tracking for AVID encountered an unexpected error: ").append(e2.getMessage());
                        com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
                    }
                }
            }
        }
    }

    @Nullable
    private static String d(String str, Map<String, String> map) {
        if (map == null || str == null) {
            return str;
        }
        for (String str2 : map.keySet()) {
            str = str.replace(str2, (CharSequence) map.get(str2));
        }
        return str;
    }

    public final void a(long j2) {
        d("AdPrefetchSuccessful");
        if (!this.w && a() != null) {
            Message obtain = Message.obtain();
            obtain.what = 14;
            Bundle bundle = new Bundle();
            bundle.putLong(AudienceNetworkActivity.PLACEMENT_ID, j2);
            obtain.setData(bundle);
            this.J.sendMessage(obtain);
        }
    }

    public final void b(long j2, InMobiAdRequestStatus inMobiAdRequestStatus) {
        if (StatusCode.EARLY_REFRESH_REQUEST.equals(inMobiAdRequestStatus.getStatusCode())) {
            c("EarlyRefreshRequest");
        } else if (StatusCode.NETWORK_UNREACHABLE.equals(inMobiAdRequestStatus.getStatusCode())) {
            c("NetworkUnreachable");
        } else {
            d("AdPrefetchFailed");
        }
        if (!this.w && a() != null) {
            Message obtain = Message.obtain();
            obtain.what = 13;
            obtain.obj = inMobiAdRequestStatus;
            Bundle bundle = new Bundle();
            bundle.putLong(AudienceNetworkActivity.PLACEMENT_ID, j2);
            obtain.setData(bundle);
            this.J.sendMessage(obtain);
        }
    }

    public final void e(final String str) {
        this.p.execute(new Runnable() {
            public final void run() {
                if (i.this.k == null || str == null) {
                    i.y;
                    return;
                }
                d.a();
                String f = i.this.k;
                String str = str;
                com.inmobi.commons.core.d.b a2 = com.inmobi.commons.core.d.b.a();
                a c = d.c(f);
                int i = 0;
                if (c != null) {
                    c.i = str;
                    i = a2.b("ad", c.a(), "imp_id=?", new String[]{f});
                }
                i.y;
                StringBuilder sb = new StringBuilder("Updated ");
                sb.append(i);
                sb.append("for blob ");
                sb.append(str);
            }
        });
    }

    public final void a(final String str, final String str2, @NonNull final com.inmobi.rendering.b bVar) {
        this.p.execute(new Runnable() {
            public final void run() {
                try {
                    if (i.this.k != null) {
                        d.a();
                        a c2 = d.c(i.this.k);
                        if (c2 != null) {
                            bVar.a(str, str2, c2.i);
                            i.y;
                            return;
                        }
                        i.y;
                        bVar.a(str, str2, "");
                        return;
                    }
                    i.y;
                    bVar.a(str, str2, "");
                } catch (Exception e) {
                    i.y;
                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                }
            }
        });
    }

    /* access modifiers changed from: 0000 */
    public final void I() {
        this.p.execute(new Runnable() {
            public final void run() {
                try {
                    final ao aoVar = new ao(i.this.d(), new JSONObject(i.this.h), i.this.g, null);
                    i.this.s.post(new Runnable() {
                        public final void run() {
                            try {
                                bf bfVar = aoVar.k;
                                if (bfVar != null) {
                                    i.this.u = new RenderView(i.this.a(), new RenderingProperties(i.this.d()), i.this.L, i.this.k);
                                    i.this.u.a(i.this.S, i.this.g);
                                    i.this.u.j = true;
                                    i.this.u.setBlobProvider(i.this);
                                    i.this.u.setIsPreload(true);
                                    i.this.u.setPlacementId(i.this.d);
                                    i.this.u.setCreativeId(i.this.x);
                                    i.this.u.setAllowAutoRedirection(i.this.A);
                                    if (i.this.r == 0) {
                                        i.this.a(true, i.this.u);
                                    }
                                    if (MoPubBrowser.DESTINATION_URL_KEY.equals(bfVar.z)) {
                                        i.this.u.b((String) bfVar.e);
                                    } else {
                                        i.this.u.a((String) bfVar.e);
                                    }
                                }
                                i.e(i.this);
                            } catch (Exception e) {
                                i.y;
                                i.this.a = 3;
                                i.this.a(new InMobiAdRequestStatus(StatusCode.INTERNAL_ERROR), false);
                                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                            }
                        }
                    });
                } catch (Exception e) {
                    i.y;
                    i.this.s.post(new Runnable() {
                        public final void run() {
                            i.this.a = 3;
                            i.this.a(new InMobiAdRequestStatus(StatusCode.INTERNAL_ERROR), false);
                        }
                    });
                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                }
            }
        });
    }

    /* access modifiers changed from: 0000 */
    @UiThread
    public final void J() {
        if (this.t && this.v && this.O) {
            C();
            K();
        }
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x002f A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0030  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x003d  */
    @android.support.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.inmobi.ads.AdContainer j() {
        /*
            r6 = this;
            int r0 = r6.a
            java.lang.String r1 = r6.m
            int r2 = r1.hashCode()
            r3 = -1084172778(0xffffffffbf60d616, float:-0.8782667)
            r4 = 2
            r5 = 1
            if (r2 == r3) goto L_0x001f
            r3 = 3213227(0x3107ab, float:4.50269E-39)
            if (r2 == r3) goto L_0x0015
            goto L_0x0029
        L_0x0015:
            java.lang.String r2 = "html"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0029
            r1 = 1
            goto L_0x002a
        L_0x001f:
            java.lang.String r2 = "inmobiJson"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0029
            r1 = 2
            goto L_0x002a
        L_0x0029:
            r1 = -1
        L_0x002a:
            r2 = 3
            r3 = 0
            switch(r1) {
                case 1: goto L_0x003d;
                case 2: goto L_0x0030;
                default: goto L_0x002f;
            }
        L_0x002f:
            return r3
        L_0x0030:
            if (r0 == 0) goto L_0x003c
            if (r5 == r0) goto L_0x003c
            if (r2 == r0) goto L_0x003c
            if (r4 != r0) goto L_0x0039
            goto L_0x003c
        L_0x0039:
            com.inmobi.ads.ah r0 = r6.o
            return r0
        L_0x003c:
            return r3
        L_0x003d:
            if (r0 == 0) goto L_0x0049
            if (r5 == r0) goto L_0x0049
            if (r2 != r0) goto L_0x0044
            goto L_0x0049
        L_0x0044:
            com.inmobi.rendering.RenderView r0 = r6.k()
            return r0
        L_0x0049:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.i.j():com.inmobi.ads.AdContainer");
    }

    public final void a(long j2, boolean z2) {
        if (!this.w && a() != null) {
            Message obtain = Message.obtain();
            obtain.what = 4;
            Bundle bundle = new Bundle();
            bundle.putLong(AudienceNetworkActivity.PLACEMENT_ID, j2);
            bundle.putBoolean("assetAvailable", z2);
            obtain.setData(bundle);
            this.J.sendMessage(obtain);
        }
    }

    public final void b(long j2, a aVar) {
        if (!this.w && a() != null) {
            this.I = SystemClock.elapsedRealtime();
            Message obtain = Message.obtain();
            obtain.what = 1;
            obtain.obj = aVar;
            Bundle bundle = new Bundle();
            bundle.putLong(AudienceNetworkActivity.PLACEMENT_ID, j2);
            bundle.putBoolean("adAvailable", true);
            obtain.setData(bundle);
            this.J.sendMessage(obtain);
        }
    }

    public final void a(long j2, @NonNull a aVar) {
        if (!this.w && a() != null) {
            Message obtain = Message.obtain();
            obtain.what = 2;
            Bundle bundle = new Bundle();
            bundle.putLong(AudienceNetworkActivity.PLACEMENT_ID, j2);
            obtain.setData(bundle);
            obtain.obj = aVar;
            this.J.sendMessage(obtain);
        }
    }

    public void a(final long j2, final InMobiAdRequestStatus inMobiAdRequestStatus) {
        if (!this.w && a() != null) {
            this.s.post(new Runnable() {
                public final void run() {
                    try {
                        if (j2 == i.this.d) {
                            i.this.a(i.this.f(), "ARN", "");
                            StringBuilder sb = new StringBuilder("Failed to fetch ad for placement id: ");
                            sb.append(i.this.d);
                            sb.append(", reason phrase available in onAdLoadFailed callback.");
                            Logger.a(InternalLogLevel.DEBUG, "InMobi", sb.toString());
                            i.this.a(inMobiAdRequestStatus, true);
                        }
                    } catch (Exception e) {
                        Logger.a(InternalLogLevel.ERROR, "[InMobi]", "Unable to load Ad; SDK encountered an unexpected error");
                        i.y;
                        new StringBuilder("onAdFetchFailed with error: ").append(e.getMessage());
                        com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                    }
                }
            });
        }
    }

    /* access modifiers changed from: 0000 */
    @UiThread
    public void a(InMobiAdRequestStatus inMobiAdRequestStatus, boolean z2) {
        if (this.a == 1 && z2) {
            this.a = 3;
        }
        b f2 = f();
        if (f2 != null) {
            f2.a(inMobiAdRequestStatus);
        }
        a(inMobiAdRequestStatus);
    }

    /* access modifiers changed from: 0000 */
    @UiThread
    public final void a(@Nullable final byte[] bArr) {
        boolean z2;
        if (!com.inmobi.commons.core.utilities.b.e.e()) {
            v();
            this.s.post(new Runnable() {
                public final void run() {
                    i.this.a(new InMobiAdRequestStatus(StatusCode.GDPR_COMPLIANCE_ENFORCED), false);
                }
            });
            z2 = true;
        } else {
            int i2 = this.a;
            if (i2 != 1) {
                switch (i2) {
                    case 6:
                    case 7:
                    case 8:
                        this.s.post(new Runnable() {
                            public final void run() {
                                i.this.a(new InMobiAdRequestStatus(StatusCode.AD_ACTIVE), false);
                            }
                        });
                        z2 = true;
                        break;
                    default:
                        switch (i2) {
                            case 10:
                                this.s.post(new Runnable() {
                                    public final void run() {
                                        i.this.a(new InMobiAdRequestStatus(StatusCode.FETCHING_SIGNALS_STATE_ERROR), false);
                                    }
                                });
                                z2 = true;
                                break;
                            case 11:
                                z2 = false;
                                break;
                            default:
                                this.s.post(new Runnable() {
                                    public final void run() {
                                        i.this.a(new InMobiAdRequestStatus(StatusCode.GET_SIGNALS_NOT_CALLED_FOR_LOAD_WITH_RESPONSE), false);
                                    }
                                });
                                z2 = true;
                                break;
                        }
                }
            } else {
                this.s.post(new Runnable() {
                    public final void run() {
                        i.this.a(new InMobiAdRequestStatus(StatusCode.LOAD_WITH_RESPONSE_CALLED_WHILE_LOADING), false);
                    }
                });
                z2 = true;
            }
        }
        if (!z2) {
            if (bArr == null || bArr.length == 0) {
                a(new InMobiAdRequestStatus(StatusCode.INVALID_RESPONSE_IN_LOAD), true);
            } else if (this.R == null) {
                a(new InMobiAdRequestStatus(StatusCode.INTERNAL_ERROR), true);
            } else {
                this.p.execute(new Runnable() {
                    public final void run() {
                        i iVar = i.this;
                        i.a(iVar, bArr, iVar.R);
                    }
                });
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public final boolean p() {
        int i2 = this.a;
        if (1 == i2) {
            b(this.d, new InMobiAdRequestStatus(StatusCode.REQUEST_PENDING));
            StringBuilder sb = new StringBuilder("An ad prefetch is already in progress. Please wait for the prefetch to complete before requesting for another ad for placement id: ");
            sb.append(this.d);
            Logger.a(InternalLogLevel.ERROR, "InMobi", sb.toString());
            return true;
        } else if (8 == i2 || 7 == i2) {
            b(this.d, new InMobiAdRequestStatus(StatusCode.AD_ACTIVE));
            StringBuilder sb2 = new StringBuilder("An ad is currently being viewed by the user. Please wait for the user to close the ad before requesting for another ad for placement id: ");
            sb2.append(this.d);
            Logger.a(InternalLogLevel.ERROR, "InMobi", sb2.toString());
            return true;
        } else {
            if (2 == i2) {
                if (AdType.HTML.equals(this.m)) {
                    b(this.d, new InMobiAdRequestStatus(StatusCode.REQUEST_PENDING));
                    StringBuilder sb3 = new StringBuilder("An ad load is already in progress. Please wait for the load to complete before requesting prefetch for another ad for placement id: ");
                    sb3.append(this.d);
                    Logger.a(InternalLogLevel.ERROR, "InMobi", sb3.toString());
                    return true;
                } else if ("inmobiJson".equals(this.m)) {
                    a(this.d);
                    return true;
                }
            }
            int i3 = this.a;
            if (5 != i3 && 9 != i3) {
                return false;
            }
            a(this.d);
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public int r() {
        String str;
        boolean z2 = true;
        try {
            this.a = 1;
            o.a().e();
            com.inmobi.commons.core.utilities.uid.c.a();
            com.inmobi.commons.core.utilities.uid.c.c();
            h hVar = new h();
            com.inmobi.commons.core.configs.b.a().a((com.inmobi.commons.core.configs.a) hVar, (com.inmobi.commons.core.configs.b.c) null);
            if (!hVar.g) {
                f u2 = u();
                this.H = System.currentTimeMillis();
                h i2 = i();
                try {
                    int i3 = this.g.c;
                    h.a();
                    i2.c = u2;
                    if ("int".equals(i2.c.e)) {
                        h.c();
                        List c2 = i2.b.c(i2.c.a, i2.c.c, i2.c.j, com.inmobi.ads.c.a.a(i2.c.g));
                        if (c2.size() == 0) {
                            if (SystemClock.elapsedRealtime() - i2.e >= ((long) (i3 * 1000))) {
                                z2 = false;
                            }
                            if (!z2) {
                                str = i2.a(i2.c, i2.c.c().equals(AppEventsConstants.EVENT_PARAM_VALUE_YES));
                            } else {
                                throw new com.inmobi.ads.a.a("Ignoring request to fetch an ad from the network sooner than the minimum request interval");
                            }
                        } else {
                            str = ((a) c2.get(0)).h;
                            if ("INMOBIJSON".equalsIgnoreCase(((a) c2.get(0)).e())) {
                                i2.a.b(i2.c.a, (a) c2.get(0));
                                i2.a(c2);
                            } else {
                                str = i2.b();
                            }
                        }
                    } else {
                        str = i2.b();
                    }
                    HashMap hashMap = new HashMap();
                    hashMap.put("im-accid", com.inmobi.commons.a.a.e());
                    hashMap.put("isPreloaded", i2.c.c());
                    i2.a.a("AdCacheAdRequested", (Map<String, Object>) hashMap);
                    this.l = str;
                    a(f(), "VAR", "");
                    if (this.n) {
                        d("AdPreLoadRequested");
                    }
                } catch (com.inmobi.ads.a.a e2) {
                    e2.getMessage();
                    this.s.post(new Runnable() {
                        public final void run() {
                            i.this.a(new InMobiAdRequestStatus(StatusCode.EARLY_REFRESH_REQUEST), true);
                        }
                    });
                }
                return 0;
            }
            d("LoadAfterMonetizationDisabled");
            Logger.a(InternalLogLevel.ERROR, y, "SDK will not perform this load operation as monetization has been disabled. Please contact InMobi for further info.");
            return -1;
        } catch (Exception e3) {
            Logger.a(InternalLogLevel.ERROR, "InMobi", "Unable to load ad; SDK encountered an unexpected error");
            new StringBuilder("Load failed with unexpected error: ").append(e3.getMessage());
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e3));
            return -2;
        }
    }

    /* access modifiers changed from: 0000 */
    public final void a(b bVar, @NonNull String str, @Nullable Runnable runnable, @Nullable Looper looper) {
        Runnable runnable2 = runnable;
        Looper looper2 = looper;
        if (AdType.HTML.equals(this.m)) {
            final String str2 = str;
            this.s.post(new Runnable() {
                public final void run() {
                    i.this.k().a(str2);
                    i.e(i.this);
                }
            });
            return;
        }
        if ("inmobiJson".equals(this.m)) {
            final WeakReference weakReference = new WeakReference(bVar);
            try {
                this.I = SystemClock.elapsedRealtime();
                ao aoVar = new ao(d(), new JSONObject(this.h), this.g, this.j);
                if (!aoVar.c() || a() == null) {
                    a("DataModelValidationFailed", weakReference);
                    return;
                }
                ah a2 = b.a(a(), new RenderingProperties(d()), aoVar, this.k, this.l, this.L, this.g, this.d, this.A, this.x);
                a2.a((com.inmobi.ads.ah.c) new com.inmobi.ads.ah.c() {
                    public final void a() {
                        if (!i.this.w) {
                            b bVar = (b) weakReference.get();
                            if (bVar != null) {
                                i.this.a(bVar, "AVFB", "");
                                bVar.b();
                                return;
                            }
                            i.this.g();
                        }
                    }

                    public final void b() {
                        i.this.d("AdRendered");
                        if (!i.this.w) {
                            i.this.s.post(new Runnable() {
                                public final void run() {
                                    i.this.b((b) weakReference.get());
                                }
                            });
                        }
                    }

                    public final void c() {
                        if (!i.this.w) {
                            b bVar = (b) weakReference.get();
                            if (bVar != null) {
                                bVar.c();
                            } else {
                                i.this.g();
                            }
                        }
                    }

                    public final void d() {
                        StringBuilder sb = new StringBuilder("Successfully impressed ad for placement id: ");
                        sb.append(i.this.d);
                        Logger.a(InternalLogLevel.DEBUG, "InMobi", sb.toString());
                        if (!i.this.w) {
                            b bVar = (b) weakReference.get();
                            if (bVar != null) {
                                bVar.g();
                            } else {
                                i.this.g();
                            }
                        }
                    }

                    public final void e() {
                        StringBuilder sb = new StringBuilder("Ad interaction for placement id: ");
                        sb.append(i.this.d);
                        Logger.a(InternalLogLevel.DEBUG, "InMobi", sb.toString());
                        if (!i.this.w) {
                            b bVar = (b) weakReference.get();
                            if (bVar != null) {
                                bVar.a((Map<Object, Object>) new HashMap<Object,Object>());
                            } else {
                                i.this.g();
                            }
                        }
                    }

                    public final void f() {
                        if (!i.this.w) {
                            StringBuilder sb = new StringBuilder("Ad dismissed for placement id: ");
                            sb.append(i.this.d);
                            Logger.a(InternalLogLevel.DEBUG, "InMobi", sb.toString());
                            i.this.s.post(new Runnable() {
                                public final void run() {
                                    i.this.c((b) weakReference.get());
                                }
                            });
                        }
                    }

                    public final void a(Map<String, String> map) {
                        if (!i.this.w) {
                            b bVar = (b) weakReference.get();
                            if (bVar != null) {
                                bVar.b((Map<Object, Object>) new HashMap<Object,Object>(map));
                            } else {
                                i.this.g();
                            }
                        }
                    }

                    public final void g() {
                        if (!i.this.w) {
                            b bVar = (b) weakReference.get();
                            if (bVar != null) {
                                bVar.f();
                            } else {
                                i.this.g();
                            }
                        }
                    }

                    public final void h() {
                        if (!i.this.w) {
                            b bVar = (b) weakReference.get();
                            if (bVar != null) {
                                bVar.h();
                            } else {
                                i.this.g();
                            }
                        }
                    }

                    public final void a(String str, Map<String, Object> map) {
                        i.this.c(str, map);
                    }

                    public final void i() {
                        if (!i.this.w) {
                            b bVar = (b) weakReference.get();
                            if (bVar != null) {
                                bVar.j();
                            } else {
                                i.this.g();
                            }
                        }
                    }

                    public final void a(boolean z) {
                        if (!i.this.w) {
                            b bVar = (b) weakReference.get();
                            if (bVar != null) {
                                bVar.b(z);
                            } else {
                                i.this.g();
                            }
                        }
                    }
                });
                this.o = a2;
                if (!(runnable2 == null || looper2 == null)) {
                    new Handler(looper2).post(runnable2);
                }
            } catch (JSONException e2) {
                a("InternalError", weakReference);
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
            } catch (Exception e3) {
                new StringBuilder("Encountered unexpected error in loading ad markup into container: ").append(e3.getMessage());
                a("InternalError", weakReference);
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e3));
            }
        }
    }

    public final void w() {
        if (!this.w && a() != null) {
            this.J.sendEmptyMessage(11);
        }
    }

    public final void y() {
        if (!this.w && a() != null) {
            this.J.sendEmptyMessage(12);
        }
    }

    public void a(RenderView renderView) {
        if (!this.w && a() != null) {
        }
    }

    public void b(RenderView renderView) {
        if (!this.w && a() != null) {
        }
    }

    public final void A() {
        if (!this.w && a() != null && 7 == this.a) {
            this.a = 3;
            a(f(), "AVFB", "");
            if (f() != null) {
                f().b();
            }
        }
    }

    public void c(RenderView renderView) {
        if (!this.w && a() != null) {
        }
    }

    public void d(RenderView renderView) {
        if (!this.w && a() != null) {
        }
    }

    public final void a(HashMap<Object, Object> hashMap) {
        if (!this.w && a() != null) {
            new StringBuilder("Ad reward action completed. Params:").append(hashMap.toString());
            if (f() != null) {
                f().b((Map<Object, Object>) hashMap);
            }
        }
    }

    public final void b(HashMap<Object, Object> hashMap) {
        if (!this.w && a() != null) {
            new StringBuilder("Ad interaction. Params:").append(hashMap.toString());
            d("AdInteracted");
            if (f() != null) {
                f().a((Map<Object, Object>) hashMap);
            }
        }
    }

    public final void B() {
        if (!(this.w || a() == null || f() == null)) {
            f().f();
        }
    }

    /* access modifiers changed from: 0000 */
    public final void a(boolean z2, RenderView renderView) {
        boolean z3 = this.g.k.j;
        for (bq bqVar : this.L) {
            if (z3 && 3 == bqVar.a) {
                try {
                    AbstractAvidAdSession a2 = v.a(a(), z2, (String) bqVar.b.get(VastResourceXmlManager.CREATIVE_TYPE), renderView);
                    if (a2 != null) {
                        bqVar.b.put("avidAdSession", a2);
                        bqVar.b.put("deferred", Boolean.valueOf(z2));
                    }
                } catch (Exception e2) {
                    new StringBuilder("Setting up impression tracking for IAS encountered an unexpected error: ").append(e2.getMessage());
                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
                }
            }
        }
    }

    public void H() {
        if (1 == this.a) {
            e eVar = this.q;
            if (eVar != null) {
                eVar.a(this);
            }
        }
    }

    public void b(InMobiAdRequestStatus inMobiAdRequestStatus) {
        if (1 == this.a) {
            e eVar = this.q;
            if (eVar != null) {
                eVar.a(this, inMobiAdRequestStatus);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x002f A[Catch:{ Exception -> 0x0049 }] */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0039 A[Catch:{ Exception -> 0x0049 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ void a(com.inmobi.ads.i r3, byte[] r4, com.inmobi.ads.b.a r5) {
        /*
            java.lang.String r0 = "AdLoadWithResponseRequested"
            long r1 = r5.c     // Catch:{ Exception -> 0x0049 }
            r3.a(r0, r1)     // Catch:{ Exception -> 0x0049 }
            r0 = 1
            r3.a = r0     // Catch:{ Exception -> 0x0049 }
            com.inmobi.ads.b.b r0 = r5.d     // Catch:{ Exception -> 0x0049 }
            if (r0 == 0) goto L_0x0041
            com.inmobi.ads.b.b r0 = r5.d     // Catch:{ Exception -> 0x0049 }
            com.inmobi.ads.f r1 = r0.a     // Catch:{ Exception -> 0x0049 }
            byte[] r4 = r1.a(r4)     // Catch:{ Exception -> 0x0049 }
            if (r4 == 0) goto L_0x002c
            int r1 = r4.length     // Catch:{ Exception -> 0x0049 }
            if (r1 != 0) goto L_0x001c
            goto L_0x002c
        L_0x001c:
            com.inmobi.commons.core.network.d r1 = new com.inmobi.commons.core.network.d     // Catch:{ Exception -> 0x0049 }
            r1.<init>()     // Catch:{ Exception -> 0x0049 }
            r1.b(r4)     // Catch:{ Exception -> 0x0049 }
            com.inmobi.ads.g r4 = new com.inmobi.ads.g     // Catch:{ Exception -> 0x0049 }
            com.inmobi.ads.f r0 = r0.a     // Catch:{ Exception -> 0x0049 }
            r4.<init>(r0, r1)     // Catch:{ Exception -> 0x0049 }
            goto L_0x002d
        L_0x002c:
            r4 = 0
        L_0x002d:
            if (r4 == 0) goto L_0x0039
            com.inmobi.ads.i r5 = r5.a     // Catch:{ Exception -> 0x0049 }
            com.inmobi.ads.h r5 = r5.i()     // Catch:{ Exception -> 0x0049 }
            r5.a(r4)     // Catch:{ Exception -> 0x0049 }
            return
        L_0x0039:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException     // Catch:{ Exception -> 0x0049 }
            java.lang.String r5 = "Unable to decrypt response."
            r4.<init>(r5)     // Catch:{ Exception -> 0x0049 }
            throw r4     // Catch:{ Exception -> 0x0049 }
        L_0x0041:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException     // Catch:{ Exception -> 0x0049 }
            java.lang.String r5 = "GMARequest is null."
            r4.<init>(r5)     // Catch:{ Exception -> 0x0049 }
            throw r4     // Catch:{ Exception -> 0x0049 }
        L_0x0049:
            android.os.Handler r4 = r3.s
            com.inmobi.ads.i$16 r5 = new com.inmobi.ads.i$16
            r5.<init>()
            r4.post(r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.i.a(com.inmobi.ads.i, byte[], com.inmobi.ads.b.a):void");
    }

    static /* synthetic */ void N() {
        com.inmobi.commons.core.utilities.uid.c.a();
        com.inmobi.commons.core.utilities.uid.c.c();
    }

    static /* synthetic */ void e(i iVar) {
        iVar.C();
        iVar.G.sendEmptyMessageDelayed(0, (long) (iVar.g.i.a * 1000));
    }
}
