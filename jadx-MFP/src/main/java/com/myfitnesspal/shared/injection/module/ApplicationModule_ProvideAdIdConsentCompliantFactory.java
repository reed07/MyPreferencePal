package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.shared.service.ads.AdIdConsentCompliant;
import com.myfitnesspal.shared.service.ads.AdsAnalyticsHelper;
import com.myfitnesspal.shared.service.ads.AndroidAdvertisementIdentifier;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvideAdIdConsentCompliantFactory implements Factory<AdIdConsentCompliant> {
    private final Provider<AdsAnalyticsHelper> adsAnalyticsHelperProvider;
    private final Provider<AndroidAdvertisementIdentifier> androidAdvertisementIdentifierProvider;
    private final Provider<LocalSettingsService> localSettingsServiceProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvideAdIdConsentCompliantFactory(ApplicationModule applicationModule, Provider<LocalSettingsService> provider, Provider<AdsAnalyticsHelper> provider2, Provider<AndroidAdvertisementIdentifier> provider3) {
        this.module = applicationModule;
        this.localSettingsServiceProvider = provider;
        this.adsAnalyticsHelperProvider = provider2;
        this.androidAdvertisementIdentifierProvider = provider3;
    }

    public AdIdConsentCompliant get() {
        return provideInstance(this.module, this.localSettingsServiceProvider, this.adsAnalyticsHelperProvider, this.androidAdvertisementIdentifierProvider);
    }

    public static AdIdConsentCompliant provideInstance(ApplicationModule applicationModule, Provider<LocalSettingsService> provider, Provider<AdsAnalyticsHelper> provider2, Provider<AndroidAdvertisementIdentifier> provider3) {
        return proxyProvideAdIdConsentCompliant(applicationModule, DoubleCheck.lazy(provider), DoubleCheck.lazy(provider2), (AndroidAdvertisementIdentifier) provider3.get());
    }

    public static ApplicationModule_ProvideAdIdConsentCompliantFactory create(ApplicationModule applicationModule, Provider<LocalSettingsService> provider, Provider<AdsAnalyticsHelper> provider2, Provider<AndroidAdvertisementIdentifier> provider3) {
        return new ApplicationModule_ProvideAdIdConsentCompliantFactory(applicationModule, provider, provider2, provider3);
    }

    public static AdIdConsentCompliant proxyProvideAdIdConsentCompliant(ApplicationModule applicationModule, Lazy<LocalSettingsService> lazy, Lazy<AdsAnalyticsHelper> lazy2, AndroidAdvertisementIdentifier androidAdvertisementIdentifier) {
        return (AdIdConsentCompliant) Preconditions.checkNotNull(applicationModule.provideAdIdConsentCompliant(lazy, lazy2, androidAdvertisementIdentifier), "Cannot return null from a non-@Nullable @Provides method");
    }
}
