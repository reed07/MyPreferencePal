package com.uacf.core.tracing;

import android.content.Context;
import io.opentracing.Tracer;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J$\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\f2\b\b\u0002\u0010\u0011\u001a\u00020\u0012H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004XD¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XD¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007XD¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XD¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XD¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007XD¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX.¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/uacf/core/tracing/FSTracerProvider;", "", "()V", "ACCESS_TOKEN", "", "COLLECTOR_HOST", "COLLECTOR_PORT", "", "COLLECTOR_PROTOCOL", "COMPONENT_NAME", "VERBOSITY_ALL_ERRORS_LOCAL", "tracerinstance", "Lio/opentracing/Tracer;", "getTracerInstance", "context", "Landroid/content/Context;", "clientProvidedTracer", "tracingSetting", "Lcom/uacf/core/tracing/FSTracingSetting;", "library_release"}, k = 1, mv = {1, 1, 13})
/* compiled from: FSTracerProvider.kt */
public final class FSTracerProvider {
    private static final String ACCESS_TOKEN = ACCESS_TOKEN;
    private static final String COLLECTOR_HOST = COLLECTOR_HOST;
    private static final int COLLECTOR_PORT = COLLECTOR_PORT;
    private static final String COLLECTOR_PROTOCOL = "https";
    private static final String COMPONENT_NAME = COMPONENT_NAME;
    public static final FSTracerProvider INSTANCE = new FSTracerProvider();
    private static final int VERBOSITY_ALL_ERRORS_LOCAL = 2;
    /* access modifiers changed from: private */
    public static Tracer tracerinstance;

    private FSTracerProvider() {
    }

    @NotNull
    public static final /* synthetic */ Tracer access$getTracerinstance$p(FSTracerProvider fSTracerProvider) {
        Tracer tracer = tracerinstance;
        if (tracer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tracerinstance");
        }
        return tracer;
    }

