package org.apache.sanselan.common.mylzw;

import java.io.IOException;
import java.io.InputStream;
import org.apache.sanselan.common.BinaryConstants;

public class MyBitInputStream extends InputStream implements BinaryConstants {
    private int bitCache;
    private int bitsInCache;
    private final int byteOrder;
    private long bytesRead;
    private final InputStream is;
    private boolean tiffLZWMode;

    public int read() throws IOException {
        return readBits(8);
    }

    public int readBits(int i) throws IOException {
        int i2;
        while (true) {
            int i3 = this.bitsInCache;
            if (i3 < i) {
                int read = this.is.read();
                if (read < 0) {
                    return this.tiffLZWMode ? 257 : -1;
                }
                int i4 = read & 255;
                int i5 = this.byteOrder;
                if (i5 == 77) {
                    this.bitCache = i4 | (this.bitCache << 8);
                } else if (i5 == 73) {
                    this.bitCache = (i4 << this.bitsInCache) | this.bitCache;
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Unknown byte order: ");
                    sb.append(this.byteOrder);
                    throw new IOException(sb.toString());
                }
                this.bytesRead++;
                this.bitsInCache += 8;
            } else {
                int i6 = (1 << i) - 1;
                int i7 = this.byteOrder;
                if (i7 == 77) {
                    i2 = (this.bitCache >> (i3 - i)) & i6;
                } else if (i7 == 73) {
                    int i8 = this.bitCache;
                    int i9 = i6 & i8;
                    this.bitCache = i8 >> i;
                    i2 = i9;
                } else {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Unknown byte order: ");
                    sb2.append(this.byteOrder);
                    throw new IOException(sb2.toString());
                }
                this.bitsInCache -= i;
                this.bitCache = ((1 << this.bitsInCache) - 1) & this.bitCache;
                return i2;
            }
        }
    }
}
