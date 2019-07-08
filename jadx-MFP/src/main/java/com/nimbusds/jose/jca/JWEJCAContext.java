package com.nimbusds.jose.jca;

import java.security.Provider;
import java.security.SecureRandom;

public final class JWEJCAContext extends JCAContext {
    private Provider ceProvider;
    private Provider keProvider;
    private Provider macProvider;

    public JWEJCAContext() {
        this(null, null, null, null, null);
    }

    public JWEJCAContext(Provider provider, Provider provider2, Provider provider3, Provider provider4, SecureRandom secureRandom) {
        super(provider, secureRandom);
        this.keProvider = provider2;
        this.ceProvider = provider3;
        this.macProvider = provider4;
    }
}
