package com.inmobi.ads;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Point;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.share.internal.ShareConstants;
import com.inmobi.ads.AdContainer.RenderingProperties.PlacementType;
import com.inmobi.rendering.RenderView;
import com.myfitnesspal.shared.constants.Constants.Analytics;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.List;

/* compiled from: NativeLayoutInflater */
final class au implements a {
    /* access modifiers changed from: private */
    public static final String e = "au";
    private static Handler n = new Handler(Looper.getMainLooper());
    /* access modifiers changed from: 0000 */
    @Nullable
    public bg a;
    int b = 0;
    /* access modifiers changed from: 0000 */
    public final n c;
    NativeViewFactory d;
    @NonNull
    private final WeakReference<Context> f;
    /* access modifiers changed from: private */
    @NonNull
    public final ao g;
    /* access modifiers changed from: private */
    @NonNull
    public final ah h;
    @NonNull
    private final c i;
    @NonNull
    private c j;
    /* access modifiers changed from: private */
    @NonNull
    public a k;
    /* access modifiers changed from: private */
    @Nullable
    public b l;
    private ax m;
    /* access modifiers changed from: private */
    public boolean o = false;
    private RenderView p;

    /* compiled from: NativeLayoutInflater */
    interface a {
        void a(View view, ak akVar);
    }

    /* compiled from: NativeLayoutInflater */
    interface b {
        void a(bb bbVar);
    }

    /* compiled from: NativeLayoutInflater */
    interface c {
        void a(int i, ak akVar);
    }

    au(@NonNull Context context, @NonNull c cVar, @NonNull ah ahVar, @NonNull ao aoVar, @NonNull c cVar2, @NonNull a aVar, @NonNull b bVar) {
        this.f = new WeakReference<>(context);
        this.h = ahVar;
        this.g = aoVar;
        this.j = cVar2;
        this.k = aVar;
        this.l = bVar;
        this.c = new n();
        this.i = cVar;
        this.d = NativeViewFactory.a(context);
    }

    public final Context a() {
        return (Context) this.f.get();
    }

    public final aw a(@Nullable aw awVar, @NonNull ViewGroup viewGroup, RenderView renderView) {
        this.p = renderView;
        aw a2 = a(awVar, viewGroup);
        if (!this.o) {
            b(a2, this.g.d);
        }
        return a2;
    }

    /* access modifiers changed from: 0000 */
    public final aw b(@Nullable aw awVar, @NonNull final ViewGroup viewGroup, RenderView renderView) {
        this.p = renderView;
        final aw a2 = a(awVar, viewGroup);
        n.post(new Runnable() {
            public final void run() {
                if (!au.this.o) {
                    au auVar = au.this;
                    auVar.b(a2, auVar.g.d);
                }
            }
        });
        return a2;
    }

