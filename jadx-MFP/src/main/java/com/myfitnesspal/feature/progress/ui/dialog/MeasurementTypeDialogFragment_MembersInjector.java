package com.myfitnesspal.feature.progress.ui.dialog;

import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.steps.StepService;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment_MembersInjector;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.squareup.otto.Bus;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class MeasurementTypeDialogFragment_MembersInjector implements MembersInjector<MeasurementTypeDialogFragment> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<DbConnectionManager> dbConnectionManagerProvider;
    private final Provider<LocalizedStringsUtil> localizedStringsUtilProvider;
    private final Provider<Bus> messageBusProvider;
    private final Provider<NavigationHelper> navigationHelperProvider;
    private final Provider<Session> sessionProvider;
    private final Provider<StepService> stepServicesProvider;

    public MeasurementTypeDialogFragment_MembersInjector(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4, Provider<LocalizedStringsUtil> provider5, Provider<StepService> provider6, Provider<DbConnectionManager> provider7) {
        this.messageBusProvider = provider;
        this.navigationHelperProvider = provider2;
        this.sessionProvider = provider3;
        this.analyticsServiceProvider = provider4;
        this.localizedStringsUtilProvider = provider5;
        this.stepServicesProvider = provider6;
        this.dbConnectionManagerProvider = provider7;
    }

    public static MembersInjector<MeasurementTypeDialogFragment> create(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4, Provider<LocalizedStringsUtil> provider5, Provider<StepService> provider6, Provider<DbConnectionManager> provider7) {
        MeasurementTypeDialogFragment_MembersInjector measurementTypeDialogFragment_MembersInjector = new MeasurementTypeDialogFragment_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7);
        return measurementTypeDialogFragment_MembersInjector;
    }

    public void injectMembers(MeasurementTypeDialogFragment measurementTypeDialogFragment) {
        CustomLayoutBaseDialogFragment_MembersInjector.injectMessageBus(measurementTypeDialogFragment, (Bus) this.messageBusProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectNavigationHelper(measurementTypeDialogFragment, (NavigationHelper) this.navigationHelperProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectSession(measurementTypeDialogFragment, DoubleCheck.lazy(this.sessionProvider));
        CustomLayoutBaseDialogFragment_MembersInjector.injectAnalyticsService(measurementTypeDialogFragment, DoubleCheck.lazy(this.analyticsServiceProvider));
        injectLocalizedStringsUtil(measurementTypeDialogFragment, DoubleCheck.lazy(this.localizedStringsUtilProvider));
        injectStepServices(measurementTypeDialogFragment, DoubleCheck.lazy(this.stepServicesProvider));
        injectDbConnectionManager(measurementTypeDialogFragment, DoubleCheck.lazy(this.dbConnectionManagerProvider));
    }

    public static void injectLocalizedStringsUtil(MeasurementTypeDialogFragment measurementTypeDialogFragment, Lazy<LocalizedStringsUtil> lazy) {
        measurementTypeDialogFragment.localizedStringsUtil = lazy;
    }

    public static void injectStepServices(MeasurementTypeDialogFragment measurementTypeDialogFragment, Lazy<StepService> lazy) {
        measurementTypeDialogFragment.stepServices = lazy;
    }

    public static void injectDbConnectionManager(MeasurementTypeDialogFragment measurementTypeDialogFragment, Lazy<DbConnectionManager> lazy) {
        measurementTypeDialogFragment.dbConnectionManager = lazy;
    }
}
