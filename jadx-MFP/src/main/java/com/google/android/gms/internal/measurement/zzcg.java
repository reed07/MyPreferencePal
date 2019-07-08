package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.config.GservicesValue;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.util.VisibleForTesting;

@ShowFirstParty
@VisibleForTesting
public final class zzcg<V> {
    private final V zzaan;
    private final GservicesValue<V> zzaao;

    private zzcg(GservicesValue<V> gservicesValue, V v) {
        Preconditions.checkNotNull(gservicesValue);
        this.zzaao = gservicesValue;
        this.zzaan = v;
    }

    static zzcg<Boolean> zza(String str, boolean z, boolean z2) {
        return new zzcg<>(GservicesValue.value(str, z2), Boolean.valueOf(z));
    }

    static zzcg<String> zza(String str, String str2, String str3) {
        return new zzcg<>(GservicesValue.value(str, str3), str2);
    }

    static zzcg<Long> zza(String str, long j, long j2) {
        return new zzcg<>(GservicesValue.value(str, Long.valueOf(j2)), Long.valueOf(j));
    }

    static zzcg<Integer> zza(String str, int i, int i2) {
        return new zzcg<>(GservicesValue.value(str, Integer.valueOf(i2)), Integer.valueOf(i));
    }

    static zzcg<Float> zza(String str, float f, float f2) {
        return new zzcg<>(GservicesValue.value(str, Float.valueOf(0.5f)), Float.valueOf(0.5f));
    }

    public final V get() {
        return this.zzaan;
    }
}
