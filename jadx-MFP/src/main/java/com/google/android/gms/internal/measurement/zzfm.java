package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzfm extends zzyc<zzfm> {
    private static volatile zzfm[] zzawb;
    public Boolean zzavj;
    public Boolean zzavk;
    public Integer zzavm;
    public String zzawc;
    public zzfk zzawd;

    public static zzfm[] zzmu() {
        if (zzawb == null) {
            synchronized (zzyg.zzcfe) {
                if (zzawb == null) {
                    zzawb = new zzfm[0];
                }
            }
        }
        return zzawb;
    }

    public zzfm() {
        this.zzavm = null;
        this.zzawc = null;
        this.zzawd = null;
        this.zzavj = null;
        this.zzavk = null;
        this.zzcev = null;
        this.zzcff = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzfm)) {
            return false;
        }
        zzfm zzfm = (zzfm) obj;
        Integer num = this.zzavm;
        if (num == null) {
            if (zzfm.zzavm != null) {
                return false;
            }
        } else if (!num.equals(zzfm.zzavm)) {
            return false;
        }
        String str = this.zzawc;
        if (str == null) {
            if (zzfm.zzawc != null) {
                return false;
            }
        } else if (!str.equals(zzfm.zzawc)) {
            return false;
        }
        zzfk zzfk = this.zzawd;
        if (zzfk == null) {
            if (zzfm.zzawd != null) {
                return false;
            }
        } else if (!zzfk.equals(zzfm.zzawd)) {
            return false;
        }
        Boolean bool = this.zzavj;
        if (bool == null) {
            if (zzfm.zzavj != null) {
                return false;
            }
        } else if (!bool.equals(zzfm.zzavj)) {
            return false;
        }
        Boolean bool2 = this.zzavk;
        if (bool2 == null) {
            if (zzfm.zzavk != null) {
                return false;
            }
        } else if (!bool2.equals(zzfm.zzavk)) {
            return false;
        }
        if (this.zzcev == null || this.zzcev.isEmpty()) {
            return zzfm.zzcev == null || zzfm.zzcev.isEmpty();
        }
        return this.zzcev.equals(zzfm.zzcev);
    }

    public final int hashCode() {
        int i;
        int hashCode = (getClass().getName().hashCode() + 527) * 31;
        Integer num = this.zzavm;
        int i2 = 0;
        int hashCode2 = (hashCode + (num == null ? 0 : num.hashCode())) * 31;
        String str = this.zzawc;
        int hashCode3 = hashCode2 + (str == null ? 0 : str.hashCode());
        zzfk zzfk = this.zzawd;
        int i3 = hashCode3 * 31;
        if (zzfk == null) {
            i = 0;
        } else {
            i = zzfk.hashCode();
        }
        int i4 = (i3 + i) * 31;
        Boolean bool = this.zzavj;
        int hashCode4 = (i4 + (bool == null ? 0 : bool.hashCode())) * 31;
        Boolean bool2 = this.zzavk;
        int hashCode5 = (hashCode4 + (bool2 == null ? 0 : bool2.hashCode())) * 31;
        if (this.zzcev != null && !this.zzcev.isEmpty()) {
            i2 = this.zzcev.hashCode();
        }
        return hashCode5 + i2;
    }

    public final void zza(zzya zzya) throws IOException {
        Integer num = this.zzavm;
        if (num != null) {
            zzya.zzd(1, num.intValue());
        }
        String str = this.zzawc;
        if (str != null) {
            zzya.zzb(2, str);
        }
        zzfk zzfk = this.zzawd;
        if (zzfk != null) {
            zzya.zza(3, (zzyi) zzfk);
        }
        Boolean bool = this.zzavj;
        if (bool != null) {
            zzya.zzb(4, bool.booleanValue());
        }
        Boolean bool2 = this.zzavk;
        if (bool2 != null) {
            zzya.zzb(5, bool2.booleanValue());
        }
        super.zza(zzya);
    }

    /* access modifiers changed from: protected */
    public final int zzf() {
        int zzf = super.zzf();
        Integer num = this.zzavm;
        if (num != null) {
            zzf += zzya.zzh(1, num.intValue());
        }
        String str = this.zzawc;
        if (str != null) {
            zzf += zzya.zzc(2, str);
        }
        zzfk zzfk = this.zzawd;
        if (zzfk != null) {
            zzf += zzya.zzb(3, (zzyi) zzfk);
        }
        Boolean bool = this.zzavj;
        if (bool != null) {
            bool.booleanValue();
            zzf += zzya.zzbd(4) + 1;
        }
        Boolean bool2 = this.zzavk;
        if (bool2 == null) {
            return zzf;
        }
        bool2.booleanValue();
        return zzf + zzya.zzbd(5) + 1;
    }

    public final /* synthetic */ zzyi zza(zzxz zzxz) throws IOException {
        while (true) {
            int zzuj = zzxz.zzuj();
            if (zzuj == 0) {
                return this;
            }
            if (zzuj == 8) {
                this.zzavm = Integer.valueOf(zzxz.zzvb());
            } else if (zzuj == 18) {
                this.zzawc = zzxz.readString();
            } else if (zzuj == 26) {
                if (this.zzawd == null) {
                    this.zzawd = new zzfk();
                }
                zzxz.zza((zzyi) this.zzawd);
            } else if (zzuj == 32) {
                this.zzavj = Boolean.valueOf(zzxz.zzup());
            } else if (zzuj == 40) {
                this.zzavk = Boolean.valueOf(zzxz.zzup());
            } else if (!super.zza(zzxz, zzuj)) {
                return this;
            }
        }
    }
}
