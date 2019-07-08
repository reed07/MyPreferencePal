package com.google.android.gms.internal.icing;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzef extends zzca<Long> implements zzdq<Long>, zzfb, RandomAccess {
    private static final zzef zzlt;
    private int size;
    private long[] zzlu;

    public static zzef zzck() {
        return zzlt;
    }

    zzef() {
        this(new long[10], 0);
    }

    private zzef(long[] jArr, int i) {
        this.zzlu = jArr;
        this.size = i;
    }

    /* access modifiers changed from: protected */
    public final void removeRange(int i, int i2) {
        zzak();
        if (i2 >= i) {
            long[] jArr = this.zzlu;
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
        if (!(obj instanceof zzef)) {
            return super.equals(obj);
        }
        zzef zzef = (zzef) obj;
        if (this.size != zzef.size) {
            return false;
        }
        long[] jArr = zzef.zzlu;
        for (int i = 0; i < this.size; i++) {
            if (this.zzlu[i] != jArr[i]) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + zzdl.zzk(this.zzlu[i2]);
        }
        return i;
    }

    public final long getLong(int i) {
        zzh(i);
        return this.zzlu[i];
    }

    public final int size() {
        return this.size;
    }

    public final boolean addAll(Collection<? extends Long> collection) {
        zzak();
        zzdl.checkNotNull(collection);
        if (!(collection instanceof zzef)) {
            return super.addAll(collection);
        }
        zzef zzef = (zzef) collection;
        int i = zzef.size;
        if (i == 0) {
            return false;
        }
        int i2 = this.size;
        if (Integer.MAX_VALUE - i2 >= i) {
            int i3 = i2 + i;
            long[] jArr = this.zzlu;
            if (i3 > jArr.length) {
                this.zzlu = Arrays.copyOf(jArr, i3);
            }
            System.arraycopy(zzef.zzlu, 0, this.zzlu, this.size, zzef.size);
            this.size = i3;
            this.modCount++;
            return true;
        }
        throw new OutOfMemoryError();
    }

    public final boolean remove(Object obj) {
        zzak();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Long.valueOf(this.zzlu[i]))) {
                long[] jArr = this.zzlu;
                System.arraycopy(jArr, i + 1, jArr, i, this.size - i);
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
        long longValue = ((Long) obj).longValue();
        zzak();
        zzh(i);
        long[] jArr = this.zzlu;
        long j = jArr[i];
        jArr[i] = longValue;
        return Long.valueOf(j);
    }

    public final /* synthetic */ Object remove(int i) {
        zzak();
        zzh(i);
        long[] jArr = this.zzlu;
        long j = jArr[i];
        int i2 = this.size;
        if (i < i2 - 1) {
            System.arraycopy(jArr, i + 1, jArr, i, i2 - i);
        }
        this.size--;
        this.modCount++;
        return Long.valueOf(j);
    }

    public final /* synthetic */ void add(int i, Object obj) {
        long longValue = ((Long) obj).longValue();
        zzak();
        if (i >= 0) {
            int i2 = this.size;
            if (i <= i2) {
                long[] jArr = this.zzlu;
                if (i2 < jArr.length) {
                    System.arraycopy(jArr, i, jArr, i + 1, i2 - i);
                } else {
                    long[] jArr2 = new long[(((i2 * 3) / 2) + 1)];
                    System.arraycopy(jArr, 0, jArr2, 0, i);
                    System.arraycopy(this.zzlu, i, jArr2, i + 1, this.size - i);
                    this.zzlu = jArr2;
                }
                this.zzlu[i] = longValue;
                this.size++;
                this.modCount++;
                return;
            }
        }
        throw new IndexOutOfBoundsException(zzi(i));
    }

    public final /* synthetic */ zzdq zzj(int i) {
        if (i >= this.size) {
            return new zzef(Arrays.copyOf(this.zzlu, i), this.size);
        }
        throw new IllegalArgumentException();
    }

    public final /* synthetic */ Object get(int i) {
        return Long.valueOf(getLong(i));
    }

    static {
        zzef zzef = new zzef();
        zzlt = zzef;
        zzef.zzaj();
    }
}
