package com.google.android.gms.measurement.internal;

import android.content.Context;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public final class zzbr extends zzcs {
    /* access modifiers changed from: private */
    public static final AtomicLong zzapc = new AtomicLong(Long.MIN_VALUE);
    private ExecutorService zzadl;
    /* access modifiers changed from: private */
    public zzbv zzaot;
    /* access modifiers changed from: private */
    public zzbv zzaou;
    private final PriorityBlockingQueue<zzbu<?>> zzaov = new PriorityBlockingQueue<>();
    private final BlockingQueue<zzbu<?>> zzaow = new LinkedBlockingQueue();
    private final UncaughtExceptionHandler zzaox = new zzbt(this, "Thread death: Uncaught exception on worker thread");
    private final UncaughtExceptionHandler zzaoy = new zzbt(this, "Thread death: Uncaught exception on network thread");
    /* access modifiers changed from: private */
    public final Object zzaoz = new Object();
    /* access modifiers changed from: private */
    public final Semaphore zzapa = new Semaphore(2);
    /* access modifiers changed from: private */
    public volatile boolean zzapb;

    zzbr(zzbw zzbw) {
        super(zzbw);
    }

    /* access modifiers changed from: protected */
    public final boolean zzgy() {
        return false;
    }

    public final void zzaf() {
        if (Thread.currentThread() != this.zzaot) {
            throw new IllegalStateException("Call expected from worker thread");
        }
    }

    public final void zzgh() {
        if (Thread.currentThread() != this.zzaou) {
            throw new IllegalStateException("Call expected from network thread");
        }
    }

    public final boolean zzkf() {
        return Thread.currentThread() == this.zzaot;
    }

    public final <V> Future<V> zzb(Callable<V> callable) throws IllegalStateException {
        zzcl();
        Preconditions.checkNotNull(callable);
        zzbu zzbu = new zzbu(this, callable, false, "Task exception on worker thread");
        if (Thread.currentThread() == this.zzaot) {
            if (!this.zzaov.isEmpty()) {
                zzgt().zzjj().zzby("Callable skipped the worker queue.");
            }
            zzbu.run();
        } else {
            zza(zzbu);
        }
        return zzbu;
    }

    public final <V> Future<V> zzc(Callable<V> callable) throws IllegalStateException {
        zzcl();
        Preconditions.checkNotNull(callable);
        zzbu zzbu = new zzbu(this, callable, true, "Task exception on worker thread");
        if (Thread.currentThread() == this.zzaot) {
            zzbu.run();
        } else {
            zza(zzbu);
        }
        return zzbu;
    }

    public final void zzc(Runnable runnable) throws IllegalStateException {
        zzcl();
        Preconditions.checkNotNull(runnable);
        zza(new zzbu<>(this, runnable, false, "Task exception on worker thread"));
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Can't wrap try/catch for region: R(6:16|17|(1:19)(1:20)|21|22|23) */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0014, code lost:
        r2 = zzgt().zzjj();
        r3 = "Timed out waiting for ";
        r4 = java.lang.String.valueOf(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0026, code lost:
        if (r4.length() == 0) goto L_0x002d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0028, code lost:
        r3 = r3.concat(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002d, code lost:
        r3 = new java.lang.String(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0033, code lost:
        r2.zzby(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0036, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r2 = zzgt().zzjj();
        r3 = "Interrupted waiting for ";
        r4 = java.lang.String.valueOf(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0049, code lost:
        if (r4.length() != 0) goto L_0x004b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x004b, code lost:
        r3 = r3.concat(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0050, code lost:
        r3 = new java.lang.String(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0056, code lost:
        r2.zzby(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x005b, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x000e, code lost:
        r1 = r1.get();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0012, code lost:
        if (r1 != null) goto L_0x0036;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:16:0x0037 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final <T> T zza(java.util.concurrent.atomic.AtomicReference<T> r1, long r2, java.lang.String r4, java.lang.Runnable r5) {
        /*
            r0 = this;
            monitor-enter(r1)
            com.google.android.gms.measurement.internal.zzbr r2 = r0.zzgs()     // Catch:{ all -> 0x005c }
            r2.zzc(r5)     // Catch:{ all -> 0x005c }
            r2 = 15000(0x3a98, double:7.411E-320)
            r1.wait(r2)     // Catch:{ InterruptedException -> 0x0037 }
            monitor-exit(r1)     // Catch:{ all -> 0x005c }
            java.lang.Object r1 = r1.get()
            if (r1 != 0) goto L_0x0036
            com.google.android.gms.measurement.internal.zzas r2 = r0.zzgt()
            com.google.android.gms.measurement.internal.zzau r2 = r2.zzjj()
            java.lang.String r3 = "Timed out waiting for "
            java.lang.String r4 = java.lang.String.valueOf(r4)
            int r5 = r4.length()
            if (r5 == 0) goto L_0x002d
            java.lang.String r3 = r3.concat(r4)
            goto L_0x0033
        L_0x002d:
            java.lang.String r4 = new java.lang.String
            r4.<init>(r3)
            r3 = r4
        L_0x0033:
            r2.zzby(r3)
        L_0x0036:
            return r1
        L_0x0037:
            com.google.android.gms.measurement.internal.zzas r2 = r0.zzgt()     // Catch:{ all -> 0x005c }
            com.google.android.gms.measurement.internal.zzau r2 = r2.zzjj()     // Catch:{ all -> 0x005c }
            java.lang.String r3 = "Interrupted waiting for "
            java.lang.String r4 = java.lang.String.valueOf(r4)     // Catch:{ all -> 0x005c }
            int r5 = r4.length()     // Catch:{ all -> 0x005c }
            if (r5 == 0) goto L_0x0050
            java.lang.String r3 = r3.concat(r4)     // Catch:{ all -> 0x005c }
            goto L_0x0056
        L_0x0050:
            java.lang.String r4 = new java.lang.String     // Catch:{ all -> 0x005c }
            r4.<init>(r3)     // Catch:{ all -> 0x005c }
            r3 = r4
        L_0x0056:
            r2.zzby(r3)     // Catch:{ all -> 0x005c }
            r2 = 0
            monitor-exit(r1)     // Catch:{ all -> 0x005c }
            return r2
        L_0x005c:
            r2 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x005c }
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzbr.zza(java.util.concurrent.atomic.AtomicReference, long, java.lang.String, java.lang.Runnable):java.lang.Object");
    }

    private final void zza(zzbu<?> zzbu) {
        synchronized (this.zzaoz) {
            this.zzaov.add(zzbu);
            if (this.zzaot == null) {
                this.zzaot = new zzbv(this, "Measurement Worker", this.zzaov);
                this.zzaot.setUncaughtExceptionHandler(this.zzaox);
                this.zzaot.start();
            } else {
                this.zzaot.zzki();
            }
        }
    }

    public final void zzd(Runnable runnable) throws IllegalStateException {
        zzcl();
        Preconditions.checkNotNull(runnable);
        zzbu zzbu = new zzbu(this, runnable, false, "Task exception on network thread");
        synchronized (this.zzaoz) {
            this.zzaow.add(zzbu);
            if (this.zzaou == null) {
                this.zzaou = new zzbv(this, "Measurement Network", this.zzaow);
                this.zzaou.setUncaughtExceptionHandler(this.zzaoy);
                this.zzaou.start();
            } else {
                this.zzaou.zzki();
            }
        }
    }

    public final ExecutorService zzkg() {
        ExecutorService executorService;
        synchronized (this.zzaoz) {
            if (this.zzadl == null) {
                ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 1, 30, TimeUnit.SECONDS, new ArrayBlockingQueue(100));
                this.zzadl = threadPoolExecutor;
            }
            executorService = this.zzadl;
        }
        return executorService;
    }

    public final /* bridge */ /* synthetic */ void zzgf() {
        super.zzgf();
    }

    public final /* bridge */ /* synthetic */ void zzgg() {
        super.zzgg();
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
