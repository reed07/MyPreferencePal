package com.facebook.ads.internal.n;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import com.facebook.ads.internal.l.b;
import com.facebook.ads.internal.settings.AdInternalSettings;
import com.facebook.ads.internal.v.a.a.C0011a;
import com.facebook.ads.internal.w.b.f;
import com.facebook.ads.internal.w.b.f.a;
import com.facebook.ads.internal.w.b.h;
import com.facebook.ads.internal.w.b.i;
import com.facebook.ads.internal.w.b.k;
import com.facebook.ads.internal.w.b.o;
import com.facebook.ads.internal.w.b.u;
import com.facebook.ads.internal.w.b.v;
import com.facebook.ads.internal.w.b.x;
import com.facebook.share.internal.MessengerShareContentUtility;
import java.io.File;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class d {
    private static final AtomicBoolean a = new AtomicBoolean();
    /* access modifiers changed from: private */
    public static final AtomicInteger b = new AtomicInteger(0);
    /* access modifiers changed from: private */
    public static String c = null;
    private static final a d = f.a();
    @Nullable
    private static String e = null;
    private final Context f;
    private final b g;

    public d(Context context, boolean z) {
        this.f = context;
        this.g = new b(context);
        a(context, z);
    }

    private static String a(Context context, String str, String str2) {
        Class cls = Class.forName(str);
        Constructor declaredConstructor = cls.getDeclaredConstructor(new Class[]{Context.class, Class.forName(str2)});
        declaredConstructor.setAccessible(true);
        try {
            return (String) cls.getMethod("getUserAgentString", new Class[0]).invoke(declaredConstructor.newInstance(new Object[]{context, null}), new Object[0]);
        } finally {
            declaredConstructor.setAccessible(false);
        }
    }

    public static String a(b bVar, Context context, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append(b(context, z));
        sb.append(" [FBAN/AudienceNetworkForAndroid;FBSN/");
        sb.append("Android");
        sb.append(";FBSV/");
        sb.append(b.a);
        sb.append(";FBAB/");
        sb.append(bVar.f());
        sb.append(";FBAV/");
        sb.append(bVar.g());
        sb.append(";FBBV/");
        sb.append(bVar.h());
        sb.append(";FBVS/");
        sb.append("5.1.0");
        sb.append(";FBLC/");
        sb.append(Locale.getDefault().toString());
        sb.append("]");
        return sb.toString();
    }

    @WorkerThread
    public static void a(Context context) {
        if (context != null) {
            final Context applicationContext = context.getApplicationContext();
            com.facebook.ads.internal.v.a.a.a((C0011a) new C0011a() {
                public Map<String, String> a() {
                    HashMap hashMap = new HashMap();
                    if (!com.facebook.ads.internal.g.b.c) {
                        hashMap.put("X-FB-Pool-Routing-Token", new d(applicationContext, true).a());
                    }
                    return hashMap;
                }
            });
        }
    }

    private static void a(final Context context, boolean z) {
        if (b.compareAndSet(0, 1)) {
            try {
                o.a();
                final SharedPreferences sharedPreferences = context.getSharedPreferences(com.facebook.ads.internal.w.f.a.a("FBAdPrefs", context), 0);
                b bVar = new b(context);
                StringBuilder sb = new StringBuilder();
                sb.append("AFP;");
                sb.append(bVar.g());
                final String sb2 = sb.toString();
                c = sharedPreferences.getString(sb2, null);
                FutureTask futureTask = new FutureTask(new Callable<Boolean>() {
                    /* renamed from: a */
                    public Boolean call() {
                        Context context = context;
                        d.c = d.b(context, context.getPackageName());
                        sharedPreferences.edit().putString(sb2, d.c).apply();
                        d.b.set(2);
                        return Boolean.valueOf(true);
                    }
                });
                Executors.newSingleThreadExecutor().submit(futureTask);
                if (z) {
                    futureTask.get();
                }
            } catch (Exception unused) {
                b.set(0);
            }
        }
    }

    /* access modifiers changed from: private */
    @Nullable
    public static String b(Context context, String str) {
        try {
            return i.a(new File(context.getPackageManager().getApplicationInfo(str, 0).sourceDir));
        } catch (Exception e2) {
            if (a.compareAndSet(false, true)) {
                com.facebook.ads.internal.w.h.a.b(context.getApplicationContext(), MessengerShareContentUtility.TEMPLATE_GENERIC_TYPE, com.facebook.ads.internal.w.h.b.A, e2);
            }
            return null;
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(6:30|29|31|32|33|34) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x0044 */
    @android.support.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String b(android.content.Context r2, boolean r3) {
        /*
            if (r2 != 0) goto L_0x0005
            java.lang.String r2 = "Unknown"
            return r2
        L_0x0005:
            if (r3 == 0) goto L_0x000e
            java.lang.String r2 = "http.agent"
            java.lang.String r2 = java.lang.System.getProperty(r2)
            return r2
        L_0x000e:
            java.lang.String r3 = e
            if (r3 == 0) goto L_0x0013
            return r3
        L_0x0013:
            java.lang.Class<com.facebook.ads.internal.n.d> r3 = com.facebook.ads.internal.n.d.class
            monitor-enter(r3)
            java.lang.String r0 = e     // Catch:{ all -> 0x005e }
            if (r0 == 0) goto L_0x001e
            java.lang.String r2 = e     // Catch:{ all -> 0x005e }
            monitor-exit(r3)     // Catch:{ all -> 0x005e }
            return r2
        L_0x001e:
            int r0 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x005e }
            r1 = 17
            if (r0 < r1) goto L_0x002e
            java.lang.String r0 = android.webkit.WebSettings.getDefaultUserAgent(r2)     // Catch:{ Exception -> 0x002e }
            e = r0     // Catch:{ Exception -> 0x002e }
            java.lang.String r2 = e     // Catch:{ Exception -> 0x002e }
            monitor-exit(r3)     // Catch:{ all -> 0x005e }
            return r2
        L_0x002e:
            java.lang.String r0 = "android.webkit.WebSettings"
            java.lang.String r1 = "android.webkit.WebView"
            java.lang.String r0 = a(r2, r0, r1)     // Catch:{ Exception -> 0x0039 }
            e = r0     // Catch:{ Exception -> 0x0039 }
            goto L_0x005a
        L_0x0039:
            java.lang.String r0 = "android.webkit.WebSettingsClassic"
            java.lang.String r1 = "android.webkit.WebViewClassic"
            java.lang.String r0 = a(r2, r0, r1)     // Catch:{ Exception -> 0x0044 }
            e = r0     // Catch:{ Exception -> 0x0044 }
            goto L_0x005a
        L_0x0044:
            android.webkit.WebView r0 = new android.webkit.WebView     // Catch:{ all -> 0x005e }
            android.content.Context r2 = r2.getApplicationContext()     // Catch:{ all -> 0x005e }
            r0.<init>(r2)     // Catch:{ all -> 0x005e }
            android.webkit.WebSettings r2 = r0.getSettings()     // Catch:{ all -> 0x005e }
            java.lang.String r2 = r2.getUserAgentString()     // Catch:{ all -> 0x005e }
            e = r2     // Catch:{ all -> 0x005e }
            r0.destroy()     // Catch:{ all -> 0x005e }
        L_0x005a:
            monitor-exit(r3)     // Catch:{ all -> 0x005e }
            java.lang.String r2 = e
            return r2
        L_0x005e:
            r2 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x005e }
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.n.d.b(android.content.Context, boolean):java.lang.String");
    }

    public static Map<String, String> b(Context context) {
        try {
            return new d(context, false).b();
        } catch (Throwable th) {
            com.facebook.ads.internal.w.h.a.a(th);
            return new HashMap();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x0094 A[SYNTHETIC, Splitter:B:34:0x0094] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0099 A[Catch:{ IOException -> 0x00a1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x009e A[Catch:{ IOException -> 0x00a1 }] */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String a() {
        /*
            r8 = this;
            android.content.Context r0 = r8.f
            r1 = 1
            a(r0, r1)
            r0 = 0
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch:{ IOException -> 0x0084, all -> 0x007e }
            r1.<init>()     // Catch:{ IOException -> 0x0084, all -> 0x007e }
            android.util.Base64OutputStream r2 = new android.util.Base64OutputStream     // Catch:{ IOException -> 0x0079, all -> 0x0074 }
            r3 = 0
            r2.<init>(r1, r3)     // Catch:{ IOException -> 0x0079, all -> 0x0074 }
            java.util.zip.DeflaterOutputStream r4 = new java.util.zip.DeflaterOutputStream     // Catch:{ IOException -> 0x0070, all -> 0x006c }
            r4.<init>(r2)     // Catch:{ IOException -> 0x0070, all -> 0x006c }
            java.util.Map r0 = r8.b()     // Catch:{ IOException -> 0x006a }
            java.lang.String r5 = com.facebook.ads.internal.g.b.b     // Catch:{ IOException -> 0x006a }
            boolean r5 = android.text.TextUtils.isEmpty(r5)     // Catch:{ IOException -> 0x006a }
            if (r5 == 0) goto L_0x002d
            android.content.Context r5 = r8.f     // Catch:{ IOException -> 0x006a }
            com.facebook.ads.internal.g.b.a(r5)     // Catch:{ IOException -> 0x006a }
            android.content.Context r5 = r8.f     // Catch:{ IOException -> 0x006a }
            a(r5)     // Catch:{ IOException -> 0x006a }
        L_0x002d:
            java.lang.String r5 = "IDFA"
            java.lang.String r6 = com.facebook.ads.internal.g.b.b     // Catch:{ IOException -> 0x006a }
            r0.put(r5, r6)     // Catch:{ IOException -> 0x006a }
            java.lang.String r5 = "USER_AGENT"
            com.facebook.ads.internal.l.b r6 = r8.g     // Catch:{ IOException -> 0x006a }
            android.content.Context r7 = r8.f     // Catch:{ IOException -> 0x006a }
            java.lang.String r3 = a(r6, r7, r3)     // Catch:{ IOException -> 0x006a }
            r0.put(r5, r3)     // Catch:{ IOException -> 0x006a }
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch:{ IOException -> 0x006a }
            r3.<init>(r0)     // Catch:{ IOException -> 0x006a }
            java.lang.String r0 = r3.toString()     // Catch:{ IOException -> 0x006a }
            byte[] r0 = r0.getBytes()     // Catch:{ IOException -> 0x006a }
            r4.write(r0)     // Catch:{ IOException -> 0x006a }
            r4.close()     // Catch:{ IOException -> 0x006a }
            java.lang.String r0 = r1.toString()     // Catch:{ IOException -> 0x006a }
            java.lang.String r3 = "\n"
            java.lang.String r5 = ""
            java.lang.String r0 = r0.replaceAll(r3, r5)     // Catch:{ IOException -> 0x006a }
            r4.close()     // Catch:{ IOException -> 0x0069 }
            r2.close()     // Catch:{ IOException -> 0x0069 }
            r1.close()     // Catch:{ IOException -> 0x0069 }
        L_0x0069:
            return r0
        L_0x006a:
            r0 = move-exception
            goto L_0x0089
        L_0x006c:
            r3 = move-exception
            r4 = r0
            r0 = r3
            goto L_0x0092
        L_0x0070:
            r3 = move-exception
            r4 = r0
            r0 = r3
            goto L_0x0089
        L_0x0074:
            r2 = move-exception
            r4 = r0
            r0 = r2
            r2 = r4
            goto L_0x0092
        L_0x0079:
            r2 = move-exception
            r4 = r0
            r0 = r2
            r2 = r4
            goto L_0x0089
        L_0x007e:
            r1 = move-exception
            r2 = r0
            r4 = r2
            r0 = r1
            r1 = r4
            goto L_0x0092
        L_0x0084:
            r1 = move-exception
            r2 = r0
            r4 = r2
            r0 = r1
            r1 = r4
        L_0x0089:
            java.lang.RuntimeException r3 = new java.lang.RuntimeException     // Catch:{ all -> 0x0091 }
            java.lang.String r5 = "Failed to build user token"
            r3.<init>(r5, r0)     // Catch:{ all -> 0x0091 }
            throw r3     // Catch:{ all -> 0x0091 }
        L_0x0091:
            r0 = move-exception
        L_0x0092:
            if (r4 == 0) goto L_0x0097
            r4.close()     // Catch:{ IOException -> 0x00a1 }
        L_0x0097:
            if (r2 == 0) goto L_0x009c
            r2.close()     // Catch:{ IOException -> 0x00a1 }
        L_0x009c:
            if (r1 == 0) goto L_0x00a1
            r1.close()     // Catch:{ IOException -> 0x00a1 }
        L_0x00a1:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.n.d.a():java.lang.String");
    }

    public Map<String, String> b() {
        a(this.f, false);
        com.facebook.ads.internal.l.a.a(this.f);
        HashMap hashMap = new HashMap();
        hashMap.put("SDK", "android");
        hashMap.put("SDK_VERSION", "5.1.0");
        hashMap.put("LOCALE", Locale.getDefault().toString());
        float f2 = x.b;
        int i = this.f.getResources().getDisplayMetrics().widthPixels;
        int i2 = this.f.getResources().getDisplayMetrics().heightPixels;
        hashMap.put("DENSITY", String.valueOf(f2));
        hashMap.put("SCREEN_WIDTH", String.valueOf((int) (((float) i) / f2)));
        hashMap.put("SCREEN_HEIGHT", String.valueOf((int) (((float) i2) / f2)));
        hashMap.put("ATTRIBUTION_ID", com.facebook.ads.internal.g.b.a);
        hashMap.put("ID_SOURCE", com.facebook.ads.internal.g.b.d);
        hashMap.put("OS", "Android");
        hashMap.put("OSVERS", b.a);
        hashMap.put("BUNDLE", this.g.f());
        hashMap.put("APPNAME", this.g.d());
        hashMap.put("APPVERS", this.g.g());
        hashMap.put("APPBUILD", String.valueOf(this.g.h()));
        hashMap.put("CARRIER", this.g.c());
        hashMap.put("MAKE", this.g.a());
        hashMap.put("MODEL", this.g.b());
        hashMap.put("ROOTED", String.valueOf(d.d));
        hashMap.put("INSTALLER", this.g.e());
        hashMap.put("SDK_CAPABILITY", com.facebook.ads.internal.w.b.b.b());
        hashMap.put("NETWORK_TYPE", String.valueOf(u.a(this.f).g));
        hashMap.put("SESSION_TIME", v.a(o.b()));
        hashMap.put("SESSION_ID", o.c());
        String str = c;
        if (str != null) {
            hashMap.put("AFP", str);
        }
        String a2 = f.a(this.f);
        if (a2 != null) {
            hashMap.put("ASHAS", a2);
        }
        hashMap.put("UNITY", String.valueOf(h.a(this.f)));
        String mediationService = AdInternalSettings.getMediationService();
        if (mediationService != null) {
            hashMap.put("MEDIATION_SERVICE", mediationService);
        }
        hashMap.put("ACCESSIBILITY_ENABLED", String.valueOf(this.g.i()));
        if (this.g.j() != -1) {
            hashMap.put("APP_MIN_SDK_VERSION", String.valueOf(this.g.j()));
        }
        hashMap.put("VALPARAMS", b.a(this.f));
        hashMap.put("ANALOG", k.a(com.facebook.ads.internal.l.a.a()));
        hashMap.put("PROCESS", c.a(this.f));
        return hashMap;
    }
}
