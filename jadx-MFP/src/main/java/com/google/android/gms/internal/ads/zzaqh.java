package com.google.android.gms.internal.ads;

final /* synthetic */ class zzaqh implements Runnable {
    private final zzaqf zzdui;
    private final boolean zzduj = true;
    private final zzbcl zzdul;
    private final String zzdum;
    private final String zzdun;

    zzaqh(zzaqf zzaqf, boolean z, zzbcl zzbcl, String str, String str2) {
        this.zzdui = zzaqf;
        this.zzdul = zzbcl;
        this.zzdum = str;
        this.zzdun = str2;
    }

    public final void run() {
        this.zzdui.zza(this.zzduj, this.zzdul, this.zzdum, this.zzdun);
    }
}
