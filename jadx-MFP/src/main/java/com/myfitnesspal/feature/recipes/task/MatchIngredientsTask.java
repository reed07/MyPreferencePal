package com.myfitnesspal.feature.recipes.task;

import android.content.Context;
import com.myfitnesspal.feature.recipes.api.RecipeMatchRequest;
import com.myfitnesspal.feature.recipes.service.RecipeService;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.model.v2.MfpIngredientItem;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.List;

public class MatchIngredientsTask extends EventedTaskBase<List<MfpIngredientItem>, ApiException> {
    private final String hash;
    private final String ingredientsString;
    private final Lazy<RecipeService> recipeService;

    public static class CompletedEvent extends TaskEventBase<List<MfpIngredientItem>, ApiException> {
    }

    public MatchIngredientsTask(Lazy<RecipeService> lazy, String str, String str2) {
        this.recipeService = lazy;
        this.ingredientsString = str;
        this.hash = str2;
    }

    /* access modifiers changed from: protected */
    public List<MfpIngredientItem> exec(Context context) throws ApiException {
        List ingredients = getIngredients(this.ingredientsString);
        if (ingredients.isEmpty()) {
            return new ArrayList();
        }
        return ((RecipeService) this.recipeService.get()).matchIngredients(new RecipeMatchRequest(ingredients, this.hash));
    }

    private List<String> getIngredients(String str) {
        String[] split = str.split("\n");
        ArrayList arrayList = new ArrayList();
        for (String trim : split) {
            String trim2 = trim.trim();
            if (!Strings.isEmpty(trim2)) {
                arrayList.add(trim2);
            }
        }
        return arrayList;
    }
}
