package com.myfitnesspal.feature.nutrition.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.feature.nutrition.service.ChartRendererFactory;
import com.myfitnesspal.feature.nutrition.service.NutritionGraphAnalyticsHelper;
import com.myfitnesspal.feature.nutrition.service.NutritionGraphPreferenceService;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class GraphViewFragment_MembersInjector implements MembersInjector<GraphViewFragment> {
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<ChartRendererFactory> chartRendererFactoryLazyProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<LocalSettingsService> localSettingsServiceProvider;
    private final Provider<NutritionGraphAnalyticsHelper> nutritionGraphAnalyticsHelperProvider;
    private final Provider<NutritionGraphPreferenceService> nutritionGraphServiceLazyProvider;
    private final Provider<PremiumService> premiumServiceLazyProvider;
    private final Provider<UserEnergyService> userEnergyServiceProvider;

    public GraphViewFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<LocalSettingsService> provider3, Provider<ChartRendererFactory> provider4, Provider<NutritionGraphPreferenceService> provider5, Provider<NutritionGraphAnalyticsHelper> provider6, Provider<UserEnergyService> provider7, Provider<PremiumService> provider8) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.localSettingsServiceProvider = provider3;
        this.chartRendererFactoryLazyProvider = provider4;
        this.nutritionGraphServiceLazyProvider = provider5;
        this.nutritionGraphAnalyticsHelperProvider = provider6;
        this.userEnergyServiceProvider = provider7;
        this.premiumServiceLazyProvider = provider8;
    }

    public static MembersInjector<GraphViewFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<LocalSettingsService> provider3, Provider<ChartRendererFactory> provider4, Provider<NutritionGraphPreferenceService> provider5, Provider<NutritionGraphAnalyticsHelper> provider6, Provider<UserEnergyService> provider7, Provider<PremiumService> provider8) {
        GraphViewFragment_MembersInjector graphViewFragment_MembersInjector = new GraphViewFragment_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8);
        return graphViewFragment_MembersInjector;
    }

    public void injectMembers(GraphViewFragment graphViewFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(graphViewFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(graphViewFragment, (Glide) this.glideProvider.get());
        injectLocalSettingsService(graphViewFragment, DoubleCheck.lazy(this.localSettingsServiceProvider));
        injectChartRendererFactoryLazy(graphViewFragment, DoubleCheck.lazy(this.chartRendererFactoryLazyProvider));
        injectNutritionGraphServiceLazy(graphViewFragment, DoubleCheck.lazy(this.nutritionGraphServiceLazyProvider));
        injectNutritionGraphAnalyticsHelper(graphViewFragment, DoubleCheck.lazy(this.nutritionGraphAnalyticsHelperProvider));
        injectUserEnergyService(graphViewFragment, DoubleCheck.lazy(this.userEnergyServiceProvider));
        injectPremiumServiceLazy(graphViewFragment, DoubleCheck.lazy(this.premiumServiceLazyProvider));
    }

    public static void injectLocalSettingsService(GraphViewFragment graphViewFragment, Lazy<LocalSettingsService> lazy) {
        graphViewFragment.localSettingsService = lazy;
    }

    public static void injectChartRendererFactoryLazy(GraphViewFragment graphViewFragment, Lazy<ChartRendererFactory> lazy) {
        graphViewFragment.chartRendererFactoryLazy = lazy;
    }

    public static void injectNutritionGraphServiceLazy(GraphViewFragment graphViewFragment, Lazy<NutritionGraphPreferenceService> lazy) {
        graphViewFragment.nutritionGraphServiceLazy = lazy;
    }

    public static void injectNutritionGraphAnalyticsHelper(GraphViewFragment graphViewFragment, Lazy<NutritionGraphAnalyticsHelper> lazy) {
        graphViewFragment.nutritionGraphAnalyticsHelper = lazy;
    }

    public static void injectUserEnergyService(GraphViewFragment graphViewFragment, Lazy<UserEnergyService> lazy) {
        graphViewFragment.userEnergyService = lazy;
    }

    public static void injectPremiumServiceLazy(GraphViewFragment graphViewFragment, Lazy<PremiumService> lazy) {
        graphViewFragment.premiumServiceLazy = lazy;
    }
}
