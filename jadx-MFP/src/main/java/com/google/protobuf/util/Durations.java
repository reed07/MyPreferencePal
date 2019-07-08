package com.google.protobuf.util;

import com.google.android.exoplayer2.C;
import com.google.common.math.IntMath;
import com.google.common.math.LongMath;
import com.google.protobuf.Duration;
import java.text.ParseException;
import java.util.Comparator;

public final class Durations {
    private static final Comparator<Duration> COMPARATOR = new Comparator<Duration>() {
        public int compare(Duration duration, Duration duration2) {
            Durations.checkValid(duration);
            Durations.checkValid(duration2);
            int compare = Long.compare(duration.getSeconds(), duration2.getSeconds());
            return compare != 0 ? compare : Integer.compare(duration.getNanos(), duration2.getNanos());
        }
    };
    static final long DURATION_SECONDS_MAX = 315576000000L;
    static final long DURATION_SECONDS_MIN = -315576000000L;
    public static final Duration MAX_VALUE = Duration.newBuilder().setSeconds(315576000000L).setNanos(999999999).build();
    public static final Duration MIN_VALUE = Duration.newBuilder().setSeconds(-315576000000L).setNanos(-999999999).build();

    public static boolean isValid(long j, int i) {
        if (j < -315576000000L || j > 315576000000L) {
            return false;
        }
        long j2 = (long) i;
        if (j2 < -999999999 || j2 >= C.NANOS_PER_SECOND) {
            return false;
        }
        int i2 = (j > 0 ? 1 : (j == 0 ? 0 : -1));
        return (i2 >= 0 && i >= 0) || (i2 <= 0 && i <= 0);
    }

    private Durations() {
    }

    public static Comparator<Duration> comparator() {
        return COMPARATOR;
    }

    public static int compare(Duration duration, Duration duration2) {
        return COMPARATOR.compare(duration, duration2);
    }

    public static boolean isValid(Duration duration) {
        return isValid(duration.getSeconds(), duration.getNanos());
    }

    public static Duration checkValid(Duration duration) {
        long seconds = duration.getSeconds();
        int nanos = duration.getNanos();
        if (isValid(seconds, nanos)) {
            return duration;
        }
        throw new IllegalArgumentException(String.format("Duration is not valid. See proto definition for valid values. Seconds (%s) must be in range [-315,576,000,000, +315,576,000,000]. Nanos (%s) must be in range [-999,999,999, +999,999,999]. Nanos must have the same sign as seconds", new Object[]{Long.valueOf(seconds), Integer.valueOf(nanos)}));
    }

    public static String toString(Duration duration) {
        checkValid(duration);
        long seconds = duration.getSeconds();
        int nanos = duration.getNanos();
        StringBuilder sb = new StringBuilder();
        if (seconds < 0 || nanos < 0) {
            sb.append("-");
            seconds = -seconds;
            nanos = -nanos;
        }
        sb.append(seconds);
        if (nanos != 0) {
            sb.append(".");
            sb.append(Timestamps.formatNanos(nanos));
        }
        sb.append("s");
        return sb.toString();
    }

    public static Duration parse(String str) throws ParseException {
        boolean z;
        if (str.isEmpty() || str.charAt(str.length() - 1) != 's') {
            StringBuilder sb = new StringBuilder();
            sb.append("Invalid duration string: ");
            sb.append(str);
            throw new ParseException(sb.toString(), 0);
        }
        if (str.charAt(0) == '-') {
            str = str.substring(1);
            z = true;
        } else {
            z = false;
        }
        String substring = str.substring(0, str.length() - 1);
        String str2 = "";
        int indexOf = substring.indexOf(46);
        if (indexOf != -1) {
            str2 = substring.substring(indexOf + 1);
            substring = substring.substring(0, indexOf);
        }
        long parseLong = Long.parseLong(substring);
        int parseNanos = str2.isEmpty() ? 0 : Timestamps.parseNanos(str2);
        if (parseLong >= 0) {
            if (z) {
                parseLong = -parseLong;
                parseNanos = -parseNanos;
            }
            try {
                return normalizedDuration(parseLong, parseNanos);
            } catch (IllegalArgumentException unused) {
                throw new ParseException("Duration value is out of range.", 0);
            }
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Invalid duration string: ");
            sb2.append(str);
            throw new ParseException(sb2.toString(), 0);
        }
    }

    public static Duration fromSeconds(long j) {
        return normalizedDuration(j, 0);
    }

    public static long toSeconds(Duration duration) {
        return checkValid(duration).getSeconds();
    }

    public static Duration fromMillis(long j) {
        return normalizedDuration(j / 1000, (int) ((j % 1000) * 1000000));
    }

    public static long toMillis(Duration duration) {
        checkValid(duration);
        return LongMath.checkedAdd(LongMath.checkedMultiply(duration.getSeconds(), 1000), ((long) duration.getNanos()) / 1000000);
    }

    public static Duration fromMicros(long j) {
        return normalizedDuration(j / 1000000, (int) ((j % 1000000) * 1000));
    }

    public static long toMicros(Duration duration) {
        checkValid(duration);
        return LongMath.checkedAdd(LongMath.checkedMultiply(duration.getSeconds(), 1000000), ((long) duration.getNanos()) / 1000);
    }

    public static Duration fromNanos(long j) {
        return normalizedDuration(j / C.NANOS_PER_SECOND, (int) (j % C.NANOS_PER_SECOND));
    }

    public static long toNanos(Duration duration) {
        checkValid(duration);
        return LongMath.checkedAdd(LongMath.checkedMultiply(duration.getSeconds(), C.NANOS_PER_SECOND), (long) duration.getNanos());
    }

    public static Duration add(Duration duration, Duration duration2) {
        checkValid(duration);
        checkValid(duration2);
        return normalizedDuration(LongMath.checkedAdd(duration.getSeconds(), duration2.getSeconds()), IntMath.checkedAdd(duration.getNanos(), duration2.getNanos()));
    }

    public static Duration subtract(Duration duration, Duration duration2) {
        checkValid(duration);
        checkValid(duration2);
        return normalizedDuration(LongMath.checkedSubtract(duration.getSeconds(), duration2.getSeconds()), IntMath.checkedSubtract(duration.getNanos(), duration2.getNanos()));
    }

    static Duration normalizedDuration(long j, int i) {
        long j2 = (long) i;
        if (j2 <= -1000000000 || j2 >= C.NANOS_PER_SECOND) {
            j = LongMath.checkedAdd(j, j2 / C.NANOS_PER_SECOND);
            i = (int) (j2 % C.NANOS_PER_SECOND);
        }
        if (j > 0 && i < 0) {
            i = (int) (((long) i) + C.NANOS_PER_SECOND);
            j--;
        }
        if (j < 0 && i > 0) {
            i = (int) (((long) i) - C.NANOS_PER_SECOND);
            j++;
        }
        return checkValid(Duration.newBuilder().setSeconds(j).setNanos(i).build());
    }
}
