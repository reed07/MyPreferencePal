package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.achievementinterstitialad.repository.UserSummaryRepository;
import com.myfitnesspal.feature.achievementinterstitialad.service.AchievementAdAnalyticsEvents;
import com.myfitnesspal.feature.achievementinterstitialad.service.AchievementAdManager;
import com.myfitnesspal.feature.achievementinterstitialad.service.StoredAchievementEvents;
import com.myfitnesspal.feature.achievementinterstitialad.ui.AchievementInterstitialAd;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.settings.model.AdsSettings;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvideAchievementAdManagerFactory implements Factory<AchievementAdManager> {
    private final Provider<AchievementAdAnalyticsEvents> achievementAdAnalyticsEventsProvider;
    private final Provider<AchievementInterstitialAd> achievementInterstitialAdProvider;
    private final Provider<AdsSettings> adsSettingsProvider;
    private final Provider<String> clientIdProvider;
    private final Provider<LocalSettingsService> localSettingsServiceProvider;
    private final ApplicationModule module;
    private final Provider<PremiumService> premiumServiceProvider;
    private final Provider<StoredAchievementEvents> storedAchievementEventsProvider;
    private final Provider<UserSummaryRepository> userSummaryRepositoryInterfaceProvider;

    public ApplicationModule_ProvideAchievementAdManagerFactory(ApplicationModule applicationModule, Provider<UserSummaryRepository> provider, Provider<PremiumService> provider2, Provider<AdsSettings> provider3, Provider<LocalSettingsService> provider4, Provider<StoredAchievementEvents> provider5, Provider<AchievementInterstitialAd> provider6, Provider<AchievementAdAnalyticsEvents> provider7, Provider<String> provider8) {
        this.module = applicationModule;
        this.userSummaryRepositoryInterfaceProvider = provider;
        this.premiumServiceProvider = provider2;
        this.adsSettingsProvider = provider3;
        this.localSettingsServiceProvider = provider4;
        this.storedAchievementEventsProvider = provider5;
        this.achievementInterstitialAdProvider = provider6;
        this.achievementAdAnalyticsEventsProvider = provider7;
        this.clientIdProvider = provider8;
    }

    public AchievementAdManager get() {
        return provideInstance(this.module, this.userSummaryRepositoryInterfaceProvider, this.premiumServiceProvider, this.adsSettingsProvider, this.localSettingsServiceProvider, this.storedAchievementEventsProvider, this.achievementInterstitialAdProvider, this.achievementAdAnalyticsEventsProvider, this.clientIdProvider);
    }

    public static AchievementAdManager provideInstance(ApplicationModule applicationModule, Provider<UserSummaryRepository> provider, Provider<PremiumService> provider2, Provider<AdsSettings> provider3, Provider<LocalSettingsService> provider4, Provider<StoredAchievementEvents> provider5, Provider<AchievementInterstitialAd> provider6, Provider<AchievementAdAnalyticsEvents> provider7, Provider<String> provider8) {
        return proxyProvideAchievementAdManager(applicationModule, DoubleCheck.lazy(provider), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3), DoubleCheck.lazy(provider4), DoubleCheck.lazy(provider5), DoubleCheck.lazy(provider6), DoubleCheck.lazy(provider7), (String) provider8.get());
    }

    public static ApplicationModule_ProvideAchievementAdManagerFactory create(ApplicationModule applicationModule, Provider<UserSummaryRepository> provider, Provider<PremiumService> provider2, Provider<AdsSettings> provider3, Provider<LocalSettingsService> provider4, Provider<StoredAchievementEvents> provider5, Provider<AchievementInterstitialAd> provider6, Provider<AchievementAdAnalyticsEvents> provider7, Provider<String> provider8) {
        ApplicationModule_ProvideAchievementAdManagerFactory applicationModule_ProvideAchievementAdManagerFactory = new ApplicationModule_ProvideAchievementAdManagerFactory(applicationModule, provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8);
        return applicationModule_ProvideAchievementAdManagerFactory;
    }

    public static AchievementAdManager proxyProvideAchievementAdManager(ApplicationModule applicationModule, Lazy<UserSummaryRepository> lazy, Lazy<PremiumService> lazy2, Lazy<AdsSettings> lazy3, Lazy<LocalSettingsService> lazy4, Lazy<StoredAchievementEvents> lazy5, Lazy<AchievementInterstitialAd> lazy6, Lazy<AchievementAdAnalyticsEvents> lazy7, String str) {
        return (AchievementAdManager) Preconditions.checkNotNull(applicationModule.provideAchievementAdManager(lazy, lazy2, lazy3, lazy4, lazy5, lazy6, lazy7, str), "Cannot return null from a non-@Nullable @Provides method");
    }
}
