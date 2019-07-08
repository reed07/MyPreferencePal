package com.myfitnesspal.feature.settings.ui.dialog;

import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.userdata.UserDistanceService;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.service.userdata.UserHeightService;
import com.myfitnesspal.shared.service.userdata.UserWeightService;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment_MembersInjector;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.squareup.otto.Bus;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class UnitsDialogFragment_MembersInjector implements MembersInjector<UnitsDialogFragment> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<ConfigService> configServiceProvider;
    private final Provider<Bus> messageBusProvider;
    private final Provider<NavigationHelper> navigationHelperProvider;
    private final Provider<Session> sessionProvider;
    private final Provider<UserDistanceService> userDistanceServiceProvider;
    private final Provider<UserEnergyService> userEnergyServiceProvider;
    private final Provider<UserHeightService> userHeightServiceProvider;
    private final Provider<UserWeightService> userWeightServiceProvider;

    public UnitsDialogFragment_MembersInjector(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4, Provider<UserWeightService> provider5, Provider<UserHeightService> provider6, Provider<UserEnergyService> provider7, Provider<UserDistanceService> provider8, Provider<ConfigService> provider9) {
        this.messageBusProvider = provider;
        this.navigationHelperProvider = provider2;
        this.sessionProvider = provider3;
        this.analyticsServiceProvider = provider4;
        this.userWeightServiceProvider = provider5;
        this.userHeightServiceProvider = provider6;
        this.userEnergyServiceProvider = provider7;
        this.userDistanceServiceProvider = provider8;
        this.configServiceProvider = provider9;
    }

    public static MembersInjector<UnitsDialogFragment> create(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4, Provider<UserWeightService> provider5, Provider<UserHeightService> provider6, Provider<UserEnergyService> provider7, Provider<UserDistanceService> provider8, Provider<ConfigService> provider9) {
        UnitsDialogFragment_MembersInjector unitsDialogFragment_MembersInjector = new UnitsDialogFragment_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9);
        return unitsDialogFragment_MembersInjector;
    }

    public void injectMembers(UnitsDialogFragment unitsDialogFragment) {
        CustomLayoutBaseDialogFragment_MembersInjector.injectMessageBus(unitsDialogFragment, (Bus) this.messageBusProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectNavigationHelper(unitsDialogFragment, (NavigationHelper) this.navigationHelperProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectSession(unitsDialogFragment, DoubleCheck.lazy(this.sessionProvider));
        CustomLayoutBaseDialogFragment_MembersInjector.injectAnalyticsService(unitsDialogFragment, DoubleCheck.lazy(this.analyticsServiceProvider));
        injectUserWeightService(unitsDialogFragment, (UserWeightService) this.userWeightServiceProvider.get());
        injectUserHeightService(unitsDialogFragment, (UserHeightService) this.userHeightServiceProvider.get());
        injectUserEnergyService(unitsDialogFragment, (UserEnergyService) this.userEnergyServiceProvider.get());
        injectUserDistanceService(unitsDialogFragment, (UserDistanceService) this.userDistanceServiceProvider.get());
        injectConfigService(unitsDialogFragment, DoubleCheck.lazy(this.configServiceProvider));
    }

    public static void injectUserWeightService(UnitsDialogFragment unitsDialogFragment, UserWeightService userWeightService) {
        unitsDialogFragment.userWeightService = userWeightService;
    }

    public static void injectUserHeightService(UnitsDialogFragment unitsDialogFragment, UserHeightService userHeightService) {
        unitsDialogFragment.userHeightService = userHeightService;
    }

    public static void injectUserEnergyService(UnitsDialogFragment unitsDialogFragment, UserEnergyService userEnergyService) {
        unitsDialogFragment.userEnergyService = userEnergyService;
    }

    public static void injectUserDistanceService(UnitsDialogFragment unitsDialogFragment, UserDistanceService userDistanceService) {
        unitsDialogFragment.userDistanceService = userDistanceService;
    }

    public static void injectConfigService(UnitsDialogFragment unitsDialogFragment, Lazy<ConfigService> lazy) {
        unitsDialogFragment.configService = lazy;
    }
}
