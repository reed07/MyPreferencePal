package com.google.android.gms.internal.ads;

import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public final class zzv {
    private final zzm zzaa;
    private final AtomicInteger zzba;
    private final Set<zzr<?>> zzbb;
    private final PriorityBlockingQueue<zzr<?>> zzbc;
    private final PriorityBlockingQueue<zzr<?>> zzbd;
    private final zzn[] zzbe;
    private final List<zzw> zzbf;
    private final zzb zzj;
    private final zzaa zzk;
    private zzd zzq;

    private zzv(zzb zzb, zzm zzm, int i, zzaa zzaa2) {
        this.zzba = new AtomicInteger();
        this.zzbb = new HashSet();
        this.zzbc = new PriorityBlockingQueue<>();
        this.zzbd = new PriorityBlockingQueue<>();
        this.zzbf = new ArrayList();
        this.zzj = zzb;
        this.zzaa = zzm;
        this.zzbe = new zzn[4];
        this.zzk = zzaa2;
    }

    private zzv(zzb zzb, zzm zzm, int i) {
        this(zzb, zzm, 4, new zzi(new Handler(Looper.getMainLooper())));
    }

    public zzv(zzb zzb, zzm zzm) {
        this(zzb, zzm, 4);
    }

    public final void start() {
        zzn[] zznArr;
        zzd zzd = this.zzq;
        if (zzd != null) {
            zzd.quit();
        }
        for (zzn zzn : this.zzbe) {
            if (zzn != null) {
                zzn.quit();
            }
        }
        this.zzq = new zzd(this.zzbc, this.zzbd, this.zzj, this.zzk);
        this.zzq.start();
        for (int i = 0; i < this.zzbe.length; i++) {
            zzn zzn2 = new zzn(this.zzbd, this.zzaa, this.zzj, this.zzk);
            this.zzbe[i] = zzn2;
            zzn2.start();
        }
    }

    public final <T> zzr<T> zze(zzr<T> zzr) {
        zzr.zza(this);
        synchronized (this.zzbb) {
            this.zzbb.add(zzr);
        }
        zzr.zza(this.zzba.incrementAndGet());
        zzr.zzb("add-to-queue");
        if (!zzr.zzi()) {
            this.zzbd.add(zzr);
            return zzr;
        }
        this.zzbc.add(zzr);
        return zzr;
    }

    /* access modifiers changed from: 0000 */
    public final <T> void zzf(zzr<T> zzr) {
        synchronized (this.zzbb) {
            this.zzbb.remove(zzr);
        }
        synchronized (this.zzbf) {
            for (zzw zzg : this.zzbf) {
                zzg.zzg(zzr);
            }
        }
    }
}
