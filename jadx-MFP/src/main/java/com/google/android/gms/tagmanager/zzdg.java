package com.google.android.gms.tagmanager;

import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
final class zzdg implements zzej {
    private final long zzabb = 900000;
    private final int zzabc = 5;
    private double zzabd = ((double) Math.min(1, 5));
    private long zzabe;
    private final Object zzabf = new Object();
    private final long zzbdn = DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS;
    private final Clock zzrz;
    private final String zzul;

    public zzdg(int i, int i2, long j, long j2, String str, Clock clock) {
        this.zzul = str;
        this.zzrz = clock;
    }

    public final boolean zzew() {
        synchronized (this.zzabf) {
            long currentTimeMillis = this.zzrz.currentTimeMillis();
            if (currentTimeMillis - this.zzabe < this.zzbdn) {
                String str = this.zzul;
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 34);
                sb.append("Excessive ");
                sb.append(str);
                sb.append(" detected; call ignored.");
                zzdi.zzab(sb.toString());
                return false;
            }
            if (this.zzabd < ((double) this.zzabc)) {
                double d = ((double) (currentTimeMillis - this.zzabe)) / ((double) this.zzabb);
                if (d > 0.0d) {
                    this.zzabd = Math.min((double) this.zzabc, this.zzabd + d);
                }
            }
            this.zzabe = currentTimeMillis;
            if (this.zzabd >= 1.0d) {
                this.zzabd -= 1.0d;
                return true;
            }
            String str2 = this.zzul;
            StringBuilder sb2 = new StringBuilder(String.valueOf(str2).length() + 34);
            sb2.append("Excessive ");
            sb2.append(str2);
            sb2.append(" detected; call ignored.");
            zzdi.zzab(sb2.toString());
            return false;
        }
    }
}
