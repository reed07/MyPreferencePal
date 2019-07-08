package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzfu extends zzyc<zzfu> {
    private static volatile zzfu[] zzaxf;
    public String name;
    public String zzamn;
    private Float zzauo;
    public Double zzaup;
    public Long zzaxg;

    public static zzfu[] zzna() {
        if (zzaxf == null) {
            synchronized (zzyg.zzcfe) {
                if (zzaxf == null) {
                    zzaxf = new zzfu[0];
                }
            }
        }
        return zzaxf;
    }

    public zzfu() {
        this.name = null;
        this.zzamn = null;
        this.zzaxg = null;
        this.zzauo = null;
        this.zzaup = null;
        this.zzcev = null;
        this.zzcff = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzfu)) {
            return false;
        }
        zzfu zzfu = (zzfu) obj;
        String str = this.name;
        if (str == null) {
            if (zzfu.name != null) {
                return false;
            }
        } else if (!str.equals(zzfu.name)) {
            return false;
        }
        String str2 = this.zzamn;
        if (str2 == null) {
            if (zzfu.zzamn != null) {
                return false;
            }
        } else if (!str2.equals(zzfu.zzamn)) {
            return false;
        }
        Long l = this.zzaxg;
        if (l == null) {
            if (zzfu.zzaxg != null) {
                return false;
            }
        } else if (!l.equals(zzfu.zzaxg)) {
            return false;
        }
        Float f = this.zzauo;
        if (f == null) {
            if (zzfu.zzauo != null) {
                return false;
            }
        } else if (!f.equals(zzfu.zzauo)) {
            return false;
        }
        Double d = this.zzaup;
        if (d == null) {
            if (zzfu.zzaup != null) {
                return false;
            }
        } else if (!d.equals(zzfu.zzaup)) {
            return false;
        }
        if (this.zzcev == null || this.zzcev.isEmpty()) {
            return zzfu.zzcev == null || zzfu.zzcev.isEmpty();
        }
        return this.zzcev.equals(zzfu.zzcev);
    }

    public final int hashCode() {
        int hashCode = (getClass().getName().hashCode() + 527) * 31;
        String str = this.name;
        int i = 0;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.zzamn;
        int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        Long l = this.zzaxg;
        int hashCode4 = (hashCode3 + (l == null ? 0 : l.hashCode())) * 31;
        Float f = this.zzauo;
        int hashCode5 = (hashCode4 + (f == null ? 0 : f.hashCode())) * 31;
        Double d = this.zzaup;
        int hashCode6 = (hashCode5 + (d == null ? 0 : d.hashCode())) * 31;
        if (this.zzcev != null && !this.zzcev.isEmpty()) {
            i = this.zzcev.hashCode();
        }
        return hashCode6 + i;
    }

    public final void zza(zzya zzya) throws IOException {
        String str = this.name;
        if (str != null) {
            zzya.zzb(1, str);
        }
        String str2 = this.zzamn;
        if (str2 != null) {
            zzya.zzb(2, str2);
        }
        Long l = this.zzaxg;
        if (l != null) {
            zzya.zzi(3, l.longValue());
        }
        Float f = this.zzauo;
        if (f != null) {
            zzya.zza(4, f.floatValue());
        }
        Double d = this.zzaup;
        if (d != null) {
            zzya.zza(5, d.doubleValue());
        }
        super.zza(zzya);
    }

    /* access modifiers changed from: protected */
    public final int zzf() {
        int zzf = super.zzf();
        String str = this.name;
        if (str != null) {
            zzf += zzya.zzc(1, str);
        }
        String str2 = this.zzamn;
        if (str2 != null) {
            zzf += zzya.zzc(2, str2);
        }
        Long l = this.zzaxg;
        if (l != null) {
            zzf += zzya.zzd(3, l.longValue());
        }
        Float f = this.zzauo;
        if (f != null) {
            f.floatValue();
            zzf += zzya.zzbd(4) + 4;
        }
        Double d = this.zzaup;
        if (d == null) {
            return zzf;
        }
        d.doubleValue();
        return zzf + zzya.zzbd(5) + 8;
    }

    public final /* synthetic */ zzyi zza(zzxz zzxz) throws IOException {
        while (true) {
            int zzuj = zzxz.zzuj();
            if (zzuj == 0) {
                return this;
            }
            if (zzuj == 10) {
                this.name = zzxz.readString();
            } else if (zzuj == 18) {
                this.zzamn = zzxz.readString();
            } else if (zzuj == 24) {
                this.zzaxg = Long.valueOf(zzxz.zzvc());
            } else if (zzuj == 37) {
                this.zzauo = Float.valueOf(Float.intBitsToFloat(zzxz.zzvd()));
            } else if (zzuj == 41) {
                this.zzaup = Double.valueOf(Double.longBitsToDouble(zzxz.zzve()));
            } else if (!super.zza(zzxz, zzuj)) {
                return this;
            }
        }
    }
}
