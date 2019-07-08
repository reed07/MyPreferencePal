package com.nimbusds.jose;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.util.Base64;
import com.nimbusds.jose.util.Base64URL;
import com.nimbusds.jose.util.JSONObjectUtils;
import com.nimbusds.jose.util.X509CertChainUtils;
import java.net.URI;
import java.text.ParseException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.jcip.annotations.Immutable;
import net.minidev.json.JSONObject;

@Immutable
public final class JWSHeader extends CommonSEHeader {
    private static final Set<String> REGISTERED_PARAMETER_NAMES;
    private static final long serialVersionUID = 1;

    public static class Builder {
        private final JWSAlgorithm alg;
        private Set<String> crit;
        private String cty;
        private Map<String, Object> customParams;
        private URI jku;
        private JWK jwk;
        private String kid;
        private Base64URL parsedBase64URL;
        private JOSEObjectType typ;
        private List<Base64> x5c;
        private Base64URL x5t;
        private Base64URL x5t256;
        private URI x5u;

        public Builder(JWSAlgorithm jWSAlgorithm) {
            if (!jWSAlgorithm.getName().equals(Algorithm.NONE.getName())) {
                this.alg = jWSAlgorithm;
                return;
            }
            throw new IllegalArgumentException("The JWS algorithm \"alg\" cannot be \"none\"");
        }

        public Builder type(JOSEObjectType jOSEObjectType) {
            this.typ = jOSEObjectType;
            return this;
        }

        public Builder contentType(String str) {
            this.cty = str;
            return this;
        }

        public Builder criticalParams(Set<String> set) {
            this.crit = set;
            return this;
        }

        public Builder jwkURL(URI uri) {
            this.jku = uri;
            return this;
        }

        public Builder jwk(JWK jwk2) {
            this.jwk = jwk2;
            return this;
        }

        public Builder x509CertURL(URI uri) {
            this.x5u = uri;
            return this;
        }

        public Builder x509CertThumbprint(Base64URL base64URL) {
            this.x5t = base64URL;
            return this;
        }

        public Builder x509CertSHA256Thumbprint(Base64URL base64URL) {
            this.x5t256 = base64URL;
            return this;
        }

        public Builder x509CertChain(List<Base64> list) {
            this.x5c = list;
            return this;
        }

        public Builder keyID(String str) {
            this.kid = str;
            return this;
        }

        public Builder customParam(String str, Object obj) {
            if (!JWSHeader.getRegisteredParameterNames().contains(str)) {
                if (this.customParams == null) {
                    this.customParams = new HashMap();
                }
                this.customParams.put(str, obj);
                return this;
            }
            StringBuilder sb = new StringBuilder("The parameter name \"");
            sb.append(str);
            sb.append("\" matches a registered name");
            throw new IllegalArgumentException(sb.toString());
        }

        public Builder parsedBase64URL(Base64URL base64URL) {
            this.parsedBase64URL = base64URL;
            return this;
        }

        public JWSHeader build() {
            JWSHeader jWSHeader = new JWSHeader(this.alg, this.typ, this.cty, this.crit, this.jku, this.jwk, this.x5u, this.x5t, this.x5t256, this.x5c, this.kid, this.customParams, this.parsedBase64URL);
            return jWSHeader;
        }
    }

    public /* bridge */ /* synthetic */ JSONObject toJSONObject() {
        return super.toJSONObject();
    }

    static {
        HashSet hashSet = new HashSet();
        hashSet.add("alg");
        hashSet.add("jku");
        hashSet.add("jwk");
        hashSet.add("x5u");
        hashSet.add("x5t");
        hashSet.add("x5t#S256");
        hashSet.add("x5c");
        hashSet.add("kid");
        hashSet.add("typ");
        hashSet.add("cty");
        hashSet.add("crit");
        REGISTERED_PARAMETER_NAMES = Collections.unmodifiableSet(hashSet);
    }

