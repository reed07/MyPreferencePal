package com.google.android.gms.internal.icing;

import java.util.ListIterator;

final class zzgb implements ListIterator<String> {
    private ListIterator<String> zznx = this.zznz.zznw.listIterator(this.zzny);
    private final /* synthetic */ int zzny;
    private final /* synthetic */ zzga zznz;

    zzgb(zzga zzga, int i) {
        this.zznz = zzga;
        this.zzny = i;
    }

    public final boolean hasNext() {
        return this.zznx.hasNext();
    }

    public final boolean hasPrevious() {
        return this.zznx.hasPrevious();
    }

    public final int nextIndex() {
        return this.zznx.nextIndex();
    }

    public final int previousIndex() {
        return this.zznx.previousIndex();
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
        return (String) this.zznx.previous();
    }

    public final /* synthetic */ Object next() {
        return (String) this.zznx.next();
    }
}
