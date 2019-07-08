package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Binder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zzbv;
import javax.annotation.concurrent.GuardedBy;

@zzark
public final class zztq {
    @Nullable
    @GuardedBy("mLock")
    private Context mContext;
    /* access modifiers changed from: private */
    public final Object mLock = new Object();
    private final Runnable zzbzq = new zztr(this);
    /* access modifiers changed from: private */
    @Nullable
    @GuardedBy("mLock")
    public zztx zzbzr;
    /* access modifiers changed from: private */
    @Nullable
    @GuardedBy("mLock")
    public zzub zzbzs;

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0047, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void initialize(android.content.Context r3) {
        /*
            r2 = this;
            if (r3 != 0) goto L_0x0003
            return
        L_0x0003:
            java.lang.Object r0 = r2.mLock
            monitor-enter(r0)
            android.content.Context r1 = r2.mContext     // Catch:{ all -> 0x0048 }
            if (r1 == 0) goto L_0x000c
            monitor-exit(r0)     // Catch:{ all -> 0x0048 }
            return
        L_0x000c:
            android.content.Context r3 = r3.getApplicationContext()     // Catch:{ all -> 0x0048 }
            r2.mContext = r3     // Catch:{ all -> 0x0048 }
            com.google.android.gms.internal.ads.zzaac<java.lang.Boolean> r3 = com.google.android.gms.internal.ads.zzaan.zzcvr     // Catch:{ all -> 0x0048 }
            com.google.android.gms.internal.ads.zzaak r1 = com.google.android.gms.internal.ads.zzwu.zzpz()     // Catch:{ all -> 0x0048 }
            java.lang.Object r3 = r1.zzd(r3)     // Catch:{ all -> 0x0048 }
            java.lang.Boolean r3 = (java.lang.Boolean) r3     // Catch:{ all -> 0x0048 }
            boolean r3 = r3.booleanValue()     // Catch:{ all -> 0x0048 }
            if (r3 == 0) goto L_0x0028
            r2.connect()     // Catch:{ all -> 0x0048 }
            goto L_0x0046
        L_0x0028:
            com.google.android.gms.internal.ads.zzaac<java.lang.Boolean> r3 = com.google.android.gms.internal.ads.zzaan.zzcvq     // Catch:{ all -> 0x0048 }
            com.google.android.gms.internal.ads.zzaak r1 = com.google.android.gms.internal.ads.zzwu.zzpz()     // Catch:{ all -> 0x0048 }
            java.lang.Object r3 = r1.zzd(r3)     // Catch:{ all -> 0x0048 }
            java.lang.Boolean r3 = (java.lang.Boolean) r3     // Catch:{ all -> 0x0048 }
            boolean r3 = r3.booleanValue()     // Catch:{ all -> 0x0048 }
            if (r3 == 0) goto L_0x0046
            com.google.android.gms.internal.ads.zzts r3 = new com.google.android.gms.internal.ads.zzts     // Catch:{ all -> 0x0048 }
            r3.<init>(r2)     // Catch:{ all -> 0x0048 }
            com.google.android.gms.internal.ads.zzst r1 = com.google.android.gms.ads.internal.zzbv.zzli()     // Catch:{ all -> 0x0048 }
            r1.zza(r3)     // Catch:{ all -> 0x0048 }
        L_0x0046:
            monitor-exit(r0)     // Catch:{ all -> 0x0048 }
            return
        L_0x0048:
            r3 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0048 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zztq.initialize(android.content.Context):void");
    }

    public final void zzod() {
        if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcvs)).booleanValue()) {
            synchronized (this.mLock) {
                connect();
                zzbv.zzlf();
                zzayh.zzelc.removeCallbacks(this.zzbzq);
                zzbv.zzlf();
                zzayh.zzelc.postDelayed(this.zzbzq, ((Long) zzwu.zzpz().zzd(zzaan.zzcvt)).longValue());
            }
        }
    }

    public final zztv zza(zzty zzty) {
        synchronized (this.mLock) {
            if (this.zzbzs == null) {
                zztv zztv = new zztv();
                return zztv;
            }
            try {
                zztv zza = this.zzbzs.zza(zzty);
                return zza;
            } catch (RemoteException e) {
                zzaxz.zzb("Unable to call into cache service.", e);
                return new zztv();
            }
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002f, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void connect() {
        /*
            r6 = this;
            java.lang.Object r0 = r6.mLock
            monitor-enter(r0)
            android.content.Context r1 = r6.mContext     // Catch:{ all -> 0x0030 }
            if (r1 == 0) goto L_0x002e
            com.google.android.gms.internal.ads.zztx r1 = r6.zzbzr     // Catch:{ all -> 0x0030 }
            if (r1 == 0) goto L_0x000c
            goto L_0x002e
        L_0x000c:
            com.google.android.gms.internal.ads.zztt r1 = new com.google.android.gms.internal.ads.zztt     // Catch:{ all -> 0x0030 }
            r1.<init>(r6)     // Catch:{ all -> 0x0030 }
            com.google.android.gms.internal.ads.zztu r2 = new com.google.android.gms.internal.ads.zztu     // Catch:{ all -> 0x0030 }
            r2.<init>(r6)     // Catch:{ all -> 0x0030 }
            com.google.android.gms.internal.ads.zztx r3 = new com.google.android.gms.internal.ads.zztx     // Catch:{ all -> 0x0030 }
            android.content.Context r4 = r6.mContext     // Catch:{ all -> 0x0030 }
            com.google.android.gms.internal.ads.zzbaf r5 = com.google.android.gms.ads.internal.zzbv.zzlv()     // Catch:{ all -> 0x0030 }
            android.os.Looper r5 = r5.zzaak()     // Catch:{ all -> 0x0030 }
            r3.<init>(r4, r5, r1, r2)     // Catch:{ all -> 0x0030 }
            r6.zzbzr = r3     // Catch:{ all -> 0x0030 }
            com.google.android.gms.internal.ads.zztx r1 = r6.zzbzr     // Catch:{ all -> 0x0030 }
            r1.checkAvailabilityAndConnect()     // Catch:{ all -> 0x0030 }
            monitor-exit(r0)     // Catch:{ all -> 0x0030 }
            return
        L_0x002e:
            monitor-exit(r0)     // Catch:{ all -> 0x0030 }
            return
        L_0x0030:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0030 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zztq.connect():void");
    }

    /* access modifiers changed from: private */
    public final void disconnect() {
        synchronized (this.mLock) {
            if (this.zzbzr != null) {
                if (this.zzbzr.isConnected() || this.zzbzr.isConnecting()) {
                    this.zzbzr.disconnect();
                }
                this.zzbzr = null;
                this.zzbzs = null;
                Binder.flushPendingCommands();
            }
        }
    }
}
