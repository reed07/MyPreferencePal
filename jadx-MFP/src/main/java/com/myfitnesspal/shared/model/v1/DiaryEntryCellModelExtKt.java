package com.myfitnesspal.shared.model.v1;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"getFood", "Lcom/myfitnesspal/shared/model/v1/Food;", "Lcom/myfitnesspal/shared/model/v1/DiaryEntryCellModel;", "app_googleRelease"}, k = 2, mv = {1, 1, 13})
/* compiled from: DiaryEntryCellModelExt.kt */
public final class DiaryEntryCellModelExtKt {
    @NotNull
    public static final Food getFood(@NotNull DiaryEntryCellModel diaryEntryCellModel) {
        Intrinsics.checkParameterIsNotNull(diaryEntryCellModel, "receiver$0");
        if (diaryEntryCellModel.isFood()) {
            return (Food) diaryEntryCellModel;
        }
        Food food = ((FoodEntry) diaryEntryCellModel).getFood();
        Intrinsics.checkExpressionValueIsNotNull(food, "(this as FoodEntry).food");
        return food;
    }
}
