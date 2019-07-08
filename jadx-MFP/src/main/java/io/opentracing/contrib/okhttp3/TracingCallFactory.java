package io.opentracing.contrib.okhttp3;

import io.opentracing.Scope;
import io.opentracing.SpanContext;
import io.opentracing.Tracer;
import io.opentracing.propagation.Format.Builtin;
import io.opentracing.tag.Tags;
import java.io.IOException;
import java.util.List;
import okhttp3.Call;
import okhttp3.Call.Factory;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.Response;

public class TracingCallFactory implements Factory {
    /* access modifiers changed from: private */
    public List<OkHttpClientSpanDecorator> decorators;
    private OkHttpClient okHttpClient;
    /* access modifiers changed from: private */
    public Tracer tracer;

    static class NetworkInterceptor implements Interceptor {
        public List<OkHttpClientSpanDecorator> decorators;
        public SpanContext parentContext;
        public Tracer tracer;

        NetworkInterceptor(Tracer tracer2, SpanContext spanContext, List<OkHttpClientSpanDecorator> list) {
            this.parentContext = spanContext;
            this.tracer = tracer2;
            this.decorators = list;
        }

        public Response intercept(Chain chain) throws IOException {
            Throwable th;
            Scope startActive = this.tracer.buildSpan(chain.request().method()).withTag(Tags.SPAN_KIND.getKey(), "client").asChildOf(this.parentContext).startActive(true);
            try {
                for (OkHttpClientSpanDecorator onRequest : this.decorators) {
                    onRequest.onRequest(chain.request(), startActive.span());
                }
                Builder newBuilder = chain.request().newBuilder();
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

    public Call newCall(Request request) {
        Scope scope = null;
        try {
            final Scope startActive = this.tracer.buildSpan(request.method()).withTag(Tags.COMPONENT.getKey(), "okhttp").startActive(false);
            OkHttpClient.Builder newBuilder = this.okHttpClient.newBuilder();
            newBuilder.networkInterceptors().add(0, new NetworkInterceptor(this.tracer, startActive.span().context(), this.decorators));
            newBuilder.interceptors().add(0, new Interceptor() {
                public Response intercept(Chain chain) throws IOException {
                    Scope activate = TracingCallFactory.this.tracer.scopeManager().activate(startActive.span(), true);
                    try {
                        Response proceed = chain.proceed(chain.request());
                        activate.close();
                        return proceed;
                    } catch (Exception e) {
                        for (OkHttpClientSpanDecorator onError : TracingCallFactory.this.decorators) {
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
