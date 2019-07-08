package com.myfitnesspal.feature.exercise.ui.dialog;

import com.myfitnesspal.feature.search.util.SortOrderHelper;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment_MembersInjector;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.squareup.otto.Bus;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class ExerciseSortOrderDialogFragment_MembersInjector implements MembersInjector<ExerciseSortOrderDialogFragment> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<Bus> messageBusProvider;
    private final Provider<NavigationHelper> navigationHelperProvider;
    private final Provider<Session> sessionProvider;
    private final Provider<SortOrderHelper> sortOrderHelperProvider;

    public ExerciseSortOrderDialogFragment_MembersInjector(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4, Provider<SortOrderHelper> provider5) {
        this.messageBusProvider = provider;
        this.navigationHelperProvider = provider2;
        this.sessionProvider = provider3;
        this.analyticsServiceProvider = provider4;
        this.sortOrderHelperProvider = provider5;
    }

    public static MembersInjector<ExerciseSortOrderDialogFragment> create(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4, Provider<SortOrderHelper> provider5) {
        ExerciseSortOrderDialogFragment_MembersInjector exerciseSortOrderDialogFragment_MembersInjector = new ExerciseSortOrderDialogFragment_MembersInjector(provider, provider2, provider3, provider4, provider5);
        return exerciseSortOrderDialogFragment_MembersInjector;
    }

    public void injectMembers(ExerciseSortOrderDialogFragment exerciseSortOrderDialogFragment) {
        CustomLayoutBaseDialogFragment_MembersInjector.injectMessageBus(exerciseSortOrderDialogFragment, (Bus) this.messageBusProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectNavigationHelper(exerciseSortOrderDialogFragment, (NavigationHelper) this.navigationHelperProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectSession(exerciseSortOrderDialogFragment, DoubleCheck.lazy(this.sessionProvider));
        CustomLayoutBaseDialogFragment_MembersInjector.injectAnalyticsService(exerciseSortOrderDialogFragment, DoubleCheck.lazy(this.analyticsServiceProvider));
        injectSortOrderHelper(exerciseSortOrderDialogFragment, DoubleCheck.lazy(this.sortOrderHelperProvider));
        injectAnalyticsService(exerciseSortOrderDialogFragment, DoubleCheck.lazy(this.analyticsServiceProvider));
    }

    public static void injectSortOrderHelper(ExerciseSortOrderDialogFragment exerciseSortOrderDialogFragment, Lazy<SortOrderHelper> lazy) {
        exerciseSortOrderDialogFragment.sortOrderHelper = lazy;
    }

    public static void injectAnalyticsService(ExerciseSortOrderDialogFragment exerciseSortOrderDialogFragment, Lazy<AnalyticsService> lazy) {
        exerciseSortOrderDialogFragment.analyticsService = lazy;
    }
}
