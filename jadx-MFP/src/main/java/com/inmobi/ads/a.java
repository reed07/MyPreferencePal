package com.inmobi.ads;

import android.content.ContentValues;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.facebook.appevents.AppEventsConstants;
import com.inmobi.ads.InMobiAdRequest.MonetizationContext;
import com.inmobi.ads.c.k;
import com.mopub.mobileads.BaseVideoPlayerActivity;
import com.myfitnesspal.shared.service.device.UserAgentProviderImpl.Params;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: Ad */
public class a {
    /* access modifiers changed from: private */
    public static final String l = "a";
    final int a;
    final String b;
    final String c;
    final long d;
    long e;
    long f;
    public final String g;
    String h;
    String i;
    boolean j;
    public final float k;
    private final String m;
    private String n;
    private MonetizationContext o;
    @Nullable
    private final String p;

    /* renamed from: com.inmobi.ads.a$a reason: collision with other inner class name */
    /* compiled from: Ad */
    static final class C0040a {
        /* JADX WARNING: Removed duplicated region for block: B:11:0x0060 A[Catch:{ Exception -> 0x02c4 }] */
        /* JADX WARNING: Removed duplicated region for block: B:17:0x0070 A[Catch:{ Exception -> 0x02c4 }] */
        /* JADX WARNING: Removed duplicated region for block: B:21:0x007c A[Catch:{ Exception -> 0x02c4 }] */
        /* JADX WARNING: Removed duplicated region for block: B:23:0x007f A[Catch:{ Exception -> 0x02c4 }] */
        /* JADX WARNING: Removed duplicated region for block: B:88:0x02a5 A[Catch:{ Exception -> 0x02c2 }] */
        @android.support.annotation.Nullable
        /* Code decompiled incorrectly, please refer to instructions dump. */
        static com.inmobi.ads.a a(org.json.JSONObject r24, long r25, java.lang.String r27, java.lang.String r28, java.lang.String r29, com.inmobi.ads.InMobiAdRequest.MonetizationContext r30, com.inmobi.ads.r r31) {
            /*
                r0 = r24
                r1 = r31
                r15 = 0
                java.lang.String r2 = "markupType"
                java.lang.String r2 = r0.getString(r2)     // Catch:{ Exception -> 0x02c4 }
                java.lang.String r3 = "expiry"
                r4 = -1
                long r6 = r0.optLong(r3, r4)     // Catch:{ Exception -> 0x02c4 }
                int r3 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
                if (r3 == 0) goto L_0x0027
                r8 = 0
                int r3 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
                if (r3 > 0) goto L_0x001e
                goto L_0x0027
            L_0x001e:
                java.util.concurrent.TimeUnit r3 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ Exception -> 0x02c4 }
                long r3 = r3.toMillis(r6)     // Catch:{ Exception -> 0x02c4 }
                r17 = r3
                goto L_0x0029
            L_0x0027:
                r17 = r4
            L_0x0029:
                java.lang.String r3 = "impressionId"
                java.lang.String r8 = r0.getString(r3)     // Catch:{ Exception -> 0x02c4 }
                java.lang.String r3 = "bid"
                java.lang.String r3 = r0.getString(r3)     // Catch:{ Exception -> 0x02c4 }
                r4 = 0
                byte[] r3 = android.util.Base64.decode(r3, r4)     // Catch:{ Exception -> 0x02c4 }
                byte[] r5 = r1.b     // Catch:{ Exception -> 0x02c4 }
                byte[] r6 = r1.a     // Catch:{ Exception -> 0x02c4 }
                byte[] r3 = com.inmobi.commons.core.utilities.a.b.a(r3, r5, r6)     // Catch:{ Exception -> 0x02c4 }
                java.lang.String r3 = com.inmobi.commons.core.network.d.a(r3)     // Catch:{ Exception -> 0x02c4 }
                float r19 = java.lang.Float.parseFloat(r3)     // Catch:{ Exception -> 0x02c4 }
                java.lang.String r3 = "bidInfoEncrypted"
                java.lang.String r3 = r0.optString(r3, r15)     // Catch:{ Exception -> 0x02c4 }
                java.lang.String r20 = r1.a(r3)     // Catch:{ Exception -> 0x02c4 }
                r1 = -1
                int r3 = r2.hashCode()     // Catch:{ Exception -> 0x02c4 }
                r5 = -1084172778(0xffffffffbf60d616, float:-0.8782667)
                r6 = 1
                r7 = 2
                if (r3 == r5) goto L_0x0070
                r5 = 3213227(0x3107ab, float:4.50269E-39)
                if (r3 == r5) goto L_0x0066
                goto L_0x0079
            L_0x0066:
                java.lang.String r3 = "html"
                boolean r2 = r2.equals(r3)     // Catch:{ Exception -> 0x02c4 }
                if (r2 == 0) goto L_0x0079
                r1 = 1
                goto L_0x0079
            L_0x0070:
                java.lang.String r3 = "inmobiJson"
                boolean r2 = r2.equals(r3)     // Catch:{ Exception -> 0x02c4 }
                if (r2 == 0) goto L_0x0079
                r1 = 2
            L_0x0079:
                switch(r1) {
                    case 1: goto L_0x02a5;
                    case 2: goto L_0x007f;
                    default: goto L_0x007c;
                }     // Catch:{ Exception -> 0x02c4 }
            L_0x007c:
                r22 = r15
                return r22
            L_0x007f:
                org.json.JSONObject r1 = new org.json.JSONObject     // Catch:{ Exception -> 0x02c4 }
                java.lang.String r2 = "pubContent"
                java.lang.String r2 = r0.getString(r2)     // Catch:{ Exception -> 0x02c4 }
                r1.<init>(r2)     // Catch:{ Exception -> 0x02c4 }
                com.inmobi.ads.a.l     // Catch:{ Exception -> 0x02c4 }
                r1.toString()     // Catch:{ Exception -> 0x02c4 }
                java.lang.String r2 = "rootContainer"
                boolean r2 = r1.isNull(r2)     // Catch:{ Exception -> 0x02c4 }
                if (r2 == 0) goto L_0x00d1
                com.inmobi.ads.a.l     // Catch:{ Exception -> 0x02c4 }
                java.util.HashMap r0 = new java.util.HashMap     // Catch:{ Exception -> 0x00b9 }
                r0.<init>()     // Catch:{ Exception -> 0x00b9 }
                java.lang.String r1 = "errorCode"
                java.lang.String r2 = "MISSING rootContainer"
                r0.put(r1, r2)     // Catch:{ Exception -> 0x00b9 }
                java.lang.String r1 = "reason"
                java.lang.String r2 = "Missing rootContainer ad markup"
                r0.put(r1, r2)     // Catch:{ Exception -> 0x00b9 }
                com.inmobi.commons.core.e.b.a()     // Catch:{ Exception -> 0x00b9 }
                java.lang.String r1 = "ads"
                java.lang.String r2 = "ServerError"
                com.inmobi.commons.core.e.b.a(r1, r2, r0)     // Catch:{ Exception -> 0x00b9 }
                goto L_0x00d0
            L_0x00b9:
                r0 = move-exception
                com.inmobi.ads.a.l     // Catch:{ Exception -> 0x02c4 }
                java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x02c4 }
                java.lang.String r2 = "Error in submitting telemetry event : ("
                r1.<init>(r2)     // Catch:{ Exception -> 0x02c4 }
                java.lang.String r0 = r0.getMessage()     // Catch:{ Exception -> 0x02c4 }
                r1.append(r0)     // Catch:{ Exception -> 0x02c4 }
                java.lang.String r0 = ")"
                r1.append(r0)     // Catch:{ Exception -> 0x02c4 }
            L_0x00d0:
                return r15
            L_0x00d1:
                java.lang.String r2 = "rootContainer"
                org.json.JSONObject r2 = r1.getJSONObject(r2)     // Catch:{ Exception -> 0x02c4 }
                org.json.JSONArray r3 = new org.json.JSONArray     // Catch:{ Exception -> 0x02c4 }
                r3.<init>()     // Catch:{ Exception -> 0x02c4 }
                java.util.List r5 = c(r2)     // Catch:{ Exception -> 0x02c4 }
                java.util.Iterator r5 = r5.iterator()     // Catch:{ Exception -> 0x02c4 }
            L_0x00e4:
                boolean r9 = r5.hasNext()     // Catch:{ Exception -> 0x02c4 }
                if (r9 == 0) goto L_0x00f4
                java.lang.Object r9 = r5.next()     // Catch:{ Exception -> 0x02c4 }
                java.lang.String r9 = (java.lang.String) r9     // Catch:{ Exception -> 0x02c4 }
                com.inmobi.ads.a.a(r3, r9, r7)     // Catch:{ Exception -> 0x02c4 }
                goto L_0x00e4
            L_0x00f4:
                java.util.List r5 = d(r2)     // Catch:{ Exception -> 0x02c4 }
                java.util.Iterator r5 = r5.iterator()     // Catch:{ Exception -> 0x02c4 }
            L_0x00fc:
                boolean r9 = r5.hasNext()     // Catch:{ Exception -> 0x02c4 }
                if (r9 == 0) goto L_0x010c
                java.lang.Object r9 = r5.next()     // Catch:{ Exception -> 0x02c4 }
                java.lang.String r9 = (java.lang.String) r9     // Catch:{ Exception -> 0x02c4 }
                com.inmobi.ads.a.a(r3, r9, r6)     // Catch:{ Exception -> 0x02c4 }
                goto L_0x00fc
            L_0x010c:
                java.lang.String r5 = a(r2)     // Catch:{ Exception -> 0x02c4 }
                boolean r16 = b(r2)     // Catch:{ Exception -> 0x02c4 }
                java.lang.String r2 = r5.trim()     // Catch:{ Exception -> 0x02c4 }
                int r2 = r2.length()     // Catch:{ Exception -> 0x02c4 }
                if (r2 != 0) goto L_0x0141
                com.inmobi.ads.a.l     // Catch:{ Exception -> 0x02c4 }
                com.inmobi.ads.a r21 = new com.inmobi.ads.a     // Catch:{ Exception -> 0x02c4 }
                java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x02c4 }
                r1 = r21
                r2 = r24
                r4 = r25
                r6 = r27
                r7 = r28
                r9 = r29
                r10 = r30
                r11 = r16
                r12 = r17
                r14 = r19
                r15 = r20
                r1.<init>(r2, r3, r4, r6, r7, r8, r9, r10, r11, r12, r14, r15)     // Catch:{ Exception -> 0x02a1 }
                return r21
            L_0x0141:
                com.inmobi.ads.c r2 = new com.inmobi.ads.c     // Catch:{ Exception -> 0x02a1 }
                r2.<init>()     // Catch:{ Exception -> 0x02a1 }
                com.inmobi.commons.core.configs.b r9 = com.inmobi.commons.core.configs.b.a()     // Catch:{ Exception -> 0x02a1 }
                r15 = 0
                r9.a(r2, r15)     // Catch:{ Exception -> 0x02c4 }
                com.inmobi.ads.bu r9 = new com.inmobi.ads.bu     // Catch:{ Exception -> 0x02c4 }
                com.inmobi.ads.c$k r10 = r2.m     // Catch:{ Exception -> 0x02c4 }
                r9.<init>(r10)     // Catch:{ Exception -> 0x02c4 }
                com.inmobi.ads.bx r5 = r9.a(r5)     // Catch:{ Exception -> 0x02c4 }
                int r9 = r5.f     // Catch:{ Exception -> 0x02c4 }
                if (r9 == 0) goto L_0x0189
                com.inmobi.ads.a.l     // Catch:{ Exception -> 0x02c4 }
                java.util.HashMap r0 = new java.util.HashMap     // Catch:{ Exception -> 0x02c4 }
                r0.<init>()     // Catch:{ Exception -> 0x02c4 }
                java.lang.String r1 = "errorCode"
                int r2 = r5.f     // Catch:{ Exception -> 0x02c4 }
                java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ Exception -> 0x02c4 }
                r0.put(r1, r2)     // Catch:{ Exception -> 0x02c4 }
                java.lang.String r1 = "reason"
                java.lang.String r2 = "Processing VAST XML to build a video descriptor failed"
                r0.put(r1, r2)     // Catch:{ Exception -> 0x02c4 }
                java.lang.String r1 = "latency"
                java.lang.String r2 = "0"
                r0.put(r1, r2)     // Catch:{ Exception -> 0x02c4 }
                com.inmobi.commons.core.e.b.a()     // Catch:{ Exception -> 0x02c4 }
                java.lang.String r1 = "ads"
                java.lang.String r2 = "VastProcessingError"
                com.inmobi.commons.core.e.b.a(r1, r2, r0)     // Catch:{ Exception -> 0x02c4 }
                return r15
            L_0x0189:
                java.util.HashMap r9 = new java.util.HashMap     // Catch:{ Exception -> 0x02c4 }
                r9.<init>()     // Catch:{ Exception -> 0x02c4 }
                java.lang.String r10 = "message"
                java.lang.String r11 = "VAST PROCESSING SUCCESS"
                r9.put(r10, r11)     // Catch:{ Exception -> 0x02c4 }
                com.inmobi.commons.core.e.b.a()     // Catch:{ Exception -> 0x02c4 }
                java.lang.String r10 = "ads"
                java.lang.String r11 = "VastProcessingSuccess"
                com.inmobi.commons.core.e.b.a(r10, r11, r9)     // Catch:{ Exception -> 0x02c4 }
                java.util.List<com.inmobi.ads.NativeTracker> r9 = r5.d     // Catch:{ Exception -> 0x02c4 }
                org.json.JSONArray r10 = new org.json.JSONArray     // Catch:{ Exception -> 0x02c4 }
                r10.<init>()     // Catch:{ Exception -> 0x02c4 }
                java.util.Iterator r9 = r9.iterator()     // Catch:{ Exception -> 0x02c4 }
            L_0x01aa:
                boolean r11 = r9.hasNext()     // Catch:{ Exception -> 0x02c4 }
                if (r11 == 0) goto L_0x01be
                java.lang.Object r11 = r9.next()     // Catch:{ Exception -> 0x02c4 }
                com.inmobi.ads.NativeTracker r11 = (com.inmobi.ads.NativeTracker) r11     // Catch:{ Exception -> 0x02c4 }
                java.lang.String r11 = r11.toString()     // Catch:{ Exception -> 0x02c4 }
                r10.put(r11)     // Catch:{ Exception -> 0x02c4 }
                goto L_0x01aa
            L_0x01be:
                java.util.List<com.inmobi.ads.bt> r9 = r5.e     // Catch:{ Exception -> 0x02c4 }
                org.json.JSONArray r11 = new org.json.JSONArray     // Catch:{ Exception -> 0x02c4 }
                r11.<init>()     // Catch:{ Exception -> 0x02c4 }
                java.util.Iterator r9 = r9.iterator()     // Catch:{ Exception -> 0x02c4 }
            L_0x01c9:
                boolean r12 = r9.hasNext()     // Catch:{ Exception -> 0x02c4 }
                if (r12 == 0) goto L_0x01dd
                java.lang.Object r12 = r9.next()     // Catch:{ Exception -> 0x02c4 }
                com.inmobi.ads.bt r12 = (com.inmobi.ads.bt) r12     // Catch:{ Exception -> 0x02c4 }
                java.lang.String r12 = r12.toString()     // Catch:{ Exception -> 0x02c4 }
                r11.put(r12)     // Catch:{ Exception -> 0x02c4 }
                goto L_0x01c9
            L_0x01dd:
                java.lang.String r9 = r5.b()     // Catch:{ Exception -> 0x02c4 }
                if (r9 == 0) goto L_0x027e
                boolean r12 = r9.isEmpty()     // Catch:{ Exception -> 0x02c4 }
                if (r12 == 0) goto L_0x01eb
                goto L_0x027e
            L_0x01eb:
                com.inmobi.ads.a.a(r3, r9, r4)     // Catch:{ Exception -> 0x02c4 }
                com.inmobi.ads.c$k r2 = r2.m     // Catch:{ Exception -> 0x02c4 }
                java.util.List r2 = a(r1, r2)     // Catch:{ Exception -> 0x02c4 }
                com.inmobi.ads.a.l     // Catch:{ Exception -> 0x02c4 }
                java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x02c4 }
                java.lang.String r12 = "Media size for pages"
                r9.<init>(r12)     // Catch:{ Exception -> 0x02c4 }
                int r12 = r2.size()     // Catch:{ Exception -> 0x02c4 }
                r9.append(r12)     // Catch:{ Exception -> 0x02c4 }
                java.util.Iterator r2 = r2.iterator()     // Catch:{ Exception -> 0x02c4 }
            L_0x0209:
                boolean r9 = r2.hasNext()     // Catch:{ Exception -> 0x02c4 }
                if (r9 == 0) goto L_0x0219
                java.lang.Object r9 = r2.next()     // Catch:{ Exception -> 0x02c4 }
                java.lang.String r9 = (java.lang.String) r9     // Catch:{ Exception -> 0x02c4 }
                com.inmobi.ads.a.a(r3, r9, r4)     // Catch:{ Exception -> 0x02c4 }
                goto L_0x0209
            L_0x0219:
                java.lang.String r2 = "pages"
                java.util.List r2 = a(r1, r2)     // Catch:{ Exception -> 0x02c4 }
                java.util.Iterator r2 = r2.iterator()     // Catch:{ Exception -> 0x02c4 }
            L_0x0223:
                boolean r4 = r2.hasNext()     // Catch:{ Exception -> 0x02c4 }
                if (r4 == 0) goto L_0x0233
                java.lang.Object r4 = r2.next()     // Catch:{ Exception -> 0x02c4 }
                java.lang.String r4 = (java.lang.String) r4     // Catch:{ Exception -> 0x02c4 }
                com.inmobi.ads.a.a(r3, r4, r7)     // Catch:{ Exception -> 0x02c4 }
                goto L_0x0223
            L_0x0233:
                java.lang.String r2 = "pages"
                java.util.List r1 = b(r1, r2)     // Catch:{ Exception -> 0x02c4 }
                java.util.Iterator r1 = r1.iterator()     // Catch:{ Exception -> 0x02c4 }
            L_0x023d:
                boolean r2 = r1.hasNext()     // Catch:{ Exception -> 0x02c4 }
                if (r2 == 0) goto L_0x024d
                java.lang.Object r2 = r1.next()     // Catch:{ Exception -> 0x02c4 }
                java.lang.String r2 = (java.lang.String) r2     // Catch:{ Exception -> 0x02c4 }
                com.inmobi.ads.a.a(r3, r2, r6)     // Catch:{ Exception -> 0x02c4 }
                goto L_0x023d
            L_0x024d:
                com.inmobi.ads.bc r21 = new com.inmobi.ads.bc     // Catch:{ Exception -> 0x02c4 }
                java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x02c4 }
                java.lang.String r12 = r5.b()     // Catch:{ Exception -> 0x02c4 }
                java.lang.String r13 = r5.b     // Catch:{ Exception -> 0x02c4 }
                java.lang.String r14 = r5.c     // Catch:{ Exception -> 0x02c4 }
                java.lang.String r22 = r10.toString()     // Catch:{ Exception -> 0x02c4 }
                java.lang.String r23 = r11.toString()     // Catch:{ Exception -> 0x02c4 }
                r1 = r21
                r2 = r24
                r4 = r25
                r6 = r27
                r7 = r28
                r9 = r29
                r10 = r12
                r11 = r13
                r12 = r14
                r13 = r22
                r14 = r23
                r22 = r15
                r15 = r30
                r1.<init>(r2, r3, r4, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r19, r20)     // Catch:{ Exception -> 0x02c2 }
                return r21
            L_0x027e:
                r22 = r15
                java.util.HashMap r0 = new java.util.HashMap     // Catch:{ Exception -> 0x02c2 }
                r0.<init>()     // Catch:{ Exception -> 0x02c2 }
                java.lang.String r1 = "errorCode"
                java.lang.String r2 = "ZERO LENGTH ASSET"
                r0.put(r1, r2)     // Catch:{ Exception -> 0x02c2 }
                java.lang.String r1 = "reason"
                java.lang.String r2 = "Asset length is 0"
                r0.put(r1, r2)     // Catch:{ Exception -> 0x02c2 }
                com.inmobi.commons.core.e.b.a()     // Catch:{ Exception -> 0x02c2 }
                java.lang.String r1 = "ads"
                java.lang.String r2 = "ServerError"
                com.inmobi.commons.core.e.b.a(r1, r2, r0)     // Catch:{ Exception -> 0x02c2 }
                com.inmobi.ads.a.l     // Catch:{ Exception -> 0x02c2 }
                return r22
            L_0x02a1:
                r0 = move-exception
                r22 = 0
                goto L_0x02c7
            L_0x02a5:
                r22 = r15
                com.inmobi.ads.a r15 = new com.inmobi.ads.a     // Catch:{ Exception -> 0x02c2 }
                r14 = 0
                r1 = r15
                r2 = r24
                r3 = r25
                r5 = r27
                r6 = r28
                r7 = r8
                r8 = r29
                r9 = r30
                r10 = r17
                r12 = r19
                r13 = r20
                r1.<init>(r2, r3, r5, r6, r7, r8, r9, r10, r12, r13, r14)     // Catch:{ Exception -> 0x02c2 }
                return r15
            L_0x02c2:
                r0 = move-exception
                goto L_0x02c7
            L_0x02c4:
                r0 = move-exception
                r22 = r15
            L_0x02c7:
                com.inmobi.ads.a.l
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                java.lang.String r2 = "Error parsing ad markup; "
                r1.<init>(r2)
                java.lang.String r2 = r0.getMessage()
                r1.append(r2)
                com.inmobi.commons.core.a.a r1 = com.inmobi.commons.core.a.a.a()
                com.inmobi.commons.core.e.a r2 = new com.inmobi.commons.core.e.a
                r2.<init>(r0)
                r1.a(r2)
                return r22
            */
            throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.a.C0040a.a(org.json.JSONObject, long, java.lang.String, java.lang.String, java.lang.String, com.inmobi.ads.InMobiAdRequest$MonetizationContext, com.inmobi.ads.r):com.inmobi.ads.a");
        }

