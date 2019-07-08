package com.inmobi.commons.core.configs;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.b.e;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* compiled from: ConfigComponent */
public class b {
    /* access modifiers changed from: private */
    public static final String a = "b";
    private static Map<a, ArrayList<WeakReference<c>>> b;
    /* access modifiers changed from: private */
    public static h c;
    private static d d;
    private HandlerThread e;
    private C0046b f;
    private boolean g;

    /* compiled from: ConfigComponent */
    private static class a {
        /* access modifiers changed from: private */
        public static final b a = new b(0);
    }

    /* renamed from: com.inmobi.commons.core.configs.b$b reason: collision with other inner class name */
    /* compiled from: ConfigComponent */
    static final class C0046b extends Handler implements com.inmobi.commons.core.configs.e.a {
        private List<a> a = new ArrayList();
        private Map<String, Map<String, a>> b = new HashMap();
        private Map<String, a> c = new HashMap();
        private ExecutorService d;

        C0046b(Looper looper) {
            super(looper);
        }

        public final void handleMessage(Message message) {
            int i;
            Message message2 = message;
            f fVar = null;
            switch (message2.what) {
                case 1:
                    a aVar = (a) message2.obj;
                    b.a;
                    StringBuilder sb = new StringBuilder("Fetch requested for config:");
                    sb.append(aVar.a());
                    sb.append(". IsAlreadyScheduled:");
                    sb.append(a(aVar.a()));
                    if (!a(aVar.a())) {
                        this.a.add(aVar);
                        if (!hasMessages(2)) {
                            sendEmptyMessage(2);
                            return;
                        }
                    } else {
                        b.a;
                        new StringBuilder("Config fetching already in progress:").append(aVar.a());
                        return;
                    }
                    break;
                case 2:
                    sendEmptyMessageDelayed(3, (long) (b.c.c * 1000));
                    return;
                case 3:
                    a(this.a);
                    this.a.clear();
                    ExecutorService executorService = this.d;
                    if (executorService == null || executorService.isShutdown()) {
                        this.d = Executors.newFixedThreadPool(1);
                        sendEmptyMessage(4);
                        return;
                    }
                case 4:
                    if (!this.b.isEmpty()) {
                        Entry entry = (Entry) this.b.entrySet().iterator().next();
                        this.c = (Map) entry.getValue();
                        this.b.remove(entry.getKey());
                        String str = (String) entry.getKey();
                        Map<String, a> map = this.c;
                        int i2 = b.c.b;
                        int i3 = b.c.a;
                        com.inmobi.commons.core.utilities.uid.d dVar = new com.inmobi.commons.core.utilities.uid.d(b.c.p.a);
                        int d2 = e.d();
                        if (d2 != 0 || !map.containsKey("root")) {
                            i = d2;
                        } else {
                            map = b.a((Map) map);
                            i = 1;
                        }
                        f fVar2 = new f(map, dVar, str, i3, i2, i);
                        if (map.containsKey("root")) {
                            fVar = new f(b.a((Map) map), dVar, b.c.e(), i3, i2, true, i);
                        }
                        this.d.execute(new e(this, fVar2, fVar));
                        return;
                    }
                    b.a;
                    sendEmptyMessage(5);
                    return;
                case 5:
                    ExecutorService executorService2 = this.d;
                    if (executorService2 != null && !executorService2.isShutdown()) {
                        this.c = null;
                        this.b.clear();
                        removeMessages(3);
                        this.d.shutdownNow();
                        break;
                    }
            }
        }

        private boolean a(String str) {
            boolean z = this.b.get(b.c.b(str)) != null && ((Map) this.b.get(b.c.b(str))).containsKey(str);
            Map<String, a> map = this.c;
            if (map == null || !map.containsKey(str)) {
                return z;
            }
            return true;
        }

