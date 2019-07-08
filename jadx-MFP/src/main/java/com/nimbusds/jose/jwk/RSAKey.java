package com.nimbusds.jose.jwk;

import com.facebook.ads.internal.j.e;
import com.myfitnesspal.feature.payments.util.GooglePlayConstants;
import com.myfitnesspal.shared.constants.Constants.Http;
import com.nimbusds.jose.Algorithm;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.util.Base64;
import com.nimbusds.jose.util.Base64URL;
import com.nimbusds.jose.util.JSONObjectUtils;
import java.io.Serializable;
import java.net.URI;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import net.jcip.annotations.Immutable;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

@Immutable
public final class RSAKey extends JWK implements AssymetricJWK {
    private static final long serialVersionUID = 1;
    private final Base64URL d;
    private final Base64URL dp;
    private final Base64URL dq;
    private final Base64URL e;
    private final Base64URL n;
    private final List<OtherPrimesInfo> oth;
    private final Base64URL p;
    private final Base64URL q;
    private final Base64URL qi;

    public static class Builder {
        private Algorithm alg;
        private Base64URL d;
        private Base64URL dp;
        private Base64URL dq;
        private final Base64URL e;
        private String kid;
        private final Base64URL n;
        private Set<KeyOperation> ops;
        private List<OtherPrimesInfo> oth;
        private Base64URL p;
        private Base64URL q;
        private Base64URL qi;
        private KeyUse use;
        private List<Base64> x5c;
        private Base64URL x5t;
        private URI x5u;

        public Builder(Base64URL base64URL, Base64URL base64URL2) {
            if (base64URL != null) {
                this.n = base64URL;
                if (base64URL2 != null) {
                    this.e = base64URL2;
                    return;
                }
                throw new IllegalArgumentException("The public exponent value must not be null");
            }
            throw new IllegalArgumentException("The modulus value must not be null");
        }

        public RSAKey build() {
            try {
                Base64URL base64URL = this.n;
                Base64URL base64URL2 = this.e;
                Base64URL base64URL3 = this.d;
                Base64URL base64URL4 = this.p;
                Base64URL base64URL5 = this.q;
                Base64URL base64URL6 = this.dp;
                Base64URL base64URL7 = this.dq;
                Base64URL base64URL8 = this.qi;
                List<OtherPrimesInfo> list = this.oth;
                KeyUse keyUse = this.use;
                Set<KeyOperation> set = this.ops;
                Algorithm algorithm = this.alg;
                String str = this.kid;
                String str2 = str;
                RSAKey rSAKey = new RSAKey(base64URL, base64URL2, base64URL3, base64URL4, base64URL5, base64URL6, base64URL7, base64URL8, list, keyUse, set, algorithm, str2, this.x5u, this.x5t, this.x5c);
                return rSAKey;
            } catch (IllegalArgumentException e2) {
                throw new IllegalStateException(e2.getMessage(), e2);
            }
        }
    }

    @Immutable
    public static class OtherPrimesInfo implements Serializable {
        private static final long serialVersionUID = 1;
        /* access modifiers changed from: private */
        public final Base64URL d;
        /* access modifiers changed from: private */
        public final Base64URL r;
        /* access modifiers changed from: private */
        public final Base64URL t;

        public OtherPrimesInfo(Base64URL base64URL, Base64URL base64URL2, Base64URL base64URL3) {
            if (base64URL != null) {
                this.r = base64URL;
                if (base64URL2 != null) {
                    this.d = base64URL2;
                    if (base64URL3 != null) {
                        this.t = base64URL3;
                        return;
                    }
                    throw new IllegalArgumentException("The factor CRT coefficient must not be null");
                }
                throw new IllegalArgumentException("The factor CRT exponent must not be null");
            }
            throw new IllegalArgumentException("The prime factor must not be null");
        }
    }

    public RSAKey(Base64URL base64URL, Base64URL base64URL2, KeyUse keyUse, Set<KeyOperation> set, Algorithm algorithm, String str, URI uri, Base64URL base64URL3, List<Base64> list) {
        this(base64URL, base64URL2, null, null, null, null, null, null, null, keyUse, set, algorithm, str, uri, base64URL3, list);
    }

