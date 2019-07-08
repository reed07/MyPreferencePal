package com.google.android.gms.internal.icing;

import java.util.Iterator;

final class zzgc implements Iterator<String> {
    private final /* synthetic */ zzga zznz;
    private Iterator<String> zzoa = this.zznz.zznw.iterator();

    zzgc(zzga zzga) {
        this.zznz = zzga;
    }

    public final boolean hasNext() {
        return this.zzoa.hasNext();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }

    public final /* synthetic */ Object next() {
        return (String) this.zzoa.next();
    }
}
