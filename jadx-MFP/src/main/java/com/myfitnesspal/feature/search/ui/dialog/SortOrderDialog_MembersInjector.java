package com.myfitnesspal.feature.search.ui.dialog;

import com.myfitnesspal.feature.search.util.SortOrderHelper;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment_MembersInjector;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.squareup.otto.Bus;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class SortOrderDialog_MembersInjector implements MembersInjector<SortOrderDialog> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<Bus> messageBusProvider;
    private final Provider<NavigationHelper> navigationHelperProvider;
    private final Provider<Session> sessionProvider;
    private final Provider<SortOrderHelper> sortOrderHelperProvider;

    public SortOrderDialog_MembersInjector(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4, Provider<SortOrderHelper> provider5) {
        this.messageBusProvider = provider;
        this.navigationHelperProvider = provider2;
        this.sessionProvider = provider3;
        this.analyticsServiceProvider = provider4;
        this.sortOrderHelperProvider = provider5;
    }

    public static MembersInjector<SortOrderDialog> create(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4, Provider<SortOrderHelper> provider5) {
        SortOrderDialog_MembersInjector sortOrderDialog_MembersInjector = new SortOrderDialog_MembersInjector(provider, provider2, provider3, provider4, provider5);
        return sortOrderDialog_MembersInjector;
    }

    public void injectMembers(SortOrderDialog sortOrderDialog) {
        CustomLayoutBaseDialogFragment_MembersInjector.injectMessageBus(sortOrderDialog, (Bus) this.messageBusProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectNavigationHelper(sortOrderDialog, (NavigationHelper) this.navigationHelperProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectSession(sortOrderDialog, DoubleCheck.lazy(this.sessionProvider));
        CustomLayoutBaseDialogFragment_MembersInjector.injectAnalyticsService(sortOrderDialog, DoubleCheck.lazy(this.analyticsServiceProvider));
        injectSortOrderHelper(sortOrderDialog, (SortOrderHelper) this.sortOrderHelperProvider.get());
    }

    public static void injectSortOrderHelper(SortOrderDialog sortOrderDialog, SortOrderHelper sortOrderHelper) {
        sortOrderDialog.sortOrderHelper = sortOrderHelper;
    }
}