    public RSAKey(Base64URL base64URL, Base64URL base64URL2, Base64URL base64URL3, Base64URL base64URL4, Base64URL base64URL5, Base64URL base64URL6, Base64URL base64URL7, Base64URL base64URL8, List<OtherPrimesInfo> list, KeyUse keyUse, Set<KeyOperation> set, Algorithm algorithm, String str, URI uri, Base64URL base64URL9, List<Base64> list2) {
        Base64URL base64URL10;
        Base64URL base64URL11 = base64URL;
        Base64URL base64URL12 = base64URL2;
        Base64URL base64URL13 = base64URL4;
        Base64URL base64URL14 = base64URL5;
        Base64URL base64URL15 = base64URL6;
        Base64URL base64URL16 = base64URL7;
        Base64URL base64URL17 = base64URL8;
        super(KeyType.RSA, keyUse, set, algorithm, str, uri, base64URL9, list2);
        if (base64URL11 != null) {
            this.n = base64URL11;
            if (base64URL12 != null) {
                this.e = base64URL12;
                this.d = base64URL3;
                if (base64URL13 == null || base64URL14 == null || base64URL15 == null || base64URL16 == null) {
                    base64URL10 = base64URL8;
                } else {
                    base64URL10 = base64URL8;
                    if (base64URL10 != null) {
                        this.p = base64URL13;
                        this.q = base64URL14;
                        this.dp = base64URL15;
                        this.dq = base64URL16;
                        this.qi = base64URL10;
                        if (list != null) {
                            this.oth = Collections.unmodifiableList(list);
                            return;
                        } else {
                            this.oth = Collections.emptyList();
                            return;
                        }
                    }
                }
                if (base64URL13 == null && base64URL14 == null && base64URL15 == null && base64URL16 == null && base64URL10 == null && list == null) {
                    this.p = null;
                    this.q = null;
                    this.dp = null;
                    this.dq = null;
                    this.qi = null;
                    this.oth = Collections.emptyList();
                } else if (base64URL13 == null) {
                    throw new IllegalArgumentException("Incomplete second private (CRT) representation: The first prime factor must not be null");
                } else if (base64URL14 == null) {
                    throw new IllegalArgumentException("Incomplete second private (CRT) representation: The second prime factor must not be null");
                } else if (base64URL15 == null) {
                    throw new IllegalArgumentException("Incomplete second private (CRT) representation: The first factor CRT exponent must not be null");
                } else if (base64URL16 == null) {
                    throw new IllegalArgumentException("Incomplete second private (CRT) representation: The second factor CRT exponent must not be null");
                } else {
                    throw new IllegalArgumentException("Incomplete second private (CRT) representation: The first CRT coefficient must not be null");
                }
            } else {
                throw new IllegalArgumentException("The public exponent value must not be null");
            }
        } else {
            throw new IllegalArgumentException("The modulus value must not be null");
        }
    }

    public Base64URL getModulus() {
        return this.n;
    }

    public Base64URL getPublicExponent() {
        return this.e;
    }

    public RSAPublicKey toRSAPublicKey() throws JOSEException {
        try {
            return (RSAPublicKey) KeyFactory.getInstance(GooglePlayConstants.BILLING_VERIFICATION_KEY_FACTORY_ALGORITHM).generatePublic(new RSAPublicKeySpec(this.n.decodeToBigInteger(), this.e.decodeToBigInteger()));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e2) {
            throw new JOSEException(e2.getMessage(), e2);
        }
    }

    public RSAKey toPublicJWK() {
        RSAKey rSAKey = new RSAKey(getModulus(), getPublicExponent(), getKeyUse(), getKeyOperations(), getAlgorithm(), getKeyID(), getX509CertURL(), getX509CertThumbprint(), getX509CertChain());
        return rSAKey;
    }

