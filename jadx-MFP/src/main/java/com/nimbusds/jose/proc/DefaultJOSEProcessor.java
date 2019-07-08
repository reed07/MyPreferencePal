package com.nimbusds.jose.proc;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.crypto.factories.DefaultJWEDecrypterFactory;
import com.nimbusds.jose.crypto.factories.DefaultJWSVerifierFactory;
import com.nimbusds.jose.proc.SecurityContext;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class DefaultJOSEProcessor<C extends SecurityContext> implements ConfigurableJOSEProcessor<C> {
    private static final BadJOSEException INVALID_SIGNATURE = new BadJWSException("JWS object rejected: Invalid signature");
    private static final JOSEException NO_JWE_DECRYPTER_FACTORY_EXCEPTION = new JOSEException("No JWE decrypter is configured");
    private static final BadJOSEException NO_JWE_KEY_CANDIDATES_EXCEPTION = new BadJOSEException("JWE object rejected: No matching key(s) found");
    private static final BadJOSEException NO_JWE_KEY_SELECTOR_EXCEPTION = new BadJOSEException("JWE object rejected: No JWE key selector is configured");
    private static final BadJOSEException NO_JWS_KEY_CANDIDATES_EXCEPTION = new BadJOSEException("JWS object rejected: No matching key(s) found");
    private static final BadJOSEException NO_JWS_KEY_SELECTOR_EXCEPTION = new BadJOSEException("JWS object rejected: No JWS key selector is configured");
    private static final JOSEException NO_JWS_VERIFIER_FACTORY_EXCEPTION = new JOSEException("No JWS verifier is configured");
    private static final BadJOSEException NO_MATCHING_DECRYPTERS_EXCEPTION = new BadJOSEException("JWE object rejected: No matching decrypter(s) found");
    private static final BadJOSEException NO_MATCHING_VERIFIERS_EXCEPTION = new BadJOSEException("JWS object rejected: No matching verifier(s) found");
    private static final BadJOSEException PLAIN_JOSE_REJECTED_EXCEPTION = new BadJOSEException("Unsecured (plain) JOSE objects are rejected, extend class to handle");
    private JWEDecrypterFactory jweDecrypterFactory = new DefaultJWEDecrypterFactory();
    private JWSVerifierFactory jwsVerifierFactory = new DefaultJWSVerifierFactory();
}
