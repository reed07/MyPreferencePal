package io.opencensus.trace.export;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public abstract class SpanExporter {
    private static final SpanExporter NOOP_SPAN_EXPORTER = new NoopSpanExporter();

    public static abstract class Handler {
    }

    private static final class NoopSpanExporter extends SpanExporter {
        private NoopSpanExporter() {
        }
    }
}
