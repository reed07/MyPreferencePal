package com.myfitnesspal.shared.provider;

import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.goals.service.NutrientGoalService;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.syncv2.SyncService;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class MPFAppWidgetProvider_MembersInjector implements MembersInjector<MPFAppWidgetProvider> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<DiaryService> diaryServiceProvider;
    private final Provider<LocalizedStringsUtil> localizedStringsUtilProvider;
    private final Provider<NutrientGoalService> nutrientGoalServiceProvider;
    private final Provider<Session> sessionProvider;
    private final Provider<SyncService> syncServiceProvider;
    private final Provider<UserEnergyService> userEnergyServiceProvider;

    public MPFAppWidgetProvider_MembersInjector(Provider<AnalyticsService> provider, Provider<Session> provider2, Provider<LocalizedStringsUtil> provider3, Provider<UserEnergyService> provider4, Provider<SyncService> provider5, Provider<NutrientGoalService> provider6, Provider<DiaryService> provider7) {
        this.analyticsServiceProvider = provider;
        this.sessionProvider = provider2;
        this.localizedStringsUtilProvider = provider3;
        this.userEnergyServiceProvider = provider4;
        this.syncServiceProvider = provider5;
        this.nutrientGoalServiceProvider = provider6;
        this.diaryServiceProvider = provider7;
    }

    public static MembersInjector<MPFAppWidgetProvider> create(Provider<AnalyticsService> provider, Provider<Session> provider2, Provider<LocalizedStringsUtil> provider3, Provider<UserEnergyService> provider4, Provider<SyncService> provider5, Provider<NutrientGoalService> provider6, Provider<DiaryService> provider7) {
        MPFAppWidgetProvider_MembersInjector mPFAppWidgetProvider_MembersInjector = new MPFAppWidgetProvider_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7);
        return mPFAppWidgetProvider_MembersInjector;
    }

    public void injectMembers(MPFAppWidgetProvider mPFAppWidgetProvider) {
        injectAnalyticsService(mPFAppWidgetProvider, DoubleCheck.lazy(this.analyticsServiceProvider));
        injectSession(mPFAppWidgetProvider, DoubleCheck.lazy(this.sessionProvider));
        injectLocalizedStringsUtil(mPFAppWidgetProvider, DoubleCheck.lazy(this.localizedStringsUtilProvider));
        injectUserEnergyService(mPFAppWidgetProvider, DoubleCheck.lazy(this.userEnergyServiceProvider));
        injectSyncService(mPFAppWidgetProvider, DoubleCheck.lazy(this.syncServiceProvider));
        injectNutrientGoalService(mPFAppWidgetProvider, DoubleCheck.lazy(this.nutrientGoalServiceProvider));
        injectDiaryService(mPFAppWidgetProvider, DoubleCheck.lazy(this.diaryServiceProvider));
    }

    public static void injectAnalyticsService(MPFAppWidgetProvider mPFAppWidgetProvider, Lazy<AnalyticsService> lazy) {
        mPFAppWidgetProvider.analyticsService = lazy;
    }

    public static void injectSession(MPFAppWidgetProvider mPFAppWidgetProvider, Lazy<Session> lazy) {
        mPFAppWidgetProvider.session = lazy;
    }

    public static void injectLocalizedStringsUtil(MPFAppWidgetProvider mPFAppWidgetProvider, Lazy<LocalizedStringsUtil> lazy) {
        mPFAppWidgetProvider.localizedStringsUtil = lazy;
    }

    public static void injectUserEnergyService(MPFAppWidgetProvider mPFAppWidgetProvider, Lazy<UserEnergyService> lazy) {
        mPFAppWidgetProvider.userEnergyService = lazy;
    }

    public static void injectSyncService(MPFAppWidgetProvider mPFAppWidgetProvider, Lazy<SyncService> lazy) {
        mPFAppWidgetProvider.syncService = lazy;
    }

    public static void injectNutrientGoalService(MPFAppWidgetProvider mPFAppWidgetProvider, Lazy<NutrientGoalService> lazy) {
        mPFAppWidgetProvider.nutrientGoalService = lazy;
    }

    public static void injectDiaryService(MPFAppWidgetProvider mPFAppWidgetProvider, Lazy<DiaryService> lazy) {
        mPFAppWidgetProvider.diaryService = lazy;
    }
}
