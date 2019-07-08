package com.nimbusds.jose;

import java.util.Collection;
import net.jcip.annotations.Immutable;

@Immutable
public final class JWEAlgorithm extends Algorithm {
    public static final JWEAlgorithm A128GCMKW = new JWEAlgorithm("A128GCMKW", Requirement.OPTIONAL);
    public static final JWEAlgorithm A128KW = new JWEAlgorithm("A128KW", Requirement.RECOMMENDED);
    public static final JWEAlgorithm A192GCMKW = new JWEAlgorithm("A192GCMKW", Requirement.OPTIONAL);
    public static final JWEAlgorithm A192KW = new JWEAlgorithm("A192KW", Requirement.OPTIONAL);
    public static final JWEAlgorithm A256GCMKW = new JWEAlgorithm("A256GCMKW", Requirement.OPTIONAL);
    public static final JWEAlgorithm A256KW = new JWEAlgorithm("A256KW", Requirement.RECOMMENDED);
    public static final JWEAlgorithm DIR = new JWEAlgorithm("dir", Requirement.RECOMMENDED);
    public static final JWEAlgorithm ECDH_ES = new JWEAlgorithm("ECDH-ES", Requirement.RECOMMENDED);
    public static final JWEAlgorithm ECDH_ES_A128KW = new JWEAlgorithm("ECDH-ES+A128KW", Requirement.RECOMMENDED);
    public static final JWEAlgorithm ECDH_ES_A192KW = new JWEAlgorithm("ECDH-ES+A192KW", Requirement.OPTIONAL);
    public static final JWEAlgorithm ECDH_ES_A256KW = new JWEAlgorithm("ECDH-ES+A256KW", Requirement.RECOMMENDED);
    public static final JWEAlgorithm PBES2_HS256_A128KW = new JWEAlgorithm("PBES2-HS256+A128KW", Requirement.OPTIONAL);
    public static final JWEAlgorithm PBES2_HS384_A192KW = new JWEAlgorithm("PBES2-HS384+A192KW", Requirement.OPTIONAL);
    public static final JWEAlgorithm PBES2_HS512_A256KW = new JWEAlgorithm("PBES2-HS512+A256KW", Requirement.OPTIONAL);
    public static final JWEAlgorithm RSA1_5 = new JWEAlgorithm("RSA1_5", Requirement.REQUIRED);
    public static final JWEAlgorithm RSA_OAEP = new JWEAlgorithm("RSA-OAEP", Requirement.OPTIONAL);
    public static final JWEAlgorithm RSA_OAEP_256 = new JWEAlgorithm("RSA-OAEP-256", Requirement.OPTIONAL);
    private static final long serialVersionUID = 1;

    public static final class Family extends AlgorithmFamily<JWEAlgorithm> {
        public static final Family AES_GCM_KW = new Family(JWEAlgorithm.A128GCMKW, JWEAlgorithm.A192GCMKW, JWEAlgorithm.A256GCMKW);
        public static final Family AES_KW = new Family(JWEAlgorithm.A128KW, JWEAlgorithm.A192KW, JWEAlgorithm.A256KW);
        public static final Family ECDH_ES = new Family(JWEAlgorithm.ECDH_ES, JWEAlgorithm.ECDH_ES_A128KW, JWEAlgorithm.ECDH_ES_A192KW, JWEAlgorithm.ECDH_ES_A256KW);
        public static final Family PBES2 = new Family(JWEAlgorithm.PBES2_HS256_A128KW, JWEAlgorithm.PBES2_HS384_A192KW, JWEAlgorithm.PBES2_HS512_A256KW);
        public static final Family RSA = new Family(JWEAlgorithm.RSA1_5, JWEAlgorithm.RSA_OAEP, JWEAlgorithm.RSA_OAEP_256);
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

        public Family(JWEAlgorithm... jWEAlgorithmArr) {
            super(jWEAlgorithmArr);
        }
    }

    public JWEAlgorithm(String str, Requirement requirement) {
        super(str, requirement);
    }

    public JWEAlgorithm(String str) {
        super(str, null);
    }

    public static JWEAlgorithm parse(String str) {
        if (str.equals(RSA1_5.getName())) {
            return RSA1_5;
        }
        if (str.equals(RSA_OAEP.getName())) {
            return RSA_OAEP;
        }
        if (str.equals(RSA_OAEP_256.getName())) {
            return RSA_OAEP_256;
        }
        if (str.equals(A128KW.getName())) {
            return A128KW;
        }
        if (str.equals(A192KW.getName())) {
            return A192KW;
        }
        if (str.equals(A256KW.getName())) {
            return A256KW;
        }
        if (str.equals(DIR.getName())) {
            return DIR;
        }
        if (str.equals(ECDH_ES.getName())) {
            return ECDH_ES;
        }
        if (str.equals(ECDH_ES_A128KW.getName())) {
            return ECDH_ES_A128KW;
        }
        if (str.equals(ECDH_ES_A192KW.getName())) {
            return ECDH_ES_A192KW;
        }
        if (str.equals(ECDH_ES_A256KW.getName())) {
            return ECDH_ES_A256KW;
        }
        if (str.equals(A128GCMKW.getName())) {
            return A128GCMKW;
        }
        if (str.equals(A192GCMKW.getName())) {
            return A192GCMKW;
        }
        if (str.equals(A256GCMKW.getName())) {
            return A256GCMKW;
        }
        if (str.equals(PBES2_HS256_A128KW.getName())) {
            return PBES2_HS256_A128KW;
        }
        if (str.equals(PBES2_HS384_A192KW.getName())) {
            return PBES2_HS384_A192KW;
        }
        if (str.equals(PBES2_HS512_A256KW.getName())) {
            return PBES2_HS512_A256KW;
        }
        return new JWEAlgorithm(str);
    }
}
