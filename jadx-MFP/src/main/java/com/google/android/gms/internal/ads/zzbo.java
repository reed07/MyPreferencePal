package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbo extends zzbut<zzbo> {
    private Long zzhh;
    private Integer zzhi;
    private Boolean zzhj;
    private int[] zzhk;
    private Long zzhl;

    public zzbo() {
        this.zzhh = null;
        this.zzhi = null;
        this.zzhj = null;
        this.zzhk = zzbvc.zzfsg;
        this.zzhl = null;
        this.zzfwt = -1;
    }

    public final void zza(zzbur zzbur) throws IOException {
        Long l = this.zzhh;
        if (l != null) {
            zzbur.zzr(1, l.longValue());
        }
        Integer num = this.zzhi;
        if (num != null) {
            zzbur.zzv(2, num.intValue());
        }
        Boolean bool = this.zzhj;
        if (bool != null) {
            zzbur.zzj(3, bool.booleanValue());
        }
        int[] iArr = this.zzhk;
        if (iArr != null && iArr.length > 0) {
            int i = 0;
            while (true) {
                int[] iArr2 = this.zzhk;
                if (i >= iArr2.length) {
                    break;
                }
                zzbur.zzv(4, iArr2[i]);
                i++;
            }
        }
        Long l2 = this.zzhl;
        if (l2 != null) {
            zzbur.zzj(5, l2.longValue());
        }
        super.zza(zzbur);
    }

    /* access modifiers changed from: protected */
    public final int zzt() {
        int[] iArr;
        int zzt = super.zzt();
        Long l = this.zzhh;
        if (l != null) {
            zzt += zzbur.zzm(1, l.longValue());
        }
        Integer num = this.zzhi;
        if (num != null) {
            zzt += zzbur.zzz(2, num.intValue());
        }
        Boolean bool = this.zzhj;
        if (bool != null) {
            bool.booleanValue();
            zzt += zzbur.zzfd(3) + 1;
        }
        int[] iArr2 = this.zzhk;
        if (iArr2 != null && iArr2.length > 0) {
            int i = 0;
            int i2 = 0;
            while (true) {
                iArr = this.zzhk;
                if (i >= iArr.length) {
                    break;
                }
                i2 += zzbur.zzfe(iArr[i]);
                i++;
            }
            zzt = zzt + i2 + (iArr.length * 1);
        }
        Long l2 = this.zzhl;
        return l2 != null ? zzt + zzbur.zzn(5, l2.longValue()) : zzt;
    }

    public final /* synthetic */ zzbuz zza(zzbuq zzbuq) throws IOException {
        while (true) {
            int zzaku = zzbuq.zzaku();
            if (zzaku == 0) {
                return this;
            }
            if (zzaku == 8) {
                this.zzhh = Long.valueOf(zzbuq.zzaln());
            } else if (zzaku == 16) {
                this.zzhi = Integer.valueOf(zzbuq.zzalm());
            } else if (zzaku == 24) {
                this.zzhj = Boolean.valueOf(zzbuq.zzala());
            } else if (zzaku == 32) {
                int zzb = zzbvc.zzb(zzbuq, 32);
                int[] iArr = this.zzhk;
                int length = iArr == null ? 0 : iArr.length;
                int[] iArr2 = new int[(zzb + length)];
                if (length != 0) {
                    System.arraycopy(this.zzhk, 0, iArr2, 0, length);
                }
                while (length < iArr2.length - 1) {
                    iArr2[length] = zzbuq.zzalm();
                    zzbuq.zzaku();
                    length++;
                }
                iArr2[length] = zzbuq.zzalm();
                this.zzhk = iArr2;
            } else if (zzaku == 34) {
                int zzer = zzbuq.zzer(zzbuq.zzalm());
                int position = zzbuq.getPosition();
                int i = 0;
                while (zzbuq.zzapl() > 0) {
                    zzbuq.zzalm();
                    i++;
                }
                zzbuq.zzgc(position);
                int[] iArr3 = this.zzhk;
                int length2 = iArr3 == null ? 0 : iArr3.length;
                int[] iArr4 = new int[(i + length2)];
                if (length2 != 0) {
                    System.arraycopy(this.zzhk, 0, iArr4, 0, length2);
                }
                while (length2 < iArr4.length) {
                    iArr4[length2] = zzbuq.zzalm();
                    length2++;
                }
                this.zzhk = iArr4;
                zzbuq.zzes(zzer);
            } else if (zzaku == 40) {
                this.zzhl = Long.valueOf(zzbuq.zzaln());
            } else if (!super.zza(zzbuq, zzaku)) {
                return this;
            }
        }
    }
}
