package com.google.android.exoplayer2.audio;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.ParsableBitArray;
import com.google.common.base.Ascii;
import com.myfitnesspal.feature.video.task.AmazonAdTask;
import java.nio.ByteBuffer;
import java.util.Arrays;

public final class DtsUtil {
    private static final int[] CHANNELS_BY_AMODE = {1, 2, 2, 2, 2, 3, 3, 4, 4, 5, 6, 6, 6, 7, 8, 8};
    private static final byte FIRST_BYTE_14B_BE = 31;
    private static final byte FIRST_BYTE_14B_LE = -1;
    private static final byte FIRST_BYTE_BE = Byte.MAX_VALUE;
    private static final byte FIRST_BYTE_LE = -2;
    private static final int[] SAMPLE_RATE_BY_SFREQ = {-1, 8000, 16000, 32000, -1, -1, 11025, 22050, 44100, -1, -1, 12000, 24000, 48000, -1, -1};
    private static final int SYNC_VALUE_14B_BE = 536864768;
    private static final int SYNC_VALUE_14B_LE = -14745368;
    private static final int SYNC_VALUE_BE = 2147385345;
    private static final int SYNC_VALUE_LE = -25230976;
    private static final int[] TWICE_BITRATE_KBPS_BY_RATE = {64, 112, 128, 192, 224, 256, 384, 448, 512, AmazonAdTask.DEFAULT_AD_WIDTH, 768, 896, 1024, 1152, 1280, 1536, 1920, 2048, 2304, 2560, 2688, 2816, 2823, 2944, 3072, 3840, 4096, 6144, 7680};

    public static boolean isSyncWord(int i) {
        return i == SYNC_VALUE_BE || i == SYNC_VALUE_LE || i == SYNC_VALUE_14B_BE || i == SYNC_VALUE_14B_LE;
    }

    public static Format parseDtsFormat(byte[] bArr, String str, String str2, DrmInitData drmInitData) {
        ParsableBitArray normalizedFrameHeader = getNormalizedFrameHeader(bArr);
        normalizedFrameHeader.skipBits(60);
        int i = CHANNELS_BY_AMODE[normalizedFrameHeader.readBits(6)];
        int i2 = SAMPLE_RATE_BY_SFREQ[normalizedFrameHeader.readBits(4)];
        int readBits = normalizedFrameHeader.readBits(5);
        int[] iArr = TWICE_BITRATE_KBPS_BY_RATE;
        int i3 = readBits >= iArr.length ? -1 : (iArr[readBits] * 1000) / 2;
        normalizedFrameHeader.skipBits(10);
        return Format.createAudioSampleFormat(str, MimeTypes.AUDIO_DTS, null, i3, -1, i + (normalizedFrameHeader.readBits(2) > 0 ? 1 : 0), i2, null, drmInitData, 0, str2);
    }

    public static int parseDtsAudioSampleCount(byte[] bArr) {
        int i;
        byte b = bArr[0];
        if (b != 31) {
            switch (b) {
                case -2:
                    i = ((bArr[4] & 252) >> 2) | ((bArr[5] & 1) << 6);
                    break;
                case -1:
                    i = ((bArr[7] & 60) >> 2) | ((bArr[4] & 7) << 4);
                    break;
                default:
                    i = ((bArr[5] & 252) >> 2) | ((bArr[4] & 1) << 6);
                    break;
            }
        } else {
            i = ((bArr[6] & 60) >> 2) | ((bArr[5] & 7) << 4);
        }
        return (i + 1) * 32;
    }

    public static int parseDtsAudioSampleCount(ByteBuffer byteBuffer) {
        int i;
        int position = byteBuffer.position();
        byte b = byteBuffer.get(position);
        if (b != 31) {
            switch (b) {
                case -2:
                    i = ((byteBuffer.get(position + 4) & 252) >> 2) | ((byteBuffer.get(position + 5) & 1) << 6);
                    break;
                case -1:
                    i = ((byteBuffer.get(position + 7) & 60) >> 2) | ((byteBuffer.get(position + 4) & 7) << 4);
                    break;
                default:
                    i = ((byteBuffer.get(position + 5) & 252) >> 2) | ((byteBuffer.get(position + 4) & 1) << 6);
                    break;
            }
        } else {
            i = ((byteBuffer.get(position + 6) & 60) >> 2) | ((byteBuffer.get(position + 5) & 7) << 4);
        }
        return (i + 1) * 32;
    }

    public static int getDtsFrameSize(byte[] bArr) {
        int i;
        boolean z = false;
        byte b = bArr[0];
        if (b != 31) {
            switch (b) {
                case -2:
                    i = (((bArr[6] & 240) >> 4) | ((bArr[4] & 3) << Ascii.FF) | ((bArr[7] & 255) << 4)) + 1;
                    break;
                case -1:
                    i = (((bArr[9] & 60) >> 2) | ((bArr[7] & 3) << Ascii.FF) | ((bArr[6] & 255) << 4)) + 1;
                    z = true;
                    break;
                default:
                    i = (((bArr[7] & 240) >> 4) | ((bArr[5] & 3) << Ascii.FF) | ((bArr[6] & 255) << 4)) + 1;
                    break;
            }
        } else {
            i = (((bArr[8] & 60) >> 2) | ((bArr[6] & 3) << Ascii.FF) | ((bArr[7] & 255) << 4)) + 1;
            z = true;
        }
        return z ? (i * 16) / 14 : i;
    }

    private static ParsableBitArray getNormalizedFrameHeader(byte[] bArr) {
        if (bArr[0] == Byte.MAX_VALUE) {
            return new ParsableBitArray(bArr);
        }
        byte[] copyOf = Arrays.copyOf(bArr, bArr.length);
        if (isLittleEndianFrameHeader(copyOf)) {
            for (int i = 0; i < copyOf.length - 1; i += 2) {
                byte b = copyOf[i];
                int i2 = i + 1;
                copyOf[i] = copyOf[i2];
                copyOf[i2] = b;
            }
        }
        ParsableBitArray parsableBitArray = new ParsableBitArray(copyOf);
        if (copyOf[0] == 31) {
            ParsableBitArray parsableBitArray2 = new ParsableBitArray(copyOf);
            while (parsableBitArray2.bitsLeft() >= 16) {
                parsableBitArray2.skipBits(2);
                parsableBitArray.putInt(parsableBitArray2.readBits(14), 14);
            }
        }
        parsableBitArray.reset(copyOf);
        return parsableBitArray;
    }

    private static boolean isLittleEndianFrameHeader(byte[] bArr) {
        return bArr[0] == -2 || bArr[0] == -1;
    }

    private DtsUtil() {
    }
}
