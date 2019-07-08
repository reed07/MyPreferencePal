package com.inmobi.ads;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.brightcove.player.event.AbstractEvent;
import com.facebook.appevents.AppEventsConstants;
import com.inmobi.ads.InMobiAdRequestStatus;
import com.inmobi.ads.InMobiAdRequestStatus.StatusCode;
import com.inmobi.ads.bi;
import com.inmobi.ads.c.d;
import com.inmobi.ads.cache.AssetStore;
import com.inmobi.ads.cache.b;
import com.inmobi.ads.cache.f;
import com.inmobi.ads.i;
import com.inmobi.ads.p;
import com.inmobi.commons.core.utilities.b.e;
import com.inmobi.commons.core.utilities.c;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: AdStore */
public class h implements com.inmobi.ads.e.a {
    /* access modifiers changed from: private */
    public static final String f = "h";
    /* access modifiers changed from: 0000 */
    @NonNull
    public final a a;
    @NonNull
    public final d b;
    /* access modifiers changed from: 0000 */
    @NonNull
    public f c;
    @NonNull
    public d d;
    long e = 0;
    private final f g = new f() {
        public final void a(b bVar) {
            String str;
            h.f;
            StringBuilder sb = new StringBuilder("onAssetsFetchFailure of batch ");
            if (bVar == null) {
                str = null;
            } else {
                str = bVar.toString();
            }
            sb.append(str);
            ArrayList<Long> arrayList = new ArrayList<>();
            if (bVar != null) {
                for (com.inmobi.ads.cache.a aVar : bVar.a) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("url", aVar.d);
                    hashMap.put("latency", Long.valueOf(aVar.a));
                    hashMap.put(AbstractEvent.SIZE, Long.valueOf(c.a(aVar.e)));
                    h.this.a.a("VideoAssetDownloadFailed", (Map<String, Object>) hashMap);
                    for (a aVar2 : h.this.b.b(aVar.d, h.this.c.c)) {
                        if (!arrayList.contains(Long.valueOf(aVar2.d))) {
                            arrayList.add(Long.valueOf(aVar2.d));
                        }
                    }
                }
            }
            if (!arrayList.contains(Long.valueOf(h.this.c.a))) {
                arrayList.add(Long.valueOf(h.this.c.a));
            }
            for (Long longValue : arrayList) {
                h.this.a.a(longValue.longValue(), false);
            }
        }

