package com.google.android.gms.internal.measurement;

public class zzvc {
    private static final zzub zzbtm = zzub.zzvr();
    private zzte zzbzw;
    private volatile zzvv zzbzx;
    private volatile zzte zzbzy;

    public int hashCode() {
        return 1;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzvc)) {
            return false;
        }
        zzvc zzvc = (zzvc) obj;
        zzvv zzvv = this.zzbzx;
        zzvv zzvv2 = zzvc.zzbzx;
        if (zzvv == null && zzvv2 == null) {
            return zztw().equals(zzvc.zztw());
        }
        if (zzvv != null && zzvv2 != null) {
            return zzvv.equals(zzvv2);
        }
        if (zzvv != null) {
            return zzvv.equals(zzvc.zzh(zzvv.zzwj()));
        }
        return zzh(zzvv2.zzwj()).equals(zzvv2);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(6:7|8|9|10|11|12) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0012 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.google.android.gms.internal.measurement.zzvv zzh(com.google.android.gms.internal.measurement.zzvv r2) {
        /*
            r1 = this;
            com.google.android.gms.internal.measurement.zzvv r0 = r1.zzbzx
            if (r0 != 0) goto L_0x001d
            monitor-enter(r1)
            com.google.android.gms.internal.measurement.zzvv r0 = r1.zzbzx     // Catch:{ all -> 0x001a }
            if (r0 == 0) goto L_0x000b
            monitor-exit(r1)     // Catch:{ all -> 0x001a }
            goto L_0x001d
        L_0x000b:
            r1.zzbzx = r2     // Catch:{ zzuv -> 0x0012 }
            com.google.android.gms.internal.measurement.zzte r0 = com.google.android.gms.internal.measurement.zzte.zzbts     // Catch:{ zzuv -> 0x0012 }
            r1.zzbzy = r0     // Catch:{ zzuv -> 0x0012 }
            goto L_0x0018
        L_0x0012:
            r1.zzbzx = r2     // Catch:{ all -> 0x001a }
            com.google.android.gms.internal.measurement.zzte r2 = com.google.android.gms.internal.measurement.zzte.zzbts     // Catch:{ all -> 0x001a }
            r1.zzbzy = r2     // Catch:{ all -> 0x001a }
        L_0x0018:
            monitor-exit(r1)     // Catch:{ all -> 0x001a }
            goto L_0x001d
        L_0x001a:
            r2 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x001a }
            throw r2
        L_0x001d:
            com.google.android.gms.internal.measurement.zzvv r2 = r1.zzbzx
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzvc.zzh(com.google.android.gms.internal.measurement.zzvv):com.google.android.gms.internal.measurement.zzvv");
    }

    public final zzvv zzi(zzvv zzvv) {
        zzvv zzvv2 = this.zzbzx;
        this.zzbzw = null;
        this.zzbzy = null;
        this.zzbzx = zzvv;
        return zzvv2;
    }

    public final int zzvx() {
        if (this.zzbzy != null) {
            return this.zzbzy.size();
        }
        if (this.zzbzx != null) {
            return this.zzbzx.zzvx();
        }
        return 0;
    }

    public final zzte zztw() {
        if (this.zzbzy != null) {
            return this.zzbzy;
        }
        synchronized (this) {
            if (this.zzbzy != null) {
                zzte zzte = this.zzbzy;
                return zzte;
            }
            if (this.zzbzx == null) {
                this.zzbzy = zzte.zzbts;
            } else {
                this.zzbzy = this.zzbzx.zztw();
            }
            zzte zzte2 = this.zzbzy;
            return zzte2;
        }
    }
}
