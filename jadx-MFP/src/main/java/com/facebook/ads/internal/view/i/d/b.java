package com.facebook.ads.internal.view.i.d;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnInfoListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnSeekCompleteListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.media.MediaPlayer.TrackInfo;
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
import com.facebook.ads.internal.settings.AdInternalSettings;
import com.facebook.ads.internal.view.i.a.a;

@TargetApi(14)
public class b extends TextureView implements OnBufferingUpdateListener, OnCompletionListener, OnErrorListener, OnInfoListener, OnPreparedListener, OnSeekCompleteListener, OnVideoSizeChangedListener, SurfaceTextureListener, c {
    private static final String t = "b";
    private Uri a;
    private e b;
    private Surface c;
    /* access modifiers changed from: private */
    @Nullable
    public MediaPlayer d;
    /* access modifiers changed from: private */
    public MediaController e;
    private d f = d.IDLE;
    private d g = d.IDLE;
    private d h = d.IDLE;
    private boolean i = false;
    private View j;
    private int k = 0;
    private long l;
    private int m = 0;
    private int n = 0;
    private float o = 1.0f;
    private boolean p = false;
    private int q = 3;
    private boolean r = false;
    private boolean s = false;
    private int u = 0;
    /* access modifiers changed from: private */
    public boolean v = false;
    private a w = a.NOT_STARTED;
    private final MediaPlayerControl x = new MediaPlayerControl() {
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
            if (b.this.d != null) {
                return b.this.d.getAudioSessionId();
            }
            return 0;
        }

        public int getBufferPercentage() {
            return 0;
        }

        public int getCurrentPosition() {
            return b.this.getCurrentPosition();
        }

        public int getDuration() {
            return b.this.getDuration();
        }

        public boolean isPlaying() {
            return b.this.d != null && b.this.d.isPlaying();
        }

        public void pause() {
            b.this.a(true);
        }

        public void seekTo(int i) {
            b.this.a(i);
        }

