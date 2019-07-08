package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class zzpe {
    private final Map<String, String> zzbgt = new HashMap();
    private Map<String, String> zzbgu;

    public final synchronized Map<String, String> zzgx() {
        if (this.zzbgu == null) {
            this.zzbgu = Collections.unmodifiableMap(new HashMap(this.zzbgt));
        }
        return this.zzbgu;
    }
}
