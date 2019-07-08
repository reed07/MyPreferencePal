package io.opencensus.trace;

import java.util.Arrays;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
public final class SpanContext {
    public static final SpanContext INVALID = new SpanContext(TraceId.INVALID, SpanId.INVALID, TraceOptions.DEFAULT, TRACESTATE_DEFAULT);
    private static final Tracestate TRACESTATE_DEFAULT = Tracestate.builder().build();
    private final SpanId spanId;
    private final TraceId traceId;
    private final TraceOptions traceOptions;
    private final Tracestate tracestate;

    public TraceOptions getTraceOptions() {
        return this.traceOptions;
    }

    public boolean equals(@Nullable Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SpanContext)) {
            return false;
        }
        SpanContext spanContext = (SpanContext) obj;
        if (!this.traceId.equals(spanContext.traceId) || !this.spanId.equals(spanContext.spanId) || !this.traceOptions.equals(spanContext.traceOptions)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.traceId, this.spanId, this.traceOptions});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SpanContext{traceId=");
        sb.append(this.traceId);
        sb.append(", spanId=");
        sb.append(this.spanId);
        sb.append(", traceOptions=");
        sb.append(this.traceOptions);
        sb.append("}");
        return sb.toString();
    }

    private SpanContext(TraceId traceId2, SpanId spanId2, TraceOptions traceOptions2, Tracestate tracestate2) {
        this.traceId = traceId2;
        this.spanId = spanId2;
        this.traceOptions = traceOptions2;
        this.tracestate = tracestate2;
    }
}
