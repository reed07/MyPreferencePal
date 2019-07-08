package com.myfitnesspal.shared.ui.activity;

import com.myfitnesspal.feature.addentry.service.WaterLoggingAnalyticsHelper;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.exercise.service.ExerciseAnalyticsHelper;
import com.myfitnesspal.feature.home.util.HomeAnalyticsHelper;
import com.myfitnesspal.feature.search.ui.FoodSearchActivityFactory;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.userdata.UserWeightService;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class FloatingButtonMixin_MembersInjector implements MembersInjector<FloatingButtonMixin> {
    private final Provider<ConfigService> configServiceProvider;
    private final Provider<DiaryService> diaryServiceProvider;
    private final Provider<ExerciseAnalyticsHelper> exerciseAnalyticsHelperProvider;
    private final Provider<FoodSearchActivityFactory> foodSearchRouterProvider;
    private final Provider<HomeAnalyticsHelper> homeAnalyticsHelperProvider;
    private final Provider<UserWeightService> userWeightServiceProvider;
    private final Provider<WaterLoggingAnalyticsHelper> waterLoggingAnalyticsHelperProvider;

    public FloatingButtonMixin_MembersInjector(Provider<ConfigService> provider, Provider<WaterLoggingAnalyticsHelper> provider2, Provider<HomeAnalyticsHelper> provider3, Provider<UserWeightService> provider4, Provider<DiaryService> provider5, Provider<ExerciseAnalyticsHelper> provider6, Provider<FoodSearchActivityFactory> provider7) {
        this.configServiceProvider = provider;
        this.waterLoggingAnalyticsHelperProvider = provider2;
        this.homeAnalyticsHelperProvider = provider3;
        this.userWeightServiceProvider = provider4;
        this.diaryServiceProvider = provider5;
        this.exerciseAnalyticsHelperProvider = provider6;
        this.foodSearchRouterProvider = provider7;
    }

    public static MembersInjector<FloatingButtonMixin> create(Provider<ConfigService> provider, Provider<WaterLoggingAnalyticsHelper> provider2, Provider<HomeAnalyticsHelper> provider3, Provider<UserWeightService> provider4, Provider<DiaryService> provider5, Provider<ExerciseAnalyticsHelper> provider6, Provider<FoodSearchActivityFactory> provider7) {
        FloatingButtonMixin_MembersInjector floatingButtonMixin_MembersInjector = new FloatingButtonMixin_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7);
        return floatingButtonMixin_MembersInjector;
    }

    public void injectMembers(FloatingButtonMixin floatingButtonMixin) {
        injectConfigService(floatingButtonMixin, DoubleCheck.lazy(this.configServiceProvider));
        injectWaterLoggingAnalyticsHelper(floatingButtonMixin, DoubleCheck.lazy(this.waterLoggingAnalyticsHelperProvider));
        injectHomeAnalyticsHelper(floatingButtonMixin, DoubleCheck.lazy(this.homeAnalyticsHelperProvider));
        injectUserWeightService(floatingButtonMixin, DoubleCheck.lazy(this.userWeightServiceProvider));
        injectDiaryService(floatingButtonMixin, DoubleCheck.lazy(this.diaryServiceProvider));
        injectExerciseAnalyticsHelper(floatingButtonMixin, DoubleCheck.lazy(this.exerciseAnalyticsHelperProvider));
        injectFoodSearchRouter(floatingButtonMixin, DoubleCheck.lazy(this.foodSearchRouterProvider));
    }

    public static void injectConfigService(FloatingButtonMixin floatingButtonMixin, Lazy<ConfigService> lazy) {
        floatingButtonMixin.configService = lazy;
    }

    public static void injectWaterLoggingAnalyticsHelper(FloatingButtonMixin floatingButtonMixin, Lazy<WaterLoggingAnalyticsHelper> lazy) {
        floatingButtonMixin.waterLoggingAnalyticsHelper = lazy;
    }

    public static void injectHomeAnalyticsHelper(FloatingButtonMixin floatingButtonMixin, Lazy<HomeAnalyticsHelper> lazy) {
        floatingButtonMixin.homeAnalyticsHelper = lazy;
    }

    public static void injectUserWeightService(FloatingButtonMixin floatingButtonMixin, Lazy<UserWeightService> lazy) {
        floatingButtonMixin.userWeightService = lazy;
    }

    public static void injectDiaryService(FloatingButtonMixin floatingButtonMixin, Lazy<DiaryService> lazy) {
        floatingButtonMixin.diaryService = lazy;
    }

    public static void injectExerciseAnalyticsHelper(FloatingButtonMixin floatingButtonMixin, Lazy<ExerciseAnalyticsHelper> lazy) {
        floatingButtonMixin.exerciseAnalyticsHelper = lazy;
    }

    public static void injectFoodSearchRouter(FloatingButtonMixin floatingButtonMixin, Lazy<FoodSearchActivityFactory> lazy) {
        floatingButtonMixin.foodSearchRouter = lazy;
    }
}
