package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzfz extends zzyc<zzfz> {
    private static volatile zzfz[] zzayv;
    public String name;
    public String zzamn;
    private Float zzauo;
    public Double zzaup;
    public Long zzaxg;
    public Long zzayw;

    public static zzfz[] zznd() {
        if (zzayv == null) {
            synchronized (zzyg.zzcfe) {
                if (zzayv == null) {
                    zzayv = new zzfz[0];
                }
            }
        }
        return zzayv;
    }

    public zzfz() {
        this.zzayw = null;
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
        if (!(obj instanceof zzfz)) {
            return false;
        }
        zzfz zzfz = (zzfz) obj;
        Long l = this.zzayw;
        if (l == null) {
            if (zzfz.zzayw != null) {
                return false;
            }
        } else if (!l.equals(zzfz.zzayw)) {
            return false;
        }
        String str = this.name;
        if (str == null) {
            if (zzfz.name != null) {
                return false;
            }
        } else if (!str.equals(zzfz.name)) {
            return false;
        }
        String str2 = this.zzamn;
        if (str2 == null) {
            if (zzfz.zzamn != null) {
                return false;
            }
        } else if (!str2.equals(zzfz.zzamn)) {
            return false;
        }
        Long l2 = this.zzaxg;
        if (l2 == null) {
            if (zzfz.zzaxg != null) {
                return false;
            }
        } else if (!l2.equals(zzfz.zzaxg)) {
            return false;
        }
        Float f = this.zzauo;
        if (f == null) {
            if (zzfz.zzauo != null) {
                return false;
            }
        } else if (!f.equals(zzfz.zzauo)) {
            return false;
        }
        Double d = this.zzaup;
        if (d == null) {
            if (zzfz.zzaup != null) {
                return false;
            }
        } else if (!d.equals(zzfz.zzaup)) {
            return false;
        }
        if (this.zzcev == null || this.zzcev.isEmpty()) {
            return zzfz.zzcev == null || zzfz.zzcev.isEmpty();
        }
        return this.zzcev.equals(zzfz.zzcev);
    }

    public final int hashCode() {
        int hashCode = (getClass().getName().hashCode() + 527) * 31;
        Long l = this.zzayw;
        int i = 0;
        int hashCode2 = (hashCode + (l == null ? 0 : l.hashCode())) * 31;
        String str = this.name;
        int hashCode3 = (hashCode2 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.zzamn;
        int hashCode4 = (hashCode3 + (str2 == null ? 0 : str2.hashCode())) * 31;
        Long l2 = this.zzaxg;
        int hashCode5 = (hashCode4 + (l2 == null ? 0 : l2.hashCode())) * 31;
        Float f = this.zzauo;
        int hashCode6 = (hashCode5 + (f == null ? 0 : f.hashCode())) * 31;
        Double d = this.zzaup;
        int hashCode7 = (hashCode6 + (d == null ? 0 : d.hashCode())) * 31;
        if (this.zzcev != null && !this.zzcev.isEmpty()) {
            i = this.zzcev.hashCode();
        }
        return hashCode7 + i;
    }

    public final void zza(zzya zzya) throws IOException {
        Long l = this.zzayw;
        if (l != null) {
            zzya.zzi(1, l.longValue());
        }
        String str = this.name;
        if (str != null) {
            zzya.zzb(2, str);
        }
        String str2 = this.zzamn;
        if (str2 != null) {
            zzya.zzb(3, str2);
        }
        Long l2 = this.zzaxg;
        if (l2 != null) {
            zzya.zzi(4, l2.longValue());
        }
        Float f = this.zzauo;
        if (f != null) {
            zzya.zza(5, f.floatValue());
        }
        Double d = this.zzaup;
        if (d != null) {
            zzya.zza(6, d.doubleValue());
        }
        super.zza(zzya);
    }

    /* access modifiers changed from: protected */
    public final int zzf() {
        int zzf = super.zzf();
        Long l = this.zzayw;
        if (l != null) {
            zzf += zzya.zzd(1, l.longValue());
        }
        String str = this.name;
        if (str != null) {
            zzf += zzya.zzc(2, str);
        }
        String str2 = this.zzamn;
        if (str2 != null) {
            zzf += zzya.zzc(3, str2);
        }
        Long l2 = this.zzaxg;
        if (l2 != null) {
            zzf += zzya.zzd(4, l2.longValue());
        }
        Float f = this.zzauo;
        if (f != null) {
            f.floatValue();
            zzf += zzya.zzbd(5) + 4;
        }
        Double d = this.zzaup;
        if (d == null) {
            return zzf;
        }
        d.doubleValue();
        return zzf + zzya.zzbd(6) + 8;
    }

    public final /* synthetic */ zzyi zza(zzxz zzxz) throws IOException {
        while (true) {
            int zzuj = zzxz.zzuj();
            if (zzuj == 0) {
                return this;
            }
            if (zzuj == 8) {
                this.zzayw = Long.valueOf(zzxz.zzvc());
            } else if (zzuj == 18) {
                this.name = zzxz.readString();
            } else if (zzuj == 26) {
                this.zzamn = zzxz.readString();
            } else if (zzuj == 32) {
                this.zzaxg = Long.valueOf(zzxz.zzvc());
            } else if (zzuj == 45) {
                this.zzauo = Float.valueOf(Float.intBitsToFloat(zzxz.zzvd()));
            } else if (zzuj == 49) {
                this.zzaup = Double.valueOf(Double.longBitsToDouble(zzxz.zzve()));
            } else if (!super.zza(zzxz, zzuj)) {
                return this;
            }
        }
    }
}