    private aw a(@Nullable aw awVar, @NonNull ViewGroup viewGroup) {
        aw awVar2 = awVar == null ? (aw) this.d.a(a(), (ak) this.g.d, this.i) : awVar;
        if (!(awVar2 == null || awVar == null)) {
            ViewParent parent = awVar2.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(awVar2);
            }
            NativeViewFactory nativeViewFactory = this.d;
            for (int childCount = awVar2.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = awVar2.getChildAt(childCount);
                awVar2.removeViewAt(childCount);
                nativeViewFactory.a(childAt);
            }
            NativeViewFactory.a((View) awVar2, this.g.d.c);
        }
        NativeViewFactory.b(this.g.d.c.a.x);
        awVar2.setLayoutParams(NativeViewFactory.a((ak) this.g.d, viewGroup));
        return awVar2;
    }

    public final ViewGroup a(@NonNull ViewGroup viewGroup, @NonNull am amVar) {
        ViewGroup viewGroup2 = (ViewGroup) this.d.a(a(), (ak) amVar, this.i);
        if (viewGroup2 != null) {
            viewGroup2.setLayoutParams(NativeViewFactory.a((ak) amVar, viewGroup));
        }
        return viewGroup2;
    }

    public final int a(int i2) {
        this.b = i2;
        this.j.a(i2, this.g.a(i2));
        return d();
    }

    private void a(View view, final ak akVar) {
        boolean z;
        final List a2 = this.c.a(view, akVar);
        if (a2 == null) {
            TrackerEventType trackerEventType = TrackerEventType.TRACKER_EVENT_TYPE_CREATIVE_VIEW;
            Iterator it = akVar.u.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (trackerEventType == ((NativeTracker) it.next()).b) {
                        z = true;
                        break;
                    }
                } else {
                    z = false;
                    break;
                }
            }
            if (!z) {
                return;
            }
        }
        view.addOnAttachStateChangeListener(new OnAttachStateChangeListener() {
            public final void onViewAttachedToWindow(View view) {
                au.this.c.a(a2);
                au.this.h;
                ak a2 = ah.a(au.this.h.h(), akVar);
                ak akVar = akVar;
                TrackerEventType trackerEventType = TrackerEventType.TRACKER_EVENT_TYPE_CREATIVE_VIEW;
                ah e = au.this.h;
                if (a2 == null) {
                    a2 = akVar;
                }
                akVar.a(trackerEventType, e.a(a2));
            }

            public final void onViewDetachedFromWindow(View view) {
                view.removeOnAttachStateChangeListener(this);
                n d = au.this.c;
                List<a> list = a2;
                if (list != null) {
                    for (a aVar : list) {
                        aVar.a.cancel();
                    }
                    d.a.removeAll(list);
                }
            }
        });
    }

    /* access modifiers changed from: 0000 */
    public final ViewGroup b(@NonNull ViewGroup viewGroup, am amVar) {
        LayoutParams layoutParams;
        double d2;
        double d3;
        av avVar;
        ViewGroup viewGroup2 = viewGroup;
        a((ak) amVar, (View) viewGroup2);
        Iterator it = amVar.iterator();
        while (it.hasNext()) {
            ak akVar = (ak) it.next();
            if ("CONTAINER" != akVar.b) {
                View view = null;
                if ("WEBVIEW".equals(akVar.b)) {
                    bf bfVar = (bf) akVar;
                    if (bfVar.A) {
                        View view2 = this.p;
                        if (view2 != null) {
                            if (view2.getParent() != null) {
                                ((ViewGroup) view2.getParent()).removeView(view2);
                            }
                            this.p = null;
                            view = view2;
                        }
                    }
                    if (Analytics.UNKNOWN.equals(bfVar.z)) {
                    }
                } else if (ShareConstants.IMAGE_URL.equals(akVar.b) && akVar.e == null) {
                }
                if (view == null) {
                    view = this.d.a(a(), akVar, this.i);
                }
                if (view != null) {
                    final WeakReference weakReference = new WeakReference(view);
                    if (akVar.o != -1) {
                        view.setVisibility(4);
                        n.postDelayed(new Runnable() {
                            public final void run() {
                                View view = (View) weakReference.get();
                                if (view != null) {
                                    view.setVisibility(0);
                                }
                            }
                        }, (long) (akVar.o * 1000));
                    } else if (akVar.p != -1) {
                        n.postDelayed(new Runnable() {
                            public final void run() {
                                View view = (View) weakReference.get();
                                if (view != null) {
                                    view.setVisibility(4);
                                }
                            }
                        }, (long) (akVar.p * 1000));
                    }
                    view.setLayoutParams(NativeViewFactory.a(akVar, viewGroup2));
                    a(view, akVar);
                    viewGroup2.addView(view);
                    if (VERSION.SDK_INT >= 15 && ShareConstants.VIDEO_URL == akVar.b) {
                        final be beVar = (be) akVar;
                        NativeVideoView videoView = ((NativeVideoWrapper) view).getVideoView();
                        if (VERSION.SDK_INT >= 15) {
                            am amVar2 = (am) beVar.t;
                            long currentTimeMillis = System.currentTimeMillis();
                            if (!(amVar2 == null || 0 == amVar2.z)) {
                                currentTimeMillis = amVar2.z;
                            }
                            if (amVar2 != null) {
                                amVar2.z = currentTimeMillis;
                            }
                            videoView.setClickable(false);
                            videoView.setId(Integer.MAX_VALUE);
                            videoView.e = 0;
                            videoView.f = 0;
                            videoView.a = Uri.parse(((by) beVar.e).b());
                            if (PlacementType.PLACEMENT_TYPE_FULLSCREEN == ((PlacementType) beVar.v.get("placementType"))) {
                                avVar = new av();
                            } else {
                                avVar = av.a();
                            }
                            videoView.c = avVar;
                            if (videoView.d != 0) {
                                videoView.c.setAudioSessionId(videoView.d);
                            } else {
                                videoView.d = videoView.c.getAudioSessionId();
                            }
                            try {
                                videoView.c.setDataSource(videoView.getContext().getApplicationContext(), videoView.a, videoView.b);
                                videoView.setTag(beVar);
                                videoView.g = new d(videoView);
                                videoView.setSurfaceTextureListener(videoView.l);
                                videoView.setFocusable(true);
                                videoView.setFocusableInTouchMode(true);
                                videoView.requestFocus();
                            } catch (IOException unused) {
                                videoView.c.a = -1;
                                videoView.c.b = -1;
                            }
                            if (beVar.y != null) {
                                beVar.a((be) beVar.y);
                            }
                            videoView.setQuartileCompletedListener(new c() {
                                public final void a(int i) {
                                    if (au.this.a != null) {
                                        au.this.a.b(beVar, i);
                                        if (3 == i) {
                                            try {
                                                au.this.a.d(beVar);
                                            } catch (Exception e) {
                                                au.e;
                                                new StringBuilder("SDK encountered unexpected error in handling the onVideoCompleted event; ").append(e.getMessage());
                                            }
                                        }
                                    }
                                }
                            });
                            videoView.setPlaybackEventListener(new b() {
                                @SuppressLint({"SwitchIntDef"})
                                public final void a(int i) {
                                    if (au.this.a != null) {
                                        if (i != 5) {
                                            switch (i) {
                                                case 0:
                                                    try {
                                                        au.this.a.a();
                                                        return;
                                                    } catch (Exception e) {
                                                        au.e;
                                                        new StringBuilder("SDK encountered unexpected error in handling onVideoPrepared event; ").append(e.getMessage());
                                                        com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                                                        return;
                                                    }
                                                case 1:
                                                    try {
                                                        au.this.a.a(beVar);
                                                        return;
                                                    } catch (Exception e2) {
                                                        au.e;
                                                        new StringBuilder("SDK encountered unexpected error in handling onVideoPlayed event; ").append(e2.getMessage());
                                                        return;
                                                    }
                                                case 2:
                                                    try {
                                                        au.this.a.b(beVar);
                                                        return;
                                                    } catch (Exception e3) {
                                                        au.e;
                                                        new StringBuilder("SDK encountered unexpected error in handling onVideoPaused event; ").append(e3.getMessage());
                                                        return;
                                                    }
                                                case 3:
                                                    try {
                                                        au.this.a.c(beVar);
                                                        return;
                                                    } catch (Exception e4) {
                                                        au.e;
                                                        new StringBuilder("SDK encountered unexpected error in handling onVideoResumed event; ").append(e4.getMessage());
                                                        return;
                                                    }
                                                default:
                                                    return;
                                            }
                                        } else {
                                            try {
                                                au.this.a.e(beVar);
                                            } catch (Exception e5) {
                                                au.e;
                                                new StringBuilder("SDK encountered unexpected error in handling fireVideoQ4Beacons event; ").append(e5.getMessage());
                                            }
                                        }
                                    }
                                }
                            });
                            videoView.setMediaErrorListener(new a() {
                                public final void a(int i) {
                                    if (au.this.a != null) {
                                        try {
                                            au.this.a.a(beVar, i);
                                        } catch (Exception e) {
                                            au.e;
                                            new StringBuilder("SDK encountered unexpected error in handling the onVideoError event; ").append(e.getMessage());
                                        }
                                    }
                                }
                            });
                            bg bgVar = this.a;
                            if (bgVar != null) {
                                try {
                                    bgVar.a(videoView);
                                } catch (Exception e2) {
                                    new StringBuilder("SDK encountered unexpected error in handling the onVideoViewCreated event; ").append(e2.getMessage());
                                }
                            }
                        }
                    }
                    a(akVar, view);
                    if ("TIMER" == akVar.b) {
                        view.setTag("timerView");
                        final bb bbVar = (bb) akVar;
                        ((NativeTimerView) view).setTimerEventsListener(new b() {
                            public final void a() {
                                if (au.this.l != null) {
                                    au.this.l.a(bbVar);
                                }
                            }
                        });
                    }
                    if (VERSION.SDK_INT >= 15 && ShareConstants.VIDEO_URL == akVar.b) {
                        NativeVideoWrapper nativeVideoWrapper = (NativeVideoWrapper) view;
                        nativeVideoWrapper.setVideoEventListener(this.a);
                        be beVar2 = (be) nativeVideoWrapper.a.getTag();
                        if (beVar2 != null) {
                            try {
                                String b2 = beVar2.b().b();
                                MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
                                mediaMetadataRetriever.setDataSource(b2);
                                int intValue = Integer.valueOf(mediaMetadataRetriever.extractMetadata(18)).intValue();
                                int intValue2 = Integer.valueOf(mediaMetadataRetriever.extractMetadata(19)).intValue();
                                mediaMetadataRetriever.release();
                                Point point = beVar2.c.a;
                                double d4 = (double) intValue;
                                double d5 = (double) intValue2;
                                if (((double) NativeViewFactory.c(point.x)) / ((double) NativeViewFactory.c(point.y)) > d4 / d5) {
                                    d3 = d4 * ((((double) NativeViewFactory.c(point.y)) * 1.0d) / d5);
                                    d2 = (double) NativeViewFactory.c(point.y);
                                } else {
                                    d2 = d5 * ((((double) NativeViewFactory.c(point.x)) * 1.0d) / d4);
                                    d3 = (double) NativeViewFactory.c(point.x);
                                }
                                layoutParams = new LayoutParams((int) d3, (int) d2);
                            } catch (Exception e3) {
                                LayoutParams layoutParams2 = new LayoutParams(-1, -1);
                                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e3));
                                layoutParams = layoutParams2;
                            }
                            layoutParams.addRule(13);
                            nativeVideoWrapper.a.setLayoutParams(layoutParams);
                        }
                    }
                    if ("WEBVIEW" == akVar.b && (view instanceof RenderView)) {
                        RenderView renderView = (RenderView) view;
                        bf bfVar2 = (bf) akVar;
                        renderView.setScrollable(bfVar2.B);
                        renderView.setReferenceContainer(this.h.n);
                        renderView.setRenderViewEventListener(this.h.u());
                        renderView.setPlacementId(this.h.e);
                        renderView.setAllowAutoRedirection(this.h.g);
                        renderView.setCreativeId(this.h.f);
                        renderView.setImpressionId(this.h.d);
                        if (!bfVar2.A) {
                            ah ahVar = this.h;
                            if (ahVar.y == 0 && ahVar.x == null && ahVar.w == null) {
                                ahVar.x = renderView;
                            }
                        }
                    }
                }
            } else if (akVar.d.equalsIgnoreCase("card_scrollable")) {
                NativeScrollableContainer nativeScrollableContainer = (NativeScrollableContainer) this.d.a(a(), akVar, this.i);
                if (nativeScrollableContainer != null) {
                    this.m = ay.a(nativeScrollableContainer.getType(), this.g, this);
                    ax axVar = this.m;
                    if (axVar != null) {
                        nativeScrollableContainer.a((am) akVar, axVar, this.b, d(), this);
                        nativeScrollableContainer.setLayoutParams(NativeViewFactory.a(akVar, viewGroup2));
                        a((View) nativeScrollableContainer, akVar);
                        viewGroup2.addView(nativeScrollableContainer);
                    }
                }
            } else {
                ViewGroup viewGroup3 = (ViewGroup) this.d.a(a(), akVar, this.i);
                if (viewGroup3 != null) {
                    ViewGroup b3 = b(viewGroup3, (am) akVar);
                    b3.setLayoutParams(NativeViewFactory.a(akVar, viewGroup2));
                    a((View) b3, akVar);
                    viewGroup2.addView(b3);
                }
            }
        }
        return viewGroup2;
    }

    private int d() {
        if (this.b == 0) {
            return 8388611;
        }
        return this.g.b() - 1 == this.b ? 8388613 : 1;
    }

    /* access modifiers changed from: 0000 */
    public final void b() {
        this.o = true;
        this.f.clear();
        ax axVar = this.m;
        if (axVar != null) {
            axVar.destroy();
        }
    }

    private void a(final ak akVar, View view) {
        if (akVar.h) {
            view.setOnClickListener(new OnClickListener() {
                public final void onClick(View view) {
                    au.this.k.a(view, akVar);
                }
            });
        }
    }
}
