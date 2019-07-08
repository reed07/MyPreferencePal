package com.nimbusds.jose.jwk;

import com.nimbusds.jose.Algorithm;
import com.nimbusds.jose.util.Base64;
import com.nimbusds.jose.util.Base64URL;
import com.nimbusds.jose.util.JSONObjectUtils;
import java.io.Serializable;
import java.net.URI;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import net.minidev.json.JSONAware;
import net.minidev.json.JSONObject;

public abstract class JWK implements Serializable, JSONAware {
    private static final long serialVersionUID = 1;
    private final Algorithm alg;
    private final String kid;
    private final KeyType kty;
    private final Set<KeyOperation> ops;
    private final KeyUse use;
    private final List<Base64> x5c;
    private final Base64URL x5t;
    private final URI x5u;

    public abstract JWK toPublicJWK();

    public JWK(KeyType keyType, KeyUse keyUse, Set<KeyOperation> set, Algorithm algorithm, String str, URI uri, Base64URL base64URL, List<Base64> list) {
        if (keyType != null) {
            this.kty = keyType;
            if (keyUse == null || set == null) {
                this.use = keyUse;
                this.ops = set;
                this.alg = algorithm;
                this.kid = str;
                this.x5u = uri;
                this.x5t = base64URL;
                this.x5c = list;
                return;
            }
            throw new IllegalArgumentException("They key use \"use\" and key options \"key_opts\" parameters cannot be set together");
        }
        throw new IllegalArgumentException("The key type \"kty\" parameter must not be null");
    }

    public KeyUse getKeyUse() {
        return this.use;
    }

    public Set<KeyOperation> getKeyOperations() {
        return this.ops;
    }

    public Algorithm getAlgorithm() {
        return this.alg;
    }

    public String getKeyID() {
        return this.kid;
    }

    public URI getX509CertURL() {
        return this.x5u;
    }

    public Base64URL getX509CertThumbprint() {
        return this.x5t;
    }

    public List<Base64> getX509CertChain() {
        List<Base64> list = this.x5c;
        if (list == null) {
            return null;
        }
        return Collections.unmodifiableList(list);
    }

    public JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("kty", this.kty.getValue());
        KeyUse keyUse = this.use;
        if (keyUse != null) {
            jSONObject.put("use", keyUse.identifier());
        }
        Set<KeyOperation> set = this.ops;
        if (set != null) {
            ArrayList arrayList = new ArrayList(set.size());
            for (KeyOperation identifier : this.ops) {
                arrayList.add(identifier.identifier());
            }
            jSONObject.put("key_ops", arrayList);
        }
        Algorithm algorithm = this.alg;
        if (algorithm != null) {
            jSONObject.put("alg", algorithm.getName());
        }
        String str = this.kid;
        if (str != null) {
            jSONObject.put("kid", str);
        }
        URI uri = this.x5u;
        if (uri != null) {
            jSONObject.put("x5u", uri.toString());
        }
        Base64URL base64URL = this.x5t;
        if (base64URL != null) {
            jSONObject.put("x5t", base64URL.toString());
        }
        List<Base64> list = this.x5c;
        if (list != null) {
            jSONObject.put("x5c", list);
        }
        return jSONObject;
    }

    public String toJSONString() {
        return toJSONObject().toString();
    }

    public String toString() {
        return toJSONObject().toString();
    }

    public static JWK parse(JSONObject jSONObject) throws ParseException {
        KeyType parse = KeyType.parse(JSONObjectUtils.getString(jSONObject, "kty"));
        if (parse == KeyType.EC) {
            return ECKey.parse(jSONObject);
        }
        if (parse == KeyType.RSA) {
            return RSAKey.parse(jSONObject);
        }
        if (parse == KeyType.OCT) {
            return OctetSequenceKey.parse(jSONObject);
        }
        StringBuilder sb = new StringBuilder("Unsupported key type \"kty\" parameter: ");
        sb.append(parse);
        throw new ParseException(sb.toString(), 0);
    }
}
