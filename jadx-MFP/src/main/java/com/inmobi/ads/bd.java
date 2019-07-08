package com.inmobi.ads;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.view.View;
import android.view.ViewGroup;
import com.brightcove.player.event.AbstractEvent;
import com.brightcove.player.event.EventType;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.share.internal.ShareConstants;
import com.inmobi.ads.AdContainer.RenderingProperties;
import com.inmobi.ads.AdContainer.RenderingProperties.PlacementType;
import com.inmobi.ads.ah.c;
import com.inmobi.ads.ai.a;
import com.inmobi.commons.core.e.b;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.integralads.avid.library.inmobi.session.AvidManagedVideoAdSession;
import com.mopub.common.Constants;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityParticipant.ProfileVisibility;
import java.lang.ref.WeakReference;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@TargetApi(15)
/* compiled from: NativeVideoAdContainer */
public class bd extends ah {
    /* access modifiers changed from: private */
    public static final String D = "bd";
    WeakReference<View> B;
    a C = new a() {
        public final void a(View view, boolean z) {
            bd.this.a(z);
            bd.a(bd.this, view, z);
        }
    };
    private final AdContainer.a E = new AdContainer.a() {
        public final void a() {
            bd.D;
            c e = bd.this.e();
            if (e != null) {
                e.a();
            }
        }

        public final void a(@NonNull Object obj) {
            if (bd.this.l() != null) {
                be beVar = (be) obj;
                bd.D;
                beVar.v.put("didRequestFullScreen", Boolean.valueOf(true));
                beVar.v.put("isFullScreen", Boolean.valueOf(true));
                beVar.v.put("shouldAutoPlay", Boolean.valueOf(true));
                if (beVar.y != null) {
                    beVar.y.v.put("didRequestFullScreen", Boolean.valueOf(true));
                    beVar.y.v.put("isFullScreen", Boolean.valueOf(true));
                    beVar.y.v.put("shouldAutoPlay", Boolean.valueOf(true));
                }
                if (PlacementType.PLACEMENT_TYPE_INLINE == bd.this.b.a) {
                    bd.this.getViewableAd().a(1);
                    beVar.a(TrackerEventType.TRACKER_EVENT_TYPE_FULLSCREEN, bd.this.g(beVar));
                }
                c e = bd.this.e();
                if (e != null) {
                    e.b();
                }
            }
        }

        public final void b(@NonNull Object obj) {
            bd.D;
            be beVar = (be) obj;
            beVar.v.put("didRequestFullScreen", Boolean.valueOf(false));
            beVar.v.put("isFullScreen", Boolean.valueOf(false));
            if (beVar.y != null) {
                beVar.y.v.put("didRequestFullScreen", Boolean.valueOf(false));
                beVar.y.v.put("isFullScreen", Boolean.valueOf(false));
                beVar.y.y = null;
            }
            beVar.y = null;
            if (bd.this.b.a == PlacementType.PLACEMENT_TYPE_INLINE) {
                bd.this.getViewableAd().a(2);
                if (bd.this.n != null) {
                    bd.this.n.getViewableAd().a(16);
                }
                beVar.a(TrackerEventType.TRACKER_EVENT_TYPE_EXIT_FULLSCREEN, bd.this.g(beVar));
            } else {
                bd.this.getViewableAd().a(3);
            }
            c e = bd.this.e();
            if (e != null) {
                e.f();
            }
        }
    };

    private void a(NativeVideoView nativeVideoView) {
        int videoVolume = nativeVideoView.getVideoVolume();
        int lastVolume = nativeVideoView.getLastVolume();
        if (videoVolume != lastVolume && lastVolume > 0) {
            b(true);
            nativeVideoView.setLastVolume(videoVolume);
        }
    }

    bd(@NonNull Context context, @NonNull RenderingProperties renderingProperties, @NonNull ao aoVar, @NonNull String str, @NonNull String str2, @Nullable Set<bq> set, @NonNull c cVar, long j, boolean z, String str3) {
        super(context, renderingProperties, aoVar, str, str2, set, cVar, j, z, str3);
        this.a = aoVar;
    }

