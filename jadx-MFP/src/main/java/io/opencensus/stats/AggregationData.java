package io.opencensus.stats;

import javax.annotation.concurrent.Immutable;

@Immutable
public abstract class AggregationData {

    @Immutable
    public static abstract class CountData extends AggregationData {
        CountData() {
            super();
        }
    }

    @Immutable
    public static abstract class DistributionData extends AggregationData {

        @Immutable
        public static abstract class Exemplar {
            Exemplar() {
            }
        }

        DistributionData() {
            super();
        }
    }

    @Immutable
    public static abstract class LastValueDataDouble extends AggregationData {
        LastValueDataDouble() {
            super();
        }
    }

    @Immutable
    public static abstract class LastValueDataLong extends AggregationData {
        LastValueDataLong() {
            super();
        }
    }

    @Immutable
    @Deprecated
    public static abstract class MeanData extends AggregationData {
        MeanData() {
            super();
        }
    }

    @Immutable
    public static abstract class SumDataDouble extends AggregationData {
        SumDataDouble() {
            super();
        }
    }

    @Immutable
    public static abstract class SumDataLong extends AggregationData {
        SumDataLong() {
            super();
        }
    }

    private AggregationData() {
    }
}
