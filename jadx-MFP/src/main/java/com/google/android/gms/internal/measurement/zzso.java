package com.google.android.gms.internal.measurement;

import android.net.Uri;

public final class zzso {
    private final String zzbru;
    /* access modifiers changed from: private */
    public final Uri zzbrv;
    /* access modifiers changed from: private */
    public final String zzbrw;
    /* access modifiers changed from: private */
    public final String zzbrx;
    private final boolean zzbry;
    private final boolean zzbrz;
    private final boolean zzbsa;

    public zzso(Uri uri) {
        this(null, uri, "", "", false, false, false);
    }

    private zzso(String str, Uri uri, String str2, String str3, boolean z, boolean z2, boolean z3) {
        this.zzbru = null;
        this.zzbrv = uri;
        this.zzbrw = str2;
        this.zzbrx = str3;
        this.zzbry = false;
        this.zzbrz = false;
        this.zzbsa = false;
    }

    public final zzsi<Long> zze(String str, long j) {
        return zzsi.zza(this, str, j);
    }

    public final zzsi<Boolean> zzd(String str, boolean z) {
        return zzsi.zza(this, str, z);
    }

    public final zzsi<Integer> zzd(String str, int i) {
        return zzsi.zza(this, str, i);
    }

    public final zzsi<Double> zzb(String str, double d) {
        return zzsi.zza(this, str, d);
    }

    public final zzsi<String> zzy(String str, String str2) {
        return zzsi.zza(this, str, str2);
    }
}
