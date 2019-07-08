package com.google.android.gms.internal.ads;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public final class zzav extends ByteArrayOutputStream {
    private final zzak zzbq;

    public zzav(zzak zzak, int i) {
        this.zzbq = zzak;
        this.buf = this.zzbq.zzb(Math.max(i, 256));
    }

    public final void close() throws IOException {
        this.zzbq.zza(this.buf);
        this.buf = null;
        super.close();
    }

    public final void finalize() {
        this.zzbq.zza(this.buf);
    }

    private final void zzc(int i) {
        if (this.count + i > this.buf.length) {
            byte[] zzb = this.zzbq.zzb((this.count + i) << 1);
            System.arraycopy(this.buf, 0, zzb, 0, this.count);
            this.zzbq.zza(this.buf);
            this.buf = zzb;
        }
    }

    public final synchronized void write(byte[] bArr, int i, int i2) {
        zzc(i2);
        super.write(bArr, i, i2);
    }

    public final synchronized void write(int i) {
        zzc(1);
        super.write(i);
    }
}
