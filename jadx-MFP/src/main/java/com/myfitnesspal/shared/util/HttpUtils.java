package com.myfitnesspal.shared.util;

public final class HttpUtils {
    private static boolean isAsciiPrintable(char c) {
        return c >= ' ' && c < 127;
    }

    public static String sanitizeHeaderValue(String str) {
        if (str == null) {
            return null;
        }
        char[] charArray = str.toCharArray();
        int length = charArray.length;
        for (int i = 0; i < length; i++) {
            if (!isAsciiPrintable(charArray[i])) {
                charArray[i] = '.';
            }
        }
        return new String(charArray);
    }
}
