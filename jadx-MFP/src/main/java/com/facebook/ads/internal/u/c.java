package com.facebook.ads.internal.u;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.facebook.ads.internal.k.d;
import com.facebook.ads.internal.k.e;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.ads.internal.protocol.h;
import com.facebook.ads.internal.settings.AdInternalSettings;
import com.facebook.ads.internal.v.a.m;
import com.facebook.ads.internal.w.b.i;
import com.facebook.ads.internal.w.b.n;
import com.facebook.ads.internal.w.b.u;
import java.io.File;
import java.security.MessageDigest;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import org.json.JSONException;

public class c {
    /* access modifiers changed from: private */
    @Nullable
    public static a a;
    private static final n j = new n();
    private static final ThreadPoolExecutor k = ((ThreadPoolExecutor) Executors.newCachedThreadPool(j));
    /* access modifiers changed from: private */
    public final Context b;
    /* access modifiers changed from: private */
    public final d c = d.a();
    private final com.facebook.ads.internal.r.a d = com.facebook.ads.internal.r.a.af(this.b);
    /* access modifiers changed from: private */
    public Map<String, String> e;
    private b f;
    /* access modifiers changed from: private */
    public b g;
    /* access modifiers changed from: private */
    public com.facebook.ads.internal.v.a.a h;
    /* access modifiers changed from: private */
    public final String i;

    public interface a {
        C0010c a(c cVar, b bVar);

        void a(c cVar, Map<String, String> map);
    }

    public interface b {
        void a(com.facebook.ads.internal.protocol.a aVar);

        void a(f fVar);
    }

    /* renamed from: com.facebook.ads.internal.u.c$c reason: collision with other inner class name */
    public static class C0010c {
        @Nullable
        public final f a;
        @Nullable
        public final com.facebook.ads.internal.protocol.a b;
    }

    public c(Context context) {
        String str;
        this.b = context.getApplicationContext();
        String urlPrefix = AdInternalSettings.getUrlPrefix();
        if (TextUtils.isEmpty(urlPrefix)) {
            str = "https://graph.facebook.com/network_ads_common";
        } else {
            str = String.format(Locale.US, "https://graph.%s.facebook.com/network_ads_common", new Object[]{urlPrefix});
        }
        this.i = str;
    }

    /* access modifiers changed from: private */
    public void a(com.facebook.ads.internal.protocol.a aVar) {
        b bVar = this.f;
        if (bVar != null) {
            bVar.a(aVar);
        }
        a();
    }

    private void a(f fVar) {
        b bVar = this.f;
        if (bVar != null) {
            bVar.a(fVar);
        }
        a();
    }

