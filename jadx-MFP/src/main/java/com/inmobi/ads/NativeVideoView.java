package com.inmobi.ads;

import android.annotation.TargetApi;
import android.app.KeyguardManager;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.AudioAttributes.Builder;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnInfoListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.view.Surface;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;
import android.view.View.MeasureSpec;
import android.widget.ImageView;
import android.widget.MediaController.MediaPlayerControl;
import android.widget.ProgressBar;
import com.brightcove.player.event.AbstractEvent;
import com.brightcove.player.event.EventType;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.inmobi.ads.AdContainer.RenderingProperties.PlacementType;
import com.inmobi.ads.cache.a.C0044a;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;

@TargetApi(15)
public class NativeVideoView extends TextureView implements MediaPlayerControl {
    /* access modifiers changed from: private */
    public static final String m = "NativeVideoView";
    private OnCompletionListener A = new OnCompletionListener() {
        public final void onCompletion(MediaPlayer mediaPlayer) {
            try {
                NativeVideoView.f(NativeVideoView.this);
            } catch (Exception e) {
                NativeVideoView.m;
                new StringBuilder("SDK encountered unexpected error in handling the media playback complete event; ").append(e.getMessage());
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            }
        }
    };
    private OnInfoListener B = new OnInfoListener() {
        @TargetApi(17)
        public final boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
            if (VERSION.SDK_INT >= 17 && 3 == i) {
                NativeVideoView.this.a(8, 8);
            }
            return true;
        }
    };
    private OnBufferingUpdateListener C = new OnBufferingUpdateListener() {
        public final void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
            NativeVideoView.this.w = i;
        }
    };
    private OnErrorListener D = new OnErrorListener() {
        public final boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
            NativeVideoView.m;
            StringBuilder sb = new StringBuilder("Media Play Error ");
            sb.append(i);
            sb.append(",");
            sb.append(i2);
            if (NativeVideoView.this.t != null) {
                NativeVideoView.this.t.a(i);
            }
            if (NativeVideoView.this.c != null) {
                NativeVideoView.this.c.a = -1;
                NativeVideoView.this.c.b = -1;
            }
            if (NativeVideoView.this.v != null) {
                NativeVideoView.this.v.b();
            }
            NativeVideoView.h(NativeVideoView.this);
            return true;
        }
    };
    Uri a;
    Map<String, String> b;
    /* access modifiers changed from: 0000 */
    public av c = null;
    int d;
    /* access modifiers changed from: 0000 */
    public int e;
    /* access modifiers changed from: 0000 */
    public int f;
    d g;
    @Nullable
    Handler h;
    boolean i;
    OnVideoSizeChangedListener j = new OnVideoSizeChangedListener() {
        public final void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
            NativeVideoView.this.e = mediaPlayer.getVideoWidth();
            NativeVideoView.this.f = mediaPlayer.getVideoHeight();
            if (NativeVideoView.this.e != 0 && NativeVideoView.this.f != 0) {
                NativeVideoView.this.requestLayout();
            }
        }
    };
    OnPreparedListener k = new OnPreparedListener() {
        public final void onPrepared(MediaPlayer mediaPlayer) {
            if (NativeVideoView.this.c != null) {
                NativeVideoView.this.c.a = 2;
                NativeVideoView nativeVideoView = NativeVideoView.this;
                nativeVideoView.x = nativeVideoView.y = nativeVideoView.z = true;
                if (NativeVideoView.this.v != null) {
                    NativeVideoView.this.v.setEnabled(true);
                }
                NativeVideoView.this.e = mediaPlayer.getVideoWidth();
                NativeVideoView.this.f = mediaPlayer.getVideoHeight();
                be beVar = (be) NativeVideoView.this.getTag();
                int i = 0;
                if (beVar != null && ((Boolean) beVar.v.get("didCompleteQ4")).booleanValue()) {
                    NativeVideoView.this.a(8, 0);
                    if (((PlacementType) beVar.v.get("placementType")) == PlacementType.PLACEMENT_TYPE_FULLSCREEN) {
                        return;
                    }
                }
                if (NativeVideoView.this.getPlaybackEventListener() != null) {
                    NativeVideoView.this.getPlaybackEventListener().a(0);
                }
                if (beVar != null && !((Boolean) beVar.v.get("didCompleteQ4")).booleanValue()) {
                    i = ((Integer) beVar.v.get(AbstractEvent.SEEK_POSITION)).intValue();
                }
                if (NativeVideoView.this.e == 0 || NativeVideoView.this.f == 0) {
                    if (3 == NativeVideoView.this.c.b && beVar != null && ((Boolean) beVar.v.get("isFullScreen")).booleanValue()) {
                        NativeVideoView.this.start();
                    }
                } else if (3 == NativeVideoView.this.c.b) {
                    if (beVar != null && ((Boolean) beVar.v.get("isFullScreen")).booleanValue()) {
                        NativeVideoView.this.start();
                    }
                    if (NativeVideoView.this.v != null) {
                        NativeVideoView.this.v.a();
                    }
                } else if (!NativeVideoView.this.isPlaying() && ((i != 0 || NativeVideoView.this.getCurrentPosition() > 0) && NativeVideoView.this.v != null)) {
                    NativeVideoView.this.v.a();
                }
            }
        }
    };
    final SurfaceTextureListener l = new SurfaceTextureListener() {
        public final void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        }

        @TargetApi(16)
        public final void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
            NativeVideoView.this.n = new Surface(surfaceTexture);
            NativeVideoView.this.g();
        }

        public final void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
            boolean z = true;
            boolean z2 = NativeVideoView.this.c != null && NativeVideoView.this.c.b == 3;
            if (i <= 0 || i2 <= 0) {
                z = false;
            }
            if (NativeVideoView.this.c != null && z2 && z) {
                if (NativeVideoView.this.getTag() != null) {
                    int intValue = ((Integer) ((be) NativeVideoView.this.getTag()).v.get(AbstractEvent.SEEK_POSITION)).intValue();
                    if (intValue != 0) {
                        NativeVideoView.this.a(intValue);
                    }
                }
                NativeVideoView.this.start();
            }
        }

        public final boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
            if (NativeVideoView.this.n != null) {
                NativeVideoView.this.n.release();
                NativeVideoView.this.n = null;
            }
            if (NativeVideoView.this.v != null) {
                NativeVideoView.this.v.b();
            }
            NativeVideoView.this.c();
            return true;
        }
    };
    /* access modifiers changed from: private */
    public Surface n = null;
    private int o;
    private int p = Integer.MIN_VALUE;
    private int q = 0;
    private c r;
    private b s;
    /* access modifiers changed from: private */
    public a t;
    private boolean u;
    /* access modifiers changed from: private */
    public NativeVideoController v;
    /* access modifiers changed from: private */
    public int w;
    /* access modifiers changed from: private */
    public boolean x;
    /* access modifiers changed from: private */
    public boolean y;
    /* access modifiers changed from: private */
    public boolean z;

    interface a {
        void a(int i);
    }

    interface b {
        void a(int i);
    }

    interface c {
        void a(int i);
    }

    static final class d extends Handler {
        private final WeakReference<NativeVideoView> a;

        d(NativeVideoView nativeVideoView) {
            this.a = new WeakReference<>(nativeVideoView);
        }

        public final void handleMessage(Message message) {
            NativeVideoView nativeVideoView = (NativeVideoView) this.a.get();
            if (nativeVideoView != null && message.what == 1) {
                int duration = nativeVideoView.getDuration();
                int currentPosition = nativeVideoView.getCurrentPosition();
                if (!(duration == -1 || currentPosition == 0)) {
                    be beVar = (be) nativeVideoView.getTag();
                    if (!((Boolean) beVar.v.get("didCompleteQ1")).booleanValue() && (currentPosition * 4) - duration >= 0) {
                        beVar.v.put("didCompleteQ1", Boolean.valueOf(true));
                        nativeVideoView.getQuartileCompletedListener().a(0);
                    }
                    if (!((Boolean) beVar.v.get("didCompleteQ2")).booleanValue() && (currentPosition * 2) - duration >= 0) {
                        beVar.v.put("didCompleteQ2", Boolean.valueOf(true));
                        nativeVideoView.getQuartileCompletedListener().a(1);
                    }
                    if (!((Boolean) beVar.v.get("didCompleteQ3")).booleanValue() && (currentPosition * 4) - (duration * 3) >= 0) {
                        beVar.v.put("didCompleteQ3", Boolean.valueOf(true));
                        nativeVideoView.getQuartileCompletedListener().a(2);
                    }
                    boolean booleanValue = ((Boolean) beVar.v.get("didQ4Fire")).booleanValue();
                    if ((((float) currentPosition) / ((float) duration)) * 100.0f > ((float) beVar.E) && !booleanValue) {
                        nativeVideoView.getPlaybackEventListener().a(5);
                    }
                }
                sendEmptyMessageDelayed(1, 1000);
            }
            super.handleMessage(message);
        }
    }

    public void seekTo(int i2) {
    }

    public NativeVideoView(Context context) {
        super(context);
        requestLayout();
        invalidate();
    }

    public av getMediaPlayer() {
        return this.c;
    }

    public final void a() {
        Surface surface = this.n;
        if (surface != null) {
            surface.release();
            this.n = null;
        }
        c();
    }

    public int getState() {
        av avVar = this.c;
        if (avVar != null) {
            return avVar.a;
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        int i4;
        int i5;
        int i6;
        try {
            int defaultSize = getDefaultSize(this.e, i2);
            int defaultSize2 = getDefaultSize(this.f, i3);
            if (this.e <= 0 || this.f <= 0) {
                i5 = defaultSize;
                i4 = defaultSize2;
            } else {
                int mode = MeasureSpec.getMode(i2);
                i5 = MeasureSpec.getSize(i2);
                int mode2 = MeasureSpec.getMode(i3);
                i4 = MeasureSpec.getSize(i3);
                if (mode == 1073741824 && mode2 == 1073741824) {
                    if (this.e * i4 < this.f * i5) {
                        i4 = (this.f * i5) / this.e;
                    } else if (this.e * i4 > this.f * i5) {
                        i5 = (this.e * i4) / this.f;
                    }
                } else if (mode == 1073741824) {
                    int i7 = (this.f * i5) / this.e;
                    if (mode2 != Integer.MIN_VALUE || i7 <= i4) {
                        i4 = i7;
                    }
                } else {
                    if (mode2 == 1073741824) {
                        i6 = (this.e * i4) / this.f;
                        if (mode == Integer.MIN_VALUE && i6 > i5) {
                        }
                    } else {
                        int i8 = this.e;
                        int i9 = this.f;
                        if (mode2 != Integer.MIN_VALUE || i9 <= i4) {
                            i6 = i8;
                            i4 = i9;
                        } else {
                            i6 = (this.e * i4) / this.f;
                        }
                        if (mode == Integer.MIN_VALUE && i6 > i5) {
                            i4 = (this.f * i5) / this.e;
                        }
                    }
                    i5 = i6;
                }
            }
            setMeasuredDimension(i5, i4);
        } catch (Exception e2) {
            new StringBuilder("SDK encountered unexpected error in handling the onMeasure event; ").append(e2.getMessage());
        }
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public NativeVideoController getMediaController() {
        return this.v;
    }

    /* access modifiers changed from: 0000 */
    public final boolean b() {
        av avVar = this.c;
        return (avVar == null || avVar.a == -1 || this.c.a == 0 || this.c.a == 1) ? false : true;
    }

    public void setIsLockScreen(boolean z2) {
        this.u = z2;
    }

    @TargetApi(20)
    public void start() {
        boolean z2;
        PowerManager powerManager = (PowerManager) getContext().getSystemService("power");
        boolean inKeyguardRestrictedInputMode = ((KeyguardManager) getContext().getSystemService("keyguard")).inKeyguardRestrictedInputMode();
        if (VERSION.SDK_INT < 20) {
            z2 = powerManager.isScreenOn();
        } else {
            z2 = powerManager.isInteractive();
        }
        boolean b2 = b();
        be beVar = (be) getTag();
        boolean z3 = beVar == null || ((Boolean) beVar.v.get("shouldAutoPlay")).booleanValue();
        if (b2 && !z3) {
            a(8, 0);
        }
        if (b2 && z2 && !this.c.isPlaying() && z3 && (this.u || !inKeyguardRestrictedInputMode)) {
            int intValue = (beVar == null || ((Boolean) beVar.v.get("didCompleteQ4")).booleanValue()) ? 0 : ((Integer) beVar.v.get(AbstractEvent.SEEK_POSITION)).intValue();
            d();
            a(intValue);
            this.c.start();
            this.c.a = 3;
            a(8, 8);
            if (beVar != null) {
                beVar.v.put("didCompleteQ4", Boolean.valueOf(false));
                if (beVar.a()) {
                    e();
                }
                if (((Boolean) beVar.v.get(EventType.DID_PAUSE)).booleanValue()) {
                    getPlaybackEventListener().a(3);
                    beVar.v.put(EventType.DID_PAUSE, Boolean.valueOf(false));
                } else {
                    getPlaybackEventListener().a(1);
                }
                d dVar = this.g;
                if (dVar != null && !dVar.hasMessages(1)) {
                    this.g.sendEmptyMessage(1);
                }
            }
            NativeVideoController nativeVideoController = this.v;
            if (nativeVideoController != null) {
                nativeVideoController.a();
            }
        }
        av avVar = this.c;
        if (avVar != null) {
            avVar.b = 3;
        }
    }

    public void pause() {
        if (b() && this.c.isPlaying()) {
            this.c.pause();
            this.c.a = 4;
            if (getTag() != null) {
                be beVar = (be) getTag();
                beVar.v.put(EventType.DID_PAUSE, Boolean.valueOf(true));
                beVar.v.put(AbstractEvent.SEEK_POSITION, Integer.valueOf(getCurrentPosition()));
            }
            getPlaybackEventListener().a(2);
        }
        av avVar = this.c;
        if (avVar != null) {
            avVar.b = 4;
        }
        this.i = false;
    }

    public int getDuration() {
        if (b()) {
            return this.c.getDuration();
        }
        return -1;
    }

    public int getCurrentPosition() {
        if (b()) {
            return this.c.getCurrentPosition();
        }
        return 0;
    }

    /* access modifiers changed from: 0000 */
    public final void a(int i2) {
        if (b()) {
            this.c.seekTo(i2);
        }
    }

    public boolean isPlaying() {
        return b() && this.c.isPlaying();
    }

    public int getBufferPercentage() {
        if (this.c != null) {
            return this.w;
        }
        return 0;
    }

    public boolean canPause() {
        return this.x;
    }

    public boolean canSeekBackward() {
        return this.y;
    }

    public boolean canSeekForward() {
        return this.z;
    }

    public int getAudioSessionId() {
        if (this.d == 0) {
            MediaPlayer mediaPlayer = new MediaPlayer();
            this.d = mediaPlayer.getAudioSessionId();
            mediaPlayer.release();
        }
        return this.d;
    }

    public void setVideoPath(String str) {
        setVideoURI(Uri.parse(str));
    }

    public void setVideoURI(Uri uri) {
        setVideoURI(uri, null);
    }

    public void setVideoURI(Uri uri, Map<String, String> map) {
        this.a = uri;
        this.b = map;
        g();
        requestLayout();
        invalidate();
    }

    /* access modifiers changed from: private */
    public void g() {
        av avVar;
        if (this.a != null && this.n != null) {
            if (this.c == null) {
                be beVar = (be) getTag();
                PlacementType placementType = PlacementType.PLACEMENT_TYPE_FULLSCREEN;
                if (beVar != null) {
                    placementType = (PlacementType) beVar.v.get("placementType");
                }
                if (PlacementType.PLACEMENT_TYPE_FULLSCREEN == placementType) {
                    avVar = new av();
                } else {
                    avVar = av.a();
                }
                this.c = avVar;
                int i2 = this.d;
                if (i2 != 0) {
                    this.c.setAudioSessionId(i2);
                } else {
                    this.d = this.c.getAudioSessionId();
                }
                try {
                    this.c.setDataSource(getContext().getApplicationContext(), this.a, this.b);
                } catch (IOException unused) {
                    av avVar2 = this.c;
                    avVar2.a = -1;
                    avVar2.b = -1;
                    return;
                }
            }
            try {
                be beVar2 = (be) getTag();
                this.c.setOnPreparedListener(this.k);
                this.c.setOnVideoSizeChangedListener(this.j);
                this.c.setOnCompletionListener(this.A);
                this.c.setOnErrorListener(this.D);
                this.c.setOnInfoListener(this.B);
                this.c.setOnBufferingUpdateListener(this.C);
                this.c.setSurface(this.n);
                if (VERSION.SDK_INT >= 26) {
                    this.c.setAudioAttributes(new Builder().setUsage(1).setContentType(2).setLegacyStreamType(3).build());
                } else {
                    this.c.setAudioStreamType(3);
                }
                this.c.prepareAsync();
                this.w = 0;
                this.c.a = 1;
                h();
                if (beVar2 != null) {
                    if (((Boolean) beVar2.v.get("shouldAutoPlay")).booleanValue()) {
                        this.c.b = 3;
                    }
                    if (((Boolean) beVar2.v.get("didCompleteQ4")).booleanValue()) {
                        a(8, 0);
                        return;
                    }
                }
                a(0, 0);
            } catch (Exception e2) {
                av avVar3 = this.c;
                avVar3.a = -1;
                avVar3.b = -1;
                this.D.onError(avVar3, 1, 0);
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public final void c() {
        if (this.c != null) {
            d dVar = this.g;
            if (dVar != null) {
                dVar.removeMessages(1);
            }
            if (getTag() != null) {
                ((be) getTag()).v.put(AbstractEvent.SEEK_POSITION, Integer.valueOf(getCurrentPosition()));
            }
            av avVar = this.c;
            avVar.a = 0;
            avVar.b = 0;
            avVar.reset();
            this.c.setOnPreparedListener(null);
            this.c.setOnVideoSizeChangedListener(null);
            this.c.setOnCompletionListener(null);
            this.c.setOnErrorListener(null);
            this.c.setOnInfoListener(null);
            this.c.setOnBufferingUpdateListener(null);
            if (getTag() != null) {
                if (PlacementType.PLACEMENT_TYPE_INLINE == ((be) getTag()).v.get("placementType")) {
                    this.c.b();
                }
            } else {
                this.c.b();
            }
            AudioManager audioManager = (AudioManager) getContext().getSystemService(MimeTypes.BASE_TYPE_AUDIO);
            if (audioManager != null) {
                audioManager.abandonAudioFocus(null);
            }
            this.c = null;
        }
    }

    public final void d() {
        av avVar = this.c;
        if (avVar != null) {
            this.o = 0;
            avVar.setVolume(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED);
            if (getTag() != null) {
                ((be) getTag()).v.put("currentMediaVolume", Integer.valueOf(0));
            }
        }
    }

    public final void e() {
        av avVar = this.c;
        if (avVar != null) {
            this.o = 1;
            avVar.setVolume(1.0f, 1.0f);
            if (getTag() != null) {
                ((be) getTag()).v.put("currentMediaVolume", Integer.valueOf(15));
            }
        }
    }

    public int getVolume() {
        if (b()) {
            return this.o;
        }
        return -1;
    }

    public int getVideoVolume() {
        if (isPlaying()) {
            return this.o;
        }
        return -1;
    }

    public int getLastVolume() {
        return this.p;
    }

    public void setLastVolume(int i2) {
        this.p = i2;
    }

    private void h() {
        if (this.c != null) {
            NativeVideoController nativeVideoController = this.v;
            if (nativeVideoController != null) {
                nativeVideoController.setMediaPlayer(this);
                this.v.setEnabled(b());
                this.v.a();
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public final void a(int i2, int i3) {
        if (this.c != null) {
            ProgressBar progressBar = ((NativeVideoWrapper) getParent()).getProgressBar();
            ImageView poster = ((NativeVideoWrapper) getParent()).getPoster();
            progressBar.setVisibility(i2);
            poster.setVisibility(i3);
        }
    }

    public void setMediaController(NativeVideoController nativeVideoController) {
        if (nativeVideoController != null) {
            this.v = nativeVideoController;
            h();
        }
    }

    public c getQuartileCompletedListener() {
        return this.r;
    }

    public void setQuartileCompletedListener(c cVar) {
        this.r = cVar;
    }

    public b getPlaybackEventListener() {
        return this.s;
    }

    public void setPlaybackEventListener(b bVar) {
        this.s = bVar;
    }

    public void setMediaErrorListener(a aVar) {
        this.t = aVar;
    }

    static /* synthetic */ void f(NativeVideoView nativeVideoView) {
        av avVar = nativeVideoView.c;
        if (avVar != null) {
            avVar.a = 5;
            avVar.b = 5;
        }
        NativeVideoController nativeVideoController = nativeVideoView.v;
        if (nativeVideoController != null) {
            nativeVideoController.b();
        }
        d dVar = nativeVideoView.g;
        if (dVar != null) {
            dVar.removeMessages(1);
        }
        if (nativeVideoView.getTag() != null) {
            be beVar = (be) nativeVideoView.getTag();
            if (!((Boolean) beVar.v.get("didCompleteQ4")).booleanValue()) {
                beVar.v.put("didCompleteQ4", Boolean.valueOf(true));
                if (nativeVideoView.getQuartileCompletedListener() != null) {
                    nativeVideoView.getQuartileCompletedListener().a(3);
                }
            }
            beVar.v.put("didSignalVideoCompleted", Boolean.valueOf(true));
            if (beVar != null) {
                beVar.v.put("didCompleteQ1", Boolean.valueOf(false));
                beVar.v.put("didCompleteQ2", Boolean.valueOf(false));
                beVar.v.put("didCompleteQ3", Boolean.valueOf(false));
                beVar.v.put(EventType.DID_PAUSE, Boolean.valueOf(false));
                beVar.v.put("didStartPlaying", Boolean.valueOf(false));
                beVar.v.put("didQ4Fire", Boolean.valueOf(false));
            }
            if (beVar.C) {
                nativeVideoView.start();
            } else if (((Boolean) beVar.v.get("isFullScreen")).booleanValue()) {
                nativeVideoView.a(8, 0);
            }
        }
    }

    static /* synthetic */ void h(NativeVideoView nativeVideoView) {
        try {
            if (nativeVideoView.a != null) {
                String uri = nativeVideoView.a.toString();
                com.inmobi.ads.cache.d.a();
                com.inmobi.commons.core.d.b a2 = com.inmobi.commons.core.d.b.a();
                List a3 = a2.a("asset", com.inmobi.ads.cache.d.a, "disk_uri=? ", new String[]{uri}, null, null, "created_ts DESC ", AppEventsConstants.EVENT_PARAM_VALUE_YES);
                a2.b();
                com.inmobi.ads.cache.a a4 = a3.isEmpty() ? null : com.inmobi.ads.cache.d.a((ContentValues) a3.get(0));
                C0044a aVar = new C0044a();
                if (a4 != null) {
                    com.inmobi.ads.cache.a a5 = aVar.a(a4.d, 0, 0).a();
                    com.inmobi.ads.cache.d.a();
                    com.inmobi.ads.cache.d.b(a5);
                }
            }
        } catch (Exception unused) {
        }
    }
}
