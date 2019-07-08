package com.nimbusds.jose;

import java.io.Serializable;
import kotlin.text.Typography;
import net.jcip.annotations.Immutable;
import net.minidev.json.JSONAware;
import net.minidev.json.JSONObject;

@Immutable
public final class JOSEObjectType implements Serializable, JSONAware {
    public static final JOSEObjectType JOSE = new JOSEObjectType("JOSE");
    public static final JOSEObjectType JOSE_JSON = new JOSEObjectType("JOSE+JSON");
    public static final JOSEObjectType JWT = new JOSEObjectType("JWT");
    private static final long serialVersionUID = 1;
    private final String type;

    public JOSEObjectType(String str) {
        if (str != null) {
            this.type = str;
            return;
        }
        throw new IllegalArgumentException("The object type must not be null");
    }

    public int hashCode() {
        return this.type.hashCode();
    }

    public boolean equals(Object obj) {
        return obj != null && (obj instanceof JOSEObjectType) && toString().equals(obj.toString());
    }

    public String toString() {
        return this.type;
    }

    public String toJSONString() {
        StringBuilder sb = new StringBuilder("\"");
        sb.append(JSONObject.escape(this.type));
        sb.append(Typography.quote);
        return sb.toString();
    }
}
