package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzbqn extends zzbpo<Double> implements zzbrk<Double>, zzbsx, RandomAccess {
    private static final zzbqn zzfmn;
    private int size;
    private double[] zzfmo;

    zzbqn() {
        this(new double[10], 0);
    }

    private zzbqn(double[] dArr, int i) {
        this.zzfmo = dArr;
        this.size = i;
    }

    /* access modifiers changed from: protected */
    public final void removeRange(int i, int i2) {
        zzakk();
        if (i2 >= i) {
            double[] dArr = this.zzfmo;
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
        if (!(obj instanceof zzbqn)) {
            return super.equals(obj);
        }
        zzbqn zzbqn = (zzbqn) obj;
        if (this.size != zzbqn.size) {
            return false;
        }
        double[] dArr = zzbqn.zzfmo;
        for (int i = 0; i < this.size; i++) {
            if (this.zzfmo[i] != dArr[i]) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + zzbrf.zzbi(Double.doubleToLongBits(this.zzfmo[i2]));
        }
        return i;
    }

    public final int size() {
        return this.size;
    }

    public final void zzd(double d) {
        zzd(this.size, d);
    }

    private final void zzd(int i, double d) {
        zzakk();
        if (i >= 0) {
            int i2 = this.size;
            if (i <= i2) {
                double[] dArr = this.zzfmo;
                if (i2 < dArr.length) {
                    System.arraycopy(dArr, i, dArr, i + 1, i2 - i);
                } else {
                    double[] dArr2 = new double[(((i2 * 3) / 2) + 1)];
                    System.arraycopy(dArr, 0, dArr2, 0, i);
                    System.arraycopy(this.zzfmo, i, dArr2, i + 1, this.size - i);
                    this.zzfmo = dArr2;
                }
                this.zzfmo[i] = d;
                this.size++;
                this.modCount++;
                return;
            }
        }
        throw new IndexOutOfBoundsException(zzek(i));
    }

    public final boolean addAll(Collection<? extends Double> collection) {
        zzakk();
        zzbrf.checkNotNull(collection);
        if (!(collection instanceof zzbqn)) {
            return super.addAll(collection);
        }
        zzbqn zzbqn = (zzbqn) collection;
        int i = zzbqn.size;
        if (i == 0) {
            return false;
        }
        int i2 = this.size;
        if (Integer.MAX_VALUE - i2 >= i) {
            int i3 = i2 + i;
            double[] dArr = this.zzfmo;
            if (i3 > dArr.length) {
                this.zzfmo = Arrays.copyOf(dArr, i3);
            }
            System.arraycopy(zzbqn.zzfmo, 0, this.zzfmo, this.size, zzbqn.size);
            this.size = i3;
            this.modCount++;
            return true;
        }
        throw new OutOfMemoryError();
    }

    public final boolean remove(Object obj) {
        zzakk();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Double.valueOf(this.zzfmo[i]))) {
                double[] dArr = this.zzfmo;
                System.arraycopy(dArr, i + 1, dArr, i, this.size - i);
                this.size--;
                this.modCount++;
                return true;
            }
        }
        return false;
    }

    private final void zzej(int i) {
        if (i < 0 || i >= this.size) {
            throw new IndexOutOfBoundsException(zzek(i));
        }
    }

    private final String zzek(int i) {
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
        zzakk();
        zzej(i);
        double[] dArr = this.zzfmo;
        double d = dArr[i];
        dArr[i] = doubleValue;
        return Double.valueOf(d);
    }

    public final /* synthetic */ Object remove(int i) {
        zzakk();
        zzej(i);
        double[] dArr = this.zzfmo;
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
        zzd(i, ((Double) obj).doubleValue());
    }

    public final /* synthetic */ zzbrk zzel(int i) {
        if (i >= this.size) {
            return new zzbqn(Arrays.copyOf(this.zzfmo, i), this.size);
        }
        throw new IllegalArgumentException();
    }

    public final /* synthetic */ Object get(int i) {
        zzej(i);
        return Double.valueOf(this.zzfmo[i]);
    }

    static {
        zzbqn zzbqn = new zzbqn();
        zzfmn = zzbqn;
        zzbqn.zzakj();
    }
}
