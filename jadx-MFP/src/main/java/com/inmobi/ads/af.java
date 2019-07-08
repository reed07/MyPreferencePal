package com.inmobi.ads;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.inmobi.ads.c.l;
import com.inmobi.commons.core.a.a;
import com.moat.analytics.mobile.inm.MoatAdEvent;
import com.moat.analytics.mobile.inm.MoatAdEventType;
import com.moat.analytics.mobile.inm.ReactiveVideoTracker;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: MoatTrackedNativeVideoAd */
class af extends bz {
    private static final String d = "af";
    @NonNull
    private final WeakReference<Activity> e;
    private ReactiveVideoTracker f;
    @NonNull
    private Map<String, Object> g;
    @NonNull
    private ca h;
    private boolean i = false;

    af(@NonNull Activity activity, @NonNull ca caVar, @NonNull bd bdVar, @NonNull Map<String, Object> map) {
        super(bdVar);
        this.e = new WeakReference<>(activity);
        this.h = caVar;
        this.g = map;
        this.f = (ReactiveVideoTracker) map.get("moatTracker");
    }

    @Nullable
    public final View a(View view, ViewGroup viewGroup, boolean z) {
        return this.h.a(view, viewGroup, z);
    }

    @Nullable
    public final View b() {
        return this.h.b();
    }

    @NonNull
    public final c c() {
        return this.h.c();
    }

    public final void a(@Nullable View... viewArr) {
        try {
            Activity activity = (Activity) this.e.get();
            l lVar = this.h.c().k;
            if (activity != null && (this.a instanceof bd) && lVar.i && ((Boolean) this.g.get("enabled")).booleanValue() && this.f == null) {
                this.f = z.a(activity.getApplication(), (String) this.g.get("partnerCode"));
                this.g.put("moatTracker", this.f);
                this.i = true;
            }
        } catch (Exception e2) {
            new StringBuilder("Exception in startTrackingForImpression with message : ").append(e2.getMessage());
            a.a().a(new com.inmobi.commons.core.e.a(e2));
        } catch (Throwable th) {
            this.h.a(viewArr);
            throw th;
        }
        this.h.a(viewArr);
    }

    @SuppressLint({"SwitchIntDef"})
    public final void a(int i2) {
        try {
            if (this.f != null) {
                StringBuilder sb = new StringBuilder("Received event : ");
                sb.append(i2);
                sb.append(" for VideoTracker(");
                sb.append(this.f.hashCode());
                sb.append(")");
                switch (i2) {
                    case 1:
                        this.f.dispatchEvent(new MoatAdEvent(MoatAdEventType.AD_EVT_ENTER_FULLSCREEN));
                        break;
                    case 2:
                        this.f.dispatchEvent(new MoatAdEvent(MoatAdEventType.AD_EVT_EXIT_FULLSCREEN));
                        break;
                    case 3:
                        this.f.dispatchEvent(new MoatAdEvent(MoatAdEventType.AD_EVT_STOPPED));
                        break;
                    case 5:
                    case 16:
                        NativeVideoWrapper nativeVideoWrapper = (NativeVideoWrapper) this.a.getVideoContainerView();
                        if (!(nativeVideoWrapper == null || this.f == null)) {
                            NativeVideoView videoView = nativeVideoWrapper.getVideoView();
                            if (!this.i) {
                                this.f.changeTargetView(nativeVideoWrapper);
                                break;
                            } else {
                                ReactiveVideoTracker reactiveVideoTracker = this.f;
                                HashMap a = d.a(Param.LEVEL, "slicer", (JSONArray) this.g.get("clientLevels"), (JSONArray) this.g.get("clientSlicers"), (JSONObject) this.g.get("zMoatExtras"));
                                a.put("zMoatVASTIDs", (String) this.g.get("zMoatVASTIDs"));
                                reactiveVideoTracker.trackVideoAd(a, Integer.valueOf(videoView.getDuration()), nativeVideoWrapper);
                                this.i = false;
                                break;
                            }
                        }
                    case 6:
                        NativeVideoWrapper nativeVideoWrapper2 = (NativeVideoWrapper) this.a.getVideoContainerView();
                        if (nativeVideoWrapper2 != null) {
                            this.f.dispatchEvent(new MoatAdEvent(MoatAdEventType.AD_EVT_START, Integer.valueOf(nativeVideoWrapper2.getVideoView().getMediaPlayer().getCurrentPosition())));
                            break;
                        }
                        break;
                    case 7:
                        this.f.dispatchEvent(new MoatAdEvent(MoatAdEventType.AD_EVT_PAUSED));
                        break;
                    case 8:
                        this.f.dispatchEvent(new MoatAdEvent(MoatAdEventType.AD_EVT_PLAYING));
                        break;
                    case 9:
                        this.f.dispatchEvent(new MoatAdEvent(MoatAdEventType.AD_EVT_FIRST_QUARTILE));
                        break;
                    case 10:
                        this.f.dispatchEvent(new MoatAdEvent(MoatAdEventType.AD_EVT_MID_POINT));
                        break;
                    case 11:
                        this.f.dispatchEvent(new MoatAdEvent(MoatAdEventType.AD_EVT_THIRD_QUARTILE));
                        break;
                    case 12:
                        this.f.dispatchEvent(new MoatAdEvent(MoatAdEventType.AD_EVT_COMPLETE));
                        break;
                    case 13:
                        this.f.setPlayerVolume(MoatAdEvent.VOLUME_MUTED);
                        break;
                    case 14:
                        this.f.setPlayerVolume(MoatAdEvent.VOLUME_UNMUTED);
                        break;
                    case 15:
                        this.f.dispatchEvent(new MoatAdEvent(MoatAdEventType.AD_EVT_SKIPPED));
                        break;
                }
            }
        } catch (Exception e2) {
            new StringBuilder("Exception in onAdEvent with message : ").append(e2.getMessage());
            a.a().a(new com.inmobi.commons.core.e.a(e2));
        } catch (Throwable th) {
            this.h.a(i2);
            throw th;
        }
        this.h.a(i2);
    }

    public final void a(Context context, int i2) {
        this.h.a(context, i2);
    }

    public final void e() {
        this.f = null;
        this.e.clear();
        super.e();
        this.h.e();
    }

    public final void d() {
        try {
            if (!((bd) this.a).i() && this.f != null) {
                this.f.stopTracking();
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
}
