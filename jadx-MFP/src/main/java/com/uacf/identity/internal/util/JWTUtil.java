package com.uacf.identity.internal.util;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader.Builder;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.OctetSequenceKey;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.util.Base64URL;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import com.uacf.identity.internal.model.IdmKeyDesc;
import java.text.ParseException;

public final class JWTUtil {
    public static String encode(JWTClaimsSet jWTClaimsSet, IdmKeyDesc idmKeyDesc) {
        SignedJWT signedJWT = new SignedJWT(new Builder(JWSAlgorithm.parse(idmKeyDesc.getAlg())).keyID(idmKeyDesc.getKid()).build(), jWTClaimsSet);
        try {
            signedJWT.sign(new MACSigner(getOctetSequenceKey(idmKeyDesc)));
            return signedJWT.serialize();
        } catch (JOSEException e) {
            Ln.e(e);
            return null;
        } catch (ParseException e2) {
            Ln.e(e2);
            return null;
        }
    }

    public static String decode(String str, IdmKeyDesc idmKeyDesc) {
        JWSVerifier jWSVerifier;
        try {
            JWSObject parse = JWSObject.parse(str);
            String upperCase = Strings.toString(parse.getHeader().getAlgorithm().getName()).toUpperCase();
            if (upperCase.startsWith("RS")) {
                jWSVerifier = new RSASSAVerifier(new RSAKey.Builder(new Base64URL(idmKeyDesc.getN()), new Base64URL(idmKeyDesc.getE())).build());
            } else if (!upperCase.startsWith("HS")) {
                return null;
            } else {
                jWSVerifier = new MACVerifier(getOctetSequenceKey(idmKeyDesc));
            }
            if (parse.verify(jWSVerifier)) {
                return Strings.toString(parse.getPayload());
            }
        } catch (JOSEException | ParseException e) {
            Ln.e(e);
        }
        return null;
    }

    private static OctetSequenceKey getOctetSequenceKey(IdmKeyDesc idmKeyDesc) throws ParseException {
        return new OctetSequenceKey.Builder(new Base64URL(idmKeyDesc.getK())).keyID(idmKeyDesc.getKid()).keyUse(KeyUse.parse(idmKeyDesc.getUse())).build();
    }
}
