package com.google.android.gms.internal.measurement;

import java.io.PrintStream;
import java.util.List;

final class zzsv extends zzss {
    private final zzst zzbsl = new zzst();

    zzsv() {
    }

    public final void zza(Throwable th, PrintStream printStream) {
        th.printStackTrace(printStream);
        List<Throwable> zza = this.zzbsl.zza(th, false);
        if (zza != null) {
            synchronized (zza) {
                for (Throwable th2 : zza) {
                    printStream.print("Suppressed: ");
                    th2.printStackTrace(printStream);
                }
            }
        }
    }
}
