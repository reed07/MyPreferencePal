package com.uacf.core.tracing;

import io.opentracing.Tracer;
import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference0;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
/* compiled from: FSTracerProvider.kt */
final class FSTracerProvider$getTracerInstance$1 extends MutablePropertyReference0 {
    FSTracerProvider$getTracerInstance$1(FSTracerProvider fSTracerProvider) {
        super(fSTracerProvider);
    }

    public String getName() {
        return "tracerinstance";
    }

    public KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(FSTracerProvider.class);
    }

    public String getSignature() {
        return "getTracerinstance()Lio/opentracing/Tracer;";
    }

    @Nullable
    public Object get() {
        return FSTracerProvider.access$getTracerinstance$p((FSTracerProvider) this.receiver);
    }

    public void set(@Nullable Object obj) {
        FSTracerProvider.tracerinstance = (Tracer) obj;
    }
}
