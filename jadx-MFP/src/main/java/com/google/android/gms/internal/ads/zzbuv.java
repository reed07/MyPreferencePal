package com.google.android.gms.internal.ads;

public final class zzbuv implements Cloneable {
    private static final zzbuw zzfwm = new zzbuw();
    private int mSize;
    private boolean zzfwn;
    private int[] zzfwo;
    private zzbuw[] zzfwp;

    zzbuv() {
        this(10);
    }

    private zzbuv(int i) {
        this.zzfwn = false;
        int idealIntArraySize = idealIntArraySize(i);
        this.zzfwo = new int[idealIntArraySize];
        this.zzfwp = new zzbuw[idealIntArraySize];
        this.mSize = 0;
    }

    /* access modifiers changed from: 0000 */
    public final zzbuw zzgf(int i) {
        int zzgh = zzgh(i);
        if (zzgh >= 0) {
            zzbuw[] zzbuwArr = this.zzfwp;
            if (zzbuwArr[zzgh] != zzfwm) {
                return zzbuwArr[zzgh];
            }
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    public final void zza(int i, zzbuw zzbuw) {
        int zzgh = zzgh(i);
        if (zzgh >= 0) {
            this.zzfwp[zzgh] = zzbuw;
            return;
        }
        int i2 = ~zzgh;
        if (i2 < this.mSize) {
            zzbuw[] zzbuwArr = this.zzfwp;
            if (zzbuwArr[i2] == zzfwm) {
                this.zzfwo[i2] = i;
                zzbuwArr[i2] = zzbuw;
                return;
            }
        }
        int i3 = this.mSize;
        if (i3 >= this.zzfwo.length) {
            int idealIntArraySize = idealIntArraySize(i3 + 1);
            int[] iArr = new int[idealIntArraySize];
            zzbuw[] zzbuwArr2 = new zzbuw[idealIntArraySize];
            int[] iArr2 = this.zzfwo;
            System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            zzbuw[] zzbuwArr3 = this.zzfwp;
            System.arraycopy(zzbuwArr3, 0, zzbuwArr2, 0, zzbuwArr3.length);
            this.zzfwo = iArr;
            this.zzfwp = zzbuwArr2;
        }
        int i4 = this.mSize;
        if (i4 - i2 != 0) {
            int[] iArr3 = this.zzfwo;
            int i5 = i2 + 1;
            System.arraycopy(iArr3, i2, iArr3, i5, i4 - i2);
            zzbuw[] zzbuwArr4 = this.zzfwp;
            System.arraycopy(zzbuwArr4, i2, zzbuwArr4, i5, this.mSize - i2);
        }
        this.zzfwo[i2] = i;
        this.zzfwp[i2] = zzbuw;
        this.mSize++;
    }

    /* access modifiers changed from: 0000 */
    public final int size() {
        return this.mSize;
    }

    /* access modifiers changed from: 0000 */
    public final zzbuw zzgg(int i) {
        return this.zzfwp[i];
    }

    public final boolean equals(Object obj) {
        boolean z;
        boolean z2;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzbuv)) {
            return false;
        }
        zzbuv zzbuv = (zzbuv) obj;
        int i = this.mSize;
        if (i != zzbuv.mSize) {
            return false;
        }
        int[] iArr = this.zzfwo;
        int[] iArr2 = zzbuv.zzfwo;
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
            zzbuw[] zzbuwArr = this.zzfwp;
            zzbuw[] zzbuwArr2 = zzbuv.zzfwp;
            int i3 = this.mSize;
            int i4 = 0;
            while (true) {
                if (i4 >= i3) {
                    z2 = true;
                    break;
                } else if (!zzbuwArr[i4].equals(zzbuwArr2[i4])) {
                    z2 = false;
                    break;
                } else {
                    i4++;
                }
            }
            if (z2) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int i = 17;
        for (int i2 = 0; i2 < this.mSize; i2++) {
            i = (((i * 31) + this.zzfwo[i2]) * 31) + this.zzfwp[i2].hashCode();
        }
        return i;
    }

    private static int idealIntArraySize(int i) {
        int i2 = i << 2;
        int i3 = 4;
        while (true) {
            if (i3 >= 32) {
                break;
            }
            int i4 = (1 << i3) - 12;
            if (i2 <= i4) {
                i2 = i4;
                break;
            }
            i3++;
        }
        return i2 / 4;
    }

    private final int zzgh(int i) {
        int i2 = this.mSize - 1;
        int i3 = 0;
        while (i3 <= i2) {
            int i4 = (i3 + i2) >>> 1;
            int i5 = this.zzfwo[i4];
            if (i5 < i) {
                i3 = i4 + 1;
            } else if (i5 <= i) {
                return i4;
            } else {
                i2 = i4 - 1;
            }
        }
        return ~i3;
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        int i = this.mSize;
        zzbuv zzbuv = new zzbuv(i);
        System.arraycopy(this.zzfwo, 0, zzbuv.zzfwo, 0, i);
        for (int i2 = 0; i2 < i; i2++) {
            zzbuw[] zzbuwArr = this.zzfwp;
            if (zzbuwArr[i2] != null) {
                zzbuv.zzfwp[i2] = (zzbuw) zzbuwArr[i2].clone();
            }
        }
        zzbuv.mSize = i;
        return zzbuv;
    }
}
