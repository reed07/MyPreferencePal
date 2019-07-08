package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzbv;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@zzark
public final class zzbbq {
    public static <V> void zza(zzbcb<V> zzbcb, zzbbn<V> zzbbn, Executor executor) {
        zzbcb.zza(new zzbbr(zzbbn, zzbcb), executor);
    }

    public static <A, B> zzbcb<B> zza(zzbcb<A> zzbcb, zzbbm<A, B> zzbbm, Executor executor) {
        zzbcl zzbcl = new zzbcl();
        zzbcb.zza(new zzbbs(zzbcl, zzbbm, zzbcb), executor);
        zza((zzbcb<A>) zzbcl, (Future<B>) zzbcb);
        return zzbcl;
    }

    public static <A, B> zzbcb<B> zza(zzbcb<A> zzbcb, zzbbl<? super A, ? extends B> zzbbl, Executor executor) {
        zzbcl zzbcl = new zzbcl();
        zzbcb.zza(new zzbbt(zzbcl, zzbbl, zzbcb), executor);
        zza((zzbcb<A>) zzbcl, (Future<B>) zzbcb);
        return zzbcl;
    }

    public static <V> zzbcb<V> zza(zzbcb<V> zzbcb, long j, TimeUnit timeUnit, ScheduledExecutorService scheduledExecutorService) {
        zzbcl zzbcl = new zzbcl();
        zza((zzbcb<A>) zzbcl, (Future<B>) zzbcb);
        ScheduledFuture schedule = scheduledExecutorService.schedule(new zzbbu(zzbcl), j, timeUnit);
        zza(zzbcb, zzbcl);
        zzbcl.zza(new zzbbv(schedule), zzbcg.zzepp);
        return zzbcl;
    }

    public static <V, X extends Throwable> zzbcb<V> zza(zzbcb<? extends V> zzbcb, Class<X> cls, zzbbl<? super X, ? extends V> zzbbl, Executor executor) {
        zzbcl zzbcl = new zzbcl();
        zza((zzbcb<A>) zzbcl, (Future<B>) zzbcb);
        zzbbw zzbbw = new zzbbw(zzbcl, zzbcb, cls, zzbbl, executor);
        zzbcb.zza(zzbbw, zzbcg.zzepp);
        return zzbcl;
    }

    public static <T> T zza(Future<T> future, T t) {
        try {
            return future.get(((Long) zzwu.zzpz().zzd(zzaan.zzctd)).longValue(), TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            future.cancel(true);
            zzaxz.zzc("InterruptedException caught while resolving future.", e);
            Thread.currentThread().interrupt();
            zzbv.zzlj().zzb(e, "Futures.resolveFuture");
            return t;
        } catch (Exception e2) {
            future.cancel(true);
            zzaxz.zzb("Error waiting for future.", e2);
            zzbv.zzlj().zzb(e2, "Futures.resolveFuture");
            return t;
        }
    }

    public static <T> T zza(Future<T> future, T t, long j, TimeUnit timeUnit) {
        try {
            return future.get(j, timeUnit);
        } catch (InterruptedException e) {
            future.cancel(true);
            zzaxz.zzc("InterruptedException caught while resolving future.", e);
            Thread.currentThread().interrupt();
            zzbv.zzlj().zzb(e, "Futures.resolveFuture");
            return t;
        } catch (Exception e2) {
            future.cancel(true);
            zzaxz.zzb("Error waiting for future.", e2);
            zzbv.zzlj().zzb(e2, "Futures.resolveFuture");
            return t;
        }
    }

    public static <T> zzbca<T> zzm(T t) {
        return new zzbca<>(t);
    }

    public static <T> zzbbz<T> zzd(Throwable th) {
        return new zzbbz<>(th);
    }

    private static <V> void zza(zzbcb<? extends V> zzbcb, zzbcl<V> zzbcl) {
        zza((zzbcb<A>) zzbcl, (Future<B>) zzbcb);
        zzbcb.zza(new zzbbx(zzbcl, zzbcb), zzbcg.zzepp);
    }

    private static <A, B> void zza(zzbcb<A> zzbcb, Future<B> future) {
        zzbcb.zza(new zzbby(zzbcb, future), zzbcg.zzepp);
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x001e  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x002a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static final /* synthetic */ void zza(com.google.android.gms.internal.ads.zzbcl r1, com.google.android.gms.internal.ads.zzbcb r2, java.lang.Class r3, com.google.android.gms.internal.ads.zzbbl r4, java.util.concurrent.Executor r5) {
        /*
            java.lang.Object r2 = r2.get()     // Catch:{ ExecutionException -> 0x0013, InterruptedException -> 0x000a, Exception -> 0x0008 }
            r1.set(r2)     // Catch:{ ExecutionException -> 0x0013, InterruptedException -> 0x000a, Exception -> 0x0008 }
            return
        L_0x0008:
            r2 = move-exception
            goto L_0x0018
        L_0x000a:
            r2 = move-exception
            java.lang.Thread r0 = java.lang.Thread.currentThread()
            r0.interrupt()
            goto L_0x0018
        L_0x0013:
            r2 = move-exception
            java.lang.Throwable r2 = r2.getCause()
        L_0x0018:
            boolean r3 = r3.isInstance(r2)
            if (r3 == 0) goto L_0x002a
            com.google.android.gms.internal.ads.zzbca r2 = zzm(r2)
            com.google.android.gms.internal.ads.zzbcb r2 = zza(r2, r4, r5)
            zza(r2, r1)
            return
        L_0x002a:
            r1.setException(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbbq.zza(com.google.android.gms.internal.ads.zzbcl, com.google.android.gms.internal.ads.zzbcb, java.lang.Class, com.google.android.gms.internal.ads.zzbbl, java.util.concurrent.Executor):void");
    }

    static final /* synthetic */ void zza(zzbcl zzbcl, zzbbl zzbbl, zzbcb zzbcb) {
        if (!zzbcl.isCancelled()) {
            try {
                zza(zzbbl.zzf(zzbcb.get()), zzbcl);
            } catch (CancellationException unused) {
                zzbcl.cancel(true);
            } catch (ExecutionException e) {
                zzbcl.setException(e.getCause());
            } catch (InterruptedException e2) {
                Thread.currentThread().interrupt();
                zzbcl.setException(e2);
            } catch (Exception e3) {
                zzbcl.setException(e3);
            }
        }
    }
}
