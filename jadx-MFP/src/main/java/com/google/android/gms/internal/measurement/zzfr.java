package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzfr extends zzyc<zzfr> {
    private static volatile zzfr[] zzawu;
    public Integer zzavg;
    public zzfx zzawv;
    public zzfx zzaww;
    public Boolean zzawx;

    public static zzfr[] zzmx() {
        if (zzawu == null) {
            synchronized (zzyg.zzcfe) {
                if (zzawu == null) {
                    zzawu = new zzfr[0];
                }
            }
        }
        return zzawu;
    }

    public zzfr() {
        this.zzavg = null;
        this.zzawv = null;
        this.zzaww = null;
        this.zzawx = null;
        this.zzcev = null;
        this.zzcff = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzfr)) {
            return false;
        }
        zzfr zzfr = (zzfr) obj;
        Integer num = this.zzavg;
        if (num == null) {
            if (zzfr.zzavg != null) {
                return false;
            }
        } else if (!num.equals(zzfr.zzavg)) {
            return false;
        }
        zzfx zzfx = this.zzawv;
        if (zzfx == null) {
            if (zzfr.zzawv != null) {
                return false;
            }
        } else if (!zzfx.equals(zzfr.zzawv)) {
            return false;
        }
        zzfx zzfx2 = this.zzaww;
        if (zzfx2 == null) {
            if (zzfr.zzaww != null) {
                return false;
            }
        } else if (!zzfx2.equals(zzfr.zzaww)) {
            return false;
        }
        Boolean bool = this.zzawx;
        if (bool == null) {
            if (zzfr.zzawx != null) {
                return false;
            }
        } else if (!bool.equals(zzfr.zzawx)) {
            return false;
        }
        if (this.zzcev == null || this.zzcev.isEmpty()) {
            return zzfr.zzcev == null || zzfr.zzcev.isEmpty();
        }
        return this.zzcev.equals(zzfr.zzcev);
    }

    public final int hashCode() {
        int i;
        int i2;
        int hashCode = (getClass().getName().hashCode() + 527) * 31;
        Integer num = this.zzavg;
        int i3 = 0;
        int hashCode2 = hashCode + (num == null ? 0 : num.hashCode());
        zzfx zzfx = this.zzawv;
        int i4 = hashCode2 * 31;
        if (zzfx == null) {
            i = 0;
        } else {
            i = zzfx.hashCode();
        }
        int i5 = i4 + i;
        zzfx zzfx2 = this.zzaww;
        int i6 = i5 * 31;
        if (zzfx2 == null) {
            i2 = 0;
        } else {
            i2 = zzfx2.hashCode();
        }
        int i7 = (i6 + i2) * 31;
        Boolean bool = this.zzawx;
        int hashCode3 = (i7 + (bool == null ? 0 : bool.hashCode())) * 31;
        if (this.zzcev != null && !this.zzcev.isEmpty()) {
            i3 = this.zzcev.hashCode();
        }
        return hashCode3 + i3;
    }

    public final void zza(zzya zzya) throws IOException {
        Integer num = this.zzavg;
        if (num != null) {
            zzya.zzd(1, num.intValue());
        }
        zzfx zzfx = this.zzawv;
        if (zzfx != null) {
            zzya.zza(2, (zzyi) zzfx);
        }
        zzfx zzfx2 = this.zzaww;
        if (zzfx2 != null) {
            zzya.zza(3, (zzyi) zzfx2);
        }
        Boolean bool = this.zzawx;
        if (bool != null) {
            zzya.zzb(4, bool.booleanValue());
        }
        super.zza(zzya);
    }

    /* access modifiers changed from: protected */
    public final int zzf() {
        int zzf = super.zzf();
        Integer num = this.zzavg;
        if (num != null) {
            zzf += zzya.zzh(1, num.intValue());
        }
        zzfx zzfx = this.zzawv;
        if (zzfx != null) {
            zzf += zzya.zzb(2, (zzyi) zzfx);
        }
        zzfx zzfx2 = this.zzaww;
        if (zzfx2 != null) {
            zzf += zzya.zzb(3, (zzyi) zzfx2);
        }
        Boolean bool = this.zzawx;
        if (bool == null) {
            return zzf;
        }
        bool.booleanValue();
        return zzf + zzya.zzbd(4) + 1;
    }

    public final /* synthetic */ zzyi zza(zzxz zzxz) throws IOException {
        while (true) {
            int zzuj = zzxz.zzuj();
            if (zzuj == 0) {
                return this;
            }
            if (zzuj == 8) {
                this.zzavg = Integer.valueOf(zzxz.zzvb());
            } else if (zzuj == 18) {
                if (this.zzawv == null) {
                    this.zzawv = new zzfx();
                }
                zzxz.zza((zzyi) this.zzawv);
            } else if (zzuj == 26) {
                if (this.zzaww == null) {
                    this.zzaww = new zzfx();
                }
                zzxz.zza((zzyi) this.zzaww);
            } else if (zzuj == 32) {
                this.zzawx = Boolean.valueOf(zzxz.zzup());
            } else if (!super.zza(zzxz, zzuj)) {
                return this;
            }
        }
    }
}
