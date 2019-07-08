package com.google.android.gms.internal.measurement;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzty extends zzta<Double> implements zzuu<Double>, zzwg, RandomAccess {
    private static final zzty zzbuz;
    private int size;
    private double[] zzbva;

    zzty() {
        this(new double[10], 0);
    }

    private zzty(double[] dArr, int i) {
        this.zzbva = dArr;
        this.size = i;
    }

    /* access modifiers changed from: protected */
    public final void removeRange(int i, int i2) {
        zzua();
        if (i2 >= i) {
            double[] dArr = this.zzbva;
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
        if (!(obj instanceof zzty)) {
            return super.equals(obj);
        }
        zzty zzty = (zzty) obj;
        if (this.size != zzty.size) {
            return false;
        }
        double[] dArr = zzty.zzbva;
        for (int i = 0; i < this.size; i++) {
            if (Double.doubleToLongBits(this.zzbva[i]) != Double.doubleToLongBits(dArr[i])) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + zzuq.zzbd(Double.doubleToLongBits(this.zzbva[i2]));
        }
        return i;
    }

    public final int size() {
        return this.size;
    }

    public final void zzd(double d) {
        zzc(this.size, d);
    }

    private final void zzc(int i, double d) {
        zzua();
        if (i >= 0) {
            int i2 = this.size;
            if (i <= i2) {
                double[] dArr = this.zzbva;
                if (i2 < dArr.length) {
                    System.arraycopy(dArr, i, dArr, i + 1, i2 - i);
                } else {
                    double[] dArr2 = new double[(((i2 * 3) / 2) + 1)];
                    System.arraycopy(dArr, 0, dArr2, 0, i);
                    System.arraycopy(this.zzbva, i, dArr2, i + 1, this.size - i);
                    this.zzbva = dArr2;
                }
                this.zzbva[i] = d;
                this.size++;
                this.modCount++;
                return;
            }
        }
        throw new IndexOutOfBoundsException(zzak(i));
    }

    public final boolean addAll(Collection<? extends Double> collection) {
        zzua();
        zzuq.checkNotNull(collection);
        if (!(collection instanceof zzty)) {
            return super.addAll(collection);
        }
        zzty zzty = (zzty) collection;
        int i = zzty.size;
        if (i == 0) {
            return false;
        }
        int i2 = this.size;
        if (Integer.MAX_VALUE - i2 >= i) {
            int i3 = i2 + i;
            double[] dArr = this.zzbva;
            if (i3 > dArr.length) {
                this.zzbva = Arrays.copyOf(dArr, i3);
            }
            System.arraycopy(zzty.zzbva, 0, this.zzbva, this.size, zzty.size);
            this.size = i3;
            this.modCount++;
            return true;
        }
        throw new OutOfMemoryError();
    }

    public final boolean remove(Object obj) {
        zzua();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Double.valueOf(this.zzbva[i]))) {
                double[] dArr = this.zzbva;
                System.arraycopy(dArr, i + 1, dArr, i, (this.size - i) - 1);
                this.size--;
                this.modCount++;
                return true;
            }
        }
        return false;
    }

    private final void zzaj(int i) {
        if (i < 0 || i >= this.size) {
            throw new IndexOutOfBoundsException(zzak(i));
        }
    }

    private final String zzak(int i) {
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
        zzua();
        zzaj(i);
        double[] dArr = this.zzbva;
        double d = dArr[i];
        dArr[i] = doubleValue;
        return Double.valueOf(d);
    }

    public final /* synthetic */ Object remove(int i) {
        zzua();
        zzaj(i);
        double[] dArr = this.zzbva;
        double d = dArr[i];
        int i2 = this.size;
        if (i < i2 - 1) {
            System.arraycopy(dArr, i + 1, dArr, i, (i2 - i) - 1);
        }
        this.size--;
        this.modCount++;
        return Double.valueOf(d);
    }

    public final /* synthetic */ void add(int i, Object obj) {
        zzc(i, ((Double) obj).doubleValue());
    }

    public final /* synthetic */ zzuu zzal(int i) {
        if (i >= this.size) {
            return new zzty(Arrays.copyOf(this.zzbva, i), this.size);
        }
        throw new IllegalArgumentException();
    }

    public final /* synthetic */ Object get(int i) {
        zzaj(i);
        return Double.valueOf(this.zzbva[i]);
    }

    static {
        zzty zzty = new zzty();
        zzbuz = zzty;
        zzty.zzsw();
    }
}
