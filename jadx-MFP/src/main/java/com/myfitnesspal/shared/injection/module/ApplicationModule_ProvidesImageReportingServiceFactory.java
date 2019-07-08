package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.home.service.ImageReportingService;
import com.myfitnesspal.shared.api.v2.MfpV2Api;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesImageReportingServiceFactory implements Factory<ImageReportingService> {
    private final Provider<MfpV2Api> apiProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvidesImageReportingServiceFactory(ApplicationModule applicationModule, Provider<MfpV2Api> provider) {
        this.module = applicationModule;
        this.apiProvider = provider;
    }

    public ImageReportingService get() {
        return provideInstance(this.module, this.apiProvider);
    }

    public static ImageReportingService provideInstance(ApplicationModule applicationModule, Provider<MfpV2Api> provider) {
        return proxyProvidesImageReportingService(applicationModule, provider);
    }

    public static ApplicationModule_ProvidesImageReportingServiceFactory create(ApplicationModule applicationModule, Provider<MfpV2Api> provider) {
        return new ApplicationModule_ProvidesImageReportingServiceFactory(applicationModule, provider);
    }

    public static ImageReportingService proxyProvidesImageReportingService(ApplicationModule applicationModule, Provider<MfpV2Api> provider) {
        return (ImageReportingService) Preconditions.checkNotNull(applicationModule.providesImageReportingService(provider), "Cannot return null from a non-@Nullable @Provides method");
    }
}
