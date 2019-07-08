package org.apache.sanselan.util;

import java.io.IOException;
import java.io.OutputStream;

public class DebugOutputStream extends OutputStream {
    private long count;
    private final OutputStream os;

    public void write(int i) throws IOException {
        this.os.write(i);
        this.count++;
    }

    public void write(byte[] bArr) throws IOException {
        this.os.write(bArr);
        this.count += (long) bArr.length;
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.os.write(bArr, i, i2);
        this.count += (long) i2;
    }

    public void flush() throws IOException {
        this.os.flush();
    }

    public void close() throws IOException {
        this.os.close();
    }
}
