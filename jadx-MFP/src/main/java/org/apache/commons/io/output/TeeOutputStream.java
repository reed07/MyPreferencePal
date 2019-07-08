package org.apache.commons.io.output;

import java.io.IOException;
import java.io.OutputStream;

public class TeeOutputStream extends ProxyOutputStream {
    protected OutputStream branch;

    public synchronized void write(byte[] bArr) throws IOException {
        super.write(bArr);
        this.branch.write(bArr);
    }

    public synchronized void write(byte[] bArr, int i, int i2) throws IOException {
        super.write(bArr, i, i2);
        this.branch.write(bArr, i, i2);
    }

    public synchronized void write(int i) throws IOException {
        super.write(i);
        this.branch.write(i);
    }

    public void flush() throws IOException {
        super.flush();
        this.branch.flush();
    }

    public void close() throws IOException {
        try {
            super.close();
        } finally {
            this.branch.close();
        }
    }
}
