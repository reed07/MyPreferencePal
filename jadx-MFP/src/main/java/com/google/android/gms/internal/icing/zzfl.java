package com.google.android.gms.internal.icing;

import java.util.Iterator;
import java.util.Map.Entry;

final class zzfl extends zzfr {
    private final /* synthetic */ zzfi zznn;

    private zzfl(zzfi zzfi) {
        this.zznn = zzfi;
        super(zzfi, null);
    }

    public final Iterator<Entry<K, V>> iterator() {
        return new zzfk(this.zznn, null);
    }

    /* synthetic */ zzfl(zzfi zzfi, zzfj zzfj) {
        this(zzfi);
    }
}
