package com.brightcove.player.drm;

import android.support.annotation.NonNull;
import android.util.Pair;
import com.brightcove.player.util.Convert;
import java.util.Map;

public class BrightcoveDrmUtil {
    private static final String LICENSE_DURATION_REMAINING = "LicenseDurationRemaining";
    private static final String PLAYBACK_DURATION_REMAINING = "PlaybackDurationRemaining";

    protected BrightcoveDrmUtil() {
    }

    public static long getLongValue(Map<String, String> map, @NonNull String str, long j) {
        return (map == null || !map.containsKey(str)) ? j : Convert.toLong(map.get(str), j);
    }

    @Deprecated
    public static long getRemainingLicenseDuration(@NonNull BrightcoveDrmSession brightcoveDrmSession) {
        return getLongValue(brightcoveDrmSession.queryKeyStatus(), "LicenseDurationRemaining", -9223372036854775807L);
    }

    @Deprecated
    public static long getRemainingPlaybackDuration(@NonNull BrightcoveDrmSession brightcoveDrmSession) {
        return getLongValue(brightcoveDrmSession.queryKeyStatus(), "PlaybackDurationRemaining", -9223372036854775807L);
    }

    @Deprecated
    public static Pair<Long, Long> getLicenseDuration(BrightcoveDrmSession brightcoveDrmSession) {
        Map queryKeyStatus = brightcoveDrmSession.queryKeyStatus();
        return new Pair<>(Long.valueOf(getLongValue(queryKeyStatus, "LicenseDurationRemaining", -9223372036854775807L)), Long.valueOf(getLongValue(queryKeyStatus, "PlaybackDurationRemaining", -9223372036854775807L)));
    }
}
