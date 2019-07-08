package com.google.android.gms.measurement.internal;

import android.os.Bundle;

final class zzdc implements Runnable {
    private final /* synthetic */ String val$name;
    private final /* synthetic */ String zzads;
    private final /* synthetic */ boolean zzaer;
    private final /* synthetic */ String zzagj;
    private final /* synthetic */ zzda zzarh;
    private final /* synthetic */ long zzari;
    private final /* synthetic */ Bundle zzarj;
    private final /* synthetic */ boolean zzark;
    private final /* synthetic */ boolean zzarl;

    zzdc(zzda zzda, String str, String str2, long j, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) {
        this.zzarh = zzda;
        this.zzads = str;
        this.val$name = str2;
        this.zzari = j;
        this.zzarj = bundle;
        this.zzaer = z;
        this.zzark = z2;
        this.zzarl = z3;
        this.zzagj = str3;
    }

    public final void run() {
        this.zzarh.zza(this.zzads, this.val$name, this.zzari, this.zzarj, this.zzaer, this.zzark, this.zzarl, this.zzagj);
    }
}
