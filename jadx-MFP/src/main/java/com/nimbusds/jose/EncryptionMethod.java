package com.nimbusds.jose;

import java.util.Collection;
import net.jcip.annotations.Immutable;

@Immutable
public final class EncryptionMethod extends Algorithm {
    public static final EncryptionMethod A128CBC_HS256 = new EncryptionMethod("A128CBC-HS256", Requirement.REQUIRED, 256);
    public static final EncryptionMethod A128CBC_HS256_DEPRECATED = new EncryptionMethod("A128CBC+HS256", Requirement.OPTIONAL, 256);
    public static final EncryptionMethod A128GCM = new EncryptionMethod("A128GCM", Requirement.RECOMMENDED, 128);
    public static final EncryptionMethod A192CBC_HS384 = new EncryptionMethod("A192CBC-HS384", Requirement.OPTIONAL, 384);
    public static final EncryptionMethod A192GCM = new EncryptionMethod("A192GCM", Requirement.OPTIONAL, 192);
    public static final EncryptionMethod A256CBC_HS512 = new EncryptionMethod("A256CBC-HS512", Requirement.REQUIRED, 512);
    public static final EncryptionMethod A256CBC_HS512_DEPRECATED = new EncryptionMethod("A256CBC+HS512", Requirement.OPTIONAL, 512);
    public static final EncryptionMethod A256GCM = new EncryptionMethod("A256GCM", Requirement.RECOMMENDED, 256);
    private static final long serialVersionUID = 1;
    private final int cekBitLength;

    public static final class Family extends AlgorithmFamily<EncryptionMethod> {
        public static final Family AES_CBC_HMAC_SHA = new Family(EncryptionMethod.A128CBC_HS256, EncryptionMethod.A192CBC_HS384, EncryptionMethod.A256CBC_HS512);
        public static final Family AES_GCM = new Family(EncryptionMethod.A128GCM, EncryptionMethod.A192GCM, EncryptionMethod.A256GCM);
        private static final long serialVersionUID = 1;

        public /* bridge */ /* synthetic */ boolean add(Algorithm algorithm) {
            return super.add(algorithm);
        }

        public /* bridge */ /* synthetic */ boolean addAll(Collection collection) {
            return super.addAll(collection);
        }

        public /* bridge */ /* synthetic */ boolean remove(Object obj) {
            return super.remove(obj);
        }

        public /* bridge */ /* synthetic */ boolean removeAll(Collection collection) {
            return super.removeAll(collection);
        }

        public /* bridge */ /* synthetic */ boolean retainAll(Collection collection) {
            return super.retainAll(collection);
        }

        public Family(EncryptionMethod... encryptionMethodArr) {
            super(encryptionMethodArr);
        }
    }

    public EncryptionMethod(String str, Requirement requirement, int i) {
        super(str, requirement);
        this.cekBitLength = i;
    }
}
