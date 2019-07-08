package com.google.ads.interactivemedia.v3.internal;

import android.media.MediaCodecInfo;
import android.media.MediaCodecInfo.CodecCapabilities;
import android.media.MediaCodecList;
import com.google.android.exoplayer2.util.MimeTypes;

/* compiled from: IMASDK */
class jo implements jn {
    private jo() {
    }

    public final boolean b() {
        return false;
    }

    public final int a() {
        return MediaCodecList.getCodecCount();
    }

    public final MediaCodecInfo a(int i) {
        return MediaCodecList.getCodecInfoAt(i);
    }

    public final boolean a(String str, CodecCapabilities codecCapabilities) {
        return MimeTypes.VIDEO_H264.equals(str);
    }

    /* synthetic */ jo(byte b) {
        this();
    }
}
