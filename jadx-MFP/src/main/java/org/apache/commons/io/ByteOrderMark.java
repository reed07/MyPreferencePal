package org.apache.commons.io;

import com.myfitnesspal.shared.constants.Constants.RequestCodes;
import java.io.Serializable;

public class ByteOrderMark implements Serializable {
    public static final ByteOrderMark UTF_16BE = new ByteOrderMark("UTF-16BE", 254, 255);
    public static final ByteOrderMark UTF_16LE = new ByteOrderMark("UTF-16LE", 255, 254);
    public static final ByteOrderMark UTF_32BE = new ByteOrderMark("UTF-32BE", 0, 0, 254, 255);
    public static final ByteOrderMark UTF_32LE = new ByteOrderMark("UTF-32LE", 255, 254, 0, 0);
    public static final ByteOrderMark UTF_8 = new ByteOrderMark("UTF-8", 239, RequestCodes.PROGRESS_STATUS_UPDATE, 191);
    private static final long serialVersionUID = 1;
    private final int[] bytes;
    private final String charsetName;

    public ByteOrderMark(String str, int... iArr) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("No charsetName specified");
        } else if (iArr == null || iArr.length == 0) {
            throw new IllegalArgumentException("No bytes specified");
        } else {
            this.charsetName = str;
            this.bytes = new int[iArr.length];
            System.arraycopy(iArr, 0, this.bytes, 0, iArr.length);
        }
    }

    public int length() {
        return this.bytes.length;
    }

    public int get(int i) {
        return this.bytes[i];
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ByteOrderMark)) {
            return false;
        }
        ByteOrderMark byteOrderMark = (ByteOrderMark) obj;
        if (this.bytes.length != byteOrderMark.length()) {
            return false;
        }
        int i = 0;
        while (true) {
            int[] iArr = this.bytes;
            if (i >= iArr.length) {
                return true;
            }
            if (iArr[i] != byteOrderMark.get(i)) {
                return false;
            }
            i++;
        }
    }

    public int hashCode() {
        int hashCode = getClass().hashCode();
        for (int i : this.bytes) {
            hashCode += i;
        }
        return hashCode;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append('[');
        sb.append(this.charsetName);
        sb.append(": ");
        for (int i = 0; i < this.bytes.length; i++) {
            if (i > 0) {
                sb.append(",");
            }
            sb.append("0x");
            sb.append(Integer.toHexString(this.bytes[i] & 255).toUpperCase());
        }
        sb.append(']');
        return sb.toString();
    }
}
