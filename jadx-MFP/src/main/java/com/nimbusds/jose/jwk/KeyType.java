package com.nimbusds.jose.jwk;

import com.myfitnesspal.feature.payments.util.GooglePlayConstants;
import com.nimbusds.jose.Requirement;
import java.io.Serializable;
import kotlin.text.Typography;
import net.jcip.annotations.Immutable;
import net.minidev.json.JSONAware;
import net.minidev.json.JSONObject;

@Immutable
public final class KeyType implements Serializable, JSONAware {
    public static final KeyType EC = new KeyType("EC", Requirement.RECOMMENDED);
    public static final KeyType OCT = new KeyType("oct", Requirement.OPTIONAL);
    public static final KeyType RSA = new KeyType(GooglePlayConstants.BILLING_VERIFICATION_KEY_FACTORY_ALGORITHM, Requirement.REQUIRED);
    private static final long serialVersionUID = 1;
    private final Requirement requirement;
    private final String value;

    public KeyType(String str, Requirement requirement2) {
        if (str != null) {
            this.value = str;
            this.requirement = requirement2;
            return;
        }
        throw new IllegalArgumentException("The key type value must not be null");
    }

    public String getValue() {
        return this.value;
    }

    public int hashCode() {
        return this.value.hashCode();
    }

    public boolean equals(Object obj) {
        return obj != null && (obj instanceof KeyType) && toString().equals(obj.toString());
    }

    public String toString() {
        return this.value;
    }

    public String toJSONString() {
        StringBuilder sb = new StringBuilder("\"");
        sb.append(JSONObject.escape(this.value));
        sb.append(Typography.quote);
        return sb.toString();
    }

    public static KeyType parse(String str) {
        if (str.equals(EC.getValue())) {
            return EC;
        }
        if (str.equals(RSA.getValue())) {
            return RSA;
        }
        if (str.equals(OCT.getValue())) {
            return OCT;
        }
        return new KeyType(str, null);
    }
}
