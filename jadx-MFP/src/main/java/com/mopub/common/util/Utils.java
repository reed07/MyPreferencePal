package com.mopub.common.util;

import java.security.MessageDigest;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicLong;

public class Utils {
    private static final AtomicLong sNextGeneratedId = new AtomicLong(1);

    public static boolean bitMaskContainsFlag(int i, int i2) {
        return (i & i2) != 0;
    }

    public static String sha1(String str) {
        StringBuilder sb = new StringBuilder();
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-1");
            byte[] bytes = str.getBytes("UTF-8");
            instance.update(bytes, 0, bytes.length);
            for (byte valueOf : instance.digest()) {
                sb.append(String.format("%02X", new Object[]{Byte.valueOf(valueOf)}));
            }
            return sb.toString().toLowerCase(Locale.US);
        } catch (Exception unused) {
            return "";
        }
    }

    public static long generateUniqueId() {
        long j;
        long j2;
        do {
            j = sNextGeneratedId.get();
            j2 = 1;
            long j3 = j + 1;
            if (j3 <= 9223372036854775806L) {
                j2 = j3;
            }
        } while (!sNextGeneratedId.compareAndSet(j, j2));
        return j;
    }
}
