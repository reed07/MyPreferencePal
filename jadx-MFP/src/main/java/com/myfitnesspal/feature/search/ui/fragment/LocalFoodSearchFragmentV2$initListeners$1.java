package com.myfitnesspal.feature.search.ui.fragment;

import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.model.v1.DiaryEntryCellModel;
import com.myfitnesspal.shared.model.v1.DiaryEntryCellModelExtKt;
import com.myfitnesspal.shared.model.v1.Food;
import com.myfitnesspal.shared.model.v1.FoodEntry;
import com.myfitnesspal.shared.model.v1.MealFood;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "item", "Lcom/myfitnesspal/shared/model/v1/DiaryEntryCellModel;", "position", "", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: LocalFoodSearchFragmentV2.kt */
final class LocalFoodSearchFragmentV2$initListeners$1 extends Lambda implements Function2<DiaryEntryCellModel, Integer, Unit> {
    final /* synthetic */ LocalFoodSearchFragmentV2 this$0;

    LocalFoodSearchFragmentV2$initListeners$1(LocalFoodSearchFragmentV2 localFoodSearchFragmentV2) {
        this.this$0 = localFoodSearchFragmentV2;
        super(2);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((DiaryEntryCellModel) obj, ((Number) obj2).intValue());
        return Unit.INSTANCE;
    }

    public final void invoke(@NotNull DiaryEntryCellModel diaryEntryCellModel, int i) {
        Intrinsics.checkParameterIsNotNull(diaryEntryCellModel, Attributes.ITEM);
        Food food = DiaryEntryCellModelExtKt.getFood(diaryEntryCellModel);
        switch (diaryEntryCellModel.itemType()) {
            case 3:
                this.this$0.switchToAddMealViewForFood((MealFood) diaryEntryCellModel, i);
                return;
            case 4:
                FoodEntry foodEntry = (FoodEntry) diaryEntryCellModel;
                Food food2 = foodEntry.getFood();
                Intrinsics.checkExpressionValueIsNotNull(food2, "foodEntry.food");
                if (!food2.isMeal() || !(diaryEntryCellModel instanceof MealFood)) {
                    this.this$0.switchToFoodSummaryViewForFood(food, foodEntry.getFoodPortion(), foodEntry.getQuantity(), i);
                    return;
                } else {
                    this.this$0.switchToAddMealViewForFood((MealFood) diaryEntryCellModel, i);
                    return;
                }
            default:
                this.this$0.switchToFoodSummaryViewForFood(food, null, 1.0f, i);
                return;
        }
    }
}
