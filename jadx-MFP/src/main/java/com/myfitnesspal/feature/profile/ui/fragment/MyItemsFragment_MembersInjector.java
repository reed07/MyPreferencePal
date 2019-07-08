package com.myfitnesspal.feature.profile.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class MyItemsFragment_MembersInjector implements MembersInjector<MyItemsFragment> {
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<PremiumService> premiumProvider;

    public MyItemsFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<PremiumService> provider3) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.premiumProvider = provider3;
    }

    public static MembersInjector<MyItemsFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<PremiumService> provider3) {
        return new MyItemsFragment_MembersInjector(provider, provider2, provider3);
    }

    public void injectMembers(MyItemsFragment myItemsFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(myItemsFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(myItemsFragment, (Glide) this.glideProvider.get());
        injectPremium(myItemsFragment, DoubleCheck.lazy(this.premiumProvider));
    }

    public static void injectPremium(MyItemsFragment myItemsFragment, Lazy<PremiumService> lazy) {
        myItemsFragment.premium = lazy;
    }
}
