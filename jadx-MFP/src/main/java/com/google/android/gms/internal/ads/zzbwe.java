package com.google.android.gms.internal.ads;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class zzbwe<E> extends AbstractList<E> {
    private static final zzbwg zzco = zzbwg.zzk(zzbwe.class);
    List<E> zzgct;
    Iterator<E> zzgcu;

    public zzbwe(List<E> list, Iterator<E> it) {
        this.zzgct = list;
        this.zzgcu = it;
    }

    public E get(int i) {
        if (this.zzgct.size() > i) {
            return this.zzgct.get(i);
        }
        if (this.zzgcu.hasNext()) {
            this.zzgct.add(this.zzgcu.next());
            return get(i);
        }
        throw new NoSuchElementException();
    }

    public Iterator<E> iterator() {
        return new zzbwf(this);
    }

    public int size() {
        zzco.zzge("potentially expensive size() call");
        zzco.zzge("blowup running");
        while (this.zzgcu.hasNext()) {
            this.zzgct.add(this.zzgcu.next());
        }
        return this.zzgct.size();
    }
}
