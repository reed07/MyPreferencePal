package com.facebook.ads.internal.w.h;

import android.content.Context;
import android.support.annotation.AnyThread;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.internal.k.e;
import com.facebook.ads.internal.settings.AdInternalSettings;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@AnyThread
public class a {
    @Nullable
    public static C0022a a;
    @VisibleForTesting
    static Executor b = Executors.newSingleThreadExecutor();
    public static boolean c = false;
    private static final Set<Integer> d = new HashSet();

    /* renamed from: com.facebook.ads.internal.w.h.a$a reason: collision with other inner class name */
    public interface C0022a {
        Map<String, String> a();

        boolean b();
    }

    public static void a(Context context, String str, int i, Exception exc) {
        boolean z = false;
        try {
            synchronized (d) {
                if (!d.contains(Integer.valueOf(i))) {
                    d.add(Integer.valueOf(i));
                    z = true;
                }
            }
            if (!z) {
                return;
            }
            if (a(context, str, i, Math.random())) {
                c(context, str, i, exc);
            }
        } catch (Throwable th) {
            a(th);
        }
    }

    public static void a(Throwable th) {
        Log.e(AudienceNetworkAds.TAG, "Exception during logging debug event.", th);
        if (c) {
            throw new RuntimeException(th);
        }
    }

    @VisibleForTesting
    static boolean a(Context context, String str, int i, double d2) {
        double d3;
        double d4;
        Set s = com.facebook.ads.internal.r.a.s(context);
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(":");
        sb.append(i);
        if (s.contains(sb.toString())) {
            d3 = (double) (com.facebook.ads.internal.r.a.u(context) * com.facebook.ads.internal.r.a.t(context));
            d4 = 10000.0d;
        } else {
            d3 = (double) com.facebook.ads.internal.r.a.u(context);
            d4 = 100.0d;
        }
        return d2 >= 1.0d - (d3 / d4);
    }

    public static void b(Context context, String str, int i, Exception exc) {
        if (context == null) {
            try {
                a(new RuntimeException("Can't log Debug Event. Context is null."));
            } catch (Throwable th) {
                a(th);
            }
        } else {
            com.facebook.ads.internal.i.a.a(context);
            if (c) {
                StringBuilder sb = new StringBuilder();
                sb.append("Debug crash because of event with subtype = ");
                sb.append(str);
                sb.append(", subtypeCode = ");
                sb.append(i);
                String sb2 = sb.toString();
                if (!AdInternalSettings.d || i != b.aa) {
                    throw new RuntimeException(sb2, exc);
                }
            }
            if (a(context, str, i, Math.random())) {
                c(context, str, i, exc);
            }
        }
    }

    private static void c(final Context context, final String str, final int i, final Exception exc) {
        C0022a aVar = a;
        if (aVar != null && aVar.b()) {
            StringBuilder sb = new StringBuilder();
            sb.append("Debug Event with subtype = ");
            sb.append(str);
            sb.append(", subtypeCode = ");
            sb.append(i);
            Log.e(AudienceNetworkAds.TAG, sb.toString(), exc);
        }
        b.execute(new Runnable() {
            public void run() {
                Map map;
                try {
                    if (a.a != null) {
                        map = a.a.a();
                    } else if (!a.c) {
                        map = new HashMap();
                    } else {
                        throw new RuntimeException("Debug crash because sEnvironmentDataProvider not injected", exc);
                    }
                    map.put("subtype", str);
                    map.put("subtype_code", String.valueOf(i));
                    e.a(exc, context, map);
                } catch (Throwable th) {
                    a.a(th);
                }
            }
        });
    }
}
