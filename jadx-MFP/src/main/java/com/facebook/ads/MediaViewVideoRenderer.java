package com.facebook.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import com.facebook.ads.internal.view.i.b.c;
import com.facebook.ads.internal.view.i.b.d;
import com.facebook.ads.internal.view.i.b.e;
import com.facebook.ads.internal.view.i.b.f;
import com.facebook.ads.internal.view.i.b.i;
import com.facebook.ads.internal.view.i.b.j;
import com.facebook.ads.internal.view.i.b.k;
import com.facebook.ads.internal.view.i.b.l;
import com.facebook.ads.internal.view.i.b.m;
import com.facebook.ads.internal.view.i.b.n;
import com.facebook.ads.internal.view.i.b.q;
import com.facebook.ads.internal.view.i.b.r;
import com.facebook.ads.internal.view.i.b.w;
import com.facebook.ads.internal.view.i.b.x;
import com.facebook.ads.internal.view.p;

public abstract class MediaViewVideoRenderer extends FrameLayout {
    private static final String d = "MediaViewVideoRenderer";
    @Nullable
    protected NativeAd a;
    protected VideoAutoplayBehavior b;
    final p c;
    private final n e = new n() {
        public void a(m mVar) {
            MediaViewVideoRenderer.this.onPrepared();
        }
    };
    private final l f = new l() {
        public void a(k kVar) {
            if (MediaViewVideoRenderer.this.a != null) {
                MediaViewVideoRenderer.this.a.f().a(true, true);
            }
            MediaViewVideoRenderer.this.onPlayed();
        }
    };
    private final j g = new j() {
        public void a(i iVar) {
            MediaViewVideoRenderer.this.onPaused();
        }
    };
    private final r h = new r() {
        public void a(q qVar) {
            MediaViewVideoRenderer.this.onSeek();
        }
    };
    private final d i = new d() {
        public void a(c cVar) {
            MediaViewVideoRenderer.this.onCompleted();
        }
    };
    private final x j = new x() {
        public void a(w wVar) {
            MediaViewVideoRenderer.this.onVolumeChanged();
        }
    };
    private final f k = new f() {
        public void a(e eVar) {
            if (MediaViewVideoRenderer.this.a != null) {
                MediaViewVideoRenderer.this.a.f().a(false, true);
            }
            MediaViewVideoRenderer.this.onError();
        }
    };
    private boolean l;
    private boolean m;

    public MediaViewVideoRenderer(Context context) {
        super(context);
        this.c = new p(context);
        b();
    }

    public MediaViewVideoRenderer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = new p(context, attributeSet);
        b();
    }

    public MediaViewVideoRenderer(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.c = new p(context, attributeSet, i2);
        b();
    }

    @TargetApi(21)
    public MediaViewVideoRenderer(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.c = new p(context, attributeSet, i2, i3);
        b();
    }

    private void b() {
        this.c.setEnableBackgroundVideo(shouldAllowBackgroundPlayback());
        LayoutParams layoutParams = new LayoutParams(-1, -1);
        this.c.setLayoutParams(layoutParams);
        super.addView(this.c, -1, layoutParams);
        com.facebook.ads.internal.w.b.j.a(this.c, com.facebook.ads.internal.w.b.j.INTERNAL_AD_MEDIA);
        this.c.getEventBus().a((T[]) new com.facebook.ads.internal.o.f[]{this.e, this.f, this.g, this.h, this.i, this.j, this.k});
    }

    /* access modifiers changed from: protected */
    public void a() {
        pause(false);
        this.c.setClientToken(null);
        this.c.setVideoMPD(null);
        this.c.setVideoURI(null);
        this.c.setVideoCTA(null);
        this.c.setNativeAd(null);
        this.b = VideoAutoplayBehavior.DEFAULT;
        NativeAd nativeAd = this.a;
        if (nativeAd != null) {
            nativeAd.f().a(false, false);
        }
        this.a = null;
    }

    public void addView(View view) {
    }

    public void addView(View view, int i2) {
    }

    public void addView(View view, int i2, int i3) {
    }

    public void addView(View view, int i2, LayoutParams layoutParams) {
    }

    public void addView(View view, LayoutParams layoutParams) {
    }

    public void destroy() {
        this.c.l();
    }

    public final void disengageSeek(VideoStartReason videoStartReason) {
        if (!this.l) {
            Log.w(d, "disengageSeek called without engageSeek.");
            return;
        }
        this.l = false;
        if (this.m) {
            this.c.a(videoStartReason.a());
        }
        onSeekDisengaged();
    }

    public final void engageSeek() {
        if (this.l) {
            Log.w(d, "engageSeek called without disengageSeek.");
            return;
        }
        this.l = true;
        this.m = com.facebook.ads.internal.view.i.d.d.STARTED.equals(this.c.getState());
        this.c.a(false);
        onSeekEngaged();
    }

    @IntRange
    public final int getCurrentTimeMs() {
        return this.c.getCurrentPositionInMillis();
    }

    @IntRange
    public final int getDuration() {
        return this.c.getDuration();
    }

    /* access modifiers changed from: 0000 */
    public final View getVideoView() {
        return this.c.getVideoView();
    }

    @FloatRange
    public final float getVolume() {
        return this.c.getVolume();
    }

    public void onCompleted() {
    }

    public void onError() {
    }

    public void onPaused() {
    }

    public void onPlayed() {
    }

    public void onPrepared() {
    }

    public void onSeek() {
    }

    public void onSeekDisengaged() {
    }

    public void onSeekEngaged() {
    }

    public void onVolumeChanged() {
    }

    public final void pause(boolean z) {
        this.c.a(z);
    }

    public final void play(VideoStartReason videoStartReason) {
        this.c.a(videoStartReason.a());
    }

    public final void seekTo(@IntRange int i2) {
        if (!this.l) {
            Log.w(d, "Seeking must be preceded by a call to engageSeek, and followed by a call to disengageSeek.");
        } else {
            this.c.a(i2);
        }
    }

    /* access modifiers changed from: 0000 */
    public final void setAdEventManager(com.facebook.ads.internal.s.c cVar) {
        this.c.setAdEventManager(cVar);
    }

    /* access modifiers changed from: 0000 */
    public final void setListener(com.facebook.ads.internal.view.q qVar) {
        this.c.setListener(qVar);
    }

    /* access modifiers changed from: protected */
    public void setNativeAd(NativeAd nativeAd) {
        this.a = nativeAd;
        this.c.setClientToken(nativeAd.h());
        this.c.setVideoMPD(nativeAd.b());
        this.c.setVideoURI(nativeAd.a());
        this.c.setVideoProgressReportIntervalMs(nativeAd.g().w());
        this.c.setVideoCTA(nativeAd.getAdCallToAction());
        this.c.setNativeAd(nativeAd);
        this.b = nativeAd.c();
    }

    public final void setVolume(@FloatRange float f2) {
        this.c.setVolume(f2);
    }

    public boolean shouldAllowBackgroundPlayback() {
        return false;
    }

    public final boolean shouldAutoplay() {
        p pVar = this.c;
        return (pVar == null || pVar.getState() == com.facebook.ads.internal.view.i.d.d.PLAYBACK_COMPLETED || this.b != VideoAutoplayBehavior.ON) ? false : true;
    }
}
