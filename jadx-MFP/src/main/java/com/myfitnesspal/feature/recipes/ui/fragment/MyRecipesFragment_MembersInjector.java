package com.myfitnesspal.feature.recipes.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.feature.recipes.service.RecipeService;
import com.myfitnesspal.feature.recipes.service.RecipesAnalyticsHelper;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.service.analytics.ActionTrackingService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class MyRecipesFragment_MembersInjector implements MembersInjector<MyRecipesFragment> {
    private final Provider<ActionTrackingService> actionTrackingServiceProvider;
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<DbConnectionManager> dbConnectionManagerProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<LocalSettingsService> localSettingsServiceProvider;
    private final Provider<RecipeService> recipeServiceProvider;
    private final Provider<RecipesAnalyticsHelper> recipesAnalyticsHelperProvider;
    private final Provider<UserEnergyService> userEnergyServiceProvider;

    public MyRecipesFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<UserEnergyService> provider3, Provider<ActionTrackingService> provider4, Provider<RecipeService> provider5, Provider<RecipesAnalyticsHelper> provider6, Provider<LocalSettingsService> provider7, Provider<DbConnectionManager> provider8) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.userEnergyServiceProvider = provider3;
        this.actionTrackingServiceProvider = provider4;
        this.recipeServiceProvider = provider5;
        this.recipesAnalyticsHelperProvider = provider6;
        this.localSettingsServiceProvider = provider7;
        this.dbConnectionManagerProvider = provider8;
    }

    public static MembersInjector<MyRecipesFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<UserEnergyService> provider3, Provider<ActionTrackingService> provider4, Provider<RecipeService> provider5, Provider<RecipesAnalyticsHelper> provider6, Provider<LocalSettingsService> provider7, Provider<DbConnectionManager> provider8) {
        MyRecipesFragment_MembersInjector myRecipesFragment_MembersInjector = new MyRecipesFragment_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8);
        return myRecipesFragment_MembersInjector;
    }

    public void injectMembers(MyRecipesFragment myRecipesFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(myRecipesFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(myRecipesFragment, (Glide) this.glideProvider.get());
        injectUserEnergyService(myRecipesFragment, (UserEnergyService) this.userEnergyServiceProvider.get());
        injectActionTrackingService(myRecipesFragment, DoubleCheck.lazy(this.actionTrackingServiceProvider));
        injectRecipeService(myRecipesFragment, DoubleCheck.lazy(this.recipeServiceProvider));
        injectRecipesAnalyticsHelper(myRecipesFragment, DoubleCheck.lazy(this.recipesAnalyticsHelperProvider));
        injectLocalSettingsService(myRecipesFragment, DoubleCheck.lazy(this.localSettingsServiceProvider));
        injectDbConnectionManager(myRecipesFragment, (DbConnectionManager) this.dbConnectionManagerProvider.get());
    }

    public static void injectUserEnergyService(MyRecipesFragment myRecipesFragment, UserEnergyService userEnergyService) {
        myRecipesFragment.userEnergyService = userEnergyService;
    }

    public static void injectActionTrackingService(MyRecipesFragment myRecipesFragment, Lazy<ActionTrackingService> lazy) {
        myRecipesFragment.actionTrackingService = lazy;
    }

    public static void injectRecipeService(MyRecipesFragment myRecipesFragment, Lazy<RecipeService> lazy) {
        myRecipesFragment.recipeService = lazy;
    }

    public static void injectRecipesAnalyticsHelper(MyRecipesFragment myRecipesFragment, Lazy<RecipesAnalyticsHelper> lazy) {
        myRecipesFragment.recipesAnalyticsHelper = lazy;
    }

    public static void injectLocalSettingsService(MyRecipesFragment myRecipesFragment, Lazy<LocalSettingsService> lazy) {
        myRecipesFragment.localSettingsService = lazy;
    }

    public static void injectDbConnectionManager(MyRecipesFragment myRecipesFragment, DbConnectionManager dbConnectionManager) {
        myRecipesFragment.dbConnectionManager = dbConnectionManager;
    }
}
