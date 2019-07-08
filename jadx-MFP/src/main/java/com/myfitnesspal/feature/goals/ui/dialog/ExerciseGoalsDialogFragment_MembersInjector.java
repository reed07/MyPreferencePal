package com.myfitnesspal.feature.goals.ui.dialog;

import com.myfitnesspal.feature.goals.service.NutrientGoalsUtil;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment_MembersInjector;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.squareup.otto.Bus;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class ExerciseGoalsDialogFragment_MembersInjector implements MembersInjector<ExerciseGoalsDialogFragment> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<Bus> messageBusProvider;
    private final Provider<NavigationHelper> navigationHelperProvider;
    private final Provider<NutrientGoalsUtil> nutrientGoalsUtilProvider;
    private final Provider<Session> sessionProvider;

    public ExerciseGoalsDialogFragment_MembersInjector(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4, Provider<NutrientGoalsUtil> provider5) {
        this.messageBusProvider = provider;
        this.navigationHelperProvider = provider2;
        this.sessionProvider = provider3;
        this.analyticsServiceProvider = provider4;
        this.nutrientGoalsUtilProvider = provider5;
    }

    public static MembersInjector<ExerciseGoalsDialogFragment> create(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4, Provider<NutrientGoalsUtil> provider5) {
        ExerciseGoalsDialogFragment_MembersInjector exerciseGoalsDialogFragment_MembersInjector = new ExerciseGoalsDialogFragment_MembersInjector(provider, provider2, provider3, provider4, provider5);
        return exerciseGoalsDialogFragment_MembersInjector;
    }

    public void injectMembers(ExerciseGoalsDialogFragment exerciseGoalsDialogFragment) {
        CustomLayoutBaseDialogFragment_MembersInjector.injectMessageBus(exerciseGoalsDialogFragment, (Bus) this.messageBusProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectNavigationHelper(exerciseGoalsDialogFragment, (NavigationHelper) this.navigationHelperProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectSession(exerciseGoalsDialogFragment, DoubleCheck.lazy(this.sessionProvider));
        CustomLayoutBaseDialogFragment_MembersInjector.injectAnalyticsService(exerciseGoalsDialogFragment, DoubleCheck.lazy(this.analyticsServiceProvider));
        injectNutrientGoalsUtil(exerciseGoalsDialogFragment, DoubleCheck.lazy(this.nutrientGoalsUtilProvider));
    }

    public static void injectNutrientGoalsUtil(ExerciseGoalsDialogFragment exerciseGoalsDialogFragment, Lazy<NutrientGoalsUtil> lazy) {
        exerciseGoalsDialogFragment.nutrientGoalsUtil = lazy;
    }
}
