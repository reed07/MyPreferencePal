package com.nimbusds.jose.crypto;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Signature;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.PSSParameterSpec;

class RSASSA {
    protected static Signature getSignerAndVerifier(JWSAlgorithm jWSAlgorithm, Provider provider) throws JOSEException {
        String str;
        Signature signature;
        PSSParameterSpec pSSParameterSpec = null;
        if (jWSAlgorithm.equals(JWSAlgorithm.RS256)) {
            str = "SHA256withRSA";
        } else if (jWSAlgorithm.equals(JWSAlgorithm.RS384)) {
            str = "SHA384withRSA";
        } else if (jWSAlgorithm.equals(JWSAlgorithm.RS512)) {
            str = "SHA512withRSA";
        } else if (jWSAlgorithm.equals(JWSAlgorithm.PS256)) {
            str = "SHA256withRSAandMGF1";
            PSSParameterSpec pSSParameterSpec2 = new PSSParameterSpec("SHA256", "MGF1", MGF1ParameterSpec.SHA256, 32, 1);
            pSSParameterSpec = pSSParameterSpec2;
        } else if (jWSAlgorithm.equals(JWSAlgorithm.PS384)) {
            str = "SHA384withRSAandMGF1";
            PSSParameterSpec pSSParameterSpec3 = new PSSParameterSpec("SHA384", "MGF1", MGF1ParameterSpec.SHA384, 48, 1);
            pSSParameterSpec = pSSParameterSpec3;
        } else if (jWSAlgorithm.equals(JWSAlgorithm.PS512)) {
            str = "SHA512withRSAandMGF1";
            PSSParameterSpec pSSParameterSpec4 = new PSSParameterSpec("SHA512", "MGF1", MGF1ParameterSpec.SHA512, 64, 1);
            pSSParameterSpec = pSSParameterSpec4;
        } else {
            throw new JOSEException(AlgorithmSupportMessage.unsupportedJWSAlgorithm(jWSAlgorithm, RSASSAProvider.SUPPORTED_ALGORITHMS));
        }
        if (provider != null) {
            try {
                signature = Signature.getInstance(str, provider);
            } catch (NoSuchAlgorithmException e) {
                StringBuilder sb = new StringBuilder("Unsupported RSASSA algorithm: ");
                sb.append(e.getMessage());
                throw new JOSEException(sb.toString(), e);
            }
        } else {
            signature = Signature.getInstance(str);
        }
        if (pSSParameterSpec != null) {
            try {
                signature.setParameter(pSSParameterSpec);
            } catch (InvalidAlgorithmParameterException e2) {
                StringBuilder sb2 = new StringBuilder("Invalid RSASSA-PSS salt length parameter: ");
                sb2.append(e2.getMessage());
                throw new JOSEException(sb2.toString(), e2);
            }
        }
        return signature;
    }

    private RSASSA() {
    }
}
