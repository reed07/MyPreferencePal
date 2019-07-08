package org.apache.commons.io.input;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;

public class ReaderInputStream extends InputStream {
    private final CharsetEncoder encoder;
    private final CharBuffer encoderIn;
    private final ByteBuffer encoderOut;
    private boolean endOfInput;
    private CoderResult lastCoderResult;
    private final Reader reader;

    private void fillBuffer() throws IOException {
        if (!this.endOfInput) {
            CoderResult coderResult = this.lastCoderResult;
            if (coderResult == null || coderResult.isUnderflow()) {
                this.encoderIn.compact();
                int position = this.encoderIn.position();
                int read = this.reader.read(this.encoderIn.array(), position, this.encoderIn.remaining());
                if (read == -1) {
                    this.endOfInput = true;
                } else {
                    this.encoderIn.position(position + read);
                }
                this.encoderIn.flip();
            }
        }
        this.encoderOut.compact();
        this.lastCoderResult = this.encoder.encode(this.encoderIn, this.encoderOut, this.endOfInput);
        this.encoderOut.flip();
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (bArr == null) {
            throw new NullPointerException("Byte array must not be null");
        } else if (i2 < 0 || i < 0 || i + i2 > bArr.length) {
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
            while (i2 > 0) {
                if (!this.encoderOut.hasRemaining()) {
                    fillBuffer();
                    if (this.endOfInput && !this.encoderOut.hasRemaining()) {
                        break;
                    }
                } else {
                    int min = Math.min(this.encoderOut.remaining(), i2);
                    this.encoderOut.get(bArr, i, min);
                    i += min;
                    i2 -= min;
                    i3 += min;
                }
            }
            if (i3 == 0 && this.endOfInput) {
                i3 = -1;
            }
            return i3;
        }
    }

    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    public int read() throws IOException {
        while (!this.encoderOut.hasRemaining()) {
            fillBuffer();
            if (this.endOfInput && !this.encoderOut.hasRemaining()) {
                return -1;
            }
        }
        return this.encoderOut.get() & 255;
    }

    public void close() throws IOException {
        this.reader.close();
    }
}
