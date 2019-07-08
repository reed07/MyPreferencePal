package com.myfitnesspal.feature.foodeditor.ui.mixin.impl;

import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.feature.meals.util.MealUtil;
import com.myfitnesspal.shared.model.mapper.impl.FoodMapper;
import com.myfitnesspal.shared.model.mapper.impl.MfpFoodMapper;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.foods.FoodService;
import com.myfitnesspal.shared.util.MultiAddFoodHelper;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class MultiAddFoodEditorMixin_MembersInjector implements MembersInjector<MultiAddFoodEditorMixin> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<ConfigService> configServiceProvider;
    private final Provider<FoodMapper> foodMapperV1Provider;
    private final Provider<MfpFoodMapper> foodMapperV2Provider;
    private final Provider<FoodService> foodServiceProvider;
    private final Provider<ImageService> imageServiceProvider;
    private final Provider<MealUtil> mealUtilProvider;
    private final Provider<MultiAddFoodHelper> multiAddFoodHelperProvider;

    public MultiAddFoodEditorMixin_MembersInjector(Provider<FoodMapper> provider, Provider<MfpFoodMapper> provider2, Provider<ConfigService> provider3, Provider<ImageService> provider4, Provider<MealUtil> provider5, Provider<MultiAddFoodHelper> provider6, Provider<AnalyticsService> provider7, Provider<FoodService> provider8) {
        this.foodMapperV1Provider = provider;
        this.foodMapperV2Provider = provider2;
        this.configServiceProvider = provider3;
        this.imageServiceProvider = provider4;
        this.mealUtilProvider = provider5;
        this.multiAddFoodHelperProvider = provider6;
        this.analyticsServiceProvider = provider7;
        this.foodServiceProvider = provider8;
    }

    public static MembersInjector<MultiAddFoodEditorMixin> create(Provider<FoodMapper> provider, Provider<MfpFoodMapper> provider2, Provider<ConfigService> provider3, Provider<ImageService> provider4, Provider<MealUtil> provider5, Provider<MultiAddFoodHelper> provider6, Provider<AnalyticsService> provider7, Provider<FoodService> provider8) {
        MultiAddFoodEditorMixin_MembersInjector multiAddFoodEditorMixin_MembersInjector = new MultiAddFoodEditorMixin_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8);
        return multiAddFoodEditorMixin_MembersInjector;
    }

    public void injectMembers(MultiAddFoodEditorMixin multiAddFoodEditorMixin) {
        FoodEditorMixin_MembersInjector.injectFoodMapperV1(multiAddFoodEditorMixin, DoubleCheck.lazy(this.foodMapperV1Provider));
        FoodEditorMixin_MembersInjector.injectFoodMapperV2(multiAddFoodEditorMixin, DoubleCheck.lazy(this.foodMapperV2Provider));
        FoodEditorMixin_MembersInjector.injectConfigService(multiAddFoodEditorMixin, DoubleCheck.lazy(this.configServiceProvider));
        FoodEditorMixin_MembersInjector.injectImageService(multiAddFoodEditorMixin, DoubleCheck.lazy(this.imageServiceProvider));
        FoodEditorMixin_MembersInjector.injectMealUtil(multiAddFoodEditorMixin, DoubleCheck.lazy(this.mealUtilProvider));
        FoodEditorMixin_MembersInjector.injectMultiAddFoodHelper(multiAddFoodEditorMixin, DoubleCheck.lazy(this.multiAddFoodHelperProvider));
        FoodEditorMixin_MembersInjector.injectAnalyticsService(multiAddFoodEditorMixin, DoubleCheck.lazy(this.analyticsServiceProvider));
        FoodEditorMixin_MembersInjector.injectFoodService(multiAddFoodEditorMixin, DoubleCheck.lazy(this.foodServiceProvider));
    }
}
