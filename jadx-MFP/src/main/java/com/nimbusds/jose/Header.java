package com.nimbusds.jose;

import com.nimbusds.jose.util.Base64URL;
import com.nimbusds.jose.util.JSONObjectUtils;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import net.minidev.json.JSONObject;

public abstract class Header implements Serializable {
    private static final Map<String, Object> EMPTY_CUSTOM_PARAMS = Collections.unmodifiableMap(new HashMap());
    private static final long serialVersionUID = 1;
    private final Algorithm alg;
    private final Set<String> crit;
    private final String cty;
    private final Map<String, Object> customParams;
    private final Base64URL parsedBase64URL;
    private final JOSEObjectType typ;

    protected Header(Algorithm algorithm, JOSEObjectType jOSEObjectType, String str, Set<String> set, Map<String, Object> map, Base64URL base64URL) {
        if (algorithm != null) {
            this.alg = algorithm;
            this.typ = jOSEObjectType;
            this.cty = str;
            if (set != null) {
                this.crit = Collections.unmodifiableSet(new HashSet(set));
            } else {
                this.crit = null;
            }
            if (map != null) {
                this.customParams = Collections.unmodifiableMap(new HashMap(map));
            } else {
                this.customParams = EMPTY_CUSTOM_PARAMS;
            }
            this.parsedBase64URL = base64URL;
            return;
        }
        throw new IllegalArgumentException("The algorithm \"alg\" header parameter must not be null");
    }

    public Algorithm getAlgorithm() {
        return this.alg;
    }

    public Set<String> getCriticalParams() {
        return this.crit;
    }

    public JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject(this.customParams);
        jSONObject.put("alg", this.alg.toString());
        JOSEObjectType jOSEObjectType = this.typ;
        if (jOSEObjectType != null) {
            jSONObject.put("typ", jOSEObjectType.toString());
        }
        String str = this.cty;
        if (str != null) {
            jSONObject.put("cty", str);
        }
        Set<String> set = this.crit;
        if (set != null && !set.isEmpty()) {
            jSONObject.put("crit", new ArrayList(this.crit));
        }
        return jSONObject;
    }

    public String toString() {
        return toJSONObject().toString();
    }

    public Base64URL toBase64URL() {
        Base64URL base64URL = this.parsedBase64URL;
        return base64URL == null ? Base64URL.encode(toString()) : base64URL;
    }

    public static Algorithm parseAlgorithm(JSONObject jSONObject) throws ParseException {
        String string = JSONObjectUtils.getString(jSONObject, "alg");
        if (string.equals(Algorithm.NONE.getName())) {
            return Algorithm.NONE;
        }
        if (jSONObject.containsKey("enc")) {
            return JWEAlgorithm.parse(string);
        }
        return JWSAlgorithm.parse(string);
    }
}
