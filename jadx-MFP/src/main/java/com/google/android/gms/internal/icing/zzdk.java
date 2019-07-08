package com.google.android.gms.internal.icing;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzdk extends zzca<Integer> implements zzdq<Integer>, zzfb, RandomAccess {
    private static final zzdk zzko;
    private int size;
    private int[] zzkp;

    zzdk() {
        this(new int[10], 0);
    }

    private zzdk(int[] iArr, int i) {
        this.zzkp = iArr;
        this.size = i;
    }

    /* access modifiers changed from: protected */
    public final void removeRange(int i, int i2) {
        zzak();
        if (i2 >= i) {
            int[] iArr = this.zzkp;
            System.arraycopy(iArr, i2, iArr, i, this.size - i2);
            this.size -= i2 - i;
            this.modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzdk)) {
            return super.equals(obj);
        }
        zzdk zzdk = (zzdk) obj;
        if (this.size != zzdk.size) {
            return false;
        }
        int[] iArr = zzdk.zzkp;
        for (int i = 0; i < this.size; i++) {
            if (this.zzkp[i] != iArr[i]) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + this.zzkp[i2];
        }
        return i;
    }

    public final int getInt(int i) {
        zzh(i);
        return this.zzkp[i];
    }

    public final int size() {
        return this.size;
    }

    public final boolean addAll(Collection<? extends Integer> collection) {
        zzak();
        zzdl.checkNotNull(collection);
        if (!(collection instanceof zzdk)) {
            return super.addAll(collection);
        }
        zzdk zzdk = (zzdk) collection;
        int i = zzdk.size;
        if (i == 0) {
            return false;
        }
        int i2 = this.size;
        if (Integer.MAX_VALUE - i2 >= i) {
            int i3 = i2 + i;
            int[] iArr = this.zzkp;
            if (i3 > iArr.length) {
                this.zzkp = Arrays.copyOf(iArr, i3);
            }
            System.arraycopy(zzdk.zzkp, 0, this.zzkp, this.size, zzdk.size);
            this.size = i3;
            this.modCount++;
            return true;
        }
        throw new OutOfMemoryError();
    }

    public final boolean remove(Object obj) {
        zzak();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Integer.valueOf(this.zzkp[i]))) {
                int[] iArr = this.zzkp;
                System.arraycopy(iArr, i + 1, iArr, i, this.size - i);
                this.size--;
                this.modCount++;
                return true;
            }
        }
        return false;
    }

    private final void zzh(int i) {
        if (i < 0 || i >= this.size) {
            throw new IndexOutOfBoundsException(zzi(i));
        }
    }

    private final String zzi(int i) {
        int i2 = this.size;
        StringBuilder sb = new StringBuilder(35);
        sb.append("Index:");
        sb.append(i);
        sb.append(", Size:");
        sb.append(i2);
        return sb.toString();
    }

    public final /* synthetic */ Object set(int i, Object obj) {
        int intValue = ((Integer) obj).intValue();
        zzak();
        zzh(i);
        int[] iArr = this.zzkp;
        int i2 = iArr[i];
        iArr[i] = intValue;
        return Integer.valueOf(i2);
    }

    public final /* synthetic */ Object remove(int i) {
        zzak();
        zzh(i);
        int[] iArr = this.zzkp;
        int i2 = iArr[i];
        int i3 = this.size;
        if (i < i3 - 1) {
            System.arraycopy(iArr, i + 1, iArr, i, i3 - i);
        }
        this.size--;
        this.modCount++;
        return Integer.valueOf(i2);
    }

    public final /* synthetic */ void add(int i, Object obj) {
        int intValue = ((Integer) obj).intValue();
        zzak();
        if (i >= 0) {
            int i2 = this.size;
            if (i <= i2) {
                int[] iArr = this.zzkp;
                if (i2 < iArr.length) {
                    System.arraycopy(iArr, i, iArr, i + 1, i2 - i);
                } else {
                    int[] iArr2 = new int[(((i2 * 3) / 2) + 1)];
                    System.arraycopy(iArr, 0, iArr2, 0, i);
                    System.arraycopy(this.zzkp, i, iArr2, i + 1, this.size - i);
                    this.zzkp = iArr2;
                }
                this.zzkp[i] = intValue;
                this.size++;
                this.modCount++;
                return;
            }
        }
        throw new IndexOutOfBoundsException(zzi(i));
    }

    public final /* synthetic */ zzdq zzj(int i) {
        if (i >= this.size) {
            return new zzdk(Arrays.copyOf(this.zzkp, i), this.size);
        }
        throw new IllegalArgumentException();
    }

    public final /* synthetic */ Object get(int i) {
        return Integer.valueOf(getInt(i));
    }

    static {
        zzdk zzdk = new zzdk();
        zzko = zzdk;
        zzdk.zzaj();
    }
}
