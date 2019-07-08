package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.fileexport.service.FileExportService;
import com.myfitnesspal.shared.api.v2.MfpV2Api;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesFileExportServiceFactory implements Factory<FileExportService> {
    private final Provider<MfpV2Api> apiProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvidesFileExportServiceFactory(ApplicationModule applicationModule, Provider<MfpV2Api> provider) {
        this.module = applicationModule;
        this.apiProvider = provider;
    }

    public FileExportService get() {
        return provideInstance(this.module, this.apiProvider);
    }

    public static FileExportService provideInstance(ApplicationModule applicationModule, Provider<MfpV2Api> provider) {
        return proxyProvidesFileExportService(applicationModule, provider);
    }

    public static ApplicationModule_ProvidesFileExportServiceFactory create(ApplicationModule applicationModule, Provider<MfpV2Api> provider) {
        return new ApplicationModule_ProvidesFileExportServiceFactory(applicationModule, provider);
    }

    public static FileExportService proxyProvidesFileExportService(ApplicationModule applicationModule, Provider<MfpV2Api> provider) {
        return (FileExportService) Preconditions.checkNotNull(applicationModule.providesFileExportService(provider), "Cannot return null from a non-@Nullable @Provides method");
    }
}
