package io.opencensus.trace.export;

import io.opencensus.trace.export.RunningSpanStore.PerSpanNameSummary;
import io.opencensus.trace.export.RunningSpanStore.Summary;
import java.util.Map;

final class AutoValue_RunningSpanStore_Summary extends Summary {
    private final Map<String, PerSpanNameSummary> perSpanNameSummary;

    AutoValue_RunningSpanStore_Summary(Map<String, PerSpanNameSummary> map) {
        if (map != null) {
            this.perSpanNameSummary = map;
            return;
        }
        throw new NullPointerException("Null perSpanNameSummary");
    }

    public Map<String, PerSpanNameSummary> getPerSpanNameSummary() {
        return this.perSpanNameSummary;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Summary{perSpanNameSummary=");
        sb.append(this.perSpanNameSummary);
        sb.append("}");
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Summary)) {
            return false;
        }
        return this.perSpanNameSummary.equals(((Summary) obj).getPerSpanNameSummary());
    }

    public int hashCode() {
        return this.perSpanNameSummary.hashCode() ^ 1000003;
    }
}
