package com.google.android.gms.internal.ads;

final class zzakv implements Runnable {
    private final /* synthetic */ zzakt zzdmx;
    private final /* synthetic */ zzaku zzdmy;

    zzakv(zzaku zzaku, zzakt zzakt) {
        this.zzdmy = zzaku;
        this.zzdmx = zzakt;
    }

    public final void run() {
        synchronized (this.zzdmy.mLock) {
            if (this.zzdmy.zzdmv == -2) {
                this.zzdmy.zzdmu = this.zzdmy.zzuo();
                if (this.zzdmy.zzdmu == null) {
                    this.zzdmy.zzco(4);
                } else if (!this.zzdmy.zzup() || this.zzdmy.zzcp(1)) {
                    this.zzdmx.zza((zzaky) this.zzdmy);
                    this.zzdmy.zza(this.zzdmx);
                } else {
                    String zzf = this.zzdmy.zzdml;
                    StringBuilder sb = new StringBuilder(String.valueOf(zzf).length() + 56);
                    sb.append("Ignoring adapter ");
                    sb.append(zzf);
                    sb.append(" as delayed impression is not supported");
                    zzaxz.zzeo(sb.toString());
                    this.zzdmy.zzco(2);
                }
            }
        }
    }
}
