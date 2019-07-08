package com.nimbusds.jwt;

import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.Payload;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class SignedJWT extends JWSObject implements JWT {
    private static final long serialVersionUID = 1;

    public SignedJWT(JWSHeader jWSHeader, JWTClaimsSet jWTClaimsSet) {
        super(jWSHeader, new Payload(jWTClaimsSet.toJSONObject()));
    }
}
