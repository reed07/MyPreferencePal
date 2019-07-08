package com.google.android.gms.internal.ads;

import android.media.MediaCodec.CodecException;

public final class zzjz extends Exception {
    private final String mimeType;
    private final boolean zzave;
    private final String zzavf;
    private final String zzavg;

    public zzjz(zzfs zzfs, Throwable th, boolean z, int i) {
        String valueOf = String.valueOf(zzfs);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 36);
        sb.append("Decoder init failed: [");
        sb.append(i);
        sb.append("], ");
        sb.append(valueOf);
        super(sb.toString(), th);
        this.mimeType = zzfs.zzzj;
        this.zzave = false;
        this.zzavf = null;
        String str = i < 0 ? "neg_" : "";
        int abs = Math.abs(i);
        StringBuilder sb2 = new StringBuilder(String.valueOf(str).length() + 64);
        sb2.append("com.google.android.exoplayer.MediaCodecTrackRenderer_");
        sb2.append(str);
        sb2.append(abs);
        this.zzavg = sb2.toString();
    }

    public zzjz(zzfs zzfs, Throwable th, boolean z, String str) {
        String valueOf = String.valueOf(zzfs);
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 23 + String.valueOf(valueOf).length());
        sb.append("Decoder init failed: ");
        sb.append(str);
        sb.append(", ");
        sb.append(valueOf);
        super(sb.toString(), th);
        this.mimeType = zzfs.zzzj;
        this.zzave = false;
        this.zzavf = str;
        String str2 = null;
        if (zzqe.SDK_INT >= 21 && (th instanceof CodecException)) {
            str2 = ((CodecException) th).getDiagnosticInfo();
        }
        this.zzavg = str2;
    }
}
