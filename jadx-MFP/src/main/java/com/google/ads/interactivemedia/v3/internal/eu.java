package com.google.ads.interactivemedia.v3.internal;

import android.media.MediaCodec.CryptoInfo;

/* compiled from: IMASDK */
public final class eu {
    public byte[] a;
    public int[] b;
    public int[] c;
    private final CryptoInfo d = new CryptoInfo();
    private final ev e;

    public eu() {
        this.e = vf.a >= 24 ? new ev(this.d, 0) : null;
    }

    public final void a(int i, int[] iArr, int[] iArr2, byte[] bArr, byte[] bArr2, int i2, int i3, int i4) {
        this.b = iArr;
        this.c = iArr2;
        this.a = bArr2;
        CryptoInfo cryptoInfo = this.d;
        cryptoInfo.numSubSamples = i;
        cryptoInfo.numBytesOfClearData = iArr;
        cryptoInfo.numBytesOfEncryptedData = iArr2;
        cryptoInfo.key = bArr;
        cryptoInfo.iv = bArr2;
        cryptoInfo.mode = i2;
        if (vf.a >= 24) {
            this.e.a(i3, i4);
        }
    }

    public final CryptoInfo a() {
        return this.d;
    }
}
