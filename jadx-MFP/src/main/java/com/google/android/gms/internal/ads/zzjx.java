package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.graphics.Point;
import android.media.MediaCodecInfo.AudioCapabilities;
import android.media.MediaCodecInfo.CodecCapabilities;
import android.media.MediaCodecInfo.CodecProfileLevel;
import android.media.MediaCodecInfo.VideoCapabilities;
import android.util.Log;
import android.util.Pair;
import com.google.android.exoplayer2.mediacodec.MediaCodecInfo;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;

@TargetApi(16)
public final class zzjx {
    private final String mimeType;
    public final String name;
    public final boolean zzadt;
    public final boolean zzatq;
    public final boolean zzatr;
    private final CodecCapabilities zzats;

    public static zzjx zzt(String str) {
        zzjx zzjx = new zzjx(str, null, null, false, false);
        return zzjx;
    }

    public static zzjx zza(String str, String str2, CodecCapabilities codecCapabilities, boolean z, boolean z2) {
        zzjx zzjx = new zzjx(str, str2, codecCapabilities, z, z2);
        return zzjx;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:32:0x005b, code lost:
        if ((com.google.android.gms.internal.ads.zzqe.SDK_INT >= 21 && r4.isFeatureSupported("secure-playback")) != false) goto L_0x005f;
     */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0031  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x004a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private zzjx(java.lang.String r2, java.lang.String r3, android.media.MediaCodecInfo.CodecCapabilities r4, boolean r5, boolean r6) {
        /*
            r1 = this;
            r1.<init>()
            java.lang.Object r2 = com.google.android.gms.internal.ads.zzpo.checkNotNull(r2)
            java.lang.String r2 = (java.lang.String) r2
            r1.name = r2
            r1.mimeType = r3
            r1.zzats = r4
            r2 = 1
            r3 = 0
            if (r5 != 0) goto L_0x002a
            if (r4 == 0) goto L_0x002a
            int r5 = com.google.android.gms.internal.ads.zzqe.SDK_INT
            r0 = 19
            if (r5 < r0) goto L_0x0025
            java.lang.String r5 = "adaptive-playback"
            boolean r5 = r4.isFeatureSupported(r5)
            if (r5 == 0) goto L_0x0025
            r5 = 1
            goto L_0x0026
        L_0x0025:
            r5 = 0
        L_0x0026:
            if (r5 == 0) goto L_0x002a
            r5 = 1
            goto L_0x002b
        L_0x002a:
            r5 = 0
        L_0x002b:
            r1.zzatq = r5
            r5 = 21
            if (r4 == 0) goto L_0x0045
            int r0 = com.google.android.gms.internal.ads.zzqe.SDK_INT
            if (r0 < r5) goto L_0x0040
            java.lang.String r0 = "tunneled-playback"
            boolean r0 = r4.isFeatureSupported(r0)
            if (r0 == 0) goto L_0x0040
            r0 = 1
            goto L_0x0041
        L_0x0040:
            r0 = 0
        L_0x0041:
            if (r0 == 0) goto L_0x0045
            r0 = 1
            goto L_0x0046
        L_0x0045:
            r0 = 0
        L_0x0046:
            r1.zzadt = r0
            if (r6 != 0) goto L_0x005f
            if (r4 == 0) goto L_0x005e
            int r6 = com.google.android.gms.internal.ads.zzqe.SDK_INT
            if (r6 < r5) goto L_0x005a
            java.lang.String r5 = "secure-playback"
            boolean r4 = r4.isFeatureSupported(r5)
            if (r4 == 0) goto L_0x005a
            r4 = 1
            goto L_0x005b
        L_0x005a:
            r4 = 0
        L_0x005b:
            if (r4 == 0) goto L_0x005e
            goto L_0x005f
        L_0x005e:
            r2 = 0
        L_0x005f:
            r1.zzatr = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzjx.<init>(java.lang.String, java.lang.String, android.media.MediaCodecInfo$CodecCapabilities, boolean, boolean):void");
    }

    public final CodecProfileLevel[] zzej() {
        CodecCapabilities codecCapabilities = this.zzats;
        if (codecCapabilities == null || codecCapabilities.profileLevels == null) {
            return new CodecProfileLevel[0];
        }
        return this.zzats.profileLevels;
    }

    public final boolean zzu(String str) {
        CodecProfileLevel[] zzej;
        if (str == null || this.mimeType == null) {
            return true;
        }
        String zzae = zzpt.zzae(str);
        if (zzae == null) {
            return true;
        }
        if (!this.mimeType.equals(zzae)) {
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 13 + String.valueOf(zzae).length());
            sb.append("codec.mime ");
            sb.append(str);
            sb.append(", ");
            sb.append(zzae);
            zzv(sb.toString());
            return false;
        }
        Pair zzw = zzkc.zzw(str);
        if (zzw == null) {
            return true;
        }
        for (CodecProfileLevel codecProfileLevel : zzej()) {
            if (codecProfileLevel.profile == ((Integer) zzw.first).intValue() && codecProfileLevel.level >= ((Integer) zzw.second).intValue()) {
                return true;
            }
        }
        StringBuilder sb2 = new StringBuilder(String.valueOf(str).length() + 22 + String.valueOf(zzae).length());
        sb2.append("codec.profileLevel, ");
        sb2.append(str);
        sb2.append(", ");
        sb2.append(zzae);
        zzv(sb2.toString());
        return false;
    }

    @TargetApi(21)
    public final boolean zza(int i, int i2, double d) {
        CodecCapabilities codecCapabilities = this.zzats;
        if (codecCapabilities == null) {
            zzv("sizeAndRate.caps");
            return false;
        }
        VideoCapabilities videoCapabilities = codecCapabilities.getVideoCapabilities();
        if (videoCapabilities == null) {
            zzv("sizeAndRate.vCaps");
            return false;
        }
        if (!zza(videoCapabilities, i, i2, d)) {
            if (i >= i2 || !zza(videoCapabilities, i2, i, d)) {
                StringBuilder sb = new StringBuilder(69);
                sb.append("sizeAndRate.support, ");
                sb.append(i);
                sb.append(AvidJSONUtil.KEY_X);
                sb.append(i2);
                sb.append(AvidJSONUtil.KEY_X);
                sb.append(d);
                zzv(sb.toString());
                return false;
            }
            StringBuilder sb2 = new StringBuilder(69);
            sb2.append("sizeAndRate.rotated, ");
            sb2.append(i);
            sb2.append(AvidJSONUtil.KEY_X);
            sb2.append(i2);
            sb2.append(AvidJSONUtil.KEY_X);
            sb2.append(d);
            String sb3 = sb2.toString();
            String str = MediaCodecInfo.TAG;
            String str2 = this.name;
            String str3 = this.mimeType;
            String str4 = zzqe.zzbic;
            StringBuilder sb4 = new StringBuilder(String.valueOf(sb3).length() + 25 + String.valueOf(str2).length() + String.valueOf(str3).length() + String.valueOf(str4).length());
            sb4.append("AssumedSupport [");
            sb4.append(sb3);
            sb4.append("] [");
            sb4.append(str2);
            sb4.append(", ");
            sb4.append(str3);
            sb4.append("] [");
            sb4.append(str4);
            sb4.append("]");
            Log.d(str, sb4.toString());
        }
        return true;
    }

    @TargetApi(21)
    public final Point zzc(int i, int i2) {
        CodecCapabilities codecCapabilities = this.zzats;
        if (codecCapabilities == null) {
            zzv("align.caps");
            return null;
        }
        VideoCapabilities videoCapabilities = codecCapabilities.getVideoCapabilities();
        if (videoCapabilities == null) {
            zzv("align.vCaps");
            return null;
        }
        int widthAlignment = videoCapabilities.getWidthAlignment();
        int heightAlignment = videoCapabilities.getHeightAlignment();
        return new Point(zzqe.zzf(i, widthAlignment) * widthAlignment, zzqe.zzf(i2, heightAlignment) * heightAlignment);
    }

    @TargetApi(21)
    public final boolean zzam(int i) {
        CodecCapabilities codecCapabilities = this.zzats;
        if (codecCapabilities == null) {
            zzv("sampleRate.caps");
            return false;
        }
        AudioCapabilities audioCapabilities = codecCapabilities.getAudioCapabilities();
        if (audioCapabilities == null) {
            zzv("sampleRate.aCaps");
            return false;
        } else if (audioCapabilities.isSampleRateSupported(i)) {
            return true;
        } else {
            StringBuilder sb = new StringBuilder(31);
            sb.append("sampleRate.support, ");
            sb.append(i);
            zzv(sb.toString());
            return false;
        }
    }

    @TargetApi(21)
    public final boolean zzan(int i) {
        CodecCapabilities codecCapabilities = this.zzats;
        if (codecCapabilities == null) {
            zzv("channelCount.caps");
            return false;
        }
        AudioCapabilities audioCapabilities = codecCapabilities.getAudioCapabilities();
        if (audioCapabilities == null) {
            zzv("channelCount.aCaps");
            return false;
        } else if (audioCapabilities.getMaxInputChannelCount() >= i) {
            return true;
        } else {
            StringBuilder sb = new StringBuilder(33);
            sb.append("channelCount.support, ");
            sb.append(i);
            zzv(sb.toString());
            return false;
        }
    }

    private final void zzv(String str) {
        String str2 = MediaCodecInfo.TAG;
        String str3 = this.name;
        String str4 = this.mimeType;
        String str5 = zzqe.zzbic;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 20 + String.valueOf(str3).length() + String.valueOf(str4).length() + String.valueOf(str5).length());
        sb.append("NoSupport [");
        sb.append(str);
        sb.append("] [");
        sb.append(str3);
        sb.append(", ");
        sb.append(str4);
        sb.append("] [");
        sb.append(str5);
        sb.append("]");
        Log.d(str2, sb.toString());
    }

    @TargetApi(21)
    private static boolean zza(VideoCapabilities videoCapabilities, int i, int i2, double d) {
        if (d == -1.0d || d <= 0.0d) {
            return videoCapabilities.isSizeSupported(i, i2);
        }
        return videoCapabilities.areSizeAndRateSupported(i, i2, d);
    }
}
