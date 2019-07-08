package com.google.android.exoplayer2.mediacodec;

import android.annotation.TargetApi;
import android.graphics.Point;
import android.media.MediaCodecInfo.AudioCapabilities;
import android.media.MediaCodecInfo.CodecCapabilities;
import android.media.MediaCodecInfo.CodecProfileLevel;
import android.media.MediaCodecInfo.VideoCapabilities;
import android.support.annotation.Nullable;
import android.util.Pair;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.mediacodec.MediaCodecUtil.DecoderQueryException;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;

@TargetApi(16)
public final class MediaCodecInfo {
    public static final int MAX_SUPPORTED_INSTANCES_UNKNOWN = -1;
    public static final String TAG = "MediaCodecInfo";
    public final boolean adaptive;
    @Nullable
    public final CodecCapabilities capabilities;
    private final boolean isVideo;
    @Nullable
    public final String mimeType;
    public final String name;
    public final boolean passthrough;
    public final boolean secure;
    public final boolean tunneling;

    public static MediaCodecInfo newPassthroughInstance(String str) {
        MediaCodecInfo mediaCodecInfo = new MediaCodecInfo(str, null, null, true, false, false);
        return mediaCodecInfo;
    }

    public static MediaCodecInfo newInstance(String str, String str2, CodecCapabilities codecCapabilities) {
        MediaCodecInfo mediaCodecInfo = new MediaCodecInfo(str, str2, codecCapabilities, false, false, false);
        return mediaCodecInfo;
    }

    public static MediaCodecInfo newInstance(String str, String str2, CodecCapabilities codecCapabilities, boolean z, boolean z2) {
        MediaCodecInfo mediaCodecInfo = new MediaCodecInfo(str, str2, codecCapabilities, false, z, z2);
        return mediaCodecInfo;
    }

    private MediaCodecInfo(String str, @Nullable String str2, @Nullable CodecCapabilities codecCapabilities, boolean z, boolean z2, boolean z3) {
        this.name = (String) Assertions.checkNotNull(str);
        this.mimeType = str2;
        this.capabilities = codecCapabilities;
        this.passthrough = z;
        boolean z4 = true;
        this.adaptive = !z2 && codecCapabilities != null && isAdaptive(codecCapabilities);
        this.tunneling = codecCapabilities != null && isTunneling(codecCapabilities);
        if (!z3 && (codecCapabilities == null || !isSecure(codecCapabilities))) {
            z4 = false;
        }
        this.secure = z4;
        this.isVideo = MimeTypes.isVideo(str2);
    }

    public String toString() {
        return this.name;
    }

    public CodecProfileLevel[] getProfileLevels() {
        CodecCapabilities codecCapabilities = this.capabilities;
        return (codecCapabilities == null || codecCapabilities.profileLevels == null) ? new CodecProfileLevel[0] : this.capabilities.profileLevels;
    }

    public int getMaxSupportedInstances() {
        if (Util.SDK_INT >= 23) {
            CodecCapabilities codecCapabilities = this.capabilities;
            if (codecCapabilities != null) {
                return getMaxSupportedInstancesV23(codecCapabilities);
            }
        }
        return -1;
    }

    public boolean isFormatSupported(Format format) throws DecoderQueryException {
        boolean z = false;
        if (!isCodecSupported(format.codecs)) {
            return false;
        }
        if (!this.isVideo) {
            if (Util.SDK_INT < 21 || ((format.sampleRate == -1 || isAudioSampleRateSupportedV21(format.sampleRate)) && (format.channelCount == -1 || isAudioChannelCountSupportedV21(format.channelCount)))) {
                z = true;
            }
            return z;
        } else if (format.width <= 0 || format.height <= 0) {
            return true;
        } else {
            if (Util.SDK_INT >= 21) {
                return isVideoSizeAndRateSupportedV21(format.width, format.height, (double) format.frameRate);
            }
            if (format.width * format.height <= MediaCodecUtil.maxH264DecodableFrameSize()) {
                z = true;
            }
            if (!z) {
                StringBuilder sb = new StringBuilder();
                sb.append("legacyFrameSize, ");
                sb.append(format.width);
                sb.append(AvidJSONUtil.KEY_X);
                sb.append(format.height);
                logNoSupport(sb.toString());
            }
            return z;
        }
    }

