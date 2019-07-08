package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.List;

final class zzwi<E> extends zzta<E> {
    private static final zzwi<Object> zzcbo;
    private final List<E> zzcab;

    public static <E> zzwi<E> zzxu() {
        return zzcbo;
    }

    zzwi() {
        this(new ArrayList(10));
    }

    private zzwi(List<E> list) {
        this.zzcab = list;
    }

    public final void add(int i, E e) {
        zzua();
        this.zzcab.add(i, e);
        this.modCount++;
    }

    public final E get(int i) {
        return this.zzcab.get(i);
    }

    public final E remove(int i) {
        zzua();
        E remove = this.zzcab.remove(i);
        this.modCount++;
        return remove;
    }

    public final E set(int i, E e) {
        zzua();
        E e2 = this.zzcab.set(i, e);
        this.modCount++;
        return e2;
    }

    public final int size() {
        return this.zzcab.size();
    }

    public final /* synthetic */ zzuu zzal(int i) {
        if (i >= size()) {
            ArrayList arrayList = new ArrayList(i);
            arrayList.addAll(this.zzcab);
            return new zzwi(arrayList);
        }
        throw new IllegalArgumentException();
    }

    static {
        zzwi<Object> zzwi = new zzwi<>();
        zzcbo = zzwi;
        zzwi.zzsw();
    }
}
