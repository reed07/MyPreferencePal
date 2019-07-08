package com.myfitnesspal.feature.search.ui.viewmodel;

import android.app.Application;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.foodeditor.ui.service.FoodEditorAnalytics;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.recipes.service.RecipeService;
import com.myfitnesspal.feature.recipes.service.RecipesAnalyticsHelper;
import com.myfitnesspal.feature.search.service.FoodSearchAnalyticsHelper;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.model.mapper.impl.MealIngredientMapper;
import com.myfitnesspal.shared.service.analytics.ActionTrackingService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.syncv2.SyncType;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.myfitnesspal.shared.util.MultiAddFoodHelper;
import com.uacf.sync.engine.UacfScheduler;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class FoodSearchViewModel_Factory implements Factory<FoodSearchViewModel> {
    private final Provider<ActionTrackingService> actionTrackingServiceProvider;
    private final Provider<Application> applicationContextProvider;
    private final Provider<ConfigService> configServiceProvider;
    private final Provider<CountryService> countryServiceProvider;
    private final Provider<DbConnectionManager> dbConnectionManagerProvider;
    private final Provider<DiaryService> diaryServiceProvider;
    private final Provider<FoodEditorAnalytics> foodEditorAnalyticsProvider;
    private final Provider<FoodSearchAnalyticsHelper> foodSearchAnalyticsHelperProvider;
    private final Provider<LocalSettingsService> localSettingsServiceProvider;
    private final Provider<LocalizedStringsUtil> localizedStringsUtilProvider;
    private final Provider<MealIngredientMapper> mealIngredientMapperProvider;
    private final Provider<MultiAddFoodHelper> multiAddFoodHelperProvider;
    private final Provider<PremiumService> premiumServiceProvider;
    private final Provider<RecipeService> recipeServiceProvider;
    private final Provider<RecipesAnalyticsHelper> recipesAnalyticsHelperProvider;
    private final Provider<Session> sessionProvider;
    private final Provider<UacfScheduler<SyncType>> syncSchedulerProvider;
    private final Provider<UserEnergyService> userEnergyServiceProvider;

    public FoodSearchViewModel_Factory(Provider<Application> provider, Provider<CountryService> provider2, Provider<MultiAddFoodHelper> provider3, Provider<LocalizedStringsUtil> provider4, Provider<UserEnergyService> provider5, Provider<DiaryService> provider6, Provider<Session> provider7, Provider<MealIngredientMapper> provider8, Provider<PremiumService> provider9, Provider<LocalSettingsService> provider10, Provider<RecipeService> provider11, Provider<DbConnectionManager> provider12, Provider<UacfScheduler<SyncType>> provider13, Provider<FoodSearchAnalyticsHelper> provider14, Provider<RecipesAnalyticsHelper> provider15, Provider<FoodEditorAnalytics> provider16, Provider<ActionTrackingService> provider17, Provider<ConfigService> provider18) {
        this.applicationContextProvider = provider;
        this.countryServiceProvider = provider2;
        this.multiAddFoodHelperProvider = provider3;
        this.localizedStringsUtilProvider = provider4;
        this.userEnergyServiceProvider = provider5;
        this.diaryServiceProvider = provider6;
        this.sessionProvider = provider7;
        this.mealIngredientMapperProvider = provider8;
        this.premiumServiceProvider = provider9;
        this.localSettingsServiceProvider = provider10;
        this.recipeServiceProvider = provider11;
        this.dbConnectionManagerProvider = provider12;
        this.syncSchedulerProvider = provider13;
        this.foodSearchAnalyticsHelperProvider = provider14;
        this.recipesAnalyticsHelperProvider = provider15;
        this.foodEditorAnalyticsProvider = provider16;
        this.actionTrackingServiceProvider = provider17;
        this.configServiceProvider = provider18;
    }

    public FoodSearchViewModel get() {
        Provider<Application> provider = this.applicationContextProvider;
        return provideInstance(provider, this.countryServiceProvider, this.multiAddFoodHelperProvider, this.localizedStringsUtilProvider, this.userEnergyServiceProvider, this.diaryServiceProvider, this.sessionProvider, this.mealIngredientMapperProvider, this.premiumServiceProvider, this.localSettingsServiceProvider, this.recipeServiceProvider, this.dbConnectionManagerProvider, this.syncSchedulerProvider, this.foodSearchAnalyticsHelperProvider, this.recipesAnalyticsHelperProvider, this.foodEditorAnalyticsProvider, this.actionTrackingServiceProvider, this.configServiceProvider);
    }

    public static FoodSearchViewModel provideInstance(Provider<Application> provider, Provider<CountryService> provider2, Provider<MultiAddFoodHelper> provider3, Provider<LocalizedStringsUtil> provider4, Provider<UserEnergyService> provider5, Provider<DiaryService> provider6, Provider<Session> provider7, Provider<MealIngredientMapper> provider8, Provider<PremiumService> provider9, Provider<LocalSettingsService> provider10, Provider<RecipeService> provider11, Provider<DbConnectionManager> provider12, Provider<UacfScheduler<SyncType>> provider13, Provider<FoodSearchAnalyticsHelper> provider14, Provider<RecipesAnalyticsHelper> provider15, Provider<FoodEditorAnalytics> provider16, Provider<ActionTrackingService> provider17, Provider<ConfigService> provider18) {
        FoodSearchViewModel foodSearchViewModel = new FoodSearchViewModel((Application) provider.get(), (CountryService) provider2.get(), DoubleCheck.lazy(provider3), DoubleCheck.lazy(provider4), DoubleCheck.lazy(provider5), DoubleCheck.lazy(provider6), DoubleCheck.lazy(provider7), DoubleCheck.lazy(provider8), DoubleCheck.lazy(provider9), (LocalSettingsService) provider10.get(), DoubleCheck.lazy(provider11), DoubleCheck.lazy(provider12), DoubleCheck.lazy(provider13), DoubleCheck.lazy(provider14), DoubleCheck.lazy(provider15), DoubleCheck.lazy(provider16), DoubleCheck.lazy(provider17), (ConfigService) provider18.get());
        return foodSearchViewModel;
    }

    public static FoodSearchViewModel_Factory create(Provider<Application> provider, Provider<CountryService> provider2, Provider<MultiAddFoodHelper> provider3, Provider<LocalizedStringsUtil> provider4, Provider<UserEnergyService> provider5, Provider<DiaryService> provider6, Provider<Session> provider7, Provider<MealIngredientMapper> provider8, Provider<PremiumService> provider9, Provider<LocalSettingsService> provider10, Provider<RecipeService> provider11, Provider<DbConnectionManager> provider12, Provider<UacfScheduler<SyncType>> provider13, Provider<FoodSearchAnalyticsHelper> provider14, Provider<RecipesAnalyticsHelper> provider15, Provider<FoodEditorAnalytics> provider16, Provider<ActionTrackingService> provider17, Provider<ConfigService> provider18) {
        FoodSearchViewModel_Factory foodSearchViewModel_Factory = new FoodSearchViewModel_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15, provider16, provider17, provider18);
        return foodSearchViewModel_Factory;
    }

    public static FoodSearchViewModel newFoodSearchViewModel(Application application, CountryService countryService, Lazy<MultiAddFoodHelper> lazy, Lazy<LocalizedStringsUtil> lazy2, Lazy<UserEnergyService> lazy3, Lazy<DiaryService> lazy4, Lazy<Session> lazy5, Lazy<MealIngredientMapper> lazy6, Lazy<PremiumService> lazy7, LocalSettingsService localSettingsService, Lazy<RecipeService> lazy8, Lazy<DbConnectionManager> lazy9, Lazy<UacfScheduler<SyncType>> lazy10, Lazy<FoodSearchAnalyticsHelper> lazy11, Lazy<RecipesAnalyticsHelper> lazy12, Lazy<FoodEditorAnalytics> lazy13, Lazy<ActionTrackingService> lazy14, ConfigService configService) {
        FoodSearchViewModel foodSearchViewModel = new FoodSearchViewModel(application, countryService, lazy, lazy2, lazy3, lazy4, lazy5, lazy6, lazy7, localSettingsService, lazy8, lazy9, lazy10, lazy11, lazy12, lazy13, lazy14, configService);
        return foodSearchViewModel;
    }
}
