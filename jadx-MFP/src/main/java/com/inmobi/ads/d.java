package com.inmobi.ads;

import android.content.ContentValues;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.facebook.appevents.AppEventsConstants;
import com.inmobi.ads.InMobiAdRequest.MonetizationContext;
import com.inmobi.commons.core.d.b;
import com.mopub.mobileads.BaseVideoPlayerActivity;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* compiled from: AdDao */
public class d {
    private static final String a = "d";
    private static d b;
    private static final Object c = new Object();
    private static final String[] d = {"id", "ad_content", BaseVideoPlayerActivity.VIDEO_URL, "video_track_duration", "click_url", "video_trackers", "companion_ads", "web_vast", "preload_webView", "asset_urls", AppEventsConstants.EVENT_PARAM_AD_TYPE, "ad_size", "placement_id", "tp_key", "insertion_ts", "expiry_duration", "imp_id", "m10_context", "client_request_id", "bid", "bidInfo", "marked"};

    public static d a() {
        d dVar = b;
        if (dVar == null) {
            synchronized (c) {
                dVar = b;
                if (dVar == null) {
                    dVar = new d();
                    b = dVar;
                }
            }
        }
        return dVar;
    }

    private static String[] a(long j, MonetizationContext monetizationContext, String str) {
        return new String[]{String.valueOf(j), monetizationContext.a, str, "0"};
    }

    private static String[] e(long j, String str, MonetizationContext monetizationContext, String str2) {
        return new String[]{String.valueOf(j), str, monetizationContext.a, str2, "0"};
    }

    private d() {
        b a2 = b.a();
        a2.a("ad", "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, placement_id INTEGER NOT NULL, ad_content TEXT NOT NULL, ad_type TEXT NOT NULL, ad_size TEXT, asset_urls TEXT, video_url TEXT, video_track_duration TEXT, click_url TEXT, video_trackers TEXT, companion_ads TEXT, web_vast TEXT, preload_webView INTEGER DEFAULT 0, insertion_ts INTEGER NOT NULL, imp_id TEXT NOT NULL UNIQUE, m10_context TEXT NOT NULL, tp_key TEXT, expiry_duration INTEGER NOT NULL, client_request_id TEXT NOT NULL,bid INTEGER NOT NULL,bidInfo TEXT,marked INTEGER DEFAULT 0)");
        d();
        a2.b();
    }

    static List<a> a(String str, long j) {
        long j2;
        ArrayList arrayList = new ArrayList();
        b a2 = b.a();
        int i = 0;
        b bVar = a2;
        List<ContentValues> a3 = bVar.a("ad", d, "ad_type=?", new String[]{str}, null, null, null, null);
        if (a3.size() == 0) {
            a2.b();
            return arrayList;
        }
        for (ContentValues a4 : a3) {
            a a5 = C0040a.a(a4);
            if (a5.c() == -1) {
                j2 = (a5.e + TimeUnit.SECONDS.toMillis(j)) - System.currentTimeMillis();
            } else {
                j2 = a5.c() - System.currentTimeMillis();
            }
            if (j2 < 0) {
                i += a(a5.g);
                arrayList.add(a5);
            }
        }
        StringBuilder sb = new StringBuilder("Deleted ");
        sb.append(i);
        sb.append(" expired ads from cache of type: ");
        sb.append(str);
        a2.b();
        return arrayList;
    }

    static List<a> b() {
        ArrayList arrayList = new ArrayList();
        for (ContentValues a2 : b.a().a("ad", d, null, null, null, null, null, null)) {
            arrayList.add(C0040a.a(a2));
        }
        return arrayList;
    }

    private static void d() {
        b a2 = b.a();
        b bVar = a2;
        for (ContentValues contentValues : bVar.a("ad", d, "marked=?", new String[]{AppEventsConstants.EVENT_PARAM_VALUE_YES}, null, null, null, null)) {
            contentValues.put("marked", "0");
            a2.a("ad", contentValues, "imp_id=?", new String[]{contentValues.getAsString("imp_id")});
        }
        a2.b();
    }

