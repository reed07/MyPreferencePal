package com.nimbusds.jwt.proc;

import com.nimbusds.jose.proc.BadJOSEException;

public class BadJWTException extends BadJOSEException {
    public BadJWTException(String str) {
        super(str);
    }
}
