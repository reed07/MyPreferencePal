package org.apache.commons.io.output;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;

public class WriterOutputStream extends OutputStream {
    private final CharsetDecoder decoder;
    private final ByteBuffer decoderIn;
    private final CharBuffer decoderOut;
    private final boolean writeImmediately;
    private final Writer writer;

    public void write(byte[] bArr, int i, int i2) throws IOException {
        while (i2 > 0) {
            int min = Math.min(i2, this.decoderIn.remaining());
            this.decoderIn.put(bArr, i, min);
            processInput(false);
            i2 -= min;
            i += min;
        }
        if (this.writeImmediately) {
            flushOutput();
        }
    }

    public void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    public void write(int i) throws IOException {
        write(new byte[]{(byte) i}, 0, 1);
    }

    public void flush() throws IOException {
        flushOutput();
        this.writer.flush();
    }

    public void close() throws IOException {
        processInput(true);
        flushOutput();
        this.writer.close();
    }

    private void processInput(boolean z) throws IOException {
        CoderResult decode;
        this.decoderIn.flip();
        while (true) {
            decode = this.decoder.decode(this.decoderIn, this.decoderOut, z);
            if (!decode.isOverflow()) {
                break;
            }
            flushOutput();
        }
        if (decode.isUnderflow()) {
            this.decoderIn.compact();
            return;
        }
        throw new IOException("Unexpected coder result");
    }

    private void flushOutput() throws IOException {
        if (this.decoderOut.position() > 0) {
            this.writer.write(this.decoderOut.array(), 0, this.decoderOut.position());
            this.decoderOut.rewind();
        }
    }
}
