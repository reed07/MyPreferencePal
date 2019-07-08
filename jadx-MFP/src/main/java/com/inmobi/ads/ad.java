package com.inmobi.ads;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.inmobi.commons.core.a.a;
import com.moat.analytics.mobile.inm.WebAdTracker;
import java.lang.ref.WeakReference;
import java.util.Map;

/* compiled from: MoatTrackedHtmlAd */
public class ad extends bz {
    private static final String d = "ad";
    @NonNull
    private final WeakReference<Activity> e;
    @NonNull
    private final ca f;
    @NonNull
    private final Map<String, Object> g;
    private WebAdTracker h;

    public ad(@NonNull AdContainer adContainer, @NonNull Activity activity, @NonNull ca caVar, @NonNull Map<String, Object> map) {
        super(adContainer);
        this.e = new WeakReference<>(activity);
        this.f = caVar;
        this.g = map;
    }

    @Nullable
    public final View a() {
        return this.f.a();
    }

    @Nullable
    public final View a(View view, ViewGroup viewGroup, boolean z) {
        return this.f.a(view, viewGroup, z);
    }

    @Nullable
    public final View b() {
        return this.f.b();
    }

    @NonNull
    public final c c() {
        return this.f.c();
    }

    public final void a(@Nullable View... viewArr) {
        try {
            Activity activity = (Activity) this.e.get();
            if (this.f.c().k.i && activity != null && ((Boolean) this.g.get("enabled")).booleanValue()) {
                if (this.h == null) {
                    if (this.a instanceof ah) {
                        ah ahVar = (ah) this.a;
                        if (ahVar.s() != null) {
                            this.h = z.a(activity.getApplication(), (WebView) ahVar.s());
                        }
                    } else {
                        View b = this.f.b();
                        if (b != null) {
                            this.h = z.a(activity.getApplication(), (WebView) b);
                        }
                    }
                }
                if (this.h != null) {
                    this.h.startTracking();
                }
            }
        } catch (Exception e2) {
            new StringBuilder("Exception in startTrackingForImpression with message : ").append(e2.getMessage());
            a.a().a(new com.inmobi.commons.core.e.a(e2));
        } catch (Throwable th) {
            this.f.a(viewArr);
            throw th;
        }
        this.f.a(viewArr);
    }

    public final void d() {
        try {
            if (this.h != null) {
                this.h.stopTracking();
            }
        } catch (Exception e2) {
            new StringBuilder("Exception in stopTrackingForImpression with message : ").append(e2.getMessage());
            a.a().a(new com.inmobi.commons.core.e.a(e2));
        } catch (Throwable th) {
            this.f.d();
            throw th;
        }
        this.f.d();
    }

    public final void a(int i) {
        this.f.a(i);
    }

    public final void a(Context context, int i) {
        this.f.a(context, i);
    }

    public final void e() {
        this.h = null;
        this.e.clear();
        super.e();
        this.f.e();
    }
}
