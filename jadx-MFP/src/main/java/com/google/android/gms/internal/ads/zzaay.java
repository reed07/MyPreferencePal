package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@zzark
@ParametersAreNonnullByDefault
public final class zzaay {
    private final long zzczf;
    @Nullable
    private final String zzczg;
    @Nullable
    private final zzaay zzczh;

    public zzaay(long j, @Nullable String str, @Nullable zzaay zzaay) {
        this.zzczf = j;
        this.zzczg = str;
        this.zzczh = zzaay;
    }

    public final long getTime() {
        return this.zzczf;
    }

    public final String zzrd() {
        return this.zzczg;
    }

    @Nullable
    public final zzaay zzre() {
        return this.zzczh;
    }
}
