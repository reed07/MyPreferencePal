package com.nimbusds.jose.jca;

import java.security.Provider;
import java.security.SecureRandom;

public class JCAContext {
    private Provider provider;
    private SecureRandom randomGen;

    public JCAContext() {
        this(null, null);
    }

    public JCAContext(Provider provider2, SecureRandom secureRandom) {
        this.provider = provider2;
        this.randomGen = secureRandom;
    }

    public Provider getProvider() {
        return this.provider;
    }

    public SecureRandom getSecureRandom() {
        SecureRandom secureRandom = this.randomGen;
        return secureRandom != null ? secureRandom : new SecureRandom();
    }
}
