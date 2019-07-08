package com.myfitnesspal.feature.dashboard.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.feature.premium.util.PremiumApiErrorUtil;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class CustomNutrientDashboardSelectionFragment_MembersInjector implements MembersInjector<CustomNutrientDashboardSelectionFragment> {
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<PremiumApiErrorUtil> premiumApiErrorUtilProvider;

    public CustomNutrientDashboardSelectionFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<PremiumApiErrorUtil> provider3) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.premiumApiErrorUtilProvider = provider3;
    }

    public static MembersInjector<CustomNutrientDashboardSelectionFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<PremiumApiErrorUtil> provider3) {
        return new CustomNutrientDashboardSelectionFragment_MembersInjector(provider, provider2, provider3);
    }

    public void injectMembers(CustomNutrientDashboardSelectionFragment customNutrientDashboardSelectionFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(customNutrientDashboardSelectionFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(customNutrientDashboardSelectionFragment, (Glide) this.glideProvider.get());
        injectPremiumApiErrorUtil(customNutrientDashboardSelectionFragment, DoubleCheck.lazy(this.premiumApiErrorUtilProvider));
    }

    public static void injectPremiumApiErrorUtil(CustomNutrientDashboardSelectionFragment customNutrientDashboardSelectionFragment, Lazy<PremiumApiErrorUtil> lazy) {
        customNutrientDashboardSelectionFragment.premiumApiErrorUtil = lazy;
    }
}
