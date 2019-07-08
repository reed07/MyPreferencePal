package io.opencensus.trace;

import io.opencensus.internal.Utils;
import javax.annotation.Nullable;

public abstract class SpanBuilder {

    static final class NoopSpanBuilder extends SpanBuilder {
        public SpanBuilder setRecordEvents(boolean z) {
            return this;
        }

        static NoopSpanBuilder createWithParent(String str, @Nullable Span span) {
            return new NoopSpanBuilder(str);
        }

        static NoopSpanBuilder createWithRemoteParent(String str, @Nullable SpanContext spanContext) {
            return new NoopSpanBuilder(str);
        }

        public Span startSpan() {
            return BlankSpan.INSTANCE;
        }

        private NoopSpanBuilder(String str) {
            Utils.checkNotNull(str, "name");
        }
    }

    public abstract SpanBuilder setRecordEvents(boolean z);

    public abstract Span startSpan();
}
