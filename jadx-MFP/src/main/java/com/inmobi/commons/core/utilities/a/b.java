package com.inmobi.commons.core.utilities.a;

import android.annotation.SuppressLint;
import android.util.Base64;
import com.myfitnesspal.feature.payments.util.GooglePlayConstants;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* compiled from: RequestEncryptionUtils */
public class b {
    private static final String a = "b";
    private static String b = "AES";
    private static String c = "AES/CBC/PKCS7Padding";
    private static byte[] d;

    public static String a(String str, byte[] bArr, byte[] bArr2, byte[] bArr3, String str2, String str3) {
        try {
            byte[] b2 = b(str.getBytes("UTF-8"), bArr, bArr2);
            byte[] b3 = b(b2, bArr3);
            byte[] a2 = a(b2);
            byte[] a3 = a(b3);
            return new String(Base64.encode(a(a(a(a(a(a(bArr), a(bArr3)), a(bArr2)), str3, str2)), a(a2, a3)), 8));
        } catch (Exception e) {
            new StringBuilder("SDK encountered unexpected error in generating secret message; ").append(e.getMessage());
            return null;
        }
    }

    @SuppressLint({"TrulyRandom"})
    private static byte[] b(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, b);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(bArr3);
        try {
            Cipher instance = Cipher.getInstance(c);
            instance.init(1, secretKeySpec, ivParameterSpec);
            instance.init(1, secretKeySpec, ivParameterSpec);
            instance.init(1, secretKeySpec, ivParameterSpec);
            return instance.doFinal(bArr);
        } catch (Throwable th) {
            new StringBuilder("SDK encountered unexpected error in getting encrypted AES bytes; ").append(th.getMessage());
            return null;
        }
    }

    private static byte[] b() {
        try {
            KeyGenerator instance = KeyGenerator.getInstance("AES");
            instance.init(128, new SecureRandom());
            SecretKey generateKey = instance.generateKey();
            if (generateKey != null) {
                return generateKey.getEncoded();
            }
        } catch (Exception e) {
            new StringBuilder("SDK encountered unexpected error in generating AES public key; ").append(e.getMessage());
        }
        return null;
    }

    private static byte[] b(byte[] bArr, byte[] bArr2) {
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, 0, bArr2.length, "HmacSHA1");
        try {
            Mac instance = Mac.getInstance("HmacSHA1");
            instance.init(secretKeySpec);
            return instance.doFinal(bArr);
        } catch (InvalidKeyException | NoSuchAlgorithmException unused) {
            return null;
        }
    }

    public static byte[] a(byte[] bArr) {
        long length = (long) bArr.length;
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        allocate.putLong(length);
        byte[] array = allocate.array();
        byte[] bArr2 = new byte[(array.length + bArr.length)];
        System.arraycopy(array, 0, bArr2, 0, array.length);
        System.arraycopy(bArr, 0, bArr2, array.length, bArr.length);
        return bArr2;
    }

    public static byte[] a(byte[] bArr, String str, String str2) {
        try {
            RSAPublicKey rSAPublicKey = (RSAPublicKey) KeyFactory.getInstance(GooglePlayConstants.BILLING_VERIFICATION_KEY_FACTORY_ALGORITHM).generatePublic(new RSAPublicKeySpec(new BigInteger(str2, 16), new BigInteger(str, 16)));
            Cipher instance = Cipher.getInstance("RSA/ECB/nopadding");
            instance.init(1, rSAPublicKey);
            return instance.doFinal(bArr);
        } catch (Throwable th) {
            new StringBuilder("SDK encountered unexpected error in getting encrypted RSA bytes; ").append(th.getMessage());
            return null;
        }
    }

    public static byte[] a(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[(bArr.length + bArr2.length)];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:10|11|12|13|14) */
    /* JADX WARNING: Can't wrap try/catch for region: R(7:2|3|4|(2:6|7)(2:8|(5:10|11|12|13|14))|15|16|17) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0043 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0050 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized byte[] a() {
        /*
            java.lang.Class<com.inmobi.commons.core.utilities.a.b> r0 = com.inmobi.commons.core.utilities.a.b.class
            monitor-enter(r0)
            com.inmobi.commons.core.utilities.a.a r1 = new com.inmobi.commons.core.utilities.a.a     // Catch:{ all -> 0x0054 }
            r1.<init>()     // Catch:{ all -> 0x0054 }
            long r2 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x0054 }
            r4 = 1000(0x3e8, double:4.94E-321)
            long r2 = r2 / r4
            com.inmobi.commons.core.d.c r4 = r1.a     // Catch:{ all -> 0x0054 }
            java.lang.String r5 = "last_generated_ts"
            r6 = 0
            long r4 = r4.b(r5, r6)     // Catch:{ all -> 0x0054 }
            long r2 = r2 - r4
            r4 = 86400(0x15180, double:4.26873E-319)
            r6 = 0
            int r7 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r7 <= 0) goto L_0x0030
            byte[] r2 = b()     // Catch:{ Exception -> 0x0050 }
            d = r2     // Catch:{ Exception -> 0x0050 }
            java.lang.String r2 = android.util.Base64.encodeToString(r2, r6)     // Catch:{ Exception -> 0x0050 }
            r1.a(r2)     // Catch:{ Exception -> 0x0050 }
            goto L_0x0050
        L_0x0030:
            byte[] r2 = d     // Catch:{ Exception -> 0x0050 }
            if (r2 != 0) goto L_0x0050
            com.inmobi.commons.core.d.c r2 = r1.a     // Catch:{ Exception -> 0x0050 }
            java.lang.String r3 = "aes_public_key"
            java.lang.String r2 = r2.c(r3)     // Catch:{ Exception -> 0x0050 }
            byte[] r2 = android.util.Base64.decode(r2, r6)     // Catch:{ IllegalArgumentException -> 0x0043 }
            d = r2     // Catch:{ IllegalArgumentException -> 0x0043 }
            goto L_0x0050
        L_0x0043:
            byte[] r2 = b()     // Catch:{ Exception -> 0x0050 }
            d = r2     // Catch:{ Exception -> 0x0050 }
            java.lang.String r2 = android.util.Base64.encodeToString(r2, r6)     // Catch:{ Exception -> 0x0050 }
            r1.a(r2)     // Catch:{ Exception -> 0x0050 }
        L_0x0050:
            byte[] r1 = d     // Catch:{ all -> 0x0054 }
            monitor-exit(r0)
            return r1
        L_0x0054:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.commons.core.utilities.a.b.a():byte[]");
    }

    public static byte[] a(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, b);
        try {
            Cipher instance = Cipher.getInstance(c);
            instance.init(2, secretKeySpec, new IvParameterSpec(bArr3));
            return instance.doFinal(bArr);
        } catch (Throwable th) {
            new StringBuilder("SDK encountered unexpected error in decrypting AES response; ").append(th.getMessage());
            return null;
        }
    }

    public static byte[] a(int i) {
        byte[] bArr = new byte[i];
        try {
            new SecureRandom().nextBytes(bArr);
        } catch (Exception e) {
            new StringBuilder("SDK encountered unexpected error in generating crypto key; ").append(e.getMessage());
        }
        return bArr;
    }
}
