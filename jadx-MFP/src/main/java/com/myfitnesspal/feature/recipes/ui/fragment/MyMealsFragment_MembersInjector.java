package com.myfitnesspal.feature.recipes.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.feature.meals.util.MealUtil;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class MyMealsFragment_MembersInjector implements MembersInjector<MyMealsFragment> {
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<ConfigService> configServiceProvider;
    private final Provider<DbConnectionManager> dbConnectionManagerProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<ImageService> imageServiceProvider;
    private final Provider<LocalSettingsService> localSettingsServiceProvider;
    private final Provider<MealUtil> mealUtilProvider;
    private final Provider<UserEnergyService> userEnergyServiceProvider;

    public MyMealsFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<MealUtil> provider3, Provider<UserEnergyService> provider4, Provider<ConfigService> provider5, Provider<ImageService> provider6, Provider<DbConnectionManager> provider7, Provider<LocalSettingsService> provider8) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.mealUtilProvider = provider3;
        this.userEnergyServiceProvider = provider4;
        this.configServiceProvider = provider5;
        this.imageServiceProvider = provider6;
        this.dbConnectionManagerProvider = provider7;
        this.localSettingsServiceProvider = provider8;
    }

    public static MembersInjector<MyMealsFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<MealUtil> provider3, Provider<UserEnergyService> provider4, Provider<ConfigService> provider5, Provider<ImageService> provider6, Provider<DbConnectionManager> provider7, Provider<LocalSettingsService> provider8) {
        MyMealsFragment_MembersInjector myMealsFragment_MembersInjector = new MyMealsFragment_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8);
        return myMealsFragment_MembersInjector;
    }

    public void injectMembers(MyMealsFragment myMealsFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(myMealsFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(myMealsFragment, (Glide) this.glideProvider.get());
        injectMealUtil(myMealsFragment, DoubleCheck.lazy(this.mealUtilProvider));
        injectUserEnergyService(myMealsFragment, DoubleCheck.lazy(this.userEnergyServiceProvider));
        injectConfigService(myMealsFragment, DoubleCheck.lazy(this.configServiceProvider));
        injectImageService(myMealsFragment, DoubleCheck.lazy(this.imageServiceProvider));
        injectDbConnectionManager(myMealsFragment, DoubleCheck.lazy(this.dbConnectionManagerProvider));
        injectLocalSettingsService(myMealsFragment, DoubleCheck.lazy(this.localSettingsServiceProvider));
    }

    public static void injectMealUtil(MyMealsFragment myMealsFragment, Lazy<MealUtil> lazy) {
        myMealsFragment.mealUtil = lazy;
    }

    public static void injectUserEnergyService(MyMealsFragment myMealsFragment, Lazy<UserEnergyService> lazy) {
        myMealsFragment.userEnergyService = lazy;
    }

    public static void injectConfigService(MyMealsFragment myMealsFragment, Lazy<ConfigService> lazy) {
        myMealsFragment.configService = lazy;
    }

    public static void injectImageService(MyMealsFragment myMealsFragment, Lazy<ImageService> lazy) {
        myMealsFragment.imageService = lazy;
    }

    public static void injectDbConnectionManager(MyMealsFragment myMealsFragment, Lazy<DbConnectionManager> lazy) {
        myMealsFragment.dbConnectionManager = lazy;
    }

    public static void injectLocalSettingsService(MyMealsFragment myMealsFragment, Lazy<LocalSettingsService> lazy) {
        myMealsFragment.localSettingsService = lazy;
    }
}
