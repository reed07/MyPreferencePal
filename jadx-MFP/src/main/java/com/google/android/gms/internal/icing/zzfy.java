package com.google.android.gms.internal.icing;

import com.google.android.gms.internal.icing.zzdj.zzd;
import java.io.IOException;
import java.util.Arrays;

public final class zzfy {
    private static final zzfy zznu = new zzfy(0, new int[0], new Object[0], false);
    private int count;
    private boolean zzfs;
    private int zzju;
    private Object[] zzmg;
    private int[] zznv;

    public static zzfy zzdp() {
        return zznu;
    }

    static zzfy zza(zzfy zzfy, zzfy zzfy2) {
        int i = zzfy.count + zzfy2.count;
        int[] copyOf = Arrays.copyOf(zzfy.zznv, i);
        System.arraycopy(zzfy2.zznv, 0, copyOf, zzfy.count, zzfy2.count);
        Object[] copyOf2 = Arrays.copyOf(zzfy.zzmg, i);
        System.arraycopy(zzfy2.zzmg, 0, copyOf2, zzfy.count, zzfy2.count);
        return new zzfy(i, copyOf, copyOf2, true);
    }

    private zzfy() {
        this(0, new int[8], new Object[8], true);
    }

    private zzfy(int i, int[] iArr, Object[] objArr, boolean z) {
        this.zzju = -1;
        this.count = i;
        this.zznv = iArr;
        this.zzmg = objArr;
        this.zzfs = z;
    }

    public final void zzaj() {
        this.zzfs = false;
    }

    /* access modifiers changed from: 0000 */
    public final void zza(zzgr zzgr) throws IOException {
        if (zzgr.zzay() == zzd.zzkm) {
            for (int i = this.count - 1; i >= 0; i--) {
                zzgr.zza(this.zznv[i] >>> 3, this.zzmg[i]);
            }
            return;
        }
        for (int i2 = 0; i2 < this.count; i2++) {
            zzgr.zza(this.zznv[i2] >>> 3, this.zzmg[i2]);
        }
    }

    public final void zzb(zzgr zzgr) throws IOException {
        if (this.count != 0) {
            if (zzgr.zzay() == zzd.zzkl) {
                for (int i = 0; i < this.count; i++) {
                    zzb(this.zznv[i], this.zzmg[i], zzgr);
                }
                return;
            }
            for (int i2 = this.count - 1; i2 >= 0; i2--) {
                zzb(this.zznv[i2], this.zzmg[i2], zzgr);
            }
        }
    }

    private static void zzb(int i, Object obj, zzgr zzgr) throws IOException {
        int i2 = i >>> 3;
        int i3 = i & 7;
        if (i3 != 5) {
            switch (i3) {
                case 0:
                    zzgr.zzi(i2, ((Long) obj).longValue());
                    return;
                case 1:
                    zzgr.zzc(i2, ((Long) obj).longValue());
                    return;
                case 2:
                    zzgr.zza(i2, (zzce) obj);
                    return;
                case 3:
                    if (zzgr.zzay() == zzd.zzkl) {
                        zzgr.zzab(i2);
                        ((zzfy) obj).zzb(zzgr);
                        zzgr.zzac(i2);
                        return;
                    }
                    zzgr.zzac(i2);
                    ((zzfy) obj).zzb(zzgr);
                    zzgr.zzab(i2);
                    return;
                default:
                    throw new RuntimeException(zzdr.zzcc());
            }
        } else {
            zzgr.zzf(i2, ((Integer) obj).intValue());
        }
    }

    public final int zzdq() {
        int i = this.zzju;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.count; i3++) {
            i2 += zzct.zzd(this.zznv[i3] >>> 3, (zzce) this.zzmg[i3]);
        }
        this.zzju = i2;
        return i2;
    }

    public final int zzbi() {
        int i;
        int i2 = this.zzju;
        if (i2 != -1) {
            return i2;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < this.count; i4++) {
            int i5 = this.zznv[i4];
            int i6 = i5 >>> 3;
            int i7 = i5 & 7;
            if (i7 != 5) {
                switch (i7) {
                    case 0:
                        i = zzct.zze(i6, ((Long) this.zzmg[i4]).longValue());
                        break;
                    case 1:
                        i = zzct.zzg(i6, ((Long) this.zzmg[i4]).longValue());
                        break;
                    case 2:
                        i = zzct.zzc(i6, (zzce) this.zzmg[i4]);
                        break;
                    case 3:
                        i = (zzct.zzs(i6) << 1) + ((zzfy) this.zzmg[i4]).zzbi();
                        break;
                    default:
                        throw new IllegalStateException(zzdr.zzcc());
                }
            } else {
                i = zzct.zzj(i6, ((Integer) this.zzmg[i4]).intValue());
            }
            i3 += i;
        }
        this.zzju = i3;
        return i3;
    }

    public final boolean equals(Object obj) {
        boolean z;
        boolean z2;
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof zzfy)) {
            return false;
        }
        zzfy zzfy = (zzfy) obj;
        int i = this.count;
        if (i == zzfy.count) {
            int[] iArr = this.zznv;
            int[] iArr2 = zzfy.zznv;
            int i2 = 0;
            while (true) {
                if (i2 >= i) {
                    z = true;
                    break;
                } else if (iArr[i2] != iArr2[i2]) {
                    z = false;
                    break;
                } else {
                    i2++;
                }
            }
            if (z) {
                Object[] objArr = this.zzmg;
                Object[] objArr2 = zzfy.zzmg;
                int i3 = this.count;
                int i4 = 0;
                while (true) {
                    if (i4 >= i3) {
                        z2 = true;
                        break;
                    } else if (!objArr[i4].equals(objArr2[i4])) {
                        z2 = false;
                        break;
                    } else {
                        i4++;
                    }
                }
                return z2;
            }
        }
    }

    public final int hashCode() {
        int i = this.count;
        int i2 = (i + 527) * 31;
        int[] iArr = this.zznv;
        int i3 = 17;
        int i4 = 17;
        for (int i5 = 0; i5 < i; i5++) {
            i4 = (i4 * 31) + iArr[i5];
        }
        int i6 = (i2 + i4) * 31;
        Object[] objArr = this.zzmg;
        for (int i7 = 0; i7 < this.count; i7++) {
            i3 = (i3 * 31) + objArr[i7].hashCode();
        }
        return i6 + i3;
    }

    /* access modifiers changed from: 0000 */
    public final void zza(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < this.count; i2++) {
            zzet.zza(sb, i, String.valueOf(this.zznv[i2] >>> 3), this.zzmg[i2]);
        }
    }
}
