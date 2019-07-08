package com.nimbusds.jose;

import java.io.Serializable;
import kotlin.text.Typography;
import net.jcip.annotations.Immutable;
import net.minidev.json.JSONAware;
import net.minidev.json.JSONObject;

@Immutable
public class Algorithm implements Serializable, JSONAware {
    public static final Algorithm NONE = new Algorithm("none", Requirement.REQUIRED);
    private static final long serialVersionUID = 1;
    private final String name;
    private final Requirement requirement;

    public Algorithm(String str, Requirement requirement2) {
        if (str != null) {
            this.name = str;
            this.requirement = requirement2;
            return;
        }
        throw new IllegalArgumentException("The algorithm name must not be null");
    }

    public Algorithm(String str) {
        this(str, null);
    }

    public final String getName() {
        return this.name;
    }

    public final int hashCode() {
        return this.name.hashCode();
    }

    public boolean equals(Object obj) {
        return obj != null && (obj instanceof Algorithm) && toString().equals(obj.toString());
    }

    public final String toString() {
        return this.name;
    }

    public final String toJSONString() {
        StringBuilder sb = new StringBuilder("\"");
        sb.append(JSONObject.escape(this.name));
        sb.append(Typography.quote);
        return sb.toString();
    }
}
