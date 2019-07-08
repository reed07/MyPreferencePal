package com.myfitnesspal.shared.ui.dialog.impl;

import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment_MembersInjector;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.squareup.otto.Bus;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class AccountRestrictedDialogFragment_MembersInjector implements MembersInjector<AccountRestrictedDialogFragment> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<Bus> messageBusProvider;
    private final Provider<NavigationHelper> navigationHelperProvider;
    private final Provider<Session> sessionProvider;

    public AccountRestrictedDialogFragment_MembersInjector(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4) {
        this.messageBusProvider = provider;
        this.navigationHelperProvider = provider2;
        this.sessionProvider = provider3;
        this.analyticsServiceProvider = provider4;
    }

    public static MembersInjector<AccountRestrictedDialogFragment> create(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4) {
        return new AccountRestrictedDialogFragment_MembersInjector(provider, provider2, provider3, provider4);
    }

    public void injectMembers(AccountRestrictedDialogFragment accountRestrictedDialogFragment) {
        CustomLayoutBaseDialogFragment_MembersInjector.injectMessageBus(accountRestrictedDialogFragment, (Bus) this.messageBusProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectNavigationHelper(accountRestrictedDialogFragment, (NavigationHelper) this.navigationHelperProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectSession(accountRestrictedDialogFragment, DoubleCheck.lazy(this.sessionProvider));
        CustomLayoutBaseDialogFragment_MembersInjector.injectAnalyticsService(accountRestrictedDialogFragment, DoubleCheck.lazy(this.analyticsServiceProvider));
        injectNavigationHelper(accountRestrictedDialogFragment, (NavigationHelper) this.navigationHelperProvider.get());
        injectSession(accountRestrictedDialogFragment, DoubleCheck.lazy(this.sessionProvider));
    }

    public static void injectNavigationHelper(AccountRestrictedDialogFragment accountRestrictedDialogFragment, NavigationHelper navigationHelper) {
        accountRestrictedDialogFragment.navigationHelper = navigationHelper;
    }

    public static void injectSession(AccountRestrictedDialogFragment accountRestrictedDialogFragment, Lazy<Session> lazy) {
        accountRestrictedDialogFragment.session = lazy;
    }
}
