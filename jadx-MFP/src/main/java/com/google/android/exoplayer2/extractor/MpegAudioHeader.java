package com.google.android.exoplayer2.extractor;

import com.google.android.exoplayer2.util.MimeTypes;

public final class MpegAudioHeader {
    private static final int[] BITRATE_V1_L1 = {32000, 64000, 96000, 128000, 160000, 192000, 224000, 256000, 288000, 320000, 352000, 384000, 416000, 448000};
    private static final int[] BITRATE_V1_L2 = {32000, 48000, 56000, 64000, 80000, 96000, 112000, 128000, 160000, 192000, 224000, 256000, 320000, 384000};
    private static final int[] BITRATE_V1_L3 = {32000, 40000, 48000, 56000, 64000, 80000, 96000, 112000, 128000, 160000, 192000, 224000, 256000, 320000};
    private static final int[] BITRATE_V2 = {8000, 16000, 24000, 32000, 40000, 48000, 56000, 64000, 80000, 96000, 112000, 128000, 144000, 160000};
    private static final int[] BITRATE_V2_L1 = {32000, 48000, 56000, 64000, 80000, 96000, 112000, 128000, 144000, 160000, 176000, 192000, 224000, 256000};
    public static final int MAX_FRAME_SIZE_BYTES = 4096;
    private static final String[] MIME_TYPE_BY_LAYER = {MimeTypes.AUDIO_MPEG_L1, MimeTypes.AUDIO_MPEG_L2, MimeTypes.AUDIO_MPEG};
    private static final int[] SAMPLING_RATE_V1 = {44100, 48000, 32000};
    public int bitrate;
    public int channels;
    public int frameSize;
    public String mimeType;
    public int sampleRate;
    public int samplesPerFrame;
    public int version;

    public static int getFrameSize(int i) {
        if ((i & -2097152) != -2097152) {
            return -1;
        }
        int i2 = (i >>> 19) & 3;
        if (i2 == 1) {
            return -1;
        }
        int i3 = (i >>> 17) & 3;
        if (i3 == 0) {
            return -1;
        }
        int i4 = (i >>> 12) & 15;
        if (i4 == 0 || i4 == 15) {
            return -1;
        }
        int i5 = (i >>> 10) & 3;
        if (i5 == 3) {
            return -1;
        }
        int i6 = SAMPLING_RATE_V1[i5];
        if (i2 == 2) {
            i6 /= 2;
        } else if (i2 == 0) {
            i6 /= 4;
        }
        int i7 = (i >>> 9) & 1;
        if (i3 == 3) {
            return ((((i2 == 3 ? BITRATE_V1_L1[i4 - 1] : BITRATE_V2_L1[i4 - 1]) * 12) / i6) + i7) * 4;
        }
        int i8 = i2 == 3 ? i3 == 2 ? BITRATE_V1_L2[i4 - 1] : BITRATE_V1_L3[i4 - 1] : BITRATE_V2[i4 - 1];
        int i9 = 144;
        if (i2 == 3) {
            return ((i8 * 144) / i6) + i7;
        }
        if (i3 == 1) {
            i9 = 72;
        }
        return ((i9 * i8) / i6) + i7;
    }

    public static boolean populateHeader(int i, MpegAudioHeader mpegAudioHeader) {
        int i2;
        int i3;
        if ((i & -2097152) != -2097152) {
            return false;
        }
        int i4 = (i >>> 19) & 3;
        if (i4 == 1) {
            return false;
        }
        int i5 = (i >>> 17) & 3;
        if (i5 == 0) {
            return false;
        }
        int i6 = (i >>> 12) & 15;
        if (i6 == 0 || i6 == 15) {
            return false;
        }
        int i7 = (i >>> 10) & 3;
        if (i7 == 3) {
            return false;
        }
        int i8 = SAMPLING_RATE_V1[i7];
        int i9 = i4 == 2 ? i8 / 2 : i4 == 0 ? i8 / 4 : i8;
        int i10 = (i >>> 9) & 1;
        int i11 = 1152;
        if (i5 == 3) {
            i3 = ((((i4 == 3 ? BITRATE_V1_L1[i6 - 1] : BITRATE_V2_L1[i6 - 1]) * 12) / i9) + i10) * 4;
            i2 = 384;
        } else {
            int i12 = 144;
            if (i4 == 3) {
                i3 = (((i5 == 2 ? BITRATE_V1_L2[i6 - 1] : BITRATE_V1_L3[i6 - 1]) * 144) / i9) + i10;
                i2 = 1152;
            } else {
                int i13 = BITRATE_V2[i6 - 1];
                if (i5 == 1) {
                    i11 = 576;
                }
                if (i5 == 1) {
                    i12 = 72;
                }
                i3 = ((i12 * i13) / i9) + i10;
                i2 = i11;
            }
        }
        mpegAudioHeader.setValues(i4, MIME_TYPE_BY_LAYER[3 - i5], i3, i9, ((i >> 6) & 3) == 3 ? 1 : 2, ((i3 * 8) * i9) / i2, i2);
        return true;
    }

    private void setValues(int i, String str, int i2, int i3, int i4, int i5, int i6) {
        this.version = i;
        this.mimeType = str;
        this.frameSize = i2;
        this.sampleRate = i3;
        this.channels = i4;
        this.bitrate = i5;
        this.samplesPerFrame = i6;
    }
}
