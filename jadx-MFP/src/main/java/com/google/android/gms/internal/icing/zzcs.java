package com.google.android.gms.internal.icing;

final class zzcs extends zzcq {
    private final byte[] buffer;
    private int limit;
    private int pos;
    private final boolean zzgj;
    private int zzgk;
    private int zzgl;
    private int zzgm;

    private zzcs(byte[] bArr, int i, int i2, boolean z) {
        super();
        this.zzgm = Integer.MAX_VALUE;
        this.buffer = bArr;
        this.limit = i2 + i;
        this.pos = i;
        this.zzgl = this.pos;
        this.zzgj = z;
    }

    public final int zzn(int i) throws zzdr {
        if (i >= 0) {
            int zzau = i + zzau();
            int i2 = this.zzgm;
            if (zzau <= i2) {
                this.zzgm = zzau;
                this.limit += this.zzgk;
                int i3 = this.limit;
                int i4 = i3 - this.zzgl;
                int i5 = this.zzgm;
                if (i4 > i5) {
                    this.zzgk = i4 - i5;
                    this.limit = i3 - this.zzgk;
                } else {
                    this.zzgk = 0;
                }
                return i2;
            }
            throw new zzdr("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }
        throw new zzdr("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    public final int zzau() {
        return this.pos - this.zzgl;
    }
}
