package com.google.android.gms.internal.ads;

public class zzbrs {
    private static final zzbqq zzfkz = zzbqq.zzamd();
    private zzbpu zzfrl;
    private volatile zzbsl zzfrm;
    private volatile zzbpu zzfrn;

    public int hashCode() {
        return 1;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzbrs)) {
            return false;
        }
        zzbrs zzbrs = (zzbrs) obj;
        zzbsl zzbsl = this.zzfrm;
        zzbsl zzbsl2 = zzbrs.zzfrm;
        if (zzbsl == null && zzbsl2 == null) {
            return zzakf().equals(zzbrs.zzakf());
        }
        if (zzbsl != null && zzbsl2 != null) {
            return zzbsl.equals(zzbsl2);
        }
        if (zzbsl != null) {
            return zzbsl.equals(zzbrs.zzk(zzbsl.zzamv()));
        }
        return zzk(zzbsl2.zzamv()).equals(zzbsl2);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(6:7|8|9|10|11|12) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0012 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.google.android.gms.internal.ads.zzbsl zzk(com.google.android.gms.internal.ads.zzbsl r2) {
        /*
            r1 = this;
            com.google.android.gms.internal.ads.zzbsl r0 = r1.zzfrm
            if (r0 != 0) goto L_0x001d
            monitor-enter(r1)
            com.google.android.gms.internal.ads.zzbsl r0 = r1.zzfrm     // Catch:{ all -> 0x001a }
            if (r0 == 0) goto L_0x000b
            monitor-exit(r1)     // Catch:{ all -> 0x001a }
            goto L_0x001d
        L_0x000b:
            r1.zzfrm = r2     // Catch:{ zzbrl -> 0x0012 }
            com.google.android.gms.internal.ads.zzbpu r0 = com.google.android.gms.internal.ads.zzbpu.zzfli     // Catch:{ zzbrl -> 0x0012 }
            r1.zzfrn = r0     // Catch:{ zzbrl -> 0x0012 }
            goto L_0x0018
        L_0x0012:
            r1.zzfrm = r2     // Catch:{ all -> 0x001a }
            com.google.android.gms.internal.ads.zzbpu r2 = com.google.android.gms.internal.ads.zzbpu.zzfli     // Catch:{ all -> 0x001a }
            r1.zzfrn = r2     // Catch:{ all -> 0x001a }
        L_0x0018:
            monitor-exit(r1)     // Catch:{ all -> 0x001a }
            goto L_0x001d
        L_0x001a:
            r2 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x001a }
            throw r2
        L_0x001d:
            com.google.android.gms.internal.ads.zzbsl r2 = r1.zzfrm
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbrs.zzk(com.google.android.gms.internal.ads.zzbsl):com.google.android.gms.internal.ads.zzbsl");
    }

    public final zzbsl zzl(zzbsl zzbsl) {
        zzbsl zzbsl2 = this.zzfrm;
        this.zzfrl = null;
        this.zzfrn = null;
        this.zzfrm = zzbsl;
        return zzbsl2;
    }

    public final int zzamj() {
        if (this.zzfrn != null) {
            return this.zzfrn.size();
        }
        if (this.zzfrm != null) {
            return this.zzfrm.zzamj();
        }
        return 0;
    }

    public final zzbpu zzakf() {
        if (this.zzfrn != null) {
            return this.zzfrn;
        }
        synchronized (this) {
            if (this.zzfrn != null) {
                zzbpu zzbpu = this.zzfrn;
                return zzbpu;
            }
            if (this.zzfrm == null) {
                this.zzfrn = zzbpu.zzfli;
            } else {
                this.zzfrn = this.zzfrm.zzakf();
            }
            zzbpu zzbpu2 = this.zzfrn;
            return zzbpu2;
        }
    }
}
