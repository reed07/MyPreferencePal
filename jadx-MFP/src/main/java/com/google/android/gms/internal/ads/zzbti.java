package com.google.android.gms.internal.ads;

import java.util.Iterator;
import java.util.Map.Entry;

final class zzbti extends zzbto {
    private final /* synthetic */ zzbtf zzftq;

    private zzbti(zzbtf zzbtf) {
        this.zzftq = zzbtf;
        super(zzbtf, null);
    }

    public final Iterator<Entry<K, V>> iterator() {
        return new zzbth(this.zzftq, null);
    }

    /* synthetic */ zzbti(zzbtf zzbtf, zzbtg zzbtg) {
        this(zzbtf);
    }
}
