package com.facebook.ads.internal.view.i.d;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.MediaController;
import android.widget.MediaController.MediaPlayerControl;
import com.facebook.ads.internal.o.b;
import com.facebook.ads.internal.settings.AdInternalSettings;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer.EventListener;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player.EventListener.CC;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.SimpleExoPlayer.VideoListener;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection.Factory;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Util;

@TargetApi(14)
public class a extends TextureView implements SurfaceTextureListener, c, EventListener, VideoListener {
    private static final String a = "a";
    private Uri b;
    @Nullable
    private String c;
    private e d;
    private Surface e;
    /* access modifiers changed from: private */
    @Nullable
    public SimpleExoPlayer f;
    /* access modifiers changed from: private */
    public MediaController g;
    private d h = d.IDLE;
    private d i = d.IDLE;
    private d j = d.IDLE;
    private boolean k = false;
    private View l;
    private boolean m = false;
    private boolean n = false;
    private long o;
    private long p;
    private long q;
    private int r;
    private int s;
    private float t = 1.0f;
    private int u = -1;
    private boolean v = false;
    private boolean w = false;
    private com.facebook.ads.internal.view.i.a.a x = com.facebook.ads.internal.view.i.a.a.NOT_STARTED;
    private boolean y = false;

    public a(Context context) {
        super(context);
    }

    public a(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public a(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }

    @TargetApi(21)
    public a(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
    }

    private void f() {
        Surface surface = this.e;
        if (surface != null) {
            surface.release();
            this.e = null;
        }
        SimpleExoPlayer simpleExoPlayer = this.f;
        if (simpleExoPlayer != null) {
            simpleExoPlayer.release();
            this.f = null;
        }
        this.g = null;
        this.m = false;
        setVideoState(d.IDLE);
    }

    private void setVideoState(d dVar) {
        if (dVar != this.h) {
            this.h = dVar;
            if (this.h == d.STARTED) {
                this.m = true;
            }
            e eVar = this.d;
            if (eVar != null) {
                eVar.a(dVar);
            }
        }
    }

    public void a() {
        if (!this.w) {
            a(false);
        }
    }

    public void a(int i2) {
        if (this.f != null) {
            this.u = getCurrentPosition();
            this.f.seekTo((long) i2);
            return;
        }
        this.q = (long) i2;
    }

    public void a(com.facebook.ads.internal.view.i.a.a aVar) {
        this.i = d.STARTED;
        this.x = aVar;
        if (this.f == null) {
            setup(this.b);
        } else if (this.h == d.PREPARED || this.h == d.PAUSED || this.h == d.PLAYBACK_COMPLETED) {
            this.f.setPlayWhenReady(true);
            setVideoState(d.STARTED);
        }
    }

    public void a(boolean z) {
        SimpleExoPlayer simpleExoPlayer = this.f;
        if (simpleExoPlayer != null) {
            simpleExoPlayer.setPlayWhenReady(false);
        } else {
            setVideoState(d.IDLE);
        }
    }

    public void b() {
        setVideoState(d.PLAYBACK_COMPLETED);
        c();
        this.q = 0;
    }

    public void c() {
        this.i = d.IDLE;
        SimpleExoPlayer simpleExoPlayer = this.f;
        if (simpleExoPlayer != null) {
            simpleExoPlayer.stop();
            this.f.release();
            this.f = null;
        }
        setVideoState(d.IDLE);
    }

    public boolean d() {
        SimpleExoPlayer simpleExoPlayer = this.f;
        return (simpleExoPlayer == null || simpleExoPlayer.getAudioFormat() == null) ? false : true;
    }

    public void e() {
        f();
    }

    public int getCurrentPosition() {
        SimpleExoPlayer simpleExoPlayer = this.f;
        if (simpleExoPlayer != null) {
            return (int) simpleExoPlayer.getCurrentPosition();
        }
        return 0;
    }

    public int getDuration() {
        SimpleExoPlayer simpleExoPlayer = this.f;
        if (simpleExoPlayer == null) {
            return 0;
        }
        return (int) simpleExoPlayer.getDuration();
    }

    public long getInitialBufferTime() {
        return this.p;
    }

    public com.facebook.ads.internal.view.i.a.a getStartReason() {
        return this.x;
    }

    public d getState() {
        return this.h;
    }

    public d getTargetState() {
        return this.i;
    }

    public int getVideoHeight() {
        return this.s;
    }

    public int getVideoWidth() {
        return this.r;
    }

    public View getView() {
        return this;
    }

    public float getVolume() {
        return this.t;
    }

    public void onLoadingChanged(boolean z) {
    }

    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
    }

