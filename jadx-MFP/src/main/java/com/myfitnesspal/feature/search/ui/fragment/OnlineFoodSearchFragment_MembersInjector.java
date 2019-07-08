package com.myfitnesspal.feature.search.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.search.service.SearchService;
import com.myfitnesspal.shared.model.mapper.impl.FoodMapper;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.service.analytics.ActionTrackingService;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.factory.VMFactory;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import com.myfitnesspal.shared.util.MultiAddFoodHelper;
import com.uacf.core.util.DeviceInfo;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class OnlineFoodSearchFragment_MembersInjector implements MembersInjector<OnlineFoodSearchFragment> {
    private final Provider<ActionTrackingService> actionTrackingServiceProvider;
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<CountryService> countryServiceProvider;
    private final Provider<DeviceInfo> deviceInfoProvider;
    private final Provider<DiaryService> diaryServiceProvider;
    private final Provider<FoodMapper> foodMapperProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<MultiAddFoodHelper> multiAddFoodHelperProvider;
    private final Provider<SearchService> searchServiceProvider;
    private final Provider<UserEnergyService> userEnergyServiceProvider;
    private final Provider<VMFactory> vmFactoryProvider;

    public OnlineFoodSearchFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<SearchService> provider3, Provider<ActionTrackingService> provider4, Provider<UserEnergyService> provider5, Provider<FoodMapper> provider6, Provider<CountryService> provider7, Provider<DiaryService> provider8, Provider<MultiAddFoodHelper> provider9, Provider<DeviceInfo> provider10, Provider<VMFactory> provider11) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.searchServiceProvider = provider3;
        this.actionTrackingServiceProvider = provider4;
        this.userEnergyServiceProvider = provider5;
        this.foodMapperProvider = provider6;
        this.countryServiceProvider = provider7;
        this.diaryServiceProvider = provider8;
        this.multiAddFoodHelperProvider = provider9;
        this.deviceInfoProvider = provider10;
        this.vmFactoryProvider = provider11;
    }

    public static MembersInjector<OnlineFoodSearchFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<SearchService> provider3, Provider<ActionTrackingService> provider4, Provider<UserEnergyService> provider5, Provider<FoodMapper> provider6, Provider<CountryService> provider7, Provider<DiaryService> provider8, Provider<MultiAddFoodHelper> provider9, Provider<DeviceInfo> provider10, Provider<VMFactory> provider11) {
        OnlineFoodSearchFragment_MembersInjector onlineFoodSearchFragment_MembersInjector = new OnlineFoodSearchFragment_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11);
        return onlineFoodSearchFragment_MembersInjector;
    }

    public void injectMembers(OnlineFoodSearchFragment onlineFoodSearchFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(onlineFoodSearchFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(onlineFoodSearchFragment, (Glide) this.glideProvider.get());
        injectSearchService(onlineFoodSearchFragment, DoubleCheck.lazy(this.searchServiceProvider));
        injectActionTrackingService(onlineFoodSearchFragment, DoubleCheck.lazy(this.actionTrackingServiceProvider));
        injectUserEnergyService(onlineFoodSearchFragment, DoubleCheck.lazy(this.userEnergyServiceProvider));
        injectFoodMapper(onlineFoodSearchFragment, DoubleCheck.lazy(this.foodMapperProvider));
        injectCountryService(onlineFoodSearchFragment, DoubleCheck.lazy(this.countryServiceProvider));
        injectDiaryService(onlineFoodSearchFragment, DoubleCheck.lazy(this.diaryServiceProvider));
        injectMultiAddFoodHelper(onlineFoodSearchFragment, DoubleCheck.lazy(this.multiAddFoodHelperProvider));
        injectDeviceInfo(onlineFoodSearchFragment, DoubleCheck.lazy(this.deviceInfoProvider));
        injectVmFactory(onlineFoodSearchFragment, (VMFactory) this.vmFactoryProvider.get());
    }

    public static void injectSearchService(OnlineFoodSearchFragment onlineFoodSearchFragment, Lazy<SearchService> lazy) {
        onlineFoodSearchFragment.searchService = lazy;
    }

    public static void injectActionTrackingService(OnlineFoodSearchFragment onlineFoodSearchFragment, Lazy<ActionTrackingService> lazy) {
        onlineFoodSearchFragment.actionTrackingService = lazy;
    }

    public static void injectUserEnergyService(OnlineFoodSearchFragment onlineFoodSearchFragment, Lazy<UserEnergyService> lazy) {
        onlineFoodSearchFragment.userEnergyService = lazy;
    }

    public static void injectFoodMapper(OnlineFoodSearchFragment onlineFoodSearchFragment, Lazy<FoodMapper> lazy) {
        onlineFoodSearchFragment.foodMapper = lazy;
    }

    public static void injectCountryService(OnlineFoodSearchFragment onlineFoodSearchFragment, Lazy<CountryService> lazy) {
        onlineFoodSearchFragment.countryService = lazy;
    }

    public static void injectDiaryService(OnlineFoodSearchFragment onlineFoodSearchFragment, Lazy<DiaryService> lazy) {
        onlineFoodSearchFragment.diaryService = lazy;
    }

    public static void injectMultiAddFoodHelper(OnlineFoodSearchFragment onlineFoodSearchFragment, Lazy<MultiAddFoodHelper> lazy) {
        onlineFoodSearchFragment.multiAddFoodHelper = lazy;
    }

    public static void injectDeviceInfo(OnlineFoodSearchFragment onlineFoodSearchFragment, Lazy<DeviceInfo> lazy) {
        onlineFoodSearchFragment.deviceInfo = lazy;
    }

    public static void injectVmFactory(OnlineFoodSearchFragment onlineFoodSearchFragment, VMFactory vMFactory) {
        onlineFoodSearchFragment.vmFactory = vMFactory;
    }
}
