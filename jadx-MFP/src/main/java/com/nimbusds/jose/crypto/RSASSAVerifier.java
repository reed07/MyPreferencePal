package com.nimbusds.jose.crypto;

import com.nimbusds.jose.CriticalHeaderParamsAware;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.jca.JCAContext;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.util.Base64URL;
import java.security.InvalidKeyException;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.RSAPublicKey;
import java.util.Set;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class RSASSAVerifier extends RSASSAProvider implements CriticalHeaderParamsAware, JWSVerifier {
    private final CriticalHeaderParamsDeferral critPolicy;
    private final RSAPublicKey publicKey;

    public /* bridge */ /* synthetic */ JCAContext getJCAContext() {
        return super.getJCAContext();
    }

    public /* bridge */ /* synthetic */ Set supportedJWSAlgorithms() {
        return super.supportedJWSAlgorithms();
    }

    public RSASSAVerifier(RSAKey rSAKey) throws JOSEException {
        this(rSAKey.toRSAPublicKey(), null);
    }

    public RSASSAVerifier(RSAPublicKey rSAPublicKey, Set<String> set) {
        this.critPolicy = new CriticalHeaderParamsDeferral();
        if (rSAPublicKey != null) {
            this.publicKey = rSAPublicKey;
            this.critPolicy.setDeferredCriticalHeaderParams(set);
            return;
        }
        throw new IllegalArgumentException("The public RSA key must not be null");
    }

    public boolean verify(JWSHeader jWSHeader, byte[] bArr, Base64URL base64URL) throws JOSEException {
        if (!this.critPolicy.headerPasses(jWSHeader)) {
            return false;
        }
        Signature signerAndVerifier = RSASSA.getSignerAndVerifier(jWSHeader.getAlgorithm(), getJCAContext().getProvider());
        try {
            signerAndVerifier.initVerify(this.publicKey);
            try {
                signerAndVerifier.update(bArr);
                return signerAndVerifier.verify(base64URL.decode());
            } catch (SignatureException unused) {
                return false;
            }
        } catch (InvalidKeyException e) {
            StringBuilder sb = new StringBuilder("Invalid public RSA key: ");
            sb.append(e.getMessage());
            throw new JOSEException(sb.toString(), e);
        }
    }
}
