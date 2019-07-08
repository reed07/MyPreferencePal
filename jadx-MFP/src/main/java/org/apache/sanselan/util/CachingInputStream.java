package org.apache.sanselan.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class CachingInputStream extends InputStream {
    private final ByteArrayOutputStream baos;
    private final InputStream is;

    public int read() throws IOException {
        int read = this.is.read();
        this.baos.write(read);
        return read;
    }

    public int available() throws IOException {
        return this.is.available();
    }

    public void close() throws IOException {
        this.is.close();
    }
}
