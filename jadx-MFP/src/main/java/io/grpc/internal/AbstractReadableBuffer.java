package io.grpc.internal;

public abstract class AbstractReadableBuffer implements ReadableBuffer {
    public void close() {
    }

    public final int readInt() {
        checkReadable(4);
        return (readUnsignedByte() << 24) | (readUnsignedByte() << 16) | (readUnsignedByte() << 8) | readUnsignedByte();
    }

    /* access modifiers changed from: protected */
    public final void checkReadable(int i) {
        if (readableBytes() < i) {
            throw new IndexOutOfBoundsException();
        }
    }
}