        private static List<String> a(JSONObject jSONObject, k kVar) {
            ArrayList arrayList = new ArrayList();
            try {
                JSONArray jSONArray = jSONObject.getJSONArray("pages");
                for (int i = 0; i < jSONArray.length(); i++) {
                    if (!jSONArray.getJSONObject(i).isNull("rootContainer")) {
                        String a = a(jSONArray.getJSONObject(i).getJSONObject("rootContainer"));
                        if (a.trim().length() == 0) {
                            a.l;
                        } else {
                            bx a2 = new bu(kVar).a(a);
                            if (a2 != null) {
                                if (a2.f == 0) {
                                    String b = a2.b();
                                    if (b != null && !b.isEmpty()) {
                                        arrayList.add(b);
                                    }
                                }
                            }
                            a.l;
                        }
                    }
                }
            } catch (JSONException unused) {
                a.l;
            }
            return arrayList;
        }

        private static List<String> a(JSONObject jSONObject, String str) {
            ArrayList arrayList = new ArrayList();
            try {
                JSONArray jSONArray = jSONObject.getJSONArray(str);
                for (int i = 0; i < jSONArray.length(); i++) {
                    if (!jSONArray.getJSONObject(i).isNull("rootContainer")) {
                        arrayList.addAll(c(jSONArray.getJSONObject(i).getJSONObject("rootContainer")));
                    }
                }
            } catch (JSONException unused) {
                a.l;
            }
            return arrayList;
        }

