package com.myfitnesspal.feature.registration.ui.dialog;

import com.myfitnesspal.feature.registration.model.SignUpModel;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment_MembersInjector;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.squareup.otto.Bus;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class SignUpWeightDialogFragment_MembersInjector implements MembersInjector<SignUpWeightDialogFragment> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<Bus> messageBusProvider;
    private final Provider<SignUpModel> modelProvider;
    private final Provider<NavigationHelper> navigationHelperProvider;
    private final Provider<Session> sessionProvider;

    public SignUpWeightDialogFragment_MembersInjector(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4, Provider<SignUpModel> provider5) {
        this.messageBusProvider = provider;
        this.navigationHelperProvider = provider2;
        this.sessionProvider = provider3;
        this.analyticsServiceProvider = provider4;
        this.modelProvider = provider5;
    }

    public static MembersInjector<SignUpWeightDialogFragment> create(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4, Provider<SignUpModel> provider5) {
        SignUpWeightDialogFragment_MembersInjector signUpWeightDialogFragment_MembersInjector = new SignUpWeightDialogFragment_MembersInjector(provider, provider2, provider3, provider4, provider5);
        return signUpWeightDialogFragment_MembersInjector;
    }

    public void injectMembers(SignUpWeightDialogFragment signUpWeightDialogFragment) {
        CustomLayoutBaseDialogFragment_MembersInjector.injectMessageBus(signUpWeightDialogFragment, (Bus) this.messageBusProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectNavigationHelper(signUpWeightDialogFragment, (NavigationHelper) this.navigationHelperProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectSession(signUpWeightDialogFragment, DoubleCheck.lazy(this.sessionProvider));
        CustomLayoutBaseDialogFragment_MembersInjector.injectAnalyticsService(signUpWeightDialogFragment, DoubleCheck.lazy(this.analyticsServiceProvider));
        injectModel(signUpWeightDialogFragment, (SignUpModel) this.modelProvider.get());
        injectAnalyticsService(signUpWeightDialogFragment, DoubleCheck.lazy(this.analyticsServiceProvider));
    }

    public static void injectModel(SignUpWeightDialogFragment signUpWeightDialogFragment, SignUpModel signUpModel) {
        signUpWeightDialogFragment.model = signUpModel;
    }

    public static void injectAnalyticsService(SignUpWeightDialogFragment signUpWeightDialogFragment, Lazy<AnalyticsService> lazy) {
        signUpWeightDialogFragment.analyticsService = lazy;
    }
}
