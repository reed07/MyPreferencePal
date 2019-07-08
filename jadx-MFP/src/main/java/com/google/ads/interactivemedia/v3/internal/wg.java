package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
public enum wg implements wn {
    IDENTITY,
    UPPER_CAMEL_CASE,
    UPPER_CAMEL_CASE_WITH_SPACES,
    LOWER_CASE_WITH_UNDERSCORES,
    LOWER_CASE_WITH_DASHES,
    LOWER_CASE_WITH_DOTS;

    static String a(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (Character.isUpperCase(charAt) && sb.length() != 0) {
                sb.append(str2);
            }
            sb.append(charAt);
        }
        return sb.toString();
    }

    static String a(String str) {
        String str2;
        StringBuilder sb = new StringBuilder();
        int i = 0;
        char charAt = str.charAt(0);
        int length = str.length();
        while (i < length - 1 && !Character.isLetter(charAt)) {
            sb.append(charAt);
            i++;
            charAt = str.charAt(i);
        }
        if (Character.isUpperCase(charAt)) {
            return str;
        }
        char upperCase = Character.toUpperCase(charAt);
        int i2 = i + 1;
        if (i2 < str.length()) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(upperCase);
            sb2.append(str.substring(i2));
            str2 = sb2.toString();
        } else {
            str2 = String.valueOf(upperCase);
        }
        sb.append(str2);
        return sb.toString();
    }
}
