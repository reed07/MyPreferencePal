package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzfi extends zzyc<zzfi> {
    private static volatile zzfi[] zzavf;
    public Integer zzavg;
    public zzfm[] zzavh;
    public zzfj[] zzavi;
    private Boolean zzavj;
    private Boolean zzavk;

    public static zzfi[] zzmr() {
        if (zzavf == null) {
            synchronized (zzyg.zzcfe) {
                if (zzavf == null) {
                    zzavf = new zzfi[0];
                }
            }
        }
        return zzavf;
    }

    public zzfi() {
        this.zzavg = null;
        this.zzavh = zzfm.zzmu();
        this.zzavi = zzfj.zzms();
        this.zzavj = null;
        this.zzavk = null;
        this.zzcev = null;
        this.zzcff = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzfi)) {
            return false;
        }
        zzfi zzfi = (zzfi) obj;
        Integer num = this.zzavg;
        if (num == null) {
            if (zzfi.zzavg != null) {
                return false;
            }
        } else if (!num.equals(zzfi.zzavg)) {
            return false;
        }
        if (!zzyg.equals((Object[]) this.zzavh, (Object[]) zzfi.zzavh) || !zzyg.equals((Object[]) this.zzavi, (Object[]) zzfi.zzavi)) {
            return false;
        }
        Boolean bool = this.zzavj;
        if (bool == null) {
            if (zzfi.zzavj != null) {
                return false;
            }
        } else if (!bool.equals(zzfi.zzavj)) {
            return false;
        }
        Boolean bool2 = this.zzavk;
        if (bool2 == null) {
            if (zzfi.zzavk != null) {
                return false;
            }
        } else if (!bool2.equals(zzfi.zzavk)) {
            return false;
        }
        if (this.zzcev == null || this.zzcev.isEmpty()) {
            return zzfi.zzcev == null || zzfi.zzcev.isEmpty();
        }
        return this.zzcev.equals(zzfi.zzcev);
    }

    public final int hashCode() {
        int hashCode = (getClass().getName().hashCode() + 527) * 31;
        Integer num = this.zzavg;
        int i = 0;
        int hashCode2 = (((((hashCode + (num == null ? 0 : num.hashCode())) * 31) + zzyg.hashCode((Object[]) this.zzavh)) * 31) + zzyg.hashCode((Object[]) this.zzavi)) * 31;
        Boolean bool = this.zzavj;
        int hashCode3 = (hashCode2 + (bool == null ? 0 : bool.hashCode())) * 31;
        Boolean bool2 = this.zzavk;
        int hashCode4 = (hashCode3 + (bool2 == null ? 0 : bool2.hashCode())) * 31;
        if (this.zzcev != null && !this.zzcev.isEmpty()) {
            i = this.zzcev.hashCode();
        }
        return hashCode4 + i;
    }

    public final void zza(zzya zzya) throws IOException {
        Integer num = this.zzavg;
        if (num != null) {
            zzya.zzd(1, num.intValue());
        }
        zzfm[] zzfmArr = this.zzavh;
        int i = 0;
        if (zzfmArr != null && zzfmArr.length > 0) {
            int i2 = 0;
            while (true) {
                zzfm[] zzfmArr2 = this.zzavh;
                if (i2 >= zzfmArr2.length) {
                    break;
                }
                zzfm zzfm = zzfmArr2[i2];
                if (zzfm != null) {
                    zzya.zza(2, (zzyi) zzfm);
                }
                i2++;
            }
        }
        zzfj[] zzfjArr = this.zzavi;
        if (zzfjArr != null && zzfjArr.length > 0) {
            while (true) {
                zzfj[] zzfjArr2 = this.zzavi;
                if (i >= zzfjArr2.length) {
                    break;
                }
                zzfj zzfj = zzfjArr2[i];
                if (zzfj != null) {
                    zzya.zza(3, (zzyi) zzfj);
                }
                i++;
            }
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
        Integer num = this.zzavg;
        if (num != null) {
            zzf += zzya.zzh(1, num.intValue());
        }
        zzfm[] zzfmArr = this.zzavh;
        int i = 0;
        if (zzfmArr != null && zzfmArr.length > 0) {
            int i2 = zzf;
            int i3 = 0;
            while (true) {
                zzfm[] zzfmArr2 = this.zzavh;
                if (i3 >= zzfmArr2.length) {
                    break;
                }
                zzfm zzfm = zzfmArr2[i3];
                if (zzfm != null) {
                    i2 += zzya.zzb(2, (zzyi) zzfm);
                }
                i3++;
            }
            zzf = i2;
        }
        zzfj[] zzfjArr = this.zzavi;
        if (zzfjArr != null && zzfjArr.length > 0) {
            while (true) {
                zzfj[] zzfjArr2 = this.zzavi;
                if (i >= zzfjArr2.length) {
                    break;
                }
                zzfj zzfj = zzfjArr2[i];
                if (zzfj != null) {
                    zzf += zzya.zzb(3, (zzyi) zzfj);
                }
                i++;
            }
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
                this.zzavg = Integer.valueOf(zzxz.zzvb());
            } else if (zzuj == 18) {
                int zzb = zzyl.zzb(zzxz, 18);
                zzfm[] zzfmArr = this.zzavh;
                int length = zzfmArr == null ? 0 : zzfmArr.length;
                zzfm[] zzfmArr2 = new zzfm[(zzb + length)];
                if (length != 0) {
                    System.arraycopy(this.zzavh, 0, zzfmArr2, 0, length);
                }
                while (length < zzfmArr2.length - 1) {
                    zzfmArr2[length] = new zzfm();
                    zzxz.zza((zzyi) zzfmArr2[length]);
                    zzxz.zzuj();
                    length++;
                }
                zzfmArr2[length] = new zzfm();
                zzxz.zza((zzyi) zzfmArr2[length]);
                this.zzavh = zzfmArr2;
            } else if (zzuj == 26) {
                int zzb2 = zzyl.zzb(zzxz, 26);
                zzfj[] zzfjArr = this.zzavi;
                int length2 = zzfjArr == null ? 0 : zzfjArr.length;
                zzfj[] zzfjArr2 = new zzfj[(zzb2 + length2)];
                if (length2 != 0) {
                    System.arraycopy(this.zzavi, 0, zzfjArr2, 0, length2);
                }
                while (length2 < zzfjArr2.length - 1) {
                    zzfjArr2[length2] = new zzfj();
                    zzxz.zza((zzyi) zzfjArr2[length2]);
                    zzxz.zzuj();
                    length2++;
                }
                zzfjArr2[length2] = new zzfj();
                zzxz.zza((zzyi) zzfjArr2[length2]);
                this.zzavi = zzfjArr2;
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
