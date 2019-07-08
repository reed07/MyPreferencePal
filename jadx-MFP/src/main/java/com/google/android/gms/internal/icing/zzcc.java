package com.google.android.gms.internal.icing;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzcc extends zzca<Boolean> implements zzdq<Boolean>, zzfb, RandomAccess {
    private static final zzcc zzfv;
    private int size;
    private boolean[] zzfw;

    public static zzcc zzan() {
        return zzfv;
    }

    zzcc() {
        this(new boolean[10], 0);
    }

    private zzcc(boolean[] zArr, int i) {
        this.zzfw = zArr;
        this.size = i;
    }

    /* access modifiers changed from: protected */
    public final void removeRange(int i, int i2) {
        zzak();
        if (i2 >= i) {
            boolean[] zArr = this.zzfw;
            System.arraycopy(zArr, i2, zArr, i, this.size - i2);
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
        if (!(obj instanceof zzcc)) {
            return super.equals(obj);
        }
        zzcc zzcc = (zzcc) obj;
        if (this.size != zzcc.size) {
            return false;
        }
        boolean[] zArr = zzcc.zzfw;
        for (int i = 0; i < this.size; i++) {
            if (this.zzfw[i] != zArr[i]) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + zzdl.zzi(this.zzfw[i2]);
        }
        return i;
    }

    public final int size() {
        return this.size;
    }

    public final boolean addAll(Collection<? extends Boolean> collection) {
        zzak();
        zzdl.checkNotNull(collection);
        if (!(collection instanceof zzcc)) {
            return super.addAll(collection);
        }
        zzcc zzcc = (zzcc) collection;
        int i = zzcc.size;
        if (i == 0) {
            return false;
        }
        int i2 = this.size;
        if (Integer.MAX_VALUE - i2 >= i) {
            int i3 = i2 + i;
            boolean[] zArr = this.zzfw;
            if (i3 > zArr.length) {
                this.zzfw = Arrays.copyOf(zArr, i3);
            }
            System.arraycopy(zzcc.zzfw, 0, this.zzfw, this.size, zzcc.size);
            this.size = i3;
            this.modCount++;
            return true;
        }
        throw new OutOfMemoryError();
    }

    public final boolean remove(Object obj) {
        zzak();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Boolean.valueOf(this.zzfw[i]))) {
                boolean[] zArr = this.zzfw;
                System.arraycopy(zArr, i + 1, zArr, i, this.size - i);
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
        boolean booleanValue = ((Boolean) obj).booleanValue();
        zzak();
        zzh(i);
        boolean[] zArr = this.zzfw;
        boolean z = zArr[i];
        zArr[i] = booleanValue;
        return Boolean.valueOf(z);
    }

    public final /* synthetic */ Object remove(int i) {
        zzak();
        zzh(i);
        boolean[] zArr = this.zzfw;
        boolean z = zArr[i];
        int i2 = this.size;
        if (i < i2 - 1) {
            System.arraycopy(zArr, i + 1, zArr, i, i2 - i);
        }
        this.size--;
        this.modCount++;
        return Boolean.valueOf(z);
    }

    public final /* synthetic */ void add(int i, Object obj) {
        boolean booleanValue = ((Boolean) obj).booleanValue();
        zzak();
        if (i >= 0) {
            int i2 = this.size;
            if (i <= i2) {
                boolean[] zArr = this.zzfw;
                if (i2 < zArr.length) {
                    System.arraycopy(zArr, i, zArr, i + 1, i2 - i);
                } else {
                    boolean[] zArr2 = new boolean[(((i2 * 3) / 2) + 1)];
                    System.arraycopy(zArr, 0, zArr2, 0, i);
                    System.arraycopy(this.zzfw, i, zArr2, i + 1, this.size - i);
                    this.zzfw = zArr2;
                }
                this.zzfw[i] = booleanValue;
                this.size++;
                this.modCount++;
                return;
            }
        }
        throw new IndexOutOfBoundsException(zzi(i));
    }

    public final /* synthetic */ zzdq zzj(int i) {
        if (i >= this.size) {
            return new zzcc(Arrays.copyOf(this.zzfw, i), this.size);
        }
        throw new IllegalArgumentException();
    }

    public final /* synthetic */ Object get(int i) {
        zzh(i);
        return Boolean.valueOf(this.zzfw[i]);
    }

    static {
        zzcc zzcc = new zzcc();
        zzfv = zzcc;
        zzcc.zzaj();
    }
}
