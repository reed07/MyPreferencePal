package com.google.android.gms.measurement.internal;

import java.util.Iterator;

final class zzae implements Iterator<String> {
    private Iterator<String> zzaie = this.zzaif.zzaid.keySet().iterator();
    private final /* synthetic */ zzad zzaif;

    zzae(zzad zzad) {
        this.zzaif = zzad;
    }

    public final boolean hasNext() {
        return this.zzaie.hasNext();
    }

    public final void remove() {
        throw new UnsupportedOperationException("Remove not supported");
    }

    public final /* synthetic */ Object next() {
        return (String) this.zzaie.next();
    }
}
