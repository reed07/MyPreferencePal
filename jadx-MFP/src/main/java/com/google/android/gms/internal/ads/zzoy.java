package com.google.android.gms.internal.ads;

import android.support.annotation.NonNull;
import java.io.IOException;
import java.io.InputStream;

public final class zzoy extends InputStream {
    private boolean closed = false;
    private final zzov zzagy;
    private final zzoz zzazo;
    private final byte[] zzbfq;
    private boolean zzbfr = false;
    private long zzbfs;

    public zzoy(zzov zzov, zzoz zzoz) {
        this.zzagy = zzov;
        this.zzazo = zzoz;
        this.zzbfq = new byte[1];
    }

    public final long zzgt() {
        return this.zzbfs;
    }

    public final void open() throws IOException {
        zzgu();
    }

    public final int read() throws IOException {
        if (read(this.zzbfq) == -1) {
            return -1;
        }
        return this.zzbfq[0] & 255;
    }

    public final int read(@NonNull byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    public final int read(@NonNull byte[] bArr, int i, int i2) throws IOException {
        zzpo.checkState(!this.closed);
        zzgu();
        int read = this.zzagy.read(bArr, i, i2);
        if (read == -1) {
            return -1;
        }
        this.zzbfs += (long) read;
        return read;
    }

    public final void close() throws IOException {
        if (!this.closed) {
            this.zzagy.close();
            this.closed = true;
        }
    }

    private final void zzgu() throws IOException {
        if (!this.zzbfr) {
            this.zzagy.zza(this.zzazo);
            this.zzbfr = true;
        }
    }
}