    /* access modifiers changed from: private */
    public void a(String str) {
        int i2;
        com.facebook.ads.internal.protocol.a aVar;
        Context context;
        Exception e2;
        String str2;
        int i3;
        Exception e3;
        try {
            e a2 = this.c.a(str);
            com.facebook.ads.internal.m.c a3 = a2.a();
            String str3 = null;
            if (a3 != null) {
                this.d.a(a3.b());
                if (AdInternalSettings.d) {
                    boolean z = true;
                    if (com.facebook.ads.internal.r.a.V(this.b)) {
                        context = this.b;
                        if (context == null) {
                            z = false;
                        }
                        if (z) {
                            try {
                                File file = new File(context.getFilesDir(), "com.facebook.ads.ipc");
                                if (!file.exists()) {
                                    z = file.createNewFile();
                                }
                            } catch (Exception e4) {
                                e2 = e4;
                            }
                        }
                        e2 = !z ? new Exception("Can't create ipc marker.") : null;
                        if (e2 != null) {
                            str2 = "ipc";
                            i3 = com.facebook.ads.internal.w.h.b.ac;
                        }
                    } else {
                        context = this.b;
                        if (context == null) {
                            z = false;
                        }
                        if (z) {
                            try {
                                File file2 = new File(context.getFilesDir(), "com.facebook.ads.ipc");
                                if (file2.exists()) {
                                    z = file2.delete();
                                }
                            } catch (Exception e5) {
                                e3 = e5;
                            }
                        }
                        e3 = !z ? new Exception("Can't delete ipc marker.") : null;
                        if (e2 != null) {
                            str2 = "ipc";
                            i3 = com.facebook.ads.internal.w.h.b.ac;
                        }
                    }
                    com.facebook.ads.internal.w.h.a.a(context, str2, i3, e2);
                }
                com.facebook.ads.internal.f.a.a(this.b, a3.c());
                a.a(a3.a().d(), this.g);
                com.facebook.ads.internal.w.g.a.a(this.b, k, a3);
            }
            switch (a2.b()) {
                case ADS:
                    if (com.facebook.ads.internal.r.a.z(this.b)) {
                        com.facebook.ads.internal.p.a.a(this.b, c());
                    }
                    f fVar = (f) a2;
                    if (a3 != null) {
                        if (a3.a().e()) {
                            a.a(str, this.g);
                        }
                        if (this.e != null) {
                            str3 = (String) this.e.get("CLIENT_REQUEST_ID");
                        }
                        String c2 = a2.c();
                        if (!TextUtils.isEmpty(c2) && !TextUtils.isEmpty(str3)) {
                            StringBuilder sb = new StringBuilder();
                            for (int i4 = 0; i4 < 32; i4++) {
                                char charAt = "73q8p304q6q511r89s8os2801s1o9sq1".charAt(i4);
                                if ((charAt < 'a' || charAt > 'm') && (charAt < 'A' || charAt > 'M')) {
                                    if ((charAt >= 'n' && charAt <= 'z') || (charAt >= 'N' && charAt <= 'Z')) {
                                        i2 = charAt - 13;
                                    }
                                    sb.append(charAt);
                                } else {
                                    i2 = charAt + 13;
                                }
                                charAt = (char) i2;
                                sb.append(charAt);
                            }
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append(str3);
                            sb2.append(c2);
                            sb2.append(sb.toString());
                            byte[] bytes = sb2.toString().getBytes("iso-8859-1");
                            MessageDigest instance = MessageDigest.getInstance("SHA-1");
                            instance.update(bytes, 0, bytes.length);
                            if (!a2.d().equals(i.a(instance.digest()))) {
                                com.facebook.ads.internal.w.h.a.b(this.b, "network", com.facebook.ads.internal.w.h.b.t, new h());
                            }
                            StringBuilder sb3 = new StringBuilder();
                            sb3.append(c2);
                            sb3.append(str3);
                            sb3.append(sb.toString());
                            byte[] bytes2 = sb3.toString().getBytes("iso-8859-1");
                            MessageDigest instance2 = MessageDigest.getInstance("SHA-1");
                            instance2.update(bytes2, 0, bytes2.length);
                            e.a((d) new com.facebook.ads.internal.k.a(c2, i.a(instance2.digest())), this.b);
                        }
                        if (!TextUtils.isEmpty(a2.e()) && !TextUtils.isEmpty(str3)) {
                            new com.facebook.ads.internal.q.a(this.b, str3, a2.e()).a();
                        }
                    }
                    a(fVar);
                    return;
                case ERROR:
                    g gVar = (g) a2;
                    String f2 = gVar.f();
                    AdErrorType adErrorTypeFromCode = AdErrorType.adErrorTypeFromCode(gVar.g(), AdErrorType.ERROR_MESSAGE);
                    if (f2 != null) {
                        str = f2;
                    }
                    aVar = com.facebook.ads.internal.protocol.a.a(adErrorTypeFromCode, str);
                    break;
                default:
                    aVar = com.facebook.ads.internal.protocol.a.a(AdErrorType.UNKNOWN_RESPONSE, str);
                    break;
            }
            a(aVar);
        } catch (Exception e6) {
            a(com.facebook.ads.internal.protocol.a.a(AdErrorType.PARSER_FAILURE, e6.getMessage()));
        }
    }

