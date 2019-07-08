package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnInfoListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.net.Uri;
import android.os.Build.VERSION;
import android.view.Surface;
import android.view.TextureView.SurfaceTextureListener;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.logging.type.LogSeverity;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@zzark
@TargetApi(14)
public final class zzbcx extends zzbdi implements OnBufferingUpdateListener, OnCompletionListener, OnErrorListener, OnInfoListener, OnPreparedListener, OnVideoSizeChangedListener, SurfaceTextureListener {
    private static final Map<Integer, String> zzeqe = new HashMap();
    private final zzbea zzeqf;
    private final boolean zzeqg;
    private int zzeqh = 0;
    private int zzeqi = 0;
    private MediaPlayer zzeqj;
    private Uri zzeqk;
    private int zzeql;
    private int zzeqm;
    private int zzeqn;
    private int zzeqo;
    private int zzeqp;
    private zzbdx zzeqq;
    private boolean zzeqr;
    private int zzeqs;
    /* access modifiers changed from: private */
    public zzbdh zzeqt;

    public zzbcx(Context context, boolean z, boolean z2, zzbdy zzbdy, zzbea zzbea) {
        super(context);
        setSurfaceTextureListener(this);
        this.zzeqf = zzbea;
        this.zzeqr = z;
        this.zzeqg = z2;
        this.zzeqf.zzb(this);
    }

    public final String zzaaz() {
        String str = "MediaPlayer";
        String valueOf = String.valueOf(this.zzeqr ? " spherical" : "");
        return valueOf.length() != 0 ? str.concat(valueOf) : new String(str);
    }

    public final void zza(zzbdh zzbdh) {
        this.zzeqt = zzbdh;
    }

    public final void setVideoPath(String str) {
        Uri parse = Uri.parse(str);
        zzty zzd = zzty.zzd(parse);
        if (zzd != null) {
            parse = Uri.parse(zzd.url);
        }
        this.zzeqk = parse;
        this.zzeqs = 0;
        zzaba();
        requestLayout();
        invalidate();
    }

