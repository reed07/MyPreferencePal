package com.nimbusds.jose.jwk;

import java.text.ParseException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public enum KeyOperation {
    SIGN("sign"),
    VERIFY("verify"),
    ENCRYPT("encrypt"),
    DECRYPT("decrypt"),
    WRAP_KEY("wrapKey"),
    UNWRAP_KEY("unwrapKey"),
    DERIVE_KEY("deriveKey"),
    DERIVE_BITS("deriveBits");
    
    private final String identifier;

    private KeyOperation(String str) {
        if (str != null) {
            this.identifier = str;
            return;
        }
        throw new IllegalArgumentException("The key operation identifier must not be null");
    }

    public String identifier() {
        return this.identifier;
    }

    public String toString() {
        return identifier();
    }

    public static Set<KeyOperation> parse(List<String> list) throws ParseException {
        KeyOperation keyOperation;
        if (list == null) {
            return null;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (String str : list) {
            if (str != null) {
                KeyOperation[] values = values();
                int length = values.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        keyOperation = null;
                        break;
                    }
                    keyOperation = values[i];
                    if (str.equals(keyOperation.identifier())) {
                        break;
                    }
                    i++;
                }
                if (keyOperation != null) {
                    linkedHashSet.add(keyOperation);
                } else {
                    StringBuilder sb = new StringBuilder("Invalid JWK operation: ");
                    sb.append(str);
                    throw new ParseException(sb.toString(), 0);
                }
            }
        }
        return linkedHashSet;
    }
}
