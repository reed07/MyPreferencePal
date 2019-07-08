package com.myfitnesspal.feature.meals.ui.mixin;

import com.myfitnesspal.feature.diary.service.MealAnalyticsHelper;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.feature.meals.service.MealService;
import com.myfitnesspal.shared.api.ApiUrlProvider;
import com.myfitnesspal.shared.service.foods.FoodPermissionsService;
import com.myfitnesspal.shared.service.foods.FoodService;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class SharedMealViewerMixin_MembersInjector implements MembersInjector<SharedMealViewerMixin> {
    private final Provider<FoodPermissionsService> foodPermissionsServiceProvider;
    private final Provider<FoodService> foodServiceProvider;
    private final Provider<ImageService> imageServiceProvider;
    private final Provider<MealAnalyticsHelper> mealAnalyticsHelperProvider;
    private final Provider<MealService> mealServiceProvider;
    private final Provider<ApiUrlProvider> urlProvider;

    public SharedMealViewerMixin_MembersInjector(Provider<FoodService> provider, Provider<ImageService> provider2, Provider<FoodPermissionsService> provider3, Provider<MealService> provider4, Provider<ApiUrlProvider> provider5, Provider<MealAnalyticsHelper> provider6) {
        this.foodServiceProvider = provider;
        this.imageServiceProvider = provider2;
        this.foodPermissionsServiceProvider = provider3;
        this.mealServiceProvider = provider4;
        this.urlProvider = provider5;
        this.mealAnalyticsHelperProvider = provider6;
    }

    public static MembersInjector<SharedMealViewerMixin> create(Provider<FoodService> provider, Provider<ImageService> provider2, Provider<FoodPermissionsService> provider3, Provider<MealService> provider4, Provider<ApiUrlProvider> provider5, Provider<MealAnalyticsHelper> provider6) {
        SharedMealViewerMixin_MembersInjector sharedMealViewerMixin_MembersInjector = new SharedMealViewerMixin_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6);
        return sharedMealViewerMixin_MembersInjector;
    }

    public void injectMembers(SharedMealViewerMixin sharedMealViewerMixin) {
        injectFoodService(sharedMealViewerMixin, DoubleCheck.lazy(this.foodServiceProvider));
        injectImageService(sharedMealViewerMixin, DoubleCheck.lazy(this.imageServiceProvider));
        injectFoodPermissionsService(sharedMealViewerMixin, DoubleCheck.lazy(this.foodPermissionsServiceProvider));
        injectMealService(sharedMealViewerMixin, DoubleCheck.lazy(this.mealServiceProvider));
        injectUrlProvider(sharedMealViewerMixin, DoubleCheck.lazy(this.urlProvider));
        injectMealAnalyticsHelper(sharedMealViewerMixin, DoubleCheck.lazy(this.mealAnalyticsHelperProvider));
    }

    public static void injectFoodService(SharedMealViewerMixin sharedMealViewerMixin, Lazy<FoodService> lazy) {
        sharedMealViewerMixin.foodService = lazy;
    }

    public static void injectImageService(SharedMealViewerMixin sharedMealViewerMixin, Lazy<ImageService> lazy) {
        sharedMealViewerMixin.imageService = lazy;
    }

    public static void injectFoodPermissionsService(SharedMealViewerMixin sharedMealViewerMixin, Lazy<FoodPermissionsService> lazy) {
        sharedMealViewerMixin.foodPermissionsService = lazy;
    }

    public static void injectMealService(SharedMealViewerMixin sharedMealViewerMixin, Lazy<MealService> lazy) {
        sharedMealViewerMixin.mealService = lazy;
    }

    public static void injectUrlProvider(SharedMealViewerMixin sharedMealViewerMixin, Lazy<ApiUrlProvider> lazy) {
        sharedMealViewerMixin.urlProvider = lazy;
    }

    public static void injectMealAnalyticsHelper(SharedMealViewerMixin sharedMealViewerMixin, Lazy<MealAnalyticsHelper> lazy) {
        sharedMealViewerMixin.mealAnalyticsHelper = lazy;
    }
}
