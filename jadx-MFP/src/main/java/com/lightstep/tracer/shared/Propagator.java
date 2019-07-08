package com.lightstep.tracer.shared;

import io.opentracing.propagation.TextMap;
import java.nio.ByteBuffer;

public interface Propagator<C> {
    public static final Propagator<ByteBuffer> BINARY = new BinaryPropagator();
    public static final Propagator<TextMap> HTTP_HEADERS = new HttpHeadersPropagator();
    public static final Propagator<TextMap> TEXT_MAP = new TextMapPropagator();

    SpanContext extract(C c);

    void inject(SpanContext spanContext, C c);
}
