package com.google.android.gms.internal.measurement;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

public final class zzxg extends AbstractList<String> implements zzve, RandomAccess {
    /* access modifiers changed from: private */
    public final zzve zzccj;

    public zzxg(zzve zzve) {
        this.zzccj = zzve;
    }

    public final zzve zzxc() {
        return this;
    }

    public final Object zzbp(int i) {
        return this.zzccj.zzbp(i);
    }

    public final int size() {
        return this.zzccj.size();
    }

    public final void zzc(zzte zzte) {
        throw new UnsupportedOperationException();
    }

    public final ListIterator<String> listIterator(int i) {
        return new zzxh(this, i);
    }

    public final Iterator<String> iterator() {
        return new zzxi(this);
    }

    public final List<?> zzxb() {
        return this.zzccj.zzxb();
    }

    public final /* synthetic */ Object get(int i) {
        return (String) this.zzccj.get(i);
    }
}
