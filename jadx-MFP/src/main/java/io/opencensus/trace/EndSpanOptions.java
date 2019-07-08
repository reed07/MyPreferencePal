package io.opencensus.trace;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
public abstract class EndSpanOptions {
    public static final EndSpanOptions DEFAULT = builder().build();

    public static abstract class Builder {
        public abstract EndSpanOptions build();

        public abstract Builder setSampleToLocalSpanStore(boolean z);

        public abstract Builder setStatus(Status status);

        Builder() {
        }
    }

    public abstract boolean getSampleToLocalSpanStore();

    @Nullable
    public abstract Status getStatus();

    public static Builder builder() {
        return new Builder().setSampleToLocalSpanStore(false);
    }

    EndSpanOptions() {
    }
}
