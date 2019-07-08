package com.myfitnesspal.feature.meals.ui.mixin;

import com.myfitnesspal.feature.diary.service.MealAnalyticsHelper;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.feature.meals.service.MealService;
import com.myfitnesspal.feature.meals.util.MealSharingDirectionsAnalyticsHelper;
import com.myfitnesspal.feature.meals.util.MealUtil;
import com.myfitnesspal.feature.search.ui.FoodSearchActivityFactory;
import com.myfitnesspal.shared.db.table.FoodNotesTable;
import com.myfitnesspal.shared.model.mapper.impl.MealIngredientMapper;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.foods.FoodPermissionsService;
import com.myfitnesspal.shared.service.foods.FoodService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.util.MultiAddFoodHelper;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class MealEditorMixin_MembersInjector implements MembersInjector<MealEditorMixin> {
    private final Provider<ConfigService> configServiceProvider;
    private final Provider<FoodNotesTable> foodNotesTableProvider;
    private final Provider<FoodPermissionsService> foodPermissionsServiceProvider;
    private final Provider<FoodSearchActivityFactory> foodSearchRouterProvider;
    private final Provider<FoodService> foodServiceProvider;
    private final Provider<ImageService> imageServiceProvider;
    private final Provider<LocalSettingsService> localSettingsServiceProvider;
    private final Provider<MealAnalyticsHelper> mealAnalyticsHelperProvider;
    private final Provider<MealSharingDirectionsAnalyticsHelper> mealBrowseAnalyticsProvider;
    private final Provider<MealUtil> mealHelperUtilProvider;
    private final Provider<MealIngredientMapper> mealIngredientMapperProvider;
    private final Provider<MealService> mealServiceProvider;
    private final Provider<MultiAddFoodHelper> multiAddFoodHelperProvider;

    public MealEditorMixin_MembersInjector(Provider<MealService> provider, Provider<FoodService> provider2, Provider<MealUtil> provider3, Provider<MultiAddFoodHelper> provider4, Provider<MealIngredientMapper> provider5, Provider<ImageService> provider6, Provider<MealAnalyticsHelper> provider7, Provider<MealSharingDirectionsAnalyticsHelper> provider8, Provider<ConfigService> provider9, Provider<LocalSettingsService> provider10, Provider<FoodPermissionsService> provider11, Provider<FoodNotesTable> provider12, Provider<FoodSearchActivityFactory> provider13) {
        this.mealServiceProvider = provider;
        this.foodServiceProvider = provider2;
        this.mealHelperUtilProvider = provider3;
        this.multiAddFoodHelperProvider = provider4;
        this.mealIngredientMapperProvider = provider5;
        this.imageServiceProvider = provider6;
        this.mealAnalyticsHelperProvider = provider7;
        this.mealBrowseAnalyticsProvider = provider8;
        this.configServiceProvider = provider9;
        this.localSettingsServiceProvider = provider10;
        this.foodPermissionsServiceProvider = provider11;
        this.foodNotesTableProvider = provider12;
        this.foodSearchRouterProvider = provider13;
    }

    public static MembersInjector<MealEditorMixin> create(Provider<MealService> provider, Provider<FoodService> provider2, Provider<MealUtil> provider3, Provider<MultiAddFoodHelper> provider4, Provider<MealIngredientMapper> provider5, Provider<ImageService> provider6, Provider<MealAnalyticsHelper> provider7, Provider<MealSharingDirectionsAnalyticsHelper> provider8, Provider<ConfigService> provider9, Provider<LocalSettingsService> provider10, Provider<FoodPermissionsService> provider11, Provider<FoodNotesTable> provider12, Provider<FoodSearchActivityFactory> provider13) {
        MealEditorMixin_MembersInjector mealEditorMixin_MembersInjector = new MealEditorMixin_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13);
        return mealEditorMixin_MembersInjector;
    }

    public void injectMembers(MealEditorMixin mealEditorMixin) {
        injectMealService(mealEditorMixin, DoubleCheck.lazy(this.mealServiceProvider));
        injectFoodService(mealEditorMixin, DoubleCheck.lazy(this.foodServiceProvider));
        injectMealHelperUtil(mealEditorMixin, DoubleCheck.lazy(this.mealHelperUtilProvider));
        injectMultiAddFoodHelper(mealEditorMixin, DoubleCheck.lazy(this.multiAddFoodHelperProvider));
        injectMealIngredientMapper(mealEditorMixin, DoubleCheck.lazy(this.mealIngredientMapperProvider));
        injectImageService(mealEditorMixin, DoubleCheck.lazy(this.imageServiceProvider));
        injectMealAnalyticsHelper(mealEditorMixin, DoubleCheck.lazy(this.mealAnalyticsHelperProvider));
        injectMealBrowseAnalytics(mealEditorMixin, DoubleCheck.lazy(this.mealBrowseAnalyticsProvider));
        injectConfigService(mealEditorMixin, DoubleCheck.lazy(this.configServiceProvider));
        injectLocalSettingsService(mealEditorMixin, DoubleCheck.lazy(this.localSettingsServiceProvider));
        injectFoodPermissionsService(mealEditorMixin, DoubleCheck.lazy(this.foodPermissionsServiceProvider));
        injectFoodNotesTable(mealEditorMixin, DoubleCheck.lazy(this.foodNotesTableProvider));
        injectFoodSearchRouter(mealEditorMixin, DoubleCheck.lazy(this.foodSearchRouterProvider));
    }

    public static void injectMealService(MealEditorMixin mealEditorMixin, Lazy<MealService> lazy) {
        mealEditorMixin.mealService = lazy;
    }

    public static void injectFoodService(MealEditorMixin mealEditorMixin, Lazy<FoodService> lazy) {
        mealEditorMixin.foodService = lazy;
    }

    public static void injectMealHelperUtil(MealEditorMixin mealEditorMixin, Lazy<MealUtil> lazy) {
        mealEditorMixin.mealHelperUtil = lazy;
    }

    public static void injectMultiAddFoodHelper(MealEditorMixin mealEditorMixin, Lazy<MultiAddFoodHelper> lazy) {
        mealEditorMixin.multiAddFoodHelper = lazy;
    }

    public static void injectMealIngredientMapper(MealEditorMixin mealEditorMixin, Lazy<MealIngredientMapper> lazy) {
        mealEditorMixin.mealIngredientMapper = lazy;
    }

    public static void injectImageService(MealEditorMixin mealEditorMixin, Lazy<ImageService> lazy) {
        mealEditorMixin.imageService = lazy;
    }

    public static void injectMealAnalyticsHelper(MealEditorMixin mealEditorMixin, Lazy<MealAnalyticsHelper> lazy) {
        mealEditorMixin.mealAnalyticsHelper = lazy;
    }

    public static void injectMealBrowseAnalytics(MealEditorMixin mealEditorMixin, Lazy<MealSharingDirectionsAnalyticsHelper> lazy) {
        mealEditorMixin.mealBrowseAnalytics = lazy;
    }

    public static void injectConfigService(MealEditorMixin mealEditorMixin, Lazy<ConfigService> lazy) {
        mealEditorMixin.configService = lazy;
    }

    public static void injectLocalSettingsService(MealEditorMixin mealEditorMixin, Lazy<LocalSettingsService> lazy) {
        mealEditorMixin.localSettingsService = lazy;
    }

    public static void injectFoodPermissionsService(MealEditorMixin mealEditorMixin, Lazy<FoodPermissionsService> lazy) {
        mealEditorMixin.foodPermissionsService = lazy;
    }

    public static void injectFoodNotesTable(MealEditorMixin mealEditorMixin, Lazy<FoodNotesTable> lazy) {
        mealEditorMixin.foodNotesTable = lazy;
    }

    public static void injectFoodSearchRouter(MealEditorMixin mealEditorMixin, Lazy<FoodSearchActivityFactory> lazy) {
        mealEditorMixin.foodSearchRouter = lazy;
    }
}
