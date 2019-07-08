package com.inmobi.ads;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.appevents.AppEventsConstants;
import com.inmobi.ads.AdContainer.RenderingProperties.PlacementType;
import com.inmobi.ads.ah.c;
import com.inmobi.rendering.RenderView;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/* compiled from: NativeInflater */
public class at extends a implements b {
    private static final String b = "at";
    @NonNull
    final au a;
    /* access modifiers changed from: private */
    @NonNull
    public final ah c;
    private final c d = new c() {
        public final void a(int i, ak akVar) {
            if (!at.this.b()) {
                at.this.c.a(i, akVar);
            }
        }
    };
    private final a e = new a() {
        public final void a(View view, ak akVar) {
            if (!at.this.b()) {
                at.this.c.a(view, akVar);
                at.this.c.a(akVar, false);
            }
        }
    };
    private final bg f = new bg() {
        public final void a(NativeVideoView nativeVideoView) {
            if (!at.this.b() && (at.this.c instanceof bd)) {
                bd bdVar = (bd) at.this.c;
                nativeVideoView.setIsLockScreen(bdVar.u);
                NativeVideoWrapper nativeVideoWrapper = (NativeVideoWrapper) nativeVideoView.getParent();
                bdVar.B = new WeakReference<>(nativeVideoWrapper);
                NativeVideoController mediaController = nativeVideoWrapper.getVideoView().getMediaController();
                if (mediaController != null) {
                    mediaController.setVideoAd(bdVar);
                }
            }
        }

        public final void a(be beVar, int i) {
            if (!at.this.b() && (at.this.c instanceof bd)) {
                ((bd) at.this.c).a(beVar, i);
            }
        }

        public final void a() {
            if (!at.this.b() && (at.this.c instanceof bd)) {
                ((bd) at.this.c).w();
            }
        }

        public final void a(be beVar) {
            if (!at.this.b() && (at.this.c instanceof bd)) {
                bd bdVar = (bd) at.this.c;
                if (!bdVar.l) {
                    if (PlacementType.PLACEMENT_TYPE_INLINE == bdVar.b.a) {
                        if (((Integer) beVar.v.get("currentMediaVolume")).intValue() > 0 && ((Integer) beVar.v.get("lastMediaVolume")).intValue() == 0) {
                            bdVar.d(beVar);
                        }
                        if (((Integer) beVar.v.get("currentMediaVolume")).intValue() == 0 && ((Integer) beVar.v.get("lastMediaVolume")).intValue() > 0) {
                            bdVar.c(beVar);
                        }
                    }
                    if (!((Boolean) beVar.v.get("didStartPlaying")).booleanValue()) {
                        beVar.v.put("didStartPlaying", Boolean.valueOf(true));
                        bdVar.getViewableAd().a(6);
                        try {
                            HashMap hashMap = new HashMap();
                            hashMap.put("isCached", AppEventsConstants.EVENT_PARAM_VALUE_YES);
                            bdVar.a("VideoPlayed", (Map<String, Object>) hashMap);
                        } catch (Exception e) {
                            StringBuilder sb = new StringBuilder("Error in submitting telemetry event : (");
                            sb.append(e.getMessage());
                            sb.append(")");
                        }
                    }
                }
            }
        }

        public final void b(be beVar) {
            if (!at.this.b() && (at.this.c instanceof bd)) {
                ((bd) at.this.c).a(beVar);
            }
        }

        public final void c(be beVar) {
            if (!at.this.b() && (at.this.c instanceof bd)) {
                ((bd) at.this.c).b(beVar);
            }
        }

        public final void d(be beVar) {
            if (!at.this.b() && (at.this.c instanceof bd)) {
                bd bdVar = (bd) at.this.c;
                if (!((Boolean) beVar.v.get("didSignalVideoCompleted")).booleanValue()) {
                    bdVar.o();
                    c e = bdVar.e();
                    if (e != null) {
                        e.h();
                    }
                }
                if (PlacementType.PLACEMENT_TYPE_FULLSCREEN == bdVar.b.a) {
                    bdVar.c((ak) beVar);
                }
            }
        }

        public final void b(be beVar, int i) {
            if (!at.this.b() && (at.this.c instanceof bd)) {
                ((bd) at.this.c).b(beVar, i);
            }
        }

        public final void e(be beVar) {
            if (!at.this.b() && (at.this.c instanceof bd)) {
                ((bd) at.this.c).e(beVar);
            }
        }
    };

    public final /* bridge */ /* synthetic */ boolean b() {
        return super.b();
    }

    public at(@NonNull Context context, @NonNull c cVar, @NonNull ah ahVar, @NonNull ao aoVar) {
        this.c = ahVar;
        au auVar = new au(context, cVar, this.c, aoVar, this.d, this.e, this);
        this.a = auVar;
        NativeViewFactory nativeViewFactory = this.a.d;
        NativeViewFactory.a(ahVar.s);
        this.a.a = this.f;
    }

    public final View a(View view, ViewGroup viewGroup, boolean z, RenderView renderView) {
        aw awVar;
        if (view != null) {
            View findViewWithTag = view.findViewWithTag("InMobiAdView");
            if (findViewWithTag != null) {
                aw awVar2 = (aw) findViewWithTag;
                if (z) {
                    awVar = this.a.b(awVar2, viewGroup, renderView);
                } else {
                    awVar = this.a.a(awVar2, viewGroup, renderView);
                }
            } else if (z) {
                awVar = this.a.b(null, viewGroup, renderView);
            } else {
                awVar = this.a.a(null, viewGroup, renderView);
            }
        } else if (z) {
            awVar = this.a.b(null, viewGroup, renderView);
        } else {
            awVar = this.a.a(null, viewGroup, renderView);
        }
        awVar.a = new WeakReference<>(this.c);
        awVar.setTag("InMobiAdView");
        return awVar;
    }

    public final void a() {
        this.a.b();
        super.a();
    }

    public final void a(bb bbVar) {
        if (bbVar.k == 1) {
            this.c.b();
        }
    }
}
