package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzfn extends zzyc<zzfn> {
    public Integer zzawe;
    public String zzawf;
    public Boolean zzawg;
    public String[] zzawh;

    public zzfn() {
        this.zzawe = null;
        this.zzawf = null;
        this.zzawg = null;
        this.zzawh = zzyl.zzcfo;
        this.zzcev = null;
        this.zzcff = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzfn)) {
            return false;
        }
        zzfn zzfn = (zzfn) obj;
        Integer num = this.zzawe;
        if (num == null) {
            if (zzfn.zzawe != null) {
                return false;
            }
        } else if (!num.equals(zzfn.zzawe)) {
            return false;
        }
        String str = this.zzawf;
        if (str == null) {
            if (zzfn.zzawf != null) {
                return false;
            }
        } else if (!str.equals(zzfn.zzawf)) {
            return false;
        }
        Boolean bool = this.zzawg;
        if (bool == null) {
            if (zzfn.zzawg != null) {
                return false;
            }
        } else if (!bool.equals(zzfn.zzawg)) {
            return false;
        }
        if (!zzyg.equals((Object[]) this.zzawh, (Object[]) zzfn.zzawh)) {
            return false;
        }
        if (this.zzcev == null || this.zzcev.isEmpty()) {
            return zzfn.zzcev == null || zzfn.zzcev.isEmpty();
        }
        return this.zzcev.equals(zzfn.zzcev);
    }

    public final int hashCode() {
        int hashCode = (getClass().getName().hashCode() + 527) * 31;
        Integer num = this.zzawe;
        int i = 0;
        int intValue = (hashCode + (num == null ? 0 : num.intValue())) * 31;
        String str = this.zzawf;
        int hashCode2 = (intValue + (str == null ? 0 : str.hashCode())) * 31;
        Boolean bool = this.zzawg;
        int hashCode3 = (((hashCode2 + (bool == null ? 0 : bool.hashCode())) * 31) + zzyg.hashCode((Object[]) this.zzawh)) * 31;
        if (this.zzcev != null && !this.zzcev.isEmpty()) {
            i = this.zzcev.hashCode();
        }
        return hashCode3 + i;
    }

    public final void zza(zzya zzya) throws IOException {
        Integer num = this.zzawe;
        if (num != null) {
            zzya.zzd(1, num.intValue());
        }
        String str = this.zzawf;
        if (str != null) {
            zzya.zzb(2, str);
        }
        Boolean bool = this.zzawg;
        if (bool != null) {
            zzya.zzb(3, bool.booleanValue());
        }
        String[] strArr = this.zzawh;
        if (strArr != null && strArr.length > 0) {
            int i = 0;
            while (true) {
                String[] strArr2 = this.zzawh;
                if (i >= strArr2.length) {
                    break;
                }
                String str2 = strArr2[i];
                if (str2 != null) {
                    zzya.zzb(4, str2);
                }
                i++;
            }
        }
        super.zza(zzya);
    }

    /* access modifiers changed from: protected */
    public final int zzf() {
        int zzf = super.zzf();
        Integer num = this.zzawe;
        if (num != null) {
            zzf += zzya.zzh(1, num.intValue());
        }
        String str = this.zzawf;
        if (str != null) {
            zzf += zzya.zzc(2, str);
        }
        Boolean bool = this.zzawg;
        if (bool != null) {
            bool.booleanValue();
            zzf += zzya.zzbd(3) + 1;
        }
        String[] strArr = this.zzawh;
        if (strArr == null || strArr.length <= 0) {
            return zzf;
        }
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            String[] strArr2 = this.zzawh;
            if (i >= strArr2.length) {
                return zzf + i2 + (i3 * 1);
            }
            String str2 = strArr2[i];
            if (str2 != null) {
                i3++;
                i2 += zzya.zzgc(str2);
            }
            i++;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: zzd */
    public final zzfn zza(zzxz zzxz) throws IOException {
        int zzvb;
        while (true) {
            int zzuj = zzxz.zzuj();
            if (zzuj == 0) {
                return this;
            }
            if (zzuj == 8) {
                try {
                    zzvb = zzxz.zzvb();
                    if (zzvb < 0 || zzvb > 6) {
                        StringBuilder sb = new StringBuilder(41);
                        sb.append(zzvb);
                        sb.append(" is not a valid enum MatchType");
                    } else {
                        this.zzawe = Integer.valueOf(zzvb);
                    }
                } catch (IllegalArgumentException unused) {
                    zzxz.zzcb(zzxz.getPosition());
                    zza(zzxz, zzuj);
                }
            } else if (zzuj == 18) {
                this.zzawf = zzxz.readString();
            } else if (zzuj == 24) {
                this.zzawg = Boolean.valueOf(zzxz.zzup());
            } else if (zzuj == 34) {
                int zzb = zzyl.zzb(zzxz, 34);
                String[] strArr = this.zzawh;
                int length = strArr == null ? 0 : strArr.length;
                String[] strArr2 = new String[(zzb + length)];
                if (length != 0) {
                    System.arraycopy(this.zzawh, 0, strArr2, 0, length);
                }
                while (length < strArr2.length - 1) {
                    strArr2[length] = zzxz.readString();
                    zzxz.zzuj();
                    length++;
                }
                strArr2[length] = zzxz.readString();
                this.zzawh = strArr2;
            } else if (!super.zza(zzxz, zzuj)) {
                return this;
            }
        }
        StringBuilder sb2 = new StringBuilder(41);
        sb2.append(zzvb);
        sb2.append(" is not a valid enum MatchType");
        throw new IllegalArgumentException(sb2.toString());
    }
}