    public final void stop() {
        zzaxz.v("AdMediaPlayerView stop");
        MediaPlayer mediaPlayer = this.zzeqj;
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            this.zzeqj.release();
            this.zzeqj = null;
            zzcx(0);
            this.zzeqi = 0;
        }
        this.zzeqf.onStop();
    }

    public final void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
        StringBuilder sb = new StringBuilder(57);
        sb.append("AdMediaPlayerView size changed: ");
        sb.append(i);
        sb.append(" x ");
        sb.append(i2);
        zzaxz.v(sb.toString());
        this.zzeql = mediaPlayer.getVideoWidth();
        this.zzeqm = mediaPlayer.getVideoHeight();
        if (this.zzeql != 0 && this.zzeqm != 0) {
            requestLayout();
        }
    }

    public final void onPrepared(MediaPlayer mediaPlayer) {
        zzaxz.v("AdMediaPlayerView prepared");
        zzcx(2);
        this.zzeqf.zzcg();
        zzayh.zzelc.post(new zzbcz(this));
        this.zzeql = mediaPlayer.getVideoWidth();
        this.zzeqm = mediaPlayer.getVideoHeight();
        int i = this.zzeqs;
        if (i != 0) {
            seekTo(i);
        }
        zzabb();
        int i2 = this.zzeql;
        int i3 = this.zzeqm;
        StringBuilder sb = new StringBuilder(62);
        sb.append("AdMediaPlayerView stream dimensions: ");
        sb.append(i2);
        sb.append(" x ");
        sb.append(i3);
        zzaxz.zzen(sb.toString());
        if (this.zzeqi == 3) {
            play();
        }
        zzabd();
    }

    public final void onCompletion(MediaPlayer mediaPlayer) {
        zzaxz.v("AdMediaPlayerView completion");
        zzcx(5);
        this.zzeqi = 5;
        zzayh.zzelc.post(new zzbda(this));
    }

    public final boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
        String str = (String) zzeqe.get(Integer.valueOf(i));
        String str2 = (String) zzeqe.get(Integer.valueOf(i2));
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 37 + String.valueOf(str2).length());
        sb.append("AdMediaPlayerView MediaPlayer info: ");
        sb.append(str);
        sb.append(":");
        sb.append(str2);
        zzaxz.v(sb.toString());
        return true;
    }

    public final boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        String str = (String) zzeqe.get(Integer.valueOf(i));
        String str2 = (String) zzeqe.get(Integer.valueOf(i2));
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 38 + String.valueOf(str2).length());
        sb.append("AdMediaPlayerView MediaPlayer error: ");
        sb.append(str);
        sb.append(":");
        sb.append(str2);
        zzaxz.zzeo(sb.toString());
        zzcx(-1);
        this.zzeqi = -1;
        zzayh.zzelc.post(new zzbdb(this, str, str2));
        return true;
    }

    public final void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
        this.zzeqn = i;
    }

    public final void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        zzaxz.v("AdMediaPlayerView surface created");
        zzaba();
        zzayh.zzelc.post(new zzbdc(this));
    }

    public final void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        zzaxz.v("AdMediaPlayerView surface changed");
        boolean z = true;
        boolean z2 = this.zzeqi == 3;
        if (!(this.zzeql == i && this.zzeqm == i2)) {
            z = false;
        }
        if (this.zzeqj != null && z2 && z) {
            int i3 = this.zzeqs;
            if (i3 != 0) {
                seekTo(i3);
            }
            play();
        }
        zzbdx zzbdx = this.zzeqq;
        if (zzbdx != null) {
            zzbdx.zzo(i, i2);
        }
        zzayh.zzelc.post(new zzbdd(this, i, i2));
    }

    public final boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        zzaxz.v("AdMediaPlayerView surface destroyed");
        MediaPlayer mediaPlayer = this.zzeqj;
        if (mediaPlayer != null && this.zzeqs == 0) {
            this.zzeqs = mediaPlayer.getCurrentPosition();
        }
        zzbdx zzbdx = this.zzeqq;
        if (zzbdx != null) {
            zzbdx.zzabq();
        }
        zzayh.zzelc.post(new zzbde(this));
        zzar(true);
        return true;
    }

    public final void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        this.zzeqf.zzc(this);
        this.zzera.zza(surfaceTexture, this.zzeqt);
    }

    /* access modifiers changed from: protected */
    public final void onWindowVisibilityChanged(int i) {
        StringBuilder sb = new StringBuilder(58);
        sb.append("AdMediaPlayerView window visibility changed to ");
        sb.append(i);
        zzaxz.v(sb.toString());
        zzayh.zzelc.post(new zzbcy(this, i));
        super.onWindowVisibilityChanged(i);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00a4, code lost:
        if (r7 != r1) goto L_0x00a6;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onMeasure(int r6, int r7) {
        /*
            r5 = this;
            int r0 = r5.zzeql
            int r0 = getDefaultSize(r0, r6)
            int r1 = r5.zzeqm
            int r1 = getDefaultSize(r1, r7)
            int r2 = r5.zzeql
            if (r2 <= 0) goto L_0x0089
            int r2 = r5.zzeqm
            if (r2 <= 0) goto L_0x0089
            com.google.android.gms.internal.ads.zzbdx r2 = r5.zzeqq
            if (r2 != 0) goto L_0x0089
            int r0 = android.view.View.MeasureSpec.getMode(r6)
            int r6 = android.view.View.MeasureSpec.getSize(r6)
            int r1 = android.view.View.MeasureSpec.getMode(r7)
            int r7 = android.view.View.MeasureSpec.getSize(r7)
            r2 = 1073741824(0x40000000, float:2.0)
            if (r0 != r2) goto L_0x0049
            if (r1 != r2) goto L_0x0049
            int r0 = r5.zzeql
            int r1 = r0 * r7
            int r2 = r5.zzeqm
            int r3 = r6 * r2
            if (r1 >= r3) goto L_0x003e
            int r0 = r0 * r7
            int r0 = r0 / r2
            r1 = r7
            r6 = r0
            goto L_0x008a
        L_0x003e:
            int r1 = r0 * r7
            int r3 = r6 * r2
            if (r1 <= r3) goto L_0x006a
            int r2 = r2 * r6
            int r1 = r2 / r0
            goto L_0x008a
        L_0x0049:
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r0 != r2) goto L_0x005b
            int r0 = r5.zzeqm
            int r0 = r0 * r6
            int r2 = r5.zzeql
            int r0 = r0 / r2
            if (r1 != r3) goto L_0x0059
            if (r0 <= r7) goto L_0x0059
            goto L_0x0068
        L_0x0059:
            r1 = r0
            goto L_0x008a
        L_0x005b:
            if (r1 != r2) goto L_0x006c
            int r1 = r5.zzeql
            int r1 = r1 * r7
            int r2 = r5.zzeqm
            int r1 = r1 / r2
            if (r0 != r3) goto L_0x0069
            if (r1 <= r6) goto L_0x0069
        L_0x0068:
            goto L_0x006a
        L_0x0069:
            r6 = r1
        L_0x006a:
            r1 = r7
            goto L_0x008a
        L_0x006c:
            int r2 = r5.zzeql
            int r4 = r5.zzeqm
            if (r1 != r3) goto L_0x0079
            if (r4 <= r7) goto L_0x0079
            int r2 = r2 * r7
            int r2 = r2 / r4
            r1 = r7
            goto L_0x007a
        L_0x0079:
            r1 = r4
        L_0x007a:
            if (r0 != r3) goto L_0x0087
            if (r2 <= r6) goto L_0x0087
            int r7 = r5.zzeqm
            int r7 = r7 * r6
            int r0 = r5.zzeql
            int r1 = r7 / r0
            goto L_0x008a
        L_0x0087:
            r6 = r2
            goto L_0x008a
        L_0x0089:
            r6 = r0
        L_0x008a:
            r5.setMeasuredDimension(r6, r1)
            com.google.android.gms.internal.ads.zzbdx r7 = r5.zzeqq
            if (r7 == 0) goto L_0x0094
            r7.zzo(r6, r1)
        L_0x0094:
            int r7 = android.os.Build.VERSION.SDK_INT
            r0 = 16
            if (r7 != r0) goto L_0x00ad
            int r7 = r5.zzeqo
            if (r7 <= 0) goto L_0x00a0
            if (r7 != r6) goto L_0x00a6
        L_0x00a0:
            int r7 = r5.zzeqp
            if (r7 <= 0) goto L_0x00a9
            if (r7 == r1) goto L_0x00a9
        L_0x00a6:
            r5.zzabb()
        L_0x00a9:
            r5.zzeqo = r6
            r5.zzeqp = r1
        L_0x00ad:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbcx.onMeasure(int, int):void");
    }

    public final String toString() {
        String name = getClass().getName();
        String hexString = Integer.toHexString(hashCode());
        StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 1 + String.valueOf(hexString).length());
        sb.append(name);
        sb.append("@");
        sb.append(hexString);
        return sb.toString();
    }

    private final void zzaba() {
        zzaxz.v("AdMediaPlayerView init MediaPlayer");
        SurfaceTexture surfaceTexture = getSurfaceTexture();
        if (this.zzeqk != null && surfaceTexture != null) {
            zzar(false);
            try {
                zzbv.zzlx();
                this.zzeqj = new MediaPlayer();
                this.zzeqj.setOnBufferingUpdateListener(this);
                this.zzeqj.setOnCompletionListener(this);
                this.zzeqj.setOnErrorListener(this);
                this.zzeqj.setOnInfoListener(this);
                this.zzeqj.setOnPreparedListener(this);
                this.zzeqj.setOnVideoSizeChangedListener(this);
                this.zzeqn = 0;
                if (this.zzeqr) {
                    this.zzeqq = new zzbdx(getContext());
                    this.zzeqq.zza(surfaceTexture, getWidth(), getHeight());
                    this.zzeqq.start();
                    SurfaceTexture zzabr = this.zzeqq.zzabr();
                    if (zzabr != null) {
                        surfaceTexture = zzabr;
                    } else {
                        this.zzeqq.zzabq();
                        this.zzeqq = null;
                    }
                }
                this.zzeqj.setDataSource(getContext(), this.zzeqk);
                zzbv.zzly();
                this.zzeqj.setSurface(new Surface(surfaceTexture));
                this.zzeqj.setAudioStreamType(3);
                this.zzeqj.setScreenOnWhilePlaying(true);
                this.zzeqj.prepareAsync();
                zzcx(1);
            } catch (IOException | IllegalArgumentException | IllegalStateException e) {
                String valueOf = String.valueOf(this.zzeqk);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 36);
                sb.append("Failed to initialize MediaPlayer at ");
                sb.append(valueOf);
                zzaxz.zzc(sb.toString(), e);
                onError(this.zzeqj, 1, 0);
            }
        }
    }

    private final void zzabb() {
        if (this.zzeqg && zzabc() && this.zzeqj.getCurrentPosition() > 0 && this.zzeqi != 3) {
            zzaxz.v("AdMediaPlayerView nudging MediaPlayer");
            zzd(BitmapDescriptorFactory.HUE_RED);
            this.zzeqj.start();
            int currentPosition = this.zzeqj.getCurrentPosition();
            long currentTimeMillis = zzbv.zzlm().currentTimeMillis();
            while (zzabc() && this.zzeqj.getCurrentPosition() == currentPosition) {
                if (zzbv.zzlm().currentTimeMillis() - currentTimeMillis > 250) {
                    break;
                }
            }
            this.zzeqj.pause();
            zzabd();
        }
    }

    private final void zzar(boolean z) {
        zzaxz.v("AdMediaPlayerView release");
        zzbdx zzbdx = this.zzeqq;
        if (zzbdx != null) {
            zzbdx.zzabq();
            this.zzeqq = null;
        }
        MediaPlayer mediaPlayer = this.zzeqj;
        if (mediaPlayer != null) {
            mediaPlayer.reset();
            this.zzeqj.release();
            this.zzeqj = null;
            zzcx(0);
            if (z) {
                this.zzeqi = 0;
                this.zzeqi = 0;
            }
        }
    }

    public final void play() {
        zzaxz.v("AdMediaPlayerView play");
        if (zzabc()) {
            this.zzeqj.start();
            zzcx(3);
            this.zzera.zzabf();
            zzayh.zzelc.post(new zzbdf(this));
        }
        this.zzeqi = 3;
    }

    public final void pause() {
        zzaxz.v("AdMediaPlayerView pause");
        if (zzabc() && this.zzeqj.isPlaying()) {
            this.zzeqj.pause();
            zzcx(4);
            zzayh.zzelc.post(new zzbdg(this));
        }
        this.zzeqi = 4;
    }

    public final int getDuration() {
        if (zzabc()) {
            return this.zzeqj.getDuration();
        }
        return -1;
    }

    public final int getCurrentPosition() {
        if (zzabc()) {
            return this.zzeqj.getCurrentPosition();
        }
        return 0;
    }

    public final void seekTo(int i) {
        StringBuilder sb = new StringBuilder(34);
        sb.append("AdMediaPlayerView seek ");
        sb.append(i);
        zzaxz.v(sb.toString());
        if (zzabc()) {
            this.zzeqj.seekTo(i);
            this.zzeqs = 0;
            return;
        }
        this.zzeqs = i;
    }

    private final boolean zzabc() {
        if (this.zzeqj != null) {
            int i = this.zzeqh;
            if (!(i == -1 || i == 0 || i == 1)) {
                return true;
            }
        }
        return false;
    }

    public final void zza(float f, float f2) {
        zzbdx zzbdx = this.zzeqq;
        if (zzbdx != null) {
            zzbdx.zzb(f, f2);
        }
    }

    public final int getVideoWidth() {
        MediaPlayer mediaPlayer = this.zzeqj;
        if (mediaPlayer != null) {
            return mediaPlayer.getVideoWidth();
        }
        return 0;
    }

    public final int getVideoHeight() {
        MediaPlayer mediaPlayer = this.zzeqj;
        if (mediaPlayer != null) {
            return mediaPlayer.getVideoHeight();
        }
        return 0;
    }

    public final void zzabd() {
        zzd(this.zzerb.getVolume());
    }

    private final void zzd(float f) {
        MediaPlayer mediaPlayer = this.zzeqj;
        if (mediaPlayer != null) {
            try {
                mediaPlayer.setVolume(f, f);
            } catch (IllegalStateException unused) {
            }
        } else {
            zzaxz.zzeo("AdMediaPlayerView setMediaPlayerVolume() called before onPrepared().");
        }
    }

    private final void zzcx(int i) {
        if (i == 3) {
            this.zzeqf.zzacd();
            this.zzerb.zzacd();
        } else if (this.zzeqh == 3) {
            this.zzeqf.zzace();
            this.zzerb.zzace();
        }
        this.zzeqh = i;
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void zzcy(int i) {
        zzbdh zzbdh = this.zzeqt;
        if (zzbdh != null) {
            zzbdh.onWindowVisibilityChanged(i);
        }
    }

    static {
        if (VERSION.SDK_INT >= 17) {
            zzeqe.put(Integer.valueOf(-1004), "MEDIA_ERROR_IO");
            zzeqe.put(Integer.valueOf(-1007), "MEDIA_ERROR_MALFORMED");
            zzeqe.put(Integer.valueOf(-1010), "MEDIA_ERROR_UNSUPPORTED");
            zzeqe.put(Integer.valueOf(-110), "MEDIA_ERROR_TIMED_OUT");
            zzeqe.put(Integer.valueOf(3), "MEDIA_INFO_VIDEO_RENDERING_START");
        }
        zzeqe.put(Integer.valueOf(100), "MEDIA_ERROR_SERVER_DIED");
        zzeqe.put(Integer.valueOf(1), "MEDIA_ERROR_UNKNOWN");
        zzeqe.put(Integer.valueOf(1), "MEDIA_INFO_UNKNOWN");
        zzeqe.put(Integer.valueOf(LogSeverity.ALERT_VALUE), "MEDIA_INFO_VIDEO_TRACK_LAGGING");
        zzeqe.put(Integer.valueOf(701), "MEDIA_INFO_BUFFERING_START");
        zzeqe.put(Integer.valueOf(702), "MEDIA_INFO_BUFFERING_END");
        zzeqe.put(Integer.valueOf(LogSeverity.EMERGENCY_VALUE), "MEDIA_INFO_BAD_INTERLEAVING");
        zzeqe.put(Integer.valueOf(801), "MEDIA_INFO_NOT_SEEKABLE");
        zzeqe.put(Integer.valueOf(802), "MEDIA_INFO_METADATA_UPDATE");
        if (VERSION.SDK_INT >= 19) {
            zzeqe.put(Integer.valueOf(901), "MEDIA_INFO_UNSUPPORTED_SUBTITLE");
            zzeqe.put(Integer.valueOf(902), "MEDIA_INFO_SUBTITLE_TIMED_OUT");
        }
    }
}
