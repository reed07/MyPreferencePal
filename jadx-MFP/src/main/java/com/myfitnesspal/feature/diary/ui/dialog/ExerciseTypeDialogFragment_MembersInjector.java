package com.myfitnesspal.feature.diary.ui.dialog;

import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment_MembersInjector;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.squareup.otto.Bus;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class ExerciseTypeDialogFragment_MembersInjector implements MembersInjector<ExerciseTypeDialogFragment> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<LocalizedStringsUtil> localizedStringsUtilProvider;
    private final Provider<Bus> messageBusProvider;
    private final Provider<NavigationHelper> navigationHelperProvider;
    private final Provider<Session> sessionProvider;

    public ExerciseTypeDialogFragment_MembersInjector(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4, Provider<LocalizedStringsUtil> provider5) {
        this.messageBusProvider = provider;
        this.navigationHelperProvider = provider2;
        this.sessionProvider = provider3;
        this.analyticsServiceProvider = provider4;
        this.localizedStringsUtilProvider = provider5;
    }

    public static MembersInjector<ExerciseTypeDialogFragment> create(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4, Provider<LocalizedStringsUtil> provider5) {
        ExerciseTypeDialogFragment_MembersInjector exerciseTypeDialogFragment_MembersInjector = new ExerciseTypeDialogFragment_MembersInjector(provider, provider2, provider3, provider4, provider5);
        return exerciseTypeDialogFragment_MembersInjector;
    }

    public void injectMembers(ExerciseTypeDialogFragment exerciseTypeDialogFragment) {
        CustomLayoutBaseDialogFragment_MembersInjector.injectMessageBus(exerciseTypeDialogFragment, (Bus) this.messageBusProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectNavigationHelper(exerciseTypeDialogFragment, (NavigationHelper) this.navigationHelperProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectSession(exerciseTypeDialogFragment, DoubleCheck.lazy(this.sessionProvider));
        CustomLayoutBaseDialogFragment_MembersInjector.injectAnalyticsService(exerciseTypeDialogFragment, DoubleCheck.lazy(this.analyticsServiceProvider));
        injectLocalizedStringsUtil(exerciseTypeDialogFragment, (LocalizedStringsUtil) this.localizedStringsUtilProvider.get());
    }

    public static void injectLocalizedStringsUtil(ExerciseTypeDialogFragment exerciseTypeDialogFragment, LocalizedStringsUtil localizedStringsUtil) {
        exerciseTypeDialogFragment.localizedStringsUtil = localizedStringsUtil;
    }
}
