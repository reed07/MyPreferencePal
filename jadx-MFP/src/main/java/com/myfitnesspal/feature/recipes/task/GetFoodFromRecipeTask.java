package com.myfitnesspal.feature.recipes.task;

import android.content.Context;
import com.myfitnesspal.feature.recipes.service.RecipeService;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase.Unchecked;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.myfitnesspal.shared.model.v2.MfpRecipe;
import com.myfitnesspal.shared.util.FoodMapperUtil;
import dagger.Lazy;

public class GetFoodFromRecipeTask extends Unchecked<MfpFood> {
    private final MfpRecipe recipe;
    private final Lazy<RecipeService> recipeService;

    public static class CompletedEvent extends TaskEventBase.Unchecked<MfpFood> {
    }

    public GetFoodFromRecipeTask(Lazy<RecipeService> lazy, MfpRecipe mfpRecipe) {
        super(CompletedEvent.class);
        this.recipeService = lazy;
        this.recipe = mfpRecipe;
    }

    /* access modifiers changed from: protected */
    public MfpFood exec(Context context) throws RuntimeException {
        return FoodMapperUtil.mapV1FoodToMfpFood(((RecipeService) this.recipeService.get()).getRecipeFoodForV2RecipeId(this.recipe.getId()));
    }
}
