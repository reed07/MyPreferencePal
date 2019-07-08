package com.google.android.gms.internal.ads;

import android.net.Uri;

public final class zzno {
    private int zzaac;
    public final long zzbdk;
    private final String zzbdl;
    public final long zzcc;

    public zzno(String str, long j, long j2) {
        if (str == null) {
            str = "";
        }
        this.zzbdl = str;
        this.zzbdk = j;
        this.zzcc = j2;
    }

    public final Uri zzy(String str) {
        return Uri.parse(zzqd.zzc(str, this.zzbdl));
    }

    private final String zzz(String str) {
        return zzqd.zzc(str, this.zzbdl);
    }

    public final zzno zza(zzno zzno, String str) {
        String zzz = zzz(str);
        if (zzno == null || !zzz.equals(zzno.zzz(str))) {
            return null;
        }
        long j = this.zzcc;
        long j2 = -1;
        if (j != -1) {
            long j3 = this.zzbdk;
            if (j3 + j == zzno.zzbdk) {
                long j4 = zzno.zzcc;
                if (j4 != -1) {
                    j2 = j + j4;
                }
                zzno zzno2 = new zzno(zzz, j3, j2);
                return zzno2;
            }
        }
        long j5 = zzno.zzcc;
        if (j5 != -1) {
            long j6 = zzno.zzbdk;
            if (j6 + j5 == this.zzbdk) {
                long j7 = this.zzcc;
                if (j7 != -1) {
                    j2 = j5 + j7;
                }
                zzno zzno3 = new zzno(zzz, j6, j2);
                return zzno3;
            }
        }
        return null;
    }

    public final int hashCode() {
        if (this.zzaac == 0) {
            this.zzaac = ((((((int) this.zzbdk) + 527) * 31) + ((int) this.zzcc)) * 31) + this.zzbdl.hashCode();
        }
        return this.zzaac;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        zzno zzno = (zzno) obj;
        return this.zzbdk == zzno.zzbdk && this.zzcc == zzno.zzcc && this.zzbdl.equals(zzno.zzbdl);
    }
}
