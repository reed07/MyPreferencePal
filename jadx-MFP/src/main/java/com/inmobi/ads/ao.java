package com.inmobi.ads;

import android.graphics.Point;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.webkit.URLUtil;
import com.brightcove.player.event.AbstractEvent;
import com.brightcove.player.model.VideoFields;
import com.facebook.internal.NativeProtocol;
import com.facebook.share.internal.ShareConstants;
import com.inmobi.ads.AdContainer.RenderingProperties.PlacementType;
import com.inmobi.commons.core.e.b;
import com.inmobi.commons.core.utilities.b.c;
import com.mopub.common.Constants;
import com.mopub.common.MoPubBrowser;
import com.mopub.volley.BuildConfig;
import com.myfitnesspal.shared.constants.Constants.Analytics;
import com.myfitnesspal.shared.model.v2.MfpGoalPreferences.MacroGoalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: NativeDataModel */
public class ao {
    private static final String l = "ao";
    int a;
    public boolean b;
    public boolean c;
    public am d;
    JSONArray e;
    final ao f;
    @Nullable
    Map<String, String> g;
    Map<String, List<ak>> h;
    a i;
    boolean j;
    bf k;
    private JSONObject m;
    private JSONObject n;
    private Map<String, ak> o;
    private Map<String, String> p;
    @Nullable
    private bx q;
    private c r;
    private PlacementType s;
    private boolean t;

    /* compiled from: NativeDataModel */
    class a {
        JSONObject a;
        @NonNull
        C0041a b = new C0041a();
        ak c;

        /* renamed from: com.inmobi.ads.ao$a$a reason: collision with other inner class name */
        /* compiled from: NativeDataModel */
        class C0041a {
            public String a;
            public String b;
            public String c;
            public String d;
            public float e;
            public String f;
            public boolean g;

            C0041a() {
            }
        }

        a() {
        }
    }

    ao() {
        this.f = null;
    }

    public ao(@NonNull PlacementType placementType, @NonNull JSONObject jSONObject, @Nullable c cVar, @Nullable bx bxVar) {
        this(placementType, jSONObject, null, false, cVar, bxVar);
    }

    public ao(@NonNull PlacementType placementType, @NonNull JSONObject jSONObject, @Nullable ao aoVar, boolean z, @Nullable c cVar, @Nullable bx bxVar) {
        this(placementType, jSONObject, aoVar, z, cVar, bxVar, 0);
    }

    private ao(@NonNull PlacementType placementType, @NonNull JSONObject jSONObject, @Nullable ao aoVar, boolean z, @Nullable c cVar, @Nullable bx bxVar, byte b2) {
        this.s = placementType;
        this.f = aoVar;
        if (cVar == null) {
            cVar = new c();
        }
        this.r = cVar;
        this.m = jSONObject;
        this.a = 0;
        this.b = false;
        this.q = bxVar;
        this.o = new HashMap();
        this.p = new HashMap();
        this.h = new HashMap();
        this.i = new a();
        this.t = z;
        f();
    }

