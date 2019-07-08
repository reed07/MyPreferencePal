package com.google.android.gms.internal.measurement;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zztc extends zzta<Boolean> implements zzuu<Boolean>, zzwg, RandomAccess {
    private static final zztc zzbtq;
    private int size;
    private boolean[] zzbtr;

    zztc() {
        this(new boolean[10], 0);
    }

    private zztc(boolean[] zArr, int i) {
        this.zzbtr = zArr;
        this.size = i;
    }

    /* access modifiers changed from: protected */
    public final void removeRange(int i, int i2) {
        zzua();
        if (i2 >= i) {
            boolean[] zArr = this.zzbtr;
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
        if (!(obj instanceof zztc)) {
            return super.equals(obj);
        }
        zztc zztc = (zztc) obj;
        if (this.size != zztc.size) {
            return false;
        }
        boolean[] zArr = zztc.zzbtr;
        for (int i = 0; i < this.size; i++) {
            if (this.zzbtr[i] != zArr[i]) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + zzuq.zzu(this.zzbtr[i2]);
        }
        return i;
    }

    public final int size() {
        return this.size;
    }

    public final void addBoolean(boolean z) {
        zza(this.size, z);
    }

    private final void zza(int i, boolean z) {
        zzua();
        if (i >= 0) {
            int i2 = this.size;
            if (i <= i2) {
                boolean[] zArr = this.zzbtr;
                if (i2 < zArr.length) {
                    System.arraycopy(zArr, i, zArr, i + 1, i2 - i);
                } else {
                    boolean[] zArr2 = new boolean[(((i2 * 3) / 2) + 1)];
                    System.arraycopy(zArr, 0, zArr2, 0, i);
                    System.arraycopy(this.zzbtr, i, zArr2, i + 1, this.size - i);
                    this.zzbtr = zArr2;
                }
                this.zzbtr[i] = z;
                this.size++;
                this.modCount++;
                return;
            }
        }
        throw new IndexOutOfBoundsException(zzak(i));
    }

    public final boolean addAll(Collection<? extends Boolean> collection) {
        zzua();
        zzuq.checkNotNull(collection);
        if (!(collection instanceof zztc)) {
            return super.addAll(collection);
        }
        zztc zztc = (zztc) collection;
        int i = zztc.size;
        if (i == 0) {
            return false;
        }
        int i2 = this.size;
        if (Integer.MAX_VALUE - i2 >= i) {
            int i3 = i2 + i;
            boolean[] zArr = this.zzbtr;
            if (i3 > zArr.length) {
                this.zzbtr = Arrays.copyOf(zArr, i3);
            }
            System.arraycopy(zztc.zzbtr, 0, this.zzbtr, this.size, zztc.size);
            this.size = i3;
            this.modCount++;
            return true;
        }
        throw new OutOfMemoryError();
    }

    public final boolean remove(Object obj) {
        zzua();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Boolean.valueOf(this.zzbtr[i]))) {
                boolean[] zArr = this.zzbtr;
                System.arraycopy(zArr, i + 1, zArr, i, (this.size - i) - 1);
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
        boolean booleanValue = ((Boolean) obj).booleanValue();
        zzua();
        zzaj(i);
        boolean[] zArr = this.zzbtr;
        boolean z = zArr[i];
        zArr[i] = booleanValue;
        return Boolean.valueOf(z);
    }

    public final /* synthetic */ Object remove(int i) {
        zzua();
        zzaj(i);
        boolean[] zArr = this.zzbtr;
        boolean z = zArr[i];
        int i2 = this.size;
        if (i < i2 - 1) {
            System.arraycopy(zArr, i + 1, zArr, i, (i2 - i) - 1);
        }
        this.size--;
        this.modCount++;
        return Boolean.valueOf(z);
    }

    public final /* synthetic */ void add(int i, Object obj) {
        zza(i, ((Boolean) obj).booleanValue());
    }

    public final /* synthetic */ zzuu zzal(int i) {
        if (i >= this.size) {
            return new zztc(Arrays.copyOf(this.zzbtr, i), this.size);
        }
        throw new IllegalArgumentException();
    }

    public final /* synthetic */ Object get(int i) {
        zzaj(i);
        return Boolean.valueOf(this.zzbtr[i]);
    }

    static {
        zztc zztc = new zztc();
        zzbtq = zztc;
        zztc.zzsw();
    }
}
