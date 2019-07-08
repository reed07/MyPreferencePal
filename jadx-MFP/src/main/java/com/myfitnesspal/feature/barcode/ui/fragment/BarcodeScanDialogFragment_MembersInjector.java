package com.myfitnesspal.feature.barcode.ui.fragment;

import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment_MembersInjector;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.squareup.otto.Bus;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class BarcodeScanDialogFragment_MembersInjector implements MembersInjector<BarcodeScanDialogFragment> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<ConfigService> configServiceProvider;
    private final Provider<Bus> messageBusProvider;
    private final Provider<NavigationHelper> navigationHelperProvider;
    private final Provider<Session> sessionProvider;

    public BarcodeScanDialogFragment_MembersInjector(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4, Provider<ConfigService> provider5) {
        this.messageBusProvider = provider;
        this.navigationHelperProvider = provider2;
        this.sessionProvider = provider3;
        this.analyticsServiceProvider = provider4;
        this.configServiceProvider = provider5;
    }

    public static MembersInjector<BarcodeScanDialogFragment> create(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4, Provider<ConfigService> provider5) {
        BarcodeScanDialogFragment_MembersInjector barcodeScanDialogFragment_MembersInjector = new BarcodeScanDialogFragment_MembersInjector(provider, provider2, provider3, provider4, provider5);
        return barcodeScanDialogFragment_MembersInjector;
    }

    public void injectMembers(BarcodeScanDialogFragment barcodeScanDialogFragment) {
        CustomLayoutBaseDialogFragment_MembersInjector.injectMessageBus(barcodeScanDialogFragment, (Bus) this.messageBusProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectNavigationHelper(barcodeScanDialogFragment, (NavigationHelper) this.navigationHelperProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectSession(barcodeScanDialogFragment, DoubleCheck.lazy(this.sessionProvider));
        CustomLayoutBaseDialogFragment_MembersInjector.injectAnalyticsService(barcodeScanDialogFragment, DoubleCheck.lazy(this.analyticsServiceProvider));
        injectConfigService(barcodeScanDialogFragment, DoubleCheck.lazy(this.configServiceProvider));
    }

    public static void injectConfigService(BarcodeScanDialogFragment barcodeScanDialogFragment, Lazy<ConfigService> lazy) {
        barcodeScanDialogFragment.configService = lazy;
    }
}
