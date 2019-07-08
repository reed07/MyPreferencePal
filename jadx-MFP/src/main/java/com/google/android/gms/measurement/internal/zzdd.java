package com.google.android.gms.measurement.internal;

final class zzdd implements Runnable {
    private final /* synthetic */ String val$name;
    private final /* synthetic */ String zzads;
    private final /* synthetic */ zzda zzarh;
    private final /* synthetic */ long zzari;
    private final /* synthetic */ Object zzarm;

    zzdd(zzda zzda, String str, String str2, Object obj, long j) {
        this.zzarh = zzda;
        this.zzads = str;
        this.val$name = str2;
        this.zzarm = obj;
        this.zzari = j;
    }

    public final void run() {
        this.zzarh.zza(this.zzads, this.val$name, this.zzarm, this.zzari);
    }
}
