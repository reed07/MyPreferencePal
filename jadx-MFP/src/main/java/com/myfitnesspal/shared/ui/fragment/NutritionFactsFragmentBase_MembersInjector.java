package com.myfitnesspal.shared.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class NutritionFactsFragmentBase_MembersInjector implements MembersInjector<NutritionFactsFragmentBase> {
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<UserEnergyService> userEnergyServiceProvider;

    public NutritionFactsFragmentBase_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<UserEnergyService> provider3) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.userEnergyServiceProvider = provider3;
    }

    public static MembersInjector<NutritionFactsFragmentBase> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<UserEnergyService> provider3) {
        return new NutritionFactsFragmentBase_MembersInjector(provider, provider2, provider3);
    }

    public void injectMembers(NutritionFactsFragmentBase nutritionFactsFragmentBase) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(nutritionFactsFragmentBase, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(nutritionFactsFragmentBase, (Glide) this.glideProvider.get());
        injectUserEnergyService(nutritionFactsFragmentBase, (UserEnergyService) this.userEnergyServiceProvider.get());
    }

    public static void injectUserEnergyService(NutritionFactsFragmentBase nutritionFactsFragmentBase, UserEnergyService userEnergyService) {
        nutritionFactsFragmentBase.userEnergyService = userEnergyService;
    }
}
