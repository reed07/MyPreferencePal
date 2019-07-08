package io.opencensus.trace.unsafe;

import io.grpc.Context;
import io.grpc.Context.Key;
import io.opencensus.trace.Span;

public final class ContextUtils {
    public static final Key<Span> CONTEXT_SPAN_KEY = Context.key("opencensus-trace-span-key");

    private ContextUtils() {
    }
}