    static int a(long j, String str, MonetizationContext monetizationContext, String str2) {
        int i;
        b a2 = b.a();
        if (str == null || str.trim().length() == 0) {
            i = a2.b("ad", "placement_id=? AND m10_context=? AND tp_key=? AND marked=?", a(j, monetizationContext, str2));
        } else {
            i = a2.b("ad", "placement_id=? AND ad_size=? AND m10_context=? AND tp_key=? AND marked=?", e(j, str, monetizationContext, str2));
        }
        a2.b();
        return i;
    }

    /* access modifiers changed from: 0000 */
    public final synchronized a b(long j, String str, MonetizationContext monetizationContext, String str2) {
        a f;
        f = f(j, str, monetizationContext, str2);
        if (f != null) {
            b.a().a("ad", "id=?", new String[]{String.valueOf(f.a)});
        }
        return f;
    }

    private synchronized a f(long j, String str, MonetizationContext monetizationContext, String str2) {
        List list;
        b a2 = b.a();
        if (str == null || str.trim().length() == 0) {
            list = a2.a("ad", d, "placement_id=? AND m10_context=? AND tp_key=? AND marked=?", a(j, monetizationContext, str2), null, null, "insertion_ts", AppEventsConstants.EVENT_PARAM_VALUE_YES);
        } else {
            list = a2.a("ad", d, "placement_id=? AND ad_size=? AND m10_context=? AND tp_key=? AND marked=?", e(j, str, monetizationContext, str2), null, null, "insertion_ts", AppEventsConstants.EVENT_PARAM_VALUE_YES);
        }
        if (list.size() == 0) {
            return null;
        }
        return C0040a.a((ContentValues) list.get(0));
    }

    @NonNull
    public final synchronized List<a> c(long j, String str, MonetizationContext monetizationContext, String str2) {
        return a(j, str, monetizationContext, str2, false);
    }

    /* access modifiers changed from: 0000 */
    public final synchronized List<a> a(String str, String str2) {
        ArrayList arrayList;
        List<ContentValues> list;
        arrayList = new ArrayList();
        b a2 = b.a();
        if (str2 == null || str2.trim().length() == 0) {
            list = a2.a("ad", d, "video_url=?", new String[]{str}, null, null, "insertion_ts", null);
        } else {
            list = a2.a("ad", d, "video_url=? AND ad_size=?", new String[]{str, str2}, null, null, "insertion_ts", null);
        }
        for (ContentValues a3 : list) {
            arrayList.add(C0040a.a(a3));
        }
        return arrayList;
    }

    public final synchronized List<a> d(long j, String str, MonetizationContext monetizationContext, String str2) {
        return a(j, str, monetizationContext, str2, true);
    }

    private static List<a> a(long j, String str, MonetizationContext monetizationContext, String str2, boolean z) {
        List<ContentValues> list;
        ArrayList arrayList = new ArrayList();
        b a2 = b.a();
        if (str == null || str.trim().length() == 0) {
            list = a2.a("ad", d, "placement_id=? AND m10_context=? AND tp_key=? AND marked=?", a(j, monetizationContext, str2), null, null, z ? "bid" : "insertion_ts", null);
        } else {
            list = a2.a("ad", d, "placement_id=? AND ad_size=? AND m10_context=? AND tp_key=? AND marked=?", e(j, str, monetizationContext, str2), null, null, z ? "bid" : "insertion_ts", null);
        }
        for (ContentValues a3 : list) {
            arrayList.add(C0040a.a(a3));
        }
        return arrayList;
    }

