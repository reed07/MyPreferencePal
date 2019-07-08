package io.opencensus.trace.samplers;

import io.opencensus.trace.Sampler;

public final class Samplers {
    private static final Sampler ALWAYS_SAMPLE = new AlwaysSampleSampler();
    private static final Sampler NEVER_SAMPLE = new NeverSampleSampler();

    private Samplers() {
    }

    public static Sampler probabilitySampler(double d) {
        return ProbabilitySampler.create(d);
    }
}
