package com.myfitnesspal.feature.recipes.service;

import com.myfitnesspal.feature.recipes.api.RecipeMatchRequest;
import com.myfitnesspal.feature.recipes.api.RecipeParseResult;
import com.myfitnesspal.feature.recipes.model.MfpRecipeListContainer;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.api.v2.MfpV2ApiErrorCallback;
import com.myfitnesspal.shared.model.v1.RecipeBoxItem;
import com.myfitnesspal.shared.model.v1.RecipeFood;
import com.myfitnesspal.shared.model.v2.MfpIngredientItem;
import com.myfitnesspal.shared.model.v2.MfpRecipe;
import com.uacf.core.util.Function1;
import java.util.List;

public interface RecipeService {
    MfpRecipe createRecipe(MfpRecipe mfpRecipe) throws ApiException;

    List<MfpRecipe> deleteRecipesAndReturnFailedRecipes(List<MfpRecipe> list);

    MfpRecipe editRecipe(MfpRecipe mfpRecipe) throws ApiException;

    RecipeFood getRecipeFoodForV2RecipeId(String str);

    MfpRecipe getRecipeForRecipeBoxItem(RecipeBoxItem recipeBoxItem) throws ApiException;

    void getRecipesAsync(String str, Function1<MfpRecipeListContainer> function1, MfpV2ApiErrorCallback mfpV2ApiErrorCallback);

    boolean isRecipeParsingEnabledForCurrentLocale();

    List<MfpIngredientItem> matchIngredients(RecipeMatchRequest recipeMatchRequest) throws ApiException;

    RecipeParseResult parseRecipe(String str) throws ApiException;
}
