package com.google.android.gms.internal.icing;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzcw extends zzca<Double> implements zzdq<Double>, zzfb, RandomAccess {
    private static final zzcw zzgp;
    private int size;
    private double[] zzgq;

    public static zzcw zzaz() {
        return zzgp;
    }

    zzcw() {
        this(new double[10], 0);
    }

    private zzcw(double[] dArr, int i) {
        this.zzgq = dArr;
        this.size = i;
    }

    /* access modifiers changed from: protected */
    public final void removeRange(int i, int i2) {
        zzak();
        if (i2 >= i) {
            double[] dArr = this.zzgq;
            System.arraycopy(dArr, i2, dArr, i, this.size - i2);
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
        if (!(obj instanceof zzcw)) {
            return super.equals(obj);
        }
        zzcw zzcw = (zzcw) obj;
        if (this.size != zzcw.size) {
            return false;
        }
        double[] dArr = zzcw.zzgq;
        for (int i = 0; i < this.size; i++) {
            if (Double.doubleToLongBits(this.zzgq[i]) != Double.doubleToLongBits(dArr[i])) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + zzdl.zzk(Double.doubleToLongBits(this.zzgq[i2]));
        }
        return i;
    }

    public final int size() {
        return this.size;
    }

    public final boolean addAll(Collection<? extends Double> collection) {
        zzak();
        zzdl.checkNotNull(collection);
        if (!(collection instanceof zzcw)) {
            return super.addAll(collection);
        }
        zzcw zzcw = (zzcw) collection;
        int i = zzcw.size;
        if (i == 0) {
            return false;
        }
        int i2 = this.size;
        if (Integer.MAX_VALUE - i2 >= i) {
            int i3 = i2 + i;
            double[] dArr = this.zzgq;
            if (i3 > dArr.length) {
                this.zzgq = Arrays.copyOf(dArr, i3);
            }
            System.arraycopy(zzcw.zzgq, 0, this.zzgq, this.size, zzcw.size);
            this.size = i3;
            this.modCount++;
            return true;
        }
        throw new OutOfMemoryError();
    }

    public final boolean remove(Object obj) {
        zzak();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Double.valueOf(this.zzgq[i]))) {
                double[] dArr = this.zzgq;
                System.arraycopy(dArr, i + 1, dArr, i, this.size - i);
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
        double doubleValue = ((Double) obj).doubleValue();
        zzak();
        zzh(i);
        double[] dArr = this.zzgq;
        double d = dArr[i];
        dArr[i] = doubleValue;
        return Double.valueOf(d);
    }

    public final /* synthetic */ Object remove(int i) {
        zzak();
        zzh(i);
        double[] dArr = this.zzgq;
        double d = dArr[i];
        int i2 = this.size;
        if (i < i2 - 1) {
            System.arraycopy(dArr, i + 1, dArr, i, i2 - i);
        }
        this.size--;
        this.modCount++;
        return Double.valueOf(d);
    }

    public final /* synthetic */ void add(int i, Object obj) {
        double doubleValue = ((Double) obj).doubleValue();
        zzak();
        if (i >= 0) {
            int i2 = this.size;
            if (i <= i2) {
                double[] dArr = this.zzgq;
                if (i2 < dArr.length) {
                    System.arraycopy(dArr, i, dArr, i + 1, i2 - i);
                } else {
                    double[] dArr2 = new double[(((i2 * 3) / 2) + 1)];
                    System.arraycopy(dArr, 0, dArr2, 0, i);
                    System.arraycopy(this.zzgq, i, dArr2, i + 1, this.size - i);
                    this.zzgq = dArr2;
                }
                this.zzgq[i] = doubleValue;
                this.size++;
                this.modCount++;
                return;
            }
        }
        throw new IndexOutOfBoundsException(zzi(i));
    }

    public final /* synthetic */ zzdq zzj(int i) {
        if (i >= this.size) {
            return new zzcw(Arrays.copyOf(this.zzgq, i), this.size);
        }
        throw new IllegalArgumentException();
    }

    public final /* synthetic */ Object get(int i) {
        zzh(i);
        return Double.valueOf(this.zzgq[i]);
    }

    static {
        zzcw zzcw = new zzcw();
        zzgp = zzcw;
        zzcw.zzaj();
    }
}
