package com.myfitnesspal.shared.ui.activity.impl.resourceloader.mixin;

import com.myfitnesspal.feature.diary.service.MealAnalyticsHelper;
import com.myfitnesspal.shared.service.foods.FoodService;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class MealFoodLoaderMixin_MembersInjector implements MembersInjector<MealFoodLoaderMixin> {
    private final Provider<FoodService> foodServiceProvider;
    private final Provider<MealAnalyticsHelper> mealAnalyticsHelperProvider;

    public MealFoodLoaderMixin_MembersInjector(Provider<FoodService> provider, Provider<MealAnalyticsHelper> provider2) {
        this.foodServiceProvider = provider;
        this.mealAnalyticsHelperProvider = provider2;
    }

    public static MembersInjector<MealFoodLoaderMixin> create(Provider<FoodService> provider, Provider<MealAnalyticsHelper> provider2) {
        return new MealFoodLoaderMixin_MembersInjector(provider, provider2);
    }

    public void injectMembers(MealFoodLoaderMixin mealFoodLoaderMixin) {
        injectFoodService(mealFoodLoaderMixin, DoubleCheck.lazy(this.foodServiceProvider));
        injectMealAnalyticsHelper(mealFoodLoaderMixin, DoubleCheck.lazy(this.mealAnalyticsHelperProvider));
    }

    public static void injectFoodService(MealFoodLoaderMixin mealFoodLoaderMixin, Lazy<FoodService> lazy) {
        mealFoodLoaderMixin.foodService = lazy;
    }

    public static void injectMealAnalyticsHelper(MealFoodLoaderMixin mealFoodLoaderMixin, Lazy<MealAnalyticsHelper> lazy) {
        mealFoodLoaderMixin.mealAnalyticsHelper = lazy;
    }
}
