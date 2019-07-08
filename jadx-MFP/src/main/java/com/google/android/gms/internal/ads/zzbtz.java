package com.google.android.gms.internal.ads;

import java.util.Iterator;

final class zzbtz implements Iterator<String> {
    private final /* synthetic */ zzbtx zzfuc;
    private Iterator<String> zzfud = this.zzfuc.zzftz.iterator();

    zzbtz(zzbtx zzbtx) {
        this.zzfuc = zzbtx;
    }

    public final boolean hasNext() {
        return this.zzfud.hasNext();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }

    public final /* synthetic */ Object next() {
        return (String) this.zzfud.next();
    }
}
