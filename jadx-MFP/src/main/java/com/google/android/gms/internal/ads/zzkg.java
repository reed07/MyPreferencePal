package com.google.android.gms.internal.ads;

import android.media.MediaCodecInfo;
import android.media.MediaCodecInfo.CodecCapabilities;
import android.media.MediaCodecList;
import com.google.android.exoplayer2.util.MimeTypes;

final class zzkg implements zzkf {
    private zzkg() {
    }

    public final boolean zzes() {
        return false;
    }

    public final int getCodecCount() {
        return MediaCodecList.getCodecCount();
    }

    public final MediaCodecInfo getCodecInfoAt(int i) {
        return MediaCodecList.getCodecInfoAt(i);
    }

    public final boolean zza(String str, CodecCapabilities codecCapabilities) {
        return MimeTypes.VIDEO_H264.equals(str);
    }
}
