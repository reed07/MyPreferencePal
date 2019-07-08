package com.google.android.gms.tagmanager;

import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
final class zzq<K, V> {
    @VisibleForTesting
    private final zzs<K, V> zzazp = new zzr(this);

    public static zzp<K, V> zza(int i, zzs<K, V> zzs) {
        return new zzdb(ExtractorMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES, zzs);
    }
}
