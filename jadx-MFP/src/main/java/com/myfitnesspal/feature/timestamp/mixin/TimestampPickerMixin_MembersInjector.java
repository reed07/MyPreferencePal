package com.myfitnesspal.feature.timestamp.mixin;

import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.timestamp.service.TimestampAnalyticsHelper;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class TimestampPickerMixin_MembersInjector implements MembersInjector<TimestampPickerMixin> {
    private final Provider<TimestampAnalyticsHelper> analyticsHelperProvider;
    private final Provider<LocalSettingsService> localSettingsServiceProvider;
    private final Provider<PremiumService> premiumServiceProvider;

    public TimestampPickerMixin_MembersInjector(Provider<PremiumService> provider, Provider<LocalSettingsService> provider2, Provider<TimestampAnalyticsHelper> provider3) {
        this.premiumServiceProvider = provider;
        this.localSettingsServiceProvider = provider2;
        this.analyticsHelperProvider = provider3;
    }

    public static MembersInjector<TimestampPickerMixin> create(Provider<PremiumService> provider, Provider<LocalSettingsService> provider2, Provider<TimestampAnalyticsHelper> provider3) {
        return new TimestampPickerMixin_MembersInjector(provider, provider2, provider3);
    }

    public void injectMembers(TimestampPickerMixin timestampPickerMixin) {
        injectPremiumService(timestampPickerMixin, (PremiumService) this.premiumServiceProvider.get());
        injectLocalSettingsService(timestampPickerMixin, (LocalSettingsService) this.localSettingsServiceProvider.get());
        injectAnalyticsHelper(timestampPickerMixin, DoubleCheck.lazy(this.analyticsHelperProvider));
    }

    public static void injectPremiumService(TimestampPickerMixin timestampPickerMixin, PremiumService premiumService) {
        timestampPickerMixin.premiumService = premiumService;
    }

    public static void injectLocalSettingsService(TimestampPickerMixin timestampPickerMixin, LocalSettingsService localSettingsService) {
        timestampPickerMixin.localSettingsService = localSettingsService;
    }

    public static void injectAnalyticsHelper(TimestampPickerMixin timestampPickerMixin, Lazy<TimestampAnalyticsHelper> lazy) {
        timestampPickerMixin.analyticsHelper = lazy;
    }
}
