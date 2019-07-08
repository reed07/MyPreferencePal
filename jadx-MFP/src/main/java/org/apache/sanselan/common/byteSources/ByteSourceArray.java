package org.apache.sanselan.common.byteSources;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ByteSourceArray extends ByteSource {
    private final byte[] bytes;

    public ByteSourceArray(byte[] bArr) {
        super(null);
        this.bytes = bArr;
    }

    public InputStream getInputStream() {
        return new ByteArrayInputStream(this.bytes);
    }

    public byte[] getBlock(int i, int i2) throws IOException {
        int i3 = i + i2;
        byte[] bArr = this.bytes;
        if (i3 <= bArr.length) {
            byte[] bArr2 = new byte[i2];
            System.arraycopy(bArr, i, bArr2, 0, i2);
            return bArr2;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Could not read block (block start: ");
        sb.append(i);
        sb.append(", block length: ");
        sb.append(i2);
        sb.append(", data length: ");
        sb.append(this.bytes.length);
        sb.append(").");
        throw new IOException(sb.toString());
    }

    public long getLength() {
        return (long) this.bytes.length;
    }
}
