package io.opencensus.stats;

import io.opencensus.internal.Utils;
import javax.annotation.concurrent.Immutable;

@Immutable
public abstract class Aggregation {

    @Immutable
    public static abstract class Count extends Aggregation {
        private static final Count INSTANCE = new AutoValue_Aggregation_Count();

        Count() {
            super();
        }

        public static Count create() {
            return INSTANCE;
        }
    }

    @Immutable
    public static abstract class Distribution extends Aggregation {
        public abstract BucketBoundaries getBucketBoundaries();

        Distribution() {
            super();
        }

        public static Distribution create(BucketBoundaries bucketBoundaries) {
            Utils.checkNotNull(bucketBoundaries, "bucketBoundaries");
            return new AutoValue_Aggregation_Distribution(bucketBoundaries);
        }
    }

    @Immutable
    public static abstract class LastValue extends Aggregation {
        private static final LastValue INSTANCE = new AutoValue_Aggregation_LastValue();

        LastValue() {
            super();
        }
    }

    @Immutable
    @Deprecated
    public static abstract class Mean extends Aggregation {
        private static final Mean INSTANCE = new AutoValue_Aggregation_Mean();

        Mean() {
            super();
        }

        public static Mean create() {
            return INSTANCE;
        }
    }

    @Immutable
    public static abstract class Sum extends Aggregation {
        private static final Sum INSTANCE = new AutoValue_Aggregation_Sum();

        Sum() {
            super();
        }
    }

    private Aggregation() {
    }
}
