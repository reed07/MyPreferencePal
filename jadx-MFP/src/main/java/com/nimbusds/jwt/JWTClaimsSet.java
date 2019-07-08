package com.nimbusds.jwt;

import com.brightcove.player.C;
import com.nimbusds.jwt.util.DateUtils;
import java.io.Serializable;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import net.jcip.annotations.Immutable;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

@Immutable
public final class JWTClaimsSet implements Serializable {
    private static final Set<String> REGISTERED_CLAIM_NAMES;
    private static final long serialVersionUID = 1;
    private final Map<String, Object> claims;

    public static class Builder {
        private final Map<String, Object> claims = new LinkedHashMap();

        public Builder claim(String str, Object obj) {
            this.claims.put(str, obj);
            return this;
        }

        public JWTClaimsSet build() {
            return new JWTClaimsSet(this.claims, null);
        }
    }

    static {
        HashSet hashSet = new HashSet();
        hashSet.add("iss");
        hashSet.add(C.DASH_ROLE_SUB_VALUE);
        hashSet.add("aud");
        hashSet.add("exp");
        hashSet.add("nbf");
        hashSet.add("iat");
        hashSet.add("jti");
        REGISTERED_CLAIM_NAMES = Collections.unmodifiableSet(hashSet);
    }

    private JWTClaimsSet(Map<String, Object> map) {
        this.claims = new LinkedHashMap();
        this.claims.putAll(map);
    }

    /* synthetic */ JWTClaimsSet(Map map, JWTClaimsSet jWTClaimsSet) {
        this(map);
    }

    public List<String> getAudience() {
        List<String> list = null;
        try {
            List stringListClaim = getStringListClaim("aud");
            if (stringListClaim != null) {
                list = Collections.unmodifiableList(stringListClaim);
            }
            return list;
        } catch (ParseException unused) {
            return null;
        }
    }

    public Object getClaim(String str) {
        return this.claims.get(str);
    }

    public String[] getStringArrayClaim(String str) throws ParseException {
        if (getClaim(str) == null) {
            return null;
        }
        try {
            List list = (List) getClaim(str);
            String[] strArr = new String[list.size()];
            int i = 0;
            while (i < strArr.length) {
                try {
                    strArr[i] = (String) list.get(i);
                    i++;
                } catch (ClassCastException unused) {
                    StringBuilder sb = new StringBuilder("The \"");
                    sb.append(str);
                    sb.append("\" claim is not a list / JSON array of strings");
                    throw new ParseException(sb.toString(), 0);
                }
            }
            return strArr;
        } catch (ClassCastException unused2) {
            StringBuilder sb2 = new StringBuilder("The \"");
            sb2.append(str);
            sb2.append("\" claim is not a list / JSON array");
            throw new ParseException(sb2.toString(), 0);
        }
    }

    public List<String> getStringListClaim(String str) throws ParseException {
        String[] stringArrayClaim = getStringArrayClaim(str);
        if (stringArrayClaim == null) {
            return null;
        }
        return Collections.unmodifiableList(Arrays.asList(stringArrayClaim));
    }

    public JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject();
        for (Entry entry : this.claims.entrySet()) {
            if (entry.getValue() instanceof Date) {
                jSONObject.put((String) entry.getKey(), Long.valueOf(DateUtils.toSecondsSinceEpoch((Date) entry.getValue())));
            } else if ("aud".equals(entry.getKey())) {
                List audience = getAudience();
                if (audience != null && !audience.isEmpty()) {
                    if (audience.size() == 1) {
                        jSONObject.put("aud", audience.get(0));
                    } else {
                        JSONArray jSONArray = new JSONArray();
                        jSONArray.addAll(audience);
                        jSONObject.put("aud", jSONArray);
                    }
                }
            } else if (entry.getValue() != null) {
                jSONObject.put((String) entry.getKey(), entry.getValue());
            }
        }
        return jSONObject;
    }

    public String toString() {
        return toJSONObject().toJSONString();
    }
}
