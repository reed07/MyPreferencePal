package com.myfitnesspal.feature.search.ui.dialog;

import com.myfitnesspal.shared.model.CheckableListItem;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment_MembersInjector;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.squareup.otto.Bus;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class SortFilterDialogBase_MembersInjector<T extends CheckableListItem> implements MembersInjector<SortFilterDialogBase<T>> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<Bus> messageBusProvider;
    private final Provider<NavigationHelper> navigationHelperProvider;
    private final Provider<Session> sessionProvider;

    public SortFilterDialogBase_MembersInjector(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4) {
        this.messageBusProvider = provider;
        this.navigationHelperProvider = provider2;
        this.sessionProvider = provider3;
        this.analyticsServiceProvider = provider4;
    }

    public static <T extends CheckableListItem> MembersInjector<SortFilterDialogBase<T>> create(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4) {
        return new SortFilterDialogBase_MembersInjector(provider, provider2, provider3, provider4);
    }

    public void injectMembers(SortFilterDialogBase<T> sortFilterDialogBase) {
        CustomLayoutBaseDialogFragment_MembersInjector.injectMessageBus(sortFilterDialogBase, (Bus) this.messageBusProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectNavigationHelper(sortFilterDialogBase, (NavigationHelper) this.navigationHelperProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectSession(sortFilterDialogBase, DoubleCheck.lazy(this.sessionProvider));
        CustomLayoutBaseDialogFragment_MembersInjector.injectAnalyticsService(sortFilterDialogBase, DoubleCheck.lazy(this.analyticsServiceProvider));
    }
}
