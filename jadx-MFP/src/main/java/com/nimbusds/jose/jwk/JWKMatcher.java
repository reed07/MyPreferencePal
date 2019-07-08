package com.nimbusds.jose.jwk;

import net.jcip.annotations.Immutable;

@Immutable
public class JWKMatcher {

    public static class Builder {
        private boolean privateOnly = false;
        private boolean publicOnly = false;
    }
}
