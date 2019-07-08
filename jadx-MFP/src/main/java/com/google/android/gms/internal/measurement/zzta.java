package com.google.android.gms.internal.measurement;

import java.util.AbstractList;
import java.util.Collection;
import java.util.List;
import java.util.RandomAccess;

abstract class zzta<E> extends AbstractList<E> implements zzuu<E> {
    private boolean zzbtn = true;

    zzta() {
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof List)) {
            return false;
        }
        if (!(obj instanceof RandomAccess)) {
            return super.equals(obj);
        }
        List list = (List) obj;
        int size = size();
        if (size != list.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!get(i).equals(list.get(i))) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < size(); i2++) {
            i = (i * 31) + get(i2).hashCode();
        }
        return i;
    }

    public boolean add(E e) {
        zzua();
        return super.add(e);
    }

    public void add(int i, E e) {
        zzua();
        super.add(i, e);
    }

    public boolean addAll(Collection<? extends E> collection) {
        zzua();
        return super.addAll(collection);
    }

    public boolean addAll(int i, Collection<? extends E> collection) {
        zzua();
        return super.addAll(i, collection);
    }

    public void clear() {
        zzua();
        super.clear();
    }

    public boolean zztz() {
        return this.zzbtn;
    }

    public final void zzsw() {
        this.zzbtn = false;
    }

    public E remove(int i) {
        zzua();
        return super.remove(i);
    }

    public boolean remove(Object obj) {
        zzua();
        return super.remove(obj);
    }

    public boolean removeAll(Collection<?> collection) {
        zzua();
        return super.removeAll(collection);
    }

    public boolean retainAll(Collection<?> collection) {
        zzua();
        return super.retainAll(collection);
    }

    public E set(int i, E e) {
        zzua();
        return super.set(i, e);
    }

    /* access modifiers changed from: protected */
    public final void zzua() {
        if (!this.zzbtn) {
            throw new UnsupportedOperationException();
        }
    }
}
