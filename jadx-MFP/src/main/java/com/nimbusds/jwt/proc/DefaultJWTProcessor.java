package com.nimbusds.jwt.proc;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.crypto.factories.DefaultJWEDecrypterFactory;
import com.nimbusds.jose.crypto.factories.DefaultJWSVerifierFactory;
import com.nimbusds.jose.proc.BadJOSEException;
import com.nimbusds.jose.proc.BadJWSException;
import com.nimbusds.jose.proc.JWEDecrypterFactory;
import com.nimbusds.jose.proc.JWSVerifierFactory;
import com.nimbusds.jose.proc.SecurityContext;

public class DefaultJWTProcessor<C extends SecurityContext> implements ConfigurableJWTProcessor<C> {
    private static final BadJWTException INVALID_NESTED_JWT_EXCEPTION = new BadJWTException("The payload is not a nested JWT");
    private static final BadJOSEException INVALID_SIGNATURE = new BadJWSException("Signed JWT rejected: Invalid signature");
    private static final JOSEException NO_JWE_DECRYPTER_FACTORY_EXCEPTION = new JOSEException("No JWE decrypter is configured");
    private static final BadJOSEException NO_JWE_KEY_CANDIDATES_EXCEPTION = new BadJOSEException("Encrypted JWT rejected: No matching key(s) found");
    private static final BadJOSEException NO_JWE_KEY_SELECTOR_EXCEPTION = new BadJOSEException("Encrypted JWT rejected: No JWE key selector is configured");
    private static final BadJOSEException NO_JWS_KEY_CANDIDATES_EXCEPTION = new BadJOSEException("Signed JWT rejected: No matching key(s) found");
    private static final BadJOSEException NO_JWS_KEY_SELECTOR_EXCEPTION = new BadJOSEException("Signed JWT rejected: No JWS key selector is configured");
    private static final JOSEException NO_JWS_VERIFIER_FACTORY_EXCEPTION = new JOSEException("No JWS verifier is configured");
    private static final BadJOSEException NO_MATCHING_DECRYPTERS_EXCEPTION = new BadJOSEException("Encrypted JWT rejected: No matching decrypter(s) found");
    private static final BadJOSEException NO_MATCHING_VERIFIERS_EXCEPTION = new BadJOSEException("JWS object rejected: No matching verifier(s) found");
    private static final BadJOSEException PLAIN_JWT_REJECTED_EXCEPTION = new BadJOSEException("Unsecured (plain) JWTs are rejected, extend class to handle");
    private JWTClaimsVerifier claimsVerifier = new DefaultJWTClaimsVerifier();
    private JWEDecrypterFactory jweDecrypterFactory = new DefaultJWEDecrypterFactory();
    private JWSVerifierFactory jwsVerifierFactory = new DefaultJWSVerifierFactory();
}
