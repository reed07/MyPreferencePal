package com.inmobi.commons.core.configs;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: RootConfig */
public final class h extends a {
    private static final Object i = new Object();
    int a = 3;
    int b = 60;
    int c = 3;
    public int d = -1;
    b e = new b();
    JSONObject f = new JSONObject();
    public boolean g = false;
    private List<a> h = new ArrayList();

    /* compiled from: RootConfig */
    static final class a {
        String a;
        long b;
        String c;
        String d;
        String e = "https://config.inmobi.cn/config-server/v1/config/secure.cfg";

        a() {
        }
    }

    /* compiled from: RootConfig */
    public static final class b {
        String a = "7.2.6";
        String b = "https://www.inmobi.com/products/sdk/#downloads";
    }

    public final String a() {
        return "root";
    }

    public final JSONObject b() throws JSONException {
        boolean z;
        JSONObject b2 = super.b();
        JSONArray jSONArray = new JSONArray();
        b2.put("maxRetries", this.a);
        b2.put("retryInterval", this.b);
        b2.put("waitTime", this.c);
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("version", this.e.a);
        jSONObject.put("url", this.e.b);
        b2.put("latestSdkInfo", jSONObject);
        synchronized (i) {
            z = false;
            for (int i2 = 0; i2 < this.h.size(); i2++) {
                a aVar = (a) this.h.get(i2);
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("type", aVar.a);
                jSONObject2.put("expiry", aVar.b);
                jSONObject2.put("protocol", aVar.c);
                jSONObject2.put("url", aVar.d);
                if ("root".equals(aVar.a)) {
                    jSONObject2.put("fallbackUrl", aVar.e);
                }
                jSONArray.put(jSONObject2);
            }
        }
        b2.put("components", jSONArray);
        b2.put("monetizationDisabled", this.g);
        JSONObject jSONObject3 = new JSONObject();
        String str = "transmitRequest";
        if (this.d == 1) {
            z = true;
        }
        jSONObject3.put(str, z);
        b2.put("gdpr", jSONObject3);
        return b2;
    }

