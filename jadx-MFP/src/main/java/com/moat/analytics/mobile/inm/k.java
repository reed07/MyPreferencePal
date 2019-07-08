package com.moat.analytics.mobile.inm;

import android.app.Application;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
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
    private MoatOptions h;

    k() {
    }

    private void a(MoatOptions moatOptions, Application application) {
        if (this.f) {
            p.a(3, Analytics.TAG, (Object) this, "Moat SDK has already been started.");
            return;
        }
        this.h = moatOptions;
        w.a().b();
        this.c = moatOptions.disableLocationServices;
        if (application != null) {
            if (moatOptions.loggingEnabled && s.b(application.getApplicationContext())) {
                this.a = true;
            }
            this.e = new WeakReference<>(application.getApplicationContext());
            this.f = true;
            this.b = moatOptions.autoTrackGMAInterstitials;
            a.a(application);
            w.a().a((b) this);
            if (!moatOptions.disableAdIdCollection) {
                s.a((Context) application);
            }
            p.a("[SUCCESS] ", "Moat Analytics SDK Version 2.5.0 started");
            return;
        }
        throw new m("Moat Analytics SDK didn't start, application was null");
    }

    @UiThread
    private void e() {
        if (this.d == null) {
            this.d = new g(a.a(), a.DISPLAY);
            this.d.a(this.g);
            String str = Analytics.TAG;
            StringBuilder sb = new StringBuilder("Preparing native display tracking with partner code ");
            sb.append(this.g);
            p.a(3, str, (Object) this, sb.toString());
            StringBuilder sb2 = new StringBuilder("Prepared for native display tracking with partner code ");
            sb2.append(this.g);
            p.a("[SUCCESS] ", sb2.toString());
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean a() {
        return this.f;
    }

    /* access modifiers changed from: 0000 */
    public boolean b() {
        MoatOptions moatOptions = this.h;
        return moatOptions != null && moatOptions.disableLocationServices;
    }

    public void c() {
        m.a();
        o.a();
        if (this.g != null) {
            try {
                e();
            } catch (Exception e2) {
                m.a(e2);
            }
        }
    }

    public void d() {
    }

    @UiThread
    public void prepareNativeDisplayTracking(String str) {
        this.g = str;
        if (w.a().a != d.OFF) {
            try {
                e();
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
