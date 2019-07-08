package com.inmobi.ads;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import com.inmobi.commons.core.a.a;
import com.moat.analytics.mobile.inm.NativeDisplayTracker;
import com.moat.analytics.mobile.inm.NativeDisplayTracker.MoatUserInteractionType;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: MoatTrackedNativeV2DisplayAd */
class ae extends bz {
    private final String d = ae.class.getSimpleName();
    @NonNull
    private final WeakReference<Activity> e;
    /* access modifiers changed from: private */
    public NativeDisplayTracker f;
    @NonNull
    private Map<String, Object> g;
    @NonNull
    private ca h;

    ae(@NonNull Activity activity, @NonNull ca caVar, @NonNull Map<String, Object> map) {
        this.e = new WeakReference<>(activity);
        this.h = caVar;
        this.g = map;
    }

    @Nullable
    public final View a() {
        return this.h.a();
    }

    @Nullable
    public final View a(View view, ViewGroup viewGroup, boolean z) {
        return this.h.a(view, viewGroup, z);
    }

    @Nullable
    public final View b() {
        return this.h.b();
    }

    public final a f() {
        return this.h.f();
    }

    @NonNull
    public final c c() {
        return this.h.c();
    }

    public final void a(@Nullable View... viewArr) {
        try {
            View b = this.h.b();
            if (b == null) {
                this.h.a(viewArr);
                return;
            }
            Activity activity = (Activity) this.e.get();
            if (this.h.c().k.i && activity != null && ((Boolean) this.g.get("enabled")).booleanValue()) {
                if (this.f == null) {
                    Application application = activity.getApplication();
                    String str = (String) this.g.get("partnerCode");
                    HashMap a = d.a("moatClientLevel", "moatClientSlicer", (JSONArray) this.g.get("clientLevels"), (JSONArray) this.g.get("clientSlicers"), (JSONObject) this.g.get("zMoatExtras"));
                    a.put("zMoatIID", (String) this.g.get("zMoatIID"));
                    this.f = z.a(application, str, b, a);
                }
                b.setOnTouchListener(new OnTouchListener() {
                    public final boolean onTouch(View view, MotionEvent motionEvent) {
                        ae.this.f.reportUserInteractionEvent(MoatUserInteractionType.TOUCH);
                        StringBuilder sb = new StringBuilder("Received touch event for DisplayTracker(");
                        sb.append(ae.this.f.hashCode());
                        sb.append(")");
                        return true;
                    }
                });
                this.f.startTracking();
                new StringBuilder("Moat initialized for Native Display for ID : ").append(this.g.get("zMoatIID"));
            }
            this.h.a(viewArr);
        } catch (Exception e2) {
            new StringBuilder("Exception in startTrackingForImpression with message : ").append(e2.getMessage());
            a.a().a(new com.inmobi.commons.core.e.a(e2));
        } catch (Throwable th) {
            this.h.a(viewArr);
            throw th;
        }
    }

    public final void d() {
        try {
            if (this.f != null) {
                this.f.stopTracking();
                new StringBuilder("Moat stopped tracking for Native Display for ID : ").append(this.g.get("zMoatIID"));
            }
        } catch (Exception e2) {
            new StringBuilder("Exception in stopTrackingForImpression with message : ").append(e2.getMessage());
            a.a().a(new com.inmobi.commons.core.e.a(e2));
        } catch (Throwable th) {
            this.h.d();
            throw th;
        }
        this.h.d();
    }

    public final void a(int i) {
        if (4 == i) {
            try {
                this.f.reportUserInteractionEvent(MoatUserInteractionType.CLICK);
                StringBuilder sb = new StringBuilder("Received click event for DisplayTracker(");
                sb.append(this.f.hashCode());
                sb.append(")");
            } catch (Exception e2) {
                new StringBuilder("Exception in onAdEvent with message : ").append(e2.getMessage());
                a.a().a(new com.inmobi.commons.core.e.a(e2));
            } catch (Throwable th) {
                this.h.a(i);
                throw th;
            }
        }
        this.h.a(i);
    }

    public final void a(Context context, int i) {
        this.h.a(context, i);
    }

    public final void e() {
        this.f = null;
        this.e.clear();
        super.e();
        this.h.e();
    }
}
