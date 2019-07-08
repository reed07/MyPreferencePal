package com.google.android.gms.internal.measurement;

import java.util.ListIterator;

final class zzxh implements ListIterator<String> {
    private ListIterator<String> zzcck = this.zzccm.zzccj.listIterator(this.zzccl);
    private final /* synthetic */ int zzccl;
    private final /* synthetic */ zzxg zzccm;

    zzxh(zzxg zzxg, int i) {
        this.zzccm = zzxg;
        this.zzccl = i;
    }

    public final boolean hasNext() {
        return this.zzcck.hasNext();
    }

    public final boolean hasPrevious() {
        return this.zzcck.hasPrevious();
    }

    public final int nextIndex() {
        return this.zzcck.nextIndex();
    }

    public final int previousIndex() {
        return this.zzcck.previousIndex();
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
        return (String) this.zzcck.previous();
    }

    public final /* synthetic */ Object next() {
        return (String) this.zzcck.next();
    }
}
