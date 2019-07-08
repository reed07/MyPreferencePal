package com.myfitnesspal.shared.ui.activity.impl.resourceloader.mixin;

import com.myfitnesspal.feature.recipes.service.RecipeService;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class GetRecipeV2Mixin_MembersInjector implements MembersInjector<GetRecipeV2Mixin> {
    private final Provider<RecipeService> recipeServiceProvider;

    public GetRecipeV2Mixin_MembersInjector(Provider<RecipeService> provider) {
        this.recipeServiceProvider = provider;
    }

    public static MembersInjector<GetRecipeV2Mixin> create(Provider<RecipeService> provider) {
        return new GetRecipeV2Mixin_MembersInjector(provider);
    }

    public void injectMembers(GetRecipeV2Mixin getRecipeV2Mixin) {
        injectRecipeService(getRecipeV2Mixin, DoubleCheck.lazy(this.recipeServiceProvider));
    }

    public static void injectRecipeService(GetRecipeV2Mixin getRecipeV2Mixin, Lazy<RecipeService> lazy) {
        getRecipeV2Mixin.recipeService = lazy;
    }
}
