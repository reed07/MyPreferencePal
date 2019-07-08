package com.inmobi.ads;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings.System;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import com.inmobi.commons.core.utilities.b.b;
import com.integralads.avid.library.inmobi.session.AvidAdSessionManager;
import com.integralads.avid.library.inmobi.session.AvidManagedVideoAdSession;
import com.integralads.avid.library.inmobi.session.ExternalAvidAdSessionContext;
import com.integralads.avid.library.inmobi.video.AvidVideoPlaybackListener;
import java.lang.ref.WeakReference;
import java.util.Set;

/* compiled from: IasTrackedNativeV2VideoAd */
class w extends bz {
    private static final String d = "w";
    @NonNull
    private final WeakReference<Activity> e;
    @NonNull
    private final ca f;
    @NonNull
    private final AvidManagedVideoAdSession g;
    @Nullable
    private a h;
    @Nullable
    private WeakReference<View> i;

    /* compiled from: IasTrackedNativeV2VideoAd */
    private static final class a extends ContentObserver {
        private Context a;
        private int b = -1;
        private WeakReference<w> c;
        /* access modifiers changed from: private */
        public boolean d = false;

        a(Context context, w wVar) {
            super(new Handler());
            this.a = context;
            this.c = new WeakReference<>(wVar);
        }

        public final void onChange(boolean z) {
            super.onChange(z);
            Context context = this.a;
            if (context != null) {
                int a2 = b.a(context);
                if (a2 != this.b) {
                    this.b = a2;
                    w wVar = (w) this.c.get();
                    if (!this.d && wVar != null) {
                        w.a(wVar, a2);
                    }
                }
            }
        }
    }

    @NonNull
    static AvidManagedVideoAdSession a(@NonNull Context context, @NonNull Set<String> set) {
        AvidManagedVideoAdSession startAvidManagedVideoAdSession = AvidAdSessionManager.startAvidManagedVideoAdSession(context, new ExternalAvidAdSessionContext("7.2.6", true));
        if (context instanceof Activity) {
            startAvidManagedVideoAdSession.registerAdView(null, (Activity) context);
        } else {
            startAvidManagedVideoAdSession.registerAdView(null, null);
        }
        for (String injectJavaScriptResource : set) {
            startAvidManagedVideoAdSession.injectJavaScriptResource(injectJavaScriptResource);
        }
        return startAvidManagedVideoAdSession;
    }

    w(@NonNull Activity activity, @NonNull ca caVar, @NonNull bd bdVar, @NonNull AvidManagedVideoAdSession avidManagedVideoAdSession) {
        super(bdVar);
        this.e = new WeakReference<>(activity);
        this.f = caVar;
        this.g = avidManagedVideoAdSession;
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

    public final a f() {
        return this.f.f();
    }

    @NonNull
    public final c c() {
        return this.f.c();
    }

    /* access modifiers changed from: private */
    public void a(ViewGroup viewGroup, NativeVideoWrapper nativeVideoWrapper) {
        int childCount = viewGroup.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = viewGroup.getChildAt(i2);
            if (!childAt.equals(nativeVideoWrapper)) {
                this.g.registerFriendlyObstruction(childAt);
                if (childAt instanceof ViewGroup) {
                    ViewGroup viewGroup2 = (ViewGroup) childAt;
                    if (viewGroup2.getChildCount() > 0) {
                        a(viewGroup2, nativeVideoWrapper);
                    }
                }
            }
        }
    }