        public void start() {
            b.this.a(a.USER_STARTED);
        }
    };

    public b(Context context) {
        super(context);
    }

    public b(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public b(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }

    @TargetApi(21)
    public b(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
    }

    private boolean a(@Nullable Surface surface) {
        MediaPlayer mediaPlayer = this.d;
        if (mediaPlayer == null) {
            return false;
        }
        try {
            mediaPlayer.setSurface(surface);
            return true;
        } catch (IllegalStateException e2) {
            com.facebook.ads.internal.w.h.a.b(getContext(), "player", com.facebook.ads.internal.w.h.b.I, e2);
            Log.d(t, "The MediaPlayer failed", e2);
            return false;
        }
    }

    private boolean f() {
        return this.f == d.PREPARED || this.f == d.STARTED || this.f == d.PAUSED || this.f == d.PLAYBACK_COMPLETED;
    }

    private boolean g() {
        MediaPlayer mediaPlayer = this.d;
        if (mediaPlayer == null) {
            return false;
        }
        try {
            mediaPlayer.reset();
            return true;
        } catch (IllegalStateException e2) {
            com.facebook.ads.internal.w.h.a.b(getContext(), "player", com.facebook.ads.internal.w.h.b.J, e2);
            Log.d(t, "The MediaPlayer failed", e2);
            return false;
        }
    }

    private void setVideoState(d dVar) {
        if (dVar != this.f) {
            this.f = dVar;
            e eVar = this.b;
            if (eVar != null) {
                eVar.a(dVar);
            }
        }
    }

    public void a() {
        if (!this.r) {
            a(false);
        }
    }

    public void a(int i2) {
        if (this.d == null || !f()) {
            this.k = i2;
        } else if (i2 < getDuration() && i2 > 0) {
            this.u = getCurrentPosition();
            this.k = i2;
            this.d.seekTo(i2);
        }
    }

    public void a(a aVar) {
        this.g = d.STARTED;
        this.w = aVar;
        if (this.f == d.STARTED || this.f == d.PREPARED || this.f == d.IDLE || this.f == d.PAUSED || this.f == d.PLAYBACK_COMPLETED) {
            MediaPlayer mediaPlayer = this.d;
            if (mediaPlayer == null) {
                setup(this.a);
            } else {
                int i2 = this.k;
                if (i2 > 0) {
                    mediaPlayer.seekTo(i2);
                }
                this.d.start();
                if (this.f != d.PREPARED || this.s) {
                    setVideoState(d.STARTED);
                }
            }
        }
        if (isAvailable()) {
            onSurfaceTextureAvailable(getSurfaceTexture(), 0, 0);
        }
    }

    public void a(boolean z) {
        d dVar;
        this.g = d.PAUSED;
        if (this.d != null) {
            if ((this.f == d.PREPARING || this.f == d.PREPARED) ? false : true) {
                if (z) {
                    this.h = d.PAUSED;
                    this.i = true;
                }
                this.d.pause();
                if (this.f != d.PLAYBACK_COMPLETED) {
                    dVar = d.PAUSED;
                }
            }
            return;
        }
        dVar = d.IDLE;
        setVideoState(dVar);
    }

    public void b() {
        setVideoState(d.PLAYBACK_COMPLETED);
        c();
        this.k = 0;
    }

    public void c() {
        this.g = d.IDLE;
        MediaPlayer mediaPlayer = this.d;
        if (mediaPlayer != null) {
            int currentPosition = mediaPlayer.getCurrentPosition();
            if (currentPosition > 0) {
                this.k = currentPosition;
            }
            this.d.stop();
            g();
            this.d.release();
            this.d = null;
            MediaController mediaController = this.e;
            if (mediaController != null) {
                mediaController.hide();
                this.e.setEnabled(false);
            }
        }
        setVideoState(d.IDLE);
    }

    @SuppressLint({"NewApi"})
    public boolean d() {
        if (this.d == null || VERSION.SDK_INT < 16) {
            return false;
        }
        try {
            for (TrackInfo trackType : this.d.getTrackInfo()) {
                if (trackType.getTrackType() == 2) {
                    return true;
                }
            }
            return false;
        } catch (RuntimeException e2) {
            Log.e(t, "Couldn't retrieve video information", e2);
            return true;
        }
    }

    public void e() {
        if (this.d != null) {
            a((Surface) null);
            this.d.setOnBufferingUpdateListener(null);
            this.d.setOnCompletionListener(null);
            this.d.setOnErrorListener(null);
            this.d.setOnInfoListener(null);
            this.d.setOnPreparedListener(null);
            this.d.setOnVideoSizeChangedListener(null);
            this.d.setOnSeekCompleteListener(null);
            g();
            this.d = null;
            setVideoState(d.IDLE);
        }
    }

    public int getCurrentPosition() {
        if (this.d == null || !f()) {
            return 0;
        }
        return this.d.getCurrentPosition();
    }

    public int getDuration() {
        if (this.d == null || !f()) {
            return 0;
        }
        return this.d.getDuration();
    }

    public long getInitialBufferTime() {
        return this.l;
    }

    public a getStartReason() {
        return this.w;
    }

    public d getState() {
        return this.f;
    }

    public d getTargetState() {
        return this.g;
    }

    public int getVideoHeight() {
        return this.n;
    }

    public int getVideoWidth() {
        return this.m;
    }

    public View getView() {
        return this;
    }

    public float getVolume() {
        return this.o;
    }

    public void onBufferingUpdate(MediaPlayer mediaPlayer, int i2) {
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        MediaPlayer mediaPlayer2 = this.d;
        if (mediaPlayer2 != null) {
            mediaPlayer2.pause();
        }
        setVideoState(d.PLAYBACK_COMPLETED);
        a(0);
        this.k = 0;
    }

    public boolean onError(MediaPlayer mediaPlayer, int i2, int i3) {
        if (this.q <= 0 || getState() != d.STARTED) {
            setVideoState(d.ERROR);
            c();
        } else {
            this.q--;
            c();
            a(this.w);
        }
        return true;
    }

    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onInfo(android.media.MediaPlayer r2, int r3, int r4) {
        /*
            r1 = this;
            r2 = 3
            r4 = 1
            if (r3 == r2) goto L_0x0022
            r2 = 0
            switch(r3) {
                case 701: goto L_0x001c;
                case 702: goto L_0x0009;
                default: goto L_0x0008;
            }
        L_0x0008:
            goto L_0x0021
        L_0x0009:
            com.facebook.ads.internal.view.i.d.d r3 = r1.f
            com.facebook.ads.internal.view.i.d.d r0 = com.facebook.ads.internal.view.i.d.d.PREPARING
            if (r3 == r0) goto L_0x0016
            com.facebook.ads.internal.view.i.d.d r3 = r1.f
            com.facebook.ads.internal.view.i.d.d r0 = com.facebook.ads.internal.view.i.d.d.PREPARED
            if (r3 == r0) goto L_0x0016
            goto L_0x0017
        L_0x0016:
            r4 = 0
        L_0x0017:
            if (r4 == 0) goto L_0x0021
            com.facebook.ads.internal.view.i.d.d r3 = com.facebook.ads.internal.view.i.d.d.STARTED
            goto L_0x001e
        L_0x001c:
            com.facebook.ads.internal.view.i.d.d r3 = com.facebook.ads.internal.view.i.d.d.BUFFERING
        L_0x001e:
            r1.setVideoState(r3)
        L_0x0021:
            return r2
        L_0x0022:
            r1.s = r4
            com.facebook.ads.internal.view.i.d.d r2 = r1.g
            com.facebook.ads.internal.view.i.d.d r3 = com.facebook.ads.internal.view.i.d.d.STARTED
            if (r2 != r3) goto L_0x002f
            com.facebook.ads.internal.view.i.d.d r2 = com.facebook.ads.internal.view.i.d.d.STARTED
            r1.setVideoState(r2)
        L_0x002f:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.view.i.d.b.onInfo(android.media.MediaPlayer, int, int):boolean");
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        setVideoState(d.PREPARED);
        if (this.p && !this.v) {
            this.e = new MediaController(getContext());
            MediaController mediaController = this.e;
            View view = this.j;
            if (view == 0) {
                view = this;
            }
            mediaController.setAnchorView(view);
            this.e.setMediaPlayer(this.x);
            this.e.setEnabled(true);
        }
        setRequestedVolume(this.o);
        this.m = mediaPlayer.getVideoWidth();
        this.n = mediaPlayer.getVideoHeight();
        int i2 = this.k;
        if (i2 > 0) {
            if (i2 >= this.d.getDuration()) {
                this.k = 0;
            }
            this.d.seekTo(this.k);
            this.k = 0;
        }
        if (this.g == d.STARTED) {
            a(this.w);
        }
    }

    public void onSeekComplete(MediaPlayer mediaPlayer) {
        e eVar = this.b;
        if (eVar != null) {
            eVar.a(this.u, this.k);
            this.k = 0;
        }
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i2, int i3) {
        if (this.c == null) {
            this.c = new Surface(surfaceTexture);
        }
        if (!a(this.c)) {
            setVideoState(d.ERROR);
            e();
            return;
        }
        this.i = false;
        if (this.f == d.PAUSED && this.h != d.PAUSED) {
            a(this.w);
        }
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        a((Surface) null);
        Surface surface = this.c;
        if (surface != null) {
            surface.release();
            this.c = null;
        }
        if (!this.i) {
            this.h = this.p ? d.STARTED : this.f;
            this.i = true;
        }
        if (this.f != d.PAUSED) {
            a(false);
        }
        return true;
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i2, int i3) {
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i2, int i3) {
        this.m = mediaPlayer.getVideoWidth();
        this.n = mediaPlayer.getVideoHeight();
        if (this.m != 0 && this.n != 0) {
            requestLayout();
        }
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (this.d != null) {
            MediaController mediaController = this.e;
            if (mediaController == null || !mediaController.isShowing()) {
                if (!z) {
                    if (!this.i) {
                        this.h = this.p ? d.STARTED : this.f;
                        this.i = true;
                    }
                    if (this.f != d.PAUSED) {
                        a();
                    }
                } else {
                    this.i = false;
                    if (this.f == d.PAUSED && this.h != d.PAUSED) {
                        a(this.w);
                    }
                }
            }
        }
    }

    public void setBackgroundDrawable(Drawable drawable) {
        if (VERSION.SDK_INT < 24) {
            super.setBackgroundDrawable(drawable);
        } else if (AdInternalSettings.isDebugBuild()) {
            Log.w(t, "Google always throw an exception with setBackgroundDrawable on Nougat above. so we silently ignore it.");
        }
    }

    public void setBackgroundPlaybackEnabled(boolean z) {
        this.r = z;
    }

    public void setControlsAnchorView(View view) {
        this.j = view;
        view.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (!b.this.v && b.this.e != null && motionEvent.getAction() == 1) {
                    if (b.this.e.isShowing()) {
                        b.this.e.hide();
                    } else {
                        b.this.e.show();
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
            Log.w(t, "Google always throw an exception with setForeground on Nougat above. so we silently ignore it.");
        }
    }

    public void setFullScreen(boolean z) {
        this.p = z;
        if (this.p && !this.v) {
            setOnTouchListener(new OnTouchListener() {
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (!b.this.v && b.this.e != null && motionEvent.getAction() == 1) {
                        if (b.this.e.isShowing()) {
                            b.this.e.hide();
                        } else {
                            b.this.e.show();
                        }
                    }
                    return true;
                }
            });
        }
    }

    public void setRequestedVolume(float f2) {
        this.o = f2;
        if (this.d != null && this.f != d.PREPARING && this.f != d.IDLE) {
            this.d.setVolume(f2, f2);
        }
    }

    public void setVideoMPD(@Nullable String str) {
    }

    public void setVideoStateChangeListener(e eVar) {
        this.b = eVar;
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x0118  */
    /* JADX WARNING: Removed duplicated region for block: B:41:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setup(android.net.Uri r10) {
        /*
            r9 = this;
            r0 = 0
            r9.s = r0
            r9.a = r10
            android.media.MediaPlayer r1 = r9.d
            r2 = 0
            if (r1 == 0) goto L_0x0018
            r9.g()
            r9.a(r2)
            android.media.MediaPlayer r1 = r9.d
            com.facebook.ads.internal.view.i.d.d r3 = com.facebook.ads.internal.view.i.d.d.IDLE
            r9.setVideoState(r3)
            goto L_0x001d
        L_0x0018:
            android.media.MediaPlayer r1 = new android.media.MediaPlayer
            r1.<init>()
        L_0x001d:
            java.lang.String r3 = r10.getScheme()     // Catch:{ Exception -> 0x00f0 }
            java.lang.String r4 = "asset"
            boolean r3 = r3.equals(r4)     // Catch:{ Exception -> 0x00f0 }
            if (r3 == 0) goto L_0x00c6
            android.content.Context r3 = r9.getContext()     // Catch:{ SecurityException -> 0x0071, IOException -> 0x006f }
            android.content.res.AssetManager r3 = r3.getAssets()     // Catch:{ SecurityException -> 0x0071, IOException -> 0x006f }
            java.lang.String r10 = r10.getPath()     // Catch:{ SecurityException -> 0x0071, IOException -> 0x006f }
            r4 = 1
            java.lang.String r10 = r10.substring(r4)     // Catch:{ SecurityException -> 0x0071, IOException -> 0x006f }
            android.content.res.AssetFileDescriptor r2 = r3.openFd(r10)     // Catch:{ SecurityException -> 0x0071, IOException -> 0x006f }
            long r5 = r2.getStartOffset()     // Catch:{ SecurityException -> 0x0071, IOException -> 0x006f }
            long r7 = r2.getLength()     // Catch:{ SecurityException -> 0x0071, IOException -> 0x006f }
            java.io.FileDescriptor r4 = r2.getFileDescriptor()     // Catch:{ SecurityException -> 0x0071, IOException -> 0x006f }
            r3 = r1
            r3.setDataSource(r4, r5, r7)     // Catch:{ SecurityException -> 0x0071, IOException -> 0x006f }
            if (r2 == 0) goto L_0x00cd
            r2.close()     // Catch:{ IOException -> 0x0055 }
            goto L_0x00cd
        L_0x0055:
            r10 = move-exception
            java.lang.String r2 = t     // Catch:{ Exception -> 0x00f0 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00f0 }
            r3.<init>()     // Catch:{ Exception -> 0x00f0 }
            java.lang.String r4 = "Unable to close"
            r3.append(r4)     // Catch:{ Exception -> 0x00f0 }
            r3.append(r10)     // Catch:{ Exception -> 0x00f0 }
            java.lang.String r10 = r3.toString()     // Catch:{ Exception -> 0x00f0 }
        L_0x0069:
            android.util.Log.w(r2, r10)     // Catch:{ Exception -> 0x00f0 }
            goto L_0x00cd
        L_0x006d:
            r10 = move-exception
            goto L_0x00a8
        L_0x006f:
            r10 = move-exception
            goto L_0x0072
        L_0x0071:
            r10 = move-exception
        L_0x0072:
            java.lang.String r3 = t     // Catch:{ all -> 0x006d }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x006d }
            r4.<init>()     // Catch:{ all -> 0x006d }
            java.lang.String r5 = "Failed to open assets "
            r4.append(r5)     // Catch:{ all -> 0x006d }
            r4.append(r10)     // Catch:{ all -> 0x006d }
            java.lang.String r10 = r4.toString()     // Catch:{ all -> 0x006d }
            android.util.Log.w(r3, r10)     // Catch:{ all -> 0x006d }
            com.facebook.ads.internal.view.i.d.d r10 = com.facebook.ads.internal.view.i.d.d.ERROR     // Catch:{ all -> 0x006d }
            r9.setVideoState(r10)     // Catch:{ all -> 0x006d }
            if (r2 == 0) goto L_0x00cd
            r2.close()     // Catch:{ IOException -> 0x0093 }
            goto L_0x00cd
        L_0x0093:
            r10 = move-exception
            java.lang.String r2 = t     // Catch:{ Exception -> 0x00f0 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00f0 }
            r3.<init>()     // Catch:{ Exception -> 0x00f0 }
            java.lang.String r4 = "Unable to close"
            r3.append(r4)     // Catch:{ Exception -> 0x00f0 }
            r3.append(r10)     // Catch:{ Exception -> 0x00f0 }
            java.lang.String r10 = r3.toString()     // Catch:{ Exception -> 0x00f0 }
            goto L_0x0069
        L_0x00a8:
            if (r2 == 0) goto L_0x00c5
            r2.close()     // Catch:{ IOException -> 0x00ae }
            goto L_0x00c5
        L_0x00ae:
            r2 = move-exception
            java.lang.String r3 = t     // Catch:{ Exception -> 0x00f0 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00f0 }
            r4.<init>()     // Catch:{ Exception -> 0x00f0 }
            java.lang.String r5 = "Unable to close"
            r4.append(r5)     // Catch:{ Exception -> 0x00f0 }
            r4.append(r2)     // Catch:{ Exception -> 0x00f0 }
            java.lang.String r2 = r4.toString()     // Catch:{ Exception -> 0x00f0 }
            android.util.Log.w(r3, r2)     // Catch:{ Exception -> 0x00f0 }
        L_0x00c5:
            throw r10     // Catch:{ Exception -> 0x00f0 }
        L_0x00c6:
            java.lang.String r10 = r10.toString()     // Catch:{ Exception -> 0x00f0 }
            r1.setDataSource(r10)     // Catch:{ Exception -> 0x00f0 }
        L_0x00cd:
            r1.setLooping(r0)     // Catch:{ Exception -> 0x00f0 }
            r1.setOnBufferingUpdateListener(r9)     // Catch:{ Exception -> 0x00f0 }
            r1.setOnCompletionListener(r9)     // Catch:{ Exception -> 0x00f0 }
            r1.setOnErrorListener(r9)     // Catch:{ Exception -> 0x00f0 }
            r1.setOnInfoListener(r9)     // Catch:{ Exception -> 0x00f0 }
            r1.setOnPreparedListener(r9)     // Catch:{ Exception -> 0x00f0 }
            r1.setOnVideoSizeChangedListener(r9)     // Catch:{ Exception -> 0x00f0 }
            r1.setOnSeekCompleteListener(r9)     // Catch:{ Exception -> 0x00f0 }
            r1.prepareAsync()     // Catch:{ Exception -> 0x00f0 }
            r9.d = r1     // Catch:{ Exception -> 0x00f0 }
            com.facebook.ads.internal.view.i.d.d r10 = com.facebook.ads.internal.view.i.d.d.PREPARING     // Catch:{ Exception -> 0x00f0 }
            r9.setVideoState(r10)     // Catch:{ Exception -> 0x00f0 }
            goto L_0x010f
        L_0x00f0:
            r10 = move-exception
            com.facebook.ads.internal.view.i.d.d r2 = com.facebook.ads.internal.view.i.d.d.ERROR
            r9.setVideoState(r2)
            r1.release()
            java.lang.String r1 = t
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Cannot prepare media player with SurfaceTexture: "
            r2.append(r3)
            r2.append(r10)
            java.lang.String r10 = r2.toString()
            android.util.Log.e(r1, r10)
        L_0x010f:
            r9.setSurfaceTextureListener(r9)
            boolean r10 = r9.isAvailable()
            if (r10 == 0) goto L_0x011f
            android.graphics.SurfaceTexture r10 = r9.getSurfaceTexture()
            r9.onSurfaceTextureAvailable(r10, r0, r0)
        L_0x011f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.view.i.d.b.setup(android.net.Uri):void");
    }
}