        private void a(List<a> list) {
            for (int i = 0; i < list.size(); i++) {
                a aVar = (a) list.get(i);
                HashMap hashMap = (HashMap) this.b.get(b.c.b(aVar.a()));
                if (hashMap == null) {
                    hashMap = new HashMap();
                    this.b.put(b.c.b(aVar.a()), hashMap);
                }
                hashMap.put(aVar.a(), aVar);
            }
        }

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0061 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void a(com.inmobi.commons.core.configs.ConfigNetworkResponse.ConfigResponse r6) {
            /*
                r5 = this;
                com.inmobi.commons.core.configs.c r0 = new com.inmobi.commons.core.configs.c
                r0.<init>()
                boolean r1 = r6.a()
                if (r1 != 0) goto L_0x00ca
                com.inmobi.commons.core.configs.ConfigNetworkResponse$ConfigResponse$ConfigResponseStatus r1 = r6.a
                com.inmobi.commons.core.configs.ConfigNetworkResponse$ConfigResponse$ConfigResponseStatus r2 = com.inmobi.commons.core.configs.ConfigNetworkResponse.ConfigResponse.ConfigResponseStatus.NOT_MODIFIED
                if (r1 != r2) goto L_0x0032
                com.inmobi.commons.core.configs.b.a
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                java.lang.String r2 = "Config not modified status from server:"
                r1.<init>(r2)
                com.inmobi.commons.core.configs.a r2 = r6.b
                java.lang.String r2 = r2.a()
                r1.append(r2)
                com.inmobi.commons.core.configs.a r6 = r6.b
                java.lang.String r6 = r6.a()
                long r1 = java.lang.System.currentTimeMillis()
                r0.a(r6, r1)
                return
            L_0x0032:
                com.inmobi.commons.core.configs.a r1 = r6.b
                com.inmobi.commons.core.d.c r2 = r0.a     // Catch:{ JSONException -> 0x0061 }
                java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0061 }
                r3.<init>()     // Catch:{ JSONException -> 0x0061 }
                java.lang.String r4 = r1.a()     // Catch:{ JSONException -> 0x0061 }
                r3.append(r4)     // Catch:{ JSONException -> 0x0061 }
                java.lang.String r4 = "_config"
                r3.append(r4)     // Catch:{ JSONException -> 0x0061 }
                java.lang.String r3 = r3.toString()     // Catch:{ JSONException -> 0x0061 }
                org.json.JSONObject r4 = r1.b()     // Catch:{ JSONException -> 0x0061 }
                java.lang.String r4 = r4.toString()     // Catch:{ JSONException -> 0x0061 }
                r2.a(r3, r4)     // Catch:{ JSONException -> 0x0061 }
                java.lang.String r1 = r1.a()     // Catch:{ JSONException -> 0x0061 }
                long r2 = java.lang.System.currentTimeMillis()     // Catch:{ JSONException -> 0x0061 }
                r0.a(r1, r2)     // Catch:{ JSONException -> 0x0061 }
            L_0x0061:
                java.util.HashMap r0 = new java.util.HashMap     // Catch:{ Exception -> 0x0083 }
                r0.<init>()     // Catch:{ Exception -> 0x0083 }
                java.lang.String r1 = "configName"
                com.inmobi.commons.core.configs.a r2 = r6.b     // Catch:{ Exception -> 0x0083 }
                java.lang.String r2 = r2.a()     // Catch:{ Exception -> 0x0083 }
                r0.put(r1, r2)     // Catch:{ Exception -> 0x0083 }
                java.lang.String r1 = "latency"
                java.lang.String r2 = "2147483647"
                r0.put(r1, r2)     // Catch:{ Exception -> 0x0083 }
                com.inmobi.commons.core.e.b.a()     // Catch:{ Exception -> 0x0083 }
                java.lang.String r1 = "root"
                java.lang.String r2 = "ConfigFetched"
                com.inmobi.commons.core.e.b.a(r1, r2, r0)     // Catch:{ Exception -> 0x0083 }
                goto L_0x009a
            L_0x0083:
                r0 = move-exception
                com.inmobi.commons.core.configs.b.a
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                java.lang.String r2 = "Error in submitting telemetry event : ("
                r1.<init>(r2)
                java.lang.String r0 = r0.getMessage()
                r1.append(r0)
                java.lang.String r0 = ")"
                r1.append(r0)
            L_0x009a:
                com.inmobi.commons.core.configs.b.a     // Catch:{ JSONException -> 0x00c9 }
                java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x00c9 }
                java.lang.String r1 = "Config cached successfully:"
                r0.<init>(r1)     // Catch:{ JSONException -> 0x00c9 }
                com.inmobi.commons.core.configs.a r1 = r6.b     // Catch:{ JSONException -> 0x00c9 }
                java.lang.String r1 = r1.a()     // Catch:{ JSONException -> 0x00c9 }
                r0.append(r1)     // Catch:{ JSONException -> 0x00c9 }
                com.inmobi.commons.core.configs.b.a     // Catch:{ JSONException -> 0x00c9 }
                java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x00c9 }
                java.lang.String r1 = "Config cached successfully:"
                r0.<init>(r1)     // Catch:{ JSONException -> 0x00c9 }
                com.inmobi.commons.core.configs.a r1 = r6.b     // Catch:{ JSONException -> 0x00c9 }
                org.json.JSONObject r1 = r1.b()     // Catch:{ JSONException -> 0x00c9 }
                java.lang.String r1 = r1.toString()     // Catch:{ JSONException -> 0x00c9 }
                r0.append(r1)     // Catch:{ JSONException -> 0x00c9 }
                com.inmobi.commons.core.configs.a r6 = r6.b     // Catch:{ JSONException -> 0x00c9 }
                com.inmobi.commons.core.configs.b.b(r6)     // Catch:{ JSONException -> 0x00c9 }
            L_0x00c9:
                return
            L_0x00ca:
                com.inmobi.commons.core.configs.b.a
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                java.lang.String r1 = "Config fetching failed:"
                r0.<init>(r1)
                com.inmobi.commons.core.configs.a r1 = r6.b
                java.lang.String r1 = r1.a()
                r0.append(r1)
                java.lang.String r1 = ", Error code:"
                r0.append(r1)
                com.inmobi.commons.core.configs.d r6 = r6.c
                int r6 = r6.a
                r0.append(r6)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.inmobi.commons.core.configs.b.C0046b.a(com.inmobi.commons.core.configs.ConfigNetworkResponse$ConfigResponse):void");
        }

        public final void a() {
            sendEmptyMessage(4);
        }
    }

    /* compiled from: ConfigComponent */
    public interface c {
        void a(a aVar);
    }

    /* compiled from: ConfigComponent */
    static class d implements c {
        d() {
        }

        public final void a(a aVar) {
            b.c = (h) aVar;
        }
    }

    /* synthetic */ b(byte b2) {
        this();
    }

    public static b a() {
        return a.a;
    }

    private b() {
        this.g = false;
        b = new HashMap();
        this.e = new HandlerThread("ConfigBootstrapHandler");
        this.e.start();
        this.f = new C0046b(this.e.getLooper());
        c = new h();
    }

    public final synchronized void b() {
        if (!this.g) {
            this.g = true;
            com.inmobi.commons.core.e.b.a().a("root", c.f);
            if (d == null) {
                d = new d();
                a((a) c, (c) d);
            }
            for (Entry key : b.entrySet()) {
                a aVar = (a) key.getKey();
                c(aVar);
                b(aVar);
            }
        }
    }

    public final synchronized void c() {
        if (this.g) {
            this.g = false;
            this.f.sendEmptyMessage(5);
        }
    }

    /* access modifiers changed from: private */
    public static void b(a aVar) {
        ArrayList arrayList = (ArrayList) b.get(aVar);
        if (arrayList != null) {
            for (int i = 0; i < arrayList.size(); i++) {
                if (!(arrayList.get(i) == null || ((WeakReference) arrayList.get(i)).get() == null)) {
                    ((c) ((WeakReference) arrayList.get(i)).get()).a(aVar);
                }
            }
        }
    }

    public final synchronized void a(a aVar, c cVar) {
        Object obj;
        if (!this.g) {
            new StringBuilder("Config component not yet started, config can't be fetched. Requested type:").append(aVar.a());
            return;
        }
        ArrayList arrayList = (ArrayList) b.get(aVar);
        if (arrayList == null) {
            arrayList = new ArrayList();
        }
        if (cVar == null) {
            obj = null;
        } else {
            obj = new WeakReference(cVar);
        }
        arrayList.add(obj);
        b.put(aVar, arrayList);
        c(aVar);
    }

    public static void d() {
        String str = c.e.a;
        String str2 = c.e.b;
        if (str.trim().length() != 0 && a("7.2.6", str.trim())) {
            InternalLogLevel internalLogLevel = InternalLogLevel.DEBUG;
            String str3 = a;
            StringBuilder sb = new StringBuilder("A newer version (version ");
            sb.append(str);
            sb.append(") of the InMobi SDK is available! You are currently on an older version (Version 7.2.6). Please download the latest InMobi SDK from ");
            sb.append(str2);
            Logger.a(internalLogLevel, str3, sb.toString());
        }
    }

    private synchronized void c(a aVar) {
        c cVar = new c();
        if (!cVar.a("root")) {
            new StringBuilder("RootConfig not available. Fetching root and returning defaults for config type:").append(aVar.a());
            d(new h());
            return;
        }
        cVar.a((a) c);
        if (a(cVar.b("root"), c.a("root"))) {
            d(new h());
        }
        if (!cVar.a(aVar.a())) {
            new StringBuilder("Requested config not present. Returning default and fetching. Config type:").append(aVar.a());
            d(aVar.d());
            return;
        }
        cVar.a(aVar);
        if (a(cVar.b(aVar.a()), c.a(aVar.a()))) {
            new StringBuilder("Requested config expired. Returning currently cached and fetching. Config type:").append(aVar.a());
            d(aVar.d());
            return;
        }
        new StringBuilder("Serving config from cache. Config:").append(aVar.a());
    }

    private static boolean a(long j, long j2) {
        return System.currentTimeMillis() - j > j2 * 1000;
    }

    private void d(a aVar) {
        Message obtainMessage = this.f.obtainMessage();
        obtainMessage.what = 1;
        obtainMessage.obj = aVar;
        this.f.sendMessage(obtainMessage);
    }

    private static boolean a(String str, String str2) {
        String[] split = str.split("\\.");
        String[] split2 = str2.split("\\.");
        try {
            for (String valueOf : split) {
                if (Integer.valueOf(valueOf).intValue() < 0) {
                    return false;
                }
            }
            for (String valueOf2 : split2) {
                if (Integer.valueOf(valueOf2).intValue() < 0) {
                    return false;
                }
            }
            int length = split.length < split2.length ? split.length : split2.length;
            int i = 0;
            while (i < length) {
                if (split[i].equals(split2[i])) {
                    i++;
                } else if (Integer.valueOf(split[i]).intValue() < Integer.valueOf(split2[i]).intValue()) {
                    return true;
                } else {
                    return false;
                }
            }
            if (split.length < split2.length) {
                return true;
            }
            return false;
        } catch (NumberFormatException unused) {
            return false;
        }
    }

    static /* synthetic */ Map a(Map map) {
        HashMap hashMap = new HashMap(1);
        hashMap.put("root", map.get("root"));
        return hashMap;
    }
}
