package com.google.ads.interactivemedia.v3.internal;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.Collection;

/* compiled from: IMASDK */
public abstract class aga<E> extends AbstractCollection<E> implements Serializable {
    private static final Object[] a = new Object[0];

    aga() {
    }

    /* renamed from: a */
    public abstract agt<E> iterator();

    /* access modifiers changed from: 0000 */
    public Object[] b() {
        return null;
    }

    public abstract boolean contains(Object obj);

    /* access modifiers changed from: 0000 */
    public abstract boolean f();

    public final Object[] toArray() {
        return toArray(a);
    }

    public final <T> T[] toArray(T[] tArr) {
        afx.a(tArr);
        int size = size();
        if (tArr.length < size) {
            Object[] b = b();
            if (b != null) {
                return Arrays.copyOfRange(b, c(), d(), tArr.getClass());
            }
            tArr = (Object[]) Array.newInstance(tArr.getClass().getComponentType(), size);
        } else if (tArr.length > size) {
            tArr[size] = null;
        }
        a(tArr, 0);
        return tArr;
    }

    /* access modifiers changed from: 0000 */
    public int c() {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: 0000 */
    public int d() {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final boolean add(E e) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final boolean remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final boolean addAll(Collection<? extends E> collection) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final boolean removeAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    public agb<E> e() {
        return isEmpty() ? agb.g() : agb.b(toArray());
    }

    /* access modifiers changed from: 0000 */
    public int a(Object[] objArr, int i) {
        agt a2 = iterator();
        while (a2.hasNext()) {
            int i2 = i + 1;
            objArr[i] = a2.next();
            i = i2;
        }
        return i;
    }

    /* access modifiers changed from: 0000 */
    public Object writeReplace() {
        return new agc(toArray());
    }
}
