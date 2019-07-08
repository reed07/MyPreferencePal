package com.myfitnesspal.feature.foodfeedback.repository;

import com.myfitnesspal.feature.foodfeedback.model.FoodExistsCheckItem;
import com.myfitnesspal.feature.foodfeedback.model.FoodFeedbackItem;
import com.myfitnesspal.shared.model.v1.Food;
import io.reactivex.Completable;
import io.reactivex.Single;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00032\u0006\u0010\t\u001a\u00020\bH&J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH&¨\u0006\u000e"}, d2 = {"Lcom/myfitnesspal/feature/foodfeedback/repository/FoodFeedbackAction;", "", "checkIfFoodWithDescriptionExists", "Lio/reactivex/Single;", "", "foodExistsCheckItem", "Lcom/myfitnesspal/feature/foodfeedback/model/FoodExistsCheckItem;", "createCustomFood", "Lcom/myfitnesspal/shared/model/v1/Food;", "food", "postFeedback", "Lio/reactivex/Completable;", "foodFeedbackItem", "Lcom/myfitnesspal/feature/foodfeedback/model/FoodFeedbackItem;", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: FoodFeedbackAction.kt */
public interface FoodFeedbackAction {
    @NotNull
    Single<Boolean> checkIfFoodWithDescriptionExists(@NotNull FoodExistsCheckItem foodExistsCheckItem);

    @NotNull
    Single<Food> createCustomFood(@NotNull Food food);

    @NotNull
    Completable postFeedback(@NotNull FoodFeedbackItem foodFeedbackItem);
}
