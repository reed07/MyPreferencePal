package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaCodec.OnFrameRenderedListener;
import android.os.Handler;
import android.support.annotation.NonNull;

@TargetApi(23)
final class zzqr implements OnFrameRenderedListener {
    private final /* synthetic */ zzqo zzbjx;

    private zzqr(zzqo zzqo, MediaCodec mediaCodec) {
        this.zzbjx = zzqo;
        mediaCodec.setOnFrameRenderedListener(this, new Handler());
    }

    public final void onFrameRendered(@NonNull MediaCodec mediaCodec, long j, long j2) {
        if (this == this.zzbjx.zzbjt) {
            this.zzbjx.zzhq();
        }
    }
}
