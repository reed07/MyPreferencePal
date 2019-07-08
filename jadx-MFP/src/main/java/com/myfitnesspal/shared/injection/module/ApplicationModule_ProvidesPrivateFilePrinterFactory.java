package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.settings.model.AppSettings;
import com.uacf.core.logging.PrivateFilePrinter;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.io.File;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesPrivateFilePrinterFactory implements Factory<PrivateFilePrinter> {
    private final Provider<AppSettings> appSettingsProvider;
    private final Provider<File> logsDirProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvidesPrivateFilePrinterFactory(ApplicationModule applicationModule, Provider<File> provider, Provider<AppSettings> provider2) {
        this.module = applicationModule;
        this.logsDirProvider = provider;
        this.appSettingsProvider = provider2;
    }

    public PrivateFilePrinter get() {
        return provideInstance(this.module, this.logsDirProvider, this.appSettingsProvider);
    }

    public static PrivateFilePrinter provideInstance(ApplicationModule applicationModule, Provider<File> provider, Provider<AppSettings> provider2) {
        return proxyProvidesPrivateFilePrinter(applicationModule, (File) provider.get(), (AppSettings) provider2.get());
    }

    public static ApplicationModule_ProvidesPrivateFilePrinterFactory create(ApplicationModule applicationModule, Provider<File> provider, Provider<AppSettings> provider2) {
        return new ApplicationModule_ProvidesPrivateFilePrinterFactory(applicationModule, provider, provider2);
    }

    public static PrivateFilePrinter proxyProvidesPrivateFilePrinter(ApplicationModule applicationModule, File file, AppSettings appSettings) {
        return (PrivateFilePrinter) Preconditions.checkNotNull(applicationModule.providesPrivateFilePrinter(file, appSettings), "Cannot return null from a non-@Nullable @Provides method");
    }
}
