package com.nimbusds.jose.util;

import com.google.common.base.Ascii;
import java.util.Arrays;

final class Base64Codec {
    private static final char[] CA = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
    private static final char[] CA_URL_SAFE = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_".toCharArray();
    private static final int[] IA = new int[256];
    private static final int[] IA_URL_SAFE = new int[256];

    Base64Codec() {
    }

    static {
        Arrays.fill(IA, -1);
        int length = CA.length;
        for (int i = 0; i < length; i++) {
            IA[CA[i]] = i;
        }
        IA[61] = 0;
        Arrays.fill(IA_URL_SAFE, -1);
        int length2 = CA_URL_SAFE.length;
        for (int i2 = 0; i2 < length2; i2++) {
            IA_URL_SAFE[CA_URL_SAFE[i2]] = i2;
        }
        IA_URL_SAFE[61] = 0;
    }

    public static int computeEncodedLength(int i, boolean z) {
        if (i == 0) {
            return 0;
        }
        if (!z) {
            return (((i - 1) / 3) + 1) << 2;
        }
        int i2 = (i / 3) << 2;
        int i3 = i % 3;
        if (i3 != 0) {
            i2 = i2 + i3 + 1;
        }
        return i2;
    }

    public static String normalizeEncodedString(String str) {
        int length = str.length();
        int countIllegalChars = (length - countIllegalChars(str)) % 4;
        int i = countIllegalChars == 0 ? 0 : 4 - countIllegalChars;
        char[] cArr = new char[(length + i)];
        str.getChars(0, length, cArr, 0);
        for (int i2 = 0; i2 < i; i2++) {
            cArr[length + i2] = '=';
        }
        for (int i3 = 0; i3 < length; i3++) {
            if (cArr[i3] == '_') {
                cArr[i3] = '/';
            } else if (cArr[i3] == '-') {
                cArr[i3] = '+';
            }
        }
        return new String(cArr);
    }

    public static int countIllegalChars(String str) {
        int i = 0;
        for (int i2 = 0; i2 < str.length(); i2++) {
            char charAt = str.charAt(i2);
            if (IA[charAt] == -1 && IA_URL_SAFE[charAt] == -1) {
                i++;
            }
        }
        return i;
    }

    public static char[] encodeToChar(byte[] bArr, boolean z) {
        int i = 0;
        int length = bArr != null ? bArr.length : 0;
        if (length == 0) {
            return new char[0];
        }
        int i2 = (length / 3) * 3;
        int computeEncodedLength = computeEncodedLength(length, z);
        char[] cArr = new char[computeEncodedLength];
        int i3 = 0;
        int i4 = 0;
        while (i3 < i2) {
            int i5 = i3 + 1;
            int i6 = i5 + 1;
            byte b = ((bArr[i3] & 255) << Ascii.DLE) | ((bArr[i5] & 255) << 8);
            int i7 = i6 + 1;
            byte b2 = b | (bArr[i6] & 255);
            if (z) {
                int i8 = i4 + 1;
                char[] cArr2 = CA_URL_SAFE;
                cArr[i4] = cArr2[(b2 >>> Ascii.DC2) & 63];
                int i9 = i8 + 1;
                cArr[i8] = cArr2[(b2 >>> Ascii.FF) & 63];
                int i10 = i9 + 1;
                cArr[i9] = cArr2[(b2 >>> 6) & 63];
                i4 = i10 + 1;
                cArr[i10] = cArr2[b2 & 63];
                i3 = i7;
            } else {
                int i11 = i4 + 1;
                char[] cArr3 = CA;
                cArr[i4] = cArr3[(b2 >>> Ascii.DC2) & 63];
                int i12 = i11 + 1;
                cArr[i11] = cArr3[(b2 >>> Ascii.FF) & 63];
                int i13 = i12 + 1;
                cArr[i12] = cArr3[(b2 >>> 6) & 63];
                i4 = i13 + 1;
                cArr[i13] = cArr3[b2 & 63];
                i3 = i7;
            }
        }
        int i14 = length - i2;
        if (i14 > 0) {
            int i15 = (bArr[i2] & 255) << 10;
            if (i14 == 2) {
                i = (bArr[length - 1] & 255) << 2;
            }
            int i16 = i15 | i;
            if (!z) {
                int i17 = computeEncodedLength - 4;
                char[] cArr4 = CA;
                cArr[i17] = cArr4[i16 >> 12];
                cArr[computeEncodedLength - 3] = cArr4[(i16 >>> 6) & 63];
                cArr[computeEncodedLength - 2] = i14 == 2 ? cArr4[i16 & 63] : '=';
                cArr[computeEncodedLength - 1] = '=';
            } else if (i14 == 2) {
                int i18 = computeEncodedLength - 3;
                char[] cArr5 = CA_URL_SAFE;
                cArr[i18] = cArr5[i16 >> 12];
                cArr[computeEncodedLength - 2] = cArr5[(i16 >>> 6) & 63];
                cArr[computeEncodedLength - 1] = cArr5[i16 & 63];
            } else {
                int i19 = computeEncodedLength - 2;
                char[] cArr6 = CA_URL_SAFE;
                cArr[i19] = cArr6[i16 >> 12];
                cArr[computeEncodedLength - 1] = cArr6[(i16 >>> 6) & 63];
            }
        }
        return cArr;
    }

    public static String encodeToString(byte[] bArr, boolean z) {
        return new String(encodeToChar(bArr, z));
    }

    public static byte[] decode(String str) {
        if (str == null || str.isEmpty()) {
            return new byte[0];
        }
        String normalizeEncodedString = normalizeEncodedString(str);
        int length = normalizeEncodedString.length();
        int countIllegalChars = length - countIllegalChars(normalizeEncodedString);
        if (countIllegalChars % 4 != 0) {
            return new byte[0];
        }
        int i = 0;
        while (length > 1) {
            length--;
            if (IA[normalizeEncodedString.charAt(length)] > 0) {
                break;
            } else if (normalizeEncodedString.charAt(length) == '=') {
                i++;
            }
        }
        int i2 = ((countIllegalChars * 6) >> 3) - i;
        byte[] bArr = new byte[i2];
        int i3 = 0;
        int i4 = 0;
        while (i3 < i2) {
            int i5 = i4;
            int i6 = 0;
            int i7 = 0;
            while (i6 < 4) {
                int i8 = i5 + 1;
                int i9 = IA[normalizeEncodedString.charAt(i5)];
                if (i9 >= 0) {
                    i7 = (i9 << (18 - (i6 * 6))) | i7;
                } else {
                    i6--;
                }
                i6++;
                i5 = i8;
            }
            int i10 = i3 + 1;
            bArr[i3] = (byte) (i7 >> 16);
            if (i10 < i2) {
                i3 = i10 + 1;
                bArr[i10] = (byte) (i7 >> 8);
                if (i3 < i2) {
                    int i11 = i3 + 1;
                    bArr[i3] = (byte) i7;
                    i3 = i11;
                    i4 = i5;
                } else {
                    i4 = i5;
                }
            } else {
                i3 = i10;
                i4 = i5;
            }
        }
        return bArr;
    }
}
