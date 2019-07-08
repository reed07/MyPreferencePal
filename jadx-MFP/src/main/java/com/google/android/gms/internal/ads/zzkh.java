package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.media.MediaCodecInfo;
import android.media.MediaCodecInfo.CodecCapabilities;
import android.media.MediaCodecList;

@TargetApi(21)
final class zzkh implements zzkf {
    private final int zzavp;
    private MediaCodecInfo[] zzavq;

    public zzkh(boolean z) {
        this.zzavp = z ? 1 : 0;
    }

    public final boolean zzes() {
        return true;
    }

    public final int getCodecCount() {
        zzet();
        return this.zzavq.length;
    }

    public final MediaCodecInfo getCodecInfoAt(int i) {
        zzet();
        return this.zzavq[i];
    }

    public final boolean zza(String str, CodecCapabilities codecCapabilities) {
        return codecCapabilities.isFeatureSupported("secure-playback");
    }

    private final void zzet() {
        if (this.zzavq == null) {
            this.zzavq = new MediaCodecList(this.zzavp).getCodecInfos();
        }
    }
}
