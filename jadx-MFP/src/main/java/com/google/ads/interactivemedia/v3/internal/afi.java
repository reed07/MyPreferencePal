package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.util.DisplayMetrics;
import com.google.android.exoplayer2.extractor.ts.PsExtractor;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.UUID;

/* compiled from: IMASDK */
public abstract class afi {
    protected DisplayMetrics a;
    protected afo b;
    private afp c;

    public String a(Context context) {
        return a(context, null, false);
    }

    /* access modifiers changed from: protected */
    public abstract void b(Context context);

    /* access modifiers changed from: protected */
    public abstract void c(Context context);

    protected afi(Context context, afo afo, afp afp) {
        this.b = afo;
        this.c = afp;
        try {
            this.a = context.getResources().getDisplayMetrics();
        } catch (UnsupportedOperationException unused) {
            this.a = new DisplayMetrics();
            this.a.density = 1.0f;
        }
    }

    private String a(Context context, String str, boolean z) {
        byte[] b2;
        try {
            synchronized (this) {
                a();
                if (z) {
                    c(context);
                } else {
                    b(context);
                }
                b2 = b();
            }
            if (b2.length == 0) {
                return Integer.toString(5);
            }
            return a(b2, str);
        } catch (NoSuchAlgorithmException unused) {
            return Integer.toString(7);
        } catch (UnsupportedEncodingException unused2) {
            return Integer.toString(7);
        } catch (IOException unused3) {
            return Integer.toString(3);
        }
    }

    /* access modifiers changed from: protected */
    public void a(int i, long j) throws IOException {
        this.c.a(i, j);
    }

    /* access modifiers changed from: protected */
    public void a(int i, String str) throws IOException {
        this.c.a(i, str);
    }

    private void a() {
        this.c.a();
    }

    private byte[] b() throws IOException {
        return this.c.b();
    }

    /* access modifiers changed from: 0000 */
    public String a(byte[] bArr, String str) throws NoSuchAlgorithmException, UnsupportedEncodingException, IOException {
        byte[] bArr2;
        if (bArr.length > 239) {
            a();
            a(20, 1);
            bArr = b();
        }
        if (bArr.length < 239) {
            byte[] bArr3 = new byte[(239 - bArr.length)];
            new SecureRandom().nextBytes(bArr3);
            bArr2 = ByteBuffer.allocate(PsExtractor.VIDEO_STREAM_MASK).put((byte) bArr.length).put(bArr).put(bArr3).array();
        } else {
            bArr2 = ByteBuffer.allocate(PsExtractor.VIDEO_STREAM_MASK).put((byte) bArr.length).put(bArr).array();
        }
        MessageDigest instance = MessageDigest.getInstance("MD5");
        instance.update(bArr2);
        byte[] array = ByteBuffer.allocate(256).put(instance.digest()).put(bArr2).array();
        byte[] bArr4 = new byte[256];
        for (aev a2 : new aeu().cN) {
            a2.a(array, bArr4);
        }
        if (str != null && str.length() > 0) {
            a(str, bArr4);
        }
        return this.b.a(bArr4, true);
    }

    static void a(String str, byte[] bArr) throws UnsupportedEncodingException {
        if (str.length() > 32) {
            str = str.substring(0, 32);
        }
        new ahb(str.getBytes("UTF-8")).a(bArr);
    }

    /* access modifiers changed from: protected */
    public String a(String str) {
        if (str == null || !str.matches("^[a-fA-F0-9]{8}-([a-fA-F0-9]{4}-){3}[a-fA-F0-9]{12}$")) {
            return str;
        }
        UUID fromString = UUID.fromString(str);
        byte[] bArr = new byte[16];
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.putLong(fromString.getMostSignificantBits());
        wrap.putLong(fromString.getLeastSignificantBits());
        return this.b.a(bArr, true);
    }
}
