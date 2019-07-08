package com.uacf.core.tracing;

import android.text.TextUtils;
import com.uacf.core.util.Ln;
import io.opentracing.Scope;
import io.opentracing.SpanContext;
import io.opentracing.Tracer;
import io.opentracing.Tracer.SpanBuilder;
import io.uacf.core.app.UacfAppId;
import java.util.concurrent.Semaphore;

public abstract class FSTraceableSdkImpl<CType> implements FSTraceableSdk<CType> {
    private static final String TRACER_APP_ID_TAG_KEY = "app-id";
    private static final String TRACER_APP_VERSION_TAG_KEY = "app-version";
    private static final String TRACER_SDK_NAME_TAG_KEY = "fs-sdk-source";
    private static final String TRACER_SDK_VERSION_CODE_TAG_KEY = "fs-sdk-version-code";
    private static final String TRACER_SDK_VERSION_NAME_TAG_KEY = "fs-sdk-version-name";
    private static final Semaphore parentSpanSemaphore = new Semaphore(1);
    private UacfAppId appId;
    private String appVersion;
    private String sdkName;
    private SpanContext spanContext;
    private Tracer tracer;
    private String versionCode;
    private String versionName;

    public FSTraceableSdkImpl(Tracer tracer2) {
        this(tracer2, null, null, null, null, null);
    }

    public FSTraceableSdkImpl(Tracer tracer2, String str, String str2, String str3, UacfAppId uacfAppId, String str4) {
        this.tracer = tracer2;
        this.sdkName = str;
        this.versionName = str2;
        this.versionCode = str3;
        this.appId = uacfAppId;
        this.appVersion = str4;
    }

    public synchronized CType setParentSpanContext(SpanContext spanContext2) {
        try {
            parentSpanSemaphore.acquire();
            this.spanContext = spanContext2;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    /* access modifiers changed from: protected */
    public Tracer getTracer() {
        return this.tracer;
    }

    private synchronized SpanContext getParentSpanContext() {
        SpanContext spanContext2;
        spanContext2 = this.spanContext;
        this.spanContext = null;
        parentSpanSemaphore.release();
        return spanContext2;
    }

    /* access modifiers changed from: protected */
    public synchronized Scope startActiveSpanForMethod() {
        String str;
        if (this.tracer != null) {
            try {
                str = FSTracingUtil.createSpanNameForCallingMethod();
                try {
                    SpanBuilder asChildOf = this.tracer.buildSpan(str).asChildOf(getParentSpanContext());
                    if (!TextUtils.isEmpty(this.sdkName)) {
                        asChildOf.withTag(TRACER_SDK_NAME_TAG_KEY, this.sdkName);
                    }
                    if (!TextUtils.isEmpty(this.versionName)) {
                        asChildOf.withTag(TRACER_SDK_VERSION_NAME_TAG_KEY, this.versionName);
                    }
                    if (!TextUtils.isEmpty(this.versionCode)) {
                        asChildOf.withTag(TRACER_SDK_VERSION_CODE_TAG_KEY, this.versionCode);
                    }
                    if (this.appId != null) {
                        asChildOf.withTag(TRACER_APP_ID_TAG_KEY, this.appId.name());
                    }
                    if (!TextUtils.isEmpty(this.appVersion)) {
                        asChildOf.withTag(TRACER_APP_VERSION_TAG_KEY, this.appVersion);
                    }
                    return asChildOf.startActive(true);
                } catch (Exception e) {
                    e = e;
                    StringBuilder sb = new StringBuilder();
                    sb.append("FSTraceableSdkImpl: Failed to create start span ");
                    sb.append(str);
                    Ln.e(e, sb.toString(), new Object[0]);
                    return null;
                }
            } catch (Exception e2) {
                e = e2;
                str = null;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("FSTraceableSdkImpl: Failed to create start span ");
                sb2.append(str);
                Ln.e(e, sb2.toString(), new Object[0]);
                return null;
            }
        }
    }
}
