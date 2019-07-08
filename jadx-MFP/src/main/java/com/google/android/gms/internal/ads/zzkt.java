package com.google.android.gms.internal.ads;

import android.os.Handler;
import java.io.IOException;

public final class zzkt {
    private final Handler handler;
    /* access modifiers changed from: private */
    public final zzks zzavv;
    private final long zzavw;

    public zzkt(Handler handler2, zzks zzks) {
        this(handler2, zzks, 0);
    }

    private zzkt(Handler handler2, zzks zzks, long j) {
        this.handler = zzks != null ? (Handler) zzpo.checkNotNull(handler2) : null;
        this.zzavv = zzks;
        this.zzavw = j;
    }

    public final zzkt zzw(long j) {
        return new zzkt(this.handler, this.zzavv, j);
    }

    public final void zza(zzoz zzoz, int i, long j) {
        zzb(zzoz, i, -1, null, 0, null, -9223372036854775807L, -9223372036854775807L, j);
    }

    public final void zzb(zzoz zzoz, int i, int i2, zzfs zzfs, int i3, Object obj, long j, long j2, long j3) {
        if (this.zzavv != null) {
            Handler handler2 = this.handler;
            zzku zzku = r0;
            zzku zzku2 = new zzku(this, zzoz, i, i2, zzfs, i3, obj, j, j2, j3);
            handler2.post(zzku);
        }
    }

    public final void zza(zzoz zzoz, int i, long j, long j2, long j3) {
        zzc(zzoz, i, -1, null, 0, null, -9223372036854775807L, -9223372036854775807L, j, j2, j3);
    }

    public final void zzc(zzoz zzoz, int i, int i2, zzfs zzfs, int i3, Object obj, long j, long j2, long j3, long j4, long j5) {
        if (this.zzavv != null) {
            zzkv zzkv = r0;
            Handler handler2 = this.handler;
            zzkv zzkv2 = new zzkv(this, zzoz, i, i2, zzfs, i3, obj, j, j2, j3, j4, j5);
            handler2.post(zzkv);
        }
    }

    public final void zzb(zzoz zzoz, int i, long j, long j2, long j3) {
        zzd(zzoz, i, -1, null, 0, null, -9223372036854775807L, -9223372036854775807L, j, j2, j3);
    }

    public final void zzd(zzoz zzoz, int i, int i2, zzfs zzfs, int i3, Object obj, long j, long j2, long j3, long j4, long j5) {
        if (this.zzavv != null) {
            zzkw zzkw = r0;
            Handler handler2 = this.handler;
            zzkw zzkw2 = new zzkw(this, zzoz, i, i2, zzfs, i3, obj, j, j2, j3, j4, j5);
            handler2.post(zzkw);
        }
    }

    public final void zza(zzoz zzoz, int i, long j, long j2, long j3, IOException iOException, boolean z) {
        zzb(zzoz, i, -1, null, 0, null, -9223372036854775807L, -9223372036854775807L, j, j2, j3, iOException, z);
    }

    public final void zzb(zzoz zzoz, int i, int i2, zzfs zzfs, int i3, Object obj, long j, long j2, long j3, long j4, long j5, IOException iOException, boolean z) {
        if (this.zzavv != null) {
            zzkx zzkx = r0;
            Handler handler2 = this.handler;
            zzkx zzkx2 = new zzkx(this, zzoz, i, i2, zzfs, i3, obj, j, j2, j3, j4, j5, iOException, z);
            handler2.post(zzkx);
        }
    }

    public final void zzb(int i, zzfs zzfs, int i2, Object obj, long j) {
        if (this.zzavv != null) {
            Handler handler2 = this.handler;
            zzky zzky = new zzky(this, i, zzfs, i2, obj, j);
            handler2.post(zzky);
        }
    }

    /* access modifiers changed from: private */
    public final long zzx(long j) {
        long zzf = zzfe.zzf(j);
        if (zzf == -9223372036854775807L) {
            return -9223372036854775807L;
        }
        return this.zzavw + zzf;
    }
}
