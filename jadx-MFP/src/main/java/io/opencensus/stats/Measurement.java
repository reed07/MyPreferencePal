package io.opencensus.stats;

import javax.annotation.concurrent.Immutable;

@Immutable
public abstract class Measurement {

    @Immutable
    public static abstract class MeasurementDouble extends Measurement {
        MeasurementDouble() {
            super();
        }
    }

    @Immutable
    public static abstract class MeasurementLong extends Measurement {
        MeasurementLong() {
            super();
        }
    }

    private Measurement() {
    }
}
