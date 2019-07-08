package com.nimbusds.jose;

import com.nimbusds.jose.util.Base64URL;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import net.jcip.annotations.Immutable;

@Immutable
public final class PlainHeader extends Header {
    private static final Set<String> REGISTERED_PARAMETER_NAMES;
    private static final long serialVersionUID = 1;

    public static class Builder {
    }

    static {
        HashSet hashSet = new HashSet();
        hashSet.add("alg");
        hashSet.add("typ");
        hashSet.add("cty");
        hashSet.add("crit");
        REGISTERED_PARAMETER_NAMES = Collections.unmodifiableSet(hashSet);
    }

    public PlainHeader() {
        this(null, null, null, null, null);
    }

    public PlainHeader(JOSEObjectType jOSEObjectType, String str, Set<String> set, Map<String, Object> map, Base64URL base64URL) {
        super(Algorithm.NONE, jOSEObjectType, str, set, map, base64URL);
    }

    public Algorithm getAlgorithm() {
        return Algorithm.NONE;
    }
}
