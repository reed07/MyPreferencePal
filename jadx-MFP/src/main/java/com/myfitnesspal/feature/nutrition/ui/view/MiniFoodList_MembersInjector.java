package com.myfitnesspal.feature.nutrition.ui.view;

import com.myfitnesspal.feature.nutrition.service.NutritionAnalyticsHelper;
import com.myfitnesspal.feature.nutrition.service.renderer.CoreChartRendererBaseConstructorArgs;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.squareup.otto.Bus;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class MiniFoodList_MembersInjector implements MembersInjector<MiniFoodList> {
    private final Provider<Bus> busProvider;
    private final Provider<ConfigService> configServiceProvider;
    private final Provider<CoreChartRendererBaseConstructorArgs> coreChartRendererBaseConstructorArgsProvider;
    private final Provider<LocalizedStringsUtil> localizedStringUtilProvider;
    private final Provider<NutritionAnalyticsHelper> nutritionAnalyticsHelperProvider;
    private final Provider<PremiumService> premiumServiceProvider;
    private final Provider<UserEnergyService> userEnergyServiceProvider;

    public MiniFoodList_MembersInjector(Provider<PremiumService> provider, Provider<CoreChartRendererBaseConstructorArgs> provider2, Provider<Bus> provider3, Provider<LocalizedStringsUtil> provider4, Provider<UserEnergyService> provider5, Provider<ConfigService> provider6, Provider<NutritionAnalyticsHelper> provider7) {
        this.premiumServiceProvider = provider;
        this.coreChartRendererBaseConstructorArgsProvider = provider2;
        this.busProvider = provider3;
        this.localizedStringUtilProvider = provider4;
        this.userEnergyServiceProvider = provider5;
        this.configServiceProvider = provider6;
        this.nutritionAnalyticsHelperProvider = provider7;
    }

    public static MembersInjector<MiniFoodList> create(Provider<PremiumService> provider, Provider<CoreChartRendererBaseConstructorArgs> provider2, Provider<Bus> provider3, Provider<LocalizedStringsUtil> provider4, Provider<UserEnergyService> provider5, Provider<ConfigService> provider6, Provider<NutritionAnalyticsHelper> provider7) {
        MiniFoodList_MembersInjector miniFoodList_MembersInjector = new MiniFoodList_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7);
        return miniFoodList_MembersInjector;
    }

    public void injectMembers(MiniFoodList miniFoodList) {
        injectPremiumService(miniFoodList, DoubleCheck.lazy(this.premiumServiceProvider));
        injectCoreChartRendererBaseConstructorArgs(miniFoodList, DoubleCheck.lazy(this.coreChartRendererBaseConstructorArgsProvider));
        injectBus(miniFoodList, DoubleCheck.lazy(this.busProvider));
        injectLocalizedStringUtil(miniFoodList, DoubleCheck.lazy(this.localizedStringUtilProvider));
        injectUserEnergyService(miniFoodList, DoubleCheck.lazy(this.userEnergyServiceProvider));
        injectConfigService(miniFoodList, DoubleCheck.lazy(this.configServiceProvider));
        injectNutritionAnalyticsHelper(miniFoodList, DoubleCheck.lazy(this.nutritionAnalyticsHelperProvider));
    }

    public static void injectPremiumService(MiniFoodList miniFoodList, Lazy<PremiumService> lazy) {
        miniFoodList.premiumService = lazy;
    }

    public static void injectCoreChartRendererBaseConstructorArgs(MiniFoodList miniFoodList, Lazy<CoreChartRendererBaseConstructorArgs> lazy) {
        miniFoodList.coreChartRendererBaseConstructorArgs = lazy;
    }

    public static void injectBus(MiniFoodList miniFoodList, Lazy<Bus> lazy) {
        miniFoodList.bus = lazy;
    }

    public static void injectLocalizedStringUtil(MiniFoodList miniFoodList, Lazy<LocalizedStringsUtil> lazy) {
        miniFoodList.localizedStringUtil = lazy;
    }

    public static void injectUserEnergyService(MiniFoodList miniFoodList, Lazy<UserEnergyService> lazy) {
        miniFoodList.userEnergyService = lazy;
    }

    public static void injectConfigService(MiniFoodList miniFoodList, Lazy<ConfigService> lazy) {
        miniFoodList.configService = lazy;
    }

    public static void injectNutritionAnalyticsHelper(MiniFoodList miniFoodList, Lazy<NutritionAnalyticsHelper> lazy) {
        miniFoodList.nutritionAnalyticsHelper = lazy;
    }
}
