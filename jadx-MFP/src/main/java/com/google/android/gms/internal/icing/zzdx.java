package com.google.android.gms.internal.icing;

import java.util.Iterator;
import java.util.Map.Entry;

final class zzdx<K> implements Iterator<Entry<K, Object>> {
    private Iterator<Entry<K, Object>> zzlj;

    public zzdx(Iterator<Entry<K, Object>> it) {
        this.zzlj = it;
    }

    public final boolean hasNext() {
        return this.zzlj.hasNext();
    }

    public final void remove() {
        this.zzlj.remove();
    }

    public final /* synthetic */ Object next() {
        Entry entry = (Entry) this.zzlj.next();
        return entry.getValue() instanceof zzdu ? new zzdw(entry) : entry;
    }
}
