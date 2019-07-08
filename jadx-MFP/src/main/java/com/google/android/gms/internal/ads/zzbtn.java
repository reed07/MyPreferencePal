package com.google.android.gms.internal.ads;

import java.util.Iterator;
import java.util.Map.Entry;

final class zzbtn implements Iterator<Entry<K, V>> {
    private int pos;
    private Iterator<Entry<K, V>> zzftp;
    private final /* synthetic */ zzbtf zzftq;
    private boolean zzftu;

    private zzbtn(zzbtf zzbtf) {
        this.zzftq = zzbtf;
        this.pos = -1;
    }

    public final boolean hasNext() {
        if (this.pos + 1 < this.zzftq.zzftk.size() || (!this.zzftq.zzftl.isEmpty() && zzaou().hasNext())) {
            return true;
        }
        return false;
    }

    public final void remove() {
        if (this.zzftu) {
            this.zzftu = false;
            this.zzftq.zzaos();
            if (this.pos < this.zzftq.zzftk.size()) {
                zzbtf zzbtf = this.zzftq;
                int i = this.pos;
                this.pos = i - 1;
                zzbtf.zzfz(i);
                return;
            }
            zzaou().remove();
            return;
        }
        throw new IllegalStateException("remove() was called before next()");
    }

    private final Iterator<Entry<K, V>> zzaou() {
        if (this.zzftp == null) {
            this.zzftp = this.zzftq.zzftl.entrySet().iterator();
        }
        return this.zzftp;
    }

    public final /* synthetic */ Object next() {
        this.zzftu = true;
        int i = this.pos + 1;
        this.pos = i;
        if (i < this.zzftq.zzftk.size()) {
            return (Entry) this.zzftq.zzftk.get(this.pos);
        }
        return (Entry) zzaou().next();
    }

    /* synthetic */ zzbtn(zzbtf zzbtf, zzbtg zzbtg) {
        this(zzbtf);
    }
}
