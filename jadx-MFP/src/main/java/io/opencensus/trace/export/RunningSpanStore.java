package io.opencensus.trace.export;

import io.opencensus.internal.Utils;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public abstract class RunningSpanStore {
    private static final RunningSpanStore NOOP_RUNNING_SPAN_STORE = new NoopRunningSpanStore();

    @Immutable
    public static abstract class Filter {
        Filter() {
        }
    }

    private static final class NoopRunningSpanStore extends RunningSpanStore {
        private static final Summary EMPTY_SUMMARY = Summary.create(Collections.emptyMap());

        private NoopRunningSpanStore() {
        }
    }

    @Immutable
    public static abstract class PerSpanNameSummary {
        PerSpanNameSummary() {
        }
    }

    @Immutable
    public static abstract class Summary {
        public abstract Map<String, PerSpanNameSummary> getPerSpanNameSummary();

        Summary() {
        }

        public static Summary create(Map<String, PerSpanNameSummary> map) {
            return new AutoValue_RunningSpanStore_Summary(Collections.unmodifiableMap(new HashMap((Map) Utils.checkNotNull(map, "perSpanNameSummary"))));
        }
    }

    protected RunningSpanStore() {
    }
}
