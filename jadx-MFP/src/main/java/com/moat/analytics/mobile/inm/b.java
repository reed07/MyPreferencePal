package com.moat.analytics.mobile.inm;

import android.app.Activity;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import com.facebook.internal.AnalyticsEvents;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

abstract class b {
    m a = null;
    WeakReference<WebView> b;
    j c;
    TrackerListener d;
    final String e;
    final boolean f;
    private WeakReference<View> g;
    private final z h;
    private final boolean i;
    private boolean j;
    private boolean k;

    b(@Nullable View view, boolean z, boolean z2) {
        String str;
        p.a(3, "BaseTracker", (Object) this, "Initializing.");
        if (z) {
            StringBuilder sb = new StringBuilder("m");
            sb.append(hashCode());
            str = sb.toString();
        } else {
            str = "";
        }
        this.e = str;
        this.g = new WeakReference<>(view);
        this.i = z;
        this.f = z2;
        this.j = false;
        this.k = false;
        this.h = new z();
    }

    private void i() {
        String str;
        String str2;
        p.a(3, "BaseTracker", (Object) this, "Attempting bridge installation.");
        if (this.b.get() != null) {
            this.c = new j((WebView) this.b.get(), a.WEBVIEW);
            str = "BaseTracker";
            str2 = "Bridge installed.";
        } else {
            this.c = null;
            str = "BaseTracker";
            str2 = "Bridge not installed, WebView is null.";
        }
        p.a(3, str, (Object) this, str2);
    }

    private void j() {
        if (this.j) {
            throw new m("Tracker already started");
        }
    }

    private void k() {
        if (this.k) {
            throw new m("Tracker already stopped");
        }
    }

    private boolean l() {
        return this.i || this.f;
    }

    /* access modifiers changed from: 0000 */
    public abstract String a();

    /* access modifiers changed from: 0000 */
    public void a(WebView webView) {
        if (webView != null) {
            this.b = new WeakReference<>(webView);
            if (this.c == null && !l()) {
                i();
            }
            j jVar = this.c;
            if (jVar != null) {
                jVar.a(this);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(j jVar) {
        this.c = jVar;
    }

    /* access modifiers changed from: 0000 */
    public void a(String str, Exception exc) {
        try {
            m.a(exc);
            String a2 = m.a(str, exc);
            if (this.d != null) {
                this.d.onTrackingFailedToStart(a2);
            }
            p.a(3, "BaseTracker", (Object) this, a2);
            StringBuilder sb = new StringBuilder();
            sb.append(a());
            sb.append(" ");
            sb.append(a2);
            p.a("[ERROR] ", sb.toString());
        } catch (Exception unused) {
        }
    }

    /* access modifiers changed from: 0000 */
    @CallSuper
    public void a(List<String> list) {
        if (f() == null && !this.f) {
            list.add("Tracker's target view is null");
        }
        if (!list.isEmpty()) {
            throw new m(TextUtils.join(" and ", list));
        }
    }

    /* access modifiers changed from: 0000 */
    @CallSuper
    public void b() {
        p.a(3, "BaseTracker", (Object) this, "Attempting to start impression.");
        c();
        d();
        a((List<String>) new ArrayList<String>());
        j jVar = this.c;
        if (jVar != null) {
            jVar.b(this);
            this.j = true;
            p.a(3, "BaseTracker", (Object) this, "Impression started.");
            return;
        }
        p.a(3, "BaseTracker", (Object) this, "Bridge is null, won't start tracking");
        throw new m("Bridge is null");
    }

    /* access modifiers changed from: 0000 */
    public void c() {
        if (this.a != null) {
            StringBuilder sb = new StringBuilder("Tracker initialization failed: ");
            sb.append(this.a.getMessage());
            throw new m(sb.toString());
        }
    }

    @CallSuper
    public void changeTargetView(View view) {
        StringBuilder sb = new StringBuilder("changing view to ");
        sb.append(p.a(view));
        p.a(3, "BaseTracker", (Object) this, sb.toString());
        this.g = new WeakReference<>(view);
    }

    /* access modifiers changed from: 0000 */
    public void d() {
        j();
        k();
    }

    /* access modifiers changed from: 0000 */
    public boolean e() {
        return this.j && !this.k;
    }

    /* access modifiers changed from: 0000 */
    public View f() {
        return (View) this.g.get();
    }

    /* access modifiers changed from: 0000 */
    public String g() {
        return p.a(f());
    }

    /* access modifiers changed from: 0000 */
    public String h() {
        this.h.a(this.e, f());
        return this.h.a;
    }

    public void removeListener() {
        this.d = null;
    }

    @Deprecated
    public void setActivity(Activity activity) {
    }

    public void setListener(TrackerListener trackerListener) {
        this.d = trackerListener;
    }

    public void startTracking() {
        try {
            p.a(3, "BaseTracker", (Object) this, "In startTracking method.");
            b();
            if (this.d != null) {
                TrackerListener trackerListener = this.d;
                StringBuilder sb = new StringBuilder("Tracking started on ");
                sb.append(g());
                trackerListener.onTrackingStarted(sb.toString());
            }
            StringBuilder sb2 = new StringBuilder("startTracking succeeded for ");
            sb2.append(g());
            String sb3 = sb2.toString();
            p.a(3, "BaseTracker", (Object) this, sb3);
            StringBuilder sb4 = new StringBuilder();
            sb4.append(a());
            sb4.append(" ");
            sb4.append(sb3);
            p.a("[SUCCESS] ", sb4.toString());
        } catch (Exception e2) {
            a("startTracking", e2);
        }
    }

    @CallSuper
    public void stopTracking() {
        boolean z = false;
        try {
            p.a(3, "BaseTracker", (Object) this, "In stopTracking method.");
            this.k = true;
            if (this.c != null) {
                this.c.c(this);
                z = true;
            }
        } catch (Exception e2) {
            m.a(e2);
        }
        String str = "BaseTracker";
        StringBuilder sb = new StringBuilder("Attempt to stop tracking ad impression was ");
        sb.append(z ? "" : "un");
        sb.append("successful.");
        p.a(3, str, (Object) this, sb.toString());
        String str2 = z ? "[SUCCESS] " : "[ERROR] ";
        StringBuilder sb2 = new StringBuilder();
        sb2.append(a());
        sb2.append(" stopTracking ");
        sb2.append(z ? AnalyticsEvents.PARAMETER_SHARE_OUTCOME_SUCCEEDED : "failed");
        sb2.append(" for ");
        sb2.append(g());
        p.a(str2, sb2.toString());
        TrackerListener trackerListener = this.d;
        if (trackerListener != null) {
            trackerListener.onTrackingStopped("");
            this.d = null;
        }
    }
}
