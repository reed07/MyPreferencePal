package com.brightcove.player;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.UUID;

public class Constants {
    public static final int ENCODING_AC3 = 5;
    public static final int ENCODING_DTS = 7;
    public static final int ENCODING_DTS_HD = 8;
    public static final int ENCODING_E_AC3 = 6;
    public static final int ENCODING_INVALID = 0;
    public static final int ENCODING_PCM_16BIT = 2;
    public static final int ENCODING_PCM_24BIT = Integer.MIN_VALUE;
    public static final int ENCODING_PCM_32BIT = 1073741824;
    public static final int ENCODING_PCM_8BIT = 3;
    public static final int INDEX_UNSET = -1;
    public static final int LENGTH_UNSET = -1;
    public static final long MATCH_LONGEST_US = -2;
    public static final int NO_VALUE = -1;
    public static final UUID PLAYREADY_UUID = new UUID(-7348484286925749626L, -6083546864340672619L);
    public static final int POSITION_UNSET = -1;
    public static final int SELECTION_FLAG_AUTOSELECT = 4;
    public static final int SELECTION_FLAG_DEFAULT = 1;
    public static final int SELECTION_FLAG_FORCED = 2;
    public static final int SELECTION_REASON_ADAPTIVE = 3;
    public static final int SELECTION_REASON_INITIAL = 1;
    public static final int SELECTION_REASON_MANUAL = 2;
    public static final int SELECTION_REASON_TRICK_PLAY = 4;
    public static final int SELECTION_REASON_UNKNOWN = 0;
    public static final int STEREO_MODE_LEFT_RIGHT = 2;
    public static final int STEREO_MODE_MONO = 0;
    public static final int STEREO_MODE_TOP_BOTTOM = 1;
    public static final long TIME_END_OF_SOURCE = Long.MIN_VALUE;
    public static final long TIME_UNSET = -9223372036854775807L;
    public static final long UNKNOWN_TIME_US = -1;
    public static final UUID WIDEVINE_UUID = new UUID(-1301668207276963122L, -6645017420763422227L);

    @Retention(RetentionPolicy.SOURCE)
    public @interface Encoding {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface PcmEncoding {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface SelectionFlags {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface SelectionReason {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface StereoMode {
    }

    public static long msToUs(long j) {
        if (j == -9223372036854775807L) {
            return -9223372036854775807L;
        }
        return 1000 * j;
    }

    public static long usToMs(long j) {
        if (j == -9223372036854775807L) {
            return -9223372036854775807L;
        }
        return j / 1000;
    }
}
