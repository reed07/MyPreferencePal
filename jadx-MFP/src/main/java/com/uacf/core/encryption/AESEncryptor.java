package com.uacf.core.encryption;

import javax.crypto.SecretKey;

interface AESEncryptor {
    String decrypt(String str, EncryptedValue encryptedValue) throws UacfEncryptionException;

    EncryptedValue encrypt(String str, String str2) throws UacfEncryptionException;

    SecretKey generateAndStoreEncryptionKey(String str) throws UacfEncryptionException;
}
