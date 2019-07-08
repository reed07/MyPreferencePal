package com.google.android.gms.internal.icing;

import java.util.Iterator;
import java.util.Map.Entry;

final class zzfq implements Iterator<Entry<K, V>> {
    private int pos;
    private Iterator<Entry<K, V>> zznm;
    private final /* synthetic */ zzfi zznn;
    private boolean zznr;

    private zzfq(zzfi zzfi) {
        this.zznn = zzfi;
        this.pos = -1;
    }

    public final boolean hasNext() {
        if (this.pos + 1 < this.zznn.zznh.size() || (!this.zznn.zzni.isEmpty() && zzdm().hasNext())) {
            return true;
        }
        return false;
    }

    public final void remove() {
        if (this.zznr) {
            this.zznr = false;
            this.zznn.zzdk();
            if (this.pos < this.zznn.zznh.size()) {
                zzfi zzfi = this.zznn;
                int i = this.pos;
                this.pos = i - 1;
                zzfi.zzak(i);
                return;
            }
            zzdm().remove();
            return;
        }
        throw new IllegalStateException("remove() was called before next()");
    }

    private final Iterator<Entry<K, V>> zzdm() {
        if (this.zznm == null) {
            this.zznm = this.zznn.zzni.entrySet().iterator();
        }
        return this.zznm;
    }

    public final /* synthetic */ Object next() {
        this.zznr = true;
        int i = this.pos + 1;
        this.pos = i;
        if (i < this.zznn.zznh.size()) {
            return (Entry) this.zznn.zznh.get(this.pos);
        }
        return (Entry) zzdm().next();
    }

    /* synthetic */ zzfq(zzfi zzfi, zzfj zzfj) {
        this(zzfi);
    }
}
