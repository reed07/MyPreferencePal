package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.IntentFilter;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.util.VisibleForTesting;

@ShowFirstParty
@VisibleForTesting
final class zzfn extends zzfm {
    /* access modifiers changed from: private */
    public static final Object zzbfz = new Object();
    private static zzfn zzbgk;
    /* access modifiers changed from: private */
    public boolean connected = true;
    private zzcc zzbdt = new zzfo(this);
    /* access modifiers changed from: private */
    public Context zzbga;
    /* access modifiers changed from: private */
    public zzcb zzbgb;
    private volatile zzby zzbgc;
    /* access modifiers changed from: private */
    public int zzbgd = 1800000;
    private boolean zzbge = true;
    private boolean zzbgf = false;
    private boolean zzbgg = true;
    private zzfq zzbgh;
    private zzdn zzbgi;
    private boolean zzbgj = false;

    public static zzfn zzqe() {
        if (zzbgk == null) {
            zzbgk = new zzfn();
        }
        return zzbgk;
    }

    private zzfn() {
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0014, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void zza(android.content.Context r2, com.google.android.gms.tagmanager.zzby r3) {
        /*
            r1 = this;
            monitor-enter(r1)
            android.content.Context r0 = r1.zzbga     // Catch:{ all -> 0x0015 }
            if (r0 == 0) goto L_0x0007
            monitor-exit(r1)
            return
        L_0x0007:
            android.content.Context r2 = r2.getApplicationContext()     // Catch:{ all -> 0x0015 }
            r1.zzbga = r2     // Catch:{ all -> 0x0015 }
            com.google.android.gms.tagmanager.zzby r2 = r1.zzbgc     // Catch:{ all -> 0x0015 }
            if (r2 != 0) goto L_0x0013
            r1.zzbgc = r3     // Catch:{ all -> 0x0015 }
        L_0x0013:
            monitor-exit(r1)
            return
        L_0x0015:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzfn.zza(android.content.Context, com.google.android.gms.tagmanager.zzby):void");
    }

    /* access modifiers changed from: 0000 */
    public final synchronized zzcb zzqf() {
        if (this.zzbgb == null) {
            if (this.zzbga != null) {
                this.zzbgb = new zzeb(this.zzbdt, this.zzbga);
            } else {
                throw new IllegalStateException("Cant get a store unless we have a context");
            }
        }
        if (this.zzbgh == null) {
            this.zzbgh = new zzfr(this, null);
            if (this.zzbgd > 0) {
                this.zzbgh.zzh((long) this.zzbgd);
            }
        }
        this.zzbgf = true;
        if (this.zzbge) {
            dispatch();
            this.zzbge = false;
        }
        if (this.zzbgi == null && this.zzbgg) {
            this.zzbgi = new zzdn(this);
            zzdn zzdn = this.zzbgi;
            Context context = this.zzbga;
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            context.registerReceiver(zzdn, intentFilter);
            IntentFilter intentFilter2 = new IntentFilter();
            intentFilter2.addAction("com.google.analytics.RADIO_POWERED");
            intentFilter2.addCategory(context.getPackageName());
            context.registerReceiver(zzdn, intentFilter2);
        }
        return this.zzbgb;
    }

    public final synchronized void dispatch() {
        if (!this.zzbgf) {
            zzdi.v("Dispatch call queued. Dispatch will run once initialization is complete.");
            this.zzbge = true;
            return;
        }
        this.zzbgc.zzh(new zzfp(this));
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public final synchronized void zzb(boolean z, boolean z2) {
        boolean isPowerSaveMode = isPowerSaveMode();
        this.zzbgj = z;
        this.connected = z2;
        if (isPowerSaveMode() != isPowerSaveMode) {
            if (isPowerSaveMode()) {
                this.zzbgh.cancel();
                zzdi.v("PowerSaveMode initiated.");
                return;
            }
            this.zzbgh.zzh((long) this.zzbgd);
            zzdi.v("PowerSaveMode terminated.");
        }
    }

    public final synchronized void zzp(boolean z) {
        zzb(this.zzbgj, z);
    }

    public final synchronized void zzqd() {
        if (!isPowerSaveMode()) {
            this.zzbgh.zzqh();
        }
    }

    /* access modifiers changed from: private */
    public final boolean isPowerSaveMode() {
        return this.zzbgj || !this.connected || this.zzbgd <= 0;
    }
}
