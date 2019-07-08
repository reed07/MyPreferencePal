package com.google.ads.interactivemedia.v3.internal;

import android.annotation.TargetApi;
import android.media.MediaCodec.CryptoInfo;
import android.media.MediaCodec.CryptoInfo.Pattern;

@TargetApi(24)
/* compiled from: IMASDK */
final class ev {
    private final CryptoInfo a;
    private final Pattern b;

    private ev(CryptoInfo cryptoInfo) {
        this.a = cryptoInfo;
        this.b = new Pattern(0, 0);
    }

    /* access modifiers changed from: private */
    public final void a(int i, int i2) {
        this.b.set(i, i2);
        this.a.setPattern(this.b);
    }

    /* synthetic */ ev(CryptoInfo cryptoInfo, byte b2) {
        this(cryptoInfo);
    }
}
