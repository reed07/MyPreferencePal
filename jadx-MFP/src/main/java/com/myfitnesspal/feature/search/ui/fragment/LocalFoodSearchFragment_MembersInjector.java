package com.myfitnesspal.feature.search.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.feature.meals.util.MealUtil;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.search.service.FoodSearchAnalyticsHelper;
import com.myfitnesspal.feature.search.service.SearchService;
import com.myfitnesspal.feature.search.ui.FoodSearchActivityFactory;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.myfitnesspal.shared.util.MultiAddFoodHelper;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class LocalFoodSearchFragment_MembersInjector implements MembersInjector<LocalFoodSearchFragment> {
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<DbConnectionManager> dbConnectionManagerProvider;
    private final Provider<FoodSearchAnalyticsHelper> foodSearchAnalyticsHelperProvider;
    private final Provider<FoodSearchActivityFactory> foodSearchRouterProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<ImageService> imageServiceProvider;
    private final Provider<LocalSettingsService> localSettingsServiceProvider;
    private final Provider<LocalizedStringsUtil> localizedStringsUtilProvider;
    private final Provider<MealUtil> mealHelperUtilProvider;
    private final Provider<MultiAddFoodHelper> multiAddFoodHelperProvider;
    private final Provider<PremiumService> premiumServiceProvider;
    private final Provider<SearchService> searchServiceProvider;
    private final Provider<UserEnergyService> userEnergyServiceProvider;

    public LocalFoodSearchFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<MultiAddFoodHelper> provider3, Provider<FoodSearchAnalyticsHelper> provider4, Provider<UserEnergyService> provider5, Provider<ImageService> provider6, Provider<MealUtil> provider7, Provider<LocalizedStringsUtil> provider8, Provider<PremiumService> provider9, Provider<SearchService> provider10, Provider<DbConnectionManager> provider11, Provider<LocalSettingsService> provider12, Provider<FoodSearchActivityFactory> provider13) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.multiAddFoodHelperProvider = provider3;
        this.foodSearchAnalyticsHelperProvider = provider4;
        this.userEnergyServiceProvider = provider5;
        this.imageServiceProvider = provider6;
        this.mealHelperUtilProvider = provider7;
        this.localizedStringsUtilProvider = provider8;
        this.premiumServiceProvider = provider9;
        this.searchServiceProvider = provider10;
        this.dbConnectionManagerProvider = provider11;
        this.localSettingsServiceProvider = provider12;
        this.foodSearchRouterProvider = provider13;
    }

    public static MembersInjector<LocalFoodSearchFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<MultiAddFoodHelper> provider3, Provider<FoodSearchAnalyticsHelper> provider4, Provider<UserEnergyService> provider5, Provider<ImageService> provider6, Provider<MealUtil> provider7, Provider<LocalizedStringsUtil> provider8, Provider<PremiumService> provider9, Provider<SearchService> provider10, Provider<DbConnectionManager> provider11, Provider<LocalSettingsService> provider12, Provider<FoodSearchActivityFactory> provider13) {
        LocalFoodSearchFragment_MembersInjector localFoodSearchFragment_MembersInjector = new LocalFoodSearchFragment_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13);
        return localFoodSearchFragment_MembersInjector;
    }

    public void injectMembers(LocalFoodSearchFragment localFoodSearchFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(localFoodSearchFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(localFoodSearchFragment, (Glide) this.glideProvider.get());
        injectMultiAddFoodHelper(localFoodSearchFragment, DoubleCheck.lazy(this.multiAddFoodHelperProvider));
        injectFoodSearchAnalyticsHelper(localFoodSearchFragment, DoubleCheck.lazy(this.foodSearchAnalyticsHelperProvider));
        injectUserEnergyService(localFoodSearchFragment, DoubleCheck.lazy(this.userEnergyServiceProvider));
        injectImageService(localFoodSearchFragment, DoubleCheck.lazy(this.imageServiceProvider));
        injectMealHelperUtil(localFoodSearchFragment, DoubleCheck.lazy(this.mealHelperUtilProvider));
        injectLocalizedStringsUtil(localFoodSearchFragment, DoubleCheck.lazy(this.localizedStringsUtilProvider));
        injectPremiumService(localFoodSearchFragment, DoubleCheck.lazy(this.premiumServiceProvider));
        injectSearchService(localFoodSearchFragment, DoubleCheck.lazy(this.searchServiceProvider));
        injectDbConnectionManager(localFoodSearchFragment, DoubleCheck.lazy(this.dbConnectionManagerProvider));
        injectLocalSettingsService(localFoodSearchFragment, DoubleCheck.lazy(this.localSettingsServiceProvider));
        injectFoodSearchRouter(localFoodSearchFragment, DoubleCheck.lazy(this.foodSearchRouterProvider));
    }

    public static void injectMultiAddFoodHelper(LocalFoodSearchFragment localFoodSearchFragment, Lazy<MultiAddFoodHelper> lazy) {
        localFoodSearchFragment.multiAddFoodHelper = lazy;
    }

    public static void injectFoodSearchAnalyticsHelper(LocalFoodSearchFragment localFoodSearchFragment, Lazy<FoodSearchAnalyticsHelper> lazy) {
        localFoodSearchFragment.foodSearchAnalyticsHelper = lazy;
    }

    public static void injectUserEnergyService(LocalFoodSearchFragment localFoodSearchFragment, Lazy<UserEnergyService> lazy) {
        localFoodSearchFragment.userEnergyService = lazy;
    }

    public static void injectImageService(LocalFoodSearchFragment localFoodSearchFragment, Lazy<ImageService> lazy) {
        localFoodSearchFragment.imageService = lazy;
    }

    public static void injectMealHelperUtil(LocalFoodSearchFragment localFoodSearchFragment, Lazy<MealUtil> lazy) {
        localFoodSearchFragment.mealHelperUtil = lazy;
    }

    public static void injectLocalizedStringsUtil(LocalFoodSearchFragment localFoodSearchFragment, Lazy<LocalizedStringsUtil> lazy) {
        localFoodSearchFragment.localizedStringsUtil = lazy;
    }

    public static void injectPremiumService(LocalFoodSearchFragment localFoodSearchFragment, Lazy<PremiumService> lazy) {
        localFoodSearchFragment.premiumService = lazy;
    }

    public static void injectSearchService(LocalFoodSearchFragment localFoodSearchFragment, Lazy<SearchService> lazy) {
        localFoodSearchFragment.searchService = lazy;
    }

    public static void injectDbConnectionManager(LocalFoodSearchFragment localFoodSearchFragment, Lazy<DbConnectionManager> lazy) {
        localFoodSearchFragment.dbConnectionManager = lazy;
    }

    public static void injectLocalSettingsService(LocalFoodSearchFragment localFoodSearchFragment, Lazy<LocalSettingsService> lazy) {
        localFoodSearchFragment.localSettingsService = lazy;
    }

    public static void injectFoodSearchRouter(LocalFoodSearchFragment localFoodSearchFragment, Lazy<FoodSearchActivityFactory> lazy) {
        localFoodSearchFragment.foodSearchRouter = lazy;
    }
}
