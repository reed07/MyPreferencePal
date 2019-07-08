package com.nimbusds.jose.crypto;

import com.nimbusds.jose.JOSEException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
class HMAC {
    HMAC() {
    }

    public static Mac getInitMac(SecretKey secretKey, Provider provider) throws JOSEException {
        Mac mac;
        if (provider != null) {
            try {
                mac = Mac.getInstance(secretKey.getAlgorithm(), provider);
            } catch (NoSuchAlgorithmException e) {
                StringBuilder sb = new StringBuilder("Unsupported HMAC algorithm: ");
                sb.append(e.getMessage());
                throw new JOSEException(sb.toString(), e);
            } catch (InvalidKeyException e2) {
                StringBuilder sb2 = new StringBuilder("Invalid HMAC key: ");
                sb2.append(e2.getMessage());
                throw new JOSEException(sb2.toString(), e2);
            }
        } else {
            mac = Mac.getInstance(secretKey.getAlgorithm());
        }
        mac.init(secretKey);
        return mac;
    }

    public static byte[] compute(String str, byte[] bArr, byte[] bArr2, Provider provider) throws JOSEException {
        return compute(new SecretKeySpec(bArr, str), bArr2, provider);
    }

    public static byte[] compute(SecretKey secretKey, byte[] bArr, Provider provider) throws JOSEException {
        Mac initMac = getInitMac(secretKey, provider);
        initMac.update(bArr);
        return initMac.doFinal();
    }
}
