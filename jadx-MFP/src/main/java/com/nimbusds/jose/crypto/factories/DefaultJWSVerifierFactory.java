package com.nimbusds.jose.crypto.factories;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.crypto.ECDSAVerifier;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jca.JCAContext;
import com.nimbusds.jose.proc.JWSVerifierFactory;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class DefaultJWSVerifierFactory implements JWSVerifierFactory {
    public static final Set<JWSAlgorithm> SUPPORTED_ALGORITHMS;
    private final JCAContext jcaContext = new JCAContext();

    static {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.addAll(MACVerifier.SUPPORTED_ALGORITHMS);
        linkedHashSet.addAll(RSASSAVerifier.SUPPORTED_ALGORITHMS);
        linkedHashSet.addAll(ECDSAVerifier.SUPPORTED_ALGORITHMS);
        SUPPORTED_ALGORITHMS = Collections.unmodifiableSet(linkedHashSet);
    }

    public Set<JWSAlgorithm> supportedJWSAlgorithms() {
        return SUPPORTED_ALGORITHMS;
    }
}
