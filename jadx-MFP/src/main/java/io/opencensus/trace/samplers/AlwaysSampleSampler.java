package io.opencensus.trace.samplers;

import io.opencensus.trace.Sampler;
import javax.annotation.concurrent.Immutable;

@Immutable
final class AlwaysSampleSampler extends Sampler {
    public String toString() {
        return "AlwaysSampleSampler";
    }

    AlwaysSampleSampler() {
    }
}
