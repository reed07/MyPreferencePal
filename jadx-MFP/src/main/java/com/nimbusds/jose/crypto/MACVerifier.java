package com.nimbusds.jose.crypto;

import com.nimbusds.jose.CriticalHeaderParamsAware;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.jca.JCAContext;
import com.nimbusds.jose.jwk.OctetSequenceKey;
import com.nimbusds.jose.util.Base64URL;
import java.util.Set;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class MACVerifier extends MACProvider implements CriticalHeaderParamsAware, JWSVerifier {
    private final CriticalHeaderParamsDeferral critPolicy;

    public /* bridge */ /* synthetic */ JCAContext getJCAContext() {
        return super.getJCAContext();
    }

    public /* bridge */ /* synthetic */ byte[] getSecret() {
        return super.getSecret();
    }

    public /* bridge */ /* synthetic */ Set supportedJWSAlgorithms() {
        return super.supportedJWSAlgorithms();
    }

    public MACVerifier(byte[] bArr) throws JOSEException {
        this(bArr, null);
    }

    public MACVerifier(OctetSequenceKey octetSequenceKey) throws JOSEException {
        this(octetSequenceKey.toByteArray());
    }

    public MACVerifier(byte[] bArr, Set<String> set) throws JOSEException {
        super(bArr, SUPPORTED_ALGORITHMS);
        this.critPolicy = new CriticalHeaderParamsDeferral();
        this.critPolicy.setDeferredCriticalHeaderParams(set);
    }

    public boolean verify(JWSHeader jWSHeader, byte[] bArr, Base64URL base64URL) throws JOSEException {
        if (!this.critPolicy.headerPasses(jWSHeader)) {
            return false;
        }
        return ConstantTimeUtils.areEqual(HMAC.compute(getJCAAlgorithmName(jWSHeader.getAlgorithm()), getSecret(), bArr, getJCAContext().getProvider()), base64URL.decode());
    }
}
