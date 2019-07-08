package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.util.VisibleForTesting;
import java.util.HashMap;
import java.util.Map;

@VisibleForTesting
public final class zzrj {
    private zzp zzbfs;
    private final Map<String, zzp> zzboz;

    private zzrj() {
        this.zzboz = new HashMap();
    }

    public final zzrj zzb(String str, zzp zzp) {
        this.zzboz.put(str, zzp);
        return this;
    }

    public final zzrj zzm(zzp zzp) {
        this.zzbfs = zzp;
        return this;
    }

    public final zzri zzta() {
        return new zzri(this.zzboz, this.zzbfs);
    }
}
