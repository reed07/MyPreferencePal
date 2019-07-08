package io.opencensus.trace.config;

import io.opencensus.internal.Utils;
import io.opencensus.trace.Sampler;
import io.opencensus.trace.samplers.Samplers;
import javax.annotation.concurrent.Immutable;

@Immutable
public abstract class TraceParams {
    public static final TraceParams DEFAULT = builder().setSampler(DEFAULT_SAMPLER).setMaxNumberOfAttributes(32).setMaxNumberOfAnnotations(32).setMaxNumberOfMessageEvents(128).setMaxNumberOfLinks(32).build();
    private static final Sampler DEFAULT_SAMPLER = Samplers.probabilitySampler(1.0E-4d);

    public static abstract class Builder {
        /* access modifiers changed from: 0000 */
        public abstract TraceParams autoBuild();

        public abstract Builder setMaxNumberOfAnnotations(int i);

        public abstract Builder setMaxNumberOfAttributes(int i);

        public abstract Builder setMaxNumberOfLinks(int i);

        public abstract Builder setMaxNumberOfMessageEvents(int i);

        public abstract Builder setSampler(Sampler sampler);

        public TraceParams build() {
            TraceParams autoBuild = autoBuild();
            boolean z = true;
            Utils.checkArgument(autoBuild.getMaxNumberOfAttributes() > 0, "maxNumberOfAttributes");
            Utils.checkArgument(autoBuild.getMaxNumberOfAnnotations() > 0, "maxNumberOfAnnotations");
            Utils.checkArgument(autoBuild.getMaxNumberOfMessageEvents() > 0, "maxNumberOfMessageEvents");
            if (autoBuild.getMaxNumberOfLinks() <= 0) {
                z = false;
            }
            Utils.checkArgument(z, "maxNumberOfLinks");
            return autoBuild;
        }
    }

    public abstract int getMaxNumberOfAnnotations();

    public abstract int getMaxNumberOfAttributes();

    public abstract int getMaxNumberOfLinks();

    public abstract int getMaxNumberOfMessageEvents();

    public abstract Sampler getSampler();

    private static Builder builder() {
        return new Builder();
    }
}
