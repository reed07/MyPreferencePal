package com.google.android.gms.internal.measurement;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.analytics.zzk;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public final class zzal extends zzau {
    /* access modifiers changed from: private */
    public final zzbi zzvq;

    public zzal(zzaw zzaw, zzay zzay) {
        super(zzaw);
        Preconditions.checkNotNull(zzay);
        this.zzvq = new zzbi(zzaw, zzay);
    }

    /* access modifiers changed from: protected */
    public final void zzag() {
        this.zzvq.zzq();
    }

    public final void start() {
        this.zzvq.start();
    }

    public final void setLocalDispatchPeriod(int i) {
        zzcl();
        zzb("setLocalDispatchPeriod (sec)", Integer.valueOf(i));
        zzca().zza((Runnable) new zzam(this, i));
    }

    public final long zza(zzaz zzaz) {
        zzcl();
        Preconditions.checkNotNull(zzaz);
        zzk.zzaf();
        long zza = this.zzvq.zza(zzaz, true);
        if (zza == 0) {
            this.zzvq.zzb(zzaz);
        }
        return zza;
    }

    public final void zza(zzck zzck) {
        Preconditions.checkNotNull(zzck);
        zzcl();
        zzb("Hit delivery requested", zzck);
        zzca().zza((Runnable) new zzap(this, zzck));
    }

    public final void zzbr() {
        zzcl();
        zzca().zza((Runnable) new zzaq(this));
    }

    public final void zza(zzcd zzcd) {
        zzcl();
        zzca().zza((Runnable) new zzar(this, zzcd));
    }

    public final void zzbs() {
        zzcl();
        Context context = getContext();
        if (!zzcw.zza(context) || !zzcx.zze(context)) {
            zza((zzcd) null);
            return;
        }
        Intent intent = new Intent("com.google.android.gms.analytics.ANALYTICS_DISPATCH");
        intent.setComponent(new ComponentName(context, "com.google.android.gms.analytics.AnalyticsService"));
        context.startService(intent);
    }

    public final boolean zzbt() {
        zzcl();
        try {
            zzca().zza((Callable<V>) new zzas<V>(this)).get(4, TimeUnit.SECONDS);
            return true;
        } catch (InterruptedException e) {
            zzd("syncDispatchLocalHits interrupted", e);
            return false;
        } catch (ExecutionException e2) {
            zze("syncDispatchLocalHits failed", e2);
            return false;
        } catch (TimeoutException e3) {
            zzd("syncDispatchLocalHits timed out", e3);
            return false;
        }
    }

    public final void zzbu() {
        zzcl();
        zzk.zzaf();
        zzbi zzbi = this.zzvq;
        zzk.zzaf();
        zzbi.zzcl();
        zzbi.zzq("Service disconnected");
    }

    /* access modifiers changed from: 0000 */
    public final void onServiceConnected() {
        zzk.zzaf();
        this.zzvq.onServiceConnected();
    }

    /* access modifiers changed from: 0000 */
    public final void zzbv() {
        zzk.zzaf();
        this.zzvq.zzbv();
    }

    public final void zza(String str, Runnable runnable) {
        Preconditions.checkNotEmpty(str, "campaign param can't be empty");
        zzca().zza((Runnable) new zzao(this, str, runnable));
    }
}
