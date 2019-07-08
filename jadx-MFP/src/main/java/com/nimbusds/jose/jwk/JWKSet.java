package com.nimbusds.jose.jwk;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

public class JWKSet {
    private final Map<String, Object> customMembers = new HashMap();
    private final List<JWK> keys = new LinkedList();

    public JSONObject toJSONObject() {
        return toJSONObject(true);
    }

    public JSONObject toJSONObject(boolean z) {
        JSONObject jSONObject = new JSONObject(this.customMembers);
        JSONArray jSONArray = new JSONArray();
        for (JWK jwk : this.keys) {
            if (z) {
                JWK publicJWK = jwk.toPublicJWK();
                if (publicJWK != null) {
                    jSONArray.add(publicJWK.toJSONObject());
                }
            } else {
                jSONArray.add(jwk.toJSONObject());
            }
        }
        jSONObject.put("keys", jSONArray);
        return jSONObject;
    }

    public String toString() {
        return toJSONObject().toString();
    }
}