    @JvmStatic
    @NotNull
    public static /* synthetic */ Tracer getTracerInstance$default(Context context, Tracer tracer, FSTracingSetting fSTracingSetting, int i, Object obj) {
        if ((i & 4) != 0) {
            fSTracingSetting = FSTracingSetting.RELEASE_BUILDS;
        }
        return getTracerInstance(context, tracer, fSTracingSetting);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0076, code lost:
        return r2;
     */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x005e A[Catch:{ MalformedURLException -> 0x0050 }] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x005f A[Catch:{ MalformedURLException -> 0x0050 }] */
    @kotlin.jvm.JvmStatic
    @org.jetbrains.annotations.NotNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final synchronized io.opentracing.Tracer getTracerInstance(@org.jetbrains.annotations.NotNull android.content.Context r2, @org.jetbrains.annotations.Nullable io.opentracing.Tracer r3, @org.jetbrains.annotations.NotNull com.uacf.core.tracing.FSTracingSetting r4) {
        /*
            java.lang.Class<com.uacf.core.tracing.FSTracerProvider> r0 = com.uacf.core.tracing.FSTracerProvider.class
            monitor-enter(r0)
            java.lang.String r1 = "context"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r2, r1)     // Catch:{ all -> 0x0077 }
            java.lang.String r1 = "tracingSetting"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r4, r1)     // Catch:{ all -> 0x0077 }
            if (r3 == 0) goto L_0x0011
            monitor-exit(r0)
            return r3
        L_0x0011:
            io.opentracing.Tracer r3 = tracerinstance     // Catch:{ all -> 0x0077 }
            if (r3 != 0) goto L_0x006c
            r3 = 0
            io.opentracing.Tracer r3 = (io.opentracing.Tracer) r3     // Catch:{ all -> 0x0077 }
            com.uacf.core.tracing.FSTracingSetting r1 = com.uacf.core.tracing.FSTracingSetting.ALL_BUILDS     // Catch:{ all -> 0x0077 }
            if (r4 == r1) goto L_0x0020
            com.uacf.core.tracing.FSTracingSetting r1 = com.uacf.core.tracing.FSTracingSetting.RELEASE_BUILDS     // Catch:{ all -> 0x0077 }
            if (r4 != r1) goto L_0x005b
        L_0x0020:
            com.lightstep.tracer.shared.Options$OptionsBuilder r4 = new com.lightstep.tracer.shared.Options$OptionsBuilder     // Catch:{ MalformedURLException -> 0x0050 }
            r4.<init>()     // Catch:{ MalformedURLException -> 0x0050 }
            java.lang.String r1 = ACCESS_TOKEN     // Catch:{ MalformedURLException -> 0x0050 }
            com.lightstep.tracer.shared.Options$OptionsBuilder r4 = r4.withAccessToken(r1)     // Catch:{ MalformedURLException -> 0x0050 }
            java.lang.String r1 = COMPONENT_NAME     // Catch:{ MalformedURLException -> 0x0050 }
            com.lightstep.tracer.shared.Options$OptionsBuilder r4 = r4.withComponentName(r1)     // Catch:{ MalformedURLException -> 0x0050 }
            java.lang.String r1 = COLLECTOR_HOST     // Catch:{ MalformedURLException -> 0x0050 }
            com.lightstep.tracer.shared.Options$OptionsBuilder r4 = r4.withCollectorHost(r1)     // Catch:{ MalformedURLException -> 0x0050 }
            int r1 = COLLECTOR_PORT     // Catch:{ MalformedURLException -> 0x0050 }
            com.lightstep.tracer.shared.Options$OptionsBuilder r4 = r4.withCollectorPort(r1)     // Catch:{ MalformedURLException -> 0x0050 }
            java.lang.String r1 = COLLECTOR_PROTOCOL     // Catch:{ MalformedURLException -> 0x0050 }
            com.lightstep.tracer.shared.Options$OptionsBuilder r4 = r4.withCollectorProtocol(r1)     // Catch:{ MalformedURLException -> 0x0050 }
            com.lightstep.tracer.android.Tracer r1 = new com.lightstep.tracer.android.Tracer     // Catch:{ MalformedURLException -> 0x0050 }
            com.lightstep.tracer.shared.Options r4 = r4.build()     // Catch:{ MalformedURLException -> 0x0050 }
            r1.<init>(r2, r4)     // Catch:{ MalformedURLException -> 0x0050 }
            r2 = r1
            io.opentracing.Tracer r2 = (io.opentracing.Tracer) r2     // Catch:{ MalformedURLException -> 0x0050 }
            goto L_0x005c
        L_0x0050:
            r2 = move-exception
            java.lang.Throwable r2 = (java.lang.Throwable) r2     // Catch:{ all -> 0x0077 }
            java.lang.String r4 = "FSTracerProvider: Failed to initialize light step tracer"
            r1 = 0
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ all -> 0x0077 }
            com.uacf.core.util.Ln.e(r2, r4, r1)     // Catch:{ all -> 0x0077 }
        L_0x005b:
            r2 = r3
        L_0x005c:
            if (r2 == 0) goto L_0x005f
            goto L_0x006a
        L_0x005f:
            io.opentracing.noop.NoopTracer r2 = io.opentracing.noop.NoopTracerFactory.create()     // Catch:{ all -> 0x0077 }
            java.lang.String r3 = "NoopTracerFactory.create()"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r3)     // Catch:{ all -> 0x0077 }
            io.opentracing.Tracer r2 = (io.opentracing.Tracer) r2     // Catch:{ all -> 0x0077 }
        L_0x006a:
            tracerinstance = r2     // Catch:{ all -> 0x0077 }
        L_0x006c:
            io.opentracing.Tracer r2 = tracerinstance     // Catch:{ all -> 0x0077 }
            if (r2 != 0) goto L_0x0075
            java.lang.String r3 = "tracerinstance"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)     // Catch:{ all -> 0x0077 }
        L_0x0075:
            monitor-exit(r0)
            return r2
        L_0x0077:
            r2 = move-exception
            monitor-exit(r0)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.uacf.core.tracing.FSTracerProvider.getTracerInstance(android.content.Context, io.opentracing.Tracer, com.uacf.core.tracing.FSTracingSetting):io.opentracing.Tracer");
    }
}
