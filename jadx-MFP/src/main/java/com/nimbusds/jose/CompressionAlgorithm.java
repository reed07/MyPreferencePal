package com.nimbusds.jose;

import java.io.Serializable;
import kotlin.text.Typography;
import net.jcip.annotations.Immutable;
import net.minidev.json.JSONAware;
import net.minidev.json.JSONObject;

@Immutable
public final class CompressionAlgorithm implements Serializable, JSONAware {
    public static final CompressionAlgorithm DEF = new CompressionAlgorithm("DEF");
    private static final long serialVersionUID = 1;
    private final String name;

    public CompressionAlgorithm(String str) {
        if (str != null) {
            this.name = str;
            return;
        }
        throw new IllegalArgumentException("The compression algorithm name must not be null");
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    public boolean equals(Object obj) {
        return obj != null && (obj instanceof CompressionAlgorithm) && toString().equals(obj.toString());
    }

    public String toString() {
        return this.name;
    }

    public String toJSONString() {
        StringBuilder sb = new StringBuilder("\"");
        sb.append(JSONObject.escape(this.name));
        sb.append(Typography.quote);
        return sb.toString();
    }
}
