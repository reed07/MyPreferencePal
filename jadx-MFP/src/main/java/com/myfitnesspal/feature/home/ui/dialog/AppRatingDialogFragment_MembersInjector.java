package com.myfitnesspal.feature.home.ui.dialog;

import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.globalsettings.GlobalSettingsService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment_MembersInjector;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.squareup.otto.Bus;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class AppRatingDialogFragment_MembersInjector implements MembersInjector<AppRatingDialogFragment> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<GlobalSettingsService> globalSettingsServiceProvider;
    private final Provider<LocalSettingsService> localSettingsServiceProvider;
    private final Provider<Bus> messageBusProvider;
    private final Provider<NavigationHelper> navigationHelperProvider;
    private final Provider<Session> sessionProvider;

    public AppRatingDialogFragment_MembersInjector(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4, Provider<LocalSettingsService> provider5, Provider<GlobalSettingsService> provider6) {
        this.messageBusProvider = provider;
        this.navigationHelperProvider = provider2;
        this.sessionProvider = provider3;
        this.analyticsServiceProvider = provider4;
        this.localSettingsServiceProvider = provider5;
        this.globalSettingsServiceProvider = provider6;
    }

    public static MembersInjector<AppRatingDialogFragment> create(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4, Provider<LocalSettingsService> provider5, Provider<GlobalSettingsService> provider6) {
        AppRatingDialogFragment_MembersInjector appRatingDialogFragment_MembersInjector = new AppRatingDialogFragment_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6);
        return appRatingDialogFragment_MembersInjector;
    }

    public void injectMembers(AppRatingDialogFragment appRatingDialogFragment) {
        CustomLayoutBaseDialogFragment_MembersInjector.injectMessageBus(appRatingDialogFragment, (Bus) this.messageBusProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectNavigationHelper(appRatingDialogFragment, (NavigationHelper) this.navigationHelperProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectSession(appRatingDialogFragment, DoubleCheck.lazy(this.sessionProvider));
        CustomLayoutBaseDialogFragment_MembersInjector.injectAnalyticsService(appRatingDialogFragment, DoubleCheck.lazy(this.analyticsServiceProvider));
        injectNavigationHelper(appRatingDialogFragment, (NavigationHelper) this.navigationHelperProvider.get());
        injectAnalyticsService(appRatingDialogFragment, (AnalyticsService) this.analyticsServiceProvider.get());
        injectLocalSettingsService(appRatingDialogFragment, DoubleCheck.lazy(this.localSettingsServiceProvider));
        injectGlobalSettingsService(appRatingDialogFragment, DoubleCheck.lazy(this.globalSettingsServiceProvider));
    }

    public static void injectNavigationHelper(AppRatingDialogFragment appRatingDialogFragment, NavigationHelper navigationHelper) {
        appRatingDialogFragment.navigationHelper = navigationHelper;
    }

    public static void injectAnalyticsService(AppRatingDialogFragment appRatingDialogFragment, AnalyticsService analyticsService) {
        appRatingDialogFragment.analyticsService = analyticsService;
    }

    public static void injectLocalSettingsService(AppRatingDialogFragment appRatingDialogFragment, Lazy<LocalSettingsService> lazy) {
        appRatingDialogFragment.localSettingsService = lazy;
    }

    public static void injectGlobalSettingsService(AppRatingDialogFragment appRatingDialogFragment, Lazy<GlobalSettingsService> lazy) {
        appRatingDialogFragment.globalSettingsService = lazy;
    }
}
