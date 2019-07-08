package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.barcode.service.BarcodeService;
import com.myfitnesspal.shared.api.v1.MfpInformationApi;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesBarcodeServiceFactory implements Factory<BarcodeService> {
    private final Provider<MfpInformationApi> apiProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvidesBarcodeServiceFactory(ApplicationModule applicationModule, Provider<MfpInformationApi> provider) {
        this.module = applicationModule;
        this.apiProvider = provider;
    }

    public BarcodeService get() {
        return provideInstance(this.module, this.apiProvider);
    }

    public static BarcodeService provideInstance(ApplicationModule applicationModule, Provider<MfpInformationApi> provider) {
        return proxyProvidesBarcodeService(applicationModule, provider);
    }

    public static ApplicationModule_ProvidesBarcodeServiceFactory create(ApplicationModule applicationModule, Provider<MfpInformationApi> provider) {
        return new ApplicationModule_ProvidesBarcodeServiceFactory(applicationModule, provider);
    }

    public static BarcodeService proxyProvidesBarcodeService(ApplicationModule applicationModule, Provider<MfpInformationApi> provider) {
        return (BarcodeService) Preconditions.checkNotNull(applicationModule.providesBarcodeService(provider), "Cannot return null from a non-@Nullable @Provides method");
    }
}
