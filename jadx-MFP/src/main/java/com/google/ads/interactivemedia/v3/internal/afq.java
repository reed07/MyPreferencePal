package com.google.ads.interactivemedia.v3.internal;

import java.nio.ByteBuffer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* compiled from: IMASDK */
public final class afq {
    private final afo a;

    public afq(afo afo, SecureRandom secureRandom) {
        this.a = afo;
    }

    public final byte[] a(String str) throws afr {
        try {
            byte[] a2 = this.a.a(str, false);
            if (a2.length == 32) {
                byte[] bArr = new byte[16];
                ByteBuffer.wrap(a2, 4, 16).get(bArr);
                for (int i = 0; i < bArr.length; i++) {
                    bArr[i] = (byte) (bArr[i] ^ 68);
                }
                return bArr;
            }
            throw new afr(this);
        } catch (IllegalArgumentException e) {
            throw new afr(this, e);
        }
    }

    public final byte[] a(byte[] bArr, String str) throws afr {
        if (bArr.length == 16) {
            try {
                byte[] a2 = this.a.a(str, false);
                if (a2.length > 16) {
                    ByteBuffer allocate = ByteBuffer.allocate(a2.length);
                    allocate.put(a2);
                    allocate.flip();
                    byte[] bArr2 = new byte[16];
                    byte[] bArr3 = new byte[(a2.length - 16)];
                    allocate.get(bArr2);
                    allocate.get(bArr3);
                    SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "AES");
                    Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
                    instance.init(2, secretKeySpec, new IvParameterSpec(bArr2));
                    return instance.doFinal(bArr3);
                }
                throw new afr(this);
            } catch (NoSuchAlgorithmException e) {
                throw new afr(this, e);
            } catch (InvalidKeyException e2) {
                throw new afr(this, e2);
            } catch (IllegalBlockSizeException e3) {
                throw new afr(this, e3);
            } catch (NoSuchPaddingException e4) {
                throw new afr(this, e4);
            } catch (BadPaddingException e5) {
                throw new afr(this, e5);
            } catch (InvalidAlgorithmParameterException e6) {
                throw new afr(this, e6);
            } catch (IllegalArgumentException e7) {
                throw new afr(this, e7);
            }
        } else {
            throw new afr(this);
        }
    }
}
