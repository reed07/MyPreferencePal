package com.google.android.gms.internal.ads;

import java.io.EOFException;
import java.io.IOException;

public final class zzhy implements zzii {
    public final void zza(long j, int i, int i2, int i3, zzij zzij) {
    }

    public final void zzf(zzfs zzfs) {
    }

    public final int zza(zzia zzia, int i, boolean z) throws IOException, InterruptedException {
        int zzv = zzia.zzv(i);
        if (zzv != -1) {
            return zzv;
        }
        if (z) {
            return -1;
        }
        throw new EOFException();
    }

    public final void zza(zzpx zzpx, int i) {
        zzpx.zzbl(i);
    }
}
