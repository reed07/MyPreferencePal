package io.opencensus.common;

import com.google.protobuf.util.TimeUtil;
import javax.annotation.concurrent.Immutable;

@Immutable
public abstract class Timestamp implements Comparable<Timestamp> {
    public abstract int getNanos();

    public abstract long getSeconds();

    Timestamp() {
    }

    public static Timestamp create(long j, int i) {
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
        } else if (i < 0) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("'nanos' is less than zero: ");
            sb3.append(i);
            throw new IllegalArgumentException(sb3.toString());
        } else if (i <= 999999999) {
            return new AutoValue_Timestamp(j, i);
        } else {
            StringBuilder sb4 = new StringBuilder();
            sb4.append("'nanos' is greater than maximum (999999999): ");
            sb4.append(i);
            throw new IllegalArgumentException(sb4.toString());
        }
    }

    public int compareTo(Timestamp timestamp) {
        int compareLongs = TimeUtils.compareLongs(getSeconds(), timestamp.getSeconds());
        if (compareLongs != 0) {
            return compareLongs;
        }
        return TimeUtils.compareLongs((long) getNanos(), (long) timestamp.getNanos());
    }
}
