package com.facebook.ads.internal.v.a;

import android.os.Build.VERSION;
import android.support.annotation.AnyThread;
import android.support.annotation.WorkerThread;
import java.io.OutputStream;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

@WorkerThread
public class a {
    private static int[] f = new int[20];
    private static final String g = a.class.getSimpleName();
    private static C0011a j;
    protected final q a = new f() {
    };
    protected final d b = new e();
    protected r c = new g();
    protected int d = 2000;
    protected int e = 8000;
    private int h = 3;
    private Map<String, String> i = new TreeMap();
    private boolean k;
    private Set<String> l;
    private Set<String> m;

    /* renamed from: com.facebook.ads.internal.v.a.a$a reason: collision with other inner class name */
    public interface C0011a {
        @WorkerThread
        Map<String, String> a();
    }

    static {
        if (VERSION.SDK_INT < 8) {
            System.setProperty("http.keepAlive", "false");
        }
        if (VERSION.SDK_INT > 8 && CookieHandler.getDefault() == null) {
            CookieHandler.setDefault(new CookieManager());
        }
    }

    public static synchronized void a(C0011a aVar) {
        synchronized (a.class) {
            j = aVar;
        }
    }

    /* access modifiers changed from: protected */
    public int a(int i2) {
        return f[i2 + 2] * 1000;
    }

