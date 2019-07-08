package com.google.android.gms.internal.icing;

import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

final class zzfk implements Iterator<Entry<K, V>> {
    private int pos;
    private Iterator<Entry<K, V>> zznm;
    private final /* synthetic */ zzfi zznn;

    private zzfk(zzfi zzfi) {
        this.zznn = zzfi;
        this.pos = this.zznn.zznh.size();
    }

    public final boolean hasNext() {
        int i = this.pos;
        return (i > 0 && i <= this.zznn.zznh.size()) || zzdm().hasNext();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }

    private final Iterator<Entry<K, V>> zzdm() {
        if (this.zznm == null) {
            this.zznm = this.zznn.zznk.entrySet().iterator();
        }
        return this.zznm;
    }

    public final /* synthetic */ Object next() {
        if (zzdm().hasNext()) {
            return (Entry) zzdm().next();
        }
        List zzb = this.zznn.zznh;
        int i = this.pos - 1;
        this.pos = i;
        return (Entry) zzb.get(i);
    }

    /* synthetic */ zzfk(zzfi zzfi, zzfj zzfj) {
        this(zzfi);
    }
}
