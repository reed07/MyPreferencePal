package io.opencensus.metrics.export;

import javax.annotation.concurrent.Immutable;

@Immutable
public abstract class Summary {

    @Immutable
    public static abstract class Snapshot {

        @Immutable
        public static abstract class ValueAtPercentile {
        }
    }

    Summary() {
    }
}
