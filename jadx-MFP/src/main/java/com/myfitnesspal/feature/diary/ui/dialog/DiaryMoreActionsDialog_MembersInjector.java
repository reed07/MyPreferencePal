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

public final class DiaryMoreActionsDialog_MembersInjector implements MembersInjector<DiaryMoreActionsDialog> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<ConfigService> configServiceProvider;
    private final Provider<Bus> messageBusProvider;
    private final Provider<NavigationHelper> navigationHelperProvider;
    private final Provider<Session> sessionProvider;

    public DiaryMoreActionsDialog_MembersInjector(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4, Provider<ConfigService> provider5) {
        this.messageBusProvider = provider;
        this.navigationHelperProvider = provider2;
        this.sessionProvider = provider3;
        this.analyticsServiceProvider = provider4;
        this.configServiceProvider = provider5;
    }

    public static MembersInjector<DiaryMoreActionsDialog> create(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4, Provider<ConfigService> provider5) {
        DiaryMoreActionsDialog_MembersInjector diaryMoreActionsDialog_MembersInjector = new DiaryMoreActionsDialog_MembersInjector(provider, provider2, provider3, provider4, provider5);
        return diaryMoreActionsDialog_MembersInjector;
    }

    public void injectMembers(DiaryMoreActionsDialog diaryMoreActionsDialog) {
        CustomLayoutBaseDialogFragment_MembersInjector.injectMessageBus(diaryMoreActionsDialog, (Bus) this.messageBusProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectNavigationHelper(diaryMoreActionsDialog, (NavigationHelper) this.navigationHelperProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectSession(diaryMoreActionsDialog, DoubleCheck.lazy(this.sessionProvider));
        CustomLayoutBaseDialogFragment_MembersInjector.injectAnalyticsService(diaryMoreActionsDialog, DoubleCheck.lazy(this.analyticsServiceProvider));
        injectConfigService(diaryMoreActionsDialog, DoubleCheck.lazy(this.configServiceProvider));
    }

    public static void injectConfigService(DiaryMoreActionsDialog diaryMoreActionsDialog, Lazy<ConfigService> lazy) {
        diaryMoreActionsDialog.configService = lazy;
    }
}
