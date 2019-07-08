package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzdl;

public final class zzfd extends zzf {
    private Handler handler;
    @VisibleForTesting
    private long zzatb = zzbx().elapsedRealtime();
    @VisibleForTesting
    private long zzatc = this.zzatb;
    private final zzy zzatd = new zzfe(this, this.zzada);
    private final zzy zzate = new zzff(this, this.zzada);

    zzfd(zzbw zzbw) {
        super(zzbw);
    }

    /* access modifiers changed from: protected */
    public final boolean zzgy() {
        return false;
    }

    private final void zzlm() {
        synchronized (this) {
            if (this.handler == null) {
                this.handler = new zzdl(Looper.getMainLooper());
            }
        }
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final void zzln() {
        zzaf();
        this.zzatd.cancel();
        this.zzate.cancel();
        this.zzatb = 0;
        this.zzatc = this.zzatb;
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void zzai(long j) {
        zzaf();
        zzlm();
        if (zzgv().zze(zzgk().zzal(), zzai.zzaky)) {
            zzgu().zzant.set(false);
        }
        zzgt().zzjo().zzg("Activity resumed, time", Long.valueOf(j));
        this.zzatb = j;
        this.zzatc = this.zzatb;
        if (zzgv().zzbi(zzgk().zzal())) {
            zzaj(zzbx().currentTimeMillis());
            return;
        }
        this.zzatd.cancel();
        this.zzate.cancel();
        if (zzgu().zzaf(zzbx().currentTimeMillis())) {
            zzgu().zzanp.set(true);
            zzgu().zzanr.set(0);
        }
        if (zzgu().zzanp.get()) {
            this.zzatd.zzh(Math.max(0, zzgu().zzann.get() - zzgu().zzanr.get()));
        } else {
            this.zzate.zzh(Math.max(0, 3600000 - zzgu().zzanr.get()));
        }
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final void zzaj(long j) {
        zzaf();
        zzlm();
        zza(j, false);
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final void zza(long j, boolean z) {
        zzaf();
        zzlm();
        this.zzatd.cancel();
        this.zzate.cancel();
        if (zzgu().zzaf(j)) {
            zzgu().zzanp.set(true);
            zzgu().zzanr.set(0);
        }
        if (z && zzgv().zzbj(zzgk().zzal())) {
            zzgu().zzanq.set(j);
        }
        if (zzgu().zzanp.get()) {
            zzal(j);
        } else {
            this.zzate.zzh(Math.max(0, 3600000 - zzgu().zzanr.get()));
        }
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void zzak(long j) {
        zzaf();
        zzlm();
        if (zzgv().zze(zzgk().zzal(), zzai.zzaky)) {
            zzgu().zzant.set(true);
        }
        this.zzatd.cancel();
        this.zzate.cancel();
        zzgt().zzjo().zzg("Activity paused, time", Long.valueOf(j));
        if (this.zzatb != 0) {
            zzgu().zzanr.set(zzgu().zzanr.get() + (j - this.zzatb));
        }
    }

    @WorkerThread
    private final void zzal(long j) {
        zzaf();
        zzgt().zzjo().zzg("Session started, time", Long.valueOf(zzbx().elapsedRealtime()));
        Long l = null;
        Long valueOf = zzgv().zzbg(zzgk().zzal()) ? Long.valueOf(j / 1000) : null;
        if (zzgv().zzbh(zzgk().zzal())) {
            l = Long.valueOf(-1);
        }
        long j2 = j;
        zzgj().zza("auto", "_sid", (Object) valueOf, j2);
        zzgj().zza("auto", "_sno", (Object) l, j2);
        zzgu().zzanp.set(false);
        Bundle bundle = new Bundle();
        if (zzgv().zzbg(zzgk().zzal())) {
            bundle.putLong("_sid", valueOf.longValue());
        }
        zzgj().zza("auto", "_s", j, bundle);
        zzgu().zzanq.set(j);
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    @VisibleForTesting
    public final void zzlo() {
        zzaf();
        zzal(zzbx().currentTimeMillis());
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    @VisibleForTesting
    public final long zzlp() {
        long elapsedRealtime = zzbx().elapsedRealtime();
        long j = elapsedRealtime - this.zzatc;
        this.zzatc = elapsedRealtime;
        return j;
    }

    @WorkerThread
    public final boolean zza(boolean z, boolean z2) {
        zzaf();
        zzcl();
        long elapsedRealtime = zzbx().elapsedRealtime();
        zzgu().zzanq.set(zzbx().currentTimeMillis());
        long j = elapsedRealtime - this.zzatb;
        if (z || j >= 1000) {
            zzgu().zzanr.set(j);
            zzgt().zzjo().zzg("Recording user engagement, ms", Long.valueOf(j));
            Bundle bundle = new Bundle();
            bundle.putLong("_et", j);
            zzdy.zza(zzgm().zzle(), bundle, true);
            if (zzgv().zzbk(zzgk().zzal())) {
                if (zzgv().zze(zzgk().zzal(), zzai.zzalc)) {
                    if (!z2) {
                        zzlp();
                    }
                } else if (z2) {
                    bundle.putLong("_fr", 1);
                } else {
                    zzlp();
                }
            }
            if (!zzgv().zze(zzgk().zzal(), zzai.zzalc) || !z2) {
                zzgj().logEvent("auto", "_e", bundle);
            }
            this.zzatb = elapsedRealtime;
            this.zzate.cancel();
            this.zzate.zzh(Math.max(0, 3600000 - zzgu().zzanr.get()));
            return true;
        }
        zzgt().zzjo().zzg("Screen exposed for less than 1000 ms. Event not sent. time", Long.valueOf(j));
        return false;
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void zzlq() {
        zzaf();
        zza(false, false);
        zzgi().zzm(zzbx().elapsedRealtime());
    }

    public final /* bridge */ /* synthetic */ void zzgf() {
        super.zzgf();
    }

    public final /* bridge */ /* synthetic */ void zzgg() {
        super.zzgg();
    }

    public final /* bridge */ /* synthetic */ void zzgh() {
        super.zzgh();
    }

    public final /* bridge */ /* synthetic */ void zzaf() {
        super.zzaf();
    }

    public final /* bridge */ /* synthetic */ zza zzgi() {
        return super.zzgi();
    }

    public final /* bridge */ /* synthetic */ zzda zzgj() {
        return super.zzgj();
    }

    public final /* bridge */ /* synthetic */ zzam zzgk() {
        return super.zzgk();
    }

    public final /* bridge */ /* synthetic */ zzeb zzgl() {
        return super.zzgl();
    }

    public final /* bridge */ /* synthetic */ zzdy zzgm() {
        return super.zzgm();
    }

    public final /* bridge */ /* synthetic */ zzao zzgn() {
        return super.zzgn();
    }

    public final /* bridge */ /* synthetic */ zzfd zzgo() {
        return super.zzgo();
    }

    public final /* bridge */ /* synthetic */ zzaa zzgp() {
        return super.zzgp();
    }

    public final /* bridge */ /* synthetic */ Clock zzbx() {
        return super.zzbx();
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    public final /* bridge */ /* synthetic */ zzaq zzgq() {
        return super.zzgq();
    }

    public final /* bridge */ /* synthetic */ zzfx zzgr() {
        return super.zzgr();
    }

    public final /* bridge */ /* synthetic */ zzbr zzgs() {
        return super.zzgs();
    }

    public final /* bridge */ /* synthetic */ zzas zzgt() {
        return super.zzgt();
    }

    public final /* bridge */ /* synthetic */ zzbd zzgu() {
        return super.zzgu();
    }

    public final /* bridge */ /* synthetic */ zzq zzgv() {
        return super.zzgv();
    }

    public final /* bridge */ /* synthetic */ zzn zzgw() {
        return super.zzgw();
    }
}
