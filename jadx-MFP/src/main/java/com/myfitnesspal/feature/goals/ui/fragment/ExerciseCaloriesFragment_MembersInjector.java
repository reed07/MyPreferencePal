package com.myfitnesspal.feature.goals.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.feature.goals.service.NutrientGoalsUtil;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class ExerciseCaloriesFragment_MembersInjector implements MembersInjector<ExerciseCaloriesFragment> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<LocalizedStringsUtil> localizedStringsUtilProvider;
    private final Provider<NutrientGoalsUtil> nutrientGoalsUtilProvider;
    private final Provider<UserEnergyService> userEnergyServiceProvider;

    public ExerciseCaloriesFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<NutrientGoalsUtil> provider3, Provider<LocalizedStringsUtil> provider4, Provider<UserEnergyService> provider5, Provider<AnalyticsService> provider6) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.nutrientGoalsUtilProvider = provider3;
        this.localizedStringsUtilProvider = provider4;
        this.userEnergyServiceProvider = provider5;
        this.analyticsServiceProvider = provider6;
    }

    public static MembersInjector<ExerciseCaloriesFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<NutrientGoalsUtil> provider3, Provider<LocalizedStringsUtil> provider4, Provider<UserEnergyService> provider5, Provider<AnalyticsService> provider6) {
        ExerciseCaloriesFragment_MembersInjector exerciseCaloriesFragment_MembersInjector = new ExerciseCaloriesFragment_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6);
        return exerciseCaloriesFragment_MembersInjector;
    }

    public void injectMembers(ExerciseCaloriesFragment exerciseCaloriesFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(exerciseCaloriesFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(exerciseCaloriesFragment, (Glide) this.glideProvider.get());
        injectNutrientGoalsUtil(exerciseCaloriesFragment, DoubleCheck.lazy(this.nutrientGoalsUtilProvider));
        injectLocalizedStringsUtil(exerciseCaloriesFragment, DoubleCheck.lazy(this.localizedStringsUtilProvider));
        injectUserEnergyService(exerciseCaloriesFragment, DoubleCheck.lazy(this.userEnergyServiceProvider));
        injectAnalyticsService(exerciseCaloriesFragment, DoubleCheck.lazy(this.analyticsServiceProvider));
    }

    public static void injectNutrientGoalsUtil(ExerciseCaloriesFragment exerciseCaloriesFragment, Lazy<NutrientGoalsUtil> lazy) {
        exerciseCaloriesFragment.nutrientGoalsUtil = lazy;
    }

    public static void injectLocalizedStringsUtil(ExerciseCaloriesFragment exerciseCaloriesFragment, Lazy<LocalizedStringsUtil> lazy) {
        exerciseCaloriesFragment.localizedStringsUtil = lazy;
    }

    public static void injectUserEnergyService(ExerciseCaloriesFragment exerciseCaloriesFragment, Lazy<UserEnergyService> lazy) {
        exerciseCaloriesFragment.userEnergyService = lazy;
    }

    public static void injectAnalyticsService(ExerciseCaloriesFragment exerciseCaloriesFragment, Lazy<AnalyticsService> lazy) {
        exerciseCaloriesFragment.analyticsService = lazy;
    }
}
