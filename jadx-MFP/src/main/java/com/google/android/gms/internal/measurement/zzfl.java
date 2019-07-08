package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzfl extends zzyc<zzfl> {
    public Integer zzavw;
    public Boolean zzavx;
    public String zzavy;
    public String zzavz;
    public String zzawa;

    public zzfl() {
        this.zzavw = null;
        this.zzavx = null;
        this.zzavy = null;
        this.zzavz = null;
        this.zzawa = null;
        this.zzcev = null;
        this.zzcff = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzfl)) {
            return false;
        }
        zzfl zzfl = (zzfl) obj;
        Integer num = this.zzavw;
        if (num == null) {
            if (zzfl.zzavw != null) {
                return false;
            }
        } else if (!num.equals(zzfl.zzavw)) {
            return false;
        }
        Boolean bool = this.zzavx;
        if (bool == null) {
            if (zzfl.zzavx != null) {
                return false;
            }
        } else if (!bool.equals(zzfl.zzavx)) {
            return false;
        }
        String str = this.zzavy;
        if (str == null) {
            if (zzfl.zzavy != null) {
                return false;
            }
        } else if (!str.equals(zzfl.zzavy)) {
            return false;
        }
        String str2 = this.zzavz;
        if (str2 == null) {
            if (zzfl.zzavz != null) {
                return false;
            }
        } else if (!str2.equals(zzfl.zzavz)) {
            return false;
        }
        String str3 = this.zzawa;
        if (str3 == null) {
            if (zzfl.zzawa != null) {
                return false;
            }
        } else if (!str3.equals(zzfl.zzawa)) {
            return false;
        }
        if (this.zzcev == null || this.zzcev.isEmpty()) {
            return zzfl.zzcev == null || zzfl.zzcev.isEmpty();
        }
        return this.zzcev.equals(zzfl.zzcev);
    }

    public final int hashCode() {
        int hashCode = (getClass().getName().hashCode() + 527) * 31;
        Integer num = this.zzavw;
        int i = 0;
        int intValue = (hashCode + (num == null ? 0 : num.intValue())) * 31;
        Boolean bool = this.zzavx;
        int hashCode2 = (intValue + (bool == null ? 0 : bool.hashCode())) * 31;
        String str = this.zzavy;
        int hashCode3 = (hashCode2 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.zzavz;
        int hashCode4 = (hashCode3 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.zzawa;
        int hashCode5 = (hashCode4 + (str3 == null ? 0 : str3.hashCode())) * 31;
        if (this.zzcev != null && !this.zzcev.isEmpty()) {
            i = this.zzcev.hashCode();
        }
        return hashCode5 + i;
    }

    public final void zza(zzya zzya) throws IOException {
        Integer num = this.zzavw;
        if (num != null) {
            zzya.zzd(1, num.intValue());
        }
        Boolean bool = this.zzavx;
        if (bool != null) {
            zzya.zzb(2, bool.booleanValue());
        }
        String str = this.zzavy;
        if (str != null) {
            zzya.zzb(3, str);
        }
        String str2 = this.zzavz;
        if (str2 != null) {
            zzya.zzb(4, str2);
        }
        String str3 = this.zzawa;
        if (str3 != null) {
            zzya.zzb(5, str3);
        }
        super.zza(zzya);
    }

    /* access modifiers changed from: protected */
    public final int zzf() {
        int zzf = super.zzf();
        Integer num = this.zzavw;
        if (num != null) {
            zzf += zzya.zzh(1, num.intValue());
        }
        Boolean bool = this.zzavx;
        if (bool != null) {
            bool.booleanValue();
            zzf += zzya.zzbd(2) + 1;
        }
        String str = this.zzavy;
        if (str != null) {
            zzf += zzya.zzc(3, str);
        }
        String str2 = this.zzavz;
        if (str2 != null) {
            zzf += zzya.zzc(4, str2);
        }
        String str3 = this.zzawa;
        return str3 != null ? zzf + zzya.zzc(5, str3) : zzf;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzc */
    public final zzfl zza(zzxz zzxz) throws IOException {
        int zzvb;
        while (true) {
            int zzuj = zzxz.zzuj();
            if (zzuj == 0) {
                return this;
            }
            if (zzuj == 8) {
                try {
                    zzvb = zzxz.zzvb();
                    if (zzvb < 0 || zzvb > 4) {
                        StringBuilder sb = new StringBuilder(46);
                        sb.append(zzvb);
                        sb.append(" is not a valid enum ComparisonType");
                    } else {
                        this.zzavw = Integer.valueOf(zzvb);
                    }
                } catch (IllegalArgumentException unused) {
                    zzxz.zzcb(zzxz.getPosition());
                    zza(zzxz, zzuj);
                }
            } else if (zzuj == 16) {
                this.zzavx = Boolean.valueOf(zzxz.zzup());
            } else if (zzuj == 26) {
                this.zzavy = zzxz.readString();
            } else if (zzuj == 34) {
                this.zzavz = zzxz.readString();
            } else if (zzuj == 42) {
                this.zzawa = zzxz.readString();
            } else if (!super.zza(zzxz, zzuj)) {
                return this;
            }
        }
        StringBuilder sb2 = new StringBuilder(46);
        sb2.append(zzvb);
        sb2.append(" is not a valid enum ComparisonType");
        throw new IllegalArgumentException(sb2.toString());
    }
}
