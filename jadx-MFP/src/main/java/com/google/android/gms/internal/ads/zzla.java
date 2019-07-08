package com.google.android.gms.internal.ads;

public final class zzla implements zzlw {
    private final zzlw[] zzawm;

    public zzla(zzlw[] zzlwArr) {
        this.zzawm = zzlwArr;
    }

    public final long zzeu() {
        long j = Long.MAX_VALUE;
        for (zzlw zzeu : this.zzawm) {
            long zzeu2 = zzeu.zzeu();
            if (zzeu2 != Long.MIN_VALUE) {
                j = Math.min(j, zzeu2);
            }
        }
        if (j == Long.MAX_VALUE) {
            return Long.MIN_VALUE;
        }
        return j;
    }

    public final boolean zzy(long j) {
        zzlw[] zzlwArr;
        boolean z;
        boolean z2 = false;
        do {
            long zzeu = zzeu();
            if (zzeu == Long.MIN_VALUE) {
                break;
            }
            z = false;
            for (zzlw zzlw : this.zzawm) {
                if (zzlw.zzeu() == zzeu) {
                    z |= zzlw.zzy(j);
                }
            }
            z2 |= z;
        } while (z);
        return z2;
    }
}