    public void onPlayerError(ExoPlaybackException exoPlaybackException) {
        setVideoState(d.ERROR);
        exoPlaybackException.printStackTrace();
        b.a(com.facebook.ads.internal.o.a.a(exoPlaybackException, "[ExoPlayer] Error during playback of ExoPlayer"));
    }

    public void onPlayerStateChanged(boolean z, int i2) {
        d dVar;
        switch (i2) {
            case 1:
                dVar = d.IDLE;
                break;
            case 2:
                int i3 = this.u;
                if (i3 >= 0) {
                    this.u = -1;
                    this.d.a(i3, getCurrentPosition());
                    return;
                }
                return;
            case 3:
                if (this.o != 0) {
                    this.p = System.currentTimeMillis() - this.o;
                }
                setRequestedVolume(this.t);
                long j2 = this.q;
                if (j2 > 0 && j2 < this.f.getDuration()) {
                    this.f.seekTo(this.q);
                    this.q = 0;
                }
                if (this.f.getCurrentPosition() != 0 && !z && this.m) {
                    dVar = d.PAUSED;
                    break;
                } else if (!z && this.h != d.PLAYBACK_COMPLETED) {
                    setVideoState(d.PREPARED);
                    if (this.i == d.STARTED) {
                        a(this.x);
                        this.i = d.IDLE;
                        return;
                    }
                    return;
                } else {
                    return;
                }
                break;
            case 4:
                if (z) {
                    setVideoState(d.PLAYBACK_COMPLETED);
                }
                SimpleExoPlayer simpleExoPlayer = this.f;
                if (simpleExoPlayer != null) {
                    simpleExoPlayer.setPlayWhenReady(false);
                    if (!z) {
                        this.f.seekToDefaultPosition();
                    }
                }
                this.m = false;
                return;
            default:
                return;
        }
        setVideoState(dVar);
    }

    public void onPositionDiscontinuity() {
    }

    public /* synthetic */ void onPositionDiscontinuity(int i2) {
        CC.$default$onPositionDiscontinuity(this, i2);
    }

    public void onRenderedFirstFrame() {
    }

    public /* synthetic */ void onRepeatModeChanged(int i2) {
        CC.$default$onRepeatModeChanged(this, i2);
    }

    public /* synthetic */ void onSeekProcessed() {
        CC.$default$onSeekProcessed(this);
    }

    public /* synthetic */ void onShuffleModeEnabledChanged(boolean z) {
        CC.$default$onShuffleModeEnabledChanged(this, z);
    }

