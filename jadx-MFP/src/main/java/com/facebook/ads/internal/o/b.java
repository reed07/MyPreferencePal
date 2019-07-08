package com.facebook.ads.internal.o;

import java.util.ArrayList;
import java.util.List;

public class b {
    private static final List<a> a = new ArrayList();

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001c, code lost:
        r0 = new org.json.JSONArray();
        r1 = r1.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0029, code lost:
        if (r1.hasNext() == false) goto L_0x0039;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002b, code lost:
        r0.put(((com.facebook.ads.internal.o.a) r1.next()).a());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003d, code lost:
        return r0.toString();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a() {
        /*
            java.util.List<com.facebook.ads.internal.o.a> r0 = a
            monitor-enter(r0)
            java.util.List<com.facebook.ads.internal.o.a> r1 = a     // Catch:{ all -> 0x003e }
            boolean r1 = r1.isEmpty()     // Catch:{ all -> 0x003e }
            if (r1 == 0) goto L_0x000f
            java.lang.String r1 = ""
            monitor-exit(r0)     // Catch:{ all -> 0x003e }
            return r1
        L_0x000f:
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ all -> 0x003e }
            java.util.List<com.facebook.ads.internal.o.a> r2 = a     // Catch:{ all -> 0x003e }
            r1.<init>(r2)     // Catch:{ all -> 0x003e }
            java.util.List<com.facebook.ads.internal.o.a> r2 = a     // Catch:{ all -> 0x003e }
            r2.clear()     // Catch:{ all -> 0x003e }
            monitor-exit(r0)     // Catch:{ all -> 0x003e }
            org.json.JSONArray r0 = new org.json.JSONArray
            r0.<init>()
            java.util.Iterator r1 = r1.iterator()
        L_0x0025:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0039
            java.lang.Object r2 = r1.next()
            com.facebook.ads.internal.o.a r2 = (com.facebook.ads.internal.o.a) r2
            org.json.JSONObject r2 = r2.a()
            r0.put(r2)
            goto L_0x0025
        L_0x0039:
            java.lang.String r0 = r0.toString()
            return r0
        L_0x003e:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x003e }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.o.b.a():java.lang.String");
    }

    public static void a(a aVar) {
        synchronized (a) {
            a.add(aVar);
        }
    }
}
