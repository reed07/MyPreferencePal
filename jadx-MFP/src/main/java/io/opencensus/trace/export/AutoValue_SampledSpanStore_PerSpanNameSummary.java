package io.opencensus.trace.export;

import io.opencensus.trace.Status.CanonicalCode;
import io.opencensus.trace.export.SampledSpanStore.LatencyBucketBoundaries;
import io.opencensus.trace.export.SampledSpanStore.PerSpanNameSummary;
import java.util.Map;

final class AutoValue_SampledSpanStore_PerSpanNameSummary extends PerSpanNameSummary {
    private final Map<CanonicalCode, Integer> numbersOfErrorSampledSpans;
    private final Map<LatencyBucketBoundaries, Integer> numbersOfLatencySampledSpans;

    AutoValue_SampledSpanStore_PerSpanNameSummary(Map<LatencyBucketBoundaries, Integer> map, Map<CanonicalCode, Integer> map2) {
        if (map != null) {
            this.numbersOfLatencySampledSpans = map;
            if (map2 != null) {
                this.numbersOfErrorSampledSpans = map2;
                return;
            }
            throw new NullPointerException("Null numbersOfErrorSampledSpans");
        }
        throw new NullPointerException("Null numbersOfLatencySampledSpans");
    }

    public Map<LatencyBucketBoundaries, Integer> getNumbersOfLatencySampledSpans() {
        return this.numbersOfLatencySampledSpans;
    }

    public Map<CanonicalCode, Integer> getNumbersOfErrorSampledSpans() {
        return this.numbersOfErrorSampledSpans;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PerSpanNameSummary{numbersOfLatencySampledSpans=");
        sb.append(this.numbersOfLatencySampledSpans);
        sb.append(", numbersOfErrorSampledSpans=");
        sb.append(this.numbersOfErrorSampledSpans);
        sb.append("}");
        return sb.toString();
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PerSpanNameSummary)) {
            return false;
        }
        PerSpanNameSummary perSpanNameSummary = (PerSpanNameSummary) obj;
        if (!this.numbersOfLatencySampledSpans.equals(perSpanNameSummary.getNumbersOfLatencySampledSpans()) || !this.numbersOfErrorSampledSpans.equals(perSpanNameSummary.getNumbersOfErrorSampledSpans())) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return ((this.numbersOfLatencySampledSpans.hashCode() ^ 1000003) * 1000003) ^ this.numbersOfErrorSampledSpans.hashCode();
    }
}
