package com.myfitnesspal.shared.ui.component;

import com.myfitnesspal.framework.mvvm.ViewModelCache;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.ui.activity.busymanager.BusyManager;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.squareup.otto.Bus;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class MfpUiComponentDelegate_MembersInjector implements MembersInjector<MfpUiComponentDelegate> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<BusyManager> busyManagerProvider;
    private final Provider<ConfigService> configServiceProvider;
    private final Provider<CountryService> countryServiceProvider;
    private final Provider<Bus> messageBusProvider;
    private final Provider<NavigationHelper> navigationHelperProvider;
    private final Provider<Session> sessionProvider;
    private final Provider<ViewModelCache> viewModelCacheProvider;

    public MfpUiComponentDelegate_MembersInjector(Provider<NavigationHelper> provider, Provider<Bus> provider2, Provider<BusyManager> provider3, Provider<AnalyticsService> provider4, Provider<CountryService> provider5, Provider<ConfigService> provider6, Provider<Session> provider7, Provider<ViewModelCache> provider8) {
        this.navigationHelperProvider = provider;
        this.messageBusProvider = provider2;
        this.busyManagerProvider = provider3;
        this.analyticsServiceProvider = provider4;
        this.countryServiceProvider = provider5;
        this.configServiceProvider = provider6;
        this.sessionProvider = provider7;
        this.viewModelCacheProvider = provider8;
    }

    public static MembersInjector<MfpUiComponentDelegate> create(Provider<NavigationHelper> provider, Provider<Bus> provider2, Provider<BusyManager> provider3, Provider<AnalyticsService> provider4, Provider<CountryService> provider5, Provider<ConfigService> provider6, Provider<Session> provider7, Provider<ViewModelCache> provider8) {
        MfpUiComponentDelegate_MembersInjector mfpUiComponentDelegate_MembersInjector = new MfpUiComponentDelegate_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8);
        return mfpUiComponentDelegate_MembersInjector;
    }

    public void injectMembers(MfpUiComponentDelegate mfpUiComponentDelegate) {
        injectNavigationHelper(mfpUiComponentDelegate, DoubleCheck.lazy(this.navigationHelperProvider));
        injectMessageBus(mfpUiComponentDelegate, DoubleCheck.lazy(this.messageBusProvider));
        injectBusyManager(mfpUiComponentDelegate, DoubleCheck.lazy(this.busyManagerProvider));
        injectAnalyticsService(mfpUiComponentDelegate, DoubleCheck.lazy(this.analyticsServiceProvider));
        injectLazyCountryService(mfpUiComponentDelegate, DoubleCheck.lazy(this.countryServiceProvider));
        injectConfigService(mfpUiComponentDelegate, DoubleCheck.lazy(this.configServiceProvider));
        injectSession(mfpUiComponentDelegate, DoubleCheck.lazy(this.sessionProvider));
        injectViewModelCache(mfpUiComponentDelegate, DoubleCheck.lazy(this.viewModelCacheProvider));
    }

    public static void injectNavigationHelper(MfpUiComponentDelegate mfpUiComponentDelegate, Lazy<NavigationHelper> lazy) {
        mfpUiComponentDelegate.navigationHelper = lazy;
    }

    public static void injectMessageBus(MfpUiComponentDelegate mfpUiComponentDelegate, Lazy<Bus> lazy) {
        mfpUiComponentDelegate.messageBus = lazy;
    }

    public static void injectBusyManager(MfpUiComponentDelegate mfpUiComponentDelegate, Lazy<BusyManager> lazy) {
        mfpUiComponentDelegate.busyManager = lazy;
    }

    public static void injectAnalyticsService(MfpUiComponentDelegate mfpUiComponentDelegate, Lazy<AnalyticsService> lazy) {
        mfpUiComponentDelegate.analyticsService = lazy;
    }

    public static void injectLazyCountryService(MfpUiComponentDelegate mfpUiComponentDelegate, Lazy<CountryService> lazy) {
        mfpUiComponentDelegate.lazyCountryService = lazy;
    }

    public static void injectConfigService(MfpUiComponentDelegate mfpUiComponentDelegate, Lazy<ConfigService> lazy) {
        mfpUiComponentDelegate.configService = lazy;
    }

    public static void injectSession(MfpUiComponentDelegate mfpUiComponentDelegate, Lazy<Session> lazy) {
        mfpUiComponentDelegate.session = lazy;
    }

    public static void injectViewModelCache(MfpUiComponentDelegate mfpUiComponentDelegate, Lazy<ViewModelCache> lazy) {
        mfpUiComponentDelegate.viewModelCache = lazy;
    }
}
