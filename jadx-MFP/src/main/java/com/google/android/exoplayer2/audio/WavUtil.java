package com.google.android.exoplayer2.audio;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.util.Util;

public final class WavUtil {
    public static final int DATA_FOURCC = Util.getIntegerCodeForString("data");
    public static final int FMT_FOURCC = Util.getIntegerCodeForString("fmt ");
    public static final int RIFF_FOURCC = Util.getIntegerCodeForString("RIFF");
    private static final int TYPE_A_LAW = 6;
    private static final int TYPE_FLOAT = 3;
    private static final int TYPE_MU_LAW = 7;
    private static final int TYPE_PCM = 1;
    private static final int TYPE_WAVE_FORMAT_EXTENSIBLE = 65534;
    public static final int WAVE_FOURCC = Util.getIntegerCodeForString("WAVE");

    public static int getTypeForEncoding(int i) {
        if (i != Integer.MIN_VALUE) {
            if (i == 268435456) {
                return 7;
            }
            if (i == 536870912) {
                return 6;
            }
            if (i != 1073741824) {
                switch (i) {
                    case 2:
                    case 3:
                        break;
                    case 4:
                        return 3;
                    default:
                        throw new IllegalArgumentException();
                }
            }
        }
        return 1;
    }

    public static int getEncodingForType(int i, int i2) {
        if (i != 1) {
            int i3 = 0;
            if (i == 3) {
                if (i2 == 32) {
                    i3 = 4;
                }
                return i3;
            } else if (i != TYPE_WAVE_FORMAT_EXTENSIBLE) {
                switch (i) {
                    case 6:
                        return 536870912;
                    case 7:
                        return C.ENCODING_PCM_MU_LAW;
                    default:
                        return 0;
                }
            }
        }
        return Util.getPcmEncoding(i2);
    }

    private WavUtil() {
    }
}
