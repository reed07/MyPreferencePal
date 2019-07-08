package com.google.android.gms.internal.ads;

import java.util.ListIterator;

final class zzbty implements ListIterator<String> {
    private ListIterator<String> zzfua = this.zzfuc.zzftz.listIterator(this.zzfub);
    private final /* synthetic */ int zzfub;
    private final /* synthetic */ zzbtx zzfuc;

    zzbty(zzbtx zzbtx, int i) {
        this.zzfuc = zzbtx;
        this.zzfub = i;
    }

    public final boolean hasNext() {
        return this.zzfua.hasNext();
    }

    public final boolean hasPrevious() {
        return this.zzfua.hasPrevious();
    }

    public final int nextIndex() {
        return this.zzfua.nextIndex();
    }

    public final int previousIndex() {
        return this.zzfua.previousIndex();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }

    public final /* synthetic */ void add(Object obj) {
        throw new UnsupportedOperationException();
    }

    public final /* synthetic */ void set(Object obj) {
        throw new UnsupportedOperationException();
    }

    public final /* synthetic */ Object previous() {
        return (String) this.zzfua.previous();
    }

    public final /* synthetic */ Object next() {
        return (String) this.zzfua.next();
    }
}
