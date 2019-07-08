package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzbre extends zzbpo<Integer> implements zzbrk<Integer>, zzbsx, RandomAccess {
    private static final zzbre zzfqp;
    private int size;
    private int[] zzfqq;

    public static zzbre zzanb() {
        return zzfqp;
    }

    zzbre() {
        this(new int[10], 0);
    }

    private zzbre(int[] iArr, int i) {
        this.zzfqq = iArr;
        this.size = i;
    }

    /* access modifiers changed from: protected */
    public final void removeRange(int i, int i2) {
        zzakk();
        if (i2 >= i) {
            int[] iArr = this.zzfqq;
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
        if (!(obj instanceof zzbre)) {
            return super.equals(obj);
        }
        zzbre zzbre = (zzbre) obj;
        if (this.size != zzbre.size) {
            return false;
        }
        int[] iArr = zzbre.zzfqq;
        for (int i = 0; i < this.size; i++) {
            if (this.zzfqq[i] != iArr[i]) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + this.zzfqq[i2];
        }
        return i;
    }

    public final int getInt(int i) {
        zzej(i);
        return this.zzfqq[i];
    }

    public final int size() {
        return this.size;
    }

    public final void zzfo(int i) {
        zzah(this.size, i);
    }

    private final void zzah(int i, int i2) {
        zzakk();
        if (i >= 0) {
            int i3 = this.size;
            if (i <= i3) {
                int[] iArr = this.zzfqq;
                if (i3 < iArr.length) {
                    System.arraycopy(iArr, i, iArr, i + 1, i3 - i);
                } else {
                    int[] iArr2 = new int[(((i3 * 3) / 2) + 1)];
                    System.arraycopy(iArr, 0, iArr2, 0, i);
                    System.arraycopy(this.zzfqq, i, iArr2, i + 1, this.size - i);
                    this.zzfqq = iArr2;
                }
                this.zzfqq[i] = i2;
                this.size++;
                this.modCount++;
                return;
            }
        }
        throw new IndexOutOfBoundsException(zzek(i));
    }

    public final boolean addAll(Collection<? extends Integer> collection) {
        zzakk();
        zzbrf.checkNotNull(collection);
        if (!(collection instanceof zzbre)) {
            return super.addAll(collection);
        }
        zzbre zzbre = (zzbre) collection;
        int i = zzbre.size;
        if (i == 0) {
            return false;
        }
        int i2 = this.size;
        if (Integer.MAX_VALUE - i2 >= i) {
            int i3 = i2 + i;
            int[] iArr = this.zzfqq;
            if (i3 > iArr.length) {
                this.zzfqq = Arrays.copyOf(iArr, i3);
            }
            System.arraycopy(zzbre.zzfqq, 0, this.zzfqq, this.size, zzbre.size);
            this.size = i3;
            this.modCount++;
            return true;
        }
        throw new OutOfMemoryError();
    }

    public final boolean remove(Object obj) {
        zzakk();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Integer.valueOf(this.zzfqq[i]))) {
                int[] iArr = this.zzfqq;
                System.arraycopy(iArr, i + 1, iArr, i, this.size - i);
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
        int intValue = ((Integer) obj).intValue();
        zzakk();
        zzej(i);
        int[] iArr = this.zzfqq;
        int i2 = iArr[i];
        iArr[i] = intValue;
        return Integer.valueOf(i2);
    }

    public final /* synthetic */ Object remove(int i) {
        zzakk();
        zzej(i);
        int[] iArr = this.zzfqq;
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
        zzah(i, ((Integer) obj).intValue());
    }

    public final /* synthetic */ zzbrk zzel(int i) {
        if (i >= this.size) {
            return new zzbre(Arrays.copyOf(this.zzfqq, i), this.size);
        }
        throw new IllegalArgumentException();
    }

    public final /* synthetic */ Object get(int i) {
        return Integer.valueOf(getInt(i));
    }

    static {
        zzbre zzbre = new zzbre();
        zzfqp = zzbre;
        zzbre.zzakj();
    }
}
