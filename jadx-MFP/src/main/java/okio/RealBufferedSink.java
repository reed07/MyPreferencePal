package okio;

import java.io.IOException;
import java.nio.ByteBuffer;

final class RealBufferedSink implements BufferedSink {
    public final Buffer buffer = new Buffer();
    boolean closed;
    public final Sink sink;

    RealBufferedSink(Sink sink2) {
        if (sink2 != null) {
            this.sink = sink2;
            return;
        }
        throw new NullPointerException("sink == null");
    }

    public Buffer buffer() {
        return this.buffer;
    }

    public void write(Buffer buffer2, long j) throws IOException {
        if (!this.closed) {
            this.buffer.write(buffer2, j);
            emitCompleteSegments();
            return;
        }
        throw new IllegalStateException("closed");
    }

    public BufferedSink write(ByteString byteString) throws IOException {
        if (!this.closed) {
            this.buffer.write(byteString);
            return emitCompleteSegments();
        }
        throw new IllegalStateException("closed");
    }

    public BufferedSink writeUtf8(String str) throws IOException {
        if (!this.closed) {
            this.buffer.writeUtf8(str);
            return emitCompleteSegments();
        }
        throw new IllegalStateException("closed");
    }

    public BufferedSink write(byte[] bArr) throws IOException {
        if (!this.closed) {
            this.buffer.write(bArr);
            return emitCompleteSegments();
        }
        throw new IllegalStateException("closed");
    }

    public BufferedSink write(byte[] bArr, int i, int i2) throws IOException {
        if (!this.closed) {
            this.buffer.write(bArr, i, i2);
            return emitCompleteSegments();
        }
        throw new IllegalStateException("closed");
    }

    public int write(ByteBuffer byteBuffer) throws IOException {
        if (!this.closed) {
            int write = this.buffer.write(byteBuffer);
            emitCompleteSegments();
            return write;
        }
        throw new IllegalStateException("closed");
    }

    public long writeAll(Source source) throws IOException {
        if (source != null) {
            long j = 0;
            while (true) {
                long read = source.read(this.buffer, 8192);
                if (read == -1) {
                    return j;
                }
                j += read;
                emitCompleteSegments();
            }
        } else {
            throw new IllegalArgumentException("source == null");
        }
    }

    public BufferedSink writeByte(int i) throws IOException {
        if (!this.closed) {
            this.buffer.writeByte(i);
            return emitCompleteSegments();
        }
        throw new IllegalStateException("closed");
    }

    public BufferedSink writeShort(int i) throws IOException {
        if (!this.closed) {
            this.buffer.writeShort(i);
            return emitCompleteSegments();
        }
        throw new IllegalStateException("closed");
    }

    public BufferedSink writeInt(int i) throws IOException {
        if (!this.closed) {
            this.buffer.writeInt(i);
            return emitCompleteSegments();
        }
        throw new IllegalStateException("closed");
    }

    public BufferedSink writeIntLe(int i) throws IOException {
        if (!this.closed) {
            this.buffer.writeIntLe(i);
            return emitCompleteSegments();
        }
        throw new IllegalStateException("closed");
    }

    public BufferedSink writeDecimalLong(long j) throws IOException {
        if (!this.closed) {
            this.buffer.writeDecimalLong(j);
            return emitCompleteSegments();
        }
        throw new IllegalStateException("closed");
    }

    public BufferedSink writeHexadecimalUnsignedLong(long j) throws IOException {
        if (!this.closed) {
            this.buffer.writeHexadecimalUnsignedLong(j);
            return emitCompleteSegments();
        }
        throw new IllegalStateException("closed");
    }

    public BufferedSink emitCompleteSegments() throws IOException {
        if (!this.closed) {
            long completeSegmentByteCount = this.buffer.completeSegmentByteCount();
            if (completeSegmentByteCount > 0) {
                this.sink.write(this.buffer, completeSegmentByteCount);
            }
            return this;
        }
        throw new IllegalStateException("closed");
    }

    public BufferedSink emit() throws IOException {
        if (!this.closed) {
            long size = this.buffer.size();
            if (size > 0) {
                this.sink.write(this.buffer, size);
            }
            return this;
        }
        throw new IllegalStateException("closed");
    }

    public void flush() throws IOException {
        if (!this.closed) {
            if (this.buffer.size > 0) {
                Sink sink2 = this.sink;
                Buffer buffer2 = this.buffer;
                sink2.write(buffer2, buffer2.size);
            }
            this.sink.flush();
            return;
        }
        throw new IllegalStateException("closed");
    }

    public boolean isOpen() {
        return !this.closed;
    }

    public void close() throws IOException {
        if (!this.closed) {
            Throwable th = null;
            try {
                if (this.buffer.size > 0) {
                    this.sink.write(this.buffer, this.buffer.size);
                }
            } catch (Throwable th2) {
                th = th2;
            }
            try {
                this.sink.close();
            } catch (Throwable th3) {
                if (th == null) {
                    th = th3;
                }
            }
            this.closed = true;
            if (th != null) {
                Util.sneakyRethrow(th);
            }
        }
    }

    public Timeout timeout() {
        return this.sink.timeout();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("buffer(");
        sb.append(this.sink);
        sb.append(")");
        return sb.toString();
    }
}
