package com.nimbusds.jose.util;

public class ByteUtils {
    public static int bitLength(int i) {
        return i * 8;
    }

    public static int byteLength(int i) {
        return i / 8;
    }
}
