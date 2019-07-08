package org.apache.sanselan.common.mylzw;

import java.io.IOException;
import java.io.InputStream;

public class BitsToByteInputStream extends InputStream {
    private final int desiredDepth;
    private final MyBitInputStream is;

    public int read() throws IOException {
        return readBits(8);
    }

    public int readBits(int i) throws IOException {
        int readBits = this.is.readBits(i);
        int i2 = this.desiredDepth;
        if (i < i2) {
            return readBits << (i2 - i);
        }
        return i > i2 ? readBits >> (i - i2) : readBits;
    }
}
