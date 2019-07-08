package com.google.android.gms.measurement.internal;

public final class zzau {
    private final int priority;
    private final /* synthetic */ zzas zzamk;
    private final boolean zzaml;
    private final boolean zzamm;

    zzau(zzas zzas, int i, boolean z, boolean z2) {
        this.zzamk = zzas;
        this.priority = i;
        this.zzaml = z;
        this.zzamm = z2;
    }

    public final void zzby(String str) {
        this.zzamk.zza(this.priority, this.zzaml, this.zzamm, str, null, null, null);
    }

    public final void zzg(String str, Object obj) {
        this.zzamk.zza(this.priority, this.zzaml, this.zzamm, str, obj, null, null);
    }

    public final void zze(String str, Object obj, Object obj2) {
        this.zzamk.zza(this.priority, this.zzaml, this.zzamm, str, obj, obj2, null);
    }

    public final void zzd(String str, Object obj, Object obj2, Object obj3) {
        this.zzamk.zza(this.priority, this.zzaml, this.zzamm, str, obj, obj2, obj3);
    }
}
