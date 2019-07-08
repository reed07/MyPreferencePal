package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzfs extends zzyc<zzfs> {
    private static volatile zzfs[] zzawy;
    public Integer zzawz;
    public Long zzaxa;

    public static zzfs[] zzmy() {
        if (zzawy == null) {
            synchronized (zzyg.zzcfe) {
                if (zzawy == null) {
                    zzawy = new zzfs[0];
                }
            }
        }
        return zzawy;
    }

    public zzfs() {
        this.zzawz = null;
        this.zzaxa = null;
        this.zzcev = null;
        this.zzcff = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzfs)) {
            return false;
        }
        zzfs zzfs = (zzfs) obj;
        Integer num = this.zzawz;
        if (num == null) {
            if (zzfs.zzawz != null) {
                return false;
            }
        } else if (!num.equals(zzfs.zzawz)) {
            return false;
        }
        Long l = this.zzaxa;
        if (l == null) {
            if (zzfs.zzaxa != null) {
                return false;
            }
        } else if (!l.equals(zzfs.zzaxa)) {
            return false;
        }
        if (this.zzcev == null || this.zzcev.isEmpty()) {
            return zzfs.zzcev == null || zzfs.zzcev.isEmpty();
        }
        return this.zzcev.equals(zzfs.zzcev);
    }

    public final int hashCode() {
        int hashCode = (getClass().getName().hashCode() + 527) * 31;
        Integer num = this.zzawz;
        int i = 0;
        int hashCode2 = (hashCode + (num == null ? 0 : num.hashCode())) * 31;
        Long l = this.zzaxa;
        int hashCode3 = (hashCode2 + (l == null ? 0 : l.hashCode())) * 31;
        if (this.zzcev != null && !this.zzcev.isEmpty()) {
            i = this.zzcev.hashCode();
        }
        return hashCode3 + i;
    }

    public final void zza(zzya zzya) throws IOException {
        Integer num = this.zzawz;
        if (num != null) {
            zzya.zzd(1, num.intValue());
        }
        Long l = this.zzaxa;
        if (l != null) {
            zzya.zzi(2, l.longValue());
        }
        super.zza(zzya);
    }

    /* access modifiers changed from: protected */
    public final int zzf() {
        int zzf = super.zzf();
        Integer num = this.zzawz;
        if (num != null) {
            zzf += zzya.zzh(1, num.intValue());
        }
        Long l = this.zzaxa;
        return l != null ? zzf + zzya.zzd(2, l.longValue()) : zzf;
    }

    public final /* synthetic */ zzyi zza(zzxz zzxz) throws IOException {
        while (true) {
            int zzuj = zzxz.zzuj();
            if (zzuj == 0) {
                return this;
            }
            if (zzuj == 8) {
                this.zzawz = Integer.valueOf(zzxz.zzvb());
            } else if (zzuj == 16) {
                this.zzaxa = Long.valueOf(zzxz.zzvc());
            } else if (!super.zza(zzxz, zzuj)) {
                return this;
            }
        }
    }
}
