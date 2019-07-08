package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

final class zzwq implements Iterator<Entry<K, V>> {
    private int pos;
    private Iterator<Entry<K, V>> zzcbz;
    private final /* synthetic */ zzwo zzcca;

    private zzwq(zzwo zzwo) {
        this.zzcca = zzwo;
        this.pos = this.zzcca.zzcbu.size();
    }

    public final boolean hasNext() {
        int i = this.pos;
        return (i > 0 && i <= this.zzcca.zzcbu.size()) || zzyh().hasNext();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }

    private final Iterator<Entry<K, V>> zzyh() {
        if (this.zzcbz == null) {
            this.zzcbz = this.zzcca.zzcbx.entrySet().iterator();
        }
        return this.zzcbz;
    }

    public final /* synthetic */ Object next() {
        if (zzyh().hasNext()) {
            return (Entry) zzyh().next();
        }
        List zzb = this.zzcca.zzcbu;
        int i = this.pos - 1;
        this.pos = i;
        return (Entry) zzb.get(i);
    }

    /* synthetic */ zzwq(zzwo zzwo, zzwp zzwp) {
        this(zzwo);
    }
}
