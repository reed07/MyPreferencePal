package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzfk extends zzyc<zzfk> {
    private static volatile zzfk[] zzavr;
    public zzfn zzavs;
    public zzfl zzavt;
    public Boolean zzavu;
    public String zzavv;

    public static zzfk[] zzmt() {
        if (zzavr == null) {
            synchronized (zzyg.zzcfe) {
                if (zzavr == null) {
                    zzavr = new zzfk[0];
                }
            }
        }
        return zzavr;
    }

    public zzfk() {
        this.zzavs = null;
        this.zzavt = null;
        this.zzavu = null;
        this.zzavv = null;
        this.zzcev = null;
        this.zzcff = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzfk)) {
            return false;
        }
        zzfk zzfk = (zzfk) obj;
        zzfn zzfn = this.zzavs;
        if (zzfn == null) {
            if (zzfk.zzavs != null) {
                return false;
            }
        } else if (!zzfn.equals(zzfk.zzavs)) {
            return false;
        }
        zzfl zzfl = this.zzavt;
        if (zzfl == null) {
            if (zzfk.zzavt != null) {
                return false;
            }
        } else if (!zzfl.equals(zzfk.zzavt)) {
            return false;
        }
        Boolean bool = this.zzavu;
        if (bool == null) {
            if (zzfk.zzavu != null) {
                return false;
            }
        } else if (!bool.equals(zzfk.zzavu)) {
            return false;
        }
        String str = this.zzavv;
        if (str == null) {
            if (zzfk.zzavv != null) {
                return false;
            }
        } else if (!str.equals(zzfk.zzavv)) {
            return false;
        }
        if (this.zzcev == null || this.zzcev.isEmpty()) {
            return zzfk.zzcev == null || zzfk.zzcev.isEmpty();
        }
        return this.zzcev.equals(zzfk.zzcev);
    }

    public final int hashCode() {
        int i;
        int i2;
        int hashCode = getClass().getName().hashCode() + 527;
        zzfn zzfn = this.zzavs;
        int i3 = hashCode * 31;
        int i4 = 0;
        if (zzfn == null) {
            i = 0;
        } else {
            i = zzfn.hashCode();
        }
        int i5 = i3 + i;
        zzfl zzfl = this.zzavt;
        int i6 = i5 * 31;
        if (zzfl == null) {
            i2 = 0;
        } else {
            i2 = zzfl.hashCode();
        }
        int i7 = (i6 + i2) * 31;
        Boolean bool = this.zzavu;
        int hashCode2 = (i7 + (bool == null ? 0 : bool.hashCode())) * 31;
        String str = this.zzavv;
        int hashCode3 = (hashCode2 + (str == null ? 0 : str.hashCode())) * 31;
        if (this.zzcev != null && !this.zzcev.isEmpty()) {
            i4 = this.zzcev.hashCode();
        }
        return hashCode3 + i4;
    }

    public final void zza(zzya zzya) throws IOException {
        zzfn zzfn = this.zzavs;
        if (zzfn != null) {
            zzya.zza(1, (zzyi) zzfn);
        }
        zzfl zzfl = this.zzavt;
        if (zzfl != null) {
            zzya.zza(2, (zzyi) zzfl);
        }
        Boolean bool = this.zzavu;
        if (bool != null) {
            zzya.zzb(3, bool.booleanValue());
        }
        String str = this.zzavv;
        if (str != null) {
            zzya.zzb(4, str);
        }
        super.zza(zzya);
    }

    /* access modifiers changed from: protected */
    public final int zzf() {
        int zzf = super.zzf();
        zzfn zzfn = this.zzavs;
        if (zzfn != null) {
            zzf += zzya.zzb(1, (zzyi) zzfn);
        }
        zzfl zzfl = this.zzavt;
        if (zzfl != null) {
            zzf += zzya.zzb(2, (zzyi) zzfl);
        }
        Boolean bool = this.zzavu;
        if (bool != null) {
            bool.booleanValue();
            zzf += zzya.zzbd(3) + 1;
        }
        String str = this.zzavv;
        return str != null ? zzf + zzya.zzc(4, str) : zzf;
    }

    public final /* synthetic */ zzyi zza(zzxz zzxz) throws IOException {
        while (true) {
            int zzuj = zzxz.zzuj();
            if (zzuj == 0) {
                return this;
            }
            if (zzuj == 10) {
                if (this.zzavs == null) {
                    this.zzavs = new zzfn();
                }
                zzxz.zza((zzyi) this.zzavs);
            } else if (zzuj == 18) {
                if (this.zzavt == null) {
                    this.zzavt = new zzfl();
                }
                zzxz.zza((zzyi) this.zzavt);
            } else if (zzuj == 24) {
                this.zzavu = Boolean.valueOf(zzxz.zzup());
            } else if (zzuj == 34) {
                this.zzavv = zzxz.readString();
            } else if (!super.zza(zzxz, zzuj)) {
                return this;
            }
        }
    }
}
