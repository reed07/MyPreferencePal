package io.opentracing.contrib.okhttp3;

import io.opentracing.Scope;
import io.opentracing.Tracer;
import io.opentracing.tag.Tags;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.Request.Builder;
import okhttp3.Response;

public class TracingInterceptor implements Interceptor {
    private static final Logger log = Logger.getLogger(TracingInterceptor.class.getName());
    private List<OkHttpClientSpanDecorator> decorators;
    private Tracer tracer;

    public Response intercept(Chain chain) throws IOException {
        if (chain.connection() == null) {
            Scope startActive = this.tracer.buildSpan(chain.request().method()).withTag(Tags.COMPONENT.getKey(), "okhttp").startActive(true);
            Builder newBuilder = chain.request().newBuilder();
            Object tag = chain.request().tag();
            newBuilder.tag(new TagWrapper(tag instanceof TagWrapper ? (TagWrapper) tag : new TagWrapper(tag), startActive.span()));
            try {
                Response proceed = chain.proceed(newBuilder.build());
                startActive.close();
                return proceed;
            } catch (Throwable th) {
                startActive.close();
                throw th;
            }
        } else {
            Object tag2 = chain.request().tag();
            if (tag2 instanceof TagWrapper) {
                return new NetworkInterceptor(this.tracer, ((TagWrapper) tag2).getSpan().context(), this.decorators).intercept(chain);
            }
            log.severe("tag is null or not an instance of TagWrapper, skipping decorator onResponse()");
            return null;
        }
    }
}
