package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzft extends zzyc<zzft> {
    private static volatile zzft[] zzaxb;
    public Integer count;
    public String name;
    public zzfu[] zzaxc;
    public Long zzaxd;
    public Long zzaxe;

    public static zzft[] zzmz() {
        if (zzaxb == null) {
            synchronized (zzyg.zzcfe) {
                if (zzaxb == null) {
                    zzaxb = new zzft[0];
                }
            }
        }
        return zzaxb;
    }

    public zzft() {
        this.zzaxc = zzfu.zzna();
        this.name = null;
        this.zzaxd = null;
        this.zzaxe = null;
        this.count = null;
        this.zzcev = null;
        this.zzcff = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzft)) {
            return false;
        }
        zzft zzft = (zzft) obj;
        if (!zzyg.equals((Object[]) this.zzaxc, (Object[]) zzft.zzaxc)) {
            return false;
        }
        String str = this.name;
        if (str == null) {
            if (zzft.name != null) {
                return false;
            }
        } else if (!str.equals(zzft.name)) {
            return false;
        }
        Long l = this.zzaxd;
        if (l == null) {
            if (zzft.zzaxd != null) {
                return false;
            }
        } else if (!l.equals(zzft.zzaxd)) {
            return false;
        }
        Long l2 = this.zzaxe;
        if (l2 == null) {
            if (zzft.zzaxe != null) {
                return false;
            }
        } else if (!l2.equals(zzft.zzaxe)) {
            return false;
        }
        Integer num = this.count;
        if (num == null) {
            if (zzft.count != null) {
                return false;
            }
        } else if (!num.equals(zzft.count)) {
            return false;
        }
        if (this.zzcev == null || this.zzcev.isEmpty()) {
            return zzft.zzcev == null || zzft.zzcev.isEmpty();
        }
        return this.zzcev.equals(zzft.zzcev);
    }

    public final int hashCode() {
        int hashCode = (((getClass().getName().hashCode() + 527) * 31) + zzyg.hashCode((Object[]) this.zzaxc)) * 31;
        String str = this.name;
        int i = 0;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        Long l = this.zzaxd;
        int hashCode3 = (hashCode2 + (l == null ? 0 : l.hashCode())) * 31;
        Long l2 = this.zzaxe;
        int hashCode4 = (hashCode3 + (l2 == null ? 0 : l2.hashCode())) * 31;
        Integer num = this.count;
        int hashCode5 = (hashCode4 + (num == null ? 0 : num.hashCode())) * 31;
        if (this.zzcev != null && !this.zzcev.isEmpty()) {
            i = this.zzcev.hashCode();
        }
        return hashCode5 + i;
    }

    public final void zza(zzya zzya) throws IOException {
        zzfu[] zzfuArr = this.zzaxc;
        if (zzfuArr != null && zzfuArr.length > 0) {
            int i = 0;
            while (true) {
                zzfu[] zzfuArr2 = this.zzaxc;
                if (i >= zzfuArr2.length) {
                    break;
                }
                zzfu zzfu = zzfuArr2[i];
                if (zzfu != null) {
                    zzya.zza(1, (zzyi) zzfu);
                }
                i++;
            }
        }
        String str = this.name;
        if (str != null) {
            zzya.zzb(2, str);
        }
        Long l = this.zzaxd;
        if (l != null) {
            zzya.zzi(3, l.longValue());
        }
        Long l2 = this.zzaxe;
        if (l2 != null) {
            zzya.zzi(4, l2.longValue());
        }
        Integer num = this.count;
        if (num != null) {
            zzya.zzd(5, num.intValue());
        }
        super.zza(zzya);
    }

    /* access modifiers changed from: protected */
    public final int zzf() {
        int zzf = super.zzf();
        zzfu[] zzfuArr = this.zzaxc;
        if (zzfuArr != null && zzfuArr.length > 0) {
            int i = 0;
            while (true) {
                zzfu[] zzfuArr2 = this.zzaxc;
                if (i >= zzfuArr2.length) {
                    break;
                }
                zzfu zzfu = zzfuArr2[i];
                if (zzfu != null) {
                    zzf += zzya.zzb(1, (zzyi) zzfu);
                }
                i++;
            }
        }
        String str = this.name;
        if (str != null) {
            zzf += zzya.zzc(2, str);
        }
        Long l = this.zzaxd;
        if (l != null) {
            zzf += zzya.zzd(3, l.longValue());
        }
        Long l2 = this.zzaxe;
        if (l2 != null) {
            zzf += zzya.zzd(4, l2.longValue());
        }
        Integer num = this.count;
        return num != null ? zzf + zzya.zzh(5, num.intValue()) : zzf;
    }

    public final /* synthetic */ zzyi zza(zzxz zzxz) throws IOException {
        while (true) {
            int zzuj = zzxz.zzuj();
            if (zzuj == 0) {
                return this;
            }
            if (zzuj == 10) {
                int zzb = zzyl.zzb(zzxz, 10);
                zzfu[] zzfuArr = this.zzaxc;
                int length = zzfuArr == null ? 0 : zzfuArr.length;
                zzfu[] zzfuArr2 = new zzfu[(zzb + length)];
                if (length != 0) {
                    System.arraycopy(this.zzaxc, 0, zzfuArr2, 0, length);
                }
                while (length < zzfuArr2.length - 1) {
                    zzfuArr2[length] = new zzfu();
                    zzxz.zza((zzyi) zzfuArr2[length]);
                    zzxz.zzuj();
                    length++;
                }
                zzfuArr2[length] = new zzfu();
                zzxz.zza((zzyi) zzfuArr2[length]);
                this.zzaxc = zzfuArr2;
            } else if (zzuj == 18) {
                this.name = zzxz.readString();
            } else if (zzuj == 24) {
                this.zzaxd = Long.valueOf(zzxz.zzvc());
            } else if (zzuj == 32) {
                this.zzaxe = Long.valueOf(zzxz.zzvc());
            } else if (zzuj == 40) {
                this.count = Integer.valueOf(zzxz.zzvb());
            } else if (!super.zza(zzxz, zzuj)) {
                return this;
            }
        }
    }
}
