package com.google.android.gms.internal.ads;

final class zzju {
    public int zzapk;
    public zzjf zzast;
    public long zzasu;
    public long zzasv;
    public long zzasw;
    public int zzasx;
    public long[] zzasy;
    public int[] zzasz;
    public int[] zzata;
    public int[] zzatb;
    public long[] zzatc;
    public boolean[] zzatd;
    public boolean zzate;
    public boolean[] zzatf;
    public zzjt zzatg;
    public int zzath;
    public zzpx zzati;
    public boolean zzatj;
    public long zzatk;

    zzju() {
    }

    public final void zzak(int i) {
        zzpx zzpx = this.zzati;
        if (zzpx == null || zzpx.limit() < i) {
            this.zzati = new zzpx(i);
        }
        this.zzath = i;
        this.zzate = true;
        this.zzatj = true;
    }

    public final long zzal(int i) {
        return this.zzatc[i] + ((long) this.zzatb[i]);
    }
}