    /* access modifiers changed from: private */
    public com.facebook.ads.internal.v.a.b c() {
        return new com.facebook.ads.internal.v.a.b() {
            /* access modifiers changed from: 0000 */
            public void a(m mVar) {
                a.b(c.this.g);
                c.this.h = null;
                try {
                    com.facebook.ads.internal.v.a.n a2 = mVar.a();
                    if (a2 != null) {
                        String e = a2.e();
                        e a3 = c.this.c.a(e);
                        if (a3.b() == a.ERROR) {
                            g gVar = (g) a3;
                            String f = gVar.f();
                            AdErrorType adErrorTypeFromCode = AdErrorType.adErrorTypeFromCode(gVar.g(), AdErrorType.ERROR_MESSAGE);
                            c cVar = c.this;
                            if (f != null) {
                                e = f;
                            }
                            cVar.a(com.facebook.ads.internal.protocol.a.a(adErrorTypeFromCode, e));
                            return;
                        }
                    }
                } catch (JSONException unused) {
                }
                c.this.a(com.facebook.ads.internal.protocol.a.a(AdErrorType.NETWORK_ERROR, mVar.getMessage()));
            }

            public void a(com.facebook.ads.internal.v.a.n nVar) {
                if (nVar != null) {
                    String e = nVar.e();
                    a.b(c.this.g);
                    c.this.h = null;
                    c.this.a(e);
                }
            }

            public void a(Exception exc) {
                if (m.class.equals(exc.getClass())) {
                    a((m) exc);
                } else {
                    c.this.a(com.facebook.ads.internal.protocol.a.a(AdErrorType.NETWORK_ERROR, exc.getMessage()));
                }
            }
        };
    }

    public void a() {
        com.facebook.ads.internal.v.a.a aVar = this.h;
        if (aVar != null) {
            aVar.c(1);
            this.h.b(1);
            this.h = null;
        }
    }

    public void a(b bVar) {
        a(bVar, false);
    }

