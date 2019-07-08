package org.apache.sanselan.common;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.sanselan.ImageWriteException;

public class BinaryOutputStream extends OutputStream implements BinaryConstants {
    private int byteOrder = 77;
    private int count = 0;
    protected boolean debug = false;
    private final OutputStream os;

    public BinaryOutputStream(OutputStream outputStream, int i) {
        this.byteOrder = i;
        this.os = outputStream;
    }

    public void write(int i) throws IOException {
        this.os.write(i);
        this.count++;
    }

    public final void write4Bytes(int i) throws ImageWriteException, IOException {
        writeNBytes(i, 4);
    }

    public final void write2Bytes(int i) throws ImageWriteException, IOException {
        writeNBytes(i, 2);
    }

    public final void writeByteArray(byte[] bArr) throws IOException {
        this.os.write(bArr, 0, bArr.length);
        this.count += bArr.length;
    }

    private byte[] convertValueToByteArray(int i, int i2) {
        byte[] bArr = new byte[i2];
        int i3 = 0;
        if (this.byteOrder == 77) {
            while (i3 < i2) {
                bArr[i3] = (byte) ((i >> (((i2 - i3) - 1) * 8)) & 255);
                i3++;
            }
        } else {
            while (i3 < i2) {
                bArr[i3] = (byte) ((i >> (i3 * 8)) & 255);
                i3++;
            }
        }
        return bArr;
    }

    private final void writeNBytes(int i, int i2) throws ImageWriteException, IOException {
        write(convertValueToByteArray(i, i2));
    }
}
