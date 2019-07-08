package com.google.android.gms.internal.measurement;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzvj extends zzta<Long> implements zzuu<Long>, zzwg, RandomAccess {
    private static final zzvj zzcaf;
    private int size;
    private long[] zzcag;

    zzvj() {
        this(new long[10], 0);
    }

    private zzvj(long[] jArr, int i) {
        this.zzcag = jArr;
        this.size = i;
    }

    /* access modifiers changed from: protected */
    public final void removeRange(int i, int i2) {
        zzua();
        if (i2 >= i) {
            long[] jArr = this.zzcag;
            System.arraycopy(jArr, i2, jArr, i, this.size - i2);
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
        if (!(obj instanceof zzvj)) {
            return super.equals(obj);
        }
        zzvj zzvj = (zzvj) obj;
        if (this.size != zzvj.size) {
            return false;
        }
        long[] jArr = zzvj.zzcag;
        for (int i = 0; i < this.size; i++) {
            if (this.zzcag[i] != jArr[i]) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + zzuq.zzbd(this.zzcag[i2]);
        }
        return i;
    }

    public final long getLong(int i) {
        zzaj(i);
        return this.zzcag[i];
    }

    public final int size() {
        return this.size;
    }

    public final void zzbe(long j) {
        zzk(this.size, j);
    }

    private final void zzk(int i, long j) {
        zzua();
        if (i >= 0) {
            int i2 = this.size;
            if (i <= i2) {
                long[] jArr = this.zzcag;
                if (i2 < jArr.length) {
                    System.arraycopy(jArr, i, jArr, i + 1, i2 - i);
                } else {
                    long[] jArr2 = new long[(((i2 * 3) / 2) + 1)];
                    System.arraycopy(jArr, 0, jArr2, 0, i);
                    System.arraycopy(this.zzcag, i, jArr2, i + 1, this.size - i);
                    this.zzcag = jArr2;
                }
                this.zzcag[i] = j;
                this.size++;
                this.modCount++;
                return;
            }
        }
        throw new IndexOutOfBoundsException(zzak(i));
    }

    public final boolean addAll(Collection<? extends Long> collection) {
        zzua();
        zzuq.checkNotNull(collection);
        if (!(collection instanceof zzvj)) {
            return super.addAll(collection);
        }
        zzvj zzvj = (zzvj) collection;
        int i = zzvj.size;
        if (i == 0) {
            return false;
        }
        int i2 = this.size;
        if (Integer.MAX_VALUE - i2 >= i) {
            int i3 = i2 + i;
            long[] jArr = this.zzcag;
            if (i3 > jArr.length) {
                this.zzcag = Arrays.copyOf(jArr, i3);
            }
            System.arraycopy(zzvj.zzcag, 0, this.zzcag, this.size, zzvj.size);
            this.size = i3;
            this.modCount++;
            return true;
        }
        throw new OutOfMemoryError();
    }

    public final boolean remove(Object obj) {
        zzua();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Long.valueOf(this.zzcag[i]))) {
                long[] jArr = this.zzcag;
                System.arraycopy(jArr, i + 1, jArr, i, (this.size - i) - 1);
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
        long longValue = ((Long) obj).longValue();
        zzua();
        zzaj(i);
        long[] jArr = this.zzcag;
        long j = jArr[i];
        jArr[i] = longValue;
        return Long.valueOf(j);
    }

    public final /* synthetic */ Object remove(int i) {
        zzua();
        zzaj(i);
        long[] jArr = this.zzcag;
        long j = jArr[i];
        int i2 = this.size;
        if (i < i2 - 1) {
            System.arraycopy(jArr, i + 1, jArr, i, (i2 - i) - 1);
        }
        this.size--;
        this.modCount++;
        return Long.valueOf(j);
    }

    public final /* synthetic */ void add(int i, Object obj) {
        zzk(i, ((Long) obj).longValue());
    }

    public final /* synthetic */ zzuu zzal(int i) {
        if (i >= this.size) {
            return new zzvj(Arrays.copyOf(this.zzcag, i), this.size);
        }
        throw new IllegalArgumentException();
    }

    public final /* synthetic */ Object get(int i) {
        return Long.valueOf(getLong(i));
    }

    static {
        zzvj zzvj = new zzvj();
        zzcaf = zzvj;
        zzvj.zzsw();
    }
}