    public final void a(JSONObject jSONObject) throws JSONException {
        super.a(jSONObject);
        this.a = jSONObject.getInt("maxRetries");
        this.b = jSONObject.getInt("retryInterval");
        this.c = jSONObject.getInt("waitTime");
        JSONObject jSONObject2 = jSONObject.getJSONObject("latestSdkInfo");
        this.e.a = jSONObject2.getString("version");
        this.e.b = jSONObject2.getString("url");
        JSONArray jSONArray = jSONObject.getJSONArray("components");
        synchronized (i) {
            this.h.clear();
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                JSONObject jSONObject3 = jSONArray.getJSONObject(i2);
                a aVar = new a();
                aVar.a = jSONObject3.getString("type");
                aVar.b = jSONObject3.getLong("expiry");
                aVar.c = jSONObject3.getString("protocol");
                aVar.d = jSONObject3.getString("url");
                if ("root".equals(aVar.a)) {
                    aVar.e = jSONObject3.getString("fallbackUrl");
                }
                this.h.add(aVar);
            }
        }
        this.g = jSONObject.getBoolean("monetizationDisabled");
        this.d = jSONObject.getJSONObject("gdpr").getBoolean("transmitRequest") ? 1 : 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00b0, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00b5, code lost:
        if (r9.d == -1) goto L_0x00b9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00b8, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00b9, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean c() {
        /*
            r9 = this;
            java.util.List<com.inmobi.commons.core.configs.h$a> r0 = r9.h
            r1 = 0
            if (r0 != 0) goto L_0x0006
            return r1
        L_0x0006:
            int r0 = r9.a
            if (r0 < 0) goto L_0x00be
            int r0 = r9.b
            if (r0 < 0) goto L_0x00be
            int r0 = r9.c
            if (r0 >= 0) goto L_0x0014
            goto L_0x00be
        L_0x0014:
            com.inmobi.commons.core.configs.h$b r0 = r9.e
            java.lang.String r0 = r0.a
            java.lang.String r0 = r0.trim()
            int r0 = r0.length()
            if (r0 == 0) goto L_0x00bd
            com.inmobi.commons.core.configs.h$b r0 = r9.e
            java.lang.String r0 = r0.b
            java.lang.String r2 = "http://"
            boolean r0 = r0.startsWith(r2)
            if (r0 != 0) goto L_0x003c
            com.inmobi.commons.core.configs.h$b r0 = r9.e
            java.lang.String r0 = r0.b
            java.lang.String r2 = "https://"
            boolean r0 = r0.startsWith(r2)
            if (r0 != 0) goto L_0x003c
            goto L_0x00bd
        L_0x003c:
            java.lang.Object r0 = i
            monitor-enter(r0)
            r2 = 0
        L_0x0040:
            java.util.List<com.inmobi.commons.core.configs.h$a> r3 = r9.h     // Catch:{ all -> 0x00ba }
            int r3 = r3.size()     // Catch:{ all -> 0x00ba }
            if (r2 >= r3) goto L_0x00b1
            java.util.List<com.inmobi.commons.core.configs.h$a> r3 = r9.h     // Catch:{ all -> 0x00ba }
            java.lang.Object r3 = r3.get(r2)     // Catch:{ all -> 0x00ba }
            com.inmobi.commons.core.configs.h$a r3 = (com.inmobi.commons.core.configs.h.a) r3     // Catch:{ all -> 0x00ba }
            java.lang.String r4 = r3.a     // Catch:{ all -> 0x00ba }
            java.lang.String r4 = r4.trim()     // Catch:{ all -> 0x00ba }
            int r4 = r4.length()     // Catch:{ all -> 0x00ba }
            if (r4 != 0) goto L_0x005e
            monitor-exit(r0)     // Catch:{ all -> 0x00ba }
            return r1
        L_0x005e:
            long r4 = r3.b     // Catch:{ all -> 0x00ba }
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ all -> 0x00ba }
            long r4 = r4.longValue()     // Catch:{ all -> 0x00ba }
            r6 = 0
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 < 0) goto L_0x00af
            long r4 = r3.b     // Catch:{ all -> 0x00ba }
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ all -> 0x00ba }
            long r4 = r4.longValue()     // Catch:{ all -> 0x00ba }
            r6 = 864000(0xd2f00, double:4.268727E-318)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 <= 0) goto L_0x0080
            goto L_0x00af
        L_0x0080:
            java.lang.String r4 = r3.c     // Catch:{ all -> 0x00ba }
            java.lang.String r4 = r4.trim()     // Catch:{ all -> 0x00ba }
            int r4 = r4.length()     // Catch:{ all -> 0x00ba }
            if (r4 != 0) goto L_0x008e
            monitor-exit(r0)     // Catch:{ all -> 0x00ba }
            return r1
        L_0x008e:
            java.lang.String r4 = r3.d     // Catch:{ all -> 0x00ba }
            boolean r4 = c(r4)     // Catch:{ all -> 0x00ba }
            if (r4 == 0) goto L_0x0098
            monitor-exit(r0)     // Catch:{ all -> 0x00ba }
            return r1
        L_0x0098:
            java.lang.String r4 = "root"
            java.lang.String r5 = r3.a     // Catch:{ all -> 0x00ba }
            boolean r4 = r4.equals(r5)     // Catch:{ all -> 0x00ba }
            if (r4 == 0) goto L_0x00ac
            java.lang.String r3 = r3.e     // Catch:{ all -> 0x00ba }
            boolean r3 = c(r3)     // Catch:{ all -> 0x00ba }
            if (r3 == 0) goto L_0x00ac
            monitor-exit(r0)     // Catch:{ all -> 0x00ba }
            return r1
        L_0x00ac:
            int r2 = r2 + 1
            goto L_0x0040
        L_0x00af:
            monitor-exit(r0)     // Catch:{ all -> 0x00ba }
            return r1
        L_0x00b1:
            monitor-exit(r0)     // Catch:{ all -> 0x00ba }
            int r0 = r9.d
            r2 = -1
            if (r0 == r2) goto L_0x00b9
            r0 = 1
            return r0
        L_0x00b9:
            return r1
        L_0x00ba:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00ba }
            throw r1
        L_0x00bd:
            return r1
        L_0x00be:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.commons.core.configs.h.c():boolean");
    }

    private static boolean c(String str) {
        return str == null || str.trim().length() == 0 || (!str.startsWith("http://") && !str.startsWith("https://"));
    }

    public final a d() {
        return new h();
    }

    public final long a(String str) {
        synchronized (i) {
            for (int i2 = 0; i2 < this.h.size(); i2++) {
                a aVar = (a) this.h.get(i2);
                if (str.equals(aVar.a)) {
                    long j = aVar.b;
                    return j;
                }
            }
            return 86400;
        }
    }

    public final String b(String str) {
        synchronized (i) {
            for (int i2 = 0; i2 < this.h.size(); i2++) {
                a aVar = (a) this.h.get(i2);
                if (str.equals(aVar.a)) {
                    String str2 = aVar.d;
                    return str2;
                }
            }
            String str3 = "";
            return str3;
        }
    }

    public final String e() {
        synchronized (i) {
            for (a aVar : this.h) {
                if ("root".equals(aVar.a)) {
                    String str = aVar.e;
                    return str;
                }
            }
            String str2 = "https://config.inmobi.cn/config-server/v1/config/secure.cfg";
            return str2;
        }
    }
}
