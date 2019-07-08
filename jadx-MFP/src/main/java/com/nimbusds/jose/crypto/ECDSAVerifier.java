package com.nimbusds.jose.crypto;

import com.nimbusds.jose.CriticalHeaderParamsAware;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.jca.JCAContext;
import com.nimbusds.jose.util.Base64URL;
import java.security.InvalidKeyException;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.ECPublicKey;
import java.util.Set;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class ECDSAVerifier extends ECDSAProvider implements CriticalHeaderParamsAware, JWSVerifier {
    private final CriticalHeaderParamsDeferral critPolicy;
    private final ECPublicKey publicKey;

    public /* bridge */ /* synthetic */ JCAContext getJCAContext() {
        return super.getJCAContext();
    }

    public /* bridge */ /* synthetic */ Set supportedJWSAlgorithms() {
        return super.supportedJWSAlgorithms();
    }

    public boolean verify(JWSHeader jWSHeader, byte[] bArr, Base64URL base64URL) throws JOSEException {
        JWSAlgorithm algorithm = jWSHeader.getAlgorithm();
        if (!supportedJWSAlgorithms().contains(algorithm)) {
            throw new JOSEException(AlgorithmSupportMessage.unsupportedJWSAlgorithm(algorithm, supportedJWSAlgorithms()));
        } else if (!this.critPolicy.headerPasses(jWSHeader)) {
            return false;
        } else {
            try {
                byte[] transcodeSignatureToDER = ECDSA.transcodeSignatureToDER(base64URL.decode());
                Signature signerAndVerifier = ECDSA.getSignerAndVerifier(algorithm, getJCAContext().getProvider());
                try {
                    signerAndVerifier.initVerify(this.publicKey);
                    signerAndVerifier.update(bArr);
                    return signerAndVerifier.verify(transcodeSignatureToDER);
                } catch (InvalidKeyException e) {
                    StringBuilder sb = new StringBuilder("Invalid EC public key: ");
                    sb.append(e.getMessage());
                    throw new JOSEException(sb.toString(), e);
                } catch (SignatureException unused) {
                    return false;
                }
            } catch (JOSEException unused2) {
                return false;
            }
        }
    }
}
