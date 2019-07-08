package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import com.myfitnesspal.feature.images.service.ImageAssociationService;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.shared.api.v2.MfpV2Api;
import com.myfitnesspal.shared.service.session.Session;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesImageAssociationServiceFactory implements Factory<ImageAssociationService> {
    private final Provider<MfpV2Api> apiProvider;
    private final Provider<Context> contextProvider;
    private final Provider<ImageService> imageServiceProvider;
    private final ApplicationModule module;
    private final Provider<Session> sessionProvider;

    public ApplicationModule_ProvidesImageAssociationServiceFactory(ApplicationModule applicationModule, Provider<Context> provider, Provider<Session> provider2, Provider<ImageService> provider3, Provider<MfpV2Api> provider4) {
        this.module = applicationModule;
        this.contextProvider = provider;
        this.sessionProvider = provider2;
        this.imageServiceProvider = provider3;
        this.apiProvider = provider4;
    }

    public ImageAssociationService get() {
        return provideInstance(this.module, this.contextProvider, this.sessionProvider, this.imageServiceProvider, this.apiProvider);
    }

    public static ImageAssociationService provideInstance(ApplicationModule applicationModule, Provider<Context> provider, Provider<Session> provider2, Provider<ImageService> provider3, Provider<MfpV2Api> provider4) {
        return proxyProvidesImageAssociationService(applicationModule, (Context) provider.get(), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3), provider4);
    }

    public static ApplicationModule_ProvidesImageAssociationServiceFactory create(ApplicationModule applicationModule, Provider<Context> provider, Provider<Session> provider2, Provider<ImageService> provider3, Provider<MfpV2Api> provider4) {
        ApplicationModule_ProvidesImageAssociationServiceFactory applicationModule_ProvidesImageAssociationServiceFactory = new ApplicationModule_ProvidesImageAssociationServiceFactory(applicationModule, provider, provider2, provider3, provider4);
        return applicationModule_ProvidesImageAssociationServiceFactory;
    }

    public static ImageAssociationService proxyProvidesImageAssociationService(ApplicationModule applicationModule, Context context, Lazy<Session> lazy, Lazy<ImageService> lazy2, Provider<MfpV2Api> provider) {
        return (ImageAssociationService) Preconditions.checkNotNull(applicationModule.providesImageAssociationService(context, lazy, lazy2, provider), "Cannot return null from a non-@Nullable @Provides method");
    }
}
