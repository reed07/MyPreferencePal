package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzo;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

final class zzes implements zzag {
    private boolean closed;
    /* access modifiers changed from: private */
    public final String zzazq;
    private String zzbar;
    private zzdh<zzo> zzbes;
    private zzal zzbet;
    private final ScheduledExecutorService zzbev;
    private final zzev zzbew;
    private ScheduledFuture<?> zzbex;
    /* access modifiers changed from: private */
    public final Context zzri;

    public zzes(Context context, String str, zzal zzal) {
        this(context, str, zzal, null, null);
    }

    @VisibleForTesting
    private zzes(Context context, String str, zzal zzal, zzew zzew, zzev zzev) {
        this.zzbet = zzal;
        this.zzri = context;
        this.zzazq = str;
        this.zzbev = new zzet(this).zzpq();
        this.zzbew = new zzeu(this);
    }

    public final synchronized void release() {
        zzpp();
        if (this.zzbex != null) {
            this.zzbex.cancel(false);
        }
        this.zzbev.shutdown();
        this.closed = true;
    }

    public final synchronized void zza(zzdh<zzo> zzdh) {
        zzpp();
        this.zzbes = zzdh;
    }

    public final synchronized void zza(long j, String str) {
        String str2 = this.zzazq;
        StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 55);
        sb.append("loadAfterDelay: containerId=");
        sb.append(str2);
        sb.append(" delay=");
        sb.append(j);
        zzdi.v(sb.toString());
        zzpp();
        if (this.zzbes != null) {
            if (this.zzbex != null) {
                this.zzbex.cancel(false);
            }
            ScheduledExecutorService scheduledExecutorService = this.zzbev;
            zzer zza = this.zzbew.zza(this.zzbet);
            zza.zza(this.zzbes);
            zza.zzdg(this.zzbar);
            zza.zzdy(str);
            this.zzbex = scheduledExecutorService.schedule(zza, j, TimeUnit.MILLISECONDS);
        } else {
            throw new IllegalStateException("callback must be set before loadAfterDelay() is called.");
        }
    }

    public final synchronized void zzdg(String str) {
        zzpp();
        this.zzbar = str;
    }

    private final synchronized void zzpp() {
        if (this.closed) {
            throw new IllegalStateException("called method after closed");
        }
    }
}
