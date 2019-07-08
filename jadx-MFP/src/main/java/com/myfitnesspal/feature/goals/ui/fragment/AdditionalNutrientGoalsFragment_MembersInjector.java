package com.myfitnesspal.feature.goals.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.feature.goals.service.NutrientGoalService;
import com.myfitnesspal.feature.goals.service.NutrientGoalsUtil;
import com.myfitnesspal.feature.premium.util.PremiumApiErrorUtil;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class AdditionalNutrientGoalsFragment_MembersInjector implements MembersInjector<AdditionalNutrientGoalsFragment> {
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<NutrientGoalService> nutrientGoalServiceProvider;
    private final Provider<NutrientGoalsUtil> nutrientGoalsUtilProvider;
    private final Provider<PremiumApiErrorUtil> premiumApiErrorUtilProvider;

    public AdditionalNutrientGoalsFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<NutrientGoalsUtil> provider3, Provider<NutrientGoalService> provider4, Provider<PremiumApiErrorUtil> provider5) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.nutrientGoalsUtilProvider = provider3;
        this.nutrientGoalServiceProvider = provider4;
        this.premiumApiErrorUtilProvider = provider5;
    }

    public static MembersInjector<AdditionalNutrientGoalsFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<NutrientGoalsUtil> provider3, Provider<NutrientGoalService> provider4, Provider<PremiumApiErrorUtil> provider5) {
        AdditionalNutrientGoalsFragment_MembersInjector additionalNutrientGoalsFragment_MembersInjector = new AdditionalNutrientGoalsFragment_MembersInjector(provider, provider2, provider3, provider4, provider5);
        return additionalNutrientGoalsFragment_MembersInjector;
    }

    public void injectMembers(AdditionalNutrientGoalsFragment additionalNutrientGoalsFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(additionalNutrientGoalsFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(additionalNutrientGoalsFragment, (Glide) this.glideProvider.get());
        injectNutrientGoalsUtil(additionalNutrientGoalsFragment, DoubleCheck.lazy(this.nutrientGoalsUtilProvider));
        injectNutrientGoalService(additionalNutrientGoalsFragment, DoubleCheck.lazy(this.nutrientGoalServiceProvider));
        injectPremiumApiErrorUtil(additionalNutrientGoalsFragment, DoubleCheck.lazy(this.premiumApiErrorUtilProvider));
    }

    public static void injectNutrientGoalsUtil(AdditionalNutrientGoalsFragment additionalNutrientGoalsFragment, Lazy<NutrientGoalsUtil> lazy) {
        additionalNutrientGoalsFragment.nutrientGoalsUtil = lazy;
    }

    public static void injectNutrientGoalService(AdditionalNutrientGoalsFragment additionalNutrientGoalsFragment, Lazy<NutrientGoalService> lazy) {
        additionalNutrientGoalsFragment.nutrientGoalService = lazy;
    }

    public static void injectPremiumApiErrorUtil(AdditionalNutrientGoalsFragment additionalNutrientGoalsFragment, Lazy<PremiumApiErrorUtil> lazy) {
        additionalNutrientGoalsFragment.premiumApiErrorUtil = lazy;
    }
}
