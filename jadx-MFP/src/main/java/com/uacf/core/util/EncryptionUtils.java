package com.uacf.core.util;

import android.content.Context;
import com.myfitnesspal.feature.payments.util.GooglePlayConstants;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

public final class EncryptionUtils {
    public static String encryptAndBase64Encode(Context context, byte[] bArr) {
        try {
            InputStream open = context.getResources().getAssets().open("public_key.der");
            byte[] bArr2 = new byte[open.available()];
            open.read(bArr2);
            PublicKey generatePublic = KeyFactory.getInstance(GooglePlayConstants.BILLING_VERIFICATION_KEY_FACTORY_ALGORITHM).generatePublic(new X509EncodedKeySpec(bArr2));
            Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1PADDING");
            instance.init(1, generatePublic);
            return Base64.encodeToString(instance.doFinal(bArr), 2);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String shaHex(byte[] bArr) {
        return Hex.encodeHexString(sha(bArr));
    }

    public static byte[] sha(byte[] bArr) {
        return getShaDigest().digest(bArr);
    }

    private static MessageDigest getShaDigest() {
        try {
            return MessageDigest.getInstance("SHA");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
