package com.nimbusds.jose;

import com.nimbusds.jose.util.Base64URL;

public interface JWSVerifier extends JWSProvider {
    boolean verify(JWSHeader jWSHeader, byte[] bArr, Base64URL base64URL) throws JOSEException;
}
