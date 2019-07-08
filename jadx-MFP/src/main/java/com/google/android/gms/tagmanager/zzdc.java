package com.google.android.gms.tagmanager;

import android.util.LruCache;

final class zzdc extends LruCache<K, V> {
    private final /* synthetic */ zzs zzbdm;

    zzdc(zzdb zzdb, int i, zzs zzs) {
        this.zzbdm = zzs;
        super(i);
    }

    /* access modifiers changed from: protected */
    public final int sizeOf(K k, V v) {
        return this.zzbdm.sizeOf(k, v);
    }
}
