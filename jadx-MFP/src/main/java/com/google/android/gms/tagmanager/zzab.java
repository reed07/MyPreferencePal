package com.google.android.gms.tagmanager;

final class zzab implements zzac {
    private final /* synthetic */ zzy zzbau;
    private Long zzbav;
    private final /* synthetic */ boolean zzbaw;

    zzab(zzy zzy, boolean z) {
        this.zzbau = zzy;
        this.zzbaw = z;
    }

    public final boolean zzb(Container container) {
        if (!this.zzbaw) {
            return !container.isDefault();
        }
        long lastRefreshTime = container.getLastRefreshTime();
        if (this.zzbav == null) {
            this.zzbav = Long.valueOf(this.zzbau.zzbal.zznz());
        }
        return lastRefreshTime + this.zzbav.longValue() >= this.zzbau.zzrz.currentTimeMillis();
    }
}
