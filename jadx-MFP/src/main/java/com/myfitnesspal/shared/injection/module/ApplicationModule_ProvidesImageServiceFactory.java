package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.shared.api.ApiUrlProvider;
import com.myfitnesspal.shared.api.v2.MfpV2Api;
import com.myfitnesspal.shared.service.session.Session;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesImageServiceFactory implements Factory<ImageService> {
    private final Provider<MfpV2Api> apiProvider;
    private final Provider<Context> contextProvider;
    private final ApplicationModule module;
    private final Provider<Session> sessionProvider;
    private final Provider<ApiUrlProvider> urlProvider;

    public ApplicationModule_ProvidesImageServiceFactory(ApplicationModule applicationModule, Provider<Context> provider, Provider<Session> provider2, Provider<ApiUrlProvider> provider3, Provider<MfpV2Api> provider4) {
        this.module = applicationModule;
        this.contextProvider = provider;
        this.sessionProvider = provider2;
        this.urlProvider = provider3;
        this.apiProvider = provider4;
    }

    public ImageService get() {
        return provideInstance(this.module, this.contextProvider, this.sessionProvider, this.urlProvider, this.apiProvider);
    }

    public static ImageService provideInstance(ApplicationModule applicationModule, Provider<Context> provider, Provider<Session> provider2, Provider<ApiUrlProvider> provider3, Provider<MfpV2Api> provider4) {
        return proxyProvidesImageService(applicationModule, (Context) provider.get(), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3), provider4);
    }

    public static ApplicationModule_ProvidesImageServiceFactory create(ApplicationModule applicationModule, Provider<Context> provider, Provider<Session> provider2, Provider<ApiUrlProvider> provider3, Provider<MfpV2Api> provider4) {
        ApplicationModule_ProvidesImageServiceFactory applicationModule_ProvidesImageServiceFactory = new ApplicationModule_ProvidesImageServiceFactory(applicationModule, provider, provider2, provider3, provider4);
        return applicationModule_ProvidesImageServiceFactory;
    }

    public static ImageService proxyProvidesImageService(ApplicationModule applicationModule, Context context, Lazy<Session> lazy, Lazy<ApiUrlProvider> lazy2, Provider<MfpV2Api> provider) {
        return (ImageService) Preconditions.checkNotNull(applicationModule.providesImageService(context, lazy, lazy2, provider), "Cannot return null from a non-@Nullable @Provides method");
    }
}
