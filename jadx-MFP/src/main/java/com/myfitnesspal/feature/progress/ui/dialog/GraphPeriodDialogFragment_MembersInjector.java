package com.myfitnesspal.feature.progress.ui.dialog;

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

public final class GraphPeriodDialogFragment_MembersInjector implements MembersInjector<GraphPeriodDialogFragment> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<ConfigService> configServiceProvider;
    private final Provider<Bus> messageBusProvider;
    private final Provider<NavigationHelper> navigationHelperProvider;
    private final Provider<Session> sessionProvider;

    public GraphPeriodDialogFragment_MembersInjector(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4, Provider<ConfigService> provider5) {
        this.messageBusProvider = provider;
        this.navigationHelperProvider = provider2;
        this.sessionProvider = provider3;
        this.analyticsServiceProvider = provider4;
        this.configServiceProvider = provider5;
    }

    public static MembersInjector<GraphPeriodDialogFragment> create(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4, Provider<ConfigService> provider5) {
        GraphPeriodDialogFragment_MembersInjector graphPeriodDialogFragment_MembersInjector = new GraphPeriodDialogFragment_MembersInjector(provider, provider2, provider3, provider4, provider5);
        return graphPeriodDialogFragment_MembersInjector;
    }

    public void injectMembers(GraphPeriodDialogFragment graphPeriodDialogFragment) {
        CustomLayoutBaseDialogFragment_MembersInjector.injectMessageBus(graphPeriodDialogFragment, (Bus) this.messageBusProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectNavigationHelper(graphPeriodDialogFragment, (NavigationHelper) this.navigationHelperProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectSession(graphPeriodDialogFragment, DoubleCheck.lazy(this.sessionProvider));
        CustomLayoutBaseDialogFragment_MembersInjector.injectAnalyticsService(graphPeriodDialogFragment, DoubleCheck.lazy(this.analyticsServiceProvider));
        injectConfigService(graphPeriodDialogFragment, DoubleCheck.lazy(this.configServiceProvider));
    }

    public static void injectConfigService(GraphPeriodDialogFragment graphPeriodDialogFragment, Lazy<ConfigService> lazy) {
        graphPeriodDialogFragment.configService = lazy;
    }
}