    public JWSHeader(JWSAlgorithm jWSAlgorithm, JOSEObjectType jOSEObjectType, String str, Set<String> set, URI uri, JWK jwk, URI uri2, Base64URL base64URL, Base64URL base64URL2, List<Base64> list, String str2, Map<String, Object> map, Base64URL base64URL3) {
        super(jWSAlgorithm, jOSEObjectType, str, set, uri, jwk, uri2, base64URL, base64URL2, list, str2, map, base64URL3);
        if (jWSAlgorithm.getName().equals(Algorithm.NONE.getName())) {
            throw new IllegalArgumentException("The JWS algorithm \"alg\" cannot be \"none\"");
        }
    }

    public static Set<String> getRegisteredParameterNames() {
        return REGISTERED_PARAMETER_NAMES;
    }

    public JWSAlgorithm getAlgorithm() {
        return (JWSAlgorithm) super.getAlgorithm();
    }

    public static JWSHeader parse(JSONObject jSONObject, Base64URL base64URL) throws ParseException {
        Algorithm parseAlgorithm = Header.parseAlgorithm(jSONObject);
        if (parseAlgorithm instanceof JWSAlgorithm) {
            Builder parsedBase64URL = new Builder((JWSAlgorithm) parseAlgorithm).parsedBase64URL(base64URL);
            for (String str : jSONObject.keySet()) {
                if (!"alg".equals(str)) {
                    if ("typ".equals(str)) {
                        parsedBase64URL = parsedBase64URL.type(new JOSEObjectType(JSONObjectUtils.getString(jSONObject, str)));
                    } else if ("cty".equals(str)) {
                        parsedBase64URL = parsedBase64URL.contentType(JSONObjectUtils.getString(jSONObject, str));
                    } else if ("crit".equals(str)) {
                        parsedBase64URL = parsedBase64URL.criticalParams(new HashSet(JSONObjectUtils.getStringList(jSONObject, str)));
                    } else if ("jku".equals(str)) {
                        parsedBase64URL = parsedBase64URL.jwkURL(JSONObjectUtils.getURI(jSONObject, str));
                    } else if ("jwk".equals(str)) {
                        parsedBase64URL = parsedBase64URL.jwk(JWK.parse(JSONObjectUtils.getJSONObject(jSONObject, str)));
                    } else if ("x5u".equals(str)) {
                        parsedBase64URL = parsedBase64URL.x509CertURL(JSONObjectUtils.getURI(jSONObject, str));
                    } else if ("x5t".equals(str)) {
                        parsedBase64URL = parsedBase64URL.x509CertThumbprint(new Base64URL(JSONObjectUtils.getString(jSONObject, str)));
                    } else if ("x5t#S256".equals(str)) {
                        parsedBase64URL = parsedBase64URL.x509CertSHA256Thumbprint(new Base64URL(JSONObjectUtils.getString(jSONObject, str)));
                    } else if ("x5c".equals(str)) {
                        parsedBase64URL = parsedBase64URL.x509CertChain(X509CertChainUtils.parseX509CertChain(JSONObjectUtils.getJSONArray(jSONObject, str)));
                    } else if ("kid".equals(str)) {
                        parsedBase64URL = parsedBase64URL.keyID(JSONObjectUtils.getString(jSONObject, str));
                    } else {
                        parsedBase64URL = parsedBase64URL.customParam(str, jSONObject.get(str));
                    }
                }
            }
            return parsedBase64URL.build();
        }
        throw new ParseException("The algorithm \"alg\" header parameter must be for signatures", 0);
    }

    public static JWSHeader parse(String str, Base64URL base64URL) throws ParseException {
        return parse(JSONObjectUtils.parseJSONObject(str), base64URL);
    }

    public static JWSHeader parse(Base64URL base64URL) throws ParseException {
        return parse(base64URL.decodeToString(), base64URL);
    }
}
