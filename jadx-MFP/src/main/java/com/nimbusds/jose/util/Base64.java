package com.nimbusds.jose.util;

import java.io.Serializable;
import java.math.BigInteger;
import java.nio.charset.Charset;
import net.jcip.annotations.Immutable;
import net.minidev.json.JSONAware;
import net.minidev.json.JSONValue;

@Immutable
public class Base64 implements Serializable, JSONAware {
    public static final Charset CHARSET = Charset.forName("UTF-8");
    private static final long serialVersionUID = 1;
    private final String value;

    public Base64(String str) {
        if (str != null) {
            this.value = str;
            return;
        }
        throw new IllegalArgumentException("The Base64 value must not be null");
    }

    public byte[] decode() {
        return Base64Codec.decode(this.value);
    }

    public BigInteger decodeToBigInteger() {
        return new BigInteger(1, decode());
    }

    public String decodeToString() {
        return new String(decode(), CHARSET);
    }

    public String toJSONString() {
        StringBuilder sb = new StringBuilder("\"");
        sb.append(JSONValue.escape(this.value));
        sb.append("\"");
        return sb.toString();
    }

    public String toString() {
        return this.value;
    }

    public int hashCode() {
        return this.value.hashCode();
    }

    public boolean equals(Object obj) {
        return obj != null && (obj instanceof Base64) && toString().equals(obj.toString());
    }
}
