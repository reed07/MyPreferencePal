package io.grpc.okhttp;

import io.grpc.internal.AbstractReadableBuffer;
import io.grpc.internal.ReadableBuffer;
import okio.Buffer;

class OkHttpReadableBuffer extends AbstractReadableBuffer {
    private final Buffer buffer;

    OkHttpReadableBuffer(Buffer buffer2) {
        this.buffer = buffer2;
    }

    public int readableBytes() {
        return (int) this.buffer.size();
    }

    public int readUnsignedByte() {
        return this.buffer.readByte() & 255;
    }

    public void readBytes(byte[] bArr, int i, int i2) {
        while (i2 > 0) {
            int read = this.buffer.read(bArr, i, i2);
            if (read != -1) {
                i2 -= read;
                i += read;
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("EOF trying to read ");
                sb.append(i2);
                sb.append(" bytes");
                throw new IndexOutOfBoundsException(sb.toString());
            }
        }
    }

    public ReadableBuffer readBytes(int i) {
        Buffer buffer2 = new Buffer();
        buffer2.write(this.buffer, (long) i);
        return new OkHttpReadableBuffer(buffer2);
    }

    public void close() {
        this.buffer.clear();
    }
}
