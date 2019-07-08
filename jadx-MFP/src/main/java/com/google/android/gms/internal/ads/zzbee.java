package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.net.Uri;
import android.view.Surface;
import android.view.TextureView.SurfaceTextureListener;
import com.google.android.gms.ads.internal.zzbv;
import java.nio.ByteBuffer;

@zzark
@TargetApi(16)
public final class zzbee extends zzbdi implements SurfaceTextureListener, zzbez {
    private Surface zzbjb;
    private final zzbdz zzerw;
    private final zzbea zzeum;
    private final boolean zzeun;
    private final zzbdy zzeuo;
    private zzbdh zzeup;
    private zzbes zzeuq;
    private String zzeur;
    private boolean zzeus;
    private int zzeut = 1;
    private zzbdx zzeuu;
    private final boolean zzeuv;
    private boolean zzeuw;
    private boolean zzeux;
    private int zzeuy;
    private int zzeuz;
    private int zzeva;
    private int zzevb;
    private float zzevc;

    public zzbee(Context context, zzbea zzbea, zzbdz zzbdz, boolean z, boolean z2, zzbdy zzbdy) {
        super(context);
        this.zzeun = z2;
        this.zzerw = zzbdz;
        this.zzeum = zzbea;
        this.zzeuv = z;
        this.zzeuo = zzbdy;
        setSurfaceTextureListener(this);
        this.zzeum.zzb(this);
    }

    private final zzbes zzach() {
        return new zzbes(this.zzerw.getContext(), this.zzeuo);
    }

    private final String zzaci() {
        return zzbv.zzlf().zzo(this.zzerw.getContext(), this.zzerw.zzabz().zzdp);
    }

    private final boolean zzacj() {
        return this.zzeuq != null && !this.zzeus;
    }

    private final boolean zzack() {
        return zzacj() && this.zzeut != 1;
    }

