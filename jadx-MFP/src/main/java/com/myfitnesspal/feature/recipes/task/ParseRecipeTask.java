package com.myfitnesspal.feature.recipes.task;

import android.content.Context;
import com.myfitnesspal.feature.recipes.api.RecipeParseResult;
import com.myfitnesspal.feature.recipes.service.RecipeService;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.api.ApiException;
import dagger.Lazy;

public class ParseRecipeTask extends EventedTaskBase<RecipeParseResult, ApiException> {
    public static final String NAME = "ParseRecipeTask";
    private final Lazy<RecipeService> recipeService;
    private final String url;

    public static class CompletedEvent extends TaskEventBase<RecipeParseResult, ApiException> {
        private final String originalUrl;

        public CompletedEvent(String str) {
            this.originalUrl = str;
        }

        public String getOriginalUrl() {
            return this.originalUrl;
        }
    }

    /* access modifiers changed from: protected */
    public String getTaskName() {
        return NAME;
    }

    public ParseRecipeTask(Lazy<RecipeService> lazy, String str) {
        super((TaskEventBase) new CompletedEvent(str));
        this.recipeService = lazy;
        this.url = str;
    }

    /* access modifiers changed from: protected */
    public RecipeParseResult exec(Context context) throws ApiException {
        return ((RecipeService) this.recipeService.get()).parseRecipe(this.url);
    }
}