    public void a(final b bVar, final boolean z) {
        a();
        if (!z) {
            a aVar = a;
            if (aVar != null) {
                C0010c a2 = aVar.a(this, bVar);
                if (a2 != null) {
                    if (a2.a != null) {
                        a(a2.a);
                        return;
                    } else if (a2.b != null) {
                        a(a2.b);
                        return;
                    }
                }
            }
        }
        if (u.a(this.b) == com.facebook.ads.internal.w.b.u.a.NONE) {
            a(new com.facebook.ads.internal.protocol.a(AdErrorType.NETWORK_ERROR, "No network connection"));
            return;
        }
        this.g = bVar;
        com.facebook.ads.internal.l.a.a(this.b);
        if (a.a(bVar)) {
            String c2 = a.c(bVar);
            if (c2 != null) {
                a(c2);
            } else {
                a(com.facebook.ads.internal.protocol.a.a(AdErrorType.LOAD_TOO_FREQUENTLY, null));
            }
        } else {
            k.submit(new Runnable() {
                /* JADX WARNING: Can't wrap try/catch for region: R(10:8|(1:12)|13|14|15|16|(2:22|(1:24)(3:25|27|30))|26|27|30) */
                /* JADX WARNING: Code restructure failed: missing block: B:28:0x0118, code lost:
                    r0 = move-exception;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:29:0x0119, code lost:
                    com.facebook.ads.internal.u.c.a(r6.c, com.facebook.ads.internal.protocol.a.a(com.facebook.ads.internal.protocol.AdErrorType.AD_REQUEST_FAILED, r0.getMessage()));
                 */
                /* JADX WARNING: Failed to process nested try/catch */
                /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x00b5 */
                /* JADX WARNING: Removed duplicated region for block: B:24:0x00db A[Catch:{ Exception -> 0x0118 }] */
                /* JADX WARNING: Removed duplicated region for block: B:25:0x00dc A[Catch:{ Exception -> 0x0118 }] */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void run() {
                    /*
                        r6 = this;
                        com.facebook.ads.internal.u.c r0 = com.facebook.ads.internal.u.c.this
                        android.content.Context r0 = r0.b
                        com.facebook.ads.internal.g.b.a(r0)
                        com.facebook.ads.internal.u.c r0 = com.facebook.ads.internal.u.c.this
                        android.content.Context r0 = r0.b
                        com.facebook.ads.internal.n.d.a(r0)
                        com.facebook.ads.internal.u.b r0 = r3
                        com.facebook.ads.internal.protocol.g r0 = r0.f()
                        boolean r0 = r0.a()
                        if (r0 == 0) goto L_0x0044
                        com.facebook.ads.internal.u.b r0 = r3     // Catch:{ b -> 0x002a }
                        com.facebook.ads.internal.protocol.g r0 = r0.f()     // Catch:{ b -> 0x002a }
                        java.lang.String r1 = com.facebook.ads.internal.g.b.b     // Catch:{ b -> 0x002a }
                        r0.a(r1)     // Catch:{ b -> 0x002a }
                        goto L_0x0034
                    L_0x002a:
                        r0 = move-exception
                        com.facebook.ads.internal.u.c r1 = com.facebook.ads.internal.u.c.this
                        com.facebook.ads.internal.protocol.a r0 = com.facebook.ads.internal.protocol.a.a(r0)
                        r1.a(r0)
                    L_0x0034:
                        com.facebook.ads.internal.u.c r0 = com.facebook.ads.internal.u.c.this
                        com.facebook.ads.internal.u.b r1 = r3
                        com.facebook.ads.internal.protocol.g r1 = r1.f()
                        java.lang.String r1 = r1.b()
                        r0.a(r1)
                        return
                    L_0x0044:
                        com.facebook.ads.internal.u.c r0 = com.facebook.ads.internal.u.c.this
                        com.facebook.ads.internal.u.b r1 = r3
                        java.util.Map r1 = r1.g()
                        r0.e = r1
                        boolean r0 = r4
                        if (r0 == 0) goto L_0x0066
                        com.facebook.ads.internal.u.c$a r0 = com.facebook.ads.internal.u.c.a
                        if (r0 == 0) goto L_0x0066
                        com.facebook.ads.internal.u.c$a r0 = com.facebook.ads.internal.u.c.a
                        com.facebook.ads.internal.u.c r1 = com.facebook.ads.internal.u.c.this
                        java.util.Map r2 = r1.e
                        r0.a(r1, r2)
                    L_0x0066:
                        com.facebook.ads.internal.u.c r0 = com.facebook.ads.internal.u.c.this     // Catch:{ Exception -> 0x00b5 }
                        java.util.Map r0 = r0.e     // Catch:{ Exception -> 0x00b5 }
                        java.lang.String r1 = "M_BANNER_KEY"
                        java.lang.String r2 = new java.lang.String     // Catch:{ Exception -> 0x00b5 }
                        java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00b5 }
                        r3.<init>()     // Catch:{ Exception -> 0x00b5 }
                        com.facebook.ads.internal.u.c r4 = com.facebook.ads.internal.u.c.this     // Catch:{ Exception -> 0x00b5 }
                        android.content.Context r4 = r4.b     // Catch:{ Exception -> 0x00b5 }
                        java.lang.String r4 = r4.getPackageName()     // Catch:{ Exception -> 0x00b5 }
                        r3.append(r4)     // Catch:{ Exception -> 0x00b5 }
                        java.lang.String r4 = " "
                        r3.append(r4)     // Catch:{ Exception -> 0x00b5 }
                        com.facebook.ads.internal.u.c r4 = com.facebook.ads.internal.u.c.this     // Catch:{ Exception -> 0x00b5 }
                        android.content.Context r4 = r4.b     // Catch:{ Exception -> 0x00b5 }
                        android.content.pm.PackageManager r4 = r4.getPackageManager()     // Catch:{ Exception -> 0x00b5 }
                        com.facebook.ads.internal.u.c r5 = com.facebook.ads.internal.u.c.this     // Catch:{ Exception -> 0x00b5 }
                        android.content.Context r5 = r5.b     // Catch:{ Exception -> 0x00b5 }
                        java.lang.String r5 = r5.getPackageName()     // Catch:{ Exception -> 0x00b5 }
                        java.lang.String r4 = r4.getInstallerPackageName(r5)     // Catch:{ Exception -> 0x00b5 }
                        r3.append(r4)     // Catch:{ Exception -> 0x00b5 }
                        java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x00b5 }
                        byte[] r3 = r3.getBytes()     // Catch:{ Exception -> 0x00b5 }
                        r4 = 2
                        byte[] r3 = android.util.Base64.encode(r3, r4)     // Catch:{ Exception -> 0x00b5 }
                        r2.<init>(r3)     // Catch:{ Exception -> 0x00b5 }
                        r0.put(r1, r2)     // Catch:{ Exception -> 0x00b5 }
                    L_0x00b5:
                        com.facebook.ads.internal.u.b r0 = r3     // Catch:{ Exception -> 0x0118 }
                        com.facebook.ads.internal.protocol.e r0 = r0.a()     // Catch:{ Exception -> 0x0118 }
                        com.facebook.ads.internal.protocol.e r1 = com.facebook.ads.internal.protocol.e.NATIVE_250     // Catch:{ Exception -> 0x0118 }
                        if (r0 == r1) goto L_0x00de
                        com.facebook.ads.internal.u.b r0 = r3     // Catch:{ Exception -> 0x0118 }
                        com.facebook.ads.internal.protocol.e r0 = r0.a()     // Catch:{ Exception -> 0x0118 }
                        com.facebook.ads.internal.protocol.e r1 = com.facebook.ads.internal.protocol.e.NATIVE_UNKNOWN     // Catch:{ Exception -> 0x0118 }
                        if (r0 == r1) goto L_0x00de
                        com.facebook.ads.internal.u.b r0 = r3     // Catch:{ Exception -> 0x0118 }
                        com.facebook.ads.internal.protocol.e r0 = r0.a()     // Catch:{ Exception -> 0x0118 }
                        com.facebook.ads.internal.protocol.e r1 = com.facebook.ads.internal.protocol.e.NATIVE_BANNER     // Catch:{ Exception -> 0x0118 }
                        if (r0 == r1) goto L_0x00de
                        com.facebook.ads.internal.u.b r0 = r3     // Catch:{ Exception -> 0x0118 }
                        com.facebook.ads.internal.protocol.e r0 = r0.a()     // Catch:{ Exception -> 0x0118 }
                        if (r0 != 0) goto L_0x00dc
                        goto L_0x00de
                    L_0x00dc:
                        r0 = 0
                        goto L_0x00df
                    L_0x00de:
                        r0 = 1
                    L_0x00df:
                        com.facebook.ads.internal.u.c r1 = com.facebook.ads.internal.u.c.this     // Catch:{ Exception -> 0x0118 }
                        com.facebook.ads.internal.u.c r2 = com.facebook.ads.internal.u.c.this     // Catch:{ Exception -> 0x0118 }
                        android.content.Context r2 = r2.b     // Catch:{ Exception -> 0x0118 }
                        com.facebook.ads.internal.v.a.a r0 = com.facebook.ads.internal.w.e.d.a(r2, r0)     // Catch:{ Exception -> 0x0118 }
                        r1.h = r0     // Catch:{ Exception -> 0x0118 }
                        com.facebook.ads.internal.u.c r0 = com.facebook.ads.internal.u.c.this     // Catch:{ Exception -> 0x0118 }
                        com.facebook.ads.internal.v.a.a r0 = r0.h     // Catch:{ Exception -> 0x0118 }
                        com.facebook.ads.internal.u.c r1 = com.facebook.ads.internal.u.c.this     // Catch:{ Exception -> 0x0118 }
                        java.lang.String r1 = r1.i     // Catch:{ Exception -> 0x0118 }
                        com.facebook.ads.internal.u.c r2 = com.facebook.ads.internal.u.c.this     // Catch:{ Exception -> 0x0118 }
                        com.facebook.ads.internal.v.a.a r2 = r2.h     // Catch:{ Exception -> 0x0118 }
                        com.facebook.ads.internal.v.a.p r2 = r2.a()     // Catch:{ Exception -> 0x0118 }
                        com.facebook.ads.internal.u.c r3 = com.facebook.ads.internal.u.c.this     // Catch:{ Exception -> 0x0118 }
                        java.util.Map r3 = r3.e     // Catch:{ Exception -> 0x0118 }
                        com.facebook.ads.internal.v.a.p r2 = r2.a(r3)     // Catch:{ Exception -> 0x0118 }
                        com.facebook.ads.internal.u.c r3 = com.facebook.ads.internal.u.c.this     // Catch:{ Exception -> 0x0118 }
                        com.facebook.ads.internal.v.a.b r3 = r3.c()     // Catch:{ Exception -> 0x0118 }
                        r0.b(r1, r2, r3)     // Catch:{ Exception -> 0x0118 }
                        goto L_0x0128
                    L_0x0118:
                        r0 = move-exception
                        com.facebook.ads.internal.u.c r1 = com.facebook.ads.internal.u.c.this
                        com.facebook.ads.internal.protocol.AdErrorType r2 = com.facebook.ads.internal.protocol.AdErrorType.AD_REQUEST_FAILED
                        java.lang.String r0 = r0.getMessage()
                        com.facebook.ads.internal.protocol.a r0 = com.facebook.ads.internal.protocol.a.a(r2, r0)
                        r1.a(r0)
                    L_0x0128:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.u.c.AnonymousClass1.run():void");
                }
            });
        }
    }

    public void a(b bVar) {
        this.f = bVar;
    }
}
