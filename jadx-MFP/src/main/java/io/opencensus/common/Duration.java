package io.opencensus.common;

import com.google.protobuf.util.TimeUtil;
import javax.annotation.concurrent.Immutable;

@Immutable
public abstract class Duration implements Comparable<Duration> {
    public abstract int getNanos();

    public abstract long getSeconds();

    public static Duration create(long j, int i) {
        if (j < TimeUtil.DURATION_SECONDS_MIN) {
            StringBuilder sb = new StringBuilder();
            sb.append("'seconds' is less than minimum (-315576000000): ");
            sb.append(j);
            throw new IllegalArgumentException(sb.toString());
        } else if (j > TimeUtil.DURATION_SECONDS_MAX) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("'seconds' is greater than maximum (315576000000): ");
            sb2.append(j);
            throw new IllegalArgumentException(sb2.toString());
        } else if (i < -999999999) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("'nanos' is less than minimum (-999999999): ");
            sb3.append(i);
            throw new IllegalArgumentException(sb3.toString());
        } else if (i <= 999999999) {
            int i2 = (j > 0 ? 1 : (j == 0 ? 0 : -1));
            if ((i2 >= 0 || i <= 0) && (i2 <= 0 || i >= 0)) {
                return new AutoValue_Duration(j, i);
            }
            StringBuilder sb4 = new StringBuilder();
            sb4.append("'seconds' and 'nanos' have inconsistent sign: seconds=");
            sb4.append(j);
            sb4.append(", nanos=");
            sb4.append(i);
            throw new IllegalArgumentException(sb4.toString());
        } else {
            StringBuilder sb5 = new StringBuilder();
            sb5.append("'nanos' is greater than maximum (999999999): ");
            sb5.append(i);
            throw new IllegalArgumentException(sb5.toString());
        }
    }

    public int compareTo(Duration duration) {
        int compareLongs = TimeUtils.compareLongs(getSeconds(), duration.getSeconds());
        if (compareLongs != 0) {
            return compareLongs;
        }
        return TimeUtils.compareLongs((long) getNanos(), (long) duration.getNanos());
    }

    Duration() {
    }
}
