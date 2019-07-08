package com.google.ads.interactivemedia.v3.internal;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* compiled from: IMASDK */
public abstract class agb<E> extends aga<E> implements List<E>, RandomAccess {
    private static final agu<Object> a = new afz(agl.a, 0);

    public static <E> agb<E> g() {
        return agl.a;
    }

    public final agb<E> e() {
        return this;
    }

    public static <E> agb<E> a(E e) {
        Object[] objArr = {e};
        int length = objArr.length;
        for (int i = 0; i < length; i++) {
            agj.a(objArr[i], i);
        }
        return b(objArr, objArr.length);
    }

    public static <E> agb<E> a(Collection<? extends E> collection) {
        if (collection instanceof aga) {
            agb<E> e = ((aga) collection).e();
            if (e.f()) {
                Object[] array = e.toArray();
                e = b(array, array.length);
            }
            return e;
        }
        Object[] array2 = collection.toArray();
        int length = array2.length;
        for (int i = 0; i < length; i++) {
            agj.a(array2[i], i);
        }
        return b(array2, array2.length);
    }

    public static <E> agb<E> a(E[] eArr) {
        if (eArr.length == 0) {
            return agl.a;
        }
        Object[] objArr = (Object[]) eArr.clone();
        int length = objArr.length;
        for (int i = 0; i < length; i++) {
            agj.a(objArr[i], i);
        }
        return b(objArr, objArr.length);
    }

    static <E> agb<E> b(Object[] objArr) {
        return b(objArr, objArr.length);
    }

    static <E> agb<E> b(Object[] objArr, int i) {
        if (i == 0) {
            return agl.a;
        }
        return new agl(objArr, i);
    }

    agb() {
    }

    public final agt<E> a() {
        return listIterator(0);
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public final agu<E> listIterator(int i) {
        afx.b(i, size());
        if (isEmpty()) {
            return a;
        }
        return new afz(this, i);
    }

    public int indexOf(Object obj) {
        if (obj == null) {
            return -1;
        }
        return agj.b((List<?>) this, obj);
    }

    public int lastIndexOf(Object obj) {
        if (obj == null) {
            return -1;
        }
        return agj.c(this, obj);
    }

    public boolean contains(Object obj) {
        return indexOf(obj) >= 0;
    }

    /* renamed from: a */
    public agb<E> subList(int i, int i2) {
        afx.a(i, i2, size());
        int i3 = i2 - i;
        if (i3 == size()) {
            return this;
        }
        if (i3 == 0) {
            return agl.a;
        }
        return new agd(this, i, i3);
    }

    @Deprecated
    public final boolean addAll(int i, Collection<? extends E> collection) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final E set(int i, E e) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final void add(int i, E e) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final E remove(int i) {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: 0000 */
    public int a(Object[] objArr, int i) {
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            objArr[i + i2] = get(i2);
        }
        return i + size;
    }

    public boolean equals(Object obj) {
        return agj.a((List<?>) this, obj);
    }

    public int hashCode() {
        int size = size();
        int i = 1;
        for (int i2 = 0; i2 < size; i2++) {
            i = ~(~((i * 31) + get(i2).hashCode()));
        }
        return i;
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Use SerializedForm");
    }

    /* access modifiers changed from: 0000 */
    public Object writeReplace() {
        return new agc(toArray());
    }

    public /* synthetic */ Iterator iterator() {
        return listIterator(0);
    }

    public /* synthetic */ ListIterator listIterator() {
        return listIterator(0);
    }
}
