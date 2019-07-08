package com.google.android.gms.internal.ads;

import java.io.PrintWriter;
import java.util.List;

final class zzbpi extends zzbpf {
    private final zzbpg zzfkt = new zzbpg();

    zzbpi() {
    }

    public final void zze(Throwable th) {
        th.printStackTrace();
        List<Throwable> zza = this.zzfkt.zza(th, false);
        if (zza != null) {
            synchronized (zza) {
                for (Throwable th2 : zza) {
                    System.err.print("Suppressed: ");
                    th2.printStackTrace();
                }
            }
        }
    }

    public final void zza(Throwable th, PrintWriter printWriter) {
        th.printStackTrace(printWriter);
        List<Throwable> zza = this.zzfkt.zza(th, false);
        if (zza != null) {
            synchronized (zza) {
                for (Throwable th2 : zza) {
                    printWriter.print("Suppressed: ");
                    th2.printStackTrace(printWriter);
                }
            }
        }
    }
}
