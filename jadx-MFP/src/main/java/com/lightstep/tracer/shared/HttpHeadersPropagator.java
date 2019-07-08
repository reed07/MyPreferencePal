package com.lightstep.tracer.shared;

import io.opentracing.propagation.TextMap;

class HttpHeadersPropagator implements Propagator<TextMap> {
    HttpHeadersPropagator() {
    }

    public void inject(SpanContext spanContext, TextMap textMap) {
        TEXT_MAP.inject(spanContext, textMap);
    }

    public SpanContext extract(TextMap textMap) {
        return TEXT_MAP.extract(textMap);
    }
}