    private final void zzacl() {
        if (this.zzeuq == null) {
            String str = this.zzeur;
            if (str != null && this.zzbjb != null) {
                if (str.startsWith("cache:")) {
                    zzbfk zzet = this.zzerw.zzet(this.zzeur);
                    if (zzet instanceof zzbfw) {
                        this.zzeuq = ((zzbfw) zzet).zzadd();
                    } else if (zzet instanceof zzbfv) {
                        zzbfv zzbfv = (zzbfv) zzet;
                        String zzaci = zzaci();
                        ByteBuffer byteBuffer = zzbfv.getByteBuffer();
                        boolean zzadc = zzbfv.zzadc();
                        String url = zzbfv.getUrl();
                        if (url == null) {
                            zzaxz.zzeo("Stream cache URL is null.");
                            return;
                        } else {
                            this.zzeuq = zzach();
                            this.zzeuq.zza(Uri.parse(url), zzaci, byteBuffer, zzadc);
                        }
                    } else {
                        String str2 = "Stream cache miss: ";
                        String valueOf = String.valueOf(this.zzeur);
                        zzaxz.zzeo(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
                        return;
                    }
                } else {
                    this.zzeuq = zzach();
                    this.zzeuq.zza(Uri.parse(this.zzeur), zzaci());
                }
                this.zzeuq.zza((zzbez) this);
                zza(this.zzbjb, false);
                this.zzeut = this.zzeuq.zzacw().getPlaybackState();
                if (this.zzeut == 3) {
                    zzacm();
                }
            }
        }
    }

    private final void zza(Surface surface, boolean z) {
        zzbes zzbes = this.zzeuq;
        if (zzbes != null) {
            zzbes.zza(surface, z);
        } else {
            zzaxz.zzeo("Trying to set surface before player is initalized.");
        }
    }

    private final void zza(float f, boolean z) {
        zzbes zzbes = this.zzeuq;
        if (zzbes != null) {
            zzbes.zzb(f, z);
        } else {
            zzaxz.zzeo("Trying to set volume before player is initalized.");
        }
    }

    public final void zzabd() {
        zza(this.zzerb.getVolume(), false);
    }

    private final void zzacm() {
        if (!this.zzeuw) {
            this.zzeuw = true;
            zzayh.zzelc.post(new zzbef(this));
            zzabd();
            this.zzeum.zzcg();
            if (this.zzeux) {
                play();
            }
        }
    }

    public final String zzaaz() {
        String valueOf = String.valueOf("ExoPlayer/3");
        String valueOf2 = String.valueOf(this.zzeuv ? " spherical" : "");
        return valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
    }

    public final void zza(zzbdh zzbdh) {
        this.zzeup = zzbdh;
    }

    public final void setVideoPath(String str) {
        if (str != null) {
            this.zzeur = str;
            zzacl();
        }
    }

    public final void play() {
        if (zzack()) {
            if (this.zzeuo.zzetk) {
                zzaco();
            }
            this.zzeuq.zzacw().zzc(true);
            this.zzeum.zzacd();
            this.zzerb.zzacd();
            this.zzera.zzabf();
            zzayh.zzelc.post(new zzbei(this));
            return;
        }
        this.zzeux = true;
    }

    public final void stop() {
        if (zzacj()) {
            this.zzeuq.zzacw().stop();
            if (this.zzeuq != null) {
                zza((Surface) null, true);
                zzbes zzbes = this.zzeuq;
                if (zzbes != null) {
                    zzbes.zza((zzbez) null);
                    this.zzeuq.release();
                    this.zzeuq = null;
                }
                this.zzeut = 1;
                this.zzeus = false;
                this.zzeuw = false;
                this.zzeux = false;
            }
        }
        this.zzeum.zzace();
        this.zzerb.zzace();
        this.zzeum.onStop();
    }

    public final void pause() {
        if (zzack()) {
            if (this.zzeuo.zzetk) {
                zzacp();
            }
            this.zzeuq.zzacw().zzc(false);
            this.zzeum.zzace();
            this.zzerb.zzace();
            zzayh.zzelc.post(new zzbej(this));
        }
    }

    public final void seekTo(int i) {
        if (zzack()) {
            this.zzeuq.zzacw().seekTo((long) i);
        }
    }

    public final void zzcz(int i) {
        zzbes zzbes = this.zzeuq;
        if (zzbes != null) {
            zzbes.zzacz().zzdf(i);
        }
    }

    public final void zzda(int i) {
        zzbes zzbes = this.zzeuq;
        if (zzbes != null) {
            zzbes.zzacz().zzdg(i);
        }
    }

    public final void zzdb(int i) {
        zzbes zzbes = this.zzeuq;
        if (zzbes != null) {
            zzbes.zzacz().zzdb(i);
        }
    }

    public final void zzdc(int i) {
        zzbes zzbes = this.zzeuq;
        if (zzbes != null) {
            zzbes.zzacz().zzdc(i);
        }
    }

    public final void zza(float f, float f2) {
        zzbdx zzbdx = this.zzeuu;
        if (zzbdx != null) {
            zzbdx.zzb(f, f2);
        }
    }

    public final int getCurrentPosition() {
        if (zzack()) {
            return (int) this.zzeuq.zzacw().zzbr();
        }
        return 0;
    }

    public final int getDuration() {
        if (zzack()) {
            return (int) this.zzeuq.zzacw().getDuration();
        }
        return 0;
    }

    public final int getVideoWidth() {
        return this.zzeuy;
    }

    public final int getVideoHeight() {
        return this.zzeuz;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0044, code lost:
        if (r0 != r12) goto L_0x0046;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onMeasure(int r11, int r12) {
        /*
            r10 = this;
            super.onMeasure(r11, r12)
            int r11 = r10.getMeasuredWidth()
            int r12 = r10.getMeasuredHeight()
            float r0 = r10.zzevc
            r1 = 0
            int r2 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r2 == 0) goto L_0x002a
            com.google.android.gms.internal.ads.zzbdx r2 = r10.zzeuu
            if (r2 != 0) goto L_0x002a
            float r2 = (float) r11
            float r3 = (float) r12
            float r3 = r2 / r3
            int r4 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r4 <= 0) goto L_0x0020
            float r2 = r2 / r0
            int r12 = (int) r2
        L_0x0020:
            float r0 = r10.zzevc
            int r2 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r2 >= 0) goto L_0x002a
            float r11 = (float) r12
            float r11 = r11 * r0
            int r11 = (int) r11
        L_0x002a:
            r10.setMeasuredDimension(r11, r12)
            com.google.android.gms.internal.ads.zzbdx r0 = r10.zzeuu
            if (r0 == 0) goto L_0x0034
            r0.zzo(r11, r12)
        L_0x0034:
            int r0 = android.os.Build.VERSION.SDK_INT
            r2 = 16
            if (r0 != r2) goto L_0x00a2
            int r0 = r10.zzeva
            if (r0 <= 0) goto L_0x0040
            if (r0 != r11) goto L_0x0046
        L_0x0040:
            int r0 = r10.zzevb
            if (r0 <= 0) goto L_0x009e
            if (r0 == r12) goto L_0x009e
        L_0x0046:
            boolean r0 = r10.zzeun
            if (r0 == 0) goto L_0x009e
            boolean r0 = r10.zzacj()
            if (r0 == 0) goto L_0x009e
            com.google.android.gms.internal.ads.zzbes r0 = r10.zzeuq
            com.google.android.gms.internal.ads.zzfg r0 = r0.zzacw()
            long r2 = r0.zzbr()
            r4 = 0
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 <= 0) goto L_0x009e
            boolean r2 = r0.zzbp()
            if (r2 == 0) goto L_0x0067
            goto L_0x009e
        L_0x0067:
            r2 = 1
            r10.zza(r1, r2)
            r0.zzc(r2)
            long r1 = r0.zzbr()
            com.google.android.gms.common.util.Clock r3 = com.google.android.gms.ads.internal.zzbv.zzlm()
            long r3 = r3.currentTimeMillis()
        L_0x007a:
            boolean r5 = r10.zzacj()
            if (r5 == 0) goto L_0x0097
            long r5 = r0.zzbr()
            int r7 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1))
            if (r7 != 0) goto L_0x0097
            com.google.android.gms.common.util.Clock r5 = com.google.android.gms.ads.internal.zzbv.zzlm()
            long r5 = r5.currentTimeMillis()
            long r5 = r5 - r3
            r7 = 250(0xfa, double:1.235E-321)
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r9 <= 0) goto L_0x007a
        L_0x0097:
            r1 = 0
            r0.zzc(r1)
            r10.zzabd()
        L_0x009e:
            r10.zzeva = r11
            r10.zzevb = r12
        L_0x00a2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbee.onMeasure(int, int):void");
    }

    public final void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        if (this.zzeuv) {
            this.zzeuu = new zzbdx(getContext());
            this.zzeuu.zza(surfaceTexture, i, i2);
            this.zzeuu.start();
            SurfaceTexture zzabr = this.zzeuu.zzabr();
            if (zzabr != null) {
                surfaceTexture = zzabr;
            } else {
                this.zzeuu.zzabq();
                this.zzeuu = null;
            }
        }
        this.zzbjb = new Surface(surfaceTexture);
        if (this.zzeuq == null) {
            zzacl();
        } else {
            zza(this.zzbjb, true);
            if (!this.zzeuo.zzetk) {
                zzaco();
            }
        }
        zzacn();
        zzayh.zzelc.post(new zzbek(this));
    }

    public final void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        zzbdx zzbdx = this.zzeuu;
        if (zzbdx != null) {
            zzbdx.zzo(i, i2);
        }
        zzayh.zzelc.post(new zzbel(this, i, i2));
    }

    public final void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        this.zzeum.zzc(this);
        this.zzera.zza(surfaceTexture, this.zzeup);
    }

    public final boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        pause();
        zzbdx zzbdx = this.zzeuu;
        if (zzbdx != null) {
            zzbdx.zzabq();
            this.zzeuu = null;
        }
        if (this.zzeuq != null) {
            zzacp();
            Surface surface = this.zzbjb;
            if (surface != null) {
                surface.release();
            }
            this.zzbjb = null;
            zza((Surface) null, true);
        }
        zzayh.zzelc.post(new zzbem(this));
        return true;
    }

    /* access modifiers changed from: protected */
    public final void onWindowVisibilityChanged(int i) {
        StringBuilder sb = new StringBuilder(57);
        sb.append("AdExoPlayerView3 window visibility changed to ");
        sb.append(i);
        zzaxz.v(sb.toString());
        zzayh.zzelc.post(new zzben(this, i));
        super.onWindowVisibilityChanged(i);
    }

    public final void zzb(boolean z, long j) {
        if (this.zzerw != null) {
            zzbcg.zzepo.execute(new zzbeo(this, z, j));
        }
    }

    public final void zzdd(int i) {
        if (this.zzeut != i) {
            this.zzeut = i;
            switch (i) {
                case 3:
                    zzacm();
                    return;
                case 4:
                    if (this.zzeuo.zzetk) {
                        zzacp();
                    }
                    this.zzeum.zzace();
                    this.zzerb.zzace();
                    zzayh.zzelc.post(new zzbeg(this));
                    break;
            }
        }
    }

    public final void zzp(int i, int i2) {
        this.zzeuy = i;
        this.zzeuz = i2;
        zzacn();
    }

    public final void zza(String str, Exception exc) {
        String canonicalName = exc.getClass().getCanonicalName();
        String message = exc.getMessage();
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 2 + String.valueOf(canonicalName).length() + String.valueOf(message).length());
        sb.append(str);
        sb.append("/");
        sb.append(canonicalName);
        sb.append(":");
        sb.append(message);
        String sb2 = sb.toString();
        String str2 = "ExoPlayerAdapter error: ";
        String valueOf = String.valueOf(sb2);
        zzaxz.zzeo(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        this.zzeus = true;
        if (this.zzeuo.zzetk) {
            zzacp();
        }
        zzayh.zzelc.post(new zzbeh(this, sb2));
    }

    private final void zzacn() {
        int i = this.zzeuz;
        float f = i > 0 ? ((float) this.zzeuy) / ((float) i) : 1.0f;
        if (this.zzevc != f) {
            this.zzevc = f;
            requestLayout();
        }
    }

    private final void zzaco() {
        zzbes zzbes = this.zzeuq;
        if (zzbes != null) {
            zzbes.zzau(true);
        }
    }

    private final void zzacp() {
        zzbes zzbes = this.zzeuq;
        if (zzbes != null) {
            zzbes.zzau(false);
        }
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void zzc(boolean z, long j) {
        this.zzerw.zza(z, j);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void zzde(int i) {
        zzbdh zzbdh = this.zzeup;
        if (zzbdh != null) {
            zzbdh.onWindowVisibilityChanged(i);
        }
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void zzacq() {
        zzbdh zzbdh = this.zzeup;
        if (zzbdh != null) {
            zzbdh.zzabh();
        }
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void zzq(int i, int i2) {
        zzbdh zzbdh = this.zzeup;
        if (zzbdh != null) {
            zzbdh.zzm(i, i2);
        }
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void zzacr() {
        zzbdh zzbdh = this.zzeup;
        if (zzbdh != null) {
            zzbdh.zzabe();
        }
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void zzacs() {
        zzbdh zzbdh = this.zzeup;
        if (zzbdh != null) {
            zzbdh.onPaused();
        }
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void zzact() {
        zzbdh zzbdh = this.zzeup;
        if (zzbdh != null) {
            zzbdh.zzabf();
        }
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void zzeu(String str) {
        zzbdh zzbdh = this.zzeup;
        if (zzbdh != null) {
            zzbdh.zzi("ExoPlayerAdapter error", str);
        }
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void zzacu() {
        zzbdh zzbdh = this.zzeup;
        if (zzbdh != null) {
            zzbdh.zzabg();
        }
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void zzacv() {
        zzbdh zzbdh = this.zzeup;
        if (zzbdh != null) {
            zzbdh.zzcg();
        }
    }
}
