package com.nimbusds.jose.crypto;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.jca.JCAContext;
import com.nimbusds.jose.util.Base64URL;
import java.security.InvalidKeyException;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.ECPrivateKey;
import java.util.Set;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class ECDSASigner extends ECDSAProvider implements JWSSigner {
    private final ECPrivateKey privateKey;

    public /* bridge */ /* synthetic */ JCAContext getJCAContext() {
        return super.getJCAContext();
    }

    public /* bridge */ /* synthetic */ Set supportedJWSAlgorithms() {
        return super.supportedJWSAlgorithms();
    }

    public Base64URL sign(JWSHeader jWSHeader, byte[] bArr) throws JOSEException {
        JWSAlgorithm algorithm = jWSHeader.getAlgorithm();
        if (supportedJWSAlgorithms().contains(algorithm)) {
            try {
                Signature signerAndVerifier = ECDSA.getSignerAndVerifier(algorithm, getJCAContext().getProvider());
                signerAndVerifier.initSign(this.privateKey, getJCAContext().getSecureRandom());
                signerAndVerifier.update(bArr);
                return Base64URL.encode(ECDSA.transcodeSignatureToConcat(signerAndVerifier.sign(), ECDSA.getSignatureByteArrayLength(jWSHeader.getAlgorithm())));
            } catch (InvalidKeyException | SignatureException e) {
                throw new JOSEException(e.getMessage(), e);
            }
        } else {
            throw new JOSEException(AlgorithmSupportMessage.unsupportedJWSAlgorithm(algorithm, supportedJWSAlgorithms()));
        }
    }
}
