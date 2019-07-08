package com.google.ads.interactivemedia.v3.internal;

import android.annotation.TargetApi;
import android.graphics.Point;
import android.media.MediaCodecInfo.CodecCapabilities;
import android.media.MediaCodecInfo.CodecProfileLevel;
import android.media.MediaCodecInfo.VideoCapabilities;
import android.util.Log;
import android.util.Pair;
import com.google.android.exoplayer2.mediacodec.MediaCodecInfo;
import com.google.android.exoplayer2.util.MimeTypes;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;

/* compiled from: IMASDK */
public final class jf {
    public final String a;
    public final String b;
    public final boolean c;
    public final boolean d;
    public final boolean e;
    private final CodecCapabilities f;
    private final boolean g;
    private final boolean h;

    public static jf a(String str) {
        jf jfVar = new jf(str, null, null, true, false, false);
        return jfVar;
    }

    public static jf a(String str, String str2, CodecCapabilities codecCapabilities, boolean z, boolean z2) {
        jf jfVar = new jf(str, str2, codecCapabilities, false, z, z2);
        return jfVar;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:32:0x005c, code lost:
        if ((com.google.ads.interactivemedia.v3.internal.vf.a >= 21 && r4.isFeatureSupported("secure-playback")) != false) goto L_0x0060;
     */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0033  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x004b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private jf(java.lang.String r2, java.lang.String r3, android.media.MediaCodecInfo.CodecCapabilities r4, boolean r5, boolean r6, boolean r7) {
        /*
            r1 = this;
            r1.<init>()
            java.lang.Object r2 = com.google.ads.interactivemedia.v3.internal.qi.a(r2)
            java.lang.String r2 = (java.lang.String) r2
            r1.a = r2
            r1.b = r3
            r1.f = r4
            r1.e = r5
            r2 = 1
            r5 = 0
            if (r6 != 0) goto L_0x002c
            if (r4 == 0) goto L_0x002c
            int r6 = com.google.ads.interactivemedia.v3.internal.vf.a
            r0 = 19
            if (r6 < r0) goto L_0x0027
            java.lang.String r6 = "adaptive-playback"
            boolean r6 = r4.isFeatureSupported(r6)
            if (r6 == 0) goto L_0x0027
            r6 = 1
            goto L_0x0028
        L_0x0027:
            r6 = 0
        L_0x0028:
            if (r6 == 0) goto L_0x002c
            r6 = 1
            goto L_0x002d
        L_0x002c:
            r6 = 0
        L_0x002d:
            r1.g = r6
            r6 = 21
            if (r4 == 0) goto L_0x0046
            int r0 = com.google.ads.interactivemedia.v3.internal.vf.a
            if (r0 < r6) goto L_0x0041
            java.lang.String r0 = "tunneled-playback"
            boolean r0 = r4.isFeatureSupported(r0)
            if (r0 == 0) goto L_0x0041
            r0 = 1
            goto L_0x0042
        L_0x0041:
            r0 = 0
        L_0x0042:
            if (r0 == 0) goto L_0x0046
            r0 = 1
            goto L_0x0047
        L_0x0046:
            r0 = 0
        L_0x0047:
            r1.c = r0
            if (r7 != 0) goto L_0x0060
            if (r4 == 0) goto L_0x005f
            int r7 = com.google.ads.interactivemedia.v3.internal.vf.a
            if (r7 < r6) goto L_0x005b
            java.lang.String r6 = "secure-playback"
            boolean r4 = r4.isFeatureSupported(r6)
            if (r4 == 0) goto L_0x005b
            r4 = 1
            goto L_0x005c
        L_0x005b:
            r4 = 0
        L_0x005c:
            if (r4 == 0) goto L_0x005f
            goto L_0x0060
        L_0x005f:
            r2 = 0
        L_0x0060:
            r1.d = r2
            boolean r2 = com.google.ads.interactivemedia.v3.internal.un.b(r3)
            r1.h = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.jf.<init>(java.lang.String, java.lang.String, android.media.MediaCodecInfo$CodecCapabilities, boolean, boolean, boolean):void");
    }

    public final String toString() {
        return this.a;
    }

    public final CodecProfileLevel[] a() {
        CodecCapabilities codecCapabilities = this.f;
        if (codecCapabilities == null || codecCapabilities.profileLevels == null) {
            return new CodecProfileLevel[0];
        }
        return this.f.profileLevels;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0150, code lost:
        if (r0 != false) goto L_0x0152;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean a(com.google.ads.interactivemedia.v3.internal.bs r12) throws com.google.ads.interactivemedia.v3.internal.jm {
        /*
            r11 = this;
            java.lang.String r0 = r12.e
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L_0x00b8
            java.lang.String r3 = r11.b
            if (r3 != 0) goto L_0x000c
            goto L_0x00b8
        L_0x000c:
            java.lang.String r3 = com.google.ads.interactivemedia.v3.internal.un.f(r0)
            if (r3 != 0) goto L_0x0015
            r0 = 1
            goto L_0x00b9
        L_0x0015:
            java.lang.String r4 = r11.b
            boolean r4 = r4.equals(r3)
            if (r4 != 0) goto L_0x004e
            java.lang.String r4 = java.lang.String.valueOf(r0)
            int r4 = r4.length()
            int r4 = r4 + 13
            java.lang.String r5 = java.lang.String.valueOf(r3)
            int r5 = r5.length()
            int r4 = r4 + r5
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>(r4)
            java.lang.String r4 = "codec.mime "
            r5.append(r4)
            r5.append(r0)
            java.lang.String r0 = ", "
            r5.append(r0)
            r5.append(r3)
            java.lang.String r0 = r5.toString()
            r11.b(r0)
            r0 = 0
            goto L_0x00b9
        L_0x004e:
            android.util.Pair r4 = com.google.ads.interactivemedia.v3.internal.jl.a(r0)
            if (r4 != 0) goto L_0x0056
            r0 = 1
            goto L_0x00b9
        L_0x0056:
            java.lang.Object r5 = r4.first
            java.lang.Integer r5 = (java.lang.Integer) r5
            int r5 = r5.intValue()
            java.lang.Object r4 = r4.second
            java.lang.Integer r4 = (java.lang.Integer) r4
            int r4 = r4.intValue()
            boolean r6 = r11.h
            if (r6 != 0) goto L_0x0070
            r6 = 42
            if (r5 == r6) goto L_0x0070
            r0 = 1
            goto L_0x00b9
        L_0x0070:
            android.media.MediaCodecInfo$CodecProfileLevel[] r6 = r11.a()
            int r7 = r6.length
            r8 = 0
        L_0x0076:
            if (r8 >= r7) goto L_0x0087
            r9 = r6[r8]
            int r10 = r9.profile
            if (r10 != r5) goto L_0x0084
            int r9 = r9.level
            if (r9 < r4) goto L_0x0084
            r0 = 1
            goto L_0x00b9
        L_0x0084:
            int r8 = r8 + 1
            goto L_0x0076
        L_0x0087:
            java.lang.String r4 = java.lang.String.valueOf(r0)
            int r4 = r4.length()
            int r4 = r4 + 22
            java.lang.String r5 = java.lang.String.valueOf(r3)
            int r5 = r5.length()
            int r4 = r4 + r5
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>(r4)
            java.lang.String r4 = "codec.profileLevel, "
            r5.append(r4)
            r5.append(r0)
            java.lang.String r0 = ", "
            r5.append(r0)
            r5.append(r3)
            java.lang.String r0 = r5.toString()
            r11.b(r0)
            r0 = 0
            goto L_0x00b9
        L_0x00b8:
            r0 = 1
        L_0x00b9:
            if (r0 != 0) goto L_0x00bc
            return r1
        L_0x00bc:
            boolean r0 = r11.h
            r3 = 21
            if (r0 == 0) goto L_0x010e
            int r0 = r12.m
            if (r0 <= 0) goto L_0x010d
            int r0 = r12.n
            if (r0 > 0) goto L_0x00cb
            goto L_0x010d
        L_0x00cb:
            int r0 = com.google.ads.interactivemedia.v3.internal.vf.a
            if (r0 < r3) goto L_0x00db
            int r0 = r12.m
            int r1 = r12.n
            float r12 = r12.o
            double r2 = (double) r12
            boolean r12 = r11.a(r0, r1, r2)
            return r12
        L_0x00db:
            int r0 = r12.m
            int r3 = r12.n
            int r0 = r0 * r3
            int r3 = com.google.ads.interactivemedia.v3.internal.jl.b()
            if (r0 > r3) goto L_0x00e8
            r1 = 1
        L_0x00e8:
            if (r1 != 0) goto L_0x010c
            int r0 = r12.m
            int r12 = r12.n
            r2 = 40
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r2)
            java.lang.String r2 = "legacyFrameSize, "
            r3.append(r2)
            r3.append(r0)
            java.lang.String r0 = "x"
            r3.append(r0)
            r3.append(r12)
            java.lang.String r12 = r3.toString()
            r11.b(r12)
        L_0x010c:
            return r1
        L_0x010d:
            return r2
        L_0x010e:
            int r0 = com.google.ads.interactivemedia.v3.internal.vf.a
            if (r0 < r3) goto L_0x024b
            int r0 = r12.t
            r3 = -1
            if (r0 == r3) goto L_0x0152
            int r0 = r12.t
            android.media.MediaCodecInfo$CodecCapabilities r4 = r11.f
            if (r4 != 0) goto L_0x0124
            java.lang.String r0 = "sampleRate.caps"
            r11.b(r0)
            r0 = 0
            goto L_0x0150
        L_0x0124:
            android.media.MediaCodecInfo$AudioCapabilities r4 = r4.getAudioCapabilities()
            if (r4 != 0) goto L_0x0131
            java.lang.String r0 = "sampleRate.aCaps"
            r11.b(r0)
            r0 = 0
            goto L_0x0150
        L_0x0131:
            boolean r4 = r4.isSampleRateSupported(r0)
            if (r4 != 0) goto L_0x014f
            r4 = 31
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>(r4)
            java.lang.String r4 = "sampleRate.support, "
            r5.append(r4)
            r5.append(r0)
            java.lang.String r0 = r5.toString()
            r11.b(r0)
            r0 = 0
            goto L_0x0150
        L_0x014f:
            r0 = 1
        L_0x0150:
            if (r0 == 0) goto L_0x024a
        L_0x0152:
            int r0 = r12.s
            if (r0 == r3) goto L_0x024b
            int r12 = r12.s
            android.media.MediaCodecInfo$CodecCapabilities r0 = r11.f
            if (r0 != 0) goto L_0x0164
            java.lang.String r12 = "channelCount.caps"
            r11.b(r12)
            r12 = 0
            goto L_0x0247
        L_0x0164:
            android.media.MediaCodecInfo$AudioCapabilities r0 = r0.getAudioCapabilities()
            if (r0 != 0) goto L_0x0172
            java.lang.String r12 = "channelCount.aCaps"
            r11.b(r12)
            r12 = 0
            goto L_0x0247
        L_0x0172:
            java.lang.String r3 = r11.a
            java.lang.String r4 = r11.b
            int r0 = r0.getMaxInputChannelCount()
            if (r0 > r2) goto L_0x022c
            int r5 = com.google.ads.interactivemedia.v3.internal.vf.a
            r6 = 26
            if (r5 < r6) goto L_0x0186
            if (r0 <= 0) goto L_0x0186
            goto L_0x022c
        L_0x0186:
            java.lang.String r5 = "audio/mpeg"
            boolean r5 = r5.equals(r4)
            if (r5 != 0) goto L_0x022c
            java.lang.String r5 = "audio/3gpp"
            boolean r5 = r5.equals(r4)
            if (r5 != 0) goto L_0x022c
            java.lang.String r5 = "audio/amr-wb"
            boolean r5 = r5.equals(r4)
            if (r5 != 0) goto L_0x022c
            java.lang.String r5 = "audio/mp4a-latm"
            boolean r5 = r5.equals(r4)
            if (r5 != 0) goto L_0x022c
            java.lang.String r5 = "audio/vorbis"
            boolean r5 = r5.equals(r4)
            if (r5 != 0) goto L_0x022c
            java.lang.String r5 = "audio/opus"
            boolean r5 = r5.equals(r4)
            if (r5 != 0) goto L_0x022c
            java.lang.String r5 = "audio/raw"
            boolean r5 = r5.equals(r4)
            if (r5 != 0) goto L_0x022c
            java.lang.String r5 = "audio/flac"
            boolean r5 = r5.equals(r4)
            if (r5 != 0) goto L_0x022c
            java.lang.String r5 = "audio/g711-alaw"
            boolean r5 = r5.equals(r4)
            if (r5 != 0) goto L_0x022c
            java.lang.String r5 = "audio/g711-mlaw"
            boolean r5 = r5.equals(r4)
            if (r5 != 0) goto L_0x022c
            java.lang.String r5 = "audio/gsm"
            boolean r5 = r5.equals(r4)
            if (r5 == 0) goto L_0x01df
            goto L_0x022c
        L_0x01df:
            java.lang.String r5 = "audio/ac3"
            boolean r5 = r5.equals(r4)
            if (r5 == 0) goto L_0x01e9
            r4 = 6
            goto L_0x01f6
        L_0x01e9:
            java.lang.String r5 = "audio/eac3"
            boolean r4 = r5.equals(r4)
            if (r4 == 0) goto L_0x01f4
            r4 = 16
            goto L_0x01f6
        L_0x01f4:
            r4 = 30
        L_0x01f6:
            java.lang.String r5 = "MediaCodecInfo"
            java.lang.String r6 = java.lang.String.valueOf(r3)
            int r6 = r6.length()
            int r6 = r6 + 59
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>(r6)
            java.lang.String r6 = "AssumedMaxChannelAdjustment: "
            r7.append(r6)
            r7.append(r3)
            java.lang.String r3 = ", ["
            r7.append(r3)
            r7.append(r0)
            java.lang.String r0 = " to "
            r7.append(r0)
            r7.append(r4)
            java.lang.String r0 = "]"
            r7.append(r0)
            java.lang.String r0 = r7.toString()
            android.util.Log.w(r5, r0)
            r0 = r4
        L_0x022c:
            if (r0 >= r12) goto L_0x0246
            r0 = 33
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r0)
            java.lang.String r0 = "channelCount.support, "
            r3.append(r0)
            r3.append(r12)
            java.lang.String r12 = r3.toString()
            r11.b(r12)
            r12 = 0
            goto L_0x0247
        L_0x0246:
            r12 = 1
        L_0x0247:
            if (r12 == 0) goto L_0x024a
            goto L_0x024b
        L_0x024a:
            return r1
        L_0x024b:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.jf.a(com.google.ads.interactivemedia.v3.internal.bs):boolean");
    }

    public final boolean b(bs bsVar) {
        if (this.h) {
            return this.g;
        }
        Pair a2 = jl.a(bsVar.e);
        return a2 != null && ((Integer) a2.first).intValue() == 42;
    }

    public final boolean a(bs bsVar, bs bsVar2, boolean z) {
        if (this.h) {
            return bsVar.h.equals(bsVar2.h) && bsVar.p == bsVar2.p && (this.g || (bsVar.m == bsVar2.m && bsVar.n == bsVar2.n)) && ((!z && bsVar2.r == null) || vf.a((Object) bsVar.r, (Object) bsVar2.r));
        }
        if (!MimeTypes.AUDIO_AAC.equals(this.b) || !bsVar.h.equals(bsVar2.h) || bsVar.s != bsVar2.s || bsVar.t != bsVar2.t) {
            return false;
        }
        Pair a2 = jl.a(bsVar.e);
        Pair a3 = jl.a(bsVar2.e);
        if (a2 == null || a3 == null) {
            return false;
        }
        return ((Integer) a2.first).intValue() == 42 && ((Integer) a3.first).intValue() == 42;
    }

    @TargetApi(21)
    public final boolean a(int i, int i2, double d2) {
        CodecCapabilities codecCapabilities = this.f;
        if (codecCapabilities == null) {
            b("sizeAndRate.caps");
            return false;
        }
        VideoCapabilities videoCapabilities = codecCapabilities.getVideoCapabilities();
        if (videoCapabilities == null) {
            b("sizeAndRate.vCaps");
            return false;
        }
        if (!a(videoCapabilities, i, i2, d2)) {
            if (i >= i2 || !a(videoCapabilities, i2, i, d2)) {
                StringBuilder sb = new StringBuilder(69);
                sb.append("sizeAndRate.support, ");
                sb.append(i);
                sb.append(AvidJSONUtil.KEY_X);
                sb.append(i2);
                sb.append(AvidJSONUtil.KEY_X);
                sb.append(d2);
                b(sb.toString());
                return false;
            }
            StringBuilder sb2 = new StringBuilder(69);
            sb2.append("sizeAndRate.rotated, ");
            sb2.append(i);
            sb2.append(AvidJSONUtil.KEY_X);
            sb2.append(i2);
            sb2.append(AvidJSONUtil.KEY_X);
            sb2.append(d2);
            String sb3 = sb2.toString();
            String str = MediaCodecInfo.TAG;
            String str2 = this.a;
            String str3 = this.b;
            String str4 = vf.e;
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
    public final Point a(int i, int i2) {
        CodecCapabilities codecCapabilities = this.f;
        if (codecCapabilities == null) {
            b("align.caps");
            return null;
        }
        VideoCapabilities videoCapabilities = codecCapabilities.getVideoCapabilities();
        if (videoCapabilities == null) {
            b("align.vCaps");
            return null;
        }
        int widthAlignment = videoCapabilities.getWidthAlignment();
        int heightAlignment = videoCapabilities.getHeightAlignment();
        return new Point(vf.a(i, widthAlignment) * widthAlignment, vf.a(i2, heightAlignment) * heightAlignment);
    }

    private final void b(String str) {
        String str2 = MediaCodecInfo.TAG;
        String str3 = this.a;
        String str4 = this.b;
        String str5 = vf.e;
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
    private static boolean a(VideoCapabilities videoCapabilities, int i, int i2, double d2) {
        if (d2 == -1.0d || d2 <= 0.0d) {
            return videoCapabilities.isSizeSupported(i, i2);
        }
        return videoCapabilities.areSizeAndRateSupported(i, i2, d2);
    }
}
