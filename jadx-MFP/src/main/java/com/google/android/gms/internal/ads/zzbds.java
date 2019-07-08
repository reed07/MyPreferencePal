package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.graphics.SurfaceTexture;
import java.util.concurrent.TimeUnit;

@zzark
@TargetApi(14)
public final class zzbds {
    private final long zzerz = TimeUnit.MILLISECONDS.toNanos(((Long) zzwu.zzpz().zzd(zzaan.zzcpd)).longValue());
    private long zzesa;
    private boolean zzesb = true;

    zzbds() {
    }

    public final void zzabf() {
        this.zzesb = true;
    }

    public final void zza(SurfaceTexture surfaceTexture, zzbdh zzbdh) {
        if (zzbdh != null) {
            long timestamp = surfaceTexture.getTimestamp();
            if (this.zzesb || Math.abs(timestamp - this.zzesa) >= this.zzerz) {
                this.zzesb = false;
                this.zzesa = timestamp;
                zzayh.zzelc.post(new zzbdt(this, zzbdh));
            }
        }
    }
}
