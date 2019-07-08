package com.google.android.gms.internal.measurement;

import java.util.Iterator;

final class zzxi implements Iterator<String> {
    private final /* synthetic */ zzxg zzccm;
    private Iterator<String> zzccn = this.zzccm.zzccj.iterator();

    zzxi(zzxg zzxg) {
        this.zzccm = zzxg;
    }

    public final boolean hasNext() {
        return this.zzccn.hasNext();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }

    public final /* synthetic */ Object next() {
        return (String) this.zzccn.next();
    }
}
