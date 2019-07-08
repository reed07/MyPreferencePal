package com.nimbusds.jose;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.util.Base64;
import com.nimbusds.jose.util.Base64URL;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minidev.json.JSONObject;

abstract class CommonSEHeader extends Header {
    private static final long serialVersionUID = 1;
    private final URI jku;
    private final JWK jwk;
    private final String kid;
    private final List<Base64> x5c;
    private final Base64URL x5t;
    private final Base64URL x5t256;
    private final URI x5u;

    protected CommonSEHeader(Algorithm algorithm, JOSEObjectType jOSEObjectType, String str, Set<String> set, URI uri, JWK jwk2, URI uri2, Base64URL base64URL, Base64URL base64URL2, List<Base64> list, String str2, Map<String, Object> map, Base64URL base64URL3) {
        String str3;
        List<Base64> list2 = list;
        super(algorithm, jOSEObjectType, str, set, map, base64URL3);
        this.jku = uri;
        this.jwk = jwk2;
        this.x5u = uri2;
        this.x5t = base64URL;
        this.x5t256 = base64URL2;
        if (list2 != null) {
            this.x5c = Collections.unmodifiableList(new ArrayList(list2));
            str3 = str2;
        } else {
            this.x5c = null;
            str3 = str2;
        }
        this.kid = str3;
    }

    public JSONObject toJSONObject() {
        JSONObject jSONObject = super.toJSONObject();
        URI uri = this.jku;
        if (uri != null) {
            jSONObject.put("jku", uri.toString());
        }
        JWK jwk2 = this.jwk;
        if (jwk2 != null) {
            jSONObject.put("jwk", jwk2.toJSONObject());
        }
        URI uri2 = this.x5u;
        if (uri2 != null) {
            jSONObject.put("x5u", uri2.toString());
        }
        Base64URL base64URL = this.x5t;
        if (base64URL != null) {
            jSONObject.put("x5t", base64URL.toString());
        }
        Base64URL base64URL2 = this.x5t256;
        if (base64URL2 != null) {
            jSONObject.put("x5t#S256", base64URL2.toString());
        }
        List<Base64> list = this.x5c;
        if (list != null && !list.isEmpty()) {
            jSONObject.put("x5c", this.x5c);
        }
        String str = this.kid;
        if (str != null) {
            jSONObject.put("kid", str);
        }
        return jSONObject;
    }
}
