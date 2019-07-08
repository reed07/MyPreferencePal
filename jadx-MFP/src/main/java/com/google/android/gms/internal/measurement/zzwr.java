package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.Map.Entry;

final class zzwr extends zzwx {
    private final /* synthetic */ zzwo zzcca;

    private zzwr(zzwo zzwo) {
        this.zzcca = zzwo;
        super(zzwo, null);
    }

    public final Iterator<Entry<K, V>> iterator() {
        return new zzwq(this.zzcca, null);
    }

    /* synthetic */ zzwr(zzwo zzwo, zzwp zzwp) {
        this(zzwo);
    }
}
