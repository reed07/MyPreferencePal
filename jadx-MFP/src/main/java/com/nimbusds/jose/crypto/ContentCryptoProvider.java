package com.nimbusds.jose.crypto;

import com.nimbusds.jose.EncryptionMethod;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

class ContentCryptoProvider {
    public static final Map<Integer, Set<EncryptionMethod>> COMPATIBLE_ENCRYPTION_METHODS;
    public static final Set<EncryptionMethod> SUPPORTED_ENCRYPTION_METHODS;

    ContentCryptoProvider() {
    }

    static {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.add(EncryptionMethod.A128CBC_HS256);
        linkedHashSet.add(EncryptionMethod.A192CBC_HS384);
        linkedHashSet.add(EncryptionMethod.A256CBC_HS512);
        linkedHashSet.add(EncryptionMethod.A128GCM);
        linkedHashSet.add(EncryptionMethod.A192GCM);
        linkedHashSet.add(EncryptionMethod.A256GCM);
        linkedHashSet.add(EncryptionMethod.A128CBC_HS256_DEPRECATED);
        linkedHashSet.add(EncryptionMethod.A256CBC_HS512_DEPRECATED);
        SUPPORTED_ENCRYPTION_METHODS = Collections.unmodifiableSet(linkedHashSet);
        HashMap hashMap = new HashMap();
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        HashSet hashSet3 = new HashSet();
        HashSet hashSet4 = new HashSet();
        HashSet hashSet5 = new HashSet();
        hashSet.add(EncryptionMethod.A128GCM);
        hashSet2.add(EncryptionMethod.A192GCM);
        hashSet3.add(EncryptionMethod.A256GCM);
        hashSet3.add(EncryptionMethod.A128CBC_HS256);
        hashSet3.add(EncryptionMethod.A128CBC_HS256_DEPRECATED);
        hashSet4.add(EncryptionMethod.A192CBC_HS384);
        hashSet5.add(EncryptionMethod.A256CBC_HS512);
        hashSet5.add(EncryptionMethod.A256CBC_HS512_DEPRECATED);
        hashMap.put(Integer.valueOf(128), Collections.unmodifiableSet(hashSet));
        hashMap.put(Integer.valueOf(192), Collections.unmodifiableSet(hashSet2));
        hashMap.put(Integer.valueOf(256), Collections.unmodifiableSet(hashSet3));
        hashMap.put(Integer.valueOf(384), Collections.unmodifiableSet(hashSet4));
        hashMap.put(Integer.valueOf(512), Collections.unmodifiableSet(hashSet5));
        COMPATIBLE_ENCRYPTION_METHODS = Collections.unmodifiableMap(hashMap);
    }
}
