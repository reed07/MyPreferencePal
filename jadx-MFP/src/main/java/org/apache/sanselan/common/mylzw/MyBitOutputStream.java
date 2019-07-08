package org.apache.sanselan.common.mylzw;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.sanselan.common.BinaryConstants;

public class MyBitOutputStream extends OutputStream implements BinaryConstants {
    private int bitCache;
    private int bitsInCache;
    private final int byteOrder;
    private int bytesWritten;
    private final OutputStream os;

    public void write(int i) throws IOException {
        writeBits(i, 8);
    }

    public void writeBits(int i, int i2) throws IOException {
        int i3 = i & ((1 << i2) - 1);
        int i4 = this.byteOrder;
        if (i4 == 77) {
            this.bitCache = i3 | (this.bitCache << i2);
        } else if (i4 == 73) {
            this.bitCache = (i3 << this.bitsInCache) | this.bitCache;
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Unknown byte order: ");
            sb.append(this.byteOrder);
            throw new IOException(sb.toString());
        }
        this.bitsInCache += i2;
        while (true) {
            int i5 = this.bitsInCache;
            if (i5 >= 8) {
                int i6 = this.byteOrder;
                if (i6 == 77) {
                    actualWrite((this.bitCache >> (i5 - 8)) & 255);
                    this.bitsInCache -= 8;
                } else if (i6 == 73) {
                    actualWrite(this.bitCache & 255);
                    this.bitCache >>= 8;
                    this.bitsInCache -= 8;
                }
                this.bitCache = ((1 << this.bitsInCache) - 1) & this.bitCache;
            } else {
                return;
            }
        }
    }

    private void actualWrite(int i) throws IOException {
        this.os.write(i);
        this.bytesWritten++;
    }
}
