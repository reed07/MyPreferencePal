package io.opentracing.noop;

public final class NoopTracerFactory {
    public static NoopTracer create() {
        return NoopTracerImpl.INSTANCE;
    }

    private NoopTracerFactory() {
    }
}
