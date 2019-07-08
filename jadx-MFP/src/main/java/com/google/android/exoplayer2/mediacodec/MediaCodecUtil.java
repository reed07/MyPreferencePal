package com.google.android.exoplayer2.mediacodec;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.media.MediaCodecInfo;
import android.media.MediaCodecInfo.CodecCapabilities;
import android.media.MediaCodecInfo.CodecProfileLevel;
import android.media.MediaCodecList;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Pair;
import android.util.SparseIntArray;
import com.brightcove.player.C;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import com.integralads.avid.library.mopub.session.internal.InternalAvidAdSessionContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressLint({"InlinedApi"})
@TargetApi(16)
public final class MediaCodecUtil {
    private static final SparseIntArray AVC_LEVEL_NUMBER_TO_CONST = new SparseIntArray();
    private static final SparseIntArray AVC_PROFILE_NUMBER_TO_CONST = new SparseIntArray();
    private static final String CODEC_ID_AVC1 = "avc1";
    private static final String CODEC_ID_AVC2 = "avc2";
    private static final String CODEC_ID_HEV1 = "hev1";
    private static final String CODEC_ID_HVC1 = "hvc1";
    private static final String CODEC_ID_MP4A = "mp4a";
    private static final Map<String, Integer> HEVC_CODEC_STRING_TO_PROFILE_LEVEL = new HashMap();
    private static final SparseIntArray MP4A_AUDIO_OBJECT_TYPE_TO_PROFILE = new SparseIntArray();
    private static final Pattern PROFILE_PATTERN = Pattern.compile("^\\D?(\\d+)$");
    private static final String TAG = "MediaCodecUtil";
    private static final HashMap<CodecKey, List<MediaCodecInfo>> decoderInfosCache = new HashMap<>();
    private static int maxH264DecodableFrameSize = -1;

    private static final class CodecKey {
        public final String mimeType;
        public final boolean secure;

        public CodecKey(String str, boolean z) {
            this.mimeType = str;
            this.secure = z;
        }

        public int hashCode() {
            String str = this.mimeType;
            return (((str == null ? 0 : str.hashCode()) + 31) * 31) + (this.secure ? 1231 : 1237);
        }

        public boolean equals(@Nullable Object obj) {
            boolean z = true;
            if (this == obj) {
                return true;
            }
            if (obj == null || obj.getClass() != CodecKey.class) {
                return false;
            }
            CodecKey codecKey = (CodecKey) obj;
            if (!TextUtils.equals(this.mimeType, codecKey.mimeType) || this.secure != codecKey.secure) {
                z = false;
            }
            return z;
        }
    }

    public static class DecoderQueryException extends Exception {
        private DecoderQueryException(Throwable th) {
            super("Failed to query underlying media codecs", th);
        }
    }

    private interface MediaCodecListCompat {
        int getCodecCount();

        MediaCodecInfo getCodecInfoAt(int i);

        boolean isSecurePlaybackSupported(String str, CodecCapabilities codecCapabilities);

        boolean secureDecodersExplicit();
    }

    private static final class MediaCodecListCompatV16 implements MediaCodecListCompat {
        public boolean secureDecodersExplicit() {
            return false;
        }

        private MediaCodecListCompatV16() {
        }

        public int getCodecCount() {
            return MediaCodecList.getCodecCount();
        }

        public MediaCodecInfo getCodecInfoAt(int i) {
            return MediaCodecList.getCodecInfoAt(i);
        }

        public boolean isSecurePlaybackSupported(String str, CodecCapabilities codecCapabilities) {
            return MimeTypes.VIDEO_H264.equals(str);
        }
    }

    @TargetApi(21)
    private static final class MediaCodecListCompatV21 implements MediaCodecListCompat {
        private final int codecKind;
        private MediaCodecInfo[] mediaCodecInfos;

        public boolean secureDecodersExplicit() {
            return true;
        }

        public MediaCodecListCompatV21(boolean z) {
            this.codecKind = z ? 1 : 0;
        }

        public int getCodecCount() {
            ensureMediaCodecInfosInitialized();
            return this.mediaCodecInfos.length;
        }

        public MediaCodecInfo getCodecInfoAt(int i) {
            ensureMediaCodecInfosInitialized();
            return this.mediaCodecInfos[i];
        }

        public boolean isSecurePlaybackSupported(String str, CodecCapabilities codecCapabilities) {
            return codecCapabilities.isFeatureSupported("secure-playback");
        }