        private static List<String> b(JSONObject jSONObject, String str) {
            ArrayList arrayList = new ArrayList();
            try {
                JSONArray jSONArray = jSONObject.getJSONArray(str);
                for (int i = 0; i < jSONArray.length(); i++) {
                    if (!jSONArray.getJSONObject(i).isNull("rootContainer")) {
                        arrayList.addAll(d(jSONArray.getJSONObject(i).getJSONObject("rootContainer")));
                    }
                }
            } catch (JSONException unused) {
                a.l;
            }
            return arrayList;
        }

        static a a(ContentValues contentValues) {
            if (!contentValues.containsKey(BaseVideoPlayerActivity.VIDEO_URL) || contentValues.getAsString(BaseVideoPlayerActivity.VIDEO_URL) == null || contentValues.getAsString(BaseVideoPlayerActivity.VIDEO_URL).isEmpty()) {
                return new a(contentValues);
            }
            return new bc(contentValues);
        }

        @NonNull
        private static String a(@NonNull JSONObject jSONObject) {
            try {
                JSONArray jSONArray = jSONObject.getJSONArray("assetValue");
                if (jSONArray.length() == 0) {
                    return "";
                }
                String string = jSONObject.getString("assetType");
                if (string.equalsIgnoreCase("video")) {
                    return jSONArray.getString(0);
                }
                if (!string.equalsIgnoreCase("container")) {
                    return "";
                }
                String str = "";
                for (int i = 0; i < jSONArray.length(); i++) {
                    str = a(jSONArray.getJSONObject(i));
                    if (str.trim().length() != 0) {
                        break;
                    }
                }
                return str;
            } catch (JSONException e) {
                a.l;
                StringBuilder sb = new StringBuilder("Error getting VAST video XML (");
                sb.append(e.getMessage());
                sb.append(")");
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                return "";
            }
        }

