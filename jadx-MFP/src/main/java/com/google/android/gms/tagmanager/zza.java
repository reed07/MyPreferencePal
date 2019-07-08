package com.google.android.gms.tagmanager;

import android.content.Context;
import android.os.Process;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
public final class zza {
    private static Object zzaze = new Object();
    private static zza zzazf;
    private volatile boolean closed;
    private volatile long zzayx;
    private volatile long zzayy;
    private volatile long zzayz;
    private volatile long zzaza;
    private final Thread zzazb;
    private final Object zzazc;
    private zzd zzazd;
    /* access modifiers changed from: private */
    public final Context zzri;
    private final Clock zzrz;
    private volatile Info zzvl;

    public static zza zzo(Context context) {
        if (zzazf == null) {
            synchronized (zzaze) {
                if (zzazf == null) {
                    zza zza = new zza(context);
                    zzazf = zza;
                    zza.zzazb.start();
                }
            }
        }
        return zzazf;
    }

    private zza(Context context) {
        this(context, null, DefaultClock.getInstance());
    }

    @VisibleForTesting
    private zza(Context context, zzd zzd, Clock clock) {
        this.zzayx = 900000;
        this.zzayy = 30000;
        this.closed = false;
        this.zzazc = new Object();
        this.zzazd = new zzb(this);
        this.zzrz = clock;
        if (context != null) {
            this.zzri = context.getApplicationContext();
        } else {
            this.zzri = context;
        }
        this.zzayz = this.zzrz.currentTimeMillis();
        this.zzazb = new Thread(new zzc(this));
    }

    public final String zzne() {
        if (this.zzvl == null) {
            zznf();
        } else {
            zzng();
        }
        zznh();
        if (this.zzvl == null) {
            return null;
        }
        return this.zzvl.getId();
    }

    public final boolean isLimitAdTrackingEnabled() {
        if (this.zzvl == null) {
            zznf();
        } else {
            zzng();
        }
        zznh();
        if (this.zzvl == null) {
            return true;
        }
        return this.zzvl.isLimitAdTrackingEnabled();
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x0010 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zznf() {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = r2.closed     // Catch:{ InterruptedException -> 0x0010 }
            if (r0 != 0) goto L_0x0010
            r2.zzng()     // Catch:{ InterruptedException -> 0x0010 }
            r0 = 500(0x1f4, double:2.47E-321)
            r2.wait(r0)     // Catch:{ InterruptedException -> 0x0010 }
            goto L_0x0010
        L_0x000e:
            r0 = move-exception
            goto L_0x0012
        L_0x0010:
            monitor-exit(r2)     // Catch:{ all -> 0x000e }
            return
        L_0x0012:
            monitor-exit(r2)     // Catch:{ all -> 0x000e }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zza.zznf():void");
    }

    private final void zzng() {
        if (this.zzrz.currentTimeMillis() - this.zzayz > this.zzayy) {
            synchronized (this.zzazc) {
                this.zzazc.notify();
            }
            this.zzayz = this.zzrz.currentTimeMillis();
        }
    }

    private final void zznh() {
        if (this.zzrz.currentTimeMillis() - this.zzaza > 3600000) {
            this.zzvl = null;
        }
    }

    /* access modifiers changed from: private */
    public final void zzni() {
        Process.setThreadPriority(10);
        while (!this.closed) {
            Info zznj = this.zzazd.zznj();
            if (zznj != null) {
                this.zzvl = zznj;
                this.zzaza = this.zzrz.currentTimeMillis();
                zzdi.zzdm("Obtained fresh AdvertisingId info from GmsCore.");
            }
            synchronized (this) {
                notifyAll();
            }
            try {
                synchronized (this.zzazc) {
                    this.zzazc.wait(this.zzayx);
                }
            } catch (InterruptedException unused) {
                zzdi.zzdm("sleep interrupted in AdvertiserDataPoller thread; continuing");
            }
        }
    }

    @VisibleForTesting
    public final void close() {
        this.closed = true;
        this.zzazb.interrupt();
    }
}
