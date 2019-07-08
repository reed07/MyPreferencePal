package com.myfitnesspal.feature.registration.ui.dialog;

import com.myfitnesspal.feature.registration.model.SignUpModel;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment_MembersInjector;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.squareup.otto.Bus;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class SignUpHeightDialogFragment_MembersInjector implements MembersInjector<SignUpHeightDialogFragment> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<Bus> messageBusProvider;
    private final Provider<SignUpModel> modelProvider;
    private final Provider<NavigationHelper> navigationHelperProvider;
    private final Provider<Session> sessionProvider;

    public SignUpHeightDialogFragment_MembersInjector(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4, Provider<SignUpModel> provider5) {
        this.messageBusProvider = provider;
        this.navigationHelperProvider = provider2;
        this.sessionProvider = provider3;
        this.analyticsServiceProvider = provider4;
        this.modelProvider = provider5;
    }

    public static MembersInjector<SignUpHeightDialogFragment> create(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4, Provider<SignUpModel> provider5) {
        SignUpHeightDialogFragment_MembersInjector signUpHeightDialogFragment_MembersInjector = new SignUpHeightDialogFragment_MembersInjector(provider, provider2, provider3, provider4, provider5);
        return signUpHeightDialogFragment_MembersInjector;
    }

    public void injectMembers(SignUpHeightDialogFragment signUpHeightDialogFragment) {
        CustomLayoutBaseDialogFragment_MembersInjector.injectMessageBus(signUpHeightDialogFragment, (Bus) this.messageBusProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectNavigationHelper(signUpHeightDialogFragment, (NavigationHelper) this.navigationHelperProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectSession(signUpHeightDialogFragment, DoubleCheck.lazy(this.sessionProvider));
        CustomLayoutBaseDialogFragment_MembersInjector.injectAnalyticsService(signUpHeightDialogFragment, DoubleCheck.lazy(this.analyticsServiceProvider));
        injectModel(signUpHeightDialogFragment, (SignUpModel) this.modelProvider.get());
    }

    public static void injectModel(SignUpHeightDialogFragment signUpHeightDialogFragment, SignUpModel signUpModel) {
        signUpHeightDialogFragment.model = signUpModel;
    }
}
