package com.google.android.gms.measurement.internal;

import java.util.List;
import java.util.Map;

final class zzfp implements zzay {
    private final /* synthetic */ zzfn zzaug;
    private final /* synthetic */ String zzauh;

    zzfp(zzfn zzfn, String str) {
        this.zzaug = zzfn;
        this.zzauh = str;
    }

    public final void zza(String str, int i, Throwable th, byte[] bArr, Map<String, List<String>> map) {
        this.zzaug.zza(i, th, bArr, this.zzauh);
    }
}
