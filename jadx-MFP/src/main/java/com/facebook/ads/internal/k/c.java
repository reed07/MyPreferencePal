package com.facebook.ads.internal.k;

import android.content.Context;
import android.os.Process;
import android.support.annotation.Nullable;
import android.util.Log;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.BuildConfig;
import com.facebook.ads.internal.r.a;
import com.facebook.ads.internal.settings.AdInternalSettings;
import com.facebook.ads.internal.w.b.o;
import com.facebook.ads.internal.w.b.s;
import com.google.android.gms.measurement.AppMeasurement;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Map;

public class c implements UncaughtExceptionHandler {
    private final UncaughtExceptionHandler a;
    private final Context b;
    private final Map<String, String> c;

    public c(@Nullable UncaughtExceptionHandler uncaughtExceptionHandler, Context context, Map<String, String> map) {
        this.a = uncaughtExceptionHandler;
        if (context != null) {
            this.b = context.getApplicationContext();
            this.c = map;
            return;
        }
        throw new IllegalArgumentException("Missing Context");
    }

    private static void a() {
        try {
            Process.killProcess(Process.myPid());
        } catch (Throwable unused) {
        }
        try {
            System.exit(10);
        } catch (Throwable unused2) {
        }
    }

    public void uncaughtException(Thread thread, Throwable th) {
        try {
            String a2 = s.a(th);
            if (a2 != null && a2.contains(BuildConfig.APPLICATION_ID)) {
                Map a3 = new b(a2, this.c).a();
                a3.put("subtype", AppMeasurement.CRASH_ORIGIN);
                a3.put("subtype_code", "0");
                e.a(new d(o.b(), o.c(), a3), this.b);
            }
        } catch (Exception unused) {
        }
        if (!a.X(this.b) || !AdInternalSettings.d) {
            UncaughtExceptionHandler uncaughtExceptionHandler = this.a;
            if (uncaughtExceptionHandler != null) {
                uncaughtExceptionHandler.uncaughtException(thread, th);
                return;
            }
        } else {
            try {
                Log.e(AudienceNetworkAds.TAG, "Facebook Audience Network process crashed with exception: ", th);
            } catch (Throwable unused2) {
            }
        }
        a();
    }
}
