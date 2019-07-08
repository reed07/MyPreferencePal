package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.shared.api.MfpApiSettings;
import com.uacf.core.mock.interceptor.RequestInterceptor;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ServiceModule_ProvidesInterceptorFactory implements Factory<RequestInterceptor> {
    private final Provider<MfpApiSettings> apiSettingsProvider;
    private final ServiceModule module;

    public ServiceModule_ProvidesInterceptorFactory(ServiceModule serviceModule, Provider<MfpApiSettings> provider) {
        this.module = serviceModule;
        this.apiSettingsProvider = provider;
    }

    public RequestInterceptor get() {
        return provideInstance(this.module, this.apiSettingsProvider);
    }

    public static RequestInterceptor provideInstance(ServiceModule serviceModule, Provider<MfpApiSettings> provider) {
        return proxyProvidesInterceptor(serviceModule, (MfpApiSettings) provider.get());
    }

    public static ServiceModule_ProvidesInterceptorFactory create(ServiceModule serviceModule, Provider<MfpApiSettings> provider) {
        return new ServiceModule_ProvidesInterceptorFactory(serviceModule, provider);
    }

    public static RequestInterceptor proxyProvidesInterceptor(ServiceModule serviceModule, MfpApiSettings mfpApiSettings) {
        return (RequestInterceptor) Preconditions.checkNotNull(serviceModule.providesInterceptor(mfpApiSettings), "Cannot return null from a non-@Nullable @Provides method");
    }
}
