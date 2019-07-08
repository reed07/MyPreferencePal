package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzfj extends zzyc<zzfj> {
    private static volatile zzfj[] zzavl;
    public Boolean zzavj;
    public Boolean zzavk;
    public Integer zzavm;
    public String zzavn;
    public zzfk[] zzavo;
    private Boolean zzavp;
    public zzfl zzavq;

    public static zzfj[] zzms() {
        if (zzavl == null) {
            synchronized (zzyg.zzcfe) {
                if (zzavl == null) {
                    zzavl = new zzfj[0];
                }
            }
        }
        return zzavl;
    }

    public zzfj() {
        this.zzavm = null;
        this.zzavn = null;
        this.zzavo = zzfk.zzmt();
        this.zzavp = null;
        this.zzavq = null;
        this.zzavj = null;
        this.zzavk = null;
        this.zzcev = null;
        this.zzcff = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzfj)) {
            return false;
        }
        zzfj zzfj = (zzfj) obj;
        Integer num = this.zzavm;
        if (num == null) {
            if (zzfj.zzavm != null) {
                return false;
            }
        } else if (!num.equals(zzfj.zzavm)) {
            return false;
        }
        String str = this.zzavn;
        if (str == null) {
            if (zzfj.zzavn != null) {
                return false;
            }
        } else if (!str.equals(zzfj.zzavn)) {
            return false;
        }
        if (!zzyg.equals((Object[]) this.zzavo, (Object[]) zzfj.zzavo)) {
            return false;
        }
        Boolean bool = this.zzavp;
        if (bool == null) {
            if (zzfj.zzavp != null) {
                return false;
            }
        } else if (!bool.equals(zzfj.zzavp)) {
            return false;
        }
        zzfl zzfl = this.zzavq;
        if (zzfl == null) {
            if (zzfj.zzavq != null) {
                return false;
            }
        } else if (!zzfl.equals(zzfj.zzavq)) {
            return false;
        }
        Boolean bool2 = this.zzavj;
        if (bool2 == null) {
            if (zzfj.zzavj != null) {
                return false;
            }
        } else if (!bool2.equals(zzfj.zzavj)) {
            return false;
        }
        Boolean bool3 = this.zzavk;
        if (bool3 == null) {
            if (zzfj.zzavk != null) {
                return false;
            }
        } else if (!bool3.equals(zzfj.zzavk)) {
            return false;
        }
        if (this.zzcev == null || this.zzcev.isEmpty()) {
            return zzfj.zzcev == null || zzfj.zzcev.isEmpty();
        }
        return this.zzcev.equals(zzfj.zzcev);
    }

    public final int hashCode() {
        int i;
        int hashCode = (getClass().getName().hashCode() + 527) * 31;
        Integer num = this.zzavm;
        int i2 = 0;
        int hashCode2 = (hashCode + (num == null ? 0 : num.hashCode())) * 31;
        String str = this.zzavn;
        int hashCode3 = (((hashCode2 + (str == null ? 0 : str.hashCode())) * 31) + zzyg.hashCode((Object[]) this.zzavo)) * 31;
        Boolean bool = this.zzavp;
        int hashCode4 = hashCode3 + (bool == null ? 0 : bool.hashCode());
        zzfl zzfl = this.zzavq;
        int i3 = hashCode4 * 31;
        if (zzfl == null) {
            i = 0;
        } else {
            i = zzfl.hashCode();
        }
        int i4 = (i3 + i) * 31;
        Boolean bool2 = this.zzavj;
        int hashCode5 = (i4 + (bool2 == null ? 0 : bool2.hashCode())) * 31;
        Boolean bool3 = this.zzavk;
        int hashCode6 = (hashCode5 + (bool3 == null ? 0 : bool3.hashCode())) * 31;
        if (this.zzcev != null && !this.zzcev.isEmpty()) {
            i2 = this.zzcev.hashCode();
        }
        return hashCode6 + i2;
    }

    public final void zza(zzya zzya) throws IOException {
        Integer num = this.zzavm;
        if (num != null) {
            zzya.zzd(1, num.intValue());
        }
        String str = this.zzavn;
        if (str != null) {
            zzya.zzb(2, str);
        }
        zzfk[] zzfkArr = this.zzavo;
        if (zzfkArr != null && zzfkArr.length > 0) {
            int i = 0;
            while (true) {
                zzfk[] zzfkArr2 = this.zzavo;
                if (i >= zzfkArr2.length) {
                    break;
                }
                zzfk zzfk = zzfkArr2[i];
                if (zzfk != null) {
                    zzya.zza(3, (zzyi) zzfk);
                }
                i++;
            }
        }
        Boolean bool = this.zzavp;
        if (bool != null) {
            zzya.zzb(4, bool.booleanValue());
        }
        zzfl zzfl = this.zzavq;
        if (zzfl != null) {
            zzya.zza(5, (zzyi) zzfl);
        }
        Boolean bool2 = this.zzavj;
        if (bool2 != null) {
            zzya.zzb(6, bool2.booleanValue());
        }
        Boolean bool3 = this.zzavk;
        if (bool3 != null) {
            zzya.zzb(7, bool3.booleanValue());
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
        String str = this.zzavn;
        if (str != null) {
            zzf += zzya.zzc(2, str);
        }
        zzfk[] zzfkArr = this.zzavo;
        if (zzfkArr != null && zzfkArr.length > 0) {
            int i = 0;
            while (true) {
                zzfk[] zzfkArr2 = this.zzavo;
                if (i >= zzfkArr2.length) {
                    break;
                }
                zzfk zzfk = zzfkArr2[i];
                if (zzfk != null) {
                    zzf += zzya.zzb(3, (zzyi) zzfk);
                }
                i++;
            }
        }
        Boolean bool = this.zzavp;
        if (bool != null) {
            bool.booleanValue();
            zzf += zzya.zzbd(4) + 1;
        }
        zzfl zzfl = this.zzavq;
        if (zzfl != null) {
            zzf += zzya.zzb(5, (zzyi) zzfl);
        }
        Boolean bool2 = this.zzavj;
        if (bool2 != null) {
            bool2.booleanValue();
            zzf += zzya.zzbd(6) + 1;
        }
        Boolean bool3 = this.zzavk;
        if (bool3 == null) {
            return zzf;
        }
        bool3.booleanValue();
        return zzf + zzya.zzbd(7) + 1;
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
                this.zzavn = zzxz.readString();
            } else if (zzuj == 26) {
                int zzb = zzyl.zzb(zzxz, 26);
                zzfk[] zzfkArr = this.zzavo;
                int length = zzfkArr == null ? 0 : zzfkArr.length;
                zzfk[] zzfkArr2 = new zzfk[(zzb + length)];
                if (length != 0) {
                    System.arraycopy(this.zzavo, 0, zzfkArr2, 0, length);
                }
                while (length < zzfkArr2.length - 1) {
                    zzfkArr2[length] = new zzfk();
                    zzxz.zza((zzyi) zzfkArr2[length]);
                    zzxz.zzuj();
                    length++;
                }
                zzfkArr2[length] = new zzfk();
                zzxz.zza((zzyi) zzfkArr2[length]);
                this.zzavo = zzfkArr2;
            } else if (zzuj == 32) {
                this.zzavp = Boolean.valueOf(zzxz.zzup());
            } else if (zzuj == 42) {
                if (this.zzavq == null) {
                    this.zzavq = new zzfl();
                }
                zzxz.zza((zzyi) this.zzavq);
            } else if (zzuj == 48) {
                this.zzavj = Boolean.valueOf(zzxz.zzup());
            } else if (zzuj == 56) {
                this.zzavk = Boolean.valueOf(zzxz.zzup());
            } else if (!super.zza(zzxz, zzuj)) {
                return this;
            }
        }
    }
}
