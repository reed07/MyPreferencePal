package com.google.ads.interactivemedia.v3.internal;

import android.net.Uri;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* compiled from: IMASDK */
final class pk implements sn {
    private final sn a;
    private final byte[] b;
    private final byte[] c;
    private CipherInputStream d;

    public pk(sn snVar, byte[] bArr, byte[] bArr2) {
        this.a = snVar;
        this.b = bArr;
        this.c = bArr2;
    }

    public final void a(tz tzVar) {
        this.a.a(tzVar);
    }

    public final long a(sr srVar) throws IOException {
        try {
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS7Padding");
            try {
                instance.init(2, new SecretKeySpec(this.b, "AES"), new IvParameterSpec(this.c));
                sq sqVar = new sq(this.a, srVar);
                this.d = new CipherInputStream(sqVar, instance);
                sqVar.a();
                return -1;
            } catch (InvalidAlgorithmParameterException | InvalidKeyException e) {
                throw new RuntimeException(e);
            }
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e2) {
            throw new RuntimeException(e2);
        }
    }

    public final int a(byte[] bArr, int i, int i2) throws IOException {
        qi.a(this.d);
        int read = this.d.read(bArr, i, i2);
        if (read < 0) {
            return -1;
        }
        return read;
    }

    public final Uri a() {
        return this.a.a();
    }

    public final Map<String, List<String>> b() {
        return this.a.b();
    }

    public final void c() throws IOException {
        if (this.d != null) {
            this.d = null;
            this.a.c();
        }
    }
}
