package com.nimbusds.jose.crypto.factories;

import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.crypto.AESDecrypter;
import com.nimbusds.jose.crypto.DirectDecrypter;
import com.nimbusds.jose.crypto.ECDHDecrypter;
import com.nimbusds.jose.crypto.PasswordBasedDecrypter;
import com.nimbusds.jose.crypto.RSADecrypter;
import com.nimbusds.jose.jca.JWEJCAContext;
import com.nimbusds.jose.proc.JWEDecrypterFactory;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class DefaultJWEDecrypterFactory implements JWEDecrypterFactory {
    public static final Set<JWEAlgorithm> SUPPORTED_ALGORITHMS;
    public static final Set<EncryptionMethod> SUPPORTED_ENCRYPTION_METHODS;
    private final JWEJCAContext jcaContext = new JWEJCAContext();

    static {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.addAll(RSADecrypter.SUPPORTED_ALGORITHMS);
        linkedHashSet.addAll(ECDHDecrypter.SUPPORTED_ALGORITHMS);
        linkedHashSet.addAll(DirectDecrypter.SUPPORTED_ALGORITHMS);
        linkedHashSet.addAll(AESDecrypter.SUPPORTED_ALGORITHMS);
        linkedHashSet.addAll(PasswordBasedDecrypter.SUPPORTED_ALGORITHMS);
        SUPPORTED_ALGORITHMS = Collections.unmodifiableSet(linkedHashSet);
        LinkedHashSet linkedHashSet2 = new LinkedHashSet();
        linkedHashSet2.addAll(RSADecrypter.SUPPORTED_ENCRYPTION_METHODS);
        linkedHashSet2.addAll(ECDHDecrypter.SUPPORTED_ENCRYPTION_METHODS);
        linkedHashSet2.addAll(DirectDecrypter.SUPPORTED_ENCRYPTION_METHODS);
        linkedHashSet2.addAll(AESDecrypter.SUPPORTED_ENCRYPTION_METHODS);
        linkedHashSet2.addAll(PasswordBasedDecrypter.SUPPORTED_ENCRYPTION_METHODS);
        SUPPORTED_ENCRYPTION_METHODS = Collections.unmodifiableSet(linkedHashSet2);
    }
}
