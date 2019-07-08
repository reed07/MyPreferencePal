package com.google.android.gms.internal.ads;

import android.os.Trace;

public final class zzqc {
    public static void beginSection(String str) {
        if (zzqe.SDK_INT >= 18) {
            Trace.beginSection(str);
        }
    }

    public static void endSection() {
        if (zzqe.SDK_INT >= 18) {
            Trace.endSection();
        }
    }
}