    public /* synthetic */ void onSurfaceSizeChanged(int i2, int i3) {
        com.google.android.exoplayer2.video.VideoListener.CC.$default$onSurfaceSizeChanged(this, i2, i3);
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i2, int i3) {
        Surface surface = this.e;
        if (surface != null) {
            surface.release();
        }
        this.e = new Surface(surfaceTexture);
        SimpleExoPlayer simpleExoPlayer = this.f;
        if (simpleExoPlayer != null) {
            simpleExoPlayer.setVideoSurface(this.e);
            this.k = false;
            if (this.h == d.PAUSED && this.j != d.PAUSED) {
                a(this.x);
            }
        }
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        Surface surface = this.e;
        if (surface != null) {
            surface.release();
            this.e = null;
            SimpleExoPlayer simpleExoPlayer = this.f;
            if (simpleExoPlayer != null) {
                simpleExoPlayer.setVideoSurface(null);
            }
        }
        if (!this.k) {
            this.j = this.n ? d.STARTED : this.h;
            this.k = true;
        }
        if (this.h != d.PAUSED) {
            a(false);
        }
        return true;
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i2, int i3) {
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    public void onTimelineChanged(Timeline timeline, Object obj) {
    }

    public /* synthetic */ void onTimelineChanged(Timeline timeline, @Nullable Object obj, int i2) {
        CC.$default$onTimelineChanged(this, timeline, obj, i2);
    }

    public void onTracksChanged(TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray) {
    }

    public void onVideoSizeChanged(int i2, int i3, int i4, float f2) {
        this.r = i2;
        this.s = i3;
        if (this.r != 0 && this.s != 0) {
            requestLayout();
        }
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (this.f != null) {
            MediaController mediaController = this.g;
            if (mediaController == null || !mediaController.isShowing()) {
                if (!z) {
                    if (!this.k) {
                        this.j = this.n ? d.STARTED : this.h;
                        this.k = true;
                    }
                    if (this.h != d.PAUSED) {
                        a();
                    }
                } else {
                    this.k = false;
                    if (this.h == d.PAUSED && this.j != d.PAUSED) {
                        a(this.x);
                    }
                }
            }
        }
    }

    public void setBackgroundDrawable(Drawable drawable) {
        if (VERSION.SDK_INT < 24) {
            super.setBackgroundDrawable(drawable);
        } else if (AdInternalSettings.isDebugBuild()) {
            Log.w(a, "Google always throw an exception with setBackgroundDrawable on Nougat above. so we silently ignore it.");
        }
    }

    public void setBackgroundPlaybackEnabled(boolean z) {
        this.w = z;
    }

    public void setControlsAnchorView(View view) {
        this.l = view;
        view.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (a.this.g != null && motionEvent.getAction() == 1) {
                    if (a.this.g.isShowing()) {
                        a.this.g.hide();
                    } else {
                        a.this.g.show();
                    }
                }
                return true;
            }
        });
    }

    public void setForeground(Drawable drawable) {
        if (VERSION.SDK_INT < 24) {
            super.setForeground(drawable);
        } else if (AdInternalSettings.isDebugBuild()) {
            Log.w(a, "Google always throw an exception with setForeground on Nougat above. so we silently ignore it.");
        }
    }

    public void setFullScreen(boolean z) {
        this.n = z;
        if (z && !this.v) {
            setOnTouchListener(new OnTouchListener() {
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (a.this.g != null && motionEvent.getAction() == 1) {
                        if (a.this.g.isShowing()) {
                            a.this.g.hide();
                        } else {
                            a.this.g.show();
                        }
                    }
                    return true;
                }
            });
        }
    }

    public void setRequestedVolume(float f2) {
        this.t = f2;
        if (this.f != null && this.h != d.PREPARING && this.h != d.IDLE) {
            this.f.setVolume(f2);
        }
    }

    public void setTestMode(boolean z) {
        this.y = z;
    }

    public void setVideoMPD(@Nullable String str) {
        this.c = str;
    }

    public void setVideoStateChangeListener(e eVar) {
        this.d = eVar;
    }

    public void setup(Uri uri) {
        if (this.f != null) {
            f();
        }
        this.b = uri;
        setSurfaceTextureListener(this);
        DefaultBandwidthMeter defaultBandwidthMeter = new DefaultBandwidthMeter();
        this.f = ExoPlayerFactory.newSimpleInstance(getContext(), (TrackSelector) new DefaultTrackSelector((Factory) new AdaptiveTrackSelection.Factory(defaultBandwidthMeter)), (LoadControl) new DefaultLoadControl());
        this.f.setVideoListener(this);
        this.f.addListener(this);
        this.f.setPlayWhenReady(false);
        if (this.n && !this.v) {
            this.g = new MediaController(getContext());
            MediaController mediaController = this.g;
            View view = this.l;
            if (view == 0) {
                view = this;
            }
            mediaController.setAnchorView(view);
            this.g.setMediaPlayer(new MediaPlayerControl() {
                public boolean canPause() {
                    return true;
                }

                public boolean canSeekBackward() {
                    return true;
                }

                public boolean canSeekForward() {
                    return true;
                }

                public int getAudioSessionId() {
                    if (a.this.f != null) {
                        return a.this.f.getAudioSessionId();
                    }
                    return 0;
                }

                public int getBufferPercentage() {
                    if (a.this.f != null) {
                        return a.this.f.getBufferedPercentage();
                    }
                    return 0;
                }

                public int getCurrentPosition() {
                    return a.this.getCurrentPosition();
                }

                public int getDuration() {
                    return a.this.getDuration();
                }

                public boolean isPlaying() {
                    return a.this.f != null && a.this.f.getPlayWhenReady();
                }

                public void pause() {
                    a.this.a(true);
                }

                public void seekTo(int i) {
                    a.this.a(i);
                }

                public void start() {
                    a.this.a(com.facebook.ads.internal.view.i.a.a.USER_STARTED);
                }
            });
            this.g.setEnabled(true);
        }
        String str = this.c;
        if (str == null || str.length() == 0 || this.y) {
            ExtractorMediaSource extractorMediaSource = new ExtractorMediaSource(this.b, new DefaultDataSourceFactory(getContext(), Util.getUserAgent(getContext(), "ads"), (TransferListener) defaultBandwidthMeter), new DefaultExtractorsFactory(), null, null);
            this.f.prepare(extractorMediaSource);
        }
        setVideoState(d.PREPARING);
        if (isAvailable()) {
            onSurfaceTextureAvailable(getSurfaceTexture(), 0, 0);
        }
    }
}
