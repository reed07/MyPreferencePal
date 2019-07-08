package com.inmobi.ads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.mopub.common.AdType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: VastCompanionAd */
public class bt {
    static final ArrayList<String> f = new ArrayList<>(Arrays.asList(new String[]{"image/jpeg", "image/png"}));
    /* access modifiers changed from: private */
    public static final String h = "bt";
    int a;
    int b;
    List<a> c = new ArrayList();
    List<NativeTracker> d = new ArrayList();
    String e;
    boolean g;
    @Nullable
    private String i;

    /* compiled from: VastCompanionAd */
    static final class a {
        int a;
        String b;

        a(int i, String str) {
            this.a = i;
            this.b = str;
        }

        /* JADX WARNING: Removed duplicated region for block: B:30:0x0065 A[Catch:{ JSONException -> 0x0077 }] */
        /* JADX WARNING: Removed duplicated region for block: B:31:0x0066 A[Catch:{ JSONException -> 0x0077 }] */
        /* JADX WARNING: Removed duplicated region for block: B:32:0x0068 A[Catch:{ JSONException -> 0x0077 }] */
        /* JADX WARNING: Removed duplicated region for block: B:33:0x006a A[Catch:{ JSONException -> 0x0077 }] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        static com.inmobi.ads.bt.a a(org.json.JSONObject r8) {
            /*
                java.lang.String r0 = "type"
                java.lang.String r0 = r8.getString(r0)     // Catch:{ JSONException -> 0x0077 }
                r1 = 3
                r2 = 2
                r3 = 1
                r4 = 0
                if (r0 == 0) goto L_0x006b
                java.lang.String r5 = r0.trim()     // Catch:{ JSONException -> 0x0077 }
                int r5 = r5.length()     // Catch:{ JSONException -> 0x0077 }
                if (r5 != 0) goto L_0x0018
                goto L_0x006b
            L_0x0018:
                java.util.Locale r5 = java.util.Locale.US     // Catch:{ JSONException -> 0x0077 }
                java.lang.String r0 = r0.toLowerCase(r5)     // Catch:{ JSONException -> 0x0077 }
                r5 = -1
                int r6 = r0.hashCode()     // Catch:{ JSONException -> 0x0077 }
                r7 = -1191214428(0xffffffffb8ff82a4, float:-1.2183681E-4)
                if (r6 == r7) goto L_0x0057
                r7 = -892481938(0xffffffffcacdce6e, float:-6743863.0)
                if (r6 == r7) goto L_0x004d
                r7 = -284840886(0xffffffffef05ac4a, float:-4.136979E28)
                if (r6 == r7) goto L_0x0042
                r7 = 3213227(0x3107ab, float:4.50269E-39)
                if (r6 == r7) goto L_0x0038
                goto L_0x0061
            L_0x0038:
                java.lang.String r6 = "html"
                boolean r0 = r0.equals(r6)     // Catch:{ JSONException -> 0x0077 }
                if (r0 == 0) goto L_0x0061
                r0 = 3
                goto L_0x0062
            L_0x0042:
                java.lang.String r6 = "unknown"
                boolean r0 = r0.equals(r6)     // Catch:{ JSONException -> 0x0077 }
                if (r0 == 0) goto L_0x0061
                r0 = 1
                goto L_0x0062
            L_0x004d:
                java.lang.String r6 = "static"
                boolean r0 = r0.equals(r6)     // Catch:{ JSONException -> 0x0077 }
                if (r0 == 0) goto L_0x0061
                r0 = 2
                goto L_0x0062
            L_0x0057:
                java.lang.String r6 = "iframe"
                boolean r0 = r0.equals(r6)     // Catch:{ JSONException -> 0x0077 }
                if (r0 == 0) goto L_0x0061
                r0 = 4
                goto L_0x0062
            L_0x0061:
                r0 = -1
            L_0x0062:
                switch(r0) {
                    case 2: goto L_0x006a;
                    case 3: goto L_0x0068;
                    case 4: goto L_0x0066;
                    default: goto L_0x0065;
                }     // Catch:{ JSONException -> 0x0077 }
            L_0x0065:
                goto L_0x006b
            L_0x0066:
                r4 = 3
                goto L_0x006b
            L_0x0068:
                r4 = 2
                goto L_0x006b
            L_0x006a:
                r4 = 1
            L_0x006b:
                java.lang.String r0 = "content"
                java.lang.String r8 = r8.getString(r0)     // Catch:{ JSONException -> 0x0077 }
                com.inmobi.ads.bt$a r0 = new com.inmobi.ads.bt$a     // Catch:{ JSONException -> 0x0077 }
                r0.<init>(r4, r8)     // Catch:{ JSONException -> 0x0077 }
                return r0
            L_0x0077:
                r8 = move-exception
                com.inmobi.ads.bt.h
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                java.lang.String r1 = "Error building resource from JSONObject; "
                r0.<init>(r1)
                java.lang.String r1 = r8.getMessage()
                r0.append(r1)
                com.inmobi.commons.core.a.a r0 = com.inmobi.commons.core.a.a.a()
                com.inmobi.commons.core.e.a r1 = new com.inmobi.commons.core.e.a
                r1.<init>(r8)
                r0.a(r1)
                r8 = 0
                return r8
            */
            throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.bt.a.a(org.json.JSONObject):com.inmobi.ads.bt$a");
        }

