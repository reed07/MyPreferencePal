package com.nimbusds.jose.crypto;

import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWEProvider;
import com.nimbusds.jose.jca.JWEJCAContext;
import java.util.Collections;
import java.util.Set;

abstract class BaseJWEProvider implements JWEProvider {
    private final Set<JWEAlgorithm> algs;
    private final Set<EncryptionMethod> encs;
    private final JWEJCAContext jcaContext = new JWEJCAContext();

    public BaseJWEProvider(Set<JWEAlgorithm> set, Set<EncryptionMethod> set2) {
        if (set != null) {
            this.algs = Collections.unmodifiableSet(set);
            if (set2 != null) {
                this.encs = set2;
                return;
            }
            throw new IllegalArgumentException("The supported encryption methods must not be null");
        }
        throw new IllegalArgumentException("The supported JWE algorithm set must not be null");
    }
}
