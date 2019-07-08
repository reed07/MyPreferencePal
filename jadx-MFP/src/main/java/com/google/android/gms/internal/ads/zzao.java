package com.google.android.gms.internal.ads;

import android.support.annotation.VisibleForTesting;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

@VisibleForTesting
final class zzao extends FilterInputStream {
    private final long zzcc;
    private long zzcd;

    zzao(InputStream inputStream, long j) {
        super(inputStream);
        this.zzcc = j;
    }

    public final int read() throws IOException {
        int read = super.read();
        if (read != -1) {
            this.zzcd++;
        }
        return read;
    }

    public final int read(byte[] bArr, int i, int i2) throws IOException {
        int read = super.read(bArr, i, i2);
        if (read != -1) {
            this.zzcd += (long) read;
        }
        return read;
    }

    /* access modifiers changed from: 0000 */
    public final long zzp() {
        return this.zzcc - this.zzcd;
    }
}
