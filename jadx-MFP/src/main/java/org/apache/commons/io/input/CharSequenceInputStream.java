package org.apache.commons.io.input;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;

public class CharSequenceInputStream extends InputStream {
    private final ByteBuffer bbuf;
    private final CharBuffer cbuf;
    private final CharsetEncoder encoder;
    private int mark;

    public void close() throws IOException {
    }

    public boolean markSupported() {
        return true;
    }

    private void fillBuffer() throws CharacterCodingException {
        this.bbuf.compact();
        CoderResult encode = this.encoder.encode(this.cbuf, this.bbuf, true);
        if (encode.isError()) {
            encode.throwException();
        }
        this.bbuf.flip();
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (bArr == null) {
            throw new NullPointerException("Byte array is null");
        } else if (i2 < 0 || i + i2 > bArr.length) {
            StringBuilder sb = new StringBuilder();
            sb.append("Array Size=");
            sb.append(bArr.length);
            sb.append(", offset=");
            sb.append(i);
            sb.append(", length=");
            sb.append(i2);
            throw new IndexOutOfBoundsException(sb.toString());
        } else {
            int i3 = 0;
            if (i2 == 0) {
                return 0;
            }
            if (!this.bbuf.hasRemaining() && !this.cbuf.hasRemaining()) {
                return -1;
            }
            while (i2 > 0) {
                if (!this.bbuf.hasRemaining()) {
                    fillBuffer();
                    if (!this.bbuf.hasRemaining() && !this.cbuf.hasRemaining()) {
                        break;
                    }
                } else {
                    int min = Math.min(this.bbuf.remaining(), i2);
                    this.bbuf.get(bArr, i, min);
                    i += min;
                    i2 -= min;
                    i3 += min;
                }
            }
            if (i3 == 0 && !this.cbuf.hasRemaining()) {
                i3 = -1;
            }
            return i3;
        }
    }

    public int read() throws IOException {
        while (!this.bbuf.hasRemaining()) {
            fillBuffer();
            if (!this.bbuf.hasRemaining() && !this.cbuf.hasRemaining()) {
                return -1;
            }
        }
        return this.bbuf.get() & 255;
    }

    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    public long skip(long j) throws IOException {
        int i = 0;
        while (j > 0 && this.cbuf.hasRemaining()) {
            this.cbuf.get();
            j--;
            i++;
        }
        return (long) i;
    }

    public int available() throws IOException {
        return this.cbuf.remaining();
    }

    public synchronized void mark(int i) {
        this.mark = this.cbuf.position();
    }

    public synchronized void reset() throws IOException {
        if (this.mark != -1) {
            this.cbuf.position(this.mark);
            this.mark = -1;
        }
    }
}
