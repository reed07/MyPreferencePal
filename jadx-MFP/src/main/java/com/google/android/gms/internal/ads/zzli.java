package com.google.android.gms.internal.ads;

import android.net.Uri;
import java.io.EOFException;
import java.io.IOException;

final class zzli {
    private final zzib zzajq;
    private final zzhz[] zzaxt;
    private zzhz zzaxu;

    public zzli(zzhz[] zzhzArr, zzib zzib) {
        this.zzaxt = zzhzArr;
        this.zzajq = zzib;
    }

    public final zzhz zza(zzia zzia, Uri uri) throws IOException, InterruptedException {
        zzhz zzhz = this.zzaxu;
        if (zzhz != null) {
            return zzhz;
        }
        zzhz[] zzhzArr = this.zzaxt;
        int length = zzhzArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            zzhz zzhz2 = zzhzArr[i];
            try {
                if (zzhz2.zza(zzia)) {
                    this.zzaxu = zzhz2;
                    zzia.zzdx();
                    break;
                }
                i++;
            } catch (EOFException unused) {
            } finally {
                zzia.zzdx();
            }
        }
        zzhz zzhz3 = this.zzaxu;
        if (zzhz3 != null) {
            zzhz3.zza(this.zzajq);
            return this.zzaxu;
        }
        String zza = zzqe.zza((Object[]) this.zzaxt);
        StringBuilder sb = new StringBuilder(String.valueOf(zza).length() + 58);
        sb.append("None of the available extractors (");
        sb.append(zza);
        sb.append(") could read the stream.");
        throw new zzmb(sb.toString(), uri);
    }

    public final void release() {
        zzhz zzhz = this.zzaxu;
        if (zzhz != null) {
            zzhz.release();
            this.zzaxu = null;
        }
    }
}
