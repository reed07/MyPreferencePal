package com.myfitnesspal.feature.foodfeedback.mixin;

import com.myfitnesspal.feature.foodfeedback.service.FoodFeedbackAnalyticsHelper;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class FoodFeedbackOptionsMixin_MembersInjector implements MembersInjector<FoodFeedbackOptionsMixin> {
    private final Provider<FoodFeedbackAnalyticsHelper> foodFeedbackAnalyticsHelperProvider;

    public FoodFeedbackOptionsMixin_MembersInjector(Provider<FoodFeedbackAnalyticsHelper> provider) {
        this.foodFeedbackAnalyticsHelperProvider = provider;
    }

    public static MembersInjector<FoodFeedbackOptionsMixin> create(Provider<FoodFeedbackAnalyticsHelper> provider) {
        return new FoodFeedbackOptionsMixin_MembersInjector(provider);
    }

    public void injectMembers(FoodFeedbackOptionsMixin foodFeedbackOptionsMixin) {
        injectFoodFeedbackAnalyticsHelper(foodFeedbackOptionsMixin, DoubleCheck.lazy(this.foodFeedbackAnalyticsHelperProvider));
    }

    public static void injectFoodFeedbackAnalyticsHelper(FoodFeedbackOptionsMixin foodFeedbackOptionsMixin, Lazy<FoodFeedbackAnalyticsHelper> lazy) {
        foodFeedbackOptionsMixin.foodFeedbackAnalyticsHelper = lazy;
    }
}
