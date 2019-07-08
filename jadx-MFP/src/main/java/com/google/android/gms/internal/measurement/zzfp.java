package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzfp extends zzyc<zzfp> {
    public String zzafi;
    public Long zzawm;
    private Integer zzawn;
    public zzfq[] zzawo;
    public zzfo[] zzawp;
    public zzfi[] zzawq;
    private String zzawr;
    private Boolean zzaws;

    public zzfp() {
        this.zzawm = null;
        this.zzafi = null;
        this.zzawn = null;
        this.zzawo = zzfq.zzmw();
        this.zzawp = zzfo.zzmv();
        this.zzawq = zzfi.zzmr();
        this.zzawr = null;
        this.zzaws = null;
        this.zzcev = null;
        this.zzcff = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzfp)) {
            return false;
        }
        zzfp zzfp = (zzfp) obj;
        Long l = this.zzawm;
        if (l == null) {
            if (zzfp.zzawm != null) {
                return false;
            }
        } else if (!l.equals(zzfp.zzawm)) {
            return false;
        }
        String str = this.zzafi;
        if (str == null) {
            if (zzfp.zzafi != null) {
                return false;
            }
        } else if (!str.equals(zzfp.zzafi)) {
            return false;
        }
        Integer num = this.zzawn;
        if (num == null) {
            if (zzfp.zzawn != null) {
                return false;
            }
        } else if (!num.equals(zzfp.zzawn)) {
            return false;
        }
        if (!zzyg.equals((Object[]) this.zzawo, (Object[]) zzfp.zzawo) || !zzyg.equals((Object[]) this.zzawp, (Object[]) zzfp.zzawp) || !zzyg.equals((Object[]) this.zzawq, (Object[]) zzfp.zzawq)) {
            return false;
        }
        String str2 = this.zzawr;
        if (str2 == null) {
            if (zzfp.zzawr != null) {
                return false;
            }
        } else if (!str2.equals(zzfp.zzawr)) {
            return false;
        }
        Boolean bool = this.zzaws;
        if (bool == null) {
            if (zzfp.zzaws != null) {
                return false;
            }
        } else if (!bool.equals(zzfp.zzaws)) {
            return false;
        }
        if (this.zzcev == null || this.zzcev.isEmpty()) {
            return zzfp.zzcev == null || zzfp.zzcev.isEmpty();
        }
        return this.zzcev.equals(zzfp.zzcev);
    }

    public final int hashCode() {
        int hashCode = (getClass().getName().hashCode() + 527) * 31;
        Long l = this.zzawm;
        int i = 0;
        int hashCode2 = (hashCode + (l == null ? 0 : l.hashCode())) * 31;
        String str = this.zzafi;
        int hashCode3 = (hashCode2 + (str == null ? 0 : str.hashCode())) * 31;
        Integer num = this.zzawn;
        int hashCode4 = (((((((hashCode3 + (num == null ? 0 : num.hashCode())) * 31) + zzyg.hashCode((Object[]) this.zzawo)) * 31) + zzyg.hashCode((Object[]) this.zzawp)) * 31) + zzyg.hashCode((Object[]) this.zzawq)) * 31;
        String str2 = this.zzawr;
        int hashCode5 = (hashCode4 + (str2 == null ? 0 : str2.hashCode())) * 31;
        Boolean bool = this.zzaws;
        int hashCode6 = (hashCode5 + (bool == null ? 0 : bool.hashCode())) * 31;
        if (this.zzcev != null && !this.zzcev.isEmpty()) {
            i = this.zzcev.hashCode();
        }
        return hashCode6 + i;
    }

    public final void zza(zzya zzya) throws IOException {
        Long l = this.zzawm;
        if (l != null) {
            zzya.zzi(1, l.longValue());
        }
        String str = this.zzafi;
        if (str != null) {
            zzya.zzb(2, str);
        }
        Integer num = this.zzawn;
        if (num != null) {
            zzya.zzd(3, num.intValue());
        }
        zzfq[] zzfqArr = this.zzawo;
        int i = 0;
        if (zzfqArr != null && zzfqArr.length > 0) {
            int i2 = 0;
            while (true) {
                zzfq[] zzfqArr2 = this.zzawo;
                if (i2 >= zzfqArr2.length) {
                    break;
                }
                zzfq zzfq = zzfqArr2[i2];
                if (zzfq != null) {
                    zzya.zza(4, (zzyi) zzfq);
                }
                i2++;
            }
        }
        zzfo[] zzfoArr = this.zzawp;
        if (zzfoArr != null && zzfoArr.length > 0) {
            int i3 = 0;
            while (true) {
                zzfo[] zzfoArr2 = this.zzawp;
                if (i3 >= zzfoArr2.length) {
                    break;
                }
                zzfo zzfo = zzfoArr2[i3];
                if (zzfo != null) {
                    zzya.zza(5, (zzyi) zzfo);
                }
                i3++;
            }
        }
        zzfi[] zzfiArr = this.zzawq;
        if (zzfiArr != null && zzfiArr.length > 0) {
            while (true) {
                zzfi[] zzfiArr2 = this.zzawq;
                if (i >= zzfiArr2.length) {
                    break;
                }
                zzfi zzfi = zzfiArr2[i];
                if (zzfi != null) {
                    zzya.zza(6, (zzyi) zzfi);
                }
                i++;
            }
        }
        String str2 = this.zzawr;
        if (str2 != null) {
            zzya.zzb(7, str2);
        }
        Boolean bool = this.zzaws;
        if (bool != null) {
            zzya.zzb(8, bool.booleanValue());
        }
        super.zza(zzya);
    }

    /* access modifiers changed from: protected */
    public final int zzf() {
        int zzf = super.zzf();
        Long l = this.zzawm;
        if (l != null) {
            zzf += zzya.zzd(1, l.longValue());
        }
        String str = this.zzafi;
        if (str != null) {
            zzf += zzya.zzc(2, str);
        }
        Integer num = this.zzawn;
        if (num != null) {
            zzf += zzya.zzh(3, num.intValue());
        }
        zzfq[] zzfqArr = this.zzawo;
        int i = 0;
        if (zzfqArr != null && zzfqArr.length > 0) {
            int i2 = zzf;
            int i3 = 0;
            while (true) {
                zzfq[] zzfqArr2 = this.zzawo;
                if (i3 >= zzfqArr2.length) {
                    break;
                }
                zzfq zzfq = zzfqArr2[i3];
                if (zzfq != null) {
                    i2 += zzya.zzb(4, (zzyi) zzfq);
                }
                i3++;
            }
            zzf = i2;
        }
        zzfo[] zzfoArr = this.zzawp;
        if (zzfoArr != null && zzfoArr.length > 0) {
            int i4 = zzf;
            int i5 = 0;
            while (true) {
                zzfo[] zzfoArr2 = this.zzawp;
                if (i5 >= zzfoArr2.length) {
                    break;
                }
                zzfo zzfo = zzfoArr2[i5];
                if (zzfo != null) {
                    i4 += zzya.zzb(5, (zzyi) zzfo);
                }
                i5++;
            }
            zzf = i4;
        }
        zzfi[] zzfiArr = this.zzawq;
        if (zzfiArr != null && zzfiArr.length > 0) {
            while (true) {
                zzfi[] zzfiArr2 = this.zzawq;
                if (i >= zzfiArr2.length) {
                    break;
                }
                zzfi zzfi = zzfiArr2[i];
                if (zzfi != null) {
                    zzf += zzya.zzb(6, (zzyi) zzfi);
                }
                i++;
            }
        }
        String str2 = this.zzawr;
        if (str2 != null) {
            zzf += zzya.zzc(7, str2);
        }
        Boolean bool = this.zzaws;
        if (bool == null) {
            return zzf;
        }
        bool.booleanValue();
        return zzf + zzya.zzbd(8) + 1;
    }

    public final /* synthetic */ zzyi zza(zzxz zzxz) throws IOException {
        while (true) {
            int zzuj = zzxz.zzuj();
            if (zzuj == 0) {
                return this;
            }
            if (zzuj == 8) {
                this.zzawm = Long.valueOf(zzxz.zzvc());
            } else if (zzuj == 18) {
                this.zzafi = zzxz.readString();
            } else if (zzuj == 24) {
                this.zzawn = Integer.valueOf(zzxz.zzvb());
            } else if (zzuj == 34) {
                int zzb = zzyl.zzb(zzxz, 34);
                zzfq[] zzfqArr = this.zzawo;
                int length = zzfqArr == null ? 0 : zzfqArr.length;
                zzfq[] zzfqArr2 = new zzfq[(zzb + length)];
                if (length != 0) {
                    System.arraycopy(this.zzawo, 0, zzfqArr2, 0, length);
                }
                while (length < zzfqArr2.length - 1) {
                    zzfqArr2[length] = new zzfq();
                    zzxz.zza((zzyi) zzfqArr2[length]);
                    zzxz.zzuj();
                    length++;
                }
                zzfqArr2[length] = new zzfq();
                zzxz.zza((zzyi) zzfqArr2[length]);
                this.zzawo = zzfqArr2;
            } else if (zzuj == 42) {
                int zzb2 = zzyl.zzb(zzxz, 42);
                zzfo[] zzfoArr = this.zzawp;
                int length2 = zzfoArr == null ? 0 : zzfoArr.length;
                zzfo[] zzfoArr2 = new zzfo[(zzb2 + length2)];
                if (length2 != 0) {
                    System.arraycopy(this.zzawp, 0, zzfoArr2, 0, length2);
                }
                while (length2 < zzfoArr2.length - 1) {
                    zzfoArr2[length2] = new zzfo();
                    zzxz.zza((zzyi) zzfoArr2[length2]);
                    zzxz.zzuj();
                    length2++;
                }
                zzfoArr2[length2] = new zzfo();
                zzxz.zza((zzyi) zzfoArr2[length2]);
                this.zzawp = zzfoArr2;
            } else if (zzuj == 50) {
                int zzb3 = zzyl.zzb(zzxz, 50);
                zzfi[] zzfiArr = this.zzawq;
                int length3 = zzfiArr == null ? 0 : zzfiArr.length;
                zzfi[] zzfiArr2 = new zzfi[(zzb3 + length3)];
                if (length3 != 0) {
                    System.arraycopy(this.zzawq, 0, zzfiArr2, 0, length3);
                }
                while (length3 < zzfiArr2.length - 1) {
                    zzfiArr2[length3] = new zzfi();
                    zzxz.zza((zzyi) zzfiArr2[length3]);
                    zzxz.zzuj();
                    length3++;
                }
                zzfiArr2[length3] = new zzfi();
                zzxz.zza((zzyi) zzfiArr2[length3]);
                this.zzawq = zzfiArr2;
            } else if (zzuj == 58) {
                this.zzawr = zzxz.readString();
            } else if (zzuj == 64) {
                this.zzaws = Boolean.valueOf(zzxz.zzup());
            } else if (!super.zza(zzxz, zzuj)) {
                return this;
            }
        }
    }
}
