package io.opencensus.trace.config;

public abstract class TraceConfig {
    private static final NoopTraceConfig NOOP_TRACE_CONFIG = new NoopTraceConfig();

    private static final class NoopTraceConfig extends TraceConfig {
        private NoopTraceConfig() {
        }
    }
}
