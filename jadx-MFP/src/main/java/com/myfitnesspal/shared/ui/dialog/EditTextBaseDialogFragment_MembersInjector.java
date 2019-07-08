package com.myfitnesspal.shared.ui.dialog;

import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.squareup.otto.Bus;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class EditTextBaseDialogFragment_MembersInjector implements MembersInjector<EditTextBaseDialogFragment> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<Bus> messageBusProvider;
    private final Provider<NavigationHelper> navigationHelperProvider;
    private final Provider<Session> sessionProvider;

    public EditTextBaseDialogFragment_MembersInjector(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4) {
        this.messageBusProvider = provider;
        this.navigationHelperProvider = provider2;
        this.sessionProvider = provider3;
        this.analyticsServiceProvider = provider4;
    }

    public static MembersInjector<EditTextBaseDialogFragment> create(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4) {
        return new EditTextBaseDialogFragment_MembersInjector(provider, provider2, provider3, provider4);
    }

    public void injectMembers(EditTextBaseDialogFragment editTextBaseDialogFragment) {
        CustomLayoutBaseDialogFragment_MembersInjector.injectMessageBus(editTextBaseDialogFragment, (Bus) this.messageBusProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectNavigationHelper(editTextBaseDialogFragment, (NavigationHelper) this.navigationHelperProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectSession(editTextBaseDialogFragment, DoubleCheck.lazy(this.sessionProvider));
        CustomLayoutBaseDialogFragment_MembersInjector.injectAnalyticsService(editTextBaseDialogFragment, DoubleCheck.lazy(this.analyticsServiceProvider));
    }
}
