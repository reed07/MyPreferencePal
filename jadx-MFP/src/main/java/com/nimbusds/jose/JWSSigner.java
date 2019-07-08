package com.nimbusds.jose;

import com.nimbusds.jose.util.Base64URL;

public interface JWSSigner extends JWSProvider {
    Base64URL sign(JWSHeader jWSHeader, byte[] bArr) throws JOSEException;
}
