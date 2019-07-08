package org.apache.sanselan.common;

import java.io.IOException;
import java.io.OutputStream;

public class MyByteArrayOutputStream extends OutputStream {
    private final byte[] bytes;
    private int count;

    public void write(int i) throws IOException {
        int i2 = this.count;
        byte[] bArr = this.bytes;
        if (i2 < bArr.length) {
            bArr[i2] = (byte) i;
            this.count = i2 + 1;
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Write exceeded expected length (");
        sb.append(this.count);
        sb.append(", ");
        sb.append(this.bytes.length);
        sb.append(")");
        throw new IOException(sb.toString());
    }
}
