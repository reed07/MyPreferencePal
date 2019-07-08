package com.myfitnesspal.feature.help.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.feature.deleteaccount.service.DeleteAccountAnalyticsHelper;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class HelpListFragment_MembersInjector implements MembersInjector<HelpListFragment> {
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<DeleteAccountAnalyticsHelper> deleteAccountAnalyticsHelperProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<PremiumService> premiumServiceProvider;

    public HelpListFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<PremiumService> provider3, Provider<DeleteAccountAnalyticsHelper> provider4) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.premiumServiceProvider = provider3;
        this.deleteAccountAnalyticsHelperProvider = provider4;
    }

    public static MembersInjector<HelpListFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<PremiumService> provider3, Provider<DeleteAccountAnalyticsHelper> provider4) {
        return new HelpListFragment_MembersInjector(provider, provider2, provider3, provider4);
    }

    public void injectMembers(HelpListFragment helpListFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(helpListFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(helpListFragment, (Glide) this.glideProvider.get());
        injectPremiumService(helpListFragment, DoubleCheck.lazy(this.premiumServiceProvider));
        injectDeleteAccountAnalyticsHelper(helpListFragment, DoubleCheck.lazy(this.deleteAccountAnalyticsHelperProvider));
    }

    public static void injectPremiumService(HelpListFragment helpListFragment, Lazy<PremiumService> lazy) {
        helpListFragment.premiumService = lazy;
    }

    public static void injectDeleteAccountAnalyticsHelper(HelpListFragment helpListFragment, Lazy<DeleteAccountAnalyticsHelper> lazy) {
        helpListFragment.deleteAccountAnalyticsHelper = lazy;
    }
}
