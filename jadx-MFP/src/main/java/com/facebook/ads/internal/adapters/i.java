package com.facebook.ads.internal.adapters;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import com.facebook.ads.internal.a.b;
import com.facebook.ads.internal.a.d;
import com.facebook.ads.internal.a.e.a;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.ads.internal.protocol.AdPlacementType;
import com.facebook.ads.internal.s.c;
import com.facebook.ads.internal.t.e;
import com.facebook.ads.internal.t.g;
import com.facebook.ads.internal.t.j;
import com.facebook.ads.internal.t.l;
import com.facebook.ads.internal.w.b.k;
import com.facebook.ads.internal.w.b.z;
import com.facebook.appevents.UserDataStore;
import com.google.android.gms.analytics.ecommerce.Promotion;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import org.json.JSONObject;

public class i implements a, AdAdapter {
    private static final String a = "i";
    private int A;
    /* access modifiers changed from: private */
    public String B;
    private boolean C;
    private boolean D;
    private boolean E;
    private boolean F;
    private boolean G;
    /* access modifiers changed from: private */
    @Nullable
    public c H;
    private e.c I;
    private Context b;
    private q c;
    private Uri d;
    private HashMap<String, String> e = new HashMap<>();
    private g f;
    private g g;
    private com.facebook.ads.internal.t.i h;
    private String i;
    private d j;
    private Collection<String> k;
    private boolean l;
    private boolean m;
    private int n;
    private int o;
    private int p;
    private int q;
    private String r;
    private String s;
    private l t;
    private int u = 200;
    private g v;
    private String w;
    private j x;
    private List<e> y;
    private int z = -1;