        private static boolean b(@NonNull JSONObject jSONObject) {
            try {
                JSONArray jSONArray = jSONObject.getJSONArray("assetValue");
                if (jSONArray.length() == 0) {
                    return false;
                }
                String string = jSONObject.getString("assetType");
                if (string.equalsIgnoreCase("webview")) {
                    if (jSONObject.isNull(Params.PRELOAD) || !jSONObject.getBoolean(Params.PRELOAD)) {
                        return false;
                    }
                    return true;
                } else if (!string.equalsIgnoreCase("container")) {
                    return false;
                } else {
                    boolean z = false;
                    for (int i = 0; i < jSONArray.length(); i++) {
                        z = b(jSONArray.getJSONObject(i));
                        if (z) {
                            break;
                        }
                    }
                    return z;
                }
            } catch (JSONException e) {
                a.l;
                StringBuilder sb = new StringBuilder("Error getting preload webview flag (");
                sb.append(e.getMessage());
                sb.append(")");
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                return false;
            }
        }

        @NonNull
        private static List<String> c(@NonNull JSONObject jSONObject) {
            ArrayList arrayList = new ArrayList();
            try {
                JSONArray jSONArray = jSONObject.getJSONArray("assetValue");
                if (jSONArray.length() == 0) {
                    return arrayList;
                }
                String string = jSONObject.getString("assetType");
                if (string.equalsIgnoreCase("image")) {
                    if (!jSONObject.isNull(Params.PRELOAD) && jSONObject.getBoolean(Params.PRELOAD)) {
                        arrayList.add(jSONArray.getString(0));
                    }
                    return arrayList;
                } else if (!string.equalsIgnoreCase("container")) {
                    return arrayList;
                } else {
                    for (int i = 0; i < jSONArray.length(); i++) {
                        arrayList.addAll(c(jSONArray.getJSONObject(i)));
                    }
                    return arrayList;
                }
            } catch (JSONException e) {
                a.l;
                StringBuilder sb = new StringBuilder("Error getting getImageAssetUrls (");
                sb.append(e.getMessage());
                sb.append(")");
                return arrayList;
            }
        }

