package com.moat.analytics.mobile.und;

import android.app.Application;
import android.content.Context;
import android.support.annotation.Nullable;
import com.brightcove.player.analytics.Analytics;
import java.lang.ref.WeakReference;

class k extends MoatAnalytics implements b {
    boolean a = false;
    boolean b = false;
    boolean c = false;
    @Nullable
    g d;
    WeakReference<Context> e;
    private boolean f = false;
    private String g;

    k() {
    }

    private void a(MoatOptions moatOptions, Application application) {
        if (this.f) {
            p.a(3, Analytics.TAG, (Object) this, "Moat SDK has already been started.");
            return;
        }
        w.a().b();
        if (moatOptions.loggingEnabled && a(application.getApplicationContext())) {
            this.a = true;
        }
        this.c = moatOptions.disableLocationServices;
        if (application == null) {
            p.a("[ERROR] ", "Moat Analytics SDK didn't start, application was null");
            return;
        }
        this.e = new WeakReference<>(application.getApplicationContext());
        this.f = true;
        this.b = moatOptions.autoTrackGMAInterstitials;
        a.a(application);
        w.a().a((b) this);
        if (!moatOptions.disableAdIdCollection) {
            s.a((Context) application);
        }
        p.a("[SUCCESS] ", "Moat Analytics SDK Version 2.2.0 started");
    }

    private static boolean a(Context context) {
        return (context.getApplicationInfo().flags & 2) != 0;
    }

    private void d() {
        if (this.d == null) {
            this.d = new g(a.a(), a.DISPLAY);
            this.d.a(this.g);
            String str = Analytics.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("Preparing native display tracking with partner code ");
            sb.append(this.g);
            p.a(3, str, (Object) this, sb.toString());
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Prepared for native display tracking with partner code ");
            sb2.append(this.g);
            p.a("[SUCCESS] ", sb2.toString());
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean a() {
        return this.f;
    }

    public void b() {
        o.a();
        if (this.g != null) {
            try {
                d();
            } catch (Exception e2) {
                m.a(e2);
            }
        }
    }

    public void c() {
    }

    public void prepareNativeDisplayTracking(String str) {
        this.g = str;
        if (w.a().a != d.OFF) {
            try {
                d();
            } catch (Exception e2) {
                m.a(e2);
            }
        }
    }

    public void start(Application application) {
        start(new MoatOptions(), application);
    }

    public void start(MoatOptions moatOptions, Application application) {
        try {
            a(moatOptions, application);
        } catch (Exception e2) {
            m.a(e2);
        }
    }
}
