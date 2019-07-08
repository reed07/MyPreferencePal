package com.myfitnesspal.feature.settings.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.service.premium.PremiumAnalyticsHelper;
import com.myfitnesspal.shared.service.userdata.UserWeightService;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class SettingsListFragment_MembersInjector implements MembersInjector<SettingsListFragment> {
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<PremiumAnalyticsHelper> premiumAnalyticsHelperProvider;
    private final Provider<PremiumService> premiumServiceProvider;
    private final Provider<UserWeightService> userWeightServiceProvider;

    public SettingsListFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<PremiumService> provider3, Provider<PremiumAnalyticsHelper> provider4, Provider<UserWeightService> provider5) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.premiumServiceProvider = provider3;
        this.premiumAnalyticsHelperProvider = provider4;
        this.userWeightServiceProvider = provider5;
    }

    public static MembersInjector<SettingsListFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<PremiumService> provider3, Provider<PremiumAnalyticsHelper> provider4, Provider<UserWeightService> provider5) {
        SettingsListFragment_MembersInjector settingsListFragment_MembersInjector = new SettingsListFragment_MembersInjector(provider, provider2, provider3, provider4, provider5);
        return settingsListFragment_MembersInjector;
    }

    public void injectMembers(SettingsListFragment settingsListFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(settingsListFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(settingsListFragment, (Glide) this.glideProvider.get());
        injectPremiumService(settingsListFragment, DoubleCheck.lazy(this.premiumServiceProvider));
        injectPremiumAnalyticsHelper(settingsListFragment, DoubleCheck.lazy(this.premiumAnalyticsHelperProvider));
        injectUserWeightService(settingsListFragment, DoubleCheck.lazy(this.userWeightServiceProvider));
    }

    public static void injectPremiumService(SettingsListFragment settingsListFragment, Lazy<PremiumService> lazy) {
        settingsListFragment.premiumService = lazy;
    }

    public static void injectPremiumAnalyticsHelper(SettingsListFragment settingsListFragment, Lazy<PremiumAnalyticsHelper> lazy) {
        settingsListFragment.premiumAnalyticsHelper = lazy;
    }

    public static void injectUserWeightService(SettingsListFragment settingsListFragment, Lazy<UserWeightService> lazy) {
        settingsListFragment.userWeightService = lazy;
    }
}
