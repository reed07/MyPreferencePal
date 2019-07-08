package io.opencensus.trace.export;

import io.opencensus.internal.Utils;
import io.opencensus.trace.Status.CanonicalCode;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public abstract class SampledSpanStore {

    @Immutable
    public static abstract class ErrorFilter {
        ErrorFilter() {
        }
    }

    public enum LatencyBucketBoundaries {
        ZERO_MICROSx10(0, TimeUnit.MICROSECONDS.toNanos(10)),
        MICROSx10_MICROSx100(TimeUnit.MICROSECONDS.toNanos(10), TimeUnit.MICROSECONDS.toNanos(100)),
        MICROSx100_MILLIx1(TimeUnit.MICROSECONDS.toNanos(100), TimeUnit.MILLISECONDS.toNanos(1)),
        MILLIx1_MILLIx10(TimeUnit.MILLISECONDS.toNanos(1), TimeUnit.MILLISECONDS.toNanos(10)),
        MILLIx10_MILLIx100(TimeUnit.MILLISECONDS.toNanos(10), TimeUnit.MILLISECONDS.toNanos(100)),
        MILLIx100_SECONDx1(TimeUnit.MILLISECONDS.toNanos(100), TimeUnit.SECONDS.toNanos(1)),
        SECONDx1_SECONDx10(TimeUnit.SECONDS.toNanos(1), TimeUnit.SECONDS.toNanos(10)),
        SECONDx10_SECONDx100(TimeUnit.SECONDS.toNanos(10), TimeUnit.SECONDS.toNanos(100)),
        SECONDx100_MAX(TimeUnit.SECONDS.toNanos(100), Long.MAX_VALUE);
        
        private final long latencyLowerNs;
        private final long latencyUpperNs;

        private LatencyBucketBoundaries(long j, long j2) {
            this.latencyLowerNs = j;
            this.latencyUpperNs = j2;
        }
    }

    @Immutable
    public static abstract class LatencyFilter {
        LatencyFilter() {
        }
    }

    @ThreadSafe
    private static final class NoopSampledSpanStore extends SampledSpanStore {
        private static final PerSpanNameSummary EMPTY_PER_SPAN_NAME_SUMMARY = PerSpanNameSummary.create(Collections.emptyMap(), Collections.emptyMap());
        @GuardedBy("registeredSpanNames")
        private final Set<String> registeredSpanNames;

        private NoopSampledSpanStore() {
            this.registeredSpanNames = new HashSet();
        }
    }

    @Immutable
    public static abstract class PerSpanNameSummary {
        public abstract Map<CanonicalCode, Integer> getNumbersOfErrorSampledSpans();

        public abstract Map<LatencyBucketBoundaries, Integer> getNumbersOfLatencySampledSpans();

        PerSpanNameSummary() {
        }

        public static PerSpanNameSummary create(Map<LatencyBucketBoundaries, Integer> map, Map<CanonicalCode, Integer> map2) {
            return new AutoValue_SampledSpanStore_PerSpanNameSummary(Collections.unmodifiableMap(new HashMap((Map) Utils.checkNotNull(map, "numbersOfLatencySampledSpans"))), Collections.unmodifiableMap(new HashMap((Map) Utils.checkNotNull(map2, "numbersOfErrorSampledSpans"))));
        }
    }

    @Immutable
    public static abstract class Summary {
        Summary() {
        }
    }

    protected SampledSpanStore() {
    }

    static SampledSpanStore newNoopSampledSpanStore() {
        return new NoopSampledSpanStore();
    }
}
