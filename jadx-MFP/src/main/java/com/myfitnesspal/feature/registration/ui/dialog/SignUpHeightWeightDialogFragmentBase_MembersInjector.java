package com.myfitnesspal.feature.registration.ui.dialog;

import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment_MembersInjector;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.squareup.otto.Bus;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class SignUpHeightWeightDialogFragmentBase_MembersInjector implements MembersInjector<SignUpHeightWeightDialogFragmentBase> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<Bus> messageBusProvider;
    private final Provider<NavigationHelper> navigationHelperProvider;
    private final Provider<Session> sessionProvider;

    public SignUpHeightWeightDialogFragmentBase_MembersInjector(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4) {
        this.messageBusProvider = provider;
        this.navigationHelperProvider = provider2;
        this.sessionProvider = provider3;
        this.analyticsServiceProvider = provider4;
    }

    public static MembersInjector<SignUpHeightWeightDialogFragmentBase> create(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4) {
        return new SignUpHeightWeightDialogFragmentBase_MembersInjector(provider, provider2, provider3, provider4);
    }

    public void injectMembers(SignUpHeightWeightDialogFragmentBase signUpHeightWeightDialogFragmentBase) {
        CustomLayoutBaseDialogFragment_MembersInjector.injectMessageBus(signUpHeightWeightDialogFragmentBase, (Bus) this.messageBusProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectNavigationHelper(signUpHeightWeightDialogFragmentBase, (NavigationHelper) this.navigationHelperProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectSession(signUpHeightWeightDialogFragmentBase, DoubleCheck.lazy(this.sessionProvider));
        CustomLayoutBaseDialogFragment_MembersInjector.injectAnalyticsService(signUpHeightWeightDialogFragmentBase, DoubleCheck.lazy(this.analyticsServiceProvider));
    }
}
