package com.google.android.gms.internal.measurement;

import java.util.Collections;
import java.util.Map;

public final class zzri {
    private final zzp zzbfs;
    private final Map<String, zzp> zzboz;

    private zzri(Map<String, zzp> map, zzp zzp) {
        this.zzboz = map;
        this.zzbfs = zzp;
    }

    public static zzrj zzsz() {
        return new zzrj();
    }

    public final void zza(String str, zzp zzp) {
        this.zzboz.put(str, zzp);
    }

    public final Map<String, zzp> zzsi() {
        return Collections.unmodifiableMap(this.zzboz);
    }

    public final zzp zzpw() {
        return this.zzbfs;
    }

    public final String toString() {
        String valueOf = String.valueOf(Collections.unmodifiableMap(this.zzboz));
        String valueOf2 = String.valueOf(this.zzbfs);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 32 + String.valueOf(valueOf2).length());
        sb.append("Properties: ");
        sb.append(valueOf);
        sb.append(" pushAfterEvaluate: ");
        sb.append(valueOf2);
        return sb.toString();
    }
}
