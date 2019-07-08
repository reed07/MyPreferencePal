package com.google.android.exoplayer2.upstream.cache;

import com.google.android.exoplayer2.upstream.DataSpec;

/* renamed from: com.google.android.exoplayer2.upstream.cache.-$$Lambda$u97poD-IIwob7OPYcVJkh9jokx0 reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$u97poDIIwob7OPYcVJkh9jokx0 implements CacheKeyFactory {
    public static final /* synthetic */ $$Lambda$u97poDIIwob7OPYcVJkh9jokx0 INSTANCE = new $$Lambda$u97poDIIwob7OPYcVJkh9jokx0();

    private /* synthetic */ $$Lambda$u97poDIIwob7OPYcVJkh9jokx0() {
    }

    public final String buildCacheKey(DataSpec dataSpec) {
        return CacheUtil.getKey(dataSpec);
    }
}