        @NonNull
        private static List<String> d(@NonNull JSONObject jSONObject) {
            ArrayList arrayList = new ArrayList();
            try {
                JSONArray jSONArray = jSONObject.getJSONArray("assetValue");
                if (jSONArray.length() == 0) {
                    return arrayList;
                }
                String string = jSONObject.getString("assetType");
                if (string.equalsIgnoreCase("gif")) {
                    arrayList.add(jSONArray.getString(0));
                    return arrayList;
                } else if (!string.equalsIgnoreCase("container")) {
                    return arrayList;
                } else {
                    for (int i = 0; i < jSONArray.length(); i++) {
                        arrayList.addAll(d(jSONArray.getJSONObject(i)));
                    }
                    return arrayList;
                }
            } catch (JSONException e) {
                a.l;
                StringBuilder sb = new StringBuilder("Error getting getGifAssetUrls (");
                sb.append(e.getMessage());
                sb.append(")");
                return arrayList;
            }
        }
    }

    /* synthetic */ a(JSONObject jSONObject, long j2, String str, String str2, String str3, String str4, MonetizationContext monetizationContext, long j3, float f2, String str5, byte b2) {
        this(jSONObject, j2, str, str2, str3, str4, monetizationContext, j3, f2, str5);
    }

    private a(JSONObject jSONObject, long j2, String str, String str2, String str3, String str4, MonetizationContext monetizationContext, long j3, float f2, @Nullable String str5) {
        this(jSONObject, null, j2, str, str2, str3, str4, monetizationContext, false, j3, f2, str5);
    }

