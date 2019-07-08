package com.nimbusds.jose.crypto;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSProvider;
import com.nimbusds.jose.jca.JCAContext;
import java.util.Collections;
import java.util.Set;

abstract class BaseJWSProvider implements JWSProvider {
    private final Set<JWSAlgorithm> algs;
    private final JCAContext jcaContext = new JCAContext();

    public BaseJWSProvider(Set<JWSAlgorithm> set) {
        if (set != null) {
            this.algs = Collections.unmodifiableSet(set);
            return;
        }
        throw new IllegalArgumentException("The supported JWS algorithm set must not be null");
    }

    public Set<JWSAlgorithm> supportedJWSAlgorithms() {
        return this.algs;
    }

    public JCAContext getJCAContext() {
        return this.jcaContext;
    }
}
