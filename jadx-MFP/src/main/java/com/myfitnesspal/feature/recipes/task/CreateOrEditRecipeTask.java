package com.myfitnesspal.feature.recipes.task;

import android.content.Context;
import com.myfitnesspal.feature.recipes.service.RecipeService;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.model.v2.MfpRecipe;
import com.myfitnesspal.shared.service.syncv2.SyncService;
import com.myfitnesspal.shared.service.syncv2.SyncType;
import dagger.Lazy;

public class CreateOrEditRecipeTask extends EventedTaskBase<MfpRecipe, ApiException> {
    public static final String NAME = "CreateOrEditRecipeTask";
    private final boolean isCreatingRecipe;
    private final MfpRecipe recipe;
    private final Lazy<RecipeService> recipeService;
    private final Lazy<SyncService> syncService;

    public static class CompletedEvent extends TaskEventBase<MfpRecipe, ApiException> {
    }

    /* access modifiers changed from: protected */
    public String getTaskName() {
        return NAME;
    }

    public static CreateOrEditRecipeTask newTaskForCreatingRecipe(Lazy<RecipeService> lazy, Lazy<SyncService> lazy2, MfpRecipe mfpRecipe) {
        return new CreateOrEditRecipeTask(lazy, lazy2, mfpRecipe, true);
    }

    public static CreateOrEditRecipeTask newTaskForEditingRecipe(Lazy<RecipeService> lazy, Lazy<SyncService> lazy2, MfpRecipe mfpRecipe) {
        return new CreateOrEditRecipeTask(lazy, lazy2, mfpRecipe, false);
    }

    private CreateOrEditRecipeTask(Lazy<RecipeService> lazy, Lazy<SyncService> lazy2, MfpRecipe mfpRecipe, boolean z) {
        super(CompletedEvent.class);
        this.recipeService = lazy;
        this.syncService = lazy2;
        this.recipe = mfpRecipe;
        this.isCreatingRecipe = z;
    }

    /* access modifiers changed from: protected */
    public MfpRecipe exec(Context context) throws ApiException {
        MfpRecipe mfpRecipe;
        if (this.isCreatingRecipe) {
            mfpRecipe = ((RecipeService) this.recipeService.get()).createRecipe(this.recipe);
        } else {
            mfpRecipe = ((RecipeService) this.recipeService.get()).editRecipe(this.recipe);
        }
        ((SyncService) this.syncService.get()).enqueueAndWait(SyncType.Incremental);
        return mfpRecipe;
    }
}
