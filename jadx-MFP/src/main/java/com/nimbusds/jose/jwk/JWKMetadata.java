package com.nimbusds.jose.jwk;

import com.nimbusds.jose.Algorithm;
import com.nimbusds.jose.util.Base64;
import com.nimbusds.jose.util.Base64URL;
import com.nimbusds.jose.util.JSONObjectUtils;
import com.nimbusds.jose.util.X509CertChainUtils;
import java.net.URI;
import java.text.ParseException;
import java.util.List;
import java.util.Set;
import net.minidev.json.JSONObject;

final class JWKMetadata {
    JWKMetadata() {
    }

    public static KeyType parseKeyType(JSONObject jSONObject) throws ParseException {
        return KeyType.parse(JSONObjectUtils.getString(jSONObject, "kty"));
    }

    public static KeyUse parseKeyUse(JSONObject jSONObject) throws ParseException {
        if (jSONObject.containsKey("use")) {
            return KeyUse.parse(JSONObjectUtils.getString(jSONObject, "use"));
        }
        return null;
    }

    public static Set<KeyOperation> parseKeyOperations(JSONObject jSONObject) throws ParseException {
        if (jSONObject.containsKey("key_ops")) {
            return KeyOperation.parse(JSONObjectUtils.getStringList(jSONObject, "key_ops"));
        }
        return null;
    }

    public static Algorithm parseAlgorithm(JSONObject jSONObject) throws ParseException {
        if (jSONObject.containsKey("alg")) {
            return new Algorithm(JSONObjectUtils.getString(jSONObject, "alg"));
        }
        return null;
    }

    public static String parseKeyID(JSONObject jSONObject) throws ParseException {
        if (jSONObject.containsKey("kid")) {
            return JSONObjectUtils.getString(jSONObject, "kid");
        }
        return null;
    }

    public static URI parseX509CertURL(JSONObject jSONObject) throws ParseException {
        if (jSONObject.containsKey("x5u")) {
            return JSONObjectUtils.getURI(jSONObject, "x5u");
        }
        return null;
    }

    public static Base64URL parseX509CertThumbprint(JSONObject jSONObject) throws ParseException {
        if (jSONObject.containsKey("x5t")) {
            return new Base64URL(JSONObjectUtils.getString(jSONObject, "x5t"));
        }
        return null;
    }

    public static List<Base64> parseX509CertChain(JSONObject jSONObject) throws ParseException {
        if (jSONObject.containsKey("x5c")) {
            return X509CertChainUtils.parseX509CertChain(JSONObjectUtils.getJSONArray(jSONObject, "x5c"));
        }
        return null;
    }
}
