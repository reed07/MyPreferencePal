package com.google.android.gms.internal.ads;

import java.io.Closeable;
import java.io.IOException;
import java.nio.ByteBuffer;

public interface zzbwa extends Closeable {
    void close() throws IOException;

    long position() throws IOException;

    int read(ByteBuffer byteBuffer) throws IOException;

    long size() throws IOException;

    void zzaw(long j) throws IOException;

    ByteBuffer zzk(long j, long j2) throws IOException;
}
