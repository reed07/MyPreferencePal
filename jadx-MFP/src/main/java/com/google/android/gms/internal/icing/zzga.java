package com.google.android.gms.internal.icing;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

public final class zzga extends AbstractList<String> implements zzea, RandomAccess {
    /* access modifiers changed from: private */
    public final zzea zznw;

    public zzga(zzea zzea) {
        this.zznw = zzea;
    }

    public final zzea zzch() {
        return this;
    }

    public final Object zzad(int i) {
        return this.zznw.zzad(i);
    }

    public final int size() {
        return this.zznw.size();
    }

    public final void zzc(zzce zzce) {
        throw new UnsupportedOperationException();
    }

    public final ListIterator<String> listIterator(int i) {
        return new zzgb(this, i);
    }

    public final Iterator<String> iterator() {
        return new zzgc(this);
    }

    public final List<?> zzcg() {
        return this.zznw.zzcg();
    }

    public final /* synthetic */ Object get(int i) {
        return (String) this.zznw.get(i);
    }
}
