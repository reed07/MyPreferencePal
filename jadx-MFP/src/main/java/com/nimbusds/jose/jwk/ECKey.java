package com.nimbusds.jose.jwk;

import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import com.nimbusds.jose.Algorithm;
import com.nimbusds.jose.util.Base64;
import com.nimbusds.jose.util.Base64URL;
import com.nimbusds.jose.util.JSONObjectUtils;
import java.io.Serializable;
import java.net.URI;
import java.text.ParseException;
import java.util.List;
import java.util.Set;
import net.jcip.annotations.Immutable;
import net.minidev.json.JSONObject;

@Immutable
public final class ECKey extends JWK implements AssymetricJWK {
    private static final long serialVersionUID = 1;
    private final Curve crv;
    private final Base64URL d;
    private final Base64URL x;
    private final Base64URL y;

    public static class Builder {
    }

    @Immutable
    public static class Curve implements Serializable {
        public static final Curve P_256 = new Curve("P-256", "secp256r1");
        public static final Curve P_384 = new Curve("P-384", "secp384r1");
        public static final Curve P_521 = new Curve("P-521", "secp521r1");
        private static final long serialVersionUID = 1;
        private final String name;
        private final String stdName;

        public Curve(String str) {
            this(str, null);
        }

        public Curve(String str, String str2) {
            if (str != null) {
                this.name = str;
                this.stdName = str2;
                return;
            }
            throw new IllegalArgumentException("The JOSE cryptographic curve name must not be null");
        }

        public String getName() {
            return this.name;
        }

        public String toString() {
            return getName();
        }

        public boolean equals(Object obj) {
            return (obj instanceof Curve) && toString().equals(obj.toString());
        }

        public static Curve parse(String str) {
            if (str == null || str.trim().isEmpty()) {
                throw new IllegalArgumentException("The cryptographic curve string must not be null or empty");
            } else if (str.equals(P_256.getName())) {
                return P_256;
            } else {
                if (str.equals(P_384.getName())) {
                    return P_384;
                }
                if (str.equals(P_521.getName())) {
                    return P_521;
                }
                return new Curve(str);
            }
        }
    }

    public ECKey(Curve curve, Base64URL base64URL, Base64URL base64URL2, KeyUse keyUse, Set<KeyOperation> set, Algorithm algorithm, String str, URI uri, Base64URL base64URL3, List<Base64> list) {
        Curve curve2 = curve;
        Base64URL base64URL4 = base64URL;
        Base64URL base64URL5 = base64URL2;
        super(KeyType.EC, keyUse, set, algorithm, str, uri, base64URL3, list);
        if (curve2 != null) {
            this.crv = curve2;
            if (base64URL4 != null) {
                this.x = base64URL4;
                if (base64URL5 != null) {
                    this.y = base64URL5;
                    this.d = null;
                    return;
                }
                throw new IllegalArgumentException("The 'y' coordinate must not be null");
            }
            throw new IllegalArgumentException("The 'x' coordinate must not be null");
        }
        throw new IllegalArgumentException("The curve must not be null");
    }

    public ECKey(Curve curve, Base64URL base64URL, Base64URL base64URL2, Base64URL base64URL3, KeyUse keyUse, Set<KeyOperation> set, Algorithm algorithm, String str, URI uri, Base64URL base64URL4, List<Base64> list) {
        Curve curve2 = curve;
        Base64URL base64URL5 = base64URL;
        Base64URL base64URL6 = base64URL2;
        Base64URL base64URL7 = base64URL3;
        super(KeyType.EC, keyUse, set, algorithm, str, uri, base64URL4, list);
        if (curve2 != null) {
            this.crv = curve2;
            if (base64URL5 != null) {
                this.x = base64URL5;
                if (base64URL6 != null) {
                    this.y = base64URL6;
                    if (base64URL7 != null) {
                        this.d = base64URL7;
                        return;
                    }
                    throw new IllegalArgumentException("The 'd' coordinate must not be null");
                }
                throw new IllegalArgumentException("The 'y' coordinate must not be null");
            }
            throw new IllegalArgumentException("The 'x' coordinate must not be null");
        }
        throw new IllegalArgumentException("The curve must not be null");
    }

    public Curve getCurve() {
        return this.crv;
    }

    public Base64URL getX() {
        return this.x;
    }

    public Base64URL getY() {
        return this.y;
    }

    public ECKey toPublicJWK() {
        ECKey eCKey = new ECKey(getCurve(), getX(), getY(), getKeyUse(), getKeyOperations(), getAlgorithm(), getKeyID(), getX509CertURL(), getX509CertThumbprint(), getX509CertChain());
        return eCKey;
    }

    public JSONObject toJSONObject() {
        JSONObject jSONObject = super.toJSONObject();
        jSONObject.put("crv", this.crv.toString());
        jSONObject.put(AvidJSONUtil.KEY_X, this.x.toString());
        jSONObject.put("y", this.y.toString());
        Base64URL base64URL = this.d;
        if (base64URL != null) {
            jSONObject.put("d", base64URL.toString());
        }
        return jSONObject;
    }

    public static ECKey parse(JSONObject jSONObject) throws ParseException {
        Curve parse = Curve.parse(JSONObjectUtils.getString(jSONObject, "crv"));
        Base64URL base64URL = new Base64URL(JSONObjectUtils.getString(jSONObject, AvidJSONUtil.KEY_X));
        Base64URL base64URL2 = new Base64URL(JSONObjectUtils.getString(jSONObject, "y"));
        if (JWKMetadata.parseKeyType(jSONObject) == KeyType.EC) {
            Base64URL base64URL3 = jSONObject.get("d") != null ? new Base64URL(JSONObjectUtils.getString(jSONObject, "d")) : null;
            if (base64URL3 == null) {
                try {
                    ECKey eCKey = new ECKey(parse, base64URL, base64URL2, JWKMetadata.parseKeyUse(jSONObject), JWKMetadata.parseKeyOperations(jSONObject), JWKMetadata.parseAlgorithm(jSONObject), JWKMetadata.parseKeyID(jSONObject), JWKMetadata.parseX509CertURL(jSONObject), JWKMetadata.parseX509CertThumbprint(jSONObject), JWKMetadata.parseX509CertChain(jSONObject));
                    return eCKey;
                } catch (IllegalArgumentException e) {
                    throw new ParseException(e.getMessage(), 0);
                }
            } else {
                ECKey eCKey2 = new ECKey(parse, base64URL, base64URL2, base64URL3, JWKMetadata.parseKeyUse(jSONObject), JWKMetadata.parseKeyOperations(jSONObject), JWKMetadata.parseAlgorithm(jSONObject), JWKMetadata.parseKeyID(jSONObject), JWKMetadata.parseX509CertURL(jSONObject), JWKMetadata.parseX509CertThumbprint(jSONObject), JWKMetadata.parseX509CertChain(jSONObject));
                return eCKey2;
            }
        } else {
            throw new ParseException("The key type \"kty\" must be EC", 0);
        }
    }
}