    /* access modifiers changed from: 0000 */
    public final synchronized List<a> b(String str, String str2) {
        ArrayList arrayList;
        List<ContentValues> list;
        arrayList = new ArrayList();
        b a2 = b.a();
        if (str2 == null || str2.trim().length() == 0) {
            b bVar = a2;
            list = bVar.a("ad", d, "video_url=?", new String[]{str}, null, null, "insertion_ts", null);
        } else {
            b bVar2 = a2;
            list = bVar2.a("ad", d, "video_url=? AND ad_size=?", new String[]{str, str2}, null, null, "insertion_ts", null);
        }
        for (ContentValues contentValues : list) {
            a2.a("ad", "id=?", new String[]{String.valueOf(contentValues.getAsInteger("id").intValue())});
            arrayList.add(C0040a.a(contentValues));
        }
        return arrayList;
    }

    public static int a(String str) {
        b a2 = b.a();
        int a3 = a2.a("ad", "imp_id = ?", new String[]{String.valueOf(str)});
        a2.b();
        return a3;
    }

    public static void b(String str) {
        b a2 = b.a();
        a c2 = c(str);
        if (c2 != null) {
            ContentValues a3 = c2.a();
            a3.put("marked", AppEventsConstants.EVENT_PARAM_VALUE_YES);
            a2.b("ad", a3, "imp_id=?", new String[]{str});
        }
    }

