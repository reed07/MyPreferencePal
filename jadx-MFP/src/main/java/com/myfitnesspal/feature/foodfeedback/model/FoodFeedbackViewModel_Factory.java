package com.myfitnesspal.feature.foodfeedback.model;

import com.myfitnesspal.feature.foodfeedback.repository.FoodFeedbackAction;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class FoodFeedbackViewModel_Factory implements Factory<FoodFeedbackViewModel> {
    private final Provider<FoodFeedbackAction> foodFeedbackRepositoryProvider;

    public FoodFeedbackViewModel_Factory(Provider<FoodFeedbackAction> provider) {
        this.foodFeedbackRepositoryProvider = provider;
    }

    public FoodFeedbackViewModel get() {
        return provideInstance(this.foodFeedbackRepositoryProvider);
    }

    public static FoodFeedbackViewModel provideInstance(Provider<FoodFeedbackAction> provider) {
        return new FoodFeedbackViewModel((FoodFeedbackAction) provider.get());
    }

    public static FoodFeedbackViewModel_Factory create(Provider<FoodFeedbackAction> provider) {
        return new FoodFeedbackViewModel_Factory(provider);
    }

    public static FoodFeedbackViewModel newFoodFeedbackViewModel(FoodFeedbackAction foodFeedbackAction) {
        return new FoodFeedbackViewModel(foodFeedbackAction);
    }
}
