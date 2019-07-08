package com.myfitnesspal.feature.recipes.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.feature.recipes.service.RecipeService;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.service.analytics.ActionTrackingService;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class RecipeDetailsFragment_MembersInjector implements MembersInjector<RecipeDetailsFragment> {
    private final Provider<ActionTrackingService> actionTrackingServiceProvider;
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<RecipeService> recipeServiceProvider;
    private final Provider<UserEnergyService> userEnergyServiceProvider;

    public RecipeDetailsFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<UserEnergyService> provider3, Provider<RecipeService> provider4, Provider<ActionTrackingService> provider5) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.userEnergyServiceProvider = provider3;
        this.recipeServiceProvider = provider4;
        this.actionTrackingServiceProvider = provider5;
    }

    public static MembersInjector<RecipeDetailsFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<UserEnergyService> provider3, Provider<RecipeService> provider4, Provider<ActionTrackingService> provider5) {
        RecipeDetailsFragment_MembersInjector recipeDetailsFragment_MembersInjector = new RecipeDetailsFragment_MembersInjector(provider, provider2, provider3, provider4, provider5);
        return recipeDetailsFragment_MembersInjector;
    }

    public void injectMembers(RecipeDetailsFragment recipeDetailsFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(recipeDetailsFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(recipeDetailsFragment, (Glide) this.glideProvider.get());
        injectUserEnergyService(recipeDetailsFragment, DoubleCheck.lazy(this.userEnergyServiceProvider));
        injectRecipeService(recipeDetailsFragment, DoubleCheck.lazy(this.recipeServiceProvider));
        injectActionTrackingService(recipeDetailsFragment, DoubleCheck.lazy(this.actionTrackingServiceProvider));
    }

    public static void injectUserEnergyService(RecipeDetailsFragment recipeDetailsFragment, Lazy<UserEnergyService> lazy) {
        recipeDetailsFragment.userEnergyService = lazy;
    }

    public static void injectRecipeService(RecipeDetailsFragment recipeDetailsFragment, Lazy<RecipeService> lazy) {
        recipeDetailsFragment.recipeService = lazy;
    }

    public static void injectActionTrackingService(RecipeDetailsFragment recipeDetailsFragment, Lazy<ActionTrackingService> lazy) {
        recipeDetailsFragment.actionTrackingService = lazy;
    }
}
