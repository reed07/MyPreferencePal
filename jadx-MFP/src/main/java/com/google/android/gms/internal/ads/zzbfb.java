package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.nio.ByteBuffer;

@zzark
final class zzbfb implements zzbwa {
    private final ByteBuffer zzaep;

    zzbfb(ByteBuffer byteBuffer) {
        this.zzaep = byteBuffer.duplicate();
    }

    public final void close() throws IOException {
    }

    public final int read(ByteBuffer byteBuffer) throws IOException {
        if (this.zzaep.remaining() == 0 && byteBuffer.remaining() > 0) {
            return -1;
        }
        int min = Math.min(byteBuffer.remaining(), this.zzaep.remaining());
        byte[] bArr = new byte[min];
        this.zzaep.get(bArr);
        byteBuffer.put(bArr);
        return min;
    }

    public final long size() throws IOException {
        return (long) this.zzaep.limit();
    }

    public final long position() throws IOException {
        return (long) this.zzaep.position();
    }

    public final void zzaw(long j) throws IOException {
        this.zzaep.position((int) j);
    }

    public final ByteBuffer zzk(long j, long j2) throws IOException {
        int position = this.zzaep.position();
        this.zzaep.position((int) j);
        ByteBuffer slice = this.zzaep.slice();
        slice.limit((int) j2);
        this.zzaep.position(position);
        return slice;
    }
}
