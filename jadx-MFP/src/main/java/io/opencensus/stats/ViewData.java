package io.opencensus.stats;

import io.opencensus.common.Function;
import javax.annotation.concurrent.Immutable;

@Immutable
public abstract class ViewData {

    /* renamed from: io.opencensus.stats.ViewData$1 reason: invalid class name */
    class AnonymousClass1 implements Function<CumulativeData, ViewData> {
    }

    @Immutable
    @Deprecated
    public static abstract class AggregationWindowData {

        @Immutable
        @Deprecated
        public static abstract class CumulativeData extends AggregationWindowData {
            CumulativeData() {
                super(null);
            }
        }

        @Immutable
        @Deprecated
        public static abstract class IntervalData extends AggregationWindowData {
            IntervalData() {
                super(null);
            }
        }

        /* synthetic */ AggregationWindowData(AnonymousClass1 r1) {
            this();
        }

        private AggregationWindowData() {
        }
    }

    ViewData() {
    }
}
