package com.google.android.gms.tagmanager;

import android.annotation.TargetApi;
import android.util.LruCache;
import com.google.android.exoplayer2.source.ExtractorMediaSource;

@TargetApi(12)
final class zzdb<K, V> implements zzp<K, V> {
    private LruCache<K, V> zzbdl;

    zzdb(int i, zzs<K, V> zzs) {
        this.zzbdl = new zzdc(this, ExtractorMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES, zzs);
    }

    public final void zza(K k, V v) {
        this.zzbdl.put(k, v);
    }

    public final V get(K k) {
        return this.zzbdl.get(k);
    }
}
