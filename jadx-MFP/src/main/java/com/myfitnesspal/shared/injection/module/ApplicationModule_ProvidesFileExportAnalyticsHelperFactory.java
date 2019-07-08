package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.fileexport.service.FileExportAnalyticsHelper;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesFileExportAnalyticsHelperFactory implements Factory<FileExportAnalyticsHelper> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvidesFileExportAnalyticsHelperFactory(ApplicationModule applicationModule, Provider<AnalyticsService> provider) {
        this.module = applicationModule;
        this.analyticsServiceProvider = provider;
    }

    public FileExportAnalyticsHelper get() {
        return provideInstance(this.module, this.analyticsServiceProvider);
    }

    public static FileExportAnalyticsHelper provideInstance(ApplicationModule applicationModule, Provider<AnalyticsService> provider) {
        return proxyProvidesFileExportAnalyticsHelper(applicationModule, DoubleCheck.lazy(provider));
    }

    public static ApplicationModule_ProvidesFileExportAnalyticsHelperFactory create(ApplicationModule applicationModule, Provider<AnalyticsService> provider) {
        return new ApplicationModule_ProvidesFileExportAnalyticsHelperFactory(applicationModule, provider);
    }

    public static FileExportAnalyticsHelper proxyProvidesFileExportAnalyticsHelper(ApplicationModule applicationModule, Lazy<AnalyticsService> lazy) {
        return (FileExportAnalyticsHelper) Preconditions.checkNotNull(applicationModule.providesFileExportAnalyticsHelper(lazy), "Cannot return null from a non-@Nullable @Provides method");
    }
}
