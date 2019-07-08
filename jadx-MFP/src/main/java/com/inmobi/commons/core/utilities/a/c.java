package com.inmobi.commons.core.utilities.a;

import android.annotation.SuppressLint;
import android.util.Base64;
import com.myfitnesspal.feature.payments.util.GooglePlayConstants;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;

/* compiled from: UidEncryptionUtils */
public class c {
    private static final String a = "c";

    @SuppressLint({"TrulyRandom"})
    public static String a(String str) {
        String str2 = null;
        if (str == null || "".equals(str)) {
            return null;
        }
        try {
            RSAPublicKey rSAPublicKey = (RSAPublicKey) KeyFactory.getInstance(GooglePlayConstants.BILLING_VERIFICATION_KEY_FACTORY_ALGORITHM).generatePublic(new RSAPublicKeySpec(new BigInteger("C10F7968CFE2C76AC6F0650C877806D4514DE58FC239592D2385BCE5609A84B2A0FBDAF29B05505EAD1FDFEF3D7209ACBF34B5D0A806DF18147EA9C0337D6B5B", 16), new BigInteger("010001", 16)));
            Cipher instance = Cipher.getInstance("RSA/ECB/nopadding");
            instance.init(1, rSAPublicKey);
            str2 = new String(Base64.encode(a(str.getBytes("UTF-8"), instance), 0));
        } catch (Exception e) {
            new StringBuilder("SDK encountered unexpected error in getting encrypted UID-map; ").append(e.getMessage());
        }
        return str2;
    }

    private static byte[] a(byte[] bArr, Cipher cipher) throws IllegalBlockSizeException, BadPaddingException {
        int i = 0;
        byte[] bArr2 = new byte[0];
        int length = bArr.length;
        byte[] bArr3 = new byte[64];
        while (i < length) {
            if (i > 0 && i % 64 == 0) {
                bArr2 = a(bArr2, cipher.doFinal(bArr3));
                bArr3 = new byte[(i + 64 > length ? length - i : 64)];
            }
            bArr3[i % 64] = bArr[i];
            i++;
        }
        return a(bArr2, cipher.doFinal(bArr3));
    }

    private static byte[] a(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[(bArr.length + bArr2.length)];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }
}
