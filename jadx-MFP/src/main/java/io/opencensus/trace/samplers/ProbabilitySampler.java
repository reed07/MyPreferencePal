package io.opencensus.trace.samplers;

import io.opencensus.internal.Utils;
import io.opencensus.trace.Sampler;
import javax.annotation.concurrent.Immutable;

@Immutable
abstract class ProbabilitySampler extends Sampler {
    /* access modifiers changed from: 0000 */
    public abstract long getIdUpperBound();

    /* access modifiers changed from: 0000 */
    public abstract double getProbability();

    ProbabilitySampler() {
    }

    static ProbabilitySampler create(double d) {
        int i = (d > 0.0d ? 1 : (d == 0.0d ? 0 : -1));
        Utils.checkArgument(i >= 0 && d <= 1.0d, "probability must be in range [0.0, 1.0]");
        long j = i == 0 ? Long.MIN_VALUE : d == 1.0d ? Long.MAX_VALUE : (long) (9.223372036854776E18d * d);
        return new AutoValue_ProbabilitySampler(d, j);
    }
}
