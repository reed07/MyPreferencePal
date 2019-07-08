package io.uacf.net.retrofit;

import android.text.TextUtils;
import io.opentracing.Scope;
import io.opentracing.SpanContext;
import io.opentracing.Tracer;
import io.opentracing.contrib.okhttp3.OkHttpClientSpanDecorator;
import io.opentracing.contrib.okhttp3.RequestBuilderInjectAdapter;
import io.opentracing.propagation.Format.Builtin;
import io.opentracing.tag.Tags;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.Call;
import okhttp3.Call.Factory;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class UacfOpenTracingCallFactory implements Factory {
    private static final OperationNameFormatter DEFAULT_COMPONENT_OPERATION_NAME_FORMATTER = new OperationNameFormatter() {
        public String getFormattedName(Request request) {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            sb.append(request.method());
            sb.append("] - ");
            sb.append(request.url().encodedPath());
            return sb.toString();
        }
    };
    private static final OperationNameFormatter DEFAULT_NETWORK_OPERATION_NAME_FORMATTER = new OperationNameFormatter() {
        public String getFormattedName(Request request) {
            StringBuilder sb = new StringBuilder();
            sb.append(request.url().host());
            sb.append(request.url().encodedPath());
            return sb.toString();
        }
    };
    private String componentName;
    private OperationNameFormatter componentOperationNameFormatter;
    /* access modifiers changed from: private */
    public List<OkHttpClientSpanDecorator> decorators;
    private OperationNameFormatter networkOperationNameFormatter;
    private OkHttpClient okHttpClient;
    /* access modifiers changed from: private */
    public Tracer tracer;

    public static final class Builder {
        /* access modifiers changed from: private */
        public String componentName;
        /* access modifiers changed from: private */
        public OperationNameFormatter componentOperationNameFormatter;
        /* access modifiers changed from: private */
        public List<OkHttpClientSpanDecorator> decorators;
        /* access modifiers changed from: private */
        public OperationNameFormatter networkOperationNameFormatter;
        /* access modifiers changed from: private */
        public OkHttpClient okHttpClient;
        /* access modifiers changed from: private */
        public Tracer tracer;

        public Builder(OkHttpClient okHttpClient2, Tracer tracer2) {
            this.okHttpClient = okHttpClient2;
            this.tracer = tracer2;
        }

        public UacfOpenTracingCallFactory build() {
            return new UacfOpenTracingCallFactory(this);
        }
    }

    static class NetworkInterceptor implements Interceptor {
        public List<OkHttpClientSpanDecorator> decorators;
        private OperationNameFormatter networkOperationNameFormatter;
        public SpanContext parentContext;
        public Tracer tracer;

        NetworkInterceptor(Tracer tracer2, SpanContext spanContext, List<OkHttpClientSpanDecorator> list, OperationNameFormatter operationNameFormatter) {
            this.parentContext = spanContext;
            this.tracer = tracer2;
            this.decorators = list;
            this.networkOperationNameFormatter = operationNameFormatter;
        }

        public Response intercept(Chain chain) throws IOException {
            Throwable th;
            Scope startActive = this.tracer.buildSpan(this.networkOperationNameFormatter.getFormattedName(chain.request())).withTag(Tags.SPAN_KIND.getKey(), "client").asChildOf(this.parentContext).startActive(true);
            try {
                String str = chain.request().headers().get("X-Request-Id");
                if (!TextUtils.isEmpty(str)) {
                    this.tracer.activeSpan().setTag("header.request-id", str);
                }
                if (this.decorators != null) {
                    for (OkHttpClientSpanDecorator onRequest : this.decorators) {
                        onRequest.onRequest(chain.request(), startActive.span());
                    }
                }
                okhttp3.Request.Builder newBuilder = chain.request().newBuilder();
                this.tracer.inject(startActive.span().context(), Builtin.HTTP_HEADERS, new RequestBuilderInjectAdapter(newBuilder));
                Response proceed = chain.proceed(newBuilder.build());
                for (OkHttpClientSpanDecorator onResponse : this.decorators) {
                    onResponse.onResponse(chain.connection(), proceed, startActive.span());
                }
                if (startActive != null) {
                    startActive.close();
                }
                return proceed;
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public interface OperationNameFormatter {
        String getFormattedName(Request request);
    }

    private UacfOpenTracingCallFactory(Builder builder) {
        this.decorators = new ArrayList();
        this.okHttpClient = builder.okHttpClient;
        this.tracer = builder.tracer;
        if (builder.decorators != null) {
            this.decorators.addAll(builder.decorators);
        }
        this.componentName = builder.componentName != null ? builder.componentName : "fs-android-net-retrofit";
        this.componentOperationNameFormatter = builder.componentOperationNameFormatter != null ? builder.componentOperationNameFormatter : DEFAULT_COMPONENT_OPERATION_NAME_FORMATTER;
        this.networkOperationNameFormatter = builder.networkOperationNameFormatter != null ? builder.networkOperationNameFormatter : DEFAULT_NETWORK_OPERATION_NAME_FORMATTER;
    }

    public Call newCall(Request request) {
        Scope scope = null;
        try {
            final Scope startActive = this.tracer.buildSpan(this.componentOperationNameFormatter.getFormattedName(request)).withTag(Tags.COMPONENT.getKey(), this.componentName).startActive(false);
            okhttp3.OkHttpClient.Builder newBuilder = this.okHttpClient.newBuilder();
            newBuilder.networkInterceptors().add(0, new NetworkInterceptor(this.tracer, startActive.span().context(), this.decorators, this.networkOperationNameFormatter));
            newBuilder.interceptors().add(0, new Interceptor() {
                public Response intercept(Chain chain) throws IOException {
                    Scope activate = UacfOpenTracingCallFactory.this.tracer.scopeManager().activate(startActive.span(), true);
                    try {
                        Response proceed = chain.proceed(chain.request());
                        activate.close();
                        return proceed;
                    } catch (Exception e) {
                        for (OkHttpClientSpanDecorator onError : UacfOpenTracingCallFactory.this.decorators) {
                            onError.onError(e, activate.span());
                        }
                        throw e;
                    } catch (Throwable th) {
                        activate.close();
                        throw th;
                    }
                }
            });
            Call newCall = newBuilder.build().newCall(request);
            startActive.close();
            return newCall;
        } catch (Throwable th) {
            scope.close();
            throw th;
        }
    }
}
