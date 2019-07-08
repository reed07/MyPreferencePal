package com.google.android.gms.internal.icing;

public class zzdy {
    private static final zzcy zzfr = zzcy.zzbd();
    private zzce zzlk;
    private volatile zzeq zzll;
    private volatile zzce zzlm;

    public int hashCode() {
        return 1;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzdy)) {
            return false;
        }
        zzdy zzdy = (zzdy) obj;
        zzeq zzeq = this.zzll;
        zzeq zzeq2 = zzdy.zzll;
        if (zzeq == null && zzeq2 == null) {
            return zzaf().equals(zzdy.zzaf());
        }
        if (zzeq != null && zzeq2 != null) {
            return zzeq.equals(zzeq2);
        }
        if (zzeq != null) {
            return zzeq.equals(zzdy.zzg(zzeq.zzbv()));
        }
        return zzg(zzeq2.zzbv()).equals(zzeq2);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(6:7|8|9|10|11|12) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0012 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.google.android.gms.internal.icing.zzeq zzg(com.google.android.gms.internal.icing.zzeq r2) {
        /*
            r1 = this;
            com.google.android.gms.internal.icing.zzeq r0 = r1.zzll
            if (r0 != 0) goto L_0x001d
            monitor-enter(r1)
            com.google.android.gms.internal.icing.zzeq r0 = r1.zzll     // Catch:{ all -> 0x001a }
            if (r0 == 0) goto L_0x000b
            monitor-exit(r1)     // Catch:{ all -> 0x001a }
            goto L_0x001d
        L_0x000b:
            r1.zzll = r2     // Catch:{ zzdr -> 0x0012 }
            com.google.android.gms.internal.icing.zzce r0 = com.google.android.gms.internal.icing.zzce.zzfx     // Catch:{ zzdr -> 0x0012 }
            r1.zzlm = r0     // Catch:{ zzdr -> 0x0012 }
            goto L_0x0018
        L_0x0012:
            r1.zzll = r2     // Catch:{ all -> 0x001a }
            com.google.android.gms.internal.icing.zzce r2 = com.google.android.gms.internal.icing.zzce.zzfx     // Catch:{ all -> 0x001a }
            r1.zzlm = r2     // Catch:{ all -> 0x001a }
        L_0x0018:
            monitor-exit(r1)     // Catch:{ all -> 0x001a }
            goto L_0x001d
        L_0x001a:
            r2 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x001a }
            throw r2
        L_0x001d:
            com.google.android.gms.internal.icing.zzeq r2 = r1.zzll
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.icing.zzdy.zzg(com.google.android.gms.internal.icing.zzeq):com.google.android.gms.internal.icing.zzeq");
    }

    public final zzeq zzh(zzeq zzeq) {
        zzeq zzeq2 = this.zzll;
        this.zzlk = null;
        this.zzlm = null;
        this.zzll = zzeq;
        return zzeq2;
    }

    public final int zzbi() {
        if (this.zzlm != null) {
            return this.zzlm.size();
        }
        if (this.zzll != null) {
            return this.zzll.zzbi();
        }
        return 0;
    }

    public final zzce zzaf() {
        if (this.zzlm != null) {
            return this.zzlm;
        }
        synchronized (this) {
            if (this.zzlm != null) {
                zzce zzce = this.zzlm;
                return zzce;
            }
            if (this.zzll == null) {
                this.zzlm = zzce.zzfx;
            } else {
                this.zzlm = this.zzll.zzaf();
            }
            zzce zzce2 = this.zzlm;
            return zzce2;
        }
    }
}
