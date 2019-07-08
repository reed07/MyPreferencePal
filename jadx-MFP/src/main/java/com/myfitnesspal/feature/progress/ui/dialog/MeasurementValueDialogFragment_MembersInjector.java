package com.myfitnesspal.feature.progress.ui.dialog;

import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.userdata.UserHeightService;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment_MembersInjector;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.myfitnesspal.shared.validation.Validator;
import com.squareup.otto.Bus;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class MeasurementValueDialogFragment_MembersInjector implements MembersInjector<MeasurementValueDialogFragment> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<LocalizedStringsUtil> localizedStringsUtilProvider;
    private final Provider<Bus> messageBusProvider;
    private final Provider<NavigationHelper> navigationHelperProvider;
    private final Provider<Session> sessionProvider;
    private final Provider<UserHeightService> userHeightServiceProvider;
    private final Provider<Validator> validatorProvider;

    public MeasurementValueDialogFragment_MembersInjector(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4, Provider<UserHeightService> provider5, Provider<Validator> provider6, Provider<LocalizedStringsUtil> provider7) {
        this.messageBusProvider = provider;
        this.navigationHelperProvider = provider2;
        this.sessionProvider = provider3;
        this.analyticsServiceProvider = provider4;
        this.userHeightServiceProvider = provider5;
        this.validatorProvider = provider6;
        this.localizedStringsUtilProvider = provider7;
    }

    public static MembersInjector<MeasurementValueDialogFragment> create(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4, Provider<UserHeightService> provider5, Provider<Validator> provider6, Provider<LocalizedStringsUtil> provider7) {
        MeasurementValueDialogFragment_MembersInjector measurementValueDialogFragment_MembersInjector = new MeasurementValueDialogFragment_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7);
        return measurementValueDialogFragment_MembersInjector;
    }

    public void injectMembers(MeasurementValueDialogFragment measurementValueDialogFragment) {
        CustomLayoutBaseDialogFragment_MembersInjector.injectMessageBus(measurementValueDialogFragment, (Bus) this.messageBusProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectNavigationHelper(measurementValueDialogFragment, (NavigationHelper) this.navigationHelperProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectSession(measurementValueDialogFragment, DoubleCheck.lazy(this.sessionProvider));
        CustomLayoutBaseDialogFragment_MembersInjector.injectAnalyticsService(measurementValueDialogFragment, DoubleCheck.lazy(this.analyticsServiceProvider));
        injectUserHeightService(measurementValueDialogFragment, (UserHeightService) this.userHeightServiceProvider.get());
        injectValidator(measurementValueDialogFragment, (Validator) this.validatorProvider.get());
        injectLocalizedStringsUtil(measurementValueDialogFragment, (LocalizedStringsUtil) this.localizedStringsUtilProvider.get());
    }

    public static void injectUserHeightService(MeasurementValueDialogFragment measurementValueDialogFragment, UserHeightService userHeightService) {
        measurementValueDialogFragment.userHeightService = userHeightService;
    }

    public static void injectValidator(MeasurementValueDialogFragment measurementValueDialogFragment, Validator validator) {
        measurementValueDialogFragment.validator = validator;
    }

    public static void injectLocalizedStringsUtil(MeasurementValueDialogFragment measurementValueDialogFragment, LocalizedStringsUtil localizedStringsUtil) {
        measurementValueDialogFragment.localizedStringsUtil = localizedStringsUtil;
    }
}
