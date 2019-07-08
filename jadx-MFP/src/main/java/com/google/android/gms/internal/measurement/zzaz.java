package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.internal.Preconditions;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class zzaz {
    private final Map<String, String> zzsy;
    private final String zzup;
    private final long zzws = 0;
    private final String zzwt;
    private final boolean zzwu;
    private long zzwv;

    public zzaz(long j, String str, String str2, boolean z, long j2, Map<String, String> map) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        this.zzup = str;
        this.zzwt = str2;
        this.zzwu = z;
        this.zzwv = j2;
        if (map != null) {
            this.zzsy = new HashMap(map);
        } else {
            this.zzsy = Collections.emptyMap();
        }
    }

    public final long zzcs() {
        return this.zzws;
    }

    public final String zzbd() {
        return this.zzup;
    }

    public final String zzct() {
        return this.zzwt;
    }

    public final boolean zzcu() {
        return this.zzwu;
    }

    public final long zzcv() {
        return this.zzwv;
    }

    public final void zzb(long j) {
        this.zzwv = j;
    }

    public final Map<String, String> zzcw() {
        return this.zzsy;
    }
}
