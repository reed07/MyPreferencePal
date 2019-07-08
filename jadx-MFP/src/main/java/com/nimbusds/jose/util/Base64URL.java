package com.nimbusds.jose.util;

import net.jcip.annotations.Immutable;

@Immutable
public class Base64URL extends Base64 {
    public Base64URL(String str) {
        super(str);
    }

    public boolean equals(Object obj) {
        return obj != null && (obj instanceof Base64URL) && toString().equals(obj.toString());
    }

    public static Base64URL encode(byte[] bArr) {
        return new Base64URL(Base64Codec.encodeToString(bArr, true));
    }

    public static Base64URL encode(String str) {
        return encode(str.getBytes(Base64.CHARSET));
    }
}