    @Nullable
    public static a c(String str) {
        List a2 = b.a().a("ad", d, "imp_id=?", new String[]{str}, null, null, null, AppEventsConstants.EVENT_PARAM_VALUE_YES);
        if (a2.size() == 0) {
            return null;
        }
        return C0040a.a((ContentValues) a2.get(0));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0016, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void a(java.util.List<com.inmobi.ads.a> r16, long r17, int r19, java.lang.String r20, com.inmobi.ads.InMobiAdRequest.MonetizationContext r21, java.lang.String r22, @android.support.annotation.Nullable java.lang.String r23) {
        /*
            r15 = this;
            r0 = r17
            r2 = r21
            r3 = r22
            monitor-enter(r15)
            boolean r4 = android.text.TextUtils.isEmpty(r23)     // Catch:{ all -> 0x00d6 }
            if (r4 == 0) goto L_0x000f
            if (r19 == 0) goto L_0x0015
        L_0x000f:
            int r5 = r16.size()     // Catch:{ all -> 0x00d6 }
            if (r5 != 0) goto L_0x0017
        L_0x0015:
            monitor-exit(r15)
            return
        L_0x0017:
            com.inmobi.commons.core.d.b r5 = com.inmobi.commons.core.d.b.a()     // Catch:{ all -> 0x00d6 }
            java.util.Iterator r6 = r16.iterator()     // Catch:{ all -> 0x00d6 }
        L_0x001f:
            boolean r7 = r6.hasNext()     // Catch:{ all -> 0x00d6 }
            if (r7 == 0) goto L_0x0040
            java.lang.Object r7 = r6.next()     // Catch:{ all -> 0x00d6 }
            com.inmobi.ads.a r7 = (com.inmobi.ads.a) r7     // Catch:{ all -> 0x00d6 }
            long r8 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x00d6 }
            r7.e = r8     // Catch:{ all -> 0x00d6 }
            android.content.ContentValues r7 = r7.a()     // Catch:{ all -> 0x00d6 }
            java.lang.String r8 = "tp_key"
            r7.put(r8, r3)     // Catch:{ all -> 0x00d6 }
            java.lang.String r8 = "ad"
            r5.a(r8, r7)     // Catch:{ all -> 0x00d6 }
            goto L_0x001f
        L_0x0040:
            if (r4 != 0) goto L_0x0045
            b(r23)     // Catch:{ all -> 0x00d6 }
        L_0x0045:
            r4 = 0
            int r6 = a(r0, r4, r2, r3)     // Catch:{ all -> 0x00d6 }
            int r6 = r6 - r19
            if (r6 <= 0) goto L_0x00d1
            java.util.HashMap r7 = new java.util.HashMap     // Catch:{ all -> 0x00d6 }
            r7.<init>()     // Catch:{ all -> 0x00d6 }
            java.lang.String r8 = "type"
            r9 = r20
            r7.put(r8, r9)     // Catch:{ all -> 0x00d6 }
            java.lang.String r8 = "count"
            java.lang.Integer r9 = java.lang.Integer.valueOf(r6)     // Catch:{ all -> 0x00d6 }
            r7.put(r8, r9)     // Catch:{ all -> 0x00d6 }
            com.inmobi.commons.core.e.b.a()     // Catch:{ all -> 0x00d6 }
            java.lang.String r8 = "ads"
            java.lang.String r9 = "DbSpaceOverflow"
            com.inmobi.commons.core.e.b.a(r8, r9, r7)     // Catch:{ all -> 0x00d6 }
            java.lang.String r7 = "ad"
            java.lang.String r8 = "id"
            java.lang.String[] r8 = new java.lang.String[]{r8}     // Catch:{ all -> 0x00d6 }
            java.lang.String r9 = "placement_id=? AND m10_context=? AND tp_key=? AND marked=?"
            java.lang.String[] r10 = a(r0, r2, r3)     // Catch:{ all -> 0x00d6 }
            r11 = 0
            r12 = 0
            java.lang.String r13 = "insertion_ts ASC"
            java.lang.String r14 = java.lang.String.valueOf(r6)     // Catch:{ all -> 0x00d6 }
            r6 = r5
            java.util.List r0 = r6.a(r7, r8, r9, r10, r11, r12, r13, r14)     // Catch:{ all -> 0x00d6 }
            int r1 = r0.size()     // Catch:{ all -> 0x00d6 }
            java.lang.String[] r1 = new java.lang.String[r1]     // Catch:{ all -> 0x00d6 }
            r2 = 0
        L_0x008f:
            int r3 = r0.size()     // Catch:{ all -> 0x00d6 }
            if (r2 >= r3) goto L_0x00aa
            java.lang.Object r3 = r0.get(r2)     // Catch:{ all -> 0x00d6 }
            android.content.ContentValues r3 = (android.content.ContentValues) r3     // Catch:{ all -> 0x00d6 }
            java.lang.String r6 = "id"
            java.lang.Integer r3 = r3.getAsInteger(r6)     // Catch:{ all -> 0x00d6 }
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x00d6 }
            r1[r2] = r3     // Catch:{ all -> 0x00d6 }
            int r2 = r2 + 1
            goto L_0x008f
        L_0x00aa:
            java.lang.String r0 = java.util.Arrays.toString(r1)     // Catch:{ all -> 0x00d6 }
            java.lang.String r1 = "["
            java.lang.String r2 = "("
            java.lang.String r0 = r0.replace(r1, r2)     // Catch:{ all -> 0x00d6 }
            java.lang.String r1 = "]"
            java.lang.String r2 = ")"
            java.lang.String r0 = r0.replace(r1, r2)     // Catch:{ all -> 0x00d6 }
            java.lang.String r1 = "ad"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x00d6 }
            java.lang.String r3 = "id IN "
            r2.<init>(r3)     // Catch:{ all -> 0x00d6 }
            r2.append(r0)     // Catch:{ all -> 0x00d6 }
            java.lang.String r0 = r2.toString()     // Catch:{ all -> 0x00d6 }
            r5.a(r1, r0, r4)     // Catch:{ all -> 0x00d6 }
        L_0x00d1:
            r5.b()     // Catch:{ all -> 0x00d6 }
            monitor-exit(r15)
            return
        L_0x00d6:
            r0 = move-exception
            monitor-exit(r15)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.d.a(java.util.List, long, int, java.lang.String, com.inmobi.ads.InMobiAdRequest$MonetizationContext, java.lang.String, java.lang.String):void");
    }

    public static void c() {
        b a2 = b.a();
        a2.a("ad", null, null);
        a2.b();
    }

    public static int a(a aVar) {
        return a(aVar.g);
    }
}
