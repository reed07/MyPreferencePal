package io.opencensus.stats;

import io.opencensus.stats.Measure.MeasureDouble;
import io.opencensus.stats.Measure.MeasureLong;
import io.opencensus.tags.TagContext;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class MeasureMap {
    public abstract MeasureMap put(MeasureDouble measureDouble, double d);

    public abstract MeasureMap put(MeasureLong measureLong, long j);

    public abstract void record(TagContext tagContext);
}
