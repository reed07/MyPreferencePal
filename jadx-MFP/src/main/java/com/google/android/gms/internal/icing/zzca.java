package com.google.android.gms.internal.icing;

import java.util.AbstractList;
import java.util.Collection;
import java.util.List;
import java.util.RandomAccess;

abstract class zzca<E> extends AbstractList<E> implements zzdq<E> {
    private boolean zzfs = true;

    zzca() {
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
        zzak();
        return super.add(e);
    }

    public void add(int i, E e) {
        zzak();
        super.add(i, e);
    }

    public boolean addAll(Collection<? extends E> collection) {
        zzak();
        return super.addAll(collection);
    }

    public boolean addAll(int i, Collection<? extends E> collection) {
        zzak();
        return super.addAll(i, collection);
    }

    public void clear() {
        zzak();
        super.clear();
    }

    public boolean zzai() {
        return this.zzfs;
    }

    public final void zzaj() {
        this.zzfs = false;
    }

    public E remove(int i) {
        zzak();
        return super.remove(i);
    }

    public boolean remove(Object obj) {
        zzak();
        return super.remove(obj);
    }

    public boolean removeAll(Collection<?> collection) {
        zzak();
        return super.removeAll(collection);
    }

    public boolean retainAll(Collection<?> collection) {
        zzak();
        return super.retainAll(collection);
    }

    public E set(int i, E e) {
        zzak();
        return super.set(i, e);
    }

    /* access modifiers changed from: protected */
    public final void zzak() {
        if (!this.zzfs) {
            throw new UnsupportedOperationException();
        }
    }
}