    private void g() {
        Activity activity = (Activity) this.e.get();
        if (activity != null && (this.a instanceof bd)) {
            final NativeVideoWrapper nativeVideoWrapper = (NativeVideoWrapper) this.a.getVideoContainerView();
            if (nativeVideoWrapper != null) {
                this.i = new WeakReference<>(nativeVideoWrapper);
                final View b = this.f.b();
                if (!(nativeVideoWrapper == null || b == null || !(b instanceof ViewGroup))) {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        public final void run() {
                            w.this.a((ViewGroup) b, nativeVideoWrapper);
                        }
                    });
                }
                this.g.registerAdView((View) this.i.get(), activity);
                if (this.h == null) {
                    this.h = new a(activity.getApplicationContext(), this);
                    activity.getContentResolver().registerContentObserver(System.CONTENT_URI, true, this.h);
                }
                new StringBuilder("Registered ad view with AVID Video AdSession ").append(this.g.hashCode());
            }
        }
    }

    @SuppressLint({"SwitchIntDef"})
    public final void a(int i2) {
        try {
            if (this.g.getAvidVideoPlaybackListener() != null) {
                StringBuilder sb = new StringBuilder("Sending event (");
                sb.append(i2);
                sb.append(") to IAS AdSession : ");
                sb.append(this.g.hashCode());
                switch (i2) {
                    case 0:
                        this.g.getAvidVideoPlaybackListener().recordAdImpressionEvent();
                        break;
                    case 1:
                        this.g.getAvidVideoPlaybackListener().recordAdExpandedChangeEvent();
                        this.g.getAvidVideoPlaybackListener().recordAdEnteredFullscreenEvent();
                        break;
                    case 2:
                        this.g.getAvidVideoPlaybackListener().recordAdExitedFullscreenEvent();
                        this.g.getAvidVideoPlaybackListener().recordAdUserMinimizeEvent();
                        break;
                    case 3:
                        this.g.getAvidVideoPlaybackListener().recordAdUserCloseEvent();
                        break;
                    case 4:
                        this.g.getAvidVideoPlaybackListener().recordAdClickThruEvent();
                        break;
                    case 6:
                        this.g.getAvidVideoPlaybackListener().recordAdStartedEvent();
                        this.g.getAvidVideoPlaybackListener().recordAdVideoStartEvent();
                        break;
                    case 7:
                        this.g.getAvidVideoPlaybackListener().recordAdPausedEvent();
                        this.g.unregisterAdView(this.i == null ? null : (View) this.i.get());
                        break;
                    case 8:
                        this.g.getAvidVideoPlaybackListener().recordAdPlayingEvent();
                        break;
                    case 9:
                        this.g.getAvidVideoPlaybackListener().recordAdVideoFirstQuartileEvent();
                        break;
                    case 10:
                        this.g.getAvidVideoPlaybackListener().recordAdVideoMidpointEvent();
                        break;
                    case 11:
                        this.g.getAvidVideoPlaybackListener().recordAdVideoThirdQuartileEvent();
                        break;
                    case 12:
                        this.g.getAvidVideoPlaybackListener().recordAdCompleteEvent();
                        this.g.getAvidVideoPlaybackListener().recordAdStoppedEvent();
                        break;
                    case 13:
                    case 14:
                        AvidVideoPlaybackListener avidVideoPlaybackListener = this.g.getAvidVideoPlaybackListener();
                        boolean z = true;
                        int i3 = 13 == i2 ? 0 : this.h != null ? b.a((Context) this.e.get()) : 1;
                        avidVideoPlaybackListener.recordAdVolumeChangeEvent(Integer.valueOf(i3));
                        if (this.h != null) {
                            a aVar = this.h;
                            if (13 != i2) {
                                z = false;
                            }
                            aVar.d = z;
                            break;
                        }
                        break;
                    case 15:
                        this.g.getAvidVideoPlaybackListener().recordAdSkippedEvent();
                        break;
                    case 16:
                        g();
                        break;
                    case 17:
                        this.g.getAvidVideoPlaybackListener().recordAdError("Unknown Player error");
                        break;
                }
            }
        } catch (Exception e2) {
            new StringBuilder("Exception in onAdEvent with message : ").append(e2.getMessage());
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
        } catch (Throwable th) {
            this.f.a(i2);
            throw th;
        }
        this.f.a(i2);
    }

    public final void a(Context context, int i2) {
        this.f.a(context, i2);
    }

    public final void e() {
        super.e();
        try {
            this.e.clear();
            if (this.i != null) {
                this.i.clear();
            }
            this.h = null;
        } catch (Exception e2) {
            new StringBuilder("Exception in destroy with message : ").append(e2.getMessage());
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
        } catch (Throwable th) {
            this.f.e();
            throw th;
        }
        this.f.e();
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x0020 */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0028 A[Catch:{ Exception -> 0x0039, all -> 0x0037 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(@android.support.annotation.Nullable android.view.View... r4) {
        /*
            r3 = this;
            com.inmobi.ads.ca r0 = r3.f     // Catch:{ Exception -> 0x0039 }
            com.inmobi.ads.c r0 = r0.c()     // Catch:{ Exception -> 0x0039 }
            com.inmobi.ads.c$l r0 = r0.k     // Catch:{ Exception -> 0x0039 }
            boolean r0 = r0.j     // Catch:{ Exception -> 0x0039 }
            if (r0 == 0) goto L_0x0031
            r3.g()     // Catch:{ Exception -> 0x0039 }
            com.integralads.avid.library.inmobi.session.AvidManagedVideoAdSession r0 = r3.g     // Catch:{ Exception -> 0x0020 }
            com.integralads.avid.library.inmobi.deferred.AvidDeferredAdSessionListener r0 = r0.getAvidDeferredAdSessionListener()     // Catch:{ Exception -> 0x0020 }
            if (r0 == 0) goto L_0x0020
            com.integralads.avid.library.inmobi.session.AvidManagedVideoAdSession r0 = r3.g     // Catch:{ Exception -> 0x0020 }
            com.integralads.avid.library.inmobi.deferred.AvidDeferredAdSessionListener r0 = r0.getAvidDeferredAdSessionListener()     // Catch:{ Exception -> 0x0020 }
            r0.recordReadyEvent()     // Catch:{ Exception -> 0x0020 }
        L_0x0020:
            com.integralads.avid.library.inmobi.session.AvidManagedVideoAdSession r0 = r3.g     // Catch:{ Exception -> 0x0039 }
            com.integralads.avid.library.inmobi.video.AvidVideoPlaybackListener r0 = r0.getAvidVideoPlaybackListener()     // Catch:{ Exception -> 0x0039 }
            if (r0 == 0) goto L_0x0031
            com.integralads.avid.library.inmobi.session.AvidManagedVideoAdSession r0 = r3.g     // Catch:{ Exception -> 0x0039 }
            com.integralads.avid.library.inmobi.video.AvidVideoPlaybackListener r0 = r0.getAvidVideoPlaybackListener()     // Catch:{ Exception -> 0x0039 }
            r0.recordAdLoadedEvent()     // Catch:{ Exception -> 0x0039 }
        L_0x0031:
            com.inmobi.ads.ca r0 = r3.f
            r0.a(r4)
            return
        L_0x0037:
            r0 = move-exception
            goto L_0x0055
        L_0x0039:
            r0 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0037 }
            java.lang.String r2 = "Exception in startTrackingForImpression with message : "
            r1.<init>(r2)     // Catch:{ all -> 0x0037 }
            java.lang.String r2 = r0.getMessage()     // Catch:{ all -> 0x0037 }
            r1.append(r2)     // Catch:{ all -> 0x0037 }
            com.inmobi.commons.core.a.a r1 = com.inmobi.commons.core.a.a.a()     // Catch:{ all -> 0x0037 }
            com.inmobi.commons.core.e.a r2 = new com.inmobi.commons.core.e.a     // Catch:{ all -> 0x0037 }
            r2.<init>(r0)     // Catch:{ all -> 0x0037 }
            r1.a(r2)     // Catch:{ all -> 0x0037 }
            goto L_0x0031
        L_0x0055:
            com.inmobi.ads.ca r1 = r3.f
            r1.a(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.w.a(android.view.View[]):void");
    }

    public final void d() {
        try {
            if (!((bd) this.a).i()) {
                this.g.unregisterAdView(this.i == null ? null : (View) this.i.get());
                this.g.endSession();
                new StringBuilder("Unregistered VideoView to IAS AdSession : ").append(this.g.hashCode());
            }
            Activity activity = (Activity) this.e.get();
            if (!(activity == null || this.h == null)) {
                activity.getContentResolver().unregisterContentObserver(this.h);
            }
        } catch (Exception e2) {
            new StringBuilder("Exception in stopTrackingForImpression with message : ").append(e2.getMessage());
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
        } catch (Throwable th) {
            this.f.d();
            throw th;
        }
        this.f.d();
    }

    static /* synthetic */ void a(w wVar, int i2) {
        try {
            if (wVar.g.getAvidVideoPlaybackListener() != null) {
                StringBuilder sb = new StringBuilder("Sending volumeChange to IAS AdSession(");
                sb.append(wVar.g.hashCode());
                sb.append(") with volume - ");
                sb.append(i2);
                wVar.g.getAvidVideoPlaybackListener().recordAdVolumeChangeEvent(Integer.valueOf(i2));
            }
        } catch (Exception e2) {
            new StringBuilder("Exception in onVolumeChange with message : ").append(e2.getMessage());
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
        }
    }
}
