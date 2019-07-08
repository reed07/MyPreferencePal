package com.google.android.gms.internal.ads;

import java.io.IOException;

final class zziu {
    private static final long[] zzakz = {128, 64, 32, 16, 8, 4, 2, 1};
    private int length;
    private int state;
    private final byte[] zzahi = new byte[8];

    public final void reset() {
        this.state = 0;
        this.length = 0;
    }

    public final long zza(zzia zzia, boolean z, boolean z2, int i) throws IOException, InterruptedException {
        if (this.state == 0) {
            if (!zzia.zza(this.zzahi, 0, 1, z)) {
                return -1;
            }
            this.length = zzae(this.zzahi[0] & 255);
            if (this.length != -1) {
                this.state = 1;
            } else {
                throw new IllegalStateException("No valid varint length mask found");
            }
        }
        int i2 = this.length;
        if (i2 > i) {
            this.state = 0;
            return -2;
        }
        if (i2 != 1) {
            zzia.readFully(this.zzahi, 1, i2 - 1);
        }
        this.state = 0;
        return zza(this.zzahi, this.length, z2);
    }

    public final int zzed() {
        return this.length;
    }

    public static int zzae(int i) {
        int i2 = 0;
        while (true) {
            long[] jArr = zzakz;
            if (i2 >= jArr.length) {
                return -1;
            }
            if ((jArr[i2] & ((long) i)) != 0) {
                return i2 + 1;
            }
            i2++;
        }
    }

    public static long zza(byte[] bArr, int i, boolean z) {
        long j = ((long) bArr[0]) & 255;
        if (z) {
            j &= ~zzakz[i - 1];
        }
        for (int i2 = 1; i2 < i; i2++) {
            j = (j << 8) | (((long) bArr[i2]) & 255);
        }
        return j;
    }
}
