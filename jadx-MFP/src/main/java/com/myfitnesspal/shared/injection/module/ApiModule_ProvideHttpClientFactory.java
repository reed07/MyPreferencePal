package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.shared.api.v2.interceptor.BaseHeaderRequestInterceptor;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import okhttp3.OkHttpClient;

public final class ApiModule_ProvideHttpClientFactory implements Factory<OkHttpClient> {
    private final ApiModule module;
    private final Provider<BaseHeaderRequestInterceptor> requestRequestInterceptorProvider;

    public ApiModule_ProvideHttpClientFactory(ApiModule apiModule, Provider<BaseHeaderRequestInterceptor> provider) {
        this.module = apiModule;
        this.requestRequestInterceptorProvider = provider;
    }

    public OkHttpClient get() {
        return provideInstance(this.module, this.requestRequestInterceptorProvider);
    }

    public static OkHttpClient provideInstance(ApiModule apiModule, Provider<BaseHeaderRequestInterceptor> provider) {
        return proxyProvideHttpClient(apiModule, (BaseHeaderRequestInterceptor) provider.get());
    }

    public static ApiModule_ProvideHttpClientFactory create(ApiModule apiModule, Provider<BaseHeaderRequestInterceptor> provider) {
        return new ApiModule_ProvideHttpClientFactory(apiModule, provider);
    }

    public static OkHttpClient proxyProvideHttpClient(ApiModule apiModule, BaseHeaderRequestInterceptor baseHeaderRequestInterceptor) {
        return (OkHttpClient) Preconditions.checkNotNull(apiModule.provideHttpClient(baseHeaderRequestInterceptor), "Cannot return null from a non-@Nullable @Provides method");
    }
}
