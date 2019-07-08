package com.moat.analytics.mobile.und;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.google.android.gms.ads.AdActivity;
import com.moat.analytics.mobile.und.a.b.a;
import java.lang.ref.WeakReference;

class f {
    @Nullable
    private static WebAdTracker a;
    private static WeakReference<Activity> b = new WeakReference<>(null);

    f() {
    }

    private static void a() {
        if (a != null) {
            p.a(3, "GMAInterstitialHelper", b.get(), "Stopping to track GMA interstitial");
            a.stopTracking();
            a = null;
        }
    }

    static void a(Activity activity) {
        try {
            if (w.a().a != d.OFF) {
                if (!b(activity)) {
                    a();
                    b = new WeakReference<>(null);
                } else if (b.get() == null || b.get() != activity) {
                    View decorView = activity.getWindow().getDecorView();
                    if (decorView instanceof ViewGroup) {
                        a a2 = aa.a((ViewGroup) decorView);
                        if (a2.c()) {
                            b = new WeakReference<>(activity);
                            a((WebView) a2.b());
                        } else {
                            p.a(3, "GMAInterstitialHelper", (Object) activity, "Sorry, no WebView in this activity");
                        }
                    }
                }
            }
        } catch (Exception e) {
            m.a(e);
        }
    }

    private static void a(WebView webView) {
        p.a(3, "GMAInterstitialHelper", b.get(), "Starting to track GMA interstitial");
        a = MoatFactory.create().createWebAdTracker(webView);
        a.startTracking();
    }

    private static boolean b(Activity activity) {
        String name = activity.getClass().getName();
        StringBuilder sb = new StringBuilder();
        sb.append("Activity name: ");
        sb.append(name);
        p.a(3, "GMAInterstitialHelper", (Object) activity, sb.toString());
        return name.contains(AdActivity.CLASS_NAME);
    }
}
