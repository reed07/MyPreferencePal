package com.myfitnesspal.feature.recipes.task;

import android.content.Context;
import com.myfitnesspal.feature.recipes.service.RecipeService;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase.Unchecked;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.db.adapter.RecipeBoxItemsDBAdapter;
import com.myfitnesspal.shared.model.v1.RecipeBoxItem;
import com.myfitnesspal.shared.model.v2.MfpRecipe;
import com.uacf.core.database.DatabaseUtil;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Function0;
import dagger.Lazy;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DeleteRecipesTask extends Unchecked<Boolean> {
    private final DbConnectionManager dbConnectionManager;
    /* access modifiers changed from: private */
    public final List<RecipeBoxItem> recipeBoxItems;
    private final Lazy<RecipeService> recipeService;
    private final List<MfpRecipe> recipes;

    public static class CompletedEvent extends TaskEventBase.Unchecked<Boolean> {
        private List<MfpRecipe> failedRecipes;
        private List<MfpRecipe> successfulRecipes;

        public List<MfpRecipe> getSuccessfulRecipes() {
            return this.successfulRecipes;
        }

        public void setSuccessfulRecipes(List<MfpRecipe> list) {
            this.successfulRecipes = list;
        }

        public List<MfpRecipe> getFailedRecipes() {
            return this.failedRecipes;
        }

        public void setFailedRecipes(List<MfpRecipe> list) {
            this.failedRecipes = list;
        }
    }

    public DeleteRecipesTask(DbConnectionManager dbConnectionManager2, List<RecipeBoxItem> list) {
        super((TaskEventBase) new CompletedEvent());
        this.dbConnectionManager = dbConnectionManager2;
        this.recipeBoxItems = list;
        this.recipeService = null;
        this.recipes = null;
    }

    public DeleteRecipesTask(Lazy<RecipeService> lazy, MfpRecipe mfpRecipe) {
        super((TaskEventBase) new CompletedEvent());
        this.recipeService = lazy;
        this.recipes = Collections.singletonList(mfpRecipe);
        this.dbConnectionManager = null;
        this.recipeBoxItems = null;
    }

    /* access modifiers changed from: protected */
    public Boolean exec(Context context) throws RuntimeException {
        if (CollectionUtils.notEmpty((Collection<?>) this.recipes)) {
            CompletedEvent completedEvent = (CompletedEvent) getEvent();
            List deleteRecipesAndReturnFailedRecipes = ((RecipeService) this.recipeService.get()).deleteRecipesAndReturnFailedRecipes(this.recipes);
            this.recipes.removeAll(deleteRecipesAndReturnFailedRecipes);
            completedEvent.setFailedRecipes(deleteRecipesAndReturnFailedRecipes);
            completedEvent.setSuccessfulRecipes(this.recipes);
        } else if (CollectionUtils.notEmpty((Collection<?>) this.recipeBoxItems)) {
            final RecipeBoxItemsDBAdapter recipeBoxItemsDBAdapter = new RecipeBoxItemsDBAdapter(context, this.dbConnectionManager);
            DatabaseUtil.ensureDatabaseTransaction(DbConnectionManager.getDb(context), new Function0() {
                public void execute() {
                    for (RecipeBoxItem deleteRecipeBoxItem : DeleteRecipesTask.this.recipeBoxItems) {
                        recipeBoxItemsDBAdapter.deleteRecipeBoxItem(deleteRecipeBoxItem);
                    }
                }
            });
        }
        return Boolean.valueOf(true);
    }
}
