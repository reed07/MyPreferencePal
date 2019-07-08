package com.google.android.gms.internal.ads;

final class zziz {
    public int index;
    public final int length;
    public int zzapa;
    public long zzapb;
    private final boolean zzapc;
    private final zzpx zzapd;
    private final zzpx zzape;
    private int zzapf;
    private int zzapg;

    public zziz(zzpx zzpx, zzpx zzpx2, boolean z) {
        this.zzape = zzpx;
        this.zzapd = zzpx2;
        this.zzapc = z;
        zzpx2.setPosition(12);
        this.length = zzpx2.zzhg();
        zzpx.setPosition(12);
        this.zzapg = zzpx.zzhg();
        boolean z2 = true;
        if (zzpx.readInt() != 1) {
            z2 = false;
        }
        zzpo.checkState(z2, "first_chunk must be 1");
        this.index = -1;
    }

    public final boolean zzee() {
        long j;
        int i = this.index + 1;
        this.index = i;
        if (i == this.length) {
            return false;
        }
        if (this.zzapc) {
            j = this.zzapd.zzhh();
        } else {
            j = this.zzapd.zzhd();
        }
        this.zzapb = j;
        if (this.index == this.zzapf) {
            this.zzapa = this.zzape.zzhg();
            this.zzape.zzbl(4);
            int i2 = this.zzapg - 1;
            this.zzapg = i2;
            this.zzapf = i2 > 0 ? this.zzape.zzhg() - 1 : -1;
        }
        return true;
    }
}
