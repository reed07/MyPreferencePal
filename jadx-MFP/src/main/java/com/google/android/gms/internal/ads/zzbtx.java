package com.google.android.gms.internal.ads;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

public final class zzbtx extends AbstractList<String> implements zzbru, RandomAccess {
    /* access modifiers changed from: private */
    public final zzbru zzftz;

    public zzbtx(zzbru zzbru) {
        this.zzftz = zzbru;
    }

    public final zzbru zzanp() {
        return this;
    }

    public final Object zzfp(int i) {
        return this.zzftz.zzfp(i);
    }

    public final int size() {
        return this.zzftz.size();
    }

    public final void zzap(zzbpu zzbpu) {
        throw new UnsupportedOperationException();
    }

    public final ListIterator<String> listIterator(int i) {
        return new zzbty(this, i);
    }

    public final Iterator<String> iterator() {
        return new zzbtz(this);
    }

    public final List<?> zzano() {
        return this.zzftz.zzano();
    }

    public final /* synthetic */ Object get(int i) {
        return (String) this.zzftz.get(i);
    }
}
