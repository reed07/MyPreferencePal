package com.myfitnesspal.shared.injection.module;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import okhttp3.OkHttpClient;

public final class ApplicationModule_ProvideOkHttpClientFactory implements Factory<OkHttpClient> {
    private final ApplicationModule module;

    public ApplicationModule_ProvideOkHttpClientFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public OkHttpClient get() {
        return provideInstance(this.module);
    }

    public static OkHttpClient provideInstance(ApplicationModule applicationModule) {
        return proxyProvideOkHttpClient(applicationModule);
    }

    public static ApplicationModule_ProvideOkHttpClientFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvideOkHttpClientFactory(applicationModule);
    }

    public static OkHttpClient proxyProvideOkHttpClient(ApplicationModule applicationModule) {
        return (OkHttpClient) Preconditions.checkNotNull(applicationModule.provideOkHttpClient(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
