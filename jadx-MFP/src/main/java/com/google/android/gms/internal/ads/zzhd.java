package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Handler;
import com.google.android.exoplayer2.util.MimeTypes;
import java.nio.ByteBuffer;

@TargetApi(16)
public final class zzhd extends zzjy implements zzps {
    /* access modifiers changed from: private */
    public final zzgl zzaeu;
    private final zzgs zzaev;
    private boolean zzaew;
    private boolean zzaex;
    private MediaFormat zzaey;
    private long zzaez;
    /* access modifiers changed from: private */
    public boolean zzafa;
    private int zzzt;
    private int zzzv;

    public zzhd(zzka zzka) {
        this(zzka, null, true);
    }

    protected static void zza(int i, long j, long j2) {
    }

    protected static void zzdi() {
    }

    protected static void zzl(int i) {
    }

    public final zzps zzbf() {
        return this;
    }

    private zzhd(zzka zzka, zzhu<Object> zzhu, boolean z) {
        this(zzka, null, true, null, null);
    }

    private zzhd(zzka zzka, zzhu<Object> zzhu, boolean z, Handler handler, zzgk zzgk) {
        this(zzka, null, true, null, null, null, new zzgi[0]);
    }

    private zzhd(zzka zzka, zzhu<Object> zzhu, boolean z, Handler handler, zzgk zzgk, zzgh zzgh, zzgi... zzgiArr) {
        super(1, zzka, zzhu, z);
        this.zzaev = new zzgs(null, zzgiArr, new zzhf(this));
        this.zzaeu = new zzgl(null, null);
    }

    /* access modifiers changed from: protected */
    public final int zza(zzka zzka, zzfs zzfs) throws zzke {
        String str = zzfs.zzzj;
        if (!zzpt.zzab(str)) {
            return 0;
        }
        int i = zzqe.SDK_INT >= 21 ? 16 : 0;
        int i2 = 3;
        if (zzs(str) && zzka.zzeq() != null) {
            return i | 4 | 3;
        }
        zzjx zzb = zzka.zzb(str, false);
        boolean z = true;
        if (zzb == null) {
            return 1;
        }
        if (zzqe.SDK_INT >= 21 && ((zzfs.zzzu != -1 && !zzb.zzam(zzfs.zzzu)) || (zzfs.zzzt != -1 && !zzb.zzan(zzfs.zzzt)))) {
            z = false;
        }
        if (!z) {
            i2 = 2;
        }
        return i | 4 | i2;
    }

    /* access modifiers changed from: protected */
    public final zzjx zza(zzka zzka, zzfs zzfs, boolean z) throws zzke {
        if (zzs(zzfs.zzzj)) {
            zzjx zzeq = zzka.zzeq();
            if (zzeq != null) {
                this.zzaew = true;
                return zzeq;
            }
        }
        this.zzaew = false;
        return super.zza(zzka, zzfs, z);
    }

    private final boolean zzs(String str) {
        return this.zzaev.zzq(str);
    }

    /* access modifiers changed from: protected */
    public final void zza(zzjx zzjx, MediaCodec mediaCodec, zzfs zzfs, MediaCrypto mediaCrypto) {
        this.zzaex = zzqe.SDK_INT < 24 && "OMX.SEC.aac.dec".equals(zzjx.name) && "samsung".equals(zzqe.MANUFACTURER) && (zzqe.DEVICE.startsWith("zeroflte") || zzqe.DEVICE.startsWith("herolte") || zzqe.DEVICE.startsWith("heroqlte"));
        if (this.zzaew) {
            this.zzaey = zzfs.zzcf();
            this.zzaey.setString("mime", MimeTypes.AUDIO_RAW);
            mediaCodec.configure(this.zzaey, null, null, 0);
            this.zzaey.setString("mime", zzfs.zzzj);
            return;
        }
        mediaCodec.configure(zzfs.zzcf(), null, null, 0);
        this.zzaey = null;
    }

    /* access modifiers changed from: protected */
    public final void zzc(String str, long j, long j2) {
        this.zzaeu.zzb(str, j, j2);
    }

    /* access modifiers changed from: protected */
    public final void zze(zzfs zzfs) throws zzff {
        super.zze(zzfs);
        this.zzaeu.zzd(zzfs);
        this.zzzv = MimeTypes.AUDIO_RAW.equals(zzfs.zzzj) ? zzfs.zzzv : 2;
        this.zzzt = zzfs.zzzt;
    }