    /* access modifiers changed from: 0000 */
    public final void a(View view) {
        if (!k() && !this.l && (view instanceof NativeVideoView)) {
            NativeVideoView nativeVideoView = (NativeVideoView) view;
            this.k = true;
            HashMap hashMap = new HashMap();
            hashMap.put("type", PlacementType.PLACEMENT_TYPE_FULLSCREEN == getRenderingProperties().a ? "int" : AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE);
            hashMap.put("clientRequestId", this.h);
            hashMap.put("impId", this.d);
            b.a();
            b.a("ads", "ViewableBeaconFired", hashMap);
            f((be) nativeVideoView.getTag());
        }
    }

    /* access modifiers changed from: 0000 */
    public final boolean i() {
        return PlacementType.PLACEMENT_TYPE_INLINE == this.b.a && l() != null;
    }

    @SuppressLint({"SwitchIntDef"})
    public ca getViewableAd() {
        Context j = j();
        if (this.j == null && j != null) {
            g();
            this.j = new ab(this, new cd(this));
            if (this.i != null) {
                if (j instanceof Activity) {
                    try {
                        Activity activity = (Activity) j;
                        for (bq bqVar : this.i) {
                            int i = bqVar.a;
                            if (i == 1) {
                                ca caVar = this.j;
                                Map<String, Object> map = bqVar.b;
                                be beVar = (be) this.a.c(ShareConstants.VIDEO_URL).get(0);
                                StringBuilder sb = new StringBuilder();
                                for (NativeTracker nativeTracker : beVar.u) {
                                    if (TrackerEventType.TRACKER_EVENT_TYPE_MOAT == nativeTracker.b) {
                                        sb.append(nativeTracker.a);
                                    }
                                }
                                if (sb.length() > 0) {
                                    map.put("zMoatVASTIDs", sb.toString());
                                }
                                this.j = new af(activity, caVar, this, map);
                            } else if (i == 3) {
                                AvidManagedVideoAdSession avidManagedVideoAdSession = (AvidManagedVideoAdSession) bqVar.b.get("avidAdSession");
                                if (avidManagedVideoAdSession != null) {
                                    this.j = new w(activity, this.j, this, avidManagedVideoAdSession);
                                }
                            } else if (i == 6) {
                                if (((List) bqVar.b.get("trackerUrls")) != null) {
                                    this.j = new com.inmobi.ads.e.a.b(this.j, this);
                                }
                            }
                        }
                    } catch (Exception e) {
                        new StringBuilder("Exception occurred while creating the video viewable ad : ").append(e.getMessage());
                        com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                    }
                } else {
                    HashMap hashMap = new HashMap();
                    hashMap.put("type", AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE);
                    hashMap.put("impId", this.d);
                    b.a();
                    b.a("ads", "TrackersForService", hashMap);
                }
            }
        }
        return this.j;
    }

    @NonNull
    public AdContainer.a getFullScreenEventsListener() {
        return this.E;
    }

    @Nullable
    public View getVideoContainerView() {
        WeakReference<View> weakReference = this.B;
        if (weakReference == null) {
            return null;
        }
        return (View) weakReference.get();
    }

    /* access modifiers changed from: 0000 */
    public final boolean n() {
        return !this.r;
    }

    /* access modifiers changed from: 0000 */
    public final void w() {
        this.j.a(5);
    }

    /* access modifiers changed from: 0000 */
    public final void e(be beVar) {
        new StringBuilder("Firing Q4 beacons for completion at ").append(beVar.E);
        beVar.v.put("didQ4Fire", Boolean.valueOf(true));
        beVar.a(TrackerEventType.TRACKER_EVENT_TYPE_Q4, g(beVar));
        this.j.a(12);
        HashMap hashMap = new HashMap();
        hashMap.put("url", beVar.b().b());
        hashMap.put("isCached", AppEventsConstants.EVENT_PARAM_VALUE_YES);
        hashMap.put("completeAfter", Integer.valueOf(beVar.E));
        a("VideoQ4Completed", (Map<String, Object>) hashMap);
    }

    /* access modifiers changed from: 0000 */
    public final void q() {
        super.q();
        NativeVideoWrapper nativeVideoWrapper = (NativeVideoWrapper) getVideoContainerView();
        if (nativeVideoWrapper != null) {
            NativeVideoView videoView = nativeVideoWrapper.getVideoView();
            if (this.b.a == PlacementType.PLACEMENT_TYPE_INLINE && !i() && videoView.getVideoVolume() > 0) {
                videoView.setLastVolume(-2);
                b(true);
            }
            videoView.pause();
        }
    }

