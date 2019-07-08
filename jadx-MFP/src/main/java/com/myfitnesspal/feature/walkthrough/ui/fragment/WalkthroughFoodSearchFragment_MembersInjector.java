package com.myfitnesspal.feature.walkthrough.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.feature.search.service.SearchService;
import com.myfitnesspal.feature.walkthrough.util.WalkthroughUtil;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class WalkthroughFoodSearchFragment_MembersInjector implements MembersInjector<WalkthroughFoodSearchFragment> {
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<LocalizedStringsUtil> localizedStringsUtilProvider;
    private final Provider<SearchService> searchServiceProvider;
    private final Provider<UserEnergyService> userEnergyServiceProvider;
    private final Provider<WalkthroughUtil> walkthroughUtilProvider;

    public WalkthroughFoodSearchFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<WalkthroughUtil> provider3, Provider<SearchService> provider4, Provider<LocalizedStringsUtil> provider5, Provider<UserEnergyService> provider6) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.walkthroughUtilProvider = provider3;
        this.searchServiceProvider = provider4;
        this.localizedStringsUtilProvider = provider5;
        this.userEnergyServiceProvider = provider6;
    }

    public static MembersInjector<WalkthroughFoodSearchFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<WalkthroughUtil> provider3, Provider<SearchService> provider4, Provider<LocalizedStringsUtil> provider5, Provider<UserEnergyService> provider6) {
        WalkthroughFoodSearchFragment_MembersInjector walkthroughFoodSearchFragment_MembersInjector = new WalkthroughFoodSearchFragment_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6);
        return walkthroughFoodSearchFragment_MembersInjector;
    }

    public void injectMembers(WalkthroughFoodSearchFragment walkthroughFoodSearchFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(walkthroughFoodSearchFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(walkthroughFoodSearchFragment, (Glide) this.glideProvider.get());
        injectWalkthroughUtil(walkthroughFoodSearchFragment, (WalkthroughUtil) this.walkthroughUtilProvider.get());
        injectSearchService(walkthroughFoodSearchFragment, DoubleCheck.lazy(this.searchServiceProvider));
        injectLocalizedStringsUtil(walkthroughFoodSearchFragment, (LocalizedStringsUtil) this.localizedStringsUtilProvider.get());
        injectUserEnergyService(walkthroughFoodSearchFragment, DoubleCheck.lazy(this.userEnergyServiceProvider));
    }

    public static void injectWalkthroughUtil(WalkthroughFoodSearchFragment walkthroughFoodSearchFragment, WalkthroughUtil walkthroughUtil) {
        walkthroughFoodSearchFragment.walkthroughUtil = walkthroughUtil;
    }

    public static void injectSearchService(WalkthroughFoodSearchFragment walkthroughFoodSearchFragment, Lazy<SearchService> lazy) {
        walkthroughFoodSearchFragment.searchService = lazy;
    }

    public static void injectLocalizedStringsUtil(WalkthroughFoodSearchFragment walkthroughFoodSearchFragment, LocalizedStringsUtil localizedStringsUtil) {
        walkthroughFoodSearchFragment.localizedStringsUtil = localizedStringsUtil;
    }

    public static void injectUserEnergyService(WalkthroughFoodSearchFragment walkthroughFoodSearchFragment, Lazy<UserEnergyService> lazy) {
        walkthroughFoodSearchFragment.userEnergyService = lazy;
    }
}