    a(JSONObject jSONObject, String str, long j2, String str2, String str3, String str4, String str5, MonetizationContext monetizationContext, boolean z, long j3, float f2, @Nullable String str6) {
        this.a = -1;
        this.c = jSONObject.toString();
        this.n = str;
        this.d = j2;
        this.b = str2;
        this.m = str3;
        this.e = System.currentTimeMillis();
        this.g = str4;
        this.h = str5;
        this.o = monetizationContext;
        this.i = "";
        this.j = z;
        this.f = j3;
        this.k = f2;
        this.p = str6;
    }

    a(ContentValues contentValues) {
        this.a = contentValues.getAsInteger("id").intValue();
        this.b = contentValues.getAsString(AppEventsConstants.EVENT_PARAM_AD_TYPE);
        this.m = contentValues.getAsString("ad_size");
        this.n = contentValues.getAsString("asset_urls");
        this.c = contentValues.getAsString("ad_content");
        this.d = contentValues.getAsLong("placement_id").longValue();
        this.e = contentValues.getAsLong("insertion_ts").longValue();
        this.f = contentValues.getAsLong("expiry_duration").longValue();
        this.g = contentValues.getAsString("imp_id");
        this.h = contentValues.getAsString("client_request_id");
        this.o = MonetizationContext.a(contentValues.getAsString("m10_context"));
        if (this.o == null) {
            this.o = MonetizationContext.MONETIZATION_CONTEXT_ACTIVITY;
        }
        this.i = contentValues.getAsString("web_vast");
        this.j = contentValues.getAsInteger("preload_webView").intValue() != 0;
        this.k = contentValues.getAsFloat("bid").floatValue();
        this.p = contentValues.getAsString("bidInfo");
    }

