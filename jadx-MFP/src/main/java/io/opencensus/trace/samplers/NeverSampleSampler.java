package io.opencensus.trace.samplers;

import io.opencensus.trace.Sampler;
import javax.annotation.concurrent.Immutable;

@Immutable
final class NeverSampleSampler extends Sampler {
    public String toString() {
        return "NeverSampleSampler";
    }

    NeverSampleSampler() {
    }
}
