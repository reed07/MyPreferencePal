package com.myfitnesspal.feature.goals.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.goals.service.NutrientGoalService;
import com.myfitnesspal.feature.goals.service.NutrientGoalsUtil;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.premium.PremiumAnalyticsHelper;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.syncv2.SyncService;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.service.userdata.UserHeightService;
import com.myfitnesspal.shared.service.userdata.UserWeightService;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class GoalsFragment_MembersInjector implements MembersInjector<GoalsFragment> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<DiaryService> diaryServiceProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<LocalizedStringsUtil> localizedStringsUtilProvider;
    private final Provider<NutrientGoalService> nutrientGoalServiceProvider;
    private final Provider<NutrientGoalsUtil> nutrientGoalsUtilProvider;
    private final Provider<PremiumAnalyticsHelper> premiumAnalyticsHelperProvider;
    private final Provider<PremiumService> premiumServiceProvider;
    private final Provider<Session> sessionProvider;
    private final Provider<SyncService> syncServiceProvider;
    private final Provider<UserEnergyService> userEnergyServiceProvider;
    private final Provider<UserHeightService> userHeightServiceProvider;
    private final Provider<UserWeightService> userWeightServiceProvider;

    public GoalsFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<UserWeightService> provider3, Provider<UserHeightService> provider4, Provider<UserEnergyService> provider5, Provider<LocalizedStringsUtil> provider6, Provider<PremiumService> provider7, Provider<Session> provider8, Provider<NutrientGoalService> provider9, Provider<NutrientGoalsUtil> provider10, Provider<SyncService> provider11, Provider<AnalyticsService> provider12, Provider<PremiumAnalyticsHelper> provider13, Provider<DiaryService> provider14) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.userWeightServiceProvider = provider3;
        this.userHeightServiceProvider = provider4;
        this.userEnergyServiceProvider = provider5;
        this.localizedStringsUtilProvider = provider6;
        this.premiumServiceProvider = provider7;
        this.sessionProvider = provider8;
        this.nutrientGoalServiceProvider = provider9;
        this.nutrientGoalsUtilProvider = provider10;
        this.syncServiceProvider = provider11;
        this.analyticsServiceProvider = provider12;
        this.premiumAnalyticsHelperProvider = provider13;
        this.diaryServiceProvider = provider14;
    }

    public static MembersInjector<GoalsFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<UserWeightService> provider3, Provider<UserHeightService> provider4, Provider<UserEnergyService> provider5, Provider<LocalizedStringsUtil> provider6, Provider<PremiumService> provider7, Provider<Session> provider8, Provider<NutrientGoalService> provider9, Provider<NutrientGoalsUtil> provider10, Provider<SyncService> provider11, Provider<AnalyticsService> provider12, Provider<PremiumAnalyticsHelper> provider13, Provider<DiaryService> provider14) {
        GoalsFragment_MembersInjector goalsFragment_MembersInjector = new GoalsFragment_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14);
        return goalsFragment_MembersInjector;
    }

    public void injectMembers(GoalsFragment goalsFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(goalsFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(goalsFragment, (Glide) this.glideProvider.get());
        injectUserWeightService(goalsFragment, DoubleCheck.lazy(this.userWeightServiceProvider));
        injectUserHeightService(goalsFragment, DoubleCheck.lazy(this.userHeightServiceProvider));
        injectUserEnergyService(goalsFragment, DoubleCheck.lazy(this.userEnergyServiceProvider));
        injectLocalizedStringsUtil(goalsFragment, DoubleCheck.lazy(this.localizedStringsUtilProvider));
        injectPremiumService(goalsFragment, DoubleCheck.lazy(this.premiumServiceProvider));
        injectSession(goalsFragment, DoubleCheck.lazy(this.sessionProvider));
        injectNutrientGoalService(goalsFragment, DoubleCheck.lazy(this.nutrientGoalServiceProvider));
        injectNutrientGoalsUtil(goalsFragment, DoubleCheck.lazy(this.nutrientGoalsUtilProvider));
        injectSyncService(goalsFragment, DoubleCheck.lazy(this.syncServiceProvider));
        injectAnalyticsService(goalsFragment, DoubleCheck.lazy(this.analyticsServiceProvider));
        injectPremiumAnalyticsHelper(goalsFragment, DoubleCheck.lazy(this.premiumAnalyticsHelperProvider));
        injectDiaryService(goalsFragment, DoubleCheck.lazy(this.diaryServiceProvider));
    }

    public static void injectUserWeightService(GoalsFragment goalsFragment, Lazy<UserWeightService> lazy) {
        goalsFragment.userWeightService = lazy;
    }

    public static void injectUserHeightService(GoalsFragment goalsFragment, Lazy<UserHeightService> lazy) {
        goalsFragment.userHeightService = lazy;
    }

    public static void injectUserEnergyService(GoalsFragment goalsFragment, Lazy<UserEnergyService> lazy) {
        goalsFragment.userEnergyService = lazy;
    }

    public static void injectLocalizedStringsUtil(GoalsFragment goalsFragment, Lazy<LocalizedStringsUtil> lazy) {
        goalsFragment.localizedStringsUtil = lazy;
    }

    public static void injectPremiumService(GoalsFragment goalsFragment, Lazy<PremiumService> lazy) {
        goalsFragment.premiumService = lazy;
    }

    public static void injectSession(GoalsFragment goalsFragment, Lazy<Session> lazy) {
        goalsFragment.session = lazy;
    }

    public static void injectNutrientGoalService(GoalsFragment goalsFragment, Lazy<NutrientGoalService> lazy) {
        goalsFragment.nutrientGoalService = lazy;
    }

    public static void injectNutrientGoalsUtil(GoalsFragment goalsFragment, Lazy<NutrientGoalsUtil> lazy) {
        goalsFragment.nutrientGoalsUtil = lazy;
    }

    public static void injectSyncService(GoalsFragment goalsFragment, Lazy<SyncService> lazy) {
        goalsFragment.syncService = lazy;
    }

    public static void injectAnalyticsService(GoalsFragment goalsFragment, Lazy<AnalyticsService> lazy) {
        goalsFragment.analyticsService = lazy;
    }

    public static void injectPremiumAnalyticsHelper(GoalsFragment goalsFragment, Lazy<PremiumAnalyticsHelper> lazy) {
        goalsFragment.premiumAnalyticsHelper = lazy;
    }

    public static void injectDiaryService(GoalsFragment goalsFragment, Lazy<DiaryService> lazy) {
        goalsFragment.diaryService = lazy;
    }
}
