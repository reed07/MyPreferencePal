package io.grpc.internal;

import com.google.common.base.Preconditions;
import io.grpc.KnownLength;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public final class ReadableBuffers {
    private static final ReadableBuffer EMPTY_BUFFER = new ByteArrayWrapper(new byte[0]);

    private static final class BufferInputStream extends InputStream implements KnownLength {
        final ReadableBuffer buffer;

        public BufferInputStream(ReadableBuffer readableBuffer) {
            this.buffer = (ReadableBuffer) Preconditions.checkNotNull(readableBuffer, "buffer");
        }

        public int available() throws IOException {
            return this.buffer.readableBytes();
        }

        public int read() {
            if (this.buffer.readableBytes() == 0) {
                return -1;
            }
            return this.buffer.readUnsignedByte();
        }

        public int read(byte[] bArr, int i, int i2) throws IOException {
            if (this.buffer.readableBytes() == 0) {
                return -1;
            }
            int min = Math.min(this.buffer.readableBytes(), i2);
            this.buffer.readBytes(bArr, i, min);
            return min;
        }

        public void close() throws IOException {
            this.buffer.close();
        }
    }

    private static class ByteArrayWrapper extends AbstractReadableBuffer {
        final byte[] bytes;
        final int end;
        int offset;

        ByteArrayWrapper(byte[] bArr) {
            this(bArr, 0, bArr.length);
        }

        ByteArrayWrapper(byte[] bArr, int i, int i2) {
            boolean z = true;
            Preconditions.checkArgument(i >= 0, "offset must be >= 0");
            Preconditions.checkArgument(i2 >= 0, "length must be >= 0");
            int i3 = i2 + i;
            if (i3 > bArr.length) {
                z = false;
            }
            Preconditions.checkArgument(z, "offset + length exceeds array boundary");
            this.bytes = (byte[]) Preconditions.checkNotNull(bArr, "bytes");
            this.offset = i;
            this.end = i3;
        }

        public int readableBytes() {
            return this.end - this.offset;
        }

        public int readUnsignedByte() {
            checkReadable(1);
            byte[] bArr = this.bytes;
            int i = this.offset;
            this.offset = i + 1;
            return bArr[i] & 255;
        }

        public void readBytes(byte[] bArr, int i, int i2) {
            System.arraycopy(this.bytes, this.offset, bArr, i, i2);
            this.offset += i2;
        }

        public ByteArrayWrapper readBytes(int i) {
            checkReadable(i);
            int i2 = this.offset;
            this.offset = i2 + i;
            return new ByteArrayWrapper(this.bytes, i2, i);
        }
    }

    public static ReadableBuffer wrap(byte[] bArr, int i, int i2) {
        return new ByteArrayWrapper(bArr, i, i2);
    }

    public static byte[] readArray(ReadableBuffer readableBuffer) {
        Preconditions.checkNotNull(readableBuffer, "buffer");
        int readableBytes = readableBuffer.readableBytes();
        byte[] bArr = new byte[readableBytes];
        readableBuffer.readBytes(bArr, 0, readableBytes);
        return bArr;
    }

    public static String readAsString(ReadableBuffer readableBuffer, Charset charset) {
        Preconditions.checkNotNull(charset, "charset");
        return new String(readArray(readableBuffer), charset);
    }

    public static InputStream openStream(ReadableBuffer readableBuffer, boolean z) {
        if (!z) {
            readableBuffer = ignoreClose(readableBuffer);
        }
        return new BufferInputStream(readableBuffer);
    }

    public static ReadableBuffer ignoreClose(ReadableBuffer readableBuffer) {
        return new ForwardingReadableBuffer(readableBuffer) {
            public void close() {
            }
        };
    }

    private ReadableBuffers() {
    }
}
