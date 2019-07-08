package io.uacf.net.retrofit;

import android.content.Context;
import android.support.annotation.Nullable;
import com.google.gson.FieldNamingPolicy;
import io.uacf.core.api.EnvironmentAwareServiceImpl;
import io.uacf.core.api.UacfApiEnvironment;
import io.uacf.core.api.UacfApiEnvironmentProvider;
import io.uacf.core.api.UacfUserAgentProvider;
import io.uacf.core.auth.UacfAuthProvider;
import io.uacf.net.retrofit.tracers.UacfOkHttpNetworkTracer;
import okhttp3.OkHttpClient;

public abstract class RetrofitBasedServiceImpl extends EnvironmentAwareServiceImpl {
    protected final UacfAuthProvider authProvider;
    protected final OkHttpClient okHttpClient;

    /* access modifiers changed from: protected */
    public abstract Class<?> getConsumerClass();

    /* access modifiers changed from: protected */
    public int getMaxThreads() {
        return 1;
    }

    /* access modifiers changed from: protected */
    public String getThreadName() {
        return "RetrofitBasedServiceImpl";
    }

    public RetrofitBasedServiceImpl(Context context, UacfUserAgentProvider uacfUserAgentProvider, UacfApiEnvironmentProvider uacfApiEnvironmentProvider, UacfAuthProvider uacfAuthProvider, @Nullable OkHttpClient okHttpClient2) {
        this(context, uacfUserAgentProvider, uacfApiEnvironmentProvider, uacfAuthProvider, okHttpClient2, null);
    }

    @Deprecated
    public RetrofitBasedServiceImpl(Context context, UacfUserAgentProvider uacfUserAgentProvider, UacfApiEnvironmentProvider uacfApiEnvironmentProvider, UacfAuthProvider uacfAuthProvider, @Nullable OkHttpClient okHttpClient2, @Nullable UacfOkHttpNetworkTracer uacfOkHttpNetworkTracer) {
        super(context, uacfUserAgentProvider, uacfApiEnvironmentProvider);
        this.authProvider = uacfAuthProvider;
        this.okHttpClient = okHttpClient2;
    }

    /* access modifiers changed from: protected */
    public <TConsumer> TConsumer getConsumerWithUnderscoresAndBasicAuthUsingCurrentEnvironment() {
        UacfApiEnvironment currentApiEnvironment = getCurrentApiEnvironment();
        return getConsumerWithUnderscoresAndBasicAuth(currentApiEnvironment.getClientId(), currentApiEnvironment.getClientSecret());
    }

    /* access modifiers changed from: protected */
    public <TConsumer> TConsumer getConsumerWithUnderscoresAndBasicAuth(String str, String str2) {
        return ((UacfApiRetrofitBuilder) getBuilderWithUnderscores().withBasicAuth(str, str2)).build();
    }

    /* access modifiers changed from: protected */
    public <TConsumer> TConsumer getConsumerWithUnderscoresAndBearerAuth() {
        return getBuilderWithUnderscoresAndBearerAuth().build();
    }

    /* access modifiers changed from: protected */
    public <TConsumer> UacfApiRetrofitBuilder<TConsumer> getBuilderWithUnderscoresAndBearerAuth() {
        return (UacfApiRetrofitBuilder) getBuilderWithUnderscores().withBearerAuth(this.authProvider.getAccessToken());
    }

    /* access modifiers changed from: protected */
    public <TConsumer> UacfApiRetrofitBuilder<TConsumer> getBuilderWithUnderscores() {
        return getBuilder(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
    }

    /* access modifiers changed from: protected */
    public <TConsumer> UacfApiRetrofitBuilder<TConsumer> getBuilder(FieldNamingPolicy fieldNamingPolicy) {
        return ((UacfApiRetrofitBuilder) new UacfApiRetrofitBuilder(getCurrentApiEnvironment().getBaseUrl()).withFieldNamingPolicy(fieldNamingPolicy).withUserAgent(this.userAgentProvider.get())).withClass(getConsumerClass()).withCustomHttpClient(this.okHttpClient).withTracer(getTracer());
    }
}
