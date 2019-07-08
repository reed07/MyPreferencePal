package com.myfitnesspal.feature.progress.ui.fragment;

import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.userdata.UserWeightService;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment_MembersInjector;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.squareup.otto.Bus;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class LegacyWeightPickerFragment_MembersInjector implements MembersInjector<LegacyWeightPickerFragment> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<ConfigService> configServiceProvider;
    private final Provider<Bus> messageBusProvider;
    private final Provider<NavigationHelper> navigationHelperProvider;
    private final Provider<Session> sessionProvider;
    private final Provider<UserWeightService> userWeightServiceProvider;

    public LegacyWeightPickerFragment_MembersInjector(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4, Provider<UserWeightService> provider5, Provider<ConfigService> provider6) {
        this.messageBusProvider = provider;
        this.navigationHelperProvider = provider2;
        this.sessionProvider = provider3;
        this.analyticsServiceProvider = provider4;
        this.userWeightServiceProvider = provider5;
        this.configServiceProvider = provider6;
    }

    public static MembersInjector<LegacyWeightPickerFragment> create(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4, Provider<UserWeightService> provider5, Provider<ConfigService> provider6) {
        LegacyWeightPickerFragment_MembersInjector legacyWeightPickerFragment_MembersInjector = new LegacyWeightPickerFragment_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6);
        return legacyWeightPickerFragment_MembersInjector;
    }

    public void injectMembers(LegacyWeightPickerFragment legacyWeightPickerFragment) {
        CustomLayoutBaseDialogFragment_MembersInjector.injectMessageBus(legacyWeightPickerFragment, (Bus) this.messageBusProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectNavigationHelper(legacyWeightPickerFragment, (NavigationHelper) this.navigationHelperProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectSession(legacyWeightPickerFragment, DoubleCheck.lazy(this.sessionProvider));
        CustomLayoutBaseDialogFragment_MembersInjector.injectAnalyticsService(legacyWeightPickerFragment, DoubleCheck.lazy(this.analyticsServiceProvider));
        injectMessageBus(legacyWeightPickerFragment, DoubleCheck.lazy(this.messageBusProvider));
        injectUserWeightService(legacyWeightPickerFragment, DoubleCheck.lazy(this.userWeightServiceProvider));
        injectConfigService(legacyWeightPickerFragment, DoubleCheck.lazy(this.configServiceProvider));
        injectAnalyticsService(legacyWeightPickerFragment, DoubleCheck.lazy(this.analyticsServiceProvider));
    }

    public static void injectMessageBus(LegacyWeightPickerFragment legacyWeightPickerFragment, Lazy<Bus> lazy) {
        legacyWeightPickerFragment.messageBus = lazy;
    }

    public static void injectUserWeightService(LegacyWeightPickerFragment legacyWeightPickerFragment, Lazy<UserWeightService> lazy) {
        legacyWeightPickerFragment.userWeightService = lazy;
    }

    public static void injectConfigService(LegacyWeightPickerFragment legacyWeightPickerFragment, Lazy<ConfigService> lazy) {
        legacyWeightPickerFragment.configService = lazy;
    }

    public static void injectAnalyticsService(LegacyWeightPickerFragment legacyWeightPickerFragment, Lazy<AnalyticsService> lazy) {
        legacyWeightPickerFragment.analyticsService = lazy;
    }
}
