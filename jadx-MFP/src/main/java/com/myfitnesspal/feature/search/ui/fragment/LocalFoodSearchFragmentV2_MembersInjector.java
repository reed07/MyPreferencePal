package com.myfitnesspal.feature.search.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.feature.meals.util.MealUtil;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.factory.VMFactory;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import com.myfitnesspal.shared.util.MultiAddFoodHelper;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class LocalFoodSearchFragmentV2_MembersInjector implements MembersInjector<LocalFoodSearchFragmentV2> {
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<ImageService> imageServiceProvider;
    private final Provider<MealUtil> mealUtilProvider;
    private final Provider<MultiAddFoodHelper> multiAddFoodHelperProvider;
    private final Provider<UserEnergyService> userEnergyServiceProvider;
    private final Provider<VMFactory> vmFactoryProvider;

    public LocalFoodSearchFragmentV2_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<VMFactory> provider3, Provider<ImageService> provider4, Provider<UserEnergyService> provider5, Provider<MultiAddFoodHelper> provider6, Provider<MealUtil> provider7) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.vmFactoryProvider = provider3;
        this.imageServiceProvider = provider4;
        this.userEnergyServiceProvider = provider5;
        this.multiAddFoodHelperProvider = provider6;
        this.mealUtilProvider = provider7;
    }

    public static MembersInjector<LocalFoodSearchFragmentV2> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<VMFactory> provider3, Provider<ImageService> provider4, Provider<UserEnergyService> provider5, Provider<MultiAddFoodHelper> provider6, Provider<MealUtil> provider7) {
        LocalFoodSearchFragmentV2_MembersInjector localFoodSearchFragmentV2_MembersInjector = new LocalFoodSearchFragmentV2_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7);
        return localFoodSearchFragmentV2_MembersInjector;
    }

    public void injectMembers(LocalFoodSearchFragmentV2 localFoodSearchFragmentV2) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(localFoodSearchFragmentV2, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(localFoodSearchFragmentV2, (Glide) this.glideProvider.get());
        injectVmFactory(localFoodSearchFragmentV2, (VMFactory) this.vmFactoryProvider.get());
        injectImageService(localFoodSearchFragmentV2, DoubleCheck.lazy(this.imageServiceProvider));
        injectUserEnergyService(localFoodSearchFragmentV2, DoubleCheck.lazy(this.userEnergyServiceProvider));
        injectMultiAddFoodHelper(localFoodSearchFragmentV2, DoubleCheck.lazy(this.multiAddFoodHelperProvider));
        injectMealUtil(localFoodSearchFragmentV2, DoubleCheck.lazy(this.mealUtilProvider));
    }

    public static void injectVmFactory(LocalFoodSearchFragmentV2 localFoodSearchFragmentV2, VMFactory vMFactory) {
        localFoodSearchFragmentV2.vmFactory = vMFactory;
    }

    public static void injectImageService(LocalFoodSearchFragmentV2 localFoodSearchFragmentV2, Lazy<ImageService> lazy) {
        localFoodSearchFragmentV2.imageService = lazy;
    }

    public static void injectUserEnergyService(LocalFoodSearchFragmentV2 localFoodSearchFragmentV2, Lazy<UserEnergyService> lazy) {
        localFoodSearchFragmentV2.userEnergyService = lazy;
    }

    public static void injectMultiAddFoodHelper(LocalFoodSearchFragmentV2 localFoodSearchFragmentV2, Lazy<MultiAddFoodHelper> lazy) {
        localFoodSearchFragmentV2.multiAddFoodHelper = lazy;
    }

    public static void injectMealUtil(LocalFoodSearchFragmentV2 localFoodSearchFragmentV2, Lazy<MealUtil> lazy) {
        localFoodSearchFragmentV2.mealUtil = lazy;
    }
}
