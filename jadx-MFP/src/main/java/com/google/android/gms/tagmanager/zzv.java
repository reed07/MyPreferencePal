package com.google.android.gms.tagmanager;

import android.os.Looper;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
final class zzv implements ContainerHolder {
    private final Looper zzazy;
    private Container zzazz;
    private Container zzbaa;
    private Status zzbab;
    private zzx zzbac;
    private zzw zzbad;
    private boolean zzbae;
    private TagManager zzbaf;

    public zzv(Status status) {
        this.zzbab = status;
        this.zzazy = null;
    }

    public zzv(TagManager tagManager, Looper looper, Container container, zzw zzw) {
        this.zzbaf = tagManager;
        if (looper == null) {
            looper = Looper.getMainLooper();
        }
        this.zzazy = looper;
        this.zzazz = container;
        this.zzbad = zzw;
        this.zzbab = Status.RESULT_SUCCESS;
        tagManager.zza(this);
    }

    public final Status getStatus() {
        return this.zzbab;
    }

    public final synchronized Container getContainer() {
        if (this.zzbae) {
            zzdi.e("ContainerHolder is released.");
            return null;
        }
        if (this.zzbaa != null) {
            this.zzazz = this.zzbaa;
            this.zzbaa = null;
        }
        return this.zzazz;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0024, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void setContainerAvailableListener(com.google.android.gms.tagmanager.ContainerHolder.ContainerAvailableListener r3) {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = r2.zzbae     // Catch:{ all -> 0x0025 }
            if (r0 == 0) goto L_0x000c
            java.lang.String r3 = "ContainerHolder is released."
            com.google.android.gms.tagmanager.zzdi.e(r3)     // Catch:{ all -> 0x0025 }
            monitor-exit(r2)
            return
        L_0x000c:
            if (r3 != 0) goto L_0x0013
            r3 = 0
            r2.zzbac = r3     // Catch:{ all -> 0x0025 }
            monitor-exit(r2)
            return
        L_0x0013:
            com.google.android.gms.tagmanager.zzx r0 = new com.google.android.gms.tagmanager.zzx     // Catch:{ all -> 0x0025 }
            android.os.Looper r1 = r2.zzazy     // Catch:{ all -> 0x0025 }
            r0.<init>(r2, r3, r1)     // Catch:{ all -> 0x0025 }
            r2.zzbac = r0     // Catch:{ all -> 0x0025 }
            com.google.android.gms.tagmanager.Container r3 = r2.zzbaa     // Catch:{ all -> 0x0025 }
            if (r3 == 0) goto L_0x0023
            r2.zznr()     // Catch:{ all -> 0x0025 }
        L_0x0023:
            monitor-exit(r2)
            return
        L_0x0025:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzv.setContainerAvailableListener(com.google.android.gms.tagmanager.ContainerHolder$ContainerAvailableListener):void");
    }

    public final synchronized void refresh() {
        if (this.zzbae) {
            zzdi.e("Refreshing a released ContainerHolder.");
        } else {
            this.zzbad.zzns();
        }
    }

    public final synchronized void release() {
        if (this.zzbae) {
            zzdi.e("Releasing a released ContainerHolder.");
            return;
        }
        this.zzbae = true;
        this.zzbaf.zzb(this);
        this.zzazz.release();
        this.zzazz = null;
        this.zzbaa = null;
        this.zzbad = null;
        this.zzbac = null;
    }

    public final synchronized void zza(Container container) {
        if (!this.zzbae) {
            this.zzbaa = container;
            zznr();
        }
    }

    public final synchronized void zzde(String str) {
        if (!this.zzbae) {
            this.zzazz.zzde(str);
        }
    }

    /* access modifiers changed from: 0000 */
    public final String getContainerId() {
        if (!this.zzbae) {
            return this.zzazz.getContainerId();
        }
        zzdi.e("getContainerId called on a released ContainerHolder.");
        return "";
    }

    /* access modifiers changed from: 0000 */
    public final void zzdf(String str) {
        if (this.zzbae) {
            zzdi.e("setCtfeUrlPathAndQuery called on a released ContainerHolder.");
        } else {
            this.zzbad.zzdf(str);
        }
    }

    /* access modifiers changed from: 0000 */
    public final String zznq() {
        if (!this.zzbae) {
            return this.zzbad.zznq();
        }
        zzdi.e("setCtfeUrlPathAndQuery called on a released ContainerHolder.");
        return "";
    }

    private final void zznr() {
        zzx zzx = this.zzbac;
        if (zzx != null) {
            zzx.sendMessage(zzx.obtainMessage(1, this.zzbaa.zzno()));
        }
    }
}
