package com.nimbusds.jose.crypto;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.KeyLengthException;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

abstract class MACProvider extends BaseJWSProvider {
    public static final Set<JWSAlgorithm> SUPPORTED_ALGORITHMS;
    private final byte[] secret;

    static {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.add(JWSAlgorithm.HS256);
        linkedHashSet.add(JWSAlgorithm.HS384);
        linkedHashSet.add(JWSAlgorithm.HS512);
        SUPPORTED_ALGORITHMS = Collections.unmodifiableSet(linkedHashSet);
    }

    protected static String getJCAAlgorithmName(JWSAlgorithm jWSAlgorithm) throws JOSEException {
        if (jWSAlgorithm.equals(JWSAlgorithm.HS256)) {
            return "HMACSHA256";
        }
        if (jWSAlgorithm.equals(JWSAlgorithm.HS384)) {
            return "HMACSHA384";
        }
        if (jWSAlgorithm.equals(JWSAlgorithm.HS512)) {
            return "HMACSHA512";
        }
        throw new JOSEException(AlgorithmSupportMessage.unsupportedJWSAlgorithm(jWSAlgorithm, SUPPORTED_ALGORITHMS));
    }

    protected MACProvider(byte[] bArr, Set<JWSAlgorithm> set) throws KeyLengthException {
        super(set);
        if (bArr.length >= 32) {
            this.secret = bArr;
            return;
        }
        throw new KeyLengthException("The secret length must be at least 256 bits");
    }

    public byte[] getSecret() {
        return this.secret;
    }
}
