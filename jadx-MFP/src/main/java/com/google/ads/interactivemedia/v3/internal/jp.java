package com.google.ads.interactivemedia.v3.internal;

import android.annotation.TargetApi;
import android.media.MediaCodecInfo;
import android.media.MediaCodecInfo.CodecCapabilities;
import android.media.MediaCodecList;

@TargetApi(21)
/* compiled from: IMASDK */
final class jp implements jn {
    private final int a;
    private MediaCodecInfo[] b;

    public jp(boolean z) {
        this.a = z ? 1 : 0;
    }

    public final boolean b() {
        return true;
    }

    public final int a() {
        c();
        return this.b.length;
    }

    public final MediaCodecInfo a(int i) {
        c();
        return this.b[i];
    }

    public final boolean a(String str, CodecCapabilities codecCapabilities) {
        return codecCapabilities.isFeatureSupported("secure-playback");
    }

    private final void c() {
        if (this.b == null) {
            this.b = new MediaCodecList(this.a).getCodecInfos();
        }
    }
}
