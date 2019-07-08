package com.nimbusds.jwt.proc;

import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class DefaultJWTClaimsVerifier implements ClockSkewAware, JWTClaimsVerifier {
    private static final BadJWTException EXPIRED_JWT_EXCEPTION = new BadJWTException("Expired JWT");
    private static final BadJWTException JWT_BEFORE_USE_EXCEPTION = new BadJWTException("JWT before use time");
    private int maxClockSkew = 60;
}