    /* access modifiers changed from: protected */
    public final void onOutputFormatChanged(MediaCodec mediaCodec, MediaFormat mediaFormat) throws zzff {
        int[] iArr;
        boolean z = this.zzaey != null;
        String string = z ? this.zzaey.getString("mime") : MimeTypes.AUDIO_RAW;
        if (z) {
            mediaFormat = this.zzaey;
        }
        int integer = mediaFormat.getInteger("channel-count");
        int integer2 = mediaFormat.getInteger("sample-rate");
        if (this.zzaex && integer == 6) {
            int i = this.zzzt;
            if (i < 6) {
                int[] iArr2 = new int[i];
                for (int i2 = 0; i2 < this.zzzt; i2++) {
                    iArr2[i2] = i2;
                }
                iArr = iArr2;
                this.zzaev.zza(string, integer, integer2, this.zzzv, 0, iArr);
            }
        }
        iArr = null;
        try {
            this.zzaev.zza(string, integer, integer2, this.zzzv, 0, iArr);
        } catch (zzgw e) {
            throw zzff.zza(e, getIndex());
        }
    }

    /* access modifiers changed from: protected */
    public final void zzb(boolean z) throws zzff {
        super.zzb(z);
        this.zzaeu.zzc(this.zzavd);
        int i = zzbn().zzaak;
        if (i != 0) {
            this.zzaev.zzn(i);
        } else {
            this.zzaev.zzcy();
        }
    }

    /* access modifiers changed from: protected */
    public final void zza(long j, boolean z) throws zzff {
        super.zza(j, z);
        this.zzaev.reset();
        this.zzaez = j;
        this.zzafa = true;
    }

    /* access modifiers changed from: protected */
    public final void onStarted() {
        super.onStarted();
        this.zzaev.play();
    }

    /* access modifiers changed from: protected */
    public final void onStopped() {
        this.zzaev.pause();
        super.onStopped();
    }

    /* access modifiers changed from: protected */
    public final void zzbm() {
        try {
            this.zzaev.release();
            try {
                super.zzbm();
            } finally {
                this.zzavd.zzds();
                this.zzaeu.zzd(this.zzavd);
            }
        } catch (Throwable th) {
            super.zzbm();
            throw th;
        } finally {
            this.zzavd.zzds();
            this.zzaeu.zzd(this.zzavd);
        }
    }

    public final boolean zzcj() {
        return super.zzcj() && this.zzaev.zzcj();
    }

    public final boolean isReady() {
        return this.zzaev.zzcw() || super.isReady();
    }

    public final long zzde() {
        long zzg = this.zzaev.zzg(zzcj());
        if (zzg != Long.MIN_VALUE) {
            if (!this.zzafa) {
                zzg = Math.max(this.zzaez, zzg);
            }
            this.zzaez = zzg;
            this.zzafa = false;
        }
        return this.zzaez;
    }

    public final zzfy zzb(zzfy zzfy) {
        return this.zzaev.zzb(zzfy);
    }

    public final zzfy zzcx() {
        return this.zzaev.zzcx();
    }

    /* access modifiers changed from: protected */
    public final boolean zza(long j, long j2, MediaCodec mediaCodec, ByteBuffer byteBuffer, int i, int i2, long j3, boolean z) throws zzff {
        if (this.zzaew && (i2 & 2) != 0) {
            mediaCodec.releaseOutputBuffer(i, false);
            return true;
        } else if (z) {
            mediaCodec.releaseOutputBuffer(i, false);
            this.zzavd.zzagk++;
            this.zzaev.zzct();
            return true;
        } else {
            try {
                if (!this.zzaev.zza(byteBuffer, j3)) {
                    return false;
                }
                mediaCodec.releaseOutputBuffer(i, false);
                this.zzavd.zzagj++;
                return true;
            } catch (zzgx | zzha e) {
                throw zzff.zza(e, getIndex());
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void zzdj() throws zzff {
        try {
            this.zzaev.zzcu();
        } catch (zzha e) {
            throw zzff.zza(e, getIndex());
        }
    }

    public final void zza(int i, Object obj) throws zzff {
        switch (i) {
            case 2:
                this.zzaev.setVolume(((Float) obj).floatValue());
                return;
            case 3:
                this.zzaev.setStreamType(((Integer) obj).intValue());
                return;
            default:
                super.zza(i, obj);
                return;
        }
    }
}
