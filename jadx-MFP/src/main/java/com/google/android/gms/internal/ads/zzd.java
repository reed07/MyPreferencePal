package com.google.android.gms.internal.ads;

import android.os.Process;
import java.util.concurrent.BlockingQueue;

public final class zzd extends Thread {
    private static final boolean DEBUG = zzaf.DEBUG;
    private final BlockingQueue<zzr<?>> zzh;
    /* access modifiers changed from: private */
    public final BlockingQueue<zzr<?>> zzi;
    private final zzb zzj;
    /* access modifiers changed from: private */
    public final zzaa zzk;
    private volatile boolean zzl = false;
    private final zzf zzm;

    public zzd(BlockingQueue<zzr<?>> blockingQueue, BlockingQueue<zzr<?>> blockingQueue2, zzb zzb, zzaa zzaa) {
        this.zzh = blockingQueue;
        this.zzi = blockingQueue2;
        this.zzj = zzb;
        this.zzk = zzaa;
        this.zzm = new zzf(this);
    }

    public final void quit() {
        this.zzl = true;
        interrupt();
    }

    public final void run() {
        if (DEBUG) {
            zzaf.v("start new dispatcher", new Object[0]);
        }
        Process.setThreadPriority(10);
        this.zzj.zza();
        while (true) {
            try {
                processRequest();
            } catch (InterruptedException unused) {
                if (this.zzl) {
                    Thread.currentThread().interrupt();
                    return;
                }
                zzaf.e("Ignoring spurious interrupt of CacheDispatcher thread; use quit() to terminate it", new Object[0]);
            }
        }
    }

    private final void processRequest() throws InterruptedException {
        zzr zzr = (zzr) this.zzh.take();
        zzr.zzb("cache-queue-take");
        zzr.isCanceled();
        zzc zza = this.zzj.zza(zzr.zzf());
        if (zza == null) {
            zzr.zzb("cache-miss");
            if (!this.zzm.zzb(zzr)) {
                this.zzi.put(zzr);
            }
        } else if (zza.zzb()) {
            zzr.zzb("cache-hit-expired");
            zzr.zza(zza);
            if (!this.zzm.zzb(zzr)) {
                this.zzi.put(zzr);
            }
        } else {
            zzr.zzb("cache-hit");
            zzx zza2 = zzr.zza(new zzp(zza.data, zza.zzf));
            zzr.zzb("cache-hit-parsed");
            if (zza.zze < System.currentTimeMillis()) {
                zzr.zzb("cache-hit-refresh-needed");
                zzr.zza(zza);
                zza2.zzbi = true;
                if (!this.zzm.zzb(zzr)) {
                    this.zzk.zza(zzr, zza2, new zze(this, zzr));
                    return;
                }
            }
            this.zzk.zzb(zzr, zza2);
        }
    }
}
