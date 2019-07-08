package com.google.android.gms.internal.ads;

import java.util.Arrays;

public final class zzpa implements zzot {
    private final boolean zzbfv;
    private final int zzbfw;
    private final byte[] zzbfx;
    private final zzos[] zzbfy;
    private int zzbfz;
    private int zzbga;
    private int zzbgb;
    private zzos[] zzbgc;

    public zzpa(boolean z, int i) {
        this(true, 65536, 0);
    }

    private zzpa(boolean z, int i, int i2) {
        zzpo.checkArgument(true);
        zzpo.checkArgument(true);
        this.zzbfv = true;
        this.zzbfw = 65536;
        this.zzbgb = 0;
        this.zzbgc = new zzos[100];
        this.zzbfx = null;
        this.zzbfy = new zzos[1];
    }

    public final synchronized void reset() {
        if (this.zzbfv) {
            zzbh(0);
        }
    }

    public final synchronized void zzbh(int i) {
        boolean z = i < this.zzbfz;
        this.zzbfz = i;
        if (z) {
            zzo();
        }
    }

    public final synchronized zzos zzgq() {
        zzos zzos;
        this.zzbga++;
        if (this.zzbgb > 0) {
            zzos[] zzosArr = this.zzbgc;
            int i = this.zzbgb - 1;
            this.zzbgb = i;
            zzos = zzosArr[i];
            this.zzbgc[this.zzbgb] = null;
        } else {
            zzos = new zzos(new byte[this.zzbfw], 0);
        }
        return zzos;
    }

    public final synchronized void zza(zzos zzos) {
        this.zzbfy[0] = zzos;
        zza(this.zzbfy);
    }

    public final synchronized void zza(zzos[] zzosArr) {
        boolean z;
        if (this.zzbgb + zzosArr.length >= this.zzbgc.length) {
            this.zzbgc = (zzos[]) Arrays.copyOf(this.zzbgc, Math.max(this.zzbgc.length << 1, this.zzbgb + zzosArr.length));
        }
        for (zzos zzos : zzosArr) {
            if (zzos.data != null) {
                if (zzos.data.length != this.zzbfw) {
                    z = false;
                    zzpo.checkArgument(z);
                    zzos[] zzosArr2 = this.zzbgc;
                    int i = this.zzbgb;
                    this.zzbgb = i + 1;
                    zzosArr2[i] = zzos;
                }
            }
            z = true;
            zzpo.checkArgument(z);
            zzos[] zzosArr22 = this.zzbgc;
            int i2 = this.zzbgb;
            this.zzbgb = i2 + 1;
            zzosArr22[i2] = zzos;
        }
        this.zzbga -= zzosArr.length;
        notifyAll();
    }

    public final synchronized void zzo() {
        int max = Math.max(0, zzqe.zzf(this.zzbfz, this.zzbfw) - this.zzbga);
        if (max < this.zzbgb) {
            Arrays.fill(this.zzbgc, max, this.zzbgb, null);
            this.zzbgb = max;
        }
    }

    public final synchronized int zzgv() {
        return this.zzbga * this.zzbfw;
    }

    public final int zzgr() {
        return this.zzbfw;
    }
}