    public ContentValues a() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(AppEventsConstants.EVENT_PARAM_AD_TYPE, this.b);
        contentValues.put("ad_size", this.m);
        contentValues.put("asset_urls", this.n);
        contentValues.put("ad_content", this.c);
        contentValues.put("placement_id", Long.valueOf(this.d));
        contentValues.put("insertion_ts", Long.valueOf(this.e));
        contentValues.put("expiry_duration", Long.valueOf(this.f));
        contentValues.put("imp_id", this.g);
        contentValues.put("client_request_id", this.h);
        contentValues.put("m10_context", this.o.a);
        String str = this.i;
        if (str != null) {
            contentValues.put("web_vast", str);
        }
        contentValues.put("preload_webView", Integer.valueOf(this.j ? 1 : 0));
        contentValues.put("bid", Float.valueOf(this.k));
        contentValues.put("bidInfo", this.p);
        return contentValues;
    }

    @NonNull
    public final JSONObject b() {
        try {
            return this.p == null ? new JSONObject() : new JSONObject(this.p);
        } catch (JSONException unused) {
            return new JSONObject();
        }
    }

    /* access modifiers changed from: 0000 */
    public final long c() {
        long j2 = this.f;
        if (j2 == -1) {
            return -1;
        }
        return this.e + j2;
    }

    @NonNull
    public final Set<bm> d() {
        HashSet hashSet = new HashSet();
        String str = this.n;
        if (str == null || str.length() == 0) {
            return hashSet;
        }
        try {
            JSONArray jSONArray = new JSONArray(this.n);
            if (jSONArray.length() == 0) {
                return hashSet;
            }
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                JSONObject jSONObject = new JSONObject(jSONArray.getString(i2));
                int i3 = jSONObject.getInt("type");
                String string = jSONObject.getString("url");
                if (string != null) {
                    hashSet.add(new bm(i3, string));
                }
            }
            return hashSet;
        } catch (JSONException e2) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
            return hashSet;
        }
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    public final String e() {
        try {
            JSONObject jSONObject = new JSONObject(this.c);
            if (jSONObject.isNull("markupType")) {
                return "";
            }
            return jSONObject.getString("markupType");
        } catch (JSONException e2) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
            return "";
        }
    }

    /* access modifiers changed from: 0000 */
    public final boolean a(@NonNull a aVar) {
        return d().equals(aVar.d());
    }

    @Nullable
    public final JSONObject f() {
        try {
            JSONObject jSONObject = new JSONObject(this.c);
            if (jSONObject.has("cachedAdData")) {
                return jSONObject.getJSONObject("cachedAdData");
            }
            return null;
        } catch (JSONException e2) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
            return null;
        }
    }

    static /* synthetic */ void a(JSONArray jSONArray, String str, int i2) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("type", i2);
        jSONObject.put("url", str);
        jSONArray.put(jSONObject);
    }
}
