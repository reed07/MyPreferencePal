package com.facebook.ads.internal.a;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import java.util.Map;

public class c {
    private static final String a = "c";

    @Nullable
    public static b a(Context context, com.facebook.ads.internal.s.c cVar, String str, Uri uri, Map<String, String> map) {
        return a(context, cVar, str, uri, map, false, false);
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0066  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0085  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0091  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0099  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00a5  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00bf  */
    @android.support.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.facebook.ads.internal.a.b a(android.content.Context r10, com.facebook.ads.internal.s.c r11, java.lang.String r12, android.net.Uri r13, java.util.Map<java.lang.String, java.lang.String> r14, boolean r15, boolean r16) {
        /*
            r3 = r11
            r5 = r13
            r1 = 0
            if (r5 == 0) goto L_0x00cf
            java.lang.String r0 = r13.getAuthority()
            if (r0 != 0) goto L_0x000d
            goto L_0x00cf
        L_0x000d:
            java.lang.String r2 = r13.getAuthority()
            java.lang.String r0 = "video_url"
            java.lang.String r4 = r13.getQueryParameter(r0)
            java.lang.String r0 = "data"
            java.lang.String r0 = r13.getQueryParameter(r0)
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L_0x0053
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0049 }
            java.lang.String r6 = "data"
            java.lang.String r6 = r13.getQueryParameter(r6)     // Catch:{ JSONException -> 0x0049 }
            r0.<init>(r6)     // Catch:{ JSONException -> 0x0049 }
            java.util.Iterator r6 = r0.keys()     // Catch:{ JSONException -> 0x0049 }
        L_0x0032:
            boolean r7 = r6.hasNext()     // Catch:{ JSONException -> 0x0049 }
            if (r7 == 0) goto L_0x0053
            java.lang.Object r7 = r6.next()     // Catch:{ JSONException -> 0x0049 }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ JSONException -> 0x0049 }
            java.lang.String r8 = r0.getString(r7)     // Catch:{ JSONException -> 0x0049 }
            r9 = r14
            r14.put(r7, r8)     // Catch:{ JSONException -> 0x0047 }
            goto L_0x0032
        L_0x0047:
            r0 = move-exception
            goto L_0x004b
        L_0x0049:
            r0 = move-exception
            r9 = r14
        L_0x004b:
            java.lang.String r6 = a
            java.lang.String r7 = "Unable to parse json data in AdActionFactory."
            android.util.Log.w(r6, r7, r0)
            goto L_0x0054
        L_0x0053:
            r9 = r14
        L_0x0054:
            android.app.Activity r0 = com.facebook.ads.internal.w.a.b.a()
            com.facebook.ads.internal.a.m r7 = com.facebook.ads.internal.a.m.a(r11, r0)
            r0 = -1
            int r6 = r2.hashCode()
            r8 = -1458789996(0xffffffffa90ca194, float:-3.122639E-14)
            if (r6 == r8) goto L_0x0085
            r8 = 109770977(0x68af8e1, float:5.2275525E-35)
            if (r6 == r8) goto L_0x007b
            r8 = 1546100943(0x5c27a0cf, float:1.88732528E17)
            if (r6 == r8) goto L_0x0071
            goto L_0x008e
        L_0x0071:
            java.lang.String r6 = "open_link"
            boolean r2 = r2.equals(r6)
            if (r2 == 0) goto L_0x008e
            r0 = 1
            goto L_0x008e
        L_0x007b:
            java.lang.String r6 = "store"
            boolean r2 = r2.equals(r6)
            if (r2 == 0) goto L_0x008e
            r0 = 0
            goto L_0x008e
        L_0x0085:
            java.lang.String r6 = "passthrough"
            boolean r2 = r2.equals(r6)
            if (r2 == 0) goto L_0x008e
            r0 = 2
        L_0x008e:
            switch(r0) {
                case 0: goto L_0x00bf;
                case 1: goto L_0x00a5;
                case 2: goto L_0x0099;
                default: goto L_0x0091;
            }
        L_0x0091:
            com.facebook.ads.internal.a.l r0 = new com.facebook.ads.internal.a.l
            r1 = r10
            r2 = r12
            r0.<init>(r10, r11, r12, r13)
            return r0
        L_0x0099:
            com.facebook.ads.internal.a.k r0 = new com.facebook.ads.internal.a.k
            r1 = r0
            r2 = r10
            r3 = r11
            r4 = r12
            r5 = r13
            r6 = r14
            r1.<init>(r2, r3, r4, r5, r6)
            return r0
        L_0x00a5:
            if (r16 == 0) goto L_0x00b3
            com.facebook.ads.internal.a.i r0 = new com.facebook.ads.internal.a.i
            r1 = r0
            r2 = r10
            r3 = r11
            r4 = r12
            r5 = r13
            r6 = r14
            r1.<init>(r2, r3, r4, r5, r6)
            return r0
        L_0x00b3:
            com.facebook.ads.internal.a.j r0 = new com.facebook.ads.internal.a.j
            r1 = r0
            r2 = r10
            r3 = r11
            r4 = r12
            r5 = r13
            r6 = r14
            r1.<init>(r2, r3, r4, r5, r6, r7)
            return r0
        L_0x00bf:
            if (r4 == 0) goto L_0x00c2
            return r1
        L_0x00c2:
            com.facebook.ads.internal.a.f r0 = new com.facebook.ads.internal.a.f
            r1 = r0
            r2 = r10
            r3 = r11
            r4 = r12
            r5 = r13
            r6 = r14
            r8 = r15
            r1.<init>(r2, r3, r4, r5, r6, r7, r8)
            return r0
        L_0x00cf:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.a.c.a(android.content.Context, com.facebook.ads.internal.s.c, java.lang.String, android.net.Uri, java.util.Map, boolean, boolean):com.facebook.ads.internal.a.b");
    }

    public static boolean a(String str) {
        return "store".equalsIgnoreCase(str) || "open_link".equalsIgnoreCase(str);
    }
}
