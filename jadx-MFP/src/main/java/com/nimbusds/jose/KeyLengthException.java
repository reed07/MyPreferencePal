package com.nimbusds.jose;

public class KeyLengthException extends KeyException {
    private final Algorithm alg = null;
    private final int expectedLength = 0;

    public KeyLengthException(String str) {
        super(str);
    }
}
