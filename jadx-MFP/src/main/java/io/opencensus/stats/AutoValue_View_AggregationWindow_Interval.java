package io.opencensus.stats;

import io.opencensus.common.Duration;
import io.opencensus.stats.View.AggregationWindow.Interval;
import javax.annotation.concurrent.Immutable;

@Immutable
@Deprecated
final class AutoValue_View_AggregationWindow_Interval extends Interval {
    private final Duration duration;

    AutoValue_View_AggregationWindow_Interval(Duration duration2) {
        if (duration2 != null) {
            this.duration = duration2;
            return;
        }
        throw new NullPointerException("Null duration");
    }

    public Duration getDuration() {
        return this.duration;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Interval{duration=");
        sb.append(this.duration);
        sb.append("}");
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Interval)) {
            return false;
        }
        return this.duration.equals(((Interval) obj).getDuration());
    }

    public int hashCode() {
        return this.duration.hashCode() ^ 1000003;
    }
}
