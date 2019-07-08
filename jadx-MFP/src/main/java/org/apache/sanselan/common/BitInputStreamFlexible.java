package org.apache.sanselan.common;

import java.io.IOException;
import java.io.InputStream;

public class BitInputStreamFlexible extends InputStream implements BinaryConstants {
    private int cacheBitsRemaining;
    private final InputStream is;

    public int read() throws IOException {
        if (this.cacheBitsRemaining <= 0) {
            return this.is.read();
        }
        throw new IOException("BitInputStream: incomplete bit read");
    }
}
