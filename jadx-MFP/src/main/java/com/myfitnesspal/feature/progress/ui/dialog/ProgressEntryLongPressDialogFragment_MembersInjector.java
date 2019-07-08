package com.myfitnesspal.feature.progress.ui.dialog;

import com.myfitnesspal.feature.images.service.ImageAssociationService;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.measurements.MeasurementsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment_MembersInjector;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.squareup.otto.Bus;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class ProgressEntryLongPressDialogFragment_MembersInjector implements MembersInjector<ProgressEntryLongPressDialogFragment> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<ImageAssociationService> imageAssociationServiceProvider;
    private final Provider<MeasurementsService> measurementServiceProvider;
    private final Provider<Bus> messageBusProvider;
    private final Provider<NavigationHelper> navigationHelperProvider;
    private final Provider<Session> sessionProvider;

    public ProgressEntryLongPressDialogFragment_MembersInjector(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4, Provider<MeasurementsService> provider5, Provider<ImageAssociationService> provider6) {
        this.messageBusProvider = provider;
        this.navigationHelperProvider = provider2;
        this.sessionProvider = provider3;
        this.analyticsServiceProvider = provider4;
        this.measurementServiceProvider = provider5;
        this.imageAssociationServiceProvider = provider6;
    }

    public static MembersInjector<ProgressEntryLongPressDialogFragment> create(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4, Provider<MeasurementsService> provider5, Provider<ImageAssociationService> provider6) {
        ProgressEntryLongPressDialogFragment_MembersInjector progressEntryLongPressDialogFragment_MembersInjector = new ProgressEntryLongPressDialogFragment_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6);
        return progressEntryLongPressDialogFragment_MembersInjector;
    }

    public void injectMembers(ProgressEntryLongPressDialogFragment progressEntryLongPressDialogFragment) {
        CustomLayoutBaseDialogFragment_MembersInjector.injectMessageBus(progressEntryLongPressDialogFragment, (Bus) this.messageBusProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectNavigationHelper(progressEntryLongPressDialogFragment, (NavigationHelper) this.navigationHelperProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectSession(progressEntryLongPressDialogFragment, DoubleCheck.lazy(this.sessionProvider));
        CustomLayoutBaseDialogFragment_MembersInjector.injectAnalyticsService(progressEntryLongPressDialogFragment, DoubleCheck.lazy(this.analyticsServiceProvider));
        injectMeasurementService(progressEntryLongPressDialogFragment, DoubleCheck.lazy(this.measurementServiceProvider));
        injectImageAssociationService(progressEntryLongPressDialogFragment, DoubleCheck.lazy(this.imageAssociationServiceProvider));
    }

    public static void injectMeasurementService(ProgressEntryLongPressDialogFragment progressEntryLongPressDialogFragment, Lazy<MeasurementsService> lazy) {
        progressEntryLongPressDialogFragment.measurementService = lazy;
    }

    public static void injectImageAssociationService(ProgressEntryLongPressDialogFragment progressEntryLongPressDialogFragment, Lazy<ImageAssociationService> lazy) {
        progressEntryLongPressDialogFragment.imageAssociationService = lazy;
    }
}
