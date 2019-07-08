package com.nimbusds.jose.crypto;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.jca.JCAContext;
import com.nimbusds.jose.jwk.OctetSequenceKey;
import com.nimbusds.jose.util.Base64URL;
import com.nimbusds.jose.util.ByteUtils;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class MACSigner extends MACProvider implements JWSSigner {
    public /* bridge */ /* synthetic */ JCAContext getJCAContext() {
        return super.getJCAContext();
    }

    public /* bridge */ /* synthetic */ byte[] getSecret() {
        return super.getSecret();
    }

    public /* bridge */ /* synthetic */ Set supportedJWSAlgorithms() {
        return super.supportedJWSAlgorithms();
    }

    public static int getMinRequiredSecretLength(JWSAlgorithm jWSAlgorithm) throws JOSEException {
        if (JWSAlgorithm.HS256.equals(jWSAlgorithm)) {
            return 256;
        }
        if (JWSAlgorithm.HS384.equals(jWSAlgorithm)) {
            return 384;
        }
        if (JWSAlgorithm.HS512.equals(jWSAlgorithm)) {
            return 512;
        }
        throw new JOSEException(AlgorithmSupportMessage.unsupportedJWSAlgorithm(jWSAlgorithm, SUPPORTED_ALGORITHMS));
    }

    public static Set<JWSAlgorithm> getCompatibleAlgorithms(int i) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        if (i >= 256) {
            linkedHashSet.add(JWSAlgorithm.HS256);
        }
        if (i >= 384) {
            linkedHashSet.add(JWSAlgorithm.HS384);
        }
        if (i >= 512) {
            linkedHashSet.add(JWSAlgorithm.HS512);
        }
        return Collections.unmodifiableSet(linkedHashSet);
    }

    public MACSigner(byte[] bArr) throws KeyLengthException {
        super(bArr, getCompatibleAlgorithms(ByteUtils.bitLength(bArr.length)));
    }

    public MACSigner(OctetSequenceKey octetSequenceKey) throws KeyLengthException {
        this(octetSequenceKey.toByteArray());
    }

    public Base64URL sign(JWSHeader jWSHeader, byte[] bArr) throws JOSEException {
        int minRequiredSecretLength = getMinRequiredSecretLength(jWSHeader.getAlgorithm());
        if (getSecret().length >= ByteUtils.byteLength(minRequiredSecretLength)) {
            return Base64URL.encode(HMAC.compute(getJCAAlgorithmName(jWSHeader.getAlgorithm()), getSecret(), bArr, getJCAContext().getProvider()));
        }
        StringBuilder sb = new StringBuilder("The secret length for ");
        sb.append(jWSHeader.getAlgorithm());
        sb.append(" must be at least ");
        sb.append(minRequiredSecretLength);
        sb.append(" bits");
        throw new KeyLengthException(sb.toString());
    }
}
