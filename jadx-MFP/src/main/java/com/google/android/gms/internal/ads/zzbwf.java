package com.google.android.gms.internal.ads;

import java.util.Iterator;
import java.util.List;

final class zzbwf implements Iterator<E> {
    private int pos = 0;
    private final /* synthetic */ zzbwe zzgcv;

    zzbwf(zzbwe zzbwe) {
        this.zzgcv = zzbwe;
    }

    public final boolean hasNext() {
        return this.pos < this.zzgcv.zzgct.size() || this.zzgcv.zzgcu.hasNext();
    }

    public final E next() {
        while (this.pos >= this.zzgcv.zzgct.size()) {
            this.zzgcv.zzgct.add(this.zzgcv.zzgcu.next());
        }
        List<E> list = this.zzgcv.zzgct;
        int i = this.pos;
        this.pos = i + 1;
        return list.get(i);
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
