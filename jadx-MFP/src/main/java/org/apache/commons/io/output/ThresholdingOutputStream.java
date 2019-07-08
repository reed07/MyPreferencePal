package org.apache.commons.io.output;

import java.io.IOException;
import java.io.OutputStream;

public abstract class ThresholdingOutputStream extends OutputStream {
    private final int threshold;
    private boolean thresholdExceeded;
    private long written;

    /* access modifiers changed from: protected */
    public abstract OutputStream getStream() throws IOException;

    /* access modifiers changed from: protected */
    public abstract void thresholdReached() throws IOException;

    public void write(int i) throws IOException {
        checkThreshold(1);
        getStream().write(i);
        this.written++;
    }

    public void write(byte[] bArr) throws IOException {
        checkThreshold(bArr.length);
        getStream().write(bArr);
        this.written += (long) bArr.length;
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        checkThreshold(i2);
        getStream().write(bArr, i, i2);
        this.written += (long) i2;
    }

    public void flush() throws IOException {
        getStream().flush();
    }

    public void close() throws IOException {
        try {
            flush();
        } catch (IOException unused) {
        }
        getStream().close();
    }

    /* access modifiers changed from: protected */
    public void checkThreshold(int i) throws IOException {
        if (!this.thresholdExceeded && this.written + ((long) i) > ((long) this.threshold)) {
            this.thresholdExceeded = true;
            thresholdReached();
        }
    }
}