        public final void b(b bVar) {
            String str;
            h.f;
            StringBuilder sb = new StringBuilder("onAssetsFetchSuccess of batch ");
            if (bVar == null) {
                str = null;
            } else {
                str = bVar.toString();
            }
            sb.append(str);
            ArrayList<Long> arrayList = new ArrayList<>();
            if (bVar != null) {
                for (com.inmobi.ads.cache.a aVar : bVar.a) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("url", aVar.d);
                    hashMap.put("latency", Long.valueOf(aVar.a));
                    hashMap.put(AbstractEvent.SIZE, Long.valueOf(c.a(aVar.e)));
                    hashMap.put("clientRequestId", bVar.f);
                    if (aVar.j) {
                        h.this.a.a("GotCachedVideoAsset", (Map<String, Object>) hashMap);
                    } else {
                        h.this.a.a("VideoAssetDownloaded", (Map<String, Object>) hashMap);
                    }
                    List<a> a2 = h.this.b.a(aVar.d, h.this.c.c);
                    h.f;
                    StringBuilder sb2 = new StringBuilder("Found ");
                    sb2.append(a2.size());
                    sb2.append(" ads mapping to this asset");
                    for (a aVar2 : a2) {
                        if (!arrayList.contains(Long.valueOf(aVar2.d))) {
                            arrayList.add(Long.valueOf(aVar2.d));
                        }
                    }
                }
            }
            if (!arrayList.contains(Long.valueOf(h.this.c.a))) {
                arrayList.add(Long.valueOf(h.this.c.a));
            }
            for (Long longValue : arrayList) {
                long longValue2 = longValue.longValue();
                h.f;
                StringBuilder sb3 = new StringBuilder("Notifying ad unit with placement ID (");
                sb3.append(longValue2);
                sb3.append(")");
                h.this.a.a(longValue2, true);
            }
        }
    };

    /* compiled from: AdStore */
    public interface a {
        void a(long j, InMobiAdRequestStatus inMobiAdRequestStatus);

        void a(long j, @NonNull a aVar);

        void a(long j, boolean z);

        void a(String str, Map<String, Object> map);

        void b(long j, a aVar);
    }

    public h(@NonNull a aVar, @NonNull d dVar, @NonNull f fVar) {
        this.a = aVar;
        this.b = d.a();
        this.d = dVar;
        this.c = fVar;
    }

    public static void a() {
        if (e.b()) {
            d.c();
        }
    }

    public static void c() {
        b.b();
    }

    /* access modifiers changed from: 0000 */
    public final void a(final a aVar) {
        new Thread() {
            public final void run() {
                h.this.b;
                d.a(aVar);
            }
        }.start();
    }

    /* access modifiers changed from: 0000 */
    public final void a(@NonNull final String str) {
        new Thread() {
            public final void run() {
                h.this.b;
                d.a(str);
            }
        }.start();
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    public final String a(f fVar, boolean z) {
        b(fVar, z);
        this.e = SystemClock.elapsedRealtime();
        new e(fVar, this).a();
        HashMap hashMap = new HashMap();
        hashMap.put("isPreloaded", fVar.c());
        hashMap.put("clientRequestId", fVar.i);
        hashMap.put("im-accid", com.inmobi.commons.a.a.e());
        this.a.a("ServerCallInitiated", (Map<String, Object>) hashMap);
        return fVar.i;
    }

    private void a(List<a> list, @NonNull String str, @Nullable String str2) {
        this.b.a(list, this.c.a, this.d.a, this.c.e, this.c.j, str, str2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x003a A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x003b  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0092  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(java.util.List<com.inmobi.ads.a> r10, java.lang.String r11) {
        /*
            r9 = this;
            r0 = 0
            java.lang.Object r1 = r10.get(r0)
            com.inmobi.ads.a r1 = (com.inmobi.ads.a) r1
            java.lang.String r2 = r1.e()
            java.util.Locale r3 = java.util.Locale.ENGLISH
            java.lang.String r2 = r2.toUpperCase(r3)
            int r3 = r2.hashCode()
            r4 = -598127114(0xffffffffdc594df6, float:-2.44663156E17)
            r5 = 1
            if (r3 == r4) goto L_0x002b
            r4 = 2228139(0x21ffab, float:3.122288E-39)
            if (r3 == r4) goto L_0x0021
            goto L_0x0035
        L_0x0021:
            java.lang.String r3 = "HTML"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x0035
            r2 = 0
            goto L_0x0036
        L_0x002b:
            java.lang.String r3 = "INMOBIJSON"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x0035
            r2 = 1
            goto L_0x0036
        L_0x0035:
            r2 = -1
        L_0x0036:
            r3 = 0
            switch(r2) {
                case 0: goto L_0x0092;
                case 1: goto L_0x003b;
                default: goto L_0x003a;
            }
        L_0x003a:
            return
        L_0x003b:
            r9.a(r10, r11, r3)
            java.lang.String r2 = "int"
            com.inmobi.ads.f r3 = r9.c
            java.lang.String r3 = r3.e
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x0054
            com.inmobi.ads.h$a r11 = r9.a
            com.inmobi.ads.f r0 = r9.c
            long r2 = r0.a
            r11.b(r2, r1)
            goto L_0x008e
        L_0x0054:
            java.lang.String r2 = "native"
            com.inmobi.ads.f r3 = r9.c
            java.lang.String r3 = r3.e
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x008e
            com.inmobi.ads.d r3 = r9.b
            com.inmobi.ads.f r2 = r9.c
            long r4 = r2.a
            com.inmobi.ads.f r2 = r9.c
            java.lang.String r6 = r2.c
            com.inmobi.ads.f r2 = r9.c
            com.inmobi.ads.InMobiAdRequest$MonetizationContext r7 = r2.j
            r8 = r11
            com.inmobi.ads.a r11 = r3.b(r4, r6, r7, r8)
            if (r11 == 0) goto L_0x007f
            boolean r1 = r1.a(r11)
            if (r1 != 0) goto L_0x0080
            r10.add(r0, r11)
            goto L_0x0080
        L_0x007f:
            r11 = r1
        L_0x0080:
            com.inmobi.ads.h$a r0 = r9.a
            com.inmobi.ads.f r1 = r9.c
            long r1 = r1.a
            r0.a(r1, r11)
            com.inmobi.ads.f r11 = r9.c
            r9.a(r11)
        L_0x008e:
            r9.a(r10)
            return
        L_0x0092:
            java.lang.String r0 = "native"
            com.inmobi.ads.f r2 = r9.c
            java.lang.String r2 = r2.e
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x00af
            com.inmobi.ads.h$a r10 = r9.a
            com.inmobi.ads.f r11 = r9.c
            long r0 = r11.a
            com.inmobi.ads.InMobiAdRequestStatus r11 = new com.inmobi.ads.InMobiAdRequestStatus
            com.inmobi.ads.InMobiAdRequestStatus$StatusCode r2 = com.inmobi.ads.InMobiAdRequestStatus.StatusCode.INTERNAL_ERROR
            r11.<init>(r2)
            r10.a(r0, r11)
            return
        L_0x00af:
            int r0 = r10.size()
            java.util.List r10 = r10.subList(r5, r0)
            r9.a(r10, r11, r3)
            com.inmobi.ads.h$a r10 = r9.a
            com.inmobi.ads.f r11 = r9.c
            long r2 = r11.a
            r10.a(r2, r1)
            com.inmobi.ads.f r10 = r9.c
            r9.a(r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.h.a(java.util.List, java.lang.String):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0055 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0056  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00bb  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void b(java.util.List<com.inmobi.ads.a> r7, java.lang.String r8, @android.support.annotation.NonNull java.lang.String r9) {
        /*
            r6 = this;
            r6.a(r7, r8, r9)
            com.inmobi.ads.f r8 = r6.c
            java.lang.String r8 = r8.e
            com.inmobi.ads.b.b()
            com.inmobi.ads.a r8 = com.inmobi.ads.d.c(r9)
            if (r8 != 0) goto L_0x0021
            com.inmobi.ads.h$a r7 = r6.a
            com.inmobi.ads.f r8 = r6.c
            long r8 = r8.a
            com.inmobi.ads.InMobiAdRequestStatus r0 = new com.inmobi.ads.InMobiAdRequestStatus
            com.inmobi.ads.InMobiAdRequestStatus$StatusCode r1 = com.inmobi.ads.InMobiAdRequestStatus.StatusCode.INTERNAL_ERROR
            r0.<init>(r1)
            r7.a(r8, r0)
            return
        L_0x0021:
            java.lang.String r0 = r8.e()
            java.util.Locale r1 = java.util.Locale.ENGLISH
            java.lang.String r0 = r0.toUpperCase(r1)
            int r1 = r0.hashCode()
            r2 = -598127114(0xffffffffdc594df6, float:-2.44663156E17)
            r3 = -1
            r4 = 0
            r5 = 1
            if (r1 == r2) goto L_0x0047
            r2 = 2228139(0x21ffab, float:3.122288E-39)
            if (r1 == r2) goto L_0x003d
            goto L_0x0051
        L_0x003d:
            java.lang.String r1 = "HTML"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0051
            r0 = 0
            goto L_0x0052
        L_0x0047:
            java.lang.String r1 = "INMOBIJSON"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0051
            r0 = 1
            goto L_0x0052
        L_0x0051:
            r0 = -1
        L_0x0052:
            switch(r0) {
                case 0: goto L_0x00bb;
                case 1: goto L_0x0056;
                default: goto L_0x0055;
            }
        L_0x0055:
            return
        L_0x0056:
            com.inmobi.ads.f r0 = r6.c
            java.lang.String r0 = r0.e
            int r1 = r0.hashCode()
            r2 = -1052618729(0xffffffffc1425017, float:-12.144553)
            if (r1 == r2) goto L_0x0073
            r2 = 104431(0x197ef, float:1.46339E-40)
            if (r1 == r2) goto L_0x0069
            goto L_0x007c
        L_0x0069:
            java.lang.String r1 = "int"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x007c
            r3 = 0
            goto L_0x007c
        L_0x0073:
            java.lang.String r1 = "native"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x007c
            r3 = 1
        L_0x007c:
            switch(r3) {
                case 0: goto L_0x0092;
                case 1: goto L_0x0080;
                default: goto L_0x007f;
            }
        L_0x007f:
            goto L_0x009b
        L_0x0080:
            com.inmobi.ads.d.a(r9)
            com.inmobi.ads.h$a r9 = r6.a
            com.inmobi.ads.f r0 = r6.c
            long r0 = r0.a
            r9.a(r0, r8)
            com.inmobi.ads.f r9 = r6.c
            r6.a(r9)
            goto L_0x009b
        L_0x0092:
            com.inmobi.ads.h$a r9 = r6.a
            com.inmobi.ads.f r0 = r6.c
            long r0 = r0.a
            r9.b(r0, r8)
        L_0x009b:
            java.util.Iterator r9 = r7.iterator()
        L_0x009f:
            boolean r0 = r9.hasNext()
            if (r0 == 0) goto L_0x00b2
            java.lang.Object r0 = r9.next()
            com.inmobi.ads.a r0 = (com.inmobi.ads.a) r0
            boolean r0 = r8.a(r0)
            if (r0 == 0) goto L_0x009f
            r4 = 1
        L_0x00b2:
            if (r4 != 0) goto L_0x00b7
            r7.add(r8)
        L_0x00b7:
            r6.a(r7)
            return
        L_0x00bb:
            com.inmobi.ads.d.a(r9)
            com.inmobi.ads.h$a r7 = r6.a
            com.inmobi.ads.f r9 = r6.c
            long r0 = r9.a
            r7.a(r0, r8)
            com.inmobi.ads.f r7 = r6.c
            r6.a(r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.h.b(java.util.List, java.lang.String, java.lang.String):void");
    }

    public final void a(g gVar) {
        StringBuilder sb = new StringBuilder();
        List<a> a2 = a(gVar, sb);
        String sb2 = sb.toString();
        boolean isEmpty = TextUtils.isEmpty(sb2);
        if (a2 == null) {
            new StringBuilder("Could not parse ad response:").append(gVar.a.b());
            this.a.a(this.c.a, new InMobiAdRequestStatus(StatusCode.INTERNAL_ERROR));
        } else if (a2.size() != 0 || !isEmpty) {
            HashMap hashMap = new HashMap();
            hashMap.put("numberOfAdsReturned", Integer.valueOf(a2.size()));
            hashMap.put("latency", Long.valueOf(SystemClock.elapsedRealtime() - this.e));
            hashMap.put("isPreloaded", this.c.c());
            hashMap.put("im-accid", com.inmobi.commons.a.a.e());
            this.a.a("ServerFill", (Map<String, Object>) hashMap);
            for (a aVar : a2) {
                HashMap hashMap2 = new HashMap();
                hashMap2.put("ts", Long.valueOf(System.currentTimeMillis()));
                hashMap2.put("impId", aVar.g);
                hashMap2.put("plId", Long.valueOf(aVar.d));
                this.a.a("AdCacheImpressionInserted", (Map<String, Object>) hashMap2);
            }
            String a3 = com.inmobi.ads.c.a.a(this.c.g);
            if (isEmpty) {
                a(a2, a3);
            } else {
                b(a2, a3, sb2);
            }
        } else {
            new StringBuilder("Ad response received but no ad available:").append(gVar.a.b());
            HashMap hashMap3 = new HashMap();
            hashMap3.put("latency", Long.valueOf(SystemClock.elapsedRealtime() - this.e));
            hashMap3.put("isPreloaded", this.c.c());
            hashMap3.put("im-accid", com.inmobi.commons.a.a.e());
            this.a.a("ServerNoFill", (Map<String, Object>) hashMap3);
            this.a.a(this.c.a, new InMobiAdRequestStatus(StatusCode.NO_FILL));
        }
    }

    /* access modifiers changed from: 0000 */
    public final void a(List<a> list) {
        if (list != null && list.size() > 0) {
            a aVar = (a) list.get(0);
            if (aVar != null) {
                Set d2 = aVar.d();
                if (d2.size() == 0) {
                    this.a.a(this.c.a, true);
                    return;
                } else {
                    AssetStore.a().a(new b(UUID.randomUUID().toString(), aVar.h, d2, this.g));
                }
            }
            for (a aVar2 : list.subList(1, list.size())) {
                if (aVar2 != null) {
                    Set d3 = aVar2.d();
                    if (d3.size() != 0) {
                        AssetStore.a().a(new b(UUID.randomUUID().toString(), aVar2.h, d3, (f) null));
                    }
                }
            }
        }
    }

    @Nullable
    private List<a> a(g gVar, @Nullable StringBuilder sb) {
        ArrayList arrayList = new ArrayList();
        try {
            JSONObject jSONObject = new JSONObject(gVar.a.b());
            sb.append(jSONObject.optString("winnerImpressionId").trim());
            JSONArray jSONArray = jSONObject.getJSONArray("ads");
            if (jSONArray != null) {
                int min = Math.min(gVar.c.d, jSONArray.length());
                for (int i = 0; i < min; i++) {
                    a a2 = C0040a.a(jSONArray.getJSONObject(i), gVar.c.a, gVar.c.e, gVar.c.c, gVar.c.i, gVar.c.j, gVar.c.k);
                    if (a2 != null) {
                        arrayList.add(a2);
                    }
                }
                if (min <= 0 || !arrayList.isEmpty()) {
                    return arrayList;
                }
                return null;
            }
        } catch (JSONException e2) {
            HashMap hashMap = new HashMap();
            hashMap.put("errorCode", "ParsingError");
            hashMap.put("reason", e2.getLocalizedMessage());
            hashMap.put("latency", Long.valueOf(SystemClock.elapsedRealtime() - this.e));
            hashMap.put("im-accid", com.inmobi.commons.a.a.e());
            this.a.a("ServerError", (Map<String, Object>) hashMap);
            arrayList = null;
        }
        return arrayList;
    }

    public final void b(g gVar) {
        HashMap hashMap = new HashMap();
        hashMap.put("errorCode", String.valueOf(gVar.a.b.a.getValue()));
        hashMap.put("reason", gVar.a.b.b);
        hashMap.put("latency", Long.valueOf(SystemClock.elapsedRealtime() - this.e));
        hashMap.put("im-accid", com.inmobi.commons.a.a.e());
        this.a.a("ServerError", (Map<String, Object>) hashMap);
        this.a.a(this.c.a, gVar.b);
    }

    /* access modifiers changed from: 0000 */
    public final String b() {
        String a2 = com.inmobi.ads.c.a.a(this.c.g);
        b.b();
        a aVar = null;
        if (d.a(this.c.a, this.c.c, this.c.j, a2) != 0) {
            a b2 = this.b.b(this.c.a, this.c.c, this.c.j, a2);
            if (b2 != null) {
                HashMap hashMap = new HashMap();
                hashMap.put("clientRequestId", b2.h);
                hashMap.put("im-accid", com.inmobi.commons.a.a.e());
                hashMap.put("isPreloaded", this.c.c());
                this.a.a("AdCacheHit", (Map<String, Object>) hashMap);
                a(this.c);
                aVar = b2;
            }
        }
        if (aVar != null) {
            String str = aVar.h;
            this.a.a(this.c.a, aVar);
            if (!"INMOBIJSON".equalsIgnoreCase(aVar.e())) {
                return str;
            }
            a((List<a>) new ArrayList<a>(Collections.singletonList(aVar)));
            return str;
        } else if (this.c.c().equals(AppEventsConstants.EVENT_PARAM_VALUE_YES)) {
            return a(this.c, true);
        } else {
            return a(this.c, false);
        }
    }

    private static void b(f fVar, boolean z) {
        if (fVar != null) {
            Map<String, String> map = fVar.h;
            if (map == null) {
                map = new HashMap<>();
            }
            map.put("preload-request", String.valueOf(z ? 1 : 0));
            fVar.h = map;
        }
    }

    public final void a(@NonNull f fVar) {
        b.b();
        int a2 = d.a(fVar.a, fVar.c, fVar.j, com.inmobi.ads.c.a.a(fVar.g));
        boolean equals = "int".equals(fVar.e);
        if (a2 < this.d.c) {
            new StringBuilder("Cached ad count below threshold, firing ad request for Placement : ").append(fVar.a);
            com.inmobi.ads.c.a a3 = com.inmobi.ads.c.a.a(fVar.e);
            if (equals) {
                b(fVar, true);
                try {
                    new bl(new com.inmobi.ads.bl.a(fVar) {
                        final /* synthetic */ com.inmobi.ads.f a;

                        {
                            this.a = r2;
                        }

                        public final void a(long j) {
                            a.d;
                        }

                        public final void b(long j, InMobiAdRequestStatus inMobiAdRequestStatus) {
                            a.d;
                            new StringBuilder("Interstitial Prefetch failed with the message - ").append(inMobiAdRequestStatus.getMessage());
                        }

                        public final void a(String str, Map<String, Object> map) {
                            a.a(str, map, this.a);
                        }
                    }, this.d).a(fVar, true, com.inmobi.ads.c.a.b.c);
                } catch (com.inmobi.ads.a.a e2) {
                    e2.getMessage();
                }
            } else {
                new Handler(Looper.getMainLooper()).post(new Runnable(fVar) {
                    final /* synthetic */ com.inmobi.ads.f a;
                    private i.e c;

                    {
                        this.a = r2;
                    }

                    public final void run() {
                        try {
                            Context b2 = com.inmobi.commons.a.a.b();
                            if (b2 != null) {
                                bi a2 = bi.a(this.a.a, this.a.g, this.a.e, this.a.f);
                                a2.f = this.a.j;
                                a.d;
                                StringBuilder sb = new StringBuilder("preFetchAdUnit. pid:");
                                sb.append(a2.a);
                                sb.append(" tp:");
                                sb.append(a2.b);
                                if (a2.c == null && a2.b != null) {
                                    HashMap hashMap = new HashMap();
                                    hashMap.put("tp", a2.b);
                                    a2.c = hashMap;
                                }
                                this.c = new C0042a(a2);
                                i a3 = a.b(a.this.c, b2, a2);
                                if (a3 != null) {
                                    a3.e = a2.d;
                                    a3.f = a2.c;
                                    a3.n = true;
                                    a3.q = this.c;
                                    if (a.this.c.equalsIgnoreCase("banner")) {
                                        ((p) a3).A = this.a.c;
                                        ((p) a3).y = true;
                                    }
                                    a3.a(true);
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
}
