package com.myfitnesspal.feature.recipes.task;

import android.content.Context;
import com.myfitnesspal.feature.recipes.service.RecipeService;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.model.v1.RecipeBoxItem;
import com.myfitnesspal.shared.model.v2.MfpRecipe;
import dagger.Lazy;

public class FetchCompleteRecipeTask extends EventedTaskBase<MfpRecipe, ApiException> {
    private final RecipeBoxItem recipeBoxItem;
    private final Lazy<RecipeService> recipeService;

    public static class CompletedEvent extends TaskEventBase<MfpRecipe, ApiException> {
    }

    public FetchCompleteRecipeTask(Lazy<RecipeService> lazy, RecipeBoxItem recipeBoxItem2) {
        super((TaskEventBase) new CompletedEvent());
        this.recipeService = lazy;
        this.recipeBoxItem = recipeBoxItem2;
    }

    /* access modifiers changed from: protected */
    public MfpRecipe exec(Context context) throws ApiException {
        return ((RecipeService) this.recipeService.get()).getRecipeForRecipeBoxItem(this.recipeBoxItem);
    }
}
