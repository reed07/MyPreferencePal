package com.myfitnesspal.feature.diary.ui.dialog;

import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment_MembersInjector;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.squareup.otto.Bus;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class DiaryLongPressDialogFragment_MembersInjector implements MembersInjector<DiaryLongPressDialogFragment> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<ConfigService> configServiceProvider;
    private final Provider<Bus> messageBusAndBusProvider;
    private final Provider<NavigationHelper> navigationHelperProvider;
    private final Provider<Session> sessionProvider;

    public DiaryLongPressDialogFragment_MembersInjector(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4, Provider<ConfigService> provider5) {
        this.messageBusAndBusProvider = provider;
        this.navigationHelperProvider = provider2;
        this.sessionProvider = provider3;
        this.analyticsServiceProvider = provider4;
        this.configServiceProvider = provider5;
    }

    public static MembersInjector<DiaryLongPressDialogFragment> create(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4, Provider<ConfigService> provider5) {
        DiaryLongPressDialogFragment_MembersInjector diaryLongPressDialogFragment_MembersInjector = new DiaryLongPressDialogFragment_MembersInjector(provider, provider2, provider3, provider4, provider5);
        return diaryLongPressDialogFragment_MembersInjector;
    }

    public void injectMembers(DiaryLongPressDialogFragment diaryLongPressDialogFragment) {
        CustomLayoutBaseDialogFragment_MembersInjector.injectMessageBus(diaryLongPressDialogFragment, (Bus) this.messageBusAndBusProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectNavigationHelper(diaryLongPressDialogFragment, (NavigationHelper) this.navigationHelperProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectSession(diaryLongPressDialogFragment, DoubleCheck.lazy(this.sessionProvider));
        CustomLayoutBaseDialogFragment_MembersInjector.injectAnalyticsService(diaryLongPressDialogFragment, DoubleCheck.lazy(this.analyticsServiceProvider));
        injectAnalyticsService(diaryLongPressDialogFragment, DoubleCheck.lazy(this.analyticsServiceProvider));
        injectBus(diaryLongPressDialogFragment, DoubleCheck.lazy(this.messageBusAndBusProvider));
        injectConfigService(diaryLongPressDialogFragment, DoubleCheck.lazy(this.configServiceProvider));
    }

    public static void injectAnalyticsService(DiaryLongPressDialogFragment diaryLongPressDialogFragment, Lazy<AnalyticsService> lazy) {
        diaryLongPressDialogFragment.analyticsService = lazy;
    }

    public static void injectBus(DiaryLongPressDialogFragment diaryLongPressDialogFragment, Lazy<Bus> lazy) {
        diaryLongPressDialogFragment.bus = lazy;
    }

    public static void injectConfigService(DiaryLongPressDialogFragment diaryLongPressDialogFragment, Lazy<ConfigService> lazy) {
        diaryLongPressDialogFragment.configService = lazy;
    }
}
