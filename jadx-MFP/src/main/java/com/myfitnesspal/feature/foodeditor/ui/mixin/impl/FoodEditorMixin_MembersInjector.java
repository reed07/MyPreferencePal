package com.myfitnesspal.feature.foodeditor.ui.mixin.impl;

import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.feature.meals.util.MealUtil;
import com.myfitnesspal.shared.model.mapper.impl.FoodMapper;
import com.myfitnesspal.shared.model.mapper.impl.MfpFoodMapper;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.foods.FoodService;
import com.myfitnesspal.shared.util.MultiAddFoodHelper;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class FoodEditorMixin_MembersInjector implements MembersInjector<FoodEditorMixin> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<ConfigService> configServiceProvider;
    private final Provider<FoodMapper> foodMapperV1Provider;
    private final Provider<MfpFoodMapper> foodMapperV2Provider;
    private final Provider<FoodService> foodServiceProvider;
    private final Provider<ImageService> imageServiceProvider;
    private final Provider<MealUtil> mealUtilProvider;
    private final Provider<MultiAddFoodHelper> multiAddFoodHelperProvider;

    public FoodEditorMixin_MembersInjector(Provider<FoodMapper> provider, Provider<MfpFoodMapper> provider2, Provider<ConfigService> provider3, Provider<ImageService> provider4, Provider<MealUtil> provider5, Provider<MultiAddFoodHelper> provider6, Provider<AnalyticsService> provider7, Provider<FoodService> provider8) {
        this.foodMapperV1Provider = provider;
        this.foodMapperV2Provider = provider2;
        this.configServiceProvider = provider3;
        this.imageServiceProvider = provider4;
        this.mealUtilProvider = provider5;
        this.multiAddFoodHelperProvider = provider6;
        this.analyticsServiceProvider = provider7;
        this.foodServiceProvider = provider8;
    }

    public static MembersInjector<FoodEditorMixin> create(Provider<FoodMapper> provider, Provider<MfpFoodMapper> provider2, Provider<ConfigService> provider3, Provider<ImageService> provider4, Provider<MealUtil> provider5, Provider<MultiAddFoodHelper> provider6, Provider<AnalyticsService> provider7, Provider<FoodService> provider8) {
        FoodEditorMixin_MembersInjector foodEditorMixin_MembersInjector = new FoodEditorMixin_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8);
        return foodEditorMixin_MembersInjector;
    }

    public void injectMembers(FoodEditorMixin foodEditorMixin) {
        injectFoodMapperV1(foodEditorMixin, DoubleCheck.lazy(this.foodMapperV1Provider));
        injectFoodMapperV2(foodEditorMixin, DoubleCheck.lazy(this.foodMapperV2Provider));
        injectConfigService(foodEditorMixin, DoubleCheck.lazy(this.configServiceProvider));
        injectImageService(foodEditorMixin, DoubleCheck.lazy(this.imageServiceProvider));
        injectMealUtil(foodEditorMixin, DoubleCheck.lazy(this.mealUtilProvider));
        injectMultiAddFoodHelper(foodEditorMixin, DoubleCheck.lazy(this.multiAddFoodHelperProvider));
        injectAnalyticsService(foodEditorMixin, DoubleCheck.lazy(this.analyticsServiceProvider));
        injectFoodService(foodEditorMixin, DoubleCheck.lazy(this.foodServiceProvider));
    }

    public static void injectFoodMapperV1(FoodEditorMixin foodEditorMixin, Lazy<FoodMapper> lazy) {
        foodEditorMixin.foodMapperV1 = lazy;
    }

    public static void injectFoodMapperV2(FoodEditorMixin foodEditorMixin, Lazy<MfpFoodMapper> lazy) {
        foodEditorMixin.foodMapperV2 = lazy;
    }

    public static void injectConfigService(FoodEditorMixin foodEditorMixin, Lazy<ConfigService> lazy) {
        foodEditorMixin.configService = lazy;
    }

    public static void injectImageService(FoodEditorMixin foodEditorMixin, Lazy<ImageService> lazy) {
        foodEditorMixin.imageService = lazy;
    }

    public static void injectMealUtil(FoodEditorMixin foodEditorMixin, Lazy<MealUtil> lazy) {
        foodEditorMixin.mealUtil = lazy;
    }

    public static void injectMultiAddFoodHelper(FoodEditorMixin foodEditorMixin, Lazy<MultiAddFoodHelper> lazy) {
        foodEditorMixin.multiAddFoodHelper = lazy;
    }

    public static void injectAnalyticsService(FoodEditorMixin foodEditorMixin, Lazy<AnalyticsService> lazy) {
        foodEditorMixin.analyticsService = lazy;
    }

    public static void injectFoodService(FoodEditorMixin foodEditorMixin, Lazy<FoodService> lazy) {
        foodEditorMixin.foodService = lazy;
    }
}