    public boolean isCodecSupported(String str) {
        CodecProfileLevel[] profileLevels;
        if (str == null || this.mimeType == null) {
            return true;
        }
        String mediaMimeType = MimeTypes.getMediaMimeType(str);
        if (mediaMimeType == null) {
            return true;
        }
        if (!this.mimeType.equals(mediaMimeType)) {
            StringBuilder sb = new StringBuilder();
            sb.append("codec.mime ");
            sb.append(str);
            sb.append(", ");
            sb.append(mediaMimeType);
            logNoSupport(sb.toString());
            return false;
        }
        Pair codecProfileAndLevel = MediaCodecUtil.getCodecProfileAndLevel(str);
        if (codecProfileAndLevel == null) {
            return true;
        }
        int intValue = ((Integer) codecProfileAndLevel.first).intValue();
        int intValue2 = ((Integer) codecProfileAndLevel.second).intValue();
        if (!this.isVideo && intValue != 42) {
            return true;
        }
        for (CodecProfileLevel codecProfileLevel : getProfileLevels()) {
            if (codecProfileLevel.profile == intValue && codecProfileLevel.level >= intValue2) {
                return true;
            }
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("codec.profileLevel, ");
        sb2.append(str);
        sb2.append(", ");
        sb2.append(mediaMimeType);
        logNoSupport(sb2.toString());
        return false;
    }

    public boolean isSeamlessAdaptationSupported(Format format) {
        if (this.isVideo) {
            return this.adaptive;
        }
        Pair codecProfileAndLevel = MediaCodecUtil.getCodecProfileAndLevel(format.codecs);
        return codecProfileAndLevel != null && ((Integer) codecProfileAndLevel.first).intValue() == 42;
    }

    public boolean isSeamlessAdaptationSupported(Format format, Format format2, boolean z) {
        boolean z2 = true;
        if (this.isVideo) {
            if (!format.sampleMimeType.equals(format2.sampleMimeType) || format.rotationDegrees != format2.rotationDegrees || ((!this.adaptive && !(format.width == format2.width && format.height == format2.height)) || ((z || format2.colorInfo != null) && !Util.areEqual(format.colorInfo, format2.colorInfo)))) {
                z2 = false;
            }
            return z2;
        } else if (!MimeTypes.AUDIO_AAC.equals(this.mimeType) || !format.sampleMimeType.equals(format2.sampleMimeType) || format.channelCount != format2.channelCount || format.sampleRate != format2.sampleRate) {
            return false;
        } else {
            Pair codecProfileAndLevel = MediaCodecUtil.getCodecProfileAndLevel(format.codecs);
            Pair codecProfileAndLevel2 = MediaCodecUtil.getCodecProfileAndLevel(format2.codecs);
            if (codecProfileAndLevel == null || codecProfileAndLevel2 == null) {
                return false;
            }
            int intValue = ((Integer) codecProfileAndLevel.first).intValue();
            int intValue2 = ((Integer) codecProfileAndLevel2.first).intValue();
            if (!(intValue == 42 && intValue2 == 42)) {
                z2 = false;
            }
            return z2;
        }
    }

    @TargetApi(21)
    public boolean isVideoSizeAndRateSupportedV21(int i, int i2, double d) {
        CodecCapabilities codecCapabilities = this.capabilities;
        if (codecCapabilities == null) {
            logNoSupport("sizeAndRate.caps");
            return false;
        }
        VideoCapabilities videoCapabilities = codecCapabilities.getVideoCapabilities();
        if (videoCapabilities == null) {
            logNoSupport("sizeAndRate.vCaps");
            return false;
        }
        if (!areSizeAndRateSupportedV21(videoCapabilities, i, i2, d)) {
            if (i >= i2 || !areSizeAndRateSupportedV21(videoCapabilities, i2, i, d)) {
                StringBuilder sb = new StringBuilder();
                sb.append("sizeAndRate.support, ");
                sb.append(i);
                sb.append(AvidJSONUtil.KEY_X);
                sb.append(i2);
                sb.append(AvidJSONUtil.KEY_X);
                sb.append(d);
                logNoSupport(sb.toString());
                return false;
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append("sizeAndRate.rotated, ");
            sb2.append(i);
            sb2.append(AvidJSONUtil.KEY_X);
            sb2.append(i2);
            sb2.append(AvidJSONUtil.KEY_X);
            sb2.append(d);
            logAssumedSupport(sb2.toString());
        }
        return true;
    }

    @TargetApi(21)
    public Point alignVideoSizeV21(int i, int i2) {
        CodecCapabilities codecCapabilities = this.capabilities;
        if (codecCapabilities == null) {
            logNoSupport("align.caps");
            return null;
        }
        VideoCapabilities videoCapabilities = codecCapabilities.getVideoCapabilities();
        if (videoCapabilities == null) {
            logNoSupport("align.vCaps");
            return null;
        }
        int widthAlignment = videoCapabilities.getWidthAlignment();
        int heightAlignment = videoCapabilities.getHeightAlignment();
        return new Point(Util.ceilDivide(i, widthAlignment) * widthAlignment, Util.ceilDivide(i2, heightAlignment) * heightAlignment);
    }

    @TargetApi(21)
    public boolean isAudioSampleRateSupportedV21(int i) {
        CodecCapabilities codecCapabilities = this.capabilities;
        if (codecCapabilities == null) {
            logNoSupport("sampleRate.caps");
            return false;
        }
        AudioCapabilities audioCapabilities = codecCapabilities.getAudioCapabilities();
        if (audioCapabilities == null) {
            logNoSupport("sampleRate.aCaps");
            return false;
        } else if (audioCapabilities.isSampleRateSupported(i)) {
            return true;
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("sampleRate.support, ");
            sb.append(i);
            logNoSupport(sb.toString());
            return false;
        }
    }

    @TargetApi(21)
    public boolean isAudioChannelCountSupportedV21(int i) {
        CodecCapabilities codecCapabilities = this.capabilities;
        if (codecCapabilities == null) {
            logNoSupport("channelCount.caps");
            return false;
        }
        AudioCapabilities audioCapabilities = codecCapabilities.getAudioCapabilities();
        if (audioCapabilities == null) {
            logNoSupport("channelCount.aCaps");
            return false;
        } else if (adjustMaxInputChannelCount(this.name, this.mimeType, audioCapabilities.getMaxInputChannelCount()) >= i) {
            return true;
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("channelCount.support, ");
            sb.append(i);
            logNoSupport(sb.toString());
            return false;
        }
    }

    private void logNoSupport(String str) {
        String str2 = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("NoSupport [");
        sb.append(str);
        sb.append("] [");
        sb.append(this.name);
        sb.append(", ");
        sb.append(this.mimeType);
        sb.append("] [");
        sb.append(Util.DEVICE_DEBUG_INFO);
        sb.append("]");
        Log.d(str2, sb.toString());
    }

    private void logAssumedSupport(String str) {
        String str2 = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("AssumedSupport [");
        sb.append(str);
        sb.append("] [");
        sb.append(this.name);
        sb.append(", ");
        sb.append(this.mimeType);
        sb.append("] [");
        sb.append(Util.DEVICE_DEBUG_INFO);
        sb.append("]");
        Log.d(str2, sb.toString());
    }

    private static int adjustMaxInputChannelCount(String str, String str2, int i) {
        if (i > 1 || ((Util.SDK_INT >= 26 && i > 0) || MimeTypes.AUDIO_MPEG.equals(str2) || MimeTypes.AUDIO_AMR_NB.equals(str2) || MimeTypes.AUDIO_AMR_WB.equals(str2) || MimeTypes.AUDIO_AAC.equals(str2) || MimeTypes.AUDIO_VORBIS.equals(str2) || MimeTypes.AUDIO_OPUS.equals(str2) || MimeTypes.AUDIO_RAW.equals(str2) || MimeTypes.AUDIO_FLAC.equals(str2) || MimeTypes.AUDIO_ALAW.equals(str2) || MimeTypes.AUDIO_MLAW.equals(str2) || MimeTypes.AUDIO_MSGSM.equals(str2))) {
            return i;
        }
        int i2 = MimeTypes.AUDIO_AC3.equals(str2) ? 6 : MimeTypes.AUDIO_E_AC3.equals(str2) ? 16 : 30;
        String str3 = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("AssumedMaxChannelAdjustment: ");
        sb.append(str);
        sb.append(", [");
        sb.append(i);
        sb.append(" to ");
        sb.append(i2);
        sb.append("]");
        Log.w(str3, sb.toString());
        return i2;
    }

    private static boolean isAdaptive(CodecCapabilities codecCapabilities) {
        return Util.SDK_INT >= 19 && isAdaptiveV19(codecCapabilities);
    }

    @TargetApi(19)
    private static boolean isAdaptiveV19(CodecCapabilities codecCapabilities) {
        return codecCapabilities.isFeatureSupported("adaptive-playback");
    }

    private static boolean isTunneling(CodecCapabilities codecCapabilities) {
        return Util.SDK_INT >= 21 && isTunnelingV21(codecCapabilities);
    }

    @TargetApi(21)
    private static boolean isTunnelingV21(CodecCapabilities codecCapabilities) {
        return codecCapabilities.isFeatureSupported("tunneled-playback");
    }

    private static boolean isSecure(CodecCapabilities codecCapabilities) {
        return Util.SDK_INT >= 21 && isSecureV21(codecCapabilities);
    }

    @TargetApi(21)
    private static boolean isSecureV21(CodecCapabilities codecCapabilities) {
        return codecCapabilities.isFeatureSupported("secure-playback");
    }

    @TargetApi(21)
    private static boolean areSizeAndRateSupportedV21(VideoCapabilities videoCapabilities, int i, int i2, double d) {
        if (d == -1.0d || d <= 0.0d) {
            return videoCapabilities.isSizeSupported(i, i2);
        }
        return videoCapabilities.areSizeAndRateSupported(i, i2, d);
    }

    @TargetApi(23)
    private static int getMaxSupportedInstancesV23(CodecCapabilities codecCapabilities) {
        return codecCapabilities.getMaxSupportedInstances();
    }
}
