package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.Map.Entry;

final class zzww implements Iterator<Entry<K, V>> {
    private int pos;
    private Iterator<Entry<K, V>> zzcbz;
    private final /* synthetic */ zzwo zzcca;
    private boolean zzcce;

    private zzww(zzwo zzwo) {
        this.zzcca = zzwo;
        this.pos = -1;
    }

    public final boolean hasNext() {
        if (this.pos + 1 < this.zzcca.zzcbu.size() || (!this.zzcca.zzcbv.isEmpty() && zzyh().hasNext())) {
            return true;
        }
        return false;
    }

    public final void remove() {
        if (this.zzcce) {
            this.zzcce = false;
            this.zzcca.zzyf();
            if (this.pos < this.zzcca.zzcbu.size()) {
                zzwo zzwo = this.zzcca;
                int i = this.pos;
                this.pos = i - 1;
                zzwo.zzby(i);
                return;
            }
            zzyh().remove();
            return;
        }
        throw new IllegalStateException("remove() was called before next()");
    }

    private final Iterator<Entry<K, V>> zzyh() {
        if (this.zzcbz == null) {
            this.zzcbz = this.zzcca.zzcbv.entrySet().iterator();
        }
        return this.zzcbz;
    }

    public final /* synthetic */ Object next() {
        this.zzcce = true;
        int i = this.pos + 1;
        this.pos = i;
        if (i < this.zzcca.zzcbu.size()) {
            return (Entry) this.zzcca.zzcbu.get(this.pos);
        }
        return (Entry) zzyh().next();
    }

    /* synthetic */ zzww(zzwo zzwo, zzwp zzwp) {
        this(zzwo);
    }
}
