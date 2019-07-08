package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import com.myfitnesspal.feature.consents.service.ConsentsService;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.session.Session;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesConsentServiceFactory implements Factory<ConsentsService> {
    private final Provider<ConfigService> configServiceProvider;
    private final Provider<Context> contextProvider;
    private final Provider<CountryService> countryServiceProvider;
    private final Provider<LocalSettingsService> localSettingsServiceProvider;
    private final ApplicationModule module;
    private final Provider<PremiumService> premiumServiceProvider;
    private final Provider<Session> sessionProvider;

    public ApplicationModule_ProvidesConsentServiceFactory(ApplicationModule applicationModule, Provider<Context> provider, Provider<CountryService> provider2, Provider<Session> provider3, Provider<LocalSettingsService> provider4, Provider<PremiumService> provider5, Provider<ConfigService> provider6) {
        this.module = applicationModule;
        this.contextProvider = provider;
        this.countryServiceProvider = provider2;
        this.sessionProvider = provider3;
        this.localSettingsServiceProvider = provider4;
        this.premiumServiceProvider = provider5;
        this.configServiceProvider = provider6;
    }

    public ConsentsService get() {
        return provideInstance(this.module, this.contextProvider, this.countryServiceProvider, this.sessionProvider, this.localSettingsServiceProvider, this.premiumServiceProvider, this.configServiceProvider);
    }

    public static ConsentsService provideInstance(ApplicationModule applicationModule, Provider<Context> provider, Provider<CountryService> provider2, Provider<Session> provider3, Provider<LocalSettingsService> provider4, Provider<PremiumService> provider5, Provider<ConfigService> provider6) {
        return proxyProvidesConsentService(applicationModule, (Context) provider.get(), (CountryService) provider2.get(), (Session) provider3.get(), (LocalSettingsService) provider4.get(), (PremiumService) provider5.get(), (ConfigService) provider6.get());
    }

    public static ApplicationModule_ProvidesConsentServiceFactory create(ApplicationModule applicationModule, Provider<Context> provider, Provider<CountryService> provider2, Provider<Session> provider3, Provider<LocalSettingsService> provider4, Provider<PremiumService> provider5, Provider<ConfigService> provider6) {
        ApplicationModule_ProvidesConsentServiceFactory applicationModule_ProvidesConsentServiceFactory = new ApplicationModule_ProvidesConsentServiceFactory(applicationModule, provider, provider2, provider3, provider4, provider5, provider6);
        return applicationModule_ProvidesConsentServiceFactory;
    }

    public static ConsentsService proxyProvidesConsentService(ApplicationModule applicationModule, Context context, CountryService countryService, Session session, LocalSettingsService localSettingsService, PremiumService premiumService, ConfigService configService) {
        return (ConsentsService) Preconditions.checkNotNull(applicationModule.providesConsentService(context, countryService, session, localSettingsService, premiumService, configService), "Cannot return null from a non-@Nullable @Provides method");
    }
}
