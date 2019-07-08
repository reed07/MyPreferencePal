package org.apache.sanselan.common;

import java.io.IOException;
import java.io.InputStream;
import org.apache.sanselan.ImageReadException;

public class BinaryFileParser extends BinaryFileFunctions {
    private int byteOrder = 77;

    /* access modifiers changed from: protected */
    public void setByteOrder(int i, int i2) throws ImageReadException, IOException {
        if (i != i2) {
            StringBuilder sb = new StringBuilder();
            sb.append("Byte Order bytes don't match (");
            sb.append(i);
            sb.append(", ");
            sb.append(i2);
            sb.append(").");
            throw new ImageReadException(sb.toString());
        } else if (i == 77) {
            this.byteOrder = i;
        } else if (i == 73) {
            this.byteOrder = i;
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Unknown Byte Order hint: ");
            sb2.append(i);
            throw new ImageReadException(sb2.toString());
        }
    }

    /* access modifiers changed from: protected */
    public void setByteOrder(int i) {
        this.byteOrder = i;
    }

    /* access modifiers changed from: protected */
    public int getByteOrder() {
        return this.byteOrder;
    }

    /* access modifiers changed from: protected */
    public final int convertByteArrayToInt(String str, byte[] bArr) {
        return convertByteArrayToInt(str, bArr, this.byteOrder);
    }

    public final int read4Bytes(String str, InputStream inputStream, String str2) throws ImageReadException, IOException {
        return read4Bytes(str, inputStream, str2, this.byteOrder);
    }

    public final int read2Bytes(String str, InputStream inputStream, String str2) throws ImageReadException, IOException {
        return read2Bytes(str, inputStream, str2, this.byteOrder);
    }

    public static boolean byteArrayHasPrefix(byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr.length < bArr2.length) {
            return false;
        }
        for (int i = 0; i < bArr2.length; i++) {
            if (bArr[i] != bArr2[i]) {
                return false;
            }
        }
        return true;
    }
}
