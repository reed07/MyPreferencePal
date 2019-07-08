package com.myfitnesspal.shared.ui.dialog;

import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.squareup.otto.Bus;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class SingleChoiceListDialogWithCustomAdapterAndButton_MembersInjector<T> implements MembersInjector<SingleChoiceListDialogWithCustomAdapterAndButton<T>> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<Bus> messageBusProvider;
    private final Provider<NavigationHelper> navigationHelperProvider;
    private final Provider<Session> sessionProvider;

    public SingleChoiceListDialogWithCustomAdapterAndButton_MembersInjector(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4) {
        this.messageBusProvider = provider;
        this.navigationHelperProvider = provider2;
        this.sessionProvider = provider3;
        this.analyticsServiceProvider = provider4;
    }

    public static <T> MembersInjector<SingleChoiceListDialogWithCustomAdapterAndButton<T>> create(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4) {
        return new SingleChoiceListDialogWithCustomAdapterAndButton_MembersInjector(provider, provider2, provider3, provider4);
    }

    public void injectMembers(SingleChoiceListDialogWithCustomAdapterAndButton<T> singleChoiceListDialogWithCustomAdapterAndButton) {
        CustomLayoutBaseDialogFragment_MembersInjector.injectMessageBus(singleChoiceListDialogWithCustomAdapterAndButton, (Bus) this.messageBusProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectNavigationHelper(singleChoiceListDialogWithCustomAdapterAndButton, (NavigationHelper) this.navigationHelperProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectSession(singleChoiceListDialogWithCustomAdapterAndButton, DoubleCheck.lazy(this.sessionProvider));
        CustomLayoutBaseDialogFragment_MembersInjector.injectAnalyticsService(singleChoiceListDialogWithCustomAdapterAndButton, DoubleCheck.lazy(this.analyticsServiceProvider));
    }
}
