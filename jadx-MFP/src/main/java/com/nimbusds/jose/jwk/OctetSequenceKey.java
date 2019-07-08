package com.nimbusds.jose.jwk;

import com.nimbusds.jose.Algorithm;
import com.nimbusds.jose.util.Base64;
import com.nimbusds.jose.util.Base64URL;
import com.nimbusds.jose.util.JSONObjectUtils;
import java.net.URI;
import java.text.ParseException;
import java.util.List;
import java.util.Set;
import net.jcip.annotations.Immutable;
import net.minidev.json.JSONObject;

@Immutable
public final class OctetSequenceKey extends JWK implements SecretJWK {
    private static final long serialVersionUID = 1;
    private final Base64URL k;

    public static class Builder {
        private Algorithm alg;
        private final Base64URL k;
        private String kid;
        private Set<KeyOperation> ops;
        private KeyUse use;
        private List<Base64> x5c;
        private Base64URL x5t;
        private URI x5u;

        public Builder(Base64URL base64URL) {
            if (base64URL != null) {
                this.k = base64URL;
                return;
            }
            throw new IllegalArgumentException("The key value must not be null");
        }

        public Builder keyUse(KeyUse keyUse) {
            this.use = keyUse;
            return this;
        }

        public Builder keyID(String str) {
            this.kid = str;
            return this;
        }

        public OctetSequenceKey build() {
            try {
                OctetSequenceKey octetSequenceKey = new OctetSequenceKey(this.k, this.use, this.ops, this.alg, this.kid, this.x5u, this.x5t, this.x5c);
                return octetSequenceKey;
            } catch (IllegalArgumentException e) {
                throw new IllegalStateException(e.getMessage(), e);
            }
        }
    }

    public OctetSequenceKey toPublicJWK() {
        return null;
    }

    public OctetSequenceKey(Base64URL base64URL, KeyUse keyUse, Set<KeyOperation> set, Algorithm algorithm, String str, URI uri, Base64URL base64URL2, List<Base64> list) {
        Base64URL base64URL3 = base64URL;
        super(KeyType.OCT, keyUse, set, algorithm, str, uri, base64URL2, list);
        if (base64URL3 != null) {
            this.k = base64URL3;
        } else {
            throw new IllegalArgumentException("The key value must not be null");
        }
    }

    public Base64URL getKeyValue() {
        return this.k;
    }

    public byte[] toByteArray() {
        return getKeyValue().decode();
    }

    public JSONObject toJSONObject() {
        JSONObject jSONObject = super.toJSONObject();
        jSONObject.put("k", this.k.toString());
        return jSONObject;
    }

    public static OctetSequenceKey parse(JSONObject jSONObject) throws ParseException {
        Base64URL base64URL = new Base64URL(JSONObjectUtils.getString(jSONObject, "k"));
        if (JWKMetadata.parseKeyType(jSONObject) == KeyType.OCT) {
            OctetSequenceKey octetSequenceKey = new OctetSequenceKey(base64URL, JWKMetadata.parseKeyUse(jSONObject), JWKMetadata.parseKeyOperations(jSONObject), JWKMetadata.parseAlgorithm(jSONObject), JWKMetadata.parseKeyID(jSONObject), JWKMetadata.parseX509CertURL(jSONObject), JWKMetadata.parseX509CertThumbprint(jSONObject), JWKMetadata.parseX509CertChain(jSONObject));
            return octetSequenceKey;
        }
        throw new ParseException("The key type \"kty\" must be oct", 0);
    }
}
