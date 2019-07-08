package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import android.content.SharedPreferences;
import com.myfitnesspal.feature.appgallery.service.AppGalleryService;
import com.myfitnesspal.feature.dashboard.service.NutrientDashboardAnalyticsHelper;
import com.myfitnesspal.feature.dashboard.ui.view.TextNutrientDashboard;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.externalsync.impl.googlefit.client.GoogleFitClient;
import com.myfitnesspal.feature.goals.service.NutrientGoalService;
import com.myfitnesspal.feature.goals.service.NutrientGoalsUtil;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.shared.service.analytics.ActionTrackingService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.steps.StepService;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesTextNutrientDashboardFactory implements Factory<TextNutrientDashboard> {
    private final Provider<ActionTrackingService> actionTrackingServiceProvider;
    private final Provider<AppGalleryService> appGalleryServiceProvider;
    private final Provider<Context> contextProvider;
    private final Provider<DiaryService> diaryServiceProvider;
    private final Provider<GoogleFitClient> googleFitClientProvider;
    private final Provider<LocalizedStringsUtil> localizedStringsUtilProvider;
    private final ApplicationModule module;
    private final Provider<NutrientDashboardAnalyticsHelper> nutrientDashboardAnalyticsHelperProvider;
    private final Provider<NutrientGoalService> nutrientGoalServiceProvider;
    private final Provider<NutrientGoalsUtil> nutritionalGoalsUtilProvider;
    private final Provider<PremiumService> premiumServiceProvider;
    private final Provider<Session> sessionProvider;
    private final Provider<SharedPreferences> sharedPreferencesProvider;
    private final Provider<StepService> stepServiceProvider;
    private final Provider<UserEnergyService> userEnergyServiceProvider;

    public ApplicationModule_ProvidesTextNutrientDashboardFactory(ApplicationModule applicationModule, Provider<Context> provider, Provider<UserEnergyService> provider2, Provider<Session> provider3, Provider<LocalizedStringsUtil> provider4, Provider<StepService> provider5, Provider<ActionTrackingService> provider6, Provider<NutrientGoalService> provider7, Provider<NutrientGoalsUtil> provider8, Provider<PremiumService> provider9, Provider<SharedPreferences> provider10, Provider<DiaryService> provider11, Provider<AppGalleryService> provider12, Provider<GoogleFitClient> provider13, Provider<NutrientDashboardAnalyticsHelper> provider14) {
        this.module = applicationModule;
        this.contextProvider = provider;
        this.userEnergyServiceProvider = provider2;
        this.sessionProvider = provider3;
        this.localizedStringsUtilProvider = provider4;
        this.stepServiceProvider = provider5;
        this.actionTrackingServiceProvider = provider6;
        this.nutrientGoalServiceProvider = provider7;
        this.nutritionalGoalsUtilProvider = provider8;
        this.premiumServiceProvider = provider9;
        this.sharedPreferencesProvider = provider10;
        this.diaryServiceProvider = provider11;
        this.appGalleryServiceProvider = provider12;
        this.googleFitClientProvider = provider13;
        this.nutrientDashboardAnalyticsHelperProvider = provider14;
    }

    public TextNutrientDashboard get() {
        return provideInstance(this.module, this.contextProvider, this.userEnergyServiceProvider, this.sessionProvider, this.localizedStringsUtilProvider, this.stepServiceProvider, this.actionTrackingServiceProvider, this.nutrientGoalServiceProvider, this.nutritionalGoalsUtilProvider, this.premiumServiceProvider, this.sharedPreferencesProvider, this.diaryServiceProvider, this.appGalleryServiceProvider, this.googleFitClientProvider, this.nutrientDashboardAnalyticsHelperProvider);
    }

    public static TextNutrientDashboard provideInstance(ApplicationModule applicationModule, Provider<Context> provider, Provider<UserEnergyService> provider2, Provider<Session> provider3, Provider<LocalizedStringsUtil> provider4, Provider<StepService> provider5, Provider<ActionTrackingService> provider6, Provider<NutrientGoalService> provider7, Provider<NutrientGoalsUtil> provider8, Provider<PremiumService> provider9, Provider<SharedPreferences> provider10, Provider<DiaryService> provider11, Provider<AppGalleryService> provider12, Provider<GoogleFitClient> provider13, Provider<NutrientDashboardAnalyticsHelper> provider14) {
        return proxyProvidesTextNutrientDashboard(applicationModule, (Context) provider.get(), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3), DoubleCheck.lazy(provider4), DoubleCheck.lazy(provider5), DoubleCheck.lazy(provider6), DoubleCheck.lazy(provider7), DoubleCheck.lazy(provider8), DoubleCheck.lazy(provider9), DoubleCheck.lazy(provider10), DoubleCheck.lazy(provider11), DoubleCheck.lazy(provider12), DoubleCheck.lazy(provider13), DoubleCheck.lazy(provider14));
    }

    public static ApplicationModule_ProvidesTextNutrientDashboardFactory create(ApplicationModule applicationModule, Provider<Context> provider, Provider<UserEnergyService> provider2, Provider<Session> provider3, Provider<LocalizedStringsUtil> provider4, Provider<StepService> provider5, Provider<ActionTrackingService> provider6, Provider<NutrientGoalService> provider7, Provider<NutrientGoalsUtil> provider8, Provider<PremiumService> provider9, Provider<SharedPreferences> provider10, Provider<DiaryService> provider11, Provider<AppGalleryService> provider12, Provider<GoogleFitClient> provider13, Provider<NutrientDashboardAnalyticsHelper> provider14) {
        ApplicationModule_ProvidesTextNutrientDashboardFactory applicationModule_ProvidesTextNutrientDashboardFactory = new ApplicationModule_ProvidesTextNutrientDashboardFactory(applicationModule, provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14);
        return applicationModule_ProvidesTextNutrientDashboardFactory;
    }

    public static TextNutrientDashboard proxyProvidesTextNutrientDashboard(ApplicationModule applicationModule, Context context, Lazy<UserEnergyService> lazy, Lazy<Session> lazy2, Lazy<LocalizedStringsUtil> lazy3, Lazy<StepService> lazy4, Lazy<ActionTrackingService> lazy5, Lazy<NutrientGoalService> lazy6, Lazy<NutrientGoalsUtil> lazy7, Lazy<PremiumService> lazy8, Lazy<SharedPreferences> lazy9, Lazy<DiaryService> lazy10, Lazy<AppGalleryService> lazy11, Lazy<GoogleFitClient> lazy12, Lazy<NutrientDashboardAnalyticsHelper> lazy13) {
        return (TextNutrientDashboard) Preconditions.checkNotNull(applicationModule.providesTextNutrientDashboard(context, lazy, lazy2, lazy3, lazy4, lazy5, lazy6, lazy7, lazy8, lazy9, lazy10, lazy11, lazy12, lazy13), "Cannot return null from a non-@Nullable @Provides method");
    }
}
