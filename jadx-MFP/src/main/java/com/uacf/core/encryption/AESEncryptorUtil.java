package com.uacf.core.encryption;

import javax.crypto.SecretKey;

public class AESEncryptorUtil implements AESEncryptor {
    private AESEncryptor AESEncryptor;

    public String decrypt(String str, EncryptedValue encryptedValue) throws UacfEncryptionException {
        return this.AESEncryptor.decrypt(str, encryptedValue);
    }

    public EncryptedValue encrypt(String str, String str2) throws UacfEncryptionException {
        return this.AESEncryptor.encrypt(str, str2);
    }

    public SecretKey generateAndStoreEncryptionKey(String str) throws UacfEncryptionException {
        return this.AESEncryptor.generateAndStoreEncryptionKey(str);
    }
}
