package org.apache.commons.io.output;

import java.io.FilterOutputStream;
import java.io.IOException;

public class ProxyOutputStream extends FilterOutputStream {
    /* access modifiers changed from: protected */
    public void afterWrite(int i) throws IOException {
    }

    /* access modifiers changed from: protected */
    public void beforeWrite(int i) throws IOException {
    }

    public void write(int i) throws IOException {
        try {
            beforeWrite(1);
            this.out.write(i);
            afterWrite(1);
        } catch (IOException e) {
            handleIOException(e);
        }
    }

    public void write(byte[] bArr) throws IOException {
        int i;
        if (bArr != null) {
            try {
                i = bArr.length;
            } catch (IOException e) {
                handleIOException(e);
                return;
            }
        } else {
            i = 0;
        }
        beforeWrite(i);
        this.out.write(bArr);
        afterWrite(i);
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        try {
            beforeWrite(i2);
            this.out.write(bArr, i, i2);
            afterWrite(i2);
        } catch (IOException e) {
            handleIOException(e);
        }
    }

    public void flush() throws IOException {
        try {
            this.out.flush();
        } catch (IOException e) {
            handleIOException(e);
        }
    }

    public void close() throws IOException {
        try {
            this.out.close();
        } catch (IOException e) {
            handleIOException(e);
        }
    }

    /* access modifiers changed from: protected */
    public void handleIOException(IOException iOException) throws IOException {
        throw iOException;
    }
}
