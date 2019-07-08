package com.google.android.gms.ads.internal;

import android.support.annotation.Nullable;
import com.google.android.exoplayer2.upstream.DefaultLoadErrorHandlingPolicy;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.zzark;
import com.google.android.gms.internal.ads.zzaxz;
import com.google.android.gms.internal.ads.zzayh;
import com.google.android.gms.internal.ads.zzwb;
import java.lang.ref.WeakReference;
import javax.annotation.ParametersAreNonnullByDefault;

@zzark
@ParametersAreNonnullByDefault
public final class zzbl {
    private final zzbn zzbqn;
    /* access modifiers changed from: private */
    @Nullable
    public zzwb zzbqo;
    /* access modifiers changed from: private */
    public boolean zzbqp;
    private boolean zzbqq;
    private long zzbqr;
    private final Runnable zzy;

    public zzbl(zza zza) {
        this(zza, new zzbn(zzayh.zzelc));
    }

    @VisibleForTesting
    private zzbl(zza zza, zzbn zzbn) {
        this.zzbqp = false;
        this.zzbqq = false;
        this.zzbqr = 0;
        this.zzbqn = zzbn;
        this.zzy = new zzbm(this, new WeakReference(zza));
    }

    public final void zzf(zzwb zzwb) {
        this.zzbqo = zzwb;
    }

    public final void cancel() {
        this.zzbqp = false;
        this.zzbqn.removeCallbacks(this.zzy);
    }

    public final void pause() {
        this.zzbqq = true;
        if (this.zzbqp) {
            this.zzbqn.removeCallbacks(this.zzy);
        }
    }

    public final void resume() {
        this.zzbqq = false;
        if (this.zzbqp) {
            this.zzbqp = false;
            zza(this.zzbqo, this.zzbqr);
        }
    }

    public final void zzku() {
        this.zzbqq = false;
        this.zzbqp = false;
        zzwb zzwb = this.zzbqo;
        if (!(zzwb == null || zzwb.extras == null)) {
            this.zzbqo.extras.remove("_ad");
        }
        zza(this.zzbqo, 0);
    }

    public final boolean zzkv() {
        return this.zzbqp;
    }

    public final void zzg(zzwb zzwb) {
        zza(zzwb, (long) DefaultLoadErrorHandlingPolicy.DEFAULT_TRACK_BLACKLIST_MS);
    }

    public final void zza(zzwb zzwb, long j) {
        if (this.zzbqp) {
            zzaxz.zzeo("An ad refresh is already scheduled.");
            return;
        }
        this.zzbqo = zzwb;
        this.zzbqp = true;
        this.zzbqr = j;
        if (!this.zzbqq) {
            StringBuilder sb = new StringBuilder(65);
            sb.append("Scheduling ad refresh ");
            sb.append(j);
            sb.append(" milliseconds from now.");
            zzaxz.zzen(sb.toString());
            this.zzbqn.postDelayed(this.zzy, j);
        }
    }
}
