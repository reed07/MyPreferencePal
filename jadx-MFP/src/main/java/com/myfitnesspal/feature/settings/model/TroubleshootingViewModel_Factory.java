package com.myfitnesspal.feature.settings.model;

import android.app.Application;
import com.myfitnesspal.feature.settings.repository.TroubleshootingRepository;
import com.myfitnesspal.feature.settings.service.TroubleshootingService;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class TroubleshootingViewModel_Factory implements Factory<TroubleshootingViewModel> {
    private final Provider<Application> applicationContextProvider;
    private final Provider<TroubleshootingRepository> troubleshootingRepositoryProvider;
    private final Provider<TroubleshootingService> troubleshootingServiceProvider;

    public TroubleshootingViewModel_Factory(Provider<Application> provider, Provider<TroubleshootingRepository> provider2, Provider<TroubleshootingService> provider3) {
        this.applicationContextProvider = provider;
        this.troubleshootingRepositoryProvider = provider2;
        this.troubleshootingServiceProvider = provider3;
    }

    public TroubleshootingViewModel get() {
        return provideInstance(this.applicationContextProvider, this.troubleshootingRepositoryProvider, this.troubleshootingServiceProvider);
    }

    public static TroubleshootingViewModel provideInstance(Provider<Application> provider, Provider<TroubleshootingRepository> provider2, Provider<TroubleshootingService> provider3) {
        return new TroubleshootingViewModel((Application) provider.get(), (TroubleshootingRepository) provider2.get(), (TroubleshootingService) provider3.get());
    }

    public static TroubleshootingViewModel_Factory create(Provider<Application> provider, Provider<TroubleshootingRepository> provider2, Provider<TroubleshootingService> provider3) {
        return new TroubleshootingViewModel_Factory(provider, provider2, provider3);
    }

    public static TroubleshootingViewModel newTroubleshootingViewModel(Application application, TroubleshootingRepository troubleshootingRepository, TroubleshootingService troubleshootingService) {
        return new TroubleshootingViewModel(application, troubleshootingRepository, troubleshootingService);
    }
}
