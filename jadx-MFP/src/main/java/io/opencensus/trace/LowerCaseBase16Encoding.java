package io.opencensus.trace;

import java.util.Arrays;

final class LowerCaseBase16Encoding {
    private static final byte[] DECODING = buildDecodingArray();
    private static final char[] ENCODING = buildEncodingArray();

    private static char[] buildEncodingArray() {
        char[] cArr = new char[512];
        for (int i = 0; i < 256; i++) {
            cArr[i] = "0123456789abcdef".charAt(i >>> 4);
            cArr[i | 256] = "0123456789abcdef".charAt(i & 15);
        }
        return cArr;
    }

    private static byte[] buildDecodingArray() {
        byte[] bArr = new byte[128];
        Arrays.fill(bArr, -1);
        for (int i = 0; i < 16; i++) {
            bArr["0123456789abcdef".charAt(i)] = (byte) i;
        }
        return bArr;
    }

    static String encodeToString(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b : bArr) {
            byte b2 = b & 255;
            sb.append(ENCODING[b2]);
            sb.append(ENCODING[b2 | 256]);
        }
        return sb.toString();
    }

    private LowerCaseBase16Encoding() {
    }
}
