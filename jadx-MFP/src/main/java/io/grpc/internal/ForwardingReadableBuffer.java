package io.grpc.internal;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;

public abstract class ForwardingReadableBuffer implements ReadableBuffer {
    private final ReadableBuffer buf;

    public ForwardingReadableBuffer(ReadableBuffer readableBuffer) {
        this.buf = (ReadableBuffer) Preconditions.checkNotNull(readableBuffer, "buf");
    }

    public int readableBytes() {
        return this.buf.readableBytes();
    }

    public int readUnsignedByte() {
        return this.buf.readUnsignedByte();
    }

    public void readBytes(byte[] bArr, int i, int i2) {
        this.buf.readBytes(bArr, i, i2);
    }

    public ReadableBuffer readBytes(int i) {
        return this.buf.readBytes(i);
    }

    public void close() {
        this.buf.close();
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add("delegate", (Object) this.buf).toString();
    }
}
