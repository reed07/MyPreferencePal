package com.inmobi.ads;

import android.app.Application;
import android.view.View;
import android.webkit.WebView;
import com.google.android.gms.fitness.data.WorkoutExercises;
import com.inmobi.a.o;
import com.inmobi.commons.core.a.a;
import com.inmobi.commons.core.utilities.uid.c;
import com.moat.analytics.mobile.inm.MoatAnalytics;
import com.moat.analytics.mobile.inm.MoatFactory;
import com.moat.analytics.mobile.inm.MoatOptions;
import com.moat.analytics.mobile.inm.NativeDisplayTracker;
import com.moat.analytics.mobile.inm.ReactiveVideoTracker;
import com.moat.analytics.mobile.inm.ReactiveVideoTrackerPlugin;
import com.moat.analytics.mobile.inm.WebAdTracker;
import java.util.Map;

/* compiled from: InMobiMoatFactory */
class z {
    private static final String a = "z";
    private static final boolean b = false;
    private static boolean c = false;

    z() {
    }

    static {
        WorkoutExercises.ROW.contains("staging");
    }

    static void a(Application application) {
        if (!c) {
            try {
                MoatOptions moatOptions = new MoatOptions();
                moatOptions.loggingEnabled = b;
                moatOptions.disableLocationServices = !o.a().a.a.a();
                c.a();
                Boolean g = c.g();
                if (g == null || g.booleanValue()) {
                    moatOptions.disableAdIdCollection = true;
                }
                MoatAnalytics.getInstance().start(moatOptions, application);
                c = true;
            } catch (Exception e) {
                new StringBuilder("Exception in initializing the Moat library : ").append(e.getMessage());
                a.a().a(new com.inmobi.commons.core.e.a(e));
            }
        }
    }

    static WebAdTracker a(Application application, WebView webView) {
        if (!c) {
            a(application);
        }
        return MoatFactory.create().createWebAdTracker(webView);
    }

    static NativeDisplayTracker a(Application application, String str, View view, Map<String, String> map) {
        if (!c) {
            a(application);
        }
        MoatAnalytics.getInstance().prepareNativeDisplayTracking(str);
        return MoatFactory.create().createNativeDisplayTracker(view, map);
    }

    static ReactiveVideoTracker a(Application application, String str) {
        if (!c) {
            a(application);
        }
        return (ReactiveVideoTracker) MoatFactory.create().createCustomTracker(new ReactiveVideoTrackerPlugin(str));
    }
}
