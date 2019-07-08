package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import java.util.Map;

public final class zza extends zze {
    private final Map<String, Long> zzafb = new ArrayMap();
    private final Map<String, Integer> zzafc = new ArrayMap();
    private long zzafd;

    public zza(zzbw zzbw) {
        super(zzbw);
    }

    public final void beginAdUnitExposure(String str, long j) {
        if (str == null || str.length() == 0) {
            zzgt().zzjg().zzby("Ad unit id must be a non-empty string");
        } else {
            zzgs().zzc((Runnable) new zzb(this, str, j));
        }
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void zza(String str, long j) {
        zzgg();
        zzaf();
        Preconditions.checkNotEmpty(str);
        if (this.zzafc.isEmpty()) {
            this.zzafd = j;
        }
        Integer num = (Integer) this.zzafc.get(str);
        if (num != null) {
            this.zzafc.put(str, Integer.valueOf(num.intValue() + 1));
        } else if (this.zzafc.size() >= 100) {
            zzgt().zzjj().zzby("Too many ads visible");
        } else {
            this.zzafc.put(str, Integer.valueOf(1));
            this.zzafb.put(str, Long.valueOf(j));
        }
    }

    public final void endAdUnitExposure(String str, long j) {
        if (str == null || str.length() == 0) {
            zzgt().zzjg().zzby("Ad unit id must be a non-empty string");
        } else {
            zzgs().zzc((Runnable) new zzc(this, str, j));
        }
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void zzb(String str, long j) {
        zzgg();
        zzaf();
        Preconditions.checkNotEmpty(str);
        Integer num = (Integer) this.zzafc.get(str);
        if (num != null) {
            zzdx zzle = zzgm().zzle();
            int intValue = num.intValue() - 1;
            if (intValue == 0) {
                this.zzafc.remove(str);
                Long l = (Long) this.zzafb.get(str);
                if (l == null) {
                    zzgt().zzjg().zzby("First ad unit exposure time was never set");
                } else {
                    long longValue = j - l.longValue();
                    this.zzafb.remove(str);
                    zza(str, longValue, zzle);
                }
                if (this.zzafc.isEmpty()) {
                    long j2 = this.zzafd;
                    if (j2 == 0) {
                        zzgt().zzjg().zzby("First ad exposure time was never set");
                        return;
                    } else {
                        zza(j - j2, zzle);
                        this.zzafd = 0;
                    }
                }
                return;
            }
            this.zzafc.put(str, Integer.valueOf(intValue));
            return;
        }
        zzgt().zzjg().zzg("Call to endAdUnitExposure for unknown ad unit id", str);
    }

    @WorkerThread
    private final void zza(long j, zzdx zzdx) {
        if (zzdx == null) {
            zzgt().zzjo().zzby("Not logging ad exposure. No active activity");
        } else if (j < 1000) {
            zzgt().zzjo().zzg("Not logging ad exposure. Less than 1000 ms. exposure", Long.valueOf(j));
        } else {
            Bundle bundle = new Bundle();
            bundle.putLong("_xt", j);
            zzdy.zza(zzdx, bundle, true);
            zzgj().logEvent("am", "_xa", bundle);
        }
    }

    @WorkerThread
    private final void zza(String str, long j, zzdx zzdx) {
        if (zzdx == null) {
            zzgt().zzjo().zzby("Not logging ad unit exposure. No active activity");
        } else if (j < 1000) {
            zzgt().zzjo().zzg("Not logging ad unit exposure. Less than 1000 ms. exposure", Long.valueOf(j));
        } else {
            Bundle bundle = new Bundle();
            bundle.putString("_ai", str);
            bundle.putLong("_xt", j);
            zzdy.zza(zzdx, bundle, true);
            zzgj().logEvent("am", "_xu", bundle);
        }
    }

    @WorkerThread
    public final void zzm(long j) {
        zzdx zzle = zzgm().zzle();
        for (String str : this.zzafb.keySet()) {
            zza(str, j - ((Long) this.zzafb.get(str)).longValue(), zzle);
        }
        if (!this.zzafb.isEmpty()) {
            zza(j - this.zzafd, zzle);
        }
        zzn(j);
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void zzn(long j) {
        for (String put : this.zzafb.keySet()) {
            this.zzafb.put(put, Long.valueOf(j));
        }
        if (!this.zzafb.isEmpty()) {
            this.zzafd = j;
        }
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