        private void ensureMediaCodecInfosInitialized() {
            if (this.mediaCodecInfos == null) {
                this.mediaCodecInfos = new MediaCodecList(this.codecKind).getCodecInfos();
            }
        }
    }

    private static final class PreferOmxGoogleCodecComparator implements Comparator<MediaCodecInfo> {
        private PreferOmxGoogleCodecComparator() {
        }

        public int compare(MediaCodecInfo mediaCodecInfo, MediaCodecInfo mediaCodecInfo2) {
            return scoreMediaCodecInfo(mediaCodecInfo) - scoreMediaCodecInfo(mediaCodecInfo2);
        }

        private static int scoreMediaCodecInfo(MediaCodecInfo mediaCodecInfo) {
            return mediaCodecInfo.name.startsWith("OMX.google") ? -1 : 0;
        }
    }

    private static final class RawAudioCodecComparator implements Comparator<MediaCodecInfo> {
        private RawAudioCodecComparator() {
        }

        public int compare(MediaCodecInfo mediaCodecInfo, MediaCodecInfo mediaCodecInfo2) {
            return scoreMediaCodecInfo(mediaCodecInfo) - scoreMediaCodecInfo(mediaCodecInfo2);
        }

        private static int scoreMediaCodecInfo(MediaCodecInfo mediaCodecInfo) {
            String str = mediaCodecInfo.name;
            if (str.startsWith("OMX.google") || str.startsWith("c2.android")) {
                return -1;
            }
            return (Util.SDK_INT >= 26 || !str.equals("OMX.MTK.AUDIO.DECODER.RAW")) ? 0 : 1;
        }
    }

    private static int avcLevelToMaxFrameSize(int i) {
        switch (i) {
            case 1:
            case 2:
                return 25344;
            case 8:
            case 16:
            case 32:
                return 101376;
            case 64:
                return 202752;
            case 128:
            case 256:
                return 414720;
            case 512:
                return 921600;
            case 1024:
                return 1310720;
            case 2048:
            case 4096:
                return 2097152;
            case 8192:
                return 2228224;
            case C.DASH_ROLE_CAPTION_FLAG /*16384*/:
                return 5652480;
            case 32768:
            case 65536:
                return 9437184;
            default:
                return -1;
        }
    }