    @VisibleForTesting
    private static String y() {
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i == 0) {
            i = (secureRandom.nextInt() & Integer.MAX_VALUE) % 10;
        }
        sb.append(i);
        for (int i2 = 1; i2 < 8; i2++) {
            sb.append((secureRandom.nextInt() & Integer.MAX_VALUE) % 10);
        }
        return sb.toString();
    }

    private void b(boolean z) {
        if (this.b.a == PlacementType.PLACEMENT_TYPE_INLINE && !i()) {
            c e = e();
            if (e != null) {
                e.a(z);
            }
        }
    }

    public void destroy() {
        if (!this.l) {
            if (getVideoContainerView() != null) {
                NativeVideoWrapper nativeVideoWrapper = (NativeVideoWrapper) getVideoContainerView();
                if (nativeVideoWrapper != null) {
                    nativeVideoWrapper.getVideoView().c();
                }
            }
            super.destroy();
        }
    }

    private void f(@NonNull be beVar) {
        if (!((Boolean) beVar.v.get("didImpressionFire")).booleanValue()) {
            List<NativeTracker> list = beVar.u;
            Map g = g(beVar);
            List<TrackerEventType> arrayList = new ArrayList<>();
            for (NativeTracker nativeTracker : list) {
                if (TrackerEventType.TRACKER_EVENT_TYPE_VIDEO_RENDER == nativeTracker.b) {
                    if (nativeTracker.a.startsWith(Constants.HTTP)) {
                        be.a(nativeTracker, g);
                    }
                    arrayList = (List) nativeTracker.d.get("referencedEvents");
                    for (TrackerEventType a : arrayList) {
                        beVar.a(a, g);
                    }
                }
            }
            if (arrayList.isEmpty()) {
                beVar.a(TrackerEventType.TRACKER_EVENT_TYPE_PLAY, g);
                beVar.a(TrackerEventType.TRACKER_EVENT_TYPE_RENDER, g);
            }
            this.a.d.a(TrackerEventType.TRACKER_EVENT_TYPE_RENDER, g(beVar));
            beVar.v.put("didImpressionFire", Boolean.valueOf(true));
            this.j.a(0);
            if (this.b.a == PlacementType.PLACEMENT_TYPE_INLINE) {
                HashMap hashMap = new HashMap();
                hashMap.put("type", AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE);
                hashMap.put("clientRequestId", this.h);
                hashMap.put("impId", this.d);
                a("AdRendered", (Map<String, Object>) hashMap);
            }
            if (e() != null) {
                e().d();
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void b(@NonNull ak akVar) {
        switch (akVar.l) {
            case 0:
                return;
            case 1:
                super.b(akVar);
                return;
            case 3:
                try {
                    if (this.w != null) {
                        this.w.d("window.imraid.broadcastEvent('replay');");
                    }
                    if (f() != null) {
                        View f = f();
                        NativeTimerView b = ah.b(f);
                        if (b != null) {
                            b.a();
                        }
                        ViewGroup viewGroup = (ViewGroup) f.getParent();
                        if (viewGroup != null) {
                            viewGroup.removeView(f);
                        }
                    }
                    if (!ShareConstants.VIDEO_URL.equals(akVar.b)) {
                        new StringBuilder("Action 3 not valid for asset of type: ").append(akVar.b);
                        return;
                    }
                    NativeVideoWrapper nativeVideoWrapper = (NativeVideoWrapper) getVideoContainerView();
                    if (nativeVideoWrapper != null) {
                        nativeVideoWrapper.getVideoView().e();
                        nativeVideoWrapper.getVideoView().start();
                    }
                    return;
                } catch (Exception e) {
                    new StringBuilder("Encountered unexpected error in handling replay action on video: ").append(e.getMessage());
                    Logger.a(InternalLogLevel.DEBUG, "InMobi", "SDK encountered unexpected error in replaying video");
                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                    return;
                }
            case 4:
                try {
                    if (PlacementType.PLACEMENT_TYPE_INLINE == this.b.a) {
                        NativeVideoWrapper nativeVideoWrapper2 = (NativeVideoWrapper) getVideoContainerView();
                        if (nativeVideoWrapper2 != null) {
                            NativeVideoView videoView = nativeVideoWrapper2.getVideoView();
                            be beVar = (be) videoView.getTag();
                            if (videoView.getState() != 1) {
                                try {
                                    if (!this.l) {
                                        if (this.p.get() != null) {
                                            if (!((Boolean) beVar.v.get("didRequestFullScreen")).booleanValue()) {
                                                beVar.v.put("didRequestFullScreen", Boolean.valueOf(true));
                                                beVar.v.put(AbstractEvent.SEEK_POSITION, Integer.valueOf(videoView.getCurrentPosition()));
                                                beVar.v.put("lastMediaVolume", Integer.valueOf(videoView.getVolume()));
                                                if (videoView.getMediaPlayer().isPlaying()) {
                                                    videoView.getMediaPlayer().pause();
                                                }
                                                videoView.getMediaPlayer().a = 4;
                                                beVar.v.put("isFullScreen", Boolean.valueOf(true));
                                                beVar.v.put(AbstractEvent.SEEK_POSITION, Integer.valueOf(videoView.getMediaPlayer().getCurrentPosition()));
                                                m();
                                            }
                                            return;
                                        }
                                    }
                                    return;
                                } catch (Exception e2) {
                                    new StringBuilder("SDK encountered unexpected error in handling the onVideoRequestedFullScreen event; ").append(e2.getMessage());
                                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
                                }
                            }
                        }
                    }
                    return;
                } catch (Exception e3) {
                    new StringBuilder("Encountered unexpected error in handling fullscreen action on video: ").append(e3.getMessage());
                    Logger.a(InternalLogLevel.DEBUG, "InMobi", "SDK encountered unexpected error in expanding video to fullscreen");
                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e3));
                    return;
                }
            case 5:
                try {
                    NativeVideoWrapper nativeVideoWrapper3 = (NativeVideoWrapper) getVideoContainerView();
                    if (nativeVideoWrapper3 != null) {
                        be beVar2 = (be) nativeVideoWrapper3.getVideoView().getTag();
                        beVar2.v.put("shouldAutoPlay", Boolean.valueOf(true));
                        if (beVar2.y != null) {
                            beVar2.y.v.put("shouldAutoPlay", Boolean.valueOf(true));
                        }
                        nativeVideoWrapper3.getVideoView().start();
                    }
                    return;
                } catch (Exception e4) {
                    new StringBuilder("Encountered unexpected error in handling play action on video: ").append(e4.getMessage());
                    Logger.a(InternalLogLevel.DEBUG, "InMobi", "SDK encountered unexpected error in playing video");
                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e4));
                    return;
                }
            default:
                try {
                    if (PlacementType.PLACEMENT_TYPE_FULLSCREEN == this.b.a) {
                        super.b(akVar);
                        if (!ShareConstants.VIDEO_URL.equals(akVar.b)) {
                            new StringBuilder("Action 2 not valid for asset of type: ").append(akVar.b);
                            return;
                        }
                        NativeVideoWrapper nativeVideoWrapper4 = (NativeVideoWrapper) getVideoContainerView();
                        if (nativeVideoWrapper4 != null) {
                            nativeVideoWrapper4.getVideoView().d();
                            NativeVideoView videoView2 = nativeVideoWrapper4.getVideoView();
                            if (videoView2.b() && videoView2.c.isPlaying()) {
                                videoView2.c.pause();
                                videoView2.c.seekTo(0);
                                if (videoView2.getTag() != null) {
                                    be beVar3 = (be) videoView2.getTag();
                                    beVar3.v.put(EventType.DID_PAUSE, Boolean.valueOf(true));
                                    beVar3.v.put(AbstractEvent.SEEK_POSITION, Integer.valueOf(0));
                                    beVar3.v.put("didCompleteQ4", Boolean.valueOf(true));
                                }
                                videoView2.c.a = 4;
                                videoView2.getPlaybackEventListener().a(4);
                            }
                            if (videoView2.c != null) {
                                videoView2.c.b = 4;
                            }
                        }
                        return;
                    }
                    c e5 = e();
                    if (e5 != null) {
                        e5.i();
                    }
                    return;
                } catch (Exception e6) {
                    new StringBuilder("Action 2 not valid for asset of type: ").append(akVar.b);
                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e6));
                    return;
                }
        }
    }

    /* access modifiers changed from: 0000 */
    public final void a(be beVar, int i) {
        if (!this.l) {
            HashMap hashMap = new HashMap();
            hashMap.put("errorCode", String.valueOf(i));
            hashMap.put("reason", "Video Player Error");
            hashMap.put("url", beVar.b().b());
            a("VideoError", (Map<String, Object>) hashMap);
            beVar.a(TrackerEventType.TRACKER_EVENT_TYPE_ERROR, g(beVar));
            this.j.a(17);
        }
    }

    /* access modifiers changed from: 0000 */
    public final void a(be beVar) {
        if (!this.l) {
            c(f());
            beVar.a(TrackerEventType.TRACKER_EVENT_TYPE_PAUSE, g(beVar));
            this.j.a(7);
        }
    }

    /* access modifiers changed from: 0000 */
    public final void b(be beVar) {
        if (!this.l) {
            d(f());
            beVar.a(TrackerEventType.TRACKER_EVENT_TYPE_RESUME, g(beVar));
            this.j.a(8);
        }
    }

    /* access modifiers changed from: 0000 */
    public final void c(be beVar) {
        if (!this.l) {
            beVar.v.put("lastMediaVolume", Integer.valueOf(0));
            beVar.a(TrackerEventType.TRACKER_EVENT_TYPE_MUTE, g(beVar));
            this.j.a(13);
        }
    }

    /* access modifiers changed from: 0000 */
    public final void d(be beVar) {
        if (!this.l) {
            beVar.v.put("lastMediaVolume", Integer.valueOf(15));
            beVar.a(TrackerEventType.TRACKER_EVENT_TYPE_UNMUTE, g(beVar));
            this.j.a(14);
        }
    }

    /* access modifiers changed from: 0000 */
    public final void b(be beVar, int i) {
        if (!this.l) {
            switch (i) {
                case 0:
                    beVar.a(TrackerEventType.TRACKER_EVENT_TYPE_Q1, g(beVar));
                    HashMap hashMap = new HashMap();
                    hashMap.put("url", beVar.b().b());
                    hashMap.put("isCached", AppEventsConstants.EVENT_PARAM_VALUE_YES);
                    a("VideoQ1Completed", (Map<String, Object>) hashMap);
                    this.j.a(9);
                    return;
                case 1:
                    beVar.a(TrackerEventType.TRACKER_EVENT_TYPE_Q2, g(beVar));
                    HashMap hashMap2 = new HashMap();
                    hashMap2.put("url", beVar.b().b());
                    hashMap2.put("isCached", AppEventsConstants.EVENT_PARAM_VALUE_YES);
                    a("VideoQ2Completed", (Map<String, Object>) hashMap2);
                    this.j.a(10);
                    return;
                case 2:
                    beVar.a(TrackerEventType.TRACKER_EVENT_TYPE_Q3, g(beVar));
                    HashMap hashMap3 = new HashMap();
                    hashMap3.put("url", beVar.b().b());
                    hashMap3.put("isCached", AppEventsConstants.EVENT_PARAM_VALUE_YES);
                    a("VideoQ3Completed", (Map<String, Object>) hashMap3);
                    this.j.a(11);
                    return;
                case 3:
                    if (!((Boolean) beVar.v.get("didQ4Fire")).booleanValue()) {
                        e(beVar);
                        break;
                    }
                    break;
            }
        }
    }

    /* access modifiers changed from: private */
    public Map<String, String> g(@NonNull be beVar) {
        am amVar = (am) beVar.t;
        HashMap hashMap = new HashMap(4);
        NativeVideoWrapper nativeVideoWrapper = (NativeVideoWrapper) this.B.get();
        if (nativeVideoWrapper != null) {
            hashMap.put("$MD", String.valueOf((int) Math.round((((double) nativeVideoWrapper.getVideoView().getDuration()) * 1.0d) / 1000.0d)));
        }
        hashMap.put("[ERRORCODE]", "405");
        long intValue = (long) ((Integer) beVar.v.get(AbstractEvent.SEEK_POSITION)).intValue();
        hashMap.put("[CONTENTPLAYHEAD]", String.format(Locale.US, "%02d:%02d:%02d.%03d", new Object[]{Long.valueOf(TimeUnit.MILLISECONDS.toHours(intValue)), Long.valueOf(TimeUnit.MILLISECONDS.toMinutes(intValue) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(intValue))), Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(intValue) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(intValue))), Long.valueOf(intValue - (TimeUnit.MILLISECONDS.toSeconds(intValue) * 1000))}));
        hashMap.put("[CACHEBUSTING]", y());
        hashMap.put("[ASSETURI]", beVar.b().b());
        hashMap.put("$TS", String.valueOf(System.currentTimeMillis()));
        hashMap.put("$LTS", String.valueOf(this.a.d.z));
        if (amVar != null) {
            hashMap.put("$STS", String.valueOf(amVar.z));
        }
        return hashMap;
    }

    static /* synthetic */ void a(bd bdVar, View view, final boolean z) {
        final NativeVideoView nativeVideoView = (NativeVideoView) view.findViewById(Integer.MAX_VALUE);
        if (nativeVideoView != null) {
            final be beVar = (be) nativeVideoView.getTag();
            if (beVar != null) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    public final void run() {
                        beVar.v.put(ProfileVisibility.VISIBLE, Boolean.valueOf(z));
                        if (!z || bd.this.m) {
                            bd.b(bd.this, nativeVideoView);
                            NativeVideoView nativeVideoView = nativeVideoView;
                            int i = beVar.F;
                            if (!nativeVideoView.i && 4 != nativeVideoView.getState()) {
                                if (nativeVideoView.h == null) {
                                    nativeVideoView.h = new Handler(Looper.getMainLooper());
                                }
                                if (i > 0) {
                                    nativeVideoView.i = true;
                                    nativeVideoView.d();
                                    nativeVideoView.h.postDelayed(new Runnable() {
                                        public final void run() {
                                            NativeVideoView.this.pause();
                                        }
                                    }, (long) (i * 1000));
                                    return;
                                }
                                nativeVideoView.pause();
                            }
                        } else {
                            beVar.v.put("lastVisibleTimestamp", Long.valueOf(SystemClock.uptimeMillis()));
                            if (nativeVideoView.i && nativeVideoView.getMediaPlayer() != null) {
                                if (beVar.a()) {
                                    nativeVideoView.e();
                                } else {
                                    nativeVideoView.d();
                                }
                            }
                            NativeVideoView nativeVideoView2 = nativeVideoView;
                            if (nativeVideoView2.h != null) {
                                nativeVideoView2.h.removeMessages(0);
                            }
                            nativeVideoView2.i = false;
                            bd.a(bd.this, nativeVideoView);
                            bd.a(bd.this, nativeVideoView, beVar);
                            if (1 == nativeVideoView.getState()) {
                                nativeVideoView.getMediaPlayer().b = 3;
                            } else if (2 == nativeVideoView.getState() || 4 == nativeVideoView.getState() || (5 == nativeVideoView.getState() && beVar.C)) {
                                nativeVideoView.start();
                            }
                        }
                    }
                });
            }
        }
    }

    static /* synthetic */ void a(bd bdVar, NativeVideoView nativeVideoView) {
        if (bdVar.b.a == PlacementType.PLACEMENT_TYPE_INLINE && !bdVar.i()) {
            int videoVolume = nativeVideoView.getVideoVolume();
            if (videoVolume != nativeVideoView.getLastVolume() && nativeVideoView.isPlaying()) {
                bdVar.b(videoVolume <= 0);
                nativeVideoView.setLastVolume(videoVolume);
            }
        }
    }

    static /* synthetic */ void a(bd bdVar, NativeVideoView nativeVideoView, be beVar) {
        if (bdVar.b.a == PlacementType.PLACEMENT_TYPE_INLINE && !bdVar.i() && !beVar.C && !nativeVideoView.isPlaying() && nativeVideoView.getState() == 5) {
            bdVar.a(nativeVideoView);
        }
    }

    static /* synthetic */ void b(bd bdVar, NativeVideoView nativeVideoView) {
        if (bdVar.b.a == PlacementType.PLACEMENT_TYPE_INLINE && !bdVar.i() && !bdVar.m) {
            bdVar.a(nativeVideoView);
        }
    }
}
