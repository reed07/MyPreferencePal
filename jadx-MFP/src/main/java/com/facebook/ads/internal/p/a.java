package com.facebook.ads.internal.p;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.util.Log;
import com.facebook.ads.internal.v.a.b;
import com.facebook.ads.internal.v.a.p;
import com.facebook.ads.internal.w.e.d;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class a {
    private static final String a = "a";
    private static final AtomicBoolean b = new AtomicBoolean();
    private static b c;
    private static String d;
    private static LinkedHashMap<String, String> e;
    private static String f;

    public static String a(int i) {
        StringBuilder sb = new StringBuilder(i);
        String str = "bbbbbbbbbb";
        for (int i2 = 0; i2 < i / 10; i2++) {
            sb.append(str);
        }
        return sb.toString();
    }

    @SuppressLint({"CatchGeneralException"})
    public static void a(Context context, b bVar) {
        if (!b.get() && (context instanceof Application)) {
            f = context.getPackageName();
            c = bVar;
            d = UUID.randomUUID().toString();
            b.compareAndSet(false, true);
        }
    }

    @SuppressLint({"CatchGeneralException"})
    public static void a(Context context, String str, String str2) {
        if (b.get() && com.facebook.ads.internal.r.a.z(context) && Math.random() <= com.facebook.ads.internal.r.a.B(context)) {
            e = new LinkedHashMap<>();
            e.put(b.ti.toString(), str2);
            e.put(b.bt.toString(), "AN_ANDROID");
            e.put(b.sn.toString(), str);
            e.put(b.ap.toString(), f);
            e.put(b.r1.toString(), d);
            String a2 = new p().a((Map<? extends String, ? extends String>) e).a();
            if (com.facebook.ads.internal.r.a.C(context) && Math.random() <= com.facebook.ads.internal.r.a.E(context)) {
                String a3 = a(com.facebook.ads.internal.r.a.D(context));
                StringBuilder sb = new StringBuilder();
                sb.append(a2);
                sb.append("&");
                sb.append(b._p.toString());
                sb.append("=");
                sb.append(a3);
                a2 = sb.toString();
            }
            com.facebook.ads.internal.v.a.a aVar = null;
            try {
                com.facebook.ads.internal.v.a.a aVar2 = d.a(context);
                try {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(com.facebook.ads.internal.r.a.A(context));
                    sb2.append("&");
                    sb2.append(a2);
                    String sb3 = sb2.toString();
                    if (sb3 != null && !sb3.isEmpty()) {
                        aVar2.a(sb3, (p) null, c);
                    }
                    aVar2.b();
                } catch (Exception e2) {
                    e = e2;
                    aVar = aVar2;
                    try {
                        Log.e(a, "Bot Detection Network Signal Error", e);
                        aVar.b();
                    } catch (Throwable th) {
                        th = th;
                        aVar2 = aVar;
                        aVar2.b();
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    aVar2.b();
                    throw th;
                }
            } catch (Exception e3) {
                e = e3;
                Log.e(a, "Bot Detection Network Signal Error", e);
                aVar.b();
            }
        }
    }
}
