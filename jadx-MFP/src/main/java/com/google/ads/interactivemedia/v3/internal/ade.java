package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.google.ads.interactivemedia.v3.api.player.VideoAdPlayer.VideoAdPlayerCallback;
import com.google.ads.interactivemedia.v3.api.player.VideoProgressUpdate;
import java.util.ArrayList;
import java.util.List;

/* compiled from: IMASDK */
public final class ade implements aej {
    /* access modifiers changed from: private */
    public final cn a;
    private final SurfaceView b;
    /* access modifiers changed from: private */
    public final sc c;
    private final FrameLayout d;
    private final ViewGroup e;
    private final Context f;
    /* access modifiers changed from: private */
    public final List<VideoAdPlayerCallback> g;
    private final adg h;
    private final adi i;
    /* access modifiers changed from: private */
    public adh j;
    /* access modifiers changed from: private */
    public boolean k;

    public ade(Context context, ViewGroup viewGroup) {
        this(context, viewGroup, ax.a(context, new cl(context), (rz) new ri()));
    }

    public final int getVolume() {
        return 100;
    }

    private ade(Context context, ViewGroup viewGroup, cn cnVar) {
        this.f = context;
        this.e = viewGroup;
        this.a = cnVar;
        new Handler();
        this.g = new ArrayList(1);
        this.h = new adg(this);
        this.i = new adi(this);
        cnVar.a((cf) this.h);
        cnVar.a((vt) this.i);
        this.d = new FrameLayout(context);
        this.d.setBackgroundColor(-16777216);
        this.c = new sc(context);
        LayoutParams layoutParams = new LayoutParams(-1, -1);
        layoutParams.gravity = 17;
        this.c.setLayoutParams(layoutParams);
        this.j = adh.IDLE;
        this.b = new SurfaceView(context);
        this.b.setZOrderMediaOverlay(true);
        this.b.getHolder().addCallback(new adf(this));
        this.c.addView(this.b);
        this.d.addView(this.c);
        this.e.addView(this.d, new ViewGroup.LayoutParams(-1, -1));
    }

    public final void playAd() {
        switch (this.j.ordinal()) {
            case 1:
                for (VideoAdPlayerCallback onPlay : this.g) {
                    onPlay.onPlay();
                }
                this.a.a(this.b.getHolder());
                break;
            case 2:
                return;
            case 3:
                for (VideoAdPlayerCallback onResume : this.g) {
                    onResume.onResume();
                }
                break;
            default:
                String valueOf = String.valueOf(this.j);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 53);
                sb.append("Ignoring call to playAd during invalid player state: ");
                sb.append(valueOf);
                Log.w("IMA SDK", sb.toString());
                return;
        }
        this.j = adh.PLAYING;
        if (this.k) {
            this.a.b(true);
        }
    }

    public final void loadAd(String str) {
        ln lnVar;
        this.a.a(false);
        cn cnVar = this.a;
        cnVar.a(cnVar.e(), 0);
        Context context = this.f;
        sx sxVar = new sx(context, vf.a(context, vf.a(context, "IMA SDK ExoPlayer")));
        Uri parse = Uri.parse(str);
        int b2 = vf.b(parse);
        if (b2 != 0) {
            switch (b2) {
                case 2:
                    lnVar = new py((so) sxVar).a(parse);
                    break;
                case 3:
                    lnVar = new mn(sxVar, new aer()).a(parse);
                    break;
                default:
                    StringBuilder sb = new StringBuilder(29);
                    sb.append("Unsupported type: ");
                    sb.append(b2);
                    throw new IllegalStateException(sb.toString());
            }
        } else {
            lnVar = new od(sxVar).a(parse);
        }
        this.a.a(lnVar);
        this.j = adh.LOADED;
    }

    public final void stopAd() {
        this.j = adh.IDLE;
        this.a.a(false);
        this.a.a((Surface) null);
    }

    public final void pauseAd() {
        this.j = adh.PAUSED;
        this.a.b(false);
        for (VideoAdPlayerCallback onPause : this.g) {
            onPause.onPause();
        }
    }

    public final void resumeAd() {
        playAd();
    }

    public final void addCallback(VideoAdPlayerCallback videoAdPlayerCallback) {
        this.g.add(videoAdPlayerCallback);
    }

    public final void removeCallback(VideoAdPlayerCallback videoAdPlayerCallback) {
        this.g.remove(videoAdPlayerCallback);
    }

    public final VideoProgressUpdate getAdProgress() {
        if ((this.a.a() == 2 || this.a.a() == 3) && this.a.d() > 0) {
            return new VideoProgressUpdate(this.a.g(), this.a.d());
        }
        return VideoProgressUpdate.VIDEO_TIME_NOT_READY;
    }

    public final void a() {
        this.d.setVisibility(0);
        this.b.setVisibility(0);
    }

    public final void b() {
        this.d.setVisibility(8);
        this.b.setVisibility(4);
    }

    public final void c() {
        this.a.b((cf) this.h);
        this.a.b((vt) this.i);
        this.a.c();
        this.e.removeView(this.d);
    }

    /* access modifiers changed from: private */
    public final void d() {
        for (VideoAdPlayerCallback onError : this.g) {
            onError.onError();
        }
    }
}
