package com.myfitnesspal.feature.recipes.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.feature.recipes.service.RecipeService;
import com.myfitnesspal.feature.recipes.service.RecipesAnalyticsHelper;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.service.syncv2.SyncService;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class CreateOrEditRecipeFragment_MembersInjector implements MembersInjector<CreateOrEditRecipeFragment> {
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<RecipeService> recipeServiceProvider;
    private final Provider<RecipesAnalyticsHelper> recipesAnalyticsHelperProvider;
    private final Provider<SyncService> syncServiceProvider;

    public CreateOrEditRecipeFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<RecipeService> provider3, Provider<SyncService> provider4, Provider<RecipesAnalyticsHelper> provider5) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.recipeServiceProvider = provider3;
        this.syncServiceProvider = provider4;
        this.recipesAnalyticsHelperProvider = provider5;
    }

    public static MembersInjector<CreateOrEditRecipeFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<RecipeService> provider3, Provider<SyncService> provider4, Provider<RecipesAnalyticsHelper> provider5) {
        CreateOrEditRecipeFragment_MembersInjector createOrEditRecipeFragment_MembersInjector = new CreateOrEditRecipeFragment_MembersInjector(provider, provider2, provider3, provider4, provider5);
        return createOrEditRecipeFragment_MembersInjector;
    }

    public void injectMembers(CreateOrEditRecipeFragment createOrEditRecipeFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(createOrEditRecipeFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(createOrEditRecipeFragment, (Glide) this.glideProvider.get());
        injectRecipeService(createOrEditRecipeFragment, DoubleCheck.lazy(this.recipeServiceProvider));
        injectSyncService(createOrEditRecipeFragment, DoubleCheck.lazy(this.syncServiceProvider));
        injectRecipesAnalyticsHelper(createOrEditRecipeFragment, DoubleCheck.lazy(this.recipesAnalyticsHelperProvider));
    }

    public static void injectRecipeService(CreateOrEditRecipeFragment createOrEditRecipeFragment, Lazy<RecipeService> lazy) {
        createOrEditRecipeFragment.recipeService = lazy;
    }

    public static void injectSyncService(CreateOrEditRecipeFragment createOrEditRecipeFragment, Lazy<SyncService> lazy) {
        createOrEditRecipeFragment.syncService = lazy;
    }

    public static void injectRecipesAnalyticsHelper(CreateOrEditRecipeFragment createOrEditRecipeFragment, Lazy<RecipesAnalyticsHelper> lazy) {
        createOrEditRecipeFragment.recipesAnalyticsHelper = lazy;
    }
}
