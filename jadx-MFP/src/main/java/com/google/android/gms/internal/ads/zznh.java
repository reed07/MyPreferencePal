package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.util.MimeTypes;

public final class zznh {
    private long zzaze;
    public final zzmf zzbat;
    public zznp zzbck;
    public zznd zzbcl;
    private int zzbcm;

    public zznh(long j, zznp zznp, boolean z, boolean z2) {
        zzhz zzhz;
        this.zzaze = j;
        this.zzbck = zznp;
        String str = zznp.zzaad.zzzi;
        int i = 0;
        if (zzpt.zzad(str) || MimeTypes.APPLICATION_TTML.equals(str)) {
            this.zzbat = null;
        } else {
            if (MimeTypes.APPLICATION_RAWCC.equals(str)) {
                zzhz = new zzjw(zznp.zzaad);
            } else {
                if (str.startsWith(MimeTypes.VIDEO_WEBM) || str.startsWith(MimeTypes.AUDIO_WEBM) || str.startsWith(MimeTypes.APPLICATION_WEBM)) {
                    zzhz = new zzip(1);
                } else {
                    if (z) {
                        i = 4;
                    }
                    if (z2) {
                        i |= 8;
                    }
                    zzhz = new zzji(i);
                }
            }
            this.zzbat = new zzmf(zzhz, zznp.zzaad);
        }
        this.zzbcl = zznp.zzgj();
    }

    public final void zza(long j, zznp zznp) throws zzkz {
        zznd zzgj = this.zzbck.zzgj();
        zznd zzgj2 = zznp.zzgj();
        this.zzaze = j;
        this.zzbck = zznp;
        if (zzgj != null) {
            this.zzbcl = zzgj2;
            if (zzgj.zzge()) {
                int zzai = zzgj.zzai(this.zzaze);
                if (zzai != 0) {
                    int zzgd = (zzgj.zzgd() + zzai) - 1;
                    long zzaw = zzgj.zzaw(zzgd) + zzgj.zze(zzgd, this.zzaze);
                    int zzgd2 = zzgj2.zzgd();
                    long zzaw2 = zzgj2.zzaw(zzgd2);
                    int i = (zzaw > zzaw2 ? 1 : (zzaw == zzaw2 ? 0 : -1));
                    if (i == 0) {
                        this.zzbcm += (zzgd + 1) - zzgd2;
                    } else if (i >= 0) {
                        this.zzbcm += zzgj.zzf(zzaw2, this.zzaze) - zzgd2;
                    } else {
                        throw new zzkz();
                    }
                }
            }
        }
    }

    public final int zzgd() {
        return this.zzbcl.zzgd() + this.zzbcm;
    }

    public final int zzgg() {
        return this.zzbcl.zzai(this.zzaze);
    }

    public final long zzay(int i) {
        return this.zzbcl.zzaw(i - this.zzbcm);
    }

    public final long zzaz(int i) {
        return zzay(i) + this.zzbcl.zze(i - this.zzbcm, this.zzaze);
    }

    public final int zzaj(long j) {
        return this.zzbcl.zzf(j, this.zzaze) + this.zzbcm;
    }

    public final zzno zzax(int i) {
        return this.zzbcl.zzax(i - this.zzbcm);
    }
}
