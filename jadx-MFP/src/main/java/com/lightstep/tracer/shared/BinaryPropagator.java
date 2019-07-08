package com.lightstep.tracer.shared;

import java.nio.ByteBuffer;

class BinaryPropagator implements Propagator<ByteBuffer> {
    public SpanContext extract(ByteBuffer byteBuffer) {
        return null;
    }

    public void inject(SpanContext spanContext, ByteBuffer byteBuffer) {
    }

    BinaryPropagator() {
    }
}
