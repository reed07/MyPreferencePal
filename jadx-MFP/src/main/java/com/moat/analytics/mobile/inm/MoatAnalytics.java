package com.moat.analytics.mobile.inm;

import android.app.Application;
import android.support.annotation.UiThread;
import com.moat.analytics.mobile.inm.v.a;

public abstract class MoatAnalytics {
    private static MoatAnalytics a;

    public static synchronized MoatAnalytics getInstance() {
        MoatAnalytics moatAnalytics;
        synchronized (MoatAnalytics.class) {
            if (a == null) {
                try {
                    a = new k();
                } catch (Exception e) {
                    m.a(e);
                    a = new a();
                }
            }
            moatAnalytics = a;
        }
        return moatAnalytics;
    }

    @UiThread
    public abstract void prepareNativeDisplayTracking(String str);

    public abstract void start(Application application);

    public abstract void start(MoatOptions moatOptions, Application application);
}
