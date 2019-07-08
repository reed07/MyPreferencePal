package com.nimbusds.jose.crypto;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.jca.JCAContext;
import com.nimbusds.jose.util.Base64URL;
import java.security.InvalidKeyException;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.RSAPrivateKey;
import java.util.Set;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class RSASSASigner extends RSASSAProvider implements JWSSigner {
    private final RSAPrivateKey privateKey;

    public /* bridge */ /* synthetic */ JCAContext getJCAContext() {
        return super.getJCAContext();
    }

    public /* bridge */ /* synthetic */ Set supportedJWSAlgorithms() {
        return super.supportedJWSAlgorithms();
    }

    public Base64URL sign(JWSHeader jWSHeader, byte[] bArr) throws JOSEException {
        Signature signerAndVerifier = RSASSA.getSignerAndVerifier(jWSHeader.getAlgorithm(), getJCAContext().getProvider());
        try {
            signerAndVerifier.initSign(this.privateKey);
            signerAndVerifier.update(bArr);
            return Base64URL.encode(signerAndVerifier.sign());
        } catch (InvalidKeyException e) {
            StringBuilder sb = new StringBuilder("Invalid private RSA key: ");
            sb.append(e.getMessage());
            throw new JOSEException(sb.toString(), e);
        } catch (SignatureException e2) {
            StringBuilder sb2 = new StringBuilder("RSA signature exception: ");
            sb2.append(e2.getMessage());
            throw new JOSEException(sb2.toString(), e2);
        }
    }
}
