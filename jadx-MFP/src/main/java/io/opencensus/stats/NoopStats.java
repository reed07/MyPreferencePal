package io.opencensus.stats;

import io.opencensus.common.Timestamp;
import io.opencensus.internal.Utils;
import io.opencensus.stats.Measure.MeasureDouble;
import io.opencensus.stats.Measure.MeasureLong;
import io.opencensus.stats.View.Name;
import io.opencensus.tags.TagContext;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

final class NoopStats {

    private static final class NoopMeasureMap extends MeasureMap {
        private static final Logger logger = Logger.getLogger(NoopMeasureMap.class.getName());
        private boolean hasUnsupportedValues;

        private NoopMeasureMap() {
        }

        public MeasureMap put(MeasureDouble measureDouble, double d) {
            if (d < 0.0d) {
                this.hasUnsupportedValues = true;
            }
            return this;
        }

        public MeasureMap put(MeasureLong measureLong, long j) {
            if (j < 0) {
                this.hasUnsupportedValues = true;
            }
            return this;
        }

        public void record(TagContext tagContext) {
            Utils.checkNotNull(tagContext, "tags");
            if (this.hasUnsupportedValues) {
                logger.log(Level.WARNING, "Dropping values, value to record must be non-negative.");
            }
        }
    }

    @ThreadSafe
    private static final class NoopStatsComponent extends StatsComponent {
        private final ViewManager viewManager;

        private NoopStatsComponent() {
            this.viewManager = NoopStats.newNoopViewManager();
        }

        public StatsRecorder getStatsRecorder() {
            return NoopStats.getNoopStatsRecorder();
        }
    }

    @Immutable
    private static final class NoopStatsRecorder extends StatsRecorder {
        static final StatsRecorder INSTANCE = new NoopStatsRecorder();

        private NoopStatsRecorder() {
        }

        public MeasureMap newMeasureMap() {
            return NoopStats.newNoopMeasureMap();
        }
    }

    @ThreadSafe
    private static final class NoopViewManager extends ViewManager {
        private static final Timestamp ZERO_TIMESTAMP = Timestamp.create(0, 0);
        @GuardedBy("registeredViews")
        private final Map<Name, View> registeredViews;

        private NoopViewManager() {
            this.registeredViews = new HashMap();
        }
    }

    private NoopStats() {
    }

    static StatsComponent newNoopStatsComponent() {
        return new NoopStatsComponent();
    }

    static StatsRecorder getNoopStatsRecorder() {
        return NoopStatsRecorder.INSTANCE;
    }

    static MeasureMap newNoopMeasureMap() {
        return new NoopMeasureMap();
    }

    static ViewManager newNoopViewManager() {
        return new NoopViewManager();
    }
}