    static {
        AVC_PROFILE_NUMBER_TO_CONST.put(66, 1);
        AVC_PROFILE_NUMBER_TO_CONST.put(77, 2);
        AVC_PROFILE_NUMBER_TO_CONST.put(88, 4);
        AVC_PROFILE_NUMBER_TO_CONST.put(100, 8);
        AVC_PROFILE_NUMBER_TO_CONST.put(110, 16);
        AVC_PROFILE_NUMBER_TO_CONST.put(122, 32);
        AVC_PROFILE_NUMBER_TO_CONST.put(244, 64);
        AVC_LEVEL_NUMBER_TO_CONST.put(10, 1);
        AVC_LEVEL_NUMBER_TO_CONST.put(11, 4);
        AVC_LEVEL_NUMBER_TO_CONST.put(12, 8);
        AVC_LEVEL_NUMBER_TO_CONST.put(13, 16);
        AVC_LEVEL_NUMBER_TO_CONST.put(20, 32);
        AVC_LEVEL_NUMBER_TO_CONST.put(21, 64);
        AVC_LEVEL_NUMBER_TO_CONST.put(22, 128);
        AVC_LEVEL_NUMBER_TO_CONST.put(30, 256);
        AVC_LEVEL_NUMBER_TO_CONST.put(31, 512);
        AVC_LEVEL_NUMBER_TO_CONST.put(32, 1024);
        AVC_LEVEL_NUMBER_TO_CONST.put(40, 2048);
        AVC_LEVEL_NUMBER_TO_CONST.put(41, 4096);
        AVC_LEVEL_NUMBER_TO_CONST.put(42, 8192);
        AVC_LEVEL_NUMBER_TO_CONST.put(50, C.DASH_ROLE_CAPTION_FLAG);
        AVC_LEVEL_NUMBER_TO_CONST.put(51, 32768);
        AVC_LEVEL_NUMBER_TO_CONST.put(52, 65536);
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("L30", Integer.valueOf(1));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("L60", Integer.valueOf(4));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("L63", Integer.valueOf(16));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("L90", Integer.valueOf(64));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("L93", Integer.valueOf(256));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("L120", Integer.valueOf(1024));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("L123", Integer.valueOf(4096));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("L150", Integer.valueOf(C.DASH_ROLE_CAPTION_FLAG));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("L153", Integer.valueOf(65536));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("L156", Integer.valueOf(C.DASH_ROLE_SUB_FLAG));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("L180", Integer.valueOf(ExtractorMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("L183", Integer.valueOf(4194304));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("L186", Integer.valueOf(com.google.android.exoplayer2.C.DEFAULT_MUXED_BUFFER_SIZE));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("H30", Integer.valueOf(2));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("H60", Integer.valueOf(8));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("H63", Integer.valueOf(32));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("H90", Integer.valueOf(128));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("H93", Integer.valueOf(512));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("H120", Integer.valueOf(2048));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("H123", Integer.valueOf(8192));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("H150", Integer.valueOf(32768));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("H153", Integer.valueOf(131072));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("H156", Integer.valueOf(524288));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("H180", Integer.valueOf(2097152));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("H183", Integer.valueOf(8388608));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("H186", Integer.valueOf(33554432));
        MP4A_AUDIO_OBJECT_TYPE_TO_PROFILE.put(1, 1);
        MP4A_AUDIO_OBJECT_TYPE_TO_PROFILE.put(2, 2);
        MP4A_AUDIO_OBJECT_TYPE_TO_PROFILE.put(3, 3);
        MP4A_AUDIO_OBJECT_TYPE_TO_PROFILE.put(4, 4);
        MP4A_AUDIO_OBJECT_TYPE_TO_PROFILE.put(5, 5);
        MP4A_AUDIO_OBJECT_TYPE_TO_PROFILE.put(6, 6);
        MP4A_AUDIO_OBJECT_TYPE_TO_PROFILE.put(17, 17);
        MP4A_AUDIO_OBJECT_TYPE_TO_PROFILE.put(20, 20);
        MP4A_AUDIO_OBJECT_TYPE_TO_PROFILE.put(23, 23);
        MP4A_AUDIO_OBJECT_TYPE_TO_PROFILE.put(29, 29);
        MP4A_AUDIO_OBJECT_TYPE_TO_PROFILE.put(39, 39);
        MP4A_AUDIO_OBJECT_TYPE_TO_PROFILE.put(42, 42);
    }

    private MediaCodecUtil() {
    }

    public static void warmDecoderInfoCache(String str, boolean z) {
        try {
            getDecoderInfos(str, z);
        } catch (DecoderQueryException e) {
            Log.e(TAG, "Codec warming failed", e);
        }
    }

    @Nullable
    public static MediaCodecInfo getPassthroughDecoderInfo() throws DecoderQueryException {
        MediaCodecInfo decoderInfo = getDecoderInfo(MimeTypes.AUDIO_RAW, false);
        if (decoderInfo == null) {
            return null;
        }
        return MediaCodecInfo.newPassthroughInstance(decoderInfo.name);
    }

    @Nullable
    public static MediaCodecInfo getDecoderInfo(String str, boolean z) throws DecoderQueryException {
        List decoderInfos = getDecoderInfos(str, z);
        if (decoderInfos.isEmpty()) {
            return null;
        }
        return (MediaCodecInfo) decoderInfos.get(0);
    }

    public static synchronized List<MediaCodecInfo> getDecoderInfos(String str, boolean z) throws DecoderQueryException {
        synchronized (MediaCodecUtil.class) {
            CodecKey codecKey = new CodecKey(str, z);
            List<MediaCodecInfo> list = (List) decoderInfosCache.get(codecKey);
            if (list != null) {
                return list;
            }
            MediaCodecListCompat mediaCodecListCompatV21 = Util.SDK_INT >= 21 ? new MediaCodecListCompatV21(z) : new MediaCodecListCompatV16();
            ArrayList decoderInfosInternal = getDecoderInfosInternal(codecKey, mediaCodecListCompatV21, str);
            if (z && decoderInfosInternal.isEmpty() && 21 <= Util.SDK_INT && Util.SDK_INT <= 23) {
                mediaCodecListCompatV21 = new MediaCodecListCompatV16();
                decoderInfosInternal = getDecoderInfosInternal(codecKey, mediaCodecListCompatV21, str);
                if (!decoderInfosInternal.isEmpty()) {
                    String str2 = TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("MediaCodecList API didn't list secure decoder for: ");
                    sb.append(str);
                    sb.append(". Assuming: ");
                    sb.append(((MediaCodecInfo) decoderInfosInternal.get(0)).name);
                    Log.w(str2, sb.toString());
                }
            }
            if (MimeTypes.AUDIO_E_AC3_JOC.equals(str)) {
                decoderInfosInternal.addAll(getDecoderInfosInternal(new CodecKey(MimeTypes.AUDIO_E_AC3, codecKey.secure), mediaCodecListCompatV21, str));
            }
            applyWorkarounds(str, decoderInfosInternal);
            List<MediaCodecInfo> unmodifiableList = Collections.unmodifiableList(decoderInfosInternal);
            decoderInfosCache.put(codecKey, unmodifiableList);
            return unmodifiableList;
        }
    }

    public static int maxH264DecodableFrameSize() throws DecoderQueryException {
        if (maxH264DecodableFrameSize == -1) {
            int i = 0;
            MediaCodecInfo decoderInfo = getDecoderInfo(MimeTypes.VIDEO_H264, false);
            if (decoderInfo != null) {
                CodecProfileLevel[] profileLevels = decoderInfo.getProfileLevels();
                int length = profileLevels.length;
                int i2 = 0;
                while (i < length) {
                    i2 = Math.max(avcLevelToMaxFrameSize(profileLevels[i].level), i2);
                    i++;
                }
                i = Math.max(i2, Util.SDK_INT >= 21 ? 345600 : 172800);
            }
            maxH264DecodableFrameSize = i;
        }
        return maxH264DecodableFrameSize;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0030, code lost:
        if (r3.equals(CODEC_ID_HEV1) != false) goto L_0x0048;
     */
    @android.support.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.util.Pair<java.lang.Integer, java.lang.Integer> getCodecProfileAndLevel(java.lang.String r6) {
        /*
            r0 = 0
            if (r6 != 0) goto L_0x0004
            return r0
        L_0x0004:
            java.lang.String r1 = "\\."
            java.lang.String[] r1 = r6.split(r1)
            r2 = 0
            r3 = r1[r2]
            r4 = -1
            int r5 = r3.hashCode()
            switch(r5) {
                case 3006243: goto L_0x003d;
                case 3006244: goto L_0x0033;
                case 3199032: goto L_0x002a;
                case 3214780: goto L_0x0020;
                case 3356560: goto L_0x0016;
                default: goto L_0x0015;
            }
        L_0x0015:
            goto L_0x0047
        L_0x0016:
            java.lang.String r2 = "mp4a"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L_0x0047
            r2 = 4
            goto L_0x0048
        L_0x0020:
            java.lang.String r2 = "hvc1"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L_0x0047
            r2 = 1
            goto L_0x0048
        L_0x002a:
            java.lang.String r5 = "hev1"
            boolean r3 = r3.equals(r5)
            if (r3 == 0) goto L_0x0047
            goto L_0x0048
        L_0x0033:
            java.lang.String r2 = "avc2"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L_0x0047
            r2 = 3
            goto L_0x0048
        L_0x003d:
            java.lang.String r2 = "avc1"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L_0x0047
            r2 = 2
            goto L_0x0048
        L_0x0047:
            r2 = -1
        L_0x0048:
            switch(r2) {
                case 0: goto L_0x0056;
                case 1: goto L_0x0056;
                case 2: goto L_0x0051;
                case 3: goto L_0x0051;
                case 4: goto L_0x004c;
                default: goto L_0x004b;
            }
        L_0x004b:
            return r0
        L_0x004c:
            android.util.Pair r6 = getAacCodecProfileAndLevel(r6, r1)
            return r6
        L_0x0051:
            android.util.Pair r6 = getAvcProfileAndLevel(r6, r1)
            return r6
        L_0x0056:
            android.util.Pair r6 = getHevcProfileAndLevel(r6, r1)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.mediacodec.MediaCodecUtil.getCodecProfileAndLevel(java.lang.String):android.util.Pair");
    }

    private static ArrayList<MediaCodecInfo> getDecoderInfosInternal(CodecKey codecKey, MediaCodecListCompat mediaCodecListCompat, String str) throws DecoderQueryException {
        int i;
        CodecKey codecKey2 = codecKey;
        MediaCodecListCompat mediaCodecListCompat2 = mediaCodecListCompat;
        try {
            ArrayList<MediaCodecInfo> arrayList = new ArrayList<>();
            String str2 = codecKey2.mimeType;
            int codecCount = mediaCodecListCompat.getCodecCount();
            boolean secureDecodersExplicit = mediaCodecListCompat.secureDecodersExplicit();
            int i2 = 0;
            while (i2 < codecCount) {
                MediaCodecInfo codecInfoAt = mediaCodecListCompat2.getCodecInfoAt(i2);
                String name = codecInfoAt.getName();
                if (isCodecUsableDecoder(codecInfoAt, name, secureDecodersExplicit, str)) {
                    String[] supportedTypes = codecInfoAt.getSupportedTypes();
                    int length = supportedTypes.length;
                    int i3 = 0;
                    while (i3 < length) {
                        String str3 = supportedTypes[i3];
                        if (str3.equalsIgnoreCase(str2)) {
                            try {
                                CodecCapabilities capabilitiesForType = codecInfoAt.getCapabilitiesForType(str3);
                                boolean isSecurePlaybackSupported = mediaCodecListCompat2.isSecurePlaybackSupported(str2, capabilitiesForType);
                                boolean codecNeedsDisableAdaptationWorkaround = codecNeedsDisableAdaptationWorkaround(name);
                                if (secureDecodersExplicit) {
                                    i = codecCount;
                                    try {
                                        if (codecKey2.secure != isSecurePlaybackSupported) {
                                            if (!secureDecodersExplicit || codecKey2.secure) {
                                                if (!secureDecodersExplicit && isSecurePlaybackSupported) {
                                                    StringBuilder sb = new StringBuilder();
                                                    sb.append(name);
                                                    sb.append(".secure");
                                                    arrayList.add(MediaCodecInfo.newInstance(sb.toString(), str2, capabilitiesForType, codecNeedsDisableAdaptationWorkaround, true));
                                                    return arrayList;
                                                }
                                            }
                                        }
                                    } catch (Exception e) {
                                        e = e;
                                        if (Util.SDK_INT <= 23) {
                                        }
                                        String str4 = TAG;
                                        StringBuilder sb2 = new StringBuilder();
                                        sb2.append("Failed to query codec ");
                                        sb2.append(name);
                                        sb2.append(" (");
                                        sb2.append(str3);
                                        sb2.append(")");
                                        Log.e(str4, sb2.toString());
                                        throw e;
                                    }
                                } else {
                                    i = codecCount;
                                    if (!secureDecodersExplicit) {
                                    }
                                    StringBuilder sb3 = new StringBuilder();
                                    sb3.append(name);
                                    sb3.append(".secure");
                                    arrayList.add(MediaCodecInfo.newInstance(sb3.toString(), str2, capabilitiesForType, codecNeedsDisableAdaptationWorkaround, true));
                                    return arrayList;
                                }
                                arrayList.add(MediaCodecInfo.newInstance(name, str2, capabilitiesForType, codecNeedsDisableAdaptationWorkaround, false));
                            } catch (Exception e2) {
                                e = e2;
                                i = codecCount;
                                if (Util.SDK_INT <= 23 || arrayList.isEmpty()) {
                                    String str42 = TAG;
                                    StringBuilder sb22 = new StringBuilder();
                                    sb22.append("Failed to query codec ");
                                    sb22.append(name);
                                    sb22.append(" (");
                                    sb22.append(str3);
                                    sb22.append(")");
                                    Log.e(str42, sb22.toString());
                                    throw e;
                                }
                                String str5 = TAG;
                                StringBuilder sb4 = new StringBuilder();
                                sb4.append("Skipping codec ");
                                sb4.append(name);
                                sb4.append(" (failed to query capabilities)");
                                Log.e(str5, sb4.toString());
                                i3++;
                                codecCount = i;
                                mediaCodecListCompat2 = mediaCodecListCompat;
                            }
                        } else {
                            i = codecCount;
                        }
                        i3++;
                        codecCount = i;
                        mediaCodecListCompat2 = mediaCodecListCompat;
                    }
                    continue;
                }
                i2++;
                codecCount = codecCount;
                mediaCodecListCompat2 = mediaCodecListCompat;
            }
            return arrayList;
        } catch (Exception e3) {
            throw new DecoderQueryException(e3);
        }
    }

    private static boolean isCodecUsableDecoder(MediaCodecInfo mediaCodecInfo, String str, boolean z, String str2) {
        if (mediaCodecInfo.isEncoder() || (!z && str.endsWith(".secure"))) {
            return false;
        }
        if (Util.SDK_INT < 21 && ("CIPAACDecoder".equals(str) || "CIPMP3Decoder".equals(str) || "CIPVorbisDecoder".equals(str) || "CIPAMRNBDecoder".equals(str) || "AACDecoder".equals(str) || "MP3Decoder".equals(str))) {
            return false;
        }
        if (Util.SDK_INT < 18 && "OMX.MTK.AUDIO.DECODER.AAC".equals(str) && ("a70".equals(Util.DEVICE) || ("Xiaomi".equals(Util.MANUFACTURER) && Util.DEVICE.startsWith("HM")))) {
            return false;
        }
        if (Util.SDK_INT == 16 && "OMX.qcom.audio.decoder.mp3".equals(str) && ("dlxu".equals(Util.DEVICE) || "protou".equals(Util.DEVICE) || "ville".equals(Util.DEVICE) || "villeplus".equals(Util.DEVICE) || "villec2".equals(Util.DEVICE) || Util.DEVICE.startsWith("gee") || "C6602".equals(Util.DEVICE) || "C6603".equals(Util.DEVICE) || "C6606".equals(Util.DEVICE) || "C6616".equals(Util.DEVICE) || "L36h".equals(Util.DEVICE) || "SO-02E".equals(Util.DEVICE))) {
            return false;
        }
        if (Util.SDK_INT == 16 && "OMX.qcom.audio.decoder.aac".equals(str) && ("C1504".equals(Util.DEVICE) || "C1505".equals(Util.DEVICE) || "C1604".equals(Util.DEVICE) || "C1605".equals(Util.DEVICE))) {
            return false;
        }
        if (Util.SDK_INT < 24 && (("OMX.SEC.aac.dec".equals(str) || "OMX.Exynos.AAC.Decoder".equals(str)) && "samsung".equals(Util.MANUFACTURER) && (Util.DEVICE.startsWith("zeroflte") || Util.DEVICE.startsWith("zerolte") || Util.DEVICE.startsWith("zenlte") || "SC-05G".equals(Util.DEVICE) || "marinelteatt".equals(Util.DEVICE) || "404SC".equals(Util.DEVICE) || "SC-04G".equals(Util.DEVICE) || "SCV31".equals(Util.DEVICE)))) {
            return false;
        }
        if (Util.SDK_INT <= 19 && "OMX.SEC.vp8.dec".equals(str) && "samsung".equals(Util.MANUFACTURER) && (Util.DEVICE.startsWith("d2") || Util.DEVICE.startsWith("serrano") || Util.DEVICE.startsWith("jflte") || Util.DEVICE.startsWith("santos") || Util.DEVICE.startsWith("t0"))) {
            return false;
        }
        if (Util.SDK_INT <= 19 && Util.DEVICE.startsWith("jflte") && "OMX.qcom.video.decoder.vp8".equals(str)) {
            return false;
        }
        if (!MimeTypes.AUDIO_E_AC3_JOC.equals(str2) || !"OMX.MTK.AUDIO.DECODER.DSPAC3".equals(str)) {
            return true;
        }
        return false;
    }

    private static void applyWorkarounds(String str, List<MediaCodecInfo> list) {
        if (MimeTypes.AUDIO_RAW.equals(str)) {
            Collections.sort(list, new RawAudioCodecComparator());
        } else if (Util.SDK_INT < 21 && list.size() > 1) {
            String str2 = ((MediaCodecInfo) list.get(0)).name;
            if ("OMX.SEC.mp3.dec".equals(str2) || "OMX.SEC.MP3.Decoder".equals(str2) || "OMX.brcm.audio.mp3.decoder".equals(str2)) {
                Collections.sort(list, new PreferOmxGoogleCodecComparator());
            }
        }
    }

    private static boolean codecNeedsDisableAdaptationWorkaround(String str) {
        return Util.SDK_INT <= 22 && ("ODROID-XU3".equals(Util.MODEL) || "Nexus 10".equals(Util.MODEL)) && ("OMX.Exynos.AVC.Decoder".equals(str) || "OMX.Exynos.AVC.Decoder.secure".equals(str));
    }

    private static Pair<Integer, Integer> getHevcProfileAndLevel(String str, String[] strArr) {
        int i;
        if (strArr.length < 4) {
            String str2 = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("Ignoring malformed HEVC codec string: ");
            sb.append(str);
            Log.w(str2, sb.toString());
            return null;
        }
        Matcher matcher = PROFILE_PATTERN.matcher(strArr[1]);
        if (!matcher.matches()) {
            String str3 = TAG;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Ignoring malformed HEVC codec string: ");
            sb2.append(str);
            Log.w(str3, sb2.toString());
            return null;
        }
        String group = matcher.group(1);
        if (AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(group)) {
            i = 1;
        } else if (InternalAvidAdSessionContext.AVID_API_LEVEL.equals(group)) {
            i = 2;
        } else {
            String str4 = TAG;
            StringBuilder sb3 = new StringBuilder();
            sb3.append("Unknown HEVC profile string: ");
            sb3.append(group);
            Log.w(str4, sb3.toString());
            return null;
        }
        Integer num = (Integer) HEVC_CODEC_STRING_TO_PROFILE_LEVEL.get(strArr[3]);
        if (num != null) {
            return new Pair<>(Integer.valueOf(i), num);
        }
        String str5 = TAG;
        StringBuilder sb4 = new StringBuilder();
        sb4.append("Unknown HEVC level string: ");
        sb4.append(matcher.group(1));
        Log.w(str5, sb4.toString());
        return null;
    }

    private static Pair<Integer, Integer> getAvcProfileAndLevel(String str, String[] strArr) {
        Integer num;
        Integer num2;
        if (strArr.length < 2) {
            String str2 = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("Ignoring malformed AVC codec string: ");
            sb.append(str);
            Log.w(str2, sb.toString());
            return null;
        }
        try {
            if (strArr[1].length() == 6) {
                Integer valueOf = Integer.valueOf(Integer.parseInt(strArr[1].substring(0, 2), 16));
                num = Integer.valueOf(Integer.parseInt(strArr[1].substring(4), 16));
                num2 = valueOf;
            } else if (strArr.length >= 3) {
                num2 = Integer.valueOf(Integer.parseInt(strArr[1]));
                num = Integer.valueOf(Integer.parseInt(strArr[2]));
            } else {
                String str3 = TAG;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Ignoring malformed AVC codec string: ");
                sb2.append(str);
                Log.w(str3, sb2.toString());
                return null;
            }
            int i = AVC_PROFILE_NUMBER_TO_CONST.get(num2.intValue(), -1);
            if (i == -1) {
                String str4 = TAG;
                StringBuilder sb3 = new StringBuilder();
                sb3.append("Unknown AVC profile: ");
                sb3.append(num2);
                Log.w(str4, sb3.toString());
                return null;
            }
            int i2 = AVC_LEVEL_NUMBER_TO_CONST.get(num.intValue(), -1);
            if (i2 != -1) {
                return new Pair<>(Integer.valueOf(i), Integer.valueOf(i2));
            }
            String str5 = TAG;
            StringBuilder sb4 = new StringBuilder();
            sb4.append("Unknown AVC level: ");
            sb4.append(num);
            Log.w(str5, sb4.toString());
            return null;
        } catch (NumberFormatException unused) {
            String str6 = TAG;
            StringBuilder sb5 = new StringBuilder();
            sb5.append("Ignoring malformed AVC codec string: ");
            sb5.append(str);
            Log.w(str6, sb5.toString());
            return null;
        }
    }

    @Nullable
    private static Pair<Integer, Integer> getAacCodecProfileAndLevel(String str, String[] strArr) {
        if (strArr.length != 3) {
            String str2 = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("Ignoring malformed MP4A codec string: ");
            sb.append(str);
            Log.w(str2, sb.toString());
            return null;
        }
        try {
            if (MimeTypes.AUDIO_AAC.equals(MimeTypes.getMimeTypeFromMp4ObjectType(Integer.parseInt(strArr[1], 16)))) {
                int i = MP4A_AUDIO_OBJECT_TYPE_TO_PROFILE.get(Integer.parseInt(strArr[2]), -1);
                if (i != -1) {
                    return new Pair<>(Integer.valueOf(i), Integer.valueOf(0));
                }
            }
        } catch (NumberFormatException unused) {
            String str3 = TAG;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Ignoring malformed MP4A codec string: ");
            sb2.append(str);
            Log.w(str3, sb2.toString());
        }
        return null;
    }
}