    private void C() {
        if (!this.G) {
            c cVar = this.H;
            if (cVar != null) {
                cVar.a(this.i);
            }
            this.G = true;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x00e5  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x012e  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0133  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x015c A[Catch:{ JSONException -> 0x0188 }, LOOP:1: B:47:0x015a->B:48:0x015c, LOOP_END] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(org.json.JSONObject r14, java.lang.String r15) {
        /*
            r13 = this;
            boolean r0 = r13.D
            if (r0 != 0) goto L_0x01d3
            if (r14 != 0) goto L_0x0007
            return
        L_0x0007:
            android.content.Context r0 = r13.b
            java.lang.String r1 = "Audience Network Loaded"
            com.facebook.ads.internal.w.b.c.a(r0, r1)
            r13.B = r15
            java.lang.String r0 = "fbad_command"
            java.lang.String r0 = com.facebook.ads.internal.w.b.k.a(r14, r0)
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            r2 = 0
            if (r1 == 0) goto L_0x001f
            r0 = r2
            goto L_0x0023
        L_0x001f:
            android.net.Uri r0 = android.net.Uri.parse(r0)
        L_0x0023:
            r13.d = r0
            java.lang.String r3 = "advertiser_name"
            java.lang.String r4 = "title"
            java.lang.String r5 = "subtitle"
            java.lang.String r6 = "headline"
            java.lang.String r7 = "body"
            java.lang.String r8 = "social_context"
            java.lang.String r9 = "link_description"
            java.lang.String r10 = "sponsored_translation"
            java.lang.String r11 = "ad_translation"
            java.lang.String r12 = "promoted_translation"
            java.lang.String[] r0 = new java.lang.String[]{r3, r4, r5, r6, r7, r8, r9, r10, r11, r12}
            int r1 = r0.length
            r3 = 0
            r4 = 0
        L_0x0040:
            if (r4 >= r1) goto L_0x0050
            r5 = r0[r4]
            java.util.HashMap<java.lang.String, java.lang.String> r6 = r13.e
            java.lang.String r7 = com.facebook.ads.internal.w.b.k.a(r14, r5)
            r6.put(r5, r7)
            int r4 = r4 + 1
            goto L_0x0040
        L_0x0050:
            java.lang.String r0 = "call_to_action"
            java.lang.String r0 = com.facebook.ads.internal.w.b.k.a(r14, r0)
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 != 0) goto L_0x0063
            java.util.HashMap<java.lang.String, java.lang.String> r1 = r13.e
            java.lang.String r4 = "call_to_action"
            r1.put(r4, r0)
        L_0x0063:
            java.lang.String r0 = "icon"
            org.json.JSONObject r0 = r14.optJSONObject(r0)
            com.facebook.ads.internal.t.g r0 = com.facebook.ads.internal.t.g.a(r0)
            r13.f = r0
            java.lang.String r0 = "image"
            org.json.JSONObject r0 = r14.optJSONObject(r0)
            com.facebook.ads.internal.t.g r0 = com.facebook.ads.internal.t.g.a(r0)
            r13.g = r0
            java.lang.String r0 = "star_rating"
            org.json.JSONObject r0 = r14.optJSONObject(r0)
            com.facebook.ads.internal.t.i r0 = com.facebook.ads.internal.t.i.a(r0)
            r13.h = r0
            java.lang.String r0 = "used_report_url"
            java.lang.String r0 = com.facebook.ads.internal.w.b.k.a(r14, r0)
            r13.i = r0
            java.lang.String r0 = "enable_view_log"
            boolean r0 = r14.optBoolean(r0)
            r13.l = r0
            java.lang.String r0 = "enable_snapshot_log"
            boolean r0 = r14.optBoolean(r0)
            r13.m = r0
            java.lang.String r0 = "snapshot_log_delay_second"
            r1 = 4
            int r0 = r14.optInt(r0, r1)
            r13.n = r0
            java.lang.String r0 = "snapshot_compress_quality"
            int r0 = r14.optInt(r0, r3)
            r13.o = r0
            java.lang.String r0 = "viewability_check_initial_delay"
            int r0 = r14.optInt(r0, r3)
            r13.p = r0
            java.lang.String r0 = "viewability_check_interval"
            r1 = 1000(0x3e8, float:1.401E-42)
            int r0 = r14.optInt(r0, r1)
            r13.q = r0
            java.lang.String r0 = "ad_choices_icon"
            org.json.JSONObject r0 = r14.optJSONObject(r0)
            java.lang.String r1 = "native_ui_config"
            org.json.JSONObject r1 = r14.optJSONObject(r1)
            if (r1 == 0) goto L_0x00dd
            int r4 = r1.length()     // Catch:{ JSONException -> 0x00e1 }
            if (r4 != 0) goto L_0x00d7
            goto L_0x00dd
        L_0x00d7:
            com.facebook.ads.internal.t.j r4 = new com.facebook.ads.internal.t.j     // Catch:{ JSONException -> 0x00e1 }
            r4.<init>(r1)     // Catch:{ JSONException -> 0x00e1 }
            goto L_0x00de
        L_0x00dd:
            r4 = r2
        L_0x00de:
            r13.x = r4     // Catch:{ JSONException -> 0x00e1 }
            goto L_0x00e3
        L_0x00e1:
            r13.x = r2
        L_0x00e3:
            if (r0 == 0) goto L_0x00eb
            com.facebook.ads.internal.t.g r0 = com.facebook.ads.internal.t.g.a(r0)
            r13.v = r0
        L_0x00eb:
            java.lang.String r0 = "ad_choices_link_url"
            java.lang.String r0 = com.facebook.ads.internal.w.b.k.a(r14, r0)
            r13.w = r0
            java.lang.String r0 = "invalidation_behavior"
            java.lang.String r0 = r14.optString(r0)
            com.facebook.ads.internal.a.d r0 = com.facebook.ads.internal.a.d.a(r0)
            r13.j = r0
            org.json.JSONArray r0 = new org.json.JSONArray     // Catch:{ JSONException -> 0x010b }
            java.lang.String r1 = "detection_strings"
            java.lang.String r1 = r14.optString(r1)     // Catch:{ JSONException -> 0x010b }
            r0.<init>(r1)     // Catch:{ JSONException -> 0x010b }
            goto L_0x0110
        L_0x010b:
            r0 = move-exception
            r0.printStackTrace()
            r0 = r2
        L_0x0110:
            java.util.Collection r0 = com.facebook.ads.internal.a.e.a(r0)
            r13.k = r0
            java.lang.String r0 = "video_url"
            java.lang.String r0 = com.facebook.ads.internal.w.b.k.a(r14, r0)
            r13.r = r0
            java.lang.String r0 = "video_mpd"
            java.lang.String r0 = com.facebook.ads.internal.w.b.k.a(r14, r0)
            r13.s = r0
            java.lang.String r0 = "video_autoplay_enabled"
            boolean r0 = r14.has(r0)
            if (r0 != 0) goto L_0x0133
            com.facebook.ads.internal.t.l r0 = com.facebook.ads.internal.t.l.DEFAULT
        L_0x0130:
            r13.t = r0
            goto L_0x0141
        L_0x0133:
            java.lang.String r0 = "video_autoplay_enabled"
            boolean r0 = r14.optBoolean(r0)
            if (r0 == 0) goto L_0x013e
            com.facebook.ads.internal.t.l r0 = com.facebook.ads.internal.t.l.ON
            goto L_0x0130
        L_0x013e:
            com.facebook.ads.internal.t.l r0 = com.facebook.ads.internal.t.l.OFF
            goto L_0x0130
        L_0x0141:
            r0 = 1
            java.lang.String r1 = "carousel"
            org.json.JSONArray r14 = r14.optJSONArray(r1)     // Catch:{ JSONException -> 0x0188 }
            if (r14 == 0) goto L_0x0190
            int r1 = r14.length()     // Catch:{ JSONException -> 0x0188 }
            if (r1 <= 0) goto L_0x0190
            int r1 = r14.length()     // Catch:{ JSONException -> 0x0188 }
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ JSONException -> 0x0188 }
            r4.<init>(r1)     // Catch:{ JSONException -> 0x0188 }
            r5 = 0
        L_0x015a:
            if (r5 >= r1) goto L_0x0185
            com.facebook.ads.internal.adapters.i r6 = new com.facebook.ads.internal.adapters.i     // Catch:{ JSONException -> 0x0188 }
            r6.<init>()     // Catch:{ JSONException -> 0x0188 }
            android.content.Context r7 = r13.b     // Catch:{ JSONException -> 0x0188 }
            org.json.JSONObject r8 = r14.getJSONObject(r5)     // Catch:{ JSONException -> 0x0188 }
            com.facebook.ads.internal.s.c r9 = r13.H     // Catch:{ JSONException -> 0x0188 }
            r6.C = r0     // Catch:{ JSONException -> 0x0188 }
            r6.b = r7     // Catch:{ JSONException -> 0x0188 }
            r6.H = r9     // Catch:{ JSONException -> 0x0188 }
            r6.z = r5     // Catch:{ JSONException -> 0x0188 }
            r6.A = r1     // Catch:{ JSONException -> 0x0188 }
            r6.a(r8, r15)     // Catch:{ JSONException -> 0x0188 }
            com.facebook.ads.internal.t.e r7 = new com.facebook.ads.internal.t.e     // Catch:{ JSONException -> 0x0188 }
            android.content.Context r8 = r13.b     // Catch:{ JSONException -> 0x0188 }
            com.facebook.ads.internal.t.e$c r9 = r13.I     // Catch:{ JSONException -> 0x0188 }
            r7.<init>(r8, r6, r2, r9)     // Catch:{ JSONException -> 0x0188 }
            r4.add(r7)     // Catch:{ JSONException -> 0x0188 }
            int r5 = r5 + 1
            goto L_0x015a
        L_0x0185:
            r13.y = r4     // Catch:{ JSONException -> 0x0188 }
            goto L_0x0190
        L_0x0188:
            r14 = move-exception
            java.lang.String r15 = a
            java.lang.String r1 = "Unable to parse carousel data."
            android.util.Log.e(r15, r1, r14)
        L_0x0190:
            r13.D = r0
            boolean r14 = r13.C
            if (r14 != 0) goto L_0x01a6
            java.util.HashMap<java.lang.String, java.lang.String> r14 = r13.e
            java.lang.String r15 = "advertiser_name"
            java.lang.Object r14 = r14.get(r15)
            java.lang.CharSequence r14 = (java.lang.CharSequence) r14
            boolean r14 = android.text.TextUtils.isEmpty(r14)
            if (r14 == 0) goto L_0x01ba
        L_0x01a6:
            java.util.HashMap<java.lang.String, java.lang.String> r14 = r13.e
            java.lang.String r15 = "title"
            java.lang.Object r14 = r14.get(r15)
            java.lang.CharSequence r14 = (java.lang.CharSequence) r14
            boolean r14 = android.text.TextUtils.isEmpty(r14)
            if (r14 != 0) goto L_0x01cf
            boolean r14 = r13.C
            if (r14 == 0) goto L_0x01cf
        L_0x01ba:
            com.facebook.ads.internal.t.g r14 = r13.f
            if (r14 != 0) goto L_0x01c2
            boolean r14 = r13.C
            if (r14 == 0) goto L_0x01cf
        L_0x01c2:
            com.facebook.ads.internal.t.g r14 = r13.g
            if (r14 != 0) goto L_0x01d0
            com.facebook.ads.internal.protocol.AdPlacementType r14 = r13.getPlacementType()
            com.facebook.ads.internal.protocol.AdPlacementType r15 = com.facebook.ads.internal.protocol.AdPlacementType.NATIVE_BANNER
            if (r14 != r15) goto L_0x01cf
            goto L_0x01d0
        L_0x01cf:
            r0 = 0
        L_0x01d0:
            r13.E = r0
            return
        L_0x01d3:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.String r15 = "Adapter already loaded data"
            r14.<init>(r15)
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.adapters.i.a(org.json.JSONObject, java.lang.String):void");
    }

    public boolean A() {
        return this.D && this.E;
    }

    public boolean B() {
        return this.C;
    }

    public d a() {
        return this.j;
    }

    @Nullable
    public String a(String str) {
        if (!A()) {
            return null;
        }
        C();
        return (String) this.e.get(str);
    }

    public void a(int i2) {
    }

    public void a(Context context, q qVar, c cVar, Map<String, Object> map, e.c cVar2) {
        this.b = context;
        this.c = qVar;
        this.H = cVar;
        this.I = cVar2;
        JSONObject jSONObject = (JSONObject) map.get("data");
        com.facebook.ads.internal.m.d dVar = (com.facebook.ads.internal.m.d) map.get("definition");
        this.u = dVar != null ? dVar.k() : 200;
        a(jSONObject, k.a(jSONObject, UserDataStore.CITY));
        if (com.facebook.ads.internal.a.e.a(context, this, cVar)) {
            qVar.a(this, new com.facebook.ads.internal.protocol.a(AdErrorType.NO_FILL, "No Fill"));
            return;
        }
        if (qVar != null) {
            qVar.a(this);
        }
    }

    public void a(View view, List<View> list) {
    }

    public void a(q qVar) {
        this.c = qVar;
    }

    public void a(Map<String, String> map) {
        if (A() && !this.F) {
            q qVar = this.c;
            if (qVar != null) {
                qVar.b(this);
            }
            final HashMap hashMap = new HashMap();
            if (map != null) {
                hashMap.putAll(map);
            }
            if (this.C) {
                hashMap.put("cardind", String.valueOf(this.z));
                hashMap.put("cardcnt", String.valueOf(this.A));
            }
            if (!TextUtils.isEmpty(getClientToken())) {
                c cVar = this.H;
                if (cVar != null) {
                    cVar.a(getClientToken(), hashMap);
                }
            }
            if (d() || h()) {
                try {
                    final HashMap hashMap2 = new HashMap();
                    if (map.containsKey(Promotion.ACTION_VIEW)) {
                        hashMap2.put(Promotion.ACTION_VIEW, map.get(Promotion.ACTION_VIEW));
                    }
                    if (map.containsKey("snapshot")) {
                        hashMap2.put("snapshot", map.get("snapshot"));
                    }
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            if (!TextUtils.isEmpty(i.this.B)) {
                                HashMap hashMap = new HashMap();
                                hashMap.putAll(hashMap);
                                hashMap.putAll(hashMap2);
                                if (i.this.H != null) {
                                    i.this.H.f(i.this.B, hashMap);
                                }
                            }
                        }
                    }, (long) (this.n * 1000));
                } catch (Exception unused) {
                }
            }
            this.F = true;
        }
    }

    public Collection<String> b() {
        return this.k;
    }

    public void b(Map<String, String> map) {
        c cVar = this.H;
        if (cVar != null) {
            cVar.k(this.B, map);
        }
    }

    public void c() {
        List<e> list = this.y;
        if (list != null && !list.isEmpty()) {
            for (e z2 : this.y) {
                z2.z();
            }
        }
    }

    public void c(Map<String, String> map) {
        c cVar = this.H;
        if (cVar != null) {
            cVar.j(this.B, map);
        }
    }

    public void d(Map<String, String> map) {
        c cVar = this.H;
        if (cVar != null) {
            cVar.i(this.B, map);
        }
    }

    public boolean d() {
        return A() && this.l;
    }

    public void e(Map<String, String> map) {
        if (A()) {
            if (!com.facebook.ads.internal.r.a.j(this.b) || !z.a(map)) {
                HashMap hashMap = new HashMap();
                if (map != null) {
                    hashMap.putAll(map);
                }
                com.facebook.ads.internal.w.b.c.a(this.b, "Click logged");
                q qVar = this.c;
                if (qVar != null) {
                    qVar.c(this);
                }
                if (this.C) {
                    hashMap.put("cardind", String.valueOf(this.z));
                    hashMap.put("cardcnt", String.valueOf(this.A));
                }
                b a2 = com.facebook.ads.internal.a.c.a(this.b, this.H, this.B, this.d, hashMap);
                if (a2 != null) {
                    try {
                        a2.a();
                    } catch (Exception e2) {
                        Log.e(a, "Error executing action", e2);
                    }
                }
                return;
            }
            Log.e(a, "Click happened on lockscreen ad");
        }
    }

    public boolean e() {
        return A() && this.x != null;
    }

    public boolean f() {
        return A() && this.d != null;
    }

    public boolean g() {
        return true;
    }

    public String getClientToken() {
        return this.B;
    }

    public AdPlacementType getPlacementType() {
        return AdPlacementType.NATIVE;
    }

    public boolean h() {
        return A() && this.m;
    }

    public int i() {
        int i2 = this.o;
        if (i2 < 0 || i2 > 100) {
            return 0;
        }
        return i2;
    }

    public int j() {
        return this.p;
    }

    public int k() {
        return this.q;
    }

    public g l() {
        if (!A()) {
            return null;
        }
        return this.f;
    }

    public g m() {
        if (!A()) {
            return null;
        }
        return this.g;
    }

    public j n() {
        if (!A()) {
            return null;
        }
        return this.x;
    }

    public String o() {
        StringBuilder sb;
        String str;
        if (!A()) {
            return null;
        }
        C();
        String str2 = (String) this.e.get("body");
        if (str2 != null) {
            StringTokenizer stringTokenizer = new StringTokenizer(str2, " ", true);
            if (str2.length() > 90 && (str2.length() > 93 || !str2.endsWith("..."))) {
                int i2 = 0;
                while (stringTokenizer.hasMoreTokens()) {
                    int length = stringTokenizer.nextToken().length() + i2;
                    if (length < 90) {
                        i2 = length;
                    }
                }
                if (i2 == 0) {
                    sb = new StringBuilder();
                    str = str2.substring(0, 90);
                } else {
                    sb = new StringBuilder();
                    str = str2.substring(0, i2);
                }
                sb.append(str);
                sb.append("...");
                str2 = sb.toString();
            }
        }
        return str2;
    }

    public void onDestroy() {
    }

    public com.facebook.ads.internal.t.i p() {
        if (!A()) {
            return null;
        }
        C();
        return this.h;
    }

    public g q() {
        if (!A()) {
            return null;
        }
        return this.v;
    }

    public String r() {
        if (!A()) {
            return null;
        }
        return this.w;
    }

    public String s() {
        if (!A()) {
            return null;
        }
        return "AdChoices";
    }

    public String t() {
        if (!A()) {
            return null;
        }
        return this.r;
    }

    public String u() {
        if (!A()) {
            return null;
        }
        return this.s;
    }

    public l v() {
        return !A() ? l.DEFAULT : this.t;
    }

    public int w() {
        return this.u;
    }

    public List<e> x() {
        if (!A()) {
            return null;
        }
        return this.y;
    }

    public int y() {
        return this.z;
    }

    public int z() {
        return this.A;
    }
}
