package com.myfitnesspal.feature.consents.model;

import android.app.Application;
import com.myfitnesspal.feature.consents.service.ConsentsService;
import com.myfitnesspal.feature.consents.util.AdConsentsAnalyticsHelper;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class AdConsentsViewModel_Factory implements Factory<AdConsentsViewModel> {
    private final Provider<AdConsentsAnalyticsHelper> adConsentsAnalyticsHelperProvider;
    private final Provider<Application> applicationContextProvider;
    private final Provider<ConsentsService> consentServiceProvider;
    private final Provider<LocalSettingsService> localSettingsServiceProvider;

    public AdConsentsViewModel_Factory(Provider<Application> provider, Provider<ConsentsService> provider2, Provider<LocalSettingsService> provider3, Provider<AdConsentsAnalyticsHelper> provider4) {
        this.applicationContextProvider = provider;
        this.consentServiceProvider = provider2;
        this.localSettingsServiceProvider = provider3;
        this.adConsentsAnalyticsHelperProvider = provider4;
    }

    public AdConsentsViewModel get() {
        return provideInstance(this.applicationContextProvider, this.consentServiceProvider, this.localSettingsServiceProvider, this.adConsentsAnalyticsHelperProvider);
    }

    public static AdConsentsViewModel provideInstance(Provider<Application> provider, Provider<ConsentsService> provider2, Provider<LocalSettingsService> provider3, Provider<AdConsentsAnalyticsHelper> provider4) {
        return new AdConsentsViewModel((Application) provider.get(), (ConsentsService) provider2.get(), (LocalSettingsService) provider3.get(), (AdConsentsAnalyticsHelper) provider4.get());
    }

    public static AdConsentsViewModel_Factory create(Provider<Application> provider, Provider<ConsentsService> provider2, Provider<LocalSettingsService> provider3, Provider<AdConsentsAnalyticsHelper> provider4) {
        return new AdConsentsViewModel_Factory(provider, provider2, provider3, provider4);
    }

    public static AdConsentsViewModel newAdConsentsViewModel(Application application, ConsentsService consentsService, LocalSettingsService localSettingsService, AdConsentsAnalyticsHelper adConsentsAnalyticsHelper) {
        return new AdConsentsViewModel(application, consentsService, localSettingsService, adConsentsAnalyticsHelper);
    }
}