    /* access modifiers changed from: protected */
    public int a(HttpURLConnection httpURLConnection, byte[] bArr) {
        OutputStream outputStream = null;
        try {
            outputStream = this.a.a(httpURLConnection);
            if (outputStream != null) {
                this.a.a(outputStream, bArr);
            }
            int responseCode = httpURLConnection.getResponseCode();
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (Exception unused) {
                }
            }
            return responseCode;
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (Exception unused2) {
                }
            }
        }
    }

    @AnyThread
    public a a(String str, String str2) {
        this.i.put(str, str2);
        return this;
    }

    public n a(l lVar) {
        long currentTimeMillis = System.currentTimeMillis();
        int i2 = 0;
        while (i2 < this.h) {
            try {
                c(a(i2));
                if (this.c.a()) {
                    r rVar = this.c;
                    StringBuilder sb = new StringBuilder();
                    sb.append(i2 + 1);
                    sb.append("of");
                    sb.append(this.h);
                    sb.append(", trying ");
                    sb.append(lVar.a());
                    rVar.a(sb.toString());
                }
                currentTimeMillis = System.currentTimeMillis();
                n a2 = a(lVar.a(), lVar.b(), lVar.c(), lVar.d());
                if (a2 != null) {
                    return a2;
                }
                i2++;
            } catch (m e2) {
                if (a((Throwable) e2, currentTimeMillis) && i2 < this.h - 1) {
                    continue;
                } else if (!this.a.a(e2) || i2 >= this.h - 1) {
                    throw e2;
                } else {
                    try {
                        Thread.sleep((long) this.d);
                    } catch (InterruptedException unused) {
                        throw e2;
                    }
                }
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't wrap try/catch for region: R(5:84|85|86|87|88) */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x00d5, code lost:
        r7 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:?, code lost:
        r7.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x010b, code lost:
        throw new com.facebook.ads.internal.v.a.m(r7, null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x0111, code lost:
        throw new com.facebook.ads.internal.v.a.m(r7, null);
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:72:0x00e4, B:84:0x0103] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:84:0x0103 */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x011a  */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x0121  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.facebook.ads.internal.v.a.n a(java.lang.String r6, com.facebook.ads.internal.v.a.j r7, java.lang.String r8, byte[] r9) {
        /*
            r5 = this;
            r0 = 0
            r1 = 0
            r5.k = r0     // Catch:{ Exception -> 0x00dc, all -> 0x00d9 }
            java.net.HttpURLConnection r6 = r5.a(r6)     // Catch:{ Exception -> 0x00dc, all -> 0x00d9 }
            r5.a(r6, r7, r8)     // Catch:{ Exception -> 0x00d7 }
            java.util.Map<java.lang.String, java.lang.String> r7 = r5.i     // Catch:{ Exception -> 0x00d7 }
            java.util.Set r7 = r7.keySet()     // Catch:{ Exception -> 0x00d7 }
            java.util.Iterator r7 = r7.iterator()     // Catch:{ Exception -> 0x00d7 }
        L_0x0015:
            boolean r8 = r7.hasNext()     // Catch:{ Exception -> 0x00d7 }
            if (r8 == 0) goto L_0x002d
            java.lang.Object r8 = r7.next()     // Catch:{ Exception -> 0x00d7 }
            java.lang.String r8 = (java.lang.String) r8     // Catch:{ Exception -> 0x00d7 }
            java.util.Map<java.lang.String, java.lang.String> r2 = r5.i     // Catch:{ Exception -> 0x00d7 }
            java.lang.Object r2 = r2.get(r8)     // Catch:{ Exception -> 0x00d7 }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ Exception -> 0x00d7 }
            r6.setRequestProperty(r8, r2)     // Catch:{ Exception -> 0x00d7 }
            goto L_0x0015
        L_0x002d:
            java.lang.Class<com.facebook.ads.internal.v.a.a> r7 = com.facebook.ads.internal.v.a.a.class
            monitor-enter(r7)     // Catch:{ Exception -> 0x00d7 }
            com.facebook.ads.internal.v.a.a$a r8 = j     // Catch:{ all -> 0x00d2 }
            if (r8 == 0) goto L_0x0058
            com.facebook.ads.internal.v.a.a$a r8 = j     // Catch:{ all -> 0x00d2 }
            java.util.Map r8 = r8.a()     // Catch:{ all -> 0x00d2 }
            java.util.Set r2 = r8.keySet()     // Catch:{ all -> 0x00d2 }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x00d2 }
        L_0x0042:
            boolean r3 = r2.hasNext()     // Catch:{ all -> 0x00d2 }
            if (r3 == 0) goto L_0x0058
            java.lang.Object r3 = r2.next()     // Catch:{ all -> 0x00d2 }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ all -> 0x00d2 }
            java.lang.Object r4 = r8.get(r3)     // Catch:{ all -> 0x00d2 }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ all -> 0x00d2 }
            r6.setRequestProperty(r3, r4)     // Catch:{ all -> 0x00d2 }
            goto L_0x0042
        L_0x0058:
            monitor-exit(r7)     // Catch:{ all -> 0x00d2 }
            com.facebook.ads.internal.v.a.r r7 = r5.c     // Catch:{ Exception -> 0x00d7 }
            boolean r7 = r7.a()     // Catch:{ Exception -> 0x00d7 }
            if (r7 == 0) goto L_0x0066
            com.facebook.ads.internal.v.a.r r7 = r5.c     // Catch:{ Exception -> 0x00d7 }
            r7.a(r6, r9)     // Catch:{ Exception -> 0x00d7 }
        L_0x0066:
            r6.connect()     // Catch:{ Exception -> 0x00d7 }
            r7 = 1
            r5.k = r7     // Catch:{ Exception -> 0x00d7 }
            java.util.Set<java.lang.String> r8 = r5.m     // Catch:{ Exception -> 0x00d7 }
            if (r8 == 0) goto L_0x007a
            java.util.Set<java.lang.String> r8 = r5.m     // Catch:{ Exception -> 0x00d7 }
            boolean r8 = r8.isEmpty()     // Catch:{ Exception -> 0x00d7 }
            if (r8 != 0) goto L_0x007a
            r8 = 1
            goto L_0x007b
        L_0x007a:
            r8 = 0
        L_0x007b:
            java.util.Set<java.lang.String> r2 = r5.l     // Catch:{ Exception -> 0x00d7 }
            if (r2 == 0) goto L_0x0088
            java.util.Set<java.lang.String> r2 = r5.l     // Catch:{ Exception -> 0x00d7 }
            boolean r2 = r2.isEmpty()     // Catch:{ Exception -> 0x00d7 }
            if (r2 != 0) goto L_0x0088
            goto L_0x0089
        L_0x0088:
            r7 = 0
        L_0x0089:
            boolean r0 = r6 instanceof javax.net.ssl.HttpsURLConnection     // Catch:{ Exception -> 0x00d7 }
            if (r0 == 0) goto L_0x00a4
            if (r8 != 0) goto L_0x0091
            if (r7 == 0) goto L_0x00a4
        L_0x0091:
            r7 = r6
            javax.net.ssl.HttpsURLConnection r7 = (javax.net.ssl.HttpsURLConnection) r7     // Catch:{ Exception -> 0x009c }
            java.util.Set<java.lang.String> r8 = r5.m     // Catch:{ Exception -> 0x009c }
            java.util.Set<java.lang.String> r0 = r5.l     // Catch:{ Exception -> 0x009c }
            com.facebook.ads.internal.v.a.o.a(r7, r8, r0)     // Catch:{ Exception -> 0x009c }
            goto L_0x00a4
        L_0x009c:
            r7 = move-exception
            java.lang.String r8 = g     // Catch:{ Exception -> 0x00d7 }
            java.lang.String r0 = "Unable to validate SSL certificates."
            android.util.Log.e(r8, r0, r7)     // Catch:{ Exception -> 0x00d7 }
        L_0x00a4:
            boolean r7 = r6.getDoOutput()     // Catch:{ Exception -> 0x00d7 }
            if (r7 == 0) goto L_0x00af
            if (r9 == 0) goto L_0x00af
            r5.a(r6, r9)     // Catch:{ Exception -> 0x00d7 }
        L_0x00af:
            boolean r7 = r6.getDoInput()     // Catch:{ Exception -> 0x00d7 }
            if (r7 == 0) goto L_0x00ba
            com.facebook.ads.internal.v.a.n r7 = r5.a(r6)     // Catch:{ Exception -> 0x00d7 }
            goto L_0x00bf
        L_0x00ba:
            com.facebook.ads.internal.v.a.n r7 = new com.facebook.ads.internal.v.a.n     // Catch:{ Exception -> 0x00d7 }
            r7.<init>(r6, r1)     // Catch:{ Exception -> 0x00d7 }
        L_0x00bf:
            com.facebook.ads.internal.v.a.r r8 = r5.c
            boolean r8 = r8.a()
            if (r8 == 0) goto L_0x00cc
            com.facebook.ads.internal.v.a.r r8 = r5.c
            r8.a(r7)
        L_0x00cc:
            if (r6 == 0) goto L_0x00d1
            r6.disconnect()
        L_0x00d1:
            return r7
        L_0x00d2:
            r8 = move-exception
            monitor-exit(r7)     // Catch:{ all -> 0x00d2 }
            throw r8     // Catch:{ Exception -> 0x00d7 }
        L_0x00d5:
            r7 = move-exception
            goto L_0x0112
        L_0x00d7:
            r7 = move-exception
            goto L_0x00de
        L_0x00d9:
            r7 = move-exception
            r6 = r1
            goto L_0x0112
        L_0x00dc:
            r7 = move-exception
            r6 = r1
        L_0x00de:
            com.facebook.ads.internal.v.a.n r1 = r5.b(r6)     // Catch:{ Exception -> 0x0103 }
            if (r1 == 0) goto L_0x00fd
            int r8 = r1.a()     // Catch:{ all -> 0x00d5 }
            if (r8 <= 0) goto L_0x00fd
            com.facebook.ads.internal.v.a.r r7 = r5.c
            boolean r7 = r7.a()
            if (r7 == 0) goto L_0x00f7
            com.facebook.ads.internal.v.a.r r7 = r5.c
            r7.a(r1)
        L_0x00f7:
            if (r6 == 0) goto L_0x00fc
            r6.disconnect()
        L_0x00fc:
            return r1
        L_0x00fd:
            com.facebook.ads.internal.v.a.m r8 = new com.facebook.ads.internal.v.a.m     // Catch:{ all -> 0x00d5 }
            r8.<init>(r7, r1)     // Catch:{ all -> 0x00d5 }
            throw r8     // Catch:{ all -> 0x00d5 }
        L_0x0103:
            r7.printStackTrace()     // Catch:{ all -> 0x010c }
            com.facebook.ads.internal.v.a.m r8 = new com.facebook.ads.internal.v.a.m     // Catch:{ all -> 0x00d5 }
            r8.<init>(r7, r1)     // Catch:{ all -> 0x00d5 }
            throw r8     // Catch:{ all -> 0x00d5 }
        L_0x010c:
            com.facebook.ads.internal.v.a.m r8 = new com.facebook.ads.internal.v.a.m     // Catch:{ all -> 0x00d5 }
            r8.<init>(r7, r1)     // Catch:{ all -> 0x00d5 }
            throw r8     // Catch:{ all -> 0x00d5 }
        L_0x0112:
            com.facebook.ads.internal.v.a.r r8 = r5.c
            boolean r8 = r8.a()
            if (r8 == 0) goto L_0x011f
            com.facebook.ads.internal.v.a.r r8 = r5.c
            r8.a(r1)
        L_0x011f:
            if (r6 == 0) goto L_0x0124
            r6.disconnect()
        L_0x0124:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.v.a.a.a(java.lang.String, com.facebook.ads.internal.v.a.j, java.lang.String, byte[]):com.facebook.ads.internal.v.a.n");
    }

    public n a(String str, p pVar) {
        return b((l) new i(str, pVar));
    }

    public n a(String str, String str2, byte[] bArr) {
        return b((l) new k(str, null, str2, bArr));
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0020 A[SYNTHETIC, Splitter:B:16:0x0020] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.facebook.ads.internal.v.a.n a(java.net.HttpURLConnection r4) {
        /*
            r3 = this;
            r0 = 0
            com.facebook.ads.internal.v.a.q r1 = r3.a     // Catch:{ all -> 0x001c }
            java.io.InputStream r1 = r1.b(r4)     // Catch:{ all -> 0x001c }
            if (r1 == 0) goto L_0x000f
            com.facebook.ads.internal.v.a.q r0 = r3.a     // Catch:{ all -> 0x001a }
            byte[] r0 = r0.a(r1)     // Catch:{ all -> 0x001a }
        L_0x000f:
            com.facebook.ads.internal.v.a.n r2 = new com.facebook.ads.internal.v.a.n     // Catch:{ all -> 0x001a }
            r2.<init>(r4, r0)     // Catch:{ all -> 0x001a }
            if (r1 == 0) goto L_0x0019
            r1.close()     // Catch:{ Exception -> 0x0019 }
        L_0x0019:
            return r2
        L_0x001a:
            r4 = move-exception
            goto L_0x001e
        L_0x001c:
            r4 = move-exception
            r1 = r0
        L_0x001e:
            if (r1 == 0) goto L_0x0023
            r1.close()     // Catch:{ Exception -> 0x0023 }
        L_0x0023:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.v.a.a.a(java.net.HttpURLConnection):com.facebook.ads.internal.v.a.n");
    }

    public p a() {
        return new p();
    }

    /* access modifiers changed from: protected */
    public HttpURLConnection a(String str) {
        try {
            new URL(str);
            return this.a.a(str);
        } catch (MalformedURLException e2) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(" is not a valid URL");
            throw new IllegalArgumentException(sb.toString(), e2);
        }
    }

    /* access modifiers changed from: protected */
    public void a(l lVar, b bVar) {
        this.b.a(this, bVar).a(lVar);
    }

    public void a(String str, p pVar, b bVar) {
        a((l) new i(str, pVar), bVar);
    }

    /* access modifiers changed from: protected */
    public void a(HttpURLConnection httpURLConnection, j jVar, String str) {
        httpURLConnection.setConnectTimeout(this.d);
        httpURLConnection.setReadTimeout(this.e);
        this.a.a(httpURLConnection, jVar, str);
    }

    @AnyThread
    public void a(Set<String> set) {
        this.m = set;
    }

    /* access modifiers changed from: protected */
    public boolean a(Throwable th, long j2) {
        long currentTimeMillis = (System.currentTimeMillis() - j2) + 10;
        if (this.c.a()) {
            r rVar = this.c;
            StringBuilder sb = new StringBuilder();
            sb.append("ELAPSED TIME = ");
            sb.append(currentTimeMillis);
            sb.append(", CT = ");
            sb.append(this.d);
            sb.append(", RT = ");
            sb.append(this.e);
            rVar.a(sb.toString());
        }
        boolean z = true;
        if (this.k) {
            if (currentTimeMillis < ((long) this.e)) {
                z = false;
            }
            return z;
        }
        if (currentTimeMillis < ((long) this.d)) {
            z = false;
        }
        return z;
    }

    public n b(l lVar) {
        try {
            return a(lVar.a(), lVar.b(), lVar.c(), lVar.d());
        } catch (m e2) {
            this.a.a(e2);
            return null;
        } catch (Exception e3) {
            this.a.a(new m(e3, null));
            return null;
        }
    }

    public n b(String str, p pVar) {
        return b((l) new k(str, pVar));
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x001e A[SYNTHETIC, Splitter:B:16:0x001e] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.facebook.ads.internal.v.a.n b(java.net.HttpURLConnection r4) {
        /*
            r3 = this;
            r0 = 0
            java.io.InputStream r1 = r4.getErrorStream()     // Catch:{ all -> 0x001a }
            if (r1 == 0) goto L_0x000d
            com.facebook.ads.internal.v.a.q r0 = r3.a     // Catch:{ all -> 0x0018 }
            byte[] r0 = r0.a(r1)     // Catch:{ all -> 0x0018 }
        L_0x000d:
            com.facebook.ads.internal.v.a.n r2 = new com.facebook.ads.internal.v.a.n     // Catch:{ all -> 0x0018 }
            r2.<init>(r4, r0)     // Catch:{ all -> 0x0018 }
            if (r1 == 0) goto L_0x0017
            r1.close()     // Catch:{ Exception -> 0x0017 }
        L_0x0017:
            return r2
        L_0x0018:
            r4 = move-exception
            goto L_0x001c
        L_0x001a:
            r4 = move-exception
            r1 = r0
        L_0x001c:
            if (r1 == 0) goto L_0x0021
            r1.close()     // Catch:{ Exception -> 0x0021 }
        L_0x0021:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.v.a.a.b(java.net.HttpURLConnection):com.facebook.ads.internal.v.a.n");
    }

    public void b() {
        this.i.clear();
    }

    @AnyThread
    public void b(int i2) {
        if (i2 < 1 || i2 > 18) {
            throw new IllegalArgumentException("Maximum retries must be between 1 and 18");
        }
        this.h = i2;
    }

    public void b(String str, p pVar, b bVar) {
        a((l) new k(str, pVar), bVar);
    }

    @AnyThread
    public void b(Set<String> set) {
        this.l = set;
    }

    @AnyThread
    public void c(int i2) {
        this.d = i2;
    }

    @AnyThread
    public void d(int i2) {
        this.e = i2;
    }
}
