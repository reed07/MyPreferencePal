package com.nimbusds.jose;

import com.nimbusds.jose.util.Base64URL;
import com.nimbusds.jwt.SignedJWT;
import java.io.Serializable;
import java.nio.charset.Charset;
import net.jcip.annotations.Immutable;
import net.minidev.json.JSONObject;

@Immutable
public final class Payload implements Serializable {
    private static final Charset CHARSET = Charset.forName("UTF-8");
    private static final long serialVersionUID = 1;
    private final Base64URL base64URL;
    private final byte[] bytes;
    private final JSONObject jsonObject;
    private final JWSObject jwsObject;
    private final Origin origin;
    private final SignedJWT signedJWT;
    private final String string;

    public enum Origin {
        JSON,
        STRING,
        BYTE_ARRAY,
        BASE64URL,
        JWS_OBJECT,
        SIGNED_JWT
    }

    private static String byteArrayToString(byte[] bArr) {
        if (bArr != null) {
            return new String(bArr, CHARSET);
        }
        return null;
    }

    private static byte[] stringToByteArray(String str) {
        if (str != null) {
            return str.getBytes(CHARSET);
        }
        return null;
    }

    public Payload(JSONObject jSONObject) {
        if (jSONObject != null) {
            this.jsonObject = jSONObject;
            this.string = null;
            this.bytes = null;
            this.base64URL = null;
            this.jwsObject = null;
            this.signedJWT = null;
            this.origin = Origin.JSON;
            return;
        }
        throw new IllegalArgumentException("The JSON object must not be null");
    }

    public Payload(Base64URL base64URL2) {
        if (base64URL2 != null) {
            this.jsonObject = null;
            this.string = null;
            this.bytes = null;
            this.base64URL = base64URL2;
            this.jwsObject = null;
            this.signedJWT = null;
            this.origin = Origin.BASE64URL;
            return;
        }
        throw new IllegalArgumentException("The Base64URL-encoded object must not be null");
    }

    public String toString() {
        String str = this.string;
        if (str != null) {
            return str;
        }
        JWSObject jWSObject = this.jwsObject;
        if (jWSObject == null) {
            JSONObject jSONObject = this.jsonObject;
            if (jSONObject != null) {
                return jSONObject.toString();
            }
            byte[] bArr = this.bytes;
            if (bArr != null) {
                return byteArrayToString(bArr);
            }
            Base64URL base64URL2 = this.base64URL;
            if (base64URL2 != null) {
                return base64URL2.decodeToString();
            }
            return null;
        } else if (jWSObject.getParsedString() != null) {
            return this.jwsObject.getParsedString();
        } else {
            return this.jwsObject.serialize();
        }
    }

    public byte[] toBytes() {
        byte[] bArr = this.bytes;
        if (bArr != null) {
            return bArr;
        }
        Base64URL base64URL2 = this.base64URL;
        if (base64URL2 != null) {
            return base64URL2.decode();
        }
        return stringToByteArray(toString());
    }

    public Base64URL toBase64URL() {
        Base64URL base64URL2 = this.base64URL;
        if (base64URL2 != null) {
            return base64URL2;
        }
        return Base64URL.encode(toBytes());
    }
}
