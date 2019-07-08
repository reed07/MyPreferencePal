package io.opencensus.trace;

import javax.annotation.Nullable;

public abstract class Tracer {
    private static final NoopTracer noopTracer = new NoopTracer();

    private static final class NoopTracer extends Tracer {
        public SpanBuilder spanBuilderWithExplicitParent(String str, @Nullable Span span) {
            return NoopSpanBuilder.createWithParent(str, span);
        }

        public SpanBuilder spanBuilderWithRemoteParent(String str, @Nullable SpanContext spanContext) {
            return NoopSpanBuilder.createWithRemoteParent(str, spanContext);
        }

        private NoopTracer() {
        }
    }

    public abstract SpanBuilder spanBuilderWithExplicitParent(String str, @Nullable Span span);

    public abstract SpanBuilder spanBuilderWithRemoteParent(String str, @Nullable SpanContext spanContext);

    static Tracer getNoopTracer() {
        return noopTracer;
    }

    protected Tracer() {
    }
}