    /* access modifiers changed from: 0000 */
    public final JSONObject a() {
        try {
            return this.e.getJSONObject(0);
        } catch (JSONException e2) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
            return null;
        }
    }

    /* access modifiers changed from: 0000 */
    public final int b() {
        am amVar = this.d;
        if (amVar == null) {
            return 0;
        }
        Iterator it = amVar.iterator();
        while (it.hasNext()) {
            ak akVar = (ak) it.next();
            if (akVar.d.equalsIgnoreCase("card_scrollable")) {
                return ((am) akVar).C;
            }
        }
        return 0;
    }

    /* access modifiers changed from: 0000 */
    public final am a(int i2) {
        Iterator it = this.d.iterator();
        while (it.hasNext()) {
            ak akVar = (ak) it.next();
            if (akVar.d.equalsIgnoreCase("card_scrollable")) {
                am amVar = (am) akVar;
                if (i2 >= amVar.C) {
                    return null;
                }
                return (am) amVar.a(i2);
            }
        }
        return null;
    }

    static am a(@NonNull ak akVar) {
        if (akVar instanceof am) {
            am amVar = (am) akVar;
            if (a(amVar)) {
                return amVar;
            }
        }
        for (am amVar2 = (am) akVar.t; amVar2 != null; amVar2 = (am) amVar2.t) {
            if (a(amVar2)) {
                return amVar2;
            }
        }
        return null;
    }

    private void d() {
        List list;
        a aVar;
        for (ak akVar : c(ShareConstants.IMAGE_URL)) {
            if (!URLUtil.isValidUrl((String) akVar.e)) {
                ak a2 = a(this, akVar);
                if (a2 == null) {
                    StringBuilder sb = new StringBuilder("Could not find referenced asset for asset (");
                    sb.append(akVar.d);
                    sb.append(")");
                } else if (a2.b.equals(akVar.b)) {
                    akVar.e = a2.e;
                } else if (ShareConstants.VIDEO_URL.equals(a2.b) && 1 != a2.m && 2 == a2.m) {
                    be beVar = (be) a2;
                    by b2 = beVar.b();
                    bt a3 = bs.a(beVar, akVar);
                    if (a3 == null) {
                        list = null;
                    } else {
                        list = a3.a(1);
                    }
                    if (list != null) {
                        Iterator it = list.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            aVar = (a) it.next();
                            if (URLUtil.isValidUrl(aVar.b)) {
                                break;
                            }
                        }
                    }
                    aVar = null;
                    if (a3 != null && aVar != null) {
                        b2.a(a3);
                        new StringBuilder("Setting image asset value: ").append(aVar.b);
                        akVar.e = aVar.b;
                        akVar.a(a3.a(TrackerEventType.TRACKER_EVENT_TYPE_CREATIVE_VIEW));
                        akVar.a(beVar.u, TrackerEventType.TRACKER_EVENT_TYPE_ERROR);
                    } else if (b2.e().size() > 0) {
                        a(beVar);
                        a(a3 == null ? "NoBestFitCompanion" : "NoValidResource", "Static", MoPubBrowser.DESTINATION_URL_KEY, (String) null, (String) null);
                    }
                }
            }
        }
    }

    private static void a(String str, String str2, String str3, String str4, String str5) {
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("errorCode", str);
            hashMap.put("type", str2);
            hashMap.put("dataType", str3);
            hashMap.put("clientRequestId", null);
            hashMap.put("impId", null);
            b.a();
            b.a("ads", "EndCardCompanionFailure", hashMap);
        } catch (Exception e2) {
            StringBuilder sb = new StringBuilder("Error in sendTelemetryEventForCompanionFailure : (");
            sb.append(e2.getMessage());
            sb.append(")");
        }
    }

    private void e() {
        String str;
        for (ak akVar : c("WEBVIEW")) {
            bf bfVar = (bf) akVar;
            if (!MoPubBrowser.DESTINATION_URL_KEY.equals(bfVar.z) && !"HTML".equals(bfVar.z)) {
                ak a2 = a(this, akVar);
                if (a2 == null) {
                    StringBuilder sb = new StringBuilder("Could not find referenced asset for asset (");
                    sb.append(akVar.d);
                    sb.append(")");
                } else if (a2.b.equals(akVar.b)) {
                    akVar.e = a2.e;
                } else if (ShareConstants.VIDEO_URL.equals(a2.b) && 2 == a2.m) {
                    be beVar = (be) a2;
                    by b2 = beVar.b();
                    bt a3 = bs.a(beVar, akVar);
                    if (a3 == null) {
                        str = null;
                    } else {
                        str = a(a3, bfVar);
                    }
                    boolean equals = "REF_IFRAME".equals(bfVar.z);
                    boolean equals2 = "REF_HTML".equals(bfVar.z);
                    if (a3 == null || ((equals && !URLUtil.isValidUrl(str)) || (equals2 && str == null))) {
                        if (b2.e().size() > 0) {
                            a(beVar);
                            a(a3 == null ? "NoBestFitCompanion" : "NoValidResource", "Rich", bfVar.z, (String) null, (String) null);
                        }
                        bfVar.z = Analytics.UNKNOWN;
                    } else {
                        b2.a(a3);
                        akVar.e = str;
                        akVar.a(a3.a(TrackerEventType.TRACKER_EVENT_TYPE_CREATIVE_VIEW));
                    }
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0062 A[Catch:{ JSONException -> 0x029c }] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0064 A[Catch:{ JSONException -> 0x029c }] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0066 A[Catch:{ JSONException -> 0x029c }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void f() {
        /*
            r10 = this;
            org.json.JSONObject r0 = r10.m     // Catch:{ JSONException -> 0x029c }
            java.lang.String r1 = "styleRefs"
            org.json.JSONObject r0 = r0.optJSONObject(r1)     // Catch:{ JSONException -> 0x029c }
            r10.n = r0     // Catch:{ JSONException -> 0x029c }
            org.json.JSONObject r0 = r10.m     // Catch:{ JSONException -> 0x029c }
            java.lang.String r1 = "orientation"
            boolean r0 = r0.isNull(r1)     // Catch:{ JSONException -> 0x029c }
            r1 = 2
            r2 = 1
            r3 = 0
            if (r0 == 0) goto L_0x0019
            r0 = 0
            goto L_0x0067
        L_0x0019:
            org.json.JSONObject r0 = r10.m     // Catch:{ JSONException -> 0x029c }
            java.lang.String r4 = "orientation"
            java.lang.String r0 = r0.getString(r4)     // Catch:{ JSONException -> 0x029c }
            java.util.Locale r4 = java.util.Locale.US     // Catch:{ JSONException -> 0x029c }
            java.lang.String r0 = r0.toLowerCase(r4)     // Catch:{ JSONException -> 0x029c }
            java.lang.String r0 = r0.trim()     // Catch:{ JSONException -> 0x029c }
            r4 = -1
            int r5 = r0.hashCode()     // Catch:{ JSONException -> 0x029c }
            r6 = -1626174665(0xffffffff9f128b37, float:-3.103186E-20)
            if (r5 == r6) goto L_0x0054
            r6 = 729267099(0x2b77bb9b, float:8.8012383E-13)
            if (r5 == r6) goto L_0x004a
            r6 = 1430647483(0x5545f2bb, float:1.36028944E13)
            if (r5 == r6) goto L_0x0040
            goto L_0x005e
        L_0x0040:
            java.lang.String r5 = "landscape"
            boolean r0 = r0.equals(r5)     // Catch:{ JSONException -> 0x029c }
            if (r0 == 0) goto L_0x005e
            r0 = 3
            goto L_0x005f
        L_0x004a:
            java.lang.String r5 = "portrait"
            boolean r0 = r0.equals(r5)     // Catch:{ JSONException -> 0x029c }
            if (r0 == 0) goto L_0x005e
            r0 = 2
            goto L_0x005f
        L_0x0054:
            java.lang.String r5 = "unspecified"
            boolean r0 = r0.equals(r5)     // Catch:{ JSONException -> 0x029c }
            if (r0 == 0) goto L_0x005e
            r0 = 1
            goto L_0x005f
        L_0x005e:
            r0 = -1
        L_0x005f:
            switch(r0) {
                case 2: goto L_0x0066;
                case 3: goto L_0x0064;
                default: goto L_0x0062;
            }     // Catch:{ JSONException -> 0x029c }
        L_0x0062:
            r0 = 0
            goto L_0x0067
        L_0x0064:
            r0 = 2
            goto L_0x0067
        L_0x0066:
            r0 = 1
        L_0x0067:
            r10.a = r0     // Catch:{ JSONException -> 0x029c }
            org.json.JSONObject r0 = r10.m     // Catch:{ JSONException -> 0x029c }
            java.lang.String r4 = "shouldAutoOpenLandingPage"
            boolean r0 = r0.optBoolean(r4, r2)     // Catch:{ JSONException -> 0x029c }
            r10.j = r0     // Catch:{ JSONException -> 0x029c }
            org.json.JSONObject r0 = r10.m     // Catch:{ JSONException -> 0x029c }
            java.lang.String r4 = "disableBackButton"
            boolean r0 = r0.optBoolean(r4, r3)     // Catch:{ JSONException -> 0x029c }
            r10.b = r0     // Catch:{ JSONException -> 0x029c }
            org.json.JSONObject r0 = r10.m     // Catch:{ JSONException -> 0x029c }
            java.lang.String r4 = "rootContainer"
            org.json.JSONObject r0 = r0.getJSONObject(r4)     // Catch:{ JSONException -> 0x029c }
            java.lang.String r4 = "CONTAINER"
            java.lang.String r5 = "/rootContainer"
            r6 = 0
            com.inmobi.ads.ak r0 = r10.a(r0, r4, r5, r6)     // Catch:{ JSONException -> 0x029c }
            com.inmobi.ads.am r0 = (com.inmobi.ads.am) r0     // Catch:{ JSONException -> 0x029c }
            r10.d = r0     // Catch:{ JSONException -> 0x029c }
            org.json.JSONObject r0 = r10.m     // Catch:{ JSONException -> 0x018d }
            java.lang.String r4 = "passThroughJson"
            boolean r0 = r0.isNull(r4)     // Catch:{ JSONException -> 0x018d }
            if (r0 != 0) goto L_0x00a8
            com.inmobi.ads.ao$a r0 = r10.i     // Catch:{ JSONException -> 0x018d }
            org.json.JSONObject r4 = r10.m     // Catch:{ JSONException -> 0x018d }
            java.lang.String r5 = "passThroughJson"
            org.json.JSONObject r4 = r4.getJSONObject(r5)     // Catch:{ JSONException -> 0x018d }
            r0.a = r4     // Catch:{ JSONException -> 0x018d }
        L_0x00a8:
            org.json.JSONObject r0 = r10.m     // Catch:{ JSONException -> 0x018d }
            java.lang.String r4 = "adContent"
            boolean r0 = r0.isNull(r4)     // Catch:{ JSONException -> 0x018d }
            if (r0 != 0) goto L_0x0105
            org.json.JSONObject r0 = r10.m     // Catch:{ JSONException -> 0x018d }
            java.lang.String r4 = "adContent"
            org.json.JSONObject r0 = r0.getJSONObject(r4)     // Catch:{ JSONException -> 0x018d }
            if (r0 == 0) goto L_0x0105
            com.inmobi.ads.ao$a$a r4 = new com.inmobi.ads.ao$a$a     // Catch:{ JSONException -> 0x018d }
            com.inmobi.ads.ao$a r5 = r10.i     // Catch:{ JSONException -> 0x018d }
            r5.getClass()     // Catch:{ JSONException -> 0x018d }
            r4.<init>()     // Catch:{ JSONException -> 0x018d }
            java.lang.String r5 = "title"
            java.lang.String r5 = r0.optString(r5, r6)     // Catch:{ JSONException -> 0x018d }
            r4.a = r5     // Catch:{ JSONException -> 0x018d }
            java.lang.String r5 = "description"
            java.lang.String r5 = r0.optString(r5, r6)     // Catch:{ JSONException -> 0x018d }
            r4.b = r5     // Catch:{ JSONException -> 0x018d }
            java.lang.String r5 = "ctaText"
            java.lang.String r5 = r0.optString(r5, r6)     // Catch:{ JSONException -> 0x018d }
            r4.d = r5     // Catch:{ JSONException -> 0x018d }
            java.lang.String r5 = "iconUrl"
            java.lang.String r5 = r0.optString(r5, r6)     // Catch:{ JSONException -> 0x018d }
            r4.c = r5     // Catch:{ JSONException -> 0x018d }
            java.lang.String r5 = "rating"
            r7 = 0
            long r7 = r0.optLong(r5, r7)     // Catch:{ JSONException -> 0x018d }
            float r5 = (float) r7     // Catch:{ JSONException -> 0x018d }
            r4.e = r5     // Catch:{ JSONException -> 0x018d }
            java.lang.String r5 = "landingPageUrl"
            java.lang.String r5 = r0.optString(r5, r6)     // Catch:{ JSONException -> 0x018d }
            r4.f = r5     // Catch:{ JSONException -> 0x018d }
            java.lang.String r5 = "isApp"
            boolean r0 = r0.optBoolean(r5)     // Catch:{ JSONException -> 0x018d }
            r4.g = r0     // Catch:{ JSONException -> 0x018d }
            com.inmobi.ads.ao$a r0 = r10.i     // Catch:{ JSONException -> 0x018d }
            r0.b = r4     // Catch:{ JSONException -> 0x018d }
        L_0x0105:
            com.inmobi.ads.ak r0 = new com.inmobi.ads.ak     // Catch:{ JSONException -> 0x018d }
            r0.<init>()     // Catch:{ JSONException -> 0x018d }
            org.json.JSONObject r4 = r10.m     // Catch:{ JSONException -> 0x018d }
            java.lang.String r5 = "onClick"
            boolean r4 = r4.isNull(r5)     // Catch:{ JSONException -> 0x018d }
            if (r4 != 0) goto L_0x0175
            org.json.JSONObject r4 = r10.m     // Catch:{ JSONException -> 0x018d }
            java.lang.String r5 = "onClick"
            org.json.JSONObject r4 = r4.getJSONObject(r5)     // Catch:{ JSONException -> 0x018d }
            java.lang.String r5 = ""
            java.lang.String r6 = ""
            java.lang.String r7 = "itemUrl"
            boolean r7 = r4.isNull(r7)     // Catch:{ JSONException -> 0x0158 }
            if (r7 != 0) goto L_0x0130
            java.lang.String r5 = "itemUrl"
            java.lang.String r5 = r4.getString(r5)     // Catch:{ JSONException -> 0x0158 }
            r7 = 1
            goto L_0x0131
        L_0x0130:
            r7 = 0
        L_0x0131:
            java.lang.String r8 = "action"
            boolean r8 = r4.isNull(r8)     // Catch:{ JSONException -> 0x0158 }
            if (r8 != 0) goto L_0x0140
            java.lang.String r6 = "action"
            java.lang.String r6 = r4.getString(r6)     // Catch:{ JSONException -> 0x0158 }
            r7 = 1
        L_0x0140:
            r0.a(r5)     // Catch:{ JSONException -> 0x0158 }
            java.lang.String r5 = "fallbackUrl"
            java.lang.String r5 = r4.optString(r5)     // Catch:{ JSONException -> 0x0158 }
            r0.b(r5)     // Catch:{ JSONException -> 0x0158 }
            r0.j = r6     // Catch:{ JSONException -> 0x0158 }
            r0.h = r7     // Catch:{ JSONException -> 0x0158 }
            java.lang.String r5 = "appBundleId"
            java.lang.String r5 = r4.optString(r5)     // Catch:{ JSONException -> 0x0158 }
            r0.w = r5     // Catch:{ JSONException -> 0x0158 }
        L_0x0158:
            java.lang.String r5 = "openMode"
            boolean r5 = r4.isNull(r5)     // Catch:{ JSONException -> 0x018d }
            if (r5 != 0) goto L_0x0175
            java.lang.String r5 = "openMode"
            java.lang.String r5 = r4.getString(r5)     // Catch:{ JSONException -> 0x018d }
            int r5 = d(r5)     // Catch:{ JSONException -> 0x018d }
            r0.i = r5     // Catch:{ JSONException -> 0x018d }
            java.lang.String r5 = "fallbackUrl"
            java.lang.String r4 = r4.optString(r5)     // Catch:{ JSONException -> 0x018d }
            r0.b(r4)     // Catch:{ JSONException -> 0x018d }
        L_0x0175:
            org.json.JSONObject r4 = r10.m     // Catch:{ JSONException -> 0x018d }
            java.lang.String r5 = "trackers"
            boolean r4 = r4.isNull(r5)     // Catch:{ JSONException -> 0x018d }
            if (r4 != 0) goto L_0x0188
            org.json.JSONObject r4 = r10.m     // Catch:{ JSONException -> 0x018d }
            java.util.List r4 = b(r4)     // Catch:{ JSONException -> 0x018d }
            r0.a(r4)     // Catch:{ JSONException -> 0x018d }
        L_0x0188:
            com.inmobi.ads.ao$a r4 = r10.i     // Catch:{ JSONException -> 0x018d }
            r4.c = r0     // Catch:{ JSONException -> 0x018d }
            goto L_0x019a
        L_0x018d:
            r0 = move-exception
            com.inmobi.commons.core.a.a r4 = com.inmobi.commons.core.a.a.a()     // Catch:{ JSONException -> 0x029c }
            com.inmobi.commons.core.e.a r5 = new com.inmobi.commons.core.e.a     // Catch:{ JSONException -> 0x029c }
            r5.<init>(r0)     // Catch:{ JSONException -> 0x029c }
            r4.a(r5)     // Catch:{ JSONException -> 0x029c }
        L_0x019a:
            r10.c = r3     // Catch:{ JSONException -> 0x029c }
            org.json.JSONObject r0 = r10.m     // Catch:{ JSONException -> 0x029c }
            java.lang.String r4 = "rewards"
            boolean r0 = r0.has(r4)     // Catch:{ JSONException -> 0x029c }
            if (r0 == 0) goto L_0x01ad
            java.util.HashMap r0 = new java.util.HashMap     // Catch:{ JSONException -> 0x029c }
            r0.<init>()     // Catch:{ JSONException -> 0x029c }
            r10.g = r0     // Catch:{ JSONException -> 0x029c }
        L_0x01ad:
            org.json.JSONObject r0 = r10.m     // Catch:{ JSONException -> 0x029c }
            java.lang.String r4 = "rewards"
            boolean r0 = r0.isNull(r4)     // Catch:{ JSONException -> 0x029c }
            if (r0 != 0) goto L_0x01db
            org.json.JSONObject r0 = r10.m     // Catch:{ JSONException -> 0x029c }
            java.lang.String r4 = "rewards"
            org.json.JSONObject r0 = r0.getJSONObject(r4)     // Catch:{ JSONException -> 0x029c }
            if (r0 == 0) goto L_0x01db
            java.util.Iterator r4 = r0.keys()     // Catch:{ JSONException -> 0x029c }
        L_0x01c5:
            boolean r5 = r4.hasNext()     // Catch:{ JSONException -> 0x029c }
            if (r5 == 0) goto L_0x01db
            java.lang.Object r5 = r4.next()     // Catch:{ JSONException -> 0x029c }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ JSONException -> 0x029c }
            java.lang.String r6 = r0.getString(r5)     // Catch:{ JSONException -> 0x029c }
            java.util.Map<java.lang.String, java.lang.String> r7 = r10.g     // Catch:{ JSONException -> 0x029c }
            r7.put(r5, r6)     // Catch:{ JSONException -> 0x029c }
            goto L_0x01c5
        L_0x01db:
            r10.d()     // Catch:{ JSONException -> 0x029c }
            r10.e()     // Catch:{ JSONException -> 0x029c }
            java.util.Map<java.lang.String, java.lang.String> r0 = r10.p     // Catch:{ JSONException -> 0x029c }
            java.util.Set r0 = r0.entrySet()     // Catch:{ JSONException -> 0x029c }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ JSONException -> 0x029c }
        L_0x01eb:
            boolean r4 = r0.hasNext()     // Catch:{ JSONException -> 0x029c }
            if (r4 == 0) goto L_0x027f
            java.lang.Object r4 = r0.next()     // Catch:{ JSONException -> 0x029c }
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4     // Catch:{ JSONException -> 0x029c }
            java.lang.Object r5 = r4.getValue()     // Catch:{ JSONException -> 0x029c }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ JSONException -> 0x029c }
            java.util.Map<java.lang.String, com.inmobi.ads.ak> r6 = r10.o     // Catch:{ JSONException -> 0x029c }
            java.lang.Object r4 = r4.getKey()     // Catch:{ JSONException -> 0x029c }
            java.lang.Object r4 = r6.get(r4)     // Catch:{ JSONException -> 0x029c }
            com.inmobi.ads.ak r4 = (com.inmobi.ads.ak) r4     // Catch:{ JSONException -> 0x029c }
            int r6 = r4.n     // Catch:{ JSONException -> 0x029c }
            r7 = 4
            if (r7 != r6) goto L_0x01eb
            java.util.Map<java.lang.String, com.inmobi.ads.ak> r6 = r10.o     // Catch:{ JSONException -> 0x029c }
            java.lang.Object r5 = r6.get(r5)     // Catch:{ JSONException -> 0x029c }
            com.inmobi.ads.ak r5 = (com.inmobi.ads.ak) r5     // Catch:{ JSONException -> 0x029c }
            java.lang.String r6 = "VIDEO"
            java.lang.String r8 = r5.b     // Catch:{ JSONException -> 0x029c }
            boolean r6 = r6.equals(r8)     // Catch:{ JSONException -> 0x029c }
            if (r6 == 0) goto L_0x01eb
            r6 = r5
            com.inmobi.ads.be r6 = (com.inmobi.ads.be) r6     // Catch:{ JSONException -> 0x029c }
            com.inmobi.ads.by r6 = r6.b()     // Catch:{ JSONException -> 0x029c }
            com.inmobi.ads.bx r6 = (com.inmobi.ads.bx) r6     // Catch:{ JSONException -> 0x029c }
            java.lang.String r6 = r6.b     // Catch:{ JSONException -> 0x029c }
            java.lang.String r8 = ":"
            java.lang.String[] r6 = r6.split(r8)     // Catch:{ JSONException -> 0x029c }
            r8 = r6[r2]     // Catch:{ ArrayIndexOutOfBoundsException -> 0x0241 }
            int r8 = java.lang.Integer.parseInt(r8)     // Catch:{ ArrayIndexOutOfBoundsException -> 0x0241 }
            int r8 = r8 * 60
            r6 = r6[r1]     // Catch:{ ArrayIndexOutOfBoundsException -> 0x0241 }
            int r6 = java.lang.Integer.parseInt(r6)     // Catch:{ ArrayIndexOutOfBoundsException -> 0x0241 }
            int r6 = r6 + r8
            goto L_0x024f
        L_0x0241:
            r6 = move-exception
            com.inmobi.commons.core.a.a r8 = com.inmobi.commons.core.a.a.a()     // Catch:{ JSONException -> 0x029c }
            com.inmobi.commons.core.e.a r9 = new com.inmobi.commons.core.e.a     // Catch:{ JSONException -> 0x029c }
            r9.<init>(r6)     // Catch:{ JSONException -> 0x029c }
            r8.a(r9)     // Catch:{ JSONException -> 0x029c }
            r6 = 0
        L_0x024f:
            if (r6 != 0) goto L_0x0256
            int r6 = r6 / 4
            r4.o = r6     // Catch:{ JSONException -> 0x029c }
            goto L_0x0276
        L_0x0256:
            int r8 = r4.o     // Catch:{ JSONException -> 0x029c }
            r9 = 50
            if (r8 == r9) goto L_0x0272
            r9 = 75
            if (r8 == r9) goto L_0x026c
            r7 = 100
            if (r8 == r7) goto L_0x0269
            int r6 = r6 / 4
            r4.o = r6     // Catch:{ JSONException -> 0x029c }
            goto L_0x0276
        L_0x0269:
            r4.o = r6     // Catch:{ JSONException -> 0x029c }
            goto L_0x0276
        L_0x026c:
            int r6 = r6 * 3
            int r6 = r6 / r7
            r4.o = r6     // Catch:{ JSONException -> 0x029c }
            goto L_0x0276
        L_0x0272:
            int r6 = r6 / 2
            r4.o = r6     // Catch:{ JSONException -> 0x029c }
        L_0x0276:
            com.inmobi.ads.be r5 = (com.inmobi.ads.be) r5     // Catch:{ JSONException -> 0x029c }
            java.util.List<com.inmobi.ads.ak> r5 = r5.z     // Catch:{ JSONException -> 0x029c }
            r5.add(r4)     // Catch:{ JSONException -> 0x029c }
            goto L_0x01eb
        L_0x027f:
            org.json.JSONObject r0 = r10.m     // Catch:{ JSONException -> 0x029c }
            java.lang.String r1 = "pages"
            boolean r0 = r0.isNull(r1)     // Catch:{ JSONException -> 0x029c }
            if (r0 == 0) goto L_0x0291
            org.json.JSONArray r0 = new org.json.JSONArray     // Catch:{ JSONException -> 0x029c }
            r0.<init>()     // Catch:{ JSONException -> 0x029c }
            r10.e = r0     // Catch:{ JSONException -> 0x029c }
            return
        L_0x0291:
            org.json.JSONObject r0 = r10.m     // Catch:{ JSONException -> 0x029c }
            java.lang.String r1 = "pages"
            org.json.JSONArray r0 = r0.getJSONArray(r1)     // Catch:{ JSONException -> 0x029c }
            r10.e = r0     // Catch:{ JSONException -> 0x029c }
            return
        L_0x029c:
            r0 = move-exception
            com.inmobi.commons.core.a.a r1 = com.inmobi.commons.core.a.a.a()
            com.inmobi.commons.core.e.a r2 = new com.inmobi.commons.core.e.a
            r2.<init>(r0)
            r1.a(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.ao.f():void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0041 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0042 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static int a(java.lang.String r4) {
        /*
            java.util.Locale r0 = java.util.Locale.US
            java.lang.String r4 = r4.toLowerCase(r0)
            java.lang.String r4 = r4.trim()
            int r0 = r4.hashCode()
            r1 = -1412832500(0xffffffffabc9e30c, float:-1.4344927E-12)
            r2 = 2
            r3 = 1
            if (r0 == r1) goto L_0x0031
            if (r0 == 0) goto L_0x0027
            r1 = 112202875(0x6b0147b, float:6.6233935E-35)
            if (r0 == r1) goto L_0x001d
            goto L_0x003b
        L_0x001d:
            java.lang.String r0 = "video"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x003b
            r4 = 2
            goto L_0x003c
        L_0x0027:
            java.lang.String r0 = ""
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x003b
            r4 = 1
            goto L_0x003c
        L_0x0031:
            java.lang.String r0 = "companion"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x003b
            r4 = 3
            goto L_0x003c
        L_0x003b:
            r4 = -1
        L_0x003c:
            switch(r4) {
                case 1: goto L_0x0042;
                case 2: goto L_0x0042;
                case 3: goto L_0x0041;
                default: goto L_0x003f;
            }
        L_0x003f:
            r4 = 0
            return r4
        L_0x0041:
            return r2
        L_0x0042:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.ao.a(java.lang.String):int");
    }

    /* access modifiers changed from: 0000 */
    public final boolean c() {
        am amVar;
        am amVar2 = this.d;
        if (amVar2 == null) {
            return false;
        }
        Iterator it = amVar2.iterator();
        while (true) {
            if (!it.hasNext()) {
                amVar = null;
                break;
            }
            ak akVar = (ak) it.next();
            if (akVar.d.equalsIgnoreCase("card_scrollable")) {
                amVar = (am) akVar;
                break;
            }
        }
        if (amVar == null) {
            return g();
        }
        if (b() <= 0) {
            return false;
        }
        return g();
    }

    private boolean g() {
        List<ak> c2 = c(ShareConstants.VIDEO_URL);
        if (c2 == null || c2.size() <= 0) {
            return true;
        }
        for (ak akVar : c2) {
            akVar.a.length();
            be beVar = (be) akVar;
            if (beVar.b() == null) {
                return false;
            }
            List c3 = beVar.b().c();
            if (c3 == null || c3.size() == 0) {
                return false;
            }
            String b2 = beVar.b().b();
            if (b2 != null) {
                if (b2.length() == 0) {
                }
            }
            HashMap hashMap = new HashMap();
            hashMap.put("[ERRORCODE]", "403");
            beVar.a(TrackerEventType.TRACKER_EVENT_TYPE_ERROR, (Map<String, String>) hashMap);
            return false;
        }
        return true;
    }

    /* JADX WARNING: type inference failed for: r21v0 */
    /* JADX WARNING: type inference failed for: r15v1, types: [com.inmobi.ads.ak, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r15v2 */
    /* JADX WARNING: type inference failed for: r21v1 */
    /* JADX WARNING: type inference failed for: r15v3 */
    /* JADX WARNING: type inference failed for: r15v4, types: [com.inmobi.ads.ak, com.inmobi.ads.am] */
    /* JADX WARNING: type inference failed for: r8v39, types: [com.inmobi.ads.am] */
    /* JADX WARNING: type inference failed for: r8v40, types: [com.inmobi.ads.am] */
    /* JADX WARNING: type inference failed for: r15v7 */
    /* JADX WARNING: type inference failed for: r2v17, types: [com.inmobi.ads.az, com.inmobi.ads.ak] */
    /* JADX WARNING: type inference failed for: r15v8 */
    /* JADX WARNING: type inference failed for: r1v32, types: [com.inmobi.ads.ak, com.inmobi.ads.ar] */
    /* JADX WARNING: type inference failed for: r15v9 */
    /* JADX WARNING: type inference failed for: r1v37, types: [com.inmobi.ads.ak, com.inmobi.ads.bb] */
    /* JADX WARNING: type inference failed for: r15v10 */
    /* JADX WARNING: type inference failed for: r15v14, types: [com.inmobi.ads.ak] */
    /* JADX WARNING: type inference failed for: r21v2 */
    /* JADX WARNING: type inference failed for: r8v46, types: [com.inmobi.ads.aq] */
    /* JADX WARNING: type inference failed for: r8v47, types: [com.inmobi.ads.as] */
    /* JADX WARNING: type inference failed for: r8v48, types: [com.inmobi.ads.aq] */
    /* JADX WARNING: type inference failed for: r15v18 */
    /* JADX WARNING: type inference failed for: r8v49, types: [com.inmobi.ads.as] */
    /* JADX WARNING: type inference failed for: r15v20 */
    /* JADX WARNING: type inference failed for: r1v61, types: [com.inmobi.ads.bf] */
    /* JADX WARNING: type inference failed for: r21v3 */
    /* JADX WARNING: type inference failed for: r15v22 */
    /* JADX WARNING: type inference failed for: r15v24 */
    /* JADX WARNING: type inference failed for: r15v25 */
    /* JADX WARNING: type inference failed for: r0v71, types: [com.inmobi.ads.ak] */
    /* JADX WARNING: type inference failed for: r21v4 */
    /* JADX WARNING: type inference failed for: r8v66, types: [com.inmobi.ads.an] */
    /* JADX WARNING: type inference failed for: r0v73 */
    /* JADX WARNING: type inference failed for: r8v67, types: [com.inmobi.ads.an] */
    /* JADX WARNING: type inference failed for: r0v75 */
    /* JADX WARNING: type inference failed for: r21v5 */
    /* JADX WARNING: type inference failed for: r15v29 */
    /* JADX WARNING: type inference failed for: r8v68, types: [com.inmobi.ads.am] */
    /* JADX WARNING: type inference failed for: r15v30 */
    /* JADX WARNING: type inference failed for: r8v69, types: [com.inmobi.ads.aq] */
    /* JADX WARNING: type inference failed for: r8v70, types: [com.inmobi.ads.as] */
    /* JADX WARNING: Code restructure failed: missing block: B:100:?, code lost:
        r3 = r15.getJSONObject("assetOnclick").optString("fallbackUrl");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x01b2, code lost:
        if (r18 == null) goto L_0x01df;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x01b8, code lost:
        if (r18.size() != 0) goto L_0x01bb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x01bd, code lost:
        r5 = r8;
        r8 = r8;
        r6 = r11;
        r11 = r1;
        r1 = r12;
        r50 = r6;
        r31 = r13;
        r32 = r24;
        r6 = r49;
        r14 = r2;
        r2 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:?, code lost:
        r8 = new com.inmobi.ads.an
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x01d5, code lost:
        r15 = r2;
        r0 = r8;
        r4 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x01d9, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x01da, code lost:
        r44 = r50;
        r50 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x01df, code lost:
        r5 = r8;
        r50 = r11;
        r4 = r12;
        r31 = r13;
        r32 = r24;
        r6 = r49;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:?, code lost:
        r8 = new com.inmobi.ads.an
        r0 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x01f8, code lost:
        r0.g = r6;
        a((com.inmobi.ads.ak) r0, r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x01fd, code lost:
        if (r3 == null) goto L_0x0202;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x01ff, code lost:
        r0.b(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x0202, code lost:
        r44 = r50;
        r21 = r0;
        r50 = r4;
        r43 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:119:0x020c, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x020d, code lost:
        r44 = r50;
        r50 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x0211, code lost:
        r43 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x0215, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:123:0x0216, code lost:
        r31 = r13;
        r32 = r24;
        r43 = r8;
        r44 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:0x0220, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x0221, code lost:
        r31 = r20;
        r32 = r24;
        r44 = r11;
        r50 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:126:0x0229, code lost:
        r43 = r22;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:0x022d, code lost:
        r31 = r20;
        r32 = r24;
        r44 = r11;
        r50 = r12;
        r15 = 0;
        r43 = r22;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:128:0x023b, code lost:
        r14 = r11;
        r1 = r12;
        r31 = r20;
        r32 = r24;
        r12 = r6;
        r6 = r49;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:133:?, code lost:
        r7.h.get(com.facebook.share.internal.ShareConstants.VIDEO_URL);
        r42 = r7.s(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:134:0x025b, code lost:
        r19 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:136:?, code lost:
        r33 = new com.inmobi.ads.be.a(r2.x, r2.y, r3.x, r3.y, r4.x, r4.y, r5.x, r5.y, r42);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:137:0x027a, code lost:
        if (r8 == null) goto L_0x0298;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:140:0x0280, code lost:
        if (a(r8, (com.inmobi.ads.al) r33) != false) goto L_0x0298;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:0x0282, code lost:
        com.inmobi.commons.core.e.b.a();
        com.inmobi.commons.core.e.b.a("ads", "InvalidVideoGeometry", new java.util.HashMap());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:142:0x0292, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:143:0x0293, code lost:
        r50 = r1;
        r44 = r19;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:146:0x029a, code lost:
        if (r7.q != null) goto L_0x02a1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:148:?, code lost:
        r0 = r7.a(r15, r0, r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:150:?, code lost:
        r0 = r7.q;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:153:0x02a9, code lost:
        if (com.inmobi.ads.AdContainer.RenderingProperties.PlacementType.PLACEMENT_TYPE_INLINE != r7.s) goto L_0x033e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:154:0x02ab, code lost:
        if (r12 == null) goto L_0x0305;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:157:0x02bb, code lost:
        if (((java.lang.Boolean) r12.v.get("didRequestFullScreen")).booleanValue() != false) goto L_0x02cf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:159:0x02bf, code lost:
        if (r7.t == false) goto L_0x02c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:161:0x02c2, code lost:
        r2 = false;
        r4 = 0;
        r6 = Integer.MAX_VALUE;
        r13 = true;
        r14 = true;
        r16 = true;
        r17 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:162:0x02cf, code lost:
        r2 = r15.optBoolean("loop", false);
        r3 = r15.optBoolean("showProgress", true);
        r9 = r15.optBoolean("soundOn", true);
        r13 = r15.optBoolean("showMute", true);
        r16 = r3;
        r4 = (int) r15.optDouble("pauseAfter", 0.0d);
        r17 = r15.optBoolean("autoPlay", true);
        r6 = ((com.inmobi.ads.be) r12).E;
        r14 = r13;
        r13 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:163:0x0305, code lost:
        r2 = r15.optBoolean("loop", true);
        r3 = r15.optBoolean("showProgress", false);
        r8 = r15.optBoolean("soundOn", false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:164:0x0335, code lost:
        r4 = (int) r15.optDouble("pauseAfter", 0.0d);
        r16 = r3;
        r14 = r15.optBoolean("showMute", false);
        r17 = r15.optBoolean("autoPlay", true);
        r6 = r15.optInt("completeAfter", Integer.MAX_VALUE);
        r13 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:167:?, code lost:
        r2 = r15.optBoolean("loop", false);
        r3 = r15.optBoolean("showProgress", true);
        r6 = r15.optBoolean("soundOn", true);
        r9 = r15.optBoolean("showMute", true);
        r4 = (int) r15.optDouble("pauseAfter", 0.0d);
        r16 = r3;
        r17 = r15.optBoolean("autoPlay", true);
        r14 = r9;
        r13 = r6;
        r6 = r15.optInt("completeAfter", Integer.MAX_VALUE);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:168:0x0378, code lost:
        r3 = new java.util.HashMap();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:169:0x0383, code lost:
        if (r15.isNull("videoViewabilityConfig") != false) goto L_0x03a7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:172:?, code lost:
        r5 = r15.getJSONObject("videoViewabilityConfig");
        r8 = r5.keys();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:174:0x0393, code lost:
        if (r8.hasNext() == false) goto L_0x03a7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:175:0x0395, code lost:
        r9 = (java.lang.String) r8.next();
        r20 = r8;
        r3.put(r9, r5.get(r9));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:176:0x03a4, code lost:
        r8 = r20;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:179:0x03af, code lost:
        r8 = r8;
        r20 = r7.r.i.m;
        r9 = r1;
        r23 = r1;
        r1 = r12;
        r12 = r0;
        r43 = r22;
        r44 = r19;
        r0 = Integer.MAX_VALUE;
        r7 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:181:?, code lost:
        r8 = new com.inmobi.ads.be(r9, r10, r33, r12, r13, r14, r2, r16, r17, r18, r47, r20);
        r8.G = new java.util.HashMap(r3);
        r2 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:182:0x03d2, code lost:
        if (r6 > 0) goto L_0x03d5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:184:0x03d5, code lost:
        r0 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:185:0x03d6, code lost:
        r2.E = r0;
        r8.g = r49;
        r8.y = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:186:0x03de, code lost:
        if (r1 == null) goto L_0x03e2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:187:0x03e0, code lost:
        r1.y = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:188:0x03e2, code lost:
        if (r4 == 0) goto L_0x03e9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:189:0x03e4, code lost:
        r8.F = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:190:0x03e9, code lost:
        r15 = r8;
        r50 = r23;
        r7 = r46;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:191:0x03f0, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:192:0x03f1, code lost:
        r44 = r19;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:193:0x03f4, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:194:0x03f5, code lost:
        r44 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:195:0x03f7, code lost:
        r43 = r22;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:196:0x03fa, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:197:0x03fb, code lost:
        r43 = r22;
        r44 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:198:0x03ff, code lost:
        r50 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:199:0x0401, code lost:
        r7 = r46;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:200:0x0405, code lost:
        r44 = r11;
        r23 = r12;
        r7 = r15;
        r31 = r20;
        r43 = r22;
        r32 = r24;
        r11 = r49;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:201:0x0414, code lost:
        if (r0 != null) goto L_0x0417;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:202:0x0416, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:204:?, code lost:
        r12 = com.inmobi.ads.bf.c(h(r47));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:205:0x0425, code lost:
        if (com.mopub.common.MoPubBrowser.DESTINATION_URL_KEY.equals(r12) == false) goto L_0x0432;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:208:0x042b, code lost:
        if (android.webkit.URLUtil.isValidUrl(r0) != false) goto L_0x0432;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:209:0x042d, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:210:0x042e, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:211:0x042f, code lost:
        r50 = r23;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:212:0x0432, code lost:
        r15 = r23;
        r14 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:214:?, code lost:
        r1 = new com.inmobi.ads.bf
        r1.z = r12;
        r1.g = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:215:0x0457, code lost:
        if (r7.optBoolean(com.myfitnesspal.shared.service.device.UserAgentProviderImpl.Params.PRELOAD, false) == false) goto L_0x0464;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:216:0x0459, code lost:
        r1.A = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:217:0x045b, code lost:
        r7 = r46;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:219:?, code lost:
        r7.k = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:220:0x045f, code lost:
        r50 = r15;
        r15 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:221:0x0464, code lost:
        r7 = r46;
        r21 = r1;
        r50 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:222:0x046c, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:223:0x046d, code lost:
        r7 = r46;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:224:0x0471, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:225:0x0472, code lost:
        r7 = r46;
        r50 = r23;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:226:0x0478, code lost:
        r44 = r11;
        r13 = r15;
        r31 = r20;
        r43 = r22;
        r32 = r24;
        r14 = r49;
        r15 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:228:?, code lost:
        r0 = a(r2, r3, r4, r5, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:229:0x0490, code lost:
        if (p(r47) == false) goto L_0x04c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:233:0x049e, code lost:
        if (r13.getJSONObject("assetOnclick").isNull("openMode") != false) goto L_0x04b1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:234:0x04a0, code lost:
        r1 = d(r13.getJSONObject("assetOnclick").getString("openMode"));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:235:0x04b1, code lost:
        r1 = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:236:0x04b2, code lost:
        r2 = r13.getJSONObject("assetOnclick").optString("fallbackUrl");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:237:0x04bf, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:238:0x04c2, code lost:
        r2 = null;
        r1 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:239:0x04c5, code lost:
        if (r18 == null) goto L_0x050c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:242:0x04cb, code lost:
        if (r18.size() != 0) goto L_0x04ce;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:248:0x04d6, code lost:
        if (com.facebook.share.internal.ShareConstants.IMAGE_URL.equals(r48) == false) goto L_0x04f4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:250:0x04de, code lost:
        r8 = r8;
        r4 = r13;
        r5 = r14;
        r14 = r1;
        r1 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:252:?, code lost:
        r8 = new com.inmobi.ads.as
     */
    /* JADX WARNING: Code restructure failed: missing block: B:253:0x04ec, code lost:
        r15 = r8;
        r3 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:254:0x04ef, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:255:0x04f0, code lost:
        r50 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:256:0x04f4, code lost:
        r4 = r13;
        r5 = r14;
        r3 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:258:?, code lost:
        r8 = new com.inmobi.ads.aq
        r15 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:259:0x050c, code lost:
        r4 = r13;
        r5 = r14;
        r3 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:260:0x0517, code lost:
        if (com.facebook.share.internal.ShareConstants.IMAGE_URL.equals(r48) == false) goto L_0x0529;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:261:0x0519, code lost:
        r8 = new com.inmobi.ads.as
        r8 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:262:0x0529, code lost:
        r8 = new com.inmobi.ads.aq
        r8 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:263:0x0538, code lost:
        r15.g = r5;
        a((com.inmobi.ads.ak) r15, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:264:0x053d, code lost:
        if (r2 == null) goto L_0x0546;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:265:0x053f, code lost:
        r15.b(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:266:0x0542, code lost:
        r50 = r3;
        r15 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:267:0x0546, code lost:
        r50 = r3;
        r21 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:268:0x054c, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:269:0x054d, code lost:
        r50 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:270:0x0551, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:271:0x0552, code lost:
        r6 = r48;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:272:0x0554, code lost:
        r50 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:273:0x0558, code lost:
        r44 = r11;
        r6 = r14;
        r31 = r20;
        r43 = r22;
        r32 = r24;
        r11 = 0;
        r14 = r49;
        r8 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:275:?, code lost:
        r0 = a(r2, r3, r4, r5, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:276:0x0573, code lost:
        if (r15.has("startOffset") == false) goto L_0x0580;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:277:0x0575, code lost:
        r1 = r7.q(r15.getJSONObject("startOffset"));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:278:0x0580, code lost:
        r1 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:280:0x0588, code lost:
        if (r15.has("timerDuration") == false) goto L_0x0595;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:281:0x058a, code lost:
        r2 = r7.q(r15.getJSONObject("timerDuration"));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:282:0x0595, code lost:
        r2 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:283:0x0597, code lost:
        r3 = r15.optBoolean("displayTimer", true);
        r1 = new com.inmobi.ads.bb
        r1.z = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:284:0x05af, code lost:
        if (r15.has("assetOnFinish") == false) goto L_0x05fc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:285:0x05b1, code lost:
        r0 = (org.json.JSONObject) r15.get("assetOnFinish");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:286:0x05bf, code lost:
        if (r0.has("action") == false) goto L_0x05fc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:287:0x05c1, code lost:
        r0 = r0.getString("action").toUpperCase(java.util.Locale.US).trim();
        r2 = r0.hashCode();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:288:0x05d8, code lost:
        if (r2 == 2142494) goto L_0x05ea;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:290:0x05dd, code lost:
        if (r2 == 2402104) goto L_0x05e0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:293:0x05e6, code lost:
        if (r0.equals("NONE") == false) goto L_0x05f4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:294:0x05e8, code lost:
        r0 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:296:0x05f0, code lost:
        if (r0.equals("EXIT") == false) goto L_0x05f4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:297:0x05f2, code lost:
        r0 = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:298:0x05f4, code lost:
        r0 = 65535;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:300:0x05f6, code lost:
        if (r0 == 2) goto L_0x05f9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:302:0x05f9, code lost:
        r11 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:303:0x05fa, code lost:
        r1.k = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:304:0x05fc, code lost:
        r1.g = r14;
        r15 = r1;
        r50 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:305:0x0603, code lost:
        r44 = r11;
        r8 = r14;
        r31 = r20;
        r43 = r22;
        r32 = r24;
        r14 = r49;
        r1 = new com.inmobi.ads.ar
        r1.g = r14;
        r15 = r1;
        r50 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:306:0x0625, code lost:
        r44 = r11;
        r8 = r14;
        r31 = r20;
        r43 = r22;
        r32 = r24;
        r14 = r49;
        r2 = new com.inmobi.ads.az
        r2.g = r14;
        r15 = r2;
        r50 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:307:0x0643, code lost:
        r44 = r11;
        r6 = r14;
        r31 = r20;
        r43 = r22;
        r32 = r24;
        r14 = r49;
        r0 = a(r2, r3, r4, r5, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:308:0x0657, code lost:
        if (r8 == null) goto L_0x066e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:310:0x065d, code lost:
        if (a(r8, r0) != false) goto L_0x066e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:311:0x065f, code lost:
        com.inmobi.commons.core.e.b.a();
        com.inmobi.commons.core.e.b.a("ads", "InvalidContainerGeometry", new java.util.HashMap());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:313:0x0672, code lost:
        if (p(r47) == false) goto L_0x06a1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:315:0x0680, code lost:
        if (r15.getJSONObject("assetOnclick").isNull("openMode") != false) goto L_0x0693;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:316:0x0682, code lost:
        r1 = d(r15.getJSONObject("assetOnclick").getString("openMode"));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:317:0x0693, code lost:
        r1 = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:318:0x0694, code lost:
        r2 = r15.getJSONObject("assetOnclick").optString("fallbackUrl");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:319:0x06a1, code lost:
        r2 = null;
        r1 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:321:0x06aa, code lost:
        if (r9.has("transitionEffect") == false) goto L_0x06e1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:322:0x06ac, code lost:
        r3 = r9.getString("transitionEffect").trim();
        r4 = r3.hashCode();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:323:0x06bd, code lost:
        if (r4 == 3151468) goto L_0x06cf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:325:0x06c2, code lost:
        if (r4 == 106426293) goto L_0x06c5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:328:0x06cb, code lost:
        if (r3.equals("paged") == false) goto L_0x06d9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:329:0x06cd, code lost:
        r3 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:331:0x06d5, code lost:
        if (r3.equals("free") == false) goto L_0x06d9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:332:0x06d7, code lost:
        r3 = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:333:0x06d9, code lost:
        r3 = 65535;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:335:0x06db, code lost:
        if (r3 == 2) goto L_0x06df;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:336:0x06dd, code lost:
        r3 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:337:0x06df, code lost:
        r3 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:338:0x06e1, code lost:
        r3 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:339:0x06e3, code lost:
        if (r18 == null) goto L_0x0705;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:341:0x06e9, code lost:
        if (r18.size() != 0) goto L_0x06ec;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:344:0x06ee, code lost:
        r8 = r8;
        r6 = 0;
        r50 = r12;
        r13 = r1;
        r1 = r14;
        r4 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:346:?, code lost:
        r8 = new com.inmobi.ads.am
        r15 = r8;
        r5 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:347:0x0705, code lost:
        r50 = r12;
        r5 = r14;
        r4 = r15;
        r6 = 0;
        r8 = new com.inmobi.ads.am
        r8 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:348:0x0719, code lost:
        r15.g = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:349:0x071b, code lost:
        if (r2 == null) goto L_0x0720;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:350:0x071d, code lost:
        r15.b(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:351:0x0720, code lost:
        a((com.inmobi.ads.ak) r15, r4);
        r1 = r4.getJSONArray("assetValue");
        r2 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:353:0x072e, code lost:
        if (r2 >= r1.length()) goto L_0x084e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:354:0x0730, code lost:
        r3 = new java.lang.StringBuilder();
        r3.append(r5);
        r3.append(".assetValue[");
        r3.append(r2);
        r3.append("]");
        r3 = r3.toString();
        r4 = r1.getJSONObject(r2);
        r8 = f(r4).toLowerCase(java.util.Locale.US).trim();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:355:0x075f, code lost:
        switch(r8.hashCode()) {
            case -938102371: goto L_0x07c1;
            case -410956671: goto L_0x07b7;
            case 98832: goto L_0x07ad;
            case 102340: goto L_0x07a2;
            case 3226745: goto L_0x0798;
            case 3556653: goto L_0x078e;
            case 100313435: goto L_0x0784;
            case 110364485: goto L_0x0779;
            case 112202875: goto L_0x076f;
            case 1224424441: goto L_0x0764;
            default: goto L_0x0762;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:358:0x076a, code lost:
        if (r8.equals("webview") == false) goto L_0x07cb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:359:0x076c, code lost:
        r8 = 9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:361:0x0775, code lost:
        if (r8.equals("video") == false) goto L_0x07cb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:362:0x0777, code lost:
        r8 = 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:364:0x077f, code lost:
        if (r8.equals("timer") == false) goto L_0x07cb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:365:0x0781, code lost:
        r8 = 8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:367:0x078a, code lost:
        if (r8.equals("image") == false) goto L_0x07cb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:368:0x078c, code lost:
        r8 = 3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:370:0x0794, code lost:
        if (r8.equals("text") == false) goto L_0x07cb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:371:0x0796, code lost:
        r8 = 5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:373:0x079e, code lost:
        if (r8.equals(com.google.ads.mediation.inmobi.InMobiNetworkValues.ICON) == false) goto L_0x07cb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:374:0x07a0, code lost:
        r8 = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:376:0x07a8, code lost:
        if (r8.equals("gif") == false) goto L_0x07cb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:377:0x07aa, code lost:
        r8 = 10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:379:0x07b3, code lost:
        if (r8.equals(com.google.ads.mediation.inmobi.InMobiNetworkValues.CTA) == false) goto L_0x07cb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:380:0x07b5, code lost:
        r8 = 6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:382:0x07bd, code lost:
        if (r8.equals("container") == false) goto L_0x07cb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:383:0x07bf, code lost:
        r8 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:385:0x07c7, code lost:
        if (r8.equals(com.google.ads.mediation.inmobi.InMobiNetworkValues.RATING) == false) goto L_0x07cb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:386:0x07c9, code lost:
        r8 = 7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:387:0x07cb, code lost:
        r8 = 65535;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:388:0x07cc, code lost:
        switch(r8) {
            case 2: goto L_0x07ea;
            case 3: goto L_0x07e7;
            case 4: goto L_0x07e4;
            case 5: goto L_0x07e1;
            case 6: goto L_0x07de;
            case 7: goto L_0x07db;
            case 8: goto L_0x07d8;
            case 9: goto L_0x07d5;
            case 10: goto L_0x07d2;
            default: goto L_0x07cf;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:389:0x07cf, code lost:
        r8 = "CONTAINER";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:390:0x07d2, code lost:
        r8 = "GIF";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:391:0x07d5, code lost:
        r8 = "WEBVIEW";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:392:0x07d8, code lost:
        r8 = "TIMER";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:393:0x07db, code lost:
        r8 = "RATING";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:394:0x07de, code lost:
        r8 = "CTA";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:395:0x07e1, code lost:
        r8 = "TEXT";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:396:0x07e4, code lost:
        r8 = com.facebook.share.internal.ShareConstants.VIDEO_URL;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:397:0x07e7, code lost:
        r8 = com.facebook.share.internal.ShareConstants.IMAGE_URL;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:398:0x07ea, code lost:
        r8 = "ICON";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:399:0x07ec, code lost:
        r8 = r7.a(r4, r8, r3, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:400:0x07f0, code lost:
        if (r8 != null) goto L_0x07fd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:401:0x07f2, code lost:
        new java.lang.StringBuilder("Cannot build asset from JSON: ").append(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:402:0x07fd, code lost:
        r8.g = r3;
        r8.t = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:403:0x0805, code lost:
        if (r15.C >= 16) goto L_0x0828;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:405:0x080c, code lost:
        if (r15.C != r15.B.length) goto L_0x081e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:406:0x080e, code lost:
        r3 = new com.inmobi.ads.ak[(r15.B.length * 2)];
        java.lang.System.arraycopy(r15.B, r6, r3, r6, r15.C);
        r15.B = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:407:0x081e, code lost:
        r3 = r15.B;
        r4 = r15.C;
        r15.C = r4 + 1;
        r3[r4] = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:408:0x0828, code lost:
        r2 = r2 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:409:0x082c, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:410:0x082e, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:411:0x082f, code lost:
        r50 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:412:0x0832, code lost:
        r15 = r21;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:418:0x0850, code lost:
        r15.n = r44;
        r15.o = r31;
        r15.p = r32;
        r1 = r43;
        r15.q = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:419:0x0860, code lost:
        if (r1 == null) goto L_0x0870;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:422:0x0868, code lost:
        r2 = r50;
        r7.p.put(r2, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:423:0x0870, code lost:
        r2 = r50;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:428:0x0880, code lost:
        r7.o.put(r2, r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:429:0x0885, code lost:
        r1 = r48;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:430:0x088d, code lost:
        if (r7.h.containsKey(r1) != false) goto L_0x088f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:431:0x088f, code lost:
        ((java.util.List) r7.h.get(r1)).add(r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:432:0x089b, code lost:
        r0 = new java.util.ArrayList();
        r0.add(r15);
        r7.h.put(r1, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:434:0x084e, code lost:
        r15 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x0159, code lost:
        r1 = 65535;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x015a, code lost:
        switch(r1) {
            case 0: goto L_0x0643;
            case 1: goto L_0x0625;
            case 2: goto L_0x0603;
            case 3: goto L_0x0558;
            case 4: goto L_0x0478;
            case 5: goto L_0x0478;
            case 6: goto L_0x0405;
            case 7: goto L_0x023b;
            case 8: goto L_0x022d;
            case 9: goto L_0x0169;
            default: goto L_0x015d;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x015d, code lost:
        r44 = r11;
        r50 = r12;
        r31 = r20;
        r43 = r22;
        r32 = r24;
        r21 = r21;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x016d, code lost:
        if (p(r47) != false) goto L_0x0170;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x016f, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x0170, code lost:
        r13 = r20;
        r8 = r22;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:?, code lost:
        r1 = c(r2, r3, r4, r5, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x0187, code lost:
        if (r15.getJSONObject("assetOnclick").isNull("openMode") != false) goto L_0x01a5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:?, code lost:
        r2 = d(r15.getJSONObject("assetOnclick").getString("openMode"));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x019a, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x019b, code lost:
        r43 = r8;
        r44 = r11;
        r50 = r12;
        r31 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x01a5, code lost:
        r2 = 2;
     */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r15v18
  assigns: [?[OBJECT, ARRAY], com.inmobi.ads.aq, com.inmobi.ads.as]
  uses: [com.inmobi.ads.ak, ?[OBJECT, ARRAY], com.inmobi.ads.aq, com.inmobi.ads.as]
  mth insns count: 968
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
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0088  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x008a  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0091  */
    /* JADX WARNING: Removed duplicated region for block: B:418:0x0850  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00f1 A[Catch:{ JSONException -> 0x0835 }] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00f3 A[Catch:{ JSONException -> 0x0835 }] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00fd A[Catch:{ JSONException -> 0x0835 }] */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0107 A[Catch:{ JSONException -> 0x0835 }] */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0111 A[Catch:{ JSONException -> 0x0835 }] */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x011b A[Catch:{ JSONException -> 0x0835 }] */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0125 A[Catch:{ JSONException -> 0x0835 }] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x012f A[Catch:{ JSONException -> 0x0835 }] */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x0139 A[Catch:{ JSONException -> 0x0835 }] */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0144 A[Catch:{ JSONException -> 0x0835 }] */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x014f A[Catch:{ JSONException -> 0x0835 }] */
    /* JADX WARNING: Unknown variable types count: 18 */
    @android.annotation.TargetApi(15)
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.inmobi.ads.ak a(@android.support.annotation.NonNull org.json.JSONObject r47, java.lang.String r48, java.lang.String r49, @android.support.annotation.Nullable com.inmobi.ads.al r50) {
        /*
            r46 = this;
            r7 = r46
            r15 = r47
            r14 = r48
            r13 = r49
            r8 = r50
            java.lang.String r12 = d(r47)
            java.lang.String r10 = e(r47)
            org.json.JSONObject r9 = r46.i(r47)
            boolean r0 = a(r9, r14)
            r21 = 0
            if (r0 != 0) goto L_0x0029
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Asset style JSON: "
            r0.<init>(r1)
            r0.append(r9)
            return r21
        L_0x0029:
            android.graphics.Point r2 = r46.j(r47)
            android.graphics.Point r4 = r7.a(r15, r2)
            android.graphics.Point r3 = r46.k(r47)
            android.graphics.Point r5 = r7.b(r15, r3)
            java.util.List r18 = b(r47)
            int r11 = l(r47)
            r6 = 1
            int r1 = a(r15, r6)
            r6 = 0
            int r13 = a(r15, r6)
            java.lang.String r6 = m(r47)
            java.lang.String r19 = ""
            java.lang.String r0 = g(r47)
            java.lang.String r0 = r0.trim()
            r20 = r1
            int r1 = r0.hashCode()
            r22 = r6
            r6 = -925155509(0xffffffffc8db3f4b, float:-449018.34)
            r23 = -1
            r24 = r13
            r13 = 2
            if (r1 == r6) goto L_0x007b
            r6 = 1728122231(0x67010d77, float:6.0943366E23)
            if (r1 == r6) goto L_0x0071
            goto L_0x0085
        L_0x0071:
            java.lang.String r1 = "absolute"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0085
            r0 = 1
            goto L_0x0086
        L_0x007b:
            java.lang.String r1 = "reference"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0085
            r0 = 2
            goto L_0x0086
        L_0x0085:
            r0 = -1
        L_0x0086:
            if (r0 == r13) goto L_0x008a
            r0 = 0
            goto L_0x008b
        L_0x008a:
            r0 = 1
        L_0x008b:
            org.json.JSONArray r1 = o(r47)
            if (r1 == 0) goto L_0x00d8
            int r6 = r1.length()
            if (r6 == 0) goto L_0x00d8
            r6 = 0
            java.lang.String r1 = r1.getString(r6)     // Catch:{ JSONException -> 0x00c2 }
            boolean r6 = android.text.TextUtils.isEmpty(r1)     // Catch:{ JSONException -> 0x00c0 }
            if (r6 == 0) goto L_0x00a3
            return r21
        L_0x00a3:
            r6 = 1
            if (r0 != r6) goto L_0x00bb
            com.inmobi.ads.ak r6 = r7.b(r1)     // Catch:{ JSONException -> 0x00c0 }
            if (r6 != 0) goto L_0x00b9
            com.inmobi.ads.ao r0 = r7.f     // Catch:{ JSONException -> 0x00b7 }
            if (r0 == 0) goto L_0x00b9
            com.inmobi.ads.ao r0 = r7.f     // Catch:{ JSONException -> 0x00b7 }
            com.inmobi.ads.ak r0 = r0.b(r1)     // Catch:{ JSONException -> 0x00b7 }
            goto L_0x00bd
        L_0x00b7:
            r0 = move-exception
            goto L_0x00c7
        L_0x00b9:
            r0 = r6
            goto L_0x00bd
        L_0x00bb:
            r0 = r21
        L_0x00bd:
            r6 = r0
            r0 = r1
            goto L_0x00dc
        L_0x00c0:
            r0 = move-exception
            goto L_0x00c5
        L_0x00c2:
            r0 = move-exception
            r1 = r19
        L_0x00c5:
            r6 = r21
        L_0x00c7:
            com.inmobi.commons.core.a.a r13 = com.inmobi.commons.core.a.a.a()
            r19 = r1
            com.inmobi.commons.core.e.a r1 = new com.inmobi.commons.core.e.a
            r1.<init>(r0)
            r13.a(r1)
            r0 = r19
            goto L_0x00dc
        L_0x00d8:
            r0 = r19
            r6 = r21
        L_0x00dc:
            int r1 = r48.hashCode()     // Catch:{ JSONException -> 0x0835 }
            r19 = 6
            r25 = 7
            r26 = 3
            r27 = 4
            r28 = 5
            r29 = 9
            r30 = 8
            switch(r1) {
                case -1919329183: goto L_0x014f;
                case -1884772963: goto L_0x0144;
                case 67056: goto L_0x0139;
                case 70564: goto L_0x012f;
                case 2241657: goto L_0x0125;
                case 2571565: goto L_0x011b;
                case 69775675: goto L_0x0111;
                case 79826725: goto L_0x0107;
                case 81665115: goto L_0x00fd;
                case 1942407129: goto L_0x00f3;
                default: goto L_0x00f1;
            }     // Catch:{ JSONException -> 0x0835 }
        L_0x00f1:
            goto L_0x0159
        L_0x00f3:
            java.lang.String r1 = "WEBVIEW"
            boolean r1 = r14.equals(r1)     // Catch:{ JSONException -> 0x0835 }
            if (r1 == 0) goto L_0x0159
            r1 = 6
            goto L_0x015a
        L_0x00fd:
            java.lang.String r1 = "VIDEO"
            boolean r1 = r14.equals(r1)     // Catch:{ JSONException -> 0x0835 }
            if (r1 == 0) goto L_0x0159
            r1 = 7
            goto L_0x015a
        L_0x0107:
            java.lang.String r1 = "TIMER"
            boolean r1 = r14.equals(r1)     // Catch:{ JSONException -> 0x0835 }
            if (r1 == 0) goto L_0x0159
            r1 = 3
            goto L_0x015a
        L_0x0111:
            java.lang.String r1 = "IMAGE"
            boolean r1 = r14.equals(r1)     // Catch:{ JSONException -> 0x0835 }
            if (r1 == 0) goto L_0x0159
            r1 = 4
            goto L_0x015a
        L_0x011b:
            java.lang.String r1 = "TEXT"
            boolean r1 = r14.equals(r1)     // Catch:{ JSONException -> 0x0835 }
            if (r1 == 0) goto L_0x0159
            r1 = 1
            goto L_0x015a
        L_0x0125:
            java.lang.String r1 = "ICON"
            boolean r1 = r14.equals(r1)     // Catch:{ JSONException -> 0x0835 }
            if (r1 == 0) goto L_0x0159
            r1 = 2
            goto L_0x015a
        L_0x012f:
            java.lang.String r1 = "GIF"
            boolean r1 = r14.equals(r1)     // Catch:{ JSONException -> 0x0835 }
            if (r1 == 0) goto L_0x0159
            r1 = 5
            goto L_0x015a
        L_0x0139:
            java.lang.String r1 = "CTA"
            boolean r1 = r14.equals(r1)     // Catch:{ JSONException -> 0x0835 }
            if (r1 == 0) goto L_0x0159
            r1 = 9
            goto L_0x015a
        L_0x0144:
            java.lang.String r1 = "RATING"
            boolean r1 = r14.equals(r1)     // Catch:{ JSONException -> 0x0835 }
            if (r1 == 0) goto L_0x0159
            r1 = 8
            goto L_0x015a
        L_0x014f:
            java.lang.String r1 = "CONTAINER"
            boolean r1 = r14.equals(r1)     // Catch:{ JSONException -> 0x0835 }
            if (r1 == 0) goto L_0x0159
            r1 = 0
            goto L_0x015a
        L_0x0159:
            r1 = -1
        L_0x015a:
            switch(r1) {
                case 0: goto L_0x0643;
                case 1: goto L_0x0625;
                case 2: goto L_0x0603;
                case 3: goto L_0x0558;
                case 4: goto L_0x0478;
                case 5: goto L_0x0478;
                case 6: goto L_0x0405;
                case 7: goto L_0x023b;
                case 8: goto L_0x022d;
                case 9: goto L_0x0169;
                default: goto L_0x015d;
            }
        L_0x015d:
            r44 = r11
            r50 = r12
            r31 = r20
            r43 = r22
            r32 = r24
            goto L_0x0832
        L_0x0169:
            boolean r1 = p(r47)     // Catch:{ JSONException -> 0x0220 }
            if (r1 != 0) goto L_0x0170
            return r21
        L_0x0170:
            r13 = r20
            r1 = r46
            r8 = r22
            r6 = r9
            com.inmobi.ads.az$a r1 = r1.c(r2, r3, r4, r5, r6)     // Catch:{ JSONException -> 0x0215 }
            java.lang.String r2 = "assetOnclick"
            org.json.JSONObject r2 = r15.getJSONObject(r2)     // Catch:{ JSONException -> 0x0215 }
            java.lang.String r3 = "openMode"
            boolean r2 = r2.isNull(r3)     // Catch:{ JSONException -> 0x0215 }
            if (r2 != 0) goto L_0x01a5
            java.lang.String r2 = "assetOnclick"
            org.json.JSONObject r2 = r15.getJSONObject(r2)     // Catch:{ JSONException -> 0x019a }
            java.lang.String r3 = "openMode"
            java.lang.String r2 = r2.getString(r3)     // Catch:{ JSONException -> 0x019a }
            int r2 = d(r2)     // Catch:{ JSONException -> 0x019a }
            goto L_0x01a6
        L_0x019a:
            r0 = move-exception
            r43 = r8
            r44 = r11
            r50 = r12
            r31 = r13
            goto L_0x083e
        L_0x01a5:
            r2 = 2
        L_0x01a6:
            java.lang.String r3 = "assetOnclick"
            org.json.JSONObject r3 = r15.getJSONObject(r3)     // Catch:{ JSONException -> 0x0215 }
            java.lang.String r4 = "fallbackUrl"
            java.lang.String r3 = r3.optString(r4)     // Catch:{ JSONException -> 0x0215 }
            if (r18 == 0) goto L_0x01df
            int r4 = r18.size()     // Catch:{ JSONException -> 0x0215 }
            if (r4 != 0) goto L_0x01bb
            goto L_0x01df
        L_0x01bb:
            com.inmobi.ads.an r4 = new com.inmobi.ads.an     // Catch:{ JSONException -> 0x0215 }
            r5 = r8
            r8 = r4
            r9 = r12
            r6 = r11
            r11 = r1
            r1 = r12
            r12 = r0
            r50 = r6
            r31 = r13
            r32 = r24
            r6 = r49
            r13 = r18
            r14 = r2
            r2 = r15
            r15 = r47
            r8.<init>(r9, r10, r11, r12, r13, r14, r15)     // Catch:{ JSONException -> 0x01d9 }
            r15 = r2
            r0 = r4
            r4 = r1
            goto L_0x01f8
        L_0x01d9:
            r0 = move-exception
            r44 = r50
            r50 = r1
            goto L_0x0211
        L_0x01df:
            r5 = r8
            r50 = r11
            r4 = r12
            r31 = r13
            r32 = r24
            r6 = r49
            com.inmobi.ads.an r16 = new com.inmobi.ads.an     // Catch:{ JSONException -> 0x020c }
            r8 = r16
            r9 = r4
            r11 = r1
            r12 = r0
            r13 = r2
            r14 = r47
            r8.<init>(r9, r10, r11, r12, r13, r14)     // Catch:{ JSONException -> 0x020c }
            r0 = r16
        L_0x01f8:
            r0.g = r6     // Catch:{ JSONException -> 0x020c }
            a(r0, r15)     // Catch:{ JSONException -> 0x020c }
            if (r3 == 0) goto L_0x0202
            r0.b(r3)     // Catch:{ JSONException -> 0x020c }
        L_0x0202:
            r44 = r50
            r21 = r0
            r50 = r4
            r43 = r5
            goto L_0x0832
        L_0x020c:
            r0 = move-exception
            r44 = r50
            r50 = r4
        L_0x0211:
            r43 = r5
            goto L_0x0840
        L_0x0215:
            r0 = move-exception
            r31 = r13
            r32 = r24
            r43 = r8
            r44 = r11
            goto L_0x082f
        L_0x0220:
            r0 = move-exception
            r31 = r20
            r32 = r24
            r44 = r11
            r50 = r12
        L_0x0229:
            r43 = r22
            goto L_0x0840
        L_0x022d:
            r31 = r20
            r32 = r24
            r44 = r11
            r50 = r12
            r15 = r21
            r43 = r22
            goto L_0x084e
        L_0x023b:
            r14 = r11
            r1 = r12
            r31 = r20
            r13 = r22
            r32 = r24
            r12 = r6
            r6 = r49
            java.util.Map<java.lang.String, java.util.List<com.inmobi.ads.ak>> r11 = r7.h     // Catch:{ JSONException -> 0x03fa }
            java.lang.String r13 = "VIDEO"
            r11.get(r13)     // Catch:{ JSONException -> 0x03f4 }
            com.inmobi.ads.ba r42 = r7.s(r9)     // Catch:{ JSONException -> 0x03f4 }
            com.inmobi.ads.be$a r11 = new com.inmobi.ads.be$a     // Catch:{ JSONException -> 0x03f4 }
            int r9 = r2.x     // Catch:{ JSONException -> 0x03f4 }
            int r2 = r2.y     // Catch:{ JSONException -> 0x03f4 }
            int r13 = r3.x     // Catch:{ JSONException -> 0x03f4 }
            int r3 = r3.y     // Catch:{ JSONException -> 0x03f4 }
            r19 = r14
            int r14 = r4.x     // Catch:{ JSONException -> 0x03f0 }
            int r4 = r4.y     // Catch:{ JSONException -> 0x03f0 }
            int r6 = r5.x     // Catch:{ JSONException -> 0x03f0 }
            int r5 = r5.y     // Catch:{ JSONException -> 0x03f0 }
            r33 = r11
            r34 = r9
            r35 = r2
            r36 = r13
            r37 = r3
            r38 = r14
            r39 = r4
            r40 = r6
            r41 = r5
            r33.<init>(r34, r35, r36, r37, r38, r39, r40, r41, r42)     // Catch:{ JSONException -> 0x03f0 }
            if (r8 == 0) goto L_0x0298
            boolean r2 = a(r8, r11)     // Catch:{ JSONException -> 0x0292 }
            if (r2 != 0) goto L_0x0298
            com.inmobi.commons.core.e.b.a()     // Catch:{ JSONException -> 0x0292 }
            java.lang.String r2 = "ads"
            java.lang.String r3 = "InvalidVideoGeometry"
            java.util.HashMap r4 = new java.util.HashMap     // Catch:{ JSONException -> 0x0292 }
            r4.<init>()     // Catch:{ JSONException -> 0x0292 }
            com.inmobi.commons.core.e.b.a(r2, r3, r4)     // Catch:{ JSONException -> 0x0292 }
            goto L_0x0298
        L_0x0292:
            r0 = move-exception
            r50 = r1
            r44 = r19
            goto L_0x0229
        L_0x0298:
            com.inmobi.ads.bx r2 = r7.q     // Catch:{ JSONException -> 0x03f0 }
            if (r2 != 0) goto L_0x02a1
            com.inmobi.ads.by r0 = r7.a(r15, r0, r12)     // Catch:{ JSONException -> 0x0292 }
            goto L_0x02a3
        L_0x02a1:
            com.inmobi.ads.bx r0 = r7.q     // Catch:{ JSONException -> 0x03f0 }
        L_0x02a3:
            com.inmobi.ads.AdContainer$RenderingProperties$PlacementType r2 = com.inmobi.ads.AdContainer.RenderingProperties.PlacementType.PLACEMENT_TYPE_INLINE     // Catch:{ JSONException -> 0x03f0 }
            com.inmobi.ads.AdContainer$RenderingProperties$PlacementType r3 = r7.s     // Catch:{ JSONException -> 0x03f0 }
            r4 = 0
            if (r2 != r3) goto L_0x033e
            if (r12 == 0) goto L_0x0305
            java.util.Map<java.lang.String, java.lang.Object> r2 = r12.v     // Catch:{ JSONException -> 0x0292 }
            java.lang.String r3 = "didRequestFullScreen"
            java.lang.Object r2 = r2.get(r3)     // Catch:{ JSONException -> 0x0292 }
            java.lang.Boolean r2 = (java.lang.Boolean) r2     // Catch:{ JSONException -> 0x0292 }
            boolean r2 = r2.booleanValue()     // Catch:{ JSONException -> 0x0292 }
            if (r2 != 0) goto L_0x02cf
            boolean r2 = r7.t     // Catch:{ JSONException -> 0x0292 }
            if (r2 == 0) goto L_0x02c2
            goto L_0x02cf
        L_0x02c2:
            r2 = 0
            r4 = 0
            r6 = 2147483647(0x7fffffff, float:NaN)
            r13 = 1
            r14 = 1
            r16 = 1
            r17 = 1
            goto L_0x0378
        L_0x02cf:
            java.lang.String r2 = "loop"
            r3 = 0
            boolean r2 = r15.optBoolean(r2, r3)     // Catch:{ JSONException -> 0x0292 }
            java.lang.String r3 = "showProgress"
            r8 = 1
            boolean r3 = r15.optBoolean(r3, r8)     // Catch:{ JSONException -> 0x0292 }
            java.lang.String r9 = "soundOn"
            boolean r9 = r15.optBoolean(r9, r8)     // Catch:{ JSONException -> 0x0292 }
            java.lang.String r13 = "showMute"
            boolean r13 = r15.optBoolean(r13, r8)     // Catch:{ JSONException -> 0x0292 }
            java.lang.String r14 = "autoPlay"
            boolean r8 = r15.optBoolean(r14, r8)     // Catch:{ JSONException -> 0x0292 }
            r14 = r12
            com.inmobi.ads.be r14 = (com.inmobi.ads.be) r14     // Catch:{ JSONException -> 0x0292 }
            int r14 = r14.E     // Catch:{ JSONException -> 0x0292 }
            java.lang.String r6 = "pauseAfter"
            double r4 = r15.optDouble(r6, r4)     // Catch:{ JSONException -> 0x0292 }
            int r6 = (int) r4     // Catch:{ JSONException -> 0x0292 }
            r16 = r3
            r4 = r6
            r17 = r8
            r6 = r14
            r14 = r13
            r13 = r9
            goto L_0x0378
        L_0x0305:
            java.lang.String r2 = "loop"
            r3 = 1
            boolean r2 = r15.optBoolean(r2, r3)     // Catch:{ JSONException -> 0x0292 }
            java.lang.String r3 = "showProgress"
            r6 = 0
            boolean r3 = r15.optBoolean(r3, r6)     // Catch:{ JSONException -> 0x0292 }
            java.lang.String r8 = "soundOn"
            boolean r8 = r15.optBoolean(r8, r6)     // Catch:{ JSONException -> 0x0292 }
            java.lang.String r9 = "showMute"
            boolean r6 = r15.optBoolean(r9, r6)     // Catch:{ JSONException -> 0x0292 }
            java.lang.String r9 = "autoPlay"
            r13 = 1
            boolean r9 = r15.optBoolean(r9, r13)     // Catch:{ JSONException -> 0x0292 }
            java.lang.String r13 = "completeAfter"
            r14 = 2147483647(0x7fffffff, float:NaN)
            int r13 = r15.optInt(r13, r14)     // Catch:{ JSONException -> 0x0292 }
            java.lang.String r14 = "pauseAfter"
            double r4 = r15.optDouble(r14, r4)     // Catch:{ JSONException -> 0x0292 }
            int r4 = (int) r4
            r16 = r3
            r14 = r6
            r17 = r9
            r6 = r13
            r13 = r8
            goto L_0x0378
        L_0x033e:
            java.lang.String r2 = "loop"
            r6 = 0
            boolean r2 = r15.optBoolean(r2, r6)     // Catch:{ JSONException -> 0x03f0 }
            java.lang.String r3 = "showProgress"
            r8 = 1
            boolean r3 = r15.optBoolean(r3, r8)     // Catch:{ JSONException -> 0x03f0 }
            java.lang.String r6 = "soundOn"
            boolean r6 = r15.optBoolean(r6, r8)     // Catch:{ JSONException -> 0x03f0 }
            java.lang.String r9 = "showMute"
            boolean r9 = r15.optBoolean(r9, r8)     // Catch:{ JSONException -> 0x03f0 }
            java.lang.String r13 = "autoPlay"
            boolean r8 = r15.optBoolean(r13, r8)     // Catch:{ JSONException -> 0x03f0 }
            java.lang.String r13 = "completeAfter"
            r14 = 2147483647(0x7fffffff, float:NaN)
            int r13 = r15.optInt(r13, r14)     // Catch:{ JSONException -> 0x03f0 }
            java.lang.String r14 = "pauseAfter"
            double r4 = r15.optDouble(r14, r4)     // Catch:{ JSONException -> 0x03f0 }
            int r4 = (int) r4     // Catch:{ JSONException -> 0x03f0 }
            r16 = r3
            r17 = r8
            r14 = r9
            r45 = r13
            r13 = r6
            r6 = r45
        L_0x0378:
            java.util.HashMap r3 = new java.util.HashMap     // Catch:{ JSONException -> 0x03f0 }
            r3.<init>()     // Catch:{ JSONException -> 0x03f0 }
            java.lang.String r5 = "videoViewabilityConfig"
            boolean r5 = r15.isNull(r5)     // Catch:{ JSONException -> 0x03f0 }
            if (r5 != 0) goto L_0x03a7
            java.lang.String r5 = "videoViewabilityConfig"
            org.json.JSONObject r5 = r15.getJSONObject(r5)     // Catch:{ JSONException -> 0x0292 }
            java.util.Iterator r8 = r5.keys()     // Catch:{ JSONException -> 0x0292 }
        L_0x038f:
            boolean r9 = r8.hasNext()     // Catch:{ JSONException -> 0x0292 }
            if (r9 == 0) goto L_0x03a7
            java.lang.Object r9 = r8.next()     // Catch:{ JSONException -> 0x0292 }
            java.lang.String r9 = (java.lang.String) r9     // Catch:{ JSONException -> 0x0292 }
            r20 = r8
            java.lang.Object r8 = r5.get(r9)     // Catch:{ JSONException -> 0x0292 }
            r3.put(r9, r8)     // Catch:{ JSONException -> 0x0292 }
            r8 = r20
            goto L_0x038f
        L_0x03a7:
            com.inmobi.ads.be r5 = new com.inmobi.ads.be     // Catch:{ JSONException -> 0x03f0 }
            com.inmobi.ads.c r8 = r7.r     // Catch:{ JSONException -> 0x03f0 }
            com.inmobi.ads.c$i r8 = r8.i     // Catch:{ JSONException -> 0x03f0 }
            boolean r9 = r8.m     // Catch:{ JSONException -> 0x03f0 }
            r8 = r5
            r20 = r9
            r9 = r1
            r23 = r1
            r1 = r12
            r12 = r0
            r43 = r22
            r44 = r19
            r0 = 2147483647(0x7fffffff, float:NaN)
            r7 = r15
            r15 = r2
            r19 = r47
            r8.<init>(r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20)     // Catch:{ JSONException -> 0x042e }
            r2 = r5
            com.inmobi.ads.be r2 = (com.inmobi.ads.be) r2     // Catch:{ JSONException -> 0x042e }
            java.util.HashMap r7 = new java.util.HashMap     // Catch:{ JSONException -> 0x042e }
            r7.<init>(r3)     // Catch:{ JSONException -> 0x042e }
            r2.G = r7     // Catch:{ JSONException -> 0x042e }
            r2 = r5
            com.inmobi.ads.be r2 = (com.inmobi.ads.be) r2     // Catch:{ JSONException -> 0x042e }
            if (r6 > 0) goto L_0x03d5
            goto L_0x03d6
        L_0x03d5:
            r0 = r6
        L_0x03d6:
            r2.E = r0     // Catch:{ JSONException -> 0x042e }
            r11 = r49
            r5.g = r11     // Catch:{ JSONException -> 0x042e }
            r5.y = r1     // Catch:{ JSONException -> 0x042e }
            if (r1 == 0) goto L_0x03e2
            r1.y = r5     // Catch:{ JSONException -> 0x042e }
        L_0x03e2:
            if (r4 == 0) goto L_0x03e9
            r0 = r5
            com.inmobi.ads.be r0 = (com.inmobi.ads.be) r0     // Catch:{ JSONException -> 0x042e }
            r0.F = r4     // Catch:{ JSONException -> 0x042e }
        L_0x03e9:
            r15 = r5
            r50 = r23
            r7 = r46
            goto L_0x084e
        L_0x03f0:
            r0 = move-exception
            r44 = r19
            goto L_0x03f7
        L_0x03f4:
            r0 = move-exception
            r44 = r14
        L_0x03f7:
            r43 = r22
            goto L_0x03ff
        L_0x03fa:
            r0 = move-exception
            r43 = r13
            r44 = r14
        L_0x03ff:
            r50 = r1
        L_0x0401:
            r7 = r46
            goto L_0x0840
        L_0x0405:
            r44 = r11
            r23 = r12
            r7 = r15
            r31 = r20
            r43 = r22
            r32 = r24
            r6 = 0
            r8 = 1
            r11 = r49
            if (r0 != 0) goto L_0x0417
            return r21
        L_0x0417:
            java.lang.String r1 = h(r47)     // Catch:{ JSONException -> 0x0471 }
            java.lang.String r12 = com.inmobi.ads.bf.c(r1)     // Catch:{ JSONException -> 0x0471 }
            java.lang.String r1 = "URL"
            boolean r1 = r1.equals(r12)     // Catch:{ JSONException -> 0x0471 }
            if (r1 == 0) goto L_0x0432
            boolean r1 = android.webkit.URLUtil.isValidUrl(r0)     // Catch:{ JSONException -> 0x042e }
            if (r1 != 0) goto L_0x0432
            return r21
        L_0x042e:
            r0 = move-exception
            r50 = r23
            goto L_0x0401
        L_0x0432:
            r15 = r23
            r1 = r46
            r14 = r11
            r11 = 0
            r13 = 1
            r6 = r9
            com.inmobi.ads.al r4 = r1.a(r2, r3, r4, r5, r6)     // Catch:{ JSONException -> 0x046c }
            com.inmobi.ads.bf r8 = new com.inmobi.ads.bf     // Catch:{ JSONException -> 0x046c }
            java.lang.String r1 = "isScrollable"
            boolean r6 = r7.optBoolean(r1)     // Catch:{ JSONException -> 0x046c }
            r1 = r8
            r2 = r15
            r3 = r10
            r5 = r0
            r1.<init>(r2, r3, r4, r5, r6)     // Catch:{ JSONException -> 0x046c }
            r8.z = r12     // Catch:{ JSONException -> 0x046c }
            r8.g = r14     // Catch:{ JSONException -> 0x046c }
            java.lang.String r0 = "preload"
            boolean r0 = r7.optBoolean(r0, r11)     // Catch:{ JSONException -> 0x046c }
            if (r0 == 0) goto L_0x0464
            r8.A = r13     // Catch:{ JSONException -> 0x046c }
            r7 = r46
            r7.k = r8     // Catch:{ JSONException -> 0x04bf }
            r50 = r15
            r15 = r8
            goto L_0x084e
        L_0x0464:
            r7 = r46
            r21 = r8
            r50 = r15
            goto L_0x0832
        L_0x046c:
            r0 = move-exception
            r7 = r46
            goto L_0x0554
        L_0x0471:
            r0 = move-exception
            r7 = r46
            r50 = r23
            goto L_0x0840
        L_0x0478:
            r44 = r11
            r13 = r15
            r31 = r20
            r43 = r22
            r32 = r24
            r11 = 0
            r14 = r49
            r15 = r12
            r1 = r46
            r6 = r9
            com.inmobi.ads.al r0 = r1.a(r2, r3, r4, r5, r6)     // Catch:{ JSONException -> 0x0551 }
            boolean r1 = p(r47)     // Catch:{ JSONException -> 0x0551 }
            if (r1 == 0) goto L_0x04c2
            java.lang.String r1 = "assetOnclick"
            org.json.JSONObject r1 = r13.getJSONObject(r1)     // Catch:{ JSONException -> 0x04bf }
            java.lang.String r2 = "openMode"
            boolean r1 = r1.isNull(r2)     // Catch:{ JSONException -> 0x04bf }
            if (r1 != 0) goto L_0x04b1
            java.lang.String r1 = "assetOnclick"
            org.json.JSONObject r1 = r13.getJSONObject(r1)     // Catch:{ JSONException -> 0x04bf }
            java.lang.String r2 = "openMode"
            java.lang.String r1 = r1.getString(r2)     // Catch:{ JSONException -> 0x04bf }
            int r1 = d(r1)     // Catch:{ JSONException -> 0x04bf }
            goto L_0x04b2
        L_0x04b1:
            r1 = 2
        L_0x04b2:
            java.lang.String r2 = "assetOnclick"
            org.json.JSONObject r2 = r13.getJSONObject(r2)     // Catch:{ JSONException -> 0x04bf }
            java.lang.String r3 = "fallbackUrl"
            java.lang.String r2 = r2.optString(r3)     // Catch:{ JSONException -> 0x04bf }
            goto L_0x04c5
        L_0x04bf:
            r0 = move-exception
            goto L_0x0554
        L_0x04c2:
            r2 = r21
            r1 = 0
        L_0x04c5:
            if (r18 == 0) goto L_0x050c
            int r3 = r18.size()     // Catch:{ JSONException -> 0x0551 }
            if (r3 != 0) goto L_0x04ce
            goto L_0x050c
        L_0x04ce:
            java.lang.String r3 = "IMAGE"
            r6 = r48
            boolean r3 = r3.equals(r6)     // Catch:{ JSONException -> 0x04bf }
            if (r3 == 0) goto L_0x04f4
            com.inmobi.ads.as r3 = new com.inmobi.ads.as     // Catch:{ JSONException -> 0x04bf }
            java.lang.String r12 = c(r47)     // Catch:{ JSONException -> 0x04bf }
            r8 = r3
            r9 = r15
            r11 = r0
            r4 = r13
            r13 = r18
            r5 = r14
            r14 = r1
            r1 = r15
            r15 = r47
            r8.<init>(r9, r10, r11, r12, r13, r14, r15)     // Catch:{ JSONException -> 0x04ef }
            r15 = r3
            r3 = r1
            goto L_0x0538
        L_0x04ef:
            r0 = move-exception
            r50 = r1
            goto L_0x0840
        L_0x04f4:
            r4 = r13
            r5 = r14
            r3 = r15
            com.inmobi.ads.aq r16 = new com.inmobi.ads.aq     // Catch:{ JSONException -> 0x054c }
            java.lang.String r12 = c(r47)     // Catch:{ JSONException -> 0x054c }
            r8 = r16
            r9 = r3
            r11 = r0
            r13 = r18
            r14 = r1
            r15 = r47
            r8.<init>(r9, r10, r11, r12, r13, r14, r15)     // Catch:{ JSONException -> 0x054c }
            r15 = r16
            goto L_0x0538
        L_0x050c:
            r4 = r13
            r5 = r14
            r3 = r15
            r6 = r48
            java.lang.String r8 = "IMAGE"
            boolean r8 = r8.equals(r6)     // Catch:{ JSONException -> 0x054c }
            if (r8 == 0) goto L_0x0529
            com.inmobi.ads.as r15 = new com.inmobi.ads.as     // Catch:{ JSONException -> 0x054c }
            java.lang.String r12 = c(r47)     // Catch:{ JSONException -> 0x054c }
            r8 = r15
            r9 = r3
            r11 = r0
            r13 = r1
            r14 = r47
            r8.<init>(r9, r10, r11, r12, r13, r14)     // Catch:{ JSONException -> 0x054c }
            goto L_0x0538
        L_0x0529:
            com.inmobi.ads.aq r15 = new com.inmobi.ads.aq     // Catch:{ JSONException -> 0x054c }
            java.lang.String r12 = c(r47)     // Catch:{ JSONException -> 0x054c }
            r8 = r15
            r9 = r3
            r11 = r0
            r13 = r1
            r14 = r47
            r8.<init>(r9, r10, r11, r12, r13, r14)     // Catch:{ JSONException -> 0x054c }
        L_0x0538:
            r15.g = r5     // Catch:{ JSONException -> 0x054c }
            a(r15, r4)     // Catch:{ JSONException -> 0x054c }
            if (r2 == 0) goto L_0x0546
            r15.b(r2)     // Catch:{ JSONException -> 0x054c }
            r50 = r3
            goto L_0x084e
        L_0x0546:
            r50 = r3
            r21 = r15
            goto L_0x0832
        L_0x054c:
            r0 = move-exception
            r50 = r3
            goto L_0x0840
        L_0x0551:
            r0 = move-exception
            r6 = r48
        L_0x0554:
            r50 = r15
            goto L_0x0840
        L_0x0558:
            r44 = r11
            r6 = r14
            r31 = r20
            r43 = r22
            r32 = r24
            r11 = 0
            r13 = 1
            r14 = r49
            r1 = r46
            r8 = r6
            r6 = r9
            com.inmobi.ads.al r0 = r1.a(r2, r3, r4, r5, r6)     // Catch:{ JSONException -> 0x082e }
            java.lang.String r1 = "startOffset"
            boolean r1 = r15.has(r1)     // Catch:{ JSONException -> 0x082e }
            if (r1 == 0) goto L_0x0580
            java.lang.String r1 = "startOffset"
            org.json.JSONObject r1 = r15.getJSONObject(r1)     // Catch:{ JSONException -> 0x082e }
            com.inmobi.ads.ba$a r1 = r7.q(r1)     // Catch:{ JSONException -> 0x082e }
            goto L_0x0582
        L_0x0580:
            r1 = r21
        L_0x0582:
            java.lang.String r2 = "timerDuration"
            boolean r2 = r15.has(r2)     // Catch:{ JSONException -> 0x082e }
            if (r2 == 0) goto L_0x0595
            java.lang.String r2 = "timerDuration"
            org.json.JSONObject r2 = r15.getJSONObject(r2)     // Catch:{ JSONException -> 0x082e }
            com.inmobi.ads.ba$a r2 = r7.q(r2)     // Catch:{ JSONException -> 0x082e }
            goto L_0x0597
        L_0x0595:
            r2 = r21
        L_0x0597:
            java.lang.String r3 = "displayTimer"
            boolean r3 = r15.optBoolean(r3, r13)     // Catch:{ JSONException -> 0x082e }
            com.inmobi.ads.ba r4 = new com.inmobi.ads.ba     // Catch:{ JSONException -> 0x082e }
            r4.<init>(r1, r2)     // Catch:{ JSONException -> 0x082e }
            com.inmobi.ads.bb r1 = new com.inmobi.ads.bb     // Catch:{ JSONException -> 0x082e }
            r1.<init>(r12, r10, r0, r4)     // Catch:{ JSONException -> 0x082e }
            r1.z = r3     // Catch:{ JSONException -> 0x082e }
            java.lang.String r0 = "assetOnFinish"
            boolean r0 = r15.has(r0)     // Catch:{ JSONException -> 0x082e }
            if (r0 == 0) goto L_0x05fc
            java.lang.String r0 = "assetOnFinish"
            java.lang.Object r0 = r15.get(r0)     // Catch:{ JSONException -> 0x082e }
            org.json.JSONObject r0 = (org.json.JSONObject) r0     // Catch:{ JSONException -> 0x082e }
            java.lang.String r2 = "action"
            boolean r2 = r0.has(r2)     // Catch:{ JSONException -> 0x082e }
            if (r2 == 0) goto L_0x05fc
            java.lang.String r2 = "action"
            java.lang.String r0 = r0.getString(r2)     // Catch:{ JSONException -> 0x082e }
            java.util.Locale r2 = java.util.Locale.US     // Catch:{ JSONException -> 0x082e }
            java.lang.String r0 = r0.toUpperCase(r2)     // Catch:{ JSONException -> 0x082e }
            java.lang.String r0 = r0.trim()     // Catch:{ JSONException -> 0x082e }
            int r2 = r0.hashCode()     // Catch:{ JSONException -> 0x082e }
            r3 = 2142494(0x20b11e, float:3.002274E-39)
            if (r2 == r3) goto L_0x05ea
            r3 = 2402104(0x24a738, float:3.366065E-39)
            if (r2 == r3) goto L_0x05e0
            goto L_0x05f4
        L_0x05e0:
            java.lang.String r2 = "NONE"
            boolean r0 = r0.equals(r2)     // Catch:{ JSONException -> 0x082e }
            if (r0 == 0) goto L_0x05f4
            r0 = 1
            goto L_0x05f5
        L_0x05ea:
            java.lang.String r2 = "EXIT"
            boolean r0 = r0.equals(r2)     // Catch:{ JSONException -> 0x082e }
            if (r0 == 0) goto L_0x05f4
            r0 = 2
            goto L_0x05f5
        L_0x05f4:
            r0 = -1
        L_0x05f5:
            r2 = 2
            if (r0 == r2) goto L_0x05f9
            goto L_0x05fa
        L_0x05f9:
            r11 = 1
        L_0x05fa:
            r1.k = r11     // Catch:{ JSONException -> 0x082e }
        L_0x05fc:
            r1.g = r14     // Catch:{ JSONException -> 0x082e }
            r15 = r1
            r50 = r12
            goto L_0x084e
        L_0x0603:
            r44 = r11
            r8 = r14
            r31 = r20
            r43 = r22
            r32 = r24
            r14 = r49
            r1 = r46
            r6 = r9
            com.inmobi.ads.al r0 = r1.a(r2, r3, r4, r5, r6)     // Catch:{ JSONException -> 0x082e }
            com.inmobi.ads.ar r1 = new com.inmobi.ads.ar     // Catch:{ JSONException -> 0x082e }
            java.lang.String r2 = c(r47)     // Catch:{ JSONException -> 0x082e }
            r1.<init>(r12, r10, r0, r2)     // Catch:{ JSONException -> 0x082e }
            r1.g = r14     // Catch:{ JSONException -> 0x082e }
            r15 = r1
            r50 = r12
            goto L_0x084e
        L_0x0625:
            r44 = r11
            r8 = r14
            r31 = r20
            r43 = r22
            r32 = r24
            r14 = r49
            r1 = r46
            r6 = r9
            com.inmobi.ads.az$a r1 = r1.b(r2, r3, r4, r5, r6)     // Catch:{ JSONException -> 0x082e }
            com.inmobi.ads.az r2 = new com.inmobi.ads.az     // Catch:{ JSONException -> 0x082e }
            r2.<init>(r12, r10, r1, r0)     // Catch:{ JSONException -> 0x082e }
            r2.g = r14     // Catch:{ JSONException -> 0x082e }
            r15 = r2
            r50 = r12
            goto L_0x084e
        L_0x0643:
            r44 = r11
            r6 = r14
            r31 = r20
            r43 = r22
            r32 = r24
            r11 = 0
            r13 = 1
            r14 = r49
            r1 = r46
            r6 = r9
            com.inmobi.ads.al r0 = r1.a(r2, r3, r4, r5, r6)     // Catch:{ JSONException -> 0x082e }
            if (r8 == 0) goto L_0x066e
            boolean r1 = a(r8, r0)     // Catch:{ JSONException -> 0x082e }
            if (r1 != 0) goto L_0x066e
            com.inmobi.commons.core.e.b.a()     // Catch:{ JSONException -> 0x082e }
            java.lang.String r1 = "ads"
            java.lang.String r2 = "InvalidContainerGeometry"
            java.util.HashMap r3 = new java.util.HashMap     // Catch:{ JSONException -> 0x082e }
            r3.<init>()     // Catch:{ JSONException -> 0x082e }
            com.inmobi.commons.core.e.b.a(r1, r2, r3)     // Catch:{ JSONException -> 0x082e }
        L_0x066e:
            boolean r1 = p(r47)     // Catch:{ JSONException -> 0x082e }
            if (r1 == 0) goto L_0x06a1
            java.lang.String r1 = "assetOnclick"
            org.json.JSONObject r1 = r15.getJSONObject(r1)     // Catch:{ JSONException -> 0x082e }
            java.lang.String r2 = "openMode"
            boolean r1 = r1.isNull(r2)     // Catch:{ JSONException -> 0x082e }
            if (r1 != 0) goto L_0x0693
            java.lang.String r1 = "assetOnclick"
            org.json.JSONObject r1 = r15.getJSONObject(r1)     // Catch:{ JSONException -> 0x082e }
            java.lang.String r2 = "openMode"
            java.lang.String r1 = r1.getString(r2)     // Catch:{ JSONException -> 0x082e }
            int r1 = d(r1)     // Catch:{ JSONException -> 0x082e }
            goto L_0x0694
        L_0x0693:
            r1 = 2
        L_0x0694:
            java.lang.String r2 = "assetOnclick"
            org.json.JSONObject r2 = r15.getJSONObject(r2)     // Catch:{ JSONException -> 0x082e }
            java.lang.String r3 = "fallbackUrl"
            java.lang.String r2 = r2.optString(r3)     // Catch:{ JSONException -> 0x082e }
            goto L_0x06a4
        L_0x06a1:
            r2 = r21
            r1 = 0
        L_0x06a4:
            java.lang.String r3 = "transitionEffect"
            boolean r3 = r9.has(r3)     // Catch:{ JSONException -> 0x082e }
            if (r3 == 0) goto L_0x06e1
            java.lang.String r3 = "transitionEffect"
            java.lang.String r3 = r9.getString(r3)     // Catch:{ JSONException -> 0x082e }
            java.lang.String r3 = r3.trim()     // Catch:{ JSONException -> 0x082e }
            int r4 = r3.hashCode()     // Catch:{ JSONException -> 0x082e }
            r5 = 3151468(0x30166c, float:4.416147E-39)
            if (r4 == r5) goto L_0x06cf
            r5 = 106426293(0x657efb5, float:4.0613115E-35)
            if (r4 == r5) goto L_0x06c5
            goto L_0x06d9
        L_0x06c5:
            java.lang.String r4 = "paged"
            boolean r3 = r3.equals(r4)     // Catch:{ JSONException -> 0x082e }
            if (r3 == 0) goto L_0x06d9
            r3 = 1
            goto L_0x06da
        L_0x06cf:
            java.lang.String r4 = "free"
            boolean r3 = r3.equals(r4)     // Catch:{ JSONException -> 0x082e }
            if (r3 == 0) goto L_0x06d9
            r3 = 2
            goto L_0x06da
        L_0x06d9:
            r3 = -1
        L_0x06da:
            r4 = 2
            if (r3 == r4) goto L_0x06df
            r3 = 0
            goto L_0x06e3
        L_0x06df:
            r3 = 1
            goto L_0x06e3
        L_0x06e1:
            r4 = 2
            r3 = 0
        L_0x06e3:
            if (r18 == 0) goto L_0x0705
            int r5 = r18.size()     // Catch:{ JSONException -> 0x082e }
            if (r5 != 0) goto L_0x06ec
            goto L_0x0705
        L_0x06ec:
            com.inmobi.ads.am r5 = new com.inmobi.ads.am     // Catch:{ JSONException -> 0x082e }
            r8 = r5
            r9 = r12
            r6 = 0
            r11 = r0
            r50 = r12
            r12 = r18
            r4 = 1
            r16 = 2
            r13 = r1
            r1 = r14
            r14 = r47
            r4 = r15
            r15 = r3
            r8.<init>(r9, r10, r11, r12, r13, r14, r15)     // Catch:{ JSONException -> 0x082c }
            r15 = r5
            r5 = r1
            goto L_0x0719
        L_0x0705:
            r50 = r12
            r5 = r14
            r4 = r15
            r6 = 0
            r16 = 2
            com.inmobi.ads.am r15 = new com.inmobi.ads.am     // Catch:{ JSONException -> 0x082c }
            r8 = r15
            r9 = r50
            r11 = r0
            r12 = r1
            r13 = r47
            r14 = r3
            r8.<init>(r9, r10, r11, r12, r13, r14)     // Catch:{ JSONException -> 0x082c }
        L_0x0719:
            r15.g = r5     // Catch:{ JSONException -> 0x082c }
            if (r2 == 0) goto L_0x0720
            r15.b(r2)     // Catch:{ JSONException -> 0x082c }
        L_0x0720:
            a(r15, r4)     // Catch:{ JSONException -> 0x082c }
            java.lang.String r1 = "assetValue"
            org.json.JSONArray r1 = r4.getJSONArray(r1)     // Catch:{ JSONException -> 0x082c }
            r2 = 0
        L_0x072a:
            int r3 = r1.length()     // Catch:{ JSONException -> 0x082c }
            if (r2 >= r3) goto L_0x084e
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x082c }
            r3.<init>()     // Catch:{ JSONException -> 0x082c }
            r3.append(r5)     // Catch:{ JSONException -> 0x082c }
            java.lang.String r4 = ".assetValue["
            r3.append(r4)     // Catch:{ JSONException -> 0x082c }
            r3.append(r2)     // Catch:{ JSONException -> 0x082c }
            java.lang.String r4 = "]"
            r3.append(r4)     // Catch:{ JSONException -> 0x082c }
            java.lang.String r3 = r3.toString()     // Catch:{ JSONException -> 0x082c }
            org.json.JSONObject r4 = r1.getJSONObject(r2)     // Catch:{ JSONException -> 0x082c }
            java.lang.String r8 = f(r4)     // Catch:{ JSONException -> 0x082c }
            java.util.Locale r9 = java.util.Locale.US     // Catch:{ JSONException -> 0x082c }
            java.lang.String r8 = r8.toLowerCase(r9)     // Catch:{ JSONException -> 0x082c }
            java.lang.String r8 = r8.trim()     // Catch:{ JSONException -> 0x082c }
            int r9 = r8.hashCode()     // Catch:{ JSONException -> 0x082c }
            switch(r9) {
                case -938102371: goto L_0x07c1;
                case -410956671: goto L_0x07b7;
                case 98832: goto L_0x07ad;
                case 102340: goto L_0x07a2;
                case 3226745: goto L_0x0798;
                case 3556653: goto L_0x078e;
                case 100313435: goto L_0x0784;
                case 110364485: goto L_0x0779;
                case 112202875: goto L_0x076f;
                case 1224424441: goto L_0x0764;
                default: goto L_0x0762;
            }     // Catch:{ JSONException -> 0x082c }
        L_0x0762:
            goto L_0x07cb
        L_0x0764:
            java.lang.String r9 = "webview"
            boolean r8 = r8.equals(r9)     // Catch:{ JSONException -> 0x082c }
            if (r8 == 0) goto L_0x07cb
            r8 = 9
            goto L_0x07cc
        L_0x076f:
            java.lang.String r9 = "video"
            boolean r8 = r8.equals(r9)     // Catch:{ JSONException -> 0x082c }
            if (r8 == 0) goto L_0x07cb
            r8 = 4
            goto L_0x07cc
        L_0x0779:
            java.lang.String r9 = "timer"
            boolean r8 = r8.equals(r9)     // Catch:{ JSONException -> 0x082c }
            if (r8 == 0) goto L_0x07cb
            r8 = 8
            goto L_0x07cc
        L_0x0784:
            java.lang.String r9 = "image"
            boolean r8 = r8.equals(r9)     // Catch:{ JSONException -> 0x082c }
            if (r8 == 0) goto L_0x07cb
            r8 = 3
            goto L_0x07cc
        L_0x078e:
            java.lang.String r9 = "text"
            boolean r8 = r8.equals(r9)     // Catch:{ JSONException -> 0x082c }
            if (r8 == 0) goto L_0x07cb
            r8 = 5
            goto L_0x07cc
        L_0x0798:
            java.lang.String r9 = "icon"
            boolean r8 = r8.equals(r9)     // Catch:{ JSONException -> 0x082c }
            if (r8 == 0) goto L_0x07cb
            r8 = 2
            goto L_0x07cc
        L_0x07a2:
            java.lang.String r9 = "gif"
            boolean r8 = r8.equals(r9)     // Catch:{ JSONException -> 0x082c }
            if (r8 == 0) goto L_0x07cb
            r8 = 10
            goto L_0x07cc
        L_0x07ad:
            java.lang.String r9 = "cta"
            boolean r8 = r8.equals(r9)     // Catch:{ JSONException -> 0x082c }
            if (r8 == 0) goto L_0x07cb
            r8 = 6
            goto L_0x07cc
        L_0x07b7:
            java.lang.String r9 = "container"
            boolean r8 = r8.equals(r9)     // Catch:{ JSONException -> 0x082c }
            if (r8 == 0) goto L_0x07cb
            r8 = 1
            goto L_0x07cc
        L_0x07c1:
            java.lang.String r9 = "rating"
            boolean r8 = r8.equals(r9)     // Catch:{ JSONException -> 0x082c }
            if (r8 == 0) goto L_0x07cb
            r8 = 7
            goto L_0x07cc
        L_0x07cb:
            r8 = -1
        L_0x07cc:
            switch(r8) {
                case 2: goto L_0x07ea;
                case 3: goto L_0x07e7;
                case 4: goto L_0x07e4;
                case 5: goto L_0x07e1;
                case 6: goto L_0x07de;
                case 7: goto L_0x07db;
                case 8: goto L_0x07d8;
                case 9: goto L_0x07d5;
                case 10: goto L_0x07d2;
                default: goto L_0x07cf;
            }     // Catch:{ JSONException -> 0x082c }
        L_0x07cf:
            java.lang.String r8 = "CONTAINER"
            goto L_0x07ec
        L_0x07d2:
            java.lang.String r8 = "GIF"
            goto L_0x07ec
        L_0x07d5:
            java.lang.String r8 = "WEBVIEW"
            goto L_0x07ec
        L_0x07d8:
            java.lang.String r8 = "TIMER"
            goto L_0x07ec
        L_0x07db:
            java.lang.String r8 = "RATING"
            goto L_0x07ec
        L_0x07de:
            java.lang.String r8 = "CTA"
            goto L_0x07ec
        L_0x07e1:
            java.lang.String r8 = "TEXT"
            goto L_0x07ec
        L_0x07e4:
            java.lang.String r8 = "VIDEO"
            goto L_0x07ec
        L_0x07e7:
            java.lang.String r8 = "IMAGE"
            goto L_0x07ec
        L_0x07ea:
            java.lang.String r8 = "ICON"
        L_0x07ec:
            com.inmobi.ads.ak r8 = r7.a(r4, r8, r3, r0)     // Catch:{ JSONException -> 0x082c }
            if (r8 != 0) goto L_0x07fd
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x082c }
            java.lang.String r8 = "Cannot build asset from JSON: "
            r3.<init>(r8)     // Catch:{ JSONException -> 0x082c }
            r3.append(r4)     // Catch:{ JSONException -> 0x082c }
            goto L_0x0828
        L_0x07fd:
            r8.g = r3     // Catch:{ JSONException -> 0x082c }
            r8.t = r15     // Catch:{ JSONException -> 0x082c }
            int r3 = r15.C     // Catch:{ JSONException -> 0x082c }
            r4 = 16
            if (r3 >= r4) goto L_0x0828
            int r3 = r15.C     // Catch:{ JSONException -> 0x082c }
            com.inmobi.ads.ak[] r4 = r15.B     // Catch:{ JSONException -> 0x082c }
            int r4 = r4.length     // Catch:{ JSONException -> 0x082c }
            if (r3 != r4) goto L_0x081e
            com.inmobi.ads.ak[] r3 = r15.B     // Catch:{ JSONException -> 0x082c }
            int r3 = r3.length     // Catch:{ JSONException -> 0x082c }
            int r3 = r3 * 2
            com.inmobi.ads.ak[] r3 = new com.inmobi.ads.ak[r3]     // Catch:{ JSONException -> 0x082c }
            com.inmobi.ads.ak[] r4 = r15.B     // Catch:{ JSONException -> 0x082c }
            int r9 = r15.C     // Catch:{ JSONException -> 0x082c }
            java.lang.System.arraycopy(r4, r6, r3, r6, r9)     // Catch:{ JSONException -> 0x082c }
            r15.B = r3     // Catch:{ JSONException -> 0x082c }
        L_0x081e:
            com.inmobi.ads.ak[] r3 = r15.B     // Catch:{ JSONException -> 0x082c }
            int r4 = r15.C     // Catch:{ JSONException -> 0x082c }
            int r9 = r4 + 1
            r15.C = r9     // Catch:{ JSONException -> 0x082c }
            r3[r4] = r8     // Catch:{ JSONException -> 0x082c }
        L_0x0828:
            int r2 = r2 + 1
            goto L_0x072a
        L_0x082c:
            r0 = move-exception
            goto L_0x0840
        L_0x082e:
            r0 = move-exception
        L_0x082f:
            r50 = r12
            goto L_0x0840
        L_0x0832:
            r15 = r21
            goto L_0x084e
        L_0x0835:
            r0 = move-exception
            r44 = r11
            r50 = r12
            r31 = r20
            r43 = r22
        L_0x083e:
            r32 = r24
        L_0x0840:
            com.inmobi.commons.core.a.a r1 = com.inmobi.commons.core.a.a.a()
            com.inmobi.commons.core.e.a r2 = new com.inmobi.commons.core.e.a
            r2.<init>(r0)
            r1.a(r2)
            r15 = r21
        L_0x084e:
            if (r15 == 0) goto L_0x08a8
            r1 = r44
            r15.n = r1
            r1 = r31
            r15.o = r1
            r1 = r32
            r15.p = r1
            r1 = r43
            r15.q = r1
            if (r1 == 0) goto L_0x0870
            int r0 = r1.length()
            if (r0 == 0) goto L_0x0870
            java.util.Map<java.lang.String, java.lang.String> r0 = r7.p
            r2 = r50
            r0.put(r2, r1)
            goto L_0x0872
        L_0x0870:
            r2 = r50
        L_0x0872:
            int r0 = r2.length()
            if (r0 == 0) goto L_0x0885
            java.util.Map<java.lang.String, com.inmobi.ads.ak> r0 = r7.o
            boolean r0 = r0.containsKey(r2)
            if (r0 != 0) goto L_0x0885
            java.util.Map<java.lang.String, com.inmobi.ads.ak> r0 = r7.o
            r0.put(r2, r15)
        L_0x0885:
            java.util.Map<java.lang.String, java.util.List<com.inmobi.ads.ak>> r0 = r7.h
            r1 = r48
            boolean r0 = r0.containsKey(r1)
            if (r0 == 0) goto L_0x089b
            java.util.Map<java.lang.String, java.util.List<com.inmobi.ads.ak>> r0 = r7.h
            java.lang.Object r0 = r0.get(r1)
            java.util.List r0 = (java.util.List) r0
            r0.add(r15)
            goto L_0x08a8
        L_0x089b:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r0.add(r15)
            java.util.Map<java.lang.String, java.util.List<com.inmobi.ads.ak>> r2 = r7.h
            r2.put(r1, r0)
        L_0x08a8:
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.ao.a(org.json.JSONObject, java.lang.String, java.lang.String, com.inmobi.ads.al):com.inmobi.ads.ak");
    }

    private static void a(@NonNull ak akVar, @NonNull JSONObject jSONObject) throws JSONException {
        String str = "";
        String str2 = "";
        boolean z = false;
        if (p(jSONObject)) {
            if (jSONObject.getJSONObject("assetOnclick").isNull("itemUrl")) {
                new StringBuilder("Missing itemUrl on asset ").append(jSONObject.toString());
            } else {
                str = jSONObject.getJSONObject("assetOnclick").getString("itemUrl");
                z = true;
            }
            if (!jSONObject.getJSONObject("assetOnclick").isNull("action")) {
                str2 = jSONObject.getJSONObject("assetOnclick").getString("action");
                z = true;
            }
        }
        akVar.a(str);
        akVar.j = str2;
        akVar.h = z;
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public final ak b(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        if (this.o.get(str) != null) {
            return (ak) this.o.get(str);
        }
        ao aoVar = this.f;
        if (aoVar != null) {
            return (ak) aoVar.o.get(str);
        }
        return null;
    }

    public final List<ak> c(String str) {
        if (this.h.containsKey(str)) {
            return (List) this.h.get(str);
        }
        return Collections.emptyList();
    }

    private static boolean a(JSONObject jSONObject, String str) {
        if (jSONObject.isNull("geometry")) {
            return false;
        }
        try {
            if (!a(jSONObject.getJSONArray("geometry"))) {
                return false;
            }
            char c2 = 65535;
            switch (str.hashCode()) {
                case -1919329183:
                    if (str.equals("CONTAINER")) {
                        c2 = 1;
                        break;
                    }
                    break;
                case 67056:
                    if (str.equals("CTA")) {
                        c2 = 9;
                        break;
                    }
                    break;
                case 70564:
                    if (str.equals("GIF")) {
                        c2 = 7;
                        break;
                    }
                    break;
                case 2241657:
                    if (str.equals("ICON")) {
                        c2 = 2;
                        break;
                    }
                    break;
                case 2571565:
                    if (str.equals("TEXT")) {
                        c2 = 8;
                        break;
                    }
                    break;
                case 69775675:
                    if (str.equals(ShareConstants.IMAGE_URL)) {
                        c2 = 3;
                        break;
                    }
                    break;
                case 79826725:
                    if (str.equals("TIMER")) {
                        c2 = 5;
                        break;
                    }
                    break;
                case 81665115:
                    if (str.equals(ShareConstants.VIDEO_URL)) {
                        c2 = 4;
                        break;
                    }
                    break;
                case 1942407129:
                    if (str.equals("WEBVIEW")) {
                        c2 = 6;
                        break;
                    }
                    break;
            }
            switch (c2) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                    return true;
                case 8:
                case 9:
                    if (jSONObject.isNull("text")) {
                        return false;
                    }
                    try {
                        if (((int) Double.parseDouble(jSONObject.getJSONObject("text").getString(AbstractEvent.SIZE))) > 0) {
                            return true;
                        }
                        return false;
                    } catch (NumberFormatException e2) {
                        com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
                        return false;
                    }
                default:
                    return false;
            }
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            return false;
        } catch (JSONException e3) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e3));
            return false;
        }
    }

    private static boolean a(JSONArray jSONArray) {
        try {
            int i2 = jSONArray.getInt(2);
            int i3 = jSONArray.getInt(3);
            if (i2 <= 0 || i3 <= 0) {
                return false;
            }
            return true;
        } catch (JSONException e2) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
            return false;
        }
    }

    private static NativeTracker a(int i2, TrackerEventType trackerEventType, JSONObject jSONObject) throws JSONException {
        String trim = jSONObject.isNull("url") ? "" : jSONObject.getString("url").trim();
        HashMap hashMap = new HashMap();
        if (TrackerEventType.TRACKER_EVENT_TYPE_VIDEO_RENDER == trackerEventType) {
            JSONArray optJSONArray = jSONObject.optJSONArray(Constants.VIDEO_TRACKING_EVENTS_KEY);
            if ((trim.length() == 0 || ((trim.startsWith(Constants.HTTP) && !URLUtil.isValidUrl(trim)) || !trim.startsWith(Constants.HTTP))) && optJSONArray == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            if (optJSONArray != null) {
                for (int i3 = 0; i3 < optJSONArray.length(); i3++) {
                    TrackerEventType a2 = NativeTracker.a(optJSONArray.getString(i3));
                    if (a2 == TrackerEventType.TRACKER_EVENT_TYPE_CREATIVE_VIEW || a2 == TrackerEventType.TRACKER_EVENT_TYPE_PLAY || a2 == TrackerEventType.TRACKER_EVENT_TYPE_RENDER) {
                        arrayList.add(a2);
                    }
                }
            }
            hashMap.put("referencedEvents", arrayList);
        } else if (trim.length() == 0 || !URLUtil.isValidUrl(trim)) {
            return null;
        }
        HashMap hashMap2 = new HashMap();
        try {
            if (!jSONObject.isNull(NativeProtocol.WEB_DIALOG_PARAMS)) {
                JSONObject jSONObject2 = jSONObject.getJSONObject(NativeProtocol.WEB_DIALOG_PARAMS);
                Iterator keys = jSONObject2.keys();
                while (keys.hasNext()) {
                    String str = (String) keys.next();
                    hashMap2.put(str, jSONObject2.getString(str));
                }
            }
        } catch (JSONException e2) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
        }
        NativeTracker nativeTracker = new NativeTracker(trim, i2, trackerEventType, hashMap2);
        nativeTracker.d = new HashMap(hashMap);
        return nativeTracker;
    }

    private static List<NativeTracker> a(JSONObject jSONObject) {
        LinkedList linkedList = new LinkedList();
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("passThroughJson");
            HashMap hashMap = new HashMap();
            if (!jSONObject2.isNull("macros")) {
                JSONObject jSONObject3 = jSONObject2.getJSONObject("macros");
                Iterator keys = jSONObject3.keys();
                while (keys.hasNext()) {
                    String str = (String) keys.next();
                    hashMap.put(str, jSONObject3.getString(str));
                }
            }
            if (!jSONObject2.isNull(Constants.VIDEO_TRACKING_URLS_KEY)) {
                JSONArray jSONArray = jSONObject2.getJSONArray(Constants.VIDEO_TRACKING_URLS_KEY);
                int length = jSONArray.length();
                for (int i2 = 0; i2 < length; i2++) {
                    linkedList.add(new NativeTracker(jSONArray.getString(i2), 0, TrackerEventType.TRACKER_EVENT_TYPE_IAS, hashMap));
                }
            }
            if (linkedList.isEmpty()) {
                linkedList.add(new NativeTracker("", 0, TrackerEventType.TRACKER_EVENT_TYPE_IAS, hashMap));
            }
        } catch (Exception e2) {
            new StringBuilder("Failed to parse IAS tracker : ").append(e2.getMessage());
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
        }
        return linkedList;
    }

    /* JADX WARNING: Removed duplicated region for block: B:100:0x0172 A[Catch:{ JSONException -> 0x019e }] */
    /* JADX WARNING: Removed duplicated region for block: B:101:0x0175 A[Catch:{ JSONException -> 0x019e }] */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x0178 A[Catch:{ JSONException -> 0x019e }] */
    /* JADX WARNING: Removed duplicated region for block: B:103:0x017b A[Catch:{ JSONException -> 0x019e }] */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x017e A[Catch:{ JSONException -> 0x019e }] */
    /* JADX WARNING: Removed duplicated region for block: B:121:0x0199 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0076 A[Catch:{ JSONException -> 0x019e }] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0079 A[Catch:{ JSONException -> 0x019e }] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x007c A[Catch:{ JSONException -> 0x019e }] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x007f A[Catch:{ JSONException -> 0x019e }] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0089 A[Catch:{ JSONException -> 0x019e }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.util.List<com.inmobi.ads.NativeTracker> b(@android.support.annotation.NonNull org.json.JSONObject r14) {
        /*
            java.lang.String r0 = "trackers"
            boolean r0 = r14.isNull(r0)
            if (r0 == 0) goto L_0x000a
            r14 = 0
            return r14
        L_0x000a:
            java.util.LinkedList r0 = new java.util.LinkedList
            r0.<init>()
            java.lang.String r1 = "trackers"
            org.json.JSONArray r14 = r14.getJSONArray(r1)     // Catch:{ JSONException -> 0x019e }
            int r1 = r14.length()     // Catch:{ JSONException -> 0x019e }
            if (r1 != 0) goto L_0x001c
            return r0
        L_0x001c:
            r2 = 0
            r3 = 0
        L_0x001e:
            if (r3 >= r1) goto L_0x019d
            org.json.JSONObject r4 = r14.getJSONObject(r3)     // Catch:{ JSONException -> 0x019e }
            java.lang.String r5 = "trackerType"
            boolean r5 = r4.isNull(r5)     // Catch:{ JSONException -> 0x019e }
            if (r5 != 0) goto L_0x0199
            java.lang.String r5 = "trackerType"
            java.lang.String r5 = r4.getString(r5)     // Catch:{ JSONException -> 0x019e }
            java.util.Locale r6 = java.util.Locale.US     // Catch:{ JSONException -> 0x019e }
            java.lang.String r5 = r5.toUpperCase(r6)     // Catch:{ JSONException -> 0x019e }
            java.lang.String r5 = r5.trim()     // Catch:{ JSONException -> 0x019e }
            int r6 = r5.hashCode()     // Catch:{ JSONException -> 0x019e }
            r7 = -1430070305(0xffffffffaac2dbdf, float:-3.461389E-13)
            r8 = 2
            r9 = 3
            r10 = -1
            r11 = 1
            if (r6 == r7) goto L_0x0068
            r7 = -158113182(0xfffffffff6936262, float:-1.4946545E33)
            if (r6 == r7) goto L_0x005e
            r7 = 1110926088(0x42376308, float:45.84671)
            if (r6 == r7) goto L_0x0054
            goto L_0x0072
        L_0x0054:
            java.lang.String r6 = "URL_WEBVIEW_PING"
            boolean r5 = r5.equals(r6)     // Catch:{ JSONException -> 0x019e }
            if (r5 == 0) goto L_0x0072
            r5 = 2
            goto L_0x0073
        L_0x005e:
            java.lang.String r6 = "URL_PING"
            boolean r5 = r5.equals(r6)     // Catch:{ JSONException -> 0x019e }
            if (r5 == 0) goto L_0x0072
            r5 = 1
            goto L_0x0073
        L_0x0068:
            java.lang.String r6 = "HTML_SCRIPT"
            boolean r5 = r5.equals(r6)     // Catch:{ JSONException -> 0x019e }
            if (r5 == 0) goto L_0x0072
            r5 = 3
            goto L_0x0073
        L_0x0072:
            r5 = -1
        L_0x0073:
            switch(r5) {
                case 1: goto L_0x007f;
                case 2: goto L_0x007c;
                case 3: goto L_0x0079;
                default: goto L_0x0076;
            }     // Catch:{ JSONException -> 0x019e }
        L_0x0076:
            java.lang.String r5 = "unknown"
            goto L_0x0081
        L_0x0079:
            java.lang.String r5 = "html_script"
            goto L_0x0081
        L_0x007c:
            java.lang.String r5 = "webview_ping"
            goto L_0x0081
        L_0x007f:
            java.lang.String r5 = "url_ping"
        L_0x0081:
            java.lang.String r6 = "url_ping"
            boolean r5 = r6.equals(r5)     // Catch:{ JSONException -> 0x019e }
            if (r5 == 0) goto L_0x0199
            java.lang.String r5 = "eventId"
            int r5 = r4.optInt(r5, r2)     // Catch:{ JSONException -> 0x019e }
            java.lang.String r6 = "uiEvent"
            boolean r6 = r4.isNull(r6)     // Catch:{ JSONException -> 0x019e }
            if (r6 != 0) goto L_0x0199
            java.lang.String r6 = "uiEvent"
            java.lang.String r6 = r4.getString(r6)     // Catch:{ JSONException -> 0x019e }
            java.util.Locale r7 = java.util.Locale.US     // Catch:{ JSONException -> 0x019e }
            java.lang.String r7 = r6.toUpperCase(r7)     // Catch:{ JSONException -> 0x019e }
            java.lang.String r7 = r7.trim()     // Catch:{ JSONException -> 0x019e }
            int r12 = r7.hashCode()     // Catch:{ JSONException -> 0x019e }
            r13 = 4
            switch(r12) {
                case -1881262698: goto L_0x00f7;
                case -825499301: goto L_0x00ec;
                case -45894975: goto L_0x00e2;
                case 2342118: goto L_0x00d8;
                case 2634405: goto L_0x00ce;
                case 64212328: goto L_0x00c4;
                case 1963885793: goto L_0x00ba;
                case 2008409463: goto L_0x00b0;
                default: goto L_0x00af;
            }     // Catch:{ JSONException -> 0x019e }
        L_0x00af:
            goto L_0x0101
        L_0x00b0:
            java.lang.String r12 = "CLIENT_FILL"
            boolean r7 = r7.equals(r12)     // Catch:{ JSONException -> 0x019e }
            if (r7 == 0) goto L_0x0101
            r7 = 2
            goto L_0x0102
        L_0x00ba:
            java.lang.String r12 = "VIDEO_VIEWABILITY"
            boolean r7 = r7.equals(r12)     // Catch:{ JSONException -> 0x019e }
            if (r7 == 0) goto L_0x0101
            r7 = 6
            goto L_0x0102
        L_0x00c4:
            java.lang.String r12 = "CLICK"
            boolean r7 = r7.equals(r12)     // Catch:{ JSONException -> 0x019e }
            if (r7 == 0) goto L_0x0101
            r7 = 5
            goto L_0x0102
        L_0x00ce:
            java.lang.String r12 = "VIEW"
            boolean r7 = r7.equals(r12)     // Catch:{ JSONException -> 0x019e }
            if (r7 == 0) goto L_0x0101
            r7 = 4
            goto L_0x0102
        L_0x00d8:
            java.lang.String r12 = "LOAD"
            boolean r7 = r7.equals(r12)     // Catch:{ JSONException -> 0x019e }
            if (r7 == 0) goto L_0x0101
            r7 = 1
            goto L_0x0102
        L_0x00e2:
            java.lang.String r12 = "IAS_VIEWABILITY"
            boolean r7 = r7.equals(r12)     // Catch:{ JSONException -> 0x019e }
            if (r7 == 0) goto L_0x0101
            r7 = 7
            goto L_0x0102
        L_0x00ec:
            java.lang.String r12 = "FALLBACK_URL_CLICK"
            boolean r7 = r7.equals(r12)     // Catch:{ JSONException -> 0x019e }
            if (r7 == 0) goto L_0x0101
            r7 = 8
            goto L_0x0102
        L_0x00f7:
            java.lang.String r12 = "RENDER"
            boolean r7 = r7.equals(r12)     // Catch:{ JSONException -> 0x019e }
            if (r7 == 0) goto L_0x0101
            r7 = 3
            goto L_0x0102
        L_0x0101:
            r7 = -1
        L_0x0102:
            switch(r7) {
                case 1: goto L_0x0123;
                case 2: goto L_0x0120;
                case 3: goto L_0x011c;
                case 4: goto L_0x0118;
                case 5: goto L_0x0114;
                case 6: goto L_0x0110;
                case 7: goto L_0x010c;
                case 8: goto L_0x0108;
                default: goto L_0x0105;
            }     // Catch:{ JSONException -> 0x019e }
        L_0x0105:
            java.util.Locale r7 = java.util.Locale.US     // Catch:{ JSONException -> 0x019e }
            goto L_0x0126
        L_0x0108:
            com.inmobi.ads.NativeTracker$TrackerEventType r6 = com.inmobi.ads.NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_FALLBACK_URL     // Catch:{ JSONException -> 0x019e }
            goto L_0x0180
        L_0x010c:
            com.inmobi.ads.NativeTracker$TrackerEventType r6 = com.inmobi.ads.NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_IAS     // Catch:{ JSONException -> 0x019e }
            goto L_0x0180
        L_0x0110:
            com.inmobi.ads.NativeTracker$TrackerEventType r6 = com.inmobi.ads.NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_VIDEO_RENDER     // Catch:{ JSONException -> 0x019e }
            goto L_0x0180
        L_0x0114:
            com.inmobi.ads.NativeTracker$TrackerEventType r6 = com.inmobi.ads.NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_CLICK     // Catch:{ JSONException -> 0x019e }
            goto L_0x0180
        L_0x0118:
            com.inmobi.ads.NativeTracker$TrackerEventType r6 = com.inmobi.ads.NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_PAGE_VIEW     // Catch:{ JSONException -> 0x019e }
            goto L_0x0180
        L_0x011c:
            com.inmobi.ads.NativeTracker$TrackerEventType r6 = com.inmobi.ads.NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_RENDER     // Catch:{ JSONException -> 0x019e }
            goto L_0x0180
        L_0x0120:
            com.inmobi.ads.NativeTracker$TrackerEventType r6 = com.inmobi.ads.NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_CLIENT_FILL     // Catch:{ JSONException -> 0x019e }
            goto L_0x0180
        L_0x0123:
            com.inmobi.ads.NativeTracker$TrackerEventType r6 = com.inmobi.ads.NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_LOAD     // Catch:{ JSONException -> 0x019e }
            goto L_0x0180
        L_0x0126:
            java.lang.String r6 = r6.toUpperCase(r7)     // Catch:{ JSONException -> 0x019e }
            java.lang.String r6 = r6.trim()     // Catch:{ JSONException -> 0x019e }
            int r7 = r6.hashCode()     // Catch:{ JSONException -> 0x019e }
            r12 = -1836567951(0xffffffff92883271, float:-8.595241E-28)
            if (r7 == r12) goto L_0x0164
            r9 = -1099027408(0xffffffffbe7e2c30, float:-0.24821544)
            if (r7 == r9) goto L_0x015b
            r8 = 1331888222(0x4f63005e, float:3.8084521E9)
            if (r7 == r8) goto L_0x0151
            r8 = 1346121898(0x503c30aa, float:1.26292234E10)
            if (r7 == r8) goto L_0x0147
            goto L_0x016e
        L_0x0147:
            java.lang.String r7 = "DOWNLOADER_INITIALIZED"
            boolean r6 = r6.equals(r7)     // Catch:{ JSONException -> 0x019e }
            if (r6 == 0) goto L_0x016e
            r8 = 1
            goto L_0x016f
        L_0x0151:
            java.lang.String r7 = "DOWNLOADER_ERROR"
            boolean r6 = r6.equals(r7)     // Catch:{ JSONException -> 0x019e }
            if (r6 == 0) goto L_0x016e
            r8 = 4
            goto L_0x016f
        L_0x015b:
            java.lang.String r7 = "DOWNLOADER_DOWNLOADING"
            boolean r6 = r6.equals(r7)     // Catch:{ JSONException -> 0x019e }
            if (r6 == 0) goto L_0x016e
            goto L_0x016f
        L_0x0164:
            java.lang.String r7 = "DOWNLOADER_DOWNLOADED"
            boolean r6 = r6.equals(r7)     // Catch:{ JSONException -> 0x019e }
            if (r6 == 0) goto L_0x016e
            r8 = 3
            goto L_0x016f
        L_0x016e:
            r8 = -1
        L_0x016f:
            switch(r8) {
                case 1: goto L_0x017e;
                case 2: goto L_0x017b;
                case 3: goto L_0x0178;
                case 4: goto L_0x0175;
                default: goto L_0x0172;
            }     // Catch:{ JSONException -> 0x019e }
        L_0x0172:
            com.inmobi.ads.NativeTracker$TrackerEventType r6 = com.inmobi.ads.NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_UNKNOWN     // Catch:{ JSONException -> 0x019e }
            goto L_0x0180
        L_0x0175:
            com.inmobi.ads.NativeTracker$TrackerEventType r6 = com.inmobi.ads.NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_DOWNLOADER_ERROR     // Catch:{ JSONException -> 0x019e }
            goto L_0x0180
        L_0x0178:
            com.inmobi.ads.NativeTracker$TrackerEventType r6 = com.inmobi.ads.NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_DOWNLOADER_DOWNLOADED     // Catch:{ JSONException -> 0x019e }
            goto L_0x0180
        L_0x017b:
            com.inmobi.ads.NativeTracker$TrackerEventType r6 = com.inmobi.ads.NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_DOWNLOADER_DOWNLOADING     // Catch:{ JSONException -> 0x019e }
            goto L_0x0180
        L_0x017e:
            com.inmobi.ads.NativeTracker$TrackerEventType r6 = com.inmobi.ads.NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_DOWNLOADER_INIT     // Catch:{ JSONException -> 0x019e }
        L_0x0180:
            com.inmobi.ads.NativeTracker$TrackerEventType r7 = com.inmobi.ads.NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_UNKNOWN     // Catch:{ JSONException -> 0x019e }
            if (r7 == r6) goto L_0x0199
            com.inmobi.ads.NativeTracker$TrackerEventType r7 = com.inmobi.ads.NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_IAS     // Catch:{ JSONException -> 0x019e }
            if (r7 == r6) goto L_0x0192
            com.inmobi.ads.NativeTracker r4 = a(r5, r6, r4)     // Catch:{ JSONException -> 0x019e }
            if (r4 == 0) goto L_0x0199
            r0.add(r4)     // Catch:{ JSONException -> 0x019e }
            goto L_0x0199
        L_0x0192:
            java.util.List r4 = a(r4)     // Catch:{ JSONException -> 0x019e }
            r0.addAll(r4)     // Catch:{ JSONException -> 0x019e }
        L_0x0199:
            int r3 = r3 + 1
            goto L_0x001e
        L_0x019d:
            return r0
        L_0x019e:
            r14 = move-exception
            com.inmobi.commons.core.a.a r1 = com.inmobi.commons.core.a.a.a()
            com.inmobi.commons.core.e.a r2 = new com.inmobi.commons.core.e.a
            r2.<init>(r14)
            r1.a(r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.ao.b(org.json.JSONObject):java.util.List");
    }

    @Nullable
    private by a(@NonNull JSONObject jSONObject, @NonNull String str, ak akVar) {
        if (f(jSONObject).equalsIgnoreCase(ShareConstants.VIDEO_URL)) {
            try {
                JSONArray jSONArray = jSONObject.getJSONArray("assetValue");
                if (jSONArray != null) {
                    if (jSONArray.length() != 0) {
                        if (akVar == null || !(akVar instanceof be)) {
                            return new bu(this.r.m).a(str);
                        }
                        return (by) akVar.e;
                    }
                }
                return null;
            } catch (JSONException e2) {
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
            }
        }
        return null;
    }

    private static String c(@NonNull JSONObject jSONObject) {
        try {
            if ((f(jSONObject).equalsIgnoreCase("ICON") || f(jSONObject).equalsIgnoreCase(ShareConstants.IMAGE_URL) || f(jSONObject).equalsIgnoreCase("GIF")) && jSONObject.getJSONArray("assetValue").getString(0).length() != 0) {
                return jSONObject.getJSONArray("assetValue").getString(0);
            }
        } catch (JSONException e2) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
        }
        return "";
    }

    private static String d(@NonNull JSONObject jSONObject) {
        try {
            return jSONObject.getString("assetId");
        } catch (JSONException e2) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
            return Integer.toString(jSONObject.hashCode());
        }
    }

    private static String e(@NonNull JSONObject jSONObject) {
        try {
            return jSONObject.getString("assetName");
        } catch (JSONException e2) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
            return "";
        }
    }

    private static String f(@NonNull JSONObject jSONObject) {
        try {
            return jSONObject.getString("assetType");
        } catch (JSONException e2) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
            return "";
        }
    }

    private static String g(@NonNull JSONObject jSONObject) {
        try {
            return jSONObject.getString("valueType");
        } catch (JSONException e2) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
            return "";
        }
    }

    private static String h(@NonNull JSONObject jSONObject) {
        try {
            return jSONObject.getString("dataType");
        } catch (JSONException e2) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
            return "";
        }
    }

    @NonNull
    private JSONObject i(@NonNull JSONObject jSONObject) {
        JSONObject jSONObject2;
        try {
            if (jSONObject.isNull("assetStyle")) {
                jSONObject2 = null;
            } else {
                jSONObject2 = jSONObject.getJSONObject("assetStyle");
            }
            if (jSONObject2 == null) {
                if (jSONObject.isNull("assetStyleRef")) {
                    return new JSONObject();
                }
                String string = jSONObject.getString("assetStyleRef");
                if (this.n == null) {
                    jSONObject2 = new JSONObject();
                } else {
                    jSONObject2 = this.n.getJSONObject(string);
                }
            }
            return jSONObject2;
        } catch (JSONException e2) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
            return new JSONObject();
        }
    }

    private Point j(@NonNull JSONObject jSONObject) {
        Point point = new Point();
        try {
            JSONObject i2 = i(jSONObject);
            if (i2.isNull("geometry")) {
                return point;
            }
            JSONArray jSONArray = i2.getJSONArray("geometry");
            point.x = c.a(jSONArray.getInt(0));
            point.y = c.a(jSONArray.getInt(1));
            return point;
        } catch (JSONException e2) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
        }
    }

    private Point a(@NonNull JSONObject jSONObject, @NonNull Point point) {
        Point point2;
        try {
            JSONObject i2 = i(jSONObject);
            if (i2.isNull("finalGeometry")) {
                return point;
            }
            point2 = new Point();
            try {
                JSONArray jSONArray = i2.getJSONArray("finalGeometry");
                point2.x = c.a(jSONArray.getInt(0));
                point2.y = c.a(jSONArray.getInt(1));
            } catch (JSONException unused) {
            }
            return point2;
        } catch (JSONException unused2) {
            point2 = null;
        }
    }

    private Point k(@NonNull JSONObject jSONObject) {
        Point point = new Point();
        try {
            JSONObject i2 = i(jSONObject);
            if (i2.isNull("geometry")) {
                return point;
            }
            JSONArray jSONArray = i2.getJSONArray("geometry");
            point.x = c.a(jSONArray.getInt(2));
            point.y = c.a(jSONArray.getInt(3));
            return point;
        } catch (JSONException e2) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
        }
    }

    private Point b(@NonNull JSONObject jSONObject, @NonNull Point point) {
        Point point2;
        try {
            JSONObject i2 = i(jSONObject);
            if (i2.isNull("finalGeometry")) {
                return point;
            }
            point2 = new Point();
            try {
                JSONArray jSONArray = i2.getJSONArray("finalGeometry");
                point2.x = c.a(jSONArray.getInt(2));
                point2.y = c.a(jSONArray.getInt(3));
            } catch (JSONException unused) {
            }
            return point2;
        } catch (JSONException unused2) {
            point2 = null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0056 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0057  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0059 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int l(@android.support.annotation.NonNull org.json.JSONObject r6) {
        /*
            r0 = 2
            org.json.JSONObject r6 = n(r6)     // Catch:{ JSONException -> 0x005a }
            java.lang.String r1 = "type"
            boolean r1 = r6.isNull(r1)     // Catch:{ JSONException -> 0x005a }
            if (r1 == 0) goto L_0x000e
            return r0
        L_0x000e:
            java.lang.String r1 = "type"
            java.lang.String r6 = r6.getString(r1)     // Catch:{ JSONException -> 0x005a }
            java.lang.String r6 = r6.trim()     // Catch:{ JSONException -> 0x005a }
            java.util.Locale r1 = java.util.Locale.US     // Catch:{ JSONException -> 0x005a }
            java.lang.String r6 = r6.toLowerCase(r1)     // Catch:{ JSONException -> 0x005a }
            r1 = -1
            int r2 = r6.hashCode()     // Catch:{ JSONException -> 0x005a }
            r3 = -921832806(0xffffffffc90df29a, float:-581417.6)
            r4 = 3
            r5 = 1
            if (r2 == r3) goto L_0x0048
            r3 = -284840886(0xffffffffef05ac4a, float:-4.136979E28)
            if (r2 == r3) goto L_0x003e
            r3 = 1728122231(0x67010d77, float:6.0943366E23)
            if (r2 == r3) goto L_0x0035
            goto L_0x0052
        L_0x0035:
            java.lang.String r2 = "absolute"
            boolean r6 = r6.equals(r2)     // Catch:{ JSONException -> 0x005a }
            if (r6 == 0) goto L_0x0052
            goto L_0x0053
        L_0x003e:
            java.lang.String r2 = "unknown"
            boolean r6 = r6.equals(r2)     // Catch:{ JSONException -> 0x005a }
            if (r6 == 0) goto L_0x0052
            r0 = 1
            goto L_0x0053
        L_0x0048:
            java.lang.String r2 = "percentage"
            boolean r6 = r6.equals(r2)     // Catch:{ JSONException -> 0x005a }
            if (r6 == 0) goto L_0x0052
            r0 = 3
            goto L_0x0053
        L_0x0052:
            r0 = -1
        L_0x0053:
            switch(r0) {
                case 2: goto L_0x0059;
                case 3: goto L_0x0057;
                default: goto L_0x0056;
            }
        L_0x0056:
            return r5
        L_0x0057:
            r6 = 4
            return r6
        L_0x0059:
            return r4
        L_0x005a:
            r6 = move-exception
            com.inmobi.commons.core.a.a r1 = com.inmobi.commons.core.a.a.a()
            com.inmobi.commons.core.e.a r2 = new com.inmobi.commons.core.e.a
            r2.<init>(r6)
            r1.a(r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.ao.l(org.json.JSONObject):int");
    }

    private static int a(@NonNull JSONObject jSONObject, boolean z) {
        try {
            JSONObject n2 = n(jSONObject);
            if (n2.isNull(z ? "delay" : "hideAfterDelay")) {
                return -1;
            }
            int i2 = n2.getInt(z ? "delay" : "hideAfterDelay");
            if (3 == l(jSONObject)) {
                return i2;
            }
            if (4 != l(jSONObject) || i2 <= 0 || i2 > 100) {
                return -1;
            }
            int[] iArr = {25, 50, 75, 100};
            double d2 = Double.MAX_VALUE;
            int i3 = -1;
            for (int i4 = 0; i4 < 4; i4++) {
                int i5 = i2 - iArr[i4];
                double d3 = (double) (i5 * i5);
                if (d3 < d2) {
                    i3 = i4;
                    d2 = d3;
                }
            }
            return iArr[i3];
        } catch (JSONException e2) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
            return -1;
        }
    }

    private static String m(@NonNull JSONObject jSONObject) {
        try {
            JSONObject n2 = n(jSONObject);
            if (n2.isNull("reference")) {
                return "";
            }
            return n2.getString("reference");
        } catch (JSONException e2) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
            return "";
        }
    }

    private static JSONObject n(@NonNull JSONObject jSONObject) {
        if (jSONObject.isNull("display")) {
            return new JSONObject();
        }
        try {
            return jSONObject.getJSONObject("display");
        } catch (JSONException e2) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
            return new JSONObject();
        }
    }

    private static JSONArray o(@NonNull JSONObject jSONObject) {
        try {
            return jSONObject.getJSONArray("assetValue");
        } catch (JSONException e2) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
            return new JSONArray();
        }
    }

    private static boolean p(@NonNull JSONObject jSONObject) {
        return !jSONObject.isNull("assetOnclick");
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0053 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0054 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0055 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0056 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int d(@android.support.annotation.NonNull java.lang.String r6) {
        /*
            java.util.Locale r0 = java.util.Locale.US
            java.lang.String r6 = r6.toUpperCase(r0)
            java.lang.String r6 = r6.trim()
            int r0 = r6.hashCode()
            r1 = -2084521848(0xffffffff83c0b888, float:-1.1327112E-36)
            r2 = 4
            r3 = 3
            r4 = 1
            r5 = 2
            if (r0 == r1) goto L_0x0045
            r1 = -1038134325(0xffffffffc21f53cb, float:-39.83183)
            if (r0 == r1) goto L_0x003b
            r1 = 69805756(0x42926bc, float:1.988364E-36)
            if (r0 == r1) goto L_0x0031
            r1 = 1411860198(0x542746e6, float:2.87379607E12)
            if (r0 == r1) goto L_0x0027
            goto L_0x004f
        L_0x0027:
            java.lang.String r0 = "DEEPLINK"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x004f
            r6 = 3
            goto L_0x0050
        L_0x0031:
            java.lang.String r0 = "INAPP"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x004f
            r6 = 2
            goto L_0x0050
        L_0x003b:
            java.lang.String r0 = "EXTERNAL"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x004f
            r6 = 1
            goto L_0x0050
        L_0x0045:
            java.lang.String r0 = "DOWNLOAD"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x004f
            r6 = 4
            goto L_0x0050
        L_0x004f:
            r6 = -1
        L_0x0050:
            switch(r6) {
                case 2: goto L_0x0056;
                case 3: goto L_0x0055;
                case 4: goto L_0x0054;
                default: goto L_0x0053;
            }
        L_0x0053:
            return r5
        L_0x0054:
            return r2
        L_0x0055:
            return r3
        L_0x0056:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.ao.d(java.lang.String):int");
    }

    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String e(@android.support.annotation.NonNull java.lang.String r1) {
        /*
            java.util.Locale r0 = java.util.Locale.US
            java.lang.String r1 = r1.toLowerCase(r0)
            java.lang.String r1 = r1.trim()
            int r0 = r1.hashCode()
            switch(r0) {
                case -1178781136: goto L_0x003a;
                case -1026963764: goto L_0x0030;
                case -891985998: goto L_0x0026;
                case 3029637: goto L_0x001c;
                case 3387192: goto L_0x0012;
                default: goto L_0x0011;
            }
        L_0x0011:
            goto L_0x0044
        L_0x0012:
            java.lang.String r0 = "none"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0044
            r1 = 1
            goto L_0x0045
        L_0x001c:
            java.lang.String r0 = "bold"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0044
            r1 = 2
            goto L_0x0045
        L_0x0026:
            java.lang.String r0 = "strike"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0044
            r1 = 4
            goto L_0x0045
        L_0x0030:
            java.lang.String r0 = "underline"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0044
            r1 = 5
            goto L_0x0045
        L_0x003a:
            java.lang.String r0 = "italic"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0044
            r1 = 3
            goto L_0x0045
        L_0x0044:
            r1 = -1
        L_0x0045:
            switch(r1) {
                case 2: goto L_0x0054;
                case 3: goto L_0x0051;
                case 4: goto L_0x004e;
                case 5: goto L_0x004b;
                default: goto L_0x0048;
            }
        L_0x0048:
            java.lang.String r1 = "none"
            return r1
        L_0x004b:
            java.lang.String r1 = "underline"
            return r1
        L_0x004e:
            java.lang.String r1 = "strike"
            return r1
        L_0x0051:
            java.lang.String r1 = "italic"
            return r1
        L_0x0054:
            java.lang.String r1 = "bold"
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.ao.e(java.lang.String):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0031  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0034  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String f(@android.support.annotation.NonNull java.lang.String r3) {
        /*
            java.util.Locale r0 = java.util.Locale.US
            java.lang.String r3 = r3.toLowerCase(r0)
            java.lang.String r3 = r3.trim()
            int r0 = r3.hashCode()
            r1 = 3321844(0x32aff4, float:4.654895E-39)
            r2 = 2
            if (r0 == r1) goto L_0x0024
            r1 = 3387192(0x33af38, float:4.746467E-39)
            if (r0 == r1) goto L_0x001a
            goto L_0x002e
        L_0x001a:
            java.lang.String r0 = "none"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x002e
            r3 = 1
            goto L_0x002f
        L_0x0024:
            java.lang.String r0 = "line"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x002e
            r3 = 2
            goto L_0x002f
        L_0x002e:
            r3 = -1
        L_0x002f:
            if (r3 == r2) goto L_0x0034
            java.lang.String r3 = "none"
            return r3
        L_0x0034:
            java.lang.String r3 = "line"
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.ao.f(java.lang.String):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0031  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0034  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String g(@android.support.annotation.NonNull java.lang.String r3) {
        /*
            java.util.Locale r0 = java.util.Locale.US
            java.lang.String r3 = r3.toLowerCase(r0)
            java.lang.String r3 = r3.trim()
            int r0 = r3.hashCode()
            r1 = -1349116587(0xffffffffaf961d55, float:-2.7305683E-10)
            r2 = 2
            if (r0 == r1) goto L_0x0024
            r1 = 1787472634(0x6a8aaafa, float:8.381959E25)
            if (r0 == r1) goto L_0x001a
            goto L_0x002e
        L_0x001a:
            java.lang.String r0 = "straight"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x002e
            r3 = 1
            goto L_0x002f
        L_0x0024:
            java.lang.String r0 = "curved"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x002e
            r3 = 2
            goto L_0x002f
        L_0x002e:
            r3 = -1
        L_0x002f:
            if (r3 == r2) goto L_0x0034
            java.lang.String r3 = "straight"
            return r3
        L_0x0034:
            java.lang.String r3 = "curved"
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.ao.g(java.lang.String):java.lang.String");
    }

    private com.inmobi.ads.ba.a q(JSONObject jSONObject) {
        com.inmobi.ads.ba.a aVar = new com.inmobi.ads.ba.a(jSONObject.optLong(com.facebook.appevents.codeless.internal.Constants.PATH_TYPE_ABSOLUTE), jSONObject.optLong(MacroGoalFormat.PERCENTAGE), jSONObject.optString("reference"), this);
        return aVar;
    }

    private com.inmobi.ads.ba.a r(JSONObject jSONObject) {
        com.inmobi.ads.ba.a aVar = new com.inmobi.ads.ba.a(jSONObject.optLong(com.facebook.appevents.codeless.internal.Constants.PATH_TYPE_ABSOLUTE), jSONObject.optLong(MacroGoalFormat.PERCENTAGE), jSONObject.optString("reference"), this);
        return aVar;
    }

    private al a(@NonNull Point point, @NonNull Point point2, @NonNull Point point3, @NonNull Point point4, @NonNull JSONObject jSONObject) throws JSONException {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        ao aoVar;
        String str6;
        String str7;
        Point point5 = point;
        Point point6 = point2;
        Point point7 = point3;
        Point point8 = point4;
        JSONObject jSONObject2 = jSONObject;
        if (jSONObject2.isNull("border")) {
            str3 = "none";
            str2 = "straight";
            str = "#ff000000";
        } else {
            JSONObject jSONObject3 = jSONObject2.getJSONObject("border");
            if (jSONObject3.isNull("style")) {
                str3 = "none";
                str2 = "straight";
                str = "#ff000000";
            } else {
                String f2 = f(jSONObject3.getString("style"));
                if (jSONObject3.isNull("corner")) {
                    str7 = "straight";
                } else {
                    str7 = g(jSONObject3.getString("corner"));
                }
                if (jSONObject3.isNull("color")) {
                    str = "#ff000000";
                    str3 = f2;
                    str2 = str7;
                } else {
                    str = jSONObject3.getString("color").trim();
                    str3 = f2;
                    str2 = str7;
                }
            }
        }
        if (jSONObject2.isNull("backgroundColor")) {
            str4 = "#00000000";
        } else {
            str4 = jSONObject2.getString("backgroundColor").trim();
        }
        String str8 = "fill";
        if (!jSONObject2.isNull("contentMode")) {
            String trim = jSONObject2.getString("contentMode").trim();
            char c2 = 65535;
            int hashCode = trim.hashCode();
            if (hashCode != -1626174665) {
                if (hashCode != -1362001767) {
                    if (hashCode != 3143043) {
                        if (hashCode == 727618043 && trim.equals("aspectFill")) {
                            c2 = 3;
                        }
                    } else if (trim.equals("fill")) {
                        c2 = 2;
                    }
                } else if (trim.equals("aspectFit")) {
                    c2 = 4;
                }
            } else if (trim.equals(BuildConfig.VERSION_NAME)) {
                c2 = 1;
            }
            switch (c2) {
                case 2:
                    str6 = "fill";
                    break;
                case 3:
                    str6 = "aspectFill";
                    break;
                case 4:
                    str6 = "aspectFit";
                    break;
                default:
                    str6 = BuildConfig.VERSION_NAME;
                    break;
            }
            str5 = str6;
            aoVar = this;
        } else {
            str5 = str8;
            aoVar = this;
        }
        al alVar = new al(point5.x, point5.y, point6.x, point6.y, point7.x, point7.y, point8.x, point8.y, str5, str3, str2, str, str4, aoVar.s(jSONObject2));
        return alVar;
    }

    private ba s(JSONObject jSONObject) throws JSONException {
        com.inmobi.ads.ba.a aVar = null;
        com.inmobi.ads.ba.a r2 = !jSONObject.isNull("startOffset") ? r(jSONObject.getJSONObject("startOffset")) : null;
        if (!jSONObject.isNull("timerDuration")) {
            aVar = r(jSONObject.getJSONObject("timerDuration"));
        }
        return new ba(r2, aVar);
    }

    private a b(@NonNull Point point, @NonNull Point point2, @NonNull Point point3, @NonNull Point point4, @NonNull JSONObject jSONObject) throws JSONException {
        String str;
        String str2;
        String str3;
        String str4;
        int i2;
        String str5;
        String[] strArr;
        int i3;
        ao aoVar;
        String str6;
        Point point5 = point;
        Point point6 = point2;
        Point point7 = point3;
        Point point8 = point4;
        JSONObject jSONObject2 = jSONObject;
        if (jSONObject2.isNull("border")) {
            str3 = "none";
            str2 = "straight";
            str = "#ff000000";
        } else {
            JSONObject jSONObject3 = jSONObject2.getJSONObject("border");
            if (jSONObject3.isNull("style")) {
                str3 = "none";
                str2 = "straight";
                str = "#ff000000";
            } else {
                String f2 = f(jSONObject3.getString("style"));
                if (jSONObject3.isNull("corner")) {
                    str6 = "straight";
                } else {
                    str6 = g(jSONObject3.getString("corner"));
                }
                if (jSONObject3.isNull("color")) {
                    str = "#ff000000";
                    str3 = f2;
                    str2 = str6;
                } else {
                    str = jSONObject3.getString("color").trim();
                    str3 = f2;
                    str2 = str6;
                }
            }
        }
        if (jSONObject2.isNull("backgroundColor")) {
            str4 = "#00000000";
        } else {
            str4 = jSONObject2.getString("backgroundColor").trim();
        }
        JSONObject jSONObject4 = jSONObject2.getJSONObject("text");
        try {
            int parseDouble = (int) Double.parseDouble(jSONObject4.getString(AbstractEvent.SIZE));
            if (jSONObject4.isNull(VideoFields.DURATION)) {
                i2 = Integer.MAX_VALUE;
            } else {
                i2 = Integer.parseInt(jSONObject4.getString(VideoFields.DURATION));
            }
            if (jSONObject4.isNull("color")) {
                str5 = "#ff000000";
            } else {
                str5 = jSONObject4.getString("color").trim();
            }
            if (jSONObject4.isNull("style")) {
                strArr = new String[]{"none"};
            } else {
                int length = jSONObject4.getJSONArray("style").length();
                if (length == 0) {
                    strArr = new String[]{"none"};
                } else {
                    String[] strArr2 = new String[length];
                    for (int i4 = 0; i4 < length; i4++) {
                        strArr2[i4] = e(jSONObject4.getJSONArray("style").getString(i4));
                    }
                    strArr = strArr2;
                }
            }
            if (!jSONObject4.isNull("align")) {
                String trim = jSONObject4.getString("align").trim();
                char c2 = 65535;
                int hashCode = trim.hashCode();
                int i5 = 2;
                if (hashCode != -1364013605) {
                    if (hashCode != 3317767) {
                        if (hashCode == 108511772 && trim.equals(TtmlNode.RIGHT)) {
                            c2 = 2;
                        }
                    } else if (trim.equals(TtmlNode.LEFT)) {
                        c2 = 1;
                    }
                } else if (trim.equals("centre")) {
                    c2 = 3;
                }
                switch (c2) {
                    case 2:
                        i5 = 1;
                        break;
                    case 3:
                        break;
                    default:
                        i5 = 0;
                        break;
                }
                aoVar = this;
                i3 = i5;
            } else {
                i3 = 0;
                aoVar = this;
            }
            a aVar = new a(point5.x, point5.y, point6.x, point6.y, point7.x, point7.y, point8.x, point8.y, str3, str2, str, str4, parseDouble, i3, i2, str5, strArr, aoVar.s(jSONObject2));
            return aVar;
        } catch (NumberFormatException e2) {
            JSONException jSONException = new JSONException(e2.getMessage());
            jSONException.initCause(e2);
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
            throw jSONException;
        }
    }

    private a c(@NonNull Point point, @NonNull Point point2, @NonNull Point point3, @NonNull Point point4, @NonNull JSONObject jSONObject) throws JSONException {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String[] strArr;
        ao aoVar;
        String str6;
        Point point5 = point;
        Point point6 = point2;
        Point point7 = point3;
        Point point8 = point4;
        JSONObject jSONObject2 = jSONObject;
        if (jSONObject2.isNull("border")) {
            str3 = "none";
            str2 = "straight";
            str = "#ff000000";
        } else {
            JSONObject jSONObject3 = jSONObject2.getJSONObject("border");
            if (jSONObject3.isNull("style")) {
                str3 = "none";
                str2 = "straight";
                str = "#ff000000";
            } else {
                String f2 = f(jSONObject3.getString("style"));
                if (jSONObject3.isNull("corner")) {
                    str6 = "straight";
                } else {
                    str6 = g(jSONObject3.getString("corner"));
                }
                if (jSONObject3.isNull("color")) {
                    str = "#ff000000";
                    str3 = f2;
                    str2 = str6;
                } else {
                    str = jSONObject3.getString("color").trim();
                    str3 = f2;
                    str2 = str6;
                }
            }
        }
        if (jSONObject2.isNull("backgroundColor")) {
            str4 = "#00000000";
        } else {
            str4 = jSONObject2.getString("backgroundColor").trim();
        }
        JSONObject jSONObject4 = jSONObject2.getJSONObject("text");
        try {
            int parseDouble = (int) Double.parseDouble(jSONObject4.getString(AbstractEvent.SIZE));
            if (jSONObject4.isNull("color")) {
                str5 = "#ff000000";
            } else {
                str5 = jSONObject4.getString("color").trim();
            }
            if (jSONObject4.isNull("style")) {
                strArr = new String[]{"none"};
                aoVar = this;
            } else {
                int length = jSONObject4.getJSONArray("style").length();
                if (length == 0) {
                    strArr = new String[]{"none"};
                    aoVar = this;
                } else {
                    String[] strArr2 = new String[length];
                    for (int i2 = 0; i2 < length; i2++) {
                        strArr2[i2] = e(jSONObject4.getJSONArray("style").getString(i2));
                    }
                    aoVar = this;
                    strArr = strArr2;
                }
            }
            a aVar = new a(point5.x, point5.y, point6.x, point6.y, point7.x, point7.y, point8.x, point8.y, str3, str2, str, str4, parseDouble, str5, strArr, aoVar.s(jSONObject2));
            return aVar;
        } catch (NumberFormatException e2) {
            JSONException jSONException = new JSONException(e2.getMessage());
            jSONException.initCause(e2);
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
            throw jSONException;
        }
    }

    private static boolean a(@NonNull am amVar) {
        return "card_scrollable".equalsIgnoreCase(amVar.d);
    }

    private static void a(be beVar) {
        beVar.x = 8;
        HashMap hashMap = new HashMap();
        hashMap.put("[ERRORCODE]", "601");
        beVar.a(TrackerEventType.TRACKER_EVENT_TYPE_ERROR, (Map<String, String>) hashMap);
    }

    @Nullable
    private static String a(bt btVar, bf bfVar) {
        if ("REF_HTML".equals(bfVar.z)) {
            List a2 = btVar.a(2);
            if (a2.size() > 0) {
                return ((a) a2.get(0)).b;
            }
            List a3 = btVar.a(3);
            if (a3.size() > 0) {
                String str = ((a) a3.get(0)).b;
                if (URLUtil.isValidUrl(str)) {
                    bfVar.z = "REF_IFRAME";
                    return str;
                }
                a("MalformedURL", "Rich", "REF_HTML", (String) null, (String) null);
            }
        } else if ("REF_IFRAME".equals(bfVar.z)) {
            List a4 = btVar.a(3);
            if (a4.size() > 0) {
                String str2 = ((a) a4.get(0)).b;
                if (URLUtil.isValidUrl(str2)) {
                    bfVar.z = "REF_IFRAME";
                    return str2;
                }
                a("MalformedURL", "Rich", "REF_IFRAME", (String) null, (String) null);
            } else {
                List a5 = btVar.a(2);
                if (a5.size() > 0) {
                    bfVar.z = "REF_HTML";
                    return ((a) a5.get(0)).b;
                }
            }
        }
        return null;
    }

    private static ak a(@NonNull ao aoVar, @NonNull ak akVar) {
        while (true) {
            String str = (String) akVar.e;
            if (str == null || str.length() == 0) {
                return null;
            }
            String[] split = str.split("\\|");
            ak b2 = aoVar.b(split[0]);
            if (b2 == null) {
                aoVar = aoVar.f;
            } else if (b2.equals(akVar)) {
                return null;
            } else {
                if (1 == split.length) {
                    b2.m = 1;
                    return b2;
                }
                b2.m = a(split[1]);
                StringBuilder sb = new StringBuilder("Referenced asset (");
                sb.append(b2.d);
                sb.append(")");
                return b2;
            }
        }
        return null;
    }

    private static boolean a(al alVar, al alVar2) {
        if (alVar.a.x + alVar.c.x >= alVar2.a.x + alVar2.c.x && alVar.a.y + alVar.c.y >= alVar2.a.y + alVar2.c.y) {
            return true;
        }
        return false;
    }
}
