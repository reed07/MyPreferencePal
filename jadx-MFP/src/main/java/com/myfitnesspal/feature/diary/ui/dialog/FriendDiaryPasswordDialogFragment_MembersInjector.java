package com.myfitnesspal.feature.diary.ui.dialog;

import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment_MembersInjector;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.squareup.otto.Bus;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class FriendDiaryPasswordDialogFragment_MembersInjector implements MembersInjector<FriendDiaryPasswordDialogFragment> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<Bus> messageBusAndBusProvider;
    private final Provider<NavigationHelper> navigationHelperProvider;
    private final Provider<Session> sessionProvider;

    public FriendDiaryPasswordDialogFragment_MembersInjector(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4) {
        this.messageBusAndBusProvider = provider;
        this.navigationHelperProvider = provider2;
        this.sessionProvider = provider3;
        this.analyticsServiceProvider = provider4;
    }

    public static MembersInjector<FriendDiaryPasswordDialogFragment> create(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4) {
        return new FriendDiaryPasswordDialogFragment_MembersInjector(provider, provider2, provider3, provider4);
    }

    public void injectMembers(FriendDiaryPasswordDialogFragment friendDiaryPasswordDialogFragment) {
        CustomLayoutBaseDialogFragment_MembersInjector.injectMessageBus(friendDiaryPasswordDialogFragment, (Bus) this.messageBusAndBusProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectNavigationHelper(friendDiaryPasswordDialogFragment, (NavigationHelper) this.navigationHelperProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectSession(friendDiaryPasswordDialogFragment, DoubleCheck.lazy(this.sessionProvider));
        CustomLayoutBaseDialogFragment_MembersInjector.injectAnalyticsService(friendDiaryPasswordDialogFragment, DoubleCheck.lazy(this.analyticsServiceProvider));
        injectBus(friendDiaryPasswordDialogFragment, DoubleCheck.lazy(this.messageBusAndBusProvider));
    }

    public static void injectBus(FriendDiaryPasswordDialogFragment friendDiaryPasswordDialogFragment, Lazy<Bus> lazy) {
        friendDiaryPasswordDialogFragment.bus = lazy;
    }
}