        public final String toString() {
            String str;
            JSONObject jSONObject = new JSONObject();
            String str2 = "type";
            try {
                switch (this.a) {
                    case 1:
                        str = "static";
                        break;
                    case 2:
                        str = AdType.HTML;
                        break;
                    case 3:
                        str = "iframe";
                        break;
                    default:
                        str = "unknown";
                        break;
                }
                jSONObject.put(str2, str);
                jSONObject.put(Param.CONTENT, this.b);
                return jSONObject.toString();
            } catch (JSONException e) {
                bt.h;
                new StringBuilder("Error serializing resource: ").append(e.getMessage());
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                return "";
            }
        }
    }

    public bt(int i2, int i3, String str, @Nullable String str2) {
        this.i = str2;
        this.a = i2;
        this.b = i3;
        this.e = str;
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    public final List<a> a(int i2) {
        ArrayList arrayList = new ArrayList();
        for (a aVar : this.c) {
            if (aVar.a == i2) {
                arrayList.add(aVar);
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: 0000 */
    public final void a(@NonNull a aVar) {
        this.c.add(aVar);
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    public final List<NativeTracker> a(TrackerEventType trackerEventType) {
        ArrayList arrayList = new ArrayList();
        for (NativeTracker nativeTracker : this.d) {
            if (nativeTracker.b.equals(trackerEventType)) {
                arrayList.add(nativeTracker);
            }
        }
        return arrayList;
    }

    public final void a(@NonNull NativeTracker nativeTracker) {
        this.d.add(nativeTracker);
    }

    @Nullable
    static bt a(JSONObject jSONObject) {
        try {
            bt btVar = new bt(jSONObject.getInt("width"), jSONObject.getInt("height"), jSONObject.has("clickThroughUrl") ? jSONObject.getString("clickThroughUrl") : null, jSONObject.optString("id", null));
            try {
                JSONArray jSONArray = new JSONArray(jSONObject.getString("trackers"));
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    NativeTracker a2 = NativeTracker.a(new JSONObject(jSONArray.getString(i2)));
                    if (a2 != null) {
                        btVar.a(a2);
                    }
                }
            } catch (JSONException e2) {
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
            }
            try {
                JSONArray jSONArray2 = new JSONArray(jSONObject.getString("resources"));
                for (int i3 = 0; i3 < jSONArray2.length(); i3++) {
                    a a3 = a.a(new JSONObject(jSONArray2.getString(i3)));
                    if (a3 != null) {
                        btVar.a(a3);
                    }
                }
            } catch (JSONException e3) {
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e3));
            }
            return btVar;
        } catch (JSONException e4) {
            new StringBuilder("Error building companion from JSON: ").append(e4.getMessage());
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e4));
            return null;
        }
    }

    public String toString() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (this.i != null) {
                jSONObject.put("id", this.i);
            }
            jSONObject.put("width", this.a);
            jSONObject.put("height", this.b);
            jSONObject.put("clickThroughUrl", this.e);
            JSONArray jSONArray = new JSONArray();
            for (a aVar : this.c) {
                jSONArray.put(aVar.toString());
            }
            jSONObject.put("resources", jSONArray);
            JSONArray jSONArray2 = new JSONArray();
            for (NativeTracker nativeTracker : this.d) {
                jSONArray2.put(nativeTracker.toString());
            }
            jSONObject.put("trackers", jSONArray2);
            return jSONObject.toString();
        } catch (JSONException e2) {
            StringBuilder sb = new StringBuilder("Error serializing an ");
            sb.append(h);
            sb.append(" instance: ");
            sb.append(e2.getMessage());
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
            return "";
        }
    }
}