    public JSONObject toJSONObject() {
        JSONObject jSONObject = super.toJSONObject();
        jSONObject.put("n", this.n.toString());
        jSONObject.put(e.a, this.e.toString());
        Base64URL base64URL = this.d;
        if (base64URL != null) {
            jSONObject.put("d", base64URL.toString());
        }
        Base64URL base64URL2 = this.p;
        if (base64URL2 != null) {
            jSONObject.put("p", base64URL2.toString());
        }
        Base64URL base64URL3 = this.q;
        if (base64URL3 != null) {
            jSONObject.put(Http.Q, base64URL3.toString());
        }
        Base64URL base64URL4 = this.dp;
        if (base64URL4 != null) {
            jSONObject.put("dp", base64URL4.toString());
        }
        Base64URL base64URL5 = this.dq;
        if (base64URL5 != null) {
            jSONObject.put("dq", base64URL5.toString());
        }
        Base64URL base64URL6 = this.qi;
        if (base64URL6 != null) {
            jSONObject.put("qi", base64URL6.toString());
        }
        List<OtherPrimesInfo> list = this.oth;
        if (list != null && !list.isEmpty()) {
            JSONArray jSONArray = new JSONArray();
            for (OtherPrimesInfo otherPrimesInfo : this.oth) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("r", otherPrimesInfo.r.toString());
                jSONObject2.put("d", otherPrimesInfo.d.toString());
                jSONObject2.put("t", otherPrimesInfo.t.toString());
                jSONArray.add(jSONObject2);
            }
            jSONObject.put("oth", jSONArray);
        }
        return jSONObject;
    }

    public static RSAKey parse(JSONObject jSONObject) throws ParseException {
        List list;
        JSONObject jSONObject2 = jSONObject;
        Base64URL base64URL = new Base64URL(JSONObjectUtils.getString(jSONObject2, "n"));
        Base64URL base64URL2 = new Base64URL(JSONObjectUtils.getString(jSONObject2, e.a));
        if (KeyType.parse(JSONObjectUtils.getString(jSONObject2, "kty")) == KeyType.RSA) {
            Base64URL base64URL3 = jSONObject2.containsKey("d") ? new Base64URL(JSONObjectUtils.getString(jSONObject2, "d")) : null;
            Base64URL base64URL4 = jSONObject2.containsKey("p") ? new Base64URL(JSONObjectUtils.getString(jSONObject2, "p")) : null;
            Base64URL base64URL5 = jSONObject2.containsKey(Http.Q) ? new Base64URL(JSONObjectUtils.getString(jSONObject2, Http.Q)) : null;
            Base64URL base64URL6 = jSONObject2.containsKey("dp") ? new Base64URL(JSONObjectUtils.getString(jSONObject2, "dp")) : null;
            Base64URL base64URL7 = jSONObject2.containsKey("dq") ? new Base64URL(JSONObjectUtils.getString(jSONObject2, "dq")) : null;
            Base64URL base64URL8 = jSONObject2.containsKey("qi") ? new Base64URL(JSONObjectUtils.getString(jSONObject2, "qi")) : null;
            if (jSONObject2.containsKey("oth")) {
                JSONArray jSONArray = JSONObjectUtils.getJSONArray(jSONObject2, "oth");
                list = new ArrayList(jSONArray.size());
                Iterator it = jSONArray.iterator();
                while (it.hasNext()) {
                    Object next = it.next();
                    if (next instanceof JSONObject) {
                        JSONObject jSONObject3 = (JSONObject) next;
                        list.add(new OtherPrimesInfo(new Base64URL(JSONObjectUtils.getString(jSONObject3, "r")), new Base64URL(JSONObjectUtils.getString(jSONObject3, "dq")), new Base64URL(JSONObjectUtils.getString(jSONObject3, "t"))));
                    }
                }
            } else {
                list = null;
            }
            try {
                RSAKey rSAKey = new RSAKey(base64URL, base64URL2, base64URL3, base64URL4, base64URL5, base64URL6, base64URL7, base64URL8, list, JWKMetadata.parseKeyUse(jSONObject), JWKMetadata.parseKeyOperations(jSONObject), JWKMetadata.parseAlgorithm(jSONObject), JWKMetadata.parseKeyID(jSONObject), JWKMetadata.parseX509CertURL(jSONObject), JWKMetadata.parseX509CertThumbprint(jSONObject), JWKMetadata.parseX509CertChain(jSONObject));
                return rSAKey;
            } catch (IllegalArgumentException e2) {
                throw new ParseException(e2.getMessage(), 0);
            }
        } else {
            throw new ParseException("The key type \"kty\" must be RSA", 0);
        }
    }
}
