package io.uacf.net.retrofit;

import android.content.Context;
import android.support.annotation.Nullable;
import com.google.gson.FieldNamingPolicy;
import io.opentracing.Tracer;
import io.uacf.core.api.EnvironmentAwareServiceImpl;
import io.uacf.core.api.UacfUserAgentProvider;
import io.uacf.core.auth.UacfAuthProvider;
import okhttp3.OkHttpClient;

public abstract class UacfNetworkingServiceImpl extends EnvironmentAwareServiceImpl {
    protected final UacfAuthProvider authProvider;
    protected final String hostUrl;
    protected final OkHttpClient okHttpClient;

    /* access modifiers changed from: protected */
    public abstract Class<?> getConsumerClass();

    /* access modifiers changed from: protected */
    public int getMaxThreads() {
        return 1;
    }

    /* access modifiers changed from: protected */
    public String getThreadName() {
        return "UacfNetworkingServiceImpl";
    }

    public UacfNetworkingServiceImpl(Context context, String str, UacfUserAgentProvider uacfUserAgentProvider, String str2, String str3, String str4, UacfAuthProvider uacfAuthProvider, @Nullable OkHttpClient okHttpClient2, Tracer tracer) {
        super(context, uacfUserAgentProvider, str2, str3, tracer);
        this.authProvider = uacfAuthProvider;
        this.okHttpClient = okHttpClient2;
        this.hostUrl = str;
    }

    /* access modifiers changed from: protected */
    public <TConsumer> TConsumer getConsumerWithIdentityFieldsAndBasicAuthUsingCurrentClientConfig() {
        return ((UacfApiRetrofitBuilder) getBuilderWithIdentityFields().withBasicAuth(getClientId(), getClientSecret())).build();
    }

    /* access modifiers changed from: protected */
    public <TConsumer> UacfApiRetrofitBuilder<TConsumer> getBuilderWithUnderscores() {
        return getBuilder(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
    }

    /* access modifiers changed from: protected */
    public <TConsumer> UacfApiRetrofitBuilder<TConsumer> getBuilderWithIdentityFields() {
        return getBuilder(FieldNamingPolicy.IDENTITY);
    }

    /* access modifiers changed from: protected */
    public <TConsumer> UacfApiRetrofitBuilder<TConsumer> getBuilder(FieldNamingPolicy fieldNamingPolicy) {
        return ((UacfApiRetrofitBuilder) new UacfApiRetrofitBuilder(this.hostUrl).withFieldNamingPolicy(fieldNamingPolicy).withUserAgent(this.userAgentProvider.get())).withClass(getConsumerClass()).withCustomHttpClient(this.okHttpClient).withTracer(getTracer());
    }
}
