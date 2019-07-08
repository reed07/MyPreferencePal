package com.google.android.gms.internal.icing;

public abstract class zzcq {
    private int zzgg;
    private int zzgh;
    private boolean zzgi;

    static zzcq zza(byte[] bArr, int i, int i2, boolean z) {
        zzcs zzcs = new zzcs(bArr, 0, i2, false);
        try {
            zzcs.zzn(i2);
            return zzcs;
        } catch (zzdr e) {
            throw new IllegalArgumentException(e);
        }
    }

    public abstract int zzau();

    public abstract int zzn(int i) throws zzdr;

    private zzcq() {
        this.zzgg = 100;
        this.zzgh = Integer.MAX_VALUE;
        this.zzgi = false;
    }
}
