package io.grpc.internal;

import java.io.Closeable;

public interface ReadableBuffer extends Closeable {
    void close();

    ReadableBuffer readBytes(int i);

    void readBytes(byte[] bArr, int i, int i2);

    int readUnsignedByte();

    int readableBytes();
}
