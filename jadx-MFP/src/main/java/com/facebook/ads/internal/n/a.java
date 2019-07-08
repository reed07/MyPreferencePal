package com.facebook.ads.internal.n;

import android.content.Context;
import android.support.annotation.UiThread;
import android.util.Log;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.internal.k.c;
import com.facebook.ads.internal.w.h.a.C0022a;
import com.facebook.ads.internal.w.h.b;
import java.util.Map;

@UiThread
public final class a {
    private static boolean a;

    public static synchronized void a(Context context) {
        synchronized (a.class) {
            if (com.facebook.ads.internal.i.a.a() == null) {
                Context applicationContext = context.getApplicationContext();
                com.facebook.ads.internal.i.a.a(applicationContext);
                c(applicationContext);
            }
        }
    }

    public static synchronized void b(Context context) {
        synchronized (a.class) {
            Context a2 = com.facebook.ads.internal.i.a.a();
            if (a2 == null) {
                a2 = context.getApplicationContext();
                com.facebook.ads.internal.i.a.a(a2);
                c(a2);
                Log.e(AudienceNetworkAds.TAG, "You don't call AudienceNetworkAds.initialize(). Some functionality may not work properly.");
                com.facebook.ads.internal.w.h.a.a(a2, "api", b.p, new Exception("initialize() not called."));
            }
            if (!a) {
                if (com.facebook.ads.internal.r.a.p(a2)) {
                    try {
                        Thread.setDefaultUncaughtExceptionHandler(new c(Thread.getDefaultUncaughtExceptionHandler(), a2, d.b(a2)));
                    } catch (SecurityException e) {
                        Log.e(AudienceNetworkAds.TAG, "No permissions to set the default uncaught exception handler", e);
                    }
                }
                a = true;
            }
        }
    }

    private static synchronized void c(final Context context) {
        synchronized (a.class) {
            if (com.facebook.ads.internal.w.h.a.a == null) {
                com.facebook.ads.internal.w.h.a.a = new C0022a() {
                    public Map<String, String> a() {
                        return d.b(context);
                    }

                    public boolean b() {
                        return com.facebook.ads.internal.w.b.a.a();
                    }
                };
            }
            com.facebook.ads.internal.w.a.b.a(context);
        }
    }
}
